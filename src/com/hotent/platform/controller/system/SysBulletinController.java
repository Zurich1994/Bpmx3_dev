package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysBulletin;
import com.hotent.platform.model.system.SysBulletinColumn;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysBulletinColumnService;
import com.hotent.platform.service.system.SysBulletinService;

/**
 * 对象功能:公告表 控制器类
 */
@Controller
@RequestMapping("/platform/system/sysBulletin/")
public class SysBulletinController extends BaseController {
	@Resource
	private SysBulletinService sysBulletinService;
	@Resource
	private SysBulletinColumnService sysBulletinColumnService;

	/**
	 * 添加或更新公告表。
	 * 
	 * @param request
	 * @param response
	 * @param sysBulletin
	 *            添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新公告表")
	public void save(HttpServletRequest request, HttpServletResponse response,
			SysBulletin sysBulletin) throws Exception {
		String resultMsg = null;
		try {
			if (sysBulletin.getId() == null) {
				SysUser sysUser = ContextUtil.getCurrentUser();
				sysBulletin.setCreatorid(sysUser.getUserId());
				sysBulletin.setCreator(sysUser.getFullname());
				sysBulletin.setId(UniqueIdUtil.genId());
				sysBulletinService.add(sysBulletin);
				resultMsg = getText("添加成功", "公告表");
			} else {
				sysBulletinService.update(sysBulletin);
				resultMsg = getText("更新成功", "公告表");
			}
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			e.printStackTrace();
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 列表数据
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看公告表分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long companyId = ContextUtil.getCurrentCompanyId();
		Boolean isSuperAdmin = SysUser.isSuperAdmin();
		QueryFilter filter = new QueryFilter(request, "sysBulletinItem");
		filter.addFilter("companyId", companyId);
		filter.addFilter("isSuperAdmin", isSuperAdmin);
		List<SysBulletin> list = sysBulletinService.getAll(filter);
		// 有权限的栏目
		List<SysBulletinColumn> columnList = sysBulletinColumnService
				.getColumn(companyId, isSuperAdmin);
		ModelAndView mv = this.getAutoView().addObject("sysBulletinList", list)
				.addObject("columnList", columnList);
		return mv;
	}

	/**
	 * 删除公告表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除公告表")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			sysBulletinService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除公告表成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑公告
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑公告")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		String returnUrl = RequestUtil.getPrePage(request);
		Long id = RequestUtil.getLong(request, "id");
		SysBulletin sysBulletin = sysBulletinService.getById(id);
		Long companyId = ContextUtil.getCurrentCompanyId();
		// 有权限的栏目
		List<SysBulletinColumn> columnList = sysBulletinColumnService
				.getColumn(companyId, SysUser.isSuperAdmin());
		return getAutoView().addObject("sysBulletin", sysBulletin)
				.addObject("returnUrl", returnUrl)
				.addObject("columnList", columnList);
	}

	/**
	 * 取得公告表明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看公告表明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		SysBulletin sysBulletin = sysBulletinService.getById(id);
		return getAutoView().addObject("sysBulletin", sysBulletin);
	}
	
	/**
	 * 在首页上面点击更多按钮的时候跳转到这里
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getAllByAlias")
	public ModelAndView getAllByAlias(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "sysBulletinItem");
		filter.addFilter("alias", RequestUtil.getString(request, "alias"));
		List<SysBulletin> list = sysBulletinService.getAllByAlias(new QueryFilter(request, "sysBulletinItem"));
		ModelAndView mv = this.getAutoView().addObject("sysBulletinList", list);
		return mv;
	}
	
}