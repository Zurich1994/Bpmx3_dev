package com.hotent.platform.service.bpm.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.model.TaskExecutor;
import com.hotent.platform.dao.system.JobDao;
import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.Job;
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
public class BpmNodeUserCalculationSameJob implements
		IBpmNodeUserCalculation {
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private JobDao jobDao;

	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		List<SysUser> users=new ArrayList<SysUser>();
		Object startJobId=vars.getVariable(BpmConst.START_JOB_ID);
		Object startUserId=vars.getVariable(BpmConst.StartUser);
		
		//得到相同职务的用户
		if(startUserId!=null)
		users= sysUserDao.getSameJobUsersByUserId(Long.valueOf(startUserId.toString()));
		
		//根据岗位得到用户
		if(startJobId!=null)
		users=sysUserDao.getByJobId(Long.valueOf(startJobId.toString()));
		
		if(startUserId==null&&startJobId==null) return null;
		return users;

	}

	public String getTitle() {
		return "与发起人相同职务";
	}
	
	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		int extractUser=bpmNodeUser.getExtractUser();
		
		Set<TaskExecutor> uIdSet = new LinkedHashSet<TaskExecutor>();
		Object startOrgId=vars.getVariable(BpmConst.START_ORG_ID);
		Object startUserId=vars.getVariable(BpmConst.StartUser);
		if(startUserId==null) return uIdSet;
	   //得到用户的职务
		List<Job> jobList=jobDao.getByUserId(Long.parseLong(startUserId.toString()));
		switch (extractUser) {
			//不抽取
			case TaskExecutor.EXACT_NOEXACT:
				for(Job job:jobList){
				TaskExecutor executor=TaskExecutor.getTaskJob(job.getJobid().toString(), job.getJobname());
				uIdSet.add(executor);
				}
				break;
			//抽取
			case TaskExecutor.EXACT_EXACT_USER:
				//得到相同职务的用户
				List<SysUser> list= sysUserDao.getSamePositionUsersByUserId(Long.valueOf(startUserId.toString()));
				for(SysUser sysUser:list){
					TaskExecutor taskExcutor=TaskExecutor.getTaskUser(sysUser.getUserId().toString(), sysUser.getFullname());
					uIdSet.add(taskExcutor);
				}
				break;
			//二次抽取
			case TaskExecutor.EXACT_EXACT_SECOND:
				for(Job job:jobList){
				TaskExecutor taskexecutor=TaskExecutor.getTaskOrg(job.getJobid().toString(), job.getJobname());
				taskexecutor.setExactType(extractUser);
				uIdSet.add(taskexecutor);
				}
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
		preViewModel.setTitle("发起人相同职务");
		preViewModel.setType(PreViewModel.START_JOB);
		list.add(preViewModel);
		
		return list;
	}
	
	
	public boolean supportPreView() {
		return true;
	}	
}
