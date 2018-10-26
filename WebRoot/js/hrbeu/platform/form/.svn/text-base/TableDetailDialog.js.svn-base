/**
 * 功能:自定义表明细对话框
 * 
 * 参数：
 * conf:配置为一个JSON
 * 
 * dialogWidth:对话框的宽度。
 * dialogHeight：对话框高度。
 * 
 * 
 */
function TableDetailDialog(conf)
{
	if(!conf) conf={};	
	
	var url=__ctx + '/platform/form/bpmFormTable/get.ht?iswin=1&tableId='+ conf.tableId;
	
	var dialogWidth=700;
	var dialogHeight=540;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1,resize:1},conf);

	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center +";resizable=" + conf.resize;
	
	url=url.getNewUrl();
	window.showModalDialog(url,"",winArgs);
	
}