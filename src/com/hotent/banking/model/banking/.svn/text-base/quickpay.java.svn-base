package com.hotent.banking.model.banking;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:用户ID Model对象
 */
public class quickpay extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *用户名
	 */
	protected String  USERID;
	/**
	 *收款人
	 */
	protected String  PAYEEID;
	/**
	 *日期
	 */
	protected java.util.Date  DATA;
	/**
	 *付款金额
	 */
	protected Double  PAYMENT;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUSERID(String USERID) 
	{
		this.USERID = USERID;
	}
	/**
	 * 返回 用户名
	 * @return
	 */
	public String getUSERID() 
	{
		return this.USERID;
	}
	public void setPAYEEID(String PAYEEID) 
	{
		this.PAYEEID = PAYEEID;
	}
	/**
	 * 返回 收款人
	 * @return
	 */
	public String getPAYEEID() 
	{
		return this.PAYEEID;
	}
	public void setDATA(java.util.Date DATA) 
	{
		this.DATA = DATA;
	}
	/**
	 * 返回 日期
	 * @return
	 */
	public java.util.Date getDATA() 
	{
		return this.DATA;
	}
	public void setPAYMENT(Double PAYMENT) 
	{
		this.PAYMENT = PAYMENT;
	}
	/**
	 * 返回 付款金额
	 * @return
	 */
	public Double getPAYMENT() 
	{
		return this.PAYMENT;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof quickpay)) 
		{
			return false;
		}
		quickpay rhs = (quickpay) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.USERID, rhs.USERID)
		.append(this.PAYEEID, rhs.PAYEEID)
		.append(this.DATA, rhs.DATA)
		.append(this.PAYMENT, rhs.PAYMENT)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.USERID) 
		.append(this.PAYEEID) 
		.append(this.DATA) 
		.append(this.PAYMENT) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("USERID", this.USERID) 
		.append("PAYEEID", this.PAYEEID) 
		.append("DATA", this.DATA) 
		.append("PAYMENT", this.PAYMENT) 
		.toString();
	}

}
