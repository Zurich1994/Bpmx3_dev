package com.hotent.platform.service.bpm.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hotent.core.model.TaskExecutor;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.bpm.BpmNodeUserUplow;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;
import com.hotent.platform.service.system.SysUserService;

/**
 * 根据节点用户设置为“上下级”，计算执行人员。
 * 
 * @author Raise
 */
public class BpmNodeUserCalculationUpLow implements IBpmNodeUserCalculation {
	@Resource
	private SysUserService sysUserService;

	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		Long userId = 0L;//发起人或上一任务发起人
		String cmpIds=bpmNodeUser.getCmpIds();
		//JSONObject jsonObject=JSONObject.fromObject(cmpIds);
		//必须用JSONArray来转，用JSONObject会出现以下错误信息：  
        // A JSONObject text must begin with '{' at character 1 of
		
		JSONArray jsonArray=  JSONArray.fromObject(cmpIds);
		JSONObject jsonObject=jsonArray.getJSONObject(0);
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
        
		List<SysUser> users = new ArrayList<SysUser>();
		users = sysUserService.getByUserIdAndUplow(userId, bpmNodeUser);
		return users;
	}

	public String getTitle() {
		return "上下级";
	}

	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		int extractUser=bpmNodeUser.getExtractUser();
		List<SysUser> sysUsers = this.getExecutor(bpmNodeUser, vars);
		Set<TaskExecutor> uIdSet =BpmNodeUserUtil.getExcutorsByUsers(sysUsers, extractUser);
		return uIdSet;
	}

	public boolean supportMockModel() {
		return true;
	}

	public List<PreViewModel> getMockModel(BpmNodeUser bpmNodeUser) {
		List< PreViewModel> list=new ArrayList<PreViewModel>();
		PreViewModel preViewModel=new PreViewModel();
		preViewModel.setType(PreViewModel.START_USER);
		
		list.add(preViewModel);
		
		return list;
	}

	public boolean supportPreView() {
		return true;
	}
}
