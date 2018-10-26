package com.hotent.platform.model.worktime;

import java.util.List;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:日历设置 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 14:57:32
 */
public class CalendarSetting extends BaseModel
{
	
	
	// 主键
	protected Long id;
	// 日历ID
	protected Long calendarId;
	// 年份
	protected Short years;
	// 月份
	protected Short months;
	// 日
	protected Short days;
	// 上班类型
	protected Short type;
	// 班次ID
	protected Long workTimeId;
	//当前日期
	protected String calDay="";
	
	//是否法定假日
	protected boolean isLegal=false;
	//班次名称
	protected String wtName="";

	

	

	/**
	 * 班次时间列表
	 */
	protected List<WorkTime> workTimeList;
	
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

	public void setCalendarId(Long calendarId) 
	{
		this.calendarId = calendarId;
	}
	/**
	 * 返回 日历ID
	 * @return
	 */
	public Long getCalendarId() 
	{
		return calendarId;
	}

	public void setYears(Short years) 
	{
		this.years = years;
	}
	/**
	 * 返回 年份
	 * @return
	 */
	public Short getYears() 
	{
		return years;
	}

	public void setMonths(Short months) 
	{
		this.months = months;
	}
	/**
	 * 返回 月份
	 * @return
	 */
	public Short getMonths() 
	{
		return months;
	}

	public void setDays(Short days) 
	{
		this.days = days;
	}
	/**
	 * 返回 1号
	 * @return
	 */
	public Short getDays() 
	{
		return days;
	}

	public void setType(Short type) 
	{
		this.type = type;
	}
	/**
	 * 返回 上班类型
	 * @return
	 */
	public Short getType() 
	{
		return type;
	}

	public void setWorkTimeId(Long workTimeId) 
	{
		this.workTimeId = workTimeId;
	}
	/**
	 * 返回 workTimeId
	 * @return
	 */
	public Long getWorkTimeId() 
	{
		return workTimeId;
	}
	
	
	public void setCalDay(String calDay) {
		this.calDay = calDay;
	}
	
	public String getCalDay() {
		return this.years +"-" + (this.months<10? "0" + this.months:this.months) +"-" + (this.days<10? "0" + this.days:this.days)  ;
	}
	/**
	 * 班次名称。
	 * @return
	 */
	public String getWtName() {
		return wtName;
	}
	public void setWtName(String wtName) {
		this.wtName = wtName;
	}
	
	/**
	 * 是否法定假日
	 * @return
	 */
	public boolean getIsLegal() {
		return isLegal;
	}
	public void setIsLegal(boolean isLegal) {
		this.isLegal = isLegal;
	}

	public List<WorkTime> getWorkTimeList() {
		return this.workTimeList;
	}
	public void setWorkTimeList(List<WorkTime> workTimeList) {
		this.workTimeList = workTimeList;
	}
	
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof CalendarSetting)) 
		{
			return false;
		}
		CalendarSetting rhs = (CalendarSetting) object;
		return new EqualsBuilder()
	
		.append(this.calendarId, rhs.calendarId)
		.append(this.years, rhs.years)
		.append(this.months, rhs.months)
		.append(this.days, rhs.days)
		.append(this.type, rhs.type)
		.append(this.workTimeId, rhs.workTimeId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
	
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("calendarId", this.calendarId) 
		.append("years", this.years) 
		.append("months", this.months) 
		.append("days", this.days) 
		.append("type", this.type) 
		.append("workTimeId", this.workTimeId) 
		.toString();
	}
   
  

}