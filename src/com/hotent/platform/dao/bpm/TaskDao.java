package com.hotent.platform.dao.bpm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Repository;

import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.db.BaseDao;
import com.hotent.core.page.PageBean;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.calendar.util.CalendarUtil;

@Repository
public class TaskDao extends BaseDao<TaskEntity>
{
	@Override
	public Class<TaskEntity> getEntityClass()
	{
		return TaskEntity.class;
	}
	
	@Override
	public String getIbatisMapperNamespace()
	{
		return "com.hotent.core.bpm.model.ProcessTask";
	}
	
	public void updTaskExecution(String taskId){
		this.update("updTaskExecution", new Long(taskId));
	}
	
	/**
	 * 获取我的任务
	 * @param queryFilter
	 * @return
	 */
	public List<TaskEntity> getMyTasks(Long userId ,QueryFilter queryFilter)
	{
		queryFilter.getFilters().put("userId",userId);
		return getBySqlKey("getAllMyTask", queryFilter);
	}
	
	
	

	
	/**
	 * 获取我的任务数量
	 * @param queryFilter
	 * @return
	 */
	public List<?> getMyTasksCount(Long userId)
	{
		return getBySqlKey("getMyTaskCount", userId);
	}
	
	
	
	/**
	 * 获取我的手机任务列表
	 * @param filter
	 * @return
	 */
	public List<TaskEntity> getMyMobileTasks(QueryFilter filter) {
		String statmentName="getMyMobileTask";
		return getBySqlKey(statmentName, filter);
	}
	
	/**
	 * 查找某用户的待办任务
	 * @param userId 用户ID 
	 * @param taskName 任务名称
	 * @param subject  任务标题 
	 * @param processName 流程定义名称
	 * @param orderField 排序字段
	 * @param orderSeq  升序或降序 值有 asc 或 desc
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ProcessTask> getTasks(Long userId,String taskName,String subject,String processName,String orderField,String orderSeq,PageBean pb){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("name", taskName);
		params.put("subject", subject);
		params.put("processName", processName);
		params.put("orderField", orderField);
		params.put("orderSeq", orderSeq);
		List list=getBySqlKey("getAllMyTask",params,pb);
		return list;
	}
	
	
	/**
	 * 获取我的任务
	 * @param userId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<TaskEntity> getMyEvents(Map param){
		return getBySqlKey("getAllMyEvent", CalendarUtil.getCalendarMap(param));
	}
	


	
	/**
	 * 设置任务的到期日期
	 * @param taskId
	 * @param dueDate
	 * @return
	 */
	public int setDueDate(String taskId,Date dueDate)
	{
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("taskId",taskId);
		params.put("dueDate", dueDate);
		return update("setDueDate", params);
	}
	
	/**
	 * 生成任务
	 * @param task
	 */
	public void insertTask(ProcessTask task)
	{
		String statement=getIbatisMapperNamespace()+".add";
		getSqlSessionTemplate().insert(statement, task);
	}

     
	/**
	 * 取得未到期的任务。
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProcessTask> getReminderTask(){
		Date curDate=new Date(System.currentTimeMillis());
		List<ProcessTask> list=this.getSqlSessionTemplate().selectList("getReminderTask", curDate);
		return list;
	}
	/**
	 * 取得需要时效提醒的任务
	 * 
	 * @Methodname: getTimeReminderTask
	 * @Discription: 
	 * @return
	 * @Author HH
	 * @Time 2012-11-26 下午4:45:35
	 */
	@SuppressWarnings("unchecked")
	public List<ProcessTask> getTimeReminderTask(){
		Date curDate=new Date(System.currentTimeMillis());
		List<ProcessTask> list=this.getSqlSessionTemplate().selectList("getTimeReminderTask", curDate);
		return list;
	}
	
	/**
	 * 取得某个流程实例下的任务列表
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ProcessTask> getTasksByRunId(Long runId){
		List list=getBySqlKey("getTasksByRunId",runId);
		return list;
	}
	
	/**
	 * 更新任务的执行人
	 * @param taskId
	 * @param userId
	 */
	public void updateTaskAssignee(String taskId,String userId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("taskId", Long.parseLong(taskId));
		params.put("userId", userId);
		update("updateTaskAssignee", params);
	}
	
	/**
	 * 更新任务的执行人的状态
	 * @param taskId
	 * @param userId
	 */
	public void updateTaskDescription(String description,String taskId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("taskId", Long.parseLong(taskId));
		params.put("description", description);
		update("updateTaskDescription", params);
	}
	
	public void updateTaskAssigneeNull(String taskId){
		Long lTaskId=new Long(taskId);
		update("updateTaskAssigneeNull", lTaskId);
	}
	/**
	 * 更新任务的所属人
	 * @param taskId
	 * @param userId
	 */
	public void updateTaskOwner(String taskId,String userId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("taskId", new Long(taskId));
		params.put("userId", userId);
		update("updateTaskOwner", params);
	}
	
	
	/**
	 * 按TaskId返回任务实体
	 * @param taskId
	 * @return
	 */
	public ProcessTask getByTaskId(String taskId){
		return (ProcessTask)getOne("getByTaskId", new Long( taskId));
	}
	
	/**
	 * 按parentTaskId和description返回任务实体
	 * @param parentTaskId
	 * @param description
	 * @return
	 */
	public List<TaskEntity> getByParentTaskIdAndDesc(String parentTaskId, String description){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("parentTaskId", parentTaskId);
		params.put("description", description);
		return getBySqlKey("getByParentTaskIdAndDesc", params);
	}
	
	/**
	 * 获取所有状态的加签任务
	 * @param parentTaskId
	 * @return
	 */
	public List<TaskEntity> getByParentTaskId(String parentTaskId){
		return getBySqlKey("getByParentTaskId", parentTaskId);
	}
	
	/**
	 * 获取所有状态的加签任务
	 * @param parentTaskId
	 * @param userId
	 * @return
	 */
	public ProcessTask getTaskByParentIdAndUser(String parentTaskId, String userId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("parentTaskId", parentTaskId);
		params.put("userId", userId);
		return (ProcessTask)getOne("getTaskByParentIdAndUser", params);
	}
	
	public ProcessTask getTaskByParentIdDescAndUser(String parentTaskId, String description, String userId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("parentTaskId", parentTaskId);
		params.put("userId", userId);
		params.put("description", description);
		return (ProcessTask)getOne("getTaskByParentIdDescAndUser", params);
	}
	
	/**
	 * 根据流程实例ID和任务定义Key获取任务。
	 * @param taskId
	 * @return
	 */
	public List<TaskEntity> getByInstanceIdTaskDefKey(String instanceId,String taskDefKey){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("instanceId", instanceId);
		params.put("taskDefKey", taskDefKey);
		return getBySqlKey("getByInstanceIdTaskDefKey", params);
	}
	
	
	/**
	 * 根据流程实例ID和任务定义Key获取任务。
	 * @param taskId
	 * @return
	 */
	public List<ProcessTask> getListByInstanceIdTaskDefKey(String instanceId,String taskDefKey){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("instanceId", instanceId);
		params.put("taskDefKey", taskDefKey);
		List list= getBySqlKey("getListByInstanceIdTaskDefKey", params);
		return  list;
	}
	/**
	 * 根据流程实例ID获取任务。
	 * @param taskId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ProcessTask> getByInstanceId(String instanceId){
		List list=getBySqlKey("getByInstanceId",new Long( instanceId));
		return list;
	}
	
	/**
	 * 根据流程实例删除任务。
	 * @param instanceId
	 */
	public void delByInstanceId(Long instanceId){
		this.update("delByInstanceId",instanceId);
	}
	
	/**
	 * 删除用户直接生成的任务。
	 * @param instanceId
	 */
	public void delCustTaskByInstId(Long instanceId){
		this.update("delCustTaskByInstId",instanceId);
	}
	
	
	
	/**
	 * 根据流程实例ID删除任务候选人。
	 * @param instanceId
	 */
	public void delCandidateByInstanceId(Long instanceId){
		this.update("delCandidateByInstanceId", instanceId);
	}
	
	public void updateNewTaskDefKeyByInstIdNodeId(String newTaskDefKey,String oldTaskDefKey,String actInstId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("newTaskDefKey", newTaskDefKey);
		params.put("oldTaskDefKey", oldTaskDefKey);
		params.put("actInstId", new Long( actInstId));
		this.update("updateNewTaskDefKeyByInstIdNodeId",params);
	}
	
	public void updateOldTaskDefKeyByInstIdNodeId(String newTaskDefKey,String oldTaskDefKey,String actInstId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("newTaskDefKey", StringUtil.isNotEmpty(newTaskDefKey)?(newTaskDefKey+"%"):newTaskDefKey);
		params.put("oldTaskDefKey", oldTaskDefKey);
		params.put("actInstId",new Long( actInstId));
		this.update("updateOldTaskDefKeyByInstIdNodeId",params);
	}

	/**
	 * 根据任务id获取是否有候选人。
	 * @param taskIds
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> getHasCandidateExecutor(String taskIds){
		List<Long> taskList=new ArrayList<Long>();
		String[] aryTask= taskIds.split(",");
		for(int i=0;i<aryTask.length;i++){
			taskList.add(new Long(aryTask[i]));
		}
		//params.put("taskIds",taskIds);
		String statement= getIbatisMapperNamespace() + ".getHasCandidateExecutor" ;
		List<Map> list=getSqlSessionTemplate().selectList(statement,taskList);
		return list;
	}
	
	
	
	/**
	 * 删除产生的沟通任务。
	 * @param parentId
	 */
	public void delByParentId(Long parentId){
		this.delBySqlKey("delByParentId", parentId.toString());
	}
	
	/**
	 * 更新任务。
	 * @param taskId
	 * @param userId
	 * @param description
	 */
	public void updateTask(String taskId,String userId,String description){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("taskId",new Long( taskId));
		params.put("userId", userId);
		params.put("description", description);
		params.put("updateTime", new Date());
		this.update("updateTask", params);
	}
	
	/**
	 * 删除某节点某人的沟通任务
	 * @param instInstId
	 * @param nodeId
	 * @param userId
	 */
	public void delCommuTaskByInstNodeUser(Long instInstId,String nodeId,Long userId){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("instInstId", instInstId);
		params.put("nodeId", nodeId);
		params.put("userId", userId);
		this.delBySqlKey("delCommuTaskByInstNodeUser", params);
	}
	
	/**
	 * 删除某任务的沟通任务
	 * @param parentTaskId 
	 */
	public void delCommuTaskByParentTaskId(String parentTaskId){
		this.delBySqlKey("delCommuTaskByParentTaskId", parentTaskId);
	}
	
	/**
	 * 删除指定parentTaskId的流转任务
	 * @param parentTaskId
	 */
	public void delTransToTaskByParentTaskId(String parentTaskId){
		this.delBySqlKey("delTransToTaskByParentTaskId", parentTaskId);
	}
	
	/**
	 * 通过用户Id和流程实例Id获得任务
	 * @param userId 用户Id
	 * @param actInstId 流程实例Id
	 * @return 任务
	 */
	public List<?> getTaskByUserInstId(Long userId, Long actInstId){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("actInstId", actInstId);
		return 	this.getBySqlKey("getTaskByUserInstId", params);
	}
	
	/**
	 * 根据流程实例ID获取数据。
	 * @param actInstId
	 * @return
	 */
	public List< ProcessTask> getTaskByInstId(Long actInstId){
		List list=this.getBySqlKey("getTaskByInstId", actInstId);
		return list;
	}
	
	/**
	 * 根据流程实例ID获取历史数据。
	 * @param actInstId
	 * @return
	 */
	public List<ProcessTask> getHisTaskByInstId(Long actInstId){
		List list=this.getBySqlKey("getHisTaskByInstId", actInstId);
		return list;
	}
	
	
	
	/**
	 * 判断用户是否有权限访问某个任务。
	 * @param taskId
	 * @param userId
	 * @return
	 */
	public boolean getHasRightsByTask(Long taskId,Long userId){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("taskId", taskId);
		params.put("userId", userId);
		Integer rtn=(Integer)this.getOne("getHasRightsByTask", params);
		return rtn>0;
		
	}
	
	
	public boolean hasRead(Long taskId,Long userId){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("taskId", taskId);
		params.put("userId", userId);
		Integer cnt = (Integer) this.getOne("hasRead", params);
		return cnt>0;
	}
	


	
	public List<ProcessTask> getTaskByActDefId(String actDefId,int num){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("actDefId", actDefId);
		params.put("num", num);
		List list = this.getBySqlKey("getTaskByActDefId",params);
		return list;
	}
	
	/**
	 * 获取流程实例中是否有该用户的记录
	 * @param actInstId
	 * @param userId
	 * @return
	 */
	public boolean getHisByInstanceidAndUserId(Long actInstId,Long userId){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("actInstId", actInstId);
		params.put("userId", userId);
		Integer rtn=(Integer)this.getOne("getHisByInstanceidAndUserId", params);
		return rtn>0;
	}
	
	/**
	 * 按任务名称或任务Id列表取到某一用户的待办事项列表
	 * @param userId
	 * @param taskName
	 * @param taskIds
	 * @param pb
	 * @return
	 */
	public List<ProcessTask> getByTaskNameOrTaskIds(String userId,String taskName, String taskIds,PageBean pb){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("taskName", taskName);
		params.put("taskIds", taskIds);
		List list= getBySqlKey("getByTaskNameOrTaskIds", params, pb);
		return list;
	}

	/**
	 * 根据instanceId删除沟通任务的子任务
	 * @param instanceId
	 */
	public void delSubCustTaskByInstId(Long instanceId) {
		this.update("delSubCustTaskByInstId",instanceId);
	}

	/**
	 * 获取手机待办数
	 * @param userId
	 * @return
	 */
	/***
	 * 通过taskid 修改紧急程度
	 * @param taskId
	 * @param level
	 */
	public void updateTaskPriority(String taskId, int level) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("taskId", taskId);
		params.put("level", level);
		
		this.update("updateTaskPriority",params);
	}

	/**
	 * LZC
	 */
	public List<?> getMyProcessCount(Long userId) {
		// TODO Auto-generated method stub
		return getBySqlKey("getMyProcessCount", userId);
	}

	/**
	 * LZC
	 */
	public List<?> getMyTaskKeyCount(Long userId) {
		// TODO Auto-generated method stub
		return getBySqlKey("getMyTaskKeyCount",userId);
	}


}
