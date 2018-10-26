/**
 * jquery自定义表单验证插件
 * 使用方法：
 * 在需要做验证的输入框，单选框，多选框，下拉框中加入validate属性
 * validate:写法如下：
 * {required:true,email:true,maxLength:50}
 * 如：
 * <input type="text" name="username" value="" validate="{required:true,maxlength:50}"/>
 * 注意一组单选框，或多选框  只需在其中一个input标记中 加入validate 属性
 * 如：
 * <input type="checkbox" name="a" value="1" validate="{required:true}" tipId="errorA"/>
 * <input type="checkbox" name="a" value="2" />
 * <input type="checkbox" name="a" value="3" />
 * 
 * tipId:错误信息显示的容器ID,设置了这个属性后，错误信息会显示到该标签中。
 * <input type="text" name="name" validate="{required:true}" tipId="errorA"/><label id="errorA"></label>
 * 
 * 
 * 调用方式:
 * 
 * $("a.save").click(function(){
 *			var rtn=$("#shipOrderForm").form().valid();
 *			if(rtn){
 *				$("#shipOrderForm").submit();
 *			}
 *	});
 *	同时也可以扩展验证的规则
 *	var rtn=$("#form").form({
 *		//扩展验证规则 追加到已有的规则中
 *		rules:[{
 *			//规则名称
 *			name:"QQ",
 *			//判断方法  返回 true 或false
 *			rule:function(v){
 *			},
 *			//错误的提示信息
 *			msg:""
 *		
 *		}],
 *		//显示的错误信息样式 element 当前验证的元素，msg：错误信息
 *		errorPlacement:function(element,msg){
 *		},
 *		//成功后的样式 element 当前验证的元素
 *		success:function(element){
 *		},
 *		excludes:":hidden"
 *		}).valid()
 *
 *	扩展 ： by  xianggang
 *	1、如果需要弹出错误提示的具体信息  可以这样使用
 *	var rtn=CustomForm.validate({returnErrorMsg:true});
 *		或者 $("form").valid({returnErrorMsg:true});
 *			其中form即为您要验证的表单或者域
 *	if(!rtn.success){
 *		$.ligerDialog.tipDialog($lang.tip.msg,"表单验证错误信息如下",rtn.errorMsg,null,function(){
 *			$.ligerDialog.hide();
 *		});
 *		return;
 *	}
 *	2、如果想要自定义错误提示信息 可以这样使用
 *		<input type="text" name="NAME" lablename="名称" class="inputText" 
 *			validate="{maxlength:100,required:true,英文字母:true}" 
 *			errormsgtips="{maxlength:'不能超过100',required:'必须填写1',英文字母:'请输入英文字母'}"  />
 *		其中的errormsgtips即为自定义错误提示信息的json对象。
 */
(function($) {
	$.extend($.fn, {
		// 表单初始化，可以添加自定义规则，出错处理和成功后的处理。
		form : function(conf) {
			if (conf) {
				if (conf.errorPlacement) {
					this.errorPlacement = conf.errorPlacement;
				};
				if (conf.rules) {
					for (var i = 0, len = conf.rules.length; i < len; i++) {
						this.addRule(conf.rules[i]);
					}
				};
				if (conf.success) {
					this.success = conf.success;
				};
				if (conf.excludes) {
					this.excludes = conf.excludes;
				}
				
			}
			var form = this;
			form.delegate("input[validate],select[validate],textarea[validate]", "blur", function() {
						form.handValidResult(this);
					});
			form.delegate("input[validate],select[validate],textarea[validate]", "focus", function() {
						form.success(this);
					});
					
			//处理验证ckeditor
			$("[validate].ckeditor",form).each(function(){
				var me= $(this),name = me.attr("name");
				setTimeout(function(){//等待ckeditor渲染完成再进行处理
					var editor=  CKEDITOR.instances[name],ck=me.next();
					if(editor){
						editor.on( 'blur', function(){
							form.handValidResult(me);
						});
						editor.on( "focus", function(){
							form.success(me);
						});
					}
				},1000);
				
			})	
		
			return this;
		},
		// 添加验证规则。
		// 扩展规则和现有的规则名称相同，则覆盖，否则添加。
		addRule : function(rule) {
			var len = this.rules.length;
			for (var i = 0; i < len; i++) {
				var r = this.rules[i];
				if (rule.name == r.name) {
					this.rules[i] = rule;
					return;
				}
			}
			this.rules.push(rule);
		},
		/**
		 * 判断元素是否在不需要校验的范围内。
		 */
		isInNotValid : function(obj) {
			if($(obj).is(":hidden"))return true;
			if (!this.excludes)
				return false;
			var scope = $(this.excludes, this);
			var aryInput = $(
					"input:text,input:hidden,textarea,select,input:checkbox,input:radio",
					scope);
			for (var i = 0, len = aryInput.length; i < len; i++) {
				var tmp = aryInput.get(i);
				if (obj == tmp) {
					return true;
				}
			}
			return false;
		},
		// 对所有有validate表单控件进行验证。
		valid : function(conf) {
			if(!conf){
				this.ignoreRequired=false;
			}
			else{
				if(conf.ignoreRequired==undefined){
					this.ignoreRequired=false;
				}
				else{
					this.ignoreRequired=conf.ignoreRequired;
				}
			}
			var _v = true, form = this,msgs={};
			$('[validate]', form).each(function() {
						var returnObj = form.handValidResult(this);
						for(var i in returnObj){
							if (i=="success"&&!returnObj.success)
							   _v = false;
							else
								msgs[i]=returnObj[i];
						}
					});
			if(conf&&conf.returnErrorMsg){
				var returnTextObj={};
				if(!_v){
					var errorMsg="<div>";
					var i=1;
					for(var m in msgs){
						if(m=="success")continue;
						errorMsg=errorMsg+(i++)+"、<span style='color:rgb(136, 10, 10);font-weight: bolder;'>"+
							m+"&nbsp&nbsp</span> <span style='color:red;'>"+msgs[m]+"</span></br>";
					}
					errorMsg+="</div>"
				}
				returnTextObj.success=_v;
				returnTextObj.errorMsg=errorMsg;
				return returnTextObj;
			}
			return _v;
		},
		// 显示表单处理结果
		handValidResult : function(obj) {
			//判断验证的控件是否是a标签，是的不处理，直接返回
			var isATag=$(obj).is("a");
			if(isATag) return;
			// 是否在不需要验证的范围内，在的话就不需要验证。
			var returnObj={};
			if (this.isInNotValid(obj)){
				returnObj.success=true;
				return returnObj;
			}
			var msg = this.validEach(obj);
			if (msg != '') {
				this.errorPlacement(obj, msg);
				returnObj.success=false;
				var opinionReg = /.*:.*:(.*)|opinion:(.*)/.exec($(obj).attr("name"));
				if(opinionReg&&opinionReg.length==2){
					var lableName = "";
					var lableTemp = $(obj).attr("lablename");
					if(lableTemp){
						lableName = lableTemp;
					}else{
						var title = $(obj).parents("tr").children("td.formTitle").text();
						title=title.replaceAll(":","");
						if(title)lableName = title;
					}
					var msgName=/.*:.*:(.*)|opinion:(.*)/.exec($(obj).attr("name"))[1]+" ("+lableName+")";
					returnObj[msgName]=msg;
				}
				return returnObj;
			} else {
				this.success(obj);
				if($(obj).hasClass('validError')){             //引对子表单的
					$(obj).removeClass('validError');
				}
				returnObj.success=true;
				return returnObj;
			}
		},
		// 验证单个控件。
		validEach : function(obj) {
			var element = $(obj), 
				rules = this.rules,
				validRule = element.attr('validate'),
				value = "",
				name = element.attr("name");
			// 处理单选框和多选框
			if(element.is(":checkbox,:radio")) {
				var parentObj = element.closest("[formtype]"),
					brotherObjs = (parentObj&&parentObj.length>0)?$(":checked[name='" + name + "']",parentObj):$(":checked[name='" + name + "']");
				
					brotherObjs.each(function() {
							if (value == "") {
								value = $(this).val();
							} else {
								value += "," + $(this).val();
							}
						});
			}else if (element.is("select")) {// 处理select
				value = element.find("option:selected").val();
                if(typeof(value)==undefined || value==null || $.trim(value) == '' || value.indexOf("请选择")>-1){
                	value = '';
                }
			}else if (element.hasClass("ckeditor") ) {// 处理ckeditor编辑器
				var editor=  CKEDITOR.instances[name];
				if($.isEmpty(editor))//ckeditor没渲染的，则取textarea的值
					value = element.val();
				else
					value = editor.getData();
			}else if(element.hasClass("selectFile")){//处理附件
				value = element.siblings("textarea[controltype='attachment']").val();
			}else if(element.hasClass("dicComboBox")||element.hasClass("dicComboTree")||element.hasClass("dicCombo")){ //处理数据字典
				var textboxname = element.attr("textboxname");
				if(!textboxname)
					textboxname = element.attr("name").replaceAll(":","") +"_id";// 修改bug，但是去除字符的有问题
				value = element.parents("td.formInput").find("input[name='"+textboxname+"']").val();
			}else {
				value = element.val();
			}
			// 处理值
			value = value == null ? "" : value.trim();

			// 获取json。
			var json = eval('(' + validRule + ')');
			var isRequired = json.required;
			
		
			
			// 非必填的字段且值为空 那么直接返回成功。
			if ((isRequired == false || isRequired == undefined) && value == "")
				return "";
			
			
			
			//忽略必填规则。
			if(this.ignoreRequired==true && value == "") return "";
			
			// 遍历json规则。
			for (var name in json) {
				var validValue = json[name];
				//验证规则
				var msg = this._validRules({
					rules:rules,//规则json
					ruleName:name,// 规则名称
					validValue:validValue,//验证的值
					value:value,//实际的值
					errormsgtips:element.attr("errormsgtips"),
					element:element
				});
				if (msg != '') 
					return msg;
			}
			return "";
		},
		/**
		 * 验证规则
		 **/
		_validRules :function(conf){
			var  _valid = true,
				rules = conf.rules,//规则json
				ruleName = conf.ruleName,// 规则名称
				validValue = conf.validValue,//验证的值
				value =conf.value,//实际的值
				element = conf.element;//当前对象
			for (var m = 0; m < rules.length; m++) {
				// 取得验证规则。
				var rule = rules[m];
				if (ruleName.toLowerCase() != rule.name.toLowerCase()) continue;
				// 验证规则如下：
				// email:true,url:true.
				//验证规则是boolean类型
				if ($.type(validValue)  === "boolean") 
					_valid = (!rule.rule(value) && validValue == true) ? false:true; 	
				else 
					_valid = rule.rule(value, validValue,element);
				
				if (!_valid){ //验证不通过返回消息
					var errorMsg=rule.msg;
					if(conf.errormsgtips){
						var errormsgtips=eval("("+conf.errormsgtips.replaceAll("'","\"")+")")
						for(var i in errormsgtips){
							if(i==ruleName){
								errorMsg=errormsgtips[i];
								break;
							}
						}
					}
					return this.format(errorMsg, validValue);
				}
			}
			return "";
		},
		/**
		 * 消息格式化
		 **/
		format:function(msg,args){
			//boolean类型的直接返回
			if ($.type(args)  === "boolean") 
				return  msg;
			if (!$.isArray(args)) //不是数组类型的
				args = [args];
			//数组类型的
			$.each(args,function(d, e) {
				msg = msg.replace(RegExp("\\{" + d + "\\}", "g"), e)
			});
			return msg;		
		},
		// 错误显示位置。
		errorPlacement : function(element, msg) {
			var errorId = $(element).attr("tipId");
			if (errorId) {
				$('#' + errorId).find('label.error').remove();
				$('#' + errorId).append($('<label class="error">' + msg
						+ '</label>'));
				return;
			}
			var parent =$(element).parent();
			parent.find('label.error').remove();
			parent.append($('<label class="error">' + msg
				+ '</label>'));
				
		},
		// 验证成功时，删除错误提示信息。
		success : function(element) {
			var errorId = $(element).attr("tipId");
			if (errorId) {
				$('#' + errorId).find('label.error').remove();
				return;
			}
			$(element).parent().find('label.error').remove();	
		},

		// 内置的规则。
		rules : [{
					name : "required",
					rule : function(v) {
						if (v == "" || v.length == 0)
							return false;
						return true;
					},
					msg : $lang_js.customValid.rules.required
				}, {
					name : "number",
					rule : function(v) {
						return /^-?[\$|￥]?((\d{1,3}(,\d{3})+?|\d+)(\.\d{1,5})?)$/
								.test(v.trim());
					},
					msg : $lang_js.customValid.rules.number
				}, {
					name : "variable",
					rule : function(v) {

						return /^[A-Za-z_0-9]*$/gi.test(v.trim());
					},
					msg : $lang_js.customValid.rules.variable
				}, {
					name : "fields",
					rule : function(v){
						return /^[A-Za-z]{1}([a-zA-Z0-9_]{1,17})?$/gi.test(v.trim());
					},
					msg : $lang_js.customValid.rules.fields
				},{
					name : "minLength",
					rule : function(v, b) {
						return (v.length >= b);
					},
					msg : $lang_js.customValid.rules.minLength
				}, {
					name : "maxLength",
					rule : function(v, b) {
						return (v.trim().length <= b);
					},
					msg : $lang_js.customValid.rules.maxLength
				}, {
					name : "rangeLength",
					rule : function(v, args) {
						return (v.trim().length >= args[0] && v.trim().length <= args[1]);
					},
					msg : $lang_js.customValid.rules.rangeLength
				}, {
					name : "email",
					rule : function(v) {
						return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i
								.test(v.trim());
					},
					msg : $lang_js.customValid.rules.email
				}, {
					name : "url",
					rule : function(v) {
						return /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i
								.test(v.trim());
					},
					msg : $lang_js.customValid.rules.url
				}, {
					name : "date",
					rule : function(v) {
						var re = /^[\d]{4}-[\d]{1,2}-[\d]{1,2}\s*[\d]{1,2}:[\d]{1,2}:[\d]{1,2}|[\d]{4}-[\d]{1,2}-[\d]{1,2}|[\d]{1,2}:[\d]{1,2}:[\d]{1,2}$/g
								.test(v.trim());
						return re;
					},
					msg : $lang_js.customValid.rules.date
				}, {
					name : "digits",
					rule : function(v) {
						return /^\d+$/.test(v.trim());
					},
					msg : $lang_js.customValid.rules.digits
				}, {
					name : "equalTo",
					rule : function(v, b) {
						var a = $("#" + b).val();
						return (v.trim() == a.trim());
					},
					msg : $lang_js.customValid.rules.equalTo
				}, {
					name : "range",
					rule : function(v, args) {
						return v <= args[1] && v >= args[0];
					},
					msg : $lang_js.customValid.rules.range
				}, {
					name : "maxvalue",
					rule : function(v, max) {
						return v <= max;
					},
					msg : $lang_js.customValid.rules.maxvalue
				},{
					name : "minvalue",
					rule : function(v, min) {
						return v >= min;
					},
					msg :$lang_js.customValid.rules.minvalue
				},{
					// 判断数字整数位
					name : "maxIntLen",
					rule : function(v, b) {
						if(/^[\$|￥]/.test(v.trim())){
							return (v + '').split(".")[0].replaceAll(",","").replaceAll("￥|$","").length <= b+1;
						}else{
							return (v + '').split(".")[0].replaceAll(",","").length <= b;
						}
					},
					msg : $lang_js.customValid.rules.maxIntLen
				}, {
					// 判断数字小数位
					name : "maxDecimalLen",
					rule : function(v, b) {
						return (v + '').replace(/^[^.]*[.]*/, '').length <= b;
					},
					msg : $lang_js.customValid.rules.maxDecimalLen
				}, {
					/**
					 * 判断日期开始范围{dateRangeStart:{
					 * 	target:'endXXX',
					 * 	mode:'name',
					 *  range:''
					 * }}
					 *  target：查找对象 结束时间的日期对象（必填）
					 *  mode:查找方式（默认是：name） 【可选值：id,name,class】
					 * 	range:查找范围（默认是‘’，查找所有body） 【可选值：'',main,sub】
					 * </br>
					 * 支持旧版的id查找 dateRangeStart：'xxxID' //结束时间的日期对象ID
					 * 
					 */
					name : "dateRangeStart",
					rule : function(v, b,e) {
						if(!$.isPlainObject(b))
							 b = { target:b,mode:'id'};
						var target = b.target,//查找对象
							mode = b.mode?b.mode:'name',//查找方式
							range = b.range?b.range:'';//查找范围
							val ='';
						if(mode == 'name'){
							if(range =="" || range =="main" ){//空或者主表
								val = $("[name='" +target+"']").val();
							}else if(range ="sub"){//子表
								val = $(e).closest('[formtype]').find("[name='" +target+"']").val();
							}
						}else if(mode == 'id'){
							if(range =="" || range =="main" ){//空或者主表
								val = $("#" +target).val();
							}else if(range ="sub"){//子表
								val = $(e).closest('[formtype]').find("#" +target).val();
							}
						}else{
							if(range =="" || range =="main" ){//空或者主表
								val = $("." +target).val();
							}else if(range ="sub"){//子表
								val = $(e).closest('[formtype]').find("." +target).val();
							}
						}
						return daysBetween(val, v);
					},
					msg : $lang_js.customValid.rules.dateRangeStart
				}, {
					/**
					 * 判断日期开始范围{dateRangeStart:{
					 * 	target:'startXXX',
					 * 	mode:'name',
					 *  range:''
					 * }}
					 *  target：查找对象 开始时间的日期对象（必填）
					 *  mode:查找方式（默认是：name） 【可选值：id,name,class】
					 * 	range:查找范围（默认是‘’，查找所有body） 【可选值：'',main,sub】
					 * </br>
					 * 支持旧版的id查找 dateRangeStart：'xxxID' //开始时间的日期对象ID
					 * 
					 */
					name : "dateRangeEnd",
					rule : function(v,b,e) {
						if(!$.isPlainObject(b))
							 b = { target:b,mode:'id'};
						var target = b.target,//查找对象
							mode = b.mode?b.mode:'name',//查找方式
							range = b.range?b.range:'';//查找范围
							val ='';
						if(mode == 'name'){
							if(range =="" || range =="main" ){//空或者主表
								val = $("[name='" +target+"']").val();
							}else if(range ="sub"){//子表
								val = $(e).closest('[formtype]').find("[name='" +target+"']").val();
							}
						}else if(mode == 'id'){
							if(range =="" || range =="main" ){//空或者主表
								val = $("#" +target).val();
							}else if(range ="sub"){//子表
								val = $(e).closest('[formtype]').find("#" +target).val();
							}
						}else{
							if(range =="" || range =="main" ){//空或者主表
								val = $("." +target).val();
							}else if(range ="sub"){//子表
								val = $(e).closest('[formtype]').find("." +target).val();
							}
						}
						return daysBetween(v, val);
					},
					msg : $lang_js.customValid.rules.dateRangeEnd
				},
				{
					// 空的字段（永远通过验证,返回true）  防止在验证JSON中出现有多余的逗号
					name : "empty",
					rule : function(v, b) {
					//	var start = $("#" + b).val();
						return true;
					},
					msg : ""
				},
				{
					// 不能以数字开头
					name : "noDigitsStart",
					rule : function(v) {
						return !/^(\d+)(.*)$/.test(v.trim());
					},
					msg : $lang_js.customValid.rules.noDigitsStart
				}, {
					name : "varirule",
					rule : function(v) {

						return /^[a-zA-Z]\w*$/.test(v.trim());
					},
					msg : "只能为字母开头,允许字母、数字和下划线"
				}]
	});

})(jQuery);
