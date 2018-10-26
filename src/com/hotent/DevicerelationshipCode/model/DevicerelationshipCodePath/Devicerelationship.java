package com.hotent.DevicerelationshipCode.model.DevicerelationshipCodePath;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:线路表 Model对象
 */
public class Devicerelationship extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *设备编号
	 */
	protected String  dev_ID;
	/**
	 *设备端口
	 */
	protected String  dev_Port;
	/**
	 *对端设备编号
	 */
	protected String  opp_ID;
	/**
	 *对端设备端口类型
	 */
	protected String  opp_PortType;
	/**
	 *对端设备端口
	 */
	protected String  opp_Port;
	/**
	 *备注
	 */
	protected String  remark;
	/**
	 *流程定义ID
	 */
	protected String  actdefid;
	
	public String getActdefid() {
		return actdefid;
	}
	public void setActdefid(String actdefid) {
		this.actdefid = actdefid;
	}
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
	public void setDev_Port(String dev_Port) 
	{
		this.dev_Port = dev_Port;
	}
	/**
	 * 返回 设备端口
	 * @return
	 */
	public String getDev_Port() 
	{
		return this.dev_Port;
	}
	public void setOpp_ID(String opp_ID) 
	{
		this.opp_ID = opp_ID;
	}
	/**
	 * 返回 对端设备编号
	 * @return
	 */
	public String getOpp_ID() 
	{
		return this.opp_ID;
	}
	public void setOpp_PortType(String opp_PortType) 
	{
		this.opp_PortType = opp_PortType;
	}
	/**
	 * 返回 对端设备端口类型
	 * @return
	 */
	public String getOpp_PortType() 
	{
		return this.opp_PortType;
	}
	public void setOpp_Port(String opp_Port) 
	{
		this.opp_Port = opp_Port;
	}
	/**
	 * 返回 对端设备端口
	 * @return
	 */
	public String getOpp_Port() 
	{
		return this.opp_Port;
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
		if (!(object instanceof Devicerelationship)) 
		{
			return false;
		}
		Devicerelationship rhs = (Devicerelationship) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.id, rhs.id)
		.append(this.dev_ID, rhs.dev_ID)
		.append(this.dev_Port, rhs.dev_Port)
		.append(this.opp_ID, rhs.opp_ID)
		.append(this.opp_PortType, rhs.opp_PortType)
		.append(this.opp_Port, rhs.opp_Port)
		.append(this.remark, rhs.remark)
		.append(this.actdefid, rhs.actdefid)
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
		.append(this.dev_Port) 
		.append(this.opp_ID) 
		.append(this.opp_PortType) 
		.append(this.opp_Port) 
		.append(this.remark) 
		.append(this.actdefid)
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
		.append("dev_Port", this.dev_Port) 
		.append("opp_ID", this.opp_ID) 
		.append("opp_PortType", this.opp_PortType) 
		.append("opp_Port", this.opp_Port) 
		.append("remark", this.remark) 
		.append("actdefid", this.actdefid) 
		.toString();
	}

}
