package com.hotent.mobile.controller.form;

import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.mobile.model.form.BpmMobileForm;
import com.hotent.mobile.service.form.BpmMobileFormService;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.service.form.BpmFormTableService;

/**
 * <pre>
 * 对象功能:手机表单 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2014-04-02 15:09:58
 * </pre>
 */
@Controller
@RequestMapping("/mobile/form/bpmMobileForm/")
public class BpmMobileFormController extends BaseController {
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private BpmMobileFormService bpmMobileFormService;

	/**
	 * 添加或更新手机表单。
	 * 
	 * @param request
	 * @param response
	 * @param bpmMobileForm
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新手机表单")
	public void save(HttpServletRequest request, HttpServletResponse response,
			BpmMobileForm bpmMobileForm) throws Exception {
		String resultMsg = null;
		try {
			bpmMobileFormService.save(bpmMobileForm);

			resultMsg = getText("保存手机表单设置成功");
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	@RequestMapping("preview")
	@Action(description = "预览手机表单")
	public ModelAndView preview(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		String formKey = RequestUtil.getString(request, "formKey");
		Long formId = RequestUtil.getLong(request, "formId", 0L);
		String formJson = RequestUtil.getString(request, "formJson");
		BpmMobileForm	bpmMobileForm =  new BpmMobileForm();
		bpmMobileForm.setFormId(formId);
		bpmMobileForm.setFormKey(formKey);
		bpmMobileForm.setFormJson(formJson);
		String html = bpmMobileFormService.preview(bpmMobileForm);

		return this.getAutoView().addObject("html", html);
	}

	/**
	 * 取得手机表单分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看手机表单分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<BpmMobileForm> list = bpmMobileFormService.getAll(new QueryFilter(
				request, "bpmMobileFormItem"));

		return this.getAutoView().addObject("bpmMobileFormList", list);
	}

	/**
	 * 删除手机表单
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除手机表单")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			bpmMobileFormService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除手机表单成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑手机表单
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑手机表单")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		String formKey = RequestUtil.getString(request, "formKey");
		Long formDefId = RequestUtil.getLong(request, "formDefId", 0L);
		Long tableId = RequestUtil.getLong(request, "tableId");
		Integer isDefault = RequestUtil.getInt(request, "isDefault",0);

		BpmFormTable bpmFormTable = bpmFormTableService.getTableById(tableId);

		BpmMobileForm bpmMobileForm = bpmMobileFormService.getByMap(formKey,
				formDefId, isDefault, bpmFormTable);
		return getAutoView().addObject("bpmFormTable", bpmFormTable).addObject(
				"bpmMobileForm", bpmMobileForm);
	}

	/**
	 * 取得手机表单明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看手机表单明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		BpmMobileForm bpmMobileForm = bpmMobileFormService.getById(id);
		return getAutoView().addObject("bpmMobileForm", bpmMobileForm);
	}

	/**
	 * 生成默认的表单模板
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("genDefaultForm")
	@Action(description = "生成默认的表单模板")
	public void genDefaultForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();		
		try {
			bpmMobileFormService.genDefaultForm();
			writeResultMessage(writer, new ResultMessage(ResultMessage.Success,
					  getText("controller.save.success")));
		} catch (Exception ex) {
			String msg = ExceptionUtil.getExceptionMessage(ex);
			writeResultMessage(writer, new ResultMessage(ResultMessage.Fail,
					msg));
		}
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(UUID.randomUUID());
		}
	}
}
