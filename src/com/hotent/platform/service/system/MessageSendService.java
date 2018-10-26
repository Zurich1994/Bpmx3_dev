package com.hotent.platform.service.system;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.system.MessageReceiverDao;
import com.hotent.platform.dao.system.MessageSendDao;
import com.hotent.platform.model.system.MessageReceiver;
import com.hotent.platform.model.system.MessageSend;
import com.hotent.platform.model.system.SysUser;

/**
 * 对象功能:发送消息 Service类 开发公司:广州宏天软件有限公司 开发人员:csx 创建时间:2012-01-14 15:10:58
 */
@Service
public class MessageSendService extends BaseService<MessageSend>
{
	@Resource
	private MessageSendDao dao;

	@Resource
	private MessageReceiverDao messageReceiverDao;
	public MessageSendService()
	{
	}

	@Override
	protected IEntityDao<MessageSend, Long> getEntityDao()
	{
		return dao;
	}

	/**
	 * 查询某个用户的接收消息
	 * 
	 * @param queryFilter
	 * @return
	 */
	public List<MessageSend> getReceiverByUser(QueryFilter queryFilter){
		return dao.getReceiverByUser(queryFilter);
	}
	
	/**
	 * 获取个人未读信息
	 * @param userId
	 * @return
	 */
	public List<MessageSend> getNotReadMsg(Long receiverId){
		
		return dao.getNotReadMsg(receiverId);
	}

	/**
	 * 插入MessageSend数据，插入MessageReceiver数据
	 * 
	 * @param messageSend
	 * @param curUser
	 * @param receiverOrgName
	 * @throws Exception
	 */
	public void addMessageSend( MessageSend messageSend, SysUser curUser, String receiverId, String receiverName,
			String receiverOrgId, String receiverOrgName)
		throws Exception
	{

		// 插入MessageSend
		if (receiverOrgName.length() > 0 && receiverName.length()>0){
			messageSend.setReceiverName(receiverName + "," + receiverOrgName);
		}

		Long messageId = null;
		if (messageSend.getId() == null){
			messageId = UniqueIdUtil.genId();
			messageSend.setId(messageId);
			messageSend.setUserId(curUser.getUserId());
			messageSend.setUserName(curUser.getFullname());
			Date now = new Date();
			messageSend.setSendTime(now);
			add(messageSend);
		} 
		else{
			messageId = messageSend.getId();
			update(messageSend);
		}

		String[] idArr = receiverId.split(",");
		String[] nameArr = receiverName.split(",");
		String[] orgIdArr = receiverOrgId.split(",");
		String[] orgNameArr = receiverOrgName.split(",");

		// 插入MessageReceiver
		MessageReceiver messageReceiver = null;
		if (receiverId.length() > 0){
			for (int i = 0; i < idArr.length; i++){
				messageReceiver = new MessageReceiver();
				messageReceiver.setId(UniqueIdUtil.genId());
				messageReceiver.setMessageId(messageId);
				if (StringUtil.isNotEmpty(idArr[i])){
					messageReceiver.setReceiverId(Long.parseLong(idArr[i]));
					if (nameArr.length > i)
						messageReceiver.setReceiver(nameArr[i]);
					messageReceiver.setReceiveType(new Short("0"));
				}
				messageReceiverDao.add(messageReceiver);
			}
		}

		if (receiverOrgId.length() > 0){
			for (int i = 0; i < orgIdArr.length; i++){
				messageReceiver = new MessageReceiver();
				messageReceiver.setId(UniqueIdUtil.genId());
				messageReceiver.setMessageId(messageId);
				if (StringUtil.isNotEmpty(orgIdArr[i])){
					messageReceiver.setReceiverId(Long.parseLong(orgIdArr[i]));
					if (orgNameArr.length > i)
						messageReceiver.setReceiver(orgNameArr[i]);
					messageReceiver.setReceiveType(MessageReceiver.TYPE_ORG);
				}
				messageReceiverDao.add(messageReceiver);
			}
		}
	}
	
	/**
	 * 添加只有一个接受者的站内消息
	 * @param messageSend
	 * @throws Exception
	 */
	public void addMessageSend(MessageSend messageSend) throws Exception{		
		dao.add(messageSend);
		MessageReceiver receiver=new MessageReceiver();
		receiver.setCreateBy(messageSend.getCreateBy());
		receiver.setCreatetime(new Date());
		receiver.setId(UniqueIdUtil.genId());
		receiver.setMessageId(messageSend.getId());
		receiver.setReceiverId(messageSend.getRid());
		receiver.setReceiveType(MessageReceiver.TYPE_USER);
		receiver.setReceiver(messageSend.getReceiverName());
		messageReceiverDao.add(receiver);
	}

}
