package com.hotent.platform.model.ats;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:法定节假日明细 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 15:48:34
 */
public class AtsLegalHolidayDetail extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9031509960393718131L;
	// 主键
	protected Long id;
	// 法定假日
	protected Long holidayId;
	// 假日名称
	protected String name;
	// 开始时间
	protected Date startTime;
	// 结束时间
	protected Date endTime;
	// 描述
	protected String memo;

	public void setId(Long id){
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	public void setHolidayId(Long holidayId){
		this.holidayId = holidayId;
	}
	/**
	 * 返回 法定假日
	 * @return
	 */
	public Long getHolidayId() {
		return this.holidayId;
	}
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 返回 假日名称
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	public void setStartTime(java.util.Date startTime){
		this.startTime = startTime;
	}
	/**
	 * 返回 开始时间
	 * @return
	 */
	public Date getStartTime() {
		return this.startTime;
	}
	public void setEndTime(java.util.Date endTime){
		this.endTime = endTime;
	}
	/**
	 * 返回 结束时间
	 * @return
	 */
	public Date getEndTime() {
		return this.endTime;
	}
	public void setMemo(String memo){
		this.memo = memo;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}
	

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AtsLegalHolidayDetail)) 
		{
			return false;
		}
		AtsLegalHolidayDetail rhs = (AtsLegalHolidayDetail) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.holidayId, rhs.holidayId)
		.append(this.name, rhs.name)
		.append(this.startTime, rhs.startTime)
		.append(this.endTime, rhs.endTime)
		.append(this.memo, rhs.memo)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.holidayId) 
		.append(this.name) 
		.append(this.startTime) 
		.append(this.endTime) 
		.append(this.memo) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("holidayId", this.holidayId) 
		.append("name", this.name) 
		.append("startTime", this.startTime) 
		.append("endTime", this.endTime) 
		.append("memo", this.memo) 
		.toString();
	}
   
  

}