/*
 * 自定义查询对话框
 */
function BpmQueryDialog(conf)
{
	if(!conf) conf={};
	var url=__ctx +"/platform/bpm/bpmFormQuery/dialog.ht";
	var dialogWidth=460;
	var dialogHeight=400;
	$.extend(conf, {dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:1,center:1});
	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,conf,winArgs);
	if(rtn!=undefined){
		conf.callback(rtn);
	}
}