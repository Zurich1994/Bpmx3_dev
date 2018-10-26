package com.hotent.platform.controller.form;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.form.BpmFormData;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.service.form.BpmFormTableDataService;
import com.hotent.platform.service.form.BpmFormTableService;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;

/**
 * 对象功能:数据 控制器类 开发公司:广州宏天软件有限公司 开发人员:xwy 创建时间:2011-12-22 11:07:56
 */
@Controller
@RequestMapping("/platform/form/bpmFormTableData/")
public class BpmFormTableDataController extends BaseController
{

	@Resource
	private BpmFormTableDataService service;
	
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private FreemarkEngine freemarkEngine;
	
	/**
	 * 查看数据列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看数据列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page, Long tableId) throws Exception
	{

		
		BpmFormTable table = bpmFormTableService.getById(tableId);
		
		Map<String, String[]> map = request.getParameterMap();
		Map<String, Object> params = new HashMap<String, Object>();
		for(Map.Entry<String, String[]> entry : map.entrySet()) {
			String key = entry.getKey();
			if(key.startsWith("Q_") && !"".equals(entry.getValue()[0])) {
				params.put(key.substring(key.indexOf("_") + 1, key.lastIndexOf("_")), "%" + entry.getValue()[0] + "%");
			}
		}
		
		List<Map<String, Object>> list = service.getAll(tableId, params);
		
		String listTemplate = table.getListTemplate();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("list", list);
		 
		
		String html=freemarkEngine.parseByStringTemplate(data, listTemplate);
		
		return this.getAutoView().addObject("html", html);
	}

	/**
	 * 查看数据列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	@Action(description = "查看数据明细")
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page, Long tableId, String pkValue) throws Exception
	{

		BpmFormTable table = bpmFormTableService.getById(tableId);
		
		BpmFormData bpmFormData = service.getByKey(tableId, pkValue);
		
		String detailTemplate = table.getDetailTemplate();
		
		Configuration cfg = new Configuration();
		cfg.setClassicCompatible(true);
		StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
		cfg.setTemplateLoader(stringTemplateLoader);
		stringTemplateLoader.putTemplate("detailTemplate", detailTemplate);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("data", bpmFormData.getMainFields());
		data.put("subDatas", bpmFormData.getSubTableMap());
		
		StringWriter writer = new StringWriter();
		cfg.getTemplate("detailTemplate").process(data, writer);
		
		return this.getAutoView().addObject("html", writer.toString()).addObject("returnUrl", RequestUtil.getPrePage(request));
	}

}
