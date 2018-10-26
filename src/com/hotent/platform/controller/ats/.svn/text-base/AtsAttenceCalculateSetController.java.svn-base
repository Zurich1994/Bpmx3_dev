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
import com.hotent.platform.model.ats.AtsAttenceCalculateSet;
import com.hotent.platform.service.ats.AtsAttenceCalculateSetService;

/**
 * <pre>
 * 对象功能:考勤计算设置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-06-03 14:46:19
 * </pre>
 */
@Controller
@RequestMapping("/platform/ats/atsAttenceCalculateSet/")
public class AtsAttenceCalculateSetController extends BaseController {
	@Resource
	private AtsAttenceCalculateSetService atsAttenceCalculateSetService;

	/**
	 * 添加或更新考勤计算设置。
	 * 
	 * @param request
	 * @param response
	 * @param atsAttenceCalculateSet
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新考勤计算设置")
	public void save(HttpServletRequest request, HttpServletResponse response,
			AtsAttenceCalculateSet atsAttenceCalculateSet) throws Exception {
		String resultMsg = "保存成功！";
		try {
			atsAttenceCalculateSetService.save(atsAttenceCalculateSet);
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得考勤计算设置分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看考勤计算设置分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsAttenceCalculateSet> list = atsAttenceCalculateSetService
				.getAll(new QueryFilter(request, "atsAttenceCalculateSetItem"));
		return this.getAutoView().addObject("atsAttenceCalculateSetList", list);
	}

	/**
	 * 删除考勤计算设置
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除考勤计算设置")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			atsAttenceCalculateSetService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除考勤计算设置成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑考勤计算设置
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑考勤计算设置")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Short type = RequestUtil.getShort(request, "type");
		AtsAttenceCalculateSet atsAttenceCalculateSet = atsAttenceCalculateSetService
				.getById(1L);
		if (BeanUtils.isNotEmpty(atsAttenceCalculateSet)) {
			if (type == 1) {
				atsAttenceCalculateSet.setDetail(atsAttenceCalculateSet
						.getSummary());
			} else {
				atsAttenceCalculateSet.setDetail(atsAttenceCalculateSet
						.getDetail());
			}
		}
		return getAutoView().addObject("atsAttenceCalculateSet",
				atsAttenceCalculateSet).addObject("type", type);
	}

	/**
	 * 取得考勤计算设置明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看考勤计算设置明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		AtsAttenceCalculateSet atsAttenceCalculateSet = atsAttenceCalculateSetService
				.getById(id);
		return getAutoView().addObject("atsAttenceCalculateSet",
				atsAttenceCalculateSet);
	}

}
