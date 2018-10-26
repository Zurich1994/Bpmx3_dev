package com.hotent.deviceNodeSet.model.deviceNodeSet;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:设备节点部署表 Model对象
 */
public class DeviceNodeSet extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *业务IP
	 */
	protected String  businessIP;
	/**
	 *流程定义ID
	 */
	protected String  actdefid;
	/**
	 *流程节点ID
	 */
	protected String  nodeid;
	/**
	 *设备表
	 */
	protected String  deviceTable;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setBusinessIP(String businessIP) 
	{
		this.businessIP = businessIP;
	}
	/**
	 * 返回 业务IP
	 * @return
	 */
	public String getBusinessIP() 
	{
		return this.businessIP;
	}
	public void setActdefid(String actdefid) 
	{
		this.actdefid = actdefid;
	}
	/**
	 * 返回 流程定义ID
	 * @return
	 */
	public String getActdefid() 
	{
		return this.actdefid;
	}
	public void setNodeid(String nodeid) 
	{
		this.nodeid = nodeid;
	}
	/**
	 * 返回 流程节点ID
	 * @return
	 */
	public String getNodeid() 
	{
		return this.nodeid;
	}
	public void setDeviceTable(String deviceTable) 
	{
		this.deviceTable = deviceTable;
	}
	/**
	 * 返回 设备表
	 * @return
	 */
	public String getDeviceTable() 
	{
		return this.deviceTable;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DeviceNodeSet)) 
		{
			return false;
		}
		DeviceNodeSet rhs = (DeviceNodeSet) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.businessIP, rhs.businessIP)
		.append(this.actdefid, rhs.actdefid)
		.append(this.nodeid, rhs.nodeid)
		.append(this.deviceTable, rhs.deviceTable)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.businessIP) 
		.append(this.actdefid) 
		.append(this.nodeid) 
		.append(this.deviceTable) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("businessIP", this.businessIP) 
		.append("actdefid", this.actdefid) 
		.append("nodeid", this.nodeid) 
		.append("deviceTable", this.deviceTable) 
		.toString();
	}

}
