package com.hotent.HistoryData.model.lc;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:历史数据信息表 Model对象
 */
public class HistoryData extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *历史数据编号
	 */
	protected String  historyDataId;
	/**
	 *流程编号
	 */
	protected String  processId;
	/**
	 *时间类别
	 */
	protected String  timeType;
	/**
	 *发生量
	 */
	protected Long  occurenceAmount;
	/**
	 *发生时间
	 */
	protected String  occurenceTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setHistoryDataId(String historyDataId) 
	{
		this.historyDataId = historyDataId;
	}
	/**
	 * 返回 历史数据编号
	 * @return
	 */
	public String getHistoryDataId() 
	{
		return this.historyDataId;
	}
	public void setProcessId(String processId) 
	{
		this.processId = processId;
	}
	/**
	 * 返回 流程编号
	 * @return
	 */
	public String getProcessId() 
	{
		return this.processId;
	}
	public void setTimeType(String timeType) 
	{
		this.timeType = timeType;
	}
	/**
	 * 返回 时间类别
	 * @return
	 */
	public String getTimeType() 
	{
		return this.timeType;
	}
	public void setOccurenceAmount(Long occurenceAmount) 
	{
		this.occurenceAmount = occurenceAmount;
	}
	/**
	 * 返回 发生量
	 * @return
	 */
	public Long getOccurenceAmount() 
	{
		return this.occurenceAmount;
	}
	public void setOccurenceTime(String occurenceTime) 
	{
		this.occurenceTime = occurenceTime;
	}
	/**
	 * 返回 发生时间
	 * @return
	 */
	public String getOccurenceTime() 
	{
		return this.occurenceTime;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof HistoryData)) 
		{
			return false;
		}
		HistoryData rhs = (HistoryData) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.historyDataId, rhs.historyDataId)
		.append(this.processId, rhs.processId)
		.append(this.timeType, rhs.timeType)
		.append(this.occurenceAmount, rhs.occurenceAmount)
		.append(this.occurenceTime, rhs.occurenceTime)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.historyDataId) 
		.append(this.processId) 
		.append(this.timeType) 
		.append(this.occurenceAmount) 
		.append(this.occurenceTime) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("historyDataId", this.historyDataId) 
		.append("processId", this.processId) 
		.append("timeType", this.timeType) 
		.append("occurenceAmount", this.occurenceAmount) 
		.append("occurenceTime", this.occurenceTime) 
		.toString();
	}

}
