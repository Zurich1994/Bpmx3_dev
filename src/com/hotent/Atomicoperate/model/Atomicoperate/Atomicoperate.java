package com.hotent.Atomicoperate.model.Atomicoperate;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:原子操作表 Model对象
 */
public class Atomicoperate extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *原子操作名称
	 */
	protected String  name;
	/**
	 *服务编号
	 */
	protected Long  serviceID;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 原子操作名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setServiceID(Long serviceID) 
	{
		this.serviceID = serviceID;
	}
	/**
	 * 返回 服务编号
	 * @return
	 */
	public Long getServiceID() 
	{
		return this.serviceID;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Atomicoperate)) 
		{
			return false;
		}
		Atomicoperate rhs = (Atomicoperate) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.serviceID, rhs.serviceID)
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
		.append(this.serviceID) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("name", this.name) 
		.append("serviceID", this.serviceID) 
		.toString();
	}

}