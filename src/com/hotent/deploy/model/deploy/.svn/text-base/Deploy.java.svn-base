package com.hotent.deploy.model.deploy;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:部署表 Model对象
 */
public class Deploy extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *部署ID
	 */
	protected String  deployID;
	/**
	 *平台服务名称
	 */
	protected String  PaaSname;
	/**
	 *平台服务编号
	 */
	protected String  PaaSID;
	/**
	 *访问地址URL
	 */
	protected String  URL;
	/**
	 *设备IP
	 */
	protected String  deviceIP;
	/**
	 *设备名称
	 */
	protected String  deployname;
	/**
	 *设备编号
	 */
	protected String  deviceID;
	/**
	 *容器版本
	 */
	protected String  containerrvision;
	/**
	 *支持操作系统
	 */
	protected String  os;
	/**
	 *容器编号
	 */
	protected Long  containerid;
	/**
	 *应用编号
	 */
	protected Long  applicationID;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setDeployID(String deployID) 
	{
		this.deployID = deployID;
	}
	/**
	 * 返回 部署ID
	 * @return
	 */
	public String getDeployID() 
	{
		return this.deployID;
	}
	public void setPaaSname(String PaaSname) 
	{
		this.PaaSname = PaaSname;
	}
	/**
	 * 返回 平台服务名称
	 * @return
	 */
	public String getPaaSname() 
	{
		return this.PaaSname;
	}
	public void setPaaSID(String PaaSID) 
	{
		this.PaaSID = PaaSID;
	}
	/**
	 * 返回 平台服务编号
	 * @return
	 */
	public String getPaaSID() 
	{
		return this.PaaSID;
	}
	public void setURL(String URL) 
	{
		this.URL = URL;
	}
	/**
	 * 返回 访问地址URL
	 * @return
	 */
	public String getURL() 
	{
		return this.URL;
	}
	public void setDeviceIP(String deviceIP) 
	{
		this.deviceIP = deviceIP;
	}
	/**
	 * 返回 设备IP
	 * @return
	 */
	public String getDeviceIP() 
	{
		return this.deviceIP;
	}
	public void setDeployname(String deployname) 
	{
		this.deployname = deployname;
	}
	/**
	 * 返回 设备名称
	 * @return
	 */
	public String getDeployname() 
	{
		return this.deployname;
	}
	public void setDeviceID(String deviceID) 
	{
		this.deviceID = deviceID;
	}
	/**
	 * 返回 设备编号
	 * @return
	 */
	public String getDeviceID() 
	{
		return this.deviceID;
	}
	public void setContainerrvision(String containerrvision) 
	{
		this.containerrvision = containerrvision;
	}
	/**
	 * 返回 容器版本
	 * @return
	 */
	public String getContainerrvision() 
	{
		return this.containerrvision;
	}
	public void setOs(String os) 
	{
		this.os = os;
	}
	/**
	 * 返回 支持操作系统
	 * @return
	 */
	public String getOs() 
	{
		return this.os;
	}
	public void setContainerid(Long containerid) 
	{
		this.containerid = containerid;
	}
	/**
	 * 返回 容器编号
	 * @return
	 */
	public Long getContainerid() 
	{
		return this.containerid;
	}
	public void setApplicationID(Long applicationID) 
	{
		this.applicationID = applicationID;
	}
	/**
	 * 返回 应用编号
	 * @return
	 */
	public Long getApplicationID() 
	{
		return this.applicationID;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Deploy)) 
		{
			return false;
		}
		Deploy rhs = (Deploy) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.deployID, rhs.deployID)
		.append(this.PaaSname, rhs.PaaSname)
		.append(this.PaaSID, rhs.PaaSID)
		.append(this.URL, rhs.URL)
		.append(this.deviceIP, rhs.deviceIP)
		.append(this.deployname, rhs.deployname)
		.append(this.deviceID, rhs.deviceID)
		.append(this.containerrvision, rhs.containerrvision)
		.append(this.os, rhs.os)
		.append(this.containerid, rhs.containerid)
		.append(this.applicationID, rhs.applicationID)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.deployID) 
		.append(this.PaaSname) 
		.append(this.PaaSID) 
		.append(this.URL) 
		.append(this.deviceIP) 
		.append(this.deployname) 
		.append(this.deviceID) 
		.append(this.containerrvision) 
		.append(this.os) 
		.append(this.containerid) 
		.append(this.applicationID) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("deployID", this.deployID) 
		.append("PaaSname", this.PaaSname) 
		.append("PaaSID", this.PaaSID) 
		.append("URL", this.URL) 
		.append("deviceIP", this.deviceIP) 
		.append("deployname", this.deployname) 
		.append("deviceID", this.deviceID) 
		.append("containerrvision", this.containerrvision) 
		.append("os", this.os) 
		.append("containerid", this.containerid) 
		.append("applicationID", this.applicationID) 
		.toString();
	}

}