package com.hotent.excelk.model.excelk;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:excelk Model对象
 */
public class Excelk extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *time
	 */
	protected String  time;
	/**
	 *reguval
	 */
	protected String  reguval;
	/**
	 *process
	 */
	protected Long  processId;
	
	/**
	 *count
	 */
	protected Long count;
	
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTime(String time) 
	{
		this.time = time;
	}
	/**
	 * 返回 time
	 * @return
	 */
	public String getTime() 
	{
		return this.time;
	}
	public void setReguval(String reguval) 
	{
		this.reguval = reguval;
	}
	/**
	 * 返回 reguval
	 * @return
	 */
	public String getReguval() 
	{
		return this.reguval;
	}
	public void setProcessId(Long processId) 
	{
		this.processId = processId;
	}
	/**
	 * 返回 process
	 * @return
	 */
	public Long getProcessId() 
	{
		return this.processId;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Excelk)) 
		{
			return false;
		}
		Excelk rhs = (Excelk) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.time, rhs.time)
		.append(this.reguval, rhs.reguval)
		.append(this.processId, rhs.processId)
		.append(this.count, rhs.count)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.time) 
		.append(this.reguval) 
		.append(this.processId) 
		.append(this.count) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("time", this.time) 
		.append("reguval", this.reguval) 
		.append("process", this.processId) 
		.append("count", this.count)
		.toString();
	}

}