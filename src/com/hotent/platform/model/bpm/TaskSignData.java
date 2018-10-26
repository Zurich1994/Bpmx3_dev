package com.hotent.platform.model.bpm;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:任务会签数据 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-19 15:29:52
 */
public class TaskSignData extends BaseModel
{
	/**
	 * 同意
	 */
	public static Short AGREE=1;
	/**
	 * 反对
	 */
	public static Short REFUSE=2;
	/**
	 * 弃票
	 */
	public static Short ABORT=0;
	/**
	 * 驳回 
	 */
	public static Short BACK=3;
	
	/**
	 * 完成
	 */
	public static Short COMPLETED=1;
	/**
	 * 尚没有完成
	 */
	public static Short NOT_COMPLETED=0;
	
	// dataId
	protected Long dataId;
	// 流程实例ID
	protected String actInstId;
	// 流程节点名称
	protected String nodeName;
	// nodeId
	protected String nodeId;
	// 会签任务Id
	protected String taskId;
	// 投票人ID
	protected String voteUserId;
	// 投票人名
	protected String voteUserName;
	// 投票时间
	protected java.util.Date voteTime;
	// 是否同意
	protected Short isAgree;
	// 投票意见内容
	protected String content;
	// signNums
	protected Integer signNums;
	// 是否完成
	protected Short isCompleted;
	
	protected String actDefId;
	/**
	 * 批次
	 */
	protected int batch=1;
	
	//批次号
	protected Long groupNo;
	

	public Long getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(Long groupNo) {
		this.groupNo = groupNo;
	}
	public String getActDefId() {
		return actDefId;
	}
	public void setActDefId(String actDefId) {
		this.actDefId = actDefId;
	}
	public void setDataId(Long dataId) 
	{
		this.dataId = dataId;
	}
	/**
	 * 返回 dataId
	 * @return
	 */
	public Long getDataId() 
	{
		return dataId;
	}

	public void setActInstId(String actInstId) 
	{
		this.actInstId = actInstId;
	}
	/**
	 * 返回 流程实例ID
	 * @return
	 */
	public String getActInstId() 
	{
		return actInstId;
	}

	public void setNodeName(String nodeName) 
	{
		this.nodeName = nodeName;
	}
	/**
	 * 返回 流程节点名称
	 * @return
	 */
	public String getNodeName() 
	{
		return nodeName;
	}

	public void setNodeId(String nodeId) 
	{
		this.nodeId = nodeId;
	}
	/**
	 * 返回 nodeId
	 * @return
	 */
	public String getNodeId() 
	{
		return nodeId;
	}

	public void setTaskId(String taskId) 
	{
		this.taskId = taskId;
	}
	/**
	 * 返回 会签任务Id
	 * @return
	 */
	public String getTaskId() 
	{
		return taskId;
	}

	public void setVoteUserId(String voteUserId) 
	{
		this.voteUserId = voteUserId;
	}
	/**
	 * 返回 投票人ID
	 * @return
	 */
	public String getVoteUserId() 
	{
		return voteUserId;
	}

	public void setVoteUserName(String voteUserName) 
	{
		this.voteUserName = voteUserName;
	}
	/**
	 * 返回 投票人名
	 * @return
	 */
	public String getVoteUserName() 
	{
		return voteUserName;
	}

	public void setVoteTime(java.util.Date voteTime) 
	{
		this.voteTime = voteTime;
	}
	/**
	 * 返回 投票时间
	 * @return
	 */
	public java.util.Date getVoteTime() 
	{
		return voteTime;
	}

	public void setIsAgree(Short isAgree) 
	{
		this.isAgree = isAgree;
	}
	/**
	 * 返回 是否同意
	 * @return
	 */
	public Short getIsAgree() 
	{
		return isAgree;
	}

	public void setContent(String content) 
	{
		this.content = content;
	}
	/**
	 * 返回 投票意见内容
	 * @return
	 */
	public String getContent() 
	{
		return content;
	}

	public void setSignNums(Integer signNums) 
	{
		this.signNums = signNums;
	}
	/**
	 * 返回 signNums
	 * @return
	 */
	public Integer getSignNums() 
	{
		return signNums;
	}

	public void setIsCompleted(Short isCompleted) 
	{
		this.isCompleted = isCompleted;
	}
	/**
	 * 返回 是否完成
	 * @return
	 */
	public Short getIsCompleted() 
	{
		return isCompleted;
	}

   
   	public int getBatch() {
		return batch;
	}
	public void setBatch(int batch) {
		this.batch = batch;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof TaskSignData)) 
		{
			return false;
		}
		TaskSignData rhs = (TaskSignData) object;
		return new EqualsBuilder()
		.append(this.dataId, rhs.dataId)
		.append(this.actInstId, rhs.actInstId)
		.append(this.nodeName, rhs.nodeName)
		.append(this.nodeId, rhs.nodeId)
		.append(this.taskId, rhs.taskId)
		.append(this.voteUserId, rhs.voteUserId)
		.append(this.voteUserName, rhs.voteUserName)
		.append(this.voteTime, rhs.voteTime)
		.append(this.isAgree, rhs.isAgree)
		.append(this.content, rhs.content)
		.append(this.signNums, rhs.signNums)
		.append(this.isCompleted, rhs.isCompleted)
		.append(this.actDefId, rhs.actDefId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.dataId) 
		.append(this.actInstId) 
		.append(this.nodeName) 
		.append(this.nodeId) 
		.append(this.taskId) 
		.append(this.voteUserId) 
		.append(this.voteUserName) 
		.append(this.voteTime) 
		.append(this.isAgree) 
		.append(this.content) 
		.append(this.signNums) 
		.append(this.isCompleted) 
		.append(this.actDefId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("dataId", this.dataId) 
		.append("actInstId", this.actInstId) 
		.append("nodeName", this.nodeName) 
		.append("nodeId", this.nodeId) 
		.append("taskId", this.taskId) 
		.append("voteUserId", this.voteUserId) 
		.append("voteUserName", this.voteUserName) 
		.append("voteTime", this.voteTime) 
		.append("isAgree", this.isAgree) 
		.append("content", this.content) 
		.append("signNums", this.signNums) 
		.append("isCompleted", this.isCompleted) 
		.append("actDefId", this.actDefId)
		.toString();
	}
   
  

}