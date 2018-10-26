package com.hotent.e_business.model.e_business;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:ORDER_BILLING Model对象
 */
public class OrderBilling extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *EMAIL
	 */
	protected String  EMAIL;
	/**
	 *FIRSTNAME
	 */
	protected String  FIRSTNAME;
	/**
	 *LASTNAME
	 */
	protected String  LASTNAME;
	/**
	 *ADDRESS
	 */
	protected String  ADDRESS;
	/**
	 *CITY
	 */
	protected String  CITY;
	/**
	 *STATE
	 */
	protected String  STATE;
	/**
	 *ZIP
	 */
	protected String  ZIP;
	/**
	 *PHONE
	 */
	protected String  PHONE;
	/**
	 *CC_TYPE
	 */
	protected String  CC_TYPE;
	/**
	 *CC_NUM
	 */
	protected String  CC_NUM;
	/**
	 *CC_EXPMONTH
	 */
	protected String  CC_EXPMONTH;
	/**
	 *CC_EXPYEAR
	 */
	protected String  CC_EXPYEAR;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setEMAIL(String EMAIL) 
	{
		this.EMAIL = EMAIL;
	}
	/**
	 * 返回 EMAIL
	 * @return
	 */
	public String getEMAIL() 
	{
		return this.EMAIL;
	}
	public void setFIRSTNAME(String FIRSTNAME) 
	{
		this.FIRSTNAME = FIRSTNAME;
	}
	/**
	 * 返回 FIRSTNAME
	 * @return
	 */
	public String getFIRSTNAME() 
	{
		return this.FIRSTNAME;
	}
	public void setLASTNAME(String LASTNAME) 
	{
		this.LASTNAME = LASTNAME;
	}
	/**
	 * 返回 LASTNAME
	 * @return
	 */
	public String getLASTNAME() 
	{
		return this.LASTNAME;
	}
	public void setADDRESS(String ADDRESS) 
	{
		this.ADDRESS = ADDRESS;
	}
	/**
	 * 返回 ADDRESS
	 * @return
	 */
	public String getADDRESS() 
	{
		return this.ADDRESS;
	}
	public void setCITY(String CITY) 
	{
		this.CITY = CITY;
	}
	/**
	 * 返回 CITY
	 * @return
	 */
	public String getCITY() 
	{
		return this.CITY;
	}
	public void setSTATE(String STATE) 
	{
		this.STATE = STATE;
	}
	/**
	 * 返回 STATE
	 * @return
	 */
	public String getSTATE() 
	{
		return this.STATE;
	}
	public void setZIP(String ZIP) 
	{
		this.ZIP = ZIP;
	}
	/**
	 * 返回 ZIP
	 * @return
	 */
	public String getZIP() 
	{
		return this.ZIP;
	}
	public void setPHONE(String PHONE) 
	{
		this.PHONE = PHONE;
	}
	/**
	 * 返回 PHONE
	 * @return
	 */
	public String getPHONE() 
	{
		return this.PHONE;
	}
	public void setCC_TYPE(String CC_TYPE) 
	{
		this.CC_TYPE = CC_TYPE;
	}
	/**
	 * 返回 CC_TYPE
	 * @return
	 */
	public String getCC_TYPE() 
	{
		return this.CC_TYPE;
	}
	public void setCC_NUM(String CC_NUM) 
	{
		this.CC_NUM = CC_NUM;
	}
	/**
	 * 返回 CC_NUM
	 * @return
	 */
	public String getCC_NUM() 
	{
		return this.CC_NUM;
	}
	public void setCC_EXPMONTH(String CC_EXPMONTH) 
	{
		this.CC_EXPMONTH = CC_EXPMONTH;
	}
	/**
	 * 返回 CC_EXPMONTH
	 * @return
	 */
	public String getCC_EXPMONTH() 
	{
		return this.CC_EXPMONTH;
	}
	public void setCC_EXPYEAR(String CC_EXPYEAR) 
	{
		this.CC_EXPYEAR = CC_EXPYEAR;
	}
	/**
	 * 返回 CC_EXPYEAR
	 * @return
	 */
	public String getCC_EXPYEAR() 
	{
		return this.CC_EXPYEAR;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof OrderBilling)) 
		{
			return false;
		}
		OrderBilling rhs = (OrderBilling) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.EMAIL, rhs.EMAIL)
		.append(this.FIRSTNAME, rhs.FIRSTNAME)
		.append(this.LASTNAME, rhs.LASTNAME)
		.append(this.ADDRESS, rhs.ADDRESS)
		.append(this.CITY, rhs.CITY)
		.append(this.STATE, rhs.STATE)
		.append(this.ZIP, rhs.ZIP)
		.append(this.PHONE, rhs.PHONE)
		.append(this.CC_TYPE, rhs.CC_TYPE)
		.append(this.CC_NUM, rhs.CC_NUM)
		.append(this.CC_EXPMONTH, rhs.CC_EXPMONTH)
		.append(this.CC_EXPYEAR, rhs.CC_EXPYEAR)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.EMAIL) 
		.append(this.FIRSTNAME) 
		.append(this.LASTNAME) 
		.append(this.ADDRESS) 
		.append(this.CITY) 
		.append(this.STATE) 
		.append(this.ZIP) 
		.append(this.PHONE) 
		.append(this.CC_TYPE) 
		.append(this.CC_NUM) 
		.append(this.CC_EXPMONTH) 
		.append(this.CC_EXPYEAR) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("EMAIL", this.EMAIL) 
		.append("FIRSTNAME", this.FIRSTNAME) 
		.append("LASTNAME", this.LASTNAME) 
		.append("ADDRESS", this.ADDRESS) 
		.append("CITY", this.CITY) 
		.append("STATE", this.STATE) 
		.append("ZIP", this.ZIP) 
		.append("PHONE", this.PHONE) 
		.append("CC_TYPE", this.CC_TYPE) 
		.append("CC_NUM", this.CC_NUM) 
		.append("CC_EXPMONTH", this.CC_EXPMONTH) 
		.append("CC_EXPYEAR", this.CC_EXPYEAR) 
		.toString();
	}

}
