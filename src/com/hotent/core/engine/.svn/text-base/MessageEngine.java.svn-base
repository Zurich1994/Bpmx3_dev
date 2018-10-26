package com.hotent.core.engine;

import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.hotent.core.sms.IShortMessage;
import com.hotent.core.util.AppUtil;
import com.hotent.platform.model.system.MessageLog;
import com.hotent.platform.model.system.MessageSend;
import com.hotent.platform.service.system.MessageLogService;
import com.hotent.platform.service.system.MessageSendService;

/**
 * 消息引擎
 * 
 * @author ht
 * 
 */
public class MessageEngine {

	private final Log logger = LogFactory.getLog(MessageEngine.class);
	private JavaMailSender mailSender;
	private IShortMessage shortMessage;

	private String fromUser = "";

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 发送邮件
	 * 
	 * @param msg
	 *            邮件对象
	 * @param ifHtml
	 *            是否为html内容
	 */
	public void sendMail(SimpleMailMessage msg) {
		Integer state = MessageLog.STATE_SUCCESS;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
					mimeMessage);
			mimeMessageHelper.setSubject(msg.getSubject());

			mimeMessageHelper.setTo(msg.getTo());
			if (msg.getCc() != null)
				mimeMessageHelper.setCc(msg.getCc());
			if (msg.getBcc() != null)
				mimeMessageHelper.setBcc(msg.getBcc());
			mimeMessageHelper.setFrom(fromUser);
			mimeMessageHelper.setSentDate(new Date());
			mimeMessageHelper.setText(msg.getText(), true);

			mailSender.send(mimeMessage);

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			state = MessageLog.STATE_FAIL;
		}
		// 保存发送消息日志
		MessageLogService messageLogService = (MessageLogService) AppUtil
				.getBean(MessageLogService.class);
		messageLogService
				.addMessageLog(msg.getSubject(),
						StringUtils.join(msg.getTo(), ","),
						MessageLog.MAIL_TYPE, state);
	}

	/**
	 * 发送手机短信
	 * 
	 * @param mobiles
	 * @param content
	 */
	public void sendSms(List<String> mobiles, String content) {
		Integer state = MessageLog.STATE_SUCCESS;
		try {
			if (this.shortMessage == null)
				this.shortMessage = (IShortMessage) AppUtil
						.getBean(IShortMessage.class);
			boolean result = shortMessage.sendSms(mobiles, content);
			state = result ? MessageLog.STATE_SUCCESS : MessageLog.STATE_FAIL;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			state = MessageLog.STATE_FAIL;
		}
		// 保存发送消息日志
		MessageLogService messageLogService = (MessageLogService) AppUtil
				.getBean(MessageLogService.class);
		messageLogService.addMessageLog(content,
				StringUtils.join(mobiles, ","), MessageLog.MOBILE_TYPE, state);
	}

	/**
	 * 发送内部消息
	 * 
	 * @param messageSend
	 */
	public void sendInnerMessage(MessageSend messageSend) {
		Integer state = MessageLog.STATE_SUCCESS;
		try {
			MessageSendService messageSendService = (MessageSendService) AppUtil
					.getBean(MessageSendService.class);
			messageSendService.addMessageSend(messageSend);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			state = MessageLog.STATE_FAIL;
		}
		// 保存发送消息日志
		MessageLogService messageLogService = (MessageLogService) AppUtil
				.getBean(MessageLogService.class);
		messageLogService.addMessageLog(messageSend.getSubject(),
				messageSend.getReceiverName(), MessageLog.INNER_TYPE, state);
	}

}
