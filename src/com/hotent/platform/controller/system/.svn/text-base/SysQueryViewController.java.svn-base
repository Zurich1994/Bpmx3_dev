package com.hotent.platform.controller.system;

import java.util.ArrayList;
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
import com.hotent.core.page.PageList;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.form.BpmFormTemplate;
import com.hotent.platform.model.form.CommonVar;
import com.hotent.platform.model.system.SysQueryFieldSetting;
import com.hotent.platform.model.system.SysQueryMetaField;
import com.hotent.platform.model.system.SysQuerySetting;
import com.hotent.platform.model.system.SysQueryView;
import com.hotent.platform.service.form.BpmFormTemplateService;
import com.hotent.platform.service.system.SysQueryFieldSettingService;
import com.hotent.platform.service.system.SysQueryMetaFieldService;
import com.hotent.platform.service.system.SysQueryViewService;
import com.hotent.platform.service.util.ServiceUtil;
/**
 *<pre>
 * 对象功能:视图定义 控制器类
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-06-08 16:02:04
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/sysQueryView/")
public class SysQueryViewController extends BaseController
{
	@Resource
	private SysQueryViewService sysQueryViewService;
	@Resource
	private BpmFormTemplateService bpmFormTemplateService; // 模板serivce类
	@Resource
	private SysQueryMetaFieldService sysQueryMetaFieldService; // 模板serivce类
	@Resource
	private SysQueryFieldSettingService sysQueryFieldSettingService;
	
	
	
	
	/**
	 * 添加或更新视图定义。
	 * @param request
	 * @param response
	 * @param sysQueryView 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新视图定义")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String contextPath=request.getContextPath();
		String resultMsg=null;		
		String json = request.getParameter("json");
		JSONObject jsonObj = JSONObject.fromObject(json);
		JSONArray sysQueryFieldSettingArray = JSONArray.fromObject(jsonObj.getString("displayField"));
		SysQueryView sysQueryView = sysQueryViewService.getSysQueryView(json);
		try {
			boolean flag = false; // 判断是添加还是保存
			if (sysQueryView.getId() == null || sysQueryView.getId() == 0){
				flag = true;
			}
			sysQueryViewService.save(sysQueryView,sysQueryFieldSettingArray, flag,contextPath);
			resultMsg = flag ? "添加视图定义成功" : "更新视图定义成功";
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
		} catch (Exception e) {
			e.printStackTrace();
			writeResultMessage(response.getWriter(), resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
		
	}
	
	
	/**
	 * 取得视图定义分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看视图定义分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		QueryFilter queryFilter = new QueryFilter(request,"sysQueryViewItem");
		List<SysQueryView> list=sysQueryViewService.getAll(queryFilter);
		ModelAndView mv=this.getAutoView().addObject("sysQueryViewList",list);
		return mv;
	}
	
	/**
	 * 删除视图定义
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除视图定义")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			sysQueryViewService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除视图定义成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑视图定义
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑视图定义")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		Long sqlId = RequestUtil.getLong(request, "sqlId", 0L);
		SysQueryView sysQueryView = null;
		List<SysQueryFieldSetting> sysQueryFieldSettingList = null;
		if(id!=0){
			sysQueryView=sysQueryViewService.getById(id);
			sysQueryFieldSettingList = sysQueryFieldSettingService.getBySysQueryViewId(id);
		}
		if(sysQueryView == null)
			sysQueryView=sysQueryViewService.getBySqlId(sqlId);
		List<BpmFormTemplate> templates = bpmFormTemplateService.getQueryDataTemplate();// 获取模板对象集
		List<SysQueryMetaField> sysQueryMetaFields = sysQueryMetaFieldService.getListBySqlId(sqlId);
		List<CommonVar> commonVars = CommonVar.geCommonVars();
		return this.getAutoView().addObject("templates", templates)
							.addObject("sysQueryView", sysQueryView)
							.addObject("sysQueryViewJson",com.alibaba.fastjson.JSONObject.toJSON(sysQueryView))
							.addObject("displayFields",JSONArray.fromObject(sysQueryFieldSettingList))
							.addObject("commonVars", commonVars)
							.addObject("sysQueryMetaFields", JSONArray.fromObject(sysQueryMetaFields));
	}

	
	@RequestMapping("editTemplate")
	@Action(description="编辑视图定义")
	public ModelAndView editTemplate(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		
		SysQueryView  sysQueryView=sysQueryViewService.getById(id);
		
		
		return this.getAutoView().addObject("sysQueryView", sysQueryView);
	}
	/**
	 * 取得视图定义明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看视图定义明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		SysQueryView sysQueryView = sysQueryViewService.getById(id);	
		return getAutoView().addObject("sysQueryView", sysQueryView);
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("data_{sqlAlias}/{view}")
	@ResponseBody
	@Action(description="取得分页数据")
	public Map<String, Object> listJsonJQ(HttpServletRequest request,HttpServletResponse response,
			@PathVariable(value="sqlAlias")String sqlAlias,
			@PathVariable(value="view")String view) throws Exception{	
		Map<String,Object> queryParams=RequestUtil.getQueryParamater(request);
		int currentPage=RequestUtil.getInt(request,"page", 1);
		int pageSize=RequestUtil.getInt(request,"pageSize", 20);
		String isSearch=request.getParameter("initSearch");
		PageList pageList=new PageList();
		if("true".equals(isSearch)){
			pageList=sysQueryViewService.getPageList(currentPage,pageSize,queryParams,sqlAlias,view);
		}
		Map map=getMapByPageListJq(pageList);
		return map;
	} 
	
	
	
	@RequestMapping("{sqlAlias}/{view}")
	@Action(description="显示列表页面")
	public ModelAndView showList(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="sqlAlias") String sqlAlias,@PathVariable(value="view") String view) throws Exception
	{
		ModelAndView mv=getShowModelAndView(sqlAlias,view,false);
		return mv;
	} 
	
	@RequestMapping("{sqlAlias}")
	@Action(description="显示列表页面")
	public ModelAndView showList(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="sqlAlias") String sqlAlias) throws Exception{
		String currentView=RequestUtil.getString(request, "view");
		ModelAndView mv=getShowModelAndView(sqlAlias,currentView,true);
		return mv;
	}
	
	private ModelAndView getShowModelAndView(String sqlAlias,String currentView,boolean hasTab) throws Exception{
		List<SysQueryView> viewList=new ArrayList<SysQueryView>();
		//是否存在TAB
		if(hasTab){
			viewList=sysQueryViewService.getHasRights(sqlAlias);
			if(BeanUtils.isEmpty(viewList)){
				ServiceUtil.getTipInfo("您没有访问当前视图的权限.");
			}
			if(StringUtil.isEmpty(currentView)){
				currentView=viewList.get(0).getAlias();
			}
		}
		SysQueryView queryView= sysQueryViewService.getQueryView( sqlAlias, currentView);
		ModelAndView mv=new ModelAndView();
		mv.addObject("viewList", viewList);
		mv.addObject("currentView", currentView);
		mv.addObject("queryView", queryView);
		mv.addObject("sqlAlias", sqlAlias);
		mv.addObject("hasTab", hasTab);
		mv.setViewName("/platform/system/sysQueryViewShow.jsp");
		return mv;
	}
	
	
	@RequestMapping("saveTemplate")
	public void saveTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultMsg = "";
		Long id = RequestUtil.getLong(request, "id");
		String template = request.getParameter( "template");
		
		SysQueryView queryView = sysQueryViewService.getById(id);
		queryView.setTemplate(template);
		sysQueryViewService.update(queryView);
		resultMsg = "更新自定义表管理显示模板成功";
		writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
	}
}

