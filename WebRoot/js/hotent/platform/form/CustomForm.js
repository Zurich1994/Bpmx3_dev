$(function() {
	CustomForm = {
		subFileJsonMap:null,                                  //子表权限对象
		onEditCallBack:null,
		tables: {},
		init : function() {
			var self=this;
			self.isMainTableRequest();    //主表的权限验证补充
			if(typeof subFilePermissionMap == 'undefined') return;
			if(subFilePermissionMap){
				//获得子表权限
				self.subFileJsonMap = subFilePermissionMap;//转换为json对象（通过后台生成HTML时直接获取）
				var subFileJsonList =self.subFileJsonMap["subFileJsonList"];                    //子表新版本字段权限
				var subTableShowList =self.subFileJsonMap["subTableShowList"];               //子表显示与否权限（新功能）
				self.subTablePermission(self,subFileJsonList,subTableShowList);       //新版本
				
				self.initUI();
				self.initComdify();
				self.validate();
			}	
		},
		
		//处理新版本的子表权限
		subTablePermission:function(obj,subFileJsonList,subTableShowList){
			$('div[type=subtable]').each(function() {
				//子表
				var table = $(this);
				var tableName = table.attr('tableName').toLowerCase();
				table.attr('tableName',tableName);
				table.attr('right','w');//子表非隐藏状态，则需要给编辑或必填权限才能显示
				var show = {};
				//是否 只读  编辑  隐藏   				决定子表的权限
				for ( var cn = 0; cn < subTableShowList.length; cn++) {
					var tableShow=subTableShowList[cn];
					if(tableName==tableShow.tableName.toLowerCase()){
						show = tableShow.show;
						if(show['y']== 'true'){    //子表编辑功能：子表表单不可见（y）
							table.attr('show','false');
							table.attr('right','y');  //子表编辑功能
							table.hide();
						}else{
							table.attr('show','true');  //子表表单可见
							if(show['addRow'] == 'false'){ // 不允许添加行
								//$("a.add",table).remove();    //只读时去除子表的添加功能
								$("a.link",table).remove();		//只读时，去除所有a.link的按钮
								
								$("a.extend",table).each(function(){	// 导入导出按钮的特殊处理      
									var extend=$(this);
									if(typeof extend.attr("importexcel") != "undefined"
										||typeof extend.attr("exportexcel") != "undefined"){	
										extend.remove();                
									}
								});
								
								if($(".subTableToolBar",table).not("a")){
									$(".subTableToolBar",table).remove(); // 删除包含打印按钮的div;
								}
								
							}else if(show['b']== 'true'){
								table.attr('right','b');  //子表编辑功能或者必填
							}
							
						}
						break;
					}
				}
				var subList =[];
				for ( var i = 0; i < subFileJsonList.length; i++) {
					var objPermission=subFileJsonList[i];
					if(tableName!=objPermission.tableName.toLowerCase()) continue;
				    var right = objPermission.power;
				    var name = "s:"+tableName+":"+objPermission.title;      //字段在HTML的真实NAME
					if(right!="w" && right!="b"){      						  //非写权限(只读)
						//处理超链接
						$("a.link",table).each(function(){      //只读时，历遍超链接
							var link=$(this);
							if(name==link.attr('name')||name==link.attr('field')){
								link.remove();                 //只读时，删除(可能会有多个一样的，要删除所以不能用return false break)
							}                            
						});
						
						//处理自定义按钮对话框的权限问题（这里只是处理字表上的）
						$("a.extend",table).each(function(){      //只读时，历遍超链接中自定义对话框的按钮
							var extend=$(this);
							var permissionName = extend.attr('permissionName');
							if(permissionName){
								var filedName = "s:"+tableName+":"+permissionName;
								if(filedName==name){
									extend.remove(); //只读时，删除(可能会有多个一样的)
								}
								return true;
							}
							var jsonStr = extend.attr('eventbtn');							
							if(jsonStr != null && 'undefined' != jsonStr.toLowerCase() && jsonStr.length>2 ){
								var jsonObjArray = eval('(' + jsonStr + ')');
								for(var j =0,jsonObj;jsonObj=jsonObjArray[j++];){
									var fileds = jsonObj.fields;
									for ( var i = 0; i < fileds.length; i++) {
										var targetArr = fileds[i].target;
										for ( var k = 0,target;target=targetArr[k++];){
											var filed =target.split("-")[1];
											var filedName = "s:"+tableName+":"+filed;
											if(filedName==name){
												extend.remove();                 //只读时，删除(可能会有多个一样的)
											    break;
											}
											
										}
											
									}
								}
							}                            
						});
						
						
						//处理其它
						$("input:visible,textarea:visible,select:visible",table).each(function(){	
							var me=$(this);						
							if(name.toLowerCase()!=me.attr('name').toLowerCase()) return true; //即是 continue;
							me.attr("right",right);
							var isSelect=me.is("select");
							var isTextarea=me.is("textarea");
							var isCheckboxOrRadio = false;   //单选或者复选
							var type = me.attr("type");  //标签种类
							if(type=="checkbox"||type=="radio"){
								isCheckboxOrRadio = true;
							}
							var val ='';
							var validRule = me.attr("validate");
							if ( validRule && 'undefined' != validRule.toLowerCase() ){
								var json = eval('(' + validRule + ')');
								if(json.isWebSign){
									var lablename = me.attr("lablename");
									if(isSelect){
										// var str = me.find("option[value='"+me.attr("val")+"']").text();    //和值相对应的显示  select中有一个val保存值的
										val = '<input name="'+name+'"  lablename="'+lablename+'" type="hidden" validate="{\'isWebSign\':true}" value="'+me.attr("val")+'" />';
									}else if(isTextarea){
										val = '<textarea name="'+name+'" lablename="'+lablename+'" class="hidden"  validate="{\'isWebSign\':true}" >'+me.val()+'</textarea>'
									}else{
										val = '<input name="'+name+'"  lablename="'+lablename+'"  type="hidden" validate="{\'isWebSign\':true}" value="'+me.val()+'" />';
									}
								}				
							}	
							if(isSelect){
								val += obj.selectValue(me);
								me.before(val);
								me.remove();
							}else if(isCheckboxOrRadio){
								me.attr("disabled","disabled");
							}else{
								val += me.val();
								var refid=me.attr("refid");
								if(typeof(refid)!=undefined && refid!=null&&refid!=''){
									var refids=me.prev("input[name='"+refid+"']").val();
									var linkType=me.attr("linktype");
									var tempval="<div>";
									var nametemp = new Array();
									var str = me.val();
									
									if(typeof(str)!=undefined && str!=null&&str!=''){
										nametemp = str.split(",");
									}
									
									if(refids){
										var id=refids.split(",");											
										for(var i=0;i<id.length; i++){
											// 先去掉浮标背景
											//tempval+="<span class='backgrounddiv'><a refid='"+id[i]+"' href='#' linktype='"+linkType+"' style='text-decoration:none'>"+nametemp[i]+"</a> </span>";
											tempval+="<span>"+nametemp[i]+"</span>";
										}
									}else{
										for(var i=0;i<nametemp.length; i++){
											//tempval+="<span class='backgrounddiv'>"+nametemp[i]+"</span>";
											tempval+="<span>"+nametemp[i]+"</span>";
										}
									}
									tempval+="</div>";
									val = tempval;
								}
								//替换 /n 为<br/>
								if(val) val = val.replace(/\n/g,"<br/>"); 
								me.before(val);
								me.remove();
							}															
						});
						
				    }else if(right=="b"){
			        	$("input:visible,textarea:visible,select:visible,a:visible",table).each(function(){
			        		var me=$(this);	
			        		me.attr("right",right);
							var isSelect=me.is("a");         //这个一般情况是子表的附件上传功能的 链接必填问题
							var objName = "";
							if(isSelect){
								objName = me.attr('field');
							}else{
								objName = me.attr('name');
							}
							if(name!=objName) return true;
							var validRule = me.attr("validate");
							if ( validRule != null && 'undefined' != validRule.toLowerCase() && validRule.length>2 ){ 
								var json = eval('(' + validRule + ')');		
								if(!json.required){
									var jsonStr = validRule.substring(0, validRule.lastIndexOf('}'));
									jsonStr +=",\'required\':true}";    //加上必填
									me.attr("validate",jsonStr);    
								}				
							}else{
								me.attr("validate","{\'required\':true}");     //必填
							}
							//	return false;    //找相就的就跳出each 相当于break  可能会有多个相同名称的
						});
			        }
				}
							
				//没有form类型的属于表内编辑模式
				var row = table.find('[formType=form]');
				//表单
				var form;
				//窗口编辑模式
				if(row.length > 0) {
					form = table.find('[formType=window]');
				}
				//页内编辑模式
				else {
					row = table.find('[formType=edit]');
				}
				table.data('row', $('<div></div>').append(row.clone()).html());				
				row.css('display', 'none').html('');
				//窗口编辑模式
				if(form) {
					//修复窗口模式附件展示
					$("div[name='div_attachment_container']",form).each(function(){
						var atta  =$("textarea[controltype='attachment']",$(this));
						var attaName =  atta.attr("name");
						if($.isEmpty(attaName)) return true;
						$("td[fieldname='"+attaName+"']",table).each(function() {
								AttachMent.insertHtml($(this),$(this).text());
						});
					});
					
					table.data('form', '<form>' + $('<div></div>').append(form.clone()).html() + '</form>');
					table.data("formProperty",{width:form.width() ,height: form.height()});
					form.remove();
				}
				obj.tables[tableName] = table;
				//权限
				var right = table.attr('right') ;
				
				//子表的权限(可以编辑或必填或只读)
				var canWrite = show['addRow'] != 'false';
				$(this).find('[formType="newrow"]').each(function() {
					if(!canWrite) return true;
					obj.addBind($(this), table);
				});
				//子表是否可以编辑
				if(canWrite) {
					var menu = $.ligerMenu({top : 100,left : 100,width : 130,
						items : [{
								text : '添加',
								click : function() {
									obj.add($(menu.target).closest('div[type=subtable]'));
								}
							}]
					});
					table.find('[formType]:first').prev().bind("contextmenu", function(e) {
						menu.target = e.target;
						menu.show({top : e.pageY,left : e.pageX});
						return false;
					});
				}					
				obj.handButton(table,canWrite);
				
				//要在完全初始化后再做必填的样式处理
				if(right=='b' && table.find('[formtype]:visible').length<1 && !(table.hasClass('validError'))){     //是必填时且子表没有数据行时
					//去掉必填样式							
					table.addClass('validError');
				}

			});	
		},
		
		
		//处理添加和删除按钮。
		handButton:function(table,canWrite){
			var self=this;
			if(!canWrite) {
				$(".add",table).addClass("disabled");
				$(".del",table).addClass("disabled");
				return;
			};
			
			$(".add",table).click(function(){
				self.add(table);
				//TODO
				self.initSubQuery();
				self.subSelectorInit();
				
			});
			
			table.delegate(".del", "click", function(){
				var t = $(this).closest('[formType]');
				
				$("input",t).each(function(){//设置被删除的数据的Input全为0
					var me = $(this);
					me.val("");
				});
				FormUtil.InitMathfunction(t);//触发其计算函数
				t.remove();//之后删除属性
				
			});
		},
		
		
		//处理块模式和表内编译模式下千分位
		initComdify:function(){
			$("div[type='custform']").delegate('[showtype]', 'blur',function (){
					CustomForm.delaComdify($(this));
				});
		},
		
		//处理弹出框模式下千分位
		initComdifyForWindow:function(){
			$("[formtype='window']").delegate('[showtype]', 'blur',function(){
					CustomForm.delaComdify($(this));
				});
		},
		
		//处理千分位
		delaComdify:function(varObj){
			var me=$(varObj);
			var value = me.val();
			var json=null;
			try{
				var jsonStr=me.attr("showtype");
				json=eval('('+jsonStr+')');
			}
			catch(err){}
			if(json!=null){
				var coinvalue=json.coinValue;
				var iscomdify=json.isShowComdify;
				var decimallen=json.decimalValue;
				//去除货币标签
				if (coinvalue && value.split(coinvalue) != -1) {
					var ary = value.split(coinvalue);
					value = ary.join("");
				}
				
				if (value.indexOf(',') != -1) {
					value = $.toNumber(value);
				}
				if(iscomdify && iscomdify=='1'){
					var value = $.comdify(value);
				}
				
					// 小数处理
				if (decimallen > 0 && value) {
					var zeroLen = '';
					if (value.indexOf('.') != -1) {
						var ary = value.split('.');
						var temp = ary[ary.length - 1];
						if (temp.length > 0 && temp.length < decimallen) {
							for (var i = 0; i < decimallen- temp.length; i++) {
								zeroLen = zeroLen + '0';
							}
							value = value + zeroLen;
						}
					} else {
						for (var i = 0; i < decimallen; i++) {
							zeroLen = zeroLen + '0';
						}
						value = value + '.' + zeroLen;
					}
				}
				//添加货币标签
				if (coinvalue && value) {
					value = coinvalue + value;
				}
			}
			me.val(value);
		},
		
		
		/**
		 * 初始化表单界面。
		 * 
		 * @param parent
		 */
		initUI : function(parent) {
			$('input[type="checkbox"]').each(function() {
				var value=$(this).val();
				var data=$(this).attr('data');
				if(!data||data==''||data=='null')return;
				var ary=data.split(",");
				for(var i=0;i<ary.length;i++){
					if(value!=ary[i]) continue;
					$(this).attr("checked","checked");
				}
			});
			
			var filter='input,textarea,.dicComboTree,.dicComboBox,.dicCombo';
			if (parent==undefined) {
				parent = $('body div[type=custform]');
				if(!parent||parent.length==0)parent = $("body");
			}else{
				//处理初始化附件的bug
				AttachMent.init('w', $("div[name='div_attachment_container']",parent));
			}
			//下拉框默认选中,在下拉框定义一个val属性,使用脚本选中。
			$("select[name][val]",parent).each(function(){
				var obj=$(this),val= obj.attr("val");
				if($.isEmpty(val))
					val= obj.val();		
				obj.val(val);
			});
			
			parent.find(filter).each(function() {
				if ($(this).is('.dicComboTree,.dicComboBox,.dicCombo')) {
					if($(this).closest('[type=append]').length<=0){//是模板的也不用初始化
						$(this).htDicCombo();
					}
				}
				//处理默认日期
				if ($(this).is('.Wdate[displayDate=1]')){
					var me = $(this);
					if($.isEmpty(me.val())){			
						var datefmt = me.attr("datefmt");
						var nowDate=new Date().Format(datefmt);
						me.val(nowDate);
					}
				}	
			});
			if (parent.is('form')) {
			
				parent.data('validate', this.getValidate(parent));
			} else {
				var v = parent.closest('form');
				this.valid = this.getValidate(v);
			}
			$('div[type=subtable]').each(function() {
				var subTable=$(this);
				CustomForm.handRow('edit',subTable);
			});
		},
		//添加一行数据
		//参数，子表区域，在那个地方插入。
		add : function(table, beforeElement) {
			var self=this;
			var right=table.attr('right');
			if(right!="w" && right!="b"){
				return;
			}
			//判断是否是使用窗口方式编辑数据。
			var frm=table.data('form');
			if (frm) {
				self.openWin('添加', $(frm), table, beforeElement, function(form, table, beforeElement) {
					//校验数据
					var v = form.data('validate');
					if (!v.valid()) {
						return false;
					} 
					var row = $(table.data('row'));
					//添加行数据
					self.addRow(row, form);
				
					self._add(table, row, beforeElement);
					self.handRow('add',table);
					return true;
				});
				//弹窗模式下初始化千分位货币处理函数
				self.initComdifyForWindow();
			} 
			//使用的行内编辑。
			else {
				var row = $(table.data('row'));
				this._add(table, row, beforeElement);
			}
			self.handRow('add',table);
		},
		
		handRow:function(type,table){
			if(typeof(handRowEvent)=="function"){
				handRowEvent(type,table);
			}
		},
		//添加一行数据往行数据中添加隐藏域，同时设置显示的数据。
		addRow:function(row,form){
			form.find('input:text[name],input:hidden[name], textarea[name],select[name]').each(function() {
				var name=$(this).attr('name');
				var val=$(this).val();
				//添加隐藏表单。
				row.append($('<input type="hidden"/>').attr('name', name).val(val));
				//修改表格的文本显示。
				var filter="[fieldName='"+name+"']";
				var objTd=$(filter,row);
				if(objTd.length>0){
					var controltype = $(this).attr('controltype');
					if(!$.isEmpty(controltype) && controltype=='attachment'){
						AttachMent.insertHtml(objTd,val);
					}else if(!$.isEmpty(controltype) && controltype=='select'){
						var text = $(this).find("option:selected").text();
						objTd.text(text);
					}else{
						objTd.text(val);
					}	
				}
				$(this).val('');
			});
			
			
			//回填checkbox的值。
			form.find('input:checkbox,input:radio').each(function() {
				var name=$(this).attr('name');
				var value=$(this).val();
				
				var filterHidden="input[name='"+ name +"']";
				var isChecked=($(this).attr("checked")!=undefined);
				var obj=$(filterHidden,row);
				
				var filter="[fieldName='"+name+"']";
				var objTd=$(filter,row);
				
			
				
				var val=(isChecked)?value:"";
				if(obj.length==0){
					row.append($('<input type="hidden"/>').attr('name', name).val(val));
				}
				else{
					var existVal = obj.val()=="" ? val : obj.val() ;
					if(existVal!="" && val!=""){
						existVal += "," +val;
					}
					obj.val(existVal);
					val = existVal;
				}
				if(objTd.length>0){
					objTd.text(val);
				}
			});
		},
		_add : function(table, newRow, beforeElement) {
			//处理newRow的字段的必填
			$("input,textarea,select",newRow).each(function(){
        		var me=$(this);	
        		//处理默认日期
				if (me.is('.Wdate[displayDate=1]') && $.isEmpty(me.val())){
					var datefmt = me.attr("datefmt");
					var nowDate=new Date().Format(datefmt);
					me.val(nowDate);
				}
        		var validRule = me.attr("validate");
				if ( validRule != null && 'undefined' != validRule.toLowerCase() && validRule.length>2 ){ 
					var json = eval('(' + validRule + ')');		
					if(json.required){
						me.addClass("validError");
					}
				}
			});
			if (beforeElement) {
				$(beforeElement).before(newRow);
			}
			//在最后面加入
			else {
				table.find('[formType]:last').after(newRow);
			}
			//初始化界面
			this.initUI(newRow);
			//添加右键绑定
			this.addBind(newRow, table);
			FormUtil.triggerChoice(newRow);
			FormUtil.initEventBtn(newRow);
			//删除必填样式
			if(table.hasClass('validError')){
				table.removeClass('validError');
			}
			// me_zlklzgzAddRowEvent(); 你需要写如下方法 
			var addRowAfterEvent = eval('window.'+table.attr("tablename")+"AddRowAfterEvent")
			if(addRowAfterEvent) addRowAfterEvent(newRow);
			
		},
		
		/**
		 * 添加行数据的右键事件绑定。
		 * @param row
		 * @param table
		 */
		addBind: function(row, table) {
			//是否需要编辑菜单项目
			//如果该表弹出窗口定义，那么就不需要编辑菜单项目。
			var needEdit = (this.tables[table.attr('tableName')].data('form') != null);
			var menu = this.getMenu(needEdit);
			row.bind("contextmenu", function(e) {
				menu.target = e.target;
				menu.show({top : e.pageY,left : e.pageX});
				return false;
			});
		},
		/**
		 * 编辑行数据。
		 * @param table
		 * @param row
		 */
		edit : function(table, row) {
			var self=this;
			var tableName = table.attr('tableName');
			//获取表单
			var form = $(this.tables[tableName].data('form'));
			
			
			//对表单数据进行初始化
			self.initFormData(form,row);
			
			form.data('row', row);
			this.openWin('编辑', form, table, null, function(form, table) {
				var v = form.data('validate');
				var rtn=v.valid();
				if(!rtn) return false;
				
				var row = form.data('row');
				//对表单进行遍历
				self.setRowData(form,row);
				return true;
				
			});
		},
		initFormData:function(form,row){
			form.find('input:text,textarea,select,input[type="hidden"]').each(function() {
				var name= $(this).attr('name');
				var value = row.find('[name="' + name+ '"]').val();
				$(this).val(value);
			});
			form.find('input:checkbox,input:radio').each(function(){
				var name=$(this).attr("name");
				var chkValue=$(this).val();
				var value=row.find('[name="' + name+ '"]').val();
				
				if(value.indexOf(chkValue)!=-1){
					$(this).attr("checked","checked");
				}
			});
		},
		setRowData:function(form,row){
			var self=this;
			//对表单进行遍历
			form.find('input:text, textarea,input[type="hidden"],select').each(function() {
				var name=$(this).attr('name');
				var val=$(this).val();
				//修改隐藏域的数据值
				var objHidden=$("input[name='"+name+"']",row);
				objHidden.val(val);
				//修改表格的文本显示。
				var filter="[fieldName='"+name+"']";
				var objTd=$(filter,row);
				if(objTd.length<=0) return true;
				//处理附件
				var controltype = $(this).attr('controltype');
				if(!$.isEmpty(controltype) && controltype=='attachment'){
					AttachMent.insertHtml(objTd,val);
				}else{
					objTd.text(val);
				}	
			});
			
			form.find('input:checkbox,input:radio').each(function(){
				var name=$(this).attr('name');
				var objHidden=$("input[name='"+name+"']",row);
				objHidden.val("");
				//修改表格的文本显示。
				var filter="[fieldName='"+name+"']";
				var objTd=$(filter,row);
				if(objTd.length>0){
					objTd.text("");
				}
			});
			
			form.find('input:checkbox:checked,input:radio:checked').each(function(){
				var name=$(this).attr('name');
				var value=$(this).val();
				var objHidden=$("input[name='"+name+"']",row);
				var filter="[fieldName='"+name+"']";
				var objTd=$(filter,row);
				
				var hidValue=objHidden.val();
				if(hidValue){
					objHidden.val(hidValue +"," +value);
					objTd.text(hidValue +"," +value);
				} else{
					objHidden.val(value);
					objTd.text(value);
				}
				
			});
			
		},
		
		/**
		 * 获取验证器。
		 * @param target 为一个表单。
		 * @returns
		 */
		getValidate : function(target) {
			return target.form({
				/**
				 * 错误消息处理
				 */
				errorPlacement : function(el, msg) {
					var element=$(el),corners =['right center','left center'],flipIt= element.parents('span.right').length > 0;
					element.addClass('validError');
					
					//添加必填样式
					var parentTd = element.closest("td");
					if(parentTd){
						var formTitle = parentTd.prev("td.formTitle");
						if(formTitle){
							var span = $("span.red", formTitle);
							if(!span || span.length==0){
								formTitle.append($("<span class='red'>*</span>"));
							}
						}
					}
					
					if(element.hasClass("ckeditor")){
						setTimeout(function(){
							element = element.next();
							element.css("border","1px solid red");
						},1000);
					}else if(element.hasClass("Wdate")||element.is('textarea')){
						element.css("border","1px solid red");
					}else if(element.is("select")||element.attr('type')&&(element.attr('type')=='checkbox'||element.attr('type')=='radio')){
						var name = element.attr('name');
						if(!name)return;
						var priElement = $("*[name='"+name+"']",$("span.select_contain_span"));
						if(priElement.length>0)return;
						element.removeClass('validError');
						var errorSpan = $('<span></span>').css({"border":"1px solid red","padding":"1px"}).addClass("select_contain_span");
						element.before(errorSpan);
						errorSpan.append(element);
					}
					
					if(!$(msg).is(':empty')){
						element.qtip({
							overwrite:false,
							content : msg,
							position:{
								my:corners[flipIt?0:1],
								at:corners[flipIt?1:0],
								viewport:$(window)
							},
					        show:{
			   			     	effect: function(offset) {
			   						$(this).slideDown(100);
			   					}
		   			        },
		   			        hide:{
		   			        	event:'click mouseleave',
		   			        	leave: false,
		   			        	fixed:true,
		   			        	delay:200
		   			        },
		   			        style:{
								classes:'ui-tooltip-red'
							}
						});
					}else{
						element.qtip("destroy");
					}
				},
				/**
				 * 成错误消息
				 */
				success : function(el) {
					var element=$(el);
					if(element.hasClass("ckeditor")){
						element = element.next();
						element.css("border","");
					}else if(element.hasClass("Wdate")||element.is('textarea')){
						element.css("border","1px solid #999");
					}else if(element.is("select")||element.attr('type')&&(element.attr('type')=='checkbox'||element.attr('type')=='radio')){
						var selectSpan = element.parents("span.select_contain_span");
						if(!selectSpan||selectSpan.length==0){
							var name = element.attr('name');
							if(!name)return;
							var priElement = $("*[name='"+name+"']",$("span.select_contain_span"));
							if(!priElement||priElement.length==0)return;
							var tipSpan = priElement.parents("span.select_contain_span");
							var formtype = priElement.parents("[formtype]");
							if(formtype&&formtype.length>0){
								$("[name='"+name+"']",formtype).each(function(){
									$(this).removeClass('validError');
									$(this).qtip("destroy");
									$(this).unbind('mouseover');
								});
							}
							else{
								$("[name='"+name+"']").each(function(){
									$(this).removeClass('validError');
									$(this).qtip("destroy");
									$(this).unbind('mouseover');
								});
							}
							tipSpan.before(priElement);
							tipSpan.remove();
						}						
						else{
							selectSpan.before(element);
							selectSpan.remove();
						}
					}else if(element.attr('type')=='subtable'){
						
					}
					element.removeClass('validError');
					element.qtip("destroy");
					element.unbind('mouseover');
				}
				,rules:com.hotent.form.rule.CustomRules
			});
		},
		validate:function(conf){
			return this.valid.valid(conf);
		},
		//处理有数据格式定义的数据。
		handNumberData:function(obj){
			var value=$(obj).val();
			var showType=$(obj).attr("showtype");
			if(!showType) return value;
			try{
				showType=showType.replaceAll("'","\"");
				var json=jQuery.parseJSON(showType);
				var coinvalue = json.coinValue;
				var isShowComdify = json.isShowComdify;
				if (coinvalue != null && coinvalue != '' && value.split(coinvalue) != -1) {
					var ary = value.split(coinvalue);
					value = ary.join("");
				}
				if (isShowComdify && value.split(",") != -1) {
					var temp = value.split(",");
					value = temp.join("");
				}
			}
			catch(err){}
			return value;
		},
		/**
		 * 获取数据。
		 * 返回json数据。
		 */
		getData: function() {
			var self=this;
			// 主表数据
			var main = {
				fields:{}
			};
			//取主表的字段。
			$("input:text[name^='m:'],input:hidden[name^='m:'],textarea[name^='m:'],select[name^='m:']").each(function() {
				var name = $(this).attr('name');
				var value=self.handNumberData(this);
				main.fields[name.replace(/.*:/, '')] = value;
			});
			
			$("textarea[name^='m:'].ckeditor").each(function() {
				var name = $(this).attr('name');
				var data=CKEDITOR.instances[name].getData();
				main.fields[name.replace(/.*:/, '')] =data;
				$(this).val(data);
			});
			//设置单选按钮。
			self.setMainRadioData(main.fields);
			//设置复选框。
			self.setMainCheckBoxData(main.fields);
			
			//子表数据
			var sub = [];
			$('div[type=subtable][right=w || right=b][show=true||undefined]').each(function() {
				var table = {
					tableName: $(this).attr('tableName'),
					fields: []
				};
				$(this).find('[formtype]').not(":first").each(function() {	/*修复需要提交隐藏的行数据的bug*/	
					var row = {};
					var objRow=$(this);
					$("input:text[name^='s:'],input[type='hidden'][name^='s:'],textarea[name^='s:'],select[name^='s:']",objRow).each(function() {
						var name = $(this).attr('name').replace(/.*:/, '');
						var value=self.handNumberData(this);
						row[name] = value;
					});
					//设置复选框按钮的数据。
					self.setSubCheckBoxData(objRow,row);
					
					//设置单选按钮的数据
					self.setSubRadioData(objRow, row);
					
					table.fields.push(row);
				});
				sub.push(table);
				
				JSON2.stringify(sub);
			});
			
			//意见
			var opinion = [];
			$('textarea[name^=opinion]').each(function() {
				opinion.push({
					name: $(this).attr('name').split(':')[1],
					value: $(this).val()
				});
			});
			
			var data = {main: main, sub: sub, opinion: opinion};
			return JSON2.stringify(data);
		},
		/**
		 * 设置子表的radio单选按钮字段。
		 * @param dataObj
		 */
		setSubRadioData:function(objParent,dataObj){
			var operatorObj = $('input:radio', objParent);
			this.setRadioData(dataObj, operatorObj);
		},
		/**
		 * 设置主表的radio单选按钮字段。
		 * @param dataObj
		 */
		setMainRadioData:function(dataObj){
			var operatorObj = $('input[name^=m]:radio');
			this.setRadioData(dataObj, operatorObj);
		},
		setRadioData:function(dataObj, operatorObj){
			$(operatorObj).each(function() {
				var name = $(this).attr('name').replace(/.*:/, '');
				var value= $(this).val();
				
				if($(this).attr("checked")!=undefined){
					dataObj[name]=value;
				}
			});
		},
		setCheckBoxData:function(dataObj, operatorObj, checkedObj){
			//将所有复选框选址清空。
			$(operatorObj).each(function() {
				var name = $(this).attr('name').replace(/.*:/, '');
				dataObj[name]="";
			});
			
			//复选框取值。
			$(checkedObj).each(function() {
				var name = $(this).attr('name').replace(/.*:/, '');
				var value= $(this).val();
				if(dataObj[name]==""){
					dataObj[name]=value;
				} else{
					dataObj[name]+="," + value;
				}
			});
		},
		/**
		 * 设置子表复选框的数据
		 * @param objParent
		 * @param dataObj
		 */
		setSubCheckBoxData:function(objParent,dataObj){
			var operatorObj = $('input:checkbox',objParent);
			var checkedObj = $('input:checkbox:checked',objParent);
			this.setCheckBoxData(dataObj, operatorObj, checkedObj);
		},
		setMainCheckBoxData:function(dataObj){
			var operatorObj = $('input[name^=m]:checkbox');
			var checkedObj = $('input[name^=m]:checkbox:checked');
			this.setCheckBoxData(dataObj, operatorObj, checkedObj);
		},
		
		/**
		 * 打开操作数据窗口。
		 * @param title 窗口标题
		 * @param form	窗口对象。
		 * @param table 表
		 * @param beforeElement
		 * @param callback 回调函数
		 */
		openWin : function(title, form, table, beforeElement, callback) {
			var formProperty=table.data("formProperty");
			var width=formProperty.width+20;
			var height=formProperty.height+100;
			var self=this;
	
			form.data('beforeElement', beforeElement);
			var win = $.ligerDialog({target:form,
					width:width,
					height:height,
					title : title,
					showMax : true,
					onClose : true,
					showButton : true,
					buttons : [ {
						text : "确定",
						onclick: function (item, dialog) {
							var result = callback(form, table, form.data('beforeElement'));
							if(!result) return;
						    if(self.onEditCallBack!=null){
								self.onEditCallBack(title);
							}
							dialog.close();
						}
					} ]
				});
			//初始化表单界面
			this.initUI(form);
			win.show();
		
		},
		/**
		 * 获取右键菜单。
		 * @param needEdit
		 * @returns
		 */
		getMenu : function(needEdit) {
			var self=this;
			//判断菜单是否存在，不存在则新建菜单。
			if ((needEdit && this.menuWithEdit) || (!needEdit && this.menu)) {
				return needEdit ? this.menuWithEdit : this.menu;
			} else {
				var menu;
				var items = [ {
					text : '在前面插入记录',
					click : function() {
						var row = $(menu.target).closest('[formType]');
						var table = row.closest('div[type=subtable]');
						self.add(table, row);
						//TODO
						self.subSelectorInit();
						self.initSubQuery();
						self.handRow('add',table);
					}
				}, {
					text : '在后面插入记录',
					click : function() {
						var row = $(menu.target).closest('[formType]');
						var table = row.closest('div[type=subtable]');
						row = row.next('[formType]:visible');
						if (row.length == 0) {
							row = null;
						}
						self.add(table, row);
						//TODO
						self.subSelectorInit();
						self.initSubQuery();
						self.handRow('add',table);
					}
				}, {
					line : true
				}, {
					text : '编辑',
					click : function() {
						var row = $(menu.target).closest('[formType]');
						var table = row.closest('div[type=subtable]');
						self.edit(table, row);
					}
				}, {
					text : '删除此记录',
					click : function() {
						var t = $(menu.target).closest('[formType]');
						var	table = t.closest('div[type=subtable]'); 
						
						/*删除前置事件   【如果返回了 false 则取消删除】*/
						var delRowBeforeEvent = eval('window.'+table.attr("tablename")+"DelRowBeforeEvent")
						if(delRowBeforeEvent) {
							if(delRowBeforeEvent(t) == false) return;
						}
						
						$("input",t).each(function(){//设置被删除的数据的Input全为0
							var me = $(this);
							me.val("");
						});
						FormUtil.InitMathfunction(t);//触发其计算函数
						t.remove();//之后删除属性
						
						self.handRow('del',table);//页面删除
					}
				}, {
					line : true
				}, {
					text : '向上移动',
					click : function() {
						var t = $(menu.target).closest('[formType]');
						var prev = t.prev('[formType]:visible');
						if (prev.length > 0) {
							prev.before(t);
						}
						var table = t.closest('div[type=subtable]');
						self.handRow('add',table);
					}
				}, {
					text : '向下移动',
					click : function() {
						var t = $(menu.target).closest('[formType]');
						var next = t.next('[formType]:visible');
						if (next.length > 0) {
							next.after(t);
						}
						var table = t.closest('div[type=subtable]');
						self.handRow('add',table);
					}
				} ];
				//如果不需要编辑，删除编辑菜单。
				if (!needEdit) {
					items.splice(3, 1);
				}
				menu = $.ligerMenu({top : 100,left : 100,width : 130,items : items});
				if (needEdit) {
					this.menuWithEdit = menu;
				}
				else{
					this.menu = menu;
				}
				return menu;
			}
		},
		
		
		/**
		 * 从数据库加载子表权限。
		 */
	  loadSubTablePermission:function(){
			var defId = $(':hidden[name=defId]').val();
			var actDefId = $(':hidden[name=actDefId]').val();
			var formKey = $(':hidden[name=formKey]').val();
			var nodeId = $(':hidden[name=nodeId]').val();
			var params={defId:defId,actDefId:actDefId,formKey:formKey,nodeId:nodeId};
			var url = "getSubTablePermission.ht";
			var map = null;
			$.ajaxSetup({async:false});  //修改成同步
			$.post(url, params,function(data){
				map = data;
	        });
			$.ajaxSetup({async:true}); //改为原来的异步
			return map;
	  },
	  
	  selectValue:function(select){
		    var html='';
			var query=select.attr("selectquery")
			var queryJson;
			if(query!=undefined && query!=null ){
				query=query.replaceAll("'","\"");
				queryJson = JSON2.parse(query);
			}else{
				queryJson = "";
				query = "";
			}
			var sValue=select.attr("val");    //select中有一个属性是保存选中的值的（初始化时用这个）
			if(sValue==undefined || sValue==null && sValue.length<=0){
				sValue = "";
			}
			if(query){
				html="<span selectvalue="+sValue+" selectquery="+query+"><lable></lable></span>"
			}else{
				if(sValue==""){
					html=select.find("option:selected").text();    //获取Select选择的Text   选中（不可编辑时或者没有值时，是默认的）
				}else{
					html += select.find("option[value='"+sValue+"']").text();    //和值相对应的显示
				}
			}
			return html;
	  },
	  
	 /**
	  * 主表的权限补充（必埴）
	  */
	  isMainTableRequest:function(){
		  //主表的附件必埴验证问题
		 $("div[name=div_attachment_container]").each(function() {
			 var div_me=$(this);
			 if(div_me.attr("right")!='b') return true;
			 $("a.link",div_me).each(function(){
	        	var me=$(this);	
        		var validRule = me.attr("validate");
				if ( validRule != null && 'undefined' != validRule.toLowerCase() && validRule.length>2 ){ 
					var json = eval('(' + validRule + ')');		
					if(!json.required){
						var jsonStr = validRule.substring(0, validRule.lastIndexOf('}'));
						jsonStr +=",'required':true}";    //加上必填
						me.attr("validate",jsonStr);    
					}				
				}else{
					me.attr("validate","{'required':true}");     //必填
				}
	         });
		 });
	  },
	  
	   //初始化子表级联
	  initSubQuery:function(){
		
	  	$("select[selectquery]",$("[type=subtable],[formtype='window']")).each(function(){
		  	var me = $(this);
		  	var selected=me.attr("selectqueryed");
		  	//selectqueryed标识这个子表是否已经执行过了change绑定，存在就是已经绑定了，不存在就是没用被绑定过
		  	if(selected)return true;
			var selectquery = me.attr("selectquery");
			if (!selectquery)
				return true;
			selectquery = selectquery.replaceAll("'", "\"");
			var queryJson = JSON2.parse(selectquery);
			var query = queryJson.query;
			for (var i = 0; i < query.length; i++) {
				var field;
				// isMain是true 就是绑定主表的字段
				if (query[i].isMain=="true") {
					field = $(".formTable [name$=':" + query[i].trigger + "']");
				} else {
					field = me.parents('table.listTable,[formtype="window"]').find("[name='" + query[i].trigger + "']");
					//添加新一个子表时，执行了多次绑定change事件，把之前的change事件都解绑
					field.unbind("change");
				}
				if (field != "")
					QueryUI.change(field, me, queryJson);
			}
			//添加一个属性，标识已经被绑定过，
			me.attr("selectqueryed","selectqueryed");
			QueryUI.getvalue(me, queryJson);
	  	});
	  },
	  //初始化子表选择器
		 subSelectorInit:function(){
	  		$('[ctlType="selector"]','.listTable,[formtype="window"]').each(function(){
	  			var me =$(this);
	  			var nameID=me.attr('name')+'ID';
	  			var isHidden=me.siblings("input[name='"+nameID+"']");
	  			//如果存在隐藏域，则是已经添加了
	  			if(isHidden.length!=0) return;
					var type = me.attr('class');
					//方法在SelectotInit.js
					buildSelector(me, type);
				});
		},	
		// 验证子表是否必填（至少要有一条记录）
		isSubTableRequest:function(){
			var result = {success : true}
			$('div[right="b"][type="subtable"]').each(function(){
				var subtable = $(this);
				var listRows = $('tr.listRow', subtable);
				if(listRows.length > 1) return true;
				var tableDesc = subtable.attr('tabledesc');
				result.success = false;
				result.errorMsg = tableDesc;
				return false;
			});
			return result;
		}
	};
	//对表单初始化。
	CustomForm.init();
});