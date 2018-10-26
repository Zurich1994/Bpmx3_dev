package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysProperty;
import com.hotent.platform.service.system.SysPropertyService;

/**
 * <pre>
 * 对象功能:系统配置参数表 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2015-04-16 11:20:41
 * </pre>
 */
@Controller
@RequestMapping("/platform/system/sysProperty/")
public class SysPropertyController extends BaseController {
	@Resource
	private SysPropertyService sysPropertyService;

	/**
	 * 添加或更新系统配置参数表。
	 * 
	 * @param request
	 * @param response
	 * @param sysProperty
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新系统配置参数表")
	public void save(HttpServletRequest request, HttpServletResponse response, SysProperty sysProperty) throws Exception {
		String resultMsg = null;
		try {
			sysPropertyService.save(sysProperty);
			resultMsg = getText("更新", "系统配置参数表");
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得系统配置参数表分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看系统配置参数表分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysProperty> list = sysPropertyService.getAll(new QueryFilter(request, "sysPropertyItem"));
		ModelAndView mv = this.getAutoView().addObject("sysPropertyList", list);
		return mv;
	}

	/**
	 * 编辑系统配置参数表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑系统配置参数表")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		SysProperty sysProperty = sysPropertyService.getById(id);

		return getAutoView().addObject("sysProperty", sysProperty).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得系统配置参数表明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看系统配置参数表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		SysProperty sysProperty = sysPropertyService.getById(id);
		return getAutoView().addObject("sysProperty", sysProperty);
	}

}
