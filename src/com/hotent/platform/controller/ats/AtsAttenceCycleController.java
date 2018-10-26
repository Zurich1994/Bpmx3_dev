package com.hotent.platform.controller.ats;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.ats.AtsAttenceCycle;
import com.hotent.platform.service.ats.AtsAttenceCycleService;

/**
 * <pre>
 * 对象功能:考勤周期 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 22:03:30
 * </pre>
 */
@Controller
@RequestMapping("/platform/ats/atsAttenceCycle/")
public class AtsAttenceCycleController extends BaseController {
	@Resource
	private AtsAttenceCycleService atsAttenceCycleService;

	/**
	 * 添加或更新考勤周期。
	 * 
	 * @param request
	 * @param response
	 * @param atsAttenceCycle
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新考勤周期")
	public void save(HttpServletRequest request, HttpServletResponse response,
			AtsAttenceCycle atsAttenceCycle) throws Exception {
		String resultMsg = null;
		try {
			if (atsAttenceCycle.getId() == null || atsAttenceCycle.getId() == 0) {
				resultMsg = getText("添加", "考勤周期");
			} else {
				resultMsg = getText("更新", "考勤周期");
			}
			atsAttenceCycleService.save(atsAttenceCycle);
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得考勤周期分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看考勤周期分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsAttenceCycle> list = atsAttenceCycleService
				.getAll(new QueryFilter(request, "atsAttenceCycleItem"));
		return this.getAutoView().addObject("atsAttenceCycleList", list);
	}

	/**
	 * 删除考勤周期
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除考勤周期")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			atsAttenceCycleService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除考勤周期成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑考勤周期
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑考勤周期")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		AtsAttenceCycle atsAttenceCycle = atsAttenceCycleService.getById(id);
		if (BeanUtils.isNotEmpty(atsAttenceCycle))
			atsAttenceCycle.setDetailList(atsAttenceCycleService
					.getDetailList(id));

		return getAutoView().addObject("atsAttenceCycle", atsAttenceCycle)
				.addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得考勤周期明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看考勤周期明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		AtsAttenceCycle atsAttenceCycle = atsAttenceCycleService.getById(id);
		return getAutoView().addObject("atsAttenceCycle", atsAttenceCycle);
	}

	/**
	 * 取得考勤周期分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description = "查看考勤周期分页列表")
	public ModelAndView selector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsAttenceCycle> list = atsAttenceCycleService
				.getAll(new QueryFilter(request, "atsAttenceCycleItem"));
		return this.getAutoView().addObject("atsAttenceCycleList", list);
	}

}
