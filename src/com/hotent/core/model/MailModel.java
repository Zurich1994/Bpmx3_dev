package com.hotent.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 邮件实体类
 * @author hotent
 *
 */
public class MailModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 标题
	 */
	private String subject;
	/**
	 * 发送者
	 */
	private String from;
	/**
	 * 接收者
	 */
	private String to[];	
	/**
	 * 抄送者
	 */
	private String[] cc;
	/**
	 * 秘密抄送者
	 */
	private String[] bcc;
	/**
	 * 发送时间
	 */
	private Date sendDate;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 邮件模板
	 */
	private String mailTemplate;
	/**
	 * 模板的数据
	 */
	private Map mailData=null; 
	
	public String getSubject() {
		return subject;
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
	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	public String[] getCc()
	{
		return cc;
	}
	public void setCc(String[] cc)
	{
		this.cc = cc;
	}
	public String[] getBcc()
	{
		return bcc;
	}
	public void setBcc(String[] bcc)
	{
		this.bcc = bcc;
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
	
	public MailModel() {
		// TODO Auto-generated constructor stub
	}
	public String getMailTemplate() {
		return mailTemplate;
	}
	public void setMailTemplate(String mailTemplate) {
		this.mailTemplate = mailTemplate;
	}
	public Map getMailData() {
		return mailData;
	}
	public void setMailData(Map mailData) {
		this.mailData = mailData;
	}
	/**
	 * @author lenovo
	 * 返回邮件实体，便于查看邮件详情
	 */
	public String toString() 
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
