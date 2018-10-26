package com.hotent.Assemble.model.Assemble;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:组装表 Model对象
 */
public class Assemble extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *子系统ID
	 */
	protected Long  sysId;
	/**
	 *子系统ID
	 */
	protected String  sysName;
	
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	/**
	 *应用ID
	 */
	protected Long  applicationId;
	/**
	 *应用ID
	 */
	protected String  applicationName;
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 *服务编号
	 */
	protected Long  serviceId;
	/**
	 *服务标号
	 */
	protected String  serviceName;
	public String getServiceName(){
		return serviceName;
	}
	public void setServiceName(String serviceName){
		this.serviceName = serviceName;
	}
	/**
	 *服务地址
	 */
	protected String  serviceaddress;
	/**
	 *服务端口号
	 */
	protected Long  serviceport;
	/**
	 *服务端个数
	 */
	protected Long serviceNum;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setSysId(Long sysId) 
	{
		this.sysId = sysId;
	}
	/**
	 * 返回 子系统ID
	 * @return
	 */
	public Long getSysId() 
	{
		return this.sysId;
	}
	public void setApplicationId(Long applicationId) 
	{
		this.applicationId = applicationId;
	}
	/**
	 * 返回 应用ID
	 * @return
	 */
	public Long getApplicationId() 
	{
		return this.applicationId;
	}
	public void setServiceId(Long serviceId) 
	{
		this.serviceId = serviceId;
	}
	/**
	 * 返回 服务编号
	 * @return
	 */
	public Long getServiceId() 
	{
		return this.serviceId;
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
	public void setServiceport(Long serviceport) 
	{
		this.serviceport = serviceport;
	}
	/**
	 * 返回 服务端口号
	 * @return
	 */
	public Long getServiceport() 
	{
		return this.serviceport;
	}
	
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	
	public void setServiceNum(Long serviceNum) 
	{
		this.serviceNum = serviceNum;
	}
	/**
	 * 服务个数
	 * @return
	 */
	public Long getServiceNum() 
	{
		return this.serviceNum;
	}
	
	public boolean equals(Object object) 
	{
		if (!(object instanceof Assemble)) 
		{
			return false;
		}
		Assemble rhs = (Assemble) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.sysId, rhs.sysId)
		.append(this.sysName, rhs.sysName)
		.append(this.applicationId, rhs.applicationId)
		.append(this.applicationName, rhs.applicationName)
		.append(this.serviceId, rhs.serviceId)
		.append(this.serviceName, rhs.serviceName)
		.append(this.serviceaddress, rhs.serviceaddress)
		.append(this.serviceport, rhs.serviceport)
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
		.append(this.sysId) 
		.append(this.sysName)
		.append(this.applicationId)
		.append(this.applicationName)
		.append(this.serviceId)
		.append(this.serviceName)
		.append(this.serviceaddress) 
		.append(this.serviceport) 
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
		.append("sysId", this.sysId)
		.append("sysName", this.sysName)
		.append("applicationId", this.applicationId) 
		.append("applicationName", this.applicationName) 
		.append("serviceId", this.serviceId) 
		.append("serviceName", this.serviceName)
		.append("serviceaddress", this.serviceaddress) 
		.append("serviceport", this.serviceport)
		.append("serviceNum", this.serviceNum)
		.toString();
	}

}