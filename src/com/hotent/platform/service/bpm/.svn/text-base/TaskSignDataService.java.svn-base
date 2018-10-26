package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.TaskSignDataDao;
import com.hotent.platform.model.bpm.BpmRunLog;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskSignData;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.service.system.SysUserService;

/**
 * 对象功能:任务会签数据 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-19 15:29:52
 */
@Service
public class TaskSignDataService extends BaseService<TaskSignData>
{
	@Resource
	private TaskSignDataDao dao;
	
	@Resource
	private BpmService bpmService;

	@Resource
	private SysUserService sysUserService;
	@Resource
	private RuntimeService runtimeService;
	
	@Resource
	private ProcessRunService processRunService;
	
	@Resource
	private BpmRunLogService bpmRunLogService;
	public TaskSignDataService()
	{
	}
	
	@Override
	protected IEntityDao<TaskSignData, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	
	/**
	 * 根据流程实例id和节点id返回会签人员数据。
	 * @param actInstId		流程实例ID。
	 * @param nodeId		流程节点ID。
	 * @return
	 */
	public List<TaskSignData> getByNodeAndInstanceId(String actInstId,String nodeId)
	{
		return dao.getByNodeAndInstanceId(actInstId, nodeId);
	}
	
	/**
	 * 返回某个流程实例某个
	 * @param actInstId
	 * @param nodeId
	 * @return
	 */
	public Integer getMaxSignNums(String actInstId,String nodeId,Short isCompleted)
	{
		return dao.getMaxSignNums(actInstId, nodeId,isCompleted);
	}
	

	
	/**
	 * 某个任务实例的一次会签投票。
	 * <pre>
	 * 获取会签数据，并更新投票的状态，意见，日期等内容。
	 * </pre>
	 * @param taskId
	 * @param content
	 * @param isAgree
	 */
	public void signVoteTask(String taskId,String content,Short isAgree){
		SysUser sysUser=ContextUtil.getCurrentUser();
		String userId = SystemConst.SYSTEMUSERID.toString();
		String fullname = SystemConst.SYSTEMUSERNAME;
		if(BeanUtils.isNotEmpty(sysUser)){
			userId = sysUser.getUserId().toString();
			fullname = sysUser.getFullname();
		}
		TaskSignData taskSignData=dao.getByTaskId(taskId);
		if(taskSignData!=null){
			taskSignData.setIsAgree(new Short(isAgree));
			taskSignData.setContent(content);
			taskSignData.setVoteTime(new Date());
			taskSignData.setVoteUserId(userId);
			taskSignData.setVoteUserName(fullname);
			update(taskSignData);
		}
	}
	
	
	
	
	/**
	 * 取得某个流程某个节点所有的投同意票的总数
	 * @param actInstId 流程实例Id
	 * @param nodeId 节点Id
	 * @return
	 */
	public Integer getAgreeVoteCount(String actInstId,String nodeId)
	{
		return dao.getAgreeVoteCount(actInstId, nodeId);
	}
	
	/**
	 * 取得某个流程某个节点所有的投同意票的总数
	 * @param actInstId 流程实例Id
	 * @param nodeId 节点Id
	 * @return
	 */
	public Integer getRefuseVoteCount(String actInstId,String nodeId)
	{
		return dao.getRefuseVoteCount(actInstId, nodeId);
	}
	
	/**
	 * 取得某个流程某个节点所有的投同意票的总数
	 * @param actInstId 流程实例Id
	 * @param nodeId 节点Id
	 * @return
	 */
	public Integer getAbortVoteCount(String actInstId,String nodeId)
	{
		return dao.getAbortVoteCount(actInstId, nodeId);
	}
	
	/**
	 * 更新本次会签投票完成的状态
	 * @param actInstId
	 * @param nodeId
	 */
	public void batchUpdateCompleted(String actInstId,String nodeId)
	{
		dao.batchUpdateCompleted(actInstId, nodeId);
	}
	/**
	 * 增加会签。
	 * <pre>
	 * 增加会签分为两种方式处理。
	 * 1.串行会签。
	 * 2.并行会签。
	 * </pre>
	 * @param userIds 会签人员ID,如1,2
	 * @param taskId 任务ID
	 */
	public void addSign(String userIds,String taskId, String opinion, String informType)throws Exception{
		//用户为空
		if(StringUtil.isEmpty(userIds)) return ;
		//检查当前任务是否为会签任务
		TaskEntity taskEntity=bpmService.getTask(taskId);
		ExecutionEntity executionEntity=bpmService.getExecution(taskEntity.getExecutionId());
		
		String processInstanceId=executionEntity.getProcessInstanceId();
		
		ProcessDefinitionEntity proDefEntity=bpmService.getProcessDefinitionByProcessInanceId(processInstanceId);
		
		ActivityImpl actImpl=proDefEntity.findActivity(executionEntity.getActivityId());
		
		//是否为多实体任务
		String multiInstance=(String)actImpl.getProperty("multiInstance");
		
		if(multiInstance==null)	return ;
		//获取最大的此号。
		Integer maxSignNums=dao.getMaxSignNums(processInstanceId, executionEntity.getActivityId(), TaskSignData.NOT_COMPLETED);
		logger.debug("multiInstance:"+ multiInstance);
		
		Integer signNums=maxSignNums==0 ? 1:maxSignNums;
		
		List<String> uidlist = new ArrayList<String>();
		
		List<TaskSignData> existTaskSignDatas = dao.getByNodeAndInstanceId(processInstanceId,taskEntity.getTaskDefinitionKey(),0);
		Integer curBatch = 1;
		for(TaskSignData taskSignData:existTaskSignDatas){
			curBatch = taskSignData.getBatch();
			uidlist.add(taskSignData.getVoteUserId());
		}
		
		String[] uIds=userIds.split("[,]");
		//可以添加的用户。
		List<String> addUsers= getCanAddUsers(uidlist,uIds);
		//添加用户的个数
		int userAmount= addUsers.size();
		
		Map<Long, Long> userTaskMap = new HashMap<Long, Long>();

		//更新会签总实例数
		Integer nrOfInstances=(Integer)runtimeService.getVariable(executionEntity.getId(), "nrOfInstances");
		if(nrOfInstances!=null){
			runtimeService.setVariable(executionEntity.getId(), "nrOfInstances", nrOfInstances + userAmount);
		}
		//串行
		if("sequential".equals(multiInstance)){
			String nodeId=executionEntity.getActivityId();
			String varName=nodeId +"_" +BpmConst.SIGN_USERIDS;
			String exeId=executionEntity.getId();
			List<TaskExecutor> addList=new ArrayList<TaskExecutor>();
			for(int i=0;i<userAmount;i++){
				Long userId=new Long(addUsers.get(i));
				int sn=signNums+1;
				addSignData("",nodeId, taskEntity.getName(), processInstanceId, sn, userId,curBatch);
				addList.add(TaskExecutor.getTaskUser(userId.toString(), ""));
				userTaskMap.put(userId, -1L);
			}
			List<TaskExecutor> list=(List<TaskExecutor>)runtimeService.getVariable(exeId, varName);
			list.addAll(addList);
			runtimeService.setVariable(executionEntity.getId(), varName, list);
		}
		//并行的情况。
		else{
			Integer loopCounter=(Integer)runtimeService.getVariable(executionEntity.getId(), "loopCounter");
			//添加活动的线程个数
			Integer nrOfActiveInstances=(Integer)runtimeService.getVariable(executionEntity.getId(), "nrOfActiveInstances");
			runtimeService.setVariable(executionEntity.getId(), "nrOfActiveInstances", nrOfActiveInstances + userAmount);
			for(int i=0;i<userAmount;i++){
				Long userId=new Long(addUsers.get(i));
				ProcessTask newProcessTask=bpmService.newTask(taskId,addUsers.get(i));
				String executionId= newProcessTask.getExecutionId();
				
				runtimeService.setVariableLocal(executionId, "loopCounter", loopCounter + i +1);
				runtimeService.setVariableLocal(executionId, "assignee", TaskExecutor.getTaskUser(userId.toString(), "") );
				//添加会签数据
				int sn=signNums+1;
				addSignData(newProcessTask.getId(), executionEntity.getActivityId(), taskEntity.getName(), processInstanceId, sn, userId,curBatch);
			}
		}
		//写入流程运行日志
		ProcessRun processRun=processRunService.getByActInstanceId(new Long(processInstanceId));
		//处理手机端没有填写补签意见的情况以及流程对外服务接口时的情况
		if (StringUtil.isNotEmpty(opinion)) {
			//添加审批意见
			processRunService.saveAddSignOpinion(taskEntity, opinion, informType, userTaskMap, processRun.getSubject());
		}
		String memo="用户在任务["+taskEntity.getName()+"]执行了补签操作。";
		bpmRunLogService.addRunLog(processRun.getRunId(), BpmRunLog.OPERATOR_TYPE_SIGN, memo);
	}
	
	/**
	 * 添加会签数据。
	 * @param taskId		任务ID
	 * @param nodeId		节点ID
	 * @param nodeName		节点名称
	 * @param instanceId	流程实例ID
	 * @param signNums
	 * @param userId
	 */
	private void addSignData(String taskId,String nodeId,String nodeName,String instanceId,Integer signNums,Long userId,Integer batch){
		Long dataId=UniqueIdUtil.genId();
		
		TaskSignData newSignData=new TaskSignData();
		newSignData.setTaskId(taskId);

		newSignData.setDataId(dataId);
		newSignData.setNodeId(nodeId);
		newSignData.setNodeName(nodeName);
		newSignData.setActInstId(instanceId);
		newSignData.setSignNums(signNums);
		newSignData.setIsCompleted(TaskSignData.NOT_COMPLETED);
		newSignData.setIsAgree(null);
		newSignData.setBatch(batch);
		newSignData.setContent(null);
		newSignData.setVoteTime(null);
		newSignData.setVoteUserId(userId.toString());
		
		SysUser sysUser=sysUserService.getById(userId);
		newSignData.setVoteUserName(sysUser.getFullname());
		//添加
		dao.add(newSignData);
	}
	
	/**
	 * 获取可以添加的用户列表。
	 * 
	 * @param curUserList
	 * @param addUsers
	 * @return
	 */
	private List<String> getCanAddUsers(List<String> curUserList,String[] addUsers){
		List<String> users=new ArrayList<String>();
		for(String userId:addUsers){
			if(!curUserList.contains(userId)){
				users.add(userId);
			}
		}
		return users;
	}
	
	/**
	 * 根据执行人获取会签数据。
	 * @param actInstId
	 * @param nodeId
	 * @param executorId
	 * @return
	 */
	public TaskSignData getUserTaskSign(String actInstId,String nodeId,String executorId){
		return dao.getUserTaskSign(actInstId, nodeId, executorId);
	}
	
	/**
	 * 取得最大的批次。
	 * @param instanceId
	 * @param nodeId
	 * @return
	 */
	public int getMaxBatch(String instanceId, String nodeId){
		
		return dao.getMaxBatch(instanceId, nodeId);
	}

	/**
	 * 返回某个任务会签的数据
	 * @param taskId
	 * @return
	 */
	public TaskSignData getByTaskId(String taskId)
	{
		return dao.getByTaskId(taskId);
	}

//	/**
//	 * 完成任务会签
//	 * @param taskId
//	 */
//	public void completeTaskSign(String taskId)
//	{
//		//TODO
//		TaskEntity taskEntity=bpmService.getTask(taskId);
//		ExecutionEntity executionEntity=bpmService.getExecution(taskEntity.getExecutionId());
//		
//		String nodeId=executionEntity.getActivityId();
//		String actInstId=taskEntity.getProcessInstanceId();
//		Integer maxNums=getMaxSignNums(actInstId,nodeId,TaskSignData.NOT_COMPLETED);
//		
//		List<TaskSignData> signDataList=getByActInstIdNodeIdSignNums(actInstId, nodeId, maxNums);
//	}
}
