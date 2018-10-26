package com.hotent.platform.controller.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysUrlRules;
import com.hotent.platform.service.system.SysUrlRulesService;
/**
 *<pre>
 * 对象功能:URL地址拦截脚本管理 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:wdr
 * 创建时间:2014-03-27 16:32:01
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/sysUrlRules/")
public class SysUrlRulesController extends BaseController
{
	@Resource
	private SysUrlRulesService sysUrlRulesService;
	@Resource
	private GroovyScriptEngine engine;
	
	
	/**
	 * 添加或更新URL地址拦截脚本管理。
	 * @param request
	 * @param response
	 * @param sysUrlRules 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新URL地址拦截脚本管理")
	public void save(HttpServletRequest request, HttpServletResponse response,SysUrlRules sysUrlRules) throws Exception
	{
		String resultMsg=null;		
		try{
			if(sysUrlRules.getId()==null||sysUrlRules.getId()==0){
				sysUrlRules.setId(UniqueIdUtil.genId());
				sysUrlRulesService.add(sysUrlRules);
				resultMsg=getText("controller.add.success");
			}else{
			    sysUrlRulesService.update(sysUrlRules);
				resultMsg=getText("controller.update.success");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 添加或更新URL地址拦截脚本管理。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("testRule")
	@Action(description="添加或更新URL地址拦截脚本管理")
	public void testRule(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String script = RequestUtil.getString(request, "script");
		String paramString = RequestUtil.getString(request, "params");
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject=JSONObject.fromObject(paramString);
		Iterator keys=jsonObject.keys();
		while (keys.hasNext()) {
			String name=keys.next().toString();
			map.put(name, jsonObject.get(name));
		}
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("map", map);
		try{
			Boolean result = engine.executeBoolean(script, vars);
			writeResultMessage(response.getWriter(),result.toString(),ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得URL地址拦截脚本管理分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看URL地址拦截脚本管理分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysUrlRules> list=sysUrlRulesService.getAll(new QueryFilter(request,"sysUrlRulesItem"));
		ModelAndView mv=this.getAutoView().addObject("sysUrlRulesList",list);
		
		return mv;
	}
	
	/**
	 * 删除URL地址拦截脚本管理
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除URL地址拦截脚本管理")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			sysUrlRulesService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, getText("controller.del.success"));
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, getText("controller.del.fail")+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑URL地址拦截脚本管理
	 * @param request
	 * @param response
	 * @throws Exception
	 *//*
	@RequestMapping("edit")
	@Action(description="编辑URL地址拦截脚本管理")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		String param=RequestUtil.getString(request, "param");
		SysUrlRules sysUrlRules=sysUrlRulesService.getById(id);
		
		return getAutoView().addObject("sysUrlRules",sysUrlRules)
							.addObject("params",param)
							.addObject("returnUrl",returnUrl);
	}*/

	/**
	 * 取得URL地址拦截脚本管理明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看URL地址拦截脚本管理明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		SysUrlRules sysUrlRules = sysUrlRulesService.getById(id);	
		return getAutoView().addObject("sysUrlRules", sysUrlRules);
	}
	
}
