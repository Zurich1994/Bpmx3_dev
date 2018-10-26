package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:消息回复 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-14 15:15:43
 */
public class MessageReply extends BaseModel
{
	// 主键
	protected Long id;
	// 消息id
	protected Long messageId;
	// 内容
	protected String content;
	// 回复人ID
	protected Long replyId;
	// 回复人
	protected String reply;
	// 回复时间
	protected java.util.Date replyTime;
	// 私密回复
	protected Short isPrivate;

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
	 * 返回 消息id
	 * @return
	 */
	public Long getMessageId() 
	{
		return messageId;
	}

	public void setContent(String content) 
	{
		this.content = content;
	}
	/**
	 * 返回 内容
	 * @return
	 */
	public String getContent() 
	{
		return content;
	}

	public void setReplyId(Long replyId) 
	{
		this.replyId = replyId;
	}
	/**
	 * 返回 回复人ID
	 * @return
	 */
	public Long getReplyId() 
	{
		return replyId;
	}

	public void setReply(String reply) 
	{
		this.reply = reply;
	}
	/**
	 * 返回 回复人
	 * @return
	 */
	public String getReply() 
	{
		return reply;
	}

	public void setReplyTime(java.util.Date replyTime) 
	{
		this.replyTime = replyTime;
	}
	/**
	 * 返回 回复时间
	 * @return
	 */
	public java.util.Date getReplyTime() 
	{
		return replyTime;
	}

	public void setIsPrivate(Short isPrivate) 
	{
		this.isPrivate = isPrivate;
	}
	/**
	 * 返回 私密回复
	 * @return
	 */
	public Short getIsPrivate() 
	{
		return isPrivate;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof MessageReply)) 
		{
			return false;
		}
		MessageReply rhs = (MessageReply) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.messageId, rhs.messageId)
		.append(this.content, rhs.content)
		.append(this.replyId, rhs.replyId)
		.append(this.reply, rhs.reply)
		.append(this.replyTime, rhs.replyTime)
		.append(this.isPrivate, rhs.isPrivate)
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
		.append(this.content) 
		.append(this.replyId) 
		.append(this.reply) 
		.append(this.replyTime) 
		.append(this.isPrivate) 
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
		.append("content", this.content) 
		.append("replyId", this.replyId) 
		.append("reply", this.reply) 
		.append("replyTime", this.replyTime) 
		.append("isPrivate", this.isPrivate) 
		.toString();
	}
   
  

}