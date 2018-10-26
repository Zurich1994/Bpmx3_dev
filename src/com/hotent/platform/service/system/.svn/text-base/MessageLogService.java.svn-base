package com.hotent.platform.service.system;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.MessageLogDao;
import com.hotent.platform.model.system.MessageLog;

/**
 * <pre>
 * 对象功能:消息日志 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2012-11-29 16:24:35
 * </pre>
 */
@Service
public class MessageLogService extends BaseService<MessageLog> {
	@Resource
	private MessageLogDao dao;

	public MessageLogService() {
	}

	@Override
	protected IEntityDao<MessageLog, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 新增消息日志
	 * @param subject 主题
	 * @param receiver 接受者 ，多个人 ","分隔
	 * @param messageType 消息类型
	 * @param state 状态
	 */
	public void addMessageLog(String subject, String receiver,
			Integer messageType, Integer state) {
		MessageLog messageLog = new MessageLog();
		messageLog.setId(UniqueIdUtil.genId());
		messageLog.setSubject(StringUtils.substring(subject, 0, 50));
		messageLog.setReceiver(receiver);
		messageLog.setMessageType(messageType);
		messageLog.setState(state);
		messageLog.setSendTime(new Date());
		dao.add(messageLog);
	}
}
