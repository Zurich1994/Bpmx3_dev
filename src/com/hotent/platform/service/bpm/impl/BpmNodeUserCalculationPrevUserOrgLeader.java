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
 * 根据节点用户设置为“上个任务执行人的直属领导(组织)”，计算执行人员。
 * 
 * @author Raise
 */
public class BpmNodeUserCalculationPrevUserOrgLeader implements IBpmNodeUserCalculation {
	@Resource
	private SysUserOrgService sysUserOrgService;
	
	@Resource
	private SysUserDao sysUserDao;

	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		Long prevExecUserId = vars.getPrevExecUserId();
		Long preOrgId=0L;
		if(vars.getVariable(BpmConst.PRE_ORG_ID)!=null){
			Object obj =vars.getVariable(BpmConst.PRE_ORG_ID);
			if(obj!=null){
				preOrgId=Long.parseLong( obj.toString());
			}
		}
		else{
			SysUserOrg userOrg= sysUserOrgService.getPrimaryByUserId(prevExecUserId);
			if(userOrg!=null){
				preOrgId=userOrg.getOrgId();
			}
		}
		//上个任务的组织。
		if(preOrgId==null || preOrgId.longValue()==0) return new ArrayList<SysUser>();
		
		List<SysUser> users = sysUserDao.getDirectLeaderByOrgId(preOrgId);
		return users;
	}

	public String getTitle() {
		//return "上个任务执行人的部门负责人";
		return "上个任务执行人的部门负责人";
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
