/**
 * 对象功能:InnoDB free: 8192 kB Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2011-12-06 13:41:44
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmNodeSet;

@Repository
public class BpmNodeSetDao extends BaseDao<BpmNodeSet>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmNodeSet.class;
	}
	
	/**
	 * 根据流程设置ID取流程节点设置
	 * @param defId
	 * @return
	 */
	public List<BpmNodeSet> getByDefId(Long defId)
	{
		return getBySqlKey("getByDefId", defId);
	}
	
	/**
	 * 
	 * 根据流程设置ID取流程节点设置(所有的)
	 * @param defId
	 * @return
	 */
	public List<BpmNodeSet> getAllByDefId(Long defId){
		return getBySqlKey("getAllByDefId", defId);
	}
	
	/**
	 * 根据流程的定义ID获取流程节点设置列表。
	 * @param actDefId	流程定义ID。
	 * @return
	 */
	public List<BpmNodeSet> getByActDef(String actDefId){
		return getBySqlKey("getByActDef", actDefId);
	}
	
	
	
	/**
	 * 通过节点Id及流程定义Id获取节点设置实体
	 * @param defId
	 * @param nodeId
	 * @return
	 */
	public BpmNodeSet getByDefIdNodeId(Long defId,String nodeId)
	{
		
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("defId", defId);
		params.put("nodeId", nodeId);
		return getUnique("getByDefIdNodeId",params);
	}
	
	/**
	 * 通过节点Id及流程定义Id获取节点设置实体
	 * @param defId
	 * @param nodeId
	 * @return
	 */
	public BpmNodeSet getByDefIdNodeId(Long defId,String nodeId, String parentActDefId)
	{
		
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("defId", defId);
		params.put("nodeId", nodeId);
		params.put("parentActDefId", parentActDefId);
		
		return getUnique("getByDefIdNodeIdAndParentActDefId", params);
	}
	
	/**
	 * 通过流程发布ID及节点id获取流程设置节点实体
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public BpmNodeSet getByActDefIdNodeId(String actDefId,String nodeId, String parentActDefId)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		params.put("parentActDefId", parentActDefId);
		return getUnique("getByActDefIdNodeIdAndParentActDefId", params);
	}
	
	/**
	 * 通过流程发布ID及节点id获取流程设置节点实体
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public BpmNodeSet getByActDefIdNodeId(String actDefId,String nodeId)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		return getUnique("getByActDefIdNodeId",params);
	}
	
	/**
	 * 取得某个流程定义中对应的某个节点为汇总节点的配置
	 * @param actDefId
	 * @param joinTaskKey
	 * @return
	 */
	public BpmNodeSet getByActDefIdJoinTaskKey(String actDefId,String joinTaskKey)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actDefId", actDefId);
		params.put("joinTaskKey", joinTaskKey);
		return getUnique("getByActDefIdJoinTaskKey",params);
	}
	
	/**
	 * 根据formKey获取关联的BpmNodeSet
	 * @param formKey
	 * @return
	 */
	public List<BpmNodeSet> getByFormKey(String formKey)
	{
		return this.getBySqlKey("getByFormKey", formKey);
	}
	
	/**
	 * 根据流程defId删除流程节点或删除以actDefId为父流程定义ID的流程节点。
	 * @param defId
	 * @param actDefId
	 */
	public void delByDefId(Long defId, String actDefId){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("defId", defId);
		params.put("parentActDefId", actDefId);
		this.delBySqlKey("delByDefId", params);
	}
	
	/**
	 * 根据流程定义Id和 表单类型查询 默认表单和起始表单。
	 * @param defId
	 * @param setType 值为(1，开始表单，2，全局表单)
	 * @return
	 */
	public BpmNodeSet getBySetType(Long defId,Short setType){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("defId", defId);
		params.put("setType", setType);
		return this.getUnique("getBySetType", params);
	}
	
	/**
	 * 根据子流程定义Id、父流程定义Id和 表单类型查询 默认表单和起始表单。
	 * @param defId
	 * @param setType 值为(1，开始表单，2，全局表单)
	 * @param parentActDefId
	 * @return
	 */
	public BpmNodeSet getBySetTypeAndParentActDefId(Long defId,Short setType, String parentActDefId){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("defId", defId);
		params.put("setType", setType);
		params.put("parentActDefId", parentActDefId);
		return this.getUnique("getBySetTypeAndParentActDefId", params);
	}
	
	/**
	 * 根据流程定义获取开始和全局表单的配置。
	 * @param defId
	 * @return
	 */
	public BpmNodeSet getByStartGlobal(Long defId){
		List<BpmNodeSet> list=this.getBySqlKey("getByStartGlobal", defId);
		if(list.size()==0)
			return null;
		return list.get(0);
	}
	
	/**
	 * 根据流程定义获取开始和全局表单的配置。
	 * @param defId
	 * @return
	 */
	public BpmNodeSet getByStartGlobal(Long defId, String parentActDefId){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("defId", defId);
		params.put("parentActDefId", parentActDefId);
		List<BpmNodeSet> list=this.getBySqlKey("getByStartGlobalParentActDefId", params);
		if(list.size()==0)
			return null;
		return list.get(0);
	}
	
	/**
	 * 取得非节点的NODESET.
	 * @param defId
	 * @return
	 */
	public List<BpmNodeSet>  getByOther(Long defId){
		List<BpmNodeSet> list=this.getBySqlKey("getByOther", defId);
		return list;
	}
	
	/**
	 * 删除起始和缺省的表单。
	 * @param defId
	 */
	public void delByStartGlobalDefId(Long defId){
		this.delBySqlKey("delByStartGlobalDefId", defId);
	}
	
	/**
	 * 删除起始和缺省的表单(子流程)。
	 * @param defId
	 * @param parentActDefId
	 */
	public void delByStartGlobalDefId(Long defId, String parentActDefId){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("defId", defId);
		params.put("parentActDefId", parentActDefId);
		this.delBySqlKey("delByStartGlobalDefIdAndParentActDefId", params);
	}
	
	/**
	 * 根据defid 获取节点数据，并转换为map。
	 * @param defId
	 * @return
	 */
	public Map<String, BpmNodeSet> getMapByDefId(Long defId){
		Map<String, BpmNodeSet> map=new HashMap<String, BpmNodeSet>();
		List<BpmNodeSet> list= getByDefId(defId);
		for(BpmNodeSet bpmNodeSet:list){
			map.put(bpmNodeSet.getNodeId(), bpmNodeSet);
		}
		return map;
	}
	
	/**
	 * 根据defid和 parentActDefId获取节点数据，并转换为map。
	 * @param defId
	 * @param parentActDefId
	 * @return
	 */
	public Map<String, BpmNodeSet> getMapByDefId(Long defId, String parentActDefId){
		Map<String, BpmNodeSet> map=new HashMap<String, BpmNodeSet>();
		List<BpmNodeSet> list = getByDefIdAndParentActDefId(defId, parentActDefId,false);
		for(BpmNodeSet bpmNodeSet:list){
			map.put(bpmNodeSet.getNodeId(), bpmNodeSet);
		}
		return map;
	}
	
	/**
	 * 根据actDefId获取流程节点数据。
	 * <pre>
	 * 这个关联了在线表单最新的表单id。
	 * </pre>
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeSet> getByActDefId(String actDefId){
		return this.getBySqlKey("getByActDefId", actDefId);
	}
	
	/**
	 * 根据actDefId获取流程节点数据。
	 * <pre>
	 * 这个关联了在线表单最新的表单id。
	 * </pre>
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeSet> getByActDefId(String actDefId, String parentActDefId){
		Map<String, String> params=new HashMap<String, String>();
		params.put("actDefId", actDefId);
		params.put("parentActDefId", parentActDefId);
		return this.getBySqlKey("getByActDefIdAndParentId", params);
	}
	
	/**
	 * 根据parentActDefId和defId获取流程节点数据。
	 * @param defId
	 * @param parentActDefId
	 * @param isAll (true :查询包括全局和业务表单的bpmNodeSet ，false:只是任务节点)
	 * @return
	 */
	public List<BpmNodeSet> getByDefIdAndParentActDefId(Long defId, String parentActDefId,boolean isAll){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("defId", defId);
		params.put("parentActDefId", parentActDefId);
		if (isAll) {
			params.put("isAll", "isAll");
		}
		return this.getBySqlKey("getByDefIdAndParentActDefId", params);
	}
	
	/**
	 * 根据actdefid 获取在线表单的数据。
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeSet> getOnlineFormByActDefId(String actDefId){
		return this.getBySqlKey("getOnlineFormByActDefId", actDefId);
	}
	
	/**
	 * 根据actdefid 和父流程定义ID获取在线表单的数据。
	 * @param actDefId
	 * @param parentActDefId
	 * @return
	 */
	public List<BpmNodeSet> getOnlineFormByActDefId(String actDefId, String parentActDefId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("parentActDefId", parentActDefId);
		params.put("actDefId", actDefId);
		return this.getBySqlKey("getOnlineFormByActDefIdAndParentDefId", params);
	}
	
	/**
	 * 通过定义ID及节点Id更新isJumpForDef字段的设置
	 * @param nodeId
	 * @param actDefId
	 * @param isJumpForDef
	 */
	public void updateIsJumpForDef(String nodeId,String actDefId,Short isJumpForDef){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("isJumpForDef", isJumpForDef);
		params.put("nodeId", nodeId);
		params.put("actDefId", actDefId);
		update("updateIsJumpForDef",params);
	}
	
	public List<BpmNodeSet> getByParentActDefId(String parentActDefId){
		return this.getBySqlKey("getByParentActDefId", parentActDefId);
	}
	
	public void delByDefIdAndParentActDefId(Long defId, String parentActDefId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("defId", defId);
		params.put("parentActDefId", parentActDefId);
		this.delBySqlKey("delByDefIdAndParentActDefId", params);
	}
	
	public void delByDefIdNodeId(String nodeId, Long defId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("nodeId", nodeId);
		params.put("defId", defId);
		this.delBySqlKey("delByDefIdNodeId", params);
	}
	
	public List<BpmNodeSet> getParentIdByDefId(Long defId){
		return this.getBySqlKey("getParentIdByDefId", defId);
	}
	
	public List<BpmNodeSet> getParentByDefIdAndNodeId(Long defId, String nodeId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("nodeId", nodeId);
		params.put("defId", defId);
		return this.getBySqlKey("getParentByDefIdAndNodeId", params);
	}
	
	public BpmNodeSet getScopeByNodeIdAndActDefId(String nodeId,String actDefId,String parentActDefId) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("nodeId", nodeId);
		params.put("actDefId", actDefId);
		params.put("parentActDefId", parentActDefId);
		return this.getUnique("getScopeByNodeIdAndActDefId",params);
	}
	public void updateScopeById(Long setId, String scope) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("setId", setId);
		params.put("scope", scope);
		this.update("updateScopeById", params);
	}
	public void updateNodeProbability(Long defId,String nodeId,String nodeProbability){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("defId", defId);
		paramMap.put("nodeId",nodeId);
		paramMap.put("nodeProbability", nodeProbability);
		update("updateNodeProbability", paramMap);
		
	}
	//取得UserTask节点个数
	public List<BpmNodeSet> getOperateCount(long defId){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("defId", defId);
		return this.getBySqlKey("getOperateCount", paramMap);
	}
	//取得UserTask节点个数
	public List<BpmNodeSet> getTransactionCount(long defId){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("defId", defId);
		return this.getBySqlKey("getTrasactionCount", paramMap);
	}

	public List<BpmNodeSet> get_By_ActDefId_NodeId(String actDefId,String nodeId) {
		Map<String,Object> params=new HashMap<String,Object>();		
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		return getBySqlKey("get_By_ActDefId_NodeId",params);
	}
	public List<BpmNodeSet> getBy_ActDef(String actDefId){
		return getBySqlKey("getBy_ActDef", actDefId);
	}

	public List<BpmNodeSet> getBySetId(Long setId) {
		// TODO Auto-generated method stub
		return getBySqlKey("getBySetId", setId);
	}
}