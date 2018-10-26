/**
 * 对象功能:流程任务审批意见
 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-11 16:06:11
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.page.PageBean;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.webservice.model.BpmFinishTask;

@Repository
public class TaskOpinionDao extends BaseDao<TaskOpinion>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return TaskOpinion.class;
	}
	
	/**
	 * 取得对应该任务的执行
	 * @param taskId
	 * @return
	 */
	public TaskOpinion getByTaskId(Long taskId){
		TaskOpinion taskOpinion= getUnique("getByTaskId", taskId);
		return taskOpinion;
	}
	
	/**
	 * 取得某个流程的所有审批意见
	 * @param actInstId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TaskOpinion> getByActInstId(List<String> actInstIds,boolean isAsc)
	{
		Map params = new HashMap();
		params.put("actInstIds", actInstIds);
		params.put("isAsc", isAsc);
		return getBySqlKey("getByActInstId", params);
	}
	

	/**
	 * 根据act流程定义Id删除对应的流程任务审批意见
	 * @param actDefId
	 */
	public void delByActDefIdTaskOption(String actDefId){
		delBySqlKey("delByActDefIdTaskOption", actDefId);
	}
	
	/**
	 * 按任务ID删除
	 * @param taskId
	 */
	public void delByTaskId(Long taskId){		
		delBySqlKey("delByTaskId",taskId);
	}
	
	/**
	 * 按任务ID删除
	 * @param taskId
	 */
	public void delByActDefId(String actDefId){		
		delBySqlKey("delByActDefId",actDefId);
	}

	
	/**
	 * 根据流程实例Id及任务定义Key取得审批列表 
	 * @param actInstId
	 * @param taskKey
	 * @return
	 */
	public List<TaskOpinion> getByActInstIdTaskKey(Long actInstId,String taskKey){
		Map<String,Object> params= new HashMap<String,Object>();
		params.put("actInstId", actInstId);
		params.put("taskKey", taskKey);
		return getBySqlKey("getByActInstIdTaskKey", params);
	}
	
	
	
	/**
	 * 按流程实例ID及执行用户ID取得最新的人员列表
	 * @param actInstId
	 * @param exeUserId
	 * @return
	 */
	public List<TaskOpinion> getByActInstIdExeUserId(String actInstId,Long exeUserId){
		Map<String,Object> params= new HashMap<String,Object>();
		params.put("actInstId", actInstId);
		params.put("exeUserId", exeUserId);
		return getBySqlKey("getByActInstIdExeUserId", params);
	}
	
	/**
	 * 根据流程实例Id取得表单的意见。
	 * @param instanceId
	 * @return
	 */
	public List<TaskOpinion> getFormOptionsByInstance(String instanceId){
		return getBySqlKey("getFormOptionsByInstance", instanceId);
	}
	
	
	
	/**
	 * 取得已经完成的审批任务
	 * @param userId
	 * @param subject
	 * @param taskName
	 * @param pb
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BpmFinishTask> getByFinishTask(Long userId,String subject,String taskName,PageBean pb){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId", userId);
		if(StringUtil.isNotEmpty(subject))
			params.put("subject", "%"+subject+"%");
		if(StringUtil.isNotEmpty(taskName))
			params.put("taskName", "%"+taskName+"%");
		return (List)getBySqlKey("getByFinishTask", params, pb);
	}
	
	
	/**
	 * 获取正在审批的意见。
	 * @param actInstId
	 */
	public List<TaskOpinion> getCheckOpinionByInstId(Long actInstId){
		return  getBySqlKey("getCheckOpinionByInstId", actInstId);
	}

	/**
	 * 根据实例ID
	 * @param actInstId
	 * @param taskKey
	 * @param checkStatus
	 * @return
	 */
	public List<TaskOpinion> getByActInstIdTaskKeyStatus(String actInstId,
			String taskKey, Short checkStatus) {
		Map<String,Object> params= new HashMap<String,Object>();
		params.put("actInstId", actInstId);
		params.put("taskKey", taskKey);
		params.put("checkStatus", checkStatus);
		return getBySqlKey("getByActInstIdTaskKeyStatus", params);
	}
	
	/**
	 * 根据任务ID获取保存的意见。
	 * @param taskId
	 * @return
	 */
	public TaskOpinion getOpinionByTaskId(Long taskId,Long userId) {
		Map<String,Object> params= new HashMap<String,Object>();
		params.put("taskId", taskId);
		params.put("userId", userId);
		return getUnique("getOpinionByTaskId", params);
	}
	
	/**
	 * 根据任务ID和状态获取意见。
	 * @param taskId		任务ID
	 * @param checkStatus	意见状态。
	 * @return
	 */
	public List<TaskOpinion> getByTaskIdStatus(Long taskId,Short checkStatus){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("taskId", taskId);
		params.put("checkStatus", checkStatus);
		return getBySqlKey("getByTaskIdStatus", params);
	}

	public void delByActInstId(String actInstId) {
		delBySqlKey("delByActInstId", actInstId);
	}
	
	
	/**
	 * 根据actInstId更新。
	 * @param actInstId
	 * @param oldActInstId
	 * @return
	 */
	public int updateActInstId(String actInstId,String oldActInstId){
		Map map=new HashMap();
		map.put("oldActInstId", oldActInstId);
		map.put("actInstId", actInstId);	
		return this.update("updateActInstId", map);
	}
	
	
	
	 /**
	  * 查询父实例ID。
	  * @param actInstId
	  * @return 
	  */
	public String getSupInstByActInstId(String actInstId) {
		return (String) this.getOne("getSupInstByActInstId", actInstId);
		
	}
	

	/**
	  * 根据父实例ID查询子实例ID列表。
	  * @param supInstId
	  * @return  List
	  */
	public List<TaskOpinion> getBySupInstId(String supInstId) {
		return  getBySqlKey("getBySupInstId" , supInstId);
	}
	
	
	
}