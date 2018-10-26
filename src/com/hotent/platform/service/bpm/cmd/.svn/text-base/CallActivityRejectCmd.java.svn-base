package com.hotent.platform.service.bpm.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;

import com.hotent.core.bpm.util.ActivitiUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.platform.event.def.CallActivitiBackEvent;

/**
 * 子流程驳回命令。
 * <pre>
 * 1.传入子流程的任务ID。
 * 2.查询父实例是否存在，如果不存在则直接返回。
 * 3.查询到父实例后，取得他的父实例，直到他的scope为true为止。
 * </pre>
 * @author ray
 *
 */
public class CallActivityRejectCmd implements Command<Boolean>{
	
	private String taskId="";
	private String targetNode="";
	

	public CallActivityRejectCmd(){}
	
	public CallActivityRejectCmd(String taskId){
		this.taskId=taskId;
	}
	

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public void setTargetNode(String targetNode) {
		this.targetNode = targetNode;
	}

	public Boolean execute(CommandContext context) {
		RepositoryService repositoryService=AppUtil.getBean(RepositoryService.class) ;
		ExecutionEntityManager executionManager= context.getExecutionEntityManager();
		TaskEntity taskEnt=context.getTaskEntityManager().findTaskById(taskId);
		ExecutionEntity execution= taskEnt.getExecution();
		
		ExecutionEntity superEnt= execution.getSuperExecution();
		if(superEnt==null) return false;
		
		while(!superEnt.isScope()){
			superEnt=superEnt.getParent();
		}
		List<TaskEntity> taskList=new ArrayList<TaskEntity>();
		//如果是顶级实例数据
		//如果是活动的那说明这个实例为单实例的子流程。
		//如果是非活动说明这个实例为多实例子流程。
		//单实例
		if(superEnt.isActive()){
			taskList.add(taskEnt);
		}
		//多实例
		else{
			List<ExecutionEntity> executionList= superEnt.getExecutions();
			for(ExecutionEntity tmp:executionList){
				ExecutionEntity subEnt=executionManager.findSubProcessInstanceBySuperExecutionId(tmp.getId());
				taskList.addAll(subEnt.getTasks());
			}
		}
		//定义事件。
		CallActivitiBackEvent event= new CallActivitiBackEvent(taskList);
		event.setCurTaskId(taskId);
		//发布事件。
		AppUtil.publishEvent(event);
		
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity)
				((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(superEnt.getProcessDefinitionId());
		ActivityImpl curAct= processDefinition.findActivity(superEnt.getActivityId());
		curAct.getOutgoingTransitions().clear();
		//创建一个连接
		ActivityImpl destAct= processDefinition.findActivity(targetNode);
		TransitionImpl transitionImpl = curAct.createOutgoingTransition();
		transitionImpl.setDestination(destAct);
		superEnt.take(transitionImpl);
		return true;
	}

}
