if (typeof FormUtil == 'undefined') {
	FormUtil = {};
}
//tab前置回调事件
//前置可以通过返回 return false组织tab切换。
var onBeforeSelectTabItemCallBack=null;
//tab后置回调事件
var onAfterSelectTabItemCallBack=null;


/**
 * 初始化表单tab。
 */
FormUtil.initTab=function(){
	var amount=$("#formTab").length;
	if(amount>0){
		$("#formTab").ligerTab({
			onBeforeSelectTabItem:function(tabid){						
				if(onBeforeSelectTabItemCallBack){
					return onBeforeSelectTabItemCallBack(tabid);
				}
			},
			onAfterSelectTabItem:function(tabid){
				FormUtil.changeTabStyle(tabid);	 //要放到TAB产生后，才执行这个样式的改变，方可可以生效，否则效果不一定有效
				if(onAfterSelectTabItemCallBack){
					onAfterSelectTabItemCallBack(tabid);
				}
	        }
		});
			
	}
};


/**
 * 初始化TAB中不能隐藏的对象。
 */
FormUtil.changeTabStyle=function(tabid){
	var tabidObj = $("div[tabid='"+tabid+"']");   //在产生的 DIV tab对象（由ligerTab产生的）		
	//处理OFFICE控件中 工具栏目 在个别浏览器上有出现不能隐藏的问题
	FormUtil.changeTabOfficeStyle(tabidObj);
	//处理图片控件容器会出现 不能隐藏的问题      要结合PictureShowPlugin处理
	FormUtil.changeTabZoomStyle(tabidObj);
};


/**
 * 处理OFFICE控件中 工具栏目 在个别浏览器上有出现不能隐藏的问题
 */
FormUtil.changeTabOfficeStyle=function(tabidObj){	
	var menuBars = $("div[name='menuBar']");
	var offices =$("input[controltype='office']");
	if( menuBars.length>0 && offices.length>0 ){
		menuBars.each(function(index){         //把所有的OFFICE控件中 工具栏目隐藏掉
			$(this).css("display","none");
		});
		
		$("div[name='menuBar']",tabidObj).each(function(index){        //把 DIV tab对象（由ligerTab产生的）的OFFICE控件中 工具栏目显示出来
			$(this).css("display","block");
		});
		
		//把在TAB里面的所有的OFFICE控件有影响的l-tab-content-item样式：overflow去掉
		tabidObj.attr("style","");
		
		 
		/*offices.each(function(index){         //把所有的OFFICE控件容器隐藏掉
			var name = $(this).attr("name");
			var divId="div_" + name.replaceAll(":","_");
			$("#"+divId).css("display","none");
		});
		
		$("input[controltype='office']",tabidObj).each(function(index){        
    		var name = $(this).attr("name");
			var divId="div_" + name.replaceAll(":","_");
			$("#"+divId).css("display","block");
		});*/

		OfficePlugin.isTabItemOffice = false;              //初始化图片控件样式的完成标志， 防止定时器不停止检查
	}
	
};

/**
 * 处理图片控件容器会在个别浏览器上有出现不能隐藏的问题      要结合PictureShowPlugin处理
 */
FormUtil.changeTabZoomStyle=function(tabidObj){	
//	var zoomContainers = $('div.zoomContainer');
	var pictureShows =$("input[controltype='pictureShow']");
	if( pictureShows.length>0 ){
        // ZOOM容器序号初始化情况        
		/*var num = null;
		if(PictureShowPlugin.isZoomNum){  //ZOOM容器isZoomNum为true证明还没有把序号初始化
        	PictureShowPlugin.initZoomContainer();         //ZOOM容器都没有序号时就重新按顺序赋值排序（图片控件对象的序号和ZOOM容器都序号是一致的，都是按每一次进入页面的顺序决定）
        	PictureShowPlugin.isZoomNum = false;
        }else{  //有序列化的情况
        	num = zoomContainers.get(zoomContainers.length-1).getAttribute("zoomContainerNum");   //ZOOM容器有序列化了，但是最后一个没有没有序号时要把最近修改的图片控件序号加上
        	if(typeof(num)==undefined||num==null||num==""||num=="null"){  //每一个ZOOM容器都没有序号证明还没有把序号初始化
            	PictureShowPlugin.setZoomContainer();         //ZOOM容器都没有序号时就重新按顺序赋值排序（图片控件对象的序号和ZOOM容器都序号是一致的，都是按每一次进入页面的顺序决定）
            }				
        }		
		
		zoomContainers.each(function(index){         //把所有的图片控件产生的zoomContainer容器隐藏掉
			$(this).css("display","none");
		});
		
		var myNum = pictureShows.get(0).getAttribute("myNum");
		if(typeof(myNum)==undefined||myNum==null||myNum==""||myNum=="null"){  //如果图片控件都没序号化时
			pictureShows.each(function(index){        //图片控件都没有序号时就重新按顺序赋值排序（图片控件对象的序号和ZOOM容器都序号是一致的，都是按每一次进入页面的顺序决定）
				$(this).attr("myNum",index);
			});         
        } */
		
		//图片控件显示处理(原因：个别浏览器有问题需要处理)
		pictureShows.each(function(index){         //把所有的图片控件容器隐藏掉
			var name = $(this).attr("name");
			var divId="div_" + name.replaceAll(":","_");
			$("#"+divId).css("display","none");
		});
		
		//把 DIV tab对象（由ligerTab产生的）的图片控件 显示出来
		$("input[controltype='pictureShow']",tabidObj).each(function(index){        
    		var name = $(this).attr("name");
			var divId="div_" + name.replaceAll(":","_");
			$("#"+divId).css("display","block");
		});
		
		//把 DIV tab对象（由ligerTab产生的）的图片控件在IE下面（IE6、9）大图有可能会发生偏移区域，只有做居左补上
		if(PictureShowPlugin.browserName=="MSIE"){
			$("div.wrap_div",tabidObj).each(function(index){        
	    		$(this).css("text-align","left");
			});
		}
		
		PictureShowPlugin.isTabItemZoom = false;                   //初始化图片控件样式的完成标志，防止定时器不停止检查
	}
};

/**
 * 默认是进入每一个TAB的特别处理
 */
FormUtil.initTabStyle=function(){
	var tabid = null;
	var amount=$("#formTab").length;
	if(amount>0){
		//默认是进入每一个TAB
		var tabid = null;
		$('div.l-tab-content-item').each(function(index){
			tabid = $(this).attr("tabid");            //选中每一个TAB
			if(typeof(tabid)!=undefined||tabid!=null||tabid!=""||tabid!="null"){ 
				return false;
			}
		});
		var tabidObj = $("div[tabid='"+tabid+"']");   //在产生的 DIV tab对象（由ligerTab产生的）	
		FormUtil.recursiveTabItemOffice(tabidObj);
		FormUtil.recursiveTabItemZoom(tabidObj);
	}	
};


/**
 * 递归函数每隔1.5秒，直到Office工具栏目的样式修改好才，停止！
 */
FormUtil.recursiveTabItemOffice=function(tabidObj){	
	window.setTimeout(function(){ 
		if(OfficePlugin.isTabItemOffice){
			FormUtil.changeTabOfficeStyle(tabidObj);
			if(OfficePlugin.isTabItemOffice){
				FormUtil.recursiveTabItemOffice(tabidObj);
			}
		}
	},1500); 
};


/**
 * 递归函数每隔1.5秒，直到图片控件的容器的样式修改好才，停止！
 */
FormUtil.recursiveTabItemZoom=function(tabidObj){	
	window.setTimeout(function(){ 
		if(PictureShowPlugin.isTabItemZoom){
			FormUtil.changeTabZoomStyle(tabidObj);
			if(PictureShowPlugin.isTabItemZoom){
				FormUtil.recursiveTabItemZoom(tabidObj);
			}
		}
	},1500); 
};



/**
 * 初始化日历控件。
 */
FormUtil.initCalendar=function(){
	$("body").delegate("input.Wdate", "click",function(){
		var fmt=$(this).attr("dateFmt");
		WdatePicker({el:this,dateFmt:fmt});
	});
};



/**
 * 绑定对话框。 按钮或者文本框定义如下：
 * dialog="{name:'globalType',fields:[{src:'TYPENAME',target:'m:mainTable:name'},{src:'TYPEID',target:'m:mainTable:address'}],query:[{'id':'bm','name':'ORGID','isMain':'true'}]}"
 * 
 * name:对话框的别名 fields：为字段映射，可以有多个映射。 src：对话框返回的字段。 target：需要映射的控件名称。dialogQuery：向自定义对话框传递的参数字段
 */
FormUtil.initCommonDialog=function(){
	
	//先把主表中dialog对话框的包含字段中有只读的权限时，只能把对话框功能删除（子表情况的放在CustomForm.js中处理了）
	$("a.extend",$(".formTable")).each(function(){      //只读时，历遍超链接中自定义对话框的按钮
		var extend=$(this);
		var jsonStr = extend.attr('dialog');							
		if(jsonStr != null && 'undefined' != jsonStr.toLowerCase() && jsonStr.length>2 ){
			var jsonObj = eval('(' + jsonStr + ')');
			var fileds = jsonObj.fields;
			for ( var i = 0; i < fileds.length; i++) {
				if($.isEmpty(fileds[i].target))//如果为空
					continue;
					var ds = $(".formTable [name$=':"+fileds[i].target+"']").not("[name^='s:']");
					if(!("undefined"==typeof(generate)))
						ds = $(".formTable [name='"+fileds[i].target+"']").not("[name^='s:']");
					if(ds.length<1){
						extend.remove();                 //主表字段在只读时，其内容直接为值，所以当对话框读取对应主表字段时没有，证明字段是只读的，所以要删除
						break;
					}else{
						var mark = false;
						ds.each(function(index){   //主表字段
							var right = $(this).attr("right");
							if(right=="r"||right=="rp"){
								extend.remove();                 //只读和只读提交时删除
								mark = true;
								return false;
							}
						});
						if(mark){
							break;
						}
					}				
			}
		}                           
	});
	
	$("body").delegate("[dialog]", "click", function(){
		var obj=$(this);
		var dialogJson=obj.attr("dialog");
		var json=eval("("+dialogJson+")" );
		var name=json.name;

		var fields=json.fields;
		var parentObj=obj.closest("[formtype]");
		var isGlobal=parentObj.length==0;
		var paramsValueString = "" ;
		var queryArr = json.query;
		var isMain,preSelector,isReturn=false ;
		if(!queryArr==false && queryArr.length>0){
			for(var i=0;i<queryArr.length;i++){
				isMain = queryArr[i].isMain ;
				if(isMain=="true"){
					preSelector = ".formTable" ;
				}else{
					preSelector = "div[type='subtable']" ;
				}
				
				//解析获取对应的数据
				$(preSelector+" :hidden[name$=':"+queryArr[i].id+"ID']").each(function(){
					if($(this).val()!=""){
						paramsValueString += queryArr[i].name + "=" + $(this).val() +"&" ;
						return false ;
					}
				});
				if(paramsValueString.indexOf(queryArr[i].name)<0){
					$(preSelector+" [name$=':"+queryArr[i].id+"']").each(function(){
						var self = $(this) ;
						if(self.val()!=""){
							paramsValueString += queryArr[i].name + "=" + $(this).val() +"&" ;
							return false ;
						}else{
							var selfClass = self.attr("class") ;
							if(!selfClass==false){
								if(selfClass.indexOf("validError")>=0){
									isReturn = true ;
									$.ligerDialog.warn('请填写好--'+self.attr("lablename"),'提示');
									return false ;
								}
							}
						}
					});
				}
				if(isReturn) return false ;
			}
		}
		CommonDialog(name,function(data){
			var len=data.length;
			for(var i=0;i<fields.length;i++){
				var json=fields[i];
				var src=json.src;
				var targets=json.target.split(','),target;
				while(target=targets.pop()){
					if(!target)return;
					var filter="[name$=':"+target+"']";
					if(!("undefined"==typeof(generate)))
						filter="[name='"+target+"']";
					//在子表中选择
					var targetObj=isGlobal?$(filter):$(filter,parentObj);
					//单选
					if(len==undefined){
						targetObj.val(data[src]);
						targetObj.trigger("change");
					}
					//多选
					else{
						for(var k=0;k<len;k++){
							var dataJson=data[k];
							if(json.data){
								json.data.push(dataJson[src]);
							}
							else{
								var tmp=[];
								tmp.push(dataJson[src]);
								json.data=tmp;
							}
						}
						targetObj.val(json.data.join(","));
						targetObj.trigger("change");
					}
				}
			}
		},paramsValueString);
	});
};

/**
 * 初始化统计函数事件绑定
 */
FormUtil.InitMathfunction = function(t){
	if(t){
		$("input",t).trigger("change");
	}
	else{
		$("input").live('change',FormMath.doMath);
	}
};

/**
 * 获取目标控件的值是否为指定值
 */
FormUtil.getTargetVal = function(t,obj){
	var type = obj.attr("type");
	 if(obj.is("select")){ //下拉框
		var val = obj.find('option:selected').val();
		if(val==t.value)
			return true;
		return false;
	 }else if(type == "radio" || type =="checkbox"){//单选框，多选框
		 var temp = false;
		 obj.each(function(){
			 var me = $(this),
			 	 state = me.attr("checked"),
			 	 val = me.val();
			 
			 if(state&&t.value.indexOf(val)>-1)
				 temp = true;
		 });
		 return temp;
	 }else{
		return false;
	 }
};

/**
 * 获取是否所有的选择框字段都是指定值
 */
FormUtil.validHasChange = function(chooseField){
	var result = true;
	for(var i=0,c;c=chooseField[i++];){
		var obj = FormUtil.getTargetObj(c.key);
		if(!FormUtil.getTargetVal(c,obj))
			result = false;
	}
	return result;
};

/**
 * 进行变更字段和表的变更
 * @param changes
 */
FormUtil.doChange = function(choiceKey,changes){
	for(var i=0,c;c=changes[i++];){
		//对表进行变更
		if(!$.isEmpty(c.fieldtype) && c.fieldtype == '1')	
			FormUtil.doChangeTable(choiceKey,c);
		else//对字段进行变更
			FormUtil.doChangeField(choiceKey,c);
	}
};

/**
 * 变换预处理
 */
FormUtil.preChange = function(choice,changes,t){
	var choiceKey = [],
		trace = $changeObj.data("formtrace");
	for(var i=0,c;c=choice[i++];){
		choiceKey.push(c.key);
		if(trace){
			for(var j=0,n;n=changes[j++];){
				var traceCall = trace[n.key+'_'+n.type];
				if(traceCall){
					var me = null;
					if(!$.isEmpty(n.fieldtype) && n.fieldtype == '1')
						me = $("div[type='subtable'][tablename='"+n.key+"']");
					else{
						me = FormUtil.getTargetObj(n.key);
					}
					traceCall.call(me,n);
					me.trigger("blur");
					delete trace[n.key+'_'+n.type];
				}
			}
		}
	}
	if(t){
		$changeFunc.push({choiceKey:choiceKey,changes:changes});	
	}
};

FormUtil.isAttachment = function(obj){
	var type = obj.attr("controltype");
	if(!$.isEmpty(type) && type== 'attachment')
		return true;
	else
		return false;
}

//字段变换方法
var $fieldChange = {
        1:function(c){//隐藏
			var self = $(this).val('');
			if(FormUtil.isAttachment(self)){	
				self.parent("div").hide();
			}else{
				self.hide();
				self.parents("label").hide();
				//隐藏自定义对话框按钮
				self.closest("span").siblings().hide();
			}
		}
        ,
        2:function(c){//非隐藏
			var self = $(this);		
			if(FormUtil.isAttachment(self)){	
				self.parent("div").show();
			}else{
				self.show();
				self.parents("label").show();
				//显示自定义对话框按钮
				self.closest("span").siblings().show();
			}
		}
        ,
        3:function(c){//只读
			var self = $(this);
			if(self.is("a"))//对选择器的特殊处理
				 return;
			if(FormUtil.isAttachment(self)){	
				self.siblings("a[field='"+c.key+"']").hide();
				self.siblings(".attachement").find("a.cancel").each(function(){
					$(this).hide();
				});
			}else{	
				self.attr("disabled",true);
			}
		}
        ,
        4:function(c){//非只读
			var self = $(this);
			if(self.is("a"))//对选择器的特殊处理
				 return;
			if(FormUtil.isAttachment(self)){	
				self.siblings("a[field='"+c.key+"']").show();
				self.siblings(".attachement").find("a.cancel").each(function(){
					$(this).show();
				});
			}else{	
				self.removeAttr("disabled");
			}
		}
        ,
        5:function(c){//必填					
			var self = $(this),
				validate = self.attr("validate");
			if(self.is("a"))//对选择器的特殊处理
				 return;
			if(FormUtil.isAttachment(self)){		
				self.siblings("a.selectFile").addClass("validError");
				self.parent().attr('right','b');
			}else{
				self.addClass("validError");
				if(!validate){
					self.attr("validate","{required:true}");						
				}
				else{
					validate = eval("("+validate+")");
					if(!validate.required){
						validate.required = true;
						self.attr("validate",JSON2.stringify(validate));
					}
				}
				if(!$.isEmpty(self.val()))
					self.removeClass("validError");
				else{
					if(self.hasClass("Wdate")){
						self.css("border","1px solid red");
					}
				}
			}
		}
        ,
        6:function(c){//非必填
			var self = $(this),
				validate = self.attr("validate");
			if(self.is("a"))//对选择器的特殊处理
				 return;
			if(FormUtil.isAttachment(self)){
				self.siblings("a.selectFile").removeClass("validError")
				self.parent().attr('right','w');
			}else{
				if(validate){
					validate = eval("("+validate+")");
					if(validate.required){
						delete validate.required;
						self.attr("validate",JSON2.stringify(validate));
					}
					//删除样式
					self.removeClass("validError");
					if(self.hasClass("Wdate")){
						self.css("border","1px solid #999999");
					}
				}
			}
		}
        ,
        7:function(c){//置空
			var self = $(this);
			if(self.is("a"))//对选择器的特殊处理
				 return;
			if(FormUtil.isAttachment(self))
				self.siblings(".attachement").html('');
			if(self.attr("type")=="checkbox"||self.attr("type")=="radio")
				self.removeAttr("checked");
			$(this).val('');
		}
	};

/**
 * 获取关联的需要变动的字段
 */
FormUtil.getTargetObj = function(filter){
	if(!$changeObj){
		return $("[name='"+filter+"']");
	}
	var formtype = $changeObj.parents("[formtype]"),
		me = null;
	if(formtype&&formtype.length>0){
		me = $("[name='"+filter+"']",formtype);
	}
	else{
		me = $("[name='"+filter+"']");
	}
	return me;
};

/**
 *  字段变换
 * @param {} changeField
 */
FormUtil.doChangeField = function(choiceKey,c){
	var me = FormUtil.getTargetObj(c.key);
	if(!me||me.length==0)return;
	var changeType = c.type,
		traceCall = null;
	
	if(changeType!='8'){
		var changeFun = $fieldChange[changeType];
		if(changeType%2){
			traceCall = $fieldChange[++changeType];
		}
		else{
			traceCall = $fieldChange[--changeType];
		}
		me.each(function(){
			changeFun.call(this,c);
		});
	}
	else{
		var	reduceSet = c.cascade.reduce,
			oldHtml = '';
			
		me.each(function(){
			var self = $(this),
				myType = self.attr('type');
			if($(this).is("a"))//对选择器的特殊处理
				 return;
			if(myType=='radio'||myType=='checkbox'){
				var spanPar = self.parents("span");
				for(var i=0,c;c=reduceSet[i++];){
					if(self.val()==c.id){
						spanPar.remove();
					}
				}
			}
			else{//下拉框
				isSelect = true;
				oldHtml = self.html();
				var options = self.find("option");			
				for(var i=0,c;c=reduceSet[i++];){
					for(var j=0,n;n=options[j++];){
						var t = $(n);
						if(!t.val())continue;
						if(t.val()==c.id){
							t.remove();
						}
					}
				}
			}
		});
		FormUtil.putInFormTrace(choiceKey,"tracedata",c.key,oldHtml);
		traceCall = function(c){
			var oldHtmlObj = $changeObj.data("tracedata");
			if(oldHtmlObj){
				oldHtml = oldHtmlObj[c.key];
				$(this).each(function(){
					var curValue = $(this).val();
					$(this).html(oldHtml).val(curValue).trigger("change");
				});
				delete oldHtmlObj[c.key];
			}
		};
	}
	FormUtil.putInFormTrace(choiceKey,"formtrace",c.key+'_'+c.type,traceCall);
	me.trigger("blur");
};

/**
 * 将变动轨迹放入控件的缓存数据中
 */
FormUtil.putInFormTrace = function(choiceKey,datakey,key,value){
	for(var j=0,n;n=choiceKey[j++];){
		var changeObj = FormUtil.getTargetObj(n);
		if(!changeObj)continue;
		var formTrace = changeObj.data(datakey);
		if(!formTrace){
			formTrace = {};
			formTrace[key] = value;
			changeObj.data(datakey,formTrace);
		}
		else{
			formTrace[key] = value;
		}
	}
};

//表表换方法集合
var $tableChange = {
		1:function(){//隐藏
			var self = $(this);
			self.hide();
		},
		2:function(){//非隐藏
			var self = $(this);
			self.show();
		},
		3:function(){//只读(不可以添加)
			var self = $(this);
			$('a.add',self).hide();
			self.find('[formType]').prev().unbind('contextmenu');	
			$('td',self).find('input,select,textarea').attr("disabled","disabled");
			$('td',self).find('a').hide();
		},
		4:function(){//非只读(可以添加)
			var self = $(this);
			$('a.add',self).show();	
			$('td',self).find('input,select,textarea').removeAttr("disabled","disabled");
			$('td',self).find('a').show();
		},
		5:function(){//必填
			var self = $(this);
			var y =self.attr('right');
			self.attr('right','b');
			self.attr("yright",y);
		},
		6:function(){//非必填
			var self = $(this);
			var y =	self.attr("yright");
			self.attr('right',y?'w':y);
		},
		7:function(){//置空
			var self = $(this);
			$(":text,select",self).each(function(){
				 $(this).val('');
			});
			$("textarea",self).each(function(){
				var s= 	$(this);
				//处理附件
				if(FormUtil.isAttachment(s))
					s.siblings(".attachement").html('');
				s.val('');
			});
			$("input[type='radio']",self).each(function(){
				 $(this).removeAttr("checked");
			});
			$("input[type='checkbox']",self).each(function(){
				 $(this).removeAttr("checked");
			});
		}
};

/**
 * 表变换
 * @param {} c
 */
FormUtil.doChangeTable = function(choiceKey,c){	
	var me = $("div[type='subtable'][tablename='"+c.key+"']"),
		changeType = c.type;
	
	var changeFun = $tableChange[changeType];
	if(changeType%2){
		traceCall = $tableChange[++changeType];
	}
	else{
		traceCall = $tableChange[--changeType];
	}
	me.each(function(){
		changeFun.call(this,c);
	});
	FormUtil.putInFormTrace(choiceKey,"formtrace",c.key+'_'+c.type,traceCall);
};

var $changeObj = null,
	$changeFunc = [],
	$changeNoChoise = [];

FormUtil.ChangeNoChoise = function(){
	FormUtil.doChange([],$changeNoChoise);
};

/**
 * 联动设置的初始化
 */
FormUtil.InitGangedSet = function(d){
	$changeNoChoise = [];
	for(var i=0,c;c=d[i++];){
		var choisefield = eval("("+c.choisefield+")"),
			changefield = eval("("+c.changefield+")");
		
		if(choisefield&&choisefield.length==0){
			$changeNoChoise = $changeNoChoise.concat(changefield);
		}
		
		for(var j=0,m;m=choisefield[j++];){
			var changeObj = $("*[name='"+m.key+"']");
			changeObj.die("change").live("change",function(e){
				$changeFunc = [];
				$changeObj = $(e.target);
				var	key = $changeObj.attr("name");
				//当一个select控件绑定了多次事件时，需要多次验证这个控件的所选值
				//比如 性别这个字段，在联动中设置了选择'男'时隐藏一个字段，选择'女'时 显示一个字段，此时需要2次验证
				if(!key)return;
				for(var x=0,n;n=d[x++];){
					if(n.choisefield.indexOf(key)>-1){
						var curChoise = eval("("+n.choisefield+")"),
							r = FormUtil.validHasChange(curChoise);
						FormUtil.preChange(curChoise,eval("("+n.changefield+")"),r);
					}
				}
				//延迟处理：等待该变换字段的所有还原方法调用完以后再进行变换
				if($changeFunc.length>0){
					for(var y=0,o;o=$changeFunc[y++];){
						FormUtil.doChange(o.choiceKey,o.changes);
					}
				}
			});
			changeObj.trigger("change");
		}
	}
	FormUtil.ChangeNoChoise();
};

/**
 * 新增子表记录时，触发联动字段change，使新增记录保持合适的联动状态
 */
FormUtil.triggerChoice = function(newRow){
	$("[name]",newRow).each(function(){
		var me = $(this),
			name = me.attr("name"),
			choiceName = FormUtil.findWithChange(name);
		
		if(choiceName){
			var choiceObj = $("[name='"+choiceName+"']"),
				val = choiceObj.val(); 
			choiceObj.trigger("change");
		}
	});
	
};

/**
 * 遍历联动设置记录，查找 应该触发哪个字段
 */
FormUtil.findWithChange = function(name){
	if(typeof bpmGangedSets == 'undefined')return null;
	for(var i=0,c;c=bpmGangedSets[i++];){
		var choisefield = eval("("+c.choisefield+")"),
			changefield = eval("("+c.changefield+")");
		
		if(choisefield&&choisefield.length==0){
			return null;
		}
		for(var j=0,m;m=changefield[j++];){
			if(m.key==name){
				return choisefield[0]["key"];
			}
		}
	}
};

/**
 * 绑定自定义查询。 按钮或者文本框定义如下：
 * query="{name:'globalType',evt:'click',fields:[{src:'TYPENAME',target:'m:mainTable:name'},{src:'TYPEID',target:'m:mainTable:address'}]}"
 * 
 * name:自定义查询的别名 fields：为字段映射，可以有多个映射。 src：对话框返回的字段。 target：需要映射的控件名称。
 */
FormUtil.initCommonQuery=function(){
	$("[query]").live("keyup change", function(event){
		var obj = $(this),
			queryJson=obj.attr("query"),
			json = eval("("+queryJson+")"),
			evt = json.evt,
			next = obj.next();
		if(evt.key!=event.type){
			return;
		}
		if("carriage_return" == evt.name && event.keyCode != evt.code){
			return;
		}
		var obj=$(this);
		var queryJson=obj.attr("query");
		var json = eval("("+queryJson+")");
		var name = json.name;
		var cond = json.cond;
		
		
		var value = obj.val();
		if(next&&next.length > 0){
			//如果是 选择器类型字段 则获取 选择器的ID字段值 作为查询条件
			if(next.hasClass("link")&&next[0].tagName == "A"){
				value = $("input[name='"+obj.attr("name")+"ID']").val();
			}
		}
		if(!value){
			return;
		}
		var fields=json.fields;
		var parentObj=obj.closest("[formtype]");
		var isGlobal=parentObj.length==0;
		var querydataStr = "{"+cond+":\""+value+"\"}";
	    var  queryCond = {alias:name,querydata:querydataStr,page:1,pagesize:10};
		DoQuery(queryCond,function(data){
			if(data.errors){
				return;
			}
			for(var i=0;i<fields.length;i++){
				var json=fields[i];
				var src=json.src;
				var targets=json.target;
				var target;
				while(target=targets.pop()){
					if(!target)return;
					var filter="[name$=':"+target+"']";
					//在子表中选择
					var targetObj=isGlobal?$(filter):$(filter,parentObj);
					if(data.list.length<1){
						targetObj.val("");
						continue;
					}
					var dataArr = [] ;
					for(var k=0;k<data.list.length;k++){
						var dataJson=data.list[k];
						if(dataArr){
							dataArr.push(dataJson[src.toLowerCase()]);
						}
						else{
							var tmp=[];
							tmp.push(dataJson[src.toLowerCase()]);
							dataArr=tmp;
						}
					}
					targetObj.val(dataArr.join(","));
					targetObj.trigger("change").trigger("blur");
				}
			}				
		});
	}).trigger("change");
};

FormUtil.initExtLink = function(){
	$("body").delegate("a[linktype]","click",function(){
		var linktype = $(this).attr("linktype");
		if(!linktype){
			return;
		}
		var type = parseInt(linktype);
		var winArgs="dialogWidth:800px;dialogHeight:600px;help:0;status:1;scroll:1;center:1";
		switch (type) {
		case 4://用户单选
		case 8://用户多选
			var userid = $(this).attr("refid");
			url = __ctx+"/platform/system/sysUser/getByUserId.ht?canReturn=2&userId="+userid;
			
			window.showModalDialog(url,"",winArgs);	
			break;
		case 5://角色
		case 17://角色
			var roleId = $(this).attr("refid");
			ShowExeInfo.showRole(roleId);
			break;
		case 6://组织
		case 18://组织
			var orgId = $(this).attr("refid");
			ShowExeInfo.showOrg(orgId);
			break;
		case 7://岗位
		case 19://岗位
			var posId = $(this).attr("refid");
			ShowExeInfo.showPos(posId);
			break;
		case 20:
			var runId = $(this).attr("refid");
			var url=__ctx+"/platform/bpm/processRun/info.ht?isOpenDialog=1&link=1&runId="+runId;
			var args="dialogWidth:900px;dialogHeight:700px;help:0;status:1;scroll:1;center:1";
			window.showModalDialog(url,"",args);		
			break;
		}
	});
};




/**
 * 关联业务数据模板
 */
FormUtil.toDataTemplate=function(){
	
	$("body").delegate("[datatemplate]", "click", function(){
		alert("toDataTemplate");
		var obj=$(this);
		var dataTemplateJson=obj.attr("datatemplate");
		var json=eval("("+dataTemplateJson+")" );
		var name=json.name;
		var paramsValueString = "" ;
		var queryArr = json.query;
		var isMain,preSelector,isReturn=false ;
		if(!queryArr==false && queryArr.length>0){
			for(var i=0;i<queryArr.length;i++){
				isMain = queryArr[i].isMain ;
				if(isMain=="true"){
					preSelector = ".formTable" ;
				}else{
					preSelector = "div[type='subtable']" ;
				}
				
				//解析获取对应的数据
				$(preSelector+" :hidden[name$=':"+queryArr[i].id+"ID']").each(function(){
					if($(this).val()!=""){
						paramsValueString += queryArr[i].name + "=" + $(this).val() +"&" ;
						return false ;
					}
				});
				if(paramsValueString.indexOf(queryArr[i].name)<0){
					$(preSelector+" [name$=':"+queryArr[i].id+"']").each(function(){
						var self = $(this) ;
						if(self.val()!=""){
							paramsValueString += "&" + queryArr[i].name + "=" + $(this).val();
							return false ;
						}else{
							var selfClass = self.attr("class") ;
							if(!selfClass==false){
								if(selfClass.indexOf("validError")>=0){
									isReturn = true ;
									$.ligerDialog.warn('请填写好--'+self.attr("lablename"),'提示');
									return false ;
								}
							}
						}
					});
				}
				if(isReturn) return false ;
			}
		}
		var url=__ctx +"/platform/form/bpmDataTemplate/preview.ht?__displayId__="+name+paramsValueString;
		alert(url);
		targetf.location.href = url; 
	});
};
