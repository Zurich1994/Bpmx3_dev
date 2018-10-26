package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.bpm.BpmUserCondition;


/**
 *<pre>
 * 对象功能: 节点下的人员的配置规则 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-12-31 15:26:17
 *</pre>
 */
@Repository
public class BpmUserConditionDao extends BaseDao<BpmUserCondition>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BpmUserCondition.class;
	}

	/**
	 * 根据流程set获得条件
	 * @param setId
	 * @return
	 */
	public List<BpmUserCondition> getBySetId(Long setId){
		return getBySqlKey("getBySetId", setId);
	}

	public List<BpmUserCondition> getByActDefId(String actDefId) {
		return getBySqlKey("getByActDefId", actDefId);
	}
	
	public List<BpmUserCondition> getByActDefIdWithParent(String actDefId, String parentActDefId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("actDefId", actDefId);
		map.put("parentActDefId", parentActDefId);
		return getBySqlKey("getByActDefIdWithParentId", map);
	}
	
	public List<BpmUserCondition> getByActDefIdForDel(String actDefId) {
		return getBySqlKey("getByActDefIdForDel", actDefId);
	}
	
	public List<BpmUserCondition> getCcByActDefId(String actDefId) {
		return getBySqlKey("getCcByActDefId", actDefId);
	}
	
	public List<BpmUserCondition> getCcByActDefId(String actDefId, String parentActDefId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("actDefId", actDefId);
		map.put("parentActDefId", parentActDefId);
		return getBySqlKey("getCcByActDefIdAndParentId", map);
	}

	/**
	 * 导出专用
	 * @param actDefId
	 * @return
	 */
	public List<BpmUserCondition> getByActDefIdExport(String actDefId) {
		return getBySqlKey("getByActDefIdExport", actDefId);
	}
	
	/**
	 * 删除节点下的人员的配置规则
	 * @param actDefId
	 */
	public void delByActDefId(String actDefId) {
		delBySqlKey("delByActDefId", actDefId);	
	}
	
	/**
	 * 添加关联记录
	 * @param taskId
	 * @param conditionId
	 */
	public void addTaskCondition(Long taskId,Long conditionId){
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("id",UniqueIdUtil.genId());
		map.put("taskId",taskId);
		map.put("conditionId",conditionId);
		
		this.insert("addTaskCondition", map);
	}
	
	/**
	 * 通过conditionId删除关联表中的记录
	 * @param conditionId
	 */
	public void delTaskConditionByConditionId(Long conditionId){
		this.delBySqlKey("delTaskConditionByConditionId", conditionId);
	}
	
	/**
	 * 通过taskID删除关联表中的记录
	 * @param taskId
	 */
	public void delTaskConditionByTaskId(Long taskId){
		this.delBySqlKey("delTaskConditionByTaskId", taskId);
	}
	
	/**
	 * 通过taskID删除condition
	 * @param taskId
	 */
	public void delConditionByTaskId(Long taskId){
		this.delBySqlKey("delConditionByTaskId", taskId);
	}
	
	/**
	 * 通过任务模板ID获取condition
	 * @param taskId
	 * @return
	 */
	public List<BpmUserCondition> getConditionByTaskId(Long taskId){
		return this.getBySqlKey("getConditionByTaskId",taskId);
	}

	/**
	 * 根据流程定义ID和节点ID，获取用户规则设置
	 * @param actDefId 流程定义ID
	 * @param nodeId 节点ID
	 * @return
	 */
	public List<BpmUserCondition> getByActDefIdAndNodeId(String actDefId, String nodeId) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		return this.getBySqlKey("getCcByActDefIdAndNodeId",params);
	}

	/**
	 * 根据流程定义ID、节点ID和类型，获取用户规则设置
	 * @param actDefId
	 * @param nodeId
	 * @param type
	 * @return
	 */
	public List<BpmUserCondition> getByActDefIdAndNodeIdAndType(String actDefId, String nodeId, Integer type) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		params.put("conditionType", type);
		return this.getBySqlKey("getByActDefIdAndNodeIdAndType",params);
	}
}