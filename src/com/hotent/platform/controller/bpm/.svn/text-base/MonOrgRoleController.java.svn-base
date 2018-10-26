package com.hotent.platform.controller.bpm;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.hotent.platform.model.bpm.MonOrgRole;
import com.hotent.platform.service.bpm.MonOrgRoleService;
import com.hotent.core.web.ResultMessage;
/**
 *<pre>
 * 对象功能:监控组权限分配 控制器类
 * 开发公司:广州宏天软件
 * 开发人员:zyp
 * 创建时间:2013-06-17 18:38:16
 *</pre>
 */
@Controller
@RequestMapping("/platform/bpm/monOrgRole/")
public class MonOrgRoleController extends BaseController
{
	@Resource
	private MonOrgRoleService monOrgRoleService;
	
	
	/**
	 * 添加或更新监控组权限分配。
	 * @param request
	 * @param response
	 * @param monOrgRole 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新监控组权限分配")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;		
		MonOrgRole monOrgRole=getFormObject(request);
		try{
			if(monOrgRole.getId()==null||monOrgRole.getId()==0){
				monOrgRole.setId(UniqueIdUtil.genId());
				monOrgRoleService.add(monOrgRole);
				resultMsg="添加监控组权限分配成功";
			}else{
			    monOrgRoleService.update(monOrgRole);
				resultMsg="更新监控组权限分配成功";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得 MonOrgRole 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected MonOrgRole getFormObject(HttpServletRequest request) throws Exception {
    
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
    
		String json=RequestUtil.getString(request, "json");
		JSONObject obj = JSONObject.fromObject(json);
		
		MonOrgRole monOrgRole = (MonOrgRole)JSONObject.toBean(obj, MonOrgRole.class);
		
		return monOrgRole;
    }
	
	/**
	 * 取得监控组权限分配分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看监控组权限分配分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<MonOrgRole> list=monOrgRoleService.getAll(new QueryFilter(request,"monOrgRoleItem"));
		ModelAndView mv=this.getAutoView().addObject("monOrgRoleList",list);
		
		return mv;
	}
	
	/**
	 * 删除监控组权限分配
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除监控组权限分配")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			monOrgRoleService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除监控组权限分配成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑监控组权限分配
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑监控组权限分配")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		MonOrgRole monOrgRole=monOrgRoleService.getById(id);
		
		return getAutoView().addObject("monOrgRole",monOrgRole).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得监控组权限分配明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看监控组权限分配明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		MonOrgRole monOrgRole = monOrgRoleService.getById(id);	
		return getAutoView().addObject("monOrgRole", monOrgRole);
	}
	
}
