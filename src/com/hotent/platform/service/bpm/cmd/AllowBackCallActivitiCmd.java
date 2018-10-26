package com.hotent.platform.service.bpm.cmd;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;

import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.service.bpm.thread.TaskThreadService;

/**
 * 判断在审批过程中是否属于子流程的驳回。
 * <pre>
 * 1.判断是否为驳回指令。
 * 2.如果不是驳回动作，那么直接返回false。
 * 3.判断当前任务是否为子流程实例。
 * 4.如果是判断当前节点是否是子流程的第一个节点，如果是那么允许驳回。
 * </pre>
 * @author ray
 *
 */
public class AllowBackCallActivitiCmd  implements Command<Boolean> {
	
	private String taskId="";
	
	public AllowBackCallActivitiCmd(String taskId){
		this.taskId=taskId;
	}

	public Boolean execute(CommandContext context) {
		ProcessCmd cmd= TaskThreadService.getProcessCmd();
		if(cmd.isBack()!=1) return false;
		TaskEntity taskEntity= context.getTaskEntityManager().findTaskById(taskId);
		ExecutionEntity entity=taskEntity.getExecution();
		
		String superEntId=entity.getSuperExecutionId();
		//判断当前是否子流程实例。
		if(StringUtil.isEmpty(superEntId)) return false;
		//判断当前节点是否第一个节点。
		boolean rtn=NodeCache.isFirstNode(entity.getProcessDefinitionId(),entity.getActivityId());
		return rtn;
	}

}
