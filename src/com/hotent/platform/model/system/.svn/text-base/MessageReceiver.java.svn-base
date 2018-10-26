package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:消息接收者 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-14 15:12:59
 */
public class MessageReceiver extends BaseModel
{
//	public final static Short TYPE_GROUP = 1;//集团
//	public final static Short TYPE_COMPANY = 2;//公司
//	public final static Short TYPE_DEPART = 3;//部门
//	public final static Short TYPE_ORG = 4;//其他组织
//	public final static Short TYPE_USER = 5;//用户
	
	public final static Short TYPE_USER = 0; //个人
	public final static Short TYPE_ORG = 1;  //组织
	
	// id
	protected Long id;
	// 消息ID
	protected Long messageId;
	// 接收者类型
	protected Short receiveType;
	// 接收人ID
	protected Long receiverId;
	// 接收人
	protected String receiver;

	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 id
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

	public void setReceiveType(Short receiveType) 
	{
		this.receiveType = receiveType;
	}
	/**
	 * 返回 接收者类型
	 * @return
	 */
	public Short getReceiveType() 
	{
		return receiveType;
	}

	public void setReceiverId(Long receiverId) 
	{
		this.receiverId = receiverId;
	}
	/**
	 * 返回 接收人ID
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

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof MessageReceiver)) 
		{
			return false;
		}
		MessageReceiver rhs = (MessageReceiver) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.messageId, rhs.messageId)
		.append(this.receiveType, rhs.receiveType)
		.append(this.receiverId, rhs.receiverId)
		.append(this.receiver, rhs.receiver)
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
		.append(this.receiveType) 
		.append(this.receiverId) 
		.append(this.receiver) 
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
		.append("receiveType", this.receiveType) 
		.append("receiverId", this.receiverId) 
		.append("receiver", this.receiver) 
		.toString();
	}
   
  

}