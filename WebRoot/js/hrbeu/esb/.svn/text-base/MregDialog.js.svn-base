

function MregDialog(conf)
{
	var dialogWidth=605;
	var dialogHeight=500;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

	var winArgs="dialogWidth="+dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	if(!conf.isSingle)conf.isSingle=1;
	var url=__ctx + '/reg/mregwsdl/mregWsdl/dialog.ht?isSingle=' + conf.isSingle;
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,null,winArgs);
	
	if(conf.callback)
	{
		if(rtn!=undefined){
			 conf.callback.call(this,rtn.id,rtn.name,rtn.alias,rtn.systemId);
		}
	}
};