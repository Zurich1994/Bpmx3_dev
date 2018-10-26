package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:接收状态 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-14 15:14:51
 */
public class MessageRead extends BaseModel
{
	// 主键
	protected Long id;
	// 消息ID
	protected Long messageId;
	// 接收人Id
	protected Long receiverId;
	// 接收人
	protected String receiver;
	// 接收时间
	protected java.util.Date receiveTime;

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
		return id;
	}

	public void setMessageId(Long messageId) 
	{
		this.messageId = messageId;
	}
	/**
	 * 返回 消息ID
	 * @return
	 */
	public Long getMessageId() 
	{
		return messageId;
	}

	public void setReceiverId(Long receiverId) 
	{
		this.receiverId = receiverId;
	}
	/**
	 * 返回 接收人Id
	 * @return
	 */
	public Long getReceiverId() 
	{
		return receiverId;
	}

	public void setReceiver(String receiver) 
	{
		this.receiver = receiver;
	}
	/**
	 * 返回 接收人
	 * @return
	 */
	public String getReceiver() 
	{
		return receiver;
	}

	public void setReceiveTime(java.util.Date receiveTime) 
	{
		this.receiveTime = receiveTime;
	}
	/**
	 * 返回 接收时间
	 * @return
	 */
	public java.util.Date getReceiveTime() 
	{
		return receiveTime;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof MessageRead)) 
		{
			return false;
		}
		MessageRead rhs = (MessageRead) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.messageId, rhs.messageId)
		.append(this.receiverId, rhs.receiverId)
		.append(this.receiver, rhs.receiver)
		.append(this.receiveTime, rhs.receiveTime)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.messageId) 
		.append(this.receiverId) 
		.append(this.receiver) 
		.append(this.receiveTime) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("messageId", this.messageId) 
		.append("receiverId", this.receiverId) 
		.append("receiver", this.receiver) 
		.append("receiveTime", this.receiveTime) 
		.toString();
	}
   
  

}