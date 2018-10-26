package com.hotent.platform.service.bpm.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;

/**
 * 根据节点用户设置为“用户”，计算执行人员。
 * 
 * @author Raise
 */
public class BpmNodeUserCalculationUser implements IBpmNodeUserCalculation {
	@Resource
	private SysUserDao sysUserDao;

	@Override
	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		List<SysUser> sysUsers = new ArrayList<SysUser>();
		String uids = bpmNodeUser.getCmpIds();
		if (StringUtil.isEmpty(uids)) {
			return sysUsers;
		}
		String[] aryUid = uids.split("[,]");
		
		for(String userId : aryUid){
			SysUser user = sysUserDao.getById(Long.valueOf(userId));
			if(BeanUtils.isNotEmpty(user)){
				sysUsers.add(user);
			}
		}
		return sysUsers;
	}

	@Override
	public String getTitle() {
		//return "用户";
		return "用户";
	}
	@Override
	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		int extraceUser=bpmNodeUser.getExtractUser();
		List<SysUser> sysUsers = this.getExecutor(bpmNodeUser, vars);
		Set<TaskExecutor> uIdSet=BpmNodeUserUtil.getExcutorsByUsers(sysUsers, extraceUser);
		return uIdSet;
	}

	@Override
	public boolean supportMockModel() {
		
		return false;
	}

	@Override
	public List<PreViewModel> getMockModel(BpmNodeUser bpmNodeUser) {
	
		return null;
	}

	@Override
	public boolean supportPreView() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
	
}