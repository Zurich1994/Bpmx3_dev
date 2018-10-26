package com.hotent.platform.service.system;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.MessageReadDao;
import com.hotent.platform.model.system.MessageRead;
import com.hotent.platform.model.system.SysUser;

/**
 * 对象功能:接收状态 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-14 15:14:52
 */
@Service
public class MessageReadService extends BaseService<MessageRead>
{
	@Resource
	private MessageReadDao dao;
	
	public MessageReadService()
	{
	}
	
	@Override
	protected IEntityDao<MessageRead, Long> getEntityDao() 
	{
		return dao;
	}	
	
	/**
	 * 添加数据MessageRead
	 * @param messageId
	 * @param sysUser
	 * @throws Exception
	 */
	public void addMessageRead(Long messageId, SysUser sysUser) throws Exception{
		
		MessageRead msgRead = dao.getReadByUser(messageId, sysUser.getUserId());
		if(msgRead==null){
			Date now = new Date();
			MessageRead messageRead = new MessageRead();
			messageRead.setId(UniqueIdUtil.genId());
			messageRead.setMessageId(messageId);
			messageRead.setReceiverId(sysUser.getUserId());
			messageRead.setReceiver(sysUser.getFullname());
			messageRead.setReceiveTime(now);
			add(messageRead);
		}
	}
	
	/**
	 * 获得已读此消息的人员
	 * @param messageId
	 * @return
	 */
	public List<MessageRead> getReadByMsgId(Long messageId)
	{
		return dao.getReadByMsgId(messageId);
	}
	
}
