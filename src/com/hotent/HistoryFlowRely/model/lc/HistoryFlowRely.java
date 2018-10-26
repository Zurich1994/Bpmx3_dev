package com.hotent.HistoryFlowRely.model.lc;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:历史数据流程依赖表 Model对象
 */
public class HistoryFlowRely extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *依赖编号
	 */
	protected String  relyId;
	/**
	 *流程编号1
	 */
	protected String  processId1;
	/**
	 *流程编号2
	 */
	protected String  processId2;
	/**
	 *依赖时间类别
	 */
	protected String  relyTimeType;
	/**
	 *依赖参数
	 */
	protected Long  relyNumber;
	/**
	 *依赖发生时间
	 */
	protected String  relyOccurTime;
	/**
	 *依赖结束时间
	 */
	protected String  relyEndTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setRelyId(String relyId) 
	{
		this.relyId = relyId;
	}
	/**
	 * 返回 依赖编号
	 * @return
	 */
	public String getRelyId() 
	{
		return this.relyId;
	}
	public void setProcessId1(String processId1) 
	{
		this.processId1 = processId1;
	}
	/**
	 * 返回 流程编号1
	 * @return
	 */
	public String getProcessId1() 
	{
		return this.processId1;
	}
	public void setProcessId2(String processId2) 
	{
		this.processId2 = processId2;
	}
	/**
	 * 返回 流程编号2
	 * @return
	 */
	public String getProcessId2() 
	{
		return this.processId2;
	}
	public void setRelyTimeType(String relyTimeType) 
	{
		this.relyTimeType = relyTimeType;
	}
	/**
	 * 返回 依赖时间类别
	 * @return
	 */
	public String getRelyTimeType() 
	{
		return this.relyTimeType;
	}
	public void setRelyNumber(Long relyNumber) 
	{
		this.relyNumber = relyNumber;
	}
	/**
	 * 返回 依赖参数
	 * @return
	 */
	public Long getRelyNumber() 
	{
		return this.relyNumber;
	}
	public void setRelyOccurTime(String relyOccurTime) 
	{
		this.relyOccurTime = relyOccurTime;
	}
	/**
	 * 返回 依赖发生时间
	 * @return
	 */
	public String getRelyOccurTime() 
	{
		return this.relyOccurTime;
	}
	public void setRelyEndTime(String relyEndTime) 
	{
		this.relyEndTime = relyEndTime;
	}
	/**
	 * 返回 依赖结束时间
	 * @return
	 */
	public String getRelyEndTime() 
	{
		return this.relyEndTime;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof HistoryFlowRely)) 
		{
			return false;
		}
		HistoryFlowRely rhs = (HistoryFlowRely) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.relyId, rhs.relyId)
		.append(this.processId1, rhs.processId1)
		.append(this.processId2, rhs.processId2)
		.append(this.relyTimeType, rhs.relyTimeType)
		.append(this.relyNumber, rhs.relyNumber)
		.append(this.relyOccurTime, rhs.relyOccurTime)
		.append(this.relyEndTime, rhs.relyEndTime)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.relyId) 
		.append(this.processId1) 
		.append(this.processId2) 
		.append(this.relyTimeType) 
		.append(this.relyNumber) 
		.append(this.relyOccurTime) 
		.append(this.relyEndTime) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("relyId", this.relyId) 
		.append("processId1", this.processId1) 
		.append("processId2", this.processId2) 
		.append("relyTimeType", this.relyTimeType) 
		.append("relyNumber", this.relyNumber) 
		.append("relyOccurTime", this.relyOccurTime) 
		.append("relyEndTime", this.relyEndTime) 
		.toString();
	}

}
