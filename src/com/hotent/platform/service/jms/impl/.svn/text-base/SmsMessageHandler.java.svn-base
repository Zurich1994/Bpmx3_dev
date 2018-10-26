package com.hotent.platform.service.jms.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.hotent.core.engine.MessageEngine;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.jms.MessageModel;
import com.hotent.platform.service.system.SysTemplateService;
import com.hotent.platform.service.util.ServiceUtil;
public class SmsMessageHandler implements IMessageHandler {
     

	private final Log logger = LogFactory.getLog(SmsMessageHandler.class);
	@Resource
	private MessageEngine messageEngine;
    @Resource SysTemplateService sysTemplateService;
    
	public String getTitle() {
		return "短信";
		//return ContextUtil.getMessages("message.sms");
	}

	public void handMessage(MessageModel model) {
		String strMobile="";
		if(model.getReceiveUser()!=null)strMobile=model.getReceiveUser().getMobile();
		if(StringUtil.isEmpty(strMobile)||!StringUtil.isMobile(strMobile))return;//手机号为空或不是手机号，直接返回
		List<String> mobiles = new ArrayList<String>();
		mobiles.add(strMobile);
		messageEngine.sendSms(mobiles, this.getContent(model));
		logger.debug("Sms");
	}

	/**
	 * 获取短信的模版内容
	 */
	private String getTemplate(MessageModel model) {
		String smsTemplate="";
		try {
			smsTemplate=model.getTemplateMap().get(SysTemplate.TEMPLATE_TYPE_PLAIN);
			smsTemplate = StringUtil.jsonUnescape(smsTemplate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return smsTemplate;
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
		
		content = ServiceUtil.replaceTemplateTag(this.getTemplate(model),
				model.getReceiveUser().getFullname(), sendUserName, model.getSubject(), "", model.getOpinion(),true);
		return content;
	}

	public boolean getIsDefaultChecked() {
		return false;
	}

}
