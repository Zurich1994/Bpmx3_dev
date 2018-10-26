package com.hotent.flowwebservice.model.flowwebservice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.fr.fs.dao.DaoAccess;
import com.hotent.core.model.BaseModel;
import com.hotent.flowwebservice.dao.flowwebservice.FlowwebserviceDao;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:flowWebService Model对象
 */
public class Flowwebservice extends BaseModel
{
	@Resource 
	private FlowwebserviceDao dao;
	//主键
	protected Long id;
	/**
	 *serviceName
	 */
	protected String  serviceName;
	/**
	 *serviceUrl
	 */
	protected String  serviceUrl;
	/**
	 *serviceFuncName
	 */
	protected String  serviceFuncName;
	/**
	 *serviceType
	 */
	protected String  serviceType;
	/**
	 *serviceState
	 */
	protected String  serviceState;
	/**
	 *defId
	 */
	protected Long  defid;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setServiceName(String serviceName) 
	{
		this.serviceName = serviceName;
	}
	/**
	 * 返回 serviceName
	 * @return
	 */
	public String getServiceName() 
	{
		return this.serviceName;
	}
	public void setServiceUrl(String serviceUrl) 
	{
		this.serviceUrl = serviceUrl;
	}
	/**
	 * 返回 serviceUrl
	 * @return
	 */
	public String getServiceUrl() 
	{
		return this.serviceUrl;
	}
	public void setServiceFuncName(String serviceFuncName) 
	{
		this.serviceFuncName = serviceFuncName;
	}
	/**
	 * 返回 serviceFuncName
	 * @return
	 */
	public String getServiceFuncName() 
	{
		return this.serviceFuncName;
	}
	public void setServiceType(String serviceType) 
	{
		this.serviceType = serviceType;
	}
	/**
	 * 返回 serviceType
	 * @return
	 */
	public String getServiceType() 
	{
		return this.serviceType;
	}
	public void setServiceState(String serviceState) 
	{
		this.serviceState = serviceState;
	}
	/**
	 * 返回 serviceState
	 * @return
	 */
	public String getServiceState() 
	{
		return this.serviceState;
	}
	public void setDefid(Long defid) 
	{
		this.defid = defid;
	}
	/**
	 * 返回 defId
	 * @return
	 */
	public Long getDefid() 
	{
		return this.defid;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Flowwebservice)) 
		{
			return false;
		}
		Flowwebservice rhs = (Flowwebservice) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.serviceName, rhs.serviceName)
		.append(this.serviceUrl, rhs.serviceUrl)
		.append(this.serviceFuncName, rhs.serviceFuncName)
		.append(this.serviceType, rhs.serviceType)
		.append(this.serviceState, rhs.serviceState)
		.append(this.defid, rhs.defid)
		.isEquals();
	}
	
	
	
	


	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.serviceName) 
		.append(this.serviceUrl) 
		.append(this.serviceFuncName) 
		.append(this.serviceType) 
		.append(this.serviceState) 
		.append(this.defid) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("serviceName", this.serviceName) 
		.append("serviceUrl", this.serviceUrl) 
		.append("serviceFuncName", this.serviceFuncName) 
		.append("serviceType", this.serviceType) 
		.append("serviceState", this.serviceState) 
		.append("defid", this.defid) 
		.toString();
	}

}
