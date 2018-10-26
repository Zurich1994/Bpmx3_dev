package com.hotent.platform.model.ats;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * 对象功能:班次时间设置 Model对象 开发公司:广州宏天软件有限公司 开发人员:zxh 创建时间:2015-05-19 14:05:03
 */
public class AtsShiftTime extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5404557715527059917L;
	// 主键
	protected Long id;
	// 班次ID
	protected Long shiftId;
	// 段次
	protected Short segment;
	// 出勤类型
	protected Short attendanceType;
	// 上班时间
	protected Date onTime;
	// 上班是否打卡
	protected Short onPunchCard;
	// 上班浮动调整值（分）
	protected Double onFloatAdjust;
	// 段内休息时间
	protected Double segmentRest;
	// 下班时间
	protected Date offTime;
	// 下班是否打卡
	protected Short offPunchCard;
	// 下班浮动调整值（分）
	protected Double offFloatAdjust;

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

	public void setSegment(Short segment) {
		this.segment = segment;
	}

	/**
	 * 返回 段次
	 * 
	 * @return
	 */
	public Short getSegment() {
		return this.segment;
	}

	public void setAttendanceType(Short attendanceType) {
		this.attendanceType = attendanceType;
	}

	/**
	 * 返回 出勤类型
	 * 
	 * @return
	 */
	public Short getAttendanceType() {
		return this.attendanceType;
	}

	public void setOnTime(Date onTime) {
		this.onTime = onTime;
	}

	/**
	 * 返回 上班时间
	 * 
	 * @return
	 */
	public Date getOnTime() {
		return this.onTime;
	}

	public void setOnPunchCard(Short onPunchCard) {
		this.onPunchCard = onPunchCard;
	}

	/**
	 * 返回 上班是否打卡
	 * 
	 * @return
	 */
	public Short getOnPunchCard() {
		return this.onPunchCard;
	}

	public void setOnFloatAdjust(Double onFloatAdjust) {
		this.onFloatAdjust = onFloatAdjust;
	}

	/**
	 * 返回 上班浮动调整值（分）
	 * 
	 * @return
	 */
	public Double getOnFloatAdjust() {
		return this.onFloatAdjust;
	}

	public void setSegmentRest(Double segmentRest) {
		this.segmentRest = segmentRest;
	}

	/**
	 * 返回 段内休息时间
	 * 
	 * @return
	 */
	public Double getSegmentRest() {
		return this.segmentRest;
	}

	public void setOffTime(Date offTime) {
		this.offTime = offTime;
	}

	/**
	 * 返回 下班时间
	 * 
	 * @return
	 */
	public Date getOffTime() {
		return this.offTime;
	}

	public void setOffPunchCard(Short offPunchCard) {
		this.offPunchCard = offPunchCard;
	}

	/**
	 * 返回 下班是否打卡
	 * 
	 * @return
	 */
	public Short getOffPunchCard() {
		return this.offPunchCard;
	}

	public void setOffFloatAdjust(Double offFloatAdjust) {
		this.offFloatAdjust = offFloatAdjust;
	}

	/**
	 * 返回 下班浮动调整值（分）
	 * 
	 * @return
	 */
	public Double getOffFloatAdjust() {
		return this.offFloatAdjust;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AtsShiftTime)) {
			return false;
		}
		AtsShiftTime rhs = (AtsShiftTime) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.shiftId, rhs.shiftId)
				.append(this.segment, rhs.segment)
				.append(this.attendanceType, rhs.attendanceType)
				.append(this.onTime, rhs.onTime)
				.append(this.onPunchCard, rhs.onPunchCard)
				.append(this.onFloatAdjust, rhs.onFloatAdjust)
				.append(this.segmentRest, rhs.segmentRest)
				.append(this.offTime, rhs.offTime)
				.append(this.offPunchCard, rhs.offPunchCard)
				.append(this.offFloatAdjust, rhs.offFloatAdjust).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id)
				.append(this.shiftId).append(this.segment)
				.append(this.attendanceType).append(this.onTime)
				.append(this.onPunchCard).append(this.onFloatAdjust)
				.append(this.segmentRest).append(this.offTime)
				.append(this.offPunchCard).append(this.offFloatAdjust)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("shiftId", this.shiftId)
				.append("segment", this.segment)
				.append("attendanceType", this.attendanceType)
				.append("onTime", this.onTime)
				.append("onPunchCard", this.onPunchCard)
				.append("onFloatAdjust", this.onFloatAdjust)
				.append("segmentRest", this.segmentRest)
				.append("offTime", this.offTime)
				.append("offPunchCard", this.offPunchCard)
				.append("offFloatAdjust", this.offFloatAdjust).toString();
	}

}