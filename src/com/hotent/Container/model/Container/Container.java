package com.hotent.Container.model.Container;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:容器表 Model对象
 */
public class Container extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *容器名
	 */
	protected String  containername;
	/**
	 *类型
	 */
	protected String type;
	/**
	 *可计算性
	 */
	protected String  computability;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setContainername(String containername) 
	{
		this.containername = containername;
	}
	/**
	 * 返回 容器名
	 * @return
	 */
	public String getContainername() 
	{
		return this.containername;
	}
	public void setType(String type) 
	{
		this.type = type;
	}
	/**
	 * 返回 类型
	 * @return
	 */
	public String getType() 
	{
		return this.type;
	}
	public void setComputability(String computability) 
	{
		this.computability = computability;
	}
	/**
	 * 返回 可计算性
	 * @return
	 */
	public String getComputability() 
	{
		return this.computability;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Container)) 
		{
			return false;
		}
		Container rhs = (Container) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.containername, rhs.containername)
		.append(this.type, rhs.type)
		.append(this.computability, rhs.computability)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.containername) 
		.append(this.type) 
		.append(this.computability) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("containername", this.containername) 
		.append("type", this.type) 
		.append("computability", this.computability) 
		.toString();
	}

}