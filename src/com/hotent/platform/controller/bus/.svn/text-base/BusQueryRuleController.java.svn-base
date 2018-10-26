package com.hotent.platform.controller.bus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.query.scan.SearchCache;
import com.hotent.core.web.query.scan.TableEntity;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bus.BusQueryRule;
import com.hotent.platform.model.form.CommonVar;
import com.hotent.platform.service.bus.BusQueryRuleService;

/**
 * <pre>
 * 对象功能:高级查询规则 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-12-09 14:19:29
 * </pre>
 */
@Controller
@RequestMapping("/platform/bus/busQueryRule/")
public class BusQueryRuleController extends BaseController {
	@Resource
	private BusQueryRuleService busQueryRuleService;

	/**
	 * 添加或更新高级查询规则。
	 * 
	 * @param request
	 * @param response
	 * @param busQueryRule
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新高级查询规则")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultMsg = null;
		BusQueryRule busQueryRule = getFormObject(request);
		try {
			if (busQueryRule.getId() == null || busQueryRule.getId() == 0) {
				busQueryRule.setId(UniqueIdUtil.genId());
				busQueryRuleService.add(busQueryRule);
				resultMsg = getText("添加", "高级查询规则");
			} else {
				busQueryRuleService.update(busQueryRule);
				resultMsg = getText("更新", "高级查询规则");
			}
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得 BpmDataTemplate 实体
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected BusQueryRule getFormObject(HttpServletRequest request)
			throws Exception {

		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher((new String[] { "yyyy-MM-dd HH:mm:ss" })));

		String json = RequestUtil.getString(request, "json",false);
		if (StringUtil.isEmpty(json))
			return null;
		JSONObject obj = JSONObject.fromObject(json);
		String displayField=obj.getString("displayField");
		String sortField=obj.getString("sortField");
		String filterField=obj.getString("filterField");
		String exportField=obj.getString("exportField");
		
		obj.remove("displayField");
		obj.remove("conditionField");
		obj.remove("sortField");
		obj.remove("filterField");
		obj.remove("manageField");
		
		BusQueryRule busQueryRule = (BusQueryRule) JSONObject.toBean(obj, BusQueryRule.class);
		
		busQueryRule.setDisplayField(displayField);
		busQueryRule.setSortField(sortField);
		busQueryRule.setFilterField(filterField);
		busQueryRule.setExportField(exportField);
		
		
		return busQueryRule;
	}

	/**
	 * 取得高级查询规则分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看高级查询规则分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<TableEntity> list = TableEntity.getAll(new QueryFilter(request,
				"tableEntityItem"));

		Map<String, Integer> queryRuleCounts = new HashMap<String, Integer>();
		
		for (TableEntity tableEntity : list) {
			String tableName = tableEntity.getTableName();
			queryRuleCounts.put(tableName, busQueryRuleService.getCountByTableName(tableName));
		}

		return this.getAutoView().addObject("tableEntityList", list)
				.addObject("queryRuleCounts", queryRuleCounts);
	}

	/**
	 * 删除高级查询规则
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除高级查询规则")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			busQueryRuleService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除高级查询规则成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑高级查询规则
	 * 
	 * @param request
	 * @param response
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit_{tableName}")
	@Action(description = "编辑高级查询规则")
	public ModelAndView editTable(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "tableName") String tableName)
			throws Exception {
		return new ModelAndView("redirect:edit.ht?tableName=" + tableName);
	}

	/**
	 * 编辑高级查询规则
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑高级查询规则")
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableName = RequestUtil.getString(request, "tableName");
		TableEntity tableEntity = SearchCache.getTableEntityMap().get(
				tableName);
		BusQueryRule busQueryRule = busQueryRuleService
				.getByTableEntity(tableEntity);

		return getAutoView().addObject("busQueryRule", busQueryRule)
				.addObject("tableEntity", tableEntity)
				.addObject("bpmFormTableJSON", JSONObject.fromObject(tableEntity))
				.addObject("DataRightsJson",JSONObject.fromObject(busQueryRule));
	}

	/**
	 * 取得高级查询规则明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看高级查询规则明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		BusQueryRule busQueryRule = busQueryRuleService.getById(id);
		return getAutoView().addObject("busQueryRule", busQueryRule);
	}

	/**
	 * 过滤条件窗口
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("filterDialog")
	public ModelAndView filterDialog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableName = RequestUtil.getString(request, "tableName");
		TableEntity tableEntity = SearchCache.getTableEntityMap().get(
				tableName);
		List<CommonVar> commonVars = CommonVar.geCommonVars();
		return this.getAutoView().addObject("commonVars", commonVars)
				.addObject("tableEntity", tableEntity)
				.addObject("tableName", tableName);
	}
	
	
	
	/**
	 * 过滤条件窗口->脚本
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("script")
	public ModelAndView script(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableName = RequestUtil.getString(request, "tableName");
		TableEntity tableEntity = SearchCache.getTableEntityMap().get(
				tableName.toLowerCase());
		List<CommonVar> commonVars = CommonVar.geCommonVars();
		return this.getAutoView().addObject("commonVars", commonVars)
				.addObject("tableEntity", tableEntity)
				.addObject("tableName", tableName);
	}

}
