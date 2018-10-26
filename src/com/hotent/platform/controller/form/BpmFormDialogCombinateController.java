package com.hotent.platform.controller.form;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.jsonobject.JSONObjectUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.form.BpmFormDialog;
import com.hotent.platform.model.form.BpmFormDialogCombinate;
import com.hotent.platform.service.form.BpmFormDialogCombinateService;
import com.hotent.platform.service.form.BpmFormDialogService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * <pre>
 * 对象功能:组合对话框 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-05-06 11:36:18
 * </pre>
 */
@Controller
@RequestMapping("/platform/form/bpmFormDialogCombinate/")
public class BpmFormDialogCombinateController extends BaseController {
	@Resource
	private BpmFormDialogCombinateService bpmFormDialogCombinateService;
	@Resource
	private BpmFormDialogService bpmFormDialogService;

	/**
	 * 添加或更新组合对话框。
	 * 
	 * @param request
	 * @param response
	 * @param bpmFormDialogCombinate
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新组合对话框")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = FileUtil.inputStream2String(request.getInputStream());
		BpmFormDialogCombinate bpmFormDialogCombinate = JSONObjectUtil.toBean(json, BpmFormDialogCombinate.class);
		String resultMsg = null;
		try {
			if (bpmFormDialogCombinate.getId() == null || bpmFormDialogCombinate.getId() == 0) {
				resultMsg = getText("添加成功", "组合对话框");
			} else {
				resultMsg = getText("更新成功", "组合对话框");
			}
			bpmFormDialogCombinateService.save(bpmFormDialogCombinate);
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
		} catch (Exception e) {
			if(e instanceof DuplicateKeyException){
				resultMsg="别名已被使用："+bpmFormDialogCombinate.getAlias();
			}else{
				resultMsg+= "," + e.getMessage();
			}
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Fail);
		}
	}

	/**
	 * 取得组合对话框分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看组合对话框分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<BpmFormDialogCombinate> list = bpmFormDialogCombinateService.getAll(new QueryFilter(request, "bpmFormDialogCombinateItem"));
		ModelAndView mv = this.getAutoView().addObject("bpmFormDialogCombinateList", list);
		return mv;
	}

	/**
	 * 删除组合对话框
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除组合对话框")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			bpmFormDialogCombinateService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除组合对话框成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("getObject")
	@Action(description = "按各种参数查询对象")
	@ResponseBody
	public Object getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id", null);
		// 根据ID获取组合对话框
		if (id != null) {
			return bpmFormDialogCombinateService.getById(id);
		}
		// 根据对话框名称获取对话框，注意这里是对话框不是组合对话框
		Long dialogId = RequestUtil.getLong(request, "dialogId", null);
		if (dialogId != null) {
			return bpmFormDialogService.getById(dialogId);
		}
		// 根据别名获取组合对话框
		String alias = RequestUtil.getString(request, "alias", "");
		if (StringUtil.isNotEmpty(alias)) {
			return bpmFormDialogCombinateService.getByAlias(alias);
		}

		return null;
	}
	
	
	/**
	 * 取得组合对话框分页列表,并添加上被组合的列
	 */
	@ResponseBody
	@RequestMapping("getAllCombDialog")
	public List<BpmFormDialogCombinate> getAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<BpmFormDialogCombinate> list = bpmFormDialogCombinateService.getAll();
		return list;
	}

}
