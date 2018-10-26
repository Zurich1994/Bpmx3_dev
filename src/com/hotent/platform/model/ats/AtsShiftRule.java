package com.hotent.platform.model.ats;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * <pre>
 * 对象功能:轮班规则 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-21 09:06:10
 *  </pre>
 */
public class AtsShiftRule extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7006892257242789967L;
	// 主键
	protected Long id;
	// 编码
	protected String code;
	// 名称
	protected String name;
	// 状态
	protected Short status = AtsConstant.YES;
	// 所属组织
	protected Long orgId;
	// 描述
	protected String memo;
	
	protected String detailList;
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
	public void setStatus(Short status){
		this.status = status;
	}
	/**
	 * 返回 状态
	 * @return
	 */
	public Short getStatus() {
		return this.status;
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
	public void setMemo(String memo){
		this.memo = memo;
	}
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}
   	public String getDetailList() {
		return detailList;
	}
	public void setDetailList(String detailList) {
		this.detailList = detailList;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AtsShiftRule)) 
		{
			return false;
		}
		AtsShiftRule rhs = (AtsShiftRule) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.code, rhs.code)
		.append(this.name, rhs.name)
		.append(this.status, rhs.status)
		.append(this.orgId, rhs.orgId)
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
		.append(this.status) 
		.append(this.orgId) 
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
		.append("status", this.status) 
		.append("orgId", this.orgId) 
		.append("memo", this.memo) 
		.toString();
	}
   
  

}