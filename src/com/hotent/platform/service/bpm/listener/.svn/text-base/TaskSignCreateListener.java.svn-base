package com.hotent.platform.service.bpm.listener;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.bpm.TaskSignData;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.AgentSettingService;
import com.hotent.platform.service.bpm.BpmProStatusService;
import com.hotent.platform.service.bpm.TaskOpinionService;
import com.hotent.platform.service.bpm.TaskSignDataService;
import com.hotent.platform.service.bpm.impl.BpmNodeUserUtil;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.bpm.thread.TaskThreadService;
import com.hotent.platform.service.bpm.thread.TaskUserAssignService;
import com.hotent.platform.service.system.SysUserService;

/**
 * 会签任务监听器。
 * @author hotent
 *
 */
public class TaskSignCreateListener extends BaseTaskListener {
	
	private Logger logger=LoggerFactory.getLogger(TaskSignCreateListener.class);

	@Resource
	private TaskSignDataService taskSignDataService;
	@Resource
	private TaskOpinionService taskOpinionService;
	@Resource
	private TaskUserAssignService taskUserAssignService;
	@Resource
	private BpmProStatusService bpmProStatusService;
	@Resource
	private AgentSettingService agentSettingService;
	@Resource
	private SysUserService sysUserService;
	
	@Override
	protected void execute(DelegateTask delegateTask, String actDefId,String nodeId) {
		//设定任务状态为待审批。
		delegateTask.setDescription(TaskOpinion.STATUS_CHECKING.toString());
			
		TaskOpinion taskOpinion=new TaskOpinion(delegateTask);
		taskOpinion.setOpinionId(UniqueIdUtil.genId());
		taskOpinionService.add(taskOpinion);
		
		//获取任务执行人。
		TaskExecutor taskExecutor=(TaskExecutor) delegateTask.getVariable("assignee");
		
		//分配用户
		assignTaskExecutor(delegateTask,taskExecutor);
		
		//把新生成的任务加至执行堆栈中
		TaskThreadService.addTask((TaskEntity)delegateTask);
		
		String processInstanceId=delegateTask.getProcessInstanceId();

		logger.debug("enter the signuser listener notify method, taskId:"+ delegateTask.getId() + " assignee:"+ delegateTask.getAssignee());
		
		Integer instanceOfNumbers=(Integer)delegateTask.getVariable("nrOfInstances");
		Integer loopCounter=(Integer)delegateTask.getVariable("loopCounter");

		if(loopCounter==null) loopCounter=0;
		
		logger.debug("instance of numbers:"+ instanceOfNumbers + " loopCounters:"+ loopCounter);
		
		if(loopCounter==0){//第一次进入执行
			addSignData(delegateTask, nodeId, processInstanceId, instanceOfNumbers);
		}
		
		//添加流程状态。
		bpmProStatusService.addOrUpd(actDefId,new Long(processInstanceId), nodeId);
		
		//更新会签数据的任务ID。
		updTaskSignData(processInstanceId,nodeId,taskExecutor,delegateTask.getId());
			
	}
	
	/**
	 * 更新会签数据的任务Id。
	 * @param processInstanceId
	 * @param nodeId
	 * @param taskExecutor
	 * @param taskId
	 */
	private void updTaskSignData(String processInstanceId,String nodeId,TaskExecutor taskExecutor,String taskId){
		String executorId= taskExecutor.getExecuteId();
		TaskSignData taskSignData =taskSignDataService.getUserTaskSign(processInstanceId, nodeId,  executorId);
		if(taskSignData==null) return;
		taskSignData.setTaskId(taskId);
		taskSignDataService.update(taskSignData);
	}

	/**
	 * 添加会签数据。
	 * @param delegateTask
	 * @param nodeId
	 * @param processInstanceId
	 * @param instanceOfNumbers
	 */
	private void addSignData(DelegateTask delegateTask, String nodeId,
			String processInstanceId, Integer instanceOfNumbers) {
		
		Map<String, List<TaskExecutor>>  nodeUserMap=taskUserAssignService.getNodeUserMap();
		if(nodeUserMap==null) return;
		List<TaskExecutor> signUserList=nodeUserMap.get(nodeId);
		if(signUserList==null) return;
		
		int batch=taskSignDataService.getMaxBatch(processInstanceId, nodeId) +1;
		
		for(int i=0;i<instanceOfNumbers;i++){
			int sn=i + 1;
			//生成投票的数据
			TaskSignData signData=new TaskSignData();
			signData.setActInstId(processInstanceId);
			
			signData.setNodeName(delegateTask.getName());
			signData.setNodeId(nodeId);
			signData.setSignNums(sn);
			signData.setIsCompleted(TaskSignData.NOT_COMPLETED);

			TaskExecutor signUser=signUserList.get(i);
			if(signUser!=null){
				signData.setVoteUserId(signUser.getExecuteId());
				signData.setVoteUserName(signUser.getExecutor());
			}
			signData.setDataId(UniqueIdUtil.genId());
			signData.setBatch(batch);
			
			taskSignDataService.add(signData);
		}
	}
	
	/**
	 * 设置任务执行人
	 * @param delegateTask
	 * @param taskExecutor
	 * @param sysUserId
	 */
	private void setAssignUser(DelegateTask delegateTask,TaskExecutor taskExecutor,Long sysUserId){
		
		SysUser sysUser = null;
		if(isAllowAgent()){
			sysUser = agentSettingService.getAgent(delegateTask,sysUserId);
		}
		if(sysUser!=null){
			delegateTask.setAssignee(sysUser.getUserId().toString());
			delegateTask.setDescription(TaskOpinion.STATUS_AGENT.toString());
			delegateTask.setOwner(sysUserId.toString());
		}else{
			delegateTask.setAssignee(sysUserId.toString());
		}
		TaskOpinion taskOpinion= taskOpinionService.getByTaskId(new Long(delegateTask.getId()));
		SysUser exeUser= sysUserService.getById(sysUserId);
		taskOpinion.setExeUserId(exeUser.getUserId());
		taskOpinion.setExeFullname(exeUser.getFullname());
		taskOpinionService.update(taskOpinion);
	}
	
	/**
	 * 分配任务执行人。
	 * @param delegateTask
	 */
	private void assignTaskExecutor(DelegateTask delegateTask,TaskExecutor taskExecutor){
		//设置执行人
		if(TaskExecutor.USER_TYPE_USER.equals(taskExecutor.getType())){
			delegateTask.setOwner(taskExecutor.getExecuteId());
			Long sysUserId = Long.valueOf(taskExecutor.getExecuteId());
			setAssignUser(delegateTask, taskExecutor, sysUserId);
		}
		else{
			delegateTask.setAssignee(BpmConst.EMPTY_USER);
			delegateTask.setOwner(BpmConst.EMPTY_USER);
			//二次抽取
			if(TaskExecutor.EXACT_EXACT_SECOND==taskExecutor.getExactType()){
				List<SysUser> userList=BpmNodeUserUtil.getUserListByExecutor(taskExecutor);
				if(BeanUtils.isEmpty(userList)){
					String msg="[" +taskExecutor.getExecutor() +"],没有设置人员,请先设置人员。";
					MessageUtil.addMsg(msg);
					throw new RuntimeException(msg);
				}
				for(SysUser sysUser:userList){
					delegateTask.addCandidateUser(sysUser.getUserId().toString());
				}
			}
			//不抽取
			else if(TaskExecutor.EXACT_NOEXACT==taskExecutor.getExactType()){
				delegateTask.addGroupIdentityLink(taskExecutor.getExecuteId(), taskExecutor.getType());
			}
			//用户组
			else if(TaskExecutor.EXACT_USER_GROUP==taskExecutor.getExactType()){
				String[] aryExecutor=taskExecutor.getExecuteId().split(",");
				if(aryExecutor.length==1){
					delegateTask.setOwner(taskExecutor.getExecuteId());
					Long sysUserId = Long.valueOf(taskExecutor.getExecuteId());
					setAssignUser(delegateTask, taskExecutor, sysUserId);
					
				}
				else{
					for(String executorId:aryExecutor){
						delegateTask.addCandidateUser(executorId);
					}
				}
			}
		}
	}
	
	/**
	 * 添加流程执行状态。
	 * @param actDefId
	 * @param processInstanceId
	 * @param nodeId
	 */
	@Override
	protected int getScriptType() {
		return BpmConst.StartScript;
	}

	@Override
	protected int getBeforeScriptType() {
		return BpmConst.StartBeforeScript;
	}

}
