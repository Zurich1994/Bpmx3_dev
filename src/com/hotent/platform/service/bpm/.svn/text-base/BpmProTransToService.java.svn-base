package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.bpm.BpmProTransTo;
import com.hotent.platform.model.bpm.BpmProTransToAssignee;
import com.hotent.platform.model.bpm.CommuReceiver;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.worktime.CalendarAssignService;
import com.hotent.platform.dao.bpm.BpmProTransToDao;
import com.hotent.platform.dao.bpm.TaskDao;
import com.hotent.platform.dao.bpm.TaskOpinionDao;
import com.hotent.platform.dao.system.SysUserDao;

/**
 * 对象功能:流程流转状态 Service类 开发公司:广州宏天软件有限公司 开发人员:helh 创建时间:2013-09-18 09:32:55
 */
@Service
public class BpmProTransToService extends BaseService<BpmProTransTo> {
	@Resource
	private BpmProTransToDao dao;
	@Resource
	private TaskDao taskDao;
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private TaskOpinionDao taskOpinionDao;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private CommuReceiverService commuReceiverService;
	@Resource
	private TaskService taskService;
	@Resource
	private CalendarAssignService calendarAssignService;

	public BpmProTransToService() {
	}

	@Override
	protected IEntityDao<BpmProTransTo, Long> getEntityDao() {
		return dao;
	}
	
	public void add(BpmProTransTo bpmProTransTo){
		if(BeanUtils.isNotEmpty(bpmProTransTo)) {
			dao.add(bpmProTransTo);
		}
	}
	
	/**
	 * 加签事宜
	 * @param filter
	 * @return
	 */
	public List<BpmProTransTo> mattersList(QueryFilter filter){
		return dao.mattersList(filter);
	}
	
	/**
	 * 获取所有流转人的任务状态
	 * @param parentTaskId
	 * @param assignee
	 * @return
	 * @throws Exception
	 */
	public List<BpmProTransToAssignee> getAssignee(String parentTaskId, String assignee) throws Exception {
		List<?> taskList = taskDao.getByParentTaskId(parentTaskId);
		List<BpmProTransToAssignee> list = handleAssignee(taskList, assignee);
		return list;
	}
	
	private List<BpmProTransToAssignee> handleAssignee(List<?> taskList, String assignee) {
		Map<String, String> taskMap = new HashMap<String, String>();
		if(taskList!=null){
			for(int i=0;i<taskList.size();i++){
				ProcessTask task = (ProcessTask)taskList.get(i);
				taskMap.put(task.getAssignee(), task.getParentTaskId());
			}
		}
		String[] assignees = assignee.split(",");
		List<BpmProTransToAssignee> list = new ArrayList<BpmProTransToAssignee>();
		for(String transTo : assignees){
			SysUser sysUser = sysUserDao.getById(Long.valueOf(transTo));
			BpmProTransToAssignee transToAssignee = new BpmProTransToAssignee();
			transToAssignee.setUserId(sysUser.getUserId());
			transToAssignee.setUserName(sysUser.getFullname());
			if(taskMap.containsKey(transTo)){
				transToAssignee.setStatus(BpmProTransToAssignee.STATUS_CHECKING);
				transToAssignee.setParentTaskId(taskMap.get(transTo));
			}else{
				transToAssignee.setStatus(BpmProTransToAssignee.STATUS_CHECKED);
			}
			list.add(transToAssignee);
		}
		return list;
	}
	
	/**
	 * 添加流转任务
	 * @param bpmProTransTo
	 * @param taskId
	 * @param opinion
	 * @param userIds
	 * @param informType
	 * @throws Exception
	 */
	public void addTransTo(BpmProTransTo bpmProTransTo, String taskId, String opinion, 
			String userIds, String informType) throws Exception {
		SysUser sysUser = ContextUtil.getCurrentUser();
		ProcessTask processTask = taskDao.getByTaskId(taskId);
		String[] aryUsers = userIds.split(",");
		// 产生流转任务
		Map<Long, Long> usrIdTaskIds = this.addTransToTask(processTask, aryUsers);
		//添加意见
		Long opinionId = this.addOpinion(processTask, opinion, sysUser, true);
		// 保存接收人
		commuReceiverService.saveReceiver(opinionId, usrIdTaskIds, sysUser);
		//更新流转人记录
		String assignee = bpmProTransTo.getAssignee();
		if(StringUtil.isEmpty(assignee)){
			bpmProTransTo.setAssignee(userIds);
		}
		else{
			bpmProTransTo.setAssignee(assignee + "," + userIds);
		}
		dao.update(bpmProTransTo);	
		
		// 发送通知。
		ProcessRun processRun=processRunService.getByActInstanceId(new Long(processTask.getProcessInstanceId()));
		processRunService.notifyCommu(processRun.getSubject(), usrIdTaskIds, informType, sysUser, opinion, SysTemplate.USE_TYPE_TRANSTO);
	}
	
	private Map<Long, Long> addTransToTask(ProcessTask ent, String[] users) throws Exception {
		Map<Long, Long> map = new HashMap<Long, Long>();
		String parentId = ent.getId();
		for (String userId : users) {
			//产生流传任务
			String taskId = String.valueOf(UniqueIdUtil.genId());
			TaskEntity task = (TaskEntity) taskService.newTask(taskId);
			task.setAssignee(userId);
			task.setOwner(userId);
			task.setCreateTime(new Date());
			task.setName(ent.getName());
			task.setParentTaskId(parentId);
			task.setTaskDefinitionKey(ent.getTaskDefinitionKey());
			task.setProcessInstanceId(ent.getProcessInstanceId());
			task.setDescription(TaskOpinion.STATUS_TRANSTO.toString());
			task.setProcessDefinitionId(ent.getProcessDefinitionId());
			taskService.saveTask(task);
			map.put(Long.valueOf(userId), Long.valueOf(taskId));
		}
		return map;
	}
	
	/**
	 * 取消流转任务
	 * @param processTask
	 * @param opinion
	 * @param informType
	 * @throws Exception
	 */
	public void cancel(ProcessTask processTask, String opinion, String informType) throws Exception {
		SysUser sysUser = ContextUtil.getCurrentUser();
		String taskId = processTask.getId();
		String parentTaskId = processTask.getParentTaskId();
		taskService.deleteTask(taskId);
		
		if(processTask.getDescription().equals(TaskOpinion.STATUS_TRANSTO_ING.toString())){
			//删除流转状态
			delByTaskId(new Long(taskId));
			this.cancelTransToTaskCascade(taskId);
		}
		
		//删除被流转任务产生的沟通任务
		taskDao.delCommuTaskByParentTaskId(taskId);
		List<TaskEntity> list = taskDao.getByParentTaskId(parentTaskId);
		BpmProTransTo bpmProTransTo = this.getByTaskId(Long.valueOf(parentTaskId));
		if(list.size()==0){//所有流转任务已结束
			ProcessTask parentTask = taskDao.getByTaskId(parentTaskId);
			if(parentTask.getParentTaskId()==null){
				//更改初始执行人状态为审批状态
				taskDao.updateTaskDescription(TaskOpinion.STATUS_CHECKING.toString(), parentTaskId);
			}else{
				//更改初始执行人状态为流转状态
				taskDao.updateTaskDescription(TaskOpinion.STATUS_CHECKING.toString(), parentTaskId);
			}
			
			this.delById(bpmProTransTo.getId());//删除流转状态
		}else{
			//删除被取消的流转人记录
			String assignee = bpmProTransTo.getAssignee();
			if(assignee.startsWith(processTask.getAssignee())){
				assignee = assignee.replace(processTask.getAssignee()+",", "");
			}else{
				assignee = assignee.replace(","+processTask.getAssignee(), "");
			}
			bpmProTransTo.setAssignee(assignee);
			this.update(bpmProTransTo);
		}
		
		//修改任务接收人状态
		commuReceiverService.setCommuReceiverStatus(new Long(taskId), CommuReceiver.CANCEL, new Long(processTask.getAssignee()));
		//添加审批意见
		this.addOpinion(processTask, opinion, sysUser, false);
		// 发送通知。
		ProcessRun processRun=processRunService.getByActInstanceId(new Long(processTask.getProcessInstanceId()));
		Map<Long, Long> usrIdTaskIds = new HashMap<Long, Long>();
		usrIdTaskIds.put(Long.valueOf(processTask.getAssignee()), null);
		processRunService.notifyCommu(processRun.getSubject(), usrIdTaskIds, informType, 
				sysUser, opinion, SysTemplate.USE_TYPE_CANCLE_TRANSTO);
	}
	
	/**
	 * 添加审批意见
	 * @param processTask
	 * @param opinion
	 * @param sysUser
	 * @param isAdd
	 * @return
	 */
	private Long addOpinion(ProcessTask processTask, String opinion, SysUser sysUser, boolean isAdd){
		Long opinionId = UniqueIdUtil.genId();
		TaskOpinion taskOpinion = new TaskOpinion();
		taskOpinion.setOpinionId(opinionId);
		taskOpinion.setActDefId(processTask.getProcessDefinitionId());
		taskOpinion.setActInstId(processTask.getProcessInstanceId());
		if(isAdd){
			taskOpinion.setCheckStatus(TaskOpinion.STATUS_ADD_TRANSTO);
			taskOpinion.setStartTime(new Date());
			taskOpinion.setEndTime(new Date());
			taskOpinion.setOpinion(opinion);
		}else{
			taskOpinion.setCheckStatus(TaskOpinion.STATUS_CANCLE_TRANSTO);
			SysUser assignee = sysUserDao.getById(Long.valueOf(processTask.getAssignee()));
			String cancelOpinion = "取消【" + assignee.getFullname() + "】的流转任务。原因：" + opinion;
			taskOpinion.setOpinion(cancelOpinion);
			taskOpinion.setStartTime(processTask.getCreateTime());
			taskOpinion.setEndTime(new Date());
			Long durationTime = calendarAssignService.getRealWorkTime(taskOpinion.getStartTime(), 
					taskOpinion.getEndTime(), sysUser.getUserId());
			taskOpinion.setDurTime(durationTime);
		}
		taskOpinion.setExeUserId(sysUser.getUserId());
		taskOpinion.setExeFullname(sysUser.getFullname());
		taskOpinion.setTaskKey(processTask.getTaskDefinitionKey());
		taskOpinion.setTaskName(processTask.getName());
		// 增加流程意见
		taskOpinionDao.add(taskOpinion);
		return opinionId;
	}
	
	public BpmProTransTo getByTaskId(Long taskId) {
		return dao.getByTaskId(taskId);
	}
	
	public void delById(Long id){
		dao.delById(id);
	}
	
	public void delByActInstId(Long actInstId){
		dao.delByActInstId(actInstId);
	}
	
	public void delByTaskId(Long taskId){
		dao.delByTaskId(taskId);
	}
	
	/**
	 * 级联删除流转任务
	 * @param taskId
	 */
	public void cancelTransToTaskCascade(String taskId){
		List list = taskDao.getByParentTaskId(taskId);
		for(int i=0;i<list.size();i++){
			//级联删除流转任务
			ProcessTask task = (ProcessTask)list.get(i);
			taskService.deleteTask(task.getId());
			//删除被流转任务产生的沟通任务
			taskDao.delCommuTaskByParentTaskId(task.getId());
			if(task.getDescription().equals(TaskOpinion.STATUS_TRANSTO_ING.toString())){
				//删除流转状态
				delByTaskId(new Long(task.getId()));
				this.cancelTransToTaskCascade(task.getId());
			}
		}
	}
		
}
