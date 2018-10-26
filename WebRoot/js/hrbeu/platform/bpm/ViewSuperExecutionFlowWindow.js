/**
 * 任务跳转规则设置。
 * conf参数属性：
 * actDefId act流程定义ID
 * nodeId:流程节点ID
 * @param conf
 */
function ViewSuperExecutionFlowWindow(conf)
{
	if(!conf) conf={};
	var url=__ctx+ "/platform/bpm/processRun/processImage.ht?actInstId="+conf.actInstanceId;
	
	var winArgs="height=600,width=800,status=no,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
	url=url.getNewUrl();
	var win=window.open(url,"superFlow",winArgs);
	win.focus();
	
}