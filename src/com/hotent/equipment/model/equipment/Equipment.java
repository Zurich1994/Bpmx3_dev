package com.hotent.equipment.model.equipment;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:设备表 Model对象
 */
public class Equipment extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *流程编号
	 */
	/**
	 *设备名称
	 */
	protected String  deviceName;
	/**
	 *设备IP
	 */
	protected String  deviceIP;
	/**
	 *操作系统
	 */
	protected String  OS;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public void setDeviceName(String deviceName) 
	{
		this.deviceName = deviceName;
	}
	/**
	 * 返回 设备名称
	 * @return
	 */
	public String getDeviceName() 
	{
		return this.deviceName;
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
	public void setOS(String OS) 
	{
		this.OS = OS;
	}
	/**
	 * 返回 操作系统
	 * @return
	 */
	public String getOS() 
	{
		return this.OS;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Equipment)) 
		{
			return false;
		}
		Equipment rhs = (Equipment) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.deviceName, rhs.deviceName)
		.append(this.deviceIP, rhs.deviceIP)
		.append(this.OS, rhs.OS)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.deviceName) 
		.append(this.deviceIP) 
		.append(this.OS) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("deviceName", this.deviceName) 
		.append("deviceIP", this.deviceIP) 
		.append("OS", this.OS) 
		.toString();
	}

}