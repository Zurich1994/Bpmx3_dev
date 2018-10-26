package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:用户所属组织或部门 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-12-07 18:23:24
 */
public class SysUserOrgPos
{
	/**
	 * 是否主要组织(0否,1是)
	 */
	public final static short PRIMARY_YES=1;
	public final static short PRIMARY_NO=0;
	
	/**
	 * 是否负责人(0否,1是)
	 */
	public final static Short CHARRGE_YES=1;
	public final static Short CHARRGE_NO=0;
	
	/**
	 * 是否组织管理员（0，否，1是)
	 */
	public final static Short IS_GRADE_MANAGE=1;
	public final static Short IS_NOT_GRADE_MANAGE=0;
	
	// 用户组织编号
	protected Long userOrgId;
	// 组织ID
	protected Long orgId;
	// userId
	protected Long userId;
	// 是否为主要
	protected Short isPrimary=PRIMARY_NO;
	// 组织负责人
	protected Short isCharge=CHARRGE_NO;
	//用户名称
	protected String userName;
	
	//帐号
	protected String account;
	
	//单位名称
	protected String orgName;
	//岗位名称
	protected String posName="";
	
	/**
	 * 负责人
	 */
	protected String chargeName="";
	
	/**
	 * 是否分级授权管理员。
	 */
	protected Short isGradeManage=0; 


	
	public void setUserOrgId(Long userOrgId) 
	{
		this.userOrgId = userOrgId;
	}
	/**
	 * 返回 用户组织编号
	 * @return
	 */
	public Long getUserOrgId() 
	{
		return userOrgId;
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
		return orgId;
	}

	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}
	/**
	 * 返回 userId
	 * @return
	 */
	public Long getUserId() 
	{
		return userId;
	}

	public void setIsPrimary(Short isPrimary) 
	{
		this.isPrimary = isPrimary;
	}
	/**
	 * 返回 是否为主要
	 * @return
	 */
	public Short getIsPrimary() 
	{
		return isPrimary;
	}

	public void setIsCharge(Short isCharge) 
	{
		this.isCharge = isCharge;
	}
	/**
	 * 返回 组织负责人ID
	 * @return
	 */
	public Short getIsCharge() 
	{
		return isCharge;
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
	
	public String getChargeName() {
		return chargeName;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	
	public String getPosName() {
		return posName;
	}
	public void setPosName(String posName) {
		this.posName = posName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Short getIsGradeManage() {
		return isGradeManage;
	}
	public void setIsGradeManage(Short isGradeManage) {
		this.isGradeManage = isGradeManage;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysUserOrg)) 
		{
			return false;
		}
		SysUserOrg rhs = (SysUserOrg) object;
		return new EqualsBuilder()
		.append(this.userOrgId, rhs.userOrgId)
		.append(this.orgId, rhs.orgId)
		.append(this.userId, rhs.userId)
		.append(this.isPrimary, rhs.isPrimary)
		.append(this.isCharge, rhs.isCharge)
		
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.userOrgId) 
		.append(this.orgId) 
		.append(this.userId) 
		.append(this.isPrimary) 
		.append(this.isCharge) 
		
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("userOrgId", this.userOrgId) 
		.append("orgId", this.orgId) 
		.append("userId", this.userId) 
		.append("isPrimary", this.isPrimary) 
		.append("isCharge", this.isCharge) 
		
		.toString();
	}
}