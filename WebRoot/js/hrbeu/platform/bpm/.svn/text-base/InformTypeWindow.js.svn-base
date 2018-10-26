/**
 * 通知方式
{actDefId:actDefId,nodeId:activitiId,activityName:activityName}
 * @param conf
 */
InformTypeWindow=function(conf){
	if(!conf) conf={};
	var url=__ctx + "/platform/bpm/bpmDefinition/informType.ht?actDefId=" + conf.actDefId+"&nodeId=" + conf.nodeId;
	var dialogWidth=400;
	var dialogHeight=200;
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);
	var winArgs="dialogWidth:"+conf.dialogWidth+"px;dialogHeight:"+conf.dialogHeight
		+"px;help:" + conf.help +";status:" + conf.status +";scroll:" + conf.scroll +";center:" +conf.center;
	if(conf.parentActDefId){
		url += "&parentActDefId="+conf.parentActDefId;
	}
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,"",winArgs);
	if (conf.callback) {
		conf.callback.call(this,rtn);
	}
};