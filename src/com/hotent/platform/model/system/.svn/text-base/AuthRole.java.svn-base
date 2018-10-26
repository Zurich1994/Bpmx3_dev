package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:SYS_AUTH_ROLE Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:miao
 * 创建时间:2014-08-08 10:16:20
 */
public class AuthRole extends BaseModel
{
	// ID
	protected Long  id;
	// AUTH_ID
	protected Long  authId;
	// ROLE_ID
	protected Long  roleId;
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
	public AuthRole(long id,Long authId, Long roleId) {
		this.id = id;
		this.authId = authId;
		this.roleId = roleId;
	}
	public void setAuthId(Long authId) 
	{
		this.authId = authId;
	}
	/**
	 * 返回 AUTH_ID
	 * @return
	 */
	public Long getAuthId() 
	{
		return this.authId;
	}
	public void setRoleId(Long roleId) 
	{
		this.roleId = roleId;
	}
	/**
	 * 返回 ROLE_ID
	 * @return
	 */
	public Long getRoleId() 
	{
		return this.roleId;
	}

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AuthRole)) 
		{
			return false;
		}
		AuthRole rhs = (AuthRole) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.authId, rhs.authId)
		.append(this.roleId, rhs.roleId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.authId) 
		.append(this.roleId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("authId", this.authId) 
		.append("roleId", this.roleId) 
		.toString();
	}
   
  

}