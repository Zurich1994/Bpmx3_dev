package com.hotent.e_business.model.e_business;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:用户信息表 Model对象
 */
public class UserInfo extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *用户E-mail地址
	 */
	protected String  EMAIL;
	/**
	 *用户密码
	 */
	protected String  PASSWORD;
	/**
	 *用户名字
	 */
	protected String  FIRSTNAME;
	/**
	 *用户姓氏
	 */
	protected String  LASTNAME;
	
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
	 * 返回 用户E-mail地址
	 * @return
	 */
	public String getEMAIL() 
	{
		return this.EMAIL;
	}
	public void setPASSWORD(String PASSWORD) 
	{
		this.PASSWORD = PASSWORD;
	}
	/**
	 * 返回 用户密码
	 * @return
	 */
	public String getPASSWORD() 
	{
		return this.PASSWORD;
	}
	public void setFIRSTNAME(String FIRSTNAME) 
	{
		this.FIRSTNAME = FIRSTNAME;
	}
	/**
	 * 返回 用户名字
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
	 * 返回 用户姓氏
	 * @return
	 */
	public String getLASTNAME() 
	{
		return this.LASTNAME;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof UserInfo)) 
		{
			return false;
		}
		UserInfo rhs = (UserInfo) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.EMAIL, rhs.EMAIL)
		.append(this.PASSWORD, rhs.PASSWORD)
		.append(this.FIRSTNAME, rhs.FIRSTNAME)
		.append(this.LASTNAME, rhs.LASTNAME)
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
		.append(this.PASSWORD) 
		.append(this.FIRSTNAME) 
		.append(this.LASTNAME) 
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
		.append("PASSWORD", this.PASSWORD) 
		.append("FIRSTNAME", this.FIRSTNAME) 
		.append("LASTNAME", this.LASTNAME) 
		.toString();
	}

}
