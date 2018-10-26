package com.hotent.warehouse.model.warehouse;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:warehouse Model对象
 */
public class Warehouse extends BaseModel
{
	//主键
	protected Long w_id;
	/**
	 *w_name
	 */
	protected String  w_name;
	/**
	 *w_street_1
	 */
	protected String  w_street_1;
	/**
	 *w_street_2
	 */
	protected String  w_street_2;
	/**
	 *w_city
	 */
	protected String  w_city;
	/**
	 *w_state
	 */
	protected String  w_state;
	/**
	 *w_zip
	 */
	protected String  w_zip;
	/**
	 *w_tax
	 */
	protected Long  w_tax;
	/**
	 *w_ytd
	 */
	protected Long  w_ytd;
	
	public Long getW_id() {
		return w_id;
	}
	public void setW_id(Long w_id) {
		this.w_id = w_id;
	}
	
	public void setW_name(String w_name) 
	{
		this.w_name = w_name;
	}
	/**
	 * 返回 w_name
	 * @return
	 */
	public String getW_name() 
	{
		return this.w_name;
	}
	public void setW_street_1(String w_street_1) 
	{
		this.w_street_1 = w_street_1;
	}
	/**
	 * 返回 w_street_1
	 * @return
	 */
	public String getW_street_1() 
	{
		return this.w_street_1;
	}
	public void setW_street_2(String w_street_2) 
	{
		this.w_street_2 = w_street_2;
	}
	/**
	 * 返回 w_street_2
	 * @return
	 */
	public String getW_street_2() 
	{
		return this.w_street_2;
	}
	public void setW_city(String w_city) 
	{
		this.w_city = w_city;
	}
	/**
	 * 返回 w_city
	 * @return
	 */
	public String getW_city() 
	{
		return this.w_city;
	}
	public void setW_state(String w_state) 
	{
		this.w_state = w_state;
	}
	/**
	 * 返回 w_state
	 * @return
	 */
	public String getW_state() 
	{
		return this.w_state;
	}
	public void setW_zip(String w_zip) 
	{
		this.w_zip = w_zip;
	}
	/**
	 * 返回 w_zip
	 * @return
	 */
	public String getW_zip() 
	{
		return this.w_zip;
	}
	public void setW_tax(Long w_tax) 
	{
		this.w_tax = w_tax;
	}
	/**
	 * 返回 w_tax
	 * @return
	 */
	public Long getW_tax() 
	{
		return this.w_tax;
	}
	public void setW_ytd(Long w_ytd) 
	{
		this.w_ytd = w_ytd;
	}
	/**
	 * 返回 w_ytd
	 * @return
	 */
	public Long getW_ytd() 
	{
		return this.w_ytd;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Warehouse)) 
		{
			return false;
		}
		Warehouse rhs = (Warehouse) object;
		return new EqualsBuilder()
		.append(this.w_id,rhs.w_id)
		.append(this.w_name, rhs.w_name)
		.append(this.w_street_1, rhs.w_street_1)
		.append(this.w_street_2, rhs.w_street_2)
		.append(this.w_city, rhs.w_city)
		.append(this.w_state, rhs.w_state)
		.append(this.w_zip, rhs.w_zip)
		.append(this.w_tax, rhs.w_tax)
		.append(this.w_ytd, rhs.w_ytd)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.w_id)
		.append(this.w_name) 
		.append(this.w_street_1) 
		.append(this.w_street_2) 
		.append(this.w_city) 
		.append(this.w_state) 
		.append(this.w_zip) 
		.append(this.w_tax) 
		.append(this.w_ytd) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("w_id",this.w_id)
		.append("w_name", this.w_name) 
		.append("w_street_1", this.w_street_1) 
		.append("w_street_2", this.w_street_2) 
		.append("w_city", this.w_city) 
		.append("w_state", this.w_state) 
		.append("w_zip", this.w_zip) 
		.append("w_tax", this.w_tax) 
		.append("w_ytd", this.w_ytd) 
		.toString();
	}

}
