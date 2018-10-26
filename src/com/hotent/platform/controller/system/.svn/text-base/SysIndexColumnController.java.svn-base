package com.hotent.platform.controller.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysIndexColumn;
import com.hotent.platform.model.system.SysObjRights;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysOrgTactic;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.service.system.SysIndexColumnService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysOrgTacticService;
import com.hotent.platform.service.system.impl.curuser.OrgSubUserService;

/**
 * <pre>
 * 对象功能:首页栏目 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-18 11:22:46
 * </pre>
 */
@Controller
@RequestMapping("/platform/system/sysIndexColumn/")
public class SysIndexColumnController extends BaseController {
	@Resource
	private SysIndexColumnService sysIndexColumnService;
	@Resource
	private SysOrgService sysOrgService;

	@Resource
	private GlobalTypeService globalTypeService;
	@Resource
	private SysOrgTacticService sysOrgTacticService;
	@Resource
	private OrgSubUserService orgSubUserService;

	/**
	 * 添加或更新首页栏目。
	 * 
	 * @param request
	 * @param response
	 * @param sysIndexColumn
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新首页栏目")
	public void save(HttpServletRequest request, HttpServletResponse response,
			SysIndexColumn sysIndexColumn) throws Exception {
		String resultMsg = null;
		try {
			String alias = sysIndexColumn.getAlias();
			// 判断别名是否存在。
			Boolean isExist = sysIndexColumnService.isExistAlias(
					sysIndexColumn.getAlias(), sysIndexColumn.getId());
			if (isExist) {
				resultMsg = "栏目别名：[" + alias + "]已存在";
				writeResultMessage(response.getWriter(), resultMsg,
						ResultMessage.Fail);
				return;
			}
			if (!SysUser.isSuperAdmin()) {// 把自己的组织设置进去
				Long orgId = sysOrgTacticService.getByCurOrgId(ContextUtil
						.getCurrentOrg());
				if (BeanUtils.isNotEmpty(orgId))
					sysIndexColumn.setOrgId(orgId);
			}
			if (sysIndexColumn.getId() == null || sysIndexColumn.getId() == 0) {
				sysIndexColumn.setId(UniqueIdUtil.genId());
				sysIndexColumnService.add(sysIndexColumn);
				resultMsg = getText("添加首页栏目成功");
			} else {
				sysIndexColumnService.update(sysIndexColumn);
				resultMsg = getText("更新首页栏目成功");
			}
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 添加或更新首页栏目。
	 * 
	 * @param request
	 * @param response
	 * @param sysIndexColumn
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveOrg")
	@Action(description = "添加或更新首页栏目")
	public void saveOrg(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String resultMsg = null;
		Long id = RequestUtil.getLong(request, "id");
		Long orgId = RequestUtil.getLong(request, "orgId", null);
		try {
			SysIndexColumn sysIndexColumn = sysIndexColumnService.getById(id);
			sysIndexColumn.setOrgId(orgId);
			sysIndexColumnService.update(sysIndexColumn);
			resultMsg = getText("保存组织成功");
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得首页栏目分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看首页栏目分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "sysIndexColumnItem");
		boolean isSuperAdmin = SysUser.isSuperAdmin();
		List<SysIndexColumn> list = sysIndexColumnService
				.getHashRightColumnList(filter, null, false);
		for (SysIndexColumn sysIndexColumn : list) {
			if (BeanUtils.isNotEmpty(sysIndexColumn.getOrgId())) {
				SysOrg sysOrg = sysOrgService
						.getById(sysIndexColumn.getOrgId());
				sysIndexColumn.setOrgName(sysOrg.getOrgName());
			}
		}
		SysOrgTactic sysOrgTactic = sysOrgTacticService.getOrgTactic();

		return this.getAutoView()
				.addObject("objType", SysObjRights.RIGHT_TYPE_INDEX_COLUMN)
				.addObject("sysIndexColumnList", list)
				.addObject("isSuperAdmin", isSuperAdmin)
				.addObject("orgTactic", sysOrgTactic.getOrgTactic());
	}

	/**
	 * 删除首页栏目
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除首页栏目")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			sysIndexColumnService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除首页栏目成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑首页栏目
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑首页栏目")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		SysIndexColumn sysIndexColumn = sysIndexColumnService.getById(id);
		if (BeanUtils.isNotEmpty(sysIndexColumn)) {
			if (BeanUtils.isNotEmpty(sysIndexColumn.getOrgId())) {
				SysOrg sysOrg = sysOrgService
						.getById(sysIndexColumn.getOrgId());
				sysIndexColumn.setOrgName(sysOrg.getOrgName());
			}
		}
		List<GlobalType> catalogList = globalTypeService.getByCatKey(
				GlobalType.CAT_INDEX_COLUMN, false);

		return getAutoView().addObject("sysIndexColumn", sysIndexColumn)
				.addObject("catalogList", catalogList)
				.addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得首页栏目明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看首页栏目明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		SysIndexColumn sysIndexColumn = sysIndexColumnService.getById(id);
		return getAutoView().addObject("sysIndexColumn", sysIndexColumn);
	}

	/**
	 * 预览模版
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getTemp")
	@Action(description = "预览模版")
	public ModelAndView getTemp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		Map<String, Object> params = RequestUtil.getParameterValueMap(request);
		String html = "";
		try {
			html = sysIndexColumnService.getHtmlById(id, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAutoView().addObject("html", html);
	}

	/**
	 * 取得首页栏目明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getData")
	@Action(description = "查看首页栏目明细")
	@ResponseBody
	public String getData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String alias = RequestUtil.getString(request, "alias");
		Map<String, Object> params = getParameterValueMap(request);
		String data = "";
		try {
			data = sysIndexColumnService.getHtmlByColumnAlias(alias, params);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return data;
	}

	private Map<String, Object> getParameterValueMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("__ctx", request.getContextPath());
		String params = RequestUtil.getString(request, "params");
		if (BeanUtils.isEmpty(params))
			return map;
		JSONObject json = JSONObject.fromObject(params);
		Iterator<?> it = json.keys();
		while (it.hasNext()) {
			String key = (String) it.next();
			Object value = json.get(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 初始化模板信息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("init")
	@Action(description = "初始化模板", detail = "初始化模板")
	public void init(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			sysIndexColumnService.initIndexColumn();
			message = new ResultMessage(ResultMessage.Success, "初始化模板成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "初始化表模板失败:"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

}
