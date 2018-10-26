package com.hotent.platform.service.jms;

import java.io.Serializable;
import java.util.Map;

import com.hotent.core.mail.model.Mail;
/**
 * 邮件发送消息模型类
 * 必须序列化
 * @author lenovo
 *
 */
public class SysMailModel implements Serializable{
	//邮件实体
	private Mail mail;
	//外部邮件设置ID
	private Long outMailUserSetingId;
	//扩展用
	private Map vars;
	
	/**
	 * 构造函数
	 * @param informType
	 */
	public SysMailModel(){
		
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public Long getOutMailUserSetingId() {
		return outMailUserSetingId;
	}

	public void setOutMailUserSetingId(Long outMailUserSetingId) {
		this.outMailUserSetingId = outMailUserSetingId;
	}

	public Map getVars() {
		return vars;
	}

	public void setVars(Map vars) {
		this.vars = vars;
	}
	
}