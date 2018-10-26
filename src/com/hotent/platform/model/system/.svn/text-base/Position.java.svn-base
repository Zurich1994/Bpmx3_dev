package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:系统岗位表，实际是部门和职务的对应关系表 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-11-27 10:19:23
 */
public class Position extends BaseModel
{
	// POSID
	protected Long  posId;
	// POSNAME
	protected String  posName;
	// POSDESC
	protected String  posDesc;
	//岗位代码
	protected String posCode;
	// ORGID
	protected Long  orgId;
	// JOBID
	protected Long  jobId;
	// ISDELETE
	protected Long  isDelete=0L;
	
	protected Short isPrimary;
	//所属组织代码(表中没有此字段)
	protected String orgCode;
	//所属职务代码(表中没有此字段)
	protected String jobCode;
	
	//组织名称，表中没有此字段
	protected String  orgName;
	
	//用户名字符串，表中没有此字段,岗位下所有用户拼接字符串
	protected String  userNames;
	
	//职务名称，表中没有此字段
	protected String  jobName;
	
	//职务等级，表中没有此字段
	protected String  jobGradeName;
		
		
	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}



   	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Short getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Short isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Long getPosId() {
		return posId;
	}

	public void setPosId(Long posId) {
		this.posId = posId;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getPosDesc() {
		return posDesc;
	}

	public void setPosDesc(String posDesc) {
		this.posDesc = posDesc;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Long isDelete) {
		this.isDelete = isDelete;
	}

	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	
	public String getJobGradeName() {
		return jobGradeName;
	}

	public void setJobGradeName(String jobGradeName) {
		this.jobGradeName = jobGradeName;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Position)) 
		{
			return false;
		}
		Position rhs = (Position) object;
		return new EqualsBuilder()
		.append(this.posId, rhs.posId)
		.append(this.posName, rhs.posName)
		.append(this.posDesc, rhs.posDesc)
		.append(this.orgId, rhs.orgId)
		.append(this.jobId, rhs.jobId)
		.append(this.isDelete, rhs.isDelete)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.posId) 
		.append(this.posName) 
		.append(this.posDesc) 
		.append(this.orgId) 
		.append(this.jobId) 
		.append(this.isDelete) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("posId", this.posId) 
		.append("posName", this.posName) 
		.append("posDesc", this.posDesc) 
		.append("orgId", this.orgId) 
		.append("jobId", this.jobId) 
		.append("isDelete", this.isDelete) 
		.toString();
	}
}