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
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.RoleResourcesService;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.platform.service.system.SysRoleService;

/**
 * 对象功能:角色资源映射 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-08 11:23:15
 */
@Controller
@RequestMapping("/platform/system/roleResources/")
public class RoleResourcesController extends BaseController
{
	@Resource
	private RoleResourcesService roleResourcesService;
	@Resource
	private SubSystemService subSystemService;
	@Resource
	private SysRoleService sysRoleService;
	
	
	
	
	/**
	 * 取得子系统资源分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	//@Action(description="查看子系统资源树")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		
		ModelAndView mv=this.getAutoView();
		List<SubSystem> subSystemList=subSystemService.getAll(new QueryFilter(request,"subSystemItem",false));
		long roleId=RequestUtil.getLong(request, "roleId");
		SysRole role=sysRoleService.getById(roleId);
		String returnUrl=RequestUtil.getString(request, "returnUrl", RequestUtil.getPrePage(request));
		
		mv.addObject("subSystemList",subSystemList);
		mv.addObject("roleId",roleId);
		mv.addObject("role",role);
		mv.addObject("returnUrl",returnUrl);
		mv.addObject("systemId",role.getSystemId());
		return mv;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping("upd")
	@Action(description="分配角色资源")
	public void upd(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Long systemId=RequestUtil.getLong(request, "systemId",0);
		Long roleId=RequestUtil.getLong(request, "roleId",0);
		
		Long[] resIds=null;
		if(request.getParameter("resIds")!=null&&!request.getParameter("resIds").equals("")){
			resIds=RequestUtil.getLongAryByStr(request, "resIds");
		}
		ResultMessage resultMessage=new ResultMessage(ResultMessage.Success,"角色资源分配成功");
		writeResultMessage(response.getWriter(), resultMessage);
		try {
			roleResourcesService.update( systemId, roleId, resIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,"角色资源分配失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	
	
	/**
	 * 批量资源授权
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("batchGrant")
	//@Action(description="查看子系统资源树")
	public ModelAndView batchGrant(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		
		ModelAndView mv=this.getAutoView();
		List<SubSystem> subSystemList=subSystemService.getAll(new QueryFilter(request,"subSystemItem",false));
		String returnUrl=RequestUtil.getString(request, "returnUrl", RequestUtil.getPrePage(request));
		
		mv.addObject("subSystemList",subSystemList)
		  .addObject("returnUrl",returnUrl);
		return mv;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("saveBatch")
	public void saveBatch(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Long systemId=RequestUtil.getLong(request, "systemId",0);
		
		Long[] resIds=null;
		Long[] roleIds=null;
		if(request.getParameter("resIds")!=null&&!request.getParameter("resIds").equals("")){
			resIds=RequestUtil.getLongAryByStr(request, "resIds");
		}
		
		if(request.getParameter("roleIds")!=null&&!request.getParameter("roleIds").equals("")){
			roleIds=RequestUtil.getLongAryByStr(request, "roleIds");
		}
		ResultMessage resultMessage=new ResultMessage(ResultMessage.Success,"角色资源分配成功");
		
		try {
			roleResourcesService.saveBatch( systemId, roleIds, resIds);
			writeResultMessage(response.getWriter(), resultMessage);
		} catch (Exception e) {
			e.printStackTrace();
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,"角色资源分配失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	

}
