package com.hotent.Sysservice.model.Sysservice;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:子系统服务表 Model对象
 */
public class Sysservice extends BaseModel
{
	//主键
	protected Long id;
	
	protected Long f_id;
	/**
	 *子系统id
	 */
	protected Long  systemId;
	/**
	 *服务类别
	 */
	protected String  service;
	/**
	 *服务数量
	 */
	protected Long  serviceNum;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getf_id() {
		return f_id;
	}
	public void setf_id(Long f_id) {
		this.f_id = f_id;
	}
	
	public void setSystemId(Long systemId) 
	{
		this.systemId = systemId;
	}
	/**
	 * 返回 子系统id
	 * @return
	 */
	public Long getSystemId() 
	{
		return this.systemId;
	}
	public void setService(String service) 
	{
		this.service = service;
	}
	/**
	 * 返回 服务类别
	 * @return
	 */
	public String getService() 
	{
		return this.service;
	}
	public void setServiceNum(Long serviceNum) 
	{
		this.serviceNum = serviceNum;
	}
	/**
	 * 返回 服务数量
	 * @return
	 */
	public Long getServiceNum() 
	{
		return this.serviceNum;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Sysservice)) 
		{
			return false;
		}
		Sysservice rhs = (Sysservice) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.f_id, rhs.f_id)
		.append(this.systemId, rhs.systemId)
		.append(this.service, rhs.service)
		.append(this.serviceNum, rhs.serviceNum)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.f_id) 
		.append(this.systemId) 
		.append(this.service) 
		.append(this.serviceNum) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("f_id", this.f_id) 
		.append("systemId", this.systemId) 
		.append("service", this.service) 
		.append("serviceNum", this.serviceNum) 
		.toString();
	}

}