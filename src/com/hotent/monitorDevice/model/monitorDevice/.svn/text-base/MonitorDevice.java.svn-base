package com.hotent.monitorDevice.model.monitorDevice;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:监控设备 Model对象
 */
public class MonitorDevice extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *类型id
	 */
	protected String  typeid;
	/**
	 *设备名
	 */
	protected String  name;
	/**
	 *操作系统类型
	 */
	protected String  os_type;
	/**
	 *主机ip
	 */
	protected String  ip;
	/**
	 *品牌
	 */
	protected String  brand;
	/**
	 *用户名
	 */
	protected String  uesrname;
	/**
	 *密码
	 */
	protected String  password;
	/**
	 *SNMP端口号
	 */
	protected Long  port;
	/**
	 *团体号
	 */
	protected String  community;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTypeid(String typeid) 
	{
		this.typeid = typeid;
	}
	/**
	 * 返回 类型id
	 * @return
	 */
	public String getTypeid() 
	{
		return this.typeid;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 设备名
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setOs_type(String os_type) 
	{
		this.os_type = os_type;
	}
	/**
	 * 返回 操作系统类型
	 * @return
	 */
	public String getOs_type() 
	{
		return this.os_type;
	}
	public void setIp(String ip) 
	{
		this.ip = ip;
	}
	/**
	 * 返回 主机ip
	 * @return
	 */
	public String getIp() 
	{
		return this.ip;
	}
	public void setBrand(String brand) 
	{
		this.brand = brand;
	}
	/**
	 * 返回 品牌
	 * @return
	 */
	public String getBrand() 
	{
		return this.brand;
	}
	public void setUesrname(String uesrname) 
	{
		this.uesrname = uesrname;
	}
	/**
	 * 返回 用户名
	 * @return
	 */
	public String getUesrname() 
	{
		return this.uesrname;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	/**
	 * 返回 密码
	 * @return
	 */
	public String getPassword() 
	{
		return this.password;
	}
	public void setPort(Long port) 
	{
		this.port = port;
	}
	/**
	 * 返回 SNMP端口号
	 * @return
	 */
	public Long getPort() 
	{
		return this.port;
	}
	public void setCommunity(String community) 
	{
		this.community = community;
	}
	/**
	 * 返回 团体号
	 * @return
	 */
	public String getCommunity() 
	{
		return this.community;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof MonitorDevice)) 
		{
			return false;
		}
		MonitorDevice rhs = (MonitorDevice) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.typeid, rhs.typeid)
		.append(this.name, rhs.name)
		.append(this.os_type, rhs.os_type)
		.append(this.ip, rhs.ip)
		.append(this.brand, rhs.brand)
		.append(this.uesrname, rhs.uesrname)
		.append(this.password, rhs.password)
		.append(this.port, rhs.port)
		.append(this.community, rhs.community)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.typeid) 
		.append(this.name) 
		.append(this.os_type) 
		.append(this.ip) 
		.append(this.brand) 
		.append(this.uesrname) 
		.append(this.password) 
		.append(this.port) 
		.append(this.community) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("typeid", this.typeid) 
		.append("name", this.name) 
		.append("os_type", this.os_type) 
		.append("ip", this.ip) 
		.append("brand", this.brand) 
		.append("uesrname", this.uesrname) 
		.append("password", this.password) 
		.append("port", this.port) 
		.append("community", this.community) 
		.toString();
	}

}
