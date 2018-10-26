package com.hotent.platform.service.bpm.skipimpl;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.task.Task;

import com.hotent.core.bpm.setting.ISkipCondition;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.service.bpm.TaskOpinionService;


/**
 * 如果之前进行过审批人和当前任务执行人相同，那么可以跳过。
 * @author Administrator
 *
 */
public class ApproveUserSkipCondition implements ISkipCondition {
	
	@Resource
	TaskOpinionService taskOpinionService;

	@Override
	public boolean canSkip(Task task) {
		String assignee=task.getAssignee();
		
		//获取之前的审批人，从审批历史中获取。
		List<TaskOpinion> list= taskOpinionService.getByActInstId(task.getProcessInstanceId());
		for(TaskOpinion opinion:list){
			Short status=opinion.getCheckStatus();
			if(status.equals(TaskOpinion.STATUS_AGREE)
					|| status.equals(TaskOpinion.STATUS_ABANDON)
					|| status.equals(TaskOpinion.STATUS_REFUSE)){
				if(assignee.equals(opinion.getExeUserId().toString())){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String getTitle() {
		return "之前审批过后跳过";
	}

}
