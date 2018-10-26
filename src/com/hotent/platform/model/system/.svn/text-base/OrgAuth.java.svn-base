package com.hotent.platform.model.system;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:SYS_ORG_AUTH Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:miao
 * 创建时间:2014-08-08 10:19:21
 */
public class OrgAuth extends BaseModel
{
	// 主键
	protected Long  id;
	// 管理员ID
	protected Long  userId;
	protected String userName;
	// 组织ID
	protected Long  orgId;
	protected String orgName;
	// 维度ID
	protected Long  dimId;
	protected String dimName;
	// 针对子用户组的添加、更新和删除
	protected String  orgPerms;
	// 针对用户与组关系的添加、更新和删除
	protected String  userPerms;
	
	List<SysRole> assignRoles ; 
	private String orgPath;
	
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
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}
	/**
	 * 返回 管理员ID
	 * @return
	 */
	public Long getUserId() 
	{
		return this.userId;
	}
	public void setOrgId(Long orgId) 
	{
		this.orgId = orgId;
	}
	/**
	 * 返回 组织ID
	 * @return
	 */
	public Long getOrgId() 
	{
		return this.orgId;
	}
	public void setDimId(Long dimId) 
	{
		this.dimId = dimId;
	}
	/**
	 * 返回 维度ID
	 * @return
	 */
	public Long getDimId() 
	{
		return this.dimId;
	}
	public void setOrgPerms(String orgPerms) 
	{
		this.orgPerms = orgPerms;
	}
	/**
	 * 返回 针对子用户组的添加、更新和删除
	 * @return
	 */
	public String getOrgPerms() 
	{
		return this.orgPerms;
	}
	public void setUserPerms(String userPerms) 
	{
		this.userPerms = userPerms;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDimName() {
		return dimName;
	}
	public void setDimName(String dimName) {
		this.dimName = dimName;
	}
	public List<SysRole> getAssignRoles() {
		return assignRoles;
	}
	public void setAssignRoles(List<SysRole> assignRoles) {
		this.assignRoles = assignRoles;
	}
	/**
	 * 返回 针对用户与组关系的添加、更新和删除
	 * @return
	 */
	public String getUserPerms() 
	{
		return this.userPerms;
	}

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof OrgAuth)) 
		{
			return false;
		}
		OrgAuth rhs = (OrgAuth) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.userId, rhs.userId)
		.append(this.orgId, rhs.orgId)
		.append(this.dimId, rhs.dimId)
		.append(this.orgPerms, rhs.orgPerms)
		.append(this.userPerms, rhs.userPerms)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.userId) 
		.append(this.orgId) 
		.append(this.dimId) 
		.append(this.orgPerms) 
		.append(this.userPerms) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("userId", this.userId) 
		.append("orgId", this.orgId) 
		.append("dimId", this.dimId) 
		.append("orgPerms", this.orgPerms) 
		.append("userPerms", this.userPerms) 
		.toString();
	}
	public String getOrgPath() {
		return orgPath;
	}
	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
   
  

}