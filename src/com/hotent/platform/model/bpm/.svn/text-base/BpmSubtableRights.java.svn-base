package com.hotent.platform.model.bpm;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:子表权限 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:wwz
 * 创建时间:2013-01-16 10:04:39
 */
public class BpmSubtableRights extends BaseModel
{
	// ID
	protected Long  id;
	// 流程定义ID
	protected String  actdefid;
	// 节点ID
	protected String  nodeid;
	// 子表表ID
	protected Long  tableid;
	// 权限类型(1,简单配置,2,脚本)
	protected Long  permissiontype;
	// 权限配置
	protected String  permissionseting;
	// 父流程定义ID
	protected String  parentActDefId;
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 ID
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
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
	 * 返回 节点ID
	 * @return
	 */
	public String getNodeid() 
	{
		return this.nodeid;
	}
	public void setTableid(Long tableid) 
	{
		this.tableid = tableid;
	}
	/**
	 * 返回 子表表ID
	 * @return
	 */
	public Long getTableid() 
	{
		return this.tableid;
	}
	public void setPermissiontype(Long permissiontype) 
	{
		this.permissiontype = permissiontype;
	}
	/**
	 * 返回 权限类型(1,简单配置,2,脚本)
	 * @return
	 */
	public Long getPermissiontype() 
	{
		return this.permissiontype;
	}
	public void setPermissionseting(String permissionseting) 
	{
		this.permissionseting = permissionseting;
	}
	/**
	 * 返回 权限配置
	 * @return
	 */
	public String getPermissionseting() 
	{
		return this.permissionseting;
	}
	
	public String getParentActDefId() {
		return parentActDefId;
	}
	public void setParentActDefId(String parentActDefId) {
		this.parentActDefId = parentActDefId;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmSubtableRights)) 
		{
			return false;
		}
		BpmSubtableRights rhs = (BpmSubtableRights) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.actdefid, rhs.actdefid)
		.append(this.nodeid, rhs.nodeid)
		.append(this.tableid, rhs.tableid)
		.append(this.permissiontype, rhs.permissiontype)
		.append(this.permissionseting, rhs.permissionseting)
		.append(this.parentActDefId, rhs.parentActDefId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.actdefid) 
		.append(this.nodeid) 
		.append(this.tableid) 
		.append(this.permissiontype) 
		.append(this.permissionseting) 
		.append(this.parentActDefId)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("actdefid", this.actdefid) 
		.append("nodeid", this.nodeid) 
		.append("tableid", this.tableid) 
		.append("permissiontype", this.permissiontype) 
		.append("permissionseting", this.permissionseting) 
		.append("parentActDefId", this.parentActDefId) 
		.toString();
	}
   
  

}