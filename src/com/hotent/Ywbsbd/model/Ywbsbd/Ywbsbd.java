package com.hotent.Ywbsbd.model.Ywbsbd;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:业务部署绑定 Model对象
 */
public class Ywbsbd extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *子系统id
	 */
	protected String  systemId;
	/**
	 *流程id
	 */
	protected String  defId;
	//节点id
	protected String nodeId;
	

	/**
	 *子系统名称
	 */
	protected String  sysname;
	/**
	 *子系统别名
	 */
	protected String  sysalias;
	/**
	 *备注
	 */
	protected String  sysmemo;
	/**
	 *服务
	 */
	protected String  service;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setSystemId(String systemId) 
	{
		this.systemId = systemId;
	}
	/**
	 * 返回 子系统id
	 * @return
	 */
	public String getSystemId() 
	{
		return this.systemId;
	}
	public void setDefId(String defId) 
	{
		this.defId = defId;
	}
	/**
	 * 返回 流程id
	 * @return
	 */
	public String getDefId() 
	{
		return this.defId;
	}
	/**
	 * @return the nodeid
	 */
	public String getNodeId() {
		return nodeId;
	}
	/**
	 * @param nodeid the nodeid to set
	 */
	public void setNodeId(String nodeid) {
		this.nodeId = nodeid;
	}
	public void setSysname(String sysname) 
	{
		this.sysname = sysname;
	}
	/**
	 * 返回 子系统名称
	 * @return
	 */
	public String getSysname() 
	{
		return this.sysname;
	}
	public void setSysalias(String sysalias) 
	{
		this.sysalias = sysalias;
	}
	/**
	 * 返回 子系统别名
	 * @return
	 */
	public String getSysalias() 
	{
		return this.sysalias;
	}
	public void setSysmeno(String sysmemo) 
	{
		this.sysmemo = sysmemo;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getSysmeno() 
	{
		return this.sysmemo;
	}
	public void setService(String service) 
	{
		this.service = service;
	}
	/**
	 * 返回 服务
	 * @return
	 */
	public String getService() 
	{
		return this.service;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Ywbsbd)) 
		{
			return false;
		}
		Ywbsbd rhs = (Ywbsbd) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.systemId, rhs.systemId)
		.append(this.defId, rhs.defId)
		.append(this.nodeId, rhs.nodeId)
		.append(this.sysname, rhs.sysname)
		.append(this.sysalias, rhs.sysalias)
		.append(this.sysmemo, rhs.sysmemo)
		.append(this.service, rhs.service)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.systemId) 
		.append(this.defId) 
		.append(this.nodeId)
		.append(this.sysname) 
		.append(this.sysalias) 
		.append(this.sysmemo) 
		.append(this.service) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("systemid", this.systemId) 
		.append("defId", this.defId) 
		.append("nodeId", this.nodeId) 
		.append("sysname", this.sysname) 
		.append("sysalias", this.sysalias) 
		.append("sysmeno", this.sysmemo) 
		.append("service", this.service) 
		.toString();
	}

}