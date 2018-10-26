package com.hotent.platform.controller.ats;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.ats.AtsShiftRule;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.service.ats.AtsShiftRuleDetailService;
import com.hotent.platform.service.ats.AtsShiftRuleService;
import com.hotent.platform.service.system.SysOrgService;

/**
 * <pre>
 * 对象功能:轮班规则 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-21 09:06:10
 * </pre>
 */
@Controller
@RequestMapping("/platform/ats/atsShiftRule/")
public class AtsShiftRuleController extends BaseController {
	@Resource
	private AtsShiftRuleService atsShiftRuleService;

	@Resource
	private AtsShiftRuleDetailService atsShiftRuleDetailService;
	@Resource
	private SysOrgService sysOrgService;
	/**
	 * 添加或更新轮班规则。
	 * 
	 * @param request
	 * @param response
	 * @param atsShiftRule
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新轮班规则")
	public void save(HttpServletRequest request, HttpServletResponse response,
			AtsShiftRule atsShiftRule) throws Exception {
		String resultMsg = null;
		try {
			if (atsShiftRule.getId() == null || atsShiftRule.getId() == 0) {
				resultMsg = getText("添加", "轮班规则");
			} else {
				resultMsg = getText("更新", "轮班规则");
			}
			atsShiftRuleService.save(atsShiftRule);
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得轮班规则分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看轮班规则分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsShiftRule> list = atsShiftRuleService.getAll(new QueryFilter(
				request, "atsShiftRuleItem"));
		for (AtsShiftRule atsShiftRule : list) {
			setAtsShiftInfo(atsShiftRule);
		}
		return this.getAutoView().addObject("atsShiftRuleList", list);
	}

	/**
	 * 删除轮班规则
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除轮班规则")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			atsShiftRuleService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除轮班规则成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑轮班规则
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑轮班规则")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		AtsShiftRule atsShiftRule = atsShiftRuleService.getById(id);

		if (BeanUtils.isNotEmpty(atsShiftRule))
			atsShiftRule.setDetailList(atsShiftRuleService.getDetailList(id));
		setAtsShiftInfo(atsShiftRule);
		return getAutoView().addObject("atsShiftRule", atsShiftRule).addObject(
				"returnUrl", returnUrl);
	}
	
	private void setAtsShiftInfo(AtsShiftRule atsShiftRule) {
		if (BeanUtils.isEmpty(atsShiftRule))
			return;


		if (BeanUtils.isNotEmpty(atsShiftRule.getOrgId())) {
			SysOrg sysOrg = sysOrgService.getById(atsShiftRule.getOrgId());
			if (BeanUtils.isNotEmpty(sysOrg))
				atsShiftRule.setOrgName(sysOrg.getOrgName());
		}
	}

	/**
	 * 取得轮班规则明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看轮班规则明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		AtsShiftRule atsShiftRule = atsShiftRuleService.getById(id);
		return getAutoView().addObject("atsShiftRule", atsShiftRule);
	}

	/**
	 * 取得轮班规则分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description = "查看轮班规则分页列表")
	public ModelAndView selector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsShiftRule> list = atsShiftRuleService.getAll(new QueryFilter(
				request, "atsShiftRuleItem"));
		return this.getAutoView().addObject("atsShiftRuleList", list);
	}

	@RequestMapping("detail")
	@ResponseBody
	public String detail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Long id = RequestUtil.getLong(request, "id");

		return atsShiftRuleService.getDetailList(id);
	}
}
