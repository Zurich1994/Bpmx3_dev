package com.hotent.platform.controller.system;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.engine.IScript;
import com.hotent.core.log.SysAuditThreadLocalHolder;

import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import com.hotent.core.web.ResultMessage;
import com.hotent.platform.model.system.ConditionScript;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.system.ConditionScriptService;
/**
 *<pre>
 * 对象功能:系统条件脚本 控制器类
 * 开发公司:hotent
 * 开发人员:heyifan
 * 创建时间:2013-04-05 11:34:56
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/conditionScript/")
@Action(ownermodel=SysAuditModelType.PROCESS_AUXILIARY)
public class ConditionScriptController extends BaseController
{
	@Resource
	private ConditionScriptService conditionScriptService;
	
	
	/**
	 * 添加或更新系统条件脚本。
	 * @param request
	 * @param response
	 * @param conditionScript 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新系统条件脚本",
			detail="<#if isAdd>添加<#else>更新</#if>" +
					"系统条件脚本:" +
					"【${conditionScript.methodDesc}】"
	)
	public void save(HttpServletRequest request, HttpServletResponse response,ConditionScript conditionScript) throws Exception
	{
		
		//添加系统日志信息 -E
		String resultMsg=null;		
		try{
			if(conditionScript.getId()==null||conditionScript.getId()==0){
				conditionScript.setId(UniqueIdUtil.genId());
				conditionScriptService.add(conditionScript);
				resultMsg="添加系统条件脚本成功";
			}else{
			    conditionScriptService.update(conditionScript);
				resultMsg="更新系统条件脚本成功";
			}
			//添加系统日志信息 -B
			try {
				SysAuditThreadLocalHolder.putParamerter("conditionScript", conditionScript);
				SysAuditThreadLocalHolder.putParamerter("isAdd", conditionScript.getId()==null||conditionScript.getId()==0);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得 ConditionScript 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected ConditionScript getFormObject(HttpServletRequest request) throws Exception {
    
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
    
		String json=RequestUtil.getString(request, "json");
		JSONObject obj = JSONObject.fromObject(json);
		
		ConditionScript conditionScript = (ConditionScript)JSONObject.toBean(obj, ConditionScript.class);
		
		return conditionScript;
    }
	
	/**
	 * 取得系统条件脚本分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看系统条件脚本分页列表",detail="查看系统条件脚本分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<ConditionScript> list=conditionScriptService.getAll(new QueryFilter(request,"conditionScriptItem"));
		ModelAndView mv=this.getAutoView().addObject("conditionScriptList",list);
		
		return mv;
	}
	
	/**
	 * 删除系统条件脚本
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除系统条件脚本",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除系统条件脚本" +
					"<#list StringUtils.split(id,\",\") as item>" +
						"<#assign entity=conditionScriptService.getById(Long.valueOf(item))/>" +
						"【${entity.methodDesc}】"+
					"</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			conditionScriptService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除系统条件脚本成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑系统条件脚本
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="添加或编辑系统条件脚本",
			detail="<#if isAdd>添加系统条件脚本<#else>"+
					"编辑系统条件脚本：" +
					"<#assign entity=conditionScriptService.getById(Long.valueOf(id))/>" +
					"【${entity.methodDesc}】</#if>")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		//获取IScript的实现类
		List<Class> implClasses = AppUtil.getImplClass(IScript.class);
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		ConditionScript conditionScript=conditionScriptService.getById(id);
		//添加系统日志信息 -B
		SysAuditThreadLocalHolder.putParamerter("isAdd", conditionScript==null );
		return getAutoView().addObject("conditionScript",conditionScript)
							.addObject("implClasses",implClasses)
							.addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得系统条件脚本明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看系统条件脚本明细",detail="查看系统条件脚本明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		ConditionScript conditionScript = conditionScriptService.getById(id);	
		return getAutoView().addObject("conditionScript", conditionScript);
	}
	
	
	/**
	 * 取得系统条件脚本明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getJson")
	@Action(description="取系统条件脚本",detail="取系统条件脚本")
	@ResponseBody
	public Map<String,Object> getJson(HttpServletRequest request, HttpServletResponse response)
	{	
		Map<String,Object> map = new HashMap<String, Object>();
		
		int status = 0;
		try{
		long id=RequestUtil.getLong(request,"id");
			ConditionScript conditionScript = conditionScriptService.getById(id);
			map.put("conditionScript", conditionScript);
		}catch (Exception e) {
			status=-1;
			e.printStackTrace();
		}
		map.put("status",status);
		return map;
	}
	
	
	/**
	 * 通过类名获取类的所有方法
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getMethodsByName")
	@ResponseBody	
	public String getMethodsByName(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String name = RequestUtil.getString(request, "name");
		JSONObject jobject = new JSONObject(); 		
		try{
			JSONArray jarray = conditionScriptService.getMethodsByName(name);
			jobject.accumulate("result", true).accumulate("methods", jarray);
		}
		catch(Exception ex){
			jobject.accumulate("result", false).accumulate("message", ex.getMessage());
		}
		return jobject.toString();
	}
	
	/**
	 * 验证脚本是否有效
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("validScriptIsEnable")
	@ResponseBody
	public String validScriptIsEnable(HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject jobject = new JSONObject(); 		
		try{
			String message = conditionScriptService.validScriptIsEnable();
			jobject.accumulate("result", true).accumulate("message", message);
		}
		catch(Exception ex){
			jobject.accumulate("result", false).accumulate("message", ex.getMessage());
		}
		return jobject.toString();
	}
	
	/**
	 * 获取所有 有效的条件脚本
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getConditionScript")
	@ResponseBody
	public String getConditionScript(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ConditionScript> list = conditionScriptService.getConditionScript();
		JSONArray jarray = JSONArray.fromObject(list);
		return jarray.toString();
	}
	
	/**
	 * 与dialog页面一起使用。 获取所有 有效的条件脚本，供选择。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description="选择器",detail="选择器")
	public ModelAndView selector(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<ConditionScript> conditionScriptList=conditionScriptService.getAll(new QueryFilter(request,"conditionScriptItem"));
		ModelAndView mv=this.getAutoView().addObject("conditionScriptList",conditionScriptList);
		
		return mv;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addDialog")
	@Action(description="添加对话框",detail="添加对话框")
	public ModelAndView addDialog(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Long defId = RequestUtil.getLong(request, "defId", 0L);
		ModelAndView mv=this.getAutoView().addObject("defId",defId);
		return mv;
	}
}
