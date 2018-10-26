function ExpressionMakerDialog(conf)
{	
	if(!conf) conf={};	
	var url=__ctx + "/platform/expression/expression.ht";
	
	var dialogWidth=780;
	var dialogHeight=570;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,"",winArgs);
	if(rtn!=undefined){
		if(conf.callback){
			conf.callback.call(this,rtn);
		}
	}	
}