package com.hotent.platform.model.bpm;

import java.util.ArrayList;
import java.util.List;

import com.hotent.core.model.TaskExecutor;

/**
 * 流程任务审批状态
 * @author 
 *
 */
public class TaskNodeStatus {
	
	//流程实例ID
	private String actInstId;
	//任务Key
	private String taskKey;
	//最后审批状态  审批状态 值来自TaskOpinion中的STATUS的常量
	/*
	 * 1=同意
	   2=反对
	   3=驳回
	   0=弃权
	   -1=正在审批
	   -2=尚未审批
	*/
	private Short lastCheckStatus=TaskOpinion.STATUS_INIT;
	/**
	 * 节点上的审批列表
	 */
	private List<TaskOpinion> taskOpinionList=new ArrayList<TaskOpinion>();
	
	/**
	 * 节点上的审批人
	 */
	private List<TaskExecutor> taskExecutorList=new ArrayList<TaskExecutor>();
	
	public void setLastCheckStatus(Short lastCheckStatus) {
		this.lastCheckStatus = lastCheckStatus;
	}

	public TaskNodeStatus() {
	
	}

	public String getActInstId() {
		return actInstId;
	}

	public void setActInstId(String actInstId) {
		this.actInstId = actInstId;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public List<TaskOpinion> getTaskOpinionList() {
		return taskOpinionList;
	}

	public void setTaskOpinionList(List<TaskOpinion> taskOpinionList) {
		this.taskOpinionList = taskOpinionList;
	}

	public TaskNodeStatus(String actInstId, String taskKey,
			Short lastCheckStatus, List<TaskOpinion> taskOpinionList,List<TaskExecutor> taskExecutorList) {
		super();
		this.actInstId = actInstId;
		this.taskKey = taskKey;
		this.lastCheckStatus = lastCheckStatus;
		this.taskOpinionList = taskOpinionList;
		this.taskExecutorList = taskExecutorList;
	}

	public Short getLastCheckStatus() {
		return lastCheckStatus;
	}

	public List<TaskExecutor> getTaskExecutorList() {
		return taskExecutorList;
	}

	public void setTaskExecutorList(List<TaskExecutor> taskExecutorList) {
		this.taskExecutorList = taskExecutorList;
	}	
	
}
