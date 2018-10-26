package com.hotent.platform.controller.bus;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.query.util.QueryConstants;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bus.BusQueryFilter;
import com.hotent.platform.service.bus.BusQueryFilterService;

/**
 * <pre>
 * 对象功能:查询过滤条件 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-12-17 11:23:06
 * </pre>
 */
@Controller
@RequestMapping("/platform/bus/busQueryFilter/")
public class BusQueryFilterController extends BaseController {
	@Resource
	private BusQueryFilterService busQueryFilterService;

	/**
	 * 添加或更新查询过滤条件。
	 * 
	 * @param request
	 * @param response
	 * @param busQueryFilter
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新查询过滤条件")
	public void save(HttpServletRequest request, HttpServletResponse response,
			BusQueryFilter busQueryFilter) throws Exception {
		String resultMsg = null;
		try {
			if (busQueryFilter.getId() == null || busQueryFilter.getId() == 0) {
				busQueryFilter.setId(UniqueIdUtil.genId());
				busQueryFilterService.add(busQueryFilter);
				resultMsg = getText("添加", "查询过滤条件");
			} else {
				busQueryFilterService.update(busQueryFilter);
				resultMsg = getText("更新", "查询过滤条件");
			}
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得查询过滤条件分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看查询过滤条件分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableName = RequestUtil.getString(request, "tableName");
		String url = RequestUtil.getString(request, "url");
		Long userId = ContextUtil.getCurrentUserId();
		List<BusQueryFilter> myFilterList = busQueryFilterService
				.getMyFilterList(tableName, userId);
		List<BusQueryFilter> shareFilterList = busQueryFilterService
				.getShareFilterList(tableName, userId);
		//
		url =  StringUtil.getUrl(url,QueryConstants.IS_QUERY+"="+QueryConstants.IS_QUERY_YES+"&"+ QueryConstants.FILTER_FLAG +"=");
		return this.getAutoView()
				.addObject("myFilterList", myFilterList)
				.addObject("shareFilterList", shareFilterList)
				.addObject("url",url);
	}

	/**
	 * 删除查询过滤条件
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除查询过滤条件")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			busQueryFilterService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除查询过滤条件成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑查询过滤条件
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑查询过滤条件")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		BusQueryFilter busQueryFilter = busQueryFilterService.getById(id);

		return getAutoView().addObject("busQueryFilter", busQueryFilter)
				.addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得查询过滤条件明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看查询过滤条件明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		BusQueryFilter busQueryFilter = busQueryFilterService.getById(id);
		return getAutoView().addObject("busQueryFilter", busQueryFilter);
	}

	/**
	 * 取得查询过滤条件明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveFilter")
	@Action(description = "保存查询过滤条件")
	public void saveFilter(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String resultMsg = null;
		try {
			Map<String, Object> queryMap = RequestUtil.getQueryParams(request);
			String filterName = RequestUtil.getString(request, "filterName");
			String filterDesc = RequestUtil.getString(request, "filterDesc");
			String tableName = RequestUtil.getString(request, "tableName");
			String filterKey = RequestUtil.getString(request, "filterKey");

			BusQueryFilter busQueryFilter = new BusQueryFilter();
			busQueryFilter.setFilterName(filterName);
			busQueryFilter.setFilterDesc(filterDesc);
			busQueryFilter.setTableName(tableName);
			busQueryFilter.setFilterKey(filterKey);
			String sortParameter = getSortParameter(request);
			
			busQueryFilterService.saveFilter(busQueryFilter, queryMap,sortParameter);

			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 获得排序的信息
	 * @param request
	 * @return
	 */
	private String getSortParameter(HttpServletRequest request) {
		String tableIdCode = RequestUtil.getString(request, "tableId_",null);
		if(StringUtils.isEmpty(tableIdCode))
			return null;
		String orderField=request.getParameter(tableIdCode + "s");
		String orderSeqNum=request.getParameter(tableIdCode+ "o");
		String orderSeq="ASC";
		if(orderSeqNum!=null && QueryFilter.ORDER_ASC.equals(orderSeqNum))
			orderSeq="DESC";
		
		if(orderField!=null){
			JSONObject json = new JSONObject();
			json.accumulate("orderField", orderField);
			json.accumulate("orderSeq", orderSeq);
			return json.toString();
		}
		return null;
	}

}
