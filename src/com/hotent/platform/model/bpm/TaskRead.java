package com.hotent.platform.model.bpm;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:任务是否已读 Model对象
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-04-16 17:30:53
 */
public class TaskRead extends BaseModel
{
	// 主键
	protected Long  id;
	// 流程实例ID
	protected Long  actinstid;
	// 任务ID
	protected Long  taskid;
	// 用户ID
	protected Long  userid;
	// 用户名
	protected String  username;
	// 创建时间
	protected java.util.Date  createtime;
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setActinstid(Long actinstid) 
	{
		this.actinstid = actinstid;
	}
	/**
	 * 返回 流程实例ID
	 * @return
	 */
	public Long getActinstid() 
	{
		return this.actinstid;
	}
	public void setTaskid(Long taskid) 
	{
		this.taskid = taskid;
	}
	/**
	 * 返回 任务ID
	 * @return
	 */
	public Long getTaskid() 
	{
		return this.taskid;
	}
	public void setUserid(Long userid) 
	{
		this.userid = userid;
	}
	/**
	 * 返回 用户ID
	 * @return
	 */
	public Long getUserid() 
	{
		return this.userid;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	/**
	 * 返回 用户名
	 * @return
	 */
	public String getUsername() 
	{
		return this.username;
	}
	public void setCreatetime(java.util.Date createtime) 
	{
		this.createtime = createtime;
	}
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreatetime() 
	{
		return this.createtime;
	}


   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof TaskRead)) 
		{
			return false;
		}
		TaskRead rhs = (TaskRead) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.actinstid, rhs.actinstid)
		.append(this.taskid, rhs.taskid)
		.append(this.userid, rhs.userid)
		.append(this.username, rhs.username)
		.append(this.createtime, rhs.createtime)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.actinstid) 
		.append(this.taskid) 
		.append(this.userid) 
		.append(this.username) 
		.append(this.createtime) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("actinstid", this.actinstid) 
		.append("taskid", this.taskid) 
		.append("userid", this.userid) 
		.append("username", this.username) 
		.append("createtime", this.createtime) 
		.toString();
	}
   
  

}