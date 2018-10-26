/**
 * 
 */
package com.hotent.platform.service.bpm;

import java.util.List;

import org.activiti.engine.impl.persistence.entity.ExecutionEntity;

import com.hotent.platform.model.bpm.BpmDefVar;

/**
 * 流程使用到的服务接口。
 * @author hotent
 *
 */
public interface IBpmActService {
	
	/**
	 * 根据流程定义id获取流程变量列表。
	 * @param defId
	 * @return
	 */
	List<BpmDefVar> getVarsByFlowDefId(Long defId);
	
	/**
	 * 根据executionId获取ExecutionEntity对象。
	 * @param executionId
	 * @return
	 */
	ExecutionEntity getExecution(String executionId);
	
	/**
	 * 结束流程
	 * @param taskId
	 */
	void endProcessByTaskId(String taskId);
	
	
	/**
	 * 驳回到某个节点。
	 * @param taskId
	 * @param nodeId
	 */
	boolean reject(String taskId,String nodeId );
	
	
	/**
	 * 根据任务ID判断流程是否允许驳回。
	 * @param taskId
	 * @return
	 */
	boolean isTaskAllowBack(String taskId);
	
	/**
	 * 判断允许子流程实例是否允许驳回。
	 * <pre>
	 * 判断当前是否为子流程实例，并且当前节点位于第一个节点。
	 * </pre>
	 * @param taskId
	 * @return
	 */
	boolean allowCallActivitiBack(String taskId);
	
	

}
