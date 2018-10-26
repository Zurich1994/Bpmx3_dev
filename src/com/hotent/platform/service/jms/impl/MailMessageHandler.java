package com.hotent.platform.service.jms.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;

import com.hotent.core.engine.MessageEngine;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.jms.MessageModel;
import com.hotent.platform.service.system.SysTemplateService;
import com.hotent.platform.service.util.ServiceUtil;
public class MailMessageHandler implements IMessageHandler {
     

	private final Log logger = LogFactory.getLog(MailMessageHandler.class);
	@Resource
	private MessageEngine messageEngine;
    @Resource SysTemplateService sysTemplateService;
    
	public String getTitle() {
		return "邮件";
		//return ContextUtil.getMessages("message.mail");//国际化
	}

	public void handMessage(MessageModel model) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setSubject(this.getSubject(model));
		
		if(model.getTo()!=null&&model.getTo().length>0){
			simpleMailMessage.setTo(model.getTo());
			simpleMailMessage.setCc(model.getCc());
		    simpleMailMessage.setBcc(model.getBcc());	
		}else{
			String eamilStr="";
			if (model.getReceiveUser() != null)eamilStr=model.getReceiveUser().getEmail();
			if ( StringUtil.isEmpty(eamilStr)||!StringUtil.isEmail(eamilStr))
				return;// 判断一下邮箱是否为空或不是邮箱，则直接返回
			String[] sendTos = { model.getReceiveUser().getEmail() };
			simpleMailMessage.setTo(sendTos);
		}
		simpleMailMessage.setText(this.getContent(model));
		simpleMailMessage.setSentDate(model.getSendDate());
		messageEngine.sendMail(simpleMailMessage);
		logger.debug("MailModel");
	}

	/**
	 * 获取右键的模版内容
	 */
	private String getTemplate(MessageModel model) {
		String mailTemplate="";
		try {
			mailTemplate=model.getTemplateMap().get(SysTemplate.TEMPLATE_TYPE_HTML);
			mailTemplate = StringUtil.jsonUnescape(mailTemplate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailTemplate;
	}
	private String getSubject(MessageModel model){
		if(model.getTemplateMap()==null)return model.getSubject();
		String title=model.getTemplateMap().get(SysTemplate.TEMPLATE_TITLE);
		title=ServiceUtil.replaceTitleTag(title, model.getReceiveUser().getFullname(),model.getSendUser().getFullname(), model.getSubject(), model.getOpinion());
		return title;
	}
	
	/**
	 * 内容要用模版进行替换，
	 * 如果有模版
	 */
	private String getContent(MessageModel model) {
		String content ="";
		if(model.getTemplateMap()==null)return model.getContent();
		
		String sendUserName="";
		
		if (model.getSendUser() == null) {
			sendUserName = "系统消息";
		} else {
			sendUserName = model.getSendUser().getFullname();
		}
		String url="";
		if(BeanUtils.isNotEmpty(model.getExtId())){
			url= ServiceUtil.getUrl(model.getExtId().toString(), model.getIsTask());
		}
		
		content = ServiceUtil.replaceTemplateTag(this.getTemplate(model),model.getReceiveUser().getFullname(), sendUserName, model.getSubject(), url, model.getOpinion(),false);

		return content;
	}
	
	/**
	 * 默认不勾选邮件
	 */
	public boolean getIsDefaultChecked() {
		return false;
	}

}
