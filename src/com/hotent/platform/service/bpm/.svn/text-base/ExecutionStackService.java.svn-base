package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.BpmProStatusDao;
import com.hotent.platform.dao.bpm.ExecutionDao;
import com.hotent.platform.dao.bpm.ExecutionStackDao;
import com.hotent.platform.model.bpm.ExecutionStack;
import com.hotent.platform.model.bpm.TaskFork;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.service.bpm.thread.TaskThreadService;
import com.hotent.platform.service.bpm.thread.TaskUserAssignService;

/**
 * 对象功能:流程执行堆栈树 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-31 09:53:00
 */
@Service
public class ExecutionStackService extends BaseService<ExecutionStack>
{
	@Resource
	private ExecutionStackDao dao;
	@Resource
	private ExecutionDao executionDao;
	
	@Resource
	private TaskService taskService;
	@Resource
	private BpmService bpmService;
	
	@Resource
	private TaskOpinionService taskOpinionService;
	
	@Resource
	private TaskUserAssignService taskUserAssignService;
	
	@Resource
	private BpmProStatusDao bpmProStatusDao;
	
	public ExecutionStackService()
	{
	}
	
	@Override
	protected IEntityDao<ExecutionStack, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 按初始化的流程实例ID构建初始化的堆栈树。
	 * <pre>
	 * 	实现方法：
	 *  1.根据流程实例id查询当前活动的任务。
	 *  2.对任务进行遍历。
	 *  	1.普通的任务直接在堆栈中添加一条记录。
	 *  	2.会签节点会在堆栈中记录 节点id，并把会签的任务id和任务执行人使用逗号分隔记录起来，再添加到堆栈中。
	 * </pre>
	 * @param actInstId
	 */
	public void initStack(String actInstId,List<Task> taskList,Long parentStackId){
		if(BeanUtils.isEmpty(taskList)) return;
		Map<String,ExecutionStack> nodeIdStackMap=new HashMap<String, ExecutionStack>();
		for(Task task:taskList){		
			String nodeId=task.getTaskDefinitionKey();
			if(!nodeIdStackMap.containsKey(nodeId)){
				ExecutionStack stack=new ExecutionStack();
				stack.setActInstId(new Long( actInstId));
				stack.setActDefId(task.getProcessDefinitionId());
				stack.setAssignees(task.getAssignee());
				stack.setDepth(1);
				stack.setParentId(parentStackId);
				stack.setStartTime(new Date());
				stack.setNodeId(nodeId);
				stack.setNodeName(task.getName());
				stack.setTaskIds(task.getId());
				Long stackId=UniqueIdUtil.genId();
				stack.setStackId(stackId);
				stack.setNodePath("0." + stackId + ".");
				nodeIdStackMap.put(nodeId, stack);
			}
			else//为多实例的会签任务
			{
				ExecutionStack stack=nodeIdStackMap.get(nodeId);
				stack.setIsMultiTask(ExecutionStack.MULTI_TASK);
				stack.setAssignees(stack.getAssignees()+","+task.getAssignee());
				stack.setTaskIds(stack.getTaskIds()+ "," + task.getId());
			}
		}
		
		Iterator<ExecutionStack> stackIt=nodeIdStackMap.values().iterator();
		while(stackIt.hasNext()){
			ExecutionStack exeStack=stackIt.next();
			dao.add(exeStack);
		}
	}
	
	/**
	 * 流程回退时，堆栈需要进行流程命令的参数变更
	 * @param processCmd
	 * @param taskEntity
	 * @param taskToken
	 * @return
	 */
	public ExecutionStack backPrepared(ProcessCmd processCmd,TaskEntity taskEntity,String taskToken){
		//若为回退时，需要找出其原来的堆栈树的父节点
		ExecutionStack parentStack=null;
		String instanceId=taskEntity.getProcessInstanceId();
		String actDefId=taskEntity.getProcessDefinitionId();
		String nodeId=taskEntity.getTaskDefinitionKey();
		//指定了其返回的堆栈节点
		if(processCmd.getStackId()!=null){
			parentStack=dao.getById(processCmd.getStackId());
		}
		//若没有指定其回退的目标节点，动态获取回退节点
		else if(StringUtils.isEmpty(processCmd.getDestTask())){
			ExecutionStack executionStack= getLastestStack(instanceId, nodeId,taskToken);
			if(executionStack!=null )
			{
				parentStack = dao.getById(executionStack.getParentId());
				if(parentStack.getActInstId().equals(executionStack.getActInstId())){
					while(nodeId.equals(parentStack.getNodeId())){
						parentStack = dao.getById(parentStack.getParentId());
					}
					//在往上遍历排除因为自循环引起的问题
					ExecutionStack preStack= dao.getById(parentStack.getParentId());
					if(preStack != null){
						while(preStack.getNodeId().equals(parentStack.getNodeId())){
							parentStack=preStack;
							preStack=dao.getById(parentStack.getParentId());
						}
					}
				}
				//将当前的堆栈加到父堆栈的外出堆栈。
				parentStack.addOutExecutionStack(executionStack);
			}
		}
		
		//找到父节点，则修改其对应的
		if(parentStack!=null){
			//设置其回退的任务ID
			processCmd.setDestTask(parentStack.getNodeId());
			boolean rtn=NodeCache.isSignTaskNode(actDefId, parentStack.getNodeId());
			//会签不获取之前的人员。
			if(!rtn){
				String assignee= parentStack.getAssignees();
				String[] aryAssignee=assignee.split(",");
				List<TaskExecutor> list=new ArrayList<TaskExecutor>();
				for(String userId:aryAssignee){
					list.add(TaskExecutor.getTaskUser(userId, ""));
				}
				taskUserAssignService.addNodeUser(parentStack.getNodeId(), list);
			}
		
		}
		
		return parentStack;
		
	}

	/**
	 * 弹出堆栈。
	 * @param parentStack
	 */
	public void pop(ExecutionStack parentStack){
		//删除当前堆栈。
		List<ExecutionStack> list=findChildsByParentId(parentStack);
		for(ExecutionStack stack:list){
			dao.delById(stack.getStackId());
		}
	}
	
	/**
	 * 根据父堆栈查找其下所有的堆栈数据。
	 * @param parentStack
	 * @return
	 */
	public List<ExecutionStack> findChildsByParentId(ExecutionStack parentStack){
		List<ExecutionStack> allList=new ArrayList<ExecutionStack>();
		List<ExecutionStack> list=getByParentId(parentStack.getStackId());
		allList.addAll(list);
		for(ExecutionStack stack:list){
			recursionByParent(stack.getStackId(),allList);
		}
		return allList;
	}
	
	/**
	 * 递归访问。
	 * @param parentId
	 * @param allList
	 */
	private void recursionByParent(Long parentId,List<ExecutionStack> allList){
		List<ExecutionStack> list=getByParentId(parentId);
		if(BeanUtils.isEmpty(list)) return;
		allList.addAll(list);
		for(ExecutionStack stack:list){
			recursionByParent(stack.getStackId(),allList);
		}
	}
	
	
	/**
	 * 当回退或驳回任务时，需要产生堆栈树节点上的兄弟节点对应的任务
	 * @param parentStack
	 * @param copyTaskId
	 */
	private void genSiblingTask(ExecutionStack parentStack,ProcessTask copyTaskEntity){
		List<ExecutionStack> parentSiblings=dao.getByActInstIdDepExStackId(parentStack.getActInstId(),parentStack.getDepth(),parentStack.getStackId());
		
		if(parentSiblings==null || parentSiblings.size()==0){//update main thread execution to parent node
			executionDao.updateMainThread(copyTaskEntity.getProcessInstanceId(), copyTaskEntity.getTaskDefinitionKey());
			executionDao.updateTaskToMainThreadId(copyTaskEntity.getProcessInstanceId(),copyTaskEntity.getId());
			//删除非主线程的Execution
			executionDao.delNotMainThread(copyTaskEntity.getProcessInstanceId());
			return;
		}

		for(ExecutionStack stack:parentSiblings){
			//查找兄弟节点下的任务节点是否还在运行,若在运行则不需要生成其任务
			List<ExecutionStack> childStackList=dao.getByLikeNodePath(stack.getNodePath());
			boolean isChildTaskExist=false;
			for(ExecutionStack childStack:childStackList){
				if(childStack.getTaskIds()==null) continue;
				String[]taskIds=childStack.getTaskIds().split("[,]");
				for(String taskId:taskIds){
					TaskEntity task=bpmService.getTask(taskId);
					if(task!=null){
						isChildTaskExist=true;
						break;
					}
				}
				if(isChildTaskExist) break;
			}
			//若旁边节点没有任务实例,则需要产生兄弟节点对应的任务实例
			if(!isChildTaskExist){
				if(StringUtils.isNotEmpty(stack.getAssignees())){
					String[]assignes=stack.getAssignees().split("[,]");
					for(String assign:assignes){
						ProcessTask newTask=bpmService.newTask(copyTaskEntity.getId(), assign,stack.getNodeId(),stack.getNodeName());
						bpmProStatusDao.updStatus(new Long (newTask.getProcessInstanceId()), newTask.getTaskDefinitionKey(), TaskOpinion.STATUS_CHECKING);
					}
				}else{
					ProcessTask newTask=bpmService.newTask(copyTaskEntity.getId(), stack.getAssignees(),stack.getNodeId(),stack.getNodeName());
					bpmProStatusDao.updStatus(new Long (newTask.getProcessInstanceId()), newTask.getTaskDefinitionKey(), TaskOpinion.STATUS_CHECKING);
				}
				
			}
		}
		
	}

	/**
	 * 把一组新的任务加至某个树节点下
	 * @param actInstId 流程实例Id
	 * @param destNodeId 新的任务挂的父节点的任务(activityId)
	 * @param newTasks 流程新任务
	 * @param oldTaskToken 上一已经完成的任务令牌
	 * @throws Exception
	 */
	public void pushNewTasks(String actInstId, String destNodeId,List<Task> newTasks,String oldTaskToken) throws Exception{
		if(newTasks.size()==0) return;
		Long curUserId=ContextUtil.getCurrentUserId();
		if(curUserId==null)curUserId = SystemConst.SYSTEMUSERID;
		
		//获取当前任务在树中的位置
		ExecutionStack curExeNode=dao.getLastestStack(actInstId, destNodeId,oldTaskToken);
//		if(curExeNode!=null){
//			if(curExeNode.getAssignees()==null){
//				curExeNode.setAssignees(curUserId.toString());
//			}
//			curExeNode.setEndTime(new Date());
//			dao.update(curExeNode);
//		}
		ProcessDefinitionEntity processDef=null;
		
		//把新增加的任务放堆栈树中去
		Map<String,ExecutionStack> nodeIdStackMap=new HashMap<String, ExecutionStack>();
		int i=0;
		
		//判断当前任务列表中是否存在相同key，若相同则代表目前是分发任务，
		//需要记录，以使后续产生令牌记录不同的任务线上执行的细则
		boolean isIssued=false;
		Set<Task> nodeSet=new HashSet<Task>(newTasks);
		if(nodeSet.size()<newTasks.size()){
			isIssued=true;
		}

		for(Task task:newTasks){
			i++;
			TaskEntity taskEntity=(TaskEntity)task;
			try{
				taskEntity=(TaskEntity)taskService.createTaskQuery().taskId(task.getId()).singleResult();
			}catch(Exception ex){
				logger.warn("ex:"+ ex.getMessage());
			}
			if(taskEntity==null) continue;
			
			String nodeId=taskEntity.getTaskDefinitionKey();
			
			if(processDef==null){
				processDef=bpmService.getProcessDefinitionEntity(taskEntity.getProcessDefinitionId());
			}
			
			ActivityImpl taskAct = processDef.findActivity(nodeId);
			
			if(taskAct==null) continue;

			String multiInstance=(String)taskAct.getProperty("multiInstance");
			ExecutionStack stack=nodeIdStackMap.get(nodeId);
			//为多实例的会签任务后续其他实体
			if(StringUtils.isNotEmpty(multiInstance) && stack!=null){
				stack.setIsMultiTask(ExecutionStack.MULTI_TASK);
				stack.setAssignees(stack.getAssignees()+","+task.getAssignee());
				stack.setTaskIds(stack.getTaskIds()+ "," + task.getId());
				dao.update(stack);
				continue;
			}
			//为普通的任务或第一次添加会签任务至集合中
			Long stackId=UniqueIdUtil.genId();
			stack=new ExecutionStack();
			stack.setActInstId(new Long( taskEntity.getProcessInstanceId()));
			stack.setAssignees(taskEntity.getAssignee());
			stack.setActDefId(taskEntity.getProcessDefinitionId());
			if(curExeNode==null){
				stack.setDepth(1);
				stack.setParentId(0L);
				stack.setNodePath("0." + stackId + ".");
			}else{
				stack.setDepth(curExeNode.getDepth()==null?1:curExeNode.getDepth()+1);
				stack.setParentId(curExeNode.getStackId());
				stack.setNodePath(curExeNode.getNodePath() + stackId + ".");
			}
			
			stack.setStartTime(new Date());
			stack.setNodeId(nodeId);
			stack.setNodeName(taskEntity.getName());
			stack.setTaskIds(taskEntity.getId());
			stack.setStackId(stackId);
			
			String taskToken=(String)taskService.getVariableLocal(taskEntity.getId(),TaskFork.TAKEN_VAR_NAME);
			if(taskToken!=null){
				stack.setTaskToken(taskToken);
			}else if(stack!=null && isIssued){//two or more tasks are the same node instance,need to distinct to each other
				String token="T_"+i;
				taskService.setVariableLocal(taskEntity.getId(),TaskFork.TAKEN_VAR_NAME, token);
				stack.setTaskToken(token);
			}
			dao.add(stack);
			nodeIdStackMap.put(nodeId, stack);
			
		}
	}
	
	/**
	 * 把当前任务完成后，产生的新任务加至执行堆栈中去
	 * @param actInstId 实体Id
	 * @param destNodeId 上一已经完成的任务定义Key
	 * @param oldTaskToken 上一已经完成的任务令牌
	 * @throws Exception
	 */
	public void addStack(String actInstId,String destNodeId,String oldTaskToken) throws Exception
	{
		List<Task> taskList=TaskThreadService.getNewTasks();
		if(taskList!=null){
			pushNewTasks(actInstId,destNodeId,taskList,oldTaskToken);
		}
		
	}
	
	/**
	 * 按父ID取得所有子结节
	 * @param parentId
	 * @return
	 */
	public List<ExecutionStack> getByParentId(Long parentId){
		return dao.getByParentId(parentId);
	}
	
	/**
	 * 按父Id取得下面所有子节点
	 * @param parentId
	 * @return
	 */
	public List<ExecutionStack> getByParentIdAndEndTimeNotNull(Long parentId){
		return dao.getByParentIdAndEndTimeNotNull(parentId);
	}
	
	/**
	 * 根据流程实例ID流程节点ID获取执行堆栈列表。
	 * @param actInstId
	 * @param nodeId
	 * @return
	 */
	public List<ExecutionStack> getByActInstIdNodeId(String actInstId, String nodeId){
		return dao.getByActInstIdNodeId(actInstId, nodeId);
	}
	
	/**
	 * 获取堆栈树中最新的某个节点
	 * @param actInstId
	 * @param nodeId
	 * @return
	 */
	public ExecutionStack getLastestStack(String actInstId, String nodeId){
		return dao.getLastestStack(actInstId, nodeId);
	}
	
	/**
	 * 根据任务token ，流程实例，节点id 获取堆栈的最近的一个堆栈数据。
	 * @param actInstId		流程实例ID
	 * @param nodeId		节点ID
	 * @param taskToken		任务token
	 * @return
	 */
	public ExecutionStack getLastestStack(String actInstId, String nodeId,String taskToken){
		return dao.getLastestStack(actInstId, nodeId,taskToken);
	}
	
	/**
	 * 级联删除子树节点下的所有子节点
	 * @param stackId
	 * @param nodePath
	 * @return
	 */
	public Integer delSubChilds(Long stackId,String nodePath){
		return dao.delSubChilds(stackId, nodePath);
	}

}
