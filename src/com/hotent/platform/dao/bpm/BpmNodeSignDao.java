/**
 * 对象功能:会签任务投票规则 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2011-12-14 08:41:55
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmNodeSign;

@Repository
public class BpmNodeSignDao extends BaseDao<BpmNodeSign>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmNodeSign.class;
	}
	
	
	/**
	 * 根据发布id和流程节点id取得对象。
	 * @param deployId
	 * @param nodeId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BpmNodeSign getByDefIdAndNodeId(String actDefId,String nodeId)
	{
		Map map=new HashMap();
		map.put("actDefId", actDefId);
		map.put("nodeId", nodeId);
		BpmNodeSign model=(BpmNodeSign) this.getUnique("getByDefIdAndNodeId", map);
		return model;
	}
	
	/**
	 * 根据发布id和流程节点id取得对象。
	 * @param deployId
	 * @param nodeId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<BpmNodeSign> getByActDefId(String actDefId)
	{
		return getBySqlKey("getByActDefId",actDefId);
	}
	
	/**
	 * 根据act流程定义Id删除会签任务投票规则
	 * @param actDefId
	 */
	public void delActDefId(String actDefId){
		 delBySqlKey("delByActDefId", actDefId);
		
	}
}