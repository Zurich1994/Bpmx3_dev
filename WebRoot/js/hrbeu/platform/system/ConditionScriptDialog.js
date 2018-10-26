function ConditionScriptDialog(conf)
{
	var dialogWidth=730;
	var dialogHeight=500;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	if(!conf.isSingle)conf.isSingle=false;
	var url=__ctx + '/platform/system/conditionScript/dialog.ht';
	url=url.getNewUrl();
	
	var rtn=window.showModalDialog(url,conf.data,winArgs);
	
	if(conf.callback)
	{
		if(rtn && rtn.id){
			 conf.callback.call(this,rtn);
		}
	}
}