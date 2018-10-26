package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysUrlPermission;
import com.hotent.platform.model.system.SysUrlRules;
import com.hotent.platform.service.system.SysUrlPermissionService;
import com.hotent.platform.service.system.SysUrlRulesService;
/**
 *<pre>
 * 对象功能:URL地址拦截管理 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:wdr
 * 创建时间:2014-03-27 16:32:01
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/sysUrlPermission/")
public class SysUrlPermissionController extends BaseController
{
	@Resource
	private SysUrlPermissionService sysUrlPermissionService;
	@Resource
	private SysUrlRulesService sysUrlRulesService;
	
	/**
	 * 添加或更新URL地址拦截管理。
	 * @param request
	 * @param response
	 * @param sysUrlPermission 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新URL地址拦截管理")
	public void save(HttpServletRequest request, HttpServletResponse response,SysUrlPermission sysUrlPermission) throws Exception
	{
		
		String resultMsg=null;		
		String subData = RequestUtil.getString(request, "subData", "");
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<SysUrlRules> SysUrlRulesList=JSONArray.toList(JSONArray.fromObject(subData), SysUrlRules.class);
		try{
			if(sysUrlPermission.getId()==null||sysUrlPermission.getId()==0){
				sysUrlPermission.setId(UniqueIdUtil.genId());
				sysUrlPermissionService.add(sysUrlPermission);
				sysUrlRulesService.addRule(SysUrlRulesList,sysUrlPermission.getId());	
				resultMsg="保存成功!";
			}else{
			    sysUrlPermissionService.update(sysUrlPermission);
			    sysUrlRulesService.updateRule(SysUrlRulesList,sysUrlPermission.getId());
				resultMsg="更新成功";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得URL地址拦截管理分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看URL地址拦截管理分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysUrlPermission> list=sysUrlPermissionService.getAll(new QueryFilter(request,"sysUrlPermissionItem"));
		ModelAndView mv=this.getAutoView().addObject("sysUrlPermissionList",list);
		
		return mv;
	}
	
	/**
	 * 删除URL地址拦截管理
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除URL地址拦截管理")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			sysUrlRulesService.delByUrlPerId(lAryId);
			sysUrlPermissionService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除成功!" );
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:"+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑URL地址拦截管理
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑URL地址拦截管理")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		SysUrlPermission sysUrlPermission=sysUrlPermissionService.getById(id);
		List<SysUrlRules> sysUrlRulesList=sysUrlRulesService.getByUrlPer(id);
		
		return getAutoView().addObject("sysUrlPermission",sysUrlPermission)
							.addObject("sysUrlRulesList",sysUrlRulesList)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得URL地址拦截管理明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看URL地址拦截管理明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		SysUrlPermission sysUrlPermission=sysUrlPermissionService.getById(id);
		List<SysUrlRules> sysUrlRulesList=sysUrlRulesService.getByUrlPer(id);
		return getAutoView().addObject("sysUrlPermission",sysUrlPermission)
							.addObject("sysUrlRulesList",sysUrlRulesList);
	}
	
}
