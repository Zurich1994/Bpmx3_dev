package com.hotent.platform.service.bpm.listener;

import javax.annotation.Resource;
import org.activiti.engine.delegate.DelegateTask;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.service.bpm.TaskOpinionService;
/**
 * 任务分配时执行事件
 * @author ray
 *
 */
public class TaskAssignListener extends BaseTaskListener {
	
	@Resource
	private TaskOpinionService taskOpinionService;
	
	@Override
	protected void execute(DelegateTask delegateTask, String actDefId,
			String nodeId) {
		String userId=delegateTask.getAssignee();
		logger.debug("任务ID:" + delegateTask.getId());
		TaskOpinion taskOpinion=taskOpinionService.getByTaskId(new Long(delegateTask.getId()));
		if(taskOpinion!=null){
			logger.debug("update taskopinion exe userId" + userId);
			
			taskOpinion.setExeUserId(Long.parseLong(userId));
			taskOpinionService.update(taskOpinion);
		}
		
		delegateTask.setOwner(userId);

		
	}

	@Override
	protected int getScriptType() {
		 
		return BpmConst.AssignScript;
	}

	@Override
	protected int getBeforeScriptType() {
		// TODO Auto-generated method stub
		return 0;
	}

}
