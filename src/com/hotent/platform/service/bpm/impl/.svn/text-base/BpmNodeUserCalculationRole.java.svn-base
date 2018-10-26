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
import com.hotent.platform.model.system.UserRole;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;
import com.hotent.platform.service.system.UserRoleService;
import com.hotent.platform.service.util.ServiceUtil;

/**
 * 根据节点用户设置为“角色”，计算执行人员。
 * 
 * @author Raise
 */
public class BpmNodeUserCalculationRole implements IBpmNodeUserCalculation {
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private SysUserDao sysUserDao;

	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		List<SysUser> users = new ArrayList<SysUser>();
		String roleIds = bpmNodeUser.getCmpIds();
		if (StringUtil.isEmpty(roleIds)) {
			return users;
		}
		List<Long> list =  ServiceUtil.getListByStr(roleIds);
		return sysUserDao.getByRoleIds(list);
	}

	public String getTitle() {
		//return "角色";
		return "角色";
	}

	
	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		int extractUser=bpmNodeUser.getExtractUser();
		Set<TaskExecutor> userIdSet = new LinkedHashSet<TaskExecutor>();
		String uids=bpmNodeUser.getCmpIds();
		if(StringUtil.isEmpty(uids)){
			return userIdSet;
		}
		switch (extractUser) {
			case TaskExecutor.EXACT_NOEXACT:
				String[] roleIds = bpmNodeUser.getCmpIds().split("[,]");
				String[] roleNames = bpmNodeUser.getCmpNames().split("[,]");
				for (int i = 0; i < roleIds.length; i++) {
					TaskExecutor taskExecutor=TaskExecutor.getTaskRole(roleIds[i],roleNames[i]) ;
					userIdSet.add(taskExecutor);
				}
				break;
			case TaskExecutor.EXACT_EXACT_USER:
				List<UserRole> userList = userRoleService.getUserByRoleIds(bpmNodeUser.getCmpIds());
				for (UserRole user : userList) {
					TaskExecutor taskExecutor=TaskExecutor.getTaskUser(user.getUserId().toString(),user.getFullname()) ;
					userIdSet.add(taskExecutor);
				}
				break;
			case TaskExecutor.EXACT_EXACT_SECOND:
				String[] aryRoleIds = bpmNodeUser.getCmpIds().split("[,]");
				String[] aryRoleNames = bpmNodeUser.getCmpNames().split("[,]");
				for (int i = 0; i < aryRoleIds.length; i++) {
					TaskExecutor taskExecutor=TaskExecutor.getTaskRole(aryRoleIds[i],aryRoleNames[i]) ;
					taskExecutor.setExactType(extractUser);
					userIdSet.add(taskExecutor);
				}
				break;
			
		}
		return userIdSet;
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
