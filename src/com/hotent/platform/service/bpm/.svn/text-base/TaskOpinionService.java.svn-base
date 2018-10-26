package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.IOException;


import javax.annotation.Resource;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hotent.core.bpm.model.ProcessExecution;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.page.PageBean;
import com.hotent.core.service.BaseService;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.bpm.ExecutionDao;
import com.hotent.platform.dao.bpm.ProcessRunDao;
import com.hotent.platform.dao.bpm.TaskOpinionDao;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.util.ServiceUtil;
import com.hotent.platform.webservice.model.BpmFinishTask;
import freemarker.template.TemplateException;

/**
 * 对象功能:流程任务审批意见 Service类 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:csx 
 * 创建时间:2012-01-11 16:06:11
 */
@Service
public class TaskOpinionService extends BaseService<TaskOpinion> {
	@Resource
	private TaskOpinionDao dao;

	@Resource
	private ExecutionDao executionDao;
	@Resource
	private BpmService bpmService;
	
	@Resource
	private SysUserService sysUserService;
	@Resource
	private TaskUserService taskUserService;
	
	@Resource
	private ProcessRunDao processRunDao;

	public TaskOpinionService() {
	}
	protected static  Logger logger = LoggerFactory.getLogger(TaskOpinionService.class);
	@Override
	protected IEntityDao<TaskOpinion, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 取得对应该任务的执行
	 * 
	 * @param taskId
	 * @return
	 */
	public TaskOpinion getByTaskId(Long taskId) {
		return dao.getByTaskId(taskId);
	}

	/**
	 * 取得某个任务的所有审批意见 按时间排序
	 * 
	 * @param actInstId
	 * @return
	 */
	public List<TaskOpinion> getByActInstId(String actInstId,boolean isAsc) {
		//取得顶级的流程实例ID
		String supInstId=getTopInstId(actInstId);
		
		List<String> instList=new ArrayList<String>();
		instList.add(supInstId);
		//递归往下查询
		getChildInst(supInstId,instList);
				
		return dao.getByActInstId(instList,isAsc);
	}
	/**
	 * 取得某个任务的所有审批意见 
	 * 
	 * @param actInstId
	 * @return
	 */
	public List<TaskOpinion> getByActInstId(String actInstId) {
		return getByActInstId( actInstId,true);
	}
	/**
	 * 根据流程实例ID获取流程实例列表包括子流程实例。
	 * @param actInstId
	 * @return 
	 * Set&lt;String>
	 */
	public Set<String> getInstListByInstId(String actInstId){
		List<TaskOpinion> opinions= getByActInstId(actInstId);
		Set<String> instSet=new HashSet<String>();
		for(TaskOpinion opinion:opinions){
			if(StringUtil.isNotEmpty(opinion.getActInstId())){
				instSet.add(opinion.getActInstId());
			}
		}
		return instSet;
	}
	
	/**
	 * 根据runId获取流程意见列表。
	 * @param runId
	 * @return
	 */
	public List<TaskOpinion> getByRunId(Long runId) {
		List<String> actInstIdList=getProcessRunList(runId);
		List<TaskOpinion> list=new ArrayList<TaskOpinion>();
		
		int len=actInstIdList.size();
		for(int i=len-1;i>=0;i--){
			String instId=actInstIdList.get(i);
			List<TaskOpinion> tmplist=getByActInstId(instId);
			list.addAll(tmplist);
		}
		return list;
	}
	
	/**
	 * 根据runId获取流程实例ID。
	 * @param runId
	 * @return
	 */
	private List<String> getProcessRunList(Long runId){
		ProcessRun processRun= getTopProcessRun(runId);
		String tmpActInstId= processRun.getActInstId();
		
		List<String> list=new ArrayList<String>();
		Long relRunId=processRun.getRelRunId();
		list.add(tmpActInstId);
		String actDefId=processRun.getActDefId();
		while(BeanUtils.isNotEmpty(relRunId)){
			ProcessRun tmpRun=processRunDao.getByHistoryId(relRunId);
			if(!actDefId.equals(tmpRun.getActDefId())){
				break;
			}
			list.add(tmpRun.getActInstId());
			relRunId=tmpRun.getRelRunId();
			actDefId=tmpRun.getActDefId();
		}
		
		return list;
	}
	
	/**
	 * 获取顶级流程实例。
	 * @param runId
	 * @return
	 */
	private ProcessRun getTopProcessRun(Long runId){
		ProcessRun	processRun=processRunDao.getById(runId);
		while(BeanUtils.isNotEmpty(processRun.getParentId())){
			processRun=processRunDao.getById(processRun.getParentId());
		}
		return processRun;
	}
	
	/**
	 * 向上查询得到顶级的流程实例。
	 * @param instId
	 * @return String
	 */
	private String getTopInstId(String actInstId){
		String rtn=actInstId;
		String supInstId=dao.getSupInstByActInstId(actInstId);
		while(StringUtil.isNotEmpty(supInstId)){
			rtn=supInstId;
			supInstId=dao.getSupInstByActInstId(supInstId);
		}
		return rtn;
	}
	
	

	/**
	 * 向下查询流程实例。
	 * @param supperId
	 * @param instList 
	 * void
	 */
	private void getChildInst(String supperId,List<String> instList){
		List<TaskOpinion> list=dao.getBySupInstId(supperId);
		if(BeanUtils.isEmpty(list)) return ;
		for(TaskOpinion taskOpinion:list){
			String instId=taskOpinion.getActInstId();
			instList.add(instId);
			getChildInst(instId,instList);
		}
	}


	/**
	 * 根据act流程定义Id删除对应在流程任务审批意见
	 * 
	 * @param actDefId
	 */
	public void delByActDefIdTaskOption(String actDefId) {
		dao.delByActDefIdTaskOption(actDefId);
	}

	/**
	 * 根据流程实例Id及任务定义Key取得审批列表
	 * 
	 * @param actInstId
	 * @param taskKey
	 * @return
	 */
	public List<TaskOpinion> getByActInstIdTaskKey(Long actInstId, String taskKey) {
		return dao.getByActInstIdTaskKey(actInstId, taskKey);
	}
	
	/**
	 * 取到最新的某个节点的审批记录
	 * 
	 * @param actInstId
	 * @param taskKey
	 * @return
	 */
	public TaskOpinion getLatestTaskOpinion(Long actInstId, String taskKey) {
		List<TaskOpinion> list = getByActInstIdTaskKey(actInstId, taskKey);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 取得某个流程实例中，某用户最新的完成的审批记录
	 * 
	 * @param actInstId
	 * @param exeUserId
	 * @return
	 */
	public TaskOpinion getLatestUserOpinion(String actInstId, Long exeUserId) {
		List<TaskOpinion> taskOpinions = dao.getByActInstIdExeUserId(actInstId, exeUserId);
		if (taskOpinions.size() == 0)
			return null;
		return taskOpinions.get(0);
	}

	/**
	 * 按任务ID删除
	 * 
	 * @param taskId
	 */
	public void delByTaskId(Long taskId) {
		dao.delByTaskId(taskId);
	}

	/**
	 * 取得已经审批完成的任务
	 * 
	 * @param userId
	 * @param subject
	 *            事项名
	 * @param taskName
	 *            任务名
	 * @param pb
	 *            分页
	 * @return
	 */
	public List<BpmFinishTask> getByFinishTask(Long userId, String subject, String taskName, PageBean pb) {
		return dao.getByFinishTask(userId, subject, taskName, pb);
	}
	
	
	/**
	 * 获取正在审批的意见。
	 * @param actInstId
	 * @return
	 */
	public List<TaskOpinion> getCheckOpinionByInstId(Long actInstId){
		return dao.getCheckOpinionByInstId(actInstId);
	}

	public List<TaskOpinion> setTaskOpinionExecutor(List<TaskOpinion> opinions){
		for (TaskOpinion taskOpinion : opinions) {
			if(taskOpinion.getCheckStatus()==TaskOpinion.STATUS_CHECKING){
				TaskEntity task = bpmService.getTask(taskOpinion.getTaskId().toString());
				if(BeanUtils.isNotEmpty(task) ){
					//执行人不为空
					String assignee=task.getAssignee();
					if(ServiceUtil.isAssigneeNotEmpty(assignee)){
						String fullname = "";
						SysUser sysuser = sysUserService.getById(new Long(assignee));
						if(BeanUtils.isNotEmpty(sysuser))
							fullname =  sysuser.getFullname();
						taskOpinion.setExeFullname(fullname);
					}
					//获取候选人
					else{
						 Set<TaskExecutor> set= taskUserService.getCandidateExecutors(task.getId());
//						 String fullname="";
						 Set<SysUser> sysUsers=new HashSet<SysUser>();
						 for(Iterator<TaskExecutor> it=set.iterator();it.hasNext();){
							 TaskExecutor taskExe=it.next();
							 if(taskExe==null){
								 continue;
							 }
							 Set<SysUser> users = taskExe.getSysUser();
							 sysUsers.addAll(users);
						 }
						 if(taskOpinion.getCandidateUsers()==null){
							 taskOpinion.setCandidateUsers(new ArrayList<SysUser>());
						 }
						taskOpinion.getCandidateUsers().addAll(sysUsers);
					}
				}
			}
		}
		return opinions;
	}
	
	/**
	 * 设置执行人的名称
	 * @param list
	 * @return
	 */
	public List<TaskOpinion> setTaskOpinionExeFullname(List<TaskOpinion> list) {
		for (TaskOpinion taskOpinion : list) {
			if(taskOpinion.getCheckStatus()==TaskOpinion.STATUS_CHECKING){
				TaskEntity task = bpmService.getTask(taskOpinion.getTaskId().toString());
				if(BeanUtils.isNotEmpty(task) ){
					//执行人为空
					String assignee=task.getAssignee();
					if(ServiceUtil.isAssigneeNotEmpty(assignee)){
						String fullname = "";
						SysUser sysuser = sysUserService.getById(new Long(assignee));
						if(BeanUtils.isNotEmpty(sysuser))
							fullname =  sysuser.getFullname();
						taskOpinion.setExeFullname(fullname);
					}
					//获取候选人
					else{
						 Set<TaskExecutor> set= taskUserService.getCandidateExecutors(task.getId());
						 String fullname="";
						 for(Iterator<TaskExecutor> it=set.iterator();it.hasNext();){
							 TaskExecutor taskExe=it.next();
							 String type=taskExe.getType();
							 if(taskExe.getType().equals(TaskExecutor.USER_TYPE_USER)){
								  fullname +=  sysUserService.getById(new Long(taskExe.getExecuteId())).getFullname() +"<br/>";								 
							 } 
						 }
						taskOpinion.setExeFullname(fullname);
						taskOpinion.setCandidateUser(fullname);
					}
				}
			}
		}
		return list;
	}
	
	
	/**
	 * 设置执行人的名称
	 * @param list
	 * @return
	 */
	public List<TaskOpinion> setTaskOpinionListExeFullname(List<TaskOpinion> list) {
		for(TaskOpinion taskOpinion:list){
			if(taskOpinion.getCheckStatus()==TaskOpinion.STATUS_CHECKING){
				TaskEntity task = bpmService.getTask(taskOpinion.getTaskId().toString());
				if(BeanUtils.isNotEmpty(task) ){
					//执行人为空
					String assignee=task.getAssignee();
					if(ServiceUtil.isAssigneeNotEmpty(assignee)){
						String fullname =  sysUserService.getById(new Long(assignee)).getFullname();
						taskOpinion.setExeFullname(fullname);
					}
					//获取候选人
					else{
						 Set<TaskExecutor> set= taskUserService.getCandidateExecutors(task.getId());
						 String fullname="";
						 for(Iterator<TaskExecutor> it=set.iterator();it.hasNext();){
							 TaskExecutor taskExe=it.next();
							 String type=taskExe.getType();
							 if(taskExe.getType().equals(TaskExecutor.USER_TYPE_USER)){
								  fullname +=  sysUserService.getById(new Long(assignee)).getFullname() +"<br/>";								 
							 } 
						 }
						//taskOpinion.setExeFullname(fullname);
						taskOpinion.setCandidateUser(fullname);
					}
				}else{
					if(BeanUtils.isNotEmpty(taskOpinion.getExeUserId())){
						String fullname =  sysUserService.getById(new Long(taskOpinion.getExeUserId())).getFullname();
						taskOpinion.setExeFullname(fullname);
					}
				}
			}
			else{
				String fullname =  sysUserService.getById(new Long(taskOpinion.getExeUserId())).getFullname();
				taskOpinion.setExeFullname(fullname);
			}
		}
		return list;
	}
	
	
	/**
	 * 根据实例节点获取任务实例状态。
	 * @param actInstId
	 * @param taskKey
	 * @param checkStatus
	 * @return
	 */
	public List<TaskOpinion> getByActInstIdTaskKeyStatus(String actInstId,
			String taskKey, Short checkStatus){
		return dao.getByActInstIdTaskKeyStatus(actInstId,taskKey,checkStatus);
	}
	
	public TaskOpinion getOpinionByTaskId(Long taskId,Long userId) {
		return dao.getOpinionByTaskId(taskId, userId);
	}
	
	
	/**
	 * 根据actInstId更新。
	 * @param actInstId
	 * @param oldActInstId
	 * @return
	 */
	public int updateActInstId(String actInstId,String oldActInstId){
		return dao.updateActInstId(actInstId, oldActInstId);
	}
	
	/**
	 * 根据意见值获取意见。
	 * @param opinion
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String getOpinion(TaskOpinion opinion,boolean isHtml) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("opinion", opinion);
		FreemarkEngine freemarkEngine=AppUtil.getBean(FreemarkEngine.class);
		String rtn="";
		try {
			String template=isHtml?"opinion.ftl":"opiniontext.ftl";
			rtn = freemarkEngine.mergeTemplateIntoString(template, model);
			if(!isHtml){
				rtn=rtn.replaceAll("</?[^>]+>", "");
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return rtn;
	}
}
