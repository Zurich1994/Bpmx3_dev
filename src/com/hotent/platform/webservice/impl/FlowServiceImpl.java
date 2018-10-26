package com.hotent.platform.webservice.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.displaytag.util.ParamEncoder;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.page.PageBean;
import com.hotent.core.page.PageList;
import com.hotent.core.util.ArrayUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmDefAuthorizeType.BPMDEFAUTHORIZE_RIGHT_TYPE;
import com.hotent.platform.model.bpm.AuthorizeRight;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmRunLog;
import com.hotent.platform.model.bpm.BpmTaskExe;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.BpmDefAuthorizeService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmRunLogService;
import com.hotent.platform.service.bpm.BpmTaskExeService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.TaskOpinionService;
import com.hotent.platform.service.bpm.impl.BpmActService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.webservice.api.FlowService;
import com.hotent.platform.webservice.impl.util.GsonUtil;

public class FlowServiceImpl implements FlowService {
	@Resource
	SysUserService sysUserService;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private TaskOpinionService taskOpinionService;
	@Resource
	BpmActService bpmActService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmDefAuthorizeService bpmDefAuthorizeService;
	@Resource
	private BpmTaskExeService bpmTaskExeService;
	@Resource
	private BpmRunLogService bpmRunLogService;
	@Resource
	private BpmFormHandlerService bpmFormHandlerService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	
	@Resource
	private GlobalTypeService globalTypeService;

	private ParamEncoder paramEncoder;

	/**
	 * 获取指定useraccount用户的已办事宜
	 */
	public String getAlreadyMattersListJson(String json) {

		JSONObject result = new JSONObject();
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);

			String account = jsonObject.getString("account");
			String classType = jsonObject.get("classType") != null ? jsonObject.getString("classType") : "";
			String currentPageStr = jsonObject.get("currentPageStr") != null ? jsonObject.getString("currentPageStr") : "";
			String pageSize = jsonObject.get("pageSize") != null ? jsonObject.getString("pageSize") : "";

			Integer currentPage = StringUtil.isNotEmpty(currentPageStr) ? Integer.parseInt(currentPageStr) : 0;
			QueryFilter filter = new QueryFilter(setCurrentPage("taskItem", currentPage), "taskItem", true, StringUtil.isNotEmpty(pageSize) ? Integer.parseInt(pageSize) : PageBean.DEFAULT_PAGE_SIZE);
			if (StringUtil.isEmpty(account)) {// 没传用户就报错
				throw new Exception("必须传入用户账号(account)");
			}
			SysUser user = sysUserService.getByAccount(account);
			if (BeanUtils.isEmpty(user)) {
				throw new Exception("该账号的用户不存在");
			}
			ContextUtil.setCurrentUser(user);

			filter.addFilter("assignee", user.getUserId());// 用户id
			if (StringUtil.isNotEmpty(classType)) {
				StringBuffer sb = new StringBuffer();
				for(String str:classType.split(",")){
					if(sb.length()>0){
						sb.append(",");
					}
					sb.append("'"+str+"'");
				}
				classType=sb.toString();
				
				boolean inOrNot = jsonObject.get("inOrNot") != null ? jsonObject.getBoolean("inOrNot") : true;// 包含还是排除\
				if (inOrNot) {
					filter.addFilter("nodeKey", classType);
				} else {
					filter.addFilter("notNodeKey", classType);
				}
			}

			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			List<ProcessRun> list = processRunService.getAlreadyMattersList(filter);
			for (ProcessRun processRun : list) {
				if (processRun.getStatus().shortValue() != ProcessRun.STATUS_FINISH.shortValue()) {
					// 1.查找当前用户是否有最新审批的任务
					TaskOpinion taskOpinion = taskOpinionService.getLatestUserOpinion(processRun.getActInstId(), new Long(user.getUserId()));
					if (BeanUtils.isNotEmpty(taskOpinion))
						processRun.setRecover(ProcessRun.STATUS_RECOVER);
				}

				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("obj", processRun);
				GlobalType globalType = globalTypeService.getById(processRun.getTypeId());
				map.put("nodeKey", globalType != null ? globalType.getNodeKey() : "");
				mapList.add(map);
			}

			result.put("totalCount", ((PageList) list).getTotalCount());
			result.put("state", "0");
			result.put("msg", "操作成功");
			result.put("list", GsonUtil.toJsonTree(mapList).toString());
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + ExceptionUtils.getRootCauseMessage(e));
		}

		return result.toString();
	}

	/**
	 * 获取指定UserAccount用户我的办结事宜
	 */
	public String getMyCompletedListJson(String json) {
		JSONObject result = new JSONObject();
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);

			String account = jsonObject.getString("account");
			String classType = jsonObject.get("classType") != null ? jsonObject.getString("classType") : "";
			String currentPageStr = jsonObject.get("currentPageStr") != null ? jsonObject.getString("currentPageStr") : "";
			String pageSize = jsonObject.get("pageSize") != null ? jsonObject.getString("pageSize") : "";

			Integer currentPage = StringUtil.isNotEmpty(currentPageStr) ? Integer.parseInt(currentPageStr) : 0;
			QueryFilter filter = new QueryFilter(setCurrentPage("taskItem", currentPage), "taskItem", true, StringUtil.isNotEmpty(pageSize) ? Integer.parseInt(pageSize) : PageBean.DEFAULT_PAGE_SIZE);
			if (StringUtil.isEmpty(account)) {// 没传用户就报错
				throw new Exception("必须传入用户账号(account)");
			}
			SysUser user = sysUserService.getByAccount(account);
			if (BeanUtils.isEmpty(user)) {
				throw new Exception("该账号的用户不存在");
			}
			filter.addFilter("creatorId", user.getUserId());// 用户id
			if (StringUtil.isNotEmpty(classType)) {
				StringBuffer sb = new StringBuffer();
				for(String str:classType.split(",")){
					if(sb.length()>0){
						sb.append(",");
					}
					sb.append("'"+str+"'");
				}
				classType=sb.toString();
				
				boolean inOrNot = jsonObject.get("inOrNot") != null ? jsonObject.getBoolean("inOrNot") : true;// 包含还是排除\
				if (inOrNot) {
					filter.addFilter("nodeKey", classType);
				} else {
					filter.addFilter("notNodeKey", classType);
				}
			}
			List<ProcessRun> list = processRunService.getMyCompletedList(filter);

			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			for (ProcessRun obj : list) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("obj", obj);
				GlobalType globalType = globalTypeService.getById(obj.getTypeId());
				map.put("nodeKey", globalType != null ? globalType.getNodeKey() : "");
			}

			result.put("totalCount", ((PageList) list).getTotalCount());
			result.put("state", "0");
			result.put("msg", "操作成功");
			result.put("list", GsonUtil.toJsonTree(mapList).toString());
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + ExceptionUtils.getRootCauseMessage(e));
		}
		return result.toString();
	}

	public String getMyFlowListJson(String json) {
		JSONObject result = new JSONObject();
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);

			String account = jsonObject.getString("account");
			String classType = jsonObject.get("classType") != null ? jsonObject.getString("classType") : "";
			String currentPageStr = jsonObject.get("currentPageStr") != null ? jsonObject.getString("currentPageStr") : "";
			String pageSize = jsonObject.get("pageSize") != null ? jsonObject.getString("pageSize") : "";

			Integer currentPage = StringUtil.isNotEmpty(currentPageStr) ? Integer.parseInt(currentPageStr) : 0;
			QueryFilter filter = new QueryFilter(setCurrentPage("taskItem", currentPage), "taskItem", true, StringUtil.isNotEmpty(pageSize) ? Integer.parseInt(pageSize) : PageBean.DEFAULT_PAGE_SIZE);
			if (account == null) {// 没传用户就报错
				throw new Exception("必须传入用户账号(account)");
			}
			SysUser user = sysUserService.getByAccount(account);
			if (BeanUtils.isEmpty(user))
				throw new Exception("该账号的用户不存在");
			ContextUtil.setCurrentUserAccount(account);
			filter.addFilter("ownerId", user.getUserId());// 用户id

			// 处理分类 多个分类以,分割<---------------
			if (StringUtil.isNotEmpty(classType)) {
				StringBuffer sb = new StringBuffer();
				for(String str:classType.split(",")){
					if(sb.length()>0){
						sb.append(",");
					}
					sb.append("'"+str+"'");
				}
				classType=sb.toString();
				
				boolean inOrNot = jsonObject.get("inOrNot") != null ? jsonObject.getBoolean("inOrNot") : true;// 包含还是排除\
				if (inOrNot) {
					filter.addFilter("nodeKey", classType);
				} else {
					filter.addFilter("notNodeKey", classType);
				}
			}
			// <----------------

			String isNeedRight = "";
			if (!SysUser.isSuperAdmin(user)) {
				isNeedRight = "yes";
				// 获得流程分管授权与用户相关的信息
				Map<String, Object> actRightMap = bpmDefAuthorizeService.getActRightByUserMap(user.getUserId(), BPMDEFAUTHORIZE_RIGHT_TYPE.START, false, false);
				// 获得流程分管授权与用户相关的信息集合的流程KEY
				String actRights = (String) actRightMap.get("authorizeIds");
				filter.addFilter("actRights", actRights);
			}
			filter.addFilter("isNeedRight", isNeedRight);
			List<BpmDefinition> list = bpmDefinitionService.getMyDefList(filter, 0L);

			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			// 把nodeKey也包含结果中
			for (BpmDefinition obj : list) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("obj", obj);
				GlobalType globalType = globalTypeService.getById(obj.getTypeId());
				map.put("nodeKey", globalType != null ? globalType.getNodeKey() : "");
				mapList.add(map);
			}

			result.put("totalCount", ((PageList) list).getTotalCount());
			result.put("state", "0");
			result.put("msg", "操作成功");
			result.put("list", GsonUtil.toJsonTree(mapList).toString());
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + ExceptionUtils.getRootCauseMessage(e));
		}
		return result.toString();
	}

	public String getMyRequestListJson(String json) {
		JSONObject result = new JSONObject();
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);

			String account = jsonObject.getString("account");
			String classType = jsonObject.get("classType") != null ? jsonObject.getString("classType") : "";
			String currentPageStr = jsonObject.get("currentPageStr") != null ? jsonObject.getString("currentPageStr") : "";
			String pageSize = jsonObject.get("pageSize") != null ? jsonObject.getString("pageSize") : "";

			Integer currentPage = StringUtil.isNotEmpty(currentPageStr) ? Integer.parseInt(currentPageStr) : 0;
			QueryFilter filter = new QueryFilter(setCurrentPage("taskItem", currentPage), "taskItem", true, StringUtil.isNotEmpty(pageSize) ? Integer.parseInt(pageSize) : PageBean.DEFAULT_PAGE_SIZE);
			if (account == null) // 没传用户就报错
				throw new Exception("必须传入用户账号(account)");
			SysUser user = sysUserService.getByAccount(account);
			if (BeanUtils.isEmpty(user))
				throw new Exception("该账号的用户不存在");
			filter.addFilter("creatorId", user.getUserId());

			if (StringUtil.isNotEmpty(classType)) {
				StringBuffer sb = new StringBuffer();
				for(String str:classType.split(",")){
					if(sb.length()>0){
						sb.append(",");
					}
					sb.append("'"+str+"'");
				}
				classType=sb.toString();
				
				boolean inOrNot = jsonObject.get("inOrNot") != null ? jsonObject.getBoolean("inOrNot") : true;// 包含还是排除\
				if (inOrNot) {
					filter.addFilter("nodeKey", classType);
				} else {
					filter.addFilter("notNodeKey", classType);
				}
			}

			List<ProcessRun> list = processRunService.getMyRequestList(filter);

			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			for (ProcessRun processRun : list) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("obj", processRun);
				GlobalType globalType = globalTypeService.getById(processRun.getTypeId());
				map.put("nodeKey", globalType != null ? globalType.getNodeKey() : "");
				mapList.add(map);
			}

			result.put("totalCount", ((PageList) list).getTotalCount());
			result.put("state", "0");
			result.put("msg", "操作成功");
			result.put("list", GsonUtil.toJsonTree(mapList).toString());
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + ExceptionUtils.getRootCauseMessage(e));
		}
		return result.toString();
	}

	public String getMyDraftListJson(String json) {
		JSONObject result = new JSONObject();
		
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);

			String account = jsonObject.getString("account");
			String currentPageStr = jsonObject.get("currentPageStr") != null ? jsonObject.getString("currentPageStr") : "";
			String pageSize = jsonObject.get("pageSize") != null ? jsonObject.getString("pageSize") : "";

			Integer currentPage = StringUtil.isNotEmpty(currentPageStr) ? Integer.parseInt(currentPageStr) : 0;
			QueryFilter filter = new QueryFilter(setCurrentPage("taskItem", currentPage), "taskItem", true, StringUtil.isNotEmpty(pageSize) ? Integer.parseInt(pageSize) : PageBean.DEFAULT_PAGE_SIZE);
			if (account == null) {// 没传用户就报错
				throw new Exception("必须传入用户账号(account)");
			}

			SysUser user = sysUserService.getByAccount(account);
			if (BeanUtils.isEmpty(user)) {
				throw new Exception("该账号的用户不存在");
			}
			ContextUtil.setCurrentUser(user);
			
			filter.addFilter("userId", user.getUserId());
			List<ProcessRun> list = processRunService.getMyDraft(filter);
			
			result.put("totalCount", ((PageList) list).getTotalCount());
			result.put("state", "0");
			result.put("msg", "操作成功");
			result.put("list", GsonUtil.toJsonTree(list).toString());
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + ExceptionUtils.getRootCauseMessage(e));
		}
		return result.toString();
	}

	public String delDraftByRunIds(String runIds) {
		try {
			if (StringUtil.isEmpty(runIds)) {
				throw new Exception("请输入需要删除的草稿id");
			}
			Long[] runIdAry = ArrayUtil.convertArray(runIds.split(","));
			for (long runId : runIdAry) {
				ProcessRun processRun = processRunService.getById(runId);
				String dsAlias = processRun.getDsAlias();
				String tableName = processRun.getTableName();
				String businessKey = processRun.getBusinessKey();
				if (StringUtil.isNotEmpty(tableName)) {
					if (StringUtil.isEmpty(dsAlias) || DataSourceUtil.DEFAULT_DATASOURCE.equals(dsAlias)) {
						bpmFormHandlerService.delByIdTableName(businessKey, "W_" + tableName);
					} else {
						bpmFormHandlerService.delByDsAliasAndTableName(dsAlias, tableName, businessKey);
					}
				}
				bpmRunLogService.addRunLog(runId, BpmRunLog.OPERATOR_TYPE_DELETEFORM, "删除草稿");
				processRunService.delById(runId);
			}
			return getReturn(true, null);
		} catch (Exception e) {
			return getReturn(false, ExceptionUtils.getRootCauseMessage(e));
		}
	}

	public String saveDraftByForm(String json) {
		JSONObject result = new JSONObject();
		JSONObject jsonObject = JSONObject.fromObject(json != null ? json : "{}");
		try {
			Long userId = jsonObject.getLong("userId");
			ProcessCmd processCmd = BpmUtil.getProcessCmd(jsonObject);
			SysUser user = sysUserService.getById(userId);
			if (user == null) {
				throw new Exception("用户ID不存在");
			}
			ContextUtil.setCurrentUser(user);
			processCmd.setCurrentUserId(userId.toString());
			// 更新
			if (StringUtil.isNotEmpty(processCmd.getBusinessKey())) {
				ProcessRun processRun = processRunService.getById(jsonObject.getLong("runId"));
				processCmd.setProcessRun(processRun);
				processRunService.saveData(processCmd);
			} else {// 添加
				processRunService.saveForm(processCmd);
			}
			result.put("state", "0");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}

		return result.toString();
	}

	/**
	 * 返回接口调用结果
	 * 
	 * @param success
	 *            接口调用是否成功
	 * @param message
	 *            消息内容
	 * @return
	 */
	private String getReturn(Boolean success, String message) {
		JsonObject json = new JsonObject();
		json.addProperty("result", success ? "true" : "false");
		if (StringUtil.isNotEmpty(message)) {
			json.addProperty("message", message);
		}
		return json.toString();
	}

	private JSONObject setCurrentPage(String tableId, Integer currentPage) {
		JSONObject json = new JSONObject();
		this.paramEncoder = new ParamEncoder(tableId);
		String tableIdCode = this.paramEncoder.encodeParameterName("");
		json.put(tableIdCode + "p", currentPage);
		return json;
	}
}
