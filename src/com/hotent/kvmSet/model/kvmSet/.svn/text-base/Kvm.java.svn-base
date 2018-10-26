package com.hotent.kvmSet.model.kvmSet;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:KVM表 Model对象
 */
public class Kvm extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *管理IP
	 */
	protected String  manage_IP;
	/**
	 *设备编号
	 */
	protected String  dev_ID;
	/**
	 *KVM类型
	 */
	protected String  kvm_TYPE;
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
	public void setKvm_TYPE(String kvm_TYPE) 
	{
		this.kvm_TYPE = kvm_TYPE;
	}
	/**
	 * 返回 KVM类型
	 * @return
	 */
	public String getKvm_TYPE() 
	{
		return this.kvm_TYPE;
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
		if (!(object instanceof Kvm)) 
		{
			return false;
		}
		Kvm rhs = (Kvm) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.manage_IP, rhs.manage_IP)
		.append(this.dev_ID, rhs.dev_ID)
		.append(this.kvm_TYPE, rhs.kvm_TYPE)
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
		.append(this.dev_ID) 
		.append(this.kvm_TYPE) 
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
		.append("dev_ID", this.dev_ID) 
		.append("kvm_TYPE", this.kvm_TYPE) 
		.append("config_info", this.config_info) 
		.append("remark", this.remark) 
		.append("actdefid", this.actdefid) 
		.append("nodeid", this.nodeid) 
		.toString();
	}

}
