/**
 * 对象功能:流程变量定义 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2011-12-01 16:50:07
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmDefVar;

@Repository
public class BpmDefVarDao extends BaseDao<BpmDefVar>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmDefVar.class;
	}
	
	
	public boolean isVarNameExist(String varName,String varKey,Long defId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("defId", defId);
		params.put("varName", varName);
		params.put("varKey", varKey);
	    Integer obj=(Integer) this.getOne("isVarNameExist", params);
		return obj>0;
	}
	
	/**
	 * 根据流程发布和节点id取得流程变量列表。
	 * @param deployId
	 * @param nodeId
	 * @return
	 */
	public List<BpmDefVar> getByDeployAndNode(String deployId,String nodeId){
		Map map=new HashMap();
		map.put("actDeployId", deployId);
		map.put("nodeId", nodeId);
		return getBySqlKey("getByDeployAndNode", map);
	}
	/**
	 * 根据流程定义id获取流程变量列表。
	 * @param defId
	 * @return
	 */
	public List<BpmDefVar> getVarsByFlowDefId(Long defId){
		return getBySqlKey("getVars", defId);
	}
	/**
	 * 根据defId删除该流程下的流程变量
	 */
	public void delByDefId(Long defId){
		delBySqlKey("delByDefId", defId);
		
	}
	
	
	/**
	 * 根据流程定义id获取流程变量列表。
	 * @param defId
	 * @return
	 */	
	public List<BpmDefVar> getByDefId(Long defId){
		return getBySqlKey("getByDefId", defId);
	}
	
}