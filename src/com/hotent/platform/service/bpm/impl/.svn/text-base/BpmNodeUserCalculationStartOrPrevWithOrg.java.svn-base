package com.hotent.platform.service.bpm.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import net.sf.json.JSONObject;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.dao.system.JobDao;
import com.hotent.platform.dao.system.PositionDao;
import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.Job;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SysUserOrg;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;
import com.hotent.platform.service.system.SysUserOrgService;

/**
 * 根据发起人或上一任务执行人的组织（包括组织、岗位、职务）计算人员。
 * <pre>
 * 人员的JSON格式如下：
 * {type:'org',userType:'start',varName:'name'}
 * type:可能的值为：
 * org 组织
 * pos 岗位
 * job 职务
 * userType:可能的值为 start、prev
 * </pre>
 * @author hjx
 *
 */
public class BpmNodeUserCalculationStartOrPrevWithOrg implements IBpmNodeUserCalculation {
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private SysOrgDao sysOrgDao;
	@Resource
	private PositionDao positionDao;
	@Resource
	private JobDao jobDao;
	@Resource
	private SysUserOrgService sysUserOrgService;
	
	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		Long userId = 0L;//发起人或上一任务发起人
		Long orgId=0L;
		List<SysUser> sysUserList=new ArrayList<SysUser>();
		String cmpIds=bpmNodeUser.getCmpIds();
		JSONObject jsonObject=JSONObject.fromObject(cmpIds);
		String type=jsonObject.getString("type");
		String userType=jsonObject.getString("userType");
		
		if("start".equals(userType)){//发起人
			userId=vars.getStartUserId();
		}
        if("prev".equals(userType)){//上一任务执行人
        	userId=vars.getPrevExecUserId();
        	if(userId==null||userId==0L)userId=vars.getStartUserId();
		}
        if(userId==null  || userId.intValue()==0) 
        	return new ArrayList<SysUser>();
        
		//组织
        if("org".equals(type)){
        	SysUserOrg userOrg= sysUserOrgService.getPrimaryByUserId(userId);
			if(userOrg!=null){
				orgId=userOrg.getOrgId();
			}
			sysUserList= sysUserDao.getByOrgId(orgId);
        }
        //职务
		 if("job".equals(type)){
			 sysUserList=sysUserDao.getSameJobUsersByUserId(userId);
		        }
		 //岗位
		 if("pos".equals(type)){
			 sysUserList=sysUserDao.getSamePositionUsersByUserId(userId);
		 }
		return sysUserList;
	}
	
	
	

	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser,CalcVars vars) {
		Long userId = 0L;//发起人或上一任务发起人
	
		String cmpIds=bpmNodeUser.getCmpIds();
		JSONObject jsonObject=JSONObject.fromObject(cmpIds);
		String type=jsonObject.getString("type");
		String userType=jsonObject.getString("userType");
		
		int extractUser=bpmNodeUser.getExtractUser();
		Set<TaskExecutor> uIdSet = new LinkedHashSet<TaskExecutor>();
		
		if("start".equals(userType)){//发起人
			userId=vars.getStartUserId();
		}
        if("prev".equals(userType)){//上一任务执行人
        	userId=vars.getPrevExecUserId();
		}
        if(userId==null  || userId.intValue()==0) 
        	return uIdSet;
        
		//组织
        if("org".equals(type)){
        	handOrg(userId, extractUser, uIdSet);
        }
        //职务
		 if("job".equals(type)){
			 //得到用户的职务
			handJob(userId, extractUser, uIdSet);
		 }
		 //岗位
		 if("pos".equals(type)){
			//得到用户的岗位
			handPos(userId, extractUser, uIdSet);
		 }
	
		return uIdSet;
	}

	private void handPos(Long userId, int extractUser, Set<TaskExecutor> uIdSet) {
		List<Position> positionList=positionDao.getByUserId(userId);
		if(BeanUtils.isEmpty(positionList)) return;
		
		switch (extractUser) {
			//不抽取
			case TaskExecutor.EXACT_NOEXACT:
				for(Position position:positionList){
				TaskExecutor executor=TaskExecutor.getTaskPos(position.getPosId().toString(), position.getPosName());
				uIdSet.add(executor);
				}
				break;
			//抽取
			case TaskExecutor.EXACT_EXACT_USER:
				//得到相同岗位的用户
				List<SysUser> list= sysUserDao.getSamePositionUsersByUserId(userId);
				for(SysUser sysUser:list){
					TaskExecutor taskExcutor=TaskExecutor.getTaskUser(sysUser.getUserId().toString(), sysUser.getFullname());
					uIdSet.add(taskExcutor);
				}
				break;
			//二次抽取
			case TaskExecutor.EXACT_EXACT_SECOND:
				for(Position position:positionList){
				TaskExecutor taskexecutor=TaskExecutor.getTaskOrg(position.getPosId().toString(), position.getPosName());
				taskexecutor.setExactType(extractUser);
				uIdSet.add(taskexecutor);
				}
				break;
		}

	}




	private void handJob(Long userId, int extractUser, Set<TaskExecutor> uIdSet) {
		List<Job> jobList=jobDao.getByUserId(userId);
		if(BeanUtils.isEmpty(jobList)) return;
		
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
				List<SysUser> list= sysUserDao.getSamePositionUsersByUserId(userId);
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
	}



	
	private void handOrg(Long userId, int extractUser, Set<TaskExecutor> uIdSet) {
		SysUserOrg userOrg= sysUserOrgService.getPrimaryByUserId(userId);
		if(userOrg==null) return;
		Long orgId=userOrg.getOrgId();
	
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
		
	}

	

	public String getTitle() {
		return "与发起人或上一任务执行人具有相同组织的人";
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