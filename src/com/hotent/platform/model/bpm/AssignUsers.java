package com.hotent.platform.model.bpm;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:bpm_assign_users Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2015-02-11 11:22:47
 */
public class AssignUsers extends BaseModel{
	// 主键
	protected Long  Id;
	// 流程执行实例ID
	protected Long  runId;
	// 流程定义Key
	protected String  defKey;
	// 节点ID
	protected String  nodeId;
	// 节点名称
	protected String  nodeName;
	// 执行人ID
	protected Long  userId;
	// 执行人名称
	protected String  userName;
	// 开始时间
	protected java.util.Date  startTime;
	// 结束时间
	protected java.util.Date  endTime;

	
	

   	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getRunId() {
		return runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	public String getDefKey() {
		return defKey;
	}

	public void setDefKey(String defKey) {
		this.defKey = defKey;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AssignUsers)) 
		{
			return false;
		}
		AssignUsers rhs = (AssignUsers) object;
		return new EqualsBuilder()
		.append(this.Id, rhs.Id)
		.append(this.runId, rhs.runId)
		.append(this.defKey, rhs.defKey)
		.append(this.nodeId, rhs.nodeId)
		.append(this.nodeName, rhs.nodeName)
		.append(this.userId, rhs.userId)
		.append(this.userName, rhs.userName)
		.append(this.startTime, rhs.startTime)
		.append(this.endTime, rhs.endTime)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.Id) 
		.append(this.runId)
		.append(this.defKey)
		.append(this.nodeId)
		.append(this.nodeName)
		.append(this.userId)
		.append(this.userName)
		.append(this.startTime)
		.append(this.endTime)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("Id", this.Id) 
		.append("runId", this.runId) 
		.append("defKey", this.defKey) 
		.append("nodeId", this.nodeId) 
		.append("nodeName", this.nodeName) 
		.append("userId", this.userId) 
		.append("userName", this.userName) 
		.append("startTime", this.startTime) 
		.append("endTime", this.endTime) 
		.toString();
	}
   
  

}