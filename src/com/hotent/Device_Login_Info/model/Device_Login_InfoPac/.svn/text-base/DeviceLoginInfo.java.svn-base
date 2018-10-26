package com.hotent.Device_Login_Info.model.Device_Login_InfoPac;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:硬件登录信息表 Model对象
 */
public class DeviceLoginInfo extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *设备编号
	 */
	protected String  dev_ID;
	/**
	 *登录方式
	 */
	protected String  login_manner;
	/**
	 *IP地址
	 */
	protected String  ip_address;
	/**
	 *端口号
	 */
	protected String  port;
	/**
	 *用户名
	 */
	protected String  username;
	/**
	 *密码
	 */
	protected String  password;
	/**
	 *备注
	 */
	protected String  remark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setDev_ID(String dev_ID) 
	{
		this.dev_ID = dev_ID;
	}
	/**
	 * 返回 设备编号
	 * @return
	 */
	public String getDev_ID() 
	{
		return this.dev_ID;
	}
	public void setLogin_manner(String login_manner) 
	{
		this.login_manner = login_manner;
	}
	/**
	 * 返回 登录方式
	 * @return
	 */
	public String getLogin_manner() 
	{
		return this.login_manner;
	}
	public void setIp_address(String ip_address) 
	{
		this.ip_address = ip_address;
	}
	/**
	 * 返回 IP地址
	 * @return
	 */
	public String getIp_address() 
	{
		return this.ip_address;
	}
	public void setPort(String port) 
	{
		this.port = port;
	}
	/**
	 * 返回 端口号
	 * @return
	 */
	public String getPort() 
	{
		return this.port;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	/**
	 * 返回 用户名
	 * @return
	 */
	public String getUsername() 
	{
		return this.username;
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
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getRemark() 
	{
		return this.remark;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DeviceLoginInfo)) 
		{
			return false;
		}
		DeviceLoginInfo rhs = (DeviceLoginInfo) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.id, rhs.id)
		.append(this.dev_ID, rhs.dev_ID)
		.append(this.login_manner, rhs.login_manner)
		.append(this.ip_address, rhs.ip_address)
		.append(this.port, rhs.port)
		.append(this.username, rhs.username)
		.append(this.password, rhs.password)
		.append(this.remark, rhs.remark)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.id) 
		.append(this.dev_ID) 
		.append(this.login_manner) 
		.append(this.ip_address) 
		.append(this.port) 
		.append(this.username) 
		.append(this.password) 
		.append(this.remark) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("id", this.id) 
		.append("dev_ID", this.dev_ID) 
		.append("login_manner", this.login_manner) 
		.append("ip_address", this.ip_address) 
		.append("port", this.port) 
		.append("username", this.username) 
		.append("password", this.password) 
		.append("remark", this.remark) 
		.toString();
	}

}
