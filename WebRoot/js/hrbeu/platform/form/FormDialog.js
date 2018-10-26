/**
 * 功能:表单选择器
 * 
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
function FormDialog(conf){
	var url=__ctx + '/platform/form/bpmFormDef/dialog.ht';
	var winArgs="dialogWidth:750px;dialogHeight:540px;help:0;status:0;scroll:1;center:1;resizable:1;";
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,"",winArgs);
	if(rtn!=undefined){
		if(conf.callback){
			conf.callback.call(this,rtn.formKey,rtn.formName,rtn.tableId);
		}
	}
}