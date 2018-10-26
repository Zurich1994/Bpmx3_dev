package com.hotent.platform.model.bpm;


/**
 * 对象功能:流程任务执行人状态Model对象 开发公司:广州宏天软件有限公司 开发人员:helh 创建时间:2013-10-28 11:19:10
 */
public class TaskExeStatus {
	//执行人或候选人的ID
	String executorId;
	//任务执行人
	public String executor;
	//候选人
	public String candidateUser;
	//是否已读
	public boolean isRead;
	//执行人或候选人的类型
	public String type;
	//如果type==user 则需要求出每个用户的主组织
	public String mainOrgName;
	
	public String getExecutorId() {
		return executorId;
	}
	public void setExecutorId(String executorId) {
		this.executorId = executorId;
	}
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
	}
	public String getCandidateUser() {
		return candidateUser;
	}
	public void setCandidateUser(String candidateUser) {
		this.candidateUser = candidateUser;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public void setMainOrgName(String mainOrgName){
		this.mainOrgName=mainOrgName;
	}
	
	public String getMainOrgName(){
		return mainOrgName;
	}
}
