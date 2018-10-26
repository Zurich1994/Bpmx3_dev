package com.hotent.platform.model.bpm;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:任务分发 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-27 09:11:41
 */
public class TaskFork extends BaseModel
{
	/**
	 * 令牌值的默认前缀
	 */
	public static String TAKEN_PRE="T";
	/**
	 * 存于任务的令牌变量名称
	 */
	public static String TAKEN_VAR_NAME="_token_";
	
	// taskForkId 主键
	protected Long taskForkId;
	// 流程任务实例ID
	protected String actInstId;
	// 分发任务名
	protected String forkTaskName;
	// 分发任务定义Key
	protected String forkTaskKey;
	// 分发序号
	protected Integer forkSn;
	// 分发个数
	protected Integer forkCount;
	// 完成个数
	protected Integer fininshCount;
	// 分发时间
	protected java.util.Date forkTime;
	// 汇总任务定义Key
	protected String joinTaskKey;
	// 汇总任务名
	protected String joinTaskName;
	//分发的令牌
	protected String forkTokens;
	//分发的令牌前缀
	protected String forkTokenPre;

	public void setTaskForkId(Long taskForkId) 
	{
		this.taskForkId = taskForkId;
	}
	/**
	 * 返回 taskForkId
	 * @return
	 */
	public Long getTaskForkId() 
	{
		return taskForkId;
	}

	public void setActInstId(String actInstId) 
	{
		this.actInstId = actInstId;
	}
	/**
	 * 返回 流程任务实例ID
	 * @return
	 */
	public String getActInstId() 
	{
		return actInstId;
	}

	public void setForkTaskName(String forkTaskName) 
	{
		this.forkTaskName = forkTaskName;
	}
	/**
	 * 返回 分发任务名
	 * @return
	 */
	public String getForkTaskName() 
	{
		return forkTaskName;
	}

	public void setForkTaskKey(String forkTaskKey) 
	{
		this.forkTaskKey = forkTaskKey;
	}
	/**
	 * 返回 分发任务定义Key
	 * @return
	 */
	public String getForkTaskKey() 
	{
		return forkTaskKey;
	}

	public void setForkSn(Integer forkSn) 
	{
		this.forkSn = forkSn;
	}
	/**
	 * 返回 分发序号
	 * @return
	 */
	public Integer getForkSn() 
	{
		return forkSn;
	}

	public void setForkCount(Integer forkCount) 
	{
		this.forkCount = forkCount;
	}
	/**
	 * 返回 分发个数
	 * @return
	 */
	public Integer getForkCount() 
	{
		return forkCount;
	}

	public void setFininshCount(Integer fininshCount) 
	{
		this.fininshCount = fininshCount;
	}
	/**
	 * 返回 完成个数
	 * @return
	 */
	public Integer getFininshCount() 
	{
		return fininshCount;
	}

	public void setForkTime(java.util.Date forkTime) 
	{
		this.forkTime = forkTime;
	}
	/**
	 * 返回 分发时间
	 * @return
	 */
	public java.util.Date getForkTime() 
	{
		return forkTime;
	}

	public void setJoinTaskKey(String joinTaskKey) 
	{
		this.joinTaskKey = joinTaskKey;
	}
	/**
	 * 返回 汇总任务定义Key
	 * @return
	 */
	public String getJoinTaskKey() 
	{
		return joinTaskKey;
	}

	public void setJoinTaskName(String joinTaskName) 
	{
		this.joinTaskName = joinTaskName;
	}
	/**
	 * 返回 汇总任务名
	 * @return
	 */
	public String getJoinTaskName() 
	{
		return joinTaskName;
	}
	

   	public String getForkTokens() {
		return forkTokens;
	}
	public void setForkTokens(String forkTokens) {
		this.forkTokens = forkTokens;
	}

	public String getForkTokenPre() {
		return forkTokenPre;
	}
	public void setForkTokenPre(String forkTokenPre) {
		this.forkTokenPre = forkTokenPre;
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof TaskFork)) 
		{
			return false;
		}
		TaskFork rhs = (TaskFork) object;
		return new EqualsBuilder()
		.append(this.taskForkId, rhs.taskForkId)
		.append(this.actInstId, rhs.actInstId)
		.append(this.forkTaskName, rhs.forkTaskName)
		.append(this.forkTaskKey, rhs.forkTaskKey)
		.append(this.forkSn, rhs.forkSn)
		.append(this.forkCount, rhs.forkCount)
		.append(this.fininshCount, rhs.fininshCount)
		.append(this.forkTime, rhs.forkTime)
		.append(this.joinTaskKey, rhs.joinTaskKey)
		.append(this.joinTaskName, rhs.joinTaskName)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.taskForkId) 
		.append(this.actInstId) 
		.append(this.forkTaskName) 
		.append(this.forkTaskKey) 
		.append(this.forkSn) 
		.append(this.forkCount) 
		.append(this.fininshCount) 
		.append(this.forkTime) 
		.append(this.joinTaskKey) 
		.append(this.joinTaskName) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("taskForkId", this.taskForkId) 
		.append("actInstId", this.actInstId) 
		.append("forkTaskName", this.forkTaskName) 
		.append("forkTaskKey", this.forkTaskKey) 
		.append("forkSn", this.forkSn) 
		.append("forkCount", this.forkCount) 
		.append("fininshCount", this.fininshCount) 
		.append("forkTime", this.forkTime) 
		.append("joinTaskKey", this.joinTaskKey) 
		.append("joinTaskName", this.joinTaskName) 
		.toString();
	}
   
  

}