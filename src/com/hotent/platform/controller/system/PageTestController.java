package com.hotent.platform.controller.system;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.page.PageList;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.util.StringUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import com.hotent.platform.model.system.SysUser;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import com.hotent.core.util.StringUtil;

import com.hotent.platform.model.system.PageTest;
import com.hotent.platform.service.system.PageTestService;
import com.hotent.core.web.ResultMessage;
/**
 *<pre>
 * 对象功能:PageTest 控制器类
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-06-02 22:29:22
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/pageTest/")
public class PageTestController extends BaseController
{
	@Resource
	private PageTestService pageTestService;
	
	
	/**
	 * 添加或更新PageTest。
	 * @param request
	 * @param response
	 * @param pageTest 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新PageTest")
	public void save(HttpServletRequest request, HttpServletResponse response,PageTest pageTest) throws Exception
	{
		String resultMsg=null;		
		try{
			if(pageTest.getId()==null||pageTest.getId()==0){
				pageTestService.save(pageTest);
				resultMsg=getText("添加","PageTest");
			}else{
			    pageTestService.save(pageTest);
				resultMsg=getText("更新","PageTest");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	@RequestMapping("listJson")
	@ResponseBody
	@Action(description="查看PageTest分页列表")
	public Map<String, Object> listJson(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		QueryFilter queryFilter= new QueryFilter(request);
		PageList pageList= (PageList) pageTestService.getAll(queryFilter);
		Map map=getMapByPageList(pageList);
		
		return map;
	}
	
	@RequestMapping("listJsonJQ")
	@ResponseBody
	@Action(description="查看PageTest分页列表")
	public Map<String, Object> listJsonJQ(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String isSearch=request.getParameter("initSearch");
		QueryFilter queryFilter= new QueryFilter(request);
		PageList pageList=new PageList();
		if("true".equals(isSearch)){
			 pageList= (PageList) pageTestService.getAll(queryFilter);
		}
		
		Map map=getMapByPageListJq(pageList);
		
		return map;
	}
	
	/**
	 * 取得PageTest分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看PageTest分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<PageTest> list=pageTestService.getAll(new QueryFilter(request,"pageTestItem"));
		ModelAndView mv=this.getAutoView().addObject("pageTestList",list);
		return mv;
	}
	
	/**
	 * 删除PageTest
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除PageTest")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			pageTestService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除PageTest成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑PageTest
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑PageTest")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		PageTest pageTest=pageTestService.getById(id);
		
		return getAutoView().addObject("pageTest",pageTest)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得PageTest明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看PageTest明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		PageTest pageTest = pageTestService.getById(id);	
		return getAutoView().addObject("pageTest", pageTest);
	}
	
}

