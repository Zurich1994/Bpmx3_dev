package com.hotent.platform.model.mail;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:最近联系人 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2012-04-13 11:11:56
 */
public class OutMailLinkman extends BaseModel
{
	// linkId
	protected Long linkId;
	// 邮件ID
	protected Long mailId;
	//用户ID
	protected Long userId;
	// 发送时间
	protected java.util.Date sendTime;
	// linkName
	protected String linkName;
	// linkAddress
	protected String linkAddress;

	public void setLinkId(Long linkId) 
	{
		this.linkId = linkId;
	}
	/**
	 * 返回 linkId
	 * @return
	 */
	public Long getLinkId() 
	{
		return linkId;
	}

	public void setMailId(Long mailId) 
	{
		this.mailId = mailId;
	}
	/**
	 * 返回 邮件ID
	 * @return
	 */
	public Long getMailId() 
	{
		return mailId;
	}

	/**
	 * 返回用户Id
	 * @return
	 */
	public Long getUserId() 
	{
		return userId;
	}
	
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	
	public void setSendTime(java.util.Date sendTime) 
	{
		this.sendTime = sendTime;
	}
	/**
	 * 返回 发送时间
	 * @return
	 */
	public java.util.Date getSendTime() 
	{
		return sendTime;
	}

	public void setLinkName(String linkName) 
	{
		this.linkName = linkName;
	}
	/**
	 * 返回 linkName
	 * @return
	 */
	public String getLinkName() 
	{
		return linkName;
	}

	public void setLinkAddress(String linkAddress) 
	{
		this.linkAddress = linkAddress;
	}
	/**
	 * 返回 linkAddress
	 * @return
	 */
	public String getLinkAddress() 
	{
		return linkAddress;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof OutMailLinkman)) 
		{
			return false;
		}
		OutMailLinkman rhs = (OutMailLinkman) object;
		return new EqualsBuilder()
		.append(this.linkId, rhs.linkId)
		.append(this.mailId, rhs.mailId)
		.append(this.sendTime, rhs.sendTime)
		.append(this.linkName, rhs.linkName)
		.append(this.linkAddress, rhs.linkAddress)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.linkId) 
		.append(this.mailId) 
		.append(this.sendTime) 
		.append(this.linkName) 
		.append(this.linkAddress) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("linkId", this.linkId) 
		.append("mailId", this.mailId) 
		.append("sendTime", this.sendTime) 
		.append("linkName", this.linkName) 
		.append("linkAddress", this.linkAddress) 
		.toString();
	}

}