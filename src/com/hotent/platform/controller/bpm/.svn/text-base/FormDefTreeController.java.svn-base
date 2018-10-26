package com.hotent.platform.controller.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.hotent.platform.model.bpm.FormDefTree;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.service.bpm.FormDefTreeService;
import com.hotent.platform.service.form.BpmDataTemplateService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.form.BpmFormTableService;

/**
 * <pre>
 * 对象功能:form_def_tree 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-05-12 14:46:20
 * </pre>
 */
@Controller
@RequestMapping("/platform/bpm/formDefTree/")
public class FormDefTreeController extends BaseController {
	@Resource
	private FormDefTreeService formDefTreeService;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private BpmFormFieldService bpmFormFieldService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private BpmDataTemplateService bpmDataTemplateService;

	/**
	 * 添加或更新form_def_tree。
	 * 
	 * @param request
	 * @param response
	 * @param formDefTree
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新form_def_tree")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = FileUtil.inputStream2String(request.getInputStream());
		FormDefTree formDefTree = JSONObjectUtil.toBean(json, FormDefTree.class);
		String resultMsg = null;
		try {
			if (formDefTree.getId() == null || formDefTree.getId() == 0) {
				resultMsg = getText("添加成功", "form_def_tree");
			} else {
				formDefTreeService.save(formDefTree);
				resultMsg = getText("更新成功", "form_def_tree");
			}
			formDefTreeService.save(formDefTree);
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
		} catch (Exception e) {
			if (e instanceof DuplicateKeyException) {
				resultMsg = "别名已被使用：" + formDefTree.getAlias();
			} else {
				resultMsg += "," + e.getMessage();
			}
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Fail);
		}
	}

	/**
	 * 取得form_def_tree分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看form_def_tree分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<FormDefTree> list = formDefTreeService.getAll(new QueryFilter(request, "formDefTreeItem"));
		ModelAndView mv = this.getAutoView().addObject("formDefTreeList", list);
		return mv;
	}

	/**
	 * 删除form_def_tree
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除form_def_tree")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			formDefTreeService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除form_def_tree成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("show_{alias}")
	@Action(description = "删除form_def_tree")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "alias") String alias) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/platform/bpm/formDefTreeShow.jsp");
		return mv.addObject("alias", alias);

	}

	/**
	 * 开始尝试一种新的controller方法 在调用ngjs中，大部分数据都是异步加载的 那么会导致写一堆controller方法，并且大部分方法只在一个页面请求用到 那么我尝试利用return Object 来达到根据页面传的不同请求参数来返回不同对象 有点类似js function的弱对象性返回的方式 by liyj
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Object
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping("getObject")
	@Action(description = "按各种参数查询对象")
	@ResponseBody
	public Object getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 根据formDefId获取表数据
		Long formDefId = RequestUtil.getLong(request, "getTableByFormdefId", null);
		if (formDefId != null) {
			BpmFormDef bpmFormDef = bpmFormDefService.getById(formDefId);
			return bpmFormTableService.getByTableId(bpmFormDef.getTableId(), 0);
		}

		// 根据	获取对象
		formDefId = RequestUtil.getLong(request, "formDefId", null);
		if (formDefId != null) {
			return formDefTreeService.getByFormDefId(formDefId);
		}

		// 根据FormDefId获取树结构的treeJson数据
		formDefId = RequestUtil.getLong(request, "getTreeByFormDefId", null);
		if (formDefId != null) {
			String id = RequestUtil.getString(request, "id");// 这个参数是在异步加载时会赋值进来的
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", id);
			return formDefTreeService.treeListJson(formDefId, params);
		}

		// 根据字段ID获取字段
		Long fieldId = RequestUtil.getLong(request, "getFieldById", null);
		if (fieldId != null) {
			return bpmFormFieldService.getById(fieldId);
		}

		// 根据FormDefId获取业务数据模板
		formDefId = RequestUtil.getLong(request, "getFormKeyByFormDefId", null);
		if (formDefId != null) {
			JSONObject json = new JSONObject();
			json.put("alias", bpmFormDefService.getById(formDefId).getFormKey());
			return json;
		}

		// 根据别名获取对象
		String alias = RequestUtil.getString(request, "alias");
		if (StringUtil.isNotEmpty(alias)) {
			return formDefTreeService.getByAlias(alias);
		}
		return null;
	}

	@RequestMapping("delData_{alias}")
	@Action(description = "删除一条数据")
	public void delData(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "alias") String alias) throws Exception {
		String[] pks = RequestUtil.getStringAryByStr(request, "pk");
		for (String pk : pks) {
			bpmDataTemplateService.deleteData(alias, pk);
		}
	}

}
