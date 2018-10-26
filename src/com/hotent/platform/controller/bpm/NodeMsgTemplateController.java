package com.hotent.platform.controller.bpm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.MapUtil;
import com.hotent.core.util.jsonobject.JSONObjectUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.NodeMsgTemplate;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.NodeMsgTemplateService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormTableService;

/**
 * <pre>
 * 对象功能:bpm_nodemsg_template 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-05-27 18:03:14
 * </pre>
 */
@Controller
@RequestMapping("/platform/bpm/nodeMsgTemplate/")
public class NodeMsgTemplateController extends BaseController {
	@Resource
	private NodeMsgTemplateService nodeMsgTemplateService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private FreemarkEngine freemarkEngine;
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;

	/**
	 * 添加或更新bpm_nodemsg_template。
	 * 
	 * @param request
	 * @param response
	 * @param nodeMsgTemplate
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新bpm_nodemsg_template")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultMsg = null;
		String json = FileUtil.inputStream2String(request.getInputStream());
		NodeMsgTemplate nodeMsgTemplate = JSONObjectUtil.toBean(json, NodeMsgTemplate.class);
		try {
			if (nodeMsgTemplate.getId() == null || nodeMsgTemplate.getId() == 0) {
				resultMsg = getText("添加成功", "bpm_nodemsg_template");
			} else {
				resultMsg = getText("更新成功", "bpm_nodemsg_template");
			}
			nodeMsgTemplateService.save(nodeMsgTemplate);
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得bpm_nodemsg_template分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看bpm_nodemsg_template分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<NodeMsgTemplate> list = nodeMsgTemplateService.getAll(new QueryFilter(request, "nodeMsgTemplateItem"));

		// 获取nodeName信息，为了展现通俗易懂点
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (NodeMsgTemplate nmt : list) {
			Map<String, Object> map = MapUtil.transBean2Map(nmt);
			BpmDefinition bpmDefinition = bpmDefinitionService.getById(nmt.getDefId());

			BpmNodeSet bpmNodeSet = bpmNodeSetService.getByMyActDefIdNodeId(bpmDefinition.getActDefId(), nmt.getNodeId());

			if (bpmNodeSet != null) {
				map.put("nodeName", bpmNodeSet.getNodeName());
			}
			if (nmt.getNodeId().equals("StartEvent1")) {
				map.put("nodeName", "开始节点");
			}
			if (nmt.getNodeId().equals("EndEvent1")) {
				map.put("nodeName", "结束节点");
			}
			mapList.add(map);
		}
		return this.getAutoView().addObject("nodeMsgTemplateList", mapList);
	}

	/**
	 * 删除bpm_nodemsg_template
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除bpm_nodemsg_template")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			nodeMsgTemplateService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除bpm_nodemsg_template成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("ngjs")
	@Action(description = "ngjs的请求，分为参数和action，action是说明这次请求的目的")
	@ResponseBody
	public Object ngjs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = RequestUtil.getString(request, "action");
		if (action.equals("getTableByDefId")) {// 获取流程表
			Long defId = RequestUtil.getLong(request, "defId");
			return bpmFormTableService.getByDefId(defId);
		}
		if (action.equals("getObject")) {// 初始化对象
			Long defId = RequestUtil.getLong(request, "defId", null);
			String nodeId = RequestUtil.getString(request, "nodeId", null);
			Long parentDefId = RequestUtil.getLong(request, "parentDefId", null);
			return nodeMsgTemplateService.getObject(nodeId, defId, parentDefId);
		}
		if (action.equals("getById")) {
			Long id = RequestUtil.getLong(request, "id");
			return nodeMsgTemplateService.getById(id);
		}
		if (action.equals("getInitTemp")) {// 获取初始化html 是通过模板生成的
			Long defId = RequestUtil.getLong(request, "defId");
			int columnNum = RequestUtil.getInt(request, "columnNum", 1);
			Map<String, Object> map = new HashMap<String, Object>();
			BpmFormTable table = bpmFormTableService.getByDefId(defId);
			map.put("table", table);
			map.put("columnNum", columnNum);
			String html = freemarkEngine.mergeTemplateIntoString("msg/nodeMsgTemp_html.ftl", map);
			String text = freemarkEngine.mergeTemplateIntoString("msg/nodeMsgTemp_text.ftl", map);
			JSONObject json = new JSONObject();
			json.put("html", html);
			json.put("text", text);
			return json;
		}
		if (action.equals("getSubTableTemp")) {// 返回这张子表的所有模板
			Long defId = RequestUtil.getLong(request, "defId");
			BpmFormTable table = bpmFormTableService.getByDefId(defId);
			JSONObject json = new JSONObject();
			for (BpmFormTable subTable : table.getSubTableList()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("subTable", subTable);
				map.put("table", table);
				String output = freemarkEngine.mergeTemplateIntoString("msg/nodeMsgTemp_subTable.ftl", map);
				json.put(subTable.getTableName(), output);
			}
			return json;
		}
		if (action.equals("getNode")) {// 获取节点信息
			String nodeId = RequestUtil.getString(request, "nodeId", null);
			Long defId = RequestUtil.getLong(request, "defId");
			BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
			BpmNodeSet bpmNodeSet = bpmNodeSetService.getByMyActDefIdNodeId(bpmDefinition.getActDefId(), nodeId);
			JSONObject json = new JSONObject();
			if(bpmNodeSet!=null){
				json.put("nodeName", bpmNodeSet.getNodeName());
			}
			if (nodeId.equals("StartEvent1")) {
				json.put("nodeName", "开始");
			}
			if (nodeId.equals("EndEvent1")) {
				json.put("nodeName", "结束");
			}
			return json;
		}

		return null;
	}

}
