package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:SYS_USER_POS Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-11-27 10:19:23
 */
public class UserPosition extends SysUserOrg
{
	/**
	 * 是否主要岗位(0否,1是)
	 */
	public final static short PRIMARY_YES=1;
	public final static short PRIMARY_NO=0;
	
	/**
	 * 是否负责人(0否,1是)
	 */
	public final static Short CHARRGE_YES=1;
	public final static Short CHARRGE_NO=0;
	
	/**
	 * 是否删除(0否,1是)
	 */
	public final static Short DELETE_YES=1;
	public final static Short DELETE_NO=0;
	
	/**
	 * 是否组织管理员（0，否，1是)
	 */
	public final static Short IS_GRADE_MANAGE=1;
	public final static Short IS_NOT_GRADE_MANAGE=0;
	
	// userposId
	protected Long  userPosId;
	// ORGID
	protected Long  orgId;
	// posId
	protected Long  posId;
	// USERID
	protected Long  userId;
	// ISPRIMARY
	protected Short  isPrimary=PRIMARY_NO;
	// ISCHARGE
	protected Short  isCharge=CHARRGE_NO;
	// ISDELETE 默认值0
	protected Short  isDelete=DELETE_NO;
	
	protected String posName;
	
	protected String userName;
	//职务id
	protected Long  jobId;
	//职务名称
	protected String jobName;
	

   	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public Long getUserPosId() {
		return userPosId;
	}

	public void setUserPosId(Long userPosId) {
		this.userPosId = userPosId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getPosId() {
		return posId;
	}

	public void setPosId(Long posId) {
		this.posId = posId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Short getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Short isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Short getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(Short isCharge) {
		this.isCharge = isCharge;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof UserPosition)) 
		{
			return false;
		}
		UserPosition rhs = (UserPosition) object;
		return new EqualsBuilder()
		.append(this.userPosId, rhs.userPosId)
		.append(this.orgId, rhs.orgId)
		.append(this.posId, rhs.posId)
		.append(this.userId, rhs.userId)
		.append(this.isPrimary, rhs.isPrimary)
		.append(this.isCharge, rhs.isCharge)
		.append(this.isDelete, rhs.isDelete)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.userPosId) 
		.append(this.orgId) 
		.append(this.posId) 
		.append(this.userId) 
		.append(this.isPrimary) 
		.append(this.isCharge) 
		.append(this.isDelete) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("userposId", this.userPosId) 
		.append("orgId", this.orgId) 
		.append("posId", this.posId) 
		.append("userId", this.userId) 
		.append("isPrimary", this.isPrimary) 
		.append("isCharge", this.isCharge) 
		.append("isDelete", this.isDelete) 
		.toString();
	}
   
  

}