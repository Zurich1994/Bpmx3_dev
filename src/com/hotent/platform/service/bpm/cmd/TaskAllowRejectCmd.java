package com.hotent.platform.service.bpm.cmd;

import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;

import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.service.bpm.BpmNodeSetService;

/**
 * 流程任务是否允许驳回。
 * <pre>
 * 1.当前节点为分发节点不允许驳回。
 * 2.当前节点为汇总节点不允许驳回。
 * 3.如果当前节点为流程定义的第一个节点并且不是外部子流程实例不允许驳回。
 * 4.前面节点不是userTask或者exclusiveGateway不允许驳回。
 * </pre>
 * @author ray
 *
 */
public class TaskAllowRejectCmd implements Command<Boolean>{
	
	private String taskId="";
	
	public TaskAllowRejectCmd(String taskId){
		this.taskId=taskId;
	}
	
	private ExecutionEntity getSuperExecutionEnt(ExecutionEntity executionEntity){
		ExecutionEntity superEnt=executionEntity.getSuperExecution();
		while(!superEnt.isScope()){
			superEnt=superEnt.getParent();
		}
		return superEnt;
	}

	public Boolean execute(CommandContext context) {
		
		BpmNodeSetService bpmNodeSetService=AppUtil.getBean(BpmNodeSetService.class);
		
		TaskEntity taskEnt=context.getTaskEntityManager().findTaskById(taskId);
		ExecutionEntity executionEntity=taskEnt.getExecution();
		String actDefId=taskEnt.getProcessDefinitionId();
		String nodeId=taskEnt.getTaskDefinitionKey();
		
		
		//获取当前节点的配置，判断是否是分发节点，是则不能有驳回功能
		BpmNodeSet bpmNodeSet = bpmNodeSetService.getBpmNodeSetByActDefIdNodeId(actDefId, nodeId);
		if (BeanUtils.isNotEmpty(bpmNodeSet)) {
			if (BeanUtils.isNotEmpty(bpmNodeSet.getNodeType()) && bpmNodeSet.getNodeType()==1) {
				return false;
			}
		}
		//节点为汇总节点
		bpmNodeSet =bpmNodeSetService.getByActDefIdJoinTaskKey(actDefId, nodeId);
		if(BeanUtils.isNotEmpty(bpmNodeSet)){
			return false;
		}
		
		boolean rtn=NodeCache.isFirstNode(actDefId, nodeId);
		//当前节点为流程第一个节点，并且流程实例非子流程实例。
		if(rtn ){
			if(StringUtil.isEmpty(executionEntity.getSuperExecutionId()) ){
				return false;
			}
			else{
				ExecutionEntity superEnt=getSuperExecutionEnt(executionEntity);
				nodeId=superEnt.getActivityId();
				actDefId=superEnt.getProcessDefinitionId();
			}
		}
		
		Map<String, FlowNode> map = NodeCache.getByActDefId(actDefId);
		FlowNode flowNode = map.get(nodeId);
		List<FlowNode> preFlowNodeList = flowNode.getPreFlowNodes();
		for (FlowNode preNode : preFlowNodeList) {
			if(!"userTask".equals(preNode.getNodeType()) && !"exclusiveGateway".equals(preNode.getNodeType())){
				return false;
			}
		}
		
		return true;
	}

	

}
