package com.hotent.platform.service.bpm.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.model.TaskExecutor;
import com.hotent.platform.dao.system.PositionDao;
import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.dao.system.SysRoleDao;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.CalcVars;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;
import com.hotent.platform.service.util.ServiceUtil;

/**
 * 根据表单变量计算人员。
 * <pre>
 * 人员的JSON格式如下：
 * {type:'user',varName:'name'},若为普通变量，则JSON格式为：{type:'user',userType:'userType',varName:'name'}
 * type:可能的值为
 * 	1,用户
 * 	2,组织
 * 	3,组织负责人
 * 	4,角色
 * 	5,岗位
 *  6,普通变量
 * </pre>
 * @author sfhq282
 *
 */
public class BpmNodeUserCalculationFormVar implements IBpmNodeUserCalculation {
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private SysOrgDao sysOrgDao;
	@Resource
	private SysRoleDao sysRoleDao;
	@Resource
	private PositionDao positionDao;
	
	
	public List<SysUser> getExecutor(BpmNodeUser bpmNodeUser, CalcVars vars) {
		List<SysUser> list=new ArrayList<SysUser>();
		String cmpIds=bpmNodeUser.getCmpIds();
		JSONObject jsonObject=JSONObject.fromObject(cmpIds);
		int type=jsonObject.getInt("type");
		if(type==6) {//普通变量
			type = jsonObject.getInt("userType");
		}
		Object ids = getFormVar(vars, jsonObject, type);
		if(ids==null) return list;
		String executorIds=ids.toString();
		
		switch(type){
			//用户
			case 1:
				list= getByUsers(executorIds);
				break;
			//组织
			case 2:
				list=getByOrgIds(executorIds);
				break;
			//组织负责人
			case 3:
				list=getMgrByOrgIds(executorIds);
				break;
			//角色
			case 4:
				list= getByRoleIds(executorIds);
				break;
			//岗位
			case 5:
				list= getByPos(executorIds);
				break;
		}
		return list;
	}
	
	
	

	public Set<TaskExecutor> getTaskExecutor(BpmNodeUser bpmNodeUser,CalcVars vars) {
		int extractUser=bpmNodeUser.getExtractUser().intValue();
		
		Set<TaskExecutor> userSet=new LinkedHashSet<TaskExecutor>();
		
		String cmpIds=bpmNodeUser.getCmpIds();
		JSONObject jsonObject=JSONObject.fromObject(cmpIds);
		Object ids = null;
		int type=jsonObject.getInt("type");
		if(type==6) {//普通变量
			type = jsonObject.getInt("userType");
			String varName=jsonObject.getString("varName");
			ids = vars.getVariable(varName);
		}
		else {
			ids = getFormVar(vars, jsonObject, type);
		}
		if(ids==null) return userSet;
		String executorIds=ids.toString();
		
		switch(type){
			//用户
			case 1:
				List<SysUser> userList= getByUsers(executorIds);
				userSet=BpmNodeUserUtil.getExcutorsByUsers(userList, extractUser);
				break;
			//组织
			case 2:
				switch (extractUser) {
					//不抽取
					case TaskExecutor.EXACT_NOEXACT :
						userSet =getTaskExecutor(2,executorIds,extractUser);
						break;
					//抽取
					case TaskExecutor.EXACT_EXACT_USER:
						List<SysUser> orgUserList=getByOrgIds(executorIds);
						userSet=getTaskExecutorByUserList(orgUserList);
						break;
					//二级抽取
					case TaskExecutor.EXACT_EXACT_SECOND:
						userSet =getTaskExecutor(2,executorIds,extractUser);
						break;
				}
				
				break;
			//组织负责人
			case 3:
				List<SysUser> orgUserList=getMgrByOrgIds(executorIds);
				userSet=getTaskExecutorByUserList(orgUserList);
				break;
			//角色
			case 4:
				switch (extractUser) {
					//不抽取
					case TaskExecutor.EXACT_NOEXACT :
						userSet =getTaskExecutor(4,executorIds,extractUser);
						break;
					//抽取
					case TaskExecutor.EXACT_EXACT_USER:
						List<SysUser> roleUserList=getByRoleIds(executorIds);
						userSet=getTaskExecutorByUserList(roleUserList);
						break;
					//二级抽取
					case TaskExecutor.EXACT_EXACT_SECOND:
						userSet =getTaskExecutor(4,executorIds,extractUser);
						break;
				}
				break;
			//岗位
			case 5:
				switch (extractUser) {
					//不抽取
					case TaskExecutor.EXACT_NOEXACT :
						userSet =getTaskExecutor(5,executorIds,extractUser);
						break;
					//抽取
					case TaskExecutor.EXACT_EXACT_USER:
						List<SysUser> posUserList=getByPos(executorIds);
						userSet=getTaskExecutorByUserList(posUserList);
						break;
					//二级抽取
					case TaskExecutor.EXACT_EXACT_SECOND:
						userSet =getTaskExecutor(5,executorIds,extractUser);
						break;
				}
				break;
			
		}
		return userSet;
	}



	private Object getFormVar(CalcVars vars, JSONObject jsonObject, int type) {
		String varName=jsonObject.getString("varName") +BpmFormField.FIELD_HIDDEN;
		Object ids=vars.getVariable(varName);
		if(ids!=null) return ids;
		//获取预览参数
		switch(type){
			//用户
			case 1:
				varName=BpmConst. PREVIEW_FORMUSER;
				break;
			//组织
			case 2:
			case 3:
				varName=BpmConst.PREVIEW_FORMORG;
				break;
			//角色
			case 4:
				varName=BpmConst.PREVIEW_FORMROLE;
				break;
			//岗位
			case 5:
				varName=BpmConst.PREVIEW_FORMPOS;
				break;
		}
		ids=vars.getVariable(varName);

		return ids;
	}
	
	

	public String getTitle() {
		//人员表单变量
		return "人员表单变量";
	}

	public boolean supportMockModel() {
		
		return true;
	}

	public List<PreViewModel> getMockModel(BpmNodeUser bpmNodeUser) {
		String cmpIds=bpmNodeUser.getCmpIds();
		JSONObject jsonObject=JSONObject.fromObject(cmpIds);
		int type=jsonObject.getInt("type");
		if(type==6){//普通变量
			type = jsonObject.getInt("userType");
		}
		List<PreViewModel> list=new ArrayList<PreViewModel>();
		switch (type) {
			case 1:
				PreViewModel userVar=new PreViewModel();
				userVar.setType(PreViewModel.FORM_USER);
				list.add(userVar);
				break;
			case 2:
			case 3:
				PreViewModel orgVar=new PreViewModel();
				orgVar.setType(PreViewModel.FORM_ORG);
				list.add(orgVar);
				break;
			case 4:
				PreViewModel roleVar=new PreViewModel();
				roleVar.setType(PreViewModel.FORM_ROLE);
				list.add(roleVar);
				break;
			case 5:
				PreViewModel posVar=new PreViewModel();
				posVar.setType(PreViewModel.FORM_POS);
				list.add(posVar);
				break;
			case 7:
				PreViewModel jobVar=new PreViewModel();
				jobVar.setType(PreViewModel.FORM_JOB);
				list.add(jobVar);
				break;	
		}
		return list;
	}

	public boolean supportPreView() {
		return true;
	}
	
	private List<SysUser> getByUsers(String uids){
		String[] aryUid = uids.split("[,]");
//		Set<String> uidSet = new LinkedHashSet<String>(Arrays.asList(aryUid));
//		return sysUserDao.getByIdSet(uidSet);
		List<SysUser> sysUsers = new ArrayList<SysUser>();
		for(String userId : aryUid){
			sysUsers.add(sysUserDao.getById(Long.valueOf(userId)));
		}
		return sysUsers;
	}
	/**
	 * 根据组织ID获取人员。
	 * @param ids
	 * @return
	 */
	private List<SysUser> getByOrgIds(String ids){
		List<Long> list =  ServiceUtil.getListByStr(ids);
		return sysUserDao.getByOrgIds(list);
	}
	
	/**
	 * 根据组织id获取组织负责人。
	 * @param ids
	 * @return
	 */
	private List<SysUser> getMgrByOrgIds(String ids){
		List<Long> list = ServiceUtil. getListByStr(ids);
		return sysUserDao.getMgrByOrgIds(list);
	}
	
	/**
	 * 根据角色Id获取用户列表。
	 * @param ids
	 * @return
	 */
	private List<SysUser> getByRoleIds(String ids){
		List<Long> list = ServiceUtil. getListByStr(ids);
		return sysUserDao.getByRoleIds(list);
	}

	private List<SysUser> getByPos(String ids){
		List<Long> list = ServiceUtil. getListByStr(ids);
		return sysUserDao.getByPos(list);
	}
	
	/**
	 * 根据用户列表获取执行人。
	 * @param list
	 * @return
	 */
	private Set<TaskExecutor> getTaskExecutorByUserList(List<SysUser> list){
		
		Set<TaskExecutor> set=new  LinkedHashSet<TaskExecutor>();
		
		for(SysUser sysUser:list){
			TaskExecutor taskExecutor=TaskExecutor.getTaskUser(sysUser.getUserId().toString(),sysUser.getFullname()) ;
			set.add(taskExecutor);
		}
		return set;
	}
	
	
	
	
	/**
	 * 取得任务执行人。
	 * @param type			执行人类型(2,组织,4,角色,5,岗位)
	 * @param cmpIds		id数据。
	 * @param extractType	抽取类型。
	 * @return
	 */
	private Set<TaskExecutor> getTaskExecutor(int type,String cmpIds,int extractType){
		Set<TaskExecutor> set=new  LinkedHashSet<TaskExecutor>();
		String[] aryIds = cmpIds.split("[,]");
		TaskExecutor taskExecutor=null;
		//2 组织,4角色,5 岗位
		for (int i = 0; i < aryIds.length; i++) {
			if(type==2){
				SysOrg org=sysOrgDao.getById(new Long(aryIds[i]));
				taskExecutor=TaskExecutor.getTaskOrg(aryIds[i],org.getOrgName()) ;
			}
			else if(type==4){
				SysRole role=sysRoleDao.getById(new Long(aryIds[i]));
				taskExecutor=TaskExecutor.getTaskRole(aryIds[i],role.getRoleName()) ;
			}
			else if(type==5){
				Position pos=positionDao.getById(new Long(aryIds[i]));
				taskExecutor=TaskExecutor.getTaskPos(aryIds[i],pos.getPosName()) ;
			}
			taskExecutor.setExactType(extractType);
			set.add(taskExecutor);
		}
		return set;
	}

	
}