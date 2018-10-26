package com.hotent.platform.controller.form;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.form.BpmFormDefHi;
import com.hotent.platform.service.form.BpmFormDefHiService;

/**
 * <pre>
 * 对象功能:BPM_FORM_DEF_HI 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2014-10-13 09:58:28
 * </pre>
 */
@Controller
@RequestMapping("/platform/form/bpmFormDefHi/")
public class BpmFormDefHiController extends BaseController {
	@Resource
	private BpmFormDefHiService bpmFormDefHiService;

	/**
	 * 取得分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		QueryFilter filter = new QueryFilter(request, "bpmFormDefHiItem");
		if (formDefId==0L) {
			formDefId=-1L;
		}
		filter.addFilter("formDefId", formDefId);
		List<BpmFormDefHi> list = bpmFormDefHiService.getAll(filter);
		ModelAndView mv = this.getAutoView()
				.addObject("bpmFormDefHiList", list)
				.addObject("formDefId",formDefId);

		return mv;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "hisId");
			bpmFormDefHiService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success,"删除成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 取得明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long hisId = RequestUtil.getLong(request, "hisId");
		BpmFormDefHi bpmFormDefHi = bpmFormDefHiService.getById(hisId);
		return getAutoView().addObject("bpmFormDefHi", bpmFormDefHi);
	}
	
	/**
	 * 异步返回历史记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getByAjax")
	@ResponseBody
	public BpmFormDefHi getByAjax(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long hisId = RequestUtil.getLong(request, "hisId");
		BpmFormDefHi bpmFormDefHi = bpmFormDefHiService.getById(hisId);
		return bpmFormDefHi;
	}

}

