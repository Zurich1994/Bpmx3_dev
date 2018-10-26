package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.mybatis.IbatisSql;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.system.MessageReceiver;
/**
 * <pre>
 * 对象功能:消息接收者 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-14 15:13:00
 * </pre>
 */
@Repository
public class MessageReceiverDao extends BaseDao<MessageReceiver>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return MessageReceiver.class;
	}

	/**
	 * 查询某个用户的接收消息
	 * 
	 * @param queryFilter
	 * @return
	 */
	public List<MessageReceiver> getMessageReceiverList(Long messageId)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("messageId", messageId);
		return this.getBySqlKey("getAll", param);
	}

	/**
	 * 查询某个用户的接收消息
	 * 
	 * @param queryFilter
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> getReadReplyByUser(Long messageId)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("messageId", messageId);
		String statement = getIbatisMapperNamespace() + "." + "getReadReplyByUser";
		List<Map> list = getSqlSessionTemplate().selectList(statement, param);
		return list;
	}

	/**
	 * 查询某个组织的接收消息
	 * 
	 * @param queryFilter
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> getReadReplyByPath(Long messageId, String path)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("messageId", messageId);
		param.put("path", path+"%");
		String statement = getIbatisMapperNamespace() + "." + "getReadReplyByPath";
		List<Map> list = getSqlSessionTemplate().selectList(statement, param);
		return list;
	}
	
	/**
	 * 根据消息ID删除接收者信息
	 * @param messageId
	 */
	public int delReceiverByMsgId(Long messageId){
		return delBySqlKey("delByMessageId", messageId);
	}
	
	
	/**
	 * 根据消息ID删除接收者信息
	 * @param messageId
	 */
	public int delReceiverByMsgIds(Long[] messageIds){
		int delCount = 0;
		if(BeanUtils.isEmpty(messageIds))
			return 0;
		for(Long messageId:messageIds){
			int i=delReceiverByMsgId(messageId);
			delCount+=i;
		}
		return delCount;
	}
	
	
	/**
	 * 根据消息ID，计算消息发送成功后，接收者收到且末删除该消息的接收者数。
	 * @param id 内部消息ID
	 * @return 接收者数
	 */
	public int getCountByMsgId(Long id){
		IbatisSql ibatisSql = getIbatisSql("getCountByMessageId", id);
		int totalCount=jdbcTemplate.queryForInt(ibatisSql.getSql(),id);
		return totalCount;
	}
}