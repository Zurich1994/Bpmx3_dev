package com.hotent.switchSet.model.switchSet;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:交换机配置表 Model对象
 */
public class DeviceSwitch extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *管理IP
	 */
	protected String  manage_IP;
	/**
	 *网卡子网掩码
	 */
	protected String  manage_NIC_subnetMas;
	/**
	 *网卡默认网关
	 */
	protected String  manage_NIC_Default_g;
	/**
	 *网卡MAC地址
	 */
	protected String  manage_NIC_MAC_addre;
	/**
	 *设备编号
	 */
	protected String  dev_ID;
	/**
	 *配置信息
	 */
	protected String  config_info;
	/**
	 *备注
	 */
	protected String  remark;
	/**
	 *流程定义ID
	 */
	protected String  actdefid;
	/**
	 *流程节点ID
	 */
	protected String  nodeid;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setManage_IP(String manage_IP) 
	{
		this.manage_IP = manage_IP;
	}
	/**
	 * 返回 管理IP
	 * @return
	 */
	public String getManage_IP() 
	{
		return this.manage_IP;
	}
	public void setManage_NIC_subnetMas(String manage_NIC_subnetMas) 
	{
		this.manage_NIC_subnetMas = manage_NIC_subnetMas;
	}
	/**
	 * 返回 网卡子网掩码
	 * @return
	 */
	public String getManage_NIC_subnetMas() 
	{
		return this.manage_NIC_subnetMas;
	}
	public void setManage_NIC_Default_g(String manage_NIC_Default_g) 
	{
		this.manage_NIC_Default_g = manage_NIC_Default_g;
	}
	/**
	 * 返回 网卡默认网关
	 * @return
	 */
	public String getManage_NIC_Default_g() 
	{
		return this.manage_NIC_Default_g;
	}
	public void setManage_NIC_MAC_addre(String manage_NIC_MAC_addre) 
	{
		this.manage_NIC_MAC_addre = manage_NIC_MAC_addre;
	}
	/**
	 * 返回 网卡MAC地址
	 * @return
	 */
	public String getManage_NIC_MAC_addre() 
	{
		return this.manage_NIC_MAC_addre;
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
	public void setConfig_info(String config_info) 
	{
		this.config_info = config_info;
	}
	/**
	 * 返回 配置信息
	 * @return
	 */
	public String getConfig_info() 
	{
		return this.config_info;
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
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DeviceSwitch)) 
		{
			return false;
		}
		DeviceSwitch rhs = (DeviceSwitch) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.manage_IP, rhs.manage_IP)
		.append(this.manage_NIC_subnetMas, rhs.manage_NIC_subnetMas)
		.append(this.manage_NIC_Default_g, rhs.manage_NIC_Default_g)
		.append(this.manage_NIC_MAC_addre, rhs.manage_NIC_MAC_addre)
		.append(this.dev_ID, rhs.dev_ID)
		.append(this.config_info, rhs.config_info)
		.append(this.remark, rhs.remark)
		.append(this.actdefid, rhs.actdefid)
		.append(this.nodeid, rhs.nodeid)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.manage_IP) 
		.append(this.manage_NIC_subnetMas) 
		.append(this.manage_NIC_Default_g) 
		.append(this.manage_NIC_MAC_addre) 
		.append(this.dev_ID) 
		.append(this.config_info) 
		.append(this.remark) 
		.append(this.actdefid) 
		.append(this.nodeid) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("manage_IP", this.manage_IP) 
		.append("manage_NIC_subnetMas", this.manage_NIC_subnetMas) 
		.append("manage_NIC_Default_g", this.manage_NIC_Default_g) 
		.append("manage_NIC_MAC_addre", this.manage_NIC_MAC_addre) 
		.append("dev_ID", this.dev_ID) 
		.append("config_info", this.config_info) 
		.append("remark", this.remark) 
		.append("actdefid", this.actdefid) 
		.append("nodeid", this.nodeid) 
		.toString();
	}

}
