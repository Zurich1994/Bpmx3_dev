package com.hotent.platform.model.ats;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:考勤计算 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-31 13:51:08
 */
public class AtsAttenceCalculate extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2593296754329148605L;
	// 主键
	protected Long id;
	// 考勤档案
	protected Long fileId;
	// 考勤日期
	protected Date attenceTime;
	//是否排班
	protected Short isScheduleShift = AtsConstant.YES;
	//日期类型
	protected Short dateType;
	//节假日名 如果是节假日
	protected String  holidayName;
	//是否有打卡记录
	protected Short isCardRecord;
	//班次时间
	protected String shiftTime;
	// 应出勤时数
	protected Double shouldAttenceHours;
	// 实际出勤时数
	protected Double actualAttenceHours;
	// 有效打卡记录
	protected String cardRecord;
	// 旷工次数
	protected Double absentNumber;
	// 旷工小时数
	protected Double absentTime;
	// 旷工记录
	protected String absentRecord;
	// 迟到次数
	protected Double lateNumber;
	// 迟到分钟数
	protected Double lateTime;
	// 迟到记录
	protected String lateRecord;
	// 早退次数
	protected Double leaveNumber;
	// 早退分钟数
	protected Double leaveTime;
	// 早退记录
	protected String leaveRecord;
	// 加班次数
	protected Double otNumber;
	// 加班分钟数
	protected Double otTime;
	// 加班记录
	protected String otRecord;
	// 请假次数
	protected Double holidayNumber;
	// 请假分钟数
	protected Double holidayTime;
	// 请假时间单位
	protected Short holidayUnit;
	// 请假记录
	protected String holidayRecord;
	// 出差次数
	protected Double tripNumber;
	// 出差分钟数
	protected Double tripTime;
	// 出差记录
	protected String tripRecord;

	
	protected String userName;
	protected Long orgId;
	protected String orgName;
	protected String account;
	
	protected String shiftTime1;
	protected String shiftTime2;
	protected String shiftTime3;
	
	
	protected String absentRecord1;
	protected String absentRecord2;
	protected String absentRecord3;
	
	protected String unit;
	
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
	public void setAttenceTime(Date attenceTime){
		this.attenceTime = attenceTime;
	}
	/**
	 * 返回 考勤日期
	 * @return
	 */
	public Date getAttenceTime() {
		return this.attenceTime;
	}
	public void setShouldAttenceHours(Double shouldAttenceHours){
		this.shouldAttenceHours = shouldAttenceHours;
	}
	/**
	 * 返回 应出勤时数
	 * @return
	 */
	public Double getShouldAttenceHours() {
		return this.shouldAttenceHours;
	}
	public void setActualAttenceHours(Double actualAttenceHours){
		this.actualAttenceHours = actualAttenceHours;
	}
	/**
	 * 返回 实际出勤时数
	 * @return
	 */
	public Double getActualAttenceHours() {
		return this.actualAttenceHours;
	}
	public void setCardRecord(String cardRecord){
		this.cardRecord = cardRecord;
	}
	/**
	 * 返回 有效打卡记录
	 * @return
	 */
	public String getCardRecord() {
		return this.cardRecord;
	}
	public void setAbsentNumber(Double absentNumber){
		this.absentNumber = absentNumber;
	}
	/**
	 * 返回 旷工次数
	 * @return
	 */
	public Double getAbsentNumber() {
		return this.absentNumber;
	}
	public void setAbsentTime(Double absentTime){
		this.absentTime = absentTime;
	}
	/**
	 * 返回 旷工小时数
	 * @return
	 */
	public Double getAbsentTime() {
		return this.absentTime;
	}
	public void setAbsentRecord(String absentRecord){
		this.absentRecord = absentRecord;
	}
	/**
	 * 返回 旷工记录
	 * @return
	 */
	public String getAbsentRecord() {
		return this.absentRecord;
	}
	public void setLateNumber(Double lateNumber){
		this.lateNumber = lateNumber;
	}
	/**
	 * 返回 迟到次数
	 * @return
	 */
	public Double getLateNumber() {
		return this.lateNumber;
	}
	public void setLateTime(Double lateTime){
		this.lateTime = lateTime;
	}
	/**
	 * 返回 迟到分钟数
	 * @return
	 */
	public Double getLateTime() {
		return this.lateTime;
	}
	public void setLateRecord(String lateRecord){
		this.lateRecord = lateRecord;
	}
	/**
	 * 返回 迟到记录
	 * @return
	 */
	public String getLateRecord() {
		return this.lateRecord;
	}
	public void setLeaveNumber(Double leaveNumber){
		this.leaveNumber = leaveNumber;
	}
	/**
	 * 返回 早退次数
	 * @return
	 */
	public Double getLeaveNumber() {
		return this.leaveNumber;
	}
	public void setLeaveTime(Double leaveTime){
		this.leaveTime = leaveTime;
	}
	/**
	 * 返回 早退分钟数
	 * @return
	 */
	public Double getLeaveTime() {
		return this.leaveTime;
	}
	public void setLeaveRecord(String leaveRecord){
		this.leaveRecord = leaveRecord;
	}
	/**
	 * 返回 早退记录
	 * @return
	 */
	public String getLeaveRecord() {
		return this.leaveRecord;
	}
	public void setOtNumber(Double otNumber){
		this.otNumber = otNumber;
	}
	/**
	 * 返回 加班次数
	 * @return
	 */
	public Double getOtNumber() {
		return this.otNumber;
	}
	public void setOtTime(Double otTime){
		this.otTime = otTime;
	}
	/**
	 * 返回 加班分钟数
	 * @return
	 */
	public Double getOtTime() {
		return this.otTime;
	}
	public void setOtRecord(String otRecord){
		this.otRecord = otRecord;
	}
	/**
	 * 返回 加班记录
	 * @return
	 */
	public String getOtRecord() {
		return this.otRecord;
	}
	public void setHolidayNumber(Double holidayNumber){
		this.holidayNumber = holidayNumber;
	}
	/**
	 * 返回 请假次数
	 * @return
	 */
	public Double getHolidayNumber() {
		return this.holidayNumber;
	}
	public void setHolidayTime(Double holidayTime){
		this.holidayTime = holidayTime;
	}
	/**
	 * 返回 请假分钟数
	 * @return
	 */
	public Double getHolidayTime() {
		return this.holidayTime;
	}
	public void setHolidayUnit(Short holidayUnit){
		this.holidayUnit = holidayUnit;
	}
	/**
	 * 返回 请假时间单位
	 * @return
	 */
	public Short getHolidayUnit() {
		return this.holidayUnit;
	}
	public void setHolidayRecord(String holidayRecord){
		this.holidayRecord = holidayRecord;
	}
	/**
	 * 返回 请假记录
	 * @return
	 */
	public String getHolidayRecord() {
		return this.holidayRecord;
	}
	public void setTripNumber(Double tripNumber){
		this.tripNumber = tripNumber;
	}
	/**
	 * 返回 出差次数
	 * @return
	 */
	public Double getTripNumber() {
		return this.tripNumber;
	}
	public void setTripTime(Double tripTime){
		this.tripTime = tripTime;
	}
	/**
	 * 返回 出差分钟数
	 * @return
	 */
	public Double getTripTime() {
		return this.tripTime;
	}
	public void setTripRecord(String tripRecord){
		this.tripRecord = tripRecord;
	}
	/**
	 * 返回 出差记录
	 * @return
	 */
	public String getTripRecord() {
		return this.tripRecord;
	}
   	public Short getIsScheduleShift() {
		return isScheduleShift;
	}
	public void setIsScheduleShift(Short isScheduleShift) {
		this.isScheduleShift = isScheduleShift;
	}
	public Short getDateType() {
		return dateType;
	}
	public void setDateType(Short dateType) {
		this.dateType = dateType;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	
	public Short getIsCardRecord() {
		return isCardRecord;
	}
	public void setIsCardRecord(Short isCardRecord) {
		this.isCardRecord = isCardRecord;
	}
	
	public String getShiftTime() {
		return shiftTime;
	}
	public void setShiftTime(String shiftTime) {
		this.shiftTime = shiftTime;
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
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getShiftTime1() {
		return shiftTime1;
	}
	public void setShiftTime1(String shiftTime1) {
		this.shiftTime1 = shiftTime1;
	}
	public String getShiftTime2() {
		return shiftTime2;
	}
	public void setShiftTime2(String shiftTime2) {
		this.shiftTime2 = shiftTime2;
	}
	public String getShiftTime3() {
		return shiftTime3;
	}
	public void setShiftTime3(String shiftTime3) {
		this.shiftTime3 = shiftTime3;
	}
	public String getAbsentRecord1() {
		return absentRecord1;
	}
	public void setAbsentRecord1(String absentRecord1) {
		this.absentRecord1 = absentRecord1;
	}
	public String getAbsentRecord2() {
		return absentRecord2;
	}
	public void setAbsentRecord2(String absentRecord2) {
		this.absentRecord2 = absentRecord2;
	}
	public String getAbsentRecord3() {
		return absentRecord3;
	}
	public void setAbsentRecord3(String absentRecord3) {
		this.absentRecord3 = absentRecord3;
	}
	
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AtsAttenceCalculate)) 
		{
			return false;
		}
		AtsAttenceCalculate rhs = (AtsAttenceCalculate) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.fileId, rhs.fileId)
		.append(this.attenceTime, rhs.attenceTime)
		.append(this.shouldAttenceHours, rhs.shouldAttenceHours)
		.append(this.actualAttenceHours, rhs.actualAttenceHours)
		.append(this.cardRecord, rhs.cardRecord)
		.append(this.absentNumber, rhs.absentNumber)
		.append(this.absentTime, rhs.absentTime)
		.append(this.absentRecord, rhs.absentRecord)
		.append(this.lateNumber, rhs.lateNumber)
		.append(this.lateTime, rhs.lateTime)
		.append(this.lateRecord, rhs.lateRecord)
		.append(this.leaveNumber, rhs.leaveNumber)
		.append(this.leaveTime, rhs.leaveTime)
		.append(this.leaveRecord, rhs.leaveRecord)
		.append(this.otNumber, rhs.otNumber)
		.append(this.otTime, rhs.otTime)
		.append(this.otRecord, rhs.otRecord)
		.append(this.holidayNumber, rhs.holidayNumber)
		.append(this.holidayTime, rhs.holidayTime)
		.append(this.holidayUnit, rhs.holidayUnit)
		.append(this.holidayRecord, rhs.holidayRecord)
		.append(this.tripNumber, rhs.tripNumber)
		.append(this.tripTime, rhs.tripTime)
		.append(this.tripRecord, rhs.tripRecord)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.fileId) 
		.append(this.attenceTime) 
		.append(this.shouldAttenceHours) 
		.append(this.actualAttenceHours) 
		.append(this.cardRecord) 
		.append(this.absentNumber) 
		.append(this.absentTime) 
		.append(this.absentRecord) 
		.append(this.lateNumber) 
		.append(this.lateTime) 
		.append(this.lateRecord) 
		.append(this.leaveNumber) 
		.append(this.leaveTime) 
		.append(this.leaveRecord) 
		.append(this.otNumber) 
		.append(this.otTime) 
		.append(this.otRecord) 
		.append(this.holidayNumber) 
		.append(this.holidayTime) 
		.append(this.holidayUnit) 
		.append(this.holidayRecord) 
		.append(this.tripNumber) 
		.append(this.tripTime) 
		.append(this.tripRecord) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("fileId", this.fileId) 
		.append("attenceTime", this.attenceTime) 
		.append("shouldAttenceHours", this.shouldAttenceHours) 
		.append("actualAttenceHours", this.actualAttenceHours) 
		.append("cardRecord", this.cardRecord) 
		.append("absentNumber", this.absentNumber) 
		.append("absentTime", this.absentTime) 
		.append("absentRecord", this.absentRecord) 
		.append("lateNumber", this.lateNumber) 
		.append("lateTime", this.lateTime) 
		.append("lateRecord", this.lateRecord) 
		.append("leaveNumber", this.leaveNumber) 
		.append("leaveTime", this.leaveTime) 
		.append("leaveRecord", this.leaveRecord) 
		.append("otNumber", this.otNumber) 
		.append("otTime", this.otTime) 
		.append("otRecord", this.otRecord) 
		.append("holidayNumber", this.holidayNumber) 
		.append("holidayTime", this.holidayTime) 
		.append("holidayUnit", this.holidayUnit) 
		.append("holidayRecord", this.holidayRecord) 
		.append("tripNumber", this.tripNumber) 
		.append("tripTime", this.tripTime) 
		.append("tripRecord", this.tripRecord) 
		.toString();
	}
  
}