package com.hotent.banking.model.banking;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:支付者信息表 Model对象
 */
public class Payee extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *用户名
	 */
	protected String  USERID;
	/**
	 *别名
	 */
	protected String  PAYEEID;
	/**
	 *名字
	 */
	protected String  NAME;
	/**
	 *街道
	 */
	protected String  STREET;
	/**
	 *城市
	 */
	protected String  CITY;
	/**
	 *州
	 */
	protected String  STATE;
	/**
	 *区号
	 */
	protected String  ZIP;
	/**
	 *电话
	 */
	protected String  PHONE;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUSERID(String uSERID) 
	{
		USERID = uSERID;
	}
	/**
	 * 返回 用户名
	 * @return
	 */
	public String getUSERID() 
	{
		return USERID;
	}
	public void setPAYEEID(String PAYEEID) 
	{
		this.PAYEEID = PAYEEID;
	}
	/**
	 * 返回 别名
	 * @return
	 */
	public String getPAYEEID() 
	{
		return this.PAYEEID;
	}
	public void setNAME(String NAME) 
	{
		this.NAME = NAME;
	}
	/**
	 * 返回 名字
	 * @return
	 */
	public String getNAME() 
	{
		return this.NAME;
	}
	public void setSTREET(String STREET) 
	{
		this.STREET = STREET;
	}
	/**
	 * 返回 街道
	 * @return
	 */
	public String getSTREET() 
	{
		return this.STREET;
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
	 * 返回 州
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
	 * 返回 区号
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
	 * 返回 电话
	 * @return
	 */
	public String getPHONE() 
	{
		return this.PHONE;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Payee)) 
		{
			return false;
		}
		Payee rhs = (Payee) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.USERID, rhs.USERID)
		.append(this.PAYEEID, rhs.PAYEEID)
		.append(this.NAME, rhs.NAME)
		.append(this.STREET, rhs.STREET)
		.append(this.CITY, rhs.CITY)
		.append(this.STATE, rhs.STATE)
		.append(this.ZIP, rhs.ZIP)
		.append(this.PHONE, rhs.PHONE)
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
		.append(this.NAME) 
		.append(this.STREET) 
		.append(this.CITY) 
		.append(this.STATE) 
		.append(this.ZIP) 
		.append(this.PHONE) 
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
		.append("NAME", this.NAME) 
		.append("STREET", this.STREET) 
		.append("CITY", this.CITY) 
		.append("STATE", this.STATE) 
		.append("ZIP", this.ZIP) 
		.append("PHONE", this.PHONE) 
		.toString();
	}

}
