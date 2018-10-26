package com.hotent.platform.service.system;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;
import com.hotent.platform.model.system.SysDataSource;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.scheduler.SchedulerService;
import com.hotent.platform.model.bpm.AgentSetting;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmFormQuery;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.form.BpmDataTemplate;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormDialog;
import com.hotent.platform.model.form.BpmFormRule;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.BpmFormTemplate;
import com.hotent.platform.model.mail.OutMailUserSeting;
import com.hotent.platform.model.system.ConditionScript;
import com.hotent.platform.model.system.Demension;
import com.hotent.platform.model.system.DesktopColumn;
import com.hotent.platform.model.system.DesktopLayout;
import com.hotent.platform.model.system.DesktopMycolumn;
import com.hotent.platform.model.system.Dictionary;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.Identity;
import com.hotent.platform.model.system.MessageLog;
import com.hotent.platform.model.system.MessageReceiver;
import com.hotent.platform.model.system.MessageSend;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.ReportTemplate;
import com.hotent.platform.model.system.Resources;
import com.hotent.platform.model.system.Script;
import com.hotent.platform.model.system.Seal;
import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.SysAcceptIp;
import com.hotent.platform.model.system.SysCodeTemplate;
import com.hotent.platform.model.system.SysDataSourceL;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.model.system.SysOfficeTemplate;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysParam;
import com.hotent.platform.model.system.SysPaur;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.model.system.SysTypeKey;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SysUserOrg;
import com.hotent.platform.model.system.SysWsLog;
import com.hotent.platform.model.worktime.OverTime;
import com.hotent.platform.model.worktime.SysCalendar;
import com.hotent.platform.model.worktime.Vacation;
import com.hotent.platform.model.worktime.WorkTimeSetting;
import com.hotent.platform.service.bpm.AgentSettingService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmFormQueryService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.form.BpmDataTemplateService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormDialogService;
import com.hotent.platform.service.form.BpmFormRuleService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.form.BpmFormTemplateService;
import com.hotent.platform.service.mail.OutMailUserSetingService;
import com.hotent.platform.service.worktime.CalendarAssignService;
import com.hotent.platform.service.worktime.OverTimeService;
import com.hotent.platform.service.worktime.SysCalendarService;
import com.hotent.platform.service.worktime.VacationService;
import com.hotent.platform.service.worktime.WorkTimeSettingService;

@Service
public class SysAuditLinkService {
	private Log logger = LogFactory.getLog(SysAuditLinkService.class);
	@Resource
	BpmDefinitionService bpmDefinitionService;
	@Resource
	BpmFormDefService bpmFormDefService;
	@Resource
	SysOrgService sysOrgService;
	@Resource
	GlobalTypeService globalTypeService;
	@Resource
	BpmFormDialogService bpmFormDialogService;
	@Resource
	BpmFormTableService bpmFormTableService;
	@Resource
	SysUserService sysUserService;
	@Resource
	AgentSettingService agentSettingService;
	@Resource
	BpmFormQueryService bpmFormQueryService;
	@Resource
	BpmService bpmService;
	@Resource
	ProcessRunService processRunService;
	@Resource
	SchedulerService schedulerService;
//	@Resource
//	SysDataSourceService sysDataSourceService;
	@Resource
	VacationService vacationService;
	@Resource
	WorkTimeSettingService workTimeSettingService;
	@Resource
	CalendarAssignService calendarAssignService;
	@Resource
	SysCalendarService sysCalendarService;
	@Resource
	OverTimeService overTimeService;
	@Resource
	DesktopColumnService desktopColumnService;
	@Resource
	DesktopLayoutService desktopLayoutService;
	@Resource
	ReportTemplateService reportTemplateService;
	@Resource
	ScriptService scriptService;
	@Resource
	IdentityService identityService;
	@Resource
	SysTemplateService sysTemplateService;
	@Resource
	ConditionScriptService conditionScriptService;
	@Resource
	SysCodeTemplateService sysCodeTemplateService;
	
	@Resource
	SysWsLogService sysWsLogService;
	@Resource
	SysAcceptIpService sysAcceptIpService;
	@Resource
	SubSystemService subSystemService;
	@Resource
	SysPaurService sysPaurService;
	@Resource
	SysTypeKeyService sysTypeKeyService;
	@Resource
	DictionaryService dictionaryService;
	@Resource
	ResourcesService resourcesService;
	@Resource
	SysFileService sysFileService;
	@Resource
	MessageLogService messageLogService;
	@Resource
	SealService sealService;
	@Resource
	SysUserOrgService sysUserOrgService;
	@Resource
	SysParamService sysParamService;
	@Resource
	PositionService positionService;
	@Resource
	DemensionService demensionService;
	@Resource
	MessageReceiverService messageReceiverService;
	@Resource
	MessageSendService messageSendService;
	@Resource
	OutMailUserSetingService outMailUserSetingService;
	@Resource
	SysOfficeTemplateService sysOfficeTemplateService;
	@Resource
	BpmDataTemplateService bpmDataTemplateService;
	@Resource
	BpmFormRuleService bpmFormRuleService;
	@Resource
	BpmFormTemplateService bpmFormTemplateService;
	@Resource
	DesktopMycolumnService desktopMycolumnService;
	//TODO
	
	/**
	 * 根据流程运行ID和和实例名称，取得流程的超连接
	 * @param runId
	 * @param actInstName
	 * @return
	 */
	public String getProcessRunLink(Long runId,String actInstName){
		return "<a href='#'  hrefLink='../../bpm/processRun/processImage.ht?runId="+ runId +"' class='ProcessRunLink' ProcessRunId='"+runId+"'>"+actInstName+"</a>";
	}
//	platform/bpm/processRun/processImage.ht?runId=10000020940003&rand=1376376315993
	
	/**
	 * 根据流程运行ID，取得流程的超连接
	 * @param runId
	 * @return
	 */
	public String getProcessRunLink(Long runId){
		ProcessRun processRun = processRunService.getById(runId);
		return getProcessRunLink(processRun);
	}
	
	/**
	 * 根据流程实例对象，取得流程的超连接
	 * @param processRun
	 * @return
	 */
	public String getProcessRunLink(ProcessRun processRun){
		return getProcessRunLink(processRun.getRunId(), processRun.getSubject());
	}
	
	/**
	 * 根据流程任务ID，取得流程的超连接
	 * @param taskId
	 * @return
	 */
	public String getProcessRunLink(String taskId){
		ProcessRun processRun = processRunService.getByTaskId(taskId);
		return getProcessRunLink(processRun);
	}
	
	public String getProcessRunDetailLink(String taskId){
		ProcessRun processRun = processRunService.getByTaskId(taskId);
		return "<a href='#'  hrefLink='../../bpm/processRun/get.ht?runId="+ processRun.getRunId() +"' class='ProcessRunLink' ProcessRunId='"+ processRun.getRunId()+"'>"+processRun.getSubject()+"</a>";
	}
	
	/**
	 * 根据流程定义ID，取得流程定义的超连接
	 * @param defId
	 * @return
	 */
	public  String getBpmDefinitionLink(Long defId){
		BpmDefinition def = bpmDefinitionService.getById(defId);
		return getBpmDefinitionLink(def);
	}
	/**
	 * 根据流程Act定义ID，取得流程定义的超连接
	 * @param actDefId
	 * @return
	 */
	public  String getBpmDefinitionLink(String actDefId){
		BpmDefinition def = bpmDefinitionService.getByActDefId(actDefId);
		return getBpmDefinitionLink(def);
	}
	
	/**
	 * 根据流程Act定义ID，取得流程定义的超连接
	 * @param actDefId
	 * @return
	 */
	public  String getBpmDefinitionLinkByKey(String actDefKey){
		BpmDefinition def = bpmDefinitionService.getMainByDefKey(actDefKey);
		return getBpmDefinitionLink(def);
	}
	
	/**
	 * 根据流程定义，取得流程定义的超连接
	 * @param bpmDefinition
	 * @return
	 */
	public  String getBpmDefinitionLink(BpmDefinition bpmDefinition){
		return getBpmDefinitionLink(bpmDefinition.getDefId(),bpmDefinition.getSubject());
	}
	/**
	 * 根据流程定义ID和定义名，取得流程定义的超连接
	 * @param defId
	 * @param actDefName
	 * @return
	 */
	public  String getBpmDefinitionLink(Long defId,String actDefName){
		return "<a href='#'  hrefLink='../../bpm/bpmDefinition/design.ht?defId=" + defId + "' class='BpmDefinitionLink' BpmDefinitionDefId='"+defId+"'>"+actDefName+"</a>";
	}
	
	
	/**
	 * 根据组织ID，取得组织的超连接
	 * @param orgId
	 * @return
	 */
	public  String getSysOrgLink(Long orgId){
		SysOrg sysOrg = sysOrgService.getById(orgId);
		return getSysOrgLink(sysOrg);
		
	}
	
	/**
	 * 根据组织，取得组织的超连接
	 * @param sysOrg
	 * @return
	 */
	public  String getSysOrgLink(SysOrg sysOrg){
		return getSysOrgLink(sysOrg.getOrgId(),sysOrg.getOrgName());
	}
	
	
	/**
	 * 根据角色，取得角色的超连接
	 * @param sysOrg
	 * @return
	 */
	public  String getSysRoleLink(SysRole sysRole){
		return "<a  href='#'  hrefLink='../../system/sysRole/getByRoleId.ht?roleId="+sysRole.getRoleId()+"' class='SysRoleLink' SysRoleId='"+sysRole.getRoleId()+"'>"+sysRole.getRoleName()+"</a>";
	}
	
	
	/**
	 * 根据组织ID、组织名称，取得组织的超连接
	 * @param orgId
	 * @param orgName
	 * @return
	 */
	public  String getSysOrgLink(Long orgId,String orgName){
		return "<a href='#'  hrefLink='../../system/sysOrg/getByLink.ht?orgId="+orgId+"' class='SysOrgLink' SysOrgId='"+orgId+"'>"+orgName+"</a>";
	}
	
	/**
	 * 根据表单定义ID，表单标题，取得表单超连接
	 * @param formDefId
	 * @param formDefDesc
	 * @return
	 */
	public  String getBpmFormDefLink(Long formDefId,String formDefName){
		return "<a href='#'  hrefLink='../../form/bpmFormDef/get.ht?formDefId="+formDefId+"' class='BpmFormDefLink' BpmFormDefId='"+formDefId+"'>"+formDefName+"</a>";
	}
	/**
	 * 根据表单定义，取得表单超连接
	 * @param def
	 * @return
	 */
	public  String getBpmFormDefLink(BpmFormDef def) {
		return getBpmFormDefLink(def.getFormDefId(),def.getSubject());
	}
	
	/**
	 * 根据表单定义ID，取得表单超连接
	 * @param formDefId
	 * @return
	 */
	public  String getBpmFormDefLink(Long formDefId) {
		BpmFormDef def = bpmFormDefService.getById(formDefId);
		return getBpmFormDefLink(def);
	}
	public  String getBpmFormDefLink(String formKey) {
		BpmFormDef def = bpmFormDefService.getDefaultVersionByFormKey(formKey);
		return getBpmFormDefLink(def);
	}
	
	
	
	public String getGlobalTypeLink(Long typeId){
		GlobalType type = globalTypeService.getById(typeId);
		return getGlobalTypeLink(type);
	}
	
	public String getGlobalTypeLink(GlobalType type){
		return getGlobalTypeLink(type.getTypeId(),type.getTypeName());
	}
	
	
	public String getGlobalTypeLink(Long typeId,String typeName){
		return "<a href='#'  hrefLink='../../system/globalType/get.ht?typeId="+typeId+"' class='GlobalTypeLink' GlobalTypeId='"+typeId+"'>"+typeName+"</a>";
	}
	
	public String getBpmFormDialogLink(Long id,String name){
		return "<a href='#'  hrefLink='../../form/bpmFormDialog/get.ht?id="+id+"' class='BpmFormDialogLink' BpmFormDialogId='"+id+"'>"+name+"</a>";
	}
	public String getBpmFormDialogLink(Long dialogId){
		BpmFormDialog dialog = bpmFormDialogService.getById(dialogId);
		return getBpmFormDialogLink(dialog);
	}
	public String getBpmFormDialogLink(BpmFormDialog dialog) {
		return getBpmFormDialogLink(dialog.getId(),dialog.getName());
	}
	
	/*
	public String getBpmFormTableDesc(Long tableId){
		BpmFormTable bpmFormTable = bpmFormTableService.getByTableId(tableId);
		return bpmFormTable.getTableDesc();
	}
	*/
	/**
	 * 获取业务数据模板的超链接
	 * @param tempId
	 * @return
	 */
	public  String getBpmDataTemplateLink(Long tempId){
		BpmDataTemplate bpmDataTemplate=bpmDataTemplateService.getById(tempId);
		return "<a href='#' hrefLink='../../form/bpmDataTemplate/detailData.ht?__displayId__=" + tempId + "&__pk__="+bpmDataTemplate.getFormKey()+"' class='BpmDataTemplateLink' BpmDataTemplateId='"+tempId
				+"'>【"+bpmDataTemplate.getName()+"】"+"</a>";
	}
	
	/**
	 * 获取通用表单查询的超链接
	 * @param queryId
	 * @return
	 */
	public String getBpmFormQueryLink(Long queryId){
		BpmFormQuery bpmFormQuery=bpmFormQueryService.getById(queryId);
		return "<a href='#' hrefLink='../../bpm/bpmFormQuery/edit.ht?id=" + queryId+ "' class='BpmFormTableLink' BpmFormTableId='"+queryId
				+"'>"+bpmFormQuery.getName()+"</a>";
	}
	
	/**
	 * 获取自定义表的超链接
	 * @param tableId
	 * @return
	 */
	public String getBpmFormTableLink(Long tableId){
		BpmFormTable bpmFormTable = bpmFormTableService.getTableById(tableId);
//		return bpmFormTable.getTableDesc()+"["+bpmFormTable.getTableName()+"]";
		return "<a href='#' hrefLink='../../form/bpmFormTable/getOfLink.ht?tableId=" + tableId + "' class='BpmFormTableLink' BpmFormTableId='"+tableId
				+"'>"+bpmFormTable.getTableDesc()+"【"+bpmFormTable.getTableName()+"】"+"</a>";
	}
	
	/**
	 * 获取自定义验证规则的超链接
	 * @param ruleId
	 * @return
	 */
	public String getBpmFormRuleLink(long ruleId){
		BpmFormRule bpmFormRule=bpmFormRuleService.getById(ruleId);
		return "<a href='#' hrefLink='../../form/bpmFormRule/get.ht?id=" + ruleId + "' class='BpmFormRuleLink' BpmFormRuleId='"+ruleId
				+"'>"+bpmFormRule.getName()+"</a>";
	}
	
	/**
	 * 获取自定义表单模板的超链接
	 * @param tempId
	 * @return
	 */
	public String getBpmFormTemplateLink(long tempId){
		BpmFormTemplate bpmFormTemplate=bpmFormTemplateService.getById(tempId);
		return "<a href='#' hrefLink='../../form/bpmFormTemplate/get.ht?templateId=" + tempId + "' class='BpmFormTemplateLink' BpmFormTemplateId='"+tempId
				+"'>"+bpmFormTemplate.getTemplateName()+"</a>";
	}
	
	
	public String getSysUserLink(long id,String name){
		return "<a href='#' hrefLink='../../system/sysUser/getByUserId.ht?userId="+ id +"' class='SysUserLink' SysUserId='"+id+"'>"+name+"</a>";
	}
	
	public String getSysUserLink(long id){
		SysUser sysUser = sysUserService.getById(id);
		return getSysUserLink(sysUser);
	}
	
	public String getSysUserLink(String account){
		SysUser sysUser = sysUserService.getByAccount(account);
		return getSysUserLink(sysUser);
	}
	
	public String getSysUserLink(SysUser sysUser){
		return  getSysUserLink(sysUser.getUserId(),sysUser.getFullname());
	}
	
	
	public String getAgentSettingLink(Long id){
		AgentSetting agentSetting = agentSettingService.getById(id);
		return getAgentSettingLink(agentSetting);
	}
	public String getAgentSettingLink(AgentSetting agentSetting){
		String agentType = "";
		if(AgentSetting.AUTHTYPE_GENERAL==agentSetting.getAuthtype().intValue()){
			agentType = "全权代理";
		}else if(AgentSetting.AUTHTYPE_PARTIAL==agentSetting.getAuthtype().intValue()){
			agentType = "部分代理";
		}else if(AgentSetting.AUTHTYPE_CONDITION==agentSetting.getAuthtype().intValue()){
			agentType = "条件代理";
		}
		String link = "【" +
				"授权人："+getSysUserLink(agentSetting.getAuthid(), agentSetting.getAuthname())+
				" ; 代理类型："+agentType+
				" ; 代理人："+getSysUserLink(agentSetting.getAgentid(), agentSetting.getAgent())+
				"】";
		return link;
	}
	
	
	
	public String getNodeName(String actDefId,String nodeId){
		FlowNode  flowNode = NodeCache.getNodeByActNodeId(actDefId, nodeId);
		return flowNode.getNodeName();
	}
	
	public String getNodeName(Long defId,String nodeId){
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		return getNodeName(bpmDefinition.getActDefId(), nodeId);
		
	}
	

	
	public Trigger getTrigger(String triggerName){
		try {
			return schedulerService.getTrigger(triggerName);
		} catch (SchedulerException e) {
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 根据id获取数据源的超链接
	 * @param sourceId
	 * @return
	 */
	public String  getSysDataSourceLink(long sourceId) {
//		SysDataSource source=sysDataSourceService.getById(sourceId);
//		return getSysDataSourceLink(source);
		return null;
	}
	
	private String getSysDataSourceLink(SysDataSourceL source) {
		//return getSysDataSourceLink(source.getId(), source.getName());
		return null;
	}

	public String  getSysDataSourceLink(long sourceId,String sourceName ) {
		//TODO
		return "<a  href='#' hrefLink='../../system/sysDataSource/edit.ht?id="+sourceId+"' class='SysDataSourceLink' SysDataSourceId='"+sourceId+"' >"+sourceName+"</a>";
	}
	
	
	/**
	 * 根据vaid，查找法定假期的连接
	 * @param vaId
	 * @return
	 */
	public String  getVacationLink(long vaId) {
		Vacation vacation=vacationService.getById(vaId);
		return getVacationLink(vacation);
	}

	private String getVacationLink(Vacation vacation) {
		return getVacationLink(vacation.getId(),vacation.getName());
	}

	private String getVacationLink(Long vaId, String vaName) {
		return "<a href='#' hrefLink='../../worktime/vacation/get.ht?id="+vaId+"' class='VacationLink' VacationId='"+vaId+"' >"+vaName+"</a>";
	}
	
	/**
	 * 根据wordId，获取班次设置的超链接
	 * @param workId
	 * @return
	 */
	public String getWorkTimeSettingLink(long workId) {
		WorkTimeSetting workTimeSetting=workTimeSettingService.getById(workId);
		return  getWorkTimeSettingLink(workTimeSetting);
	}
	private String getWorkTimeSettingLink(WorkTimeSetting workTimeSetting) {
		return getWorkTimeSettingLink(workTimeSetting.getId(),workTimeSetting.getName());
	}
	private String getWorkTimeSettingLink(Long workId, String workName) {
		return "<a href='#' hrefLink='../../worktime/workTimeSetting/get.ht?id="+workId+"' class='WorkTimeSettingLink' WorkTimeSettingId='"+workId+"' >"+workName+"</a>";
	}
	
	/**
	 * 根据syscaId ,获取日历的超链接
	 * @param syscaId
	 * @return
	 */
	public String getSysCalendarLink(long syscaId) {
		SysCalendar sysCalendar=sysCalendarService.getById(syscaId);
		return getSysCalendarLink(sysCalendar.getId(),sysCalendar.getName());
	}
	
	private String getSysCalendarLink(Long syscaId, String syscaName) {
		return "<a href='#' hrefLink='../../worktime/sysCalendar/get.ht?id="+syscaId+"' class='SysCalendarLink' SysCalendarId='"+syscaId+"' >"+syscaName+"</a>";
	}


	
	public String getOverTimeLink(long otId){
		OverTime overTime=overTimeService.getById(otId);
		return "<a href='#' hrefLink='../../worktime/overTime/get.ht?id="+overTime.getId()+"' class='OverTimeLink' OverTimeId='"+overTime.getId()+"' >"+overTime.getSubject()+"</a>";
	}
	
	/**
	 * 根据desId，获取桌面栏目的超链接
	 * @param desId
	 * @return
	 */
	public String  getDesktopColumnLink(long desId) {
		DesktopColumn desktopColumn=desktopColumnService.getById(desId);
		return "<a href='#' hrefLink='../../system/desktopColumn/get.ht?id="+desktopColumn.getId()+"' class='DesktopColumnLink' DesktopColumnId='"+desktopColumn.getId()+"' >"+desktopColumn.getColumnName()+"</a>";
		
	}
	
	/**
	 * 根据deskId，获取桌面布局的超链接
	 * @param deskId
	 * @return
	 */
	public String getDesktopLayoutLink(long deskId) {
		DesktopLayout desktopLayout=desktopLayoutService.getById(deskId);
		return "<a href='#' hrefLink='../../system/desktopLayout/get.ht?id="+desktopLayout.getId()+"' class='DesktopLayoutLink' DesktopLayoutId='"+desktopLayout.getId()+"' >"+desktopLayout.getName()+"</a>";
		
	}
	
	/**
	 * 根据reportId，获取报表的超链接
	 * @param reportId
	 * @return
	 */
	public String getReportTemplateLink(long reportId) {
		ReportTemplate reportTemplate=reportTemplateService.getById(reportId);
		return "<a href='#' hrefLink='../../system/reportTemplate/get.ht?reportId="+reportTemplate.getReportId()+"' class='ReportTemplateLink' ReportTemplateId='"+reportTemplate.getReportId()+"' >"+reportTemplate.getTitle()+"</a>";
	}
	
	/**
	 * 根据scrId，获取脚本的超链接
	 * @param scrId
	 * @return
	 */
	public String getScriptLink(long scrId) {
		Script script=scriptService.getById(scrId);
		return "<a href='#' hrefLink='../../system/script/get.ht?id="+script.getId()+"' class='ScriptLink' ScriptId='"+script.getId()+"' >"+script.getName()+"</a>";
	}
	
	/**
	 * 根据indId，获取流水号的超链接
	 * @param indId
	 * @return
	 */
	public String getIdentityLink(long indId){
		Identity identity=identityService.getById(indId);
		return "<a href='#' hrefLink='../../system/identity/get.ht?id="+identity.getId()+"' class='IdentityLink' IdentityId='"+identity.getId()+"' >"+identity.getName()+"</a>";
	}
	
	/**
	 * 根据temId，获取模板的超链接
	 * @param temId
	 * @return
	 */
	public String getSysTemplateLink(long temId){
		SysTemplate sysTemplate=sysTemplateService.getById(temId);
		return "<a href='#' hrefLink='../../system/sysTemplate/get.ht?templateId="+sysTemplate.getTemplateId()+"' class='SysTemplateLink' SysTemplateId='"+sysTemplate.getTemplateId()+"' >"+sysTemplate.getName()+"</a>";
	}
	
	/**
	 * 根据conId ，获取条件脚本的超链接
	 * @param conId
	 * @return
	 */
	public String getConditionScriptLink(long conId) {
		ConditionScript conditionScript=conditionScriptService.getById(conId);
		return "<a href='#' hrefLink='../../system/conditionScript/edit.ht?id="+conditionScript.getId()+"' class='ConditionScriptLink' ConditionScriptId='"+conditionScript.getId()+"' >"+conditionScript.getMethodName()+"</a>";
	}
	
	/**
	 * 根据codetemId，获取自定义模板的超链接
	 * @param codetemId
	 * @return
	 */
	public String getSysCodeTemplateLink(long codetemId){
		SysCodeTemplate sysCodeTemplate=sysCodeTemplateService.getById(codetemId);
		return "<a href='#' hrefLink='../../system/sysCodeTemplate/get.ht?id="+sysCodeTemplate.getId()+"' class='SysCodeTemplateLink' SysCodeTemplateId='"+sysCodeTemplate.getId()+"' >"+sysCodeTemplate.getTemplateName()+"</a>";
	}
	
	
	
	/**
	 * 根据wsId，获取控制器的超链接
	 * @param wsId
	 * @return
	 */
	public String getSysWsLogLink(long wsId){
		SysWsLog sysWsLog=sysWsLogService.getById(wsId);
		return "<a href='#' hrefLink='../../system/sysWsLog/get.ht?id="+sysWsLog.getLogId()+"' class='SysWsLogLink' SysWsLogId='"+sysWsLog.getLogId()+"' >"+sysWsLog.getClsName()+"</a>";
	}
	
	/**
	 * 根据accId，获取访问地址的超链接
	 * @param wsId
	 * @return
	 */
	public String getSysAcceptIpLink(long accId){
		 SysAcceptIp sysAcceptIp=sysAcceptIpService.getById(accId);
		return "<a href='#' hrefLink='../../system/sysAcceptIp/get.ht?acceptId="+sysAcceptIp.getAcceptId()+"' class='SysAcceptIpLink' SysAcceptIpId='"+sysAcceptIp.getAcceptId()+"' >"+sysAcceptIp.getTitle()+"</a>";
	}
	
	/**
	 * 根据accId，获取子系统的超链接
	 * @param wsId
	 * @return
	 */
	public String getSubSystemLink(long subId){
		SubSystem subSystem=subSystemService.getById(subId);
		return "<a href='#' hrefLink='../../system/subSystem/get.ht?id="+subId+"' class='SubSystemLink' SubSystemId='"+subId+"' >"+subSystem.getSysName()+"</a>";
	}
	
	/**
	 * 根据accId，获取子系统的超链接
	 * @param wsId
	 * @return
	 */
	public String getSysPaurLink(long paurId){
		SysPaur sysPaur=sysPaurService.getById(paurId);
		return "<a href='#' hrefLink='../../system/sysPaur/get.ht?paurid="+paurId+"' class='SysPaurLink' SysPaurId='"+paurId+"' >"+sysPaur.getPaurname()+"</a>";
	}
	
	/**
	 * 根据typeId，获取分类标识的超链接
	 * @param typeId
	 * @return
	 */
	public String getSysTypeKeyLink(long typeId){
		SysTypeKey sysTypeKey=sysTypeKeyService.getById(typeId);
		return "<a href='#' hrefLink='../../system/sysTypeKey/get.ht?typeId="+typeId+"' class='SysTypeKeyLink' SysTypeKeyId='"+typeId+"' >"+sysTypeKey.getTypeName()+"</a>";
	}
	
	/**
	 * 根据dictId，获取数据字典的超链接
	 * @param dictId
	 * @return
	 */
	public String getDictionaryLink(long dictId){
		Dictionary dictionary=dictionaryService.getById(dictId);
		return "<a href='#' hrefLink='../../system/dictionary/edit.ht?dicId="+dictId+"' class='DictionaryLink' DictionaryId='"+dictId+"' >"+dictionary.getItemName()+"</a>";
	}
	
	/**
	 * 根据gtypeId，获取系统分类的超链接
	 * @param gtypeId
	 * @return
	 *//*
	public String  getGlobalTypeLink(long gtypeId) {
		GlobalType globalType=globalTypeService.getById(gtypeId);
		return "<a href='#' hrefLink='../../system/globalType/get.ht?typeId="+gtypeId+"' class='GlobalTypeLink' GlobalTypeId='"+gtypeId+"' >"+globalType.getTypeName()+"</a>";
	}
	*/
	/**
	 * 获取子系统资源的超链接
	 * @param resId
	 * @return
	 */
	public String getResourcesLink(long resId){
		Resources resources=resourcesService.getById(resId);
		return "<a href='#' hrefLink='../../system/resources/get.ht?resId="+resId+"' class='ResourcesLink' ResourcesId='"+resId+"' >"+resources.getResName()+"</a>";
	}
	
	/**
	 * 获取附件的超链接
	 * @param fileId
	 * @return
	 */
	public String getSysFileLink(long fileId){
		SysFile sysFile=sysFileService.getById(fileId);
		return "<a href='#' hrefLink='../../system/sysFile/get.ht?fileId="+fileId+"' class='SysFileLink' SysFileId='"+fileId+"' >"+sysFile.getFileName()+"</a>";
	}
	
	
	/**
	 * 获取消息日志的超链接
	 * @param mesId
	 * @return
	 */
	public String getMessageLogLink(long mesId){
		MessageLog messageLog=messageLogService.getById(mesId);
		return "<a href='#' hrefLink='../../system/messageLog/get.ht?id="+mesId+"' class='MessageLogLink' MessageLogId='"+mesId+"' >"+messageLog.getSubject()+"</a>";
	} 
	
	/**
	 * 获取电子印章的超链接
	 * @param sealId
	 * @return
	 */
	public String getSealLink(long sealId){
		Seal seal=sealService.getById(sealId);
		return "<a href='#' hrefLink='../../system/seal/get.ht?sealId="+sealId+"' class='SealLink' SealId='"+sealId+"' >"+seal.getSealName()+"</a>";
	}
	
	/**
	 * 向组织添加人员
	 * @param orgId
	 * @param userIds
	 * @return
	 */
	public String  getOrgUserName(long orgId , String userIds) {
		SysOrg sysOrg=sysOrgService.getById(orgId);
		String[] userids=userIds.split(",");
		String userName="";
		for (int i = 0; i < userids.length; i++) {
			SysUser sysUser=sysUserService.getById(Long.valueOf(userids[i]));
			userName+=sysUser.getFullname()+",";
		}
		return "向组织【"+sysOrg.getOrgName()+"】添加人员【"+userName+"】";
	}
	/**
	 * 
	 * @param userPosId
	 * @return
	 */
	public SysUserOrg getByUserPosId(long userPosId){
		SysUserOrg sysUserOrg=sysUserOrgService.getById(userPosId);
		SysOrg sysOrg=sysOrgService.getById(sysUserOrg.getOrgId());
		SysUser sysUser=sysUserService.getById(sysUserOrg.getUserId());
		sysUserOrg.setOrgName(sysOrg.getOrgName());
		sysUserOrg.setUserName(sysUser.getFullname());
		return sysUserOrg;
	}
	
	/**
	 * 获取系统参数的超链接
	 * @param parId
	 * @return
	 */
	public String getSysParamLink(long parId){
		SysParam sysParam = sysParamService.getById(parId);
		return "<a href='#' hrefLink='../../system/sysParam/get.ht?paramId="+parId+"' class='SysParamLink' SysParamId='"+parId+"' >"+sysParam.getParamName()+"</a>";
	}
	
	
	/**
	 * 获取SYS_DEMENSION的超链接
	 * @param demId
	 * @return
	 */
	public String getDemensionLink(long demId) {
		Demension demension=demensionService.getById(demId);
		return "<a href='#' hrefLink='../../system/demension/get.ht?demId="+demId+"' class='DemensionLink' DemensionId='"+demId+"' >"+demension.getDemName()+"</a>";
	}
	
	public String getPositionLink(long posId){
		Position position=positionService.getById(posId);
		return "<a href='#' hrefLink='../../system/position/getByPosId.ht?posId="+posId+"' class='PositionLink' PositionId='"+posId+"' >"+position.getPosName()+"</a>";
	}
	
	/**
	 * 获取消息接收者的超链接
	 * @param mereId
	 * @return
	 */
	public String getMessageReceiverLink(long mereId){
		MessageReceiver messageReceiver=messageReceiverService.getById(mereId);
		return "<a href='#' hrefLink='../../system/messageReceiver/get.ht?id="+mereId+"' class='MessageReceiverLink' MessageReceiverId='"+mereId+"' >"+messageReceiver.getReceiver()+"</a>";
	}
	/**
	 * 获取消息发送的超链接
	 * @param mesendId
	 * @return
	 */
	public String getMessageSendLink(long mesendId){
		MessageSend messageSend =messageSendService.getById(mesendId);
		return "<a href='#' hrefLink='../../system/messageSend/get.ht?id="+mesendId+"' class='MessageSendLink' MessageSendId='"+mesendId+"' >"+messageSend.getSubject()+"</a>";
	}
	
	public String getOutMailUserSetingLink(long outsetId){
		OutMailUserSeting outMailUserSeting = outMailUserSetingService.getById(outsetId);
		return "<a href='#' hrefLink='../../mail/outMailUserSeting/get.ht?id="+outsetId+"' class='OutMailUserSetingLink' OutMailUserSetingId='"+outsetId+"' >"+outMailUserSeting.getUserName()+"</a>";
	}
	
	
	/**
	 * 获取Office模板的超链接
	 * @param tempId
	 * @return
	 */
	public String getsysOfficeTemplateLink(long tempId){
		SysOfficeTemplate sysOfficeTemplate=sysOfficeTemplateService.getById(tempId);
		return "<a href='#' hrefLink='../../system/sysOfficeTemplate/get.ht?id="+tempId+"' class='SysOfficeTemplateLink' SysOfficeTemplateId='"+tempId+"' >"+sysOfficeTemplate.getSubject()+"</a>";
	}
	
	/**
	 * 获取桌面个人栏目的超链接
	 * @param deskId
	 * @return
	 */
	public String getDesktopMycolumnLink(long deskId){
		DesktopMycolumn desktopMycolumn=desktopMycolumnService.getById(deskId);
		return "<a href='#' hrefLink='../../system/desktopMycolumn/get.ht?id="+deskId+"' class='DesktopMycolumnLink' DesktopMycolumnId='"+deskId+"' >"+desktopMycolumn.getColumnName()+"</a>";
	}
	
}
