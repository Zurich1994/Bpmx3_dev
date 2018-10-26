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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.engine.FreemarkEngine;
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
import com.hotent.platform.service.system.DesktopLayoutcolService;
import com.hotent.platform.service.system.DesktopMycolumnService;

/**
 * 对象功能:桌面栏目管理表 控制器类 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:ray 
 * 创建时间:2012-03-20 16:39:01
 */
@Controller
@RequestMapping("/platform/system/desktopLayoutcol/")
@Action(ownermodel=SysAuditModelType.DESKTOP_MANAGEMENT)
public class DesktopLayoutcolController extends BaseController {
	@Resource
	private DesktopLayoutcolService desktopLayoutcolService;
	@Resource
	private DesktopColumnService desktopColumnService;
	@Resource
	private DesktopLayoutService desktopLayoutService;
	@Resource
	private DesktopMycolumnService desktopMycolumnService;
	@Resource
	private FreemarkEngine freemarkEngine;

	/**
	 * 取得桌面栏目管理表分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看桌面栏目管理表分页列表",detail = "查看桌面栏目管理表分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<DesktopLayoutcol> list = desktopLayoutcolService
				.getAll(new QueryFilter(request, "desktopLayoutcolItem"));
	
		ModelAndView mv = this.getAutoView().addObject("desktopLayoutcolList",list);
		return mv;
	}

	/**
	 * 删除桌面栏目管理表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除桌面栏目管理表",
			execOrder=ActionExecOrder.BEFORE,
			detail= "删除桌面栏目管理表：" +
					"<#list StringUtils.split(id,\",\") as item>" +
						"<#assign entity=desktopLayoutcolService.getById(Long.valueOf(item))/>" +
						"【${entity.layoutName}】" +
					"</#list>"
			)
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
		ResultMessage message = null;
		try {
			desktopLayoutcolService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除桌面栏目管理表成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败:"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑桌面栏目管理
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑桌面栏目管理表",
			detail="编辑桌面栏目管理表:" +
					"<#assign entity=desktopLayoutcolService.getById(Long.valueOf(id))/>" +
					"【<a href='${ctx}/bpmx/platform/system/desktopLayoutcol/get.ht?id=${id}'>${entity.layoutName}</a>】"
					)
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		String returnUrl = RequestUtil.getPrePage(request);
		DesktopLayoutcol desktopLayoutcol = null;
		Map<String, String> desktopColumnmap = new HashMap<String, String>();
		Map<String, String> desktopLayoutmap = new HashMap<String, String>();
		desktopLayoutcol = desktopLayoutcolService.editData(id, desktopColumnmap, desktopLayoutmap);
		return getAutoView().addObject("desktopLayoutcol", desktopLayoutcol)
				.addObject("returnUrl", returnUrl)
				.addObject("desktopColumnmap", desktopColumnmap)
				.addObject("desktopLayoutmap", desktopLayoutmap);
	}

	/**
	 * 取得桌面栏目管理表明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看桌面栏目管理表明细",detail="查看桌面栏目管理表明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "id");
		DesktopLayoutcol desktopLayoutcol = desktopLayoutcolService.getById(id);
		return getAutoView().addObject("desktopLayoutcol", desktopLayoutcol);
	}

	/**
	 * 查看默认布局的栏目
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("show")
	@Action(description = "查看桌面显示",detail = "查看桌面显示")
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long layoutId = RequestUtil.getLong(request, "id");
		String ctxPath= request.getContextPath();
		List list=desktopColumnService.getAll();
		List layoutcolList=desktopLayoutcolService.layoutcolData(layoutId);
		DesktopLayout desktopLayout=desktopLayoutService.getById(layoutId);	
		Map mapData = desktopMycolumnService.getDefaultDeskDataById(layoutId, ctxPath);
		String html = freemarkEngine.mergeTemplateIntoString("desktop/getDeskTop.ftl", mapData);
		
		return getAutoView().addObject("desktopColumnList", list)
							.addObject("html",html)
							.addObject("desktopLayout",desktopLayout);
	}

	/**
	 * 获取布局列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getLayoutcolData")
	@ResponseBody
	public List<DesktopLayoutcol> getLayoutcolData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long layoutId=RequestUtil.getLong(request,"id");
		List<DesktopLayoutcol> list = desktopLayoutcolService.layoutcolData(layoutId);
		DesktopLayout layout=desktopLayoutService.getById(layoutId);
		for(DesktopLayoutcol layoutCol:list){
			Long colId=layoutCol.getColumnId();
			DesktopColumn  desktopColumn=desktopColumnService.getById(colId);
			layoutCol.setWidths(layout.getWidth());
			layoutCol.setColumnName(desktopColumn.getColumnName());
			layoutCol.setColumnUrl(desktopColumn.getColumnUrl());
		}
		return list;
	}

	/**
	 * 保存布局栏目
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveCol")
	public void saveCol(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long userId = ContextUtil.getCurrentUserId();
		Long layoutId = RequestUtil.getLong(request, "layoutId");
		String dataStr = RequestUtil.getString(request, "data");
		JSONArray jsoA = JSONArray.fromObject(dataStr);
		List<DesktopLayoutcol> list = new ArrayList<DesktopLayoutcol>();
		for (int i = 0; i < jsoA.size(); i++) {
			JSONObject jsoO = jsoA.getJSONObject(i);
			DesktopLayoutcol desktopLayoutcol = new DesktopLayoutcol();
			desktopLayoutcol.setCol(jsoO.getInt("col"));
			desktopLayoutcol.setSn(jsoO.getInt("sn"));
			desktopLayoutcol.setColumnId(jsoO.getLong("columnId"));
			desktopLayoutcol.setId(UniqueIdUtil.genId());
			desktopLayoutcol.setLayoutId(layoutId);			
			list.add(desktopLayoutcol);
		}
		ResultMessage resultObj = null;
		PrintWriter out = response.getWriter();
		if (list.size() < 0 || layoutId==0) {
			resultObj = new ResultMessage(ResultMessage.Fail, "栏目设置异常，不能保存");
		} else {
			desktopLayoutcolService.saveCol(list, layoutId);
			resultObj = new ResultMessage(ResultMessage.Success, "保存成功");
		}		
		out.print(resultObj);
	}
	
	
}
