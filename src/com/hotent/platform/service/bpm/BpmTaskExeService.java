package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.model.ProcessExecution;
import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestContext;
import com.hotent.platform.dao.bpm.BpmTaskExeDao;
import com.hotent.platform.dao.bpm.ExecutionDao;
import com.hotent.platform.dao.bpm.ProcessRunDao;
import com.hotent.platform.dao.bpm.TaskDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmProTransTo;
import com.hotent.platform.model.bpm.BpmTaskExe;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.SysTemplateService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.worktime.CalendarAssignService;

/**
 * <pre>
 * 对象功能:任务转办代理 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-03-27 12:02:35
 * </pre>
 */
@Service
public class BpmTaskExeService extends BaseService<BpmTaskExe> {
	@Resource
	private BpmTaskExeDao dao;
	@Resource
	private ProcessRunDao processRunDao;
	@Resource
	private BpmService bpmService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private TaskDao taskDao;
	@Resource
	private TaskOpinionService taskOpinionService;
	@Resource
	private SysTemplateService sysTemplateService;
	@Resource
	private TaskService taskService;
	@Resource
	private CalendarAssignService calendarAssignService;
	@Resource
	private TaskMessageService taskMessageService;
	@Resource
	private BpmProTransToService bpmProTransToService;
	@Autowired
	private RuntimeService runtimeService;
	@Resource
	private ExecutionDao executionDao;
	@Resource
	private ProcessRunService  processRunService;

	public BpmTaskExeService() {
	}

	@Override
	protected IEntityDao<BpmTaskExe, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 添加任务转办或代理。
	 * 
	 * <pre>
	 * 	1.添加转办消息。
	 *  2.修改任务类型为转办。
	 *  3.删除产生该人的通知任务。
	 *  4.将原来的任务意见修改成当前执行人，并添加备注。
	 *  5.添加一条新的意见，状态为待审批，执行人为转办的人。
	 *  4.发送信息通知沟通发起人。
	 * </pre>
	 * 
	 * @param bpmTaskExe
	 * @throws Exception
	 */
	public void assignSave(BpmTaskExe bpmTaskExe) throws Exception {
		// 保存代理信息
		dao.add(bpmTaskExe);
		Long taskId = bpmTaskExe.getTaskId();
		Short assignType = bpmTaskExe.getAssignType();
		Long assigneeId = bpmTaskExe.getAssigneeId();
		Short opinionStatus = TaskOpinion.STATUS_DELEGATE;
		Integer useType=0;
		Integer agentUseType= 0 ;//被代理人的信息模板代号
		if (BpmTaskExe.TYPE_ASSIGNEE.equals(assignType)) {
			opinionStatus = TaskOpinion.STATUS_AGENT;
			useType=SysTemplate.USE_TYPE_AGENT;
			agentUseType = SysTemplate.USE_TYPE_NOTIFYOWNER_AGENT;
			
		} 
		else if (BpmTaskExe.TYPE_TRANSMIT.equals(assignType)) {
			opinionStatus = TaskOpinion.STATUS_DELEGATE;
			taskDao.updateTask(taskId.toString(), assigneeId.toString(), opinionStatus.toString());
			useType=SysTemplate.USE_TYPE_DELEGATE;
			
			// 如果是多级转办，需要更新状态
			List<BpmTaskExe> list = getByTaskIdStatusInit(taskId);
			if(BeanUtils.isNotEmpty(list) && list.size()>1){
				for(BpmTaskExe task:list){
					task.setAssignType(BpmTaskExe.TYPE_MUTIL_ASSIGNEE);
					update(task);
				}
			}
		}

		// 取消通知任务。
		taskDao.delCommuTaskByInstNodeUser(bpmTaskExe.getActInstId(),bpmTaskExe.getTaskDefKey(), assigneeId);
		// 将原来的任务审批意见修改成代办状态，并填写任务意见。
		TaskOpinion taskOpinion = taskOpinionService.getByTaskId(taskId);
		taskOpinion.setCheckStatus(opinionStatus);

		// 如果是转办，更新执行人
		if (BpmTaskExe.TYPE_TRANSMIT.equals(assignType)) {
			SysUser sysUser = ContextUtil.getCurrentUser();
			taskOpinion.setExeUserId(sysUser.getUserId());
			taskOpinion.setExeFullname(sysUser.getFullname());
		}
		taskOpinion.setOpinion(bpmTaskExe.getMemo());
		taskOpinion.setEndTime(new Date());
		Long duration = calendarAssignService.getRealWorkTime(taskOpinion.getStartTime(), taskOpinion.getEndTime(), taskOpinion.getExeUserId());
		taskOpinion.setDurTime(duration);
		taskOpinionService.update(taskOpinion);
		
		// 新添加一个任务意见，状态为初始值。
		ProcessRun processRun = processRunDao.getById(bpmTaskExe.getRunId());
		TaskOpinion newOpinion = new TaskOpinion();
		newOpinion.setOpinionId(UniqueIdUtil.genId());
		newOpinion.setActInstId(processRun.getActInstId());
		newOpinion.setActDefId(processRun.getActDefId());
		newOpinion.setCheckStatus(TaskOpinion.STATUS_CHECKING);
		newOpinion.setStartTime(new Date());
		newOpinion.setTaskKey(bpmTaskExe.getTaskDefKey());
		newOpinion.setTaskName(bpmTaskExe.getTaskName());
		newOpinion.setTaskId(taskId);
		newOpinion.setExeUserId(assigneeId);
		newOpinion.setExeFullname(bpmTaskExe.getAssigneeName());
		
		TaskEntity taskEntity=bpmService.getTask(taskId.toString());
		this.dealTaskOpinSupId(taskEntity, newOpinion);

		
		taskOpinionService.add(newOpinion);
		
		//发送转办/代理人消息
		this.sendMessage(bpmTaskExe, bpmTaskExe.getInformType(), useType, agentUseType, bpmTaskExe.getMemo(), false);
	}
	/**
	 * 发送消息
	 * @param bpmTaskExe
	 * @param userType
	 * @param moduleType
	 * @throws Exception
	 */
	private void sendMessage(BpmTaskExe bpmTaskExe,String informType, Integer userType, Integer agentUseType,
			 String opinion,boolean cancel) throws Exception {
		
		//取消时设置任务id为空。
		Long taskId=cancel?null:bpmTaskExe.getTaskId();
		
		Map<String, String> msgTempMap = sysTemplateService.getTempByFun(userType);
		
		Long receiverId = bpmTaskExe.getAssigneeId();
		SysUser assignUser = sysUserService.getById(receiverId);
		List<SysUser> receiverUserList=new ArrayList<SysUser>();
		receiverUserList.add(assignUser);
		SysUser curUser = ContextUtil.getCurrentUser();
		taskMessageService.sendMessage(curUser, receiverUserList, informType, msgTempMap, bpmTaskExe.getSubject(), opinion, taskId, null,null);
		
		//处理通知任务所属人(代理)的消息
		if (agentUseType != 0) {
			Map<String, String> msgAgeTempMap = sysTemplateService.getTempByFun(agentUseType);
			long owerId = bpmTaskExe.getOwnerId();
			SysUser owerUser = sysUserService.getById(owerId);
			List<SysUser> receiverAgeUserList=new ArrayList<SysUser>();
			receiverAgeUserList.add(owerUser);
			taskMessageService.sendMessage(curUser, receiverUserList, informType, msgTempMap, bpmTaskExe.getSubject(), opinion, taskId, null,null);
		}

	}

	/**
	 * 取消转办/代理/流转。
	 * 
	 * @param bpmTaskExe
	 * @param sysUser
	 * @param opinion
	 * @param informType
	 * @return
	 * @throws Exception 
	 */
	public ProcessRun doCancel(BpmTaskExe bpmTaskExe, SysUser sysUser,
			String opinion, String informType) throws Exception {
		// 在转办任务中该记录标记为取消
		Short opininStatus = TaskOpinion.STATUS_CHECKING;
		
		SysUser curUser = ContextUtil.getCurrentUser();

		String memo = bpmTaskExe.getMemo() + ",<br/>取消了该任务原因:" + opinion;
		bpmTaskExe.setMemo(memo);
		bpmTaskExe.setExeUserId(bpmTaskExe.getOwnerId());
		bpmTaskExe.setExeUserName(bpmTaskExe.getOwnerName());
		bpmTaskExe.setStatus(BpmTaskExe.STATUS_CANCEL);
		bpmTaskExe.setExeTime(new Date());
		dao.update(bpmTaskExe);

		/****
		 * // 设置任务执行人为本人 taskDao.updateTask(bpmTaskExe.getTaskId().toString(),
		 * sysUser.getUserId().toString(), opininStatus.toString() );
		 ***/
			taskDao.updateTask(bpmTaskExe.getTaskId().toString(),sysUser.getUserId().toString(), opininStatus.toString() );
			
		// 获取任务意见并进行修改。
		TaskOpinion taskOpinion = taskOpinionService.getByTaskId(bpmTaskExe.getTaskId());
		// 状态修改为取消。
		taskOpinion.setCheckStatus(TaskOpinion.STATUS_DELEGATE_CANCEL);
		taskOpinion.setExeUserId(curUser.getUserId());
		taskOpinion.setExeFullname(curUser.getFullname());
		taskOpinion.setOpinion(opinion);
		taskOpinion.setEndTime(BeanUtils.isEmpty(taskOpinion.getEndTime())?new Date():taskOpinion.getEndTime());
		Long duration = calendarAssignService.getRealWorkTime(taskOpinion.getStartTime(), taskOpinion.getEndTime(), sysUser.getUserId());
		taskOpinion.setDurTime(duration);
		taskOpinion.setEndTime(new Date());
		taskOpinionService.update(taskOpinion);

		// 新添加一个任务意见，状态为初始值。
		ProcessRun processRun = processRunDao.getById(bpmTaskExe.getRunId());
		TaskOpinion newOpinion = new TaskOpinion();
		newOpinion.setOpinionId(UniqueIdUtil.genId());
		newOpinion.setActInstId(processRun.getActInstId());
		newOpinion.setActDefId(processRun.getActDefId());
		newOpinion.setCheckStatus(TaskOpinion.STATUS_CHECKING);
		newOpinion.setStartTime(new Date());
		newOpinion.setTaskKey(bpmTaskExe.getTaskDefKey());
		newOpinion.setTaskName(bpmTaskExe.getTaskName());
		newOpinion.setTaskId(bpmTaskExe.getTaskId());
		newOpinion.setExeUserId(bpmTaskExe.getOwnerId());
		newOpinion.setExeFullname(bpmTaskExe.getOwnerName());
		
		TaskEntity taskEntity=bpmService.getTask(bpmTaskExe.getId().toString());	
		this.dealTaskOpinSupId(taskEntity, newOpinion);
		
		taskOpinionService.add(newOpinion);
		
		Integer userType = SysTemplate.USE_TYPE_CANCLE_DELEGATE;
		if (BpmTaskExe.TYPE_ASSIGNEE.equals(bpmTaskExe.getAssignType())) {
			userType = SysTemplate.USE_TYPE_CANCLE_AGENT;
		} else if (BpmTaskExe.TYPE_TRANSMIT.equals(bpmTaskExe.getAssignType())) {
			userType = SysTemplate.USE_TYPE_CANCLE_DELEGATE;
		} 
		// 转办人通知被转办人。
		this.sendMessage(bpmTaskExe, informType,userType,0, opinion,true);

		return processRun;

	}
	
	/**
	 * 在转办的情况下，进行多级取消转办
	 * @param bpmTaskExe
	 * @param sysUser
	 * @param opinion
	 * @param informType
	 * @throws Exception
	 */
	public void cancel(BpmTaskExe bpmTaskExe, SysUser sysUser, String opinion, String informType) throws Exception {
		if(BpmTaskExe.TYPE_TRANSMIT.equals(bpmTaskExe.getAssignType())){
			cancelMutilLevel(bpmTaskExe, sysUser, opinion, informType);
		}else {
			doCancel(bpmTaskExe, sysUser, opinion, informType);
		}
	}
	
	public void cancelMutilLevel(BpmTaskExe bpmTaskExe, SysUser sysUser, String opinion, String informType) throws Exception {
		// 查看是否还有下级转办
		Map<String, Object> params = new HashMap<String, Object>() ;
		params.put("ownerId", bpmTaskExe.getAssigneeId());
		params.put("taskId", bpmTaskExe.getTaskId());
		params.put("status", BpmTaskExe.STATUS_INIT);
		QueryFilter filter = new QueryFilter(RequestContext.getHttpServletRequest());
		filter.setFilters(params);
		List<BpmTaskExe> taskList = getAll(filter);
		if(BeanUtils.isNotEmpty(taskList)){
			// 继续对下级转办进行取消操作
			SysUser user = sysUserService.getById(bpmTaskExe.getAssigneeId()) ;
			cancelMutilLevel(taskList.get(0), user, opinion, informType);
		}
		doCancel(bpmTaskExe, sysUser, opinion, informType);
	}
	
	/**
	 * 批量取消转办。
	 * 
	 * @param ids
	 * @param opinion
	 * @param informType
	 * @throws Exception 
	 */
	public List<BpmTaskExe> cancelBat(String ids, String opinion,
			String informType, SysUser sysUser) throws Exception {
		String[] aryId = ids.split(",");
		List<BpmTaskExe> list = new ArrayList<BpmTaskExe>();
		for (int i = 0; i < aryId.length; i++) {
			Long lId = Long.parseLong(aryId[i]);
			BpmTaskExe bpmTaskExe = dao.getById(lId);
			Long taskId = bpmTaskExe.getTaskId();
			TaskEntity taskEntity = bpmService.getTask(taskId.toString());
			if (taskEntity == null) {
				MessageUtil.addMsg("<span class='red'>"
						+ bpmTaskExe.getSubject() + "已经结束!</span><br/>");
				continue;
			}
			cancel(bpmTaskExe, sysUser, opinion, informType);
			list.add(bpmTaskExe);
			MessageUtil.addMsg("<span class='red'>" + bpmTaskExe.getSubject()
					+ ",已经取消成功!</span><br/>");
		}
		return list;

	}


	/**
	 * 通过任务ID取消记录
	 * 
	 * @param taskId
	 */
	public void cancel(Long taskId) {
		List<BpmTaskExe> list = getByTaskIdStatusInit(taskId);
		if (BeanUtils.isNotEmpty(list)) {
			for(BpmTaskExe bpmTaskExe:list){
				bpmTaskExe.setStatus(BpmTaskExe.STATUS_CANCEL);
				dao.update(bpmTaskExe);
			}
		}
	}

	/**
	 * 完成任务时更新转办任务的状态。
	 * 
	 * @param taskId
	 */
	public void complete(Long taskId) {
		List<BpmTaskExe> list = getByTaskId(taskId);
		SysUser sysuer = ContextUtil.getCurrentUser();
		for (BpmTaskExe bpmTaskExe : list) {
			if (bpmTaskExe.getStatus().shortValue() == BpmTaskExe.STATUS_INIT) {
				bpmTaskExe.setExeTime(new Date());
				bpmTaskExe.setExeUserId(sysuer.getUserId());
				bpmTaskExe.setExeUserName(sysuer.getFullname());
				if (bpmTaskExe.getAssigneeId().equals(sysuer.getUserId())) {
					bpmTaskExe.setStatus(BpmTaskExe.STATUS_COMPLETE);
				} else {
					bpmTaskExe.setStatus(BpmTaskExe.STATUS_OTHER_COMPLETE);
				}
			} 
			dao.update(bpmTaskExe);
		}
	}

	public List<BpmTaskExe> getByTaskIdStatusInit(Long taskId) {
		List<BpmTaskExe> bpmTaskExe = dao.getByTaskIdStatus(taskId,
				BpmTaskExe.STATUS_INIT);
		return bpmTaskExe;
	}

	/**
	 * 根据任务ID获得任务转办代理
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 */
	public List<BpmTaskExe> getByTaskId(Long taskId) {
		return dao.getByTaskId(taskId);
	}

	/**
	 * 判断是否允许转办 。
	 * 
	 * @param taskId
	 * @return 允许为false，不允许为true
	 */
	public boolean isAssigneeTask(Long taskId) {
		return BeanUtils.isNotEmpty(getByTaskIdStatusInit(taskId)) ? true
				: false;
	}

	/**
	 * 返回流程的状态
	 * 
	 * @param list
	 * @return
	 */
	public Map<String, Short> getTaskMap(List<?> list) {
		Map<String, Short> map = new HashMap<String, Short>();
		if (BeanUtils.isNotEmpty(list)) {
			SysUser user = ContextUtil.getCurrentUser() ;
			QueryFilter filter = new QueryFilter(RequestContext.getHttpServletRequest());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("ownerId", user.getUserId()) ;
			params.put("status", BpmTaskExe.STATUS_INIT) ;
			for (int i = 0; i < list.size(); i++) {
				ProcessTask task = (ProcessTask) list.get(i);
				params.put("taskId", task.getId()) ;
				filter.setFilters(params);
				List<BpmTaskExe> bpmTaskExe = getAll(filter);
				if (BeanUtils.isEmpty(bpmTaskExe)) {
					map.put(task.getId(), (short) 0);
				} else {
					map.put(task.getId(), bpmTaskExe.get(0).getAssignType());
				}
			}
		}
		return map;
	}

	public List<BpmTaskExe> accordingMattersList(QueryFilter filter) {
		return dao.accordingMattersList(filter);
	}
	
	/**
	 * 判断是否可以转办。
	 * @param taskId
	 * @param bpmDefinition
	 * @return
	 */
	public boolean isAssigneeTask(TaskEntity taskEnt,BpmDefinition bpmDefinition){
		//在第一个节点上不能转办。
		boolean isFirstNode= NodeCache.isFirstNode(taskEnt.getProcessDefinitionId(), taskEnt.getTaskDefinitionKey());
		if(isFirstNode){
			return false;
		}
		boolean rtn= bpmDefinition.getAllowDivert()==1;
		if(!rtn) return false;

		//驳回到发起人 、 驳回 、已经转办的任务不能在转办      《？？？？？？    以下还木有成功实现 驳回不能转办的判定。？？？？？》
		//现改为已经转办的任务可进行多级转办
		
		boolean isCanAssignee =BpmTaskUtil.isCanAssignee(taskEnt.getDescription());
		
		return isCanAssignee;
	}
	
	/**
	 * 根据任务ID取得代办。
	 * @param taskId
	 * @return
	 */
	public boolean getByIsAssign(Long taskId){
		int rtn= dao.getByIsAssign(taskId);
		return (rtn>0);
			
	}
	
	/**
	 * 根据流程实例ID删除转办代理事宜
	 * @param runId
	 */
	public void delByRunId(Long runId) {
		dao.delByRunId(runId);
	}

	public List<BpmTaskExe> getByRunId(Long runId) {
		return dao.getByRunId(runId);
	}
	
	/**
	 * 處理審批意見是否添加父流程Id，如果是外部子流程則添加，否則就不添加為空
	 * @param taskEntity
	 * @param taskOpinion
	 */
	private void dealTaskOpinSupId(TaskEntity taskEntity, TaskOpinion taskOpinion) {
		if (BeanUtils.isEmpty(taskEntity)) return;
		//加签会签时，获取流转的主任务
		while (taskEntity.getExecutionId()==null) {
			String superActinstId= processRunService.getSuperActInstId(taskEntity.getProcessInstanceId());
			
			taskOpinion.setSuperExecution(superActinstId);
				
			//外部子流程设置流程实例
			taskOpinion.setActInstId(taskEntity.getProcessInstanceId());
			taskOpinion.setActDefId(taskEntity.getProcessDefinitionId());

	}
	}
	
}