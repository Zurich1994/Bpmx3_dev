package com.hotent.Virtual.model.VirtualPac;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:虚拟机表 Model对象
 */
public class DeviceVirtual extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *虚拟机编号
	 */
	protected String  virtual_ID;
	/**
	 *服务器编号
	 */
	protected String  server_ID;
	/**
	 *IP
	 */
	protected String  ip;
	/**
	 *操作系统
	 */
	protected String  os_name;
	/**
	 *使用人
	 */
	protected String  user;
	/**
	 *用途
	 */
	protected String  use;
	/**
	 *用户名
	 */
	protected String  login_Username;
	/**
	 *密码
	 */
	protected String  login_Password;
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
	
	public void setVirtual_ID(String virtual_ID) 
	{
		this.virtual_ID = virtual_ID;
	}
	/**
	 * 返回 虚拟机编号
	 * @return
	 */
	public String getVirtual_ID() 
	{
		return this.virtual_ID;
	}
	public void setServer_ID(String server_ID) 
	{
		this.server_ID = server_ID;
	}
	/**
	 * 返回 服务器编号
	 * @return
	 */
	public String getServer_ID() 
	{
		return this.server_ID;
	}
	public void setIp(String ip) 
	{
		this.ip = ip;
	}
	/**
	 * 返回 IP
	 * @return
	 */
	public String getIp() 
	{
		return this.ip;
	}
	public void setOs_name(String os_name) 
	{
		this.os_name = os_name;
	}
	/**
	 * 返回 操作系统
	 * @return
	 */
	public String getOs_name() 
	{
		return this.os_name;
	}
	public void setUser(String user) 
	{
		this.user = user;
	}
	/**
	 * 返回 使用人
	 * @return
	 */
	public String getUser() 
	{
		return this.user;
	}
	public void setUse(String use) 
	{
		this.use = use;
	}
	/**
	 * 返回 用途
	 * @return
	 */
	public String getUse() 
	{
		return this.use;
	}
	public void setLogin_Username(String login_Username) 
	{
		this.login_Username = login_Username;
	}
	/**
	 * 返回 用户名
	 * @return
	 */
	public String getLogin_Username() 
	{
		return this.login_Username;
	}
	public void setLogin_Password(String login_Password) 
	{
		this.login_Password = login_Password;
	}
	/**
	 * 返回 密码
	 * @return
	 */
	public String getLogin_Password() 
	{
		return this.login_Password;
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
		if (!(object instanceof DeviceVirtual)) 
		{
			return false;
		}
		DeviceVirtual rhs = (DeviceVirtual) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.id, rhs.id)
		.append(this.virtual_ID, rhs.virtual_ID)
		.append(this.server_ID, rhs.server_ID)
		.append(this.ip, rhs.ip)
		.append(this.os_name, rhs.os_name)
		.append(this.user, rhs.user)
		.append(this.use, rhs.use)
		.append(this.login_Username, rhs.login_Username)
		.append(this.login_Password, rhs.login_Password)
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
		.append(this.virtual_ID) 
		.append(this.server_ID) 
		.append(this.ip) 
		.append(this.os_name) 
		.append(this.user) 
		.append(this.use) 
		.append(this.login_Username) 
		.append(this.login_Password) 
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
		.append("virtual_ID", this.virtual_ID) 
		.append("server_ID", this.server_ID) 
		.append("ip", this.ip) 
		.append("os_name", this.os_name) 
		.append("user", this.user) 
		.append("use", this.use) 
		.append("login_Username", this.login_Username) 
		.append("login_Password", this.login_Password) 
		.append("remark", this.remark) 
		.toString();
	}

}
