
/**
 * 简单设置。
 * conf:参数如下：
 * deployId:流程定义ID
 * actDefId：act流程节点Id
 * nodeId：流程节点Id
 * dialogWidth：窗口宽度，默认值500
 * dialogWidth：窗口宽度，默认值300
 * @param conf
 */

function SimpleSetWindow(conf)
{	
	var url=__ctx + "/devicein/deviceinpack/deviceInfomation/smailedit.ht?actDefId=" + conf.actDefId +"&nodeId=" + conf.nodeId+"&manageIP=" + conf.manageIP;
	
	
	DialogUtil.open({
		height:650,
		width: 800,
		title : '设备详细信息',
		url: url, 
		isResize: true,
	});
}