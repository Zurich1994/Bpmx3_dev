package com.hotent.banking.model.banking;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:更新账户信息 Model对象
 */
public class update extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *用户名，由英文及数字组合而成
	 */
	protected String  USERID;
	/**
	 *密码
	 */
	protected String  PASSWORD;
	/**
	 *地址
	 */
	protected String  ADDRESS;
	/**
	 *城市
	 */
	protected String  CITY;
	/**
	 *州,2位州代码
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
	/**
	 *邮件地址
	 */
	protected String  EMAIL;
	
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
	 * 返回 用户名，由英文及数字组合而成
	 * @return
	 */
	public String getUSERID() 
	{
		return this.USERID;
	}
	public void setPASSWORD(String PASSWORD) 
	{
		this.PASSWORD = PASSWORD;
	}
	/**
	 * 返回 密码
	 * @return
	 */
	public String getPASSWORD() 
	{
		return this.PASSWORD;
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
	 * 返回 州,2位州代码
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
	public void setEMAIL(String EMAIL) 
	{
		this.EMAIL = EMAIL;
	}
	/**
	 * 返回 邮件地址
	 * @return
	 */
	public String getEMAIL() 
	{
		return this.EMAIL;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof update)) 
		{
			return false;
		}
		update rhs = (update) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.USERID, rhs.USERID)
		.append(this.PASSWORD, rhs.PASSWORD)
		.append(this.ADDRESS, rhs.ADDRESS)
		.append(this.CITY, rhs.CITY)
		.append(this.STATE, rhs.STATE)
		.append(this.ZIP, rhs.ZIP)
		.append(this.PHONE, rhs.PHONE)
		.append(this.EMAIL, rhs.EMAIL)
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
		.append(this.PASSWORD) 
		.append(this.ADDRESS) 
		.append(this.CITY) 
		.append(this.STATE) 
		.append(this.ZIP) 
		.append(this.PHONE) 
		.append(this.EMAIL) 
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
		.append("PASSWORD", this.PASSWORD) 
		.append("ADDRESS", this.ADDRESS) 
		.append("CITY", this.CITY) 
		.append("STATE", this.STATE) 
		.append("ZIP", this.ZIP) 
		.append("PHONE", this.PHONE) 
		.append("EMAIL", this.EMAIL) 
		.toString();
	}

}
