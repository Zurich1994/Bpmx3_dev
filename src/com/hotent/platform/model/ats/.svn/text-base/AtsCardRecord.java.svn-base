package com.hotent.platform.model.ats;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * <pre>
 * 对象功能:打卡记录 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-26 11:21:21
 * </pre>
 */
public class AtsCardRecord extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8113261282991169653L;
	// 主键
	protected Long id;
	// 考勤卡号
	protected String cardNumber;
	// 打卡日期
	protected Date cardDate;
	// 打卡来源
	protected Short cardSource = AtsConstant.CARD_SOURCE_UNKNOWN;
	// 打卡位置
	protected String cardPlace;

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

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * 返回 考勤卡号
	 * 
	 * @return
	 */
	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardDate(Date cardDate) {
		this.cardDate = cardDate;
	}

	/**
	 * 返回 打卡日期
	 * 
	 * @return
	 */
	public Date getCardDate() {
		return this.cardDate;
	}

	public void setCardSource(Short cardSource) {
		this.cardSource = cardSource;
	}

	/**
	 * 返回 打卡来源
	 * 
	 * @return
	 */
	public Short getCardSource() {
		return this.cardSource;
	}

	public void setCardPlace(String cardPlace) {
		this.cardPlace = cardPlace;
	}

	/**
	 * 返回 打卡位置
	 * 
	 * @return
	 */
	public String getCardPlace() {
		return this.cardPlace;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AtsCardRecord)) {
			return false;
		}
		AtsCardRecord rhs = (AtsCardRecord) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.cardNumber, rhs.cardNumber)
				.append(this.cardDate, rhs.cardDate)
				.append(this.cardSource, rhs.cardSource)
				.append(this.cardPlace, rhs.cardPlace).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id)
				.append(this.cardNumber).append(this.cardDate)
				.append(this.cardSource).append(this.cardPlace).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("cardNumber", this.cardNumber)
				.append("cardDate", this.cardDate)
				.append("cardSource", this.cardSource)
				.append("cardPlace", this.cardPlace).toString();
	}

}