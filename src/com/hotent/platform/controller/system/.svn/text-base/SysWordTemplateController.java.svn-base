package com.hotent.platform.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hotent.core.annotion.Action;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysDataSource;
import com.hotent.platform.model.system.SysWordTemplate;
import com.hotent.platform.service.system.SysDataSourceService;
import com.hotent.platform.service.system.SysWordTemplateService;
import com.hotent.platform.service.util.ServiceUtil;
/**
 *<pre>
 * 对象功能:word套打模板 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:guojh
 * 创建时间:2015-03-23 10:48:07
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/sysWordTemplate/")
public class SysWordTemplateController extends BaseController
{
	@Resource
	private SysWordTemplateService sysWordTemplateService;
	
	@Resource
	private SysDataSourceService sysDataSourceService;
	
	
	/**
	 * 添加或更新word套打模板。
	 * @param request
	 * @param response
	 * @param sysWordTemplate 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新word套打模板")
	public void save(HttpServletRequest request, HttpServletResponse response,SysWordTemplate sysWordTemplate) throws Exception
	{
		try{
			Long id = sysWordTemplate.getId();
			String alias = sysWordTemplate.getAlias();
			SysWordTemplate template = sysWordTemplateService.getByAlias(alias);
			if(id==null ){
				if(template!=null){
					writeResultMessage(response.getWriter(), "系统中别名已存在,请输入其他的别名！", ResultMessage.Fail);
					return;
				}
			}
			else if(template != null && !template.getId().equals(id)) {
				writeResultMessage(response.getWriter(), "系统中别名已存在,请输入其他的别名！", ResultMessage.Fail);
				return;
			}
			sysWordTemplateService.save(sysWordTemplate);
			writeResultMessage(response.getWriter(), sysWordTemplate.getId().toString(), ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得word套打模板分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看word套打模板分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysWordTemplate> list=sysWordTemplateService.getAll(new QueryFilter(request,"sysWordTemplateItem"));
		ModelAndView mv=this.getAutoView().addObject("sysWordTemplateList",list);
		return mv;
	}
	
	/**
	 * 取得word套打模板分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getAllTemplate")
	@ResponseBody
	@Action(description="获取word套打模板列表")
	public List<SysWordTemplate> getAllTemplate(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		return sysWordTemplateService.getAllTemplate();
	}
	
	/**
	 * 删除word套打模板
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除word套打模板")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			sysWordTemplateService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除word套打模板成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑word套打模板
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑word套打模板-基础内容编辑")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		SysWordTemplate sysWordTemplate=sysWordTemplateService.getById(id);
		List<SysDataSource> dsList = sysDataSourceService.getAll();
		return getAutoView().addObject("sysWordTemplate",sysWordTemplate)
							.addObject("returnUrl",returnUrl)
							.addObject("dsList",dsList);
	}
	
	/**
	 * 	编辑word套打模板
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("editTemplate")
	@Action(description="编辑word套打模板-word模板编辑")
	public ModelAndView editTemplate(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		SysWordTemplate sysWordTemplate = null;
		Map<String, Object> tableMap = new HashMap<String, Object>();
		List<SysDataSource> dsList = null;
		try {
			sysWordTemplate = sysWordTemplateService.getById(id);
			tableMap = sysWordTemplateService.getTableMap(sysWordTemplate);
			dsList = sysDataSourceService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return ServiceUtil.getTipInfo(ExceptionUtils.getRootCauseMessage(e));
		}
		return getAutoView().addObject("sysWordTemplate",sysWordTemplate)
				.addObject("dsList",dsList)
				.addObject("tableMap", JSONObject.toJSON(tableMap));
	}

	/**
	 * 取得word套打模板明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看word套打模板明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		SysWordTemplate sysWordTemplate = sysWordTemplateService.getById(id);	
		List<SysDataSource> dsList = sysDataSourceService.getAll();
		return getAutoView().addObject("sysWordTemplate", sysWordTemplate).addObject("dsList",dsList);
	}
	
	
	/**
	 * 取得word套打模板明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("preview_{alias}")
	@Action(description="预览word套打模板显示")
	public ModelAndView preview(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="alias") String alias) throws Exception
	{
		Long pk = RequestUtil.getLong(request, "pk");
		SysWordTemplate sysWordTemplate = null;
		Map<String, Object> dataMap = null;
		try {
			sysWordTemplate = sysWordTemplateService.getByAlias(alias);
			dataMap = sysWordTemplateService.preivew(sysWordTemplate, pk);
		} catch (Exception e) {
			e.printStackTrace();
			return ServiceUtil.getTipInfo(ExceptionUtils.getRootCauseMessage(e));
		}
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/platform/system/sysWordTemplatePreview.jsp");
		return mv.addObject("sysWordTemplate", sysWordTemplate)
				.addObject("dataJson", JSONObject.toJSON(dataMap));
	}
	
}

