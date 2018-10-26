package com.hotent.platform.service.bpm.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.model.TaskExecutor;
import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;

/**
 * 根据节点用户设置为“与发起人相同部门”，计算执行人员。
 * <pre>
 * 测试OK ,从变量中获取发起人部门。
 * </pre>
 * 
 * @author Raise
 */
public class BpmNodeUserCalculationSameDepartment implements
		IBpmNodeUserCalculation {
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private SysOrgDao sysOrgDao;

	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		Object startOrgId=vars.getVariable(BpmConst.START_ORG_ID);
		if(startOrgId==null) return new ArrayList<SysUser>();
		Long orgId=Long.parseLong(startOrgId.toString());
		List<SysUser> users= sysUserDao.getByOrgId(orgId);
		return users;
	}

	public String getTitle() {
		//return "与发起人相同部门";
		return "与发起人相同部门";
	}
	
	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		int extractUser=bpmNodeUser.getExtractUser();
		
		Set<TaskExecutor> uIdSet = new LinkedHashSet<TaskExecutor>();
		Object startOrgId=vars.getVariable(BpmConst.START_ORG_ID);
		if(startOrgId==null) return uIdSet;
		Long orgId=(Long)startOrgId;
		SysOrg sysOrg= sysOrgDao.getById(orgId);
		
		switch (extractUser) {
			//不抽取
			case TaskExecutor.EXACT_NOEXACT:
				TaskExecutor executor=TaskExecutor.getTaskOrg(orgId.toString(), sysOrg.getOrgName());
				uIdSet.add(executor);
				break;
			//抽取
			case TaskExecutor.EXACT_EXACT_USER:
				List<SysUser> list= sysUserDao.getByOrgId(orgId);
				for(SysUser sysUser:list){
					TaskExecutor taskExcutor=TaskExecutor.getTaskUser(sysUser.getUserId().toString(), sysUser.getFullname());
					uIdSet.add(taskExcutor);
				}
				break;
			//二次抽取
			case TaskExecutor.EXACT_EXACT_SECOND:
				TaskExecutor taskexecutor=TaskExecutor.getTaskOrg(orgId.toString(), sysOrg.getOrgName());
				taskexecutor.setExactType(extractUser);
				uIdSet.add(taskexecutor);
				break;
		}
	
		return uIdSet;
	}

	public boolean supportMockModel() {
		
		return true;
	}

	public List< PreViewModel> getMockModel(BpmNodeUser bpmNodeUser) {
		List< PreViewModel> list=new ArrayList<PreViewModel>();
		
		PreViewModel preViewModel=new PreViewModel();
		preViewModel.setTitle("发起人部门");
		preViewModel.setType(PreViewModel.START_ORG);
		list.add(preViewModel);
		
		return list;
	}
	
	
	public boolean supportPreView() {
		return true;
	}

	
	
}
