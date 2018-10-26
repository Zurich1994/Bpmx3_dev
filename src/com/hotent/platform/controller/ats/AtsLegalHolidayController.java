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
import com.hotent.platform.model.ats.AtsLegalHoliday;
import com.hotent.platform.service.ats.AtsLegalHolidayService;

/**
 * <pre>
 * 对象功能:法定节假日 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 15:48:22
 * </pre>
 */
@Controller
@RequestMapping("/platform/ats/atsLegalHoliday/")
public class AtsLegalHolidayController extends BaseController {
	@Resource
	private AtsLegalHolidayService atsLegalHolidayService;

	/**
	 * 添加或更新法定节假日。
	 * 
	 * @param request
	 * @param response
	 * @param atsLegalHoliday
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新法定节假日")
	public void save(HttpServletRequest request, HttpServletResponse response,
			AtsLegalHoliday atsLegalHoliday) throws Exception {
		String resultMsg = null;
		try {
			if (atsLegalHoliday.getId() == null || atsLegalHoliday.getId() == 0) {
				resultMsg = getText("添加", "法定节假日");
			} else {
				resultMsg = getText("更新", "法定节假日");
			}
			atsLegalHolidayService.save(atsLegalHoliday);
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得法定节假日分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看法定节假日分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsLegalHoliday> list = atsLegalHolidayService
				.getAll(new QueryFilter(request, "atsLegalHolidayItem"));
		return this.getAutoView().addObject("atsLegalHolidayList", list);
	}

	/**
	 * 删除法定节假日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除法定节假日")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			atsLegalHolidayService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除法定节假日成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑法定节假日
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑法定节假日")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		AtsLegalHoliday atsLegalHoliday = atsLegalHolidayService.getById(id);
		if (BeanUtils.isNotEmpty(atsLegalHoliday))
			atsLegalHoliday.setDetailList(atsLegalHolidayService
					.getDetailList(id));

		return getAutoView().addObject("atsLegalHoliday", atsLegalHoliday)
				.addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得法定节假日明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看法定节假日明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		AtsLegalHoliday atsLegalHoliday = atsLegalHolidayService.getById(id);
		return getAutoView().addObject("atsLegalHoliday", atsLegalHoliday);
	}

	/**
	 * 取得日历模版分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description = "查看法定节假日分页列表")
	public ModelAndView selector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsLegalHoliday> list = atsLegalHolidayService
				.getAll(new QueryFilter(request, "atsLegalHolidayItem"));
		return this.getAutoView().addObject("atsLegalHolidayList", list);
	}
}
