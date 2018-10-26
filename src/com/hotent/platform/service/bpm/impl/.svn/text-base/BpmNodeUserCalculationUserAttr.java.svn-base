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
import com.hotent.platform.service.system.SysUserService;

/**
 * 根据节点用户设置为“用户属性”，计算执行人员。
 * 
 * @author Raise
 */
@Deprecated
public class BpmNodeUserCalculationUserAttr implements IBpmNodeUserCalculation {
	@Resource
	private SysUserService sysUserService;

	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		List<SysUser> users = new ArrayList<SysUser>();
		try {
			users = sysUserService.getByUserParam(bpmNodeUser.getCmpIds());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public String getTitle() {
		return "用户属性";
	}

	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		int extractUser=bpmNodeUser.getExtractUser();
		List<SysUser> sysUsers= getExecutor(bpmNodeUser,vars);
		Set<TaskExecutor> uIdSet =BpmNodeUserUtil.getExcutorsByUsers(sysUsers, extractUser);
		return uIdSet;
	}

	public boolean supportMockModel() {
		
		return false;
	}

	public List<PreViewModel> getMockModel(BpmNodeUser bpmNodeUser) {
		
		return null;
	}

	public boolean supportPreView() {
		return true;
	}

	

}
