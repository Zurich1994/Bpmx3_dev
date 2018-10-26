package com.hotent.platform.service.bpm;

import java.util.Map;

public interface IFlowStatus {
	
	/**根据流程实例ID，获取流程的节点的状态显示颜色
	 * @param instanceId
	 * @return 流程节点的状态的显示颜色的Map：key=nodeId，value=color
	 */
	Map<String, String> getStatusByInstanceId(Long instanceId);
		
}
