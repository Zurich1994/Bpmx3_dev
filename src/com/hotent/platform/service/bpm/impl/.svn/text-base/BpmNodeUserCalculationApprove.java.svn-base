package com.hotent.platform.service.bpm.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.core.model.TaskExecutor;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;

/**
 * 当前流程实例审批人。
 * @author ray
 *
 */
public class BpmNodeUserCalculationApprove implements IBpmNodeUserCalculation {

	@Resource
	private SysUserDao sysUserDao;
	
	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		String actInstId= vars.getActInstId();
		
		List<SysUser> list= sysUserDao.getExeUserByInstnceId(Long.parseLong(actInstId));
		
		return list;
	}

	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser,
			CalcVars vars) {
		int extraceUser=bpmNodeUser.getExtractUser();
		
		List<SysUser> list= getExecutor(bpmNodeUser, vars);
		Set<TaskExecutor> set=BpmNodeUserUtil.getExcutorsByUsers(list, extraceUser);
		return set;
	}

	public String getTitle() {
		//当前流程实例审批人
		return "当前流程实例审批人";
	}

	public boolean supportMockModel() {
		
		return false;
	}

	public List<PreViewModel> getMockModel(BpmNodeUser bpmNodeUser) {
		
		return null;
	}

	public boolean supportPreView() {
	
		return false;
	}

	

	

	
}
