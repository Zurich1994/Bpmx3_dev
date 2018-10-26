package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:SYS_PAUR Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-12-24 14:41:59
 */
public class SysPaur extends BaseModel
{
	// PAURID
	protected Long  paurid;
	// 名称
	protected String  paurname;
	// 别名
	protected String  aliasname;
	// 值
	protected String  paurvalue;
	// 所属用户
	protected Long  userid;
	public void setPaurid(Long paurid) 
	{
		this.paurid = paurid;
	}
	/**
	 * 返回 PAURID
	 * @return
	 */
	public Long getPaurid() 
	{
		return this.paurid;
	}
	public void setPaurname(String paurname) 
	{
		this.paurname = paurname;
	}
	/**
	 * 返回 名称
	 * @return
	 */
	public String getPaurname() 
	{
		return this.paurname;
	}
	public void setAliasname(String aliasname) 
	{
		this.aliasname = aliasname;
	}
	/**
	 * 返回 别名
	 * @return
	 */
	public String getAliasname() 
	{
		return this.aliasname;
	}
	public void setPaurvalue(String paurvalue) 
	{
		this.paurvalue = paurvalue;
	}
	/**
	 * 返回 值
	 * @return
	 */
	public String getPaurvalue() 
	{
		return this.paurvalue;
	}
	public void setUserid(Long userid) 
	{
		this.userid = userid;
	}
	/**
	 * 返回 所属用户
	 * @return
	 */
	public Long getUserid() 
	{
		return this.userid;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysPaur)) 
		{
			return false;
		}
		SysPaur rhs = (SysPaur) object;
		return new EqualsBuilder()
		.append(this.paurid, rhs.paurid)
		.append(this.paurname, rhs.paurname)
		.append(this.aliasname, rhs.aliasname)
		.append(this.paurvalue, rhs.paurvalue)
		.append(this.userid, rhs.userid)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.paurid) 
		.append(this.paurname) 
		.append(this.aliasname) 
		.append(this.paurvalue) 
		.append(this.userid) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("paurid", this.paurid) 
		.append("paurname", this.paurname) 
		.append("aliasname", this.aliasname) 
		.append("paurvalue", this.paurvalue) 
		.append("userid", this.userid) 
		.toString();
	}
   
  

}