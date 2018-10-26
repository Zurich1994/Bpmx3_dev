package com.hotent.mobile.controller.bpm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.mobile.controller.base.BaseMobileController;
import com.hotent.platform.model.bpm.AgentDef;
import com.hotent.platform.model.bpm.AgentSetting;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.AgentSettingService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.system.GlobalTypeService;

/**
 * <pre>
 * 对象功能:手机代理 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2014-04-02 15:09:58
 * </pre>
 */
@Controller
@RequestMapping("/mobile/bpm/bpmMobileAgent/")
public class BpmMobileAgentController extends BaseMobileController {

	@Resource
	private AgentSettingService agentSettingService;
	@Resource
	private GlobalTypeService globalTypeService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	/**
	 * 取得代理设定分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看代理设定分页列表")
	@ResponseBody
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QueryFilter filter = new QueryFilter(request, true);
		Long curUserId = ContextUtil.getCurrentUserId();
		filter.addFilter("authid", curUserId);
		List<AgentSetting> list = agentSettingService.getAll(filter);
		this.returnCallbackPageList(request, response, list, filter);
	}

	/**
	 * 添加或更新代理设定。
	 * 
	 * @param request
	 * @param response
	 * @param agentSetting
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新代理设定", detail = "<#if isAdd>添加代理设定<#else>更新代理设定</#if>"
			+ "${SysAuditLinkService.getAgentSettingLink(agentSetting)}")
	@ResponseBody
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean isSuccess = false;
		boolean isAdd = false;
		AgentSetting agentSetting = getFormObject(request);
		
	
		
		try {
			String msg = customValidate(agentSetting);
			 if(StringUtil.isNotEmpty(msg)){
					this.returnCallbackErrorData(request, response,
							msg);
					return;
			 }
				 
			if (agentSetting.getId() == null || agentSetting.getId() == 0) {
				agentSetting.setId(UniqueIdUtil.genId());
				// 非管理员添加，授权人为当前用户。
				boolean isAdmin = RequestUtil.getBoolean(request, "isAdmin",
						false);
				if (!isAdmin) {
					SysUser sysUser = ContextUtil.getCurrentUser();
					agentSetting.setAuthid(sysUser.getUserId());
				}
				agentSettingService.addAll(agentSetting);
				isAdd = true;

			} else {
				agentSettingService.updateAll(agentSetting);
			}
			this.returnCallbackSuccessData(request, response,
					"");
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			this.returnCallbackErrorData(request, response,
					"");
		}
		// **添加 系统日志信息** 开始//
		try {
			if (isSuccess) {
				SysAuditThreadLocalHolder
						.setResult(SysAuditThreadLocalHolder.RESULT_SUCCESS);
			} else {
				SysAuditThreadLocalHolder
						.setResult(SysAuditThreadLocalHolder.RESULT_FAIL);
			}
			SysAuditThreadLocalHolder.putParamerter("agentSetting",
					agentSetting);
			SysAuditThreadLocalHolder.putParamerter("isAdd", isAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// **添加 系统日志信息** end//
	}

	private String customValidate(AgentSetting agentSetting) throws Exception {
		if(agentSetting.getEnabled().longValue() != 1)
			return "";
		Short authtype = agentSetting.getAuthtype();
		String msg = "";
		if(authtype.shortValue() == AgentSetting.AUTHTYPE_GENERAL){//全权代理
			 msg = this.validateSettingComplictAgainstAll(agentSetting);	
			 return msg;
		}else if(authtype.shortValue() == AgentSetting.AUTHTYPE_PARTIAL ||
				authtype.shortValue() == AgentSetting.AUTHTYPE_CONDITION){//部分代理
			 msg = this.validateSettingComplictAgainstGeneral(agentSetting);	
			 if(StringUtil.isNotEmpty(msg))
				 return msg;
			 msg = 	 this.validateSettingComplictByFlow(agentSetting);
			 if(StringUtil.isNotEmpty(msg))
				 return msg;
		}
		return msg;
	}
	
	
	/**
	 * 验证流程是否可以代理
	 * @param agentSetting
	 * @return
	 * @throws Exception
	 */
	public String validateSettingComplictByFlow(AgentSetting agentSetting) throws Exception{
		String msg = "";
	
		Long authorId = agentSetting.getAuthid();
		if(authorId.longValue()==0L){
			authorId = ContextUtil.getCurrentUserId();
		}
		Date startDate =agentSetting.getStartdate();
		Date endDate = agentSetting.getEnddate();
		Long id = agentSetting.getId();
		List<AgentDef> flowKeyList = agentSetting.getAgentDefList();
		
		if(id!=0L){
			for(AgentDef agentDef:flowKeyList){
				boolean flag = agentSettingService.isComplictAgainstPartialOrConditionByFlow(agentDef.getFlowkey(),startDate,endDate,authorId,id);
				if(flag){
					String flowname =agentDef.getFlowname();
					msg += flowname+":"+getText("controller.agentSetting.validateSettingComplictByFlow")+";";
					continue;
				}
			}
			
		}else{
			for(AgentDef agentDef:flowKeyList){
				boolean flag = agentSettingService.isComplictAgainstPartialOrConditionByFlow(agentDef.getFlowkey(),startDate,endDate,authorId);
				if(flag){
					String flowname =agentDef.getFlowname();
					msg += flowname+":"+getText("controller.agentSetting.validateSettingComplictByFlow")+";";
					continue;
				}
			}
		}
		return msg;	

	}
	
	/**
	 * 验证是否与存在的有效全权代理代理冲突
	 * @param agentSetting
	 * @throws Exception
	 */
	public String validateSettingComplictAgainstGeneral(AgentSetting agentSetting) throws Exception{
		String msg = "";
		Long authorId = agentSetting.getAuthid();
		if(authorId.longValue()==0L){
			authorId = ContextUtil.getCurrentUserId();
		}
		Date startDate =agentSetting.getStartdate();
		Date endDate = agentSetting.getEnddate();
		Long id = agentSetting.getId();
		if(id!=0L){
			boolean flag = agentSettingService.isComplictAgainstGeneral(startDate, endDate, authorId,id);
			if(flag){
				//此时间段内与已有的有效全权代理冲突！
				msg = getText("controller.agentSetting.validateSettingComplictAgainstGeneral");
			}
		}else{
			boolean flag = agentSettingService.isComplictAgainstGeneral(startDate, endDate, authorId);
			if(flag){
				//此时间段内与已有的有效全权代理冲突！
				msg =  getText("controller.agentSetting.validateSettingComplictAgainstGeneral");
			}
		}
		return msg;	
	}
	
	/**
	 * 验证是否与存在的其它代理冲突
	 * @param agentSetting
	 * @throws Exception
	 */
	public String  validateSettingComplictAgainstAll(AgentSetting agentSetting) throws Exception{
		String msg = "";
		Long authorId = agentSetting.getAuthid();
		if(authorId.longValue()==0L){
			authorId = ContextUtil.getCurrentUserId();
		}
		Date startDate =agentSetting.getStartdate();
		Date endDate = agentSetting.getEnddate();
		Long id = agentSetting.getId();
		if(id!=0L){
			boolean flag = agentSettingService.validateSettingComplictAgainstAll(startDate, endDate, authorId,id);
			if(flag){
				//此时间段内与已有的有效代理冲突
				msg =  getText("controller.agentSetting.validateSettingComplictAgainstAll");
		
			}
		}else{
			boolean flag = agentSettingService.validateSettingComplictAgainstAll(startDate, endDate, authorId);
			if(flag){
				msg =   getText("controller.agentSetting.validateSettingComplictAgainstAll");
			}
		}
		return msg;		
	}

	/**
	 * 取得 AgentSetting 实体
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected AgentSetting getFormObject(HttpServletRequest request)
			throws Exception {

		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher((new String[] { "yyyy-MM-dd" })));

		String json = RequestUtil.getString(request, "json");

		AgentSetting agentSetting = JSONObject.parseObject(json,
				AgentSetting.class);
		if (AgentSetting.AUTHTYPE_GENERAL == agentSetting.getAuthtype()) {
			agentSetting.getAgentDefList().clear();
			agentSetting.getAgentConditionList().clear();
			agentSetting.setFlowkey(null);
			agentSetting.setFlowname(null);
		} else if (AgentSetting.AUTHTYPE_PARTIAL == agentSetting.getAuthtype()) {
			agentSetting.getAgentConditionList().clear();
			agentSetting.setFlowkey(null);
			agentSetting.setFlowname(null);
		} else if (AgentSetting.AUTHTYPE_CONDITION == agentSetting
				.getAuthtype()) {
			agentSetting.getAgentDefList().clear();
			agentSetting.setAgentid(null);
			agentSetting.setAgent(null);
		} else {
			throw new Exception(
					getText("controller.agentSetting.getFormObject.invalidAgent"));
		}

		return agentSetting;
	}

	/**
	 * 更新流程代理状态
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("updStatus")
	@Action(description = "更新流程代理状态", detail = "设置流程代理${SysAuditLinkService.getAgentSettingLink(Long.valueOf(id))} 为 "
			+ "<#if AgentSetting.ENABLED_YES.equals(Long.valueOf(status))>启用状态"
			+ "<#else>禁用状态" + "</#if>")
	@ResponseBody
	public void updStatus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		short status = RequestUtil.getShort(request, "status", (short)-1);
		try {
			if (id == 0L) {
				this.returnCallbackErrorData(request, response,
						"controller.agentSetting.updStatus.invalidAgent");
				return;
			} else if (status == -1) {
				this.returnCallbackErrorData(request, response,
						"controller.agentSetting.updStatus.invalidState");
				return;
			} else {
				AgentSetting agentSetting = agentSettingService.getById(id);
				agentSetting.setEnabled(status);
				String msg = customValidate(agentSetting);
				 if(StringUtil.isNotEmpty(msg)){
						this.returnCallbackErrorData(request, response,
								msg);
						return;
				 }
					 
				agentSettingService.update(agentSetting);
				if (AgentSetting.ENABLED_NO.equals(status)) {
					this.returnCallbackSuccessData(request, response,
							"controller.agentSetting.updStatus.disabled");
					return;
				} else {
					this.returnCallbackSuccessData(request, response,
							"controller.agentSetting.updStatus.enabled");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.returnCallbackSuccessData(request, response,
					"controller.agentSetting.updStatus.fail");
			return;
		}

	}

	/**
	 * 编辑代理设定
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑代理设定")
	@ResponseBody
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		AgentSetting agentSetting = agentSettingService.getById(id);
		if (agentSetting != null) {
			if (agentSetting.getAuthtype() == AgentSetting.AUTHTYPE_CONDITION) {
				// 不支持
			} else {
				List<AgentDef> agentDefList = agentSettingService
						.getAgentDefList(id);
				agentSetting.setAgentDefList(agentDefList);
			}
		}
		else{
			agentSetting = new AgentSetting();
			agentSetting.setEnabled((short)1);
			agentSetting.setAuthtype((short)0);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("agentSetting", agentSetting);
		map.put("isAdmin", RequestUtil.getBoolean(request, "isAdmin", false));
		this.returnCallbackSuccessData(request, response, map);
	}
	
	/**
	 * 删除代理设定
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除代理设定",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除流程代理设定" +
					"<#list StringUtils.split(id, \",\") as item>" +
					" ${SysAuditLinkService.getAgentSettingLink(Long.valueOf(item))}," +
					"</#list>"
			)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			agentSettingService.delByIds(lAryId);
			this.returnCallbackSuccessData(request, response,
					"");
		}catch(Exception ex){
			this.returnCallbackErrorData(request, response,
					ex.getMessage());
		}
	
	}
	

	
	
	

}
