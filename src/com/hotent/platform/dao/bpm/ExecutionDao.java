package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.bpm.model.ProcessExecution;
import com.hotent.core.db.GenericDao;
@Repository
public class ExecutionDao extends GenericDao<ProcessExecution,String>
{
	@Override
	public Class getEntityClass()
	{
		return ProcessExecution.class;
	}

	@Override
	public String getIbatisMapperNamespace()
	{
		return "com.hotent.core.bpm.model.ProcessExecution";
	}
	/**
	 * 更新主线程的执行为当前节点
	 * @param executionId
	 * @param nodeId
	 */
	public void updateMainThread(String executionId,String nodeId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("nodeId", nodeId);
		params.put("executionId", executionId);
		update("updateMainThread", params);
	}
	
	/**
	 * 按流程实例Id删除非主线程的Execution
	 * @param procInstId
	 */
	public void delNotMainThread(String procInstId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("procInstId", procInstId);
		delBySqlKey("delNotMainThread", params);
	}
	
	public void updateTaskToMainThreadId(String executionId,String taskId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("executionId", executionId);
		params.put("taskId", taskId);
		update("updateTaskToMainThreadId",params);
	}
	
	/**
	 * 当多实例任务完成后，需要删除流程相关的变量
	 * @param executionId
	 */
	public void delLoopAssigneeVars(String executionId)
	{
		delBySqlKey("delAssigneeByExecutionId", executionId);
		delBySqlKey("delLoopCounterByExecutionId", executionId);
	}
	
	public void delExecutionById(String executionId)
	{
		delBySqlKey("delById", executionId);
		
	}
	
	public void delTokenVarByTaskId(String taskId,String token){
		Map<String,String> params=new HashMap<String, String>();
		params.put("taskId", taskId);
		params.put("name", token);
		delBySqlKey("delTokenVarByTaskId", params);
	}
	
	/**
	 * 根据流程实例Id删除流程变量。
	 * @param procInstId
	 */
	public void delVariableByProcInstId(Long procInstId){
		delBySqlKey("delVariableByProcInstId", procInstId);
	}
	
	/**
	 * 根据流程实例ID删除execution表。
	 * @param procInstId
	 */
	public void delExecutionByProcInstId(Long procInstId){
		delBySqlKey("delExecutionByProcInstId", procInstId);
	}
	
	/**
	 * 删除出主线程之外的excution。
	 * @param procInstId
	 */
	public void delSubExecutionByProcInstId(Long procInstId){
		delBySqlKey("delSubExecutionByProcInstId", procInstId);
	}
	
	
	
	public void updateRevByInstanceId(String actInstId){
		Map<String,String> params=new HashMap<String, String>();
		params.put("actInstId", actInstId);
		update("updateRevByInstanceId",params);
	}

	public void delVarsByExecutionId(String executionId) {
		delBySqlKey("delVarsByExecutionId", executionId);
	}
	
	/**
	 * 根据流程实例Id删除非主线程的流程变量及子流程实例。
	 * @param procInstId
	 */
	public void delSubByProcInstId(String procInstId){
		//删除子流程的流程变量
		delBySqlKey("delSubVarByProcInstId", procInstId);
		//删除子流程的实例
		delBySqlKey("delSubInstByProcInstId", procInstId);		
	}
	
	/**
	 * 根据流程实例Id删除非主线程的流程变量及子流程实例。
	 * @param procInstId
	 */
	public List<ProcessExecution> getSubExecutionByProcInstId(String procInstId){
		return this.getBySqlKey("getSubExecutionByProcInstId", procInstId)	;
	}
	
	
	/**
	 * 
	 * @param procInstId
	 * @return
	 */
	public List<ProcessExecution> getExecutionBySuperId(String procInstId){
		return this.getBySqlKey("getExecutionBySuperId", procInstId)	;
	}
	
	/**
	 * 
	 * @param procInstId
	 * @return
	 */
	public List<ProcessExecution> getSubExecutionBySuperId(String id){
		return this.getBySqlKey("getSubExecutionBySuperId", id)	;
	}
	
	public void delVarByInstIdAndVarName(Long procInstId,String name){
		Map<String, Object> vars=new HashMap<String, Object>();
		vars.put("procInstId", procInstId);
		vars.put("name", name);
		delBySqlKey("delVarByInstIdAndVarName", vars);
	}

	public void delSubVariableByProcInstId(Long procInstId) {
		//删除子流程的流程变量
		delBySqlKey("delSubVarByProcInstId", procInstId);	
	}
	
	
}
