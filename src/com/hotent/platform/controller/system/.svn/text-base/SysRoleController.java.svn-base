package com.hotent.platform.controller.system;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.platform.service.system.SysOrgRoleService;
import com.hotent.platform.service.system.SysRoleService;
import com.hotent.platform.service.system.UserRoleService;

/**
 * 对象功能:系统角色表 控制器类 开发公司:广州宏天软件有限公司 开发人员:csx 创建时间:2011-11-28 11:39:03
 */
@Controller
@RequestMapping("/platform/system/sysRole/")
@Action(ownermodel=SysAuditModelType.USER_MANAGEMENT)
public class SysRoleController extends BaseController {
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SubSystemService subSystemService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private SysOrgRoleService sysOrgRoleService;
	

	/**
	 * 角色对话框的展示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description="角色对话框显示",
			execOrder=ActionExecOrder.AFTER,
			detail="角色对话框显示",
			exectype = "管理日志")
	public ModelAndView selector(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		boolean isGrade = RequestUtil.getBoolean(request, "isGrade");
		QueryFilter queryFilter = new QueryFilter(request, "sysRoleItem");
		String roleName = RequestUtil.getString(request, "Q_roleName_S");
		if(StringUtil.isNotEmpty(roleName)){
			queryFilter.addFilter("roleName","%"+ roleName+"%");
		}
		List<SysRole> list = null;
		if(isGrade){
			list = sysRoleService.getUserAssignRole(queryFilter);
			queryFilter.setForWeb(); 
		}else{
			list = sysRoleService.getAll(queryFilter);
		}
		ModelAndView mv = this.getAutoView()
				.addObject("sysRoleList", list)
				.addObject("isSingle", isSingle)
				.addObject("isGrade", isGrade); 
		return mv;
	}

	/**
	 * 取得系统角色表分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看系统角色表分页列表",
			execOrder=ActionExecOrder.AFTER,
			detail="查看系统角色表分页列表",
			exectype = "管理日志")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysRole> list = sysRoleService.getRoleList(new QueryFilter(request, "sysRoleItem"));
		ModelAndView mv = this.getAutoView().addObject("sysRoleList", list);
		SysUser current_user=ContextUtil.getCurrentUser();
		boolean isAdmin =current_user.getAccount().equals(SysUser.getAdminAccount());
		mv.addObject("isAdmin", isAdmin);
		return mv;
	}

	/**
	 * 删除系统角色表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除系统角色表",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除系统角色表"+
					"<#list StringUtils.split(roleId,\",\") as item>" +
					"<#assign entity=sysRoleService.getById(Long.valueOf(item))/>" +
					"【${entity.roleName}】"+
					"</#list>",
					exectype = "管理日志")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		SysUser current_user=ContextUtil.getCurrentUser();
		List<SysRole>userroles= sysRoleService.getByUserId(current_user.getUserId());
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "roleId");
			for(SysRole userrole : userroles){
				int index=Arrays.binarySearch(lAryId,userrole.getRoleId());
				if(index < 0){
					delByIds(lAryId);
					message = new ResultMessage(ResultMessage.Success, "删除系统角色成功");
				}else{
					message = new ResultMessage(ResultMessage.Fail, "不能删除自身所在角色，删除系统角色失败");
				}
			}
		} catch (Exception e) {
			message = new ResultMessage(ResultMessage.Fail, "删除系统角色失败:" + e.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	private void delByIds(Long[] lAryId){
		if(BeanUtils.isEmpty(lAryId)) return;
		for(Long id:lAryId){
			sysRoleService.delById(id);
			userRoleService.delByRoleId(id);
			sysOrgRoleService.delByRoleId(id);
		}
	}

	@RequestMapping("copy")
	public ModelAndView copy(HttpServletRequest request) throws Exception {
		Long roleId = RequestUtil.getLong(request, "roleId");

		SysRole sysRole = sysRoleService.getById(roleId);
		Long systemId = sysRole.getSystemId();
		// 如果系统id不为空，则将角色别名系统前缀替换掉。
		if (systemId != null && systemId !=0) {
			SubSystem subSystem = subSystemService.getById(systemId);
			String sysAlias = subSystem.getAlias();
			String roleAlias = sysRole.getAlias().replaceFirst(sysAlias + "_", "");
			sysRole.setAlias(roleAlias);
		}
		return getAutoView().addObject("sysRole", sysRole);
	}

	/**
	 * 编辑系统角色
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "添加或编辑系统角色表",
			execOrder=ActionExecOrder.AFTER,
			detail="添加或编辑系统角色表",
			exectype="管理日志")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long roleId = RequestUtil.getLong(request, "roleId");
		String returnUrl = RequestUtil.getPrePage(request);
		SysRole sysRole = null;
		if (roleId != 0) {
			sysRole = sysRoleService.getById(roleId);
			Long systemId = sysRole.getSystemId();
			// 如果系统id不为空，则将角色别名系统前缀替换掉。
			if (systemId != null && systemId !=0) {
				SubSystem subSystem = subSystemService.getById(systemId);
				String sysAlias = subSystem.getAlias();
				String roleAlias = sysRole.getAlias().replaceFirst(sysAlias + "_", "");
				sysRole.setAlias(roleAlias);
			}
		} else {
			sysRole = new SysRole();
		}
		List<SubSystem> list = subSystemService.getActiveSystem();
		return getAutoView().addObject("sysRole", sysRole).addObject("subsystemList", list).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得系统角色表明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv=this.getAutoView();
		return getView(request, response, mv, 0);
	}
	
	/**
	 * 取得系统角色表明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getByRoleId")
	public ModelAndView getByRoleId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv=new ModelAndView("/platform/system/sysRoleGet.jsp");
		return getView(request, response, mv, 1);
	}
	
	/**
	 * 取得系统角色表明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Action(description = "查看系统角色表明细",
			execOrder=ActionExecOrder.AFTER,
			detail="查看系统角色表明细"+
					"<#assign entity=sysRoleService.getById(Long.valueOf(roleId))/>" +
					"【${entity.roleName}】",
			exectype="管理日志")
	public ModelAndView getView(HttpServletRequest request, HttpServletResponse response,ModelAndView mv ,int isOtherLink) throws Exception {
		long id = RequestUtil.getLong(request, "roleId");
		SysRole sysRole = sysRoleService.getById(id);
		SubSystem subsystem = null;
		if (sysRole.getSystemId() != null) {
			subsystem = subSystemService.getById(sysRole.getSystemId());
		}

		return mv.addObject("sysRole", sysRole).addObject("subsystem", subsystem).addObject("isOtherLink",isOtherLink);
	}

	/**
	 * 复制角色
	 * 
	 * @param request
	 * @param response
	 * @param po
	 * @throws Exception
	 */
	@RequestMapping("copyRole")
	@Action(description = "复制角色",
			execOrder=ActionExecOrder.AFTER,
			detail="复制角色"+
					"<#assign entity=sysRoleService.getById(Long.valueOf(roleId))/>" +
					"【${entity.roleName}】<#if isSuccess>成功<#else>失败</#if>",
			exectype="管理日志")
	public void copyRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		long roleId = RequestUtil.getLong(request, "roleId");

		String roleName = RequestUtil.getString(request, "newRoleName");
		String alias = RequestUtil.getString(request, "newAlias");
		long newRoleId = UniqueIdUtil.genId();

		SysRole sysRole = sysRoleService.getById(roleId);
		Long systemId = sysRole.getSystemId();
		
		if (systemId != null && systemId !=0) {
			SubSystem subSystem = subSystemService.getById(systemId);
			alias = subSystem.getAlias() + "_" + alias;
		}

		boolean rtn = sysRoleService.isExistRoleAlias(alias);
		if (rtn) {
			writeResultMessage(writer, "输入的别名已存在", ResultMessage.Fail);
			return;
		}

		SysRole newRole = (SysRole) sysRole.clone();
		newRole.setRoleId(newRoleId);
		newRole.setAlias(alias);
		newRole.setRoleName(roleName);

		boolean issuccess=true;
		try {
			sysRoleService.copyRole(newRole, roleId);
			writeResultMessage(writer, "复制角色成功!", ResultMessage.Success);
		} catch (Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"复制角色失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
			 issuccess=false;
		}
		SysAuditThreadLocalHolder.putParamerter("isSuccess", issuccess);
	}

	/**
	 * 获取角色树。
	 * 
	 * <pre>
	 * SysRole 的systemId 
	 * 1. 0代表是子系统。
	 * 2. 1代表全局角色。
	 * 3. 其他为子系统的角色。
	 * </pre>
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getTreeData")
	@ResponseBody
	@Action(description="获取角色树形数据",execOrder=ActionExecOrder.AFTER,detail="获取角色树形数据",	exectype="管理日志")
	public List  getTreeData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//List<SysRole> rolList = sysRoleService.getRoleTree(new QueryFilter(request, "sysRole", false));
		List<SysRole> rolList=sysRoleService.getAll();
		return getRoleTree(rolList);
	}
	
	@RequestMapping("getGradeTreeData")
	@ResponseBody
	@Action(description="获取角色树形数据",execOrder=ActionExecOrder.AFTER,detail="获取分级角色树形数据",	exectype="管理日志")
	public List  getGradeTreeData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long userId = ContextUtil.getCurrentUserId();
		List<SysRole> rolList=sysRoleService.getByUser(userId);
		return getRoleTree(rolList);
	}
	
	private List getRoleTree(List<SysRole> rolList){
		SysRole rol = null;
		List  treeList=new ArrayList();
		//全局   父节点 {0,0，全局角色,true}
		rol=new SysRole();
		rol.setRoleId(new Long(0));
		rol.setSystemId(new Long(0));//相当于pid
		rol.setRoleName("全局角色");
		rol.setIsParent("true");//是否父节点,不加这个，无自己点的时候，父节点不是文件夹样式
		treeList.add(rol);
	
		//全局   子节点
		for(SysRole sysRole:rolList){
			//全局变量
			if(sysRole.getSystemId().longValue()==0){
				//sysRole.setSystemId(0L);
				//{sysRole.getRoleId().toString(),"0",sysRole.getRoleName()}
				treeList.add(sysRole);
			}
		}
		
		//循环子系统，{子系统角色id,"-1","子系统1"}
		List<SubSystem> sublist = subSystemService.getActiveSystem();
		Long i=-1L;
		for(SubSystem subSys:sublist){
			 i--;//子系统 角色 pid，-1,-2，-3，
			 rol=new SysRole();
			 rol.setRoleId(i);
			 rol.setSystemId(-9999999999L);//子系统pid
			 rol.setRoleName(subSys.getSysName());
			 //子系统父节点
			 treeList.add(rol);
			//{String.valueOf(i),  "-9999999999",subSys.getSysName()}
			
			//List<SysRole> rolList2=sysRoleService.getAll();
			for(SysRole sysRole2:rolList){//子系统添加 角色子节点
				if(sysRole2.getSystemId()!=null &&subSys.getSystemId()==sysRole2.getSystemId()){
					sysRole2.setSystemId( i);
					//所有子系统的子节点
					treeList.add(sysRole2);
					//{String.valueOf(sysRole2.getSystemId()),  String.valueOf(i),sysRole2.getRoleName()}	
				}
			}
		}
		return treeList;
	}

	/**
	 * 取得系统角色表分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getAll")
	@ResponseBody
	@Action(description="获取角色信息",
			execOrder=ActionExecOrder.AFTER,
			detail="获取角色信息",
			exectype="管理日志")
	public List<SysRole> getAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysRole> list = sysRoleService.getAll(new QueryFilter(request, false));
		return list;
	}

	/**
	 * 禁用或启用角色
	 * 
	 * @param request
	 * @param response
	 * @param po
	 * @throws Exception
	 */
	@RequestMapping("runEnable")
	@Action(description = "禁用或启用角色",
			detail="<#if enabled=='0'>启用<#else>禁用</#if>系统角色【${SysAuditLinkService.getSysRoleLink(sysRoleService.getById(Long.valueOf(roleId)))}】",
			exectype="管理日志"
			)
	public void runEnableRole(HttpServletRequest request, HttpServletResponse response) throws Exception {

		long roleId = RequestUtil.getLong(request, "roleId");
		long enabled=RequestUtil.getLong(request, "enabled");
		SysRole sysRole = sysRoleService.getById(roleId);
		if(sysRole.getEnabled().equals((short)1)){
			sysRole.setEnabled((short) 0);
		}
		else{
			sysRole.setEnabled((short) 1);
		}
		sysRoleService.update(sysRole);
		
		response.sendRedirect(RequestUtil.getPrePage(request));
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("dialog")
	@Action(description="选择角色",
			execOrder=ActionExecOrder.AFTER,
			detail="选择角色",
			exectype="管理日志"
	)
	public ModelAndView dialog(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		String isGrade = RequestUtil.getString(request, "isGrade", "false");
		return this.getAutoView().addObject("isSingle", isSingle).addObject("isGrade", isGrade); 
    }
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getRoleTree")
	@ResponseBody
	@Action(description="获取角色树形数据",
			execOrder=ActionExecOrder.AFTER,
			detail="获取角色树形数据",
			exectype="管理日志")
	public List<SysRole> getRoleTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long systemId = RequestUtil.getLong(request, "systemId", 0);
		
		//获取当前系统的角色
		List<SysRole> sysRoleList= sysRoleService.getBySystemId(systemId);
		for (SysRole sysRole :sysRoleList) {
			sysRole.setSystemId(-1L);
		}
		//获取全局角色
		List<SysRole> overAllRole = sysRoleService.getBySystemId(0L);
		for (SysRole sysRole :overAllRole){
			sysRole.setSystemId(-2L);
		}
		sysRoleList.addAll(overAllRole);
		return sysRoleList;
	}
}
