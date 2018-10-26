package com.hotent.tpcc.model.tpcc;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:district Model对象
 */
public class District extends BaseModel
{
	


	protected Long ol_i_id;
	//主键
	protected Long d_id;
	/**
	 *d_w_id
	 */
	protected Long  d_w_id;
	/**
	 *d_name
	 */
	protected String  d_name;
	/**
	 *d_street_1
	 */
	protected String  d_street_1;
	/**
	 *d_street_2
	 */
	protected String  d_street_2;
	/**
	 *d_city
	 */
	protected String  d_city;
	/**
	 *d_state
	 */
	protected String  d_state;
	/**
	 *d_zip
	 */
	protected String  d_zip;
	/**
	 *d_tax
	 */
	protected double  d_tax;
	/**
	 *d_ytd
	 */
	protected Long  d_ytd;
	/**
	 *d_next_o_id
	 */
	protected Long  d_next_o_id;
	
	public Long getD_id() {
		return d_id;
	}
	public void setD_id(Long d_id) {
		this.d_id = d_id;
	}
	
	public void setD_w_id(Long d_w_id) 
	{
		this.d_w_id = d_w_id;
	}
	/**
	 * 返回 d_w_id
	 * @return
	 */
	public Long getD_w_id() 
	{
		return this.d_w_id;
	}
	public void setD_name(String d_name) 
	{
		this.d_name = d_name;
	}
	/**
	 * 返回 d_name
	 * @return
	 */
	public String getD_name() 
	{
		return this.d_name;
	}
	public void setD_street_1(String d_street_1) 
	{
		this.d_street_1 = d_street_1;
	}
	/**
	 * 返回 d_street_1
	 * @return
	 */
	public String getD_street_1() 
	{
		return this.d_street_1;
	}
	public void setD_street_2(String d_street_2) 
	{
		this.d_street_2 = d_street_2;
	}
	/**
	 * 返回 d_street_2
	 * @return
	 */
	public String getD_street_2() 
	{
		return this.d_street_2;
	}
	public void setD_city(String d_city) 
	{
		this.d_city = d_city;
	}
	/**
	 * 返回 d_city
	 * @return
	 */
	public String getD_city() 
	{
		return this.d_city;
	}
	public void setD_state(String d_state) 
	{
		this.d_state = d_state;
	}
	/**
	 * 返回 d_state
	 * @return
	 */
	public String getD_state() 
	{
		return this.d_state;
	}
	public void setD_zip(String d_zip) 
	{
		this.d_zip = d_zip;
	}
	/**
	 * 返回 d_zip
	 * @return
	 */
	public String getD_zip() 
	{
		return this.d_zip;
	}
	public void setD_tax(double d_tax) 
	{
		this.d_tax = d_tax;
	}
	/**
	 * 返回 d_tax
	 * @return
	 */
	public double getD_tax() 
	{
		return this.d_tax;
	}
	public void setD_ytd(Long d_ytd) 
	{
		this.d_ytd = d_ytd;
	}
	/**
	 * 返回 d_ytd
	 * @return
	 */
	public Long getD_ytd() 
	{
		return this.d_ytd;
	}
	public void setD_next_o_id(Long d_next_o_id) 
	{
		this.d_next_o_id = d_next_o_id;
	}
	/**
	 * 返回 d_next_o_id
	 * @return
	 */
	public Long getD_next_o_id() 
	{
		return this.d_next_o_id;
	}
	public Long getOl_i_id() {
		return ol_i_id;
	}
	public void setOl_i_id(Long olIId) {
		ol_i_id = olIId;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof District)) 
		{
			return false;
		}
		District rhs = (District) object;
		return new EqualsBuilder()
		.append(this.d_id,rhs.d_id)
		.append(this.d_w_id, rhs.d_w_id)
		.append(this.d_name, rhs.d_name)
		.append(this.d_street_1, rhs.d_street_1)
		.append(this.d_street_2, rhs.d_street_2)
		.append(this.d_city, rhs.d_city)
		.append(this.d_state, rhs.d_state)
		.append(this.d_zip, rhs.d_zip)
		.append(this.d_tax, rhs.d_tax)
		.append(this.d_ytd, rhs.d_ytd)
		.append(this.d_next_o_id, rhs.d_next_o_id)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.d_id)
		.append(this.d_w_id) 
		.append(this.d_name) 
		.append(this.d_street_1) 
		.append(this.d_street_2) 
		.append(this.d_city) 
		.append(this.d_state) 
		.append(this.d_zip) 
		.append(this.d_tax) 
		.append(this.d_ytd) 
		.append(this.d_next_o_id) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("d_id",this.d_id)
		.append("d_w_id", this.d_w_id) 
		.append("d_name", this.d_name) 
		.append("d_street_1", this.d_street_1) 
		.append("d_street_2", this.d_street_2) 
		.append("d_city", this.d_city) 
		.append("d_state", this.d_state) 
		.append("d_zip", this.d_zip) 
		.append("d_tax", this.d_tax) 
		.append("d_ytd", this.d_ytd) 
		.append("d_next_o_id", this.d_next_o_id) 
		.toString();
	}

}
