/**
 * 功能:表单选择器
 * 史欣慧
 * 参数：
 * conf:配置为一个JSON
 * 
 * dialogWidth:对话框的宽度。
 * dialogHeight：对话框高度。
 * 
 * callback：回调函数
 * 回调函数支持的参数
 * formId：表单ID
 * formName:表单名称
 */
function FormDialog2(conf){
	var url=__ctx + '/platform/form/bpmFormDef/dialog2.ht';
	var winArgs="dialogWidth:750px;dialogHeight:540px;help:0;status:0;scroll:1;center:1;resizable:1;";
	
	url=url.getNewUrl();
	/*var rtn=window.showModalDialog(url,"",winArgs);
	if(rtn!=undefined){
		if(conf.callback){
			conf.callback.call(this,rtn.formKey,rtn.formName,rtn.tableId);
		}
	}*/
	
	/*KILLDIALOG*/
	var that =this;
	DialogUtil.open({
		height:540,
		width: 750,
		title : '表单选择器',
		url: url, 
		isResize: true,
		//自定义参数
		sucCall:function(rtn){
			//alert("sucCall!");
			if(rtn!=undefined){
				//alert("Inside sucCall!");
				//alert("formKey Inside:"+rtn.formKey	);
				if(conf.callback){
					alert("conf Callback :");
					conf.callback.call(that,rtn.formKey,rtn.formName,rtn.tableId);
				}
			}
		},
		returnValue:function(rtn){
			if(rtn!=undefined){
				if(conf.callbackwithValue){
					conf.callbackwithValue.call(that,rtn.text,rtn.key);
				}
			}
		}
		
	});
}