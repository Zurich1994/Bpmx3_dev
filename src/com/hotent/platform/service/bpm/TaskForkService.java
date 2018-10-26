package com.hotent.platform.service.bpm;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateTask;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskFork;
import com.hotent.platform.dao.bpm.ProcessRunDao;
import com.hotent.platform.dao.bpm.TaskForkDao;

/**
 * 对象功能:任务分发 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-27 09:11:41
 */
@Service
public class TaskForkService extends BaseService<TaskFork>
{
	@Resource
	private TaskForkDao dao;
	@Resource
	private ProcessRunDao processRunDao;
	public TaskForkService()
	{
	}
	
	@Override
	protected IEntityDao<TaskFork, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 返回当前分发序号的最大数
	 * @param actInstId
	 * @return
	 */
	public Integer getMaxSn(String actInstId){
		return dao.getMaxSn(actInstId);
	}
	/**
	 * 产生任务分发的记录。
	 * <pre>
	 *  分发记录记录了。
	 *  1.流程实例ID。
	 *  2.分发时间。
	 *  3.完成分支的数量。
	 *  4.分发节点（节点id和节点名称）
	 *  5.汇总的节点（节点id和节点名称）
	 *  6.记录分发的令牌。
	 *  7.分发的层次，是1级分发，还是二级分发。
	 * </pre>
	 * @param delegateTask 产生的分发任务
	 * @param joinTaskName 汇总任务名
	 * @param joinTaskKey 汇总任务key
	 * @param forkCount 分发用户的个数。
	 */
	public TaskFork newTaskFork(DelegateTask delegateTask,String joinTaskName,String joinTaskKey,Integer forkCount){
		//获取当前任务的分发的令牌, value as T_1 or T_1_2
		String token=(String)delegateTask.getVariableLocal(TaskFork.TAKEN_VAR_NAME);
		
		TaskFork taskFork=new TaskFork();
		taskFork.setTaskForkId(UniqueIdUtil.genId());
		taskFork.setActInstId(delegateTask.getProcessInstanceId());
		taskFork.setForkTime(new Date());
		taskFork.setFininshCount(0);
		taskFork.setForkCount(forkCount);
		taskFork.setForkTaskKey(delegateTask.getTaskDefinitionKey());
		taskFork.setForkTaskName(delegateTask.getName());
		taskFork.setJoinTaskKey(joinTaskKey);
		taskFork.setJoinTaskName(joinTaskName);
		
		Integer sn=1;
		if(token==null) {
			taskFork.setForkTokenPre(TaskFork.TAKEN_PRE+"_");
		}
		else{
			String[]lines=token.split("[_]");
			if(lines!=null && lines.length>1){
				sn=lines.length-1;
				String forkTokenPre=token.substring(0, token.lastIndexOf(lines[sn]));
				taskFork.setForkTokenPre(forkTokenPre);
			}
		}
		//记录分发令牌。
		String forkTokens="";
		for(int i=1;i<=forkCount;i++){
			forkTokens+=taskFork.getForkTokenPre() + i + ",";
		}
		taskFork.setForkTokens(forkTokens);
		//记录分发的层次。
		taskFork.setForkSn(sn);
		add(taskFork);
		return taskFork;
	}
	
	/**
	 * 返回某个流程实例中的汇总节点的配置信息
	 * @param actInstId
	 * @param joinTaskKey
	 * @return
	 */
	public  TaskFork getByInstIdJoinTaskKey(String actInstId,String joinTaskKey){
		return dao.getByInstIdJoinTaskKey(actInstId, joinTaskKey);
	}
	
	/**
	 * 返回带有某个流程实例汇总的令牌的分发实体
	 * @param actInstId
	 * @param joinTaskKey
	 * @param forkToken
	 * @return
	 */
	public TaskFork getByInstIdJoinTaskKeyForkToken(String actInstId,String joinTaskKey,String forkToken){
		return dao.getByInstIdJoinTaskKeyForkToken(actInstId, joinTaskKey, forkToken);
	}
	
	/**
	 * 根据act流程定义Id删除流程分发信息
	 * @param actDefId
	 */
	public void delByActDefId(String actDefId){
		List<ProcessRun> prolist = processRunDao.getbyActDefId(actDefId);
		if(prolist.size()>0){
			for(ProcessRun processRun:prolist){
				if(ProcessRun.STATUS_FORM.equals(processRun.getStatus())) continue;
				dao.delByActInstId(processRun.getActInstId().toString()) ;
			}
		}
	}
}
