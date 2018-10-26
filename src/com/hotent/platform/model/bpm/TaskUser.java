package com.hotent.platform.model.bpm;

/**
 * 任务的用户授权实体
 * @author csx
 *
 */
public class TaskUser
{
	public TaskUser(){
		
	}
	/**
	 * 以下字段映射回act_ru_identitylink表
	 */
	protected String id;
	protected Integer reversion;
	protected String groupId;
	/**
	 * 用户类型
	 */
	protected String type;
	protected String userId;
	protected String taskId;
	
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Integer getReversion()
	{
		return reversion;
	}

	public void setReversion(Integer reversion)
	{
		this.reversion = reversion;
	}

	public String getGroupId()
	{
		return groupId;
	}

	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getTaskId()
	{
		return taskId;
	}

	public void setTaskId(String taskId)
	{
		this.taskId = taskId;
	}

	
}
