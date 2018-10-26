package com.hotent.platform.controller.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.annotion.Action;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;

import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.MapUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.util.StringUtil;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.jsonobject.JSONObjectUtil;

import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNode;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.BpmNodeSql;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.system.SysDataSource;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmNodeSqlService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.core.web.ResultMessage;

/**
 * <pre>
 * 对象功能:bpm_node_sql 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-03-05 09:59:30
 * </pre>
 */
@Controller
@RequestMapping("/platform/bpm/bpmNodeSql/")
public class BpmNodeSqlController extends BaseController {
	@Resource
	private BpmNodeSqlService bpmNodeSqlService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmNodeSetService bpmNodeSetService;

	/**
	 * 添加或更新bpm_node_sql。
	 * 
	 * @param request
	 * @param response
	 * @param bpmNodeSql
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新bpm_node_sql")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultMsg = null;
		String json = FileUtil.inputStream2String(request.getInputStream());
		BpmNodeSql bpmNodeSql = JSONObjectUtil.toBean(json, BpmNodeSql.class);
		try {
			if (bpmNodeSql.getId() == null || bpmNodeSql.getId() == 0) {
				bpmNodeSql.setId(UniqueIdUtil.genId());
				bpmNodeSqlService.add(bpmNodeSql);
				resultMsg = getText("添加", "bpm_node_sql");
			} else {
				bpmNodeSqlService.update(bpmNodeSql);
				resultMsg = getText("更新", "bpm_node_sql");
			}
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得bpm_node_sql分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看bpm_node_sql分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryFilter queryFilter = new QueryFilter(request, "bpmNodeSqlItem");
		Long defId = RequestUtil.getLong(request, "defId");
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		queryFilter.addFilter("actdefId", bpmDefinition.getActDefId());
		List<BpmNodeSql> list = bpmNodeSqlService.getAll(queryFilter);

		// 获取nodeName信息，为了展现通俗易懂点
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for (BpmNodeSql bnq : list) {
			Map<String, Object> map = MapUtil.transBean2Map(bnq);
			BpmNodeSet bpmNodeSet = bpmNodeSetService.getByMyActDefIdNodeId(bpmDefinition.getActDefId(), bnq.getNodeId());
			if (bpmNodeSet != null) {
				map.put("nodeName", bpmNodeSet.getNodeName());
			}
			if (bnq.getNodeId().equals("StartEvent1")) {
				map.put("nodeName", "开始节点");
			}
			if (bnq.getNodeId().equals("EndEvent1")) {
				map.put("nodeName", "结束节点");
			}
			mapList.add(map);
		}

		ModelAndView mv = this.getAutoView().addObject("bpmNodeSqlList", mapList);
		return mv;
	}

	/**
	 * 删除bpm_node_sql
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除bpm_node_sql")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			bpmNodeSqlService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除bpm_node_sql成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑bpm_node_sql
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑bpm_node_sql")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		String returnUrl = RequestUtil.getPrePage(request);
		return getAutoView().addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得bpm_node_sql明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看bpm_node_sql明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		BpmNodeSql bpmNodeSql = bpmNodeSqlService.getById(id);
		return getAutoView().addObject("bpmNodeSql", bpmNodeSql);
	}

	@RequestMapping("getObject")
	@Action(description = "按各种参数查询bpmNodeSql")
	@ResponseBody
	public BpmNodeSql getObject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id", null);
		if (id != null) {
			return bpmNodeSqlService.getById(id);
		}
		String nodeId = RequestUtil.getString(request, "nodeId", "");
		String actdefId = RequestUtil.getString(request, "actdefId", "");
		if (StringUtil.isNotEmpty(actdefId) && StringUtil.isNotEmpty(nodeId)) {
			return bpmNodeSqlService.getByNodeIdAndActdefId(nodeId, actdefId);
		}

		return null;
	}

	// 获取表，主要是为了主表字段
	@RequestMapping("getTable")
	@ResponseBody
	public BpmFormTable getTable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String actdefId = RequestUtil.getString(request, "actdefId", "");
		Long id = RequestUtil.getLong(request, "id", null);

		if (StringUtil.isEmpty(actdefId)) {
			actdefId = bpmNodeSqlService.getById(id).getActdefId();
		}
		BpmDefinition bpmDefinition = bpmDefinitionService.getByActDefId(actdefId);
		BpmFormTable bpmFormTable = AppUtil.getBean(BpmFormTableService.class).getByDefId(bpmDefinition.getDefId());
		return bpmFormTable;
	}

	// 获取节点信息
	@RequestMapping("getNodeType")
	@ResponseBody
	public Object getNodeType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nodeId = RequestUtil.getString(request, "nodeId", "");
		String actdefId = RequestUtil.getString(request, "actdefId", "");
		FlowNode flowNode = NodeCache.getByActDefId(actdefId).get(nodeId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nodeType", flowNode.getNodeType());
		return map;
	}
}
