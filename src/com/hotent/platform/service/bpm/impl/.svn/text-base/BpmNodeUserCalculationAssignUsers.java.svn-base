package com.hotent.platform.service.bpm.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.core.model.TaskExecutor;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.AssignUsers;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.bpm.BpmUserCondition;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.AssignUsersService;
import com.hotent.platform.service.bpm.BpmUserConditionService;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;

/**
 * 根据节点用户设置为“开始节点设置人员”，计算执行人员。
 * 
 * @author Raise
 */
public class BpmNodeUserCalculationAssignUsers implements IBpmNodeUserCalculation {
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private BpmUserConditionService bpmUserConditionService;
	@Resource
	private AssignUsersService assignUsersService;

	@Override
	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		return null;
	}

	@Override
	public String getTitle() {
		//开始节点设置人员
		return "开始节点设置人员";
	}
	@Override
	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		Set<TaskExecutor> userIdSet = new LinkedHashSet<TaskExecutor>();
		Long runId = (Long) vars.getVariable("flowRunId");
		Long conditionId = bpmNodeUser.getConditionId();
		BpmUserCondition bpmUserCondition = bpmUserConditionService.getById(conditionId);
		String nodeId = bpmUserCondition.getNodeid();
		List<AssignUsers> assignUsersList=assignUsersService.getByRunIdAndNodeId(runId,nodeId);
		for (AssignUsers assignUsers : assignUsersList) {
			userIdSet.add(TaskExecutor.getTaskUser(assignUsers.getUserId().toString(),assignUsers.getUserName()));
		}
		return userIdSet;
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
		return false;
	}

	
}
