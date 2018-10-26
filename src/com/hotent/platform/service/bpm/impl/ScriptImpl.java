package com.hotent.platform.service.bpm.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.engine.IScript;
import com.hotent.core.exception.BusDataException;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.MapUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.TimeUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.HistoryTaskInstanceDao;
import com.hotent.platform.dao.form.BpmFormHandlerDao;
import com.hotent.platform.dao.system.JobDao;
import com.hotent.platform.dao.system.PositionDao;
import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.dao.system.SysOrgTypeDao;
import com.hotent.platform.dao.system.SysRoleDao;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.dao.system.SysUserParamDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.dao.system.UserPositionDao;
import com.hotent.platform.model.form.BpmFormData;
import com.hotent.platform.model.system.Demension;
import com.hotent.platform.model.system.Job;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysOrgParam;
import com.hotent.platform.model.system.SysOrgType;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SysUserParam;
import com.hotent.platform.model.system.UserPosition;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.TaskOpinionService;
import com.hotent.platform.service.bpm.thread.TaskThreadService;
import com.hotent.platform.service.bpm.thread.TaskUserAssignService;
import com.hotent.platform.service.system.IdentityService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysUserOrgService;
import com.hotent.platform.service.system.SysOrgParamService;
import com.hotent.platform.service.system.SysUserParamService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.system.UserUnderService;

/**
 * 实现这个接口可以在groovy脚本中直接使用。
 * 
 * @author ray。
 * 
 */
public class ScriptImpl implements IScript {
	
	private static final Log logger = LogFactory.getLog(Dom4jUtil.class);
	@Resource
	TaskOpinionService taskOpinionService;
	@Resource
	private SysRoleDao sysRoleDao;
	@Resource
	private SysUserOrgService sysUserOrgService;
	@Resource
	private SysOrgDao sysOrgDao;
	@Resource
	private PositionDao positionDao;
	@Resource
	private UserUnderService userUnderService;
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Resource
	private SysUserService sysUserService;

	@Resource
	private SysOrgTypeDao sysOrgTypeDao;

	@Resource
	private SysUserParamDao sysUserParamDao;

	@Resource
	private ProcessRunService processRunService;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private BpmFormHandlerDao bpmFormHandlerDao;
	@Resource
	private JobDao jobDao;
	@Resource
	private TaskUserAssignService taskUserAssignService;//add by yangxiao 
	
	@Resource
	private IdentityService is;//written by sxh

	@Resource
	BpmDefinitionService bpmDefinitionService;
	UserPositionDao userPositionDao;
	@Resource
	BpmService bpmService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private SysOrgParamService sysOrgParamService;
	@Resource
	private SysUserParamService sysUserParamService;
	@Resource
	private HistoryTaskInstanceDao historyTaskInstanceDao;
	
	/**
	 * 取得当前登录用户工号 。<br>
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getAccount() 
	 * </pre>
	 * @return
	 */
	public String getAccount(){
		SysUser sysUser=ContextUtil.getCurrentUser();
		if(sysUser==null) return "";
		return sysUser.getAccount();
	}
	/**
	 * 取得当前登录用户id 。<br>
	 * 
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getCurrentUserId()
	 * </pre>
	 * 
	 * @return
	 */
	public long getCurrentUserId() {
		long userId= ContextUtil.getCurrentUserId();
		return userId;
	}
	
	

	/**
	 * 取得当前登录用户名称 。<br>
	 * 
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getCurrentName()
	 * </pre>
	 * 
	 * @return
	 */
	public String getCurrentName() {
		SysUser sysUser=ContextUtil.getCurrentUser();
		if(sysUser==null) return "";
		return sysUser.getFullname();
	}

	/**
	 * 获取当前系统的用户。
	 * 
	 * <pre>
	 * 脚本中使用方法: scriptImpl.getCurrentUser();
	 * </pre>
	 * 
	 * @return 用户对象。
	 */
	public SysUser getCurrentUser() {
		return ContextUtil.getCurrentUser();
	}

	/**
	 * 获取当前日期。
	 * 
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getCurrentDate();
	 * 返回日期类型如下：
	 * 2002-11-06
	 * </pre>
	 * 
	 * @return
	 */
	public String getCurrentDate() {
		return TimeUtil.getCurrentDate();
	}
	/**
	 * 获取当前日期。指定格式输出
	 * 
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getCurrentDate("yyyy-MM-dd HH:mm:ss");
	 * 返回日期类型如下：
	 * 2002-11-06
	 * </pre>
	 * 
	 * @return
	 */
	public String getCurrentDate(String style) {
		if(StringUtils.isEmpty(style))
			style="yyyy-MM-dd";
		return TimeUtil.getCurrentDate(style);
	}

	/**
	 * 获取当前组织。
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getCurrentOrg();
	 * </pre>
	 * @return
	 */
	public SysOrg getCurrentOrg() {
		SysOrg sysOrg = ContextUtil.getCurrentOrg();
		return sysOrg;
	}

	/**
	 * 获取当前用户组织的ID
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getCurrentOrgId();
	 * </pre>
	 * @return
	 */
	public Long getCurrentOrgId() throws Exception {
		SysOrg sysOrg = ContextUtil.getCurrentOrg();
		if (sysOrg == null)
			return 0L;
		return sysOrg.getOrgId();
	}

	/**
	 * 取得当前用户组织的名称。
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getCurrentOrgName();
	 * </pre>
	 * @return
	 */
	public String getCurrentOrgName() {
		SysOrg sysOrg = ContextUtil.getCurrentOrg();
		if (sysOrg == null)
			return "";
		return sysOrg.getOrgName();
	}

	/**
	 * 取得当前用户主组织名称
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getCurrentPrimaryOrgName();
	 * </pre> 
	 * @return
	 */
	public String getCurrentPrimaryOrgName() {
		Long userId = ContextUtil.getCurrentUserId();
		SysOrg sysOrg = sysOrgDao.getPrimaryOrgByUserId(userId);
		if (sysOrg == null)
			return "";
		return sysOrg.getOrgName();
	}
	
	/**
	 * 取得当前用户主组织的ID。
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getCurrentPrimaryOrgId();
	 * </pre> 
	 * @return
	 */
	public Long getCurrentPrimaryOrgId() {
		Long userId = ContextUtil.getCurrentUserId();
		SysOrg sysOrg = sysOrgDao.getPrimaryOrgByUserId(userId);
		if (sysOrg == null)
			return 0L;
		return sysOrg.getOrgId();
	}

	/**
	 * 取得某用户主组织的Id。
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getUserOrgId(Long userId);
	 * </pre>
	 * @return
	 */
	public Long getUserOrgId(Long userId) {
		SysOrg sysOrg= sysOrgDao.getPrimaryOrgByUserId(userId);
		if (sysOrg == null)
			return 0L;
		return sysOrg.getOrgId();
	}

	/**
	 * 取得某用户主组织的名称。
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getUserOrgName(Long userId);
	 * </pre>
	 * @return
	 */
	public String getUserOrgName(Long userId) {
		SysOrg sysOrg= sysOrgDao.getPrimaryOrgByUserId(userId);
		if (sysOrg == null)
			return "";
		return sysOrg.getOrgName();
	}

	
	
	/**
	 * 判断当前用户是否属于该角色。
	 * 
	 * <pre>
	 * 在脚本中使用方法:
	 * scriptImpl.hasRole(alias)
	 * </pre>
	 * 
	 * @param alias
	 *            角色别名
	 * @return
	 */
	public boolean hasRole(String alias) {
		long userId = ContextUtil.getCurrentUserId();
		List<SysRole> roleList = sysRoleDao.getByUserId(userId);
		for (SysRole role : roleList) {
			if (role.getAlias().equals(alias)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取当前用户所属角色。
	 * 
	 * <pre>
	 * 在脚本中使用方法: scriptImpl.getCurrentUserRoles();
	 * </pre>
	 * 
	 * @return 返回角色列表。
	 */
	public List<SysRole> getCurrentUserRoles() {
		long userId = ContextUtil.getCurrentUserId();
		List<SysRole> list = sysRoleDao.getByUserId(userId);
		return list;
	}

	/**
	 * 获取发起用户所属角色。
	 * 
	 * <pre>
	 * 在脚本中使用方法: scriptImpl.getUserRoles(strUserId);
	 * </pre>
	 * 
	 * @return 返回角色列表。
	 * @param strUserId
	 *            用户ID
	 * @return
	 */
	public List<SysRole> getUserRoles(String strUserId) {
		if(StringUtil.isEmpty(strUserId)) {
			return Collections.EMPTY_LIST;
		}
		Long userId = Long.parseLong(strUserId);
		List<SysRole> list = sysRoleDao.getByUserId(userId);
		return list;
	}

	/**
	 * 判断用户是否属于某角色。
	 * 
	 * @param userId
	 *            用户id。
	 * @param role
	 *            角色别名 在脚本中使用方法: scriptImpl.isUserInRole(userId,role);
	 * @return
	 */
	public boolean isUserInRole(String userId, String role) {
		Long lUserId = Long.parseLong(userId);
		List<SysRole> list = sysRoleDao.getByUserId(lUserId);
		if (BeanUtils.isEmpty(list))
			return false;
		for (SysRole sysRole : list) {
			if (sysRole.getAlias().equals(role)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断用户是否属于某组织。
	 * <pre>
	 * 在脚本中使用方法: scriptImpl.isUserInOrg(String userId, String orgName);
	 * </pre>
	 * @param userId	用户ID
	 * @param org		组织名称
	 * @return
	 */
	public boolean isUserInOrg(String userId, String orgName) {
		Long lUserId = Long.parseLong(userId);
		List<SysOrg> list = sysOrgDao.getOrgsByUserId(lUserId);
		if (BeanUtils.isEmpty(list))
			return false;
		for (SysOrg sysOrg : list) {
			if (sysOrg.getOrgName().equals(orgName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断当前用户是否属于某组织。
	 * 
	 * @param userId
	 *            用户id。
	 * @param org
	 *            组织名 在脚本中使用方法: scriptImpl.isCurUserInOrg(String orgName);
	 * @return
	 */
	public boolean isCurUserInOrg(String orgName) {
		Long userId = ContextUtil.getCurrentUserId();
		return isUserInOrg(userId.toString(), orgName);
	}
	
	
	/**
	 * 判断用户是否属于某组织（存在多级组织）。
	 * <pre>
	 * 在脚本中使用方法: scriptImpl.isUserInOrgs(String userId, String orgName);
	 * </pre>
	 * @param userId	用户ID
	 * @param org		组织名称
	 * @return
	 */
	public boolean isUserInOrgs(String userId, String orgName) {
		Long lUserId = Long.parseLong(userId);
		SysOrg sysOrg = sysOrgDao.getOrgByOrgName(orgName);
		if(sysOrg==null)return false;
		
		String path="%"+sysOrg.getPath()+"%";
		List<SysOrg> list = sysOrgDao.getOrgByUserIdPath(lUserId,path.trim());
		
		if (BeanUtils.isEmpty(list)){
			return false;
		}
		
		return true;
	}

	/**
	 * 获取用户的主岗位名称
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getUserPos(userId)
	 * </pre>
	 * @param userId	用户ID
	 * @return 主岗位名称
	 */
	public String getUserPos(Long userId) {
		String posName = "";
		Position position = positionDao.getPosByUserId(userId);
		if (!BeanUtils.isEmpty(position)) {
			posName = position.getPosName();
		}
		return posName;
	}

	
	/**
	 * 根据用户ID获取岗位Id。
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getUserPosId(userId)
	 * </pre>
	 * @param userId
	 * @return
	 */
	public String getUserPosId(Long userId) {
		String posId = "";
		Position position = positionDao.getPosByUserId(userId);
		if (!BeanUtils.isEmpty(position)) {
			posId = position.getPosId().toString();
		}
		return posId;
	}
	
	
	/**
	 * 根据工号获取岗位ID。
	 * <pre>
	 * 脚本中使用方法:
	 * return scriptImpl.getPosIdByAccount(account);
	 * </pre>
	 * @param account
	 * @return
	 */
	public String getPosIdByAccount(String account) {
		SysUser sysUser=sysUserService.getByAccount(account);
		if(sysUser==null){
			return "";
		}
		String posId = "";
		Position position = positionDao.getPosByUserId(sysUser.getUserId());
		if (!BeanUtils.isEmpty(position)) {
			posId = position.getPosId().toString();
		}
		return posId;
	}

	
	/**
	 * 根据工号获取岗位名称。
	 * <pre>
	 * 脚本中使用方法:
	 * return scriptImpl.getPosNameByAccount(account);
	 * </pre>
	 * @param userId
	 * @return
	 */
	public String getPosNameByAccount(String account) {
		SysUser sysUser=sysUserService.getByAccount(account);
		if(sysUser==null){
			return "";
		}
		String name = "";
		Position position = positionDao.getPosByUserId(sysUser.getUserId());
		if (!BeanUtils.isEmpty(position)) {
			name = position.getPosName();
		}
		return name;
	}

	/**
	 * 获取当前用户的主岗位名称。
	 * 
	 * <pre>
	 * 脚本中使用方法: scriptImpl.getCurUserPos();
	 * </pre>
	 * 
	 * @return
	 */
	public String getCurUserPos() {
		long userId = ContextUtil.getCurrentUserId();
		String posName = getUserPos(userId);
		return posName;
	}
	
	/**
	 * 获取当前用户的主岗位名称。
	 * <pre>
	 * 脚本中使用方法: scriptImpl.getCurUserPosName();
	 * </pre>
	 * @return
	 */
	public String getCurUserPosName() {
		long userId = ContextUtil.getCurrentUserId();
		String posName = getUserPos(userId);
		return posName;
	}
	
	
	/**
	 * 获取当前用户的主岗位ID。
	 * <pre>
	 * 脚本中使用方法: scriptImpl.getCurUserPosId();
	 * </pre>
	 * @return
	 */
	public String getCurUserPosId() {
		long userId = ContextUtil.getCurrentUserId();
		String posId = getUserPosId(userId);
		return posId;
	}
	
	/**
	 * 获取当前用户的岗位名称
	 * <pre>
	 * 脚本中使用方法: scriptImpl.getCurrentPosName();
	 * </pre>
	 * @return
	 */
	public String getCurrentPosName(){
		Position position = ContextUtil.getCurrentPos();
		if(position != null && StringUtil.isNotEmpty(position.getPosName())){
			return position.getPosName();
		}
		return "";
	}
	
	/**
	 * 获取当前用户的岗位id
	 * <pre>
	 * 脚本中使用方法: scriptImpl.getCurrentPosId();
	 * </pre>
	 * @return
	 */
	public Long getCurrentPosId(){
		Position position = ContextUtil.getCurrentPos();
		if(position != null && position.getPosId() != null){
			return position.getPosId();
		}
		return null;
	}

	/**
	 * 获取cmd对象。
	 */
	public ProcessCmd getProcessCmd(){
		return TaskThreadService.getProcessCmd();
	}

	/**
	 * 通过用户账号设置任务执行人
	 * 
	 * @param task
	 * @param userAccout
	 */
	public void setAssigneeByAccount(TaskEntity task, String userAccout) {
		String[] aryAccount = userAccout.split(",");
		List<String> userIds = new ArrayList<String>();
		for (String str : aryAccount) {
			SysUser sysUser = sysUserService.getByAccount(str);
			userIds.add(sysUser.getUserId().toString());
		}
		if (userIds.size() == 0)
			return;
		// 传进来一个用户则直接设置为执行人
		if (userIds.size() == 1) {
			task.setAssignee(userIds.get(0));
		}
		// 传进来多个用户则添加到候选人
		else {
			task.addCandidateUsers(userIds);
		}
	}

	/**
	 * 启动某个流程。
	 * 脚本使用方法：
	 * scriptImpl.startFlow(String flowKey, String businnessKey,Map<String, Object> vars);
	 * @param flowKey
	 *            流程定义key。
	 * @param businnessKey
	 *            业务主键
	 * @param vars
	 *            流程变量。
	 * @return
	 * @throws Exception
	 */
	public ProcessRun startFlow(String flowKey, String businnessKey,
			Map<String, Object> vars) throws Exception {
		ProcessCmd processCmd = new ProcessCmd();
		BpmDefinition bpmDefinition=null;
		processCmd.setFlowKey(flowKey);
		if(StringUtils.isEmpty(businnessKey)){
			businnessKey=Long.toString(UniqueIdUtil.genId());
		}
		processCmd.setBusinessKey(businnessKey);
		if (BeanUtils.isNotEmpty(vars)) {
			processCmd.setVariables(vars);
		}

		bpmDefinition = bpmDefinitionService.getMainDefByActDefKey(flowKey);
		processCmd.addTransientVar(BpmConst.BPM_DEF, bpmDefinition);
		return processRunService.startProcess(processCmd);
	}

	/**
	 * 根据工号获取姓名。
	 * <pre>
	 * 脚本中使用方法: scriptImpl.getFullNameByAccount(String accout);
	 * </pre>
	 * @param accout
	 * @return
	 */
	public String getFullNameByAccount(String accout){
		
		SysUser sysUser= sysUserService.getByAccount(accout);
		if(sysUser==null){
			return "";
		}
		return sysUser.getFullname();
	}
	
	/**
	 * 根据多工号获取姓名字符串
	 * <pre>
	 * 脚本中使用方法: scriptImpl.getFullNameByAccounts(String accouts);
	 * </pre>
	 * @param accouts 工号字符串，多个以逗号隔开
	 * @return
	 */
	public String getFullNameByAccounts(String accouts){
		
		List<SysUser> sysUsers= sysUserService.getByAccounts(accouts);
		StringBuilder fullNames = new StringBuilder();
		for(SysUser s : sysUsers) {
			fullNames.append(s.getFullname());
			fullNames.append(",");
		}
		if(fullNames.length()>1) {
			fullNames.deleteCharAt(fullNames.length()-1);
		}
		return fullNames.toString();
	}
	
	/**
	 * 根据多工号获取用户ID字符串
	 * <pre>
	 * 脚本中使用方法: scriptImpl.getUserIdsByAccounts(String accouts);
	 * </pre>
	 * @param accout
	 * @return
	 */
	public String getUserIdsByAccounts(String accouts){
		
		List<SysUser> sysUsers= sysUserService.getByAccounts(accouts);
		StringBuilder ids = new StringBuilder();
		for(SysUser s : sysUsers) {
			ids.append(s.getUserId());
			ids.append(",");
		}
		if(ids.length()>1) {
			ids.deleteCharAt(ids.length()-1);
		}
		return ids.toString();
	}
	/**
	 * 根据工号获取用户ID
	 * <pre>
	 * 脚本中使用方法: scriptImpl.getUserIdByAccount(String accout);
	 * </pre>
	 * @param accout
	 * @return
	 */
	public String getUserIdByAccount(String accout){
		SysUser sysUser= sysUserService.getByAccount(accout);
		if(sysUser==null){
			return "";
		}
		return sysUser.getUserId().toString();
	}
	
	/**
	 * 根据工号获取组织名称。
	 * <pre>
	 * 脚本中使用方法: scriptImpl.getOrgNameByAccount(String accout);
	 * </pre>
	 * @param account
	 * @return
	 */
	public String getOrgNameByAccount(String account){
		SysOrg sysOrg=sysOrgDao.getOrgByAccount(account);
		return sysOrg.getOrgName();
	}
	
	/**
	 * 根据工号获取组织ID。
	 * <pre>
	 * 脚本中使用方法:
	 *  scriptImpl.getOrgIdByAccount(String account);
	 * </pre>
	 * @param account
	 * @return
	 */
	public String getOrgIdByAccount(String account){
		SysOrg sysOrg=sysOrgDao.getOrgByAccount(account);
		return sysOrg.getOrgId().toString();
	}
	
	/**
	 * 判断组织A是否为组织B的子组织
	 * @param sunOrgId
	 * @param parentOrgId
	 * @return
	 */
	public boolean getOrgBelongTo(String sonOrgId,Long parentOrgId){
		SysOrg sonSysOrg = sysOrgDao.getById(Long.parseLong(sonOrgId));
		SysOrg parentSysOrg = sysOrgDao.getById(parentOrgId);
		if(BeanUtils.isEmpty(sonSysOrg)||BeanUtils.isEmpty(parentSysOrg))return false;
		String sonPath = sonSysOrg.getPath();
		String parentPath = parentSysOrg.getPath();
		int result = sonPath.indexOf(parentPath); 
		return result > -1;
	}

	/**
	 * 根据组织id获取组织名称。
	 * <pre>
	 * scriptImpl.getOrgNameById(Long orgId);
	 * </pre>
	 * @param orgId
	 * @return
	 */
	public String getOrgNameById(Long orgId){
		String orgName="";
		try{
			SysOrg sysOrg= sysOrgDao.getById(orgId);
			orgName=sysOrg.getOrgName();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return orgName;
		
		
	}
	
	/**
	 * 根据组织ID获取组织ID(用于接口)
	 * <pre>
	 * scriptImpl.getOrgNameById(String orgId);
	 * </pre>
	 * @param orgId
	 * @return
	 */
	public String getOrgIdById(String orgId){
	  String rOrgId="";
	  try{
	    SysOrg sysOrg= sysOrgDao.getById(Long.valueOf(orgId));
	    rOrgId=String.valueOf(sysOrg.getOrgId());
	  }
	  catch (Exception e) {
	    e.printStackTrace();
	  }
	  return rOrgId;
	  
	  
	}
	
	/**
	 * 根据组织ID获取组织名称(用于接口传字符串类型的ID)
	 * <pre>
	 * scriptImpl.getOrgNameById(String orgId);
	 * </pre>
	 * @param orgId
	 * @return
	 */
	public String getOrgNameById(String orgId){
	  String orgName="";
	  try{
	    SysOrg sysOrg= sysOrgDao.getById(Long.valueOf(orgId));
	    orgName=sysOrg.getOrgName();
	  }
	  catch (Exception e) {
	    e.printStackTrace();
	  }
	  return orgName;
	  
	  
	}


	/**
	 * 返回当前组织的类型。
	 * 
	 * @return
	 */
	public SysOrgType getCurrentOrgType() {
		SysOrg sysOrg = ContextUtil.getCurrentOrg();
		if (sysOrg == null)
			return null;
		Long orgType = sysOrg.getOrgType();
		if (orgType == null)
			return null;
		SysOrgType sysOrgType = sysOrgTypeDao.getById(orgType);
		return sysOrgType;
	}

	/**
	 * 返回当前组织类型的名称。
	 * 
	 * @return
	 */
	public String getCurrentOrgTypeName() {
		SysOrg sysOrg = ContextUtil.getCurrentOrg();
		if (sysOrg == null)
			return "";
		Long orgType = sysOrg.getOrgType();
		if (orgType == null)
			return "";
		SysOrgType sysOrgType = sysOrgTypeDao.getById(orgType);
		return sysOrgType.getName();
	}

	/**
	 * 根据当前用户取得指定参数key的参数值。
	 * 脚本中使用方法:
	 * scriptImpl.getParaValue(String paramKey);
	 * @param paraName
	 *            参数名称
	 * @return
	 */
	public Object getParaValue(String paramKey) {
		Long currentUserId = ContextUtil.getCurrentUserId();

		return getParaValueByUser(currentUserId, paramKey);
	}

	/**
	 * 根据用户ID和参数key获取参数值。
	 * 脚本中使用方法:
	 * scriptImpl.getParaValueByUser(Long userId, String paramKey);
	 * @param userId
	 *            用户ID
	 * @param paramKey
	 *            参数键
	 * @return
	 */
	public Object getParaValueByUser(Long userId, String paramKey) {
		SysUserParam sysUserParam = sysUserParamDao.getByParaUserId(userId,
				paramKey);
		if (sysUserParam == null) {
			return null;
		}
		String dataType = sysUserParam.getDataType();
		if ("String".equals(dataType)) {
			return sysUserParam.getParamValue();
		} else if ("Integer".equals(dataType)) {
			return sysUserParam.getParamIntValue();
		} else {
			return sysUserParam.getParamDateValue();
		}
	}

	/**
	 * 获取流程当前用户直属领导的主岗位名称。
	 * 
	 * <pre>
	 * 脚本中使用方法:
	 * scriptImpl.getCurDirectLeaderPos();
	 * </pre>
	 * 
	 * @param userId
	 *            用户ID
	 * @return 主岗位名称
	 */
	public String getCurDirectLeaderPos() {
		String userId = ContextUtil.getCurrentUserId().toString();
		String posName = getDirectLeaderPosByUserId(userId);
		return posName;
	}

	/**
	 * 获取用户的组织的直属领导岗位。
	 * 
	 * <pre>
	 * 1.当前人是普通员工，则获取部门负责人，如果找不到，往上级查询负责人岗位。
	 * 2.当前人员是部门负责人，则获取上级部门负责人，如果找不到则往上级查询负责人岗位。
	 * <br>
	 * 脚本使用方法：
	 * scriptImpl.getDirectLeaderPosByUserId(String userId);
	 * </pre>
	 * 
	 * @param userId
	 * @return 如果有负责人则返回，没有返回null。
	 */
	public String getDirectLeaderPosByUserId(String userId) {
		String posName = "";
		posName = sysUserOrgService
				.getLeaderPosByUserId(Long.parseLong(userId));
		return posName;
	}

	/**获取上级的部门负责人*/
	public Set<String> getDirectLeaderByUserId(String userId) {
		Set<String> userSet = new HashSet<String>();
		List<TaskExecutor> userList = sysUserOrgService.getLeaderByUserId(Long
				.parseLong(userId));
		// 获取直属上司为空。

		if (BeanUtils.isEmpty(userList))
			return userSet;
		for (TaskExecutor user : userList) {
			userSet.add(user.getExecuteId());
		}

		return userSet;
	}

	
	
	/**
	 * 获取组织负责人。
	 * @param orgId
	 * @return
	 */
	private List<TaskExecutor> getExecutor(Long orgId) {
		List<UserPosition> userPositionList = sysUserOrgService.getChargeByOrgId(orgId);
		if (BeanUtils.isEmpty(userPositionList)) return null;
		List<TaskExecutor> executors = new ArrayList<TaskExecutor>();
		for (UserPosition userPosition : userPositionList) {
			TaskExecutor taskExecutor=TaskExecutor.getTaskUser(userPosition.getUserId().toString(),userPosition.getUserName());
			executors.add(taskExecutor);
		}
		return executors;
	}
	/**
	 * 此方法用于逐级审批跳转规则。
	 * 此方法如果返回为true，则流程玩下执行。
	 * 返回为false 则流程跳转回本地。
	 * <pre>
	 * 	1.获取流程变量（PreStepUpserApproverOrgId）。
	 * 		1.如果没有获取到则返回true。
	 * 		2.获取到了则判断组织类型和指定类型是否一致。
	 * 			如果一致则删除流程变量。
	 * 			否则返回false。
	 * </pre>
	 * 当组织类型为指定类型时，流程按照流程图进行跳转，否则跳回本节点。
	 * @param orgType 组织级别 
	 * @return
	 */
	public boolean isTopUpserApprove(Long orgType){
		ProcessCmd cmd = getProcessCmd();
		String taskId=cmd.getTaskId();
		
		Long orgId = (Long) bpmService.getVarsByTaskId(taskId).get(PreStepUpserApproverOrgId);
		
		if (BeanUtils.isEmpty(orgId)) return true;
		SysOrg sysOrg = sysOrgService.getById(orgId);
		if (BeanUtils.isEmpty(sysOrg)||orgType.equals(sysOrg.getOrgType())) {
			TaskEntity task= bpmService.getTask(taskId);
			String actInstId= task.getProcessInstanceId();
			runtimeService.removeVariable(actInstId,PreStepUpserApproverOrgId);
			return true;
		}
		return false;
	}
	
	/**
	 * 判断用户是否该部门的负责人
	 * 脚本使用方法：
	 * scriptImpl.isDepartmentLeader(String userId, String orgId) ;
	 * @param userId
	 *            用户ID
	 * @param orgId
	 *            部门ID
	 * @return
	 */
	public boolean isDepartmentLeader(String userId, String orgId) {
		// 根据部门ID获取部门负责人
		List<TaskExecutor> leaders = sysUserOrgService.getLeaderByOrgId(Long
				.parseLong(orgId));
		for (TaskExecutor leader : leaders) {
			if (userId.equals(leader.getExecuteId())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断用户是否该职务
	 * 脚本使用方法：
	 * scriptImpl.isJobName(String userId, String jobName) ;
	 * @param userId
	 *            用户ID
	 * @param jobName
	 *            职务名
	 * @return
	 */
	public boolean isJobName(String userId, String jobName) {
		String Id="";
		if(StringUtils.isEmpty(userId)){
		    Id = ContextUtil.getCurrentUserId().toString();
		}
		else{
			Id=userId;
		}
		// 根据用户ID获取职务
		String  selectSql="select b.jobname from sys_job b,sys_user_pos p ";
		selectSql+="where b.jobid=p.jobid and p.userid="+Id;		
		List<Map<String,Object>> map=jdbcTemplate.queryForList(selectSql);
		for (Map p : map) {
			if (jobName.equals(p.get("jobname").toString())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取用户领导id集合。
	 * 脚本使用方法：
	 * scriptImpl.getLeaderByUserId(Long userId);
	 * @param userId
	 * @return
	 */
	public Set<String> getLeaderByUserId(Long userId) {
		Set<TaskExecutor> set = userUnderService.getMyLeader(userId);
		Set<String> userSet = new HashSet<String>();
		for (Iterator<TaskExecutor> it = set.iterator(); it.hasNext();) {
			userSet.add(it.next().getExecuteId());
		}
		return userSet;
	}
	
	/**
	 * 获取当前用户领导id集合。
	 * @param userId
	 * @return
	 */
	public Set<String> getMyLeader() {
		Long userId=ContextUtil.getCurrentUserId();
		Set<String> userSet= getLeaderByUserId(userId);
		return userSet;
	}

	/**
	 * 获取用户下属用户ID集合
	 * 脚本使用方法：
	 * scriptImpl.getMyUnderUserId();
	 * @param userId
	 * @return
	 */
	public Set<String> getMyUnderUserId() {
		Long userId=ContextUtil.getCurrentUserId();
		return userUnderService.getMyUnderUserId(userId);
	}

	/**
	 * 通过表名更新数据
	 * @param businessKey 主键
	 * @param tableName 表名
	 * @param map 要更新的数据
	 */
	public void updateByTableName(String businessKey,String tableName,Map<String,Object> map){
		String sql = "";
		if(map.size()==0)return;
		Object[] objs = new Object[map.size()+1];
		int count = 0;
		for(Iterator<Entry<String, Object>> it=map.entrySet().iterator();it.hasNext();){
			Entry<String, Object> obj=it.next();
			sql += ", " + obj.getKey() + "=?";
			objs[count] = obj.getValue();
			count++;
		}
		objs[count] = businessKey;
		sql = sql.replaceFirst(",", "");
		sql = "update " + tableName + " set " + sql + " where ID=?";
		jdbcTemplate.update(sql, objs);
	}
	
	/**
	 * 获取该当前用户的组织责任人，临时用 
	 */
	public String getMgrByOrgIds(){
		Long userId = ContextUtil.getCurrentUserId();
		List<SysUser> sysUser=sysUserDao.getOrgMainUser(userId);
		if(BeanUtils.isEmpty(sysUser)) return "暂时没有资产负责人";
		return sysUser.get(0).getFullname();
	}
	
	/**
	 * 取得当前用户所在的组织某个角色对应的用户
	 * @param roleName 角色名称别名
	 * @return
	 */
	public SysUser getUserByCurOrgRoleAlias(String roleNameAlias){
		//Long userId = ContextUtil.getCurrentUserId();
		//SysUserOrg sysUserOrg=sysUserOrgService.getPrimaryByUserId(userId);
		SysOrg curOrg=ContextUtil.getCurrentOrg();
		SysRole sysRole=sysRoleDao.getByRoleAlias(roleNameAlias);
		if(curOrg!=null && sysRole!=null){
			List<SysUser> sysUserList=sysUserService.getUserByRoleIdOrgId(sysRole.getRoleId(),curOrg.getOrgId());
			if(BeanUtils.isNotEmpty(sysUserList)) return sysUserList.get(0);
		}
		return null;
	}
	
	/**
	 * 取得当前用户所在的组织某个角色对应的用户名
	 * @param roleName 角色名称别名
	 * @return
	 */
	public String getUserFullnameByCurOrgRoleAlias(String roleNameAlias){
		SysUser sysUser=getUserByCurOrgRoleAlias(roleNameAlias);
		return sysUser==null?"":sysUser.getFullname();
	}
	
	/**
	 * 取得某个组织(包括当前组织)的上级部门包括某个岗位的人员列表
	 * @param startOrgId
	 * @param posName
	 * @return
	 */
	public Set<String> getUsersByOrgAndPos(Long startOrgId,String posName){
		Set<String> users=new HashSet<String>();
		if(startOrgId==null) return users;
		SysOrg sysOrg=sysOrgService.getById(startOrgId);
		List<Position> posList=positionDao.getByPosName(posName);
		if(BeanUtils.isEmpty(posList)){
			return users;
		}
		Long posId=posList.get(0).getPosId();
		String[]upOrgPaths=sysOrg.getPath().split("[.]");
		for(int i=upOrgPaths.length-1;i>0;i--){
			SysOrg tempOrg=sysOrgService.getById(new Long(upOrgPaths[i]));
			List<SysUser> sysUserList=sysUserDao.getByOrgIdPosId(tempOrg.getOrgId(), posId);
			for(SysUser sysUser:sysUserList){
				users.add(sysUser.getUserId().toString());
			}
		}
		return users;
	}
	
	/**
	 * 获取部门的指定职务的用户
	 * @param orgId
	 * @param jobKey
	 * @return
	 */
	public Set<String> getLeaderByOrgName(String orgId,String jobCode){
		Set<String> users=new HashSet<String>();
		if(orgId==null||jobCode==null) return users;
		SysOrg sysOrg=sysOrgService.getById(Long.parseLong(orgId));
		List<Position> orgPosListByOrgIds = positionDao.getOrgPosListByOrgIds(sysOrg.getOrgId().toString());
		Job byJobCode = jobDao.getByJobCode(jobCode);
		Long jobId = byJobCode.getJobid();
		
		for (Position position : orgPosListByOrgIds) {
			Long id = position.getJobId();
			if(id.equals(jobId)){
				List<Long> userIdsByPosId = userPositionDao.getUserIdsByPosId(position.getPosId());
				for (Long long1 : userIdsByPosId) {
					users.add(long1.toString());
				}
			}
		}
		return users;
	}
	
	/**
	 * 获取组织的分管领导
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public Set<String> getChargeOfOrgId(String orgId) throws Exception{
		Set<String> users=new HashSet<String>();
		if(orgId==null) return users;
		String fgbm = "[{\"fgbm\":\""+orgId+"\"}]";
		List<SysUser> byUserParam = sysUserService.getByUserParam(fgbm);
		for (SysUser sysUser : byUserParam) {
			users.add(sysUser.getUserId().toString());
		}
		return users;
	}
	
	/**
	 * 通过用户ID获取其职务代码
	 * @param userId
	 * @return
	 */
	public String getJobCodeByUserId(String userId){
		Long id = Long.parseLong(userId);
		Position posByUserId = positionDao.getPosByUserId(id);
		Long jobId = posByUserId.getJobId();
		Job job = jobDao.getById(jobId);
		String jobKey = "";
		if(BeanUtils.isNotEmpty(job)){
			jobKey = job.getJobcode();
		}
		return jobKey;
	}
	
	/**
	 * 获取当前登陆者职务
	 * @return
	 */
	public Job getCurrentJob(){
		Position pos = ContextUtil.getCurrentPos();
		if(pos == null || pos.getJobId() == null){
			return null;
		}
		Long jobId = pos.getJobId();
		Job job = jobDao.getById(jobId);
		return job;
	}
	
	/**
	 * 获取当前登陆者职务等级
	 * @return
	 */
	public Short getCurrentJobGrade(){
		Job job = this.getCurrentJob();
		if(job == null || job.getGrade() == null){
			return null;
		}
		return job.getGrade();
	}
	

	/**
	 * 根据业务主键和表名查看子表是否有数据。
	 * @param tableName
	 * @param pk
	 * @return
	 */
	public boolean isSubTableHasData(String tableName,Long fk){
		List<Map<String, Object>> list= bpmFormHandlerDao.getByFk(tableName, fk);
		return list.size()>0;
	}
	
	/**
	 * 根据表名和外键获取数据。
	 * @param tableName
	 * @param fk
	 * @return
	 */
	public List<Map<String, Object>> getByFk(String tableName,Long fk){
		List<Map<String, Object>> list= bpmFormHandlerDao.getByFk(tableName, fk);
		return list;
	}
	
	/**
	 * 在子流程中获取父流程某个节点的审批人。
	 * @param flowRunId		子流程的RUNID
	 * @param nodeId		主流程的节点ID
	 * @return
	 */
	public Set<String> getAuditByMainInstId(Long flowRunId,String nodeId){
		Set<String> set=new LinkedHashSet<String>();
		ProcessRun processRun= processRunService.getById(flowRunId);
		
		if(processRun==null){
			return set;
		}
		if(processRun.getParentId()!=null){
			processRun= processRunService.getById(processRun.getParentId());
			TaskOpinion taskOpinion = taskOpinionService.getLatestTaskOpinion(Long.parseLong(processRun.getActInstId()) , nodeId);
			set.add(taskOpinion.getExeUserId().toString());
		}
		return set;
	}
	

	/**
	 * 获取当前用户部门ID
	 * @return
	 */
	public Long getCurrUserDeptId(){
		return this.getCurrUserOryByTypeId(3L);
	}
	
	/**
	 * 获取当前用户单位ID
	 * @return
	 */
	public Long getCurrUserUnitId(){
		return this.getCurrUserOryByTypeId(2L);
	}
	
	/**
	 * 
	 * 根据当前用户的组织id遍历出类型相对应的id
	 * 本数据库的组织分类为：1:集团,2:公司/单位,3:部门,4:小组,5:其他组织
	 * @param userId
	 * @return
	 */
	private Long getCurrUserOryByTypeId(Long orgType){
		Long id=0L;
		Long orgId = ContextUtil.getCurrentOrgId();
		if(orgId == null) return id;
		SysOrg sysOrg = sysOrgDao.getById(orgId);
		String path = sysOrg.getPath();
		String[]pathArr = path.split("\\.");
		// 从下往上遍历
		for(int i=pathArr.length -1;i>=0;i--){
			String orgId2= pathArr[i];
			if(StringUtil.isNotEmpty(orgId2)){
				SysOrg sysOrg2 = sysOrgDao.getById(Long.valueOf(orgId2));
				if(sysOrg2 != null && sysOrg2.getOrgType() == orgType){
					id = sysOrg2.getOrgId();
					break;
				}
			}
		}
		return id;
	}
	
	//add by yangxiao
	public void delete3(String proc_def_id_,String task_def_key_){
		
		  String sql="delete from  act_ru_task where id_ in ( select a.id_  from (select id_ from act_ru_task where proc_def_id_='"+proc_def_id_+"' and task_def_key_='"+task_def_key_+"' ) a ) ";
		  JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		  template.execute(sql);
		  
		  
		  taskUserAssignService.addNodeUser("UserTask5","10000000470062");
		

		
	}
	//written by sxh 
	public void updateWorkoderState(String workorderNo,String workorderState){
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="update w_gdjbxx4 set F_gdzt='"+workorderState+"' where F_gdh='"+workorderNo+"'";
		template.update(sql);
	}
	
	//written by sxh,return the new businessKey.
	public String createNewOrder(String workorderNo){
		String no=is.nextId("workorderNo");
		Long key=UniqueIdUtil.genId();
		String businessKey=key.toString();
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="select f_ywxt,f_sgdw,f_sfycsd from w_gdjbxx4 where f_gdh="+workorderNo;
		List<Map<String,Object>> list=template.queryForList(sql);
		sql="insert into w_gdjbxx4 (id) values("+businessKey+")";
		template.update(sql);
		sql="update w_gdjbxx4 set f_ywxt='"+list.get(0).get("f_ywxt")+"',f_sgdw='"+list.get(0).get("f_sgdw")+"',f_sfycsd='"+
			list.get(0).get("f_sfycsd")+"',f_gdh='"+no+"',f_glgdh='"+workorderNo+"' where id='"+businessKey+"'";
		template.update(sql);
		sql="update w_gdjbxx4 set f_glgdh='"+no+"' where f_gdh='"+workorderNo+"'";
		template.update(sql);
		return businessKey;
	}
	
	//written by sxh,write businessKey to table w_gdjbxx3_index
	public void insertID(String businessKey){
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="insert into w_gdjbxx3_index ( " +
				"select id,f_gdh,f_ywxt,f_sgdw,f_xdsj from w_gdjbxx3 where id='"+businessKey+"')";
		template.update(sql);
	}
	
	//written by sxh,update the attribute to the value given by user.
	public void updateWorkorderAttr(String workorderNo,String attribute,String value){
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="update w_gdjbxx4 set F_"+attribute+"='"+value+"' where F_gdh='"+workorderNo+"'";
		template.update(sql);
	}
	
	//written by sxh,insert the flowNo right now after the flow was started
	public void insertFlowNo(Long businessKey){
		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String flowNo = is.nextId("workorderNo");
		//long temp = Long.valueOf(businessKey)+1;
		Long temp = businessKey +1;
		String sql="update w_gdjbxx4 set F_gdh='"+flowNo+"' where id='"+temp+"'";
		
		System.out.println("sql:"+sql);
		
		template.update(sql);
	}
	/**
	 * 获取当前用户所属的指定级别的组织
	 * @param orgType 指定级别
	 * @returngetLeaderByUserId
	 */
	public String getCurrUserOrgNameByTypeId(Long orgType){
		Long currUserOryByTypeId = getCurrUserOryByTypeId(orgType);
		SysOrg sysOrg = sysOrgService.getById(currUserOryByTypeId);
		String orgName = "";
		if(BeanUtils.isNotEmpty(sysOrg)){
			orgName = sysOrg.getOrgName();
		}
		return orgName;
	}
	
	/*// 获取当前节点的上一节点是否为当前节点
	private boolean preNodeEqThis(){
		ProcessCmd cmd = getProcessCmd();
		TaskEntity entity = processRunService.getTaskEntByCmd(cmd);
		entity.getParentTaskId();
		String nodeId = entity.getTaskDefinitionKey();
		taskOpinionService.get
		
		entity.getp
		cmd.getDestTask();//scriptImpl.getLeaderByUserId( startUser );
		
		return false;
	}*/
	
	/**
	 * 执行用户自定义的sql语句，替换掉[CUR_USER]：当前用户id，[CUR_ORG]：当前组织id
	 * @param sql
	 * @return String
	 */
	public String executeSql(String sql){
		try {
			if(StringUtil.isEmpty(sql)) 
				return "";
			// 替换值，目前只需要替换"当前用户id"和"当前组织id"
			Long userId = ContextUtil.getCurrentUserId();
			Long orgId = ContextUtil.getCurrentOrgId();
			Long posId = ContextUtil.getCurrentPosId();
			sql = sql.replaceAll("''", "'");
			if(userId != null){
				sql = sql.toUpperCase().replace("[CUR_USER]", userId.toString());
			}
			if(orgId != null){
				sql = sql.toUpperCase().replace("[CUR_ORG]", orgId.toString());
			}
			if(posId != null){
				sql = sql.toUpperCase().replace("[CUR_POS]", posId.toString());
			}
			List<String> list = jdbcTemplate.queryForList(sql, String.class);
			if(list != null && list.size() > 0){
				return list.get(0);
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "请检查sql 语句是否正确";
		}
	}
	/**
	 * 执行用户自定义的sql语句，替换掉[CUR_USER]：当前用户id，[CUR_ORG]：当前组织id
	 * @param sql
	 * @return String
	 */
	public void executeSql(BpmFormData formData,String sql) {
		Map<String, Object> dataMap = formData.getMainFields();
		if (dataMap != null && !dataMap.isEmpty()) {
			// 1.先根据主表数据拼装sql
			Pattern pattern = Pattern.compile("<#(.*?)#>");
			Matcher matcher = pattern.matcher(sql);
			while (matcher.find()) {
				String str = matcher.group();
				String key = matcher.group(1);
				String val =MapUtil.getString(dataMap, key);
				sql = sql.replace(str, val);
			}
		}
		// 2.开始执行sql
		try {
			jdbcTemplate.execute(sql);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	/**
	 * 根据组织等级和职务ID获取人员。
	 * @param grade
	 * @param jobId
	 * @return
	 */
	public List<SysUser> getByOrgGradeAndJob(int grade,Long jobId){
		SysOrg sysOrg=ContextUtil.getCurrentOrg();
		
		List<SysOrgType> typeList= sysOrgTypeDao.getByDemId(Demension.ADMINSTRATION);
		Map<Long,Integer> map= convertToMap(typeList);
		int curGrade=map.get(sysOrg.getOrgType());
		
		while(grade!=curGrade){
			Long parentOrgId=sysOrg.getOrgSupId();
			if(parentOrgId.equals(SysOrg.BEGIN_ORGID)) break;
			sysOrg= sysOrgService.getById(parentOrgId);
			curGrade=map.get(sysOrg.getOrgType());
		}
		if(curGrade!=grade){
			return new ArrayList<SysUser>();
		}
		
		Position pos= positionDao.getByOrgJobId(sysOrg.getOrgId(), jobId);
		
		List<SysUser> users= sysUserDao.getByPosId(pos.getPosId());
		
		
		return users;
	}
	
	private Map<Long,Integer> convertToMap(List<SysOrgType> typeList){
		Map<Long,Integer> map =new HashMap<Long, Integer>();
		for(SysOrgType orgType:typeList){
			map.put(orgType.getId(), orgType.getLevels().intValue());
		}
		return map;
	}
	
	/**
	 * 判断当前登陆者部门是否拥有该部门属性
	 * @param key
	 * @return
	 */
	public Boolean hasParamKeyForOrg(String paramKey){
		Long orgId = ContextUtil.getCurrentOrgId();
		if(orgId == null){
			return false;
		}
		return this.hasParamKey(orgId.toString(),paramKey);
	}
	
	/**
	 * 判断当前登陆者的分公司是否拥有该部门属性
	 * @param key
	 * @return
	 */
	public Boolean hasParamKeyForCompany(String paramKey){
		Long companyId = ContextUtil.getCurrentCompanyId();
		if(companyId == null){
			return false;
		}
		return this.hasParamKey(companyId.toString(),paramKey);
	}
	
	/**
	 * 根据部门ID或分公司ID,判断是否有paramKey属性
	 * @param orgId
	 * @param paramKey
	 * @return
	 */
	public Boolean hasParamKey(String orgId,String paramKey){
		if(StringUtil.isEmpty(orgId) || StringUtil.isEmpty(paramKey) ){
			return false;
		}
		SysOrgParam sysOrgParam = sysOrgParamService.getByParamKeyAndOrgId(paramKey, Long.valueOf(orgId));
		if(BeanUtils.isEmpty(sysOrgParam)){
			return false;
		}
		return true;
	}
	
	/**
	 * 根据部门ID和参数key,获取参数值
	 * @param orgId 流程变量
	 * @param key
	 * @return
	 */
	public String getParamValueByOrgIdKey(String orgId,String paramKey){
		if(StringUtil.isEmpty(orgId) || StringUtil.isEmpty(paramKey)){
			return null;
		}
		SysOrgParam sysOrgParam = sysOrgParamService.getByParamKeyAndOrgId(paramKey, Long.valueOf(orgId));
		if(BeanUtils.isEmpty(sysOrgParam)){
			return null;
		}
		return sysOrgParam.getParamValue();
	}
	
	/**
	 * 根据参数Key和参数Value获取用户集合
	 * @param paramKey
	 * @param paramValue
	 * @return
	 */
	public Set<String> getUserByParamKeyValue(String paramKey,String paramValue){
		Set<String> userSet = new HashSet<String>();
		List<SysUserParam> userList = sysUserParamService.getByParamKeyValue(paramKey,paramValue);
		if (BeanUtils.isEmpty(userList))
			return userSet;
		for (SysUserParam user : userList) {
			userSet.add(user.getUserId().toString());
		}
		return userSet;
	}
	
	/**
	 * 获取分管审批人
	 * @param orgId		流程变量	
	 * @param fgDept	分管部门标示,如"fg_dept"
	 * @param fgUser	分管用户标示,如"fg_user"
	 * @return
	 */
	public Set<String> getFgUsers(String orgId,String fgDept,String fgUser){
		String paramValue = this.getParamValueByOrgIdKey(orgId,fgDept);
		Set<String> userSet = this.getUserByParamKeyValue(fgUser,paramValue);
		return userSet;
	}
	
	/**
	 * 根据发起人的分公司ID和角色ID获取对应的人员列表。
	 * @param grade		级别
	 * @param roleId	角色ID
	 * @return
	 */
	public Set<String> getByCompanyRole(String companyId,Long roleId){
		List<SysUser> userList = sysUserDao.getByCompanyRole(Long.valueOf(companyId), roleId);
		Set<String> userSet = new HashSet<String>();
		if (BeanUtils.isEmpty(userList))
			return userSet;
		for (SysUser user : userList) {
			userSet.add(user.getUserId().toString());
		}
		return userSet;
	}
	
	/**
	 * 判断当前登陆者部门是否拥有该部门属性
	 * @param key
	 * @return
	 */
	public Boolean hasParamKeyForUser(String paramKey){
		Long userId = ContextUtil.getCurrentUserId();
		SysUserParam sysUserParam = sysUserParamService.getByParamKeyAndUserId(paramKey,userId);
		if(BeanUtils.isEmpty(sysUserParam)){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取当前单位。
	 * <pre>
	 * 1.从缓存中读取当前用户的所属单位。
	 * 2.取得当前系统中设置的分公司策略，获取分公司列表。
	 * 3.根据当前人公司是否在分公司列表。
	 * 	1.如果存在则返回。
	 *  2.如果不在则往上查找，直到找到顶级组织。
	 * </pre>
	 * @return
	 * @throws Exception 
	 */
	public SysOrg getCurrentCompany(){
		SysOrg org = ContextUtil.getCurrentCompany();
		if(org == null){
			return null;
		}
		return ContextUtil.getCurrentCompany();
	}
	
	/**
	 * 获取当前单位类型
	 * <pre>
	 * 1.从缓存中读取当前用户的所属单位。
	 * 2.取得当前系统中设置的分公司策略，获取分公司列表。
	 * 3.根据当前人公司是否在分公司列表。
	 * 	1.如果存在则返回。
	 *  2.如果不在则往上查找，直到找到顶级组织。
	 * </pre>
	 * @return
	 */
	public Long getCurrentCompanyType(){
		SysOrg org = ContextUtil.getCurrentCompany();
		if(org == null){
			return null;
		}
		return ContextUtil.getCurrentCompany().getOrgType();
	}

	/**
	 * 获取当前单位id
	 * <pre>
	 * 1.从缓存中读取当前用户的所属单位。
	 * 2.取得当前系统中设置的分公司策略，获取分公司列表。
	 * 3.根据当前人公司是否在分公司列表。
	 * 	1.如果存在则返回。
	 *  2.如果不在则往上查找，直到找到顶级组织。
	 * </pre>
	 * @return
	 */
	public Long getCurrentCompanyOrgId(){
		SysOrg org = ContextUtil.getCurrentCompany();
		if(org == null){
			return null;
		}
		return ContextUtil.getCurrentCompany().getOrgId();
	}
	
	/**
	 * 获取当前单位名称
	 * <pre>
	 * 1.从缓存中读取当前用户的所属单位。
	 * 2.取得当前系统中设置的分公司策略，获取分公司列表。
	 * 3.根据当前人公司是否在分公司列表。
	 * 	1.如果存在则返回。
	 *  2.如果不在则往上查找，直到找到顶级组织。
	 * </pre>
	 * @return
	 */
	public String getCurrentCompanyOrgName(){
		SysOrg org = ContextUtil.getCurrentCompany();
		if(org == null){
			return null;
		}
		return ContextUtil.getCurrentCompany().getOrgName();
	}
	
	
	/**
	 * 根据用户的级别ID和角色ID获取对应的人员列表。
	 * @param grade		级别
	 * @param roleId	角色ID
	 * @return
	 */
	public List<SysUser> getByOrgGradeAndRole(int grade,Long roleId){
		SysOrg sysOrg=ContextUtil.getCurrentOrg();
		
		List<SysOrgType> typeList= sysOrgTypeDao.getByDemId(Demension.ADMINSTRATION);
		Map<Long,Integer> map= convertToMap(typeList);
		int curGrade=map.get(sysOrg.getOrgType());
		
		while(grade!=curGrade){
			Long parentOrgId=sysOrg.getOrgSupId();
			if(parentOrgId.equals(SysOrg.BEGIN_ORGID)) break;
			sysOrg= sysOrgService.getById(parentOrgId);
			curGrade=map.get(sysOrg.getOrgType());
		}
		if(curGrade!=grade){
			return new ArrayList<SysUser>();
		}
		List<SysUser> sysUsers= sysUserDao.getByOrgRole(sysOrg.getOrgId(), roleId);
		return sysUsers;
	}
	
	/**
	 * 在流程驳回时清除上下文的数据，让他使用数据库的配置。
	 * 这个方法配置到任务完成事件中。
	 */
	public  void clearNodeUserMap(){
		ProcessCmd cmd=TaskThreadService.getProcessCmd();
		//驳回和驳回到发起人删除preStepUpserApproverOrgId变量。
		if(cmd.isBack()==1 || cmd.isBack()==2){
			HistoryTaskInstanceDao historyTaskInstanceDao=AppUtil.getBean(HistoryTaskInstanceDao.class);
			RuntimeService runtimeService=AppUtil.getBean(RuntimeService.class);
			Long taskId=new Long(cmd.getTaskId());
			HistoricTaskInstanceEntity taskEnt=historyTaskInstanceDao.getById(taskId);
			
			String actInstanceId= taskEnt.getProcessInstanceId();
			
			TaskUserAssignService userAssignService=AppUtil.getBean(TaskUserAssignService.class);
			userAssignService.clearNodeUserMap();
			//移除流程变量。
			runtimeService.removeVariable(actInstanceId, "preStepUpserApproverOrgId");
		}
	}
	
	/**
	 * 判断数据是否在系统中存在。
	 * @param formData BpmFormData
	 * @param fieldName 字段名
	 * @return
	 */
	public void validDataExist(BpmFormData formData,String fieldName,String messages){
		boolean isAdd=formData.isAdd();
		String sql="";
		Object[] aryObj=null;
		String name = (String) formData.getMainField(fieldName);
		if(StringUtil.isNotEmpty(name)){
			name = name.trim();
		}
		//添加
		if(isAdd){
			aryObj=new Object[1];
			aryObj[0]= name;
			sql="select count(*) from " + formData.getFullTableName() +" where " + fieldName +"=?" ;
		}
		//更新数据
		else{
			aryObj=new Object[2];
			aryObj[0] = name;
			aryObj[1] = formData.getPkValue().getValue();
			sql="select  count(*) from " + formData.getFullTableName() +" where " + fieldName +"=? and "
					+formData.getBpmFormTable().getPkField()+"!=?" ;
		}
		Integer rtn= jdbcTemplate.queryForInt(sql, aryObj);
		if(rtn>0){
			if(StringUtil.isNotEmpty(messages)){
				throw new BusDataException(messages);
			}else{
				throw new BusDataException(name +"数据已经存在,请检查表单数据!");
			}
		}
	}
	
	/**
	 * 判断数据是否在系统中存在。
	 * @param formData BpmFormData
	 * @param fieldName 字段名
	 * @return
	 */
	public void validDataExist(BpmFormData formData,String fieldName){
		this.validDataExist(formData, fieldName,null);
	}
	
	private  Set<String> getUserStep(SysOrg startOrg,Long currentUserId,Long orgType){
		SysOrg sysOrg=null;
		Set<String> userSet = new LinkedHashSet<String>();
		List<TaskExecutor> executorList = new ArrayList<TaskExecutor>();
		ProcessCmd cmd = getProcessCmd();
		
		if(cmd==null) return userSet;
		GroovyScriptEngine groovyScriptEngine=AppUtil.getBean(GroovyScriptEngine.class);
		String actInstId=(String) groovyScriptEngine.getVariable("actInstId");
		Long preStepUpserApproverOrgId = (Long)runtimeService.getVariable(actInstId, PreStepUpserApproverOrgId);
		
		//如果是第一次处理  
		//获取发起人的组织的负责人@dec
		if(BeanUtils.isEmpty(preStepUpserApproverOrgId)){
			sysOrg=startOrg;
			//驳回时找回之前的组织ID
			if(cmd.isBack()==1){
				Long approverOrgId = (Long)runtimeService.getVariable(actInstId, "ApproverOrgId");
				sysOrg=sysOrgDao.getById(approverOrgId);
			}
			
			//获取组织的负责人
			Long orgId=sysOrg.getOrgId();
			executorList =getExecutor(orgId);
			
			if(executorList.size()==1){
				TaskExecutor executor=executorList.get(0);
				//如果当前人就是负责人，他需要查找上级的负责人
				if(currentUserId.toString().equals(executor.getExecuteId())){
					if(!sysOrg.getOrgType().equals(orgType)){
						executorList =getExecutor(sysOrg.getOrgSupId());
						orgId=sysOrg.getOrgSupId();
					}
				}
			}
			runtimeService.setVariable(actInstId, PreStepUpserApproverOrgId, orgId);
			runtimeService.setVariable(actInstId, "ApproverOrgId",orgId);
		}else {
			//取上一级组织的负责人
			sysOrg = sysOrgService.getById(preStepUpserApproverOrgId);
			executorList =getExecutor(sysOrg.getOrgSupId());
			runtimeService.setVariable(actInstId, PreStepUpserApproverOrgId, sysOrg.getOrgSupId());
		}
		
		if (BeanUtils.isNotEmpty(executorList)){
			for (TaskExecutor user : executorList) {
				userSet.add(user.getExecuteId());
			}
		}
		return  userSet;
	}
	
	public final static String  PreStepUpserApproverOrgId= "preStepUpserApproverOrgId";
	/**
	 * 逐级审批，获取人员
	 * <pre>
	 * 这个脚本配置到人员脚本。
	 * 逻辑如下：
	 * 1.首先获取上一个审批人的组织ID，根据流程变量preStepUpserApproverOrgId获取。
	 * 2.如果获取不到则表示为第一次审批，那么获取当前人的组织ID,并根据该组织ID获取此组织的负责人，
	 * 	如果负责人和当前人的ID是一致的，那么获取上一个组织的ID，并获取他的负责人，并将此组织ID设置到流程变量preStepUpserApproverOrgId中。
	 * 3.如果从流程变量中获取到了preStepUpserApproverOrgId组织ID,则取该组织父级组织的负责人。
	 * </pre>
	 * preStepUpserApproverOrgId = 上一个组织ID
	 * */
	public Set<String> getUserByStep(Long orgType){
		SysOrg sysOrg=ContextUtil.getCurrentOrg();
		Long currentUserId=ContextUtil.getCurrentUserId();
		Set<String> userSet=getUserStep(sysOrg,currentUserId,orgType);
		return  userSet;
	}
	

	/**
	 * 逐级审批，获取人员
	 * <pre>
	 * 这个脚本配置到人员脚本。
	 * 逻辑如下：
	 * 1.首先获取上一个审批人的组织ID，根据流程变量preStepUpserApproverOrgId获取。
	 * 2.如果获取不到则表示为第一次审批，那么获取当前人的组织ID,并根据该组织ID获取此组织的负责人，
	 * 	如果负责人和当前人的ID是一致的，那么获取上一个组织的ID，并获取他的负责人，并将此组织ID设置到流程变量preStepUpserApproverOrgId中。
	 * 3.如果从流程变量中获取到了preStepUpserApproverOrgId组织ID,则取该组织父级组织的负责人 。
	 * 
	 * preStepUpserApproverOrgId = 上一个组织ID
	 * </pre>
	 * @param fieldName	字段名称	
	 * @param isOrg		是否为组织
	 * 
	 * */
	public Set<String> getUserByFieldStep(String fieldName,Boolean isOrg,Long orgType){
		GroovyScriptEngine groovyScriptEngine=AppUtil.getBean(GroovyScriptEngine.class);
		String actInstId=(String) groovyScriptEngine.getVariable("actInstId");
		
		String str=(String) runtimeService.getVariable(actInstId,fieldName);
		Long objId= Long.parseLong(str);
		SysOrg org=null;
		Long orgId=0L;
		if(isOrg){
			orgId=objId;
		}
		else{
			UserPosition position= userPositionDao.getPrimaryUserPositionByUserId(objId);
			orgId=position.getOrgId();
		}
		org=sysOrgDao.getById(orgId);
		
		Long userId=0L;
		if(!isOrg){
			userId=objId;
		}
		
		Set<String> userSet=getUserStep(org,userId,orgType);
		return  userSet;
	}
	
	
	/**
	 * 获取子表字段作为节点审批人员。
	 * @param tableName	表明
	 * @param field		字段名
	 * @return
	 */
	public Set<String> getSubFieldUser(String tableName,String field){
		Set<String> userSet=new LinkedHashSet<String>();
		ProcessCmd cmd=getProcessCmd();
		Map map= cmd.getFormDataMap();
		String json= (String) map.get("formData");
		JSONObject jsonObj=JSONObject.fromObject(json);
		JSONArray subTable=jsonObj.getJSONArray("sub");
		for(Object tableJson:subTable){
			JSONObject tableObj=(JSONObject)tableJson;
			String tbName=(String) tableObj.get("tableName");
			if(!tableName.equals(tbName)) continue;
			JSONArray jsonRows=tableObj.getJSONArray("fields");
			for(Object obj:jsonRows){
				JSONObject row=(JSONObject)obj;
				String userId=row.getString(field);
				userSet.add(userId);
			}
		}
		return userSet;
	}
	
	/**
	 * 取得子表的数据，从数据库中获取。
	 * @param tableName		表名
	 * @param fieldName		字段名称
	 * @param businessKey	业务主键
	 * @return
	 */
	public Set<String> getSubTableUser(String tableName,String fieldName,String businessKey){
		Set<String> userSet=new LinkedHashSet<String>();
	    List<Map<String,Object>> values = getByFk(tableName,new Long( businessKey));
	    for(Map<String,Object> r : values){
	    	Object userId = r.get( fieldName);
	    	if(userId != null){
	    		userSet.add(userId.toString());
	    	}
	    }
	    return userSet;
	}
	
	
}
