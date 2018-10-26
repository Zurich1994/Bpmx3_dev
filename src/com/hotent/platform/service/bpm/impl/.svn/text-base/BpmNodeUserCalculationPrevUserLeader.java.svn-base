package com.hotent.platform.service.bpm.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.core.model.TaskExecutor;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;
import com.hotent.platform.service.system.UserUnderService;

/**
 * 根据节点用户设置为“上个任务执行人的领导”，计算执行人员。
 * 
 * @author Raise
 */
@Deprecated
public class BpmNodeUserCalculationPrevUserLeader implements
		IBpmNodeUserCalculation {
	@Resource
	UserUnderService userUnderService;

	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		Long prevUserId = vars.getPrevExecUserId();
		List<SysUser> users = userUnderService.getMyLeaders(prevUserId);
		return users;
	}

	public String getTitle() {
		return "上个任务执行人的领导";
	}

	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		Set<TaskExecutor> uIdSet = new LinkedHashSet<TaskExecutor>();
		List<SysUser> sysUsers = this.getExecutor(bpmNodeUser, vars);
		for (SysUser sysUser : sysUsers) {
			uIdSet.add(TaskExecutor.getTaskUser(sysUser.getUserId().toString(),sysUser.getFullname()));
		}
		return uIdSet;
	}

	public boolean supportMockModel() {
		
		return true;
	}

	public List< PreViewModel> getMockModel(BpmNodeUser bpmNodeUser) {
		List< PreViewModel> list=new ArrayList<PreViewModel>();
		PreViewModel preViewModel=new PreViewModel();
		preViewModel.setType(PreViewModel.PRE_VIEW_USER);
		
		list.add(preViewModel);
		return list;
		
		
	}
	
	public boolean supportPreView() {
		return true;
	}

}
