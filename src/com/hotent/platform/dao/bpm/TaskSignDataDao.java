/**
 * 对象功能:任务会签数据 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-19 15:29:52
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.TaskSignData;

@Repository
public class TaskSignDataDao extends BaseDao<TaskSignData>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return TaskSignData.class;
	}
	
	
	/**
	 * 返回某个流程实例某个
	 * @param actInstId
	 * @param nodeId
	 * @return
	 */
	public Integer getMaxSignNums(String actInstId,String nodeId,Short isCompleted)
	{
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("actInstId", actInstId);
		params.put("nodeId", nodeId);
		params.put("isCompleted",isCompleted);
		
		Integer maxNums=(Integer)getOne("getMaxSignNums", params);
		
		return maxNums==null? 0: maxNums;
	}
	
	
	/**
	 * 返回某个任务会签的数据
	 * @param taskId
	 * @return
	 */
	public TaskSignData getByTaskId(String taskId)
	{
		return getUnique("getByTaskId", taskId);
	}

	
	
	/**
	 * 取得某个流程某个节点所有的投同意票的总数
	 * @param actInstId 流程实例Id
	 * @param nodeId 节点Id
	 * @return
	 */
	public Integer getAgreeVoteCount(String actInstId,String nodeId)
	{
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("actInstId", actInstId);
		params.put("nodeId", nodeId);
		Integer count=(Integer)getOne("getAgreeVoteCount", params);
		return count;
	}
	
	/**
	 * 取得某个流程某个节点所有的投同意票的总数
	 * @param actInstId 流程实例Id
	 * @param nodeId 节点Id
	 * @return
	 */
	public Integer getRefuseVoteCount(String actInstId,String nodeId)
	{
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("actInstId", actInstId);
		params.put("nodeId", nodeId);
		Integer count=(Integer)getOne("getRefuseVoteCount", params);
		return count;
	}
	
	/**
	 * 取得某个流程某个节点所有的投同意票的总数
	 * @param actInstId 流程实例Id
	 * @param nodeId 节点Id
	 * @return
	 */
	public Integer getAbortVoteCount(String actInstId,String nodeId)
	{
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("actInstId", actInstId);
		params.put("nodeId", nodeId);
		Integer count=(Integer)getOne("getAbortVoteCount", params);
		return count;
	}
	/**
	 * 返回某个用户某次会签的记录
	 * @param actInstId
	 * @param nodeId
	 * @param signNums
	 * @param voteUserId
	 * @return
	 */
	public TaskSignData getUserTaskSign(String actInstId,String nodeId,String voteUserId)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actInstId", actInstId);
		params.put("nodeId", nodeId);
		params.put("voteUserId", voteUserId);
		
		return getUnique("getUserTaskSign", params);
	}
	
	/**
	 * 更新本次会签投票完成的状态
	 * @param actInstId
	 * @param nodeId
	 */
	public void batchUpdateCompleted(String actInstId,String nodeId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actInstId", actInstId);
		params.put("nodeId", nodeId);
	
		update("updateCompleted", params);
	}
	/**
	 * 根据act流程定义id删除任务会签数据
	 * @param actDefId
	 */
	public void delByIdActDefId(String actDefId){
		delBySqlKey("delByIdActDefId", actDefId);
		
	}
	
	/**
	 * 得到此流程已参与的会签人员
	 * @param taskProId
	 * @param nodeId
	 * @param userIds
	 * @return
	 */
	public List<TaskSignData> getByNodeAndInstanceId(String instanceId, String nodeId){
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("actInstId",  instanceId);
		params.put("nodeId", nodeId);
		return getBySqlKey("getByNodeAndInstanceId", params);
	}
	
	/**
	 * 得到此流程已参与的并且未投票的会签人员
	 * @param taskProId
	 * @param nodeId
	 * @param userIds
	 * @return
	 */
	public List<TaskSignData> getByNodeAndInstanceId(String instanceId, String nodeId,Integer isComplete){
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("actInstId",  instanceId);
		params.put("nodeId", nodeId);
		params.put("isComplete", isComplete);
		return getBySqlKey("getByNodeAndInstanceId", params);
	}
	
	/**
	 * 取得最大的批次。
	 * @param instanceId
	 * @param nodeId
	 * @return
	 */
	public int getMaxBatch(String instanceId, String nodeId){
		
		Map<String,Object>params=new HashMap<String,Object>();
		params.put("actInstId",  instanceId);
		params.put("nodeId", nodeId);
		
		Object obj= this.getOne("getMaxBatch", params);
		if(obj==null){
			return 0;
		}
		return (Integer)obj;
	}
	
}