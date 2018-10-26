package com.hotent.platform.model.worktime;

import java.util.Date;

import com.hotent.core.model.BaseModel;
import com.hotent.core.util.TimeUtil;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:班次时间 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 09:25:50
 */
public class WorkTime extends BaseModel implements Cloneable
{
	// 主键
	protected Long id;
	// 设置ID
	protected Long settingId;
	// 开始时间
	protected String startTime;
	// 结束时间
	protected String endTime;
	//备注
	protected String memo="";
	//日期
	protected String calDay  ="";
	//开始时间
	protected Date startDateTime;
	//结束时间
	protected Date endDateTime;

	
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

	public void setSettingId(Long settingId) 
	{
		this.settingId = settingId;
	}
	/**
	 * 返回 设置ID
	 * @return
	 */
	public Long getSettingId() 
	{
		return settingId;
	}

	public void setStartTime(String startTime) 
	{
		this.startTime = startTime;
	}
	/**
	 * 返回 开始时间
	 * @return
	 */
	public String getStartTime() 
	{
		return startTime;
	}

	public void setEndTime(String endTime) 
	{
		this.endTime = endTime;
	}
	/**
	 * 返回 结束时间
	 * @return
	 */
	public String getEndTime() 
	{
		return endTime;
	}
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	public String getCalDay() {
		return calDay;
	}
	public void setCalDay(String calDay) {
		this.calDay = calDay;
		String start=calDay +" " + this.startTime +":00";
		String end=calDay +" " + this.endTime+":00";
		
		this.startDateTime=TimeUtil.getDateTimeByTimeString(start);
		this.endDateTime=TimeUtil.getDateTimeByTimeString(end);
		if(this.startDateTime.compareTo(this.endDateTime)>0){
			this.endDateTime=TimeUtil.getNextDays(this.endDateTime,1);
		}
	}


   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof WorkTime)) 
		{
			return false;
		}
		WorkTime rhs = (WorkTime) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.settingId, rhs.settingId)
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
		.append(this.settingId) 
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
		.append("settingId", this.settingId) 
		.append("startTime", this.startTime) 
		.append("endTime", this.endTime) 
		.toString();
	}
	
	public Object clone()
	{
		WorkTime obj=null;
		try{
			obj=(WorkTime)super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}
   
  

}