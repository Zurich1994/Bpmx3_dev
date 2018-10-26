package com.hotent.platform.model.bpm;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
import com.hotent.core.util.TimeUtil;

/**
 * 对象功能:沟通接收人 Model对象
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-04-09 19:44:59
 */
public class CommuReceiver extends BaseModel
{
	/**
	 * 未读
	 */
	public final static Integer NOT_READ=0;
	/**
	 * 已读
	 */
	public final static Integer READED=1;
	/**
	 * 已反馈
	 */
	public final static Integer FEEDBACK=2;
	/**
	 * 已取消
	 */
	public final static Integer CANCEL=3;
	// 主键
	protected Long  id;
	// 意见ID
	protected Long  opinionid;
	// 接收人ID
	protected Long  recevierid;
	// 接收人
	protected String  receivername;
	// 状态 (0 未读,1,已阅,2,已反馈,3,已取消）
	protected Integer  status=0;
	// 接收时间
	protected java.util.Date  receivetime;
	
	protected java.util.Date  feedbacktime;
	
	protected Long  taskId;
	
	
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
	public void setOpinionid(Long opinionid) 
	{
		this.opinionid = opinionid;
	}
	/**
	 * 返回 意见ID
	 * @return
	 */
	public Long getOpinionid() 
	{
		return this.opinionid;
	}
	public void setRecevierid(Long recevierid) 
	{
		this.recevierid = recevierid;
	}
	/**
	 * 返回 接收人ID
	 * @return
	 */
	public Long getRecevierid() 
	{
		return this.recevierid;
	}
	public void setReceivername(String receivername) 
	{
		this.receivername = receivername;
	}
	/**
	 * 返回 接收人
	 * @return
	 */
	public String getReceivername() 
	{
		return this.receivername;
	}
	
	
	public void setStatus(Integer status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态
	 * @return
	 */
	public Integer getStatus() 
	{
		return this.status;
	}
	public void setReceivetime(java.util.Date receivetime) 
	{
		this.receivetime = receivetime;
	}
	/**
	 * 返回 接收时间
	 * @return
	 */
	public java.util.Date getReceivetime() 
	{
		return this.receivetime;
	}

	public String getReceivetimeStr() 
	{
		return TimeUtil.getDateTimeString(this.receivetime) ;
	}
	
	public String getCreatetimeStr() 
	{
		return TimeUtil.getDateTimeString(this.createtime) ;
	}

	public String getFeedbacktimeStr() 
	{
		return TimeUtil.getDateTimeString(this.feedbacktime) ;
	}
	
   	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public java.util.Date getFeedbacktime() {
		return feedbacktime;
	}
	public void setFeedbacktime(java.util.Date feedbacktime) {
		this.feedbacktime = feedbacktime;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof CommuReceiver)) 
		{
			return false;
		}
		CommuReceiver rhs = (CommuReceiver) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.opinionid, rhs.opinionid)
		.append(this.recevierid, rhs.recevierid)
		.append(this.receivername, rhs.receivername)
		.append(this.status, rhs.status)
		.append(this.receivetime, rhs.receivetime)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.opinionid) 
		.append(this.recevierid) 
		.append(this.receivername) 
		.append(this.status) 
		.append(this.receivetime) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("opinionid", this.opinionid) 
		.append("recevierid", this.recevierid) 
		.append("receivername", this.receivername) 
		.append("status", this.status) 
		.append("receivetime", this.receivetime) 
		.toString();
	}
   
  

}