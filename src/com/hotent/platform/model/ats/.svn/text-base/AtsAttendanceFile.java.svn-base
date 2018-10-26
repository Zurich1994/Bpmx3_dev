package com.hotent.platform.model.ats;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * <pre>
 * 对象功能:考勤档案 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-20 09:11:05
 * </pre>
 */
public class AtsAttendanceFile extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2556494386354760645L;
	// 主键
	protected Long id;
	// 用户
	protected Long userId;
	// 考勤卡号
	protected String cardNumber;
	// 是否参与考勤
	protected Short isAttendance = AtsConstant.YES;
	// 考勤制度
	protected Long attencePolicy;
	// 假期制度
	protected Long holidayPolicy;
	// 默认班次
	protected Long defaultShift;
	//状态
	protected Short status  = AtsConstant.YES;
	
	//=======非数据库字段
	//用户名
	protected String userName;
	protected String account;
	protected String orgName;
	// 考勤制度
	protected String attencePolicyName;
	// 假期制度
	protected String holidayPolicyName;
	// 默认班次
	protected String defaultShiftName;

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
	public void setUserId(Long userId){
		this.userId = userId;
	}
	/**
	 * 返回 用户
	 * @return
	 */
	public Long getUserId() {
		return this.userId;
	}
	public void setCardNumber(String cardNumber){
		this.cardNumber = cardNumber;
	}
	/**
	 * 返回 考勤卡号
	 * @return
	 */
	public String getCardNumber() {
		return this.cardNumber;
	}
	public void setIsAttendance(Short isAttendance){
		this.isAttendance = isAttendance;
	}
	/**
	 * 返回 是否参与考勤
	 * @return
	 */
	public Short getIsAttendance() {
		return this.isAttendance;
	}
	public void setAttencePolicy(Long attencePolicy){
		this.attencePolicy = attencePolicy;
	}
	/**
	 * 返回 考勤制度
	 * @return
	 */
	public Long getAttencePolicy() {
		return this.attencePolicy;
	}
	public void setHolidayPolicy(Long holidayPolicy){
		this.holidayPolicy = holidayPolicy;
	}
	/**
	 * 返回 假期制度
	 * @return
	 */
	public Long getHolidayPolicy() {
		return this.holidayPolicy;
	}
	public void setDefaultShift(Long defaultShift){
		this.defaultShift = defaultShift;
	}
	/**
	 * 返回 默认班次
	 * @return
	 */
	public Long getDefaultShift() {
		return this.defaultShift;
	}
   	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAttencePolicyName() {
		return attencePolicyName;
	}
	public void setAttencePolicyName(String attencePolicyName) {
		this.attencePolicyName = attencePolicyName;
	}
	public String getHolidayPolicyName() {
		return holidayPolicyName;
	}
	public void setHolidayPolicyName(String holidayPolicyName) {
		this.holidayPolicyName = holidayPolicyName;
	}
	public String getDefaultShiftName() {
		return defaultShiftName;
	}
	public void setDefaultShiftName(String defaultShiftName) {
		this.defaultShiftName = defaultShiftName;
	}
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AtsAttendanceFile)) 
		{
			return false;
		}
		AtsAttendanceFile rhs = (AtsAttendanceFile) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.userId, rhs.userId)
		.append(this.cardNumber, rhs.cardNumber)
		.append(this.isAttendance, rhs.isAttendance)
		.append(this.attencePolicy, rhs.attencePolicy)
		.append(this.holidayPolicy, rhs.holidayPolicy)
		.append(this.defaultShift, rhs.defaultShift)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.userId) 
		.append(this.cardNumber) 
		.append(this.isAttendance) 
		.append(this.attencePolicy) 
		.append(this.holidayPolicy) 
		.append(this.defaultShift) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("userId", this.userId) 
		.append("cardNumber", this.cardNumber) 
		.append("isAttendance", this.isAttendance) 
		.append("attencePolicy", this.attencePolicy) 
		.append("holidayPolicy", this.holidayPolicy) 
		.append("defaultShift", this.defaultShift) 
		.toString();
	}
   
  

}