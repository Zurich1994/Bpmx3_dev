package com.hotent.platform.model.bpm;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * 对象功能:任务转办代理 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-03-27 12:02:35
 */
@SuppressWarnings("serial")
public class BpmTaskExe extends BaseModel
{

	/**	0=初始状态*/
	public final static Short STATUS_INIT =0;
	/** 1=完成  --由代理人来完成*/
	public final static Short STATUS_COMPLETE =1;
	/**2=取消  --代理人撤消*/
	public final static Short STATUS_CANCEL =2;
	/**	3=任务完成  --由其他人来完成*/
	public final static Short STATUS_OTHER_COMPLETE =3;
	/**	4=驳回*/
	public final static Short STATUS_BACK =4;
	
	/**	1=代理*/
	public final static Short TYPE_ASSIGNEE=1;
	/**	2=转办*/
	public final static Short TYPE_TRANSMIT =2;
	/**	3=流转*/
	public final static Short TYPE_TRANSTO =3;
	/**	4=多级转办*/
	public final static Short TYPE_MUTIL_ASSIGNEE=4;
	// ID
	protected Long  id;
	// 任务ID
	protected Long  taskId;
	// 承接人ID
	protected Long  assigneeId;
	// 承接人
	protected String  assigneeName;
	// 被代理人ID
	protected Long  ownerId;
	// 被代理人
	protected String  ownerName;
	// 实例标题
	protected String  subject;
	// STATUS
	protected Short  status;
	// 原因备注
	protected String  memo;
	// CRATETIME
	protected java.util.Date  cratetime;
	// 流程实例ID
	protected Long  actInstId;
	// TASK_NAME
	protected String  taskName;
	// 任务Key
	protected String  taskDefKey;
	// 完成时间
	protected java.util.Date  exeTime;
	// EXE_USER_ID
	protected Long  exeUserId;
	// 完成人名
	protected String  exeUserName;
	// 1=代理 2=转办3=流转
	protected Short  assignType;
	//运行ID
	protected Long  runId;
	//类型
	protected Long typeId;
	//转办时 发送哪几种消息给  收到转办任务的人
	protected String informType;
	
	//以下是查询
	protected String processName;
	protected String codebefore;
	protected Long creatorId;


	protected String creator;
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 ID
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setTaskId(Long taskId) 
	{
		this.taskId = taskId;
	}
	/**
	 * 返回 任务ID
	 * @return
	 */
	public Long getTaskId() 
	{
		return this.taskId;
	}
	public void setAssigneeId(Long assigneeId) 
	{
		this.assigneeId = assigneeId;
	}
	/**
	 * 返回 承接人ID
	 * @return
	 */
	public Long getAssigneeId() 
	{
		return this.assigneeId;
	}
	public void setAssigneeName(String assigneeName) 
	{
		this.assigneeName = assigneeName;
	}
	/**
	 * 返回 ASSIGNEE_NAME
	 * @return
	 */
	public String getAssigneeName() 
	{
		return this.assigneeName;
	}
	public void setOwnerId(Long ownerId) 
	{
		this.ownerId = ownerId;
	}
	/**
	 * 返回 OWNER_ID
	 * @return
	 */
	public Long getOwnerId() 
	{
		return this.ownerId;
	}
	public void setOwnerName(String ownerName) 
	{
		this.ownerName = ownerName;
	}
	/**
	 * 返回 被代理人
	 * @return
	 */
	public String getOwnerName() 
	{
		return this.ownerName;
	}
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}
	/**
	 * 返回 实例标题
	 * @return
	 */
	public String getSubject() 
	{
		return this.subject;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	
	/**
	 * 返回 STATUS
	 * @return
	 */
	public Short getStatus() {
		return status;
	}

	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	/**
	 * 返回 原因备注
	 * @return
	 */
	public String getMemo() 
	{
		return this.memo;
	}
	public void setCratetime(java.util.Date cratetime) 
	{
		this.cratetime = cratetime;
	}
	/**
	 * 返回 CRATETIME
	 * @return
	 */
	public java.util.Date getCratetime() 
	{
		return this.cratetime;
	}
	public void setActInstId(Long actInstId) 
	{
		this.actInstId = actInstId;
	}
	/**
	 * 返回 流程实例ID
	 * @return
	 */
	public Long getActInstId() 
	{
		return this.actInstId;
	}
	public void setTaskName(String taskName) 
	{
		this.taskName = taskName;
	}
	/**
	 * 返回 TASK_NAME
	 * @return
	 */
	public String getTaskName() 
	{
		return this.taskName;
	}
	public void setTaskDefKey(String taskDefKey) 
	{
		this.taskDefKey = taskDefKey;
	}
	/**
	 * 返回 任务Key
	 * @return
	 */
	public String getTaskDefKey() 
	{
		return this.taskDefKey;
	}
	public void setExeTime(java.util.Date exeTime) 
	{
		this.exeTime = exeTime;
	}
	/**
	 * 返回 完成时间
	 * @return
	 */
	public java.util.Date getExeTime() 
	{
		return this.exeTime;
	}
	public void setExeUserId(Long exeUserId) 
	{
		this.exeUserId = exeUserId;
	}
	/**
	 * 返回 EXE_USER_ID
	 * @return
	 */
	public Long getExeUserId() 
	{
		return this.exeUserId;
	}
	public void setExeUserName(String exeUserName) 
	{
		this.exeUserName = exeUserName;
	}
	/**
	 * 返回 完成人名
	 * @return
	 */
	public String getExeUserName() 
	{
		return this.exeUserName;
	}

   	public Short getAssignType() {
		return assignType;
	}
	public void setAssignType(Short assignType) {
		this.assignType = assignType;
	}
	
	public Long getRunId() {
		return runId;
	}
	public void setRunId(Long runId) {
		this.runId = runId;
	}
	
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getInformType() {
		return informType;
	}
	public void setInformType(String informType) {
		this.informType = informType;
	}
	
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getCodebefore() {
		return codebefore;
	}
	public void setCodebefore(String codebefore) {
		this.codebefore = codebefore;
	}
	
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmTaskExe)) 
		{
			return false;
		}
		BpmTaskExe rhs = (BpmTaskExe) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.taskId, rhs.taskId)
		.append(this.assigneeId, rhs.assigneeId)
		.append(this.assigneeName, rhs.assigneeName)
		.append(this.ownerId, rhs.ownerId)
		.append(this.ownerName, rhs.ownerName)
		.append(this.subject, rhs.subject)
		.append(this.status, rhs.status)
		.append(this.memo, rhs.memo)
		.append(this.cratetime, rhs.cratetime)
		.append(this.actInstId, rhs.actInstId)
		.append(this.taskName, rhs.taskName)
		.append(this.taskDefKey, rhs.taskDefKey)
		.append(this.exeTime, rhs.exeTime)
		.append(this.exeUserId, rhs.exeUserId)
		.append(this.exeUserName, rhs.exeUserName)
		.append(this.assignType, rhs.assignType)
		.append(this.runId, rhs.runId)
		.append(this.typeId, rhs.typeId)
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
		.append(this.assigneeId) 
		.append(this.assigneeName) 
		.append(this.ownerId) 
		.append(this.ownerName) 
		.append(this.subject) 
		.append(this.status) 
		.append(this.memo) 
		.append(this.cratetime) 
		.append(this.actInstId) 
		.append(this.taskName) 
		.append(this.taskDefKey) 
		.append(this.exeTime) 
		.append(this.exeUserId) 
		.append(this.exeUserName) 
		.append(this.assignType)
		.append(this.runId)
		.append(this.typeId)
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
		.append("assigneeId", this.assigneeId) 
		.append("assigneeName", this.assigneeName) 
		.append("ownerId", this.ownerId) 
		.append("ownerName", this.ownerName) 
		.append("subject", this.subject) 
		.append("status", this.status) 
		.append("memo", this.memo) 
		.append("cratetime", this.cratetime) 
		.append("actInstId", this.actInstId) 
		.append("taskName", this.taskName) 
		.append("taskDefKey", this.taskDefKey) 
		.append("exeTime", this.exeTime) 
		.append("exeUserId", this.exeUserId) 
		.append("exeUserName", this.exeUserName) 
		.append("assignType", this.assignType) 
		.append("runId",this.runId)
		.append("typeId",this.typeId)
		.toString();
	}
   
  

}