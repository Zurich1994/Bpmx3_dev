package com.hotent.platform.controller.ats;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.ats.AtsCardRule;
import com.hotent.platform.service.ats.AtsCardRuleService;

/**
 * <pre>
 * 对象功能:取卡规则 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-18 16:16:16
 * </pre>
 */
@Controller
@RequestMapping("/platform/ats/atsCardRule/")
public class AtsCardRuleController extends BaseController {
	@Resource
	private AtsCardRuleService atsCardRuleService;

	/**
	 * 添加或更新取卡规则。
	 * 
	 * @param request
	 * @param response
	 * @param atsCardRule
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新取卡规则")
	public void save(HttpServletRequest request, HttpServletResponse response,
			AtsCardRule atsCardRule) throws Exception {
		String resultMsg = null;
		try {
			if (atsCardRule.getId() == null || atsCardRule.getId() == 0) {
				resultMsg = getText("添加", "取卡规则");
			} else {
				resultMsg = getText("更新", "取卡规则");
			}
			atsCardRuleService.save(atsCardRule);
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得取卡规则分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看取卡规则分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsCardRule> list = atsCardRuleService.getAll(new QueryFilter(
				request, "atsCardRuleItem"));
		return this.getAutoView().addObject("atsCardRuleList", list);
	}

	/**
	 * 删除取卡规则
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除取卡规则")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			atsCardRuleService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除取卡规则成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑取卡规则
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑取卡规则")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		AtsCardRule atsCardRule = atsCardRuleService.getById(id);
		if(atsCardRule == null){
			atsCardRule = new AtsCardRule();
			atsCardRule.setStartNum(3d);
			atsCardRule.setEndNum(3d);
			atsCardRule.setSegBefFirStartNum(3d);
			atsCardRule.setSegBefFirEndNum(4d);
			atsCardRule.setSegAftFirStartNum(4d);
			atsCardRule.setSegAftFirEndNum(3d);
			atsCardRule.setSegAftSecTakeCardType((short)2);
			atsCardRule.setSegAftFirTakeCardType((short)2);
			atsCardRule.setSegFirAssignType((short)1);
			atsCardRule.setSegmentNum((short)2);
		}
		return getAutoView().addObject("atsCardRule", atsCardRule).addObject(
				"returnUrl", returnUrl);
	}

	/**
	 * 取得取卡规则明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看取卡规则明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		AtsCardRule atsCardRule = atsCardRuleService.getById(id);
		return getAutoView().addObject("atsCardRule", atsCardRule);
	}

	/**
	 * 取得班次时间设置分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description = "查看班次时间设置分页列表")
	public ModelAndView selector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsCardRule> list = atsCardRuleService.getAll(new QueryFilter(
				request, "atsCardRuleItem"));
		return this.getAutoView().addObject("atsCardRuleList", list);
	}

}
