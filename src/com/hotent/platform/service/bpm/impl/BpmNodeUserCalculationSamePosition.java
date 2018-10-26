package com.hotent.platform.service.bpm.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.model.TaskExecutor;
import com.hotent.platform.dao.system.PositionDao;
import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.dao.system.UserPositionDao;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;

/**
 * 根据节点用户设置为“与发起人相同岗位”，计算执行人员。
 * @author Raise
 */
public class BpmNodeUserCalculationSamePosition implements
		IBpmNodeUserCalculation {
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private UserPositionDao userPositionDao;
	@Resource
	private PositionDao positionDao;
	
	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		List<SysUser> users=new ArrayList<SysUser>();
		Object startPOsId=vars.getVariable(BpmConst.START_POS_ID);
		Object startUserId=vars.getVariable(BpmConst.StartUser);
		
		//得到相同岗位的用户
		if(startUserId!=null)
		users= sysUserDao.getSamePositionUsersByUserId(Long.valueOf(startUserId.toString()));
		
		//根据岗位得到用户
		if(startPOsId!=null)
		users=sysUserDao.getByPosId(Long.valueOf(startPOsId.toString()));
		
		if(startUserId==null&&startPOsId==null) return null;
		return users;
	}

	public String getTitle() {
		return "与发起人相同岗位";
	}
	
	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		int extractUser=bpmNodeUser.getExtractUser();
		
		Set<TaskExecutor> uIdSet = new LinkedHashSet<TaskExecutor>();
		Object startOrgId=vars.getVariable(BpmConst.START_ORG_ID);
		Object startUserId=vars.getVariable(BpmConst.StartUser);
		if(startUserId==null) return uIdSet;
	//得到用户的岗位
		List<Position> positionList=positionDao.getByUserId(Long.parseLong(startUserId.toString()));
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
				List<SysUser> list= sysUserDao.getSamePositionUsersByUserId(Long.valueOf(startUserId.toString()));
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
	
		return uIdSet;
	}

	public boolean supportMockModel() {
		
		return true;
	}

	public List< PreViewModel> getMockModel(BpmNodeUser bpmNodeUser) {
		List< PreViewModel> list=new ArrayList<PreViewModel>();
		
		PreViewModel preViewModel=new PreViewModel();
		preViewModel.setTitle("与发起人相同岗位");
		preViewModel.setType(PreViewModel.START_POS);
		list.add(preViewModel);
		
		return list;
	}
	
	
	public boolean supportPreView() {
		return true;
	}

	
	
}
