package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:组织策略 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-31 13:43:14
 */
public class SysOrgTactic extends BaseModel{
	public static short ORG_TACTIC_WITHOUT = 0;
	public static short ORG_TACTIC_LEVEL = 1;
	public static short ORG_TACTIC_SELECT = 2;
	public static short ORG_TACTIC_COMBINATION = 3;
	
	public static Long DEFAULT_ID =1L;

	// 主键
	protected Long  id;
	// 策略（0、无策略1、按照级别 2、手工选择，3、按照级别+手工选择）
	protected Short  orgTactic;
	// 维度ID
	protected Long  demId;
	// 组织级别
	protected Long  orgType;
	// 组织选择ID
	protected String  orgSelectId;

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
	public void setOrgTactic(Short orgTactic){
		this.orgTactic = orgTactic;
	}
	/**
	 * 返回 策略（0、无策略1、按照级别 2、手工选择，3、按照级别+手工选择）
	 * @return
	 */
	public Short getOrgTactic() {
		return this.orgTactic;
	}
	public void setDemId(Long demId){
		this.demId = demId;
	}
	/**
	 * 返回 维度ID
	 * @return
	 */
	public Long getDemId() {
		return this.demId;
	}
	public void setOrgType(Long orgType){
		this.orgType = orgType;
	}
	/**
	 * 返回 组织级别
	 * @return
	 */
	public Long getOrgType() {
		return this.orgType;
	}
	public void setOrgSelectId(String orgSelectId){
		this.orgSelectId = orgSelectId;
	}
	/**
	 * 返回 组织选择ID
	 * @return
	 */
	public String getOrgSelectId() {
		return this.orgSelectId;
	}
	

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysOrgTactic)) 
		{
			return false;
		}
		SysOrgTactic rhs = (SysOrgTactic) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.orgTactic, rhs.orgTactic)
		.append(this.demId, rhs.demId)
		.append(this.orgType, rhs.orgType)
		.append(this.orgSelectId, rhs.orgSelectId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.orgTactic) 
		.append(this.demId) 
		.append(this.orgType) 
		.append(this.orgSelectId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("orgTactic", this.orgTactic) 
		.append("demId", this.demId) 
		.append("orgType", this.orgType) 
		.append("orgSelectId", this.orgSelectId) 
		.toString();
	}
   
  

}