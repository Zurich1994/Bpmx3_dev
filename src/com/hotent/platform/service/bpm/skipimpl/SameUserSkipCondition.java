package com.hotent.platform.service.bpm.skipimpl;

import org.activiti.engine.task.Task;

import com.hotent.core.bpm.setting.ISkipCondition;
import com.hotent.core.util.ContextUtil;
import com.hotent.platform.model.system.SysUser;

/**
 * 相同执行人跳过。
 * @author ray
 */
public class SameUserSkipCondition implements ISkipCondition {

	@Override
	public boolean canSkip(Task task) {
		SysUser sysUser=ContextUtil.getCurrentUser();
		String assignee=task.getAssignee();
		String curUserId=sysUser.getUserId().toString();
		if(curUserId.equals(assignee)){
			return true;
		}
		return false;
	}

	@Override
	public String getTitle() {
		return "相同执行人跳过";
	}

}
