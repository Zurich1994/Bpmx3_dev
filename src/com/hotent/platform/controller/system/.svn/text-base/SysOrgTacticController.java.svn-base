package com.hotent.platform.controller.system;

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
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysOrgTactic;
import com.hotent.platform.model.system.SysOrgType;
import com.hotent.platform.service.system.SysOrgTacticService;
import com.hotent.platform.service.system.SysOrgTypeService;

/**
 * <pre>
 * 对象功能:组织策略 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-31 13:43:14
 * </pre>
 */
@Controller
@RequestMapping("/platform/system/sysOrgTactic/")
public class SysOrgTacticController extends BaseController {
	@Resource
	private SysOrgTacticService sysOrgTacticService;
	@Resource
	private SysOrgTypeService sysOrgTypeService;
	/**
	 * 添加或更新组织策略。
	 * 
	 * @param request
	 * @param response
	 * @param sysOrgTactic
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新组织策略")
	public void save(HttpServletRequest request, HttpServletResponse response,
			SysOrgTactic sysOrgTactic) throws Exception {
		String resultMsg=null;		
		try{
			SysOrgTactic orgTactic=sysOrgTacticService.getById(SysOrgTactic.DEFAULT_ID);
			if(BeanUtils.isEmpty(orgTactic)){
				sysOrgTacticService.add(sysOrgTactic);
			}else{
			    sysOrgTacticService.update(sysOrgTactic);
			}
			resultMsg=getText("保存组织策略成功");
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}

	/**
	 * 取得组织策略分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看组织策略分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<SysOrgTactic> list = sysOrgTacticService.getAll(new QueryFilter(
				request, "sysOrgTacticItem"));
		ModelAndView mv = this.getAutoView()
				.addObject("sysOrgTacticList", list);

		return mv;
	}

	/**
	 * 删除组织策略
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除组织策略")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			sysOrgTacticService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除组织策略成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑组织策略
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑组织策略")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		// 写死维度1L
		List<SysOrgType> orgTypelist = sysOrgTypeService.getByDemId(1L);
		SysOrgTactic sysOrgTactic = sysOrgTacticService
				.getById(SysOrgTactic.DEFAULT_ID);
		if (BeanUtils.isEmpty(sysOrgTactic)) {
			sysOrgTactic = new SysOrgTactic();
			sysOrgTactic.setId(SysOrgTactic.DEFAULT_ID);
			sysOrgTactic.setOrgTactic(SysOrgTactic.ORG_TACTIC_WITHOUT);
		}

		return getAutoView().addObject("sysOrgTactic", sysOrgTactic).addObject(
				"orgTypelist", orgTypelist);
	}

	/**
	 * 取得组织策略明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看组织策略明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		SysOrgTactic sysOrgTactic = sysOrgTacticService.getById(id);
		return getAutoView().addObject("sysOrgTactic", sysOrgTactic);
	}
	/**
	 * 取得组织策略明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description = "查看组织策略明细")
	public ModelAndView selector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String orgName = RequestUtil.getString(request, "orgName");
		List<SysOrg> sysOrgList = sysOrgTacticService.getSysOrgListByOrgName(orgName);
		return getAutoView().addObject("sysOrgList", sysOrgList).addObject("isSingle",true);
	}
	
}
