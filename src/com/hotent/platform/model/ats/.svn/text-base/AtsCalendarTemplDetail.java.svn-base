package com.hotent.platform.model.ats;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * 对象功能:日历模版明细 Model对象 开发公司:广州宏天软件有限公司 开发人员:zxh 创建时间:2015-05-17 15:45:31
 */
public class AtsCalendarTemplDetail extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6780348815964430074L;
	// 主键
	protected Long id;
	// 日历模版
	protected Long calendarId;
	// 星期
	protected Short week;
	// 日期类型
	protected Short dayType;
	// 描述
	protected String memo;

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

	public void setCalendarId(Long calendarId) {
		this.calendarId = calendarId;
	}

	/**
	 * 返回 日历模版
	 * 
	 * @return
	 */
	public Long getCalendarId() {
		return this.calendarId;
	}

	public void setWeek(Short week) {
		this.week = week;
	}

	/**
	 * 返回 星期
	 * 
	 * @return
	 */
	public Short getWeek() {
		return this.week;
	}

	public void setDayType(Short dayType) {
		this.dayType = dayType;
	}

	/**
	 * 返回 日期类型
	 * 
	 * @return
	 */
	public Short getDayType() {
		return this.dayType;
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

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AtsCalendarTemplDetail)) {
			return false;
		}
		AtsCalendarTemplDetail rhs = (AtsCalendarTemplDetail) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.calendarId, rhs.calendarId)
				.append(this.week, rhs.week).append(this.dayType, rhs.dayType)
				.append(this.memo, rhs.memo).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id)
				.append(this.calendarId).append(this.week).append(this.dayType)
				.append(this.memo).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("calendarId", this.calendarId)
				.append("week", this.week).append("dayType", this.dayType)
				.append("memo", this.memo).toString();
	}

}