package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:下属管理 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-07-05 10:08:07
 */
public class UserUnder extends BaseModel
{
	// 主键
	protected Long  id;
	// 用户ID
	protected Long  userid;
	// 下属用户ID
	protected Long  underuserid;
	// 下属用户名
	protected String  underusername;
	
	protected String leaderName="";
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setUserid(Long userid) 
	{
		this.userid = userid;
	}
	/**
	 * 返回 用户ID
	 * @return
	 */
	public Long getUserid() 
	{
		return this.userid;
	}
	public void setUnderuserid(Long underuserid) 
	{
		this.underuserid = underuserid;
	}
	/**
	 * 返回 下属用户ID
	 * @return
	 */
	public Long getUnderuserid() 
	{
		return this.underuserid;
	}
	public void setUnderusername(String underusername) 
	{
		this.underusername = underusername;
	}
	/**
	 * 返回 下属用户名
	 * @return
	 */
	public String getUnderusername() 
	{
		return this.underusername;
	}

   
   	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof UserUnder)) 
		{
			return false;
		}
		UserUnder rhs = (UserUnder) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.userid, rhs.userid)
		.append(this.underuserid, rhs.underuserid)
		.append(this.underusername, rhs.underusername)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.userid) 
		.append(this.underuserid) 
		.append(this.underusername) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("userid", this.userid) 
		.append("underuserid", this.underuserid) 
		.append("underusername", this.underusername) 
		.toString();
	}
   
  

}