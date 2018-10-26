
package com.hotent.platform.controller.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmCommonWsSet;
import com.hotent.platform.model.system.WsDataTemplate;
import com.hotent.platform.service.bpm.BpmCommonWsSetService;
import com.hotent.platform.service.system.WsDataTemplateService;
/**
 * 对象功能:webservice数据模板展示 控制器类
 */
@Controller
@RequestMapping("/platform/system/wsDataTemplate/")
public class WsDataTemplateController extends BaseController
{
	@Resource
	private WsDataTemplateService service;
	@Resource
	private BpmCommonWsSetService bpmCommonWsSetService;
	
	/**
	 * 添加或更新webservice数据模板展示。
	 * @param request
	 * @param response
	 * @param wsDataTemplate 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新webservice数据模板展示")
	public void save(HttpServletRequest request, HttpServletResponse response,WsDataTemplate wsDataTemplate) throws Exception
	{
		String resultMsg=null;		
		Long setId = RequestUtil.getLong(request, "setId",0);
		wsDataTemplate.setServiceId(setId);
		try{
			if(wsDataTemplate.getId()==null){
				Long id=UniqueIdUtil.genId();
				wsDataTemplate.setId(id);
				service.add(wsDataTemplate);
				resultMsg="添加成功";
			}else{
			    service.update(wsDataTemplate);
				resultMsg="更新成功";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得webservice数据模板展示分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看webservice数据模板展示分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		QueryFilter queryFilter=new QueryFilter(request,"wsDataTemplateItem");
		List<WsDataTemplate> list=service.getAll(queryFilter);
		List<BpmCommonWsSet> setList = bpmCommonWsSetService.getAll();
		return this.getAutoView().addObject("wsDataTemplateList",list).addObject("setList", setList);
	}
	
	/**
	 * 删除webservice数据模板展示
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除webservice数据模板展示")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			service.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除成功");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑webservice数据模板展示
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑webservice数据模板展示")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		WsDataTemplate wsDataTemplate=service.getById(id);
		List<BpmCommonWsSet> bpmCommonWsSetList = bpmCommonWsSetService.getAll();
		return this.getAutoView().addObject("wsDataTemplate",wsDataTemplate).addObject("returnUrl",returnUrl)
				.addObject("bpmCommonWsSetList",bpmCommonWsSetList);
	}

	/**
	 * 取得webservice数据模板展示明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看webservice数据模板展示明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		WsDataTemplate wsDataTemplate=service.getById(id);
		List<BpmCommonWsSet> bpmCommonWsSetList = bpmCommonWsSetService.getAll();
		return getAutoView().addObject("wsDataTemplate", wsDataTemplate).addObject("bpmCommonWsSetList", bpmCommonWsSetList);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("parse")
	@ResponseBody
	@Action(description="解析webservice返回的XML，结合数据模板进行展示")
	public String parse(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String returnXML = RequestUtil.getString(request, "returnXML","");
		String template = RequestUtil.getString(request, "template","");
		String parseScript = RequestUtil.getString(request, "parseScript","");
		JSONObject jobject = new JSONObject();
		try{
			Map<String, String> result = service.parseToDataModel(returnXML, template, parseScript);
			jobject.accumulate("result", ResultMessage.Success)
			       .accumulate("message", result.get("message"))
			       .accumulate("template", result.get("template"));
		}
		catch(Exception ex){
			jobject.accumulate("result", ResultMessage.Fail)
			   	   .accumulate("message", ex.getMessage());
		}
		return jobject.toString();
	}
	
	/**
	 * 根据webservice id，请求并返回XML
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("doExecute")
	@ResponseBody
	public String doExecute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long setId = RequestUtil.getLong(request, "setId");
		String json = RequestUtil.getString(request, "json");
		JSONObject jobject = new JSONObject();
		BpmCommonWsSet bpmCommonWsSet = bpmCommonWsSetService.getById(setId);
		if(bpmCommonWsSet==null){
			jobject.accumulate("result", ResultMessage.Fail)
				   .accumulate("message", "未获取到该webservice调用设置");
		}
		else{
			try{
				String result = service.doExecute(bpmCommonWsSet,json);
				jobject.accumulate("result", ResultMessage.Success)
				       .accumulate("message", result);
			}
			catch(Exception ex){
				jobject.accumulate("result", ResultMessage.Fail)
				   	   .accumulate("message", ex.getMessage());
			}
		}
		return jobject.toString();
	}
	
	@RequestMapping("getWsDocumentById")
	@ResponseBody
	public String getWsDocumentById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long id=RequestUtil.getLong(request,"setId");
		BpmCommonWsSet bpmCommonWsSet = bpmCommonWsSetService.getById(id);	
		return bpmCommonWsSet.getDocument();
	}	
	
	@RequestMapping("show_{id}")
	@Action(description="根据ID展示webservice数据模板内容")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) throws Exception{
		Map<String, Object> params = RequestUtil.getQueryMap(request);
		WsDataTemplate data = service.getById(id);
		if(data==null){
			throw new Exception("未获取到webservice数据模板配置信息");
		}
		Long serviceId = data.getServiceId() ;
		BpmCommonWsSet bpmCommonWsSet = bpmCommonWsSetService.getById(serviceId);
		String document = bpmCommonWsSet.getDocument();
		String html = service.show(data, bpmCommonWsSet, params);
		JSONObject jsonOjbect = JSONObject.fromObject(document);
		JSONArray jsonArray = JSONArray.fromObject(jsonOjbect.get("inputs"));
		ModelAndView mv = new ModelAndView("/platform/system/wsDataTemplateShow.jsp");
		return mv.addObject("html", html).addObject("list", jsonArray) ;
	}
}
