package com.hotent.platform.webservice.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.Job;
import com.hotent.platform.model.system.OrgAuth;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysOrgParam;
import com.hotent.platform.model.system.SysParam;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SysUserParam;
import com.hotent.platform.model.system.UserPosition;
import com.hotent.platform.model.system.UserUnder;
import com.hotent.platform.service.system.JobService;
import com.hotent.platform.service.system.OrgAuthService;
import com.hotent.platform.service.system.PositionService;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.platform.service.system.SysOrgParamService;
import com.hotent.platform.service.system.SysOrgRoleService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysParamService;
import com.hotent.platform.service.system.SysRoleService;
import com.hotent.platform.service.system.SysUserParamService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.system.UserPositionService;
import com.hotent.platform.service.system.UserRoleService;
import com.hotent.platform.service.system.UserUnderService;
import com.hotent.platform.webservice.api.UserOrgService;
import com.hotent.platform.webservice.impl.util.GsonUtil;

/**
 * 用户组织数据交互接口
 * 
 */
public class UserOrgServiceImpl implements UserOrgService {
	@Resource
	SysUserService sysUserService;
	@Resource
	SysRoleService sysRoleService;
	@Resource
	SysOrgService sysOrgService;
	@Resource
	PositionService positionService;
	@Resource
	JobService jobService;
	@Resource
	SysParamService sysParamService;
	@Resource
	SysOrgParamService sysOrgParamService;
	@Resource
	SysUserParamService sysUserParamService;
	@Resource
	UserUnderService userUnderService;
	@Resource
	UserRoleService userRoleService;
	@Resource
	SysOrgRoleService sysOrgRoleService;
	@Resource
	OrgAuthService orgAuthService;
	@Resource
	UserPositionService userPositionService;
	@Resource
	SubSystemService subSystemService;
	
	private static List<String> defaultPerms = new ArrayList<String>(Arrays.asList("add","delete","edit"));
	
	/**
	 * 返回接口调用结果
	 * @param success 接口调用是否成功
	 * @return
	 */
	private String getReturn(Boolean success){
		return getReturn(success,null);
	}
	
	/**
	 * 返回接口调用结果
	 * @param success 接口调用是否成功
	 * @param message 消息内容
	 * @return
	 */
	private String getReturn(Boolean success,String message){
		JsonObject json = new JsonObject();
		json.addProperty("result", success?"true":"false");
		if(StringUtil.isNotEmpty(message)){
			json.addProperty("message", message);
		}
		return json.toString();
	}
	
	public String addOrUpdateUser(String userInfo) {
		try{
			SysUser user = GsonUtil.toBean(userInfo, SysUser.class);
			String account = user.getAccount();
			if(StringUtil.isEmpty(account)){
				throw new Exception("必须传入用户账号(account)");
			}
			SysUser existUser = sysUserService.getByAccount(account);
			//该账号已经存在用户，则更新用户信息
			if(BeanUtils.isNotEmpty(existUser)){
				JsonElement sourceJson = GsonUtil.toJsonTree(existUser);
				JsonElement newJson = GsonUtil.parse(userInfo);
				//融合对象
				GsonUtil.merge(sourceJson, newJson);
				SysUser mergeUser = GsonUtil.toBean(sourceJson.toString(), SysUser.class);
				sysUserService.update(mergeUser);
			}
			//不存在该账号的用户，则添加用户
			else{
				user.setUserId(UniqueIdUtil.genId());
				if(BeanUtils.isEmpty(user.getPassword())){
					user.setPassword("");
				}
				if(BeanUtils.isEmpty(user.getIsExpired())){
					user.setIsExpired(new Short("0"));
				}
				if(BeanUtils.isEmpty(user.getIsLock())){
					user.setIsLock(new Short("0"));
				}
				if(BeanUtils.isEmpty(user.getStatus())){
					user.setStatus(new Short("1"));
				}
				if(BeanUtils.isEmpty(user.getSex())){
					user.setSex("1");
				}
				if(BeanUtils.isEmpty(user.getFromType())){
					user.setFromType(new Short("0"));
				}
				sysUserService.add(user);
			}
			return getReturn(true);
		}
		catch (Exception e) {
			return getReturn(false,ExceptionUtils.getRootCauseMessage(e));
		}
	}

	public String deleteUser(String account) {
		try{
			if(StringUtil.isEmpty(account)){
				throw new Exception("必须传入用户账号(account)");
			}
			SysUser user = sysUserService.getByAccount(account);
			if(BeanUtils.isEmpty(user)){
				throw new Exception("该账号的用户不存在");
			}
			sysUserService.delById(user.getUserId());
			return getReturn(true);
		}
		catch (Exception e) {
			return getReturn(false,ExceptionUtils.getRootCauseMessage(e));
		}
	}
	
	public String addOrUpdateRole(String roleInfo) {
		try{
			SysRole role = GsonUtil.toBean(roleInfo, SysRole.class);
			String alias = role.getAlias();
			if(StringUtil.isEmpty(alias)){
				throw new Exception("必须传入角色别名(alias)");
			}
			SysRole existRole = sysRoleService.getByRoleAlias(alias);
			String systemName = role.getSystemName();
			SubSystem subSystem = subSystemService.getByAlias(systemName);
			//该别名已经存在角色，则更新角色信息
			if(BeanUtils.isNotEmpty(existRole)){
				JsonElement sourceJson = GsonUtil.toJsonTree(existRole);
				JsonElement newJson = GsonUtil.parse(roleInfo);
				//融合对象
				GsonUtil.merge(sourceJson, newJson);
				SysRole mergeRole = GsonUtil.toBean(sourceJson.toString(), SysRole.class);
				if (BeanUtils.isNotEmpty(subSystem)) {
					mergeRole.setSystemId(subSystem.getSystemId());
				}
				sysRoleService.update(mergeRole);
			}
			//不存在该别名的角色，则添加角色
			else{
				role.setRoleId(UniqueIdUtil.genId());
				if(BeanUtils.isEmpty(role.getAllowDel())){
					role.setAllowDel(new Short("1"));
				}
				if(BeanUtils.isEmpty(role.getAllowEdit())){
					role.setAllowEdit(new Short("1"));
				}
				if(BeanUtils.isEmpty(role.getEnabled())){
					role.setEnabled(new Short("1"));
				}
				
				if (BeanUtils.isNotEmpty(subSystem)) {
					role.setSystemId(subSystem.getSystemId());
				}else {
					role.setSystemId(1L);
				}
				sysRoleService.add(role);
			}
			return getReturn(true);
		}
		catch (Exception e) {
			return getReturn(false,ExceptionUtils.getRootCauseMessage(e));
		}
	}
	
	public String deleteRole(String alias) {
		try{
			if(StringUtil.isEmpty(alias)){
				throw new Exception("必须传入角色别名(alias)");
			}
			SysRole existRole = sysRoleService.getByRoleAlias(alias);
			if(BeanUtils.isEmpty(existRole)){
				throw new Exception("该别名的角色不存在");
			}
			sysRoleService.delById(existRole.getRoleId());
			return getReturn(true);
		}
		catch (Exception e) {
			return getReturn(false,ExceptionUtils.getRootCauseMessage(e));
		}
	}
	
	public String addOrUpdateOrg(String orgInfo) {
		try{
			SysOrg org = GsonUtil.toBean(orgInfo, SysOrg.class);
			String code = org.getCode();
			String supCode = org.getSupCode();
			Long demId = org.getDemId();
			if(BeanUtils.isEmpty(demId)){
				throw new Exception("必须传入组织所属维度ID(demId)");
			}
			if(StringUtil.isEmpty(code)){
				throw new Exception("必须传入组织代码(code)");
			}
			if(StringUtil.isEmpty(supCode)){
				throw new Exception("必须传入上级组织代码(supCode)");
			}
			SysOrg supOrg = sysOrgService.getByCode(supCode);
			if(BeanUtils.isEmpty(supOrg)){
				throw new Exception("不存在该上级组织代码的组织");
			}
			SysOrg existOrg = sysOrgService.getByCode(code);
		
			//该代码已经存在组织，则更新组织信息
			if(BeanUtils.isNotEmpty(existOrg)){
				JsonElement sourceJson = GsonUtil.toJsonTree(existOrg);
				JsonElement newJson = GsonUtil.parse(orgInfo);
				//融合对象
				GsonUtil.merge(sourceJson, newJson);
				SysOrg mergeOrg = GsonUtil.toBean(sourceJson.toString(), SysOrg.class);
				mergeOrg.setOrgSupId(supOrg.getOrgId());
				mergeOrg.setPath(supOrg.getPath() + mergeOrg.getOrgId() +".");
				mergeOrg.setOrgPathname(supOrg.getOrgPathname() +"/" + mergeOrg.getOrgName());
				sysOrgService.update(mergeOrg);
			}
			//不存在该代码的组织，则添加组织
			else{
				Long id = UniqueIdUtil.genId();
				org.setOrgId(id);
				org.setSn(id);
				org.setPath(supOrg.getPath() + org.getOrgId() +".");
				org.setOrgPathname(supOrg.getOrgPathname() +"/" + org.getOrgName());
				org.setOrgSupId(supOrg.getOrgId());
				//如果未指定组织类型，则使用父组织的组织类型
				if(BeanUtils.isEmpty(org.getOrgType())){
					org.setOrgType(supOrg.getOrgType());
				}
				if(BeanUtils.isEmpty(org.getCreatorId())){
					//默认为管理员添加
					org.setCreatorId(1L);
				}
				sysOrgService.add(org);
			}
			return getReturn(true);
		}
		catch (Exception e) {
			return getReturn(false,ExceptionUtils.getRootCauseMessage(e));
		}
	}
	
	public String deleteOrg(String code) {
		try{
			if(StringUtil.isEmpty(code)){
				throw new Exception("必须传入组织代码(code)");
			}
			SysOrg org = sysOrgService.getByCode(code);
			if(BeanUtils.isEmpty(org)){
				throw new Exception("该代码的组织不存在");
			}
			sysOrgService.delById(org.getOrgId());
			return getReturn(true);
		}
		catch (Exception e) {
			return getReturn(false,ExceptionUtils.getRootCauseMessage(e));
		}
	}
	
	public String addOrUpdatePos(String posInfo) {
		try{
			Position position = GsonUtil.toBean(posInfo, Position.class);
			String posCode = position.getPosCode();
			String orgCode = position.getOrgCode();
			String jobCode = position.getJobCode();
			if(StringUtil.isEmpty(posCode)){
				throw new Exception("必须传入岗位代码(posCode)");
			}
			if(StringUtil.isEmpty(orgCode)){
				throw new Exception("必须传入所属组织代码(orgCode)");
			}
			SysOrg org = sysOrgService.getByCode(orgCode);
			if(BeanUtils.isEmpty(org)){
				throw new Exception("不存在所属组织代码的组织");
			}
			if(StringUtil.isEmpty(jobCode)){
				throw new Exception("必须传入所属职务代码(jobCode)");
			}
			Job job = jobService.getByJobCode(jobCode);
			if(BeanUtils.isEmpty(job)){
				throw new Exception("不存在所属职务代码的职务");
			}
			Position existPosition = positionService.getByPosCode(posCode);
			//该别名已经存在角色，则更新角色信息
			if(BeanUtils.isNotEmpty(existPosition)){
				JsonElement sourceJson = GsonUtil.toJsonTree(existPosition);
				JsonElement newJson = GsonUtil.parse(posInfo);
				//融合对象
				GsonUtil.merge(sourceJson, newJson);
				Position mergePosition = GsonUtil.toBean(sourceJson.toString(), Position.class);
				mergePosition.setOrgId(org.getOrgId());
				mergePosition.setJobId(job.getJobid());
				positionService.update(mergePosition);
			}
			//不存在该别名的角色，则添加角色
			else{
				position.setPosId(UniqueIdUtil.genId());
				position.setOrgId(org.getOrgId());
				position.setJobId(job.getJobid());
				positionService.add(position);
			}
			return getReturn(true);
		}
		catch (Exception e) {
			return getReturn(false,ExceptionUtils.getRootCauseMessage(e));
		}
	}
	
	public String deletePos(String posCode) {
		try{
			if(StringUtil.isEmpty(posCode)){
				throw new Exception("必须传入岗位代码(posCode)");
			}
			Position position = positionService.getByPosCode(posCode);
			if(BeanUtils.isEmpty(position)){
				throw new Exception("不存在该岗位");
			}
			positionService.delById(position.getPosId());
			return getReturn(true);
		}
		catch(Exception e) {
			return getReturn(false,ExceptionUtils.getRootCauseMessage(e));
		}
	}
	
	public String addOrUpdateJob(String jobInfo) {
		try{
			Job job = GsonUtil.toBean(jobInfo, Job.class);
			String jobcode = job.getJobcode();
			if(StringUtil.isEmpty(jobcode)){
				throw new Exception("必须传入职务代码(jobcode)");
			}
			Job existJob = jobService.getByJobCode(jobcode);
			//该职务代码已经存在职务，则更新职务信息
			if(BeanUtils.isNotEmpty(existJob)){
				JsonElement sourceJson = GsonUtil.toJsonTree(existJob);
				JsonElement newJson = GsonUtil.parse(jobInfo);
				//融合对象
				GsonUtil.merge(sourceJson, newJson);
				Job mergeJob = GsonUtil.toBean(sourceJson.toString(), Job.class);
				jobService.update(mergeJob);
			}
			//不存在该代码的职务，则添加职务
			else{
				job.setJobid(UniqueIdUtil.genId());
				String jobName = job.getJobname();
				if (StringUtil.isEmpty(jobName)) {
					throw new Exception("新增时必须传入职务名称(jobName)");
				}
				jobService.add(job);
			}
			return getReturn(true);
		}
		catch (Exception e) {
			return getReturn(false,ExceptionUtils.getRootCauseMessage(e));
		}
	}
	
	public String deleteJob(String jobcode) {
		try{
			if(StringUtil.isEmpty(jobcode)){
				throw new Exception("必须传入职务代码(jobcode)");
			}
			Job job = jobService.getByJobCode(jobcode);
			if(BeanUtils.isEmpty(job)){
				throw new Exception("不存在该职务");
			}
			jobService.delById(job.getJobid());
			return getReturn(true);
		}
		catch(Exception e) {
			return getReturn(false,ExceptionUtils.getRootCauseMessage(e));
		}
	}
	
	public String addOrUpdateParam(String paramInfo) {
		try{
			SysParam sysParam = GsonUtil.toBean(paramInfo, SysParam.class);
			String paramKey = sysParam.getParamKey();
			String dataType = sysParam.getDataType();
			Short effect = sysParam.getEffect();
			if(StringUtil.isEmpty(paramKey)){
				throw new Exception("必须传入参数代码(paramKey)");
			}
			if(StringUtil.isEmpty(dataType)){
				throw new Exception("必须传入参数的数据类型(dataType)");
			}
			if(BeanUtils.isEmpty(effect)){
				throw new Exception("必须传入参数所属类型(effect)");
			}
			SysParam existSysParam = sysParamService.getByParamKey(paramKey);
			//该参数KEY已经存在参数，则更新参数信息
			if(BeanUtils.isNotEmpty(existSysParam)){
				JsonElement sourceJson = GsonUtil.toJsonTree(existSysParam);
				JsonElement newJson = GsonUtil.parse(paramInfo);
				//融合对象
				GsonUtil.merge(sourceJson, newJson);
				SysParam mergeSysParam = GsonUtil.toBean(sourceJson.toString(), SysParam.class);
				sysParamService.update(mergeSysParam);
			}
			//不存在该参数，则添加参数
			else{
				sysParam.setParamId(UniqueIdUtil.genId());
				sysParamService.add(sysParam);
			}
			return getReturn(true);
		}
		catch (Exception e) {
			return getReturn(false,ExceptionUtils.getRootCauseMessage(e));
		}
	}

	
	public String deleteParam(String paramKey) {
		try {
			if (StringUtil.isEmpty(paramKey)) {
				throw new Exception("必须传入参数Key(paramKey)");
			}
			SysParam sysParam = sysParamService.getByParamKey(paramKey);
			if(BeanUtils.isEmpty(sysParam)){
				throw new Exception("不存在该参数");
			}
			sysParamService.delById(sysParam.getParamId());
			return getReturn(true);
		} catch (Exception e) {
			return getReturn(false,ExceptionUtils.getRootCauseMessage(e));
		}
	}

	
	public String getParam() {
		List<SysParam> sysParamList = sysParamService.getAll();
		JsonElement jsonTree = GsonUtil.toJsonTree(sysParamList);
		return jsonTree.toString();
	}

	
	public String editParamValue(String type, String key, String paramValue) {
		try {
			if (StringUtil.isEmpty(type)) {
				throw new Exception("请输入参数的类型,类型 user：用户属性，org：组织属性");
			}
			if (StringUtil.isEmpty(key)) {
				throw new Exception("请输入修改参数值的用户账号(account)或者组织代码(code)！");
			}
			//判断输入修改参数是否为空或者为json数组类型
			JsonElement jsonElement = GsonUtil.parse(paramValue);
			if(!jsonElement.isJsonArray()||jsonElement.isJsonNull()){
				throw new Exception("请输入修改正确的参数值格式：[{paramKey:arg1,paramValue:value1},{...}]");
			}
			List<SysUserParam> userList=null;
			List<SysOrgParam> orgList=null;
			Short patype = SysParam.EFFECT_USER;
			SysUser sysuser =null;
			SysOrg sysOrg = null;
			if ("user".equals(type)) {
				sysuser = sysUserService.getByAccount(key);
				if (BeanUtils.isEmpty(sysuser)) {
					throw new Exception("不存在用户账号(account)为:"+key+"的用户!");
				}
				userList = new ArrayList<SysUserParam>();
			}else if ("org".equals(type)){
				sysOrg = sysOrgService.getByCode(key);
				if (BeanUtils.isEmpty(sysuser)) {
					throw new Exception("不存在组织代码(code)为:"+key+"的组织!");
				}
				orgList = new ArrayList<SysOrgParam>();
				patype = SysParam.EFFECT_ORG;
			}else {
				throw new Exception("参数的类型:"+type+",不符合要求!请检查(类型 user：用户属性，org：组织属性)");
			}
			
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			
			for (JsonElement jsonElement2 : jsonArray) {
				JsonObject asJsonObject = jsonElement2.getAsJsonObject();
				JsonElement keyElement = asJsonObject.get("paramKey");
				JsonElement valueElement = asJsonObject.get("paramValue");
				if (!keyElement.isJsonPrimitive() || !valueElement.isJsonPrimitive()) {
					throw new Exception("请输入修改正确的参数值格式：[{paramKey:\"arg1\",paramValue:\"value1\"},{...}]");
				}
				String paramKey = keyElement.getAsString();
				SysParam sysParam = sysParamService.getByParamKey(paramKey);
				if (BeanUtils.isEmpty(sysParam)) {
					throw new Exception("参数Key:"+paramKey+"对应的参数不存在,请检查清楚!");
				}
				Date paramDate = new Date();
				Long paramLong = 0L;
				String paramString = "";
				String dataType = sysParam.getDataType();
				if (SysParam.DATA_TYPE_MAP.get(dataType) != null
						&& SysParam.DATA_TYPE_MAP.get(dataType).equals("数字")) {
					try {
						 paramDate  = GsonUtil.toBean(valueElement.toString(), Date.class);
					} catch (Exception e) {
						throw new Exception("参数Key:"+type+"的值类型为Date,请检查paramValue格式为：yyyy-MM-dd HH:mm:ss形式");
					}
				} else if (SysParam.DATA_TYPE_MAP.get(dataType) != null
						&& SysParam.DATA_TYPE_MAP.get(dataType).equals("日期")) {
					paramLong = valueElement.getAsLong();
				}else {
					paramString = valueElement.getAsString();
				}
				
				if (patype==SysParam.EFFECT_USER) {
					SysUserParam sysUserParam = new SysUserParam();
					sysUserParam.setValueId(UniqueIdUtil.genId());
					sysUserParam.setParamId(sysParam.getParamId());
					sysUserParam.setUserId(sysuser.getUserId());
					sysUserParam.setParamValue(paramString);
					sysUserParam.setParamIntValue(paramLong);
					sysUserParam.setParamDateValue(paramDate);
					userList.add(sysUserParam);
					
				}else {
					SysOrgParam sysOrgParam = new SysOrgParam();
					sysOrgParam.setValueId(UniqueIdUtil.genId());
					sysOrgParam.setParamId(sysParam.getParamId());
					sysOrgParam.setOrgId(sysOrg.getOrgId());
					sysOrgParam.setParamValue(paramString);
					sysOrgParam.setParamIntValue(paramLong);
					sysOrgParam.setParamDateValue(paramDate);
					orgList.add(sysOrgParam);
					
				}
			}
			if (patype==SysParam.EFFECT_USER) {
				sysUserParamService.add(sysuser.getUserId(), userList);
			}else {
				sysOrgParamService.add(sysOrg.getOrgId(), orgList);
			}
			return getReturn(true);
		} catch (Exception e) {
			return getReturn(false, ExceptionUtils.getRootCauseMessage(e));
		}
	}

	
	
	public String addUserRel(String upUserAccount, String downUserAccount) {
		try {
			if (StringUtil.isEmpty(upUserAccount) || StringUtil.isEmpty(downUserAccount)) {
				throw new Exception("请输入参数upUserAccount 或者 downUserAccount");
			}
			if (upUserAccount.equals(downUserAccount)) {
				throw new Exception("不能添加自己为下属!");
			}
			SysUser upSysUser = sysUserService.getByAccount(upUserAccount);
			if (BeanUtils.isEmpty(upSysUser)) {
				throw new Exception("输入的 upUserAccount:"+upUserAccount+" 没有对应的用户!请检查清楚再输入");
			}
			List<UserUnder> userUnders = new ArrayList<UserUnder>();
			String[] downUserAccountArray = downUserAccount.split(",");
			for (int i = 0; i < downUserAccountArray.length; i++) {
				String userAccount = downUserAccountArray[i];
				SysUser downSysUser = sysUserService.getByAccount(userAccount);
				if (BeanUtils.isEmpty(downSysUser)) {
					throw new Exception("输入的 downUserAccount:"+userAccount+" 没有对应的用户!请检查清楚再输入");
				}
				boolean isExist = userUnderService.getByUpAndDown(upSysUser.getUserId(),downSysUser.getUserId());
				if (isExist) continue ; //存在这不再添加
				UserUnder userUnder = new UserUnder();
				userUnder.setId(UniqueIdUtil.genId());
				userUnder.setUserid(upSysUser.getUserId());
				userUnder.setUnderuserid(downSysUser.getUserId());
				userUnder.setUnderusername(downSysUser.getFullname());
				userUnders.add(userUnder);
			}
		
			for (UserUnder userUnder : userUnders) {
				userUnderService.add(userUnder);
			}
			return getReturn(true);
		} catch (Exception e) {
			return getReturn(false, ExceptionUtils.getRootCauseMessage(e));
		}
	}

	
	public String removeUserRel(String upUserAccount, String downUserAccount) {
		try {
			if (StringUtil.isEmpty(upUserAccount) ) {
				throw new Exception("请输入参数upUserAccount");
			}
			SysUser sysUser = sysUserService.getByAccount(upUserAccount);
			if (BeanUtils.isEmpty(sysUser)) {
				throw new Exception("输入的 upUserAccount:"+upUserAccount+" 没有对应的用户!请检查清楚再输入");
			}
			if (StringUtil.isEmpty(downUserAccount)) {
				userUnderService.delByUpUserId(sysUser.getUserId());
				return getReturn(true);
			}
			String[] downUserAccountArray = downUserAccount.split(",");
			List<SysUser> downUserList = new ArrayList<SysUser>();
			for (int i = 0; i < downUserAccountArray.length; i++) {
				String userAccount = downUserAccountArray[i];
				SysUser downSysUser = sysUserService.getByAccount(userAccount);
				if (BeanUtils.isEmpty(downSysUser)) {
					throw new Exception("输入的 downUserAccount:"+userAccount+" 没有对应的用户!请检查清楚再输入");
				}
				downUserList.add(downSysUser);
			}
			for (SysUser downSysUser : downUserList) {
				userUnderService.delByUpAndDown(sysUser.getUserId(),downSysUser.getUserId());
			}
			return getReturn(true);
		} catch (Exception e) {
			return getReturn(false, ExceptionUtils.getRootCauseMessage(e));
		}
	}

	

	public String addUserRoleRel(String userAccount, String roleAlias) {
		try {
			if (StringUtil.isEmpty(userAccount)||StringUtil.isEmpty(roleAlias)) {
				throw new Exception("请输入参数userAccount或者roleAlias");
			}
			String[] userAccountArray = userAccount.split(",");//转为数组
			String[] roleAliasArray = roleAlias.split(",");//转为数组
			List<SysUser> sysUserList = new ArrayList<SysUser>();//保存正确的sysUser
			List<SysRole> sysRoleList = new ArrayList<SysRole>();//保存正确的SysRole
			
			//验证输入的用户账号是否都存在对应的数据
			for (String account : userAccountArray) {
				SysUser sysUser = sysUserService.getByAccount(account);
				if (BeanUtils.isEmpty(sysUser)) {
					throw  new Exception("输入的 userAccount:"+account+" 没有对应的用户!请检查清楚再输入");
				}
				sysUserList.add(sysUser);
			}
			//验证输入的角色别名是否都存在对应的数据
			for (String alias : roleAliasArray) {
				SysRole sysRole = sysRoleService.getByRoleAlias(alias);
				if (BeanUtils.isEmpty(sysRole)) {
					throw new Exception("输入的 roleAlias:"+alias+" 没有对应的角色!请检查清楚再输入");
				}
				sysRoleList.add(sysRole);
			}
			
			//需要添加的用户转为用户Id数组
			Long[] userIds = new Long[sysUserList.size()];
			for (int i = 0; i < sysUserList.size(); i++) {
				userIds[i]=sysUserList.get(i).getUserId();
			}
			
			for (SysRole sysRole : sysRoleList) {
				userRoleService.add(sysRole.getRoleId(), userIds);
			}
			return getReturn(true);
		} catch (Exception e) {
			return getReturn(false, ExceptionUtils.getRootCauseMessage(e));
		}
	}

	
	public String removeUserRoleRel(String userAccount, String roleAlias) {
		try {
			if (StringUtil.isEmpty(userAccount)&&StringUtil.isEmpty(roleAlias) ) {
				throw new Exception("参数不能同时为空,请输入userAccount或者roleAlias!");
			}
			String[] userAccountArray = userAccount.split(",");//转为数组
			String[] roleAliasArray = roleAlias.split(",");//转为数组
			List<SysUser> sysUserList = new ArrayList<SysUser>();//保存正确的sysUser
			List<SysRole> sysRoleList = new ArrayList<SysRole>();//保存正确的SysRole
			
			//验证输入的用户账号是否都存在对应的数据
			for (String account : userAccountArray) {
				SysUser sysUser = sysUserService.getByAccount(account);
				if (BeanUtils.isEmpty(sysUser)) {
					throw  new Exception("输入的 userAccount:"+account+" 没有对应的用户!请检查清楚再输入");
				}
				sysUserList.add(sysUser);
			}
			//验证输入的角色别名是否都存在对应的数据
			for (String alias : roleAliasArray) {
				SysRole sysRole = sysRoleService.getByRoleAlias(alias);
				if (BeanUtils.isEmpty(sysRole)) {
					throw new Exception("输入的 roleAlias:"+alias+" 没有对应的角色!请检查清楚再输入");
				}
				sysRoleList.add(sysRole);
			}
			//当用户列表为空，则清除角色下的所有用户角色关系
			if (BeanUtils.isEmpty(sysUserList)) {
				for (SysRole sysRole : sysRoleList) {
					userRoleService.delByRoleId(sysRole.getRoleId());
				}
				return getReturn(true);
			}
			//当角色列表为空，则清除角色下的所有用户角色关系
			if (BeanUtils.isEmpty(sysRoleList)) {
				for (SysUser sysUser:sysUserList) {
					userRoleService.delByUserId(sysUser.getUserId());
				}
				return getReturn(true);
			}
			
			for (SysRole sysRole : sysRoleList) {
				for (SysUser sysUser : sysUserList) {
					userRoleService.delByUserIdAndRoleId(sysUser.getUserId(),sysRole.getRoleId());
				}
			}
			return getReturn(true);
		} catch (Exception e) {
			return getReturn(false, ExceptionUtils.getRootCauseMessage(e));
		}
	}

	
	public String addUserOrgRel(String userAccount, String orgCode,String userPerms,String orgPerms, String roleAlias) {
		try {
			if (StringUtil.isEmpty(userAccount)&& StringUtil.isEmpty(orgCode) ) {
				throw new Exception("请输入参数userAccount和orgCode");
			}
			SysUser sysUser = sysUserService.getByAccount(userAccount);
			if (BeanUtils.isEmpty(sysUser)) {
				throw new Exception("输入的 userAccount:"+userAccount+" 没有对应的用户!请检查清楚再输入");
			}
			SysOrg sysOrg = sysOrgService.getByCode(orgCode);
			if (BeanUtils.isEmpty(sysOrg)) {
				throw new Exception("输入的 orgCode:"+orgCode+" 没有对应的组织!请检查清楚再输入");
			}
			List<SysRole> sysRoleList = new ArrayList<SysRole>();
			if(StringUtil.isNotEmpty(roleAlias)){
				String[] roleAliasArray = roleAlias.split(",");
				for (int i = 0; i < roleAliasArray.length; i++) {
					String roleAliasString = roleAliasArray[i];
					SysRole sysRole = sysRoleService.getByRoleAlias(roleAliasString);
					if (BeanUtils.isEmpty(sysRole)) {
						throw new Exception("输入的 roleAlias:"+roleAliasString+" 没有对应的角色!请检查清楚再输入");
					}
					sysRoleList.add(sysRole);
				}
			}
			Long[] roleIds = null;
			if (BeanUtils.isNotEmpty(sysRoleList)) {
				roleIds = new Long[sysRoleList.size()];
				for (int i = 0 ;i<sysRoleList.size();i++) {
					roleIds[i]=sysRoleList.get(i).getRoleId();
				}
			}
			
			
			userPerms=this.handPerms(userPerms);
			orgPerms=this.handPerms(orgPerms);
			
			long usrId = sysUser.getUserId();
			long orgId = sysOrg.getOrgId();
			OrgAuth orgAuth = orgAuthService.getByUserIdAndOrgId(usrId, orgId);
			if (BeanUtils.isEmpty(orgAuth)) {
				orgAuth = new OrgAuth();
				orgAuth.setId(UniqueIdUtil.genId());
				orgAuth.setUserId(usrId);
				orgAuth.setUserPerms(userPerms);
				orgAuth.setOrgId(orgId);
				orgAuth.setOrgPerms(orgPerms);
				orgAuth.setDimId(sysOrg.getDemId());
				orgAuthService.add(orgAuth,roleIds);
			}else {
				orgAuth.setUserPerms(userPerms);
				orgAuth.setOrgPerms(orgPerms);
				orgAuthService.update(orgAuth,roleIds);
			}
			return getReturn(true);
		} catch (Exception e) {
			return getReturn(false, ExceptionUtils.getRootCauseMessage(e));
		}
	}
	
	private  String handPerms(String perms)throws Exception {
		if (StringUtil.isEmpty(perms))return "add";
			String[] PermsAry = perms.split(",");
			List<String> existPerms = new ArrayList<String>();
			for (int i = 0; i < PermsAry.length; i++) {
				if(!defaultPerms.contains(PermsAry[i])){
					throw new Exception(perms+"包含了非法的权限:"+PermsAry[i]+"!权限分别为'add,delete,edit'");
				}
				if (StringUtil.isNotEmpty(PermsAry[i])&&!existPerms.contains(PermsAry[i])) {
					existPerms.add(PermsAry[i]);
				}
			}
			StringBuffer su = new StringBuffer();
			for (String permsString : existPerms) {
				su.append(permsString);
				su.append(",");
			}
			String result = su.toString().substring(0, su.length()-1);
		return result;
	}
	

	
	public String removeUserOrgRel(String userAccount, String orgCode) {
		try {
			if (StringUtil.isEmpty(userAccount)&& StringUtil.isEmpty(orgCode) ) {
				throw new Exception("请输入参数userAccount和orgCode");
			}
			SysUser sysUser = sysUserService.getByAccount(userAccount);
			if (BeanUtils.isEmpty(sysUser)) {
				throw new Exception("输入的 userAccount:"+userAccount+" 没有对应的用户!请检查清楚再输入");
			}
			SysOrg sysOrg = sysOrgService.getByCode(orgCode);
			if (BeanUtils.isEmpty(sysOrg)) {
				throw new Exception("输入的 orgCode:"+orgCode+" 没有对应的组织!请检查清楚再输入");
			}
			long userId = sysUser.getUserId();
			long orgId = sysOrg.getOrgId();
			OrgAuth orgAuth = orgAuthService.getByUserIdAndOrgId(userId,orgId);
			if (BeanUtils.isEmpty(orgAuth)) {
				throw new Exception("输入的 orgCode:"+orgCode+" 和userAccount:"+userAccount+"没有对应的组织分级授权");
			}
			orgAuthService.delById(orgAuth.getId());
			return getReturn(true);
		} catch (Exception e) {
			return getReturn(false, ExceptionUtils.getRootCauseMessage(e));
		}
	}

	
	public String addUserPosRel(String userAccount, String posCode,String orgType , String posType) {
		try {
			if (StringUtil.isEmpty(userAccount) || StringUtil.isEmpty(posCode) ) {
				throw new Exception("请输入参数userAccount和posCode");
			}
			SysUser sysUser = sysUserService.getByAccount(userAccount);
			if (BeanUtils.isEmpty(sysUser)) {
				throw new Exception("输入的 userAccount:"+userAccount+" 没有对应的用户!请检查清楚再输入");
			}
			Position position = positionService.getByPosCode(posCode);
			if (BeanUtils.isEmpty(position)) {
				throw new Exception("输入的 posCode:"+posCode+" 没有对应的岗位!请检查清楚再输入");
			}
			long userId = sysUser.getUserId();
			long posId = position.getPosId();
			//根据UserId获取数据库中没有标记删除的用户岗位信息
			List<UserPosition> userPositionList = userPositionService.getByUserId(userId);
			boolean isAdd = true;
			UserPosition  userPositionTemp = null;
			for(UserPosition userPosition : userPositionList){
				boolean isExist =posId==userPosition.getPosId()?true:false;
				if(isExist){
					isAdd = false;//存在则改为false 表示更新
					userPositionTemp = userPosition;
				}
			}
			short isPrimary =(short) 0;
			if ("main".equals(posType)) {
				isPrimary = (short) 1;
			}
			short isCharge =(short) 0;
			if ("main".equals(orgType)) {
				isCharge = (short) 1;
			}
			
			if (isAdd) {
				if (isCharge==1) {
					//如果是添加的，而且是主岗位则更新其他为不是主岗位
					for (UserPosition userPosition : userPositionList) {
						userPosition.setIsCharge(UserPosition.CHARRGE_NO);
						userPositionService.update(userPosition);
					}
				}
				userPositionTemp = new UserPosition();
				userPositionTemp.setUserPosId(UniqueIdUtil.genId());
				userPositionTemp.setOrgId(position.getOrgId());
				userPositionTemp.setPosId(position.getPosId());
				userPositionTemp.setUserId(sysUser.getUserId());
				userPositionTemp.setJobId(position.getJobId());
				userPositionTemp.setIsDelete(UserPosition.DELETE_NO);
				userPositionTemp.setIsCharge(isCharge);
				userPositionTemp.setIsPrimary(isPrimary);
				userPositionService.add(userPositionTemp);
				
			}else {
				if (isCharge==1) {
					//更新时，在list中移除了需要更新的对象
					userPositionList.remove(userPositionTemp);
					//如果是更新的，而且是主岗位则更新其他为不是主岗位
					for (UserPosition userPosition : userPositionList) {
						userPosition.setIsCharge(UserPosition.CHARRGE_NO);
						userPositionService.update(userPosition);
					}
				}
				userPositionTemp.setIsCharge(isCharge);
				userPositionTemp.setIsPrimary(isPrimary);
				userPositionService.update(userPositionTemp);
			}
			return getReturn(true);
		} catch (Exception e) {
			e.printStackTrace();
			return getReturn(false, ExceptionUtils.getRootCauseMessage(e));
		}
	}

	
	public String removeUserPosRel(String userAccount, String posCode) {
		try {
			if (StringUtil.isEmpty(userAccount) || StringUtil.isEmpty(posCode) ) {
				throw new Exception("请输入参数userAccount和posCode");
			}
			SysUser sysUser = sysUserService.getByAccount(userAccount);
			if (BeanUtils.isEmpty(sysUser)) {
				throw new Exception("输入的 userAccount:"+userAccount+" 没有对应的用户!请检查清楚再输入");
			}
			Position position = positionService.getByPosCode(posCode);
			if (BeanUtils.isEmpty(position)) {
				throw new Exception("输入的 posCode:"+posCode+" 没有对应的岗位!请检查清楚再输入");
			}
			userPositionService.delByUserIdAndPosId(sysUser.getUserId(),position.getPosId());
			return getReturn(true);
		} catch (Exception e) {
			return getReturn(false, ExceptionUtils.getRootCauseMessage(e));
		}
	}

	
	public String addOrgRoleRel(String orgCode, String roleAlias) {
		try {
			
			
			if (StringUtil.isEmpty(orgCode)||StringUtil.isEmpty(roleAlias)) {
				throw new Exception("请输入参数orgCode或者roleAlias");
			}
			String[] orgCodeArray = orgCode.split(",");//转为数组
			String[] roleAliasArray = roleAlias.split(",");//转为数组
			List<SysOrg> sysOrgList = new ArrayList<SysOrg>();//保存正确的SysOrg
			List<SysRole> sysRoleList = new ArrayList<SysRole>();//保存正确的SysRole
			
			//验证输入的组织代码是否都存在对应的数据
			for (String code : orgCodeArray) {
				SysOrg sysOrg = sysOrgService.getByCode(code);
				if (BeanUtils.isEmpty(sysOrg)) {
					throw  new Exception("输入的 orgCode:"+code+" 没有对应的组织!请检查清楚再输入");
				}
				sysOrgList.add(sysOrg);
			}
			//验证输入的角色别名是否都存在对应的数据
			for (String alias : roleAliasArray) {
				SysRole sysRole = sysRoleService.getByRoleAlias(alias);
				if (BeanUtils.isEmpty(sysRole)) {
					throw new Exception("输入的 roleAlias:"+alias+" 没有对应的角色!请检查清楚再输入");
				}
				sysRoleList.add(sysRole);
			}
			
			//需要添加的用户转为角色Id数组
			Long[] roles = new Long[sysRoleList.size()];
			for (int i = 0; i < sysRoleList.size(); i++) {
				roles[i]=sysRoleList.get(i).getRoleId();
			}
			for (SysOrg sysOrg : sysOrgList) {
				sysOrgRoleService.addOrgRole(roles, sysOrg.getOrgId(), 1);
			}
			return getReturn(true);
		} catch (Exception e) {
			return getReturn(false, ExceptionUtils.getRootCauseMessage(e));
		}
	}

	
	public String removeOrgRoleRel(String orgCode, String roleAlias) {
		try {
			
			if (StringUtil.isEmpty(orgCode)&&StringUtil.isEmpty(roleAlias) ) {
				throw new Exception("参数不能同时为空,请输入orgCode或者roleCode!");
			}
			String[] orgCodeArray = orgCode.split(",");//转为数组
			String[] roleAliasArray = roleAlias.split(",");//转为数组
			List<SysOrg> sysOrgList = new ArrayList<SysOrg>();//保存正确的SysOrg
			List<SysRole> sysRoleList = new ArrayList<SysRole>();//保存正确的SysRole
			
			//验证输入的组织代码是否都存在对应的数据
			for (String code : orgCodeArray) {
				SysOrg sysOrg = sysOrgService.getByCode(code);
				if (BeanUtils.isEmpty(sysOrg)) {
					throw  new Exception("输入的 orgCode:"+code+" 没有对应的组织!请检查清楚再输入");
				}
				sysOrgList.add(sysOrg);
			}
			//验证输入的角色别名是否都存在对应的数据
			for (String alias : roleAliasArray) {
				SysRole sysRole = sysRoleService.getByRoleAlias(alias);
				if (BeanUtils.isEmpty(sysRole)) {
					throw new Exception("输入的 roleAlias:"+alias+" 没有对应的角色!请检查清楚再输入");
				}
				sysRoleList.add(sysRole);
			}
			//当组织列表为空，则清除角色下的所有组织角色关系
			if (BeanUtils.isEmpty(sysOrgList)) {
				for (SysRole sysRole : sysRoleList) {
					sysOrgRoleService.delByRoleId(sysRole.getRoleId());
				}
				return getReturn(true);
			}
			//当角色列表为空，则清除角色下的所有用户角色关系
			if (BeanUtils.isEmpty(sysRoleList)) {
				for (SysOrg sysOrg:sysOrgList) {
					sysOrgRoleService.delByOrgId(sysOrg.getOrgId());
				}
				return getReturn(true);
			}
			
			//两个都不为空
			for (SysRole sysRole : sysRoleList) {
				for (SysOrg sysOrg : sysOrgList) {
					sysOrgRoleService.delByOrgIdAndRoleId(sysOrg.getOrgId(),sysRole.getRoleId());
				}
			}
			return getReturn(true);
		} catch (Exception e) {
			return getReturn(false, ExceptionUtils.getRootCauseMessage(e));
		}
	}
}
