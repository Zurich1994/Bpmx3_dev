/**update 2013-1-1**/
package com.hotent.platform.controller.form;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.form.BpmPrintTemplate;
import com.hotent.platform.service.form.BpmPrintTemplateService;

/**
 *<pre>
 * 对象功能:打印模板 控制器类 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:ray 
 * 创建时间:2012-12-31 10:01:30
 * </pre>
 */
@Controller
@RequestMapping("/platform/form/bpmPrintTemplate/")
public class BpmPrintTemplateController extends BaseController

{
	@Resource
	private BpmPrintTemplateService bpmPrintTemplateService;

	/**
	 * 取得打印模版分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看打印模版分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	//	Long formKey = RequestUtil.getLong(request, "formKey", 0L);
		String formKey = RequestUtil.getString(request, "formKey");
		QueryFilter filter = new QueryFilter(request, "bpmPrintTemplateItem");
		filter.getFilters().put("formKey", formKey);

		List<BpmPrintTemplate> list = bpmPrintTemplateService.getAll(filter);
		ModelAndView mv = this.getAutoView()
				.addObject("bpmPrintTemplateList", list)
				.addObject("formKey", formKey);
		return mv;
	}

	/**
	 * 删除打印模版
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除打印模版")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");

			bpmPrintTemplateService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, ContextUtil.getMessages("打印模版删除成功"));
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, ContextUtil.getMessages("打印模版删除失败")
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("setDefault")
	@Action(description = "编辑打印模版")
	public void setDefault(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		Long id = RequestUtil.getLong(request, "id");
		Long formKey = RequestUtil.getLong(request, "formKey");
		ResultMessage message = null;
		try {
			bpmPrintTemplateService.setDefault(id, formKey);
			message = new ResultMessage(ResultMessage.Success, ContextUtil.getMessages("设置成功"));
		} catch (Exception e) {
			message = new ResultMessage(ResultMessage.Fail, ContextUtil.getMessages("设置失败")
					+ e.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑打印模版
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑打印模版")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
	//	Long formKey = RequestUtil.getLong(request, "formKey");
		String formKey = RequestUtil.getString(request, "formKey");
		String returnUrl = RequestUtil.getPrePage(request);

		BpmPrintTemplate bpmPrintTemplate = null;
		if (id == 0L) {
			bpmPrintTemplate = bpmPrintTemplateService
					.getDefaultPrintTemplateByFormKey(formKey);
		} else {
			bpmPrintTemplate = bpmPrintTemplateService.getById(id);
		}
		return getAutoView().addObject("bpmPrintTemplate", bpmPrintTemplate)
				.addObject("returnUrl", returnUrl)
				.addObject("formKey", formKey)
				  .addObject("locale", ContextUtil.getLocale().toString());
	}

	/**
	 * 预览打印模版
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "预览打印模版")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");

		BpmPrintTemplate bpmPrintTemplate = bpmPrintTemplateService.getById(id);
		String html = bpmPrintTemplateService.obtainHtml(bpmPrintTemplate);
		return getAutoView().addObject("html", html);
	}
}
