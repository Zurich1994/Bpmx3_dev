/**
 * 对象功能:流程节点邮件 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-12-31 15:48:59
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmNodeMessage;

@Repository
public class BpmNodeMessageDao extends BaseDao<BpmNodeMessage>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmNodeMessage.class;
	}
	/**
	 * 通过流程发布ID及节点id获取流程设置节点实体
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public List<BpmNodeMessage> getMessageByActDefIdNodeId(String actDefId,String nodeId)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);	
		return this.getBySqlKey("getMessageByActDefIdNodeId", params);
	}
	/**
	 * 根据act流程定义Id删除流程消息节点
	 * @param actDefId
	 */
	public void delByActDefId(String actDefId){
		delBySqlKey("delByActDefId", actDefId);
	}
	
	public  void delByActDefIdAndNodeId(String actDefId,String nodeId){
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("actDefId", actDefId);
		param.put("nodeId", nodeId);
		delBySqlKey("delByActDefIdAndNodeId", param);
	}
	
	/**
	 * 根据流程定义ID取得消息列表。
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeMessage> getByActDefId(String actDefId) {
		return this.getBySqlKey("getByActDefId", actDefId);
	}
	public List<BpmNodeMessage> getByActDefIdAndNodeId(String actDefId,
			String nodeId) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		return this.getBySqlKey("getByActDefIdAndNodeId", params);
	}
	
}