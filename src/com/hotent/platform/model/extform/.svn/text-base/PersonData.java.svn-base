package com.hotent.platform.model.extform;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:PERSON_DATA Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-01-10 09:44:06
 */
public class PersonData extends BaseModel
{
	// USERID
	protected Long  id;
	// USERNAME
	protected String  username;
	// IDCARD
	protected String  idcard;
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 USERID
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
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
		if (!(object instanceof PersonData)) 
		{
			return false;
		}
		PersonData rhs = (PersonData) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
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
		.append(this.id) 
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
		.append("id", this.id) 
		.append("username", this.username) 
		.append("idcard", this.idcard) 
		.toString();
	}
   
  

}