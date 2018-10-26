/**
 * 对象功能:流程节点规则 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2011-12-14 15:41:53
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmNodeRule;

@Repository
public class BpmNodeRuleDao extends BaseDao<BpmNodeRule>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmNodeRule.class;
	}
	/**
	 * 获取所有的任务定义
	 * @param actDefId
	 * @param ruleId
	 * @return
	 */
	public  List<BpmNodeRule>  getByDefIdNodeId(String actDefId,String nodeId)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);

		return getBySqlKey("getByDefIdNodeId", params); 
	}
	
	/**
	 * 更新规则排序。
	 * @param ruleId
	 * @param priority
	 */
	public void reSort(Long ruleId,Long priority){
		Map map=new HashMap();
		map.put("ruleId", ruleId);
		map.put("priority", priority);
		this.update("reSort", map);
	}
	/**
	 * 根据act流程定义Id删除流程节点规则
	 * @param actDefId
	 */
	public void delByActDefId(String actDefId){
		 delBySqlKey("delByActDefId", actDefId);
	}
	/**
	 * 通过act流程定义ID获得节点规则
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeRule> getByActDefId(String actDefId) {
		return getBySqlKey("getByActDefId", actDefId); 
	}
}