package com.hotent.platform.controller.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysBulletinColumn;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysBulletinColumnService;
import com.hotent.platform.service.system.SysBulletinService;
import com.hotent.platform.service.system.SysOrgService;

/**
 * 对象功能:公告栏目 控制器类
 */
@Controller
@RequestMapping("/platform/system/sysBulletinColumn/")
public class SysBulletinColumnController extends BaseController {
	@Resource
	private SysBulletinColumnService sysBulletinColumnService;
	@Resource
	private SysBulletinService sysBulletinService;
	@Resource
	private SysOrgService sysOrgService;
	
	/**
	 * 添加或更新公告栏目。
	 * 
	 * @param request
	 * @param response
	 * @param sysBulletinColumn
	 *            添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新公告栏目")
	public void save(HttpServletRequest request, HttpServletResponse response,
			SysBulletinColumn sysBulletinColumn) throws Exception {
		String resultMsg = null;
		try {
			if (sysBulletinColumn.getId() == null) {
				sysBulletinColumn = InitData(sysBulletinColumn);
				sysBulletinColumnService.add(sysBulletinColumn);
				resultMsg = getText("添加成功", "公告栏目");
			}else{
				sysBulletinColumnService.update(sysBulletinColumn);
				resultMsg = getText("更新成功", "公告栏目");
			}
			writeResultMessage(response.getWriter(),resultMsg + "", ResultMessage.Success);
		}catch(DuplicateKeyException ex){
			writeResultMessage(response.getWriter(),"该栏目别名已存在.",ResultMessage.Fail);
		} 
		catch (Exception e) {
			e.printStackTrace();
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 设置保存的数据
	 * @param request
	 * @param sysBulletinColumn
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private SysBulletinColumn InitData(SysBulletinColumn sysBulletinColumn) throws Exception {
		SysUser sysUser = ContextUtil.getCurrentUser();
		sysBulletinColumn.setId(UniqueIdUtil.genId());
		sysBulletinColumn.setCreatetime(new Date());
		sysBulletinColumn.setCreator(sysUser.getFullname());
		sysBulletinColumn.setCreatorid(sysUser.getUserId());
		return sysBulletinColumn;
	}

	/**
	 * 取得公告栏目分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看公告栏目分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long companyId = ContextUtil.getCurrentCompanyId();
		QueryFilter filter = new QueryFilter(request, "sysBulletinColumnList");
		filter.addFilter("companyId", companyId);
		filter.addFilter("isSuperAdmin",SysUser.isSuperAdmin());
		List<SysBulletinColumn> list = sysBulletinColumnService.getAll(filter);
		return this.getAutoView()
					.addObject("sysBulletinColumnList",list)
					.addObject("isSuperAdmin", SysUser.isSuperAdmin());
	}
	
	/**
	 * 删除公告栏目
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除公告栏目")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除公告栏目成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 删除栏目，和选中栏目下的公告
	 * @param lAryId
	 */
	private void delByIds(Long[] lAryId) {
		if(BeanUtils.isEmpty(lAryId)) return;
		for(Long id:lAryId){
			sysBulletinColumnService.delById(id);
			sysBulletinService.delByColumnId(id);
		}
	}

	/**
	 * 编辑公告栏目
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑公告栏目")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		String returnUrl = RequestUtil.getPrePage(request);
		SysBulletinColumn sysBulletinColumn = sysBulletinColumnService.getById(id);
		List<Map<String,Object>> companyList = sysOrgService.getCompany();
		return getAutoView().addObject("sysBulletinColumn", sysBulletinColumn)
				.addObject("companyList", companyList)
				.addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得公告栏目明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看公告栏目明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		SysBulletinColumn sysBulletinColumn = sysBulletinColumnService
				.getById(id);
		return getAutoView().addObject("sysBulletinColumn", sysBulletinColumn);
	}
}