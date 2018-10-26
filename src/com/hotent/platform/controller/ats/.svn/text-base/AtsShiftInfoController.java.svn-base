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
import com.hotent.platform.model.ats.AtsCardRule;
import com.hotent.platform.model.ats.AtsConstant;
import com.hotent.platform.model.ats.AtsShiftInfo;
import com.hotent.platform.model.ats.AtsShiftType;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.service.ats.AtsCardRuleService;
import com.hotent.platform.service.ats.AtsShiftInfoService;
import com.hotent.platform.service.ats.AtsShiftTimeService;
import com.hotent.platform.service.ats.AtsShiftTypeService;
import com.hotent.platform.service.system.SysOrgService;

/**
 * <pre>
 * 对象功能:班次设置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-18 17:21:46
 * </pre>
 */
@Controller
@RequestMapping("/platform/ats/atsShiftInfo/")
public class AtsShiftInfoController extends BaseController {
	@Resource
	private AtsShiftInfoService atsShiftInfoService;
	@Resource
	private AtsShiftTimeService atsShiftTimeService;
	@Resource
	private AtsShiftTypeService atsShiftTypeService;
	@Resource
	private AtsCardRuleService atsCardRuleService;
	@Resource
	private SysOrgService sysOrgService;

	/**
	 * 添加或更新班次设置。
	 * 
	 * @param request
	 * @param response
	 * @param atsShiftInfo
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新班次设置")
	public void save(HttpServletRequest request, HttpServletResponse response,
			AtsShiftInfo atsShiftInfo) throws Exception {
		String resultMsg = null;
		try {
			if (atsShiftInfo.getId() == null || atsShiftInfo.getId() == 0) {
				resultMsg = getText("添加", "班次设置");
			} else {
				resultMsg = getText("更新", "班次设置");
			}
			atsShiftInfoService.save(atsShiftInfo);
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得班次设置分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看班次设置分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsShiftInfo> list = atsShiftInfoService.getAll(new QueryFilter(
				request, "atsShiftInfoItem"));
		for (AtsShiftInfo atsShiftInfo : list) {
			setAtsShiftInfo(atsShiftInfo);
		}
		return this.getAutoView().addObject("atsShiftInfoList", list);
	}

	/**
	 * 删除班次设置
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除班次设置")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			atsShiftInfoService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除班次设置成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑班次设置
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑班次设置")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		AtsShiftInfo atsShiftInfo = atsShiftInfoService.getById(id);
		if (BeanUtils.isNotEmpty(atsShiftInfo))
			atsShiftInfo.setDetailList(atsShiftInfoService.getDetailList(id));
		setAtsShiftInfo(atsShiftInfo);
		List<AtsShiftType> shiftTypeList = atsShiftTypeService
				.getListByStatus(AtsConstant.YES);
		return getAutoView().addObject("atsShiftInfo", atsShiftInfo)
				.addObject("shiftTypeList", shiftTypeList)
				.addObject("returnUrl", returnUrl);
	}

	private void setAtsShiftInfo(AtsShiftInfo atsShiftInfo) {
		if (BeanUtils.isEmpty(atsShiftInfo))
			return;
		if (BeanUtils.isNotEmpty(atsShiftInfo.getShiftType())) {
			AtsShiftType atsShiftType = atsShiftTypeService
					.getById(atsShiftInfo.getShiftType());
			if (BeanUtils.isNotEmpty(atsShiftType))
				atsShiftInfo.setShiftTypeName(atsShiftType.getName());
		}
		if (BeanUtils.isNotEmpty(atsShiftInfo.getCardRule())) {
			AtsCardRule atsCardRule = atsCardRuleService.getById(atsShiftInfo
					.getCardRule());
			if (BeanUtils.isNotEmpty(atsCardRule))
				atsShiftInfo.setCardRuleName(atsCardRule.getName());
		}

		if (BeanUtils.isNotEmpty(atsShiftInfo.getOrgId())) {
			SysOrg sysOrg = sysOrgService.getById(atsShiftInfo.getOrgId());
			if (BeanUtils.isNotEmpty(sysOrg))
				atsShiftInfo.setOrgName(sysOrg.getOrgName());
		}
	}

	/**
	 * 取得班次设置明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看班次设置明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		AtsShiftInfo atsShiftInfo = atsShiftInfoService.getById(id);
		return getAutoView().addObject("atsShiftInfo", atsShiftInfo);
	}
	
	/**
	 *取得班次设置分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("replace")
	public ModelAndView replace(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		return this.getAutoView().addObject("isSingle", isSingle);
	}
	/**
	 *取得班次设置分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dialog")
	public ModelAndView dialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		return this.getAutoView().addObject("isSingle", isSingle);
	}

	/**
	 * 取得班次设置分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description = "查看班次设置分页列表")
	public ModelAndView selector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		List<AtsShiftInfo> list = atsShiftInfoService.getAll(new QueryFilter(
				request, "atsShiftInfoItem"));
		for (AtsShiftInfo atsShiftInfo : list) {
			atsShiftInfo.setShiftTime(atsShiftTimeService.getShiftTime(atsShiftInfo.getId()));
			setAtsShiftInfo(atsShiftInfo);
		}
		return this.getAutoView().addObject("atsShiftInfoList", list).addObject("isSingle", isSingle);
	}
}
