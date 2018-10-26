package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.hotent.core.model.BaseModel;

/**
 * 对象功能:组织角色授权信息 Model对象
 * 开发公司:宏天
 * 开发人员:hotent
 * 创建时间:2012-10-30 09:55:48
 */
public class SysOrgRole extends BaseModel
{
	// 主键
	protected Long  id;
	// 组织ID
	protected Long  orgid;
	// 角色ID
	protected Long  roleid;
	//来源类型（0:可分配角色，1:角色授权）
	protected Integer fromType = 0;
	
	protected String orgName="";

	private SysRole role;
	//是否可以删除(0，不可以，1，可以）
	protected Integer  canDel=0;
	
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
	public void setRoleid(Long roleid) 
	{
		this.roleid = roleid;
	}
	/**
	 * 返回 角色ID
	 * @return
	 */
	public Long getRoleid() 
	{
		return this.roleid;
	}

	public SysRole getRole() {
		return role;
	}
	
	
	public void setRole(SysRole role) {
		this.role = role;
	}
   
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public Integer getCanDel() {
		return canDel;
	}
	public void setCanDel(Integer canDel) {
		this.canDel = canDel;
	}
	public Integer getFromType() {
		return fromType;
	}
	public void setFromType(Integer fromType) {
		this.fromType = fromType;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysOrgRole)) 
		{
			return false;
		}
		SysOrgRole rhs = (SysOrgRole) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.orgid, rhs.orgid)
		.append(this.roleid, rhs.roleid)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.orgid) 
		.append(this.roleid) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("orgid", this.orgid) 
		.append("roleid", this.roleid) 
		.toString();
	}
   
  

}