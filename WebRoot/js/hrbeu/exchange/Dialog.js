function DataSetDialog(conf)
{
	var dialogWidth=605;
	var dialogHeight=500;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

	var winArgs="dialogWidth="+dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	if(!conf.isSingle)conf.isSingle=1;
	var url=__ctx + '/dataExchange/exchange/dataSet/dialog.ht?isSingle=' + conf.isSingle+"&systemId="+conf.systemId;
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,null,winArgs);
	
	if(conf.callback)
	{
		if(rtn!=undefined){
			 conf.callback.call(this,rtn.setId,rtn.setCode,rtn.setName);
		}
	}
};

function SubMetadataDialog(conf)
{
	var dialogWidth=605;
	var dialogHeight=500;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

	var winArgs="dialogWidth="+dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	if(!conf.isSingle)conf.isSingle=1;
	var url=__ctx + '/dataExchange/exchange/subMetadata/dialog.ht?isSingle=' + conf.isSingle+"&setId="+conf.setId;
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,null,winArgs);
	
	if(conf.callback)
	{
		if(rtn!=undefined){
			 conf.callback.call(this,rtn.dataId,rtn.dataCode,rtn.dataName,rtn.genDataId,rtn.genDataName);
		}
	}
};

function PublishMetadataDialog(conf)
{
	var dialogWidth=605;
	var dialogHeight=500;
	
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

	var winArgs="dialogWidth="+dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	if(!conf.isSingle)conf.isSingle=1;
	var url=__ctx + '/dataExchange/exchange/pubMetadata/dialog.ht?publishId=' + conf.publishId;
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,null,winArgs);
	
	if(conf.callback)
	{
		if(rtn!=undefined){
			 conf.callback.call(this,rtn.genDataId,rtn.genDataName,rtn.pubMetadataId,rtn.pubMetadataName);
		}
	}
};

