package com.hotent.platform.service.bpm.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysUserOrgService;

/**
 * 根据节点用户设置为“部门的上级类型部门的负责人”，计算执行人员。
 * 
 * 问题1:
 * 使用发起人，需要获取发起人发起流程时的组织，测试的时候是没有发起组织的，这个时候取模拟发起人的主组织。
 * 
 * 问题2:
 * 	计算方法，获取当前组织后，不使用递归查询，使用path 进行查询：
 * 	111,222,333,444.
 * 
 * @author Raise
 */
public class BpmNodeUserCalculationPrevTypeUserLeader implements
		IBpmNodeUserCalculation {
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private SysUserOrgService sysUserOrgService;

	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		Long startUserId = vars.getStartUserId();
		Long prevUserId = vars.getPrevExecUserId();
		String actInsId = vars.getActInstId();
		String startOrgId = vars.getStartOrgId();
		List<SysUser> users = new ArrayList<SysUser>();
		String expandParams = bpmNodeUser.getCmpIds();
		if (StringUtil.isEmpty(expandParams)) {
			return users;
		}
		JSONObject json = JSONObject.fromObject(expandParams);
		
		SysOrg sysOrg = null;
		if(StringUtils.isEmpty(actInsId)){//判断为模拟预览状态
			if("0".equals(json.get("orgSource"))){//发起人
				sysOrg = sysOrgService.getPrimaryOrgByUserId(startUserId);
			}
			else {//上一部执行人
				sysOrg = sysOrgService.getPrimaryOrgByUserId(prevUserId);
			}
		}
		else {//判断为流程运行时的状态
			if("0".equals(json.get("orgSource"))) {//发起人
				if(StringUtils.isEmpty(startOrgId)) return users;
				sysOrg = sysOrgService.getById(Long.valueOf(startOrgId));
			}
			else{//上一步执行人
				if (prevUserId.equals(ContextUtil.getCurrentUserId())) {
					sysOrg = ContextUtil.getCurrentOrg();
				} else {
					sysOrg = sysOrgService.getDefaultOrgByUserId(prevUserId);
				}
			}
		}
		
		if (sysOrg == null) {
			return users;
		}

		if ("0".equals(json.get("orgSource"))) { // 部门从发起人取
			users = getPrveTypeLeader(json.get("level").toString(), json.get("stategy").toString(), sysOrg);
		} else { // 部门从上一步执行人取
			users = getPrveTypeLeader(json.get("level").toString(),	json.get("stategy").toString(), sysOrg);
		}
		return users;
	}

	
	/**
	 * 获取部门的指定类型部门的负责人，一直获取下去直到最顶级为空或取得指定一类型的负责人。
	 * 
	 * @param level
	 * @param stategy
	 * @param sysOrg
	 * @return
	 */
	private List<SysUser> getPrveTypeLeader(String level,
			String stategy, SysOrg sysOrg) {
		String pathArr[] = sysOrg.getPath().split("\\.");
		int currentDepth = pathArr.length;
		//为什么减2 
		for(int len = currentDepth-1; len>0; len--) {
			Long orgId = Long.valueOf(pathArr[len]);
			SysOrg parentOrg = sysOrgService.getById(orgId);
			if(BeanUtils.isEmpty(parentOrg)) continue;
			if((parentOrg.getOrgType()).equals(Long.parseLong(level))){
				if("1".equals(stategy)){//根据查找策略stategy=1只查指定类型部门
					return sysUserOrgService.getLeaderUserByOrgId(parentOrg.getOrgId(), false);
				}
				else {//stategy=0指定类型为空继续往上查询部门负责人
					List<SysUser> list = sysUserOrgService.getLeaderUserByOrgId(parentOrg.getOrgId(), false);
					if(list.size()==0) continue;//如果为空，继续找
					else return list;
				}
			}
		}
		return new ArrayList<SysUser>();
	}

	public String getTitle() {
		return "部门的上级类型部门的负责人";
	}

	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser,
			CalcVars vars) {
		Set<TaskExecutor> uIdSet = new HashSet<TaskExecutor>();
		List<SysUser> sysUsers = this.getExecutor(bpmNodeUser, vars);
		for (SysUser sysUser : sysUsers) {
			uIdSet.add(TaskExecutor.getTaskUser(sysUser.getUserId().toString(),
					sysUser.getFullname()));
		}
		return uIdSet;
	}


	public boolean supportMockModel() {
		return true;
	}

	public List<PreViewModel> getMockModel(BpmNodeUser bpmNodeUser) {
		String cmpIds=bpmNodeUser.getCmpIds();
		JSONObject jsonObject=JSONObject.fromObject(cmpIds);
		String orgSource=jsonObject.getString("orgSource");
		List< PreViewModel> list=new ArrayList<PreViewModel>();
		if("0".equals(orgSource)) {
			PreViewModel preViewModelStartUser=new PreViewModel();
			preViewModelStartUser.setTitle("发起人");
			preViewModelStartUser.setType(PreViewModel.START_USER);
			list.add(preViewModelStartUser);
		}
		else {
			PreViewModel preViewModelPreViewUser=new PreViewModel();
			preViewModelPreViewUser.setTitle("上一步执行人");
			preViewModelPreViewUser.setType(PreViewModel.PRE_VIEW_USER);
			list.add(preViewModelPreViewUser);
		}
		return list;
	}

	public boolean supportPreView() {
		return true;
	}

}
