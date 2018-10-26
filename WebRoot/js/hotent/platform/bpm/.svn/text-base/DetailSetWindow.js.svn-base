
/**
 * 设备选择。
 * conf:参数如下：
 * deployId:流程定义ID
 * actDefId：act流程节点Id
 * nodeId：流程节点Id
 * dialogWidth：窗口宽度，默认值500
 * dialogWidth：窗口宽度，默认值300
 * @param conf
 */

function DetailSetWindow(conf)
{	
	var nodeName = conf.nodeName;
	var titl = "";
	var url = "";
	if(nodeName=="netRouter")
	{
		url=__ctx + "/deviceRouter/deviceRouter/deviceRouter/smailedit.ht?actDefId=" + conf.actDefId +"&nodeId=" + conf.nodeId;
		titl = "路由器";
	}else if(nodeName=="Switch"){
		url=__ctx + "/switchSet/switchSet/deviceSwitch/smailedit.ht?actDefId=" + conf.actDefId +"&nodeId=" + conf.nodeId;
		titl = "交换机";
	}else if(nodeName=="Server"){
		url=__ctx + "/serviceSet/serviceSet/deviceServer/smailedit.ht?actDefId=" + conf.actDefId +"&nodeId=" + conf.nodeId;
		titl = "服务器";
	}else if(nodeName=="Firewall"){
		url=__ctx + "/firewallSet/firewallSet/firewall/smailedit.ht?actDefId=" + conf.actDefId +"&nodeId=" + conf.nodeId;
		titl = "防火墙";
	}else if(nodeName=="KVM"){
		url=__ctx + "/kvmSet/kvmSet/kvm/smailedit.ht?actDefId=" + conf.actDefId +"&nodeId=" + conf.nodeId;
		titl = "KVM";
	}else{
		
	}
	
	DialogUtil.open({
		height:650,
		width: 800,
		title : titl + '信息配置',
		url: url, 
		isResize: true,
	});
}