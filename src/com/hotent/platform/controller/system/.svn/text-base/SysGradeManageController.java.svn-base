package com.hotent.platform.controller.system;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.OrgAuth;
import com.hotent.platform.model.system.SysOrgRole;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.OrgAuthService;
import com.hotent.platform.service.system.SysOrgRoleManageService;
import com.hotent.platform.service.system.SysOrgRoleService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysUserOrgService;

@Controller
@RequestMapping("/platform/system/grade/")
public class SysGradeManageController extends BaseController{
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private SysUserOrgService  sysUserOrgService;
	@Resource
	private SysOrgRoleService sysOrgRoleService;
	@Resource
	private SysOrgRoleManageService sysOrgRoleManageService;
	@Resource
	private OrgAuthService orgAuthService;
	
	/**
	 * 授权管理页面。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage")
	public ModelAndView manage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long userId = ContextUtil.getCurrentUserId();
		List<OrgAuth> orgAuthList = orgAuthService.getByUserId(userId);
		
		return this.getAutoView().addObject("orgAuthList", orgAuthList);
	}
	
	/**
	 * 根据用户返回树形JSON 。
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getOrgJsonByAuthOrgId")
	public void getOrgJsonByUserId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long orgId = RequestUtil.getLong(request, "orgId");
		/*
		String ctxPath=request.getContextPath();
		String str= sysUserOrgService.getOrgJsonByAuthOrgId(orgId,ctxPath);*/
		String str= sysUserOrgService.getOrgJsonByAuthOrgId(orgId);
		response.getWriter().print(str);
	}
	
	/**
	 * 添加组织角色。
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("addOrgRole")
	public void addOrgRole(HttpServletRequest request, HttpServletResponse response) throws IOException{
		addOrgRole(request,response,0);
	}
	
	/**
	 * 添加可分配的角色。
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("addGradeOrgRole")
	public void addGradeOrgRole(HttpServletRequest request, HttpServletResponse response) throws IOException{
		addOrgRole(request,response,1);
	}
	
	private void addOrgRole(HttpServletRequest request, HttpServletResponse response,int grade) throws IOException{
		String roleIds=RequestUtil.getString(request, "roleIds");
		Long orgId=RequestUtil.getLong(request, "orgId");
		
		if(StringUtil.isEmpty(roleIds)){
			writeResultMessage(response.getWriter(), "没有选择角色!", ResultMessage.Fail);
			return ;
		}
		try{
			boolean rtn= sysOrgRoleManageService.addOrgRole(orgId, roleIds,grade);
			if(rtn){
				String msg="添加角色成功!";
				writeResultMessage(response.getWriter(), msg, ResultMessage.Success);
			}
			else{
				String msg="选择的角色在系统中已存在!";
				writeResultMessage(response.getWriter(), msg, ResultMessage.Fail);
			}
		}
		catch(Exception ex){
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"添加角色失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	
	
	/**
	 * 组织可以分配的角色。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("assignRoleList")
	public ModelAndView assignRoleList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();
		mv.addObject("scope", "global");
		mv=getAssignRoleListMv(request,mv);
		return mv;
	}
	
	@RequestMapping("assignRoleGradeList")
	public ModelAndView assignRoleGradeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/platform/system/gradeAssignRoleList.jsp");
		mv.addObject("scope", "grade");
		mv=getAssignRoleListMv(request,mv);
		return mv;
	}
	
	private ModelAndView getAssignRoleListMv(HttpServletRequest request,ModelAndView mv ){
		Long orgId=RequestUtil.getLong(request, "orgId");
		if(orgId>0){
			List<SysOrgRole> list=   sysOrgRoleService.getAssignRoleByOrgId(orgId);
			mv.addObject("orgRoles", list)
			.addObject("orgId", orgId);
		}
		return mv;
	}
	
	//角色选择。
	@RequestMapping("roleSelector")
	public ModelAndView roleSelector(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();
		Long orgId=RequestUtil.getLong(request, "orgId");
		
		List<SysOrgRole> list=   sysOrgRoleService.getAssignRoleByOrgId(orgId);
		mv.addObject("orgRoles", list)
		.addObject("orgId", orgId);
	
		
		return mv;
	}
	
	@RequestMapping("del")
	@Action(description="删除组织可以授权的角色范围(用于分级授权)")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		delete(request, response);
	}
	
	@RequestMapping("delGrade")
	public void delGrade(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		delete(request, response);
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			sysOrgRoleManageService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除组织可以授权的角色范围(用于分级授权)成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
}
