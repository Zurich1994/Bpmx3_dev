package com.hotent.platform.model.ats;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * 对象功能:班次设置 Model对象 开发公司:广州宏天软件有限公司 开发人员:zxh 创建时间:2015-05-18 17:21:46
 */
public class AtsShiftInfo extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4036721029927225380L;
	// 主键
	protected Long id;
	// 编码
	protected String code;
	// 名称
	protected String name;
	// 状态
	protected Short status;
	// 班次类型
	protected Long shiftType;
	// 加班补偿方式
	protected Long otCompens;
	// 所属组织
	protected Long orgId;
	// 取卡规则
	protected Long cardRule;
	// 标准工时
	protected Double standardHour;
	// 是否默认
	protected Short isDefault = AtsConstant.NO;
	// 描述
	protected String memo;

	protected String detailList;

	protected String shiftTypeName;
	protected String orgName;
	protected String cardRuleName;
	protected String shiftTime;
	
	protected AtsCardRule atsCardRule;
	protected List<AtsShiftTime> shiftTimeList;

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 主键
	 * 
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 返回 编码
	 * 
	 * @return
	 */
	public String getCode() {
		return this.code;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 名称
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * 返回 状态
	 * 
	 * @return
	 */
	public Short getStatus() {
		return this.status;
	}

	public void setShiftType(Long shiftType) {
		this.shiftType = shiftType;
	}

	/**
	 * 返回 班次类型
	 * 
	 * @return
	 */
	public Long getShiftType() {
		return this.shiftType;
	}

	public void setOtCompens(Long otCompens) {
		this.otCompens = otCompens;
	}

	/**
	 * 返回 加班补偿方式
	 * 
	 * @return
	 */
	public Long getOtCompens() {
		return this.otCompens;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/**
	 * 返回 所属组织
	 * 
	 * @return
	 */
	public Long getOrgId() {
		return this.orgId;
	}

	public void setCardRule(Long cardRule) {
		this.cardRule = cardRule;
	}

	/**
	 * 返回 取卡规则
	 * 
	 * @return
	 */
	public Long getCardRule() {
		return this.cardRule;
	}

	public void setStandardHour(Double standardHour) {
		this.standardHour = standardHour;
	}

	/**
	 * 返回 标准工时
	 * 
	 * @return
	 */
	public Double getStandardHour() {
		return this.standardHour;
	}

	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * 返回 是否默认
	 * 
	 * @return
	 */
	public Short getIsDefault() {
		return this.isDefault;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 返回 描述
	 * 
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

	public String getShiftTypeName() {
		return shiftTypeName;
	}

	public void setShiftTypeName(String shiftTypeName) {
		this.shiftTypeName = shiftTypeName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCardRuleName() {
		return cardRuleName;
	}

	public void setCardRuleName(String cardRuleName) {
		this.cardRuleName = cardRuleName;
	}

	public String getShiftTime() {
		return shiftTime;
	}

	public void setShiftTime(String shiftTime) {
		this.shiftTime = shiftTime;
	}

	public List<AtsShiftTime> getShiftTimeList() {
		return shiftTimeList;
	}

	public void setShiftTimeList(List<AtsShiftTime> shiftTimeList) {
		this.shiftTimeList = shiftTimeList;
	}

	public AtsCardRule getAtsCardRule() {
		return atsCardRule;
	}

	public void setAtsCardRule(AtsCardRule atsCardRule) {
		this.atsCardRule = atsCardRule;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AtsShiftInfo)) {
			return false;
		}
		AtsShiftInfo rhs = (AtsShiftInfo) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.code, rhs.code).append(this.name, rhs.name)
				.append(this.status, rhs.status)
				.append(this.shiftType, rhs.shiftType)
				.append(this.otCompens, rhs.otCompens)
				.append(this.orgId, rhs.orgId)
				.append(this.cardRule, rhs.cardRule)
				.append(this.standardHour, rhs.standardHour)
				.append(this.isDefault, rhs.isDefault)
				.append(this.memo, rhs.memo).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id)
				.append(this.code).append(this.name).append(this.status)
				.append(this.shiftType).append(this.otCompens)
				.append(this.orgId).append(this.cardRule)
				.append(this.standardHour).append(this.isDefault)
				.append(this.memo).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("code", this.code).append("name", this.name)
				.append("status", this.status)
				.append("shiftType", this.shiftType)
				.append("otCompens", this.otCompens)
				.append("orgId", this.orgId).append("cardRule", this.cardRule)
				.append("standardHour", this.standardHour)
				.append("isDefault", this.isDefault).append("memo", this.memo)
				.toString();
	}

}