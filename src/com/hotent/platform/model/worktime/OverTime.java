package com.hotent.platform.model.worktime;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:加班情况 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 09:25:51
 */
public class OverTime extends BaseModel
{	
	// 主键
	protected Long id;
	// 用户ID
	protected Long userId;
	// 开始时间
	protected java.util.Date startTime;
	// 结束时间
	protected java.util.Date endTime;
	// 用户名称
	protected String userName;
	// 开始时间String
	protected String sTime;
	// 结束时间String
	protected String eTime;
	//班次变动类型
	protected Short workType;
	//主题
	protected String subject;
	//备注
	protected String memo;
	
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
		return id;
	}

	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}
	/**
	 * 返回 用户ID
	 * @return
	 */
	public Long getUserId() 
	{
		return userId;
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
	
	/**
	 * 返回班次改动类型
	 * @return
	 */
	public Short getWorkType() {
		return workType;
	}
	
	/**
	 * 返回主题
	 * @return
	 */
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * 返回备注
	 * @return
	 */
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public void setWorkType(Short workType) {
		this.workType = workType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
   
	public String getsTime() {
		return sTime;
	}
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	public String geteTime() {
		return eTime;
	}
	public void seteTime(String eTime) {
		this.eTime = eTime;
	}
	
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof OverTime)) 
		{
			return false;
		}
		OverTime rhs = (OverTime) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.userId, rhs.userId)
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
		.append(this.id) 
		.append(this.userId) 
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
		.append("id", this.id) 
		.append("userId", this.userId) 
		.append("startTime", this.startTime) 
		.append("endTime", this.endTime)
		.toString();
	}
   
  

}