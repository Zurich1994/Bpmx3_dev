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
import com.hotent.platform.model.ats.AtsAttenceGroup;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.service.ats.AtsAttenceGroupService;
import com.hotent.platform.service.system.SysOrgService;

/**
 * <pre>
 * 对象功能:考勤组 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-26 10:07:27
 * </pre>
 */
@Controller
@RequestMapping("/platform/ats/atsAttenceGroup/")
public class AtsAttenceGroupController extends BaseController {
	@Resource
	private AtsAttenceGroupService atsAttenceGroupService;
	@Resource
	private SysOrgService sysOrgService;

	/**
	 * 添加或更新考勤组。
	 * 
	 * @param request
	 * @param response
	 * @param atsAttenceGroup
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新考勤组")
	public void save(HttpServletRequest request, HttpServletResponse response,
			AtsAttenceGroup atsAttenceGroup) throws Exception {
		String resultMsg = null;
		try {
			if (atsAttenceGroup.getId() == null || atsAttenceGroup.getId() == 0) {
				resultMsg = getText("添加", "考勤组");
			} else {
				resultMsg = getText("更新", "考勤组");
			}
			atsAttenceGroupService.save(atsAttenceGroup);
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得考勤组分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看考勤组分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsAttenceGroup> list = atsAttenceGroupService
				.getAll(new QueryFilter(request, "atsAttenceGroupItem"));
		for (AtsAttenceGroup atsAttenceGroup : list) {
			setAtsAttenceGroup(atsAttenceGroup);
		}
		return this.getAutoView().addObject("atsAttenceGroupList", list);
	}

	/**
	 * 删除考勤组
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除考勤组")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			atsAttenceGroupService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除考勤组成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑考勤组
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑考勤组")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		AtsAttenceGroup atsAttenceGroup = atsAttenceGroupService.getById(id);
		if (BeanUtils.isNotEmpty(atsAttenceGroup))
			atsAttenceGroup.setDetailList(atsAttenceGroupService
					.getDetailList(id));
		setAtsAttenceGroup(atsAttenceGroup);
		return getAutoView().addObject("atsAttenceGroup", atsAttenceGroup)
				.addObject("returnUrl", returnUrl);
	}

	private void setAtsAttenceGroup(AtsAttenceGroup atsAttenceGroup) {
		if (BeanUtils.isEmpty(atsAttenceGroup))
			return;
		if (BeanUtils.isNotEmpty(atsAttenceGroup.getOrgId())) {
			SysOrg sysOrg = sysOrgService.getById(atsAttenceGroup.getOrgId());
			if (BeanUtils.isNotEmpty(sysOrg))
				atsAttenceGroup.setOrgName(sysOrg.getOrgName());
		}
	}

	/**
	 * 取得考勤组明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看考勤组明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		AtsAttenceGroup atsAttenceGroup = atsAttenceGroupService.getById(id);
		return getAutoView().addObject("atsAttenceGroup", atsAttenceGroup);
	}

	/**
	 * 取得考勤组分页列表
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
	 * 取得考勤组分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description = "查看考勤组分页列表")
	public ModelAndView selector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		List<AtsAttenceGroup> list = atsAttenceGroupService
				.getAll(new QueryFilter(request, "atsAttenceGroupItem"));
		for (AtsAttenceGroup atsAttenceGroup : list) {
			setAtsAttenceGroup(atsAttenceGroup);
		}
		return this.getAutoView().addObject("atsAttenceGroupList", list)
				.addObject("isSingle", isSingle);
	}
}
