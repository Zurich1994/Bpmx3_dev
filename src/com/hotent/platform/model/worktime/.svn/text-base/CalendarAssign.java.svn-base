package com.hotent.platform.model.worktime;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:日历分配 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 09:25:51
 */
public class CalendarAssign extends BaseModel
{
	/**
	 * 用户
	 */
	public static int User=1;
	
	/**
	 * 组织
	 */
	public static int Organization=2;
	
	// 主键
	protected Long id;
	// 日历ID
	protected Long canlendarId;
	// 分配者类型
	protected Short assignType;
	// 分配者ID
	protected Long assignId;
	// 日历名称
	protected String calendarName;
	// 分配人名称
	protected String assignUserName;
	
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

	public void setCanlendarId(Long canlendarId) 
	{
		this.canlendarId = canlendarId;
	}
	/**
	 * 返回 日历ID
	 * @return
	 */
	public Long getCanlendarId() 
	{
		return canlendarId;
	}

	public void setAssignType(Short assignType) 
	{
		this.assignType = assignType;
	}
	/**
	 * 返回 分配者类型
	 * @return
	 */
	public Short getAssignType() 
	{
		return assignType;
	}

	public void setAssignId(Long assignId) 
	{
		this.assignId = assignId;
	}
	/**
	 * 返回 分配者ID
	 * @return
	 */
	public Long getAssignId() 
	{
		return assignId;
	}

	public String getCalendarName() {
		return calendarName;
	}
	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}
	
	public String getAssignUserName() {
		return assignUserName;
	}
	public void setAssignUserName(String assignUserName) {
		this.assignUserName = assignUserName;
	}
   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof CalendarAssign)) 
		{
			return false;
		}
		CalendarAssign rhs = (CalendarAssign) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.canlendarId, rhs.canlendarId)
		.append(this.assignType, rhs.assignType)
		.append(this.assignId, rhs.assignId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.canlendarId) 
		.append(this.assignType) 
		.append(this.assignId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("canlendarId", this.canlendarId) 
		.append("assignType", this.assignType) 
		.append("assignId", this.assignId) 
		.toString();
	}
   
  

}