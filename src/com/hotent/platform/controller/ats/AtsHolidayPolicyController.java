package com.hotent.platform.controller.ats;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fr.base.core.json.JSONObject;
import com.hotent.core.annotion.Action;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.ats.AtsConstant;
import com.hotent.platform.model.ats.AtsHolidayPolicy;
import com.hotent.platform.model.ats.AtsHolidayPolicyDetail;
import com.hotent.platform.model.ats.AtsHolidayType;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.service.ats.AtsHolidayPolicyService;
import com.hotent.platform.service.ats.AtsHolidayTypeService;
import com.hotent.platform.service.system.SysOrgService;

/**
 * <pre>
 * 对象功能:假期制度 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-19 17:42:20
 * </pre>
 */
@Controller
@RequestMapping("/platform/ats/atsHolidayPolicy/")
public class AtsHolidayPolicyController extends BaseController {
	@Resource
	private AtsHolidayPolicyService atsHolidayPolicyService;

	@Resource
	private SysOrgService sysOrgService;

	@Resource
	private AtsHolidayTypeService atsHolidayTypeService;

	/**
	 * 添加或更新假期制度。
	 * 
	 * @param request
	 * @param response
	 * @param atsHolidayPolicy
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新假期制度")
	public void save(HttpServletRequest request, HttpServletResponse response,
			AtsHolidayPolicy atsHolidayPolicy) throws Exception {
		String resultMsg = null;
		try {
			if (atsHolidayPolicy.getId() == null
					|| atsHolidayPolicy.getId() == 0) {
				resultMsg = getText("添加", "假期制度");
			} else {
				resultMsg = getText("更新", "假期制度");
			}
			atsHolidayPolicyService.save(atsHolidayPolicy);
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得假期制度分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看假期制度分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsHolidayPolicy> list = atsHolidayPolicyService
				.getAll(new QueryFilter(request, "atsHolidayPolicyItem"));
		for (AtsHolidayPolicy atsHolidayPolicy : list) {
			if (BeanUtils.isNotEmpty(atsHolidayPolicy.getOrgId())) {
				SysOrg sysOrg = sysOrgService.getById(atsHolidayPolicy
						.getOrgId());
				if (BeanUtils.isNotEmpty(sysOrg))
					atsHolidayPolicy.setOrgName(sysOrg.getOrgName());
			}
		}
		return this.getAutoView().addObject("atsHolidayPolicyList", list);
	}

	/**
	 * 删除假期制度
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除假期制度")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			atsHolidayPolicyService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除假期制度成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑假期制度
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑假期制度")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		AtsHolidayPolicy atsHolidayPolicy = atsHolidayPolicyService.getById(id);
		if (atsHolidayPolicy == null) {
			atsHolidayPolicy = new AtsHolidayPolicy();
		}
		List<AtsHolidayType> list = atsHolidayTypeService.getAll();
		JSONObject jo = new JSONObject();
		if (BeanUtils.isEmpty(atsHolidayPolicy.getAtsHolidayPolicyDetailList())) {
			List<AtsHolidayPolicyDetail> atsHolidayPolicyDetailList = new ArrayList<AtsHolidayPolicyDetail>();
			for (AtsHolidayType at : list) {
				AtsHolidayPolicyDetail atsHolidayPolicyDetail = new AtsHolidayPolicyDetail();
				atsHolidayPolicyDetail.setHolidayType(at.getId());
				if (at.getIsSys() == AtsConstant.YES) {
					jo.accumulate(at.getId().toString(), at.getName());
				}
				atsHolidayPolicyDetailList.add(atsHolidayPolicyDetail);
			}
			atsHolidayPolicy
					.setAtsHolidayPolicyDetailList(atsHolidayPolicyDetailList);
		} else {
			for (AtsHolidayType at : list) {
				if (at.getIsSys() == AtsConstant.YES) {
					jo.accumulate(at.getId().toString(), at.getName());
				}
			}
		}

		if (atsHolidayPolicy != null
				&& BeanUtils.isNotEmpty(atsHolidayPolicy.getOrgId())) {
			SysOrg sysOrg = sysOrgService.getById(atsHolidayPolicy.getOrgId());
			if (BeanUtils.isNotEmpty(sysOrg))
				atsHolidayPolicy.setOrgName(sysOrg.getOrgName());
		}
		return getAutoView().addObject("atsHolidayPolicy", atsHolidayPolicy)
				.addObject("returnUrl", returnUrl)
				.addObject("atsHolidayTypeMap", jo);
	}

	/**
	 * 取得假期制度明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看假期制度明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		AtsHolidayPolicy atsHolidayPolicy = atsHolidayPolicyService.getById(id);
		return getAutoView().addObject("atsHolidayPolicy", atsHolidayPolicy);
	}

	/**
	 * 取得假期制度分页列表
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
	 * 取得假期制度分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description = "查看假期制度分页列表")
	public ModelAndView selector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		List<AtsHolidayPolicy> list = atsHolidayPolicyService
				.getAll(new QueryFilter(request, "atsHolidayPolicyItem"));
		return this.getAutoView().addObject("atsHolidayPolicyList", list)
				.addObject("isSingle", isSingle);
	}
}
