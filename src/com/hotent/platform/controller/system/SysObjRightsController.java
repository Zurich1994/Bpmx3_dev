package com.hotent.platform.controller.system;

import java.util.List;

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
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.jsonobject.JSONObjectUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysObjRights;
import com.hotent.platform.service.system.ICurUserService;
import com.hotent.platform.service.system.SysObjRightsService;

/**
 * <pre>
 * 对象功能:对象权限表 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-04-09 17:19:22
 * </pre>
 */
@Controller
@RequestMapping("/platform/system/sysObjRights/")
public class SysObjRightsController extends BaseController {
	@Resource
	private SysObjRightsService sysObjRightsService;

	/**
	 * 添加或更新对象权限表。
	 * 
	 * @param request
	 * @param response
	 * @param sysObjRights
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新对象权限表")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String objType=RequestUtil.getString(request, "objType");
			String objectId=RequestUtil.getString(request, "objectId");
			// 先删除旧数据
			if (StringUtil.isNotEmpty(objectId)&&StringUtil.isNotEmpty(objType)) {
				sysObjRightsService.deleteByObjTypeAndObjectId(objType, objectId);
			}

			String sysObjRightsStr = RequestUtil.getString(request, "sysObjRights");

			JSONArray jsonArray = JSONArray.fromObject(sysObjRightsStr);
			for (Object obj : jsonArray) {
				SysObjRights sysObjRights = JSONObjectUtil.toBean(obj.toString(), SysObjRights.class);
				sysObjRightsService.save(sysObjRights);
			}

			writeResultMessage(response.getWriter(), "权限更改成功", ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), "权限更改失败" + "," + e.getMessage(), ResultMessage.Fail);
		}

	}

	/**
	 * 取得对象权限表分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看对象权限表分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysObjRights> list = sysObjRightsService.getAll(new QueryFilter(request, "sysObjRightsItem"));
		ModelAndView mv = this.getAutoView().addObject("sysObjRightsList", list);
		return mv;
	}

	/**
	 * 删除对象权限表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除对象权限表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			sysObjRightsService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除对象权限表成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("getRightType")
	@Action(description = "获取这个类型的授权类型列表")
	@ResponseBody
	public JSONArray getRightType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String beanId = RequestUtil.getString(request, "beanId");
		if (StringUtil.isEmpty(beanId)) {
			return new JSONArray();
		}
		List<ICurUserService> list = (List<ICurUserService>) AppUtil.getBean(beanId);
		JSONArray ja = new JSONArray();
		for (ICurUserService ics : list) {
			JSONObject jo = new JSONObject();
			jo.put("key", ics.getKey());
			jo.put("value", ics.getTitle());
			ja.add(jo);
		}
		return ja;
	}

	@RequestMapping("getObject")
	@Action(description = "按各种参数查询权限")
	@ResponseBody
	public Object getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String objType = RequestUtil.getString(request, "objType");
		String objectId = RequestUtil.getString(request, "objectId");
		if (StringUtil.isNotEmpty(objectId) && StringUtil.isNotEmpty(objType)) {
			List list = sysObjRightsService.getByObjTypeAndObjectId(objType, objectId);
			return sysObjRightsService.getByObjTypeAndObjectId(objType, objectId);
		}
		return null;
	}
}
