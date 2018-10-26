package com.hotent.platform.service.bpm.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.model.TaskExecutor;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SysUserOrg;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;
import com.hotent.platform.service.system.SysUserOrgService;

/**
 * 根据节点用户设置为“发起人的直属领导(组织)”，计算执行人员。
 * 
 * @author Raise
 */
public class BpmNodeUserCalculationDirectLeader implements
		IBpmNodeUserCalculation {
	@Resource
	private SysUserOrgService sysUserOrgService;
	
	@Resource
	private SysUserDao sysUserDao;
	

	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		Long startUserId = vars.getStartUserId();
		//获取发起人的组织。
		Long startOrgId=0L;
		if(vars.getVariable(BpmConst.START_ORG_ID)!=null){
			Object obj =vars.getVariable(BpmConst.START_ORG_ID);
			if(obj!=null){
				startOrgId=Long.parseLong( obj.toString());
			}
		}
		else{
			SysUserOrg userOrg= sysUserOrgService.getPrimaryByUserId(startUserId);
			if(userOrg!=null){
				startOrgId=userOrg.getOrgId();
			}
		}
		
		if(startOrgId==null ||  startOrgId.longValue()==0) return new ArrayList<SysUser>();
		
		List<SysUser> list=sysUserDao.getDirectLeaderByOrgId(startOrgId);
		return list;
	}

	public String getTitle() {
		//发起人的部门负责人
		return "发起人的部门负责人";
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

	public List<PreViewModel> getMockModel(BpmNodeUser bpmNodeUser) {
		List<PreViewModel> list=new ArrayList<PreViewModel>();
		PreViewModel preViewModel=new PreViewModel();
		preViewModel.setType(PreViewModel.START_USER);
		list.add(preViewModel);
			
		return list;
	}
	
	public boolean supportPreView() {
		return true;
	}


}
