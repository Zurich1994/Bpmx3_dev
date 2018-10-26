package com.hotent.platform.service.bpm;

import com.hotent.platform.model.bpm.TaskOpinion;

public class BpmTaskUtil {
	
	/**
	 * 判断任务状态是否能交办。
	 * @param taskStatus
	 * @return
	 */
	public static boolean isCanAssignee(String taskStatus){
		boolean isCanAssignee=true;
		if(TaskOpinion.STATUS_RECOVER_TOSTART.toString().equals(taskStatus) ||
				TaskOpinion.STATUS_REJECT_TOSTART.toString().equals(taskStatus)||
				TaskOpinion.STATUS_REJECT.toString().equals(taskStatus)||
				TaskOpinion.STATUS_DELEGATE.toString().equals(taskStatus)){
			isCanAssignee=false;
		}
		return isCanAssignee;
	}

}
