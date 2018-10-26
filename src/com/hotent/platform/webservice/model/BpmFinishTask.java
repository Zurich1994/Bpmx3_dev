package com.hotent.platform.webservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class BpmFinishTask {

	protected Long opinionId;
	//Activiti流程定义ID
	protected String actDefId;
	// actInstId
	protected String actInstId;
	// 任务名称
	protected String taskName;
	// 任务Key
	protected String taskKey;
	// taskId
	protected Long taskId;
	// 执行开始时间
	protected java.util.Date startTime;
	// 结束时间
	protected java.util.Date endTime;
	// 持续时间
	protected Long durTime;
	// 执行人ID
	protected Long exeUserId;
	// 执行人名
	protected String exeFullname;
	// 审批意见
	protected String opinion;
	// 审批状态
	protected Short checkStatus;
	// 流程名称
	protected String flowName;
	// 事项标题
	protected String subject;
	// 业务主键
	protected String businessKey;
	// 表单明细url
	protected String formUrl;

	public Long getOpinionId() {
		return opinionId;
	}

	public void setOpinionId(Long opinionId) {
		this.opinionId = opinionId;
	}

	public String getActInstId() {
		return actInstId;
	}

	public void setActInstId(String actInstId) {
		this.actInstId = actInstId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public java.util.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public java.util.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	public Long getDurTime() {
		return durTime;
	}

	public void setDurTime(Long durTime) {
		this.durTime = durTime;
	}

	public Long getExeUserId() {
		return exeUserId;
	}

	public void setExeUserId(Long exeUserId) {
		this.exeUserId = exeUserId;
	}

	public String getExeFullname() {
		return exeFullname;
	}

	public void setExeFullname(String exeFullname) {
		this.exeFullname = exeFullname;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Short getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Short checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}

}
