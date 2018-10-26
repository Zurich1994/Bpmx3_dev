/**
 * 功能:流程运行对话框
 * 
 * 参数：
 * conf:配置为一个JSON
 * 
 * dialogWidth:对话框的宽度。
 * dialogHeight：对话框高度。
 * 
 * actDefId:流程定义ID。
 * 
 */
function BpmImageDialog(conf)
{
	if(!conf) conf={};
	var url=__ctx + '/platform/bpm/bpmDefinition/flowImg.ht?actDefId=' + conf.actDefId;
	url=url.getNewUrl();
	DialogUtil.open({
		height:600,
		width: 800,
		title : '流程图',
		url: url, 
		isResize: true
	});
}