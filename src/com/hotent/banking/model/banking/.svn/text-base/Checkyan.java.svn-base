package com.hotent.banking.model.banking;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:支票查看表 Model对象
 */
public class Checkyan extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *支票号
	 */
	protected String  checkno;
	/**
	 *用户名
	 */
	protected String  userid;
	/**
	 *账号
	 */
	protected String  account;
	/**
	 *正面图像地址
	 */
	protected String  fronturl;
	/**
	 *反面图像地址
	 */
	protected String  backurl;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setCheckno(String checkno) 
	{
		this.checkno = checkno;
	}
	/**
	 * 返回 支票号
	 * @return
	 */
	public String getCheckno() 
	{
		return this.checkno;
	}
	public void setUserid(String userid) 
	{
		this.userid = userid;
	}
	/**
	 * 返回 用户名
	 * @return
	 */
	public String getUserid() 
	{
		return this.userid;
	}
	public void setAccount(String account) 
	{
		this.account = account;
	}
	/**
	 * 返回 账号
	 * @return
	 */
	public String getAccount() 
	{
		return this.account;
	}
	public void setFronturl(String fronturl) 
	{
		this.fronturl = fronturl;
	}
	/**
	 * 返回 正面图像地址
	 * @return
	 */
	public String getFronturl() 
	{
		return this.fronturl;
	}
	public void setBackurl(String backurl) 
	{
		this.backurl = backurl;
	}
	/**
	 * 返回 反面图像地址
	 * @return
	 */
	public String getBackurl() 
	{
		return this.backurl;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Checkyan)) 
		{
			return false;
		}
		Checkyan rhs = (Checkyan) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.checkno, rhs.checkno)
		.append(this.userid, rhs.userid)
		.append(this.account, rhs.account)
		.append(this.fronturl, rhs.fronturl)
		.append(this.backurl, rhs.backurl)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.checkno) 
		.append(this.userid) 
		.append(this.account) 
		.append(this.fronturl) 
		.append(this.backurl) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("checkno", this.checkno) 
		.append("userid", this.userid) 
		.append("account", this.account) 
		.append("fronturl", this.fronturl) 
		.append("backurl", this.backurl) 
		.toString();
	}

}
