package com.hotent.HistoryScale.model.lc;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:历史数据发生比例表 Model对象
 */
public class HistoryScale extends BaseModel
{

	

	public final static String STATUS_YEAR = "年年";
	public final static String STATUS_MONTH = "年月";
	public final static String STATUS_DAY = "月日";
	public final static String STATUS_HOUR = "日时";
	public final static String STATUS_SEC = "时分";


	//主键
	protected Long id;
	/**
	 *历史比例编号
	 */
	protected String  historyProportionId;
	/**
	 *流程编号
	 */
	protected String  processtId;
	/**
	 *比例时间类别
	 */
	protected String  proportionTimeType;
	/**
	 *比例
	 */
	protected String  proportion;
	/**
	 *比例发生时间
	 */
	protected String  proportionOccurTime;
	
	public static String getStatusYear() {
		return STATUS_YEAR;
	}
	public static String getStatusMonth() {
		return STATUS_MONTH;
	}
	public static String getStatusDay() {
		return STATUS_DAY;
	}
	public static String getStatusHour() {
		return STATUS_HOUR;
	}
	public static String getStatusSec() {
		return STATUS_SEC;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setHistoryProportionId(String historyProportionId) 
	{
		this.historyProportionId = historyProportionId;
	}
	/**
	 * 返回 历史比例编号
	 * @return
	 */
	public String getHistoryProportionId() 
	{
		return this.historyProportionId;
	}
	public void setProcesstId(String processtId) 
	{
		this.processtId = processtId;
	}
	/**
	 * 返回 流程编号
	 * @return
	 */
	public String getProcesstId() 
	{
		return this.processtId;
	}
	public void setProportionTimeType(String proportionTimeType) 
	{
		this.proportionTimeType = proportionTimeType;
	}
	/**
	 * 返回 比例时间类别
	 * @return
	 */
	public String getProportionTimeType() 
	{
		return this.proportionTimeType;
	}
	public void setProportion(String proportion) 
	{
		this.proportion = proportion;
	}
	/**
	 * 返回 比例
	 * @return
	 */
	public String getProportion() 
	{
		return this.proportion;
	}
	public void setProportionOccurTime(String proportionOccurTime) 
	{
		this.proportionOccurTime = proportionOccurTime;
	}
	/**
	 * 返回 比例发生时间
	 * @return
	 */
	public String getProportionOccurTime() 
	{
		return this.proportionOccurTime;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof HistoryScale)) 
		{
			return false;
		}
		HistoryScale rhs = (HistoryScale) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.historyProportionId, rhs.historyProportionId)
		.append(this.processtId, rhs.processtId)
		.append(this.proportionTimeType, rhs.proportionTimeType)
		.append(this.proportion, rhs.proportion)
		.append(this.proportionOccurTime, rhs.proportionOccurTime)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.historyProportionId) 
		.append(this.processtId) 
		.append(this.proportionTimeType) 
		.append(this.proportion) 
		.append(this.proportionOccurTime) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("historyProportionId", this.historyProportionId) 
		.append("processtId", this.processtId) 
		.append("proportionTimeType", this.proportionTimeType) 
		.append("proportion", this.proportion) 
		.append("proportionOccurTime", this.proportionOccurTime) 
		.toString();
	}

}
