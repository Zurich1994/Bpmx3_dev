package com.hotent.platform.controller.bpm;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmBatchApproval;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.BpmTree;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.FormField;
import com.hotent.platform.service.bpm.BpmBatchApprovalService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.form.BpmFormTableService;

/**
 * <pre>
 * 对象功能:流程批量审批定义设置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-04-17 15:34:17
 * </pre>
 */
@Controller
@RequestMapping("/platform/bpm/bpmBatchApproval/")
public class BpmBatchApprovalController extends BaseController {
	@Resource
	private BpmBatchApprovalService bpmBatchApprovalService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private BpmFormFieldService bpmFormFieldService;

	@Resource
	private BpmService bpmService;
	@Resource
	private ProcessRunService processRunService;

	/**
	 * 添加或更新流程批量审批定义设置。
	 * 
	 * @param request
	 * @param response
	 * @param bpmBatchApproval
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新流程批量审批定义设置")
	public void save(HttpServletRequest request, HttpServletResponse response,
			BpmBatchApproval bpmBatchApproval) throws Exception {
		String resultMsg = null;
		try {
			//检查是否添加过
			boolean isExists = bpmBatchApprovalService.isExists(bpmBatchApproval);
			if(isExists){
				writeResultMessage(response.getWriter(), "添加的流程定义和节点已经存在，请检查。",
						ResultMessage.Fail);
				return;
			}
			Boolean isAdd = bpmBatchApprovalService.save(bpmBatchApproval);
			resultMsg = getText(isAdd ? "添加" : "更新", "流程批量审批定义设置");
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得流程批量审批定义设置分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看流程批量审批定义设置分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<BpmBatchApproval> list = bpmBatchApprovalService
				.getAll(new QueryFilter(request, "bpmBatchApprovalItem"));
		for (BpmBatchApproval bpmBatchApproval : list) {
			BpmDefinition bpmDefinition = bpmDefinitionService
					.getMainByDefKey(bpmBatchApproval.getDefKey());
			BpmNodeSet bpmNodeSet = bpmNodeSetService.getByMyActDefIdNodeId(
					bpmDefinition.getActDefId(), bpmBatchApproval.getNodeId());
			bpmBatchApproval.setSubject(bpmDefinition.getSubject());
			bpmBatchApproval.setNodeName(bpmNodeSet.getNodeName());
		}

		ModelAndView mv = this.getAutoView().addObject("bpmBatchApprovalList",
				list);
		return mv;
	}

	/**
	 * 删除流程批量审批定义设置
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除流程批量审批定义设置")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			bpmBatchApprovalService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success,
					"删除流程批量审批定义设置成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑流程批量审批定义设置
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑流程批量审批定义设置")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);

		BpmBatchApproval bpmBatchApproval = bpmBatchApprovalService.getById(id);
		if (BeanUtils.isNotEmpty(bpmBatchApproval)) {
			BpmDefinition bpmDefinition = bpmDefinitionService
					.getMainByDefKey(bpmBatchApproval.getDefKey());
			bpmBatchApproval.setSubject(bpmDefinition.getSubject());
			FlowNode flowNode = NodeCache.getNodeByActNodeId(
					bpmDefinition.getActDefId(), bpmBatchApproval.getNodeId());
			bpmBatchApproval.setNodeName(flowNode.getNodeName());
		}

		return getAutoView().addObject("bpmBatchApproval", bpmBatchApproval)
				.addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得流程批量审批定义设置明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看流程批量审批定义设置明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		BpmBatchApproval bpmBatchApproval = bpmBatchApprovalService.getById(id);
		return getAutoView().addObject("bpmBatchApproval", bpmBatchApproval);
	}

	/**
	 * 取得流程批量审批定义设置明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fieldDialog")
	@Action(description = "查看流程批量审批定义设置明细")
	public ModelAndView fieldDialog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String defKey = RequestUtil.getString(request, "defKey");
		String nodeId = RequestUtil.getString(request, "nodeId");

		String fieldJson = RequestUtil.getString(request, "fieldJson");
		Map<String, FormField> map = getFieldJson(fieldJson);

		BpmDefinition bpmDefinition = bpmDefinitionService
				.getMainByDefKey(defKey);
		BpmNodeSet bpmNodeSet = bpmNodeSetService.getByActDefIdNodeId(
				bpmDefinition.getActDefId(), nodeId);

		BpmFormDef bpmFormDef = bpmFormDefService
				.getDefaultPublishedByFormKey(bpmNodeSet.getFormKey());
		Long tableId = null;

		List<FormField> formFieldList = new ArrayList<FormField>();
		if (BeanUtils.isEmpty(bpmFormDef))
			return getAutoView().addObject("formFieldList", formFieldList)
					.addObject("tableId", tableId);
		tableId = bpmFormDef.getTableId();
		List<BpmFormField> list = bpmFormFieldService.getAllByTableId(tableId);

		for (BpmFormField bpmFormField : list) {
			String fieldName = bpmFormField.getFieldName();

			FormField formField = map.get(fieldName);
			if (BeanUtils.isEmpty(formField)) {
				formField = new FormField();
				formField.setFieldDesc(bpmFormField.getFieldDesc());
			}
			formField.setFieldName(bpmFormField.getFieldName());

			formFieldList.add(formField);
		}
		formFieldSort(formFieldList);

		return getAutoView().addObject("formFieldList", formFieldList)
				.addObject("tableId", tableId);
	}

	/**
	 * 排序
	 * 
	 * @param list
	 */
	private void formFieldSort(List<FormField> list) {
		// 排序
		Collections.sort(list, new Comparator<FormField>() {
			public int compare(FormField arg0, FormField arg1) {
				int sn1 = arg0.getSn();
				int sn2 = arg1.getSn();
				if (sn1 == sn2) {
					return 0;
				} else if (sn1 > sn2) {
					return 1;
				} else {
					return -1;
				}

			}
		});
	}

	/**
	 * 获取字段JSON
	 * 
	 * @param fieldJson
	 * @return
	 */
	private Map<String, FormField> getFieldJson(String fieldJson) {
		Map<String, FormField> map = new HashMap<String, FormField>();
		if (BeanUtils.isEmpty(fieldJson))
			return map;

		JSONArray jary = JSONArray.fromObject(fieldJson);
		for (Object obj : jary) {
			FormField formField = new FormField();
			JSONObject json = (JSONObject) obj;
			String fieldName = (String) json.get("fieldName");
			String fieldDesc = (String) json.get("fieldDesc");
			String isShow = (String) json.get("isShow");
			String sn = (String) json.get("sn");
			formField.setFieldName(fieldName);
			formField.setFieldDesc(fieldDesc);
			formField.setIsShow(Short.parseShort(isShow));
			formField.setSn(Integer.parseInt(sn));
			map.put(fieldName, formField);
		}
		return map;
	}

	/**
	 * 展示树数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("tree")
	@ResponseBody
	public List<BpmTree> tree(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, false);
		List<?> taskList = bpmService.getMyTasks(filter); //这个考虑下是否这样获取数据
		List<BpmBatchApproval> approvalList = bpmBatchApprovalService.getAll();//这个没有权限，把所有数据取出来 
		Map<String, BpmBatchApproval> map = new LinkedHashMap<String, BpmBatchApproval>();
		List<BpmTree> treeList = new ArrayList<BpmTree>();
		if (BeanUtils.isEmpty(taskList) || BeanUtils.isEmpty(approvalList))
			return treeList;

		for (BpmBatchApproval bpmBatchApproval : approvalList) {
			BpmDefinition bpmDefinition = bpmDefinitionService
					.getMainByDefKey(bpmBatchApproval.getDefKey());
			BpmNodeSet bpmNodeSet = bpmNodeSetService.getByMyActDefIdNodeId(
					bpmDefinition.getActDefId(), bpmBatchApproval.getNodeId());
			bpmBatchApproval.setSubject(bpmDefinition.getSubject());
			bpmBatchApproval.setNodeName(bpmNodeSet.getNodeName());
			map.put(bpmBatchApproval.getDefKey() + ";"
					+ bpmBatchApproval.getNodeId(), bpmBatchApproval);
		}
		Map<String, List<ProcessTask>> map1 = new HashMap<String, List<ProcessTask>>();
		for (int i = 0; i < taskList.size(); i++) {
			ProcessTask task = (ProcessTask) taskList.get(i);
			String actDefId = task.getProcessDefinitionId();
			String defKey = actDefId.split(":")[0];
			String nodeId = task.getTaskDefinitionKey();
			String key = defKey + ";" + nodeId;
			BpmBatchApproval b = map.get(key);
			if (BeanUtils.isEmpty(b))
				continue;
			List<ProcessTask> list1 = map1.get(key);
			if (BeanUtils.isEmpty(list1))
				list1 = new ArrayList<ProcessTask>();
			list1.add(task);
			map1.put(key, list1);
		}

		// 构建树
		Map<String, BpmBatchApproval> defMap = new HashMap<String, BpmBatchApproval>();

		// 构建树-节点
		for (Entry<String, BpmBatchApproval> entry : map.entrySet()) {
			String key = entry.getKey();
			BpmBatchApproval bpmBatchApproval = entry.getValue();
			List<ProcessTask> list = map1.get(key);
			int count = 0;
			if (BeanUtils.isNotEmpty(list))
				count = list.size();
			String defKey = bpmBatchApproval.getDefKey();
			String nodeId = bpmBatchApproval.getNodeId();
			BpmTree tree = new BpmTree();
			tree.setDefKey(defKey);
			tree.setNodeId(nodeId);
			tree.setName(bpmBatchApproval.getNodeName() + "(" + count + ")");
			tree.setId(defKey + "_" + nodeId);
			tree.setPid(defKey);
			tree.setCount(count);
			tree.setBatchId(bpmBatchApproval.getId());
			treeList.add(tree);

			BpmBatchApproval b = defMap.get(defKey);
			if (BeanUtils.isNotEmpty(b)) {
				b.setCount(b.getCount() + count);
				defMap.put(defKey, b);
			} else {
				bpmBatchApproval.setCount(count);
				defMap.put(defKey, bpmBatchApproval);
			}
		}
		// 构建树-流程定义
		for (Entry<String, BpmBatchApproval> entry : defMap.entrySet()) {
			BpmBatchApproval bpmBatchApproval = entry.getValue();
			String defKey = bpmBatchApproval.getDefKey();
			String nodeId = bpmBatchApproval.getNodeId();
			int count = bpmBatchApproval.getCount();

			BpmTree tree = new BpmTree();
			tree.setDefKey(defKey);
			tree.setNodeId(nodeId);
			tree.setName(bpmBatchApproval.getSubject() + "(" + count + ")");
			tree.setId(defKey);
			tree.setPid(null);
			tree.setCount(count);
			treeList.add(tree);
		}
		return treeList;
	}

	/**
	 * 取得流程批量审批定义设置分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("lists")
	@Action(description = "查看流程批量审批定义设置分页列表")
	public ModelAndView lists(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long batchId = RequestUtil.getLong(request, "batchId");
		BpmBatchApproval bpmBatchApproval = bpmBatchApprovalService
				.getById(batchId); // 获取到需要批量审批配置信息
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<String> fieldList = new ArrayList<String>();
		List<String> fieldNameList = new ArrayList<String>();
		boolean isDataError =  false;
		try {
			if(BeanUtils.isNotEmpty(bpmBatchApproval)){
				// 列表显示字段
				fieldList = bpmBatchApprovalService.getFieldList(bpmBatchApproval);
				// 列表字段
				fieldNameList = bpmBatchApprovalService
						.getFieldNameList(bpmBatchApproval);
				// 列表数据
				dataList = bpmBatchApprovalService.getDataList(bpmBatchApproval);
			}
		} catch (Exception e) {
			e.printStackTrace();
			isDataError = true;
		}

		return this.getAutoView().addObject("dataList", dataList)
				.addObject("fieldNameList", fieldNameList)
				.addObject("fieldList", fieldList)
				.addObject("isDataError",isDataError);
	}

	/**
	 * 批量审批窗口
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dialog")
	@Action(description = "批量审批")
	public ModelAndView dialog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskIds = RequestUtil.getString(request, "taskIds");
		return this.getAutoView().addObject("taskIds", taskIds);
	}

	/**
	 * 批量审批同意
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("batComplte")
	@Action(description = "批量审批同意")
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		ResultMessage resultMessage = null;
		String taskIds = RequestUtil.getString(request, "taskIds");
		String opinion = RequestUtil.getString(request, "opinion");
		try {
			processRunService.nextProcessBat(taskIds, opinion);
			String message = MessageUtil.getMessage();
			resultMessage = new ResultMessage(ResultMessage.Success, message);
		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail, str);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
			}
		}
		out.print(resultMessage);
	}
}
