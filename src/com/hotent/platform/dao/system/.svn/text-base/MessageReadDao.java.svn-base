/**
 * 对象功能:接收状态 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-14 15:14:52
 */
package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.system.MessageRead;

@Repository
public class MessageReadDao extends BaseDao<MessageRead>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return MessageRead.class;
	}
	/**
	 * 查询某个用户的接收消息
	 * @param queryFilter
	 * @return
	 */
	public List<MessageRead> getMessageReadList(Long messageId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("messageId", messageId);
		return this.getBySqlKey("getAll", param);		
	}
	
	/**
	 * 查找用户是否已读该消息
	 * @param messageId
	 * @param receiverId
	 * @return
	 */
	public MessageRead getReadByUser(Long messageId, Long receiverId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("messageId", messageId);
		param.put("receiverId", receiverId);
		return this.getUnique("getReadByUser", param);
	}
	
	/**
	 * 获得已读此消息的人员
	 * @param messageId
	 * @return
	 */
	public List<MessageRead> getReadByMsgId(Long messageId)
	{
		return this.getBySqlKey("getReadByMsgId", messageId);
	}
	
	/**
	 * 根据消息ID删除已读信息
	 * @param messageId
	 */
	public int delReadByMsgId(Long messageId){
		return delBySqlKey("delByMessageId", messageId);
	}
	
	/**
	 * 根据消息ID删除已读信息
	 * @param messageId
	 */
	public int delReadByMsgIds(Long[] messageIds){
		int delCount = 0;
		if(BeanUtils.isEmpty(messageIds))
			return 0;
		for(Long messageId:messageIds){
			int i=delReadByMsgId(messageId);
			delCount+=i;
		}
		return delCount;
	}
	
}