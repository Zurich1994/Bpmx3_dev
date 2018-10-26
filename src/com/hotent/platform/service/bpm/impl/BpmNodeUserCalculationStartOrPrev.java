package com.hotent.platform.service.bpm.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import net.sf.json.JSONObject;
import com.hotent.core.model.TaskExecutor;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SysUserOrg;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;
import com.hotent.platform.service.system.SysUserOrgService;
import com.hotent.platform.service.system.UserUnderService;

/**
 * 根据发起人或上一任务执行人计算人员。
 * <pre>
 * 人员的JSON格式如下：
 * {type:'director',userType:'start',varName:'name'}
 * type:可能的值为
 * 	director,部门负责人
 * 	leader,领导
 * userType:可能的值为 start、prev
 * </pre>
 * @author hjx
 *
 */
public class BpmNodeUserCalculationStartOrPrev implements IBpmNodeUserCalculation {
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private SysUserOrgService sysUserOrgService;
	@Resource
	private UserUnderService userUnderService;
	
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
        
		if ("director".equals(type)){//负责人
			
			SysUserOrg userOrg= sysUserOrgService.getPrimaryByUserId(userId);
			if(userOrg!=null){
				orgId=userOrg.getOrgId();
			}
			sysUserList=sysUserDao.getDirectLeaderByOrgId(orgId);
		}
		if ("leader".equals(type)){//领导
			sysUserList = userUnderService.getMyLeaders(userId);
		}
	
		return sysUserList;
	}
	
	
	

	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser,CalcVars vars) {
		Set<TaskExecutor> userSet = new LinkedHashSet<TaskExecutor>();
		List<SysUser> sysUsers = this.getExecutor(bpmNodeUser, vars);
		for (SysUser sysUser : sysUsers) {
			userSet.add(TaskExecutor.getTaskUser(sysUser.getUserId().toString(),sysUser.getFullname()));
		}
		return userSet;
	}

	

	public String getTitle() {
		return "发起人或上一任务执行人的领导或负责人";
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