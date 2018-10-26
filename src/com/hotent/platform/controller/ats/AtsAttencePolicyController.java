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
import com.hotent.platform.model.ats.AtsAttencePolicy;
import com.hotent.platform.model.ats.AtsWorkCalendar;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.service.ats.AtsAttenceCycleService;
import com.hotent.platform.service.ats.AtsAttencePolicyService;
import com.hotent.platform.service.ats.AtsWorkCalendarService;
import com.hotent.platform.service.system.SysOrgService;

/**
 * <pre>
 * 对象功能:考勤制度 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 20:54:19
 * </pre>
 */
@Controller
@RequestMapping("/platform/ats/atsAttencePolicy/")
public class AtsAttencePolicyController extends BaseController {
	@Resource
	private AtsAttencePolicyService atsAttencePolicyService;
	@Resource
	private AtsAttenceCycleService atsAttenceCycleService;
	@Resource
	private AtsWorkCalendarService atsWorkCalendarService;
	@Resource
	private SysOrgService sysOrgService;

	/**
	 * 添加或更新考勤制度。
	 * 
	 * @param request
	 * @param response
	 * @param atsAttencePolicy
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新考勤制度")
	public void save(HttpServletRequest request, HttpServletResponse response,
			AtsAttencePolicy atsAttencePolicy) throws Exception {
		String resultMsg = null;
		try {
			if (atsAttencePolicy.getId() == null
					|| atsAttencePolicy.getId() == 0) {
				resultMsg = getText("添加", "考勤制度");
			} else {
				resultMsg = getText("更新", "考勤制度");
			}
			atsAttencePolicyService.save(atsAttencePolicy);
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	private void setAtsAttencePolicy(AtsAttencePolicy atsAttencePolicy) {
		if (BeanUtils.isNotEmpty(atsAttencePolicy.getAttenceCycle())) {
			AtsAttenceCycle atsAttenceCycle = atsAttenceCycleService
					.getById(atsAttencePolicy.getAttenceCycle());
			if (BeanUtils.isNotEmpty(atsAttenceCycle))
				atsAttencePolicy.setAttenceCycleName(atsAttenceCycle.getName());
		}
		if (BeanUtils.isNotEmpty(atsAttencePolicy.getWorkCalendar())) {
			AtsWorkCalendar atsWorkCalendar = atsWorkCalendarService
					.getById(atsAttencePolicy.getWorkCalendar());
			if (BeanUtils.isNotEmpty(atsWorkCalendar))
				atsAttencePolicy.setWorkCalendarName(atsWorkCalendar.getName());
		}

		if (BeanUtils.isNotEmpty(atsAttencePolicy.getOrgId())) {
			SysOrg sysOrg = sysOrgService.getById(atsAttencePolicy.getOrgId());
			if (BeanUtils.isNotEmpty(sysOrg))
				atsAttencePolicy.setOrgName(sysOrg.getOrgName());
		}
	}

	/**
	 * 取得考勤制度分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看考勤制度分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsAttencePolicy> list = atsAttencePolicyService
				.getAll(new QueryFilter(request, "atsAttencePolicyItem"));
		for (AtsAttencePolicy atsAttencePolicy : list) {
			setAtsAttencePolicy(atsAttencePolicy);
		}
		return this.getAutoView().addObject("atsAttencePolicyList", list);
	}

	/**
	 * 删除考勤制度
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除考勤制度")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			atsAttencePolicyService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除考勤制度成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑考勤制度
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑考勤制度")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		AtsAttencePolicy atsAttencePolicy = atsAttencePolicyService.getById(id);
		if (BeanUtils.isEmpty(atsAttencePolicy)) {
			atsAttencePolicy = new AtsAttencePolicy();
			atsAttencePolicy.setWeekHour(40d);
			atsAttencePolicy.setDaysHour(8d);
			atsAttencePolicy.setMonthDay(21.75d);
			atsAttencePolicy.setAbsentAllow(120);
			atsAttencePolicy.setLeaveAllow(0);
			atsAttencePolicy.setLateAllow(0);
			atsAttencePolicy.setLeaveStart(30);
			atsAttencePolicy.setOtStart(0);
		} else
			setAtsAttencePolicy(atsAttencePolicy);
		return getAutoView().addObject("atsAttencePolicy", atsAttencePolicy)
				.addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得考勤制度明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看考勤制度明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		AtsAttencePolicy atsAttencePolicy = atsAttencePolicyService.getById(id);
		return getAutoView().addObject("atsAttencePolicy", atsAttencePolicy);
	}

	/**
	 * 取得考勤制度分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dialog")
	public ModelAndView dialog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		return this.getAutoView().addObject("isSingle", isSingle);
	}

	/**
	 * 取得考勤制度分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description = "查看考勤制度分页列表")
	public ModelAndView selector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		List<AtsAttencePolicy> list = atsAttencePolicyService
				.getAll(new QueryFilter(request, "atsAttencePolicyItem"));
		for (AtsAttencePolicy atsAttencePolicy : list) {
			setAtsAttencePolicy(atsAttencePolicy);
		}
		return this.getAutoView().addObject("atsAttencePolicyList", list)
				.addObject("isSingle", isSingle);
	}
}
