package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang.StringUtils;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.jms.MessageProducer;
import com.hotent.core.model.InnerMessage;
import com.hotent.core.model.MailModel;
import com.hotent.core.model.SmsMobile;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.bpm.BpmNodeMessage;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.jms.MessageModel;
import com.hotent.platform.service.util.ServiceUtil;

/**
 * 发送消息任务任务。
 * 
 * @author ray
 * 
 */
public class MessageTask implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		sendMessage(execution);
	}

	private void sendMessage(DelegateExecution execution) throws Exception {
		BpmNodeMessageService bpmNodeMessageService = (BpmNodeMessageService) AppUtil.getBean(BpmNodeMessageService.class);
		ProcessRunService processRunService= (ProcessRunService) AppUtil.getBean(ProcessRunService.class);
		
		String actDefId = execution.getProcessDefinitionId();
		String nodeId = execution.getCurrentActivityId();
		String actInstId = execution.getProcessInstanceId();
		Map<String,Object> variables=execution.getVariables();
		ProcessRun processRun = processRunService.getByActInstanceId(Long.valueOf(actInstId));
		Long runId = processRun.getRunId();
		String processTitle = processRun.getSubject();
		SysUser currentUser=ContextUtil.getCurrentUser();
		if(currentUser==null){
			currentUser=new SysUser();
			currentUser.setFullname("系统");
			currentUser.setUserId(0L);
		}
		List<BpmNodeMessage> bpmNodeMessages = bpmNodeMessageService.getNodeMessages(actDefId, nodeId);
		
		for(BpmNodeMessage message:bpmNodeMessages){
			Short messageType = message.getMessageType();
			if(BeanUtils.isEmpty(message)||message.getIsSend()==0)continue;
			
			if(messageType.equals(BpmNodeMessage.MESSAGE_TYPE_INNER)){
				sendInnerMessage(execution, message, currentUser, processTitle, runId, variables);
			}else if(messageType.equals(BpmNodeMessage.MESSAGE_TYPE_MAIL)){
				sendMailMessage(execution, message, currentUser, processTitle, runId, variables);
			}else if(messageType.equals(BpmNodeMessage.MESSAGE_TYPE_SMS)){
				sendSmsMessage(execution, message.getTemplate(), processTitle, currentUser,variables);
			}
		}
	}

	/**
	 * 发送手机短信
	 * @param execution
	 * @param template
	 * @param title
	 * @param currentUser
	 */
	private void sendSmsMessage(DelegateExecution execution,String template,String title,SysUser currentUser,Map<String,Object> variables) {
		MessageProducer messageProducer = (MessageProducer) AppUtil.getBean(MessageProducer.class);
		BpmNodeMessageService bpmNodeMessageService = (BpmNodeMessageService) AppUtil.getBean(BpmNodeMessageService.class);
		List<SysUser> users=bpmNodeMessageService.getSmsReceiver( execution,currentUser.getUserId());
		if(BeanUtils.isEmpty(users)) return;
		
		for(SysUser user:users){
			String phoneNumber = user.getMobile();
			if(StringUtil.isEmpty(phoneNumber) || !StringUtil.isMobile(phoneNumber)) continue;
			
			String content = ServiceUtil.replaceTemplateTag(template, user.getFullname() , currentUser.getFullname() , title, "", null ,false);
			content=BpmUtil.getTitleByRule(content, variables);
//			SmsMobile smsMobile = new SmsMobile();
//			smsMobile.setPhoneNumber(user.getMobile());
//			smsMobile.setSmsContent(content);
//			messageProducer.send(smsMobile);
			//消息发送模型统一用MessageModel
			MessageModel messageModel=new MessageModel(BpmConst.MESSAGE_TYPE_SMS);
			messageModel.setContent(content);
			messageModel.setReceiveUser(user);
			messageModel.setSendUser(currentUser);
			messageProducer.send(messageModel);	
		}
	}
	
	/**
	 * 发送邮件
	 * @param execution
	 * @param message
	 * @param currentUser
	 * @param title
	 * @param runId
	 * @param variables
	 */
	private void sendMailMessage(DelegateExecution execution,BpmNodeMessage message,SysUser currentUser,String title,Long runId,Map<String,Object>variables) {
		MessageProducer messageProducer = (MessageProducer) AppUtil.getBean(MessageProducer.class);
		BpmNodeMessageService bpmNodeMessageService = (BpmNodeMessageService) AppUtil.getBean(BpmNodeMessageService.class);
		List<SysUser> mailReceiver = bpmNodeMessageService.getMailReceiver( execution,currentUser.getUserId());
		List<SysUser> mailCopyTo = bpmNodeMessageService.getMailCopyTo( execution,currentUser.getUserId());
		List<SysUser> mailBcc = bpmNodeMessageService.getMailBcc( execution,currentUser.getUserId());
		List<String> receiverMails = new ArrayList<String>();
		List<String> receiverNames = new ArrayList<String>();
		for(SysUser user:mailReceiver){
			String mail = user.getEmail();
			if(StringUtil.isNotEmpty(mail) && StringUtil.isEmail(mail)){
				receiverMails.add(mail);
			}
			receiverNames.add(user.getFullname());
		}
		List<String> receiverCopyTos = new ArrayList<String>();
		for(SysUser user:mailCopyTo){
			String mail = user.getEmail();
			if(StringUtil.isNotEmpty(mail) && StringUtil.isEmail(mail)){
				receiverCopyTos.add(mail);
			}
		}
		List<String> receiverBccs = new ArrayList<String>();
		for(SysUser user:mailBcc){
			String mail = user.getEmail();
			if(StringUtil.isNotEmpty(mail) && StringUtil.isEmail(mail)){
				receiverBccs.add(mail);
			}
		}
		String url = ServiceUtil.getUrl(runId.toString(), false);
		
		String subject = ServiceUtil.replaceTitleTag(message.getSubject() , StringUtils.join(receiverNames,",") ,currentUser.getFullname(), title,"");
		String content = ServiceUtil.replaceTemplateTag(message.getTemplate(), StringUtils.join(receiverNames,",") , currentUser.getFullname() , title, url, "" ,false);
//		MailModel mailModel = new MailModel();
//		mailModel.setSubject(subject);
//		mailModel.setContent(BpmUtil.getTitleByRule(content, variables));
//		mailModel.setSendDate(new Date());
//		mailModel.setTo(receiverMails.toArray(new String[0]));
//		mailModel.setCc(receiverCopyTos.toArray(new String[0]));
//		mailModel.setBcc(receiverBccs.toArray(new String[0]));
//		messageProducer.send(mailModel);
		//消息发送模型统一用MessageModel
		content=BpmUtil.getTitleByRule(content, variables);
		MessageModel messageModel=new MessageModel(BpmConst.MESSAGE_TYPE_MAIL);
		messageModel.setSubject(subject);
		messageModel.setContent(content);
		messageModel.setSendDate(new Date());
		messageModel.setTo(receiverMails.toArray(new String[0]));
		messageModel.setCc(receiverCopyTos.toArray(new String[0]));
		messageModel.setBcc(receiverBccs.toArray(new String[0]));
		messageProducer.send(messageModel);	
	}

	/**
	 * 发送站内消息
	 * @param execution
	 * @param message
	 * @param currentUser
	 * @param title
	 * @param runId
	 * @param variables
	 */
	private void sendInnerMessage(DelegateExecution execution,BpmNodeMessage message,SysUser currentUser,String title,Long runId,Map<String,Object>variables) {
		MessageProducer messageProducer = (MessageProducer) AppUtil.getBean(MessageProducer.class);
		BpmNodeMessageService bpmNodeMessageService = (BpmNodeMessageService) AppUtil.getBean(BpmNodeMessageService.class);
		List<SysUser> users=bpmNodeMessageService.getInnerReceiver( execution,currentUser.getUserId());
		if(BeanUtils.isEmpty(users)) return;
		
		for(SysUser user:users){
			String url = ServiceUtil.getUrl(runId.toString(), false );
			
			String subject = ServiceUtil.replaceTitleTag(message.getSubject() , user.getFullname() ,currentUser.getFullname(), title,"");
			String content = ServiceUtil.replaceTemplateTag(message.getTemplate(), user.getFullname() , currentUser.getFullname() , title, url, "" ,false);
//			InnerMessage innerModel = new InnerMessage();
//			innerModel.setContent(BpmUtil.getTitleByRule(content, variables));
//			innerModel.setSendDate(new Date());
//			innerModel.setSubject(subject);
//			innerModel.setFrom(currentUser.getUserId().toString());
//			innerModel.setFromName(currentUser.getFullname());
//			innerModel.setTo(user.getUserId().toString());
//			innerModel.setToName(user.getFullname());
//			messageProducer.send(innerModel);
			content=BpmUtil.getTitleByRule(content, variables);
			//消息发送模型统一用MessageModel
			MessageModel messageModel=new MessageModel(BpmConst.MESSAGE_TYPE_INNER);
			messageModel.setSubject(subject);
			messageModel.setContent(content);
			messageModel.setSendDate(new Date());
			messageModel.setReceiveUser(user);
			messageModel.setSendUser(currentUser);
			messageProducer.send(messageModel);	
		}
		
		
	}

}
