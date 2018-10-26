package com.hotent.platform.service.jms.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.MessageSend;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.jms.MessageModel;
import com.hotent.platform.service.system.SysPropertyService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.util.ServiceUtil;
public class OpenfireMessageHandler implements IMessageHandler {
	private final Log logger = LogFactory.getLog(OpenfireMessageHandler.class);
	XMPPConnection openfireConnection = null;
	ChatManager systemNotityChatManager = null;
	private String serviceName ;
	
	public String getTitle() {
		 return " 即时消息";
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
		logger.debug("InnerMessage");
		sendMessage(messageSend);
	}
	
	public void sendMessage(MessageSend messageSend){
		if(openfireConnection == null && !hasLogined) getSystemNotityChatManager();
		if(openfireConnection == null) return ;
		
		//获取收信人的账号。
		SysUser user = AppUtil.getBean(SysUserService.class).getById(messageSend.getRid());
		if(user == null) return;
		//获取消息
		Message msg  = getMessageByMessageSend(messageSend);
		try {
			//发送消息
			if(systemNotityChatManager == null) return ;
			
			systemNotityChatManager.createChat(user.getAccount()+"@"+serviceName,new MessageListener() {
				public void processMessage(Chat chat, Message message) {/* 不做回执消息监听器 */}
			}).sendMessage(msg);
		
		} catch (Exception e) {
			logger.warn("连接异常，发送消息失败！");
			e.printStackTrace();
		}
	}
	private Message getMessageByMessageSend(MessageSend messageSend) {
		Message msg = new Message();
		msg.setSubject(messageSend.getSubject());
		msg.setBody(messageSend.getContent());
		//msg.setPacketID(packetID); 做系统通知
		//doSome
		return msg;
	}
	/***
	 * 获取系统通知账号连接信息
	 */
	private boolean hasLogined = false;//无论连接是否失败
	private void getSystemNotityChatManager() {
		hasLogined = true;
		String systemAccount = SysPropertyService.getByAlias("systemNotification.account","admin");
		String systemPassword = SysPropertyService.getByAlias("systemNotification.password","1");
		serviceName = SysPropertyService.getByAlias("openfire.serviceName","vansai");
		
		openfireConnection = null;
		try {
			openfireConnection =new XMPPConnection(serviceName);
			openfireConnection.connect();
			openfireConnection.login(systemAccount,systemPassword);
			systemNotityChatManager	= openfireConnection.getChatManager();
		}catch (Exception e){ //XMPPException
			e.printStackTrace();
			openfireConnection = null;
			logger.error("连接Openfire服务器失败！");
		}
		/*catch (SaslException e) {
			e.printStackTrace();
		} catch (XMPPException e) {
			e.printStackTrace();
		} catch (SmackException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
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
		Map vars = model.getVars();
		if(BeanUtils.isNotEmpty(vars)){
			if (BeanUtils.isNotEmpty(vars.get("copyId"))) {
				Long copuId = (Long) vars.get("copyId");
				url = url+"&copyId="+copuId;
			}
		}
		if (StringUtil.isNotEmpty(url)) {
			url = url.replace("http://", "");
			url = url.substring(url.indexOf("/"), url.length());
		}

		// 发送内容
		 content = ServiceUtil.replaceTemplateTag(
				this.getTemplate(model), model.getReceiveUser().getFullname(),
				this.getSendUserName(model), model.getSubject(), url,
				model.getOpinion(), true);
		 content.replaceAll("<br/>", "/n");
		 
		// 处理nodeMsgTemplate
		content = content.replace("${html表单}", model.getTemplateMap().get("htmlDefForm")).replace("${text表单}", model.getTemplateMap().get("textDefForm"));
		 
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

	@Override
	public boolean getIsDefaultChecked() {
		return true;
	}

	public static void main(String[] args) throws Exception {
		/*XMPPTCPConnectionConfigura config = XMPPTCPConnectionConfiguration.builder()
				.setDebuggerEnabled(true)
				.setUsernameAndPassword("zhangs", "1") 
				.setResource("bpmx3")
				.setServiceName("vansai").build();*/
		XMPPConnection con = new XMPPConnection("vansai");
		con.connect();
		con.login("admin", "1");
		ChatManager chatManager = con.getChatManager();
		 chatManager.createChat("zhangs@vansai", new MessageListener() {
			
			@Override
			public void processMessage(Chat chat, Message message) {
				
			}
		}).sendMessage("sss");;
		
		
	}

}
