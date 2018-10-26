package com.hotent.platform.service.jms.impl;

import java.util.Date;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.hotent.core.engine.MessageEngine;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.MessageSend;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.jms.MessageModel;
import com.hotent.platform.service.system.SysTemplateService;
import com.hotent.platform.service.util.ServiceUtil;


public class InnerMessageHandler implements IMessageHandler {
     

	private final Log logger = LogFactory.getLog(InnerMessageHandler.class);
	@Resource
	private MessageEngine messageEngine;
    @Resource SysTemplateService sysTemplateService;
    
	
	public String getTitle() {
		 return " 站内消息";
		//return ContextUtil.getMessages("message.inner");
	}

	public void handMessage(MessageModel model) {
		MessageSend messageSend = new MessageSend();

		if (model.getReceiveUser() == null
				|| model.getSendUser() == null)
			return;
		messageSend.setId(UniqueIdUtil.genId());
		messageSend.setUserName(this.getSendUserName(model));// 发送人姓名
		messageSend.setUserId(model.getSendUser().getUserId());// 发送人ID
		messageSend.setSendTime(model.getSendDate());
		messageSend.setMessageType(MessageSend.MESSAGETYPE_FLOWTASK);
		messageSend.setContent(this.getContent(model));
		messageSend.setSubject(this.getSubject(model));
		messageSend.setCreateBy(model.getSendUser().getUserId());// 创建人ID
		messageSend.setRid(model.getReceiveUser().getUserId());// 接收人ID
		messageSend.setReceiverName(model.getReceiveUser().getFullname());// 接收人姓名
		messageSend.setCreatetime(model.getSendDate() == null ? new Date(): model.getSendDate());
		messageEngine.sendInnerMessage(messageSend);
		logger.debug("InnerMessage");
	}

	/**
	 * 获取短信的模版内容
	 */
	private String getTemplate(MessageModel model) {
		String smsTemplate="";
		try {
			smsTemplate=model.getTemplateMap().get(SysTemplate.TEMPLATE_TYPE_HTML);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return smsTemplate;
	}
	
	
	private String getSendUserName(MessageModel model) {
		String sendUserName = "";
		if (model.getSendUser() == null) {
			sendUserName = "系统消息";
		} else {
			sendUserName = model.getSendUser().getFullname();
		}
		return sendUserName;
	}

	/**
	 * 内容要用模版进行替换，
	 * 如果有模版
	 */
	private String getContent(MessageModel model) {
		String content ="";
		if(model.getTemplateMap()==null)return model.getContent();
       
		Long id = model.getExtId();// 流程运行ID或任务ID（由isTask决定）
		boolean isTask = model.getIsTask(); // 是否是任务（taskId 是true,runId 是false）
		// 链接地址

		String url = "";
		if (BeanUtils.isNotEmpty(id)) {
			url = ServiceUtil.getUrl(id.toString(), isTask);
		}
		if (StringUtil.isNotEmpty(url)) {
			url = url.replace("http://", "");
			url = url.substring(url.indexOf("/"), url.length());
		}

		// 发送内容
		 content = ServiceUtil.replaceTemplateTag(
				this.getTemplate(model), model.getReceiveUser().getFullname(),
				this.getSendUserName(model), model.getSubject(), url,
				model.getOpinion(), false);

		return content;
	}
	
	private String getSubject(MessageModel model) {
		if(model.getTemplateMap()==null)return model.getSubject();
		// 发送标题
		String title = ServiceUtil.replaceTitleTag(
				model.getTemplateMap().get(SysTemplate.TEMPLATE_TITLE), model
						.getReceiveUser().getFullname(), this
						.getSendUserName(model), model.getSubject(), model
						.getOpinion());

		return title;
	}

	public boolean getIsDefaultChecked() {
		return true;
	}


}
