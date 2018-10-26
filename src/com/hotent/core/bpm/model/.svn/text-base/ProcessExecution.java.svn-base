package com.hotent.core.bpm.model;

import org.activiti.engine.impl.persistence.entity.ExecutionEntity;

import com.hotent.core.util.BeanUtils;

/**
 * ExecutionImpl的替代类，由BPM框架直接操作。
 * 直接访问activiti数据库表。
 * @author csx
 *
 */
public class ProcessExecution
{
	private String id;
	private Integer revision;
	private String processInstanceId;
	private String businessKey;
	private String processDefinitionId;
	private String activityId;
	private Short isActive;
	private Short isConcurrent;
	private Short isScope;
	private String parentId;
	private String superExecutionId;
	private Short isEventScope;
	private Integer suspensionState;
	private Integer cachedEntityState;
	
	
	public ProcessExecution()
	{
	}
	
	/**
	 * 根据executionEntity复制excution。
	 * @param executionEntity
	 */
	public ProcessExecution(ExecutionEntity executionEntity)
	{
		this.revision=executionEntity.getRevision();
		this.processInstanceId=executionEntity.getProcessInstanceId();
		//this.businessKey=executionEntity.getBusinessKey();
		this.processDefinitionId=executionEntity.getProcessDefinitionId();
		this.activityId=executionEntity.getActivityId();
		this.isActive=executionEntity.isActive()?(short)1:0;
		this.isConcurrent=executionEntity.isConcurrent()?(short)1:0;
		this.isScope=executionEntity.isScope()?(short)1:0;
		this.parentId=executionEntity.getParentId();
		this.superExecutionId=executionEntity.getSuperExecutionId();
		this.isEventScope=executionEntity.isEventScope()?(short)1:0;
		this.suspensionState=executionEntity.getSuspensionState();
		this.cachedEntityState=executionEntity.getCachedEntityState();
	}
		
	public Integer getRevision()
	{
		return revision;
	}
	
	public void setRevision(Integer revision)
	{
		this.revision = revision;
	}
	
	public String getProcessInstanceId()
	{
		return processInstanceId;
	}
	
	public void setProcessInstanceId(String processInstanceId)
	{
		this.processInstanceId = processInstanceId;
	}
	
	public String getBusinessKey()
	{
		return businessKey;
	}
	public void setBusinessKey(String businessKey)
	{
		this.businessKey = businessKey;
	}
	public String getParentId()
	{
		return parentId;
	}
	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}
	public String getProcessDefinitionId()
	{
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId)
	{
		this.processDefinitionId = processDefinitionId;
	}
	public String getSuperExecutionId()
	{
		return superExecutionId;
	}
	public void setSuperExecutionId(String superExecutionId)
	{
		this.superExecutionId = superExecutionId;
	}
	public String getActivityId()
	{
		return activityId;
	}
	public void setActivityId(String activityId)
	{
		this.activityId = activityId;
	}
	public Short getIsActive()
	{
		return isActive;
	}
	public void setIsActive(Short isActive)
	{
		this.isActive = isActive;
	}
	public Short getIsConcurrent()
	{
		return isConcurrent;
	}
	public void setIsConcurrent(Short isConcurrent)
	{
		this.isConcurrent = isConcurrent;
	}
	public Short getIsScope()
	{
		return isScope;
	}
	public void setIsScope(Short isScope)
	{
		this.isScope = isScope;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Short getIsEventScope() {
		return isEventScope;
	}

	public void setIsEventScope(Short isEventScope) {
		this.isEventScope = isEventScope;
	}

	public Integer getSuspensionState() {
		return suspensionState;
	}

	public void setSuspensionState(Integer suspensionState) {
		this.suspensionState = suspensionState;
	}

	public Integer getCachedEntityState() {
		return cachedEntityState;
	}

	public void setCachedEntityState(Integer cachedEntityState) {
		this.cachedEntityState = cachedEntityState;
	}

}
