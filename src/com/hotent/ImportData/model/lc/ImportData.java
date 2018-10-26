package com.hotent.ImportData.model.lc;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:导入数据 Model对象
 */
public class ImportData extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *年年
	 */
	protected String  year_year_date;
	/**
	 *年月
	 */
	protected String  year_month_date;
	/**
	 *月日
	 */
	protected String  month_day_date;
	/**
	 *日时
	 */
	protected String  day_hour_date;
	/**
	 *时分
	 */
	protected String  hour_minute_date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setYear_year_date(String year_year_date) 
	{
		this.year_year_date = year_year_date;
	}
	/**
	 * 返回 年年
	 * @return
	 */
	public String getYear_year_date() 
	{
		return this.year_year_date;
	}
	public void setYear_month_date(String year_month_date) 
	{
		this.year_month_date = year_month_date;
	}
	/**
	 * 返回 年月
	 * @return
	 */
	public String getYear_month_date() 
	{
		return this.year_month_date;
	}
	public void setMonth_day_date(String month_day_date) 
	{
		this.month_day_date = month_day_date;
	}
	/**
	 * 返回 月日
	 * @return
	 */
	public String getMonth_day_date() 
	{
		return this.month_day_date;
	}
	public void setDay_hour_date(String day_hour_date) 
	{
		this.day_hour_date = day_hour_date;
	}
	/**
	 * 返回 日时
	 * @return
	 */
	public String getDay_hour_date() 
	{
		return this.day_hour_date;
	}
	public void setHour_minute_date(String hour_minute_date) 
	{
		this.hour_minute_date = hour_minute_date;
	}
	/**
	 * 返回 时分
	 * @return
	 */
	public String getHour_minute_date() 
	{
		return this.hour_minute_date;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof ImportData)) 
		{
			return false;
		}
		ImportData rhs = (ImportData) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.year_year_date, rhs.year_year_date)
		.append(this.year_month_date, rhs.year_month_date)
		.append(this.month_day_date, rhs.month_day_date)
		.append(this.day_hour_date, rhs.day_hour_date)
		.append(this.hour_minute_date, rhs.hour_minute_date)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.year_year_date) 
		.append(this.year_month_date) 
		.append(this.month_day_date) 
		.append(this.day_hour_date) 
		.append(this.hour_minute_date) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("year_year_date", this.year_year_date) 
		.append("year_month_date", this.year_month_date) 
		.append("month_day_date", this.month_day_date) 
		.append("day_hour_date", this.day_hour_date) 
		.append("hour_minute_date", this.hour_minute_date) 
		.toString();
	}

}
