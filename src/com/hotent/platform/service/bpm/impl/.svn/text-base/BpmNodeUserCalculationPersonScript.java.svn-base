package com.hotent.platform.service.bpm.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation.PreViewModel;
import com.hotent.platform.service.bpm.thread.TaskThreadService;
import com.hotent.platform.service.system.SysUserService;

/**
 * 根据节点用户设置为“人员脚本”
 * 
 * @author Raise
 */
public class BpmNodeUserCalculationPersonScript implements IBpmNodeUserCalculation {
	@Resource
	private SysUserService sysUserService;
	@Resource
	private GroovyScriptEngine groovyScriptEngine;
	
	@Override
	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		Long prevUserId = vars.getPrevExecUserId();
		Long startUserId = vars.getStartUserId();
		
		String script = bpmNodeUser.getCmpIds();
		List<SysUser> users = new ArrayList<SysUser>();
		Map<String, Object> grooVars = new HashMap<String, Object>();
		
		String actInstId="";
		if(StringUtil.isNotEmpty(vars.getActInstId()) ){
			actInstId=vars.getActInstId();
		}
		grooVars.put("actInstId", actInstId);
		if(vars.getVars().size()>0){
			grooVars.putAll(vars.getVars());
		}
		
		
		grooVars.put(BpmConst.StartUser, startUserId);
		grooVars.put(BpmConst.PrevUser, prevUserId);
		Object result = groovyScriptEngine.executeObject(script, grooVars);
		if (result == null) {
			return users;
		}
		Set<String> set = (Set<String>) result;
		for (Iterator<String> it = set.iterator(); it.hasNext();) {
			String userId = it.next();
			SysUser sysUser = sysUserService.getById(Long.parseLong(userId));
			users.add(sysUser);
		}
		return users;
	}

	@Override
	public String getTitle() {
		return "人员脚本";
	}

	@Override
	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		int extractUser=bpmNodeUser.getExtractUser();
		
		List<SysUser> sysUsers = this.getExecutor(bpmNodeUser, vars);
		Set<TaskExecutor> uIdSet = BpmNodeUserUtil.getExcutorsByUsers(sysUsers, extractUser);
		return uIdSet;
	
	}

	@Override
	public boolean supportMockModel() {
		
		return true;
	}

	@Override
	public List<PreViewModel> getMockModel(BpmNodeUser bpmNodeUser) {
		List<PreViewModel> list=new ArrayList<PreViewModel>();
		
		PreViewModel startViewModel=new PreViewModel();
		startViewModel.setType(PreViewModel.START_USER);
		list.add(startViewModel);
		
		PreViewModel preViewModel=new PreViewModel();
		preViewModel.setType(PreViewModel.PRE_VIEW_USER);
		list.add(preViewModel);
		
		return list;
	}
	
	@Override
	public boolean supportPreView() {
		return true;
	}


}
