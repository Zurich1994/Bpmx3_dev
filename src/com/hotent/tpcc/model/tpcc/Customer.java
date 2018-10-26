package com.hotent.tpcc.model.tpcc;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:customer Model对象
 */
public class Customer extends BaseModel
{
	//主键
	protected Long c_id;
	/**
	 *c_d_id
	 */
	protected Long  c_d_id;
	/**
	 *c_w_id
	 */
	protected Long  c_w_id;
	/**
	 *c_first
	 */
	protected String  c_first;
	/**
	 *c_middle
	 */
	protected String  c_middle;
	/**
	 *c_last
	 */
	protected String  c_last;
	/**
	 *c_street_1
	 */
	protected String  c_street_1;
	/**
	 *c_street_2
	 */
	protected String  c_street_2;
	/**
	 *c_city
	 */
	protected String  c_city;
	/**
	 *c_state
	 */
	protected String  c_state;
	/**
	 *c_zip
	 */
	protected String  c_zip;
	/**
	 *c_phone
	 */
	protected String  c_phone;
	/**
	 *c_since
	 */
	protected java.util.Date  c_since;
	/**
	 *c_credit
	 */
	protected String  c_credit;
	/**
	 *c_credit_lim
	 */
	protected Long  c_credit_lim;
	/**
	 *c_discount
	 */
	protected Double  c_discount;
	/**
	 *c_balance
	 */
	protected Long  c_balance;
	/**
	 *c_ytd_payment
	 */
	protected Long  c_ytd_payment;
	/**
	 *c_payment_cnt
	 */
	protected Long  c_payment_cnt;
	/**
	 *c_delivery_cnt
	 */
	protected Long  c_delivery_cnt;
	/**
	 *c_data
	 */
	protected String  c_data;
	/**
	 *w_tax
	 */
	protected double  w_tax;
	/**
	 *d_tax
	 */
	protected String  d_tax;
	
	public String getD_tax() {
		return d_tax;
	}
	public void setD_tax(String d_tax) {
		this.d_tax = d_tax;
	}
	public double getW_tax() {
		return w_tax;
	}
	public void setW_tax(double w_tax) {
		this.w_tax = w_tax;
	}
	public Long getC_id() {
		return c_id;
	}
	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}
	
	public void setC_d_id(Long c_d_id) 
	{
		this.c_d_id = c_d_id;
	}
	/**
	 * 返回 c_d_id
	 * @return
	 */
	public Long getC_d_id() 
	{
		return this.c_d_id;
	}
	public void setC_w_id(Long c_w_id) 
	{
		this.c_w_id = c_w_id;
	}
	/**
	 * 返回 c_w_id
	 * @return
	 */
	public Long getC_w_id() 
	{
		return this.c_w_id;
	}
	public void setC_first(String c_first) 
	{
		this.c_first = c_first;
	}
	/**
	 * 返回 c_first
	 * @return
	 */
	public String getC_first() 
	{
		return this.c_first;
	}
	public void setC_middle(String c_middle) 
	{
		this.c_middle = c_middle;
	}
	/**
	 * 返回 c_middle
	 * @return
	 */
	public String getC_middle() 
	{
		return this.c_middle;
	}
	public void setC_last(String c_last) 
	{
		this.c_last = c_last;
	}
	/**
	 * 返回 c_last
	 * @return
	 */
	public String getC_last() 
	{
		return this.c_last;
	}
	public void setC_street_1(String c_street_1) 
	{
		this.c_street_1 = c_street_1;
	}
	/**
	 * 返回 c_street_1
	 * @return
	 */
	public String getC_street_1() 
	{
		return this.c_street_1;
	}
	public void setC_street_2(String c_street_2) 
	{
		this.c_street_2 = c_street_2;
	}
	/**
	 * 返回 c_street_2
	 * @return
	 */
	public String getC_street_2() 
	{
		return this.c_street_2;
	}
	public void setC_city(String c_city) 
	{
		this.c_city = c_city;
	}
	/**
	 * 返回 c_city
	 * @return
	 */
	public String getC_city() 
	{
		return this.c_city;
	}
	public void setC_state(String c_state) 
	{
		this.c_state = c_state;
	}
	/**
	 * 返回 c_state
	 * @return
	 */
	public String getC_state() 
	{
		return this.c_state;
	}
	public void setC_zip(String c_zip) 
	{
		this.c_zip = c_zip;
	}
	/**
	 * 返回 c_zip
	 * @return
	 */
	public String getC_zip() 
	{
		return this.c_zip;
	}
	public void setC_phone(String c_phone) 
	{
		this.c_phone = c_phone;
	}
	/**
	 * 返回 c_phone
	 * @return
	 */
	public String getC_phone() 
	{
		return this.c_phone;
	}
	public void setC_since(java.util.Date c_since) 
	{
		this.c_since = c_since;
	}
	/**
	 * 返回 c_since
	 * @return
	 */
	public java.util.Date getC_since() 
	{
		return this.c_since;
	}
	public void setC_credit(String c_credit) 
	{
		this.c_credit = c_credit;
	}
	/**
	 * 返回 c_credit
	 * @return
	 */
	public String getC_credit() 
	{
		return this.c_credit;
	}
	public void setC_credit_lim(Long c_credit_lim) 
	{
		this.c_credit_lim = c_credit_lim;
	}
	/**
	 * 返回 c_credit_lim
	 * @return
	 */
	public Long getC_credit_lim() 
	{
		return this.c_credit_lim;
	}
	public void setC_discount(Double c_discount) 
	{
		this.c_discount = c_discount;
	}
	/**
	 * 返回 c_discount
	 * @return
	 */
	public Double getC_discount() 
	{
		return this.c_discount;
	}
	public void setC_balance(Long c_balance) 
	{
		this.c_balance = c_balance;
	}
	/**
	 * 返回 c_balance
	 * @return
	 */
	public Long getC_balance() 
	{
		return this.c_balance;
	}
	public void setC_ytd_payment(Long c_ytd_payment) 
	{
		this.c_ytd_payment = c_ytd_payment;
	}
	/**
	 * 返回 c_ytd_payment
	 * @return
	 */
	public Long getC_ytd_payment() 
	{
		return this.c_ytd_payment;
	}
	public void setC_payment_cnt(Long c_payment_cnt) 
	{
		this.c_payment_cnt = c_payment_cnt;
	}
	/**
	 * 返回 c_payment_cnt
	 * @return
	 */
	public Long getC_payment_cnt() 
	{
		return this.c_payment_cnt;
	}
	public void setC_delivery_cnt(Long c_delivery_cnt) 
	{
		this.c_delivery_cnt = c_delivery_cnt;
	}
	/**
	 * 返回 c_delivery_cnt
	 * @return
	 */
	public Long getC_delivery_cnt() 
	{
		return this.c_delivery_cnt;
	}
	public void setC_data(String c_data) 
	{
		this.c_data = c_data;
	}
	/**
	 * 返回 c_data
	 * @return
	 */
	public String getC_data() 
	{
		return this.c_data;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Customer)) 
		{
			return false;
		}
		Customer rhs = (Customer) object;
		return new EqualsBuilder()
		.append(this.c_id,rhs.c_id)
		.append(this.c_d_id, rhs.c_d_id)
		.append(this.c_w_id, rhs.c_w_id)
		.append(this.c_first, rhs.c_first)
		.append(this.c_middle, rhs.c_middle)
		.append(this.c_last, rhs.c_last)
		.append(this.c_street_1, rhs.c_street_1)
		.append(this.c_street_2, rhs.c_street_2)
		.append(this.c_city, rhs.c_city)
		.append(this.c_state, rhs.c_state)
		.append(this.c_zip, rhs.c_zip)
		.append(this.c_phone, rhs.c_phone)
		.append(this.c_since, rhs.c_since)
		.append(this.c_credit, rhs.c_credit)
		.append(this.c_credit_lim, rhs.c_credit_lim)
		.append(this.c_discount, rhs.c_discount)
		.append(this.c_balance, rhs.c_balance)
		.append(this.c_ytd_payment, rhs.c_ytd_payment)
		.append(this.c_payment_cnt, rhs.c_payment_cnt)
		.append(this.c_delivery_cnt, rhs.c_delivery_cnt)
		.append(this.c_data, rhs.c_data)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.c_id)
		.append(this.c_d_id) 
		.append(this.c_w_id) 
		.append(this.c_first) 
		.append(this.c_middle) 
		.append(this.c_last) 
		.append(this.c_street_1) 
		.append(this.c_street_2) 
		.append(this.c_city) 
		.append(this.c_state) 
		.append(this.c_zip) 
		.append(this.c_phone) 
		.append(this.c_since) 
		.append(this.c_credit) 
		.append(this.c_credit_lim) 
		.append(this.c_discount) 
		.append(this.c_balance) 
		.append(this.c_ytd_payment) 
		.append(this.c_payment_cnt) 
		.append(this.c_delivery_cnt) 
		.append(this.c_data) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("c_id",this.c_id)
		.append("c_d_id", this.c_d_id) 
		.append("c_w_id", this.c_w_id) 
		.append("c_first", this.c_first) 
		.append("c_middle", this.c_middle) 
		.append("c_last", this.c_last) 
		.append("c_street_1", this.c_street_1) 
		.append("c_street_2", this.c_street_2) 
		.append("c_city", this.c_city) 
		.append("c_state", this.c_state) 
		.append("c_zip", this.c_zip) 
		.append("c_phone", this.c_phone) 
		.append("c_since", this.c_since) 
		.append("c_credit", this.c_credit) 
		.append("c_credit_lim", this.c_credit_lim) 
		.append("c_discount", this.c_discount) 
		.append("c_balance", this.c_balance) 
		.append("c_ytd_payment", this.c_ytd_payment) 
		.append("c_payment_cnt", this.c_payment_cnt) 
		.append("c_delivery_cnt", this.c_delivery_cnt) 
		.append("c_data", this.c_data) 
		.toString();
	}

}
