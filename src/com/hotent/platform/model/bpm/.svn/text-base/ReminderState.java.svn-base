package com.hotent.platform.model.bpm;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:任务催办执行情况 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-17 17:17:36
 */
public class ReminderState extends BaseModel
{
	// id
	protected Long id;
	// 任务ID
	protected String taskId;
	// 催办时间
	protected java.util.Date reminderTime;
	//流程实例ID
	protected String actInstanceId="";
	//用户ID
	protected Long userId=0L;
	//流程定义ID
	protected String actDefId="";
	//提醒类型 1，提醒，2办结的动作。
	protected int remindType=1;

	
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 id
	 * @return
	 */
	public Long getId() 
	{
		return id;
	}

	
	
	public void setTaskId(String taskId) 
	{
		this.taskId = taskId;
	}
	/**
	 * 返回 任务ID
	 * @return
	 */
	public String getTaskId() 
	{
		return taskId;
	}

	public void setReminderTime(java.util.Date reminderTime) 
	{
		this.reminderTime = reminderTime;
	}
	/**
	 * 返回 催办时间
	 * @return
	 */
	public java.util.Date getReminderTime() 
	{
		return reminderTime;
	}
	
	public String getActInstanceId() {
		return actInstanceId;
	}
	public void setActInstanceId(String actInstanceId) {
		this.actInstanceId = actInstanceId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getActDefId() {
		return actDefId;
	}
	public void setActDefId(String actDefId) {
		this.actDefId = actDefId;
	}
   
   	public int getRemindType() {
		return remindType;
	}
	public void setRemindType(int remindType) {
		this.remindType = remindType;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof ReminderState)) 
		{
			return false;
		}
		ReminderState rhs = (ReminderState) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
	
		.append(this.taskId, rhs.taskId)
		.append(this.reminderTime, rhs.reminderTime)
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
		.append(this.reminderTime) 
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
		.append("reminderTime", this.reminderTime) 
		.toString();
	}
   
  

}