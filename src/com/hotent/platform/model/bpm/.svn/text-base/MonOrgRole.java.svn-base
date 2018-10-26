package com.hotent.platform.model.bpm;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:监控组权限分配 Model对象
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-06-17 18:38:16
 */
public class MonOrgRole extends BaseModel
{
	// 主键
	protected Long  id;
	// 流程定义分组
	protected Long  groupid;
	// 角色Id
	protected Long  roleid;
	// 组织ID
	protected Long  orgid;
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setGroupid(Long groupid) 
	{
		this.groupid = groupid;
	}
	/**
	 * 返回 流程定义分组
	 * @return
	 */
	public Long getGroupid() 
	{
		return this.groupid;
	}
	public void setRoleid(Long roleid) 
	{
		this.roleid = roleid;
	}
	/**
	 * 返回 角色Id
	 * @return
	 */
	public Long getRoleid() 
	{
		return this.roleid;
	}
	public void setOrgid(Long orgid) 
	{
		this.orgid = orgid;
	}
	/**
	 * 返回 组织ID
	 * @return
	 */
	public Long getOrgid() 
	{
		return this.orgid;
	}


   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof MonOrgRole)) 
		{
			return false;
		}
		MonOrgRole rhs = (MonOrgRole) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.groupid, rhs.groupid)
		.append(this.roleid, rhs.roleid)
		.append(this.orgid, rhs.orgid)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.groupid) 
		.append(this.roleid) 
		.append(this.orgid) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("groupid", this.groupid) 
		.append("roleid", this.roleid) 
		.append("orgid", this.orgid) 
		.toString();
	}
   
  

}