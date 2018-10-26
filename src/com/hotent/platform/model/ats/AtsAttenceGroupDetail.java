package com.hotent.platform.model.ats;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:考勤组明细 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-26 10:07:59
 */
public class AtsAttenceGroupDetail extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4096294203695757785L;
	// 主键
	protected Long id;
	// 考勤组
	protected Long groupId;
	// 考勤档案
	protected Long fileId;
	
	protected String account;
	protected String userName;
	protected String orgName;
	

	public void setId(Long id){
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	public void setGroupId(Long groupId){
		this.groupId = groupId;
	}
	/**
	 * 返回 考勤组
	 * @return
	 */
	public Long getGroupId() {
		return this.groupId;
	}
	public void setFileId(Long fileId){
		this.fileId = fileId;
	}
	/**
	 * 返回 考勤档案
	 * @return
	 */
	public Long getFileId() {
		return this.fileId;
	}
	

   	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AtsAttenceGroupDetail)) 
		{
			return false;
		}
		AtsAttenceGroupDetail rhs = (AtsAttenceGroupDetail) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.groupId, rhs.groupId)
		.append(this.fileId, rhs.fileId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.groupId) 
		.append(this.fileId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("groupId", this.groupId) 
		.append("fileId", this.fileId) 
		.toString();
	}
   
  

}