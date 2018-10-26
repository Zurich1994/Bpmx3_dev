package com.hotent.platform.controller.system;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.DesktopColumn;
import com.hotent.platform.model.system.DesktopLayout;
import com.hotent.platform.model.system.DesktopLayoutcol;
import com.hotent.platform.model.system.DesktopMycolumn;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.system.DesktopColumnService;
import com.hotent.platform.service.system.DesktopLayoutService;
import com.hotent.platform.service.system.DesktopMycolumnService;

/**
 * 对象功能:桌面个人栏目 控制器类 开发公司:广州宏天软件有限公司 开发人员:ray 创建时间:2012-03-20 16:39:01
 */
@Controller
@RequestMapping("/platform/system/desktopMycolumn/")
@Action(ownermodel=SysAuditModelType.DESKTOP_MANAGEMENT)
public class DesktopMycolumnController extends BaseController {
	@Resource
	private DesktopMycolumnService desktopMycolumnService;
	@Resource
	private DesktopColumnService desktopColumnService;
	@Resource
	private DesktopLayoutService desktopLayoutService;
	@Resource
	private FreemarkEngine freemarkEngine;
	private String columnWidth;

	/**
	 * 取得桌面个人栏目分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看桌面个人栏目分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<DesktopMycolumn> list = desktopMycolumnService.getAll(new QueryFilter(request, "desktopMycolumnItem"));
		ModelAndView mv = this.getAutoView().addObject("desktopMycolumnList", list);
		return mv;
	}

	/**
	 * 删除桌面个人栏目
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除桌面个人栏目",execOrder=ActionExecOrder.BEFORE,
			detail="删除桌面个人栏目" +
					"<#list StringUtils.split(id,\",\") as item>" +
					"<#assign entity=desktopMycolumnService.getById(Long.valueOf(item))/>" +
					"【${entity.columnName}】"+
					"</#list>")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			desktopMycolumnService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除桌面个人栏目成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑桌面个人栏目
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑桌面个人栏目")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		String returnUrl = RequestUtil.getPrePage(request);
		DesktopMycolumn desktopMycolumn = null;
		if (id != 0) {
			desktopMycolumn = desktopMycolumnService.getById(id);
		} else {
			desktopMycolumn = new DesktopMycolumn();
		}
		return getAutoView().addObject("desktopMycolumn", desktopMycolumn).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得桌面个人栏目明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看桌面个人栏目明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "id");
		long canReturn = RequestUtil.getLong(request, "canReturn",0);
		DesktopMycolumn desktopMycolumn = desktopMycolumnService.getById(id);
		return getAutoView().addObject("desktopMycolumn", desktopMycolumn).addObject("canReturn", canReturn);
	}

	/**
	 * 获取个人桌面栏目布局数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping("getMycolumnData")
	@ResponseBody
	public ModelAndView getLayoutcolData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ctxPath = request.getContextPath();
		String html = getSelfDeskData(ctxPath);
		return getAutoView().addObject("html", html);
	}

	/**
	 * 显示个人桌面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("show")
	@Action(description = "显示个人桌面")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DesktopLayout bean = desktopMycolumnService.getShowData(ContextUtil.getCurrentUserId());
		Map<String, String> desktopLayoutmap = new HashMap<String, String>();
		Map<String, String> desktopColumnmap = new HashMap<String, String>();
		List<DesktopColumn> desktopColumnList = desktopColumnService.getAll();
		// 桌面所有栏目
		for (DesktopColumn dc : desktopColumnList) {
			desktopColumnmap.put("" + dc.getId(), dc.getColumnName());
		}
		desktopLayoutmap.put("cols", "" + bean.getCols());
		desktopLayoutmap.put("widths", bean.getWidth());
		desktopLayoutmap.put("id", "" + bean.getId());
		return getAutoView().addObject("desktopLayoutmap", desktopLayoutmap)
							.addObject("desktopColumnmap", desktopColumnmap)
							.addObject("desktop", "show");
	}

	private String getSelfDeskData(String ctxPath) throws Exception {
		long userId = ContextUtil.getCurrentUserId();
		Map mapData = desktopMycolumnService.getMyDeskData(userId, ctxPath);
		return freemarkEngine.mergeTemplateIntoString("desktop/getDeskTop.ftl", mapData);
	}

	/**
	 * 个人桌面布局设置
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("news")
	@Action(description = "个人桌面重置布局")
	public ModelAndView news(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<DesktopLayout> desktopLayout = desktopLayoutService.getAll();
		DesktopLayout bean = desktopMycolumnService.getShowData(ContextUtil.getCurrentUserId());
		if(BeanUtils.isEmpty(bean)){
			bean=desktopLayoutService.getDefaultLayout();
		}
		List<DesktopColumn> desktopColumnList = desktopColumnService.getAll();
		String ctxPath = request.getContextPath();
		String html = getSelfDeskData(ctxPath);
		return getAutoView().addObject("desktopLayoutmap", bean)
							.addObject("desktopColumnList", desktopColumnList)
							.addObject("desktopLayout", desktopLayout)
							.addObject("html", html);
	}

	/**
	 * 个人桌面编辑布局
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("change")
	@Action(description = "个人桌面编辑布局")
	public ModelAndView change(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DesktopLayout bean = desktopMycolumnService.getShowData(ContextUtil.getCurrentUserId());
		Map<String, String> desktopLayoutmap = new HashMap<String, String>();
		Map<String, String> desktopColumnmap = new HashMap<String, String>();
		List<DesktopColumn> desktopColumnList = desktopColumnService.getAll();
		// 桌面所有栏目
		for (DesktopColumn dc : desktopColumnList) {
			desktopColumnmap.put("" + dc.getId(), dc.getColumnName());
		}
		desktopLayoutmap.put("cols", "" + bean.getCols());
		desktopLayoutmap.put("widths", bean.getWidth());
		desktopLayoutmap.put("id", "" + bean.getId());
		return getAutoView().addObject("desktopLayoutmap", desktopLayoutmap)
							.addObject("desktopColumnmap", desktopColumnmap)
							.addObject("desktop", "change");
	}

	/**
	 * 空栏目提醒
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("warm")
	@Action(description = "提醒")
	public ModelAndView warm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return getAutoView();
	}

	/**
	 * 保存桌面布局
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveCol")
	public void saveCol(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long userId = ContextUtil.getCurrentUserId();
		Long layoutId = RequestUtil.getLong(request, "layoutId");
		String dataStr = RequestUtil.getString(request, "data");
		JSONArray jsoA = JSONArray.fromObject(dataStr);
		List<DesktopMycolumn> list = new ArrayList<DesktopMycolumn>();
		for (int i = 0; i < jsoA.size(); i++) {
			JSONObject jsoO = jsoA.getJSONObject(i);
			DesktopMycolumn desktopMy = new DesktopMycolumn();
			desktopMy.setCol((short) jsoO.getInt("col"));
			desktopMy.setSn(jsoO.getInt("sn"));
			desktopMy.setColumnId(jsoO.getLong("columnId"));
			desktopMy.setId(UniqueIdUtil.genId());
			desktopMy.setLayoutId(layoutId);
			desktopMy.setUserId(userId);
			list.add(desktopMy);
		}
		ResultMessage resultObj = null;
		PrintWriter out = response.getWriter();
		if (list.size() < 0 || layoutId==0) {
			resultObj = new ResultMessage(ResultMessage.Fail, "桌面布局异常，不能保存");
		} else {
			desktopMycolumnService.saveMycolumn(list, layoutId, userId);
			resultObj = new ResultMessage(ResultMessage.Success, "保存成功");
		}		
		out.print(resultObj);
	}

	public String getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(String columnWidth) {
		this.columnWidth = columnWidth;
	}
}
