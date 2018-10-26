package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntity;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

@Repository
public class HistoryActivityDao extends BaseDao<HistoricActivityInstanceEntity> {

	@Override
	public Class getEntityClass() {
		return HistoricActivityInstanceEntity.class;
	}
	
	/**
	 * 根据流程实例ID 历史活动实例。
	 * @param actInstId
	 * @param nodeId
	 * @return
	 */
	public List<HistoricActivityInstanceEntity> getByInstanceId(Long actInstId,String nodeId){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("actInstId", actInstId);
		params.put("nodeId", nodeId);
		return this.getBySqlKey("getByInstanceId", params);
	}
	
	
	

	/**
	 * 更新执行人。
	 * @param actInstId
	 * @param nodeId
	 * @param assignee
	 */
	public void updateAssignee(HistoricActivityInstanceEntity hisActEnt){
		this.update("updateAssignee", hisActEnt);
	}
	
	/**
	 * 修改历史为开始。
	 * @param actInstId	实例ID
	 * @param nodeId	节点ID
	 */
	public void updateIsStart(Long actInstId,String nodeId){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("processInstanceId", actInstId);
		params.put("activityId", nodeId);
		this.update("updateIsStart", params);
	}

	public List<HistoricActivityInstanceEntity> getByFilter(Map<String,Object> params){
		return	this.getBySqlKey("getByFilter", params);
	}
	
	/**
	 * 根据executionId获取流程历史实例。
	 * @param executionId
	 * @param nodeId
	 * @return
	 */
	public List<HistoricActivityInstanceEntity> getByExecutionId(String executionId,String nodeId){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("executionId", executionId);
		params.put("nodeId", nodeId);
		return this.getBySqlKey("getByExecutionId", params);
	}
	
	public List<HistoricActivityInstanceEntity> getByActInstId(
			String actInstId, String nodeId) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("actInstId", actInstId);
		params.put("nodeId", nodeId);
		return this.getBySqlKey("getByActInstId", params);
	}
	
}
