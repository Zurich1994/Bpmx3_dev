package com.hotent.BusinessCollectCot.model.lc;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:业务发生量采集与计算表 Model对象
 */
public class BusinessCollectCot extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *业务发生编号
	 */
	protected String  businessId;
	/**
	 *流程定义id
	 */
	protected String  processId;
	/**
	 *业务发生开始时间
	 */
	protected String  startTime;
	/**
	 *业务发生结束时间
	 */
	protected String  endTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setBusinessId(String businessId) 
	{
		this.businessId = businessId;
	}
	/**
	 * 返回 业务发生编号
	 * @return
	 */
	public String getBusinessId() 
	{
		return this.businessId;
	}
	public void setProcessId(String processId) 
	{
		this.processId = processId;
	}
	/**
	 * 返回 流程定义id
	 * @return
	 */
	public String getProcessId() 
	{
		return this.processId;
	}
	public void setStartTime(String startTime) 
	{
		this.startTime = startTime;
	}
	/**
	 * 返回 业务发生开始时间
	 * @return
	 */
	public String getStartTime() 
	{
		return this.startTime;
	}
	public void setEndTime(String endTime) 
	{
		this.endTime = endTime;
	}
	/**
	 * 返回 业务发生结束时间
	 * @return
	 */
	public String getEndTime() 
	{
		return this.endTime;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BusinessCollectCot)) 
		{
			return false;
		}
		BusinessCollectCot rhs = (BusinessCollectCot) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.businessId, rhs.businessId)
		.append(this.processId, rhs.processId)
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
		.append(this.businessId) 
		.append(this.processId) 
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
		.append("id",this.id)
		.append("businessId", this.businessId) 
		.append("processId", this.processId) 
		.append("startTime", this.startTime) 
		.append("endTime", this.endTime) 
		.toString();
	}

}
