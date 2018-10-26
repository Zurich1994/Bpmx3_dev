package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.system.MessageReadDao;
import com.hotent.platform.dao.system.MessageReceiverDao;
import com.hotent.platform.dao.system.MessageReplyDao;
import com.hotent.platform.dao.system.MessageSendDao;
import com.hotent.platform.model.system.MessageReceiver;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;

/**
 * 对象功能:消息接收者 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2012-01-14 15:13:00
 */
@Service
public class MessageReceiverService extends BaseService<MessageReceiver>
{
	@Resource
	private MessageReceiverDao dao;
	@Resource
	private MessageReceiverDao redao;
	@Resource
	private SysOrgService orgSevice;
	
	@Resource
	private MessageSendDao messageSendDao;
	@Resource
	private MessageReplyDao messageReplyDao;
	@Resource
	private MessageReadDao messageReadDao;
	@Resource
	private MessageReadService readService;
	
	public MessageReceiverService()
	{
	}
	
	@Override
	protected IEntityDao<MessageReceiver, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	public List<MessageReceiver> getByMessageId(Long messageId){
		List<MessageReceiver> messageReceivers=null;
		messageReceivers=dao.getMessageReceiverList(messageId);
		return messageReceivers;
	}
	
	
	
	/**
	 * 查询某个用户的接收消息
	 * @param queryFilter
	 * @return
	 */
	public List<Map> getMessageReadReply(Long messageId)
	{
		String path="";
		List<Map> list= new ArrayList<Map>();
		List<Map> listByUser= new ArrayList<Map>();
		List<Map> listByOrg= new ArrayList<Map>();
		List<MessageReceiver> reList=redao.getMessageReceiverList(messageId);
		if(reList==null&&reList.size()==0) return null;
		for(MessageReceiver reModel: reList)
		{
			if(reModel.getReceiveType().equals(MessageReceiver.TYPE_USER))//收信的对象是用户
			{
				listByUser=dao.getReadReplyByUser(messageId);
				list.addAll(listByUser);
			}
			else//收信的对象是组织
			{
				SysOrg sysOrg=orgSevice.getById(reModel.getReceiverId());
				if(sysOrg==null) continue;
					path=sysOrg.getPath();
					if(StringUtil.isEmpty(path)) continue;						
						listByOrg=dao.getReadReplyByPath(messageId,path);
						list.addAll(listByOrg);
			}
			
		}	
		return list;
	}
	
	/**
	 * 根据消息者Id,删除消息者信息。
	 * 当要删除的消息者是相应消息的最后一个末删除的消息者，将级联删除所有与相应消息相关的消息数据，
	 * 包括消息发送、消息回复、消息已读。
	 * @param ids 消息者ID
	 */
	public void delByIdsCascade(Long[] ids){
		if(BeanUtils.isEmpty(ids))
			return;
		for (Long id : ids){
			MessageReceiver receiver = getById(id);
			Long messageId=receiver.getMessageId();
			int count = dao.getCountByMsgId(messageId);
			if(count==1){
				messageReplyDao.delReplyByMsgId(messageId);
				messageReadDao.delReadByMsgId(messageId);
				delById(id);
				messageSendDao.delById(messageId);
			}else{
				delById(id);
			}
		}
	}
	
	/**
	 * 标记为已读
	 * @param ids
	 * @throws Exception 
	 */
	public void updateReadStatus(Long[] ids) throws Exception{
		if(ids.length==0)return;
		SysUser sysUser = ContextUtil.getCurrentUser();
		for(Long id : ids){
			if(id>0){
				MessageReceiver receiver = dao.getById(id);
				if(BeanUtils.isEmpty(receiver)) continue;
				readService.addMessageRead(receiver.getMessageId(), sysUser);
			}
		}
	}
}
