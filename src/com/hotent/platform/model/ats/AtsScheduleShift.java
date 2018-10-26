package com.hotent.platform.model.ats;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * <pre>
 * 对象功能:排班列表 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-25 17:05:06
 * </pre>
 */
public class AtsScheduleShift extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	protected Long id;
	// 考勤用户ID
	protected Long fileId;
	// 考勤日期
	protected Date attenceTime;
	// 日期类型
	protected Short dateType;
	// 班次ID
	protected Long shiftId;
	// 标题
	protected String title;

	// 用户ID
	protected Long userId;
	// 用户名
	protected String userName;
	// 组织名ID
	protected Long orgId;
	// 组织名
	protected String orgName;
	// 班次名称
	protected String shiftName;
	// 考勤卡号
	protected String cardNumber;
	// 取卡规则ID
	protected Long cardRule;
	// 取卡规则
	protected String cardRuleName;
	// 考勤制度ID
	protected Long attencePolicy;
	// 考勤制度
	protected String attencePolicyName;

	protected AtsShiftInfo shiftInfo;

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

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	/**
	 * 返回 考勤用户ID
	 * 
	 * @return
	 */
	public Long getFileId() {
		return this.fileId;
	}

	public void setAttenceTime(Date attenceTime) {
		this.attenceTime = attenceTime;
	}

	/**
	 * 返回 考勤日期
	 * 
	 * @return
	 */
	public Date getAttenceTime() {
		return this.attenceTime;
	}

	public void setDateType(Short dateType) {
		this.dateType = dateType;
	}

	/**
	 * 返回 日期类型
	 * 
	 * @return
	 */
	public Short getDateType() {
		return this.dateType;
	}

	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}

	/**
	 * 返回 班次ID
	 * 
	 * @return
	 */
	public Long getShiftId() {
		return this.shiftId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 返回 标题
	 * 
	 * @return
	 */
	public String getTitle() {
		return this.title;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Long getCardRule() {
		return cardRule;
	}

	public void setCardRule(Long cardRule) {
		this.cardRule = cardRule;
	}

	public String getCardRuleName() {
		return cardRuleName;
	}

	public void setCardRuleName(String cardRuleName) {
		this.cardRuleName = cardRuleName;
	}

	public Long getAttencePolicy() {
		return attencePolicy;
	}

	public void setAttencePolicy(Long attencePolicy) {
		this.attencePolicy = attencePolicy;
	}

	public String getAttencePolicyName() {
		return attencePolicyName;
	}

	public void setAttencePolicyName(String attencePolicyName) {
		this.attencePolicyName = attencePolicyName;
	}

	public AtsShiftInfo getShiftInfo() {
		return shiftInfo;
	}

	public void setShiftInfo(AtsShiftInfo shiftInfo) {
		this.shiftInfo = shiftInfo;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AtsScheduleShift)) {
			return false;
		}
		AtsScheduleShift rhs = (AtsScheduleShift) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.fileId, rhs.fileId)
				.append(this.attenceTime, rhs.attenceTime)
				.append(this.dateType, rhs.dateType)
				.append(this.shiftId, rhs.shiftId)
				.append(this.title, rhs.title).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id)
				.append(this.fileId).append(this.attenceTime)
				.append(this.dateType).append(this.shiftId).append(this.title)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("fileId", this.fileId)
				.append("attenceTime", this.attenceTime)
				.append("dateType", this.dateType)
				.append("shiftId", this.shiftId).append("title", this.title)
				.toString();
	}

}