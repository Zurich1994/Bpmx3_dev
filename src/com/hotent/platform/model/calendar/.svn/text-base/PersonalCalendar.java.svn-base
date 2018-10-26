package com.hotent.platform.model.calendar;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:个人日历 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2014-03-12 14:50:14
 */
public class PersonalCalendar extends BaseModel
{
	// 主键
	protected Long  id;
	// 标题
	protected String  title;
	// 描述
	protected String  description;
	// 开始时间
	protected java.util.Date  startTime;
	// 结束时间
	protected java.util.Date  endTime;
	//提醒方式
	protected Integer remindMode;
	//提醒时间
	protected java.util.Date  remindTime;
	//提醒类型
	protected String  remindType;
	
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
	public void setTitle(String title) 
	{
		this.title = title;
	}
	/**
	 * 返回 标题
	 * @return
	 */
	public String getTitle() 
	{
		return this.title;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getDescription() 
	{
		return this.description;
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
		return this.startTime;
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
		return this.endTime;
	}

   	public Integer getRemindMode() {
		return remindMode;
	}
	public void setRemindMode(Integer remindMode) {
		this.remindMode = remindMode;
	}
	public java.util.Date getRemindTime() {
		return remindTime;
	}
	public void setRemindTime(java.util.Date remindTime) {
		this.remindTime = remindTime;
	}
	public String getRemindType() {
		return remindType;
	}
	public void setRemindType(String remindType) {
		this.remindType = remindType;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof PersonalCalendar)) 
		{
			return false;
		}
		PersonalCalendar rhs = (PersonalCalendar) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.title, rhs.title)
		.append(this.description, rhs.description)
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
		.append(this.title) 
		.append(this.description) 
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
		.append("title", this.title) 
		.append("description", this.description) 
		.append("startTime", this.startTime) 
		.append("endTime", this.endTime) 
		.toString();
	}
   
  

}