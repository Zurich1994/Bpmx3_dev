function FlowDetailWindow(conf)
{
	if(!conf) conf={};
	var url=__ctx + "/platform/bpm/processRun/get.ht?tab=1&runId=" + conf.runId;
	url=url.getNewUrl();
	var rtn=window.open(url);
}