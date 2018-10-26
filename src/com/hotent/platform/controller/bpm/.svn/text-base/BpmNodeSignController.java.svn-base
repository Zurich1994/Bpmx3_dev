package com.hotent.platform.controller.bpm;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmNodePrivilege;
import com.hotent.platform.model.bpm.BpmNodeSign;
import com.hotent.platform.service.bpm.BpmNodeSignService;

/**
 * 对象功能:会签任务投票规则 控制器类 开发公司:广州宏天软件有限公司 开发人员:ray 创建时间:2011-12-14 08:41:55
 */
@Controller
@RequestMapping("/platform/bpm/bpmNodeSign/")
public class BpmNodeSignController extends BaseController {
	@Resource
	private BpmNodeSignService bpmNodeSignService;

	/**
	 * 取得会签任务投票规则分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看会签任务投票规则分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<BpmNodeSign> list = bpmNodeSignService.getAll(new QueryFilter(
				request, "bpmNodeSignItem"));
		ModelAndView mv = this.getAutoView().addObject("bpmNodeSignList", list);
		return mv;
	}

	/**
	 * 删除会签任务投票规则
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除会签任务投票规则")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		Long[] lAryId = RequestUtil.getLongAryByStr(request, "signId");
		bpmNodeSignService.delByIds(lAryId);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description = "编辑会签任务投票规则")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		// Long signId=RequestUtil.getLong(request,"signId");
		String actDefId = RequestUtil.getString(request, "actDefId");
		String nodeId = RequestUtil.getString(request, "nodeId");

		BpmNodeSign bpmNodeSign = bpmNodeSignService.getByDefIdAndNodeId(
				actDefId, nodeId);
		if (bpmNodeSign == null) {
			bpmNodeSign = new BpmNodeSign();
			bpmNodeSign.setActDefId(actDefId);
			bpmNodeSign.setNodeId(nodeId);
		}
		// 0=拥有所有特权
		// 1=允许直接处理
		// 2=允许一票制
		// 3=允许补签
		// 这里的顺序不能乱.
		List<String> modeList = Arrays.asList("所有特权,允许直接处理,允许一票制,允许补签"
				.split(","));
		List<BpmNodePrivilege> bpmNodePrivileges = bpmNodeSignService
				.getPrivilegesByDefIdAndNodeId(actDefId, nodeId);
		BpmNodePrivilege[] bpmNodePrivilegeList = new BpmNodePrivilege[modeList.size()];
		if (bpmNodePrivileges != null) {
			for (int i = 0; i < bpmNodePrivileges.size(); i++) {
				BpmNodePrivilege vo = bpmNodePrivileges.get(i);
				if (vo.getPrivilegemode() == null) {
					continue;
				}
				bpmNodePrivilegeList[vo.getPrivilegemode().intValue()] = vo;
			}
		}

		return getAutoView().addObject("bpmNodeSign", bpmNodeSign)
				.addObject("modeList", modeList)
				.addObject("bpmNodePrivilegeList", bpmNodePrivilegeList);
	}

	/**
	 * 取得会签任务投票规则明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看会签任务投票规则明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "signId");
		BpmNodeSign bpmNodeSign = bpmNodeSignService.getById(id);
		return getAutoView().addObject("bpmNodeSign", bpmNodeSign);
	}
}
