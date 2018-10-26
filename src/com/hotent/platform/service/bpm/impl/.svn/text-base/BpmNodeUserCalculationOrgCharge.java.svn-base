package com.hotent.platform.service.bpm.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;
import com.hotent.platform.service.util.ServiceUtil;

/**
 * 根据节点用户设置为“组织负责人”，计算执行人员。
 * <pre>
 * 测试OK RAY
 * </pre>
 * @author Raise
 */
public class BpmNodeUserCalculationOrgCharge implements IBpmNodeUserCalculation {
	@Resource
	private SysUserDao sysUserDao;

	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		List<SysUser> users = new ArrayList<SysUser>();

		String uids = bpmNodeUser.getCmpIds();
		if (StringUtil.isEmpty(uids)) {
			return users;
		}
		List<Long> list =  ServiceUtil.getListByStr(uids);
		List<SysUser> userList= sysUserDao.getMgrByOrgIds(list);
		return userList;
	}

	public String getTitle() {
		//return "组织负责人";
		return "组织负责人";
	}

	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		int extraceUser=bpmNodeUser.getExtractUser();
		List<SysUser> sysUsers = this.getExecutor(bpmNodeUser, vars);
		Set<TaskExecutor> uIdSet=BpmNodeUserUtil.getExcutorsByUsers(sysUsers, extraceUser);
		return uIdSet;
	}

	public boolean supportMockModel() {
		return false;
	}

	public List< PreViewModel> getMockModel(BpmNodeUser bpmNodeUser) {
		
		return null;
	}
	
	public boolean supportPreView() {
		return true;
	}

}
