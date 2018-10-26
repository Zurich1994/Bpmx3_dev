package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:角色资源映射 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-08 11:23:15
 */
public class RoleResources extends BaseModel implements Cloneable
{
	// 角色资源Id
	protected Long roleResId;
	// roleId
	protected Long roleId;
	// 资源主键
	protected Long resId;
	
	protected Long systemId;

	public void setRoleResId(Long roleResId) 
	{
		this.roleResId = roleResId;
	}
	/**
	 * 返回 角色资源Id
	 * @return
	 */
	public Long getRoleResId() 
	{
		return roleResId;
	}

	public void setRoleId(Long roleId) 
	{
		this.roleId = roleId;
	}
	/**
	 * 返回 roleId
	 * @return
	 */
	public Long getRoleId() 
	{
		return roleId;
	}

	public void setResId(Long resId) 
	{
		this.resId = resId;
	}
	/**
	 * 返回 资源主键
	 * @return
	 */
	public Long getResId() 
	{
		return resId;
	}

   
   	public Long getSystemId() {
		return systemId;
	}
	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof RoleResources)) 
		{
			return false;
		}
		RoleResources rhs = (RoleResources) object;
		return new EqualsBuilder()
		.append(this.roleResId, rhs.roleResId)
		.append(this.roleId, rhs.roleId)
		.append(this.resId, rhs.resId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.roleResId) 
		.append(this.roleId) 
		.append(this.resId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("roleResId", this.roleResId) 
		.append("roleId", this.roleId) 
		.append("resId", this.resId) 
		.toString();
	}
   
	
	public Object clone(){
		RoleResources obj=null;
		try{
			obj=(RoleResources)super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}
  

}