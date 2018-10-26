function FlowSetWindow(conf)
{
	if(!conf) conf={};
	var url=__ctx+ "/platform/bpm/bpmDefinition/subFlowDetail.ht?defId="+conf.defId+"&actDefId=" + conf.actDefId +"&nodeId=" +conf.nodeId;
	url=url.getNewUrl();
	var rtn=window.open(url);
}