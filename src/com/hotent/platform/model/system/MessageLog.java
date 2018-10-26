package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * <pre>
 * 对象功能:消息日志 Model对象 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:zxh 
 * 创建时间:2012-11-29 16:24:35
 * </pre>
 */
public class MessageLog extends BaseModel {
	/** */
	private static final long serialVersionUID = 1L;

	/**
	 * 消息的类型
	 */
	/**
	 * 邮件信息=1
	 */
	public final static Integer MAIL_TYPE = 1;
	/**
	 * 手机短信
	 */
	public final static Integer MOBILE_TYPE = 2;
	/**
	 * 站内消息
	 */
	public final static Integer INNER_TYPE = 3;

	/**
	 * 成功
	 */
	public final static Integer STATE_SUCCESS = 1;
	/**
	 * 失败
	 */
	public final static Integer STATE_FAIL = 0;
	// 主键
	protected Long id;
	// 主题
	protected String subject;
	// 发送时间
	protected java.util.Date sendTime;
	// 接收者
	protected String receiver;
	// 消息类型 1;邮件信息 2;手机短信 3;内部消息
	protected Integer messageType;
	// 发送状态 1:成功 0:不成功
	protected Integer state;

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 主键
	 * 
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 返回 主题
	 * 
	 * @return
	 */
	public String getSubject() {
		return this.subject;
	}

	/**
	 * 设置 主题
	 * 
	 * @return
	 */
	public void setSendTime(java.util.Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 返回 发送时间
	 * 
	 * @return
	 */
	public java.util.Date getSendTime() {
		return this.sendTime;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/**
	 * 返回 接收者
	 * 
	 * @return
	 */
	public String getReceiver() {
		return this.receiver;
	}

	/**
	 * 返回 消息类型 1;邮件信息 2;手机短信 3;内部消息
	 * 
	 * @return
	 */
	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	/**
	 * 返回 发送状态 1:成功 0:不成功
	 * 
	 * @return
	 */
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof MessageLog)) {
			return false;
		}
		MessageLog rhs = (MessageLog) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.subject, rhs.subject)
				.append(this.sendTime, rhs.sendTime)
				.append(this.receiver, rhs.receiver)
				.append(this.messageType, rhs.messageType)
				.append(this.state, rhs.state).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id)
				.append(this.subject).append(this.sendTime)
				.append(this.receiver).append(this.messageType)
				.append(this.state).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("subject", this.subject)
				.append("sendTime", this.sendTime)
				.append("receiver", this.receiver)
				.append("messageType", this.messageType)
				.append("state", this.state).toString();
	}

}