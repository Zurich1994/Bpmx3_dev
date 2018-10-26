package com.hotent.platform.model.ats;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:轮班规则明细 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-21 09:06:50
 */
public class AtsShiftRuleDetail extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1064776984265594280L;
	// 主键
	protected Long id;
	// 班次ID
	protected Long ruleId;
	// 日期类型
	protected Short dateType;
	// 班次ID
	protected Long shiftId;
	// 上下班时间
	protected String shiftTime;
	// 排序
	protected Integer sn;
	
	
	protected String shiftName;

	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
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
	public void setRuleId(Long ruleId){
		this.ruleId = ruleId;
	}
	/**
	 * 返回 班次ID
	 * @return
	 */
	public Long getRuleId() {
		return this.ruleId;
	}
	public void setDateType(Short dateType){
		this.dateType = dateType;
	}
	/**
	 * 返回 日期类型
	 * @return
	 */
	public Short getDateType() {
		return this.dateType;
	}
	public void setShiftId(Long shiftId){
		this.shiftId = shiftId;
	}
	/**
	 * 返回 班次ID
	 * @return
	 */
	public Long getShiftId() {
		return this.shiftId;
	}
	public void setShiftTime(String shiftTime){
		this.shiftTime = shiftTime;
	}
	/**
	 * 返回 上下班时间
	 * @return
	 */
	public String getShiftTime() {
		return this.shiftTime;
	}
	public void setSn(Integer sn){
		this.sn = sn;
	}
	/**
	 * 返回 排序
	 * @return
	 */
	public Integer getSn() {
		return this.sn;
	}
	

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AtsShiftRuleDetail)) 
		{
			return false;
		}
		AtsShiftRuleDetail rhs = (AtsShiftRuleDetail) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.ruleId, rhs.ruleId)
		.append(this.dateType, rhs.dateType)
		.append(this.shiftId, rhs.shiftId)
		.append(this.shiftTime, rhs.shiftTime)
		.append(this.sn, rhs.sn)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.ruleId) 
		.append(this.dateType) 
		.append(this.shiftId) 
		.append(this.shiftTime) 
		.append(this.sn) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("ruleId", this.ruleId) 
		.append("dateType", this.dateType) 
		.append("shiftId", this.shiftId) 
		.append("shiftTime", this.shiftTime) 
		.append("sn", this.sn) 
		.toString();
	}
   
  

}