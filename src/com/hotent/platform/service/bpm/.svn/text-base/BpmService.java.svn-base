package com.hotent.platform.service.bpm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.activity.ActivityRequiredException;
import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.cfg.IdGenerator;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.cache.ActivitiDefCache;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.model.ProcessExecution;
import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.bpm.model.ProcessTaskHistory;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.engine.IScript;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.DateUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.bpm.BpmDao;
import com.hotent.platform.dao.bpm.BpmDefinitionDao;
import com.hotent.platform.dao.bpm.BpmFormRunDao;
import com.hotent.platform.dao.bpm.BpmProStatusDao;
import com.hotent.platform.dao.bpm.BpmProTransToDao;
import com.hotent.platform.dao.bpm.ExecutionDao;
import com.hotent.platform.dao.bpm.ExecutionExtDao;
import com.hotent.platform.dao.bpm.HistoryActivityDao;
import com.hotent.platform.dao.bpm.HistoryProcessInstanceDao;
import com.hotent.platform.dao.bpm.ProcessRunDao;
import com.hotent.platform.dao.bpm.TaskDao;
import com.hotent.platform.dao.bpm.TaskHistoryDao;
import com.hotent.platform.dao.bpm.TaskUserDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.NodeTranUser;
import com.hotent.platform.model.bpm.NodeUserMap;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskAmount;
import com.hotent.platform.model.bpm.TaskFork;
import com.hotent.platform.model.bpm.TaskNodeStatus;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.bpm.TaskUser;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.thread.TaskThreadService;
import com.hotent.platform.service.system.SysTemplateService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.worktime.CalendarAssignService;

/**
 * Activiti流程接口，供第三方应用程序访问
 * @author csx
 *
 */
@Service
public class BpmService implements IScript
{
	private Logger logger=LoggerFactory.getLogger(BpmService.class);
	
	@Resource
	private BpmDao bpmDao;
	@Resource
	private TaskDao taskDao;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private ExecutionDao executionDao;
	@Resource
	private TaskUserDao taskUserDao;
	@Resource
	private TaskOpinionService taskOpinionService;
	
	@Resource
	private TaskHistoryDao taskHistoryDao;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;
	
	@Resource
	private HistoryService historyService;

	@Autowired
	private RepositoryService repositoryService;
	
	
	@Resource
	ProcessEngineConfiguration processEngineConfiguration;
	
	@Resource
	BpmDefinitionDao bpmDefinitionDao;
	@Resource
	BpmNodeSetService bpmNodeSetService;
	
	@Resource
	TaskForkService taskForkService;
	
	@Resource
	BpmNodeUserService bpmNodeUserService;
	
	@Resource
	SysUserService sysUserService;
	
	@Resource
	BpmRunLogService bpmRunLogService;
	
	@Resource
	IdGenerator idGenerator;
	
	@Resource
	HistoryActivityDao historyActivityDao;
	@Resource
	HistoryProcessInstanceDao historyProcessInstanceDao;
	
	@Resource
	private SysTemplateService sysTemplateService;
	@Resource
	private ExecutionExtDao executionExtDao;
	
	
	@Resource
	private CalendarAssignService calendarAssignService;
	
	@Resource
	private ProcessRunDao processRunDao;
	@Resource
	private BpmFormRunDao bpmFormRunDao;
	@Resource
	private BpmProStatusDao bpmProStatusDao;
	@Resource
	private BpmProTransToDao bpmProTransToDao;
	@Resource
	private TaskMessageService taskMessageService;
	

	/**
	 * 根据流程定义ID启动流程
	 * 
	 * @param proessDefId
	 *            流程定义ID
	 * @param variables
	 *            流程变量
	 * @param action
	 *            外部启动流程保存动作
	 */
	public ProcessInstance startFlowById(String proessDefId, Map<String, Object> variables)
	{
		ProcessInstance instance = runtimeService.startProcessInstanceById(proessDefId, variables);
		
		return instance;
	}
	
	/**
	 * 根据流程定义ID启动流程。
	 * @param porcessDefId		流程定义ID。
	 * @param businessKey		主键。
	 * @param variables			流程变量。
	 * @return
	 */
	public ProcessInstance startFlowById(String porcessDefId,String businessKey,Map<String,Object> variables)
	{
		System.out.println("看一下流程变量"+variables+"流程定义ID:"+porcessDefId+"主键"+businessKey);
		ProcessInstance processInstance=runtimeService.startProcessInstanceById(porcessDefId, businessKey, variables);
	
		return processInstance;
	}
	/**
	 * 按流程定义Key进行启动流程。
	 * @param processDefKey		流程定义KEY
	 * @param businessKey		主键
	 * @param variables			流程变量。
	 * @return
	 */
	public ProcessInstance startFlowByKey(String processDefKey,String businessKey,Map<String,Object> variables)
	{
		ProcessInstance processInstance =runtimeService.startProcessInstanceByKey(processDefKey, businessKey, variables);
		
		return processInstance;
	}

	/**
	 * 发布流程定义
	 * @param name		流程名称
	 * @param xml		流程的xml
	 * @param action	外部保存流程的接口
	 * @throws UnsupportedEncodingException 
	 */
	public Deployment deploy(String name, String xml) throws UnsupportedEncodingException
	{
		InputStream stream = new ByteArrayInputStream(xml.getBytes("utf-8"));
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
		deploymentBuilder.name(name);
		deploymentBuilder.addInputStream("bpmn20.xml", stream);
		Deployment deploy = deploymentBuilder.deploy();
		//Deployment deploy = repositoryService.createDeployment().name(name).addInputStream("bpmn20.xml", stream).deploy();
		return deploy;
	}


	/**
	 * 获取流程定义
	 * @param processDefinitionId
	 * @return
	 */
	public ProcessDefinitionEntity getProcessDefinitionEntity(String processDefinitionId){
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processDefinitionId);
		return processDefinition;
	}
	

	/**
	 * 转移到指定的节点。<br>
	 * 指定任务ID,转移到的节点，转移成功保存的相关情况。
	 * 
	 * @param taskId	任务ID
	 * @param toNode	转移到的节点
	 * @param variables	转移到的任务变量,产生的任务为全局变量。
	 * @throws ActivityRequiredException
	 */
	public void transTo(TaskEntity task, String toNode) throws ActivityRequiredException{
		// 获取流程定义对象
		ProcessDefinitionEntity processDefinition = getProcessDefinitionEntity(task.getProcessDefinitionId());
		// 获取当前流程的活动实例
		ActivityImpl curActi = processDefinition.findActivity(task.getTaskDefinitionKey());
		//后续的节点设置
		BpmNodeSet bpmNodeSet=null;
		// 取得目标节点定义
		ActivityImpl destAct=null;
		//是否需要去除当前任务的后续跳转线
		boolean isNeedRemoveTran=false;
		//仅完成当前任务
		if(JumpRule.RULE_INVALID.equals(toNode)){
			isNeedRemoveTran=true;
		}else{
			//1.正常按定义规则跳转
			if(StringUtils.isEmpty(toNode)){
				//取得后续的节点是否
				for(PvmTransition tran:curActi.getOutgoingTransitions()){
					String destActId=tran.getDestination().getId();
					bpmNodeSet=bpmNodeSetService.getByActDefIdJoinTaskKey(task.getProcessDefinitionId(), destActId);
					if(bpmNodeSet!=null){
						destAct=(ActivityImpl)tran.getDestination();
						break;
					}
				}
			}else{
				destAct= processDefinition.findActivity(toNode);
			}

			//若目标节点为空，则代表没有进行自由跳转，同时后续的节点也不为汇总节点
			if (destAct == null){
				//完成当前任务
				taskService.complete(task.getId());
				return;
			}
			
			//检查目标任务是否为汇总节点，若为汇总节点，需要拿到当前任务的令牌，检查该令牌对应的TaskFork的已经汇总的任务个数，若目前的任务为最后一个汇总，则删除TaskFork，并且任务进行下一步的跳转，
			//若目前任务不是最后一个汇总，则需要更新汇总完成的任务个数，并且只是完成当前任务，不作跳转。	
			if(bpmNodeSet==null){
				bpmNodeSet=bpmNodeSetService.getByActDefIdJoinTaskKey(task.getProcessDefinitionId(), destAct.getId());
			}
			if(bpmNodeSet!=null){//目前该目标任务为分发汇总
				boolean rtn=handForkTask(task,destAct.getId(),processDefinition);
				if(rtn){
					isNeedRemoveTran=true;
				}
			}
		}// end of if(JumpRule.RULE_INVALID.equals(toNode))
		//解决选择路径跳转中，如果跳转的目标节点为同步网关，则流程无法正常流转的问题
		if(!isNeedRemoveTran){
			fixParallelGateway(curActi);
		}
		//清除当前节点的后续节点。
		curActi.getOutgoingTransitions().clear();
		if(!isNeedRemoveTran){
			//创建一个连接
			TransitionImpl transitionImpl = curActi.createOutgoingTransition();
			transitionImpl.setDestination(destAct);
		}
		//完成当前任务
 		taskService.complete(task.getId());
	}
	
	/**
	 * 解决同步网关的问题。
	 * @param curActi
	 */
	private void fixParallelGateway(ActivityImpl curActi){
		List<PvmTransition> transitions= curActi.getOutgoingTransitions();
		for(PvmTransition trans:transitions){
			PvmActivity targetActivity= trans.getDestination();
			List<PvmTransition> incomeTransitions= targetActivity.getIncomingTransitions();
			for(Iterator<PvmTransition> it=incomeTransitions.iterator();it.hasNext();){
				PvmTransition pt=it.next();
				if(pt.getSource().getId().equals(curActi.getId())){
					it.remove();
				}
			}
		}
	}
	
	/**
	 * 处理分发任务。
	 * @param task
	 * @param destAct
	 * @param processDefinition
	 * @return
	 */
	private boolean handForkTask(TaskEntity task,String destNodeId,ProcessDefinitionEntity processDefinition){
		String taskId=task.getId();
		boolean isNeedRemoveTran=false;
		//取当前任务的分发令牌
		String token=(String)taskService.getVariableLocal(task.getId(),TaskFork.TAKEN_VAR_NAME);
		if(StringUtil.isEmpty(token)) return isNeedRemoveTran;
		TaskFork taskFork =taskForkService.getByInstIdJoinTaskKeyForkToken(task.getProcessInstanceId(),destNodeId, token);
		
		if(taskFork==null) return isNeedRemoveTran;
		
		//完成次数小于定义分发的次数，需要移除跳转的路径，表示只是完成当前的任务，并不发生跳转。
		if(taskFork.getFininshCount()<taskFork.getForkCount()-1){
			//更新完成任务的个数
			taskFork.setFininshCount(taskFork.getFininshCount()+1);
			taskForkService.update(taskFork);
			//更新Token
			String[]tokenSplits=token.split("[_]");
			if(tokenSplits.length==2){//若为最外层的汇总，格式如T_1,则需要删除该令牌
				taskService.setVariableLocal(task.getId(), TaskFork.TAKEN_VAR_NAME, null);
			}
			isNeedRemoveTran=true;
		}else{
			String executionId=task.getExecutionId();
			taskForkService.delById(taskFork.getTaskForkId());
			//更新Token
			String[]tokenSplits=token.split("[_]");
			if(tokenSplits.length==2){//若为最外层的汇总，格式如T_1,则需要删除该令牌
				taskService.setVariableLocal(task.getId(), TaskFork.TAKEN_VAR_NAME, null);
				
				String instanceId=task.getProcessInstanceId();
				ExecutionEntity ent=executionExtDao.getById(executionId);
				ActivityImpl curAct = processDefinition.findActivity(ent.getActivityId());
				ExecutionEntity processInstance=executionExtDao.getById(instanceId);
				processInstance.setActivity(curAct);
				
				executionExtDao.update(processInstance);
											
				//将任务的excution的id更新为流程实例ID
				taskDao.updTaskExecution(taskId);
				
				//删除token变量。
				executionDao.delTokenVarByTaskId(taskId,TaskFork.TAKEN_VAR_NAME);
				
				executionDao.delVarsByExecutionId(executionId);
				//删除execution。
				executionDao.delExecutionById(executionId);
				
				executionDao.delSubVariableByProcInstId(new Long( instanceId));
				executionDao.delSubExecutionByProcInstId(new Long( instanceId));
				
			}else if(tokenSplits.length>=3){//更新token，转换如：T_1_1转成T_1
				String newToken=token.substring(0, token.lastIndexOf("_"+tokenSplits[tokenSplits.length-1]));
				taskService.setVariableLocal(task.getId(), TaskFork.TAKEN_VAR_NAME, newToken);
			}
		}
			
	
		return isNeedRemoveTran;
	}
	
	/**
	 * 产生沟通意见流程任务。
	 * 
	 * @param actDefId
	 * @param actInstanceId
	 * @param ent
	 * @param users
	 */
	public Map<Long, Long> genCommunicationTask(TaskEntity ent, String[] users,
			SysUser sysUser) {
		if (BeanUtils.isEmpty(users)) return null;
		Map<Long, Long> map = new HashMap<Long, Long>();
		String parentId = ent.getId();
		for (String userId : users) {
			if (userId.equals(sysUser.getUserId().toString()))
				continue;
			String taskId = String.valueOf(UniqueIdUtil.genId());
			TaskEntity task = (TaskEntity) taskService.newTask(taskId);
			task.setAssignee(userId);
			task.setOwner(userId);
			task.setCreateTime(new Date());
			task.setName(ent.getName());

			task.setParentTaskId(parentId);
			task.setTaskDefinitionKey(ent.getTaskDefinitionKey());
			task.setProcessInstanceId(ent.getProcessInstanceId());
			task.setDescription(TaskOpinion.STATUS_COMMUNICATION.toString());
			task.setProcessDefinitionId(ent.getProcessDefinitionId());
			taskService.saveTask(task);
			map.put(Long.valueOf(userId), Long.valueOf(taskId));
		}
		return map;
	}
	
	/**
	 * 产生流程流转任务。
	 * 
	 * @param actDefId
	 * @param actInstanceId
	 * @param ent
	 * @param users
	 * @throws Exception 
	 */
	public Map<Long, Long> genTransToTask(TaskEntity ent, String[] users) throws Exception {
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
	 * 仅完成任务，不作跳转处理。
	 * <pre>
	 * 1.获取流程活动实例。
	 * 2.备份原来的跳转情况。
	 * 3.清空流程跳转。
	 * 4.完成当前任务。
	 * 5.将备份的跳转再添加回来。
	 * </pre>
	 * @param taskId
	 * @param variables
	 */
	public  void onlyCompleteTask(String taskId){
		TaskEntity task = getTask(taskId);
		// 获取流程定义对象
		ProcessDefinitionEntity processDefinition = getProcessDefinitionEntity(task.getProcessDefinitionId());
		// 获取当前流程的活动实例
		ActivityImpl curActi = processDefinition.findActivity(task.getTaskDefinitionKey());
		//记录原来的跳转线
		List<PvmTransition> backTransList = new ArrayList<PvmTransition>();
		backTransList.addAll(curActi.getOutgoingTransitions());

		curActi.getOutgoingTransitions().clear();
		//完成当前任务
		taskService.complete(task.getId());

		
	}
	
	/**
	 * 通过Deployment获取流程定义
	 * @param deployId
	 * @return
	 */
	public ProcessDefinitionEntity getProcessDefinitionByDeployId(String deployId){
		ProcessDefinition proDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();
		if(proDefinition==null) return null;
		return getProcessDefinitionByDefId(proDefinition.getId());
	}
	/**
	 * 通过流程定义Id获取流程定义实体
	 * @param defId act流程定义ID
	 * @return
	 */
	public ProcessDefinitionEntity getProcessDefinitionByDefId(String actDefId){
		ProcessDefinitionEntity ent=(ProcessDefinitionEntity)((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(actDefId);
		return ent;
	}
	
	/**
	 * 通过任务ID获取该流程定义
	 * @param taskId
	 * @return
	 */
	public ProcessDefinitionEntity getProcessDefinitionByTaskId(String taskId)
	{
		TaskEntity taskEntity=(TaskEntity)taskService.createTaskQuery().taskId(taskId).singleResult();
		return getProcessDefinitionByDefId(taskEntity.getProcessDefinitionId());
	}
	
	/**
	 * 根据流程key查询流程定义列表。
	 * @param key
	 * @return
	 */
	public List<ProcessDefinition> getProcessDefinitionByKey(String key){
		List<ProcessDefinition> list= repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).list();
		return list;
	}
	
	/**
	 * 按流程实例ID取得流程定义
	 * @param processInstanceId
	 * @return
	 */
	public ProcessDefinitionEntity getProcessDefinitionByProcessInanceId(String processInstanceId)
	{
		String processDefinitionId=null;
		ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if(processInstance==null){
		    HistoricProcessInstance hisProInstance=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		    processDefinitionId=hisProInstance.getProcessDefinitionId();
		}else{
			processDefinitionId=processInstance.getProcessDefinitionId();
		}
		return getProcessDefinitionByDefId(processDefinitionId);
	}
	
	
	/**
	 * 取得所有的活动任务列表
	 * @param taskId 
	 * @return
	 */
	public List<String> getActiveTasks(String taskId)
	{
		List<String> acts=new ArrayList<String>();
		TaskEntity taskEntity=(TaskEntity)taskService.createTaskQuery().taskId(taskId).singleResult();
		
		List<ProcessTask> tasks= taskDao.getByInstanceId(taskEntity.getProcessInstanceId());
		
		for(ProcessTask task:tasks)
		{
			acts.add(task.getName());
		}

		return acts;
	}
	/**
	 * 取得当前任务节点上的所有跳转任务
	 * @param taskId
	 * @return 返回的Map格式为 <taskDefinitionKey,taskName>
	 */
	public Map<String,String> getOutNodesByTaskId(String taskId){
		Map<String,String> map=new HashMap<String,String>();
		Task task=getTask(taskId);
		ProcessDefinitionEntity ent = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(task.getProcessDefinitionId());
		// 获取当前流程的活动实例
		ActivityImpl curActi = ent.findActivity(task.getTaskDefinitionKey());
		
		List<PvmTransition> outs= curActi.getOutgoingTransitions();
		for(PvmTransition tran:outs){
			ActivityImpl destNode=(ActivityImpl)tran.getDestination();
			map.put(destNode.getId(), (String)destNode.getProperty("name"));
		}
		return map;
	}
	
	/**
	 * 按任务获取其所有所有的运行节点ID
	 * @param taskId
	 * @return
	 */
	public List<String> getActiveActIdsByTaskId(String taskId)
	{
		TaskEntity taskEntity=(TaskEntity)taskService.createTaskQuery().taskId(taskId).singleResult();
		return getActiveActIdsByProcessInstanceId(taskEntity.getProcessInstanceId());
	}
	/**
	 * 按流程实例获取其所有的运行节点ID
	 * @param processInstanceId
	 * @return
	 */
	public List<String> getActiveActIdsByProcessInstanceId(String processInstanceId)
	{
		List<String> acts=new ArrayList<String>();
		List<ProcessTask> taskList=getTasks(processInstanceId);
		
		for(ProcessTask entity:taskList)
		{
			acts.add(entity.getTaskDefinitionKey());
		}
		
		return acts;
	}

	/**
	 * 通过deployId获取定义XML
	 * @param deployId
	 * @return
	 */
	public String getDefXmlByDeployId(String deployId){
		String actDefId = bpmDao.getActDefIdByDeployId(deployId);
		String xml = bpmDao.getDefXmlByDeployId(deployId);
	//	xml = handlerLocale(actDefId, xml);
		return xml;

	}
	
	/**
	 * 将defXml中的name按当前语言环境替换为对应资源
	 * @param actDefId
	 * @param defXml
	 * @return
	 */
//	private String handlerLocale(String actDefId,String defXml){
//		Document doc = Dom4jUtil.loadXml(defXml);
//		List<Node> list = doc.selectNodes("/definitions//*[@name]");
//		
//		for(Node node : list){
//			Element element = (Element)node;
//			String id = element.attributeValue("id");
//			String name = element.attributeValue("name");
//			if(StringUtil.isEmpty(id)||StringUtil.isEmpty(name))continue;
//			BpmFormLanguage bpmFormLanguage =bpmFormLanguageService.getByIdAndNodeIdAndLan(actDefId, id,BpmFormLanguage.BPM_DEFINITION_TYPE);
//			if(BeanUtils.isEmpty(bpmFormLanguage))continue;
//			String newName = bpmFormLanguage.getResvalue();
//			Attribute nameAttr = element.attribute("name");
//			nameAttr.setText(newName);
//		}
//		return doc.asXML();
//	}
	
//	/**
//	 * 在线流程设计获取国际化资源
//	 * @param actDefId
//	 * @param defXml
//	 * @return
//	 */
//	public String  getLocaleDefXml(String actDefId,String defXml) {
//		return handlLocale(actDefId, defXml);
//		
//	}
	
//	/**
//	 * 处理在线流程设计的国际化资源
//	 * @param actDefId
//	 * @param defXml
//	 * @return
//	 */
//	private String handlLocale(String actDefId, String defXml) {
//		Document doc = Dom4jUtil.loadXml(defXml);
//		List<Node> list = doc.selectNodes("//label");
//		for(Node node : list){
//			Element element = (Element)node;
//			String text = element.getText();
//			if(StringUtil.isEmpty(text))continue;
//			Element parentEle = element.getParent();
//			String nodeId = Dom4jUtil.getString(parentEle, "id");
//			BpmFormLanguage bpmFormLanguage =bpmFormLanguageService.getByIdAndNodeIdAndLan(actDefId, nodeId,BpmFormLanguage.BPM_DEFINITION_TYPE);
//			if(BeanUtils.isEmpty(bpmFormLanguage))continue;
//			element.setText(bpmFormLanguage.getResvalue());
//		}
//		return Dom4jUtil.docToPrettyString(doc);
//	}

	/**
	 * 按流程义获取流程定义XML
	 * @param processDefinitionId
	 * @return
	 */
	public String getDefXmlByProcessDefinitionId(String processDefinitionId)
	{
		ProcessDefinitionEntity entity=getProcessDefinitionByDefId(processDefinitionId);
		if(entity==null){
			return null;
		}
	    String defXml=	getDefXmlByDeployId(entity.getDeploymentId());
		return defXml;
	}
	/**
	 * 通过任务Id获取流程定义XML
	 * @param processDefinitionId
	 * @return
	 */
	public String getDefXmlByProcessTaskId(String taskId){
		ProcessDefinitionEntity entity=getProcessDefinitionByTaskId(taskId);
		if(entity==null){
			return null;
		}
		String defXml = getDefXmlByDeployId(entity.getDeploymentId());
		return defXml;
	}
	
	public String getDefXmlByProcessProcessInanceId(String processInstanceId){
		ProcessDefinitionEntity entity=getProcessDefinitionByProcessInanceId(processInstanceId);
		if(entity==null){
			return null;
		}
		String defXml = getDefXmlByDeployId(entity.getDeploymentId());
		return defXml;
	}

	/**
	 * 通过deployId及xml更新至数据库
	 * @param deployId
	 * @param defXml
	 */
	public void wirteDefXml(final String deployId, String defXml)
	{
		bpmDao.wirteDefXml(deployId, defXml);
		ProcessDefinitionEntity ent=getProcessDefinitionByDeployId(deployId);
		
	
		//clean the cache
		//TODO 清除缓存
//		((ProcessEngineConfigurationImpl)processEngineConfiguration).getDeploymentCache().clearProcessDefinitionEntity();
	}
	
	/**
	 * 获取所有的活动执行节点。
	 * @param actDefId activiti的流程定义Id
	 * @return
	 */
	public List<ActivityImpl> getActivityNodes(String actDefId)
	{
		ProcessDefinitionEntity ent=(ProcessDefinitionEntity)((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(actDefId);
		return ent.getActivities();
	}
	
	/**
	 * 根据流程定义ID和节点Id判断当前节点是否是会签节点。
	 * @param actDefId	流程定义ID。
	 * @param nodeId	节点id。
	 * @return
	 */
	public boolean isSignTask(String actDefId,String nodeId){
		ProcessDefinitionEntity ent=(ProcessDefinitionEntity)((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(actDefId);
		List<ActivityImpl> list=ent.getActivities();
		for(ActivityImpl actImpl:list){
			if(!actImpl.getId().equals(nodeId)) continue;
			String multiInstance=(String)actImpl.getProperty("multiInstance");
			if(multiInstance!=null){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 返回任务实体
	 * @param taskId
	 * @return
	 */
	public TaskEntity getTask(String taskId)
	{
		TaskEntity taskEntity=(TaskEntity)taskService.createTaskQuery().taskId(taskId).singleResult();
		return taskEntity;
	}
	
	
	
	/**
	 * 根据任务id获取可以跳转的任务节点。
	 * <pre>
	 * 	数据结构解释。
	 * 	该方法返回的数据结构为：
	 * 	Map&lt;String, Map&lt;String, String>>
	 * 	键可能的值为4个：任务节点,网关节点，结束节点,自动任务
	 *  对应的值为一个MAP:
	 *  键为任务id，值为任务名称。
	 * </pre>
	 * @param taskId
	 * @return
	 */
	public Map<String, Map<String, String>>  getJumpNodes(String taskId){
		//取到当前正在活动的节点
		List<String> actIds= getActiveActIdsByTaskId(taskId);
		TaskEntity taskEntity=(TaskEntity)taskService.createTaskQuery().taskId(taskId).singleResult();
		String defXml= this.getDefXmlByProcessDefinitionId(taskEntity.getProcessDefinitionId());
		Map<String, Map<String, String>>  map=BpmUtil.getTranstoActivitys(defXml, actIds,false);
		return map;
	}
	
	/**
	 * 获取某个流程节义中除去nodeId之外的所有节点
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public Map<String,String> getTaskNodes(String actDefId,String nodeId){
		//取得流程的任务节点
		Map<String,String> nodeMaps=getExecuteNodesMap(actDefId,true);
		//移除当前的活动节点。
		if(nodeMaps.containsKey(nodeId)){
			nodeMaps.remove(nodeId);
		}
		return nodeMaps;
	}
	
	/**
	 * 获取某个任务活动
	 * @param actImpl
	 * @return
	 */
	protected Map<String,String> getExecuteNodes(ActivityImpl actImpl)
	{
		Map<String,String> nodeMap=new HashMap<String,String>();
		List<ActivityImpl> acts=actImpl.getActivities();
		if(acts.size()==0) return nodeMap;
		for(ActivityImpl act:acts)
		{
			String nodeType=(String)act.getProperties().get("type");
			//为任务节点
			if(nodeType.indexOf("Task")!=-1)
			{
				String name=(String)act.getProperties().get("name");
				nodeMap.put(act.getId(),name);
			}
			else if("subProcess".equals(nodeType))
			{
				nodeMap.putAll(getExecuteNodes(act));
			}
		}
		return nodeMap;
	}
	
	
	/**
	 * 获取某个定义所有的任务节点，包括子流程下的
	 * @param actDefId
	 * @return
	 */
	public List<String> getExecuteNodes(String actDefId)
	{
		List<String> values=new ArrayList<String>();
		Map<String,String> nodeMap=getExecuteNodesMap(actDefId,true);

		values.addAll(nodeMap.values());
		
		Iterator<String> valuesIt=nodeMap.values().iterator();
		while(valuesIt.hasNext())
		{
			String value=valuesIt.next();
			if(StringUtils.isNotEmpty(value)){
				values.add(value);
			}
		}

		return values;
	}
	
	/**
	 * 返回流程任务节点的映射<activitiId,activityName>
	 * @param actDefId ACT流程定义ID
	 * @return
	 */
	public Map<String,String> getExecuteNodesMap(String actDefId,boolean includeSubProcess)
	{
		Map<String,String> nodeMap=new HashMap<String,String>();
		
		List<ActivityImpl> acts=getActivityNodes(actDefId);
		for(ActivityImpl actImpl:acts){
			String nodeType=(String)actImpl.getProperties().get("type");
			//为任务节点
			if(nodeType.indexOf("Task")!=-1){
				String name=(String)actImpl.getProperties().get("name");
				nodeMap.put(actImpl.getId(), name);
			}
			else if(includeSubProcess && "subProcess".equals(nodeType)){
				nodeMap.putAll(getExecuteNodes(actImpl));
			}
		}
		return nodeMap;
	}
	
	/**
	 * 获取所有的任务列表
	 * @param queryFilter
	 * @return
	 */
	public List<TaskEntity> getTasks(QueryFilter queryFilter)
	{
		return taskDao.getAll(queryFilter);
	}
	
	/**
	 * 获取我的任务列表
	 * @param queryFilter
	 * @return
	 */
	public List<TaskEntity> getMyTasks(QueryFilter queryFilter)
	{
		return taskDao.getMyTasks(ContextUtil.getCurrentUserId(),queryFilter);
	}
	
	
	
	
	/**
	 * 获取我的手机任务列表
	 * @param filter
	 * @return
	 */
	public List<TaskEntity> getMyMobileTasks(QueryFilter filter) {
		return taskDao.getMyMobileTasks(filter);
	}
		
	/**
	 * 判断流程是否结束
	 * @param processInstanceId
	 * @return
	 */
	public boolean isEndProcess(String processInstanceId)
	{
		HistoricProcessInstance his=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if(his!=null && his.getEndTime()!=null){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断某个任务是否为会签任务(multiInstance task)
	 * @param taskEntity
	 * @return
	 */
	public boolean isSignTask(TaskEntity taskEntity)
	{
		RepositoryServiceImpl impl = (RepositoryServiceImpl) repositoryService;
		// 获取流程定义对象
		ProcessDefinitionEntity ent = (ProcessDefinitionEntity) impl.getDeployedProcessDefinition(taskEntity.getProcessDefinitionId());
		// 获取当前流程的活动实例
		ActivityImpl taskAct = ent.findActivity(taskEntity.getTaskDefinitionKey());
		
		String multiInstance=(String)taskAct.getProperty("multiInstance");
		
		if(multiInstance!=null) return true;
		
		return false;
	}
	
	/**
	 * 查看某个流程实例的所有历史
	 * @param processInstanceId
	 * @return
	 */
	public List<HistoricTaskInstance> getHistoryTasks(String processInstanceId)
	{
		List<HistoricTaskInstance> list= historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
		return list;
	}
	/**
	 * 返回单独一条的流程历史
	 * @param taskId
	 * @return
	 */
	public HistoricTaskInstanceEntity getHistoricTaskInstanceEntity(String taskId)
	{
		return (HistoricTaskInstanceEntity)historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
	}
	
	/**
	 * 把某个任务授权给某个用户
	 * @param taskId
	 * @param userId
	 */
	public void assignTask(String taskId,String userId)
	{
		taskService.setOwner(taskId, userId);
		taskService.setAssignee(taskId, userId);
	}
	/**
	 * 设置某个任务的到期时间
	 * @param taskId
	 * @param dueDate
	 */
	public void setDueDate(String taskId,Date dueDate)
	{
		taskDao.setDueDate(taskId, dueDate);
	}
	
	
	/**
	 * 根据executionId获取ExecutionEntity，并赋予流程变量。
	 * @param executionId
	 * @return
	 */
	public ExecutionEntity getExecution(String executionId)
	{
		Execution execution=runtimeService.createExecutionQuery().executionId(executionId).singleResult();
		return (ExecutionEntity)execution;
	}
	
	
	/**
	 * 按任务ID获取执行实体
	 * @param taskId 任务Id
	 * @return
	 */
	public ExecutionEntity getExecutionByTaskId(String taskId)
	{
		TaskEntity taskEntity=getTask(taskId);
		if(taskEntity.getExecutionId()==null)return null;
		return getExecution(taskEntity.getExecutionId());
	}
	
	public Map<String,Object> getVarsByTaskId(String taskId)
	{
		return taskService.getVariables(taskId);
	}
	
	public void setExecutionVariable(String executionId,String variableName,Object varVal)
	{
		runtimeService.setVariable(executionId, variableName, varVal);
	}
	
	public void setTaskVariable(String taskId,String variableName,Object varVal)
	{
		taskService.setVariable(taskId, variableName, varVal);
	}
	
	public ProcessTask newTask(String orgTaskId,String assignee){
		return newTask(orgTaskId,assignee,null,null);
	}
	
	/**
	 * 按任务Id，复制另一会签任务出来.
	 * <pre>
	 * 1.生成流程执行实例。
	 * 2.生成任务。
	 * 3.增加任务历史。
	 * </pre>
	 * @param orgTaskId
	 * @param assignee
	 * @param newNodeId 另一个节点ID
	 * @return
	 */
	public ProcessTask newTask(String orgTaskId,String assignee,String newNodeId,String newNodeName)
	{
		
		String newExecutionId=idGenerator.getNextId();
		String newTaskId=idGenerator.getNextId();
		
		TaskEntity taskEntity=getTask(orgTaskId);
		ExecutionEntity executionEntity=null;
		executionEntity=getExecution(taskEntity.getExecutionId());
		
		ProcessExecution newExecution=new ProcessExecution(executionEntity);
		newExecution.setId(newExecutionId);
		
		ProcessTask newTask=new ProcessTask();
		BeanUtils.copyNotNullProperties(newTask, taskEntity);
		newTask.setId(newTaskId);
		newTask.setExecutionId(newExecutionId);
		newTask.setCreateTime(new Date());
		
		newTask.setAssignee(assignee);
		newTask.setOwner(assignee);
		
		ProcessTaskHistory newTaskHistory=new ProcessTaskHistory(taskEntity);
		newTaskHistory.setAssignee(assignee);
		newTaskHistory.setStartTime(new Date());
		newTaskHistory.setId(newTaskId);
		newTaskHistory.setOwner(assignee);
		
		if(newNodeId!=null){
			newExecution.setActivityId(newNodeId);
			newTask.setTaskDefinitionKey(newNodeId);
			newTask.setName(newNodeName);
			newTaskHistory.setTaskDefinitionKey(newNodeId);
			newTaskHistory.setName(newNodeName);
		}
		
		executionDao.add(newExecution);
		taskDao.insertTask(newTask);
		taskHistoryDao.add(newTaskHistory);
		
		//更新节点的状态
		
		return newTask;
	}
	
	
	/**
	 * 从一个任务创建相关的的任务。
	 * <pre>
	 * 1.设置当前任务的任务执行人。
	 * 2.其他的用户创建新的任务，excution和历史数据。
	 * 		1.添加任务到队列。
	 * 		2.添加分发令牌。
	 * 			1.如还没有令牌：T_1,T_2,T_3 。
	 * 			2.如果任务存在则令牌如下。T_1_1,T_1_2,T_1_3
	 * 		3.添加任务意见。
	 * </pre>
	 * @param taskEntity
	 * @param uIds 用户Id或组织Id或角色Id或岗位ID
	 * @param userType 值来自TaskUser里的type值
	 */
	public void newForkTasks(TaskEntity taskEntity,Set<TaskExecutor> uIds){
		//获取当前任务的分发的令牌, value as T_1 or T_1_2
		String token=(String)taskEntity.getVariableLocal(TaskFork.TAKEN_VAR_NAME);
		if(token==null) token=TaskFork.TAKEN_PRE;
		Iterator<TaskExecutor> uIt=uIds.iterator();
		int i=0;
		while(uIt.hasNext()){
			if(i++==0){//原activiti产生的流程仅需要进行人员授权
				assignTask(taskEntity,uIt.next());
				//加上分发令牌变量
				taskEntity.setVariableLocal(TaskFork.TAKEN_VAR_NAME, token+ "_"+ i);
				//同时改变其Execution，以防止当该线的任务进行汇总时，
				//其exectionId及proc_inst_id_相同时，删除该任务引起关联级别的错误
				changeTaskExecution(taskEntity);
			}else{//扩展产生的流程任务，另需要加上审批意见的记录
				ProcessTask processTask=newTask(taskEntity,uIt.next());
				
				TaskEntity newTask=getTask(processTask.getId());
				//加上新的任务至新任务列队，以方便后续的任务回退处理
				TaskThreadService.addTask(newTask);
				
				//加上分发令牌变量
				taskService.setVariableLocal(processTask.getId(),TaskFork.TAKEN_VAR_NAME, token+ "_"+ i);
				
				//添加任务的意见。
				TaskOpinion taskOpinion=new TaskOpinion(processTask);
				taskOpinion.setOpinionId(UniqueIdUtil.genId());
				taskOpinion.setSuperExecution(processRunService.getSuperActInstId(processTask.getProcessInstanceId()));
				taskOpinion.setTaskToken(token);
				
				//配置执行人情况
				String userId=processTask.getOwner();
				if (userId!=null && Long.valueOf(userId)>0) {
					SysUser sysUser=sysUserService.getById(Long.valueOf(userId));
					taskOpinion.setExeUserId(Long.valueOf(userId));
					taskOpinion.setExeFullname(sysUser.getFullname());
				}
				
				
				//處理審批意見是否添加父流程Id，如果是外部子流程則添加，否則就不添加為空
				Object isExtCall = runtimeService.getVariable(taskEntity.getExecutionId(), BpmConst.IS_EXTERNAL_CALL);
				// 外部子流程做处理
				if (isExtCall!=null) {
					// 处理如果是外部子流程，运行到外部子流程节点时，审批意见添加父流程的ID
					ProcessExecution processExecution = executionDao.getById(taskEntity.getExecutionId());
					// 子流程获取主流程的实例ID
					if (StringUtil.isEmpty(processExecution.getSuperExecutionId())) {
						taskOpinion.setSuperExecution(processExecution.getParentId());
					} else {
						ProcessExecution processExecution1 = executionDao.getById(processExecution.getSuperExecutionId());
						taskOpinion.setSuperExecution(processExecution1.getParentId());
					}
				}
				taskOpinionService.add(taskOpinion);
			}
		}
	}
	

	
	public void newForkTasks(TaskEntity taskEntity,List<TaskExecutor> uIdList){
		Set<TaskExecutor> uIdSet=new HashSet<TaskExecutor>();
		uIdSet.addAll(uIdList);
		newForkTasks(taskEntity,uIdSet);
	}
	
	/**
	 * 授权任务
	 * @param taskEntity 任务实体
	 * @param uId  用户id或组织id或角色id
	 * @param userType 用户类型
	 */
	public void assignTask(TaskEntity taskEntity,TaskExecutor taskExecutor){
		if(TaskExecutor.USER_TYPE_USER.equals(taskExecutor.getType())){
			taskEntity.setAssignee(taskExecutor.getExecuteId());
			taskEntity.setOwner(taskExecutor.getExecuteId());
		}else{
			taskEntity.addGroupIdentityLink(taskExecutor.getExecuteId(), taskExecutor.getType());
		}
	}
	
	/**
	 * 改变当前任务对应的exectution
	 * @param taskEntity
	 */
	protected void changeTaskExecution(TaskEntity taskEntity){
		String newExecutionId=idGenerator.getNextId();
		ProcessExecution newExecution=new ProcessExecution(taskEntity.getExecution());
		
		newExecution.setId(newExecutionId);
		
		executionDao.add(newExecution);
		//ExecutionEntity execution=(ExecutionEntity)runtimeService.createExecutionQuery().executionId(newExecutionId).singleResult();
		//taskEntity.setExecution(execution);
		taskEntity.setExecutionId(newExecutionId);
	}
	
	/**
	 * 新建任务。
	 * <pre>
	 * 1.添加task。
	 * 2.如果用户类型为组时，添加为候选组。
	 * 3.添加ProcessExecution。
	 * 4.添加任务历史。ProcessTaskHistory
	 * 
	 * </pre>
	 * @param taskEntity
	 * @param uId 用户Id或组织Id或角色Id或岗位ID
	 * @param userType
	 * @return
	 */
	protected ProcessTask newTask(TaskEntity taskEntity,TaskExecutor taskExecutor){
		
		String newExecutionId=idGenerator.getNextId();
		String newTaskId=idGenerator.getNextId();
		
		ProcessExecution newExecution=new ProcessExecution(taskEntity.getExecution());
		newExecution.setId(newExecutionId);
		//设置流程将exctuion 的父id设置为流程实例ID。
		//newExecution.setParentId(taskEntity.getProcessInstanceId());
		
		ProcessTask newTask=new ProcessTask();
		BeanUtils.copyNotNullProperties(newTask, taskEntity); 
		newTask.setId(newTaskId);
		newTask.setExecutionId(newExecutionId);
		newTask.setCreateTime(new Date());
		ProcessTaskHistory newTaskHistory=new ProcessTaskHistory(taskEntity);
		/**
		 * 任务对应的用户
		 */
		TaskUser taskUser=null;
		
		String executorId=taskExecutor.getExecuteId();
		//若为用户类型时
		if(TaskExecutor.USER_TYPE_USER.equals(taskExecutor.getType())){
			newTask.setAssignee(executorId);
			newTask.setOwner(executorId);
			newTaskHistory.setAssignee(executorId);
			newTaskHistory.setOwner(executorId);
		}else{
			taskUser=new TaskUser();
			taskUser.setId(idGenerator.getNextId());
			taskUser.setGroupId(executorId);
			taskUser.setType(taskExecutor.getType());
			taskUser.setReversion(1);
			taskUser.setTaskId(newTaskId);
			taskUser.setUserId(BpmConst.EMPTY_USER);
		}

		newTaskHistory.setStartTime(new Date());
		newTaskHistory.setId(newTaskId);

		executionDao.add(newExecution);
		taskDao.insertTask(newTask);
		taskHistoryDao.add(newTaskHistory);
		if(taskUser!=null){
			taskUserDao.add(taskUser);
		}
		
		return newTask;
	}
	


	/**
	 * 获取某个流程实例的所有任务实例
	 * @param processInstanctId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ProcessTask> getTasks(String processInstanceId)
	{
		
		List taskList=taskDao.getByInstanceId(processInstanceId) ;
		return taskList;
	}
	
	/**
	 * 获取任务的候选用户
	 * @param taskId
	 * @return
	 */
	public List<TaskUser> getCandidateUsers(String taskId)
	{
		return taskUserDao.getByTaskId(taskId);
	}
	
	

	
	/**
	 * 设置分支节点条件，同时保存流程定义的xml和流程设计的xml文件。
	 * @param defId					流程定义ID
	 * @param forkNode				网关节点
	 * @param map					条件映射。
	 * @param canChoicePathNodeId	可以选择路径的节点，一般为之前网关节点之前的任务节点。
	 * @throws IOException
	 */
	public void saveCondition(long defId,String forkNode,Map<String,String> map,String canChoicePathNodeId) throws IOException{
		BpmDefinition bpmDefinition= bpmDefinitionDao.getById(defId);
		if(StringUtil.isNotEmpty(canChoicePathNodeId)){
			bpmDefinition.getCanChoicePathNodeMap().put(forkNode, canChoicePathNodeId);
			bpmDefinition.updateCanChoicePath();
		}
		else{
			Object o = bpmDefinition.getCanChoicePathNodeMap().get(forkNode);
			if(o!=null)
				bpmDefinition.getCanChoicePathNodeMap().remove(forkNode);
			bpmDefinition.updateCanChoicePath();
		}
		String deployId=bpmDefinition.getActDeployId().toString();
		String actDefId=bpmDefinition.getActDefId();
		String defXml=bpmDao.getDefXmlByDeployId(deployId);
		String graphXml=bpmDefinition.getDefXml();
		defXml= BpmUtil.setCondition(forkNode, map, defXml);
		graphXml=BpmUtil.setGraphXml(forkNode, map, graphXml);
		bpmDefinition.setDefXml(graphXml);
		bpmDao.wirteDefXml(deployId, defXml);
		
		bpmDefinitionDao.update(bpmDefinition);
		
		//clean the cache
		//TODO 清除线程变量
		//DeploymentCache.clearProcessDefinitionEntity();
		//DeploymentCache.removeGlobalDefCache(actDefId);
		
		ActivitiDefCache.clearByDefId(actDefId);
		
	
		//查询流程定义。
		((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(actDefId);
	}
	


	
	
	/**
	 * 根据userId获取任务列表,流程代理里用到
	 * @param touserid
	 * @return
	 */
	public List<TaskEntity> getTaskByUserId(Long agentuserid,QueryFilter filter) {
		return taskDao.getMyTasks(agentuserid, filter);
	}
	
	
	
	
	/**
	 * 获取我的任务列表
	 * @param params
	 * @return
	 */
	public String getMyEvents(Map map)
	{	
		List<TaskEntity> list = taskDao.getMyEvents(map);
		
		String mode = (String)map.get("mode");
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		
		for(int idx=0;idx<list.size();idx++){
			
			Object obj = list.get(idx);
			ProcessTask task = (ProcessTask)obj;
			
			sb.append("{\"id\":\"").append(task.getId()).append("\",");

			Date startTime=task.getCreateTime();
			if(startTime==null){
				Calendar curCal=Calendar.getInstance();
				startTime=curCal.getTime();
			}

			Date endTime=task.getDueDate();
			if(endTime==null&&"month".equals(mode)){
				endTime=startTime;
			}

			String sTime =DateUtil.formatEnDate(startTime);
			String eTime =endTime==null?"":DateUtil.formatEnDate(endTime);
			
			String eTime0 = "";
			if("month".equals(mode)&&sTime.substring(0, 10).equals(eTime.substring(0, 10))){
				String[] dateArr = sTime.substring(0, 10).split("/");
				eTime0 = DateUtil.addOneDay(dateArr[2]+"-"+dateArr[0]+"-"+dateArr[1])
						+" 00:00:00 AM";
			}

			if(!"month".equals(mode)){
				String[] dateArr = sTime.substring(0, 10).split("/");
				eTime0 = DateUtil.addOneHour(dateArr[2]+"-"+dateArr[0]+"-"+dateArr[1]
					+sTime.substring(10,sTime.length()));
			}
			
			sb.append("\"type\":\"").append("2").append("\",");
			sb.append("\"startTime\":\"");
			
			if("month".equals(mode)){
				sb.append(sTime.substring(0, 10)+" 00:00:00 AM").append("\",");
			}else{
				sb.append(sTime).append("\",");
			}
			
			if(!eTime0.equals("")){
				sb.append("\"endTime\":\"").append(eTime0).append("\",");
			}else{
				sb.append("\"endTime\":\"").append(eTime).append("\",");
			}
			
			sb.append("\"title\":\"").append(task.getSubject()).append("\",");
			sb.append("\"description\":\"").append(task.getProcessName()).append("\",");
			sb.append("\"status\":\"").append("0").append("\"");
			sb.append("},");
		}
		
		if(list.size()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	/**
	 * 判断是否允许选择路径。
	 * @param actDefId
	 * @param taskId
	 * @return
	 */
	public boolean getCanChoicePath(String actDefId,String taskId){
		if(StringUtil.isEmpty(taskId)){
			return false;
		}
		TaskEntity taskEntity=getTask(taskId);
		String taskKey = taskEntity.getTaskDefinitionKey();
		if(StringUtil.isEmpty(actDefId))
			actDefId = taskEntity.getProcessDefinitionId();
		BpmDefinition bpmDefinition = bpmDefinitionDao.getByActDefId(actDefId);		
		String canChoicePath = bpmDefinition.getCanChoicePath();
		if(StringUtil.isNotEmpty(canChoicePath))
			return canChoicePath.contains(taskKey);
		else
			return false;
	}
	
	/**
	 * 取到当前任务的下一步的节点及其映射人员
	 * @param taskId		任务ID
	 * @param preUserId		前一用户ID
	 * @return
	 */
	public List<NodeTranUser> getNodeTaskUserMap(String taskId,Long preUserId,boolean canChoicePath){
		TaskEntity taskEntity=getTask(taskId);
		//获取流程实例id
		String instanceId=taskEntity.getProcessInstanceId();
		
		String actDefId=taskEntity.getProcessDefinitionId();
		
		//从节点缓存中获取节点数据。
		FlowNode flowNode=NodeCache.getByActDefId(actDefId).get(taskEntity.getTaskDefinitionKey());
		
		ProcessRun processRun=processRunService.getByActInstanceId(new Long(instanceId));
		
		Long curUserId=null;
		Long startOrgId=null;
		//获取任务发起人和发起人组织。
		if(processRun!=null){
			curUserId=processRun.getCreatorId();
			startOrgId=processRun.getStartOrgId();
		}
		
		Map<String,Object> vars =taskService.getVariables(taskId);
		vars.put(BpmConst.EXECUTION_ID_,taskEntity.getExecutionId() );
		return getNodeUserMap(actDefId,instanceId,flowNode.getNextFlowNodes(),curUserId,preUserId,canChoicePath,vars);
	}
	
	/**
	 * 获取起始节点的下一步执行人。
	 * @param actDefId		流程定义ID
	 * @param startUserId	流程发起人id。
	 * @return
	 * @throws Exception
	 */
	public List<NodeTranUser> getStartNodeUserMap(String actDefId,Long startUserId,Map<String,Object> vars) throws Exception{
		BpmDefinition bpmDefinition= bpmDefinitionDao.getByActDefId(actDefId);
		
		boolean jumpOverFirstNode= bpmDefinition.getToFirstNode()==(short)1;
		
		FlowNode startNode=NodeCache.getStartNode(actDefId);
		
		List<FlowNode> nextNodes=startNode.getNextFlowNodes();
		
		
		if(nextNodes.size()!=1) throw new Exception("开始节点后没有连接的节点!");
		
		if(jumpOverFirstNode){
			FlowNode flowNode=nextNodes.get(0);
			if(!"userTask".equals(flowNode.getNodeType())){
				if(nextNodes.size()!=1) throw new Exception("第一个节点必须为任务节点!");
			}
			nextNodes=flowNode.getNextFlowNodes();
		}
		
			
		return getNodeUserMap(actDefId,null,nextNodes,startUserId,startUserId,false,vars);
	}
	/**
	 * 取得流程节点中的流程节点对应的执行人员列表
	 * @param processDefinitionId
	 * @param nextFlowNodes
	 * @param curUserId
	 * @param preUserId
	 * @return
	 */
	public List<NodeTranUser> getNodeUserMap(String processDefinitionId,String instanceId, List<FlowNode> nextFlowNodes,Long curUserId,Long preUserId ,boolean canChoicePath,Map<String,Object> vars ){
		List<NodeTranUser> nodeList=new ArrayList<NodeTranUser>();
		for(FlowNode flowNode:nextFlowNodes){
			Set<NodeUserMap> nodeUserMapSet=new LinkedHashSet<NodeUserMap>();
			NodeTranUser nodeTranUser=new NodeTranUser(flowNode.getNodeId(),flowNode.getNodeName(),nodeUserMapSet);
			
			//若为任务节点，即把任务节点对应的人员取出来
			if(flowNode.getNodeType().equals("userTask")){
				Set<TaskExecutor> taskExecutors=getNodeHandlerUsers(processDefinitionId,instanceId,flowNode.getNodeId(),curUserId,preUserId,vars);
				nodeUserMapSet.add(new NodeUserMap(flowNode.getNodeId(),flowNode.getNodeName(),taskExecutors,flowNode.getIsMultiInstance()));
			}else if(flowNode.getNodeType().indexOf("Gateway")!=-1){
				//如果是条件网关类型，可以让用户在界面上选择执行的分支
				if(canChoicePath&&"inclusiveGateway".equals(flowNode.getNodeType())){
					Map<String,String> nextPathMap = new HashMap<String,String>();
					List<FlowNode> gatewayPathList = flowNode.getNextFlowNodes(); 
					for(FlowNode gatewayFlowNode:gatewayPathList){
						nextPathMap.put(gatewayFlowNode.getNodeId(),gatewayFlowNode.getNodeName());
						//TODO 需要查看一下这个代码。
						String nodeType=gatewayFlowNode.getNodeType();
						if(nodeType.equals("callActivity")){
							Map<String,FlowNode> subGatewayChildNodes=gatewayFlowNode.getSubProcessNodes();
							String subFlowKey=gatewayFlowNode.getAttribute("subFlowKey");
							FlowNode startNode= NodeCache.getStartNode(subGatewayChildNodes);
							//获取子流程
							BpmDefinition bpmDefinition= bpmDefinitionDao.getByActDefKeyIsMain(subFlowKey);
							String subProcessDefinitionId=bpmDefinition.getActDefId();
							genUserMap(subProcessDefinitionId,instanceId,startNode.getNextFlowNodes(),nodeUserMapSet,curUserId,preUserId,vars);
						}
					}
					nodeTranUser.setNextPathMap(nextPathMap);
				}
				genUserMap(processDefinitionId,instanceId,flowNode.getNextFlowNodes(),nodeUserMapSet,curUserId,preUserId,vars);
			}else if(flowNode.getNodeType().equals("subProcess")&& flowNode.getSubFirstNode()!=null){//若为子流程，则查找子流程中的开始节点后的任务作为后续的跳转节点
				genUserMap(processDefinitionId,instanceId,flowNode.getSubFirstNode().getNextFlowNodes(),nodeUserMapSet,curUserId,preUserId,vars);
			}
			//若外部子流程
			else if(flowNode.getNodeType().equals("callActivity")){
				Map<String,FlowNode> subChildNodes=flowNode.getSubProcessNodes();
				String subFlowKey=flowNode.getAttribute("subFlowKey");
				FlowNode startNode= NodeCache.getStartNode(subChildNodes);
				//获取子流程
				BpmDefinition bpmDefinition= bpmDefinitionDao.getByActDefKeyIsMain(subFlowKey);
				String subProcessDefinitionId=bpmDefinition.getActDefId();
				//在变量中临时添加父流程定义ID
				vars.put(BpmConst.FLOW_PARENT_ACTDEFID, processDefinitionId);
				genUserMap(subProcessDefinitionId,instanceId,startNode.getNextFlowNodes(),nodeUserMapSet,curUserId,preUserId,vars);
			}			
			nodeList.add(nodeTranUser);
		}
		return nodeList;
	}	
	
	/**
	 * 取得流程节点中的流程节点对应的执行人员列表
	 * @param processDefinitionId
	 * @param nextFlowNodes
	 * @param nodeUserMapSet
	 * @param curUserId
	 * @param preUserId
	 */
	private void genUserMap(String processDefinitionId,String instanceId, List<FlowNode> nextFlowNodes, Set<NodeUserMap> nodeUserMapSet,Long curUserId,Long preUserId,Map<String,Object> vars){
		for(FlowNode flowNode:nextFlowNodes){			
			if(flowNode.getNodeType().indexOf("Gateway")!=-1){//网关
				genUserMap(processDefinitionId,instanceId,flowNode.getNextFlowNodes(),nodeUserMapSet,curUserId,preUserId,vars);				
			}
			else if(flowNode.getNodeType().indexOf("subProcess")!=-1){//内部子流程
				genUserMap(processDefinitionId,instanceId,flowNode.getSubFirstNode().getNextFlowNodes(),nodeUserMapSet,curUserId,preUserId,vars);
			}
			else if("userTask".equals(flowNode.getNodeType())){
				 Set<TaskExecutor> taskExecutors=getNodeHandlerUsers(processDefinitionId,instanceId,flowNode.getNodeId(),curUserId,preUserId,vars);
				 NodeUserMap nodeUserMap = new NodeUserMap(flowNode.getNodeId(),flowNode.getNodeName(),taskExecutors,flowNode.getIsMultiInstance());				
				 nodeUserMapSet.add(nodeUserMap);
			}
		}
	}
	

	
	/**
	 * 根据流程实例某个节点上的执行人员
	 * @param actInstanceId
	 * @param nodeId
	 * @param startUserId
	 * @return
	 */
	public Set<TaskExecutor> getNodeHandlerUsers(String actInstanceId,String nodeId,Map<String,Object> vars){
		Set<TaskExecutor> uSet=new HashSet<TaskExecutor>();
		ProcessRun processRun = processRunService
				.getByActInstanceId(new Long( actInstanceId));
		String actDefId = processRun.getActDefId();
		String startUserId = processRun.getCreatorId().toString();
		List<TaskExecutor> taskExecutorList = bpmNodeUserService.getExeUserIds(actDefId, actInstanceId, nodeId, startUserId, "", vars);
		
	
		if(BeanUtils.isEmpty(taskExecutorList)){
			return uSet;
		}
		uSet.addAll(taskExecutorList);
	
		return uSet;
	}
	/**
	 * 取至某个流程定义某个节点上的执行人员
	 * @param actDefId 流程定义ID
	 * @param actInstId 流程定义实例
	 * @param nodeId 节点ID
	 * @param startUserId 启动用户
	 * @return
	 */
	public Set<TaskExecutor> getNodeHandlerUsers(String actDefId,String instanceId, String nodeId,Long startUserId,Long preUserId,Map<String,Object> vars){
		Set<TaskExecutor> uSet=new LinkedHashSet<TaskExecutor>();
		List<TaskExecutor> taskExecutorList=bpmNodeUserService.getExeUserIds(actDefId, instanceId, nodeId, startUserId.toString(), preUserId.toString(), vars);
		//
		if(taskExecutorList==null){
			return uSet;
		}
		for(TaskExecutor taskExecutor:taskExecutorList){
			uSet.add(taskExecutor);
		} 
		return uSet;
	}
	
	/**
	 * 删除任务
	 * @param taskId
	 */
	public void deleteTask(String taskId){		
		TaskEntity taskEntity=getTask(taskId);
		taskService.deleteTask(taskId);
		executionDao.delById(taskEntity.getExecutionId());
	}
	
	/**
	 * 删除多个任务
	 * @param taskIds
	 */
	public void deleteTasks(String[]taskIds){
		for (String taskId : taskIds) {
			deleteTask(taskId);			
		}
	}
	
	/**
	 * 更新任务的执行人
	 * @param taskId
	 * @param userId
	 */
	public void updateTaskAssignee(String taskId,String userId){
		
		taskDao.updateTaskAssignee(taskId, userId);
		
	}
	
	/**
	 * 修改指定任务的执行人为空。
	 * @param taskId	任务ID。
	 */
	public void updateTaskAssigneeNull(String taskId){
		taskDao.updateTaskAssigneeNull(taskId);
	}
	/**
	 * 更新任务的所属人
	 * @param taskId
	 * @param userId
	 */
	public void updateTaskOwner(String taskId,String userId){
		taskDao.updateTaskOwner(taskId, userId);
	}
	
	public ProcessInstance getProcessInstance(String actInstId){
		ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(actInstId).singleResult();
		return processInstance;
	}
	
	
	
	
	
	
	/**
	 * 根据流程实例ID终结流程。 
	 * 1.更新bpm_pro_run状态为结束。
	 * 2.删除act_ru_identitylink数据。
	 * 3.删除任务表  ACT_RU_TASK。
	 * 4.更新节点实例。
	 *  update ACT_HI_ACTINST set EXECUTION_ID_ = ?,
	 * ASSIGNEE_ = ?, END_TIME_ = ?, DURATION_ = ? where ID_ = ? 
	 * 5.更新历史实例。
	 * update ACT_HI_PROCINST set PROC_DEF_ID_ = ?, START_TIME_ = ?, END_TIME_ =
	 * ?, DURATION_ = ?, END_ACT_ID_ = ?, DELETE_REASON_ = ? where ID_ = ? 
	 * 6. 删除流程变量。
	 * ACT_RU_VARIABLE 
	 * 7. 删除execution 表。
	 * ACT_RU_EXECUTION。
	 * 删除流程表单运行情况 BPM_FORM_RUN
	 * @param instanceId
	 * @throws Exception 
	 */
	public ProcessRun endProcessByInstanceId(Long instanceId, String nodeId,
			String memo) throws Exception {
		SysUser sysUser = ContextUtil.getCurrentUser();
		// 更新流程执行状态。
		ProcessRun processRun = processRunService.getByActInstanceId(instanceId);
		processRun.setStatus(ProcessRun.STATUS_MANUAL_FINISH);
		processRun.setEndTime(new Date());
		Long startUserId=processRun.getCreatorId();
		SysUser startUser=sysUserService.getById(startUserId);
		
		BpmDefinition bpmDefinition=bpmDefinitionDao.getByActDefId(processRun.getActDefId());
		long duration = processRunService.getProcessDuration(processRun);
		long lastSubmitDuration = processRunService.getProcessLastSubmitDuration(processRun);
		processRun.setDuration(duration);
		processRun.setLastSubmitDuration(lastSubmitDuration);
		
		// 更新历史
		processRunDao.updateHistory(processRun);
		// 删除流程实例
		processRunDao.delById(processRun.getRunId());

		// 删除候选人
		taskDao.delCandidateByInstanceId(instanceId);
		// 根据流程实例删除流程。
		taskDao.delByInstanceId(instanceId);
		//删除流程表单运行情况
		bpmFormRunDao.delByInstanceId(String.valueOf(instanceId));
		// 更新历史实例。
		updHistoryActInst(instanceId, nodeId, sysUser.getUserId().toString());
		// 更新流程实例
		HistoricProcessInstanceEntity processInstance = (HistoricProcessInstanceEntity) historyService
				.createHistoricProcessInstanceQuery()
				.processInstanceId(instanceId.toString()).singleResult();
		processInstance.setEndTime(new Date());
		processInstance.setDurationInMillis(System.currentTimeMillis()
				- processInstance.getStartTime().getTime());
		processInstance.setEndActivityId(nodeId);
		historyProcessInstanceDao.update(processInstance);
		// 根据流程实例删除变量
		executionDao.delVariableByProcInstId(instanceId);
		// 根据流程实例删除excution。
		executionDao.delExecutionByProcInstId(instanceId);

		bpmProStatusDao.updStatus(instanceId, nodeId,
				TaskOpinion.STATUS_ENDPROCESS);
		//删除流转状态记录
		bpmProTransToDao.delByActInstId(instanceId);
		// 更新流程审批历史。
		updateTaskOpinion(instanceId, memo, sysUser);
		String informStart=bpmDefinition.getInformStart();
		List<SysUser>receiverUserList=new ArrayList<SysUser>();
		receiverUserList.add(startUser);
		Map<String,String> msgTempMap = sysTemplateService.getTempByFun(SysTemplate.USE_TYPE_TERMINATION);
		//TODO 发送通知
		if(StringUtil.isNotEmpty(informStart)){
			taskMessageService.sendMessage(sysUser, receiverUserList, informStart, msgTempMap, processRun.getSubject(), memo, null, processRun.getRunId(),null);

		}
		
		return processRun;

	}
	
	
	
	

	/**
	 * 根据任务ID获取是否有候选人。
	 * @param taskIds
	 * @return
	 */
	public List<Map> getHasCandidateExecutor(String taskIds) {
		return taskDao.getHasCandidateExecutor(taskIds);
	}
	
	/**
	 * 设置流程内部变量。
	 * @param task		任务
	 * @param varName	变量名称
	 * @param obj		设置流程变量值。
	 */
	public void setInnerVariable(DelegateTask task,String varName,Object obj){
		Map<String, Object> map=(Map<String, Object>)task.getVariable(BpmConst.PROCESS_INNER_VARNAME);
		map.put(varName, obj);
		task.setVariable(BpmConst.PROCESS_INNER_VARNAME, map);
	}
	
	/**
	 * 获取输出变量。
	 * @param task		任务
	 * @param varName	变量名称
	 * @return
	 */
	public Object getOutVariable(DelegateTask task,String varName){
		Map<String,Object> vars=(Map<String,Object>) task.getVariable(BpmConst.PROCESS_EXT_VARNAME);
		return vars.get(varName);
	}
	
	
	
	/**
	 * 获取流程变量。
	 * @param excution
	 * @param varName
	 * @return
	 */
	public Object getOutVariable(DelegateExecution excution,String varName){
		Map<String,Object> vars=(Map<String,Object>) excution.getVariable(BpmConst.PROCESS_EXT_VARNAME);
		return vars.get(varName);
	}
	
	/**
	 * 通过线程变量传递值。
	 * @param obj
	 */
	public void setObject(Object obj){
		TaskThreadService.setObject(obj);
	}
	
	/**
	 * 通过线程变量获取值。
	 * @param obj
	 */
	public Object getObject(){
		return TaskThreadService.getObject();
	}
	
	/**
	 * 获取上下文中的ProcessCmd对象。
	 * @return
	 */
	public ProcessCmd getProcessCmd(){
		return TaskThreadService.getProcessCmd();
	}
	

	/**
	 * 获取我的任务数量
	 * 
	 * @param userId
	 * @return
	 */
	public List<TaskAmount> getMyTasksCount(Long userId) {
		return (List<TaskAmount>) taskDao.getMyTasksCount(userId);
	}
	
	
	/**
	 * 获取第一个任务。
	 * 
	 * @param instanceId
	 * @param actDefId
	 * @return
	 */
	public ProcessTask getFirstNodeTask(String instanceId) {
		Long actInstanceId = Long.parseLong(instanceId);
		List<ProcessTask> tasks = taskDao.getTaskByInstId(actInstanceId);
		if (BeanUtils.isEmpty(tasks))
			return null;
		return tasks.get(0);
	}
	
	/**
	 * 更新流程意见为结束。
	 * 
	 * @param instanceId
	 */
	private void updateTaskOpinion(Long instanceId, String memo, SysUser sysUser) {
		List<TaskOpinion> list = taskOpinionService
				.getCheckOpinionByInstId(instanceId);
		for (TaskOpinion taskOpion : list) {
			taskOpion.setCheckStatus(TaskOpinion.STATUS_ENDPROCESS);
			taskOpion.setExeUserId(sysUser.getUserId());
			taskOpion.setExeFullname(sysUser.getFullname());
			taskOpion.setEndTime(new Date());
			taskOpion.setOpinion(memo);
			Long duration = calendarAssignService.getRealWorkTime(taskOpion.getStartTime(), taskOpion.getEndTime(), sysUser.getUserId());
			taskOpion.setDurTime(duration);
			taskOpinionService.update(taskOpion);
		}
	}
	
	/**
	 * 逻辑删除流程实例。
	 * 
	 * @param instanceId
	 * @param nodeId
	 * @param memo
	 * @return
	 */
	public ProcessRun delProcessByInstanceId(Long instanceId, String memo) {
		SysUser sysUser = ContextUtil.getCurrentUser();
		List<ProcessTask> list = getTasks(instanceId.toString());
		String nodeId = "";
		if (BeanUtils.isNotEmpty(list)) {
			ProcessTask task = list.get(0);
			nodeId = task.getTaskDefinitionKey();
		}

		// 更新流程执行状态。
		ProcessRun processRun = processRunService.getByActInstanceId(instanceId);
		processRun.setStatus(ProcessRun.STATUS_DELETE);
		processRun.setEndTime(new Date());
		Long duration = processRunService.getProcessDuration(processRun);
		processRun.setDuration(duration);
		Long lastSubmitDuration = processRunService.getProcessLastSubmitDuration(processRun);
		processRun.setLastSubmitDuration(lastSubmitDuration);
		// 更新历史
		processRunDao.updateHistory(processRun);
		// 删除流程实例
		processRunDao.delById(processRun.getRunId());

		// 删除候选人
		taskDao.delCandidateByInstanceId(instanceId);
		// 根据流程实例删除流程。
		taskDao.delByInstanceId(instanceId);
		// 更新历史实例。
		updHistoryActInst(instanceId, nodeId, sysUser.getUserId().toString());
		// 更新流程实例
		HistoricProcessInstanceEntity processInstance = (HistoricProcessInstanceEntity) historyService
				.createHistoricProcessInstanceQuery()
				.processInstanceId(instanceId.toString()).singleResult();
		processInstance.setEndTime(new Date());

		// 对Act框架的表，时间暂时不作按日历计算
		processInstance.setDurationInMillis(System.currentTimeMillis()
				- processInstance.getStartTime().getTime());
		processInstance.setEndActivityId(nodeId);
		historyProcessInstanceDao.update(processInstance);
		// 根据流程实例删除变量
		executionDao.delVariableByProcInstId(instanceId);
		// 根据流程实例删除excution。
		executionDao.delExecutionByProcInstId(instanceId);

		bpmProStatusDao.updStatus(instanceId, nodeId,
				TaskOpinion.STATUS_ENDPROCESS);
		// 更新流程审批历史。
		updateTaskOpinion(instanceId, memo, sysUser);

		return processRun;

	}
	
	/**
	 * 更新流程实例列表。
	 * 1、更新流程的执行时间
	 * 2、执行人
	 * 
	 * @param list
	 */
	public void updHistoryActInst(Long actInstId, String nodeId, String assignee) {
		List<HistoricActivityInstanceEntity> hisList = historyActivityDao.getByInstanceId(actInstId, nodeId);
		for (HistoricActivityInstanceEntity hisActInst : hisList) {
//			Date startTime = hisActInst.getStartTime();
			Date endTime = new Date();
//			Long userId = StringUtil.isEmpty(assignee)?0:Long.valueOf(assignee);
			
//			Long duration = calendarAssignService.getRealWorkTime(startTime, endTime,userId );
			hisActInst.setEndTime(endTime);
			hisActInst.setDurationInMillis(System.currentTimeMillis()
					- hisActInst.getStartTime().getTime());
			//任务执行人为空或者 执行人和当前人不一致，设置当前人为任务执行人。
			if(StringUtil.isEmpty(hisActInst.getAssignee()) || !hisActInst.getAssignee().equals(assignee)){
				hisActInst.setAssignee(assignee);
			}
			historyActivityDao.update(hisActInst);
		}
	}
	
	/**
	 * 
	 * @param actInstId
	 * @return
	 */
	public List<TaskNodeStatus> getNodeCheckStatusInfo(String actInstId) {
		List<TaskNodeStatus> taskNodeStatusList = new ArrayList<TaskNodeStatus>();
		List<TaskOpinion> taskOpinionList = taskOpinionService.getByActInstId(actInstId);
		
		Map<TaskNodeStatus,TaskNodeStatus> map=new HashMap<TaskNodeStatus, TaskNodeStatus>();
		
		for(TaskOpinion taskOpinion:taskOpinionList){
			TaskNodeStatus taskNodeStatus = new TaskNodeStatus();
			taskNodeStatus.setActInstId(taskOpinion.getActInstId());
			taskNodeStatus.setTaskKey(taskOpinion.getTaskKey());
			if(map.containsKey(taskNodeStatus)){
				TaskNodeStatus tmp= map.get(taskNodeStatus);
				tmp.getTaskOpinionList().add(taskOpinion);
			}
			else{
				taskNodeStatus.getTaskOpinionList().add(taskOpinion);
				map.put(taskNodeStatus, taskNodeStatus);
			}
		}
		
		taskNodeStatusList.addAll(map.values());
		return taskNodeStatusList;
	}
	
	
	/**
	 * 获取流程任务审批状态
	 * 
	 * @param actInstId		流程实例ID
	 * @param nodeId		节点ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TaskNodeStatus getNodeCheckStatusInfo(String actInstId, String nodeId) {
		TaskNodeStatus taskNodeStatus = new TaskNodeStatus();
		List<TaskOpinion> taskOpinionList = taskOpinionService.getByActInstIdTaskKey(new Long( actInstId), nodeId);
		if (BeanUtils.isNotEmpty(taskOpinionList)) {
			Collections.sort(taskOpinionList, new Comparator<TaskOpinion>() {
				public int compare(TaskOpinion o1, TaskOpinion o2) {
					return o1.getStartTime().compareTo(o2.getStartTime());
				}
			});
			Collections.reverse(taskOpinionList);
			taskNodeStatus.setActInstId(actInstId);
			taskNodeStatus.setTaskKey(nodeId);
			taskNodeStatus.setTaskOpinionList(taskOpinionList);

			TaskOpinion opinion = taskOpinionList.get(taskOpinionList.size() - 1);
			taskNodeStatus.setLastCheckStatus(opinion.getCheckStatus());
		}
		else{
			ProcessRun processRun = processRunService.getByActInstanceId(Long.parseLong(actInstId));
			if(processRun.getStatus().equals(ProcessRun.STATUS_RUNNING)){//流程运行时
				Map<String, Object> vars = runtimeService.getVariables(actInstId);
				// 为TaskNodeStatus添加流程执行候选人
				List<TaskExecutor> taskExecutorList = bpmNodeUserService.getExeUserIds(processRun.getActDefId(),
						actInstId, nodeId, (String)vars.get("startUser"), (String)vars.get("startUser"), vars);
				taskNodeStatus.setTaskExecutorList(taskExecutorList);
			}
		}
		return taskNodeStatus;
	}
	
	/**
	 * 根据流程实例ID和任务定义Key获取任务。
	 * 
	 * @param taskId
	 * @return
	 */
	public List<?> getByInstanceIdTaskDefKey(String instanceId,
			String taskDefKey) {
		return taskDao.getByInstanceIdTaskDefKey(instanceId, taskDefKey);
	}
	
	/**
	 * 清除起始节点后的连线，重新创建起始节点到某个节点的连线
	 * @param actDefId			流程定义ID
	 * @param targetNodeId		跳转到的目标节点
	 * 开发人员 helh
	 */
	public void reDrawLink(String actDefId, String targetNodeId){
		String start = NodeCache.getStartNode(actDefId).getNodeId();
		// 获取流程定义对象
		ProcessDefinitionEntity processDefinition = getProcessDefinitionEntity(actDefId);
		// 获取当前流程的活动实例
		ActivityImpl curActi = processDefinition.findActivity(start);
		ActivityImpl destAct= processDefinition.findActivity(targetNodeId);
		curActi.getOutgoingTransitions().clear();
		//创建一个连接
		TransitionImpl transitionImpl = curActi.createOutgoingTransition();
		transitionImpl.setDestination(destAct);
	}
	
	/**
	 * 通过用户Id和流程实例Id获得任务
	 * 
	 * @param userId
	 *            用户Id
	 * @param actInstId
	 *            流程实例Id
	 * @return 任务
	 */
	public List<?> getTaskByUserInstId(Long userId, Long actInstId) {
		return taskDao.getTaskByUserInstId(userId, actInstId);
	}
	/**
	 * 获取手机待办数
	 * @param userId
	 * @return
	 */
//	public int getMyMobileTaskCount(Long userId) {
//		return taskDao.getMyMobileTaskCount(userId);
//	}
	
	/**
	 * LZC
	 */
	public List<TaskAmount> getMyProcessCount(Long userId) {
		// TODO Auto-generated method stub
	   	 return (List<TaskAmount>) taskDao.getMyProcessCount(userId);
	}

	/**
	 * LZC
	 */
	public List<TaskAmount> getMyTaskKeyCount(Long userId) {
		// TODO Auto-generated method stub
	   	 return (List<TaskAmount>) taskDao.getMyTaskKeyCount(userId);
	}
}
