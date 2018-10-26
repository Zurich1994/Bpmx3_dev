package com.hotent.platform.model.bpm;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.hotent.core.model.BaseModel;

/**
 * 对象功能:流程流转状态
 * 对象 开发公司:广州宏天软件有限公司 
 * 开发人员:helh 创建时间:2013-09-18
 * 09:21:29
 */
public class BpmProTransTo extends BaseModel {
	/**
	 * 主键
	 */
	protected Long id=0L;
	/**
	 * 任务id
	 */
	protected Long taskId=0L;
	/**
	 * 流程实例id
	 */
	protected Long actInstId=0L;
	/**
	 * 流转类型(1,非会签,2,会签)
	 */
	protected Integer transType;
	/**
	 * 执行后动作(1,返回,2,提交)
	 */
	protected Integer action;
	/**
	 * 发起人ID
	 */
	protected Long createUserId=0L;
	/**
	 * 创建时间
	 */
	protected java.util.Date createtime;
	/**
	 * 被流转人投票结果,1同意,2反对
	 */
	protected Integer transResult;
	/**
	 * 流转接收人
	 */
	protected String assignee;
	
	//流程相关
	/**
	 * 流程实例runId
	 */
	protected Long runId=0L;
	/**
	 * 流程实例标题
	 */
	protected String subject;
	/**
	 * 流程名称
	 */
	protected String processName;
	
	/**
	 * 类型
	 */
	protected Long typeId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public Integer getTransType() {
		return transType;
	}
	public void setTransType(Integer transType) {
		this.transType = transType;
	}
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public java.util.Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	public Long getActInstId() {
		return actInstId;
	}
	public void setActInstId(Long actInstId) {
		this.actInstId = actInstId;
	}
	public Integer getTransResult() {
		return transResult;
	}
	public void setTransResult(Integer transResult) {
		this.transResult = transResult;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	public Long getRunId() {
		return runId;
	}
	public void setRunId(Long runId) {
		this.runId = runId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmProStatus)) 
		{
			return false;
		}
		BpmProTransTo rhs = (BpmProTransTo) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.taskId, rhs.taskId)
		.append(this.transType, rhs.transType)
		.append(this.action, rhs.action)
		.append(this.createUserId, rhs.createUserId)
		.append(this.createtime, rhs.createtime)
		.append(this.actInstId, rhs.actInstId)
		.append(this.assignee, rhs.assignee)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.taskId) 
		.append(this.transType) 
		.append(this.action) 
		.append(this.createUserId) 
		.append(this.createtime) 
		.append(this.actInstId)
		.append(this.assignee)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("taskId", this.taskId) 
		.append("transType", this.transType) 
		.append("createUserId", this.createUserId) 
		.append("createtime", this.createtime) 
		.append("action", this.action) 
		.append("actInstId", this.actInstId)
		.append("assignee", this.assignee)
		.toString();
	}
	
}