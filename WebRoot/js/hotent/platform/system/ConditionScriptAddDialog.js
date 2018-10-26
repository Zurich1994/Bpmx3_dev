
function ConditionScriptAddDialog(conf)
{
	var dialogWidth=700;
	var dialogHeight=480;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	if(!conf.isSingle)conf.isSingle=false;
	var url=__ctx + '/platform/system/conditionScript/addDialog.ht?defId='+conf.data.defId;
	url=url.getNewUrl();
	
	/*var rtn=window.showModalDialog(url,conf.data,winArgs);
	
	if(conf.callback)
	{
		if(rtn && rtn.status){
			 conf.callback.call(this,rtn.data);
		}
	}*/
	/*KILLDIALOG*/
	var that =this;
	DialogUtil.open({
		height:conf.dialogHeight,
		width: conf.dialogWidth,
		title : '',
		url: url, 
		isResize: true,
		//自定义参数
		data: conf.data,
		sucCall:function(rtn){
			if(conf.callback)
			{
				if(rtn && rtn.status){
					 conf.callback.call(that,rtn.data);
				}
			}
		}
	});
};

