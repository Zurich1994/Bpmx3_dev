package com.hotent.platform.service.bpm.listener;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.cxf.binding.soap.interceptor.StartBodyInterceptor;

import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.BpmNodeSetDao;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskFork;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.AgentSettingService;
import com.hotent.platform.service.bpm.BpmNodeUserService;
import com.hotent.platform.service.bpm.BpmProStatusService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.TaskForkService;
import com.hotent.platform.service.bpm.TaskOpinionService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.bpm.thread.TaskThreadService;
import com.hotent.platform.service.bpm.thread.TaskUserAssignService;

/**
 * 一般任务开始事件监听器。
 * <pre>
 * 1.携带上一任务的分发令牌。
 * 2.记录新产生的任务，为后续的回退作准备。
 * 3.添加流程意见。
 * 4.启动流程时添加或保存状态数据。
 * 5.如果当前节点是分发节点则进行任务分发，并直接返回，否则往下执行。
 * 6.从上下文中获取分配人员的数据，进行分配，如果有人员则进行分配，如果没有则往下执行。
 * 7.从数据库中加载人员进行人员分配。
 * 
 * </pre>
 * @author ray
 *
 */
public class TaskCreateListener extends BaseTaskListener {
	
	@Resource
	private TaskOpinionService taskOpinionService;
	@Resource
	private TaskUserAssignService taskUserAssignService;
	@Resource
	private TaskForkService taskForkService;
	@Resource
	private BpmNodeSetDao bpmNodeSetDao;
	@Resource
	private BpmService bpmService;
	@Resource
	private BpmProStatusService bpmProStatusService;
	@Resource
	private AgentSettingService agentSettingService;
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private ProcessRunService processRunService;
	
	
	@Override
	protected void execute(DelegateTask delegateTask,String actDefId,String nodeId)  {
		//设置任务状态。
		delegateTask.setDescription(TaskOpinion.STATUS_CHECKING.toString());
		//setTaskStatus(TaskThreadService.getProcessCmd(), delegateTask);
		//携带上一任务的分发令牌
		String token=TaskThreadService.getToken();
		if(token!=null){
			delegateTask.setVariableLocal(TaskFork.TAKEN_VAR_NAME, token);
		}
		//记录新产生的任务，为后续的回退作准备
		TaskThreadService.addTask((TaskEntity)delegateTask);
		//生成任务签批意见
		addOpinion(token,delegateTask);
		
		Long actInstanceId=new Long ( delegateTask.getProcessInstanceId());
		
		//启动流程时添加或保存状态数据。
		bpmProStatusService.addOrUpd(actDefId, actInstanceId,nodeId);
		
		Map<String,List<TaskExecutor>> nodeUserMap=taskUserAssignService.getNodeUserMap();
		//处理任务分发。
		boolean isHandForkTask=handlerForkTask(actDefId,nodeId,nodeUserMap,delegateTask);
		if(isHandForkTask) return;
		
		boolean isSubProcess=handSubProcessUser(delegateTask);
		if(isSubProcess) return;
		//处理外部子流程用户。
		boolean isHandExtUser= handExtSubProcessUser(delegateTask);
		if(isHandExtUser) return;
		
		//在上一步中指定了该任务的执行人员
		if(nodeUserMap!=null && nodeUserMap.get(nodeId)!=null){
			List<TaskExecutor> executorIds=nodeUserMap.get(nodeId);
			assignUser(delegateTask,executorIds);
			return;
		}
		
		List<TaskExecutor> executorUsers = taskUserAssignService.getExecutors();
		//当前执行人。
		if(BeanUtils.isNotEmpty(executorUsers)){
			assignUser(delegateTask,executorUsers);
			return;
		}
		//处理从数据库加载用户，并进行分配。
		handAssignUserFromDb(actDefId,nodeId,delegateTask);
	}
	
	/**
	 * 处理内部子流程的人员分配。
	 * <pre>
	 * 	将任务的执行变量人取出，指定给任务执行人。
	 * </pre>
	 * @param delegateTask
	 * @return
	 */
	private boolean handSubProcessUser(DelegateTask delegateTask){
		FlowNode flowNode=NodeCache.getByActDefId(delegateTask.getProcessDefinitionId()).get(delegateTask.getTaskDefinitionKey());
		boolean isMultipleNode=flowNode.getIsMultiInstance();
		if(!isMultipleNode) return false;
		//若为多实例子流程中的任务，则从线程中的人员取出，并且把该人员从线程中删除
		TaskExecutor taskExecutor=(TaskExecutor)delegateTask.getVariable("assignee");
		if(taskExecutor!=null){
			//分配任务执行人。
			assignUser(delegateTask,taskExecutor);
			
			int completeInstance=(Integer)delegateTask.getVariable("nrOfCompletedInstances");
			int nrOfInstances=(Integer)delegateTask.getVariable("nrOfInstances");
			//清空该人员集合
			if( completeInstance==nrOfInstances){
				delegateTask.removeVariable(BpmConst.SUBPRO_MULTI_USERIDS);
			}
		}
		return true;
	}
	
	/**
	 * 外部子流程流程多实例任务人员分配。
	 * @param delegateTask
	 * @return
	 * @throws Exception 
	 */
	private boolean handExtSubProcessUser(DelegateTask delegateTask) {
		ExecutionEntity executionEnt=(ExecutionEntity) delegateTask.getExecution() ;
		//没有父流程
		if(executionEnt.getSuperExecution()==null) return false;
		if(! BpmUtil.isMuiltiExcetion(executionEnt.getSuperExecution())) return false;
		String actDefId=executionEnt.getSuperExecution().getProcessDefinitionId();
		Map<String, FlowNode> mapParent= NodeCache.getByActDefId(actDefId);
		
		String parentNodeId=executionEnt.getSuperExecution().getActivityId();
		String curentNodeId=executionEnt.getActivityId();
		
		FlowNode parentFlowNode= mapParent.get(parentNodeId);
		Map<String, FlowNode> subNodeMap=parentFlowNode.getSubProcessNodes();
		FlowNode startNode= NodeCache.getStartNode(subNodeMap);
		
		if(startNode.getNextFlowNodes().size()==1){
			FlowNode nextNode=startNode.getNextFlowNodes().get(0);
			if(nextNode.getNodeId().equals(curentNodeId)){
				TaskExecutor taskExecutor=(TaskExecutor) executionEnt.getSuperExecution().getVariable("assignee");
				if(taskExecutor!=null){
					assignUser(delegateTask,taskExecutor);
				}
				return true;
			}
			return false;
		}
		logger.debug("多实例外部调用子流程起始节点后只能跟一个任务节点");
		return false;
		
	}
	
	
	/**
	 * 添加流程任务意见。
	 * @param token
	 * @param delegateTask
	 */
	private void addOpinion(String token,DelegateTask delegateTask){
		TaskOpinion taskOpinion=new TaskOpinion(delegateTask);
		taskOpinion.setOpinionId(UniqueIdUtil.genId());
		taskOpinion.setTaskToken(token);
		taskOpinionService.add(taskOpinion);
	}
	
	/**
	 * 从数据库加载人员并分配用户。
	 * @param actDefId
	 * @param nodeId
	 * @param delegateTask
	 */
	private void handAssignUserFromDb(String actDefId,String nodeId,DelegateTask delegateTask){
		BpmNodeUserService userService=(BpmNodeUserService) AppUtil.getBean(BpmNodeUserService.class);
		
		String actInstId=delegateTask.getProcessInstanceId();
		
		ProcessInstance processInstance=bpmService.getProcessInstance(actInstId);
		List<TaskExecutor> users=null; 
		//获取流程变量。
		Map<String,Object> vars=delegateTask.getVariables();
		
		vars.put(BpmConst.EXECUTION_ID_, delegateTask.getExecutionId());
		//执行任务的情况
		if(processInstance!=null){
			//获取上个任务的执行人，这个执行人在上一个流程任务的完成事件中进行设置。
			//代码请参考TaskCompleteListener。
			String startUserId=(String)vars.get(BpmConst.StartUser);
			
			String preStepUserId=ContextUtil.getCurrentUserId().toString();
			Long preStepOrgId=ContextUtil.getCurrentOrgId();
			vars.put(BpmConst.PRE_ORG_ID, preStepOrgId);
			
			if(StringUtil.isEmpty(startUserId) && vars.containsKey(BpmConst.PROCESS_INNER_VARNAME)){
				Map<String,Object> localVars=(Map<String,Object>)vars.get(BpmConst.PROCESS_INNER_VARNAME);
				startUserId=(String)localVars.get(BpmConst.StartUser);
			}
			
			users=userService.getExeUserIds(actDefId, actInstId, nodeId, startUserId,preStepUserId,vars);
		}
		//启动流程
		else{
			//startUser
			//上个节点的任务执行人
			String startUserId=(String)vars.get(BpmConst.StartUser);
			//内部子流程启动
			if(StringUtil.isEmpty(startUserId) && vars.containsKey(BpmConst.PROCESS_INNER_VARNAME)){
				Map<String,Object> localVars=(Map<String,Object>)vars.get(BpmConst.PROCESS_INNER_VARNAME);
				startUserId=(String)localVars.get(BpmConst.StartUser);
			}
			users=userService.getExeUserIds(actDefId, actInstId, nodeId, startUserId, startUserId,vars);
		}
		assignUser(delegateTask,users);
	}
	
	/**
	 * 处理任务分发。
	 * <pre>
	 * 	1.根据指定的用户产生新的任务，并指定了相应的excution，任务历史数据。
	 * 		支持用户独立的往下执行，不像会签的方式需要等待其他的任务完成才往下执行。
	 *  2.产生分发记录。
	 *   
	 * </pre>
	 * @param actDefId			流程定义ID
	 * @param nodeId			流程节点ID
	 * @param nodeUserMap		上下文指定的分发用户。
	 * @param delegateTask		任务对象。
	 * @return
	 */
	private boolean handlerForkTask(String actDefId,String nodeId,Map<String,List<TaskExecutor>> nodeUserMap,DelegateTask delegateTask){
		//若任务进行回退至分发任务节点上，则不再进行任务分发
		ProcessCmd processCmd=TaskThreadService.getProcessCmd();
		if(processCmd!=null && BpmConst.TASK_BACK.equals(processCmd.isBack())) return false;
		BpmNodeSet bpmNodeSet=bpmNodeSetDao.getByActDefIdNodeId(actDefId, nodeId);
		//当前任务为分发任务,即根据当前分发要求进行生成分发任务
		if(bpmNodeSet!=null && BpmNodeSet.NODE_TYPE_FORK.equals(bpmNodeSet.getNodeType())){
			List<TaskExecutor> taskExecutors=taskUserAssignService.getExecutors();
			//若当前的线程里包含了该任务对应的执行人员列表，则任务的分发用户来自于此
			if(BeanUtils.isEmpty(taskExecutors)){
				//若当前的线程里包含了该任务对应的执行人员列表，则任务的分发用户来自于此
				if(nodeUserMap!=null && nodeUserMap.get(nodeId)!=null){
					taskExecutors=nodeUserMap.get(nodeId);
				}
				//否则，从数据库获取人员设置
				else{
					BpmNodeUserService userService=(BpmNodeUserService) AppUtil.getBean(BpmNodeUserService.class);
					ProcessInstance processInstance=bpmService.getProcessInstance(delegateTask.getProcessInstanceId());
					if(processInstance!=null){
						Map<String,Object> vars=delegateTask.getVariables();
						vars.put("executionId", delegateTask.getExecutionId());
						String preTaskUser=ContextUtil.getCurrentUserId().toString();
						String actInstId=delegateTask.getProcessInstanceId();
						String startUserId=(String)delegateTask.getVariable(BpmConst.StartUser);
						taskExecutors = userService.getExeUserIds(actDefId, actInstId, nodeId, startUserId, preTaskUser, vars);
					}
				}
			}
			if(BeanUtils.isNotEmpty(taskExecutors)){
				bpmService.newForkTasks((TaskEntity)delegateTask, taskExecutors);
				taskForkService.newTaskFork(delegateTask,bpmNodeSet.getJoinTaskName(), bpmNodeSet.getJoinTaskKey(), taskExecutors.size());
			}
			else{
				ProcessRun processRun= processRunService.getByActInstanceId(new Long( delegateTask.getProcessInstanceId()));
			    String msg=processRun.getSubject() + "请设置分发人员";
			    MessageUtil.addMsg(msg);
				throw new RuntimeException(msg);
				
			}
			
			return true;	
		}
		return false;
	}
	

	
	/**
	 * 分配用户执行人或候选人组。
	 * @param delegateTask
	 * @param taskExecutor
	 */
	private void assignUser(DelegateTask delegateTask, TaskExecutor taskExecutor){
		if(TaskExecutor.USER_TYPE_USER.equals(taskExecutor.getType())){
			delegateTask.setOwner(taskExecutor.getExecuteId());
			
			Long sysUserId = Long.valueOf(taskExecutor.getExecuteId());
			SysUser sysUser =null;
			
			//取代理用户
			if(isAllowAgent()){
				sysUser = agentSettingService.getAgent(delegateTask,sysUserId);
			}
			
			if(sysUser!=null){
				delegateTask.setAssignee(sysUser.getUserId().toString());
				delegateTask.setDescription(TaskOpinion.STATUS_AGENT.toString());
				delegateTask.setOwner(taskExecutor.getExecuteId());
			}else{
				delegateTask.setAssignee(taskExecutor.getExecuteId());
			}
			TaskOpinion taskOpinion= taskOpinionService.getByTaskId(new Long(delegateTask.getId()));
			SysUser exeUser= sysUserDao.getById(sysUserId);
			taskOpinion.setExeUserId(exeUser.getUserId());
			taskOpinion.setExeFullname(exeUser.getFullname());
			taskOpinionService.update(taskOpinion);
		}
		else{
			delegateTask.setAssignee(BpmConst.EMPTY_USER);
			delegateTask.setOwner(BpmConst.EMPTY_USER);
			List<TaskExecutor> userList= getByTaskExecutor(taskExecutor);
			for(TaskExecutor ex:userList){
				if(ex.getType().equals(TaskExecutor.USER_TYPE_USER)){
					delegateTask.addCandidateUser(ex.getExecuteId());
				}
				else{
					delegateTask.addGroupIdentityLink(ex.getExecuteId(),ex.getType());
				}
			}
		}
	}
	
	/**
	 * 分配任务执行人。
	 * @param delegateTask
	 * @param users
	 */
	private void assignUser(DelegateTask delegateTask, List<TaskExecutor> executors){
		if(BeanUtils.isEmpty(executors)){
			String msg="节点:" + delegateTask.getName() +",没有设置执行人";
			MessageUtil.addMsg(msg);
			throw new RuntimeException(msg);
		} 
			
		//只有一个人的情况。
		if(executors.size()==1){
			TaskExecutor taskExecutor=executors.get(0);
			
			if(TaskExecutor.USER_TYPE_USER.equals(taskExecutor.getType())){
				
				//是否是流程启动，并跳过第一个节点
				Long sysUserId = Long.valueOf(taskExecutor.getExecuteId());
				SysUser sysUser =null;
				//取代理用户
				if(isAllowAgent()){
					sysUser = agentSettingService.getAgent(delegateTask,sysUserId);
				}
				//有代理人员的情况
				if(sysUser!=null){
					delegateTask.setAssignee(sysUser.getUserId().toString());
					delegateTask.setDescription(TaskOpinion.STATUS_AGENT.toString());
					delegateTask.setOwner(taskExecutor.getExecuteId());
				}else{
					delegateTask.setAssignee(taskExecutor.getExecuteId());
				}
				
				TaskOpinion taskOpinion= taskOpinionService.getByTaskId(new Long(delegateTask.getId()));
				sysUser= sysUserDao.getById(sysUserId);
				taskOpinion.setExeUserId(sysUser.getUserId());
				taskOpinion.setExeFullname(sysUser.getFullname());
				
				taskOpinionService.update(taskOpinion);
			}
			else{
				delegateTask.setAssignee(BpmConst.EMPTY_USER);
				delegateTask.setOwner(BpmConst.EMPTY_USER);
				List<TaskExecutor> list=getByTaskExecutor(taskExecutor);
				if(BeanUtils.isEmpty(list)){
					String msg=getNotAssignMessage(taskExecutor);
					MessageUtil.addMsg(msg);
				}
				for(TaskExecutor ex:list){
					if(ex.getType().equals(TaskExecutor.USER_TYPE_USER)){
						delegateTask.addCandidateUser(ex.getExecuteId());
					}
					else{
						delegateTask.addGroupIdentityLink(ex.getExecuteId(), ex.getType());
					}
				}
			}
		}
		else{
			delegateTask.setAssignee(BpmConst.EMPTY_USER);
			delegateTask.setOwner(BpmConst.EMPTY_USER);
			
			Set<TaskExecutor> set=getByTaskExecutors(executors);
			if(BeanUtils.isEmpty(set)){
				String msg="没有设置人员,请检查人员配置!";
				MessageUtil.addMsg(msg);
				throw new RuntimeException(msg);
			}
			for(Iterator<TaskExecutor> it=set.iterator();it.hasNext();){
				TaskExecutor ex=it.next();
				if(ex.getType().equals(TaskExecutor.USER_TYPE_USER)){
					delegateTask.addCandidateUser(ex.getExecuteId());
				}
				else{
					delegateTask.addGroupIdentityLink(ex.getExecuteId(), ex.getType());
				}
			}
		}
	}
	
	
	

	@Override
	protected int getScriptType() {
		return BpmConst.StartScript;
	}

	@Override
	protected int getBeforeScriptType() {
		return BpmConst.StartBeforeScript ;
	}
	

	
	
}
