package com.hotent.platform.model.bpm;

import java.util.ArrayList;
import java.util.List;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:流程执行堆栈树 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-31 09:57:59
 */
public class ExecutionStack extends BaseModel
{
	/**
	 * 多实例任务
	 */
	public static final Short MULTI_TASK=1;
	/**
	 * 普通任务
	 */
	public static final Short COMMON_TASK=0;
	
	// stackId
	protected Long stackId;
	// 节点ID
	protected String nodeId;
	// 节点名
	protected String nodeName;
	// 开始时间
	protected java.util.Date startTime;
	// 结束时间
	protected java.util.Date endTime;
	// 执行人IDS，如1,2,3
	protected String assignees;
	// 1=是 0=否
	protected Short isMultiTask=COMMON_TASK;
	// 父ID
	protected Long parentId;
	// actInstId
	protected Long actInstId;
	// taskIds
	protected String taskIds;
	// 节点路径
	protected String nodePath;
	// 节点层
	protected Integer depth;
	
	protected String actDefId;
	//任务令牌
	protected String taskToken;
	
	/**
	 * 前面的堆栈。
	 */
	protected ExecutionStack prevExecutionStack=null; 
	
	/**
	 * 后续的节点。
	 */
	protected List<ExecutionStack> outExecutionStacks=new ArrayList<ExecutionStack>();
	

	public void setStackId(Long stackId) 
	{
		this.stackId = stackId;
	}
	/**
	 * 返回 stackId
	 * @return
	 */
	public Long getStackId() 
	{
		return stackId;
	}

	public void setNodeId(String nodeId) 
	{
		this.nodeId = nodeId;
	}
	/**
	 * 返回 节点ID
	 * @return
	 */
	public String getNodeId() 
	{
		return nodeId;
	}

	public void setNodeName(String nodeName) 
	{
		this.nodeName = nodeName;
	}
	/**
	 * 返回 节点名
	 * @return
	 */
	public String getNodeName() 
	{
		return nodeName;
	}

	public void setStartTime(java.util.Date startTime) 
	{
		this.startTime = startTime;
	}
	/**
	 * 返回 开始时间
	 * @return
	 */
	public java.util.Date getStartTime() 
	{
		return startTime;
	}

	public void setEndTime(java.util.Date endTime) 
	{
		this.endTime = endTime;
	}
	/**
	 * 返回 结束时间
	 * @return
	 */
	public java.util.Date getEndTime() 
	{
		return endTime;
	}

	public void setAssignees(String assignees) 
	{
		this.assignees = assignees;
	}
	/**
	 * 返回 执行人IDS，如1,2,3
	 * @return
	 */
	public String getAssignees() 
	{
		return assignees;
	}

	public void setIsMultiTask(Short isMultiTask) 
	{
		this.isMultiTask = isMultiTask;
	}
	/**
	 * 返回 1=是
	 * @return
	 */
	public Short getIsMultiTask() 
	{
		return isMultiTask;
	}

	public void setParentId(Long parentId) 
	{
		this.parentId = parentId;
	}
	/**
	 * 返回 父ID
	 * @return
	 */
	public Long getParentId() 
	{
		return parentId;
	}

	public void setActInstId(Long actInstId) 
	{
		this.actInstId = actInstId;
	}
	/**
	 * 返回 actInstId
	 * @return
	 */
	public Long getActInstId() 
	{
		return actInstId;
	}

	public void setTaskIds(String taskIds) 
	{
		this.taskIds = taskIds;
	}
	/**
	 * 返回 taskIds
	 * @return
	 */
	public String getTaskIds() 
	{
		return taskIds;
	}

	public void setNodePath(String nodePath) 
	{
		this.nodePath = nodePath;
	}
	/**
	 * 返回 节点路径
	 * @return
	 */
	public String getNodePath() 
	{
		return nodePath;
	}

	public void setDepth(Integer depth) 
	{
		this.depth = depth;
	}
	
	/**
	 * 返回 节点层
	 * @return
	 */
	public Integer getDepth() 
	{
		return depth;
	}

   	public String getActDefId() {
		return actDefId;
	}
   	
	public void setActDefId(String actDefId) {
		this.actDefId = actDefId;
	}
	
	public ExecutionStack getPrevExecutionStack() {
		return prevExecutionStack;
	}
	public void setPrevExecutionStack(ExecutionStack prevExecutionStack) {
		this.prevExecutionStack = prevExecutionStack;
	}
	public List<ExecutionStack> getOutExecutionStacks() {
		return outExecutionStacks;
	}
	public void setOutExecutionStacks(List<ExecutionStack> outExecutionStacks) {
		this.outExecutionStacks = outExecutionStacks;
	}
	
	/**
	 * 添加后续的executionStack
	 * @param executionStack
	 */
	public void addOutExecutionStack(ExecutionStack  executionStack){
		this.outExecutionStacks.add(executionStack);
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof ExecutionStack)) 
		{
			return false;
		}
		ExecutionStack rhs = (ExecutionStack) object;
		return new EqualsBuilder()
		.append(this.stackId, rhs.stackId)
		.append(this.nodeId, rhs.nodeId)
		.append(this.nodeName, rhs.nodeName)
		.append(this.startTime, rhs.startTime)
		.append(this.endTime, rhs.endTime)
		.append(this.assignees, rhs.assignees)
		.append(this.isMultiTask, rhs.isMultiTask)
		.append(this.parentId, rhs.parentId)
		.append(this.actInstId, rhs.actInstId)
		.append(this.taskIds, rhs.taskIds)
		.append(this.nodePath, rhs.nodePath)
		.append(this.depth, rhs.depth)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.stackId) 
		.append(this.nodeId) 
		.append(this.nodeName) 
		.append(this.startTime) 
		.append(this.endTime) 
		.append(this.assignees) 
		.append(this.isMultiTask) 
		.append(this.parentId) 
		.append(this.actInstId) 
		.append(this.taskIds) 
		.append(this.nodePath) 
		.append(this.depth) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("stackId", this.stackId) 
		.append("nodeId", this.nodeId) 
		.append("nodeName", this.nodeName) 
		.append("startTime", this.startTime) 
		.append("endTime", this.endTime) 
		.append("assignees", this.assignees) 
		.append("isMultiTask", this.isMultiTask) 
		.append("parentId", this.parentId) 
		.append("actInstId", this.actInstId) 
		.append("taskIds", this.taskIds) 
		.append("nodePath", this.nodePath) 
		.append("depth", this.depth) 
		.toString();
	}
	
	public String getTaskToken() {
		return taskToken;
	}
	public void setTaskToken(String taskToken) {
		this.taskToken = taskToken;
	}
   
  

}