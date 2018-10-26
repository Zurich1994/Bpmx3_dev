package com.hotent.platform.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.excel.util.ExcelUtil;

import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.form.BpmFormTemplate;
import com.hotent.platform.model.form.CommonVar;
import com.hotent.platform.model.system.SysQueryField;
import com.hotent.platform.model.system.SysQuerySetting;
import com.hotent.platform.service.form.BpmFormTemplateService;
import com.hotent.platform.service.system.SysQueryFieldService;
import com.hotent.platform.service.system.SysQuerySettingService;
import com.hotent.core.web.ResultMessage;
import com.hotent.platform.model.form.BpmFormTemplate;
import com.hotent.platform.model.form.CommonVar;
import com.hotent.platform.service.form.BpmFormTemplateService;

/**
 * <pre>
 * 对象功能:SYS_QUERY_SETTING 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Aschs
 * 创建时间:2014-05-22 12:43:14
 * </pre>
 */
@Controller
@RequestMapping("/platform/system/sysQuerySetting/")
public class SysQuerySettingController extends BaseController {
	@Resource
	private BpmFormTemplateService bpmFormTemplateService; // 模板serivce类
	@Resource
	private SysQuerySettingService sysQuerySettingService; // 配置表service类
	@Resource
	private SysQueryFieldService sysQueryFieldService; // 字段表service类

	
	/**
	 * 编辑
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑SYS_QUERY_SETTING")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long sqlId = RequestUtil.getLong(request, "sqlId", 0L);
		List<BpmFormTemplate> templates = bpmFormTemplateService.getQueryDataTemplate();// 获取模板对象集
		BpmFormTemplate bpp = templates.get(0);
		System.out.println(bpp.getAlias());
		SysQuerySetting sysQuerySetting = sysQuerySettingService.getBySqlId(sqlId);// 获取配置对象
		List<SysQueryField> sysQueryFields = sysQueryFieldService.getDisplayFieldListBySqlId(sqlId);// 排序用到
		return this.getAutoView().addObject("templates", templates)
							.addObject("bpmDataTemplate", sysQuerySetting)
							.addObject("DataRightsJson",JSONObject.fromObject(sysQuerySetting))
							.addObject("bpmFormTableJSON", JSONArray.fromObject(sysQueryFields));
	}

	
	/**
	 * 添加或更新业务数据模板
	 * 
	 * @param request
	 * @param response
	 * @param bpmDataTemplate
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新业务查询数据模板", execOrder = ActionExecOrder.AFTER, detail = "<#if isAdd>添加<#else>更新</#if>")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultMsg = null;
		String sysQuerySettingJson = request.getParameter("json");
		SysQuerySetting sysQuerySetting = sysQuerySettingService.getSysQuerySetting(sysQuerySettingJson);
		try {
			boolean flag = false; // 判断是添加还是保存
			if (sysQuerySetting.getId() == null || sysQuerySetting.getId() == 0){
				flag = true;
			}
			
			boolean recreateTemplete=RequestUtil.getBoolean(request, "recreateTemplete");
			
			sysQuerySettingService.save(sysQuerySetting, flag,recreateTemplete);
			resultMsg = flag ? "添加业务查询模板成功" : "更新业务查询模板成功";
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
		} catch (Exception e) {
			e.printStackTrace();
			writeResultMessage(response.getWriter(), resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 过滤条件窗口
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("filterDialog")
	public ModelAndView filterDialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long sqlId = RequestUtil.getLong(request, "sqlId", 0L);
		// 获取fields
		List<SysQueryField> sysQueryFields = sysQueryFieldService.getListBySqlId(sqlId);
		List<CommonVar> commonVars = CommonVar.geCommonVars();
		return this.getAutoView().addObject("commonVars", commonVars).addObject("sysQueryFields", sysQueryFields).addObject("source", "1");

	}

	/**
	 * 脚本
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("script")
	public ModelAndView script(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long sqlId = RequestUtil.getLong(request, "sqlId", 0L);
		List<SysQueryField> sysQueryFields = sysQueryFieldService.getListBySqlId(sqlId);

		List<CommonVar> commonVars = CommonVar.geCommonVars();

		return this.getAutoView().addObject("commonVars", commonVars).addObject("sysQueryFields", sysQueryFields).addObject("source", "1");

	}

	/**
	 * 预览
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("show/{alias}")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("alias") String alias ) throws Exception {
		// 取得当前页URL,如有参数则带参数
		String __baseURL = request.getRequestURI().replace("/show/"+alias+".ht", "/getDisplay/"+alias+".ht");
		Map<String, Object> params = RequestUtil.getQueryMap(request);
		Map<String, Object> queryParams = RequestUtil.getQueryParams(request);
		// 取得传入参数ID
		//Long id = RequestUtil.getLong(request, "__displayId__");
		params.put("__baseURL", __baseURL);
		params.put(SysQuerySetting.PARAMS_KEY_CTX, request.getContextPath());
		params.put("__tic", "sysQuerySetting");

		String html = sysQuerySettingService.getDisplay(alias, params, queryParams);
		ModelAndView mv=new ModelAndView("/platform/system/sysQuerySettingPreview.jsp");
		return mv.addObject("html", html);
	}

	/**
	 * 查询数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getDisplay/{alias}")
	public Map<String, Object> getDisplay(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("alias") String alias) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try {
			Map<String, Object> params = RequestUtil.getQueryMap(request);
			// 人为把加上的‘|’去掉  by aschs
			/*for (Iterator it = params.entrySet().iterator(); it.hasNext();) {
				Map.Entry entry = (Map.Entry) it.next();
				if (!(entry.getKey() + "").equals("_display")) {
					entry.setValue((entry.getValue() + "").replace("|_", "_"));
					params.put((String) entry.getKey(), entry.getValue());
				}
			}*/
			Map<String, Object> queryParams = RequestUtil.getQueryParams(request);
			
			String __baseURL = request.getRequestURI();
			params.put("__baseURL", __baseURL);
			params.put("__ctx", request.getContextPath());
			params.put("__displayId__", alias);
			params.put(SysQuerySetting.PARAMS_KEY_FILTERKEY, RequestUtil.getString(request, SysQuerySetting.PARAMS_KEY_FILTERKEY));
			params.put(SysQuerySetting.PARAMS_KEY_ISQUERYDATA, true);
			params.put("__tic", "sysQuerySetting");
			String html = sysQuerySettingService.getDisplay(alias, params, queryParams);
			map.put("html", html);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", e.getMessage());
		}
		return map;
	}

	/**
	 * 导出数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportData")
	@Action(description = "导出业务数据模板数据", detail = "导出业务数据模板数据")
	public void exportData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = RequestUtil.getQueryMap(request);
		Long id = RequestUtil.getLong(request, "__displayId__");
		// 暂时不做定点导出的(不知道那个是PK)
		// String [] exportIds = RequestUtil.getStringAryByStr(request, "__exportIds__");
		int exportType = RequestUtil.getInt(request, "__exportType__");
		HSSFWorkbook wb = sysQuerySettingService.export(id, exportType, params);
		String fileName = "SysQuery_" + DateFormatUtil.getNowByString("yyyyMMddHHmmdd");
		ExcelUtil.downloadExcel(wb, fileName, response);
	}

	/**
	 * 编辑模板
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editTemplate")
	public ModelAndView editTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		SysQuerySetting sysQuerySetting = sysQuerySettingService.getById(id);
		return getAutoView().addObject("sysQuerySetting", sysQuerySetting);
	}

	/**
	 * 保存模板
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveTemplate")
	public void saveTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultMsg = "";
		Long id = RequestUtil.getLong(request, "id");
		String templateHtml = RequestUtil.getString(request, "templateHtml");

		templateHtml = templateHtml.replace("''", "'");
		SysQuerySetting sysQuerySetting = sysQuerySettingService.getById(id);
		sysQuerySetting.setTemplateHtml(templateHtml);
		sysQuerySettingService.update(sysQuerySetting);
		resultMsg = "更新自定义表管理显示模板成功";
		writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
	}
}
