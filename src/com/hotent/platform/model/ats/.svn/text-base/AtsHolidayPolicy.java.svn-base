package com.hotent.platform.model.ats;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:假期制度 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-19 17:42:20
 */
public class AtsHolidayPolicy extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1571199842696902581L;
	// 主键
	protected Long id;
	// 编码
	protected String code;
	// 名称
	protected String name;
	// 所属组织
	protected Long orgId;
	// 是否默认
	protected Short isDefault;
	// 是否启动半天假
	protected Short isHalfDayOff;
	// 描述
	protected String memo;

	protected String orgName;
	//明细
		protected List<AtsHolidayPolicyDetail> atsHolidayPolicyDetailList;
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
	public void setCode(String code){
		this.code = code;
	}
	/**
	 * 返回 编码
	 * @return
	 */
	public String getCode() {
		return this.code;
	}
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 返回 名称
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	public void setOrgId(Long orgId){
		this.orgId = orgId;
	}
	/**
	 * 返回 所属组织
	 * @return
	 */
	public Long getOrgId() {
		return this.orgId;
	}
	public void setIsDefault(Short isDefault){
		this.isDefault = isDefault;
	}
	/**
	 * 返回 是否默认
	 * @return
	 */
	public Short getIsDefault() {
		return this.isDefault;
	}
	public void setIsHalfDayOff(Short isHalfDayOff){
		this.isHalfDayOff = isHalfDayOff;
	}
	/**
	 * 返回 是否启动半天假
	 * @return
	 */
	public Short getIsHalfDayOff() {
		return this.isHalfDayOff;
	}
	public void setMemo(String memo){
		this.memo = memo;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getMemo() {
		return this.memo;
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
		if (!(object instanceof AtsHolidayPolicy)) 
		{
			return false;
		}
		AtsHolidayPolicy rhs = (AtsHolidayPolicy) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.code, rhs.code)
		.append(this.name, rhs.name)
		.append(this.orgId, rhs.orgId)
		.append(this.isDefault, rhs.isDefault)
		.append(this.isHalfDayOff, rhs.isHalfDayOff)
		.append(this.memo, rhs.memo)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.code) 
		.append(this.name) 
		.append(this.orgId) 
		.append(this.isDefault) 
		.append(this.isHalfDayOff) 
		.append(this.memo) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("code", this.code) 
		.append("name", this.name) 
		.append("orgId", this.orgId) 
		.append("isDefault", this.isDefault) 
		.append("isHalfDayOff", this.isHalfDayOff) 
		.append("memo", this.memo) 
		.toString();
	}
	public List<AtsHolidayPolicyDetail> getAtsHolidayPolicyDetailList() {
		return atsHolidayPolicyDetailList;
	}
	public void setAtsHolidayPolicyDetailList(
			List<AtsHolidayPolicyDetail> atsHolidayPolicyDetailList) {
		this.atsHolidayPolicyDetailList = atsHolidayPolicyDetailList;
	}
   

}