package com.hotent.platform.controller.system;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.encrypt.EncryptUtil;
import com.hotent.core.ldap.model.LdapUser;
import com.hotent.core.ldap.until.UserHelper;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.model.OnlineUser;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.event.def.EventUtil;
import com.hotent.platform.event.def.UserEvent;
import com.hotent.platform.model.system.Demension;
import com.hotent.platform.model.system.OrgAuth;
import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SysUserOrg;
import com.hotent.platform.model.system.SysUserParam;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.model.system.UserPosition;
import com.hotent.platform.model.system.UserRole;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.ldap.LdapUserService;
import com.hotent.platform.service.ldap.SysOrgSyncService;
import com.hotent.platform.service.ldap.SysUserSyncService;
import com.hotent.platform.service.system.DemensionService;
import com.hotent.platform.service.system.OrgAuthService;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.platform.service.system.SysPropertyService;
import com.hotent.platform.service.system.SysUserOrgService;
import com.hotent.platform.service.system.SysUserParamService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.system.UserPositionService;
import com.hotent.platform.service.system.UserRoleService;
import com.hotent.platform.service.system.UserSyncService;
import com.hotent.platform.service.system.UserUnderService;
import com.hotent.platform.service.system.impl.OrgServiceImpl;

/**
 * 对象功能:用户表 控制器类 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:csx 
 * 创建时间:2011-11-28 10:17:09
 */
@Controller
@RequestMapping("/platform/system/sysUser/")
@Action(ownermodel=SysAuditModelType.USER_MANAGEMENT)
public class SysUserController extends BaseController {
	@Resource
	private SysUserService sysUserService;
	@Resource
	private DemensionService demensionService;
	@Resource
	private SubSystemService subSystemService;
	@Resource
	private SysUserParamService sysUserParamService;
	@Resource
	private SysUserOrgService sysUserOrgService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private UserPositionService userPositionService;
	@Resource
	private LdapUserService ldapUserService;
	@Resource
	private SysOrgSyncService sysOrgSyncService;
	@Resource
	private SysUserSyncService sysUserSyncService;
	@Resource
	private UserSyncService userSyncService;
	@Resource
	private OrgAuthService orgAuthService;
	@Resource
	private OrgServiceImpl orgServiceImpl;
	@Resource
	private UserUnderService userUnderService;

		
	@Resource
	Properties configproperties;
	private final String defaultUserImage = "commons/image/default_image_male.jpg";

	/**
	 * 取得用户表分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看用户表分页列表",
			execOrder=ActionExecOrder.AFTER,
			detail="查看用户表分页列表",
			exectype="管理日志")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long userId = RequestUtil.getLong(request, "userId");
		List<SysUser> list = sysUserService.getUsersByQuery(new QueryFilter(request, "sysUserItem"));
		ModelAndView mv = this.getAutoView().addObject("sysUserList", list).addObject("userId", userId);

		return mv;
	}

	/**
	 * 删除用户表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除用户表",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除用户表"+
					"<#list StringUtils.split(userId,\",\") as item>" +
					"<#assign entity=sysUserService.getById(Long.valueOf(item))/>" +
					"【${entity.fullname}】"+
					"</#list>",
					exectype = "管理日志")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultMessage message = null;
		String preUrl = RequestUtil.getPrePage(request);
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "userId");
			delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除用户成功");
		} catch (Exception e) {
			message = new ResultMessage(ResultMessage.Fail, "删除用户失败");
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	private void delByIds(Long[] lAryId){
		if(BeanUtils.isEmpty(lAryId)) return;
		for(Long id:lAryId){
			EventUtil.publishUserEvent(id, UserEvent.ACTION_DEL);
			sysUserService.delById(id);
			sysUserOrgService.delByUserId(id);
			userPositionService.delByUserId(id);
			userRoleService.delByUserId(id);
		}
	}

	@RequestMapping("edit")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		ModelAndView mv= getAutoView();
		mv.addObject("action", "global");
		List<Demension>demensionList=demensionService.getAll();
	//	String returnUrl=RequestUtil.getString(request, "returnUrl", "list.ht");
		return getEditMv(request,mv).addObject("demensionList", demensionList);
	}
	
	@RequestMapping("editGrade")
	public ModelAndView editGrade(HttpServletRequest request) throws Exception {
		ModelAndView mv= new ModelAndView();
		// 获取当前用户所能管理的组
		long userId = ContextUtil.getCurrentUserId();
		List<OrgAuth> orgAuthList = orgAuthService.getByUserId(userId);
		
		mv.setViewName("/platform/system/sysUserEdit.jsp");
		mv.addObject("action", "grade");
		mv.addObject("orgAuthList", orgAuthList);
		return getEditMv(request,mv);
	}
	
	@Action(description = "添加或编辑用户表",
			execOrder=ActionExecOrder.AFTER,
			detail= "添加或编辑用户表" ,
			exectype="管理日志")
	public ModelAndView getEditMv(HttpServletRequest request,ModelAndView mv){
		String returnUrl = RequestUtil.getPrePage(request); 
		Long orgId=RequestUtil.getLong(request, "orgId");
		Long userId = RequestUtil.getLong(request, "userId");
		int bySelf = RequestUtil.getInt(request, "bySelf");
		SysUser sysUser = null;
		if (userId != 0) {
			sysUser = sysUserService.getById(userId);
			List<UserRole> roleList = userRoleService.getByUserId(userId);
			List<UserPosition> userPosList = userPositionService.getByUserId(userId);
			List<SysUser> userUnders=userUnderService.getMyLeaders(userId);
			
			List<String> orgIdList=new ArrayList<String>();
			String orgIds="";
			for(UserPosition up:userPosList){
				orgIdList.add(up.getOrgId().toString());
			}
			 orgIds=StringUtil.getArrayAsString(orgIdList);
			
			mv.addObject("roleList", roleList)
			.addObject("userPosList", userPosList)
			.addObject("orgIds",orgIds)
			.addObject("userUnders",userUnders);
		} else {
			sysUser =new SysUser();
			
		}
		String pictureLoad = defaultUserImage;
		if (sysUser != null) {
			if (StringUtil.isNotEmpty(sysUser.getPicture())) {
				pictureLoad = sysUser.getPicture();
			}
		}
		return mv.addObject("sysUser", sysUser)
				.addObject("userId", userId)
				.addObject("returnUrl", returnUrl)
				.addObject("pictureLoad", pictureLoad)
				.addObject("bySelf",bySelf);
	}

	@RequestMapping("modifyPwdView")
	public ModelAndView modifyPwdView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long userId = RequestUtil.getLong(request, "userId");
		SysUser sysUser = sysUserService.getById(userId);
		return getAutoView().addObject("sysUser", sysUser).addObject("userId",userId);
	}
	
	@RequestMapping("modifyPwd")
	@Action(description = "修改密码",
			execOrder=ActionExecOrder.AFTER,
			detail= "<#assign entity=sysUserService.getById(Long.valueOf(userId))/>" +
					"【${entity.fullname}】修改密码<#if isSuccess> 成功<#else>失败</#if>",
			exectype="管理日志")
	public void modifyPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String primitivePassword =  RequestUtil.getString(request, "primitivePassword");
		String enPassword=EncryptUtil.encryptSha256(primitivePassword);
		String newPassword = RequestUtil.getString(request, "newPassword");
		Long userId = RequestUtil.getLong(request, "userId");
		SysUser sysUser = sysUserService.getById(userId);
		String password=sysUser.getPassword();
		boolean issuccess=false;
		if(StringUtil.isEmpty( newPassword)||StringUtil.isEmpty( primitivePassword)){
			writeResultMessage(response.getWriter(), "输入的密码不能为空", ResultMessage.Fail);	
		}else if(!enPassword.equals(password)){
			writeResultMessage(response.getWriter(), "你输入的原始密码不正确", ResultMessage.Fail);		
		}else if(primitivePassword.equals(newPassword)){
			writeResultMessage(response.getWriter(), "你修改的密码和原始密码相同", ResultMessage.Fail);
		}else{
			try {
				sysUserService.updPwd(userId, newPassword);
				writeResultMessage(response.getWriter(),"修改密码成功", ResultMessage.Success);
				issuccess=true;
			} catch (Exception ex) {
				String str = MessageUtil.getMessage();
				if (StringUtil.isNotEmpty(str)) {
					ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"修改密码失败:" + str);
					response.getWriter().print(resultMessage);
				} else {
					String message = ExceptionUtil.getExceptionMessage(ex);
					ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
					response.getWriter().print(resultMessage);
				}
			}
		}
		SysAuditThreadLocalHolder.putParamerter("isSuccess", issuccess);
	}

	
	@RequestMapping("resetPwdView")
	public ModelAndView resetPwdView(HttpServletRequest request) throws Exception {
		String returnUrl = RequestUtil.getPrePage(request);
		Long userId = RequestUtil.getLong(request, "userId");
		SysUser sysUser = sysUserService.getById(userId);
		return getAutoView().addObject("sysUser", sysUser).addObject("userId", userId).addObject("returnUrl", returnUrl);
	}

	@RequestMapping("resetPwd")
	@Action(description = "重置密码",
			execOrder=ActionExecOrder.AFTER,
			detail= "<#assign entity=sysUserService.getById(Long.valueOf(userId))/>" +
					"【${entity.fullname}】重置密码<#if isSuccess> 成功<#else>失败</#if>",
			exectype="管理日志")
	public void resetPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String password = RequestUtil.getString(request, "password");
		Long userId = RequestUtil.getLong(request, "userId");
		boolean issuccess=true;
		try {
			if(userId.longValue()==1){
				throw new Exception("试用限制，超级管理员不能重置密码");
			}
			sysUserService.updPwd(userId, password);
			writeResultMessage(response.getWriter(), "重置密码成功!", ResultMessage.Success);
		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"重置密码失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
			issuccess=false;
		}
		SysAuditThreadLocalHolder.putParamerter("isSuccess", issuccess);
	}

	@RequestMapping("editStatusView")
	public ModelAndView editStatusView(HttpServletRequest request) throws Exception {
		String returnUrl = RequestUtil.getPrePage(request);
		Long userId = RequestUtil.getLong(request, "userId");
		SysUser sysUser = sysUserService.getById(userId);
		return getAutoView().addObject("sysUser", sysUser).addObject("userId", userId).addObject("returnUrl", returnUrl);
	}

	@RequestMapping("editStatus")
	@Action(description = "设置用户状态",
			execOrder=ActionExecOrder.AFTER,
			detail= "设置用户状态",
			exectype="管理日志")
	public void editStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long userId = RequestUtil.getLong(request, "userId");
		int isLock = RequestUtil.getInt(request, "isLock");
		int status = RequestUtil.getInt(request, "status");
		try {
			sysUserService.updStatus(userId, (short) status, (short) isLock);
			writeResultMessage(response.getWriter(), "修改用户状态成功!", ResultMessage.Success);
		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"修改用户状态失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}

	/**
	 * 取得用户表明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv =this.getAutoView();
		return getView(request, response,mv,0);
	}
	
	/**
	 * 取得用户表明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getByUserId")
	public ModelAndView getByUserId(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mv= new ModelAndView("/platform/system/sysUserGet.jsp");
		mv= getView(request, response,mv,1);
		return mv;
	}

	
	
	/**
	 * 取得用户表明细
	 * 
	 * @param request
	 * @param response
	 * @param isOtherLink  
	 * @return
	 * @throws Exception
	 */
	@Action(description = "查看用户表明细",
			execOrder=ActionExecOrder.AFTER,
			detail= "查看用户表明细",
			exectype="管理日志")
	public ModelAndView getView(HttpServletRequest request, HttpServletResponse response,ModelAndView mv,int isOtherLink) throws Exception {
		long userId = RequestUtil.getLong(request, "userId");
		long canReturn=RequestUtil.getLong(request, "canReturn",0);
		//查看的类型
		String openType = RequestUtil.getString(request, "openType","");
		SysUser sysUser = sysUserService.getById(userId);
		String pictureLoad = defaultUserImage;
		if (sysUser != null) {
			if (StringUtil.isNotEmpty(sysUser.getPicture())) {
				pictureLoad = sysUser.getPicture();
			}
		}
		List<UserRole> roleList = userRoleService.getByUserId(userId);
		List<UserPosition> posList = userPositionService.getByUserId(userId);
		//List<SysUserOrg> orgList = sysUserOrgService.getOrgByUserId(userId);

		//List<UserPosition> userPosList = userPositionService.getByUserId(userId);
		List<UserPosition> userPosList=userPositionService.getOrgListByUserId(userId);
		List<SysUserParam> userParamList = sysUserParamService.getByUserId(userId);
		String returnUrl=RequestUtil.getPrePage(request);
		return mv.addObject("sysUser", sysUser)
				.addObject("roleList", roleList)
				.addObject("posList", posList)
				.addObject("orgList", userPosList)
				//.addObject("userPosList", userPosList)
				.addObject("pictureLoad", pictureLoad)
				.addObject("userParamList", userParamList)
				.addObject("canReturn",canReturn)
				.addObject("returnUrl",returnUrl)
				.addObject("isOtherLink", isOtherLink)
				.addObject("openType", openType);
	}


	/**
	 * 取得用户表分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dialog")
	public ModelAndView dialog(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mv = this.getAutoView();

		List<Demension> demensionList = demensionService.getAll();
		mv.addObject("demensionList", demensionList);
		List<SubSystem> subSystemList = subSystemService.getAll();
		mv.addObject("subSystemList", subSystemList);

		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		mv.addObject("isSingle", isSingle);
		handelUserSoruce(mv);
		return mv;
	}
	
	private void handelUserSoruce(ModelAndView mv) {
		boolean  isShowPos = SysPropertyService.getBooleanByAlias("userDialog.showPos",true);
		boolean  isShowRole = SysPropertyService.getBooleanByAlias("userDialog.showRole",true);
		boolean  isShowOnlineUser = SysPropertyService.getBooleanByAlias("userDialog.showOnlineUser",true);
		
		mv.addObject("isShowPos",isShowPos);
		mv.addObject("isShowRole",isShowRole);
		mv.addObject("isShowOnlineUser",isShowOnlineUser);
	}

	/**
	 * 取得用户表分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("gradeDialog")
	public ModelAndView GradeDialog(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mv = this.getAutoView();
		
		long userId = ContextUtil.getCurrentUserId();
		List<OrgAuth> orgAuthList = orgAuthService.getByUserId(userId);
		mv.addObject("orgAuthList",orgAuthList);
		
		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		mv.addObject("isSingle", isSingle);
		handelUserSoruce(mv);
		return mv;
	}
	
	@RequestMapping("flowDialog")
	public ModelAndView flowDialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();
		List<Demension> demensionList = demensionService.getAll();
		mv.addObject("demensionList", demensionList);
		List<SubSystem> subSystemList = subSystemService.getAll();
		
		mv.addObject("isSingle", "false");
		mv.addObject("subSystemList", subSystemList);
		
		handelUserSoruce(mv);
		return mv;
	}

	@RequestMapping("selector")
	@Action(description = "用户选择器",execOrder=ActionExecOrder.AFTER,detail= "用户选择器",exectype="管理日志")
	public ModelAndView selector(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysUser> list = null;
		ModelAndView result = getAutoView();
		String searchBy = RequestUtil.getString(request, "searchBy");
		String type = RequestUtil.getString(request, "type");
		String typeVal = RequestUtil.getString(request, "typeVal");
		int includSub=RequestUtil.getInt(request, "includSub",0);
		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		QueryFilter queryFilter=new QueryFilter(request, "sysUserItem");
		if (SystemConst.SEARCH_BY_ONL.equals(searchBy)) {
			String demId = RequestUtil.getString(request, "path");
			if (demId.equals("-1")) {//未分配组织的或者没有主组织的用户
				list=sysUserService.getUserNoOrg(queryFilter);
			} else{
				queryFilter.addFilter("isPrimary",1);
				list = sysUserService.getDistinctUserByOrgPath(queryFilter);
			}
			list = sysUserService.getOnlineUser(list);
		//按组织
		} else if (SystemConst.SEARCH_BY_ORG.equals(searchBy)) {
			if(includSub==0){
				list=sysUserService.getDistinctUserByOrgId(queryFilter);
			}else{
				list = sysUserService.getDistinctUserByOrgPath(queryFilter);
			}
		//按岗位
		} else if (SystemConst.SEARCH_BY_POS.equals(searchBy)) {
			if(includSub==0){
				list=sysUserService.getDistinctUserByPosId(queryFilter);
			}else{
				list = sysUserService.getDistinctUserByPosPath(queryFilter);
			}
		//按角色
		} else if (SystemConst.SEARCH_BY_ROL.equals(searchBy)) {
			list = sysUserService.getUserByRoleId(queryFilter);
		} else {
			SysOrg sysOrg = ContextUtil.getCurrentOrg();
			if (StringUtil.isNotEmpty(type)&&!"all".equals(typeVal)&&BeanUtils.isNotEmpty(sysOrg)) {
				String path = orgServiceImpl.getSysOrgByScope(type, typeVal).getPath();
				queryFilter.addFilter("path",path+"%");
				list = sysUserService.getDistinctUserByOrgPath(queryFilter);
			}else {
				list = sysUserService.getUserByQuery(queryFilter);
			}

		}
		List<SysUser> userList=new ArrayList<SysUser>();
		String orgNames="";
		//循环用户
		for(SysUser user:list){
			//获取某用户的组织列表字符串（可能多个组织）
			orgNames=userPositionService.getOrgnamesByUserId(user.getUserId());
			user.setOrgName(orgNames.toString());
			userList.add(user);
		}

		result.addObject("sysUserList", userList);
		result.addObject("isSingle", isSingle);
		result.addObject("type", type);
		result.addObject("typeVal", typeVal);

		return result;
	}

	/**
	 * 获取在线用户树
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getTreeData")
	@ResponseBody
	@Action(description = "用户选择器查询",
			execOrder=ActionExecOrder.AFTER,
			detail= "用户选择器查询",
			exectype="管理日志")
	public List<OnlineUser> getTreeData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<Long, OnlineUser> onlineUsers = AppUtil.getOnlineUsers();
		List<OnlineUser> onlineList = new ArrayList<OnlineUser>();
		for (Long sessionId : onlineUsers.keySet()) {
			OnlineUser onlineUser = new OnlineUser();
			onlineUser = onlineUsers.get(sessionId);
			onlineList.add(onlineUser);
		}
		return onlineList;
	}
	
	

	/**
	 * 获取系统中，来自Ad的用户数据。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("syncList")
	@Action(description = "用户选择器显示",
			execOrder=ActionExecOrder.AFTER,
			detail="用户选择器显示",
			exectype="管理日志")
	public ModelAndView ldapUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean connectable = isLdapConnnectable();
		ModelAndView mv=getAutoView();
		mv.addObject("connectable", connectable);
		if(!connectable){
			return mv;
		}
		QueryFilter queryFilter=new QueryFilter(request, "sysUserMapItem");
		queryFilter.addFilter("fromType", SystemConst.FROMTYPE_AD);
		List<SysUser> sysUserList = sysUserService.getUserByQuery(queryFilter);
//		queryFilter.addFilter("fromType", SysUser.FROMTYPE_AD_SET);
//		sysUserList.addAll(sysUserService.getUserByQuery(queryFilter));

		List<LdapUser> ldapUserList;

		ldapUserList = ldapUserService.getAll();
		if(ldapUserList==null){
			ldapUserList=new ArrayList<LdapUser>();
		}
		List<Map<String,Object>> userMapList=new ArrayList<Map<String,Object>>();
		for(SysUser sysUser:sysUserList){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("sysUser", sysUser);
			LdapUser ldapUser=UserHelper.getEqualUserByAccount(sysUser, ldapUserList);		
			if(ldapUser!=null){//AD未删除
				boolean sync = UserHelper.isSysLdapUsrEqualIngoreOrg(sysUser, ldapUser);
				if(sync){
					map.put("sync", 0);//DB与AD同步
				}else{
					map.put("sync", 1);//DB与AD不同步
				}
			}else{//AD已删除
				map.put("sync", -1);
			}
			userMapList.add(map);
		}
		mv.addObject("sysUserMapList", userMapList);
		return mv;
	}
	
	
	/**
	 * 将指定的系统用户在AD中数据同步到系统中，用户在Ad中数据将覆盖用户在系统中的数据。
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("syncLdap")
	@Action(description = "用户选择器显示",
			execOrder=ActionExecOrder.AFTER,
			detail="用户选择器显示",
			exectype="管理日志")
	public void syncLdap(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultMessage message = null;
		String preUrl=RequestUtil.getPrePage(request);
		Long userId=RequestUtil.getLong(request, "userId");
		SysUser sysUser=sysUserService.getById(userId);
		if(sysUser==null){
			message=new ResultMessage(ResultMessage.Fail, "在数据库中未找到用户数据！");
			addMessage(message, request);
			response.sendRedirect(preUrl);
			return;
		}
		String account=sysUser.getAccount();
		LdapUser ldapUser = ldapUserService.getByAccount(account);
		if(ldapUser==null){
			message=new ResultMessage(ResultMessage.Fail, "在AD中未找到用户数据！");
			addMessage(message, request);
			response.sendRedirect(preUrl);
			return;
		}
		if(!UserHelper.isSysLdapUsrEqualIngoreOrg(sysUser, ldapUser)){
			sysUser.setAccount(ldapUser.getsAMAccountName());
			sysUser.setEmail(ldapUser.getMail());
			String givenName= ldapUser.getGivenName();
			String sn = ldapUser.getSn();
			String fullname= (sn==null?"":sn)+(givenName==null?"":givenName);
			fullname=fullname.equals("")?null:fullname;
			sysUser.setFullname(fullname);
			sysUser.setPhone(ldapUser.getHomePhone());
			sysUser.setMobile(ldapUser.getTelephoneNumber());
			sysUser.setStatus(ldapUser.isAccountDisable()?SystemConst.STATUS_NO:SystemConst.STATUS_OK);
		}
		sysUserService.update(sysUser);
		message=new ResultMessage(ResultMessage.Success, "用户数据与AD同步成功！");
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	/**
	 * 查看用户在系统与Ad中的数据差异
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getLdapComp")
	@Action(description = "查看用户在系统与Ad中的数据差异",
			execOrder=ActionExecOrder.AFTER,
			detail="查看用户在系统与Ad中的数据差异",
			exectype="管理日志")
	public ModelAndView getLdapComp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long userId=RequestUtil.getLong(request, "userId");
		SysUser sysUser=sysUserService.getById(userId);
		String account=sysUser.getAccount();
		LdapUser ldapUser = ldapUserService.getByAccount(account);
		ModelAndView mv=getAutoView();
		mv.addObject("sysUser", sysUser);
		mv.addObject("ldapUser", ldapUser);
		return mv;
	}
	
	@RequestMapping("syncToLdap")
	@Action(description = "查看用户同步",
			execOrder=ActionExecOrder.AFTER,
			detail="查看用户同步",
			exectype="管理日志")
	public ModelAndView syncToLdap(HttpServletRequest request, HttpServletResponse response) throws Exception {
		sysUserSyncService.reset();
		sysOrgSyncService.syncLodapToDb(); 
		int syncNum = sysUserSyncService.syncLodapToDb();
		ModelAndView mv=getAutoView();
		mv.addObject("syncNum", syncNum);
		mv.addObject("lastSyncTakeTime", sysUserSyncService.getLastSyncTakeTime());
		mv.addObject("lastSyncTime", sysUserSyncService.getLastSyncTime());
		mv.addObject("newFromLdapUserList", sysUserSyncService.getNewFromLdapUserList());
		mv.addObject("deleteLocalUserList", sysUserSyncService.getDeleteLocalUserList());
		mv.addObject("updateLocalUserList", sysUserSyncService.getUpdateLocalUserList());
		return mv;
	}
	
	public boolean isLdapConnnectable(){
		try{
			LdapTemplate ldapTemplate = (LdapTemplate) AppUtil.getBean(LdapTemplate.class);
			if(ldapTemplate==null){
				return false;
			}else{
				DistinguishedName base = new  DistinguishedName();
				ldapTemplate.list(base);
				return true;
			}
		}catch (Exception e) {
			return false;
		}
	}
	
	
	/**
	 * 同步AD用户。
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("syncUser")
	public void syncUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultMessage message=new ResultMessage(ResultMessage.Success, "用户数据与AD同步成功！");
		PrintWriter writer=response.getWriter();
		boolean isAvaible=isLdapConnnectable();
		if(!isAvaible){
			message=new ResultMessage(ResultMessage.Fail, "活动目录连接失败,请检查配置是否正确！");
		}
		else{
			try{
				userSyncService.syncUsers();
			}
			catch(Exception ex){
				String msg = ExceptionUtil.getExceptionMessage(ex);
				message = new ResultMessage(ResultMessage.Fail,msg);
			}
		}
		writer.print(message);
	}
	
	@RequestMapping("getUserListByJobId")
	@ResponseBody
	@Action(description="根据职务ID取得用户List")
	public List<SysUser> getUserListByJobId(HttpServletRequest request) throws Exception
	{
		Long jobId=RequestUtil.getLong(request,"jobId");
		 List<SysUser> list=sysUserService.getUserListByJobId(jobId);
		 return list;
	}
	
	@RequestMapping("getUserListByPosId")
	@ResponseBody
	@Action(description="根据岗位ID取得用户List")
	public List<SysUser> getUserListByPosId(HttpServletRequest request) throws Exception
	{
		Long posId=RequestUtil.getLong(request,"posId");
		 List<SysUser> list=sysUserService.getUserListByPosId(posId);
		 return list;
	}
	
}
