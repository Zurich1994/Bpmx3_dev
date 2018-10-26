package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.OrgAuth;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.service.system.OrgAuthService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysRoleService;
/**
 *<pre>
 * 对象功能:组织管理员 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:miao
 * 创建时间:2014-08-08 09:08:59
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/orgAuth/")
public class OrgAuthController extends BaseController
{
	@Resource
	private OrgAuthService orgAuthService;
	@Resource
	private SysOrgService orgService;
	@Resource
	private SysRoleService roleService;
	
	/**
	 * 添加或更新组织管理员。
	 * @param request
	 * @param response
	 * @param orgAuth 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新组织管理员")
	public void save(HttpServletRequest request, HttpServletResponse response,OrgAuth orgAuth) throws Exception
	{
		Long [] roleIds = RequestUtil.getLongAryByStr(request, "roleIds");
		String resultMsg=null;		
		try{
			if(orgAuth.getId()==null||orgAuth.getId()==0){
				orgAuth.setId(UniqueIdUtil.genId());
				orgAuthService.add(orgAuth,roleIds);
				resultMsg="添加组织管理员成功！";
			}else{
			    orgAuthService.update(orgAuth,roleIds);
				resultMsg="修改组织管理员成功！";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得组织管理员分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看组织管理员分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Long orgId = RequestUtil.getLong(request, "orgId", 0);
		QueryFilter filter = new QueryFilter(request,"orgAuthItem");
		if(orgId >0 ) filter.addFilter("orgId", orgId);
		
		List<OrgAuth> list=orgAuthService.getAll(filter);
		
		ModelAndView mv=this.getAutoView().addObject("orgAuthList",list);
		mv.addObject("orgId", orgId);
		return mv;
	}
	
	@RequestMapping("gradeList")
	@Action(description="查看组织管理员分页列表")
	public ModelAndView gradeList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Long orgId = RequestUtil.getLong(request, "orgId", 0);
		QueryFilter filter = new QueryFilter(request,"orgAuthItem");
		filter.addFilter("orgId", orgId);
		
		List<OrgAuth> list=orgAuthService.getAll(filter);
		
		ModelAndView mv=new ModelAndView("/platform/system/orgAuthList.jsp")
			.addObject("orgAuthList",list)
			.addObject("orgId", orgId)
			.addObject("grade",true);
		return mv;
	}
	
	/**
	 * 删除组织管理员
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除组织管理员")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			orgAuthService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除组织管理员成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑组织管理员
	 */
	@RequestMapping("edit")
	@Action(description="编辑组织管理员")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		Long orgId = RequestUtil.getLong(request, "orgId", 0);
		boolean isGrade = RequestUtil.getBoolean(request, "isGrade"); // 分级组织管理下、分配分级管理员
		ModelAndView mv = getAutoView();
		
		///修改情况
		if(id != 0){
			OrgAuth orgAuth=orgAuthService.getById(id);
			List<SysRole> roleList = roleService.getByAuthId(id);
			mv.addObject("orgAuth", orgAuth)
			  .addObject("roleList", roleList);
		}else{
			mv.addObject("sysOrg", orgService.getById(orgId));   
		}
		
		return mv.addObject("returnUrl",RequestUtil.getPrePage(request))
					.addObject("orgId", orgId).addObject("isGrade", isGrade);
	}

	/**
	 * 取得组织管理员明细
	 * @deprecated
	 */
	@RequestMapping("get")
	@Action(description="查看组织管理员明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		Long id=RequestUtil.getLong(request,"id");
		OrgAuth orgAuth = orgAuthService.getById(id);	
		return getAutoView().addObject("orgAuth", orgAuth);
	}
	
}
