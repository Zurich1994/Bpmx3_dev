package com.hotent.core.bpm.model;

import java.util.Date;

import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;

import com.hotent.core.util.BeanUtils;

public class ProcessTaskHistory
{
	private String id ;
    private String processDefinitionId ;
    private String processInstanceId ;
    private String executionId ;
    private String name ;
    private String parentTaskId ;
    private String description ;
    private String owner ;
    private String assignee ;
    private Date startTime ;
    private Date endTime ;
    private Long durationInMillis;
    private String deleteReason ;
    private String taskDefinitionKey ;
    private Integer priority;
    private Date dueDate ;
    
    public ProcessTaskHistory()
	{
	
	}
    
    public ProcessTaskHistory(TaskEntity taskEntity)
    {
    	this.processDefinitionId=taskEntity.getId();
    	this.processInstanceId=taskEntity.getProcessInstanceId();
    	this.executionId=taskEntity.getExecutionId();
    	this.name=taskEntity.getName();
    	this.parentTaskId=taskEntity.getParentTaskId();
    	this.description=taskEntity.getDescription();
    	this.owner=taskEntity.getOwner();
    	this.assignee=taskEntity.getAssignee();
    	this.startTime=taskEntity.getCreateTime();
    	//this.endTime=taskEntity.getDueDate();
    	this.dueDate=taskEntity.getDueDate();
    	try{
    		this.durationInMillis=taskEntity.getDueDate()==null?0:taskEntity.getDueDate().getTime()-taskEntity.getCreateTime().getTime();
    	}catch(Exception ex){}
    	this.taskDefinitionKey=taskEntity.getTaskDefinitionKey();
    	this.priority=taskEntity.getPriority();
    }
    
    public ProcessTaskHistory(HistoricTaskInstanceEntity historyTask)
    {
    	BeanUtils.copyProperties(this, historyTask);
    }
    
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getProcessDefinitionId()
	{
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId)
	{
		this.processDefinitionId = processDefinitionId;
	}
	public String getProcessInstanceId()
	{
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId)
	{
		this.processInstanceId = processInstanceId;
	}
	public String getExecutionId()
	{
		return executionId;
	}
	public void setExecutionId(String executionId)
	{
		this.executionId = executionId;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getParentTaskId()
	{
		return parentTaskId;
	}
	public void setParentTaskId(String parentTaskId)
	{
		this.parentTaskId = parentTaskId;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getOwner()
	{
		return owner;
	}
	public void setOwner(String owner)
	{
		this.owner = owner;
	}
	public String getAssignee()
	{
		return assignee;
	}
	public void setAssignee(String assignee)
	{
		this.assignee = assignee;
	}
	public Date getStartTime()
	{
		return startTime;
	}
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}
	public Date getEndTime()
	{
		return endTime;
	}
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}
	public Long getDurationInMillis()
	{
		return durationInMillis;
	}
	public void setDurationInMillis(Long durationInMillis)
	{
		this.durationInMillis = durationInMillis;
	}
	public String getDeleteReason()
	{
		return deleteReason;
	}
	public void setDeleteReason(String deleteReason)
	{
		this.deleteReason = deleteReason;
	}
	public String getTaskDefinitionKey()
	{
		return taskDefinitionKey;
	}
	public void setTaskDefinitionKey(String taskDefinitionKey)
	{
		this.taskDefinitionKey = taskDefinitionKey;
	}
	public Integer getPriority()
	{
		return priority;
	}
	public void setPriority(Integer priority)
	{
		this.priority = priority;
	}
	public Date getDueDate()
	{
		return dueDate;
	}
	public void setDueDate(Date dueDate)
	{
		this.dueDate = dueDate;
	}
}
