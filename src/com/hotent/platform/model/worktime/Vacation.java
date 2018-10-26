package com.hotent.platform.model.worktime;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:法定假期设置 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 09:25:48
 */
public class Vacation extends BaseModel
{
	// 主键
	protected Long id;
	// 假日名称
	protected String name;
	// 年份
	protected Short years;
	// 开始时间
	protected java.util.Date statTime;
	// 结束时间
	protected java.util.Date endTime;
	// 开始时间String
	protected String sTime;
	// 结束时间String
	protected String eTime;

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

	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 假日名称
	 * @return
	 */
	public String getName() 
	{
		return name;
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

	public void setStatTime(java.util.Date statTime) 
	{
		this.statTime = statTime;
	}
	/**
	 * 返回 开始时间
	 * @return
	 */
	public java.util.Date getStatTime() 
	{
		return statTime;
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
		if (!(object instanceof Vacation)) 
		{
			return false;
		}
		Vacation rhs = (Vacation) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.years, rhs.years)
		.append(this.statTime, rhs.statTime)
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
		.append(this.name) 
		.append(this.years) 
		.append(this.statTime) 
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
		.append("name", this.name) 
		.append("years", this.years) 
		.append("statTime", this.statTime) 
		.append("endTime", this.endTime) 
		.toString();
	}
   
  

}