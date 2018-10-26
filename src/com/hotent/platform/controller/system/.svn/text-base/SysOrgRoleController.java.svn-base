package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.cache.ICache;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysOrgRole;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.SysOrgRoleService;
import com.hotent.platform.service.system.SysOrgService;
/**
 * 对象功能:组织角色授权信息 控制器类
 * 开发公司:宏天
 * 开发人员:hotent
 * 创建时间:2012-10-30 10:49:36
 */
@Controller
@RequestMapping("/platform/system/sysOrgRole/")
public class SysOrgRoleController extends BaseController
{
	@Resource
	private SysOrgRoleService sysOrgRoleService;
	
	@Resource
	private SysOrgService sysOrgService;

	
	/**
	 * 添加或更新组织角色授权信息。
	 * @param request
	 * @param response
	 * @param sysOrgRole 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新组织角色授权信息")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;		
		SysOrgRole sysOrgRole=getFormObject(request);
		try{
			if(sysOrgRole.getId()==null||sysOrgRole.getId()==0){
				sysOrgRole.setId(UniqueIdUtil.genId());
				sysOrgRoleService.add(sysOrgRole);
				resultMsg="添加组织角色授权信息成功";
			}else{
			    sysOrgRoleService.update(sysOrgRole);
				resultMsg="更新组织角色授权信息成功";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"组织角色授权失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	
	/**
	 * 取得 SysOrgRole 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected SysOrgRole getFormObject(HttpServletRequest request) throws Exception {
    
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
    
		String json=RequestUtil.getString(request, "json");
		JSONObject obj = JSONObject.fromObject(json);
		
		SysOrgRole sysOrgRole = (SysOrgRole)JSONObject.toBean(obj, SysOrgRole.class);
		
		return sysOrgRole;
    }
	
	/**
	 * 取得组织角色授权信息分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看组织角色授权信息分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysOrgRole> list=sysOrgRoleService.getAll(new QueryFilter(request,"sysOrgRoleItem"));
		ModelAndView mv=this.getAutoView().addObject("sysOrgRoleList",list);
		
		return mv;
	}
	
	/**
	 * 取得组织下的所有用户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("roleList")
	@Action(description="取得分配的角色列表")
	public ModelAndView roleList(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=this.getAutoView();
		mv.addObject("scope", "global");
		mv= getRoleListMv(request,mv);
		return mv;
	}
	
	
	
	@RequestMapping("roleGradeList")
	@Action(description="取得分配的角色列表")
	public ModelAndView roleGradeList(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		ModelAndView mv=new ModelAndView();
		mv.addObject("scope", "grade");
		mv.setViewName("/platform/system/sysOrgRoleRoleList.jsp");
		mv= getRoleListMv(request,mv);
		return mv;
	}
	
	/**
	 * 根据组织id获取角色列表。
	 * @param request
	 * @param mv
	 * @return
	 */
	private ModelAndView getRoleListMv(HttpServletRequest request,ModelAndView mv){
		Long orgId=RequestUtil.getLong(request, "orgId");
		SysOrg sysOrg= sysOrgService.getById(orgId);
		if(sysOrg==null){
			mv.addObject("sysOrg",sysOrg);
		}
		else{
			List<SysOrgRole> orgRoles=sysOrgRoleService.getRolesByOrgId(orgId);
			mv.addObject("orgRoles",orgRoles)
			  .addObject("orgId",orgId)
			  .addObject("sysOrg",sysOrg);
		}
		return mv;
	}
	
	/**
	 * 给组织分配角色。
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("addOrgRole")
	public void addOrgRole(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		addOrgRole(request,response,0);
	}
	
	/**
	 * 给组织分配角色。
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("addGradeOrgRole")
	public void addGradeOrgRole(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		addOrgRole(request,response,1);
	}
	
	/**
	 * 添加组织和角色关联关系。
	 * @param request
	 * @param response
	 * @param grade
	 * @throws Exception
	 */
	private void addOrgRole(HttpServletRequest request,HttpServletResponse response,int grade) throws Exception{
		Long[] roleIds =RequestUtil.getLongAryByStr(request, "roleIds");
		Long orgId=RequestUtil.getLong(request, "orgId");
		ResultMessage resultMessage=null;
		try {
			String result = sysOrgRoleService.addOrgRole(roleIds, orgId,grade);
			
			
			resultMessage=new ResultMessage(ResultMessage.Success,"给组织授权角色成功"+result);
			writeResultMessage(response.getWriter(), resultMessage);
		} catch (Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,"给组织授权角色失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	
	/**
	 * 删除组织角色授权信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除组织角色授权信息")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception{
		delete(request,response);
	}
	
	@RequestMapping("delGrade")
	@Action(description="删除组织角色授权信息")
	public void delGrade(HttpServletRequest request, HttpServletResponse response) throws Exception{
		delete(request,response);
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			Long orgId= sysOrgRoleService.delByOrgRoleIds(lAryId);
		
			
			message=new ResultMessage(ResultMessage.Success, "删除组织角色授权信息成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑组织角色授权信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑组织角色授权信息")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		SysOrgRole sysOrgRole=sysOrgRoleService.getById(id);
		
		return getAutoView().addObject("sysOrgRole",sysOrgRole).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得组织角色授权信息明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看组织角色授权信息明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		SysOrgRole sysOrgRole = sysOrgRoleService.getById(id);	
		return getAutoView().addObject("sysOrgRole", sysOrgRole);
	}
}
