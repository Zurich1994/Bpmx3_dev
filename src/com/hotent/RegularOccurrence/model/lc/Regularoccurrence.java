package com.hotent.RegularOccurrence.model.lc;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:发生规律信息表 Model对象
 */
public class Regularoccurrence extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *流程编号
	 */
	protected String  processId;
	/**
	 *时间
	 */
	protected String  time;
	/**
	 *发生规律值
	 */
	protected String  regularValue;
	
	protected String timeType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setTime(String time) 
	{
		this.time = time;
	}
	/**
	 * 返回 时间
	 * @return
	 */
	public String getTime() 
	{
		return this.time;
	}
	public void setRegularValue(String regularValue) 
	{
		this.regularValue = regularValue;
	}
	/**
	 * 返回 发生规律值
	 * @return
	 */
	public String getRegularValue() 
	{
		return this.regularValue;
	}
	
	
	
   	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Regularoccurrence)) 
		{
			return false;
		}
		Regularoccurrence rhs = (Regularoccurrence) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.processId, rhs.processId)
		.append(this.time, rhs.time)
		.append(this.regularValue, rhs.regularValue)
		.append(this.timeType, rhs.timeType)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.processId) 
		.append(this.time) 
		.append(this.regularValue) 
		.append(this.timeType)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("processId", this.processId) 
		.append("time", this.time) 
		.append("regularValue", this.regularValue) 
		.append("timeType", this.timeType)
		.toString();
	}

}
