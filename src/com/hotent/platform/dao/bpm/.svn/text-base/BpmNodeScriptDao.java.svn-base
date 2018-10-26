/**
 * 对象功能:节点运行脚本 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2011-12-30 14:31:19
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmNodeRule;
import com.hotent.platform.model.bpm.BpmNodeScript;
import com.hotent.platform.model.bpm.BpmNodeSet;

@Repository
public class BpmNodeScriptDao extends BaseDao<BpmNodeScript>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmNodeScript.class;
	}
	
	/**
	 * 根据节点获取事件列表代码。
	 * @param nodeId
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeScript> getByBpmNodeScriptId(String nodeId,String actDefId)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		
		return getBySqlKey("getBpmNodeScript",params);
	}
	
	/**
	 * 根据节点获取事件列表代码。
	 * @param nodeId
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeScript> getByActDefId(String actDefId)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actDefId", actDefId);

		return getBySqlKey("getBpmNodeScript",params);
	}
	
	
	/**
	 * 根据流程定义ID和节点ID删除脚本数据。
	 * @param nodeId
	 * @param actDefId
	 */
	public void delByDefAndNodeId(String actDefId,String nodeId)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		
		this.update("delByDefAndNodeId",params);
	}
	
	/**
	 * 根据节点和脚本类型脚本。
	 * @param nodeId
	 * @param actDefId
	 * @param scriptType
	 * @return
	 */
	public BpmNodeScript getScriptByType(String nodeId,String actDefId,Integer scriptType){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		params.put("scriptType", scriptType);
		return this.getUnique("getScriptByType", params);
	}
	/**
	 * 根据act流程定义Id删除流程节点事件脚本
	 * @param actDefId
	 */
	public void delByActDefId(String actDefId){
		delBySqlKey("delByActDefId", actDefId);
	}

}