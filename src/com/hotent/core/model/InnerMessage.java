package com.hotent.core.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 内部消息对象。
 * @author ray
 *
 */
public class InnerMessage implements Serializable {
	/**
	 * 标题
	 */
	private String subject;
	/**
	 * 发送者
	 */
	private String from;
	/**
	 * 发送者名称
	 */
	private String fromName;
	/**
	 * 接收者
	 */
	private String to;
	/**
	 * 接受者名称
	 */
	private String toName;

	/**
	 * 发送时间
	 */
	private Date sendDate;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 是否可回复
	 */
	private short canReply;

	public short getCanReply() {
		return canReply;
	}

	public void setCanReply(short canReply) {
		this.canReply = canReply;
	}

	public String getSubject() {
		return subject;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
