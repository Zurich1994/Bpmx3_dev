/**
 * 任务跳转规则设置。
 * conf参数属性：
 * actDefId act流程定义ID
 * nodeId:流程节点ID
 * @param conf
 */
function ViewSubFlowWindow(conf)
{
	if(!conf) conf={};
	var url=__ctx+ "/platform/bpm/bpmDefinition/subFlowImage.ht?defId="+conf.defId+"&actDefId=" + conf.actDefId +"&nodeId=" +conf.nodeId;
	
	var dialogWidth=800;
	var dialogHeight=600;
	conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);
	var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
		+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
	url=url.getNewUrl();
	//var rtn=window.showModalDialog(url,"",winArgs);
	
	/*KILLDIALOG*/
	DialogUtil.open({
		height:conf.dialogHeight,
		width: conf.dialogWidth,
		title : '任务跳转规则设置',
		url: url, 
		isResize: true,
	});
}