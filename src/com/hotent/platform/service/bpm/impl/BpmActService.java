package com.hotent.platform.service.bpm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.util.ActivitiUtil;
import com.hotent.platform.dao.bpm.BpmDefVarDao;
import com.hotent.platform.model.bpm.BpmDefVar;
import com.hotent.platform.service.bpm.IBpmActService;
import com.hotent.platform.service.bpm.cmd.AllowBackCallActivitiCmd;
import com.hotent.platform.service.bpm.cmd.CallActivityRejectCmd;
import com.hotent.platform.service.bpm.cmd.EndProcessCmd;
import com.hotent.platform.service.bpm.cmd.GetExecutionCmd;
import com.hotent.platform.service.bpm.cmd.TaskAllowRejectCmd;

@Service
public class BpmActService  implements IBpmActService {
	
	@Resource
	private BpmDefVarDao dao;
	public BpmActService() {
		
	}
	
	public List<BpmDefVar> getVarsByFlowDefId(Long defId) {
		return dao.getVarsByFlowDefId(defId);
	}
	
	
	public ExecutionEntity getExecution(String executionId) {
		CommandExecutor commandExecutor=ActivitiUtil.getCommandExecutor();
		return commandExecutor.execute(new GetExecutionCmd(executionId)) ;
	}
	
	/**
	 * 根据任务ID结束流程。
	 * @param taskId
	 */
	public void endProcessByTaskId(String taskId){
		CommandExecutor commandExecutor=ActivitiUtil.getCommandExecutor();
		EndProcessCmd cmd=new EndProcessCmd(taskId);
		commandExecutor.execute(cmd);
	}

	public boolean reject(String taskId, String nodeId) {
		CommandExecutor commandExecutor=ActivitiUtil.getCommandExecutor();
		CallActivityRejectCmd cmd=new CallActivityRejectCmd(taskId);
		cmd.setTargetNode(nodeId);
		return  commandExecutor.execute(cmd);
	}

	public boolean isTaskAllowBack(String taskId) {
		CommandExecutor commandExecutor=ActivitiUtil.getCommandExecutor();
		TaskAllowRejectCmd cmd=new TaskAllowRejectCmd(taskId);
		return commandExecutor.execute(cmd);
	}

	public boolean allowCallActivitiBack(String taskId) {
		CommandExecutor commandExecutor=ActivitiUtil.getCommandExecutor();
		AllowBackCallActivitiCmd cmd=new AllowBackCallActivitiCmd(taskId);
		return commandExecutor.execute(cmd);
	}

}
