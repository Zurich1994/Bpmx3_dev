package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:系统错误日志 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-07-12 16:42:11
 */
public class SysErrorLog extends BaseModel
{
	// 主键
	protected Long  id;
	// 错误hashcode
	protected String  hashcode;
	// 用户
	protected String  account;
	// IP地址
	protected String  ip;
	// 错误URL
	protected String  errorurl;
	// 错误信息
	protected String  error;
	// 错误日期
	protected java.util.Date  errordate;
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
	public void setHashcode(String hashcode) 
	{
		this.hashcode = hashcode;
	}
	/**
	 * 返回 错误hashcode
	 * @return
	 */
	public String getHashcode() 
	{
		return this.hashcode;
	}
	public void setAccount(String account) 
	{
		this.account = account;
	}
	/**
	 * 返回 用户
	 * @return
	 */
	public String getAccount() 
	{
		return this.account;
	}
	public void setIp(String ip) 
	{
		this.ip = ip;
	}
	/**
	 * 返回 IP地址
	 * @return
	 */
	public String getIp() 
	{
		return this.ip;
	}
	public void setErrorurl(String errorurl) 
	{
		this.errorurl = errorurl;
	}
	/**
	 * 返回 错误URL
	 * @return
	 */
	public String getErrorurl() 
	{
		return this.errorurl;
	}
	public void setError(String error) 
	{
		this.error = error;
	}
	/**
	 * 返回 错误信息
	 * @return
	 */
	public String getError() 
	{
		return this.error;
	}
	public void setErrordate(java.util.Date errordate) 
	{
		this.errordate = errordate;
	}
	/**
	 * 返回 错误日期
	 * @return
	 */
	public java.util.Date getErrordate() 
	{
		return this.errordate;
	}


   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysErrorLog)) 
		{
			return false;
		}
		SysErrorLog rhs = (SysErrorLog) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.hashcode, rhs.hashcode)
		.append(this.account, rhs.account)
		.append(this.ip, rhs.ip)
		.append(this.errorurl, rhs.errorurl)
		.append(this.error, rhs.error)
		.append(this.errordate, rhs.errordate)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.hashcode) 
		.append(this.account) 
		.append(this.ip) 
		.append(this.errorurl) 
		.append(this.error) 
		.append(this.errordate) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("hashcode", this.hashcode) 
		.append("account", this.account) 
		.append("ip", this.ip) 
		.append("errorurl", this.errorurl) 
		.append("error", this.error) 
		.append("errordate", this.errordate) 
		.toString();
	}
   
  

}