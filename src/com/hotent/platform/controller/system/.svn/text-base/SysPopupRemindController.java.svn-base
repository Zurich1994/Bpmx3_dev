package com.hotent.platform.controller.system;

import java.util.ArrayList;
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
import com.hotent.core.db.datasource.JdbcTemplateUtil;

import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.util.StringUtil;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.jsonobject.JSONObjectUtil;

import com.hotent.platform.model.bpm.BpmNodeSql;
import com.hotent.platform.model.system.SysPopupRemind;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysPopupRemindService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.core.web.ResultMessage;

/**
 * <pre>
 * 对象功能:sys_popup_remind 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-03-18 11:36:25
 * </pre>
 */
@Controller
@RequestMapping("/platform/system/sysPopupRemind/")
public class SysPopupRemindController extends BaseController {
	@Resource
	private SysPopupRemindService sysPopupRemindService;

	/**
	 * 添加或更新sys_popup_remind。
	 * 
	 * @param request
	 * @param response
	 * @param sysPopupRemind
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新sys_popup_remind")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = FileUtil.inputStream2String(request.getInputStream());
		SysPopupRemind sysPopupRemind = JSONObjectUtil.toBean(json, SysPopupRemind.class);
		String resultMsg = null;
		try {
			if (sysPopupRemind.getId() == null || sysPopupRemind.getId() == 0) {
				sysPopupRemind.setId(UniqueIdUtil.genId());
				sysPopupRemindService.add(sysPopupRemind);
				resultMsg = getText("添加成功", "sys_popup_remind");
			} else {
				sysPopupRemindService.update(sysPopupRemind);
				resultMsg = getText("更新成功", "sys_popup_remind");
			}
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	@RequestMapping("setEnabled")
	@ResponseBody
	public JSONObject setEnabled(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();

		String[] ids = RequestUtil.getStringAryByStr(request, "ids");
		Short enabled = RequestUtil.getShort(request, "enabled", null);
		if (enabled == null || ids.length == 0) {
			result.put("status", "0");
			result.put("msg", "ids,enabled参数错误");
			return result;
		}

		try {
			sysPopupRemindService.updateEnabled(ids, enabled);
			result.put("status", "1");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("status", "0");
			result.put("msg", "更新错误:" + ExceptionUtil.getExceptionMessage(e));
		}

		return result;

	}

	/**
	 * 取得sys_popup_remind分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看sys_popup_remind分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysPopupRemind> list = sysPopupRemindService.getAll(new QueryFilter(request, "sysPopupRemindItem"));
		ModelAndView mv = this.getAutoView().addObject("sysPopupRemindList", list);
		mv.addObject("cls", SysPopupRemind.class.getName());
		return mv;
	}

	/**
	 * 删除sys_popup_remind
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除sys_popup_remind")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			sysPopupRemindService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除sys_popup_remind成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("getObject")
	@Action(description = "按各种参数查询bpmNodeSql")
	@ResponseBody
	public SysPopupRemind getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id", null);
		if (id != null) {
			return sysPopupRemindService.getById(id);
		}
		return null;
	}

	@RequestMapping("show")
	@Action(description = "展示某个用户的弹框，默认当前用户")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = RequestUtil.getString(request, "userId", "");
		SysUser user = ContextUtil.getCurrentUser();
		if (StringUtil.isNotEmpty(userId)) {
			user = AppUtil.getBean(SysUserService.class).getById(Long.parseLong(userId));
		}
		// 获取用户能生效的列表
		List<SysPopupRemind> reminds = sysPopupRemindService.getByUser(user);
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		for (SysPopupRemind spr : reminds) {
			String sql = spr.getSql().replace("{curUserId}", ContextUtil.getCurrentUserId().toString());
			int count = JdbcTemplateUtil.getNewJdbcTemplate(spr.getDsalias()).queryForInt(sql);
			if (count <= 0) {// 没有数据
				continue;
			}
			JSONObject json = new JSONObject();

			String str = "您有 " + count + " 个 " + spr.getDesc();
			json.put("msg", str);
			json.put("url", spr.getUrl());
			json.put("subject", spr.getSubject());
			jsonList.add(json);
		}

		ModelAndView mv = this.getAutoView().addObject("jsonList", jsonList);
		return mv;
	}

	@RequestMapping("showSize")
	@Action(description = "获取userId的弹框数据的大小，用来判断是否需要弹框")
	@ResponseBody
	public JSONObject showSize(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jsonObject = new JSONObject();

		String userId = RequestUtil.getString(request, "userId", "");
		SysUser user = ContextUtil.getCurrentUser();
		if (StringUtil.isNotEmpty(userId)) {
			user = AppUtil.getBean(SysUserService.class).getById(Long.parseLong(userId));
		}
		// 获取用户能生效的列表
		List<SysPopupRemind> reminds = sysPopupRemindService.getByUser(user);

		int size = 0;
		for (SysPopupRemind spr : reminds) {
			String sql = spr.getSql().replace("{curUserId}", ContextUtil.getCurrentUserId().toString());
			int count = JdbcTemplateUtil.getNewJdbcTemplate(spr.getDsalias()).queryForInt(sql);
			if (count <= 0) {// 没有数据
				continue;
			}
			size++;
		}
		jsonObject.put("size", size);
		return jsonObject;
	}

}
