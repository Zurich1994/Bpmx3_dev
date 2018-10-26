package com.hotent.platform.model.ats;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * 对象功能:假期制度明细 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2015-05-28 14:25:03
 */
public class AtsHolidayPolicyDetail extends BaseModel{
	// 主键
	protected Long id;
	// 假期制度ID
	protected Long holidayId;
	// 假期类型
	protected Long holidayType;
	// 假期单位
	protected Short holidayUnit;
	// 启动周期
	protected Short enablePeriod;
	// 周期长度
	protected Double periodLength;
	// 周期单位
	protected Short periodUnit;
	// 控制单位额度
	protected Short enableMinAmt;
	// 单位额度
	protected Double minAmt;
	// 是否允许补请假
	protected Short isFillHoliday;
	// 补请假期限
	protected Double fillHoliday;
	// 补请假期限单位
	protected Short fillHolidayUnit;
	// 是否允许销假
	protected Short isCancelLeave;
	// 销假期限
	protected Double cancelLeave;
	// 销假期限单位
	protected Short cancelLeaveUnit;
	// 是否控制假期额度
	protected Short isCtrlLimit;
	// 假期额度规则
	protected Long holidayRule;
	// 是否允许超额请假
	protected Short isOver;
	// 超出额度下期扣减
	protected Short isOverAutoSub;
	// 是否允许修改额度
	protected Short isCanModifyLimit;
	// 包括公休日
	protected Short isIncludeRest;
	// 包括法定假日
	protected Short isIncludeLegal;
	// 描述
	protected String memo;

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AtsHolidayPolicyDetail)) 
		{
			return false;
		}
		AtsHolidayPolicyDetail rhs = (AtsHolidayPolicyDetail) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.holidayId, rhs.holidayId)
		.append(this.holidayType, rhs.holidayType)
		.append(this.holidayUnit, rhs.holidayUnit)
		.append(this.enablePeriod, rhs.enablePeriod)
		.append(this.periodLength, rhs.periodLength)
		.append(this.periodUnit, rhs.periodUnit)
		.append(this.enableMinAmt, rhs.enableMinAmt)
		.append(this.minAmt, rhs.minAmt)
		.append(this.isFillHoliday, rhs.isFillHoliday)
		.append(this.fillHoliday, rhs.fillHoliday)
		.append(this.fillHolidayUnit, rhs.fillHolidayUnit)
		.append(this.isCancelLeave, rhs.isCancelLeave)
		.append(this.cancelLeave, rhs.cancelLeave)
		.append(this.cancelLeaveUnit, rhs.cancelLeaveUnit)
		.append(this.isCtrlLimit, rhs.isCtrlLimit)
		.append(this.holidayRule, rhs.holidayRule)
		.append(this.isOver, rhs.isOver)
		.append(this.isOverAutoSub, rhs.isOverAutoSub)
		.append(this.isCanModifyLimit, rhs.isCanModifyLimit)
		.append(this.isIncludeRest, rhs.isIncludeRest)
		.append(this.isIncludeLegal, rhs.isIncludeLegal)
		.append(this.memo, rhs.memo)
		.isEquals();
	}
	/**
	 * 返回 销假期限
	 * @return
	 */
	public Double getCancelLeave() {
		return this.cancelLeave;
	}
	/**
	 * 返回 销假期限单位
	 * @return
	 */
	public Short getCancelLeaveUnit() {
		return this.cancelLeaveUnit;
	}
	/**
	 * 返回 控制单位额度
	 * @return
	 */
	public Short getEnableMinAmt() {
		return this.enableMinAmt;
	}
	/**
	 * 返回 启动周期
	 * @return
	 */
	public Short getEnablePeriod() {
		return this.enablePeriod;
	}
	/**
	 * 返回 补请假期限
	 * @return
	 */
	public Double getFillHoliday() {
		return this.fillHoliday;
	}
	/**
	 * 返回 补请假期限单位
	 * @return
	 */
	public Short getFillHolidayUnit() {
		return this.fillHolidayUnit;
	}
	/**
	 * 返回 假期制度ID
	 * @return
	 */
	public Long getHolidayId() {
		return this.holidayId;
	}
	/**
	 * 返回 假期额度规则
	 * @return
	 */
	public Long getHolidayRule() {
		return this.holidayRule;
	}
	/**
	 * 返回 假期类型
	 * @return
	 */
	public Long getHolidayType() {
		return this.holidayType;
	}
	/**
	 * 返回 假期单位
	 * @return
	 */
	public Short getHolidayUnit() {
		return this.holidayUnit;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	/**
	 * 返回 是否允许销假
	 * @return
	 */
	public Short getIsCancelLeave() {
		return this.isCancelLeave;
	}
	/**
	 * 返回 是否允许修改额度
	 * @return
	 */
	public Short getIsCanModifyLimit() {
		return this.isCanModifyLimit;
	}
	/**
	 * 返回 是否控制假期额度
	 * @return
	 */
	public Short getIsCtrlLimit() {
		return this.isCtrlLimit;
	}
	/**
	 * 返回 是否允许补请假
	 * @return
	 */
	public Short getIsFillHoliday() {
		return this.isFillHoliday;
	}
	/**
	 * 返回 包括法定假日
	 * @return
	 */
	public Short getIsIncludeLegal() {
		return this.isIncludeLegal;
	}
	/**
	 * 返回 包括公休日
	 * @return
	 */
	public Short getIsIncludeRest() {
		return this.isIncludeRest;
	}
	/**
	 * 返回 是否允许超额请假
	 * @return
	 */
	public Short getIsOver() {
		return this.isOver;
	}
	/**
	 * 返回 超出额度下期扣减
	 * @return
	 */
	public Short getIsOverAutoSub() {
		return this.isOverAutoSub;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}
	/**
	 * 返回 单位额度
	 * @return
	 */
	public Double getMinAmt() {
		return this.minAmt;
	}
	/**
	 * 返回 周期长度
	 * @return
	 */
	public Double getPeriodLength() {
		return this.periodLength;
	}
	/**
	 * 返回 周期单位
	 * @return
	 */
	public Short getPeriodUnit() {
		return this.periodUnit;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.holidayId) 
		.append(this.holidayType) 
		.append(this.holidayUnit) 
		.append(this.enablePeriod) 
		.append(this.periodLength) 
		.append(this.periodUnit) 
		.append(this.enableMinAmt) 
		.append(this.minAmt) 
		.append(this.isFillHoliday) 
		.append(this.fillHoliday) 
		.append(this.fillHolidayUnit) 
		.append(this.isCancelLeave) 
		.append(this.cancelLeave) 
		.append(this.cancelLeaveUnit) 
		.append(this.isCtrlLimit) 
		.append(this.holidayRule) 
		.append(this.isOver) 
		.append(this.isOverAutoSub) 
		.append(this.isCanModifyLimit) 
		.append(this.isIncludeRest) 
		.append(this.isIncludeLegal) 
		.append(this.memo) 
		.toHashCode();
	}
	public void setCancelLeave(Double cancelLeave){
		this.cancelLeave = cancelLeave;
	}
	public void setCancelLeaveUnit(Short cancelLeaveUnit){
		this.cancelLeaveUnit = cancelLeaveUnit;
	}
	public void setEnableMinAmt(Short enableMinAmt){
		this.enableMinAmt = enableMinAmt;
	}
	public void setEnablePeriod(Short enablePeriod){
		this.enablePeriod = enablePeriod;
	}
	public void setFillHoliday(Double fillHoliday){
		this.fillHoliday = fillHoliday;
	}
	public void setFillHolidayUnit(Short fillHolidayUnit){
		this.fillHolidayUnit = fillHolidayUnit;
	}
	public void setHolidayId(Long holidayId){
		this.holidayId = holidayId;
	}
	public void setHolidayRule(Long holidayRule){
		this.holidayRule = holidayRule;
	}
	public void setHolidayType(Long holidayType){
		this.holidayType = holidayType;
	}
	public void setHolidayUnit(Short holidayUnit){
		this.holidayUnit = holidayUnit;
	}
	public void setId(Long id){
		this.id = id;
	}
	public void setIsCancelLeave(Short isCancelLeave){
		this.isCancelLeave = isCancelLeave;
	}
	public void setIsCanModifyLimit(Short isCanModifyLimit){
		this.isCanModifyLimit = isCanModifyLimit;
	}
	public void setIsCtrlLimit(Short isCtrlLimit){
		this.isCtrlLimit = isCtrlLimit;
	}
	public void setIsFillHoliday(Short isFillHoliday){
		this.isFillHoliday = isFillHoliday;
	}
	public void setIsIncludeLegal(Short isIncludeLegal){
		this.isIncludeLegal = isIncludeLegal;
	}
	public void setIsIncludeRest(Short isIncludeRest){
		this.isIncludeRest = isIncludeRest;
	}
	public void setIsOver(Short isOver){
		this.isOver = isOver;
	}
	public void setIsOverAutoSub(Short isOverAutoSub){
		this.isOverAutoSub = isOverAutoSub;
	}
	public void setMemo(String memo){
		this.memo = memo;
	}
	public void setMinAmt(Double minAmt){
		this.minAmt = minAmt;
	}
	

   	public void setPeriodLength(Double periodLength){
		this.periodLength = periodLength;
	}

	public void setPeriodUnit(Short periodUnit){
		this.periodUnit = periodUnit;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("holidayId", this.holidayId) 
		.append("holidayType", this.holidayType) 
		.append("holidayUnit", this.holidayUnit) 
		.append("enablePeriod", this.enablePeriod) 
		.append("periodLength", this.periodLength) 
		.append("periodUnit", this.periodUnit) 
		.append("enableMinAmt", this.enableMinAmt) 
		.append("minAmt", this.minAmt) 
		.append("isFillHoliday", this.isFillHoliday) 
		.append("fillHoliday", this.fillHoliday) 
		.append("fillHolidayUnit", this.fillHolidayUnit) 
		.append("isCancelLeave", this.isCancelLeave) 
		.append("cancelLeave", this.cancelLeave) 
		.append("cancelLeaveUnit", this.cancelLeaveUnit) 
		.append("isCtrlLimit", this.isCtrlLimit) 
		.append("holidayRule", this.holidayRule) 
		.append("isOver", this.isOver) 
		.append("isOverAutoSub", this.isOverAutoSub) 
		.append("isCanModifyLimit", this.isCanModifyLimit) 
		.append("isIncludeRest", this.isIncludeRest) 
		.append("isIncludeLegal", this.isIncludeLegal) 
		.append("memo", this.memo) 
		.toString();
	}
   
  

}