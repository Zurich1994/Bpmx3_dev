package com.hotent.platform.model.system;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:发送消息 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-14 15:10:58
 */
public class MessageSend extends BaseModel
{
	public final static String MESSAGETYPE_PERSON="1";//个人信息
	public final static String MESSAGETYPE_SCHEDULE="2";//日程安排 
	public final static String MESSAGETYPE_PLAN="3";//计划任务
	public final static String MESSAGETYPE_SYSTEM="4";//系统信息
	public final static String MESSAGETYPE_AGENCY ="5";//代办提醒
	public final static String MESSAGETYPE_FLOWTASK ="6";//流程提醒
	
	public final static String SPLIT_FLAG = "[userorg]";
	
	// 主键
	protected Long id;
	// 标题
	protected String subject;
	// 发送人ID
	protected Long userId;
	// 发送人
	protected String userName;
	// 消息类型(使用数据字典）
	protected String messageType;
	// 内容
	protected String content;
	// 发送时间
	protected java.util.Date sendTime;
	// 不需回复
	protected Short canReply;
	// receiverName
	protected String receiverName;
	// receiverOrgName
	protected String receiverOrgName;
	//收信时间
	protected java.util.Date receiveTime;
	//收信id
	protected Long rid;
	public Long getRid()
	{
		return rid;
	}
	public void setRid(Long rid)
	{
		this.rid = rid;
	}
	public java.util.Date getReceiveTime()
	{
		return receiveTime;
	}
	public void setReceiveTime(java.util.Date receiveTime)
	{
		this.receiveTime = receiveTime;
	}
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

	public void setSubject(String subject) 
	{
		this.subject = subject;
	}
	/**
	 * 返回 标题
	 * @return
	 */
	public String getSubject() 
	{
		return subject;
	}

	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}
	/**
	 * 返回 发送人ID
	 * @return
	 */
	public Long getUserId() 
	{
		return userId;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	/**
	 * 返回 发送人
	 * @return
	 */
	public String getUserName() 
	{
		return userName;
	}

	public void setMessageType(String messageType) 
	{
		this.messageType = messageType;
	}
	/**
	 * 返回 消息类型(使用数据字典）
	 * @return
	 */
	public String getMessageType() 
	{
		return messageType;
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

	public void setCanReply(Short canReply) 
	{
		this.canReply = canReply;
	}
	/**
	 * 返回 不需回复
	 * @return
	 */
	public Short getCanReply() 
	{
		return canReply;
	}

	public void setReceiverName(String receiverName) 
	{
		this.receiverName = receiverName;
	}
	/**
	 * 返回 receiverName
	 * @return
	 */
	public String getReceiverName() 
	{
//		if(receiverName.indexOf(SPLIT_FLAG)!=-1){
//			receiverName = receiverName.replace(SPLIT_FLAG, ",");
//		}
		return receiverName;
	}
	
	public String getReceiverOrgName() {
//		if(receiverName.indexOf(SPLIT_FLAG)!=-1){
//			receiverOrgName = receiverOrgName.split("[org]")[1];
//		}
		return receiverOrgName;
	}
	public void setReceiverOrgName(String receiverOrgName) {
		this.receiverOrgName = receiverOrgName;
	}
   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof MessageSend)) 
		{
			return false;
		}
		MessageSend rhs = (MessageSend) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.subject, rhs.subject)
		.append(this.userId, rhs.userId)
		.append(this.userName, rhs.userName)
		.append(this.messageType, rhs.messageType)
		.append(this.content, rhs.content)
		.append(this.sendTime, rhs.sendTime)
		.append(this.canReply, rhs.canReply)
		.append(this.receiverName, rhs.receiverName)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.subject) 
		.append(this.userId) 
		.append(this.userName) 
		.append(this.messageType) 
		.append(this.content) 
		.append(this.sendTime) 
		.append(this.canReply) 
		.append(this.receiverName) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("subject", this.subject) 
		.append("userId", this.userId) 
		.append("userName", this.userName) 
		.append("messageType", this.messageType) 
		.append("content", this.content) 
		.append("sendTime", this.sendTime) 
		.append("canReply", this.canReply) 
		.append("receiverName", this.receiverName) 
		.toString();
	}
   
  

}