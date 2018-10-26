package com.hotent.Serviceproducts.model.Serviceproducts;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:服务产品表 Model对象
 */
public class Serviceproducts extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *服务产品名
	 */
	protected String  name;
	/**
	 *服务编号
	 */
	protected String  serviceID;
	/**
	 *服务地址
	 */
	protected String  serviceaddress;
	/**
	 *服务端口号
	 */
	protected String  serviceport;
	
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
	 * 返回 服务产品名
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setServiceID(String serviceID) 
	{
		this.serviceID = serviceID;
	}
	/**
	 * 返回 服务编号
	 * @return
	 */
	public String getServiceID() 
	{
		return this.serviceID;
	}
	public void setServiceaddress(String serviceaddress) 
	{
		this.serviceaddress = serviceaddress;
	}
	/**
	 * 返回 服务地址
	 * @return
	 */
	public String getServiceaddress() 
	{
		return this.serviceaddress;
	}
	public void setServiceport(String serviceport) 
	{
		this.serviceport = serviceport;
	}
	/**
	 * 返回 服务端口号
	 * @return
	 */
	public String getServiceport() 
	{
		return this.serviceport;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Serviceproducts)) 
		{
			return false;
		}
		Serviceproducts rhs = (Serviceproducts) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.serviceID, rhs.serviceID)
		.append(this.serviceaddress, rhs.serviceaddress)
		.append(this.serviceport, rhs.serviceport)
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
		.append(this.serviceaddress) 
		.append(this.serviceport) 
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
		.append("serviceaddress", this.serviceaddress) 
		.append("serviceport", this.serviceport) 
		.toString();
	}

}