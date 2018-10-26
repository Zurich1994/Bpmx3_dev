package com.hotent.platform.service.jms;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.hotent.platform.model.system.SysUser;
/**
 * 消息模型类
 * 必须序列化
 * @author lenovo
 *
 */
public class MessageModel implements Serializable{
	//接收人
	private SysUser receiveUser;
	//发送人
	private SysUser  sendUser;
	//消息类型，BpmConst.MESSAGE_TYPE_SMS， BpmConst.MESSAGE_TYPE_MAIL,BpmConst.MESSAGE_TYPE_INNER
	private String informType;
	//模版
	private Map<String,String>  templateMap;
	
	//发送内容,无模版就取发送内容
	private String content ;
	//主题
	private String subject;
	//事件原因
	private String opinion;
	//发送时间
	private Date sendDate;
	

	//任务ID或runid
	private Long extId;
	
	private boolean isTask;
	//扩展用
	private Map vars;
	
	// 邮件群发，邮件接收者
	private String[] to;	
	//邮件抄送者
	private String[] cc;
	//右键秘密抄送者
	private String[] bcc;
	
	/**
	 * 构造函数
	 * 消息模型必须指定消息类型
	 * @param informType
	 */
	public MessageModel(String informType){
		this.informType=informType;
		
	}
	
	public boolean getIsTask() {
		return isTask;
	}

	public void setIsTask(boolean isTask) {
		this.isTask = isTask;
	}     
	/**
	 * 获取发送时间
	 */
	public Date getSendDate() {
		if(sendDate==null)
			return new Date();
		return sendDate;
	}

	/**
	 * 设置发送时间
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * 获取消息内容模版
	 */
	public Map<String, String> getTemplateMap() {
		return templateMap;
	}

	/**
	 * 设置消息内容模版
	 */
	public void setTemplateMap(Map<String, String> templateMap) {
		this.templateMap = templateMap;
	}

	/**
	 * 获取接收人
	 */
	public SysUser getReceiveUser() {
		return receiveUser;
	}

	/**
	 * 设置接收人
	 */
	public void setReceiveUser(SysUser receiveUser) {
		this.receiveUser = receiveUser;
	}

	/**
	 * 获取发送人
	 */
	public SysUser getSendUser() {
		return sendUser;
	}

	/**
	 * 设置发送人
	 */
	public void setSendUser(SysUser sendUser) {
		this.sendUser = sendUser;
	}

	/**
	 * 获取信息类型
	 */
	public String getInformType() {
		return informType;
	}

	/**
	 * 设置信息类型，
	 * informType一定要指定
	 * BpmConst.MESSAGE_TYPE_MAIL
	 * BpmConst.MESSAGE_TYPE_SMS
	 * BpmConst.MESSAGE_TYPE_INNER
	 */
	public void setInformType(String informType) {
		this.informType = informType;
	}


	/**
	 * 获取主题
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 设置主题
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 获取意见原因
	 */
	public String getOpinion() {
		return opinion;
	}

	/**
	 * 设置意见原因
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}



	public Long getExtId() {
		return extId;
	}

	public void setExtId(Long extId) {
		this.extId = extId;
	}

	/**
	 * 设置变量
	 */
	public Map getVars() {
		return vars;
	}

	/**
	 * 获取变量
	 */
	public void setVars(Map vars) {
		this.vars = vars;
	}

	/**
	 * 获取发送邮件地址
	 */
	public String[] getTo() {
		return to;
	}

	/**
	 * 设置发送邮件地址
	 */
	public void setTo(String[] to) {
		this.to = to;
	}

	/**
	 * 获取抄送送邮件地址
	 */
	public String[] getCc() {
		return cc;
	}

	/**
	 *设置抄送送邮件地址
	 */
	public void setCc(String[] cc) {
		this.cc = cc;
	}

	/**
	 * 获取暗送送邮件地址
	 */
	public String[] getBcc() {
		return bcc;
	}

	/**
	 * 设置暗送送邮件地址
	 */
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}