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
import com.hotent.platform.model.ats.AtsCalendarTempl;
import com.hotent.platform.model.ats.AtsConstant;
import com.hotent.platform.service.ats.AtsCalendarTemplService;

/**
 * <pre>
 * 对象功能:日历模版 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 15:44:41
 * </pre>
 */
@Controller
@RequestMapping("/platform/ats/atsCalendarTempl/")
public class AtsCalendarTemplController extends BaseController {
	@Resource
	private AtsCalendarTemplService atsCalendarTemplService;

	/**
	 * 添加或更新日历模版。
	 * 
	 * @param request
	 * @param response
	 * @param atsCalendarTempl
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新日历模版")
	public void save(HttpServletRequest request, HttpServletResponse response,
			AtsCalendarTempl atsCalendarTempl) throws Exception {
		String resultMsg = null;
		try {
			if (atsCalendarTempl.getId() == null
					|| atsCalendarTempl.getId() == 0) {
				resultMsg = getText("添加", "日历模版");
			} else {
				resultMsg = getText("更新", "日历模版");
			}
			atsCalendarTemplService.save(atsCalendarTempl);
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
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
	@RequestMapping("list")
	@Action(description = "查看日历模版分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsCalendarTempl> list = atsCalendarTemplService
				.getAll(new QueryFilter(request, "atsCalendarTemplItem"));
		return this.getAutoView().addObject("atsCalendarTemplList", list);
	}

	/**
	 * 删除日历模版
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除日历模版")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			String msg = atsCalendarTemplService.delDataByIds(lAryId);
			if(BeanUtils.isEmpty(msg))
				message = new ResultMessage(ResultMessage.Success, "删除日历模版成功!");
			else{
				message = new ResultMessage(ResultMessage.Fail, "删除失败,"+msg);
			}
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑日历模版
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑日历模版")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		AtsCalendarTempl atsCalendarTempl = atsCalendarTemplService.getById(id);
		if (BeanUtils.isNotEmpty(atsCalendarTempl))
			atsCalendarTempl.setDetailList(atsCalendarTemplService
					.getDetailList(id));
		return getAutoView().addObject("atsCalendarTempl", atsCalendarTempl)
				.addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得日历模版明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看日历模版明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		AtsCalendarTempl atsCalendarTempl = atsCalendarTemplService.getById(id);
		return getAutoView().addObject("atsCalendarTempl", atsCalendarTempl);
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
	@Action(description = "查看日历模版分页列表")
	public ModelAndView selector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsCalendarTempl> list = atsCalendarTemplService
				.getAll(new QueryFilter(request, "atsCalendarTemplItem"));
		return this.getAutoView().addObject("atsCalendarTemplList", list);
	}
	/**
	 * 取得假期类型明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("enable")
	@Action(description="查看假期类型明细")
	public ModelAndView enable(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Boolean enable=RequestUtil.getBoolean(request,"enable");
		AtsCalendarTempl atsCalendarTempl = atsCalendarTemplService.getById(id);
		atsCalendarTempl.setStatus(enable?AtsConstant.YES:AtsConstant.NO);
		atsCalendarTemplService.update(atsCalendarTempl);
		return new ModelAndView("redirect:list.ht");
	}
}
