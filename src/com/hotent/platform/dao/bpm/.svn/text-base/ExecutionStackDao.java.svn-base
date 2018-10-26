/**
 * 对象功能:流程执行堆栈树 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-31 09:58:00
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.bpm.ExecutionStack;

@Repository
public class ExecutionStackDao extends BaseDao<ExecutionStack>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return ExecutionStack.class;
	}
	
	/***
	 * 根据流程实例，任务节点id按照开始时间降序排列。
	 * @param actInstId
	 * @param nodeId
	 * @return
	 */
	public List<ExecutionStack> getByActInstIdNodeId(String actInstId,String nodeId){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("actInstId", actInstId);
		params.put("nodeId", nodeId);
		return getBySqlKey("getByActInstIdNodeId", params);
	}
	
	public void delByActInstId(String actInstId){
		delBySqlKey("delByActInstId", actInstId);
	}
	
	
	/**
	 * 根据流程实例，任务节点，任务token 按照开始时间降序获取执行执行堆栈列表。
	 * @param actInstId
	 * @param nodeId
	 * @param taskToken
	 * @return
	 */
	public List<ExecutionStack> getByActInstIdNodeIdToken(String actInstId,String nodeId,String taskToken){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("actInstId", actInstId);
		params.put("nodeId", nodeId);
		params.put("taskToken", taskToken);
		return getBySqlKey("getByActInstIdNodeIdToken", params);
	}
	
	/**
	 * 获取堆栈树中最新的某个节点
	 * @param actInstId
	 * @param nodeId
	 * @return
	 */
	public ExecutionStack getLastestStack(String actInstId,String nodeId){
		List<ExecutionStack> list=getByActInstIdNodeId(actInstId, nodeId);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 获取堆栈树中最新的某个节点
	 * @param actInstId
	 * @param parentNodeId
	 * @param taskToken
	 * @return
	 */
	public ExecutionStack getLastestStack(String actInstId,String parentNodeId,String taskToken){
		if(StringUtil.isNotEmpty(taskToken)){
			List<ExecutionStack> list=getByActInstIdNodeIdToken(actInstId, parentNodeId,taskToken);
			if(list.size()>0){
				return list.get(0);
			}
			return null;
		}else{
			return getLastestStack(actInstId,parentNodeId);
		}
	}
	
	/**
	 * 取得堆栈树中某层上的所有节点。
	 * @param actInstId
	 * @param depth
	 * @return
	 */
	public List<ExecutionStack> getByActInstIdDepth(String actInstId,Integer depth)
	{
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("actInstId", actInstId);
		params.put("depth", depth);
		return getBySqlKey("getByActInstIdDepth", params);
	}
	/**
	 * 级联删除子树节点下的所有子节点。
	 * @param stackId
	 * @param nodePath
	 * @return
	 */
	public Integer delSubChilds(Long stackId,String nodePath)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("stackId",stackId);
		params.put("nodePath", nodePath+"%");
		return delBySqlKey("delSubChilds",params);
	}
	
	/**
	 * 取得某个节点旁边的所有兄弟节点
	 * @param actInstId
	 * @param depth
	 * @param stackId
	 * @return
	 */
	public List<ExecutionStack> getByActInstIdDepExStackId(Long actInstId,Integer depth,Long stackId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actInstId", actInstId);
		params.put("depth",depth);
		params.put("stackId",stackId);
		return getBySqlKey("getByActInstIdDepExStackId",params);
	}
	
	/**
	 * 按节点路径取某个节点下的所有节点
	 * @param nodePath
	 * @return
	 */
	public List<ExecutionStack> getByLikeNodePath(String nodePath){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("nodePath", nodePath+"%");
		return getBySqlKey("getByLikeNodePath",params);
	}
	
	/**
	 * 按父ID取得所有子结节
	 * @param parentId
	 * @return
	 */
	public List<ExecutionStack> getByParentId(Long parentId)
	{
		return getBySqlKey("getByParentId", parentId);
	}
	
	/**
	 * 按父ID取得下面的所有的已经完成的任务节点
	 * @param parentId
	 * @return
	 */
	public List<ExecutionStack> getByParentIdAndEndTimeNotNull(Long parentId){
		return getBySqlKey("getByParentIdAndEndTimeNotNull", parentId);
	}
	
	/**
	 * 根据act流程定义Id删除对应的流程执行的堆栈树
	 * @param actDefId
	 */
	public void delByActDefId(String actDefId){
		delBySqlKey("delByActDefId", actDefId);
	}
	
	/**
	 * 更新ExecutionStack中tasktoken字段 
	 * @param taskId
	 * @param nodeId
	 * @return
	 */
	public void udpTaskTokenByTaskIdNodeId(String taskToken,String taskId,String nodeId){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("taskToken", taskToken);
		params.put("taskIds", taskId);
		params.put("nodeId", nodeId);
		update("udpTaskTokenByTaskIdNodeId", params);
	}
	
	/**
	 * 根据父ID删除堆栈。
	 * @param parentId
	 */
	public void delByParentfId(Long parentId){
		delBySqlKey("delByParentfId", parentId);
	}

	
}