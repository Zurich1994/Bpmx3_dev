package com.hotent.platform.model.mobile;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:MOBILE_USER_INFO Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-01-06 11:55:09
 */
public class MobileUserInfo extends BaseModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// USERID
	protected Long  userid;
	// USERNAME
	protected String  username;
	// IDCARD
	protected String  idcard;
	public void setUserid(Long userid) 
	{
		this.userid = userid;
	}
	/**
	 * 返回 USERID
	 * @return
	 */
	public Long getUserid() 
	{
		return this.userid;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	/**
	 * 返回 USERNAME
	 * @return
	 */
	public String getUsername() 
	{
		return this.username;
	}
	public void setIdcard(String idcard) 
	{
		this.idcard = idcard;
	}
	/**
	 * 返回 IDCARD
	 * @return
	 */
	public String getIdcard() 
	{
		return this.idcard;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof MobileUserInfo)) 
		{
			return false;
		}
		MobileUserInfo rhs = (MobileUserInfo) object;
		return new EqualsBuilder()
		.append(this.userid, rhs.userid)
		.append(this.username, rhs.username)
		.append(this.idcard, rhs.idcard)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.userid) 
		.append(this.username) 
		.append(this.idcard) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("userid", this.userid) 
		.append("username", this.username) 
		.append("idcard", this.idcard) 
		.toString();
	}
   
  

}