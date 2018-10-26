package com.hotent.platform.model.ats;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * 对象功能:工作日历 Model对象 开发公司:广州宏天软件有限公司 开发人员:zxh 创建时间:2015-05-17 15:46:19
 */
public class AtsWorkCalendar extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2487345327663459455L;
	// 主键
	protected Long id;
	// 编码
	protected String code;
	// 名称
	protected String name;
	// 开始时间
	protected Date startTime;
	// 结束时间
	protected Date endTime;
	// 日历模版
	protected Long calendarTempl;
	// 法定假日
	protected Long legalHoliday;
	// 是否系统预置
	protected Short isDefault = AtsConstant.NO;
	// 描述
	protected String memo;

	protected String calendarTemplName;

	protected String legalHolidayName;

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

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 返回 开始时间
	 * 
	 * @return
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 返回 结束时间
	 * 
	 * @return
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	public void setCalendarTempl(Long calendarTempl) {
		this.calendarTempl = calendarTempl;
	}

	/**
	 * 返回 日历模版
	 * 
	 * @return
	 */
	public Long getCalendarTempl() {
		return this.calendarTempl;
	}

	public void setLegalHoliday(Long legalHoliday) {
		this.legalHoliday = legalHoliday;
	}

	/**
	 * 返回 日历模版
	 * 
	 * @return
	 */
	public Long getLegalHoliday() {
		return this.legalHoliday;
	}

	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * 返回 是否系统预置
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

	public String getCalendarTemplName() {
		return calendarTemplName;
	}

	public void setCalendarTemplName(String calendarTemplName) {
		this.calendarTemplName = calendarTemplName;
	}

	public String getLegalHolidayName() {
		return legalHolidayName;
	}

	public void setLegalHolidayName(String legalHolidayName) {
		this.legalHolidayName = legalHolidayName;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AtsWorkCalendar)) {
			return false;
		}
		AtsWorkCalendar rhs = (AtsWorkCalendar) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.code, rhs.code).append(this.name, rhs.name)
				.append(this.startTime, rhs.startTime)
				.append(this.endTime, rhs.endTime)
				.append(this.calendarTempl, rhs.calendarTempl)
				.append(this.legalHoliday, rhs.legalHoliday)
				.append(this.isDefault, rhs.isDefault)
				.append(this.memo, rhs.memo).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id)
				.append(this.code).append(this.name).append(this.startTime)
				.append(this.endTime).append(this.calendarTempl)
				.append(this.legalHoliday).append(this.isDefault)
				.append(this.memo).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("code", this.code).append("name", this.name)
				.append("startTime", this.startTime)
				.append("endTime", this.endTime)
				.append("calendarTempl", this.calendarTempl)
				.append("legalHoliday", this.legalHoliday)
				.append("isDefault", this.isDefault).append("memo", this.memo)
				.toString();
	}

}