package com.hotent.e_business.model.e_business;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:运货表 Model对象
 */
public class OrderShipping extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *EMAIL地址
	 */
	protected String  EMAIL;
	/**
	 *名字
	 */
	protected String  FIRSTNAME;
	/**
	 *姓氏
	 */
	protected String  LASTNAME;
	/**
	 *地址
	 */
	protected String  ADDRESS;
	/**
	 *城市
	 */
	protected String  CITY;
	/**
	 *州代码
	 */
	protected String  STATE;
	/**
	 *邮编
	 */
	protected Long  ZIP;
	/**
	 *电话
	 */
	protected String  PHONE;
	/**
	 *运货类型
	 */
	protected String  SHIPPING;
	
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
	 * 返回 EMAIL地址
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
	 * 返回 名字
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
	 * 返回 姓氏
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
	 * 返回 地址
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
	 * 返回 城市
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
	 * 返回 州代码
	 * @return
	 */
	public String getSTATE() 
	{
		return this.STATE;
	}
	public void setZIP(Long ZIP) 
	{
		this.ZIP = ZIP;
	}
	/**
	 * 返回 邮编
	 * @return
	 */
	public Long getZIP() 
	{
		return this.ZIP;
	}
	public void setPHONE(String PHONE) 
	{
		this.PHONE = PHONE;
	}
	/**
	 * 返回 电话
	 * @return
	 */
	public String getPHONE() 
	{
		return this.PHONE;
	}
	public void setSHIPPING(String SHIPPING) 
	{
		this.SHIPPING = SHIPPING;
	}
	/**
	 * 返回 运货类型
	 * @return
	 */
	public String getSHIPPING() 
	{
		return this.SHIPPING;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof OrderShipping)) 
		{
			return false;
		}
		OrderShipping rhs = (OrderShipping) object;
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
		.append(this.SHIPPING, rhs.SHIPPING)
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
		.append(this.SHIPPING) 
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
		.append("SHIPPING", this.SHIPPING) 
		.toString();
	}

}
