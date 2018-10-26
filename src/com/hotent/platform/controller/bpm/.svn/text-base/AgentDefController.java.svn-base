package com.hotent.platform.controller.bpm;

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
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.AgentDef;
import com.hotent.platform.service.bpm.AgentDefService;
/**
 *<pre>
 * 对象功能:代理的流程列表 控制器类
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-04-29 11:15:10
 *</pre>
 */
@Controller
@RequestMapping("/platform/bpm/agentDef/")
public class AgentDefController extends BaseController
{
	@Resource
	private AgentDefService agentDefService;
	
	
	/**
	 * 添加或更新代理的流程列表。
	 * @param request
	 * @param response
	 * @param agentDef 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新代理的流程列表")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;		
		AgentDef agentDef=getFormObject(request);
		try{
			if(agentDef.getId()==null||agentDef.getId()==0){
				agentDef.setId(UniqueIdUtil.genId());
				agentDefService.add(agentDef);
				resultMsg="添加代理定义成功!";
			}else{
			    agentDefService.update(agentDef);
				resultMsg="更新代理定义成功!";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得 AgentDef 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected AgentDef getFormObject(HttpServletRequest request) throws Exception {
    
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
    
		String json=RequestUtil.getString(request, "json");
		JSONObject obj = JSONObject.fromObject(json);
		
		AgentDef agentDef = (AgentDef)JSONObject.toBean(obj, AgentDef.class);
		
		return agentDef;
    }
	
	/**
	 * 取得代理的流程列表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看代理的流程列表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<AgentDef> list=agentDefService.getAll(new QueryFilter(request,"agentDefItem"));
		ModelAndView mv=this.getAutoView().addObject("agentDefList",list);
		
		return mv;
	}
	
	/**
	 * 删除代理的流程列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除代理的流程列表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			agentDefService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success,"删除代理流程定义成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除代理流程定义失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑代理的流程列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑代理的流程列表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		AgentDef agentDef=agentDefService.getById(id);
		
		return getAutoView().addObject("agentDef",agentDef).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得代理的流程列表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看代理的流程列表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		AgentDef agentDef = agentDefService.getById(id);	
		return getAutoView().addObject("agentDef", agentDef);
	}
	
}
