
/**
 * 复制模板选择窗口。
 * dialogWidth：窗口宽度，默认值350
 * dialogWidth：窗口宽度，默认值150
 * callback：回调函数
 * 回调参数如下：
 * key:参数key
 * name:参数名称
 * 使用方法如下：
 * 
 * CopyRoleDialog({callback:function(content){
 *		//回调函数处理
 *	}});
 * @param conf
 */
function CopyTemplateDialog(conf){
	if(!conf)conf={};
	var templateId=conf.templateId;
	var templateName=conf.templateName;
	var alias=conf.alias;
	var url="copy.ht?templateId="+templateId;
	
	var dialogWidth=450;
	var dialogHeight=250;
	$.extend(conf,{dialogWidth:dialogWidth,dialogHeight:dialogHeight,help:0,status:0,scroll:0,center:1});
	var obj={templateId:conf.templateId,templateName:conf.templateName,alias:conf.alias};
	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
	+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,obj,winArgs);
	if(conf){
		location.reload();
	}
}
