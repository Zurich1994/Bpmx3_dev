package com.hotent.platform.service.form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.db.JdbcDao;
import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.DialectUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.TimeUtil;
import com.hotent.mobile.model.form.PlatformType;
import com.hotent.platform.dao.form.BpmFormFieldDao;
import com.hotent.platform.dao.form.BpmFormHandlerDao;
import com.hotent.platform.dao.form.BpmFormTableDao;
import com.hotent.platform.dao.system.PositionDao;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.form.BpmFormData;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.PkValue;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysBusEvent;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SysUserOrg;
import com.hotent.platform.model.util.FieldPool;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.TaskOpinionService;
import com.hotent.platform.service.system.IdentityService;
import com.hotent.platform.service.system.SysBusEventService;
import com.hotent.platform.service.system.SysUserOrgService;

import freemarker.template.TemplateException;

/**
 * 对象功能:自定义表单数据处理Service类 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:xwy 
 * 创建时间:2011-12-22 11:07:56
 */
@Service
public class BpmFormHandlerService {
	protected Logger logger = LoggerFactory.getLogger(BpmFormHandlerService.class);
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private BpmFormHandlerDao dao;
	@Resource
	private IdentityService identityService;
	@Resource
	private BpmFormRightsService bpmFormRightsService;
	@Resource
	private BpmFormControlService bpmFormControlService;
	
	@Resource
	private TaskOpinionService taskOpinionService;
	@Resource
	private FreemarkEngine freemarkEngine;
	@Resource
	private BpmFormFieldDao bpmFormFieldDao; 
	@Resource
	private BpmFormTableDao bpmFormTableDao;
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private JdbcDao jdbcDao;
//	@Resource
//	private SysDataSourceService sysDataSourceService;
	
	@Resource	
	private PositionDao positionDao;
	@Resource
	private SysUserDao sysUserDao;
	@Resource 
	private SysUserOrgService sysUserOrgService;

	@Resource
	private BpmFormTableService bpmFormTableService;
	
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private SysBusEventService sysBusEventService;
	@Resource
	private GroovyScriptEngine groovyScriptEngine;
	@Resource
	private TaskService taskService;
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	@Resource
	private BpmService bpmService;
	//流程实例分隔符
	private String INSTANCEID_SPLITOR="#instanceId#";
	//流程示意图替换符
	private String FLOW_CHART_SPLITOR = "(?s)<div[^>]*?\\s+name=\"editable-input\"\\s+class=\"flowchart\">(.*?)</div>";
	private String FLOW_CHART_SPLITOR_IE = "(?s)<div[^>]*?\\s+class=\"flowchart\"\\s+name=\"editable-input\">(.*?)</div>";

	

	/**
	 * 根据表单定义，用户ID，主键获取表单的html代码。
	 * 
	 * <pre>
	 * 实现流程：
	 * 1.获取表单的模版。
	 * 2.判断主键是否为空。
	 * 		1.主键为空。
	 * 			实例化BpmFormData数据。
	 * 			1.判断字段值来源是流水号。
	 * 				如果是流水号,则生成流水号。
	 * 			2.判断字段值是来自脚本。
	 * 				通过脚本计算得出字段的值。
	 * 		2.主键不为空。
	 * 			根据主键获取表单的数据。
	 * 
	 * 3.将数据和字段权限给模版引擎解析，生成html。
	 * </pre>
	 * 
	 * @param bpmFormDef 	表单定义对象。
	 * @param processRun 	流程运行实例对象。
	 * @param userId 		用户ID。
	 * @param pkValue  		主键值。
	 * @return
	 * @throws Exception
	 */
//	public String obtainHtml(BpmFormDef bpmFormDef, ProcessRun processRun, Long userId, String nodeId,String ctxPath, String parentActDefId) throws Exception {
//		String pkValue = processRun.getBusinessKey();
//		String instanceId = processRun.getActInstId();
//		String actDefId = processRun.getActDefId();
//		return obtainHtml(bpmFormDef, userId, pkValue, instanceId, actDefId, nodeId, ctxPath, parentActDefId,false);
//
//	}
	
	/**
	 * 根据表单定义，用户ID，主键获取表单的html代码。
	 * 
	 * <pre>
	 * 实现流程：
	 * 1.获取表单的模版。
	 * 2.判断主键是否为空。
	 * 		1.主键为空。
	 * 			实例化BpmFormData数据。
	 * 			1.判断字段值来源是流水号。
	 * 				如果是流水号,则生成流水号。
	 * 			2.判断字段值是来自脚本。
	 * 				通过脚本计算得出字段的值。
	 * 		2.主键不为空。
	 * 			根据主键获取表单的数据。
	 * 
	 * 3.将数据和字段权限给模版引擎解析，生成html。
	 * </pre>
	 * 
	 * @param bpmFormDef 	表单定义对象。
	 * @param processRun 	流程运行实例对象。
	 * @param userId 		用户ID。
	 * @param pkValue  		主键值。
	 * @return
	 * @throws Exception
	 */
//	public String obtainHtml(BpmFormDef bpmFormDef, ProcessRun processRun, Long userId, String nodeId,String ctxPath, String parentActDefId,boolean isCopyFlow) throws Exception {
//		String pkValue = processRun.getBusinessKey();
//		String instanceId = processRun.getActInstId();
//		String actDefId = processRun.getActDefId();
//		return obtainHtml(bpmFormDef, userId, pkValue, instanceId, actDefId, nodeId, ctxPath, parentActDefId,isCopyFlow);
//
//	}

	/**
	 * 根据表单定义，用户ID，主键获取表单的html代码。
	 * 
	 * <pre>
	 * 实现流程：
	 * 1.获取表单的模版。
	 * 2.判断主键是否为空。
	 * 		1.主键为空。
	 * 			实例化BpmFormData数据。
	 * 			1.判断字段值来源是流水号。
	 * 				如果是流水号,则生成流水号。
	 * 			2.判断字段值是来自脚本。
	 * 				通过脚本计算得出字段的值。
	 * 			3.如果是日期控件，或者日期。
	 * 				默认显示时间的话，根据日期格式获取当前日期。
	 * 		2.主键不为空。
	 * 			根据主键获取表单的数据。
	 * 
	 * 3.将数据和字段权限给模版引擎解析，生成html。
	 * </pre>
	 * @param bpmFormDef 自定义表单对象
	 * @param userId     用户Id
	 * @param pkValue    主键值
	 * @param instanceId ACT流程实例ID
	 * @param actDefId   ACT流程定义ID
	 * @param nodeId     
	 * @return
	 * @throws Exception
	 */
	
//	public String getFormDetail(Long formDefId, String businessKey,
//			Long userId,ProcessRun processRun,String ctxPath,boolean isCopyFlow) throws Exception {
		public String getFormDetail(Long formDefId, String businessKey, Long userId, ProcessRun processRun, String ctxPath, boolean isCopyFlow) throws Exception {
		BpmFormDef bpmFormDef=bpmFormDefService.getById(formDefId);
		return getFormDetail(bpmFormDef, businessKey, userId,processRun,ctxPath,isCopyFlow);
	}
	
	/**
	 * 根据表单定义，用户ID，主键获取表单的html代码。
	 * 
	 * <pre>
	 * 实现流程：
	 * 1.获取表单的模版。
	 * 2.判断主键是否为空。
	 * 		1.主键为空。
	 * 			实例化BpmFormData数据。
	 * 			1.判断字段值来源是流水号。
	 * 				如果是流水号,则生成流水号。
	 * 			2.判断字段值是来自脚本。
	 * 				通过脚本计算得出字段的值。
	 * 			3.如果是日期控件，或者日期。
	 * 				默认显示时间的话，根据日期格式获取当前日期。
	 * 		2.主键不为空。
	 * 			根据主键获取表单的数据。
	 * 
	 * 3.将数据和字段权限给模版引擎解析，生成html。
	 * </pre>
	 * @param bpmFormDef 自定义表单对象
	 * @param userId     用户Id
	 * @param pkValue    主键值
	 * @param instanceId ACT流程实例ID
	 * @param actDefId   ACT流程定义ID
	 * @param nodeId     
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
		public String getFormDetail(BpmFormDef bpmFormDef, String businessKey, Long userId, ProcessRun processRun, String ctxPath, boolean isCopyFlow) throws Exception {
//	public String getFormDetail(BpmFormDef bpmFormDef, String businessKey,
	//		Long userId,ProcessRun processRun,String ctxPath,boolean isCopyFlow) throws Exception {
		Long tableId = bpmFormDef.getTableId();
		String tempStr=bpmFormDef.getTemplate();
		String instanceId =  "";
		String actDefId = "";
		if(BeanUtils.isNotEmpty(processRun)){
			 instanceId = processRun.getActInstId();
			 actDefId = processRun.getActDefId();
		}
		
		
		String template =tempStr ;
		BpmFormData data = getBpmFormData(tableId,businessKey,instanceId,actDefId,"",isCopyFlow);
		
		Map<String, Map> model = new HashMap<String, Map>();
		model.put("main", data.getMainFields());
		model.put("opinion", data.getOptions());
		model.put("sub", data.getSubTableMap());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("model", model);
		//兼容之前生成的模版。
		map.put("table", new HashMap<Object, Object>());
		// 传入控制器的service，用于在模版中解析字段。
		map.put("service", bpmFormControlService);
		map.put("permission", bpmFormRightsService.getByFormKey(bpmFormDef));
		map.put("ctx", ctxPath); // 加入上下文路径
		String html = freemarkEngine.parseByStringTemplate(map, template);
		
		//解析子表的默认内容
		html = changeSubShow(html);
		
		String tabTitle = bpmFormDef.getTabTitle();
		if (tabTitle.indexOf(BpmFormDef.PageSplitor ) > -1) {
			html = getTabHtml(tabTitle, html);
		}
		
		if(BeanUtils.isNotEmpty(processRun)){
			// 有分页的情况。
			

			// 流程图解析
			if (instanceId != "") {
				// 替换流程实例分隔符。
				html = html.replace(INSTANCEID_SPLITOR, instanceId);
				String repStr = "<iframe src=\"" + ctxPath + "/platform/bpm/processRun/processImage.ht?actInstId=" + instanceId + "&notShowTopBar=0\" name=\"flowchart\" id=\"flowchart\" marginwidth=\"0\" marginheight=\"0\" frameborder=\"0\" scrolling=\"no\" width=\"100%\"></iframe>";
				html = html.replaceAll(FLOW_CHART_SPLITOR, repStr).replaceAll(FLOW_CHART_SPLITOR_IE, repStr);
			} else if (actDefId != "") {
				String repStr = "<iframe src=\"" + ctxPath + "/platform/bpm/bpmDefinition/flowImg.ht?actDefId=" + actDefId + "\"  name=\"flowchart\" id=\"flowchart\" marginwidth=\"0\" marginheight=\"0\" frameborder=\"0\" scrolling=\"no\" width=\"100%\"></iframe>";
				html = html.replaceAll(FLOW_CHART_SPLITOR, repStr).replaceAll(FLOW_CHART_SPLITOR_IE, repStr);
			}
		}
		
		//子表权限放到页面 （在页面解析的）并虚构子表权限 避免页面解析出错
        BpmFormTable bpmFormTable=bpmFormTableService.getTableById(tableId);
		html += getSubPermission(bpmFormTable,true);
		return html;
	}
	
	/**
	 * 根据表单获取生成html。
	 * @param bpmFormDef		表单定义
	 * @param userId			用户ID
	 * @param pkValue			主键
	 * @param instanceId		流程实例ID
	 * @param actDefId			流程定义ID
	 * @param nodeId			节点ID
	 * @param ctxPath			上下文
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String obtainHtml(BpmFormDef bpmFormDef, Long userId, String pkValue, String instanceId, String actDefId, String nodeId,String ctxPath, String parentActDefId,boolean isCopyFlow) throws Exception {
		Long tableId = bpmFormDef.getTableId();
		String template=bpmFormDef.getTemplate();
		
	//	Long formKey = bpmFormDef.getFormKey();
			String formKey = bpmFormDef.getFormKey();
		//实例化BpmFormData数据
		BpmFormData data = getBpmFormData(tableId,pkValue,instanceId,actDefId,nodeId,isCopyFlow);
		
		//处理业务数据模板的情况。如果actDefId=#dataTem 说明是从业务数据模板调用该方法的，actDefId=#formPrev 则为表单预览的情况需要在这里处理为空 即 actDefId="";
		if("#dataTem".equals(actDefId) || "#formPrev".equals(actDefId)){
			actDefId="";
		}
		Map<String, Map> model = new HashMap<String, Map>();
		model.put("main", data.getMainFields());
		model.put("opinion", data.getOptions());
		model.put("sub", data.getSubTableMap());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("model", model);
		// 传入控制器的service，用于在模版中解析字段。
		map.put("service", bpmFormControlService);
		// 传入字段的权限。
		if(userId>0){
			if(StringUtil.isEmpty(parentActDefId)){
				map.put("permission", bpmFormRightsService.getByFormKeyAndUserId(formKey, userId, actDefId, nodeId,PlatformType.PC));
			}else{//获取子流程中设置表单的权限
				map.put("permission", bpmFormRightsService.getByFormKeyAndUserId(formKey, userId, actDefId, nodeId, parentActDefId,PlatformType.PC));
			}
		}else{
			map.put("permission", bpmFormRightsService.getByFormKey(bpmFormDef));
		}
		map.put("table", new HashMap<String, Object>());
		map.put("ctx", ctxPath); // 加入上下文路径
		String output = freemarkEngine.parseByStringTemplate(map, template);
		
		//解析子表的默认内容
		output = changeSubShow(output);
		
		String tabTitle = bpmFormDef.getTabTitle();
		// 有分页的情况。
		if (tabTitle.indexOf(BpmFormDef.PageSplitor ) > -1) {
			output = getTabHtml(tabTitle, output);
		}
		// 替换流程实例分隔符。
		output = output.replace(INSTANCEID_SPLITOR, instanceId);
		// 流程图解析
		if (instanceId != "") {
			String repStr = "<iframe src=\"" + ctxPath + "/platform/bpm/processRun/processImage.ht?actInstId=" + instanceId + "&notShowTopBar=0\" name=\"flowchart\" id=\"flowchart\" marginwidth=\"0\" marginheight=\"0\" frameborder=\"0\" scrolling=\"no\" width=\"100%\"></iframe>";
			output = output.replaceAll(FLOW_CHART_SPLITOR, repStr).replaceAll(FLOW_CHART_SPLITOR_IE, repStr);
		} else if (actDefId != "") {
			String repStr = "<iframe src=\"" + ctxPath + "/platform/bpm/bpmDefinition/flowImg.ht?actDefId=" + actDefId + "\"  name=\"flowchart\" id=\"flowchart\" marginwidth=\"0\" marginheight=\"0\" frameborder=\"0\" scrolling=\"no\" width=\"100%\"></iframe>";
			output = output.replaceAll(FLOW_CHART_SPLITOR, repStr).replaceAll(FLOW_CHART_SPLITOR_IE, repStr);
		}
		
		//子表权限放到页面 （在页面解析的）
		//子表字段相关权限
		Map<String, List<JSONObject>> subFileJsonMap = null;
		if(StringUtil.isEmpty(parentActDefId)){
			subFileJsonMap = bpmFormRightsService.getSubTablePermission(formKey, userId, actDefId, nodeId,PlatformType.PC);
		}else{
			subFileJsonMap = bpmFormRightsService.getSubTablePermission(formKey, userId, actDefId, nodeId, parentActDefId,PlatformType.PC);
		}
		String subFilePermissionMap = "{}";
		if(BeanUtils.isNotEmpty(subFileJsonMap)){
			 subFilePermissionMap = JSONObject.fromObject(subFileJsonMap).toString();
		}
		output+="<script type=\"text/javascript\" > var subFilePermissionMap = "+subFilePermissionMap+" </script>";
		return output;
	}
	
	/**
	 * 根据表ID，主键值，流程实例Id 获取bpmFormData 实例
	 * @param tableId
	 * @param pkValue
	 * @param instanceId
	 * @return
	 * @throws Exception
	 */
	public BpmFormData getBpmFormData(Long tableId,String pkValue,String instanceId,String actDefId,String nodeId,boolean isCopyFlow) throws Exception{
		BpmFormData data = null;
		if (StringUtil.isNotEmpty(pkValue)) {
			//处理业务数据模板的情况。如果actDefId=#dataTem 说明是从业务数据模板调用该方法的，需要在这里处理为空 即 actDefId="";
			if("#dataTem".equals(actDefId) || "#formPrev".equals(actDefId)){
				actDefId="";
			}
			// 根据主键和表取出数据填充BpmFormData。
			data = dao.getByKey(tableId, pkValue,actDefId,nodeId,true);
			// 获取表单的意见。
			if (StringUtil.isNotEmpty(instanceId)) {
				
				Map<String, String> formOptions = getFormOptionsByInstance(instanceId);
				if (BeanUtils.isNotEmpty(formOptions)) {
					data.setOptions(formOptions);
				}
			}
			
			if(isCopyFlow){//如果是复制流程重新计算流水号和脚本计算结果
				// 获取流水号和脚本计算结果
				List<BpmFormField> list = bpmFormFieldDao.getByTableIdContainHidden(tableId);
				Map<String, Object> resultMap = this.getCalculateDataMap(list, data,actDefId);
				data.setMainFields(resultMap);
			}
		}else {
			data = new BpmFormData();
			// 获取流水号和脚本计算结果
//			List<BpmFormField> list = bpmFormFieldService.getByTableId(tableId);
			List<BpmFormField> list = bpmFormFieldDao.getByTableIdContainHidden(tableId);
			Map<String, Object> resultMap = this.getCalculateDataMap(list, data,actDefId);
			// 将流水号和脚本计算结果加入data
			data.setMainFields(resultMap);
		}
		
		return data;
	}
	/**
	 *  获取流水号和脚本计算结果
	 * @param list
	 * @param data
	 * @param actDefId
	 * @return 
	 * Map<String,Object>
	 * @since  1.0.0
	 */
	private Map<String, Object> getCalculateDataMap(List<BpmFormField> list, BpmFormData data, String actDefId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SysUser user = ContextUtil.getCurrentUser();
		SysOrg org = ContextUtil.getCurrentOrg();
		Position pos=ContextUtil.getCurrentPos();
		for (BpmFormField field : list) {
			String fieldName = field.getFieldName().toLowerCase();
			// 值来源为流水号。
			if (field.getValueFrom() == BpmFormField.VALUE_FROM_IDENTITY) {
				//如果actDefId=#dataTem 则说明是业务数据模板调用数据,需要立刻获取流水号
				if ("#dataTem".equals(actDefId)) {
					String id = identityService.nextId(field.getIdentity());
					resultMap.put(fieldName, id);
				}else if ("#formPrev".equals(actDefId)) {
					//表单预览情况则不获取流水号
				}else {
					//处理启动流程时是否显示流水号的数据，判断依据来源于设计字段时，是否勾选了 启动时显示   isShowidentity=1  显示在启动页面, 为0则显示
					String prop=field.getCtlProperty();
					if (StringUtil.isNotEmpty(prop)) {
						JSONObject jsonObject=JSONObject.fromObject(prop);
						if(jsonObject.containsKey("isShowidentity")){
							String isShowidentity=jsonObject.getString("isShowidentity");
							if ("1".equals(isShowidentity)) {
								String id = identityService.nextId(field.getIdentity());
								resultMap.put(fieldName, id);
							}
						}
					}
			
				}
			}
			// 值来源为脚本。
			else if (field.getValueFrom() == BpmFormField.VALUE_FROM_SCRIPT_SHOW) {
				Object result = FormUtil.calcuteField(field.getScript(), data.getMainFields(), TableModel.CUSTOMER_COLUMN_PREFIX);
				resultMap.put(fieldName, result);
			}
			//计算默认日期数据
			else if(field.getControlType()==15 || "date".equals(field.getFieldType())){
				String prop=field.getCtlProperty();
				//{"format":"yyyy-MM-dd","displayDate":1,"condition":"like"}
				if(StringUtil.isNotEmpty(prop)){
					try{
						JSONObject jsonObject=JSONObject.fromObject(prop);
						if(jsonObject.containsKey("displayDate")){
							String format=jsonObject.getString("format");
							String displayDate=jsonObject.getString("displayDate");
							if(displayDate.equals("1")){
								resultMap.put(fieldName, TimeUtil.getDateString(format));
							}
						}
					} catch (Exception ex) {
						logger.debug(ex.getMessage());
					}
				}
			}
			// 用户选择器默认当前用户
			else if (field.getControlType().shortValue() == FieldPool.SELECTOR_USER_SINGLE) {
				String prop=field.getCtlProperty();
				if(StringUtil.isNotEmpty(prop)){
					JSONObject jsonObject=JSONObject.fromObject(prop);
					if(jsonObject.containsKey("showCurUser")){
						String showCurUser = JSONObject.fromObject(prop).getString("showCurUser");
						if(showCurUser.equals("1")){
							if(field.getIsHidden()==1){
								resultMap.put(fieldName, user.getUserId());
							}else{
								resultMap.put(fieldName, user.getFullname());
							}
						}
					}
					
				}
			}else if(FieldPool.SELECTOR_ORG_SINGLE==field.getControlType().shortValue()&&BeanUtils.isNotEmpty(org)){
				String prop=field.getCtlProperty();
				if(StringUtil.isNotEmpty(prop)){
					JSONObject jsonObject=JSONObject.fromObject(prop);
					if(jsonObject.containsKey("showCurOrg")){
						String showCurUser = JSONObject.fromObject(prop).getString("showCurOrg");
						if(showCurUser.equals("1")){
							if(field.getIsHidden()==1){
								resultMap.put(fieldName, org.getOrgId());
							}else{
								resultMap.put(fieldName, org.getOrgName());
							}
						}
					}
					
				}
			}else if(FieldPool.SELECTOR_POSITION_SINGLE==field.getControlType()&&BeanUtils.isNotEmpty(pos)){
				String prop=field.getCtlProperty();
				if(StringUtil.isNotEmpty(prop)){
					JSONObject jsonObject=JSONObject.fromObject(prop);
					if(jsonObject.containsKey("showCurPos")){
						String showCurUser = JSONObject.fromObject(prop).getString("showCurPos");
						if(showCurUser.equals("1")){
							if(field.getIsHidden()==1){
								resultMap.put(fieldName, pos.getPosId());
							}else{
								resultMap.put(fieldName, pos.getPosName());
							}
						}
					}
					
				}
			}
			
		}
		return resultMap;
	}
	
	
	
	/**
	 * 对设计的表单进行解析。
	 * <pre>
	 * 生成实际的表单html。
	 * </pre>
	 * @param title
	 * @param parseResult
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String obtainHtml(String title, ParseReult parseResult,Map<String, Map<String, String>> permission,Boolean isPreview) throws Exception {
		String template = parseResult.getTemplate();
		
		BpmFormTable bpmFormTable=parseResult.getBpmFormTable();
		
		if(BeanUtils.isEmpty(permission)){
			permission=new HashMap<String, Map<String, String>>();
			
			Map<String, String> fieldPermission = new HashMap<String, String>();
			Map<String, String> tablePermission = new HashMap<String, String>();
			Map<String, String> opinionPermission = new HashMap<String, String>();
			
			permission.put("field", fieldPermission);
			permission.put("table", tablePermission);
			permission.put("opinion", opinionPermission);
		}
		
		
		BpmFormData	data = new BpmFormData();
		// 获取流水号和脚本计算结果
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		SysUser user = ContextUtil.getCurrentUser();
		SysOrg org = ContextUtil.getCurrentOrg();
		Position pos=ContextUtil.getCurrentPos();
		List<BpmFormField> list = bpmFormTable.getFieldList();
		for (BpmFormField field : list) {
			String fieldName = field.getFieldName().toLowerCase();
			// 值来源为流水号。
			if (field.getValueFrom() == BpmFormField.VALUE_FROM_IDENTITY) {
				// String id = identityService.nextId(field.getIdentity());
				// 如果是预览则不显示流水号，否则显示
				if (!isPreview) {
					String id = identityService.getCurIdByAlias(field.getIdentity());
					resultMap.put(fieldName, id);
				}
			}
			// 值来源为脚本。
			else if (field.getValueFrom() == BpmFormField.VALUE_FROM_SCRIPT_SHOW) {
				Object result = FormUtil.calcuteField(field.getScript(), data.getMainFields(), TableModel.CUSTOMER_COLUMN_PREFIX);
				resultMap.put(fieldName, result);
			}
			// 计算默认日期数据
			else if (field.getControlType() == 15 || field.getFieldType().equals("date")) {
				String prop = field.getCtlProperty();
				// {"format":"yyyy-MM-dd","displayDate":1,"condition":"like"}
				if (StringUtil.isNotEmpty(prop)) {
					try {
						JSONObject jsonObject = JSONObject.fromObject(prop);
						String format = jsonObject.getString("format");
						String displayDate = jsonObject.getString("displayDate");
						if (displayDate.equals("1")) {
							resultMap.put(fieldName, TimeUtil.getDateString(format));
						}
					} catch (Exception ex) {
					}
				}
			}

			// 用户选择器默认当前用户
			else if (field.getControlType() == FieldPool.SELECTOR_USER_SINGLE) {
				String prop=field.getCtlProperty();
				if(StringUtil.isNotEmpty(prop)){
					JSONObject jsonObj = JSONObject.fromObject(prop);
					if(jsonObj.containsKey("showCurUser")){
						String showCurUser = jsonObj.getString("showCurUser");
						if(showCurUser.equals("1")){
							resultMap.put(fieldName + "id", user.getUserId());
							resultMap.put(fieldName, user.getFullname());
						}
					}
				}
			}
			
			// 组织选择器默认当前组织
			else if (field.getControlType() == FieldPool.SELECTOR_ORG_SINGLE&&BeanUtils.isNotEmpty(org)) {
				String prop=field.getCtlProperty();
				if(StringUtil.isNotEmpty(prop)){
					JSONObject jsonObj = JSONObject.fromObject(prop);
					if(jsonObj.containsKey("showCurOrg")){
						String showCurOrg = jsonObj.getString("showCurOrg");
						if(showCurOrg.equals("1")){
							resultMap.put(fieldName + "id", org.getOrgId());
							resultMap.put(fieldName, org.getOrgName());
						}
					}
				}
			}
			//岗位选择器默认当前岗位
			else if(FieldPool.SELECTOR_POSITION_SINGLE==field.getControlType()&&BeanUtils.isNotEmpty(pos)){
				String prop=field.getCtlProperty();
				if(StringUtil.isNotEmpty(prop)){
					JSONObject jsonObject=JSONObject.fromObject(prop);
					if(jsonObject.containsKey("showCurPos")){
						String showCurUser = JSONObject.fromObject(prop).getString("showCurPos");
						if(showCurUser.equals("1")){
							resultMap.put(fieldName + "id", pos.getPosId());
							resultMap.put(fieldName, pos.getPosName());
						}
					}
				}
			}
			//处理预览时输入框有预设值的情况
			else if (isPreview && field.getControlType()==FieldPool.TEXT_INPUT) {
				resultMap.put(fieldName, field.getDefValue());
			}
		}
		// 将流水号和脚本计算结果加入data
		data.setMainFields(resultMap);
		

		Map<String, Map> model = new HashMap<String, Map>();
		model.put("main", data.getMainFields());
		model.put("opinion", data.getOptions());
		model.put("sub", data.getSubTableMap());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("model", model);
		// 传入控制器的service，用于在模版中解析字段。
		map.put("service", bpmFormControlService);
		// 传入字段的权限。
		map.put("permission", permission);
		//兼容之前生成的模版。
		map.put("table", new HashMap());
		
		String output = freemarkEngine.parseByStringTemplate(map, template);
		//解析子表的默认内容
		output = changeSubShow(output);
		//虚构子表权限
		output += getSubPermission(bpmFormTable,false);
		// 有分页的情况。
		if (title.indexOf(BpmFormDef.PageSplitor ) > -1) {
			output = getTabHtml(title, output);
		}	
		return output;
	}

	/**
	 * 根据流程实例取得流程的意见。
	 * 
	 * @param instanceId
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	public Map<String, String> getFormOptionsByInstance(String instanceId) throws IOException, TemplateException {
	/*
		Map<String, String> map = new HashMap<String, String>();
		Map<String, List<TaskOpinion>> taskMap = new HashMap<String, List<TaskOpinion>>();
		
		
		List<TaskOpinion> list = taskOpinionService.getByActInstId(instanceId);
		for (TaskOpinion option : list) {
			if (StringUtil.isNotEmpty(option.getFieldName())) {
				String fieldName = option.getFieldName().toLowerCase();
				if (taskMap.containsKey(fieldName)) {
					List<TaskOpinion> opinionList = taskMap.get(fieldName);
					opinionList.add(option);
				} else {
					List<TaskOpinion> opinionList = new ArrayList<TaskOpinion>();
					opinionList.add(option);
					taskMap.put(fieldName, opinionList);
				}
			}
		}
		Set<Map.Entry<String, List<TaskOpinion>>> set = taskMap.entrySet();
		for (Iterator<Map.Entry<String, List<TaskOpinion>>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, List<TaskOpinion>> entry = (Map.Entry<String, List<TaskOpinion>>) it.next();
			List<TaskOpinion> optionList = entry.getValue();
			String options = "";
			for (TaskOpinion opinion : optionList) {
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("opinion", opinion);
				options += freemarkEngine.mergeTemplateIntoString("opinion.ftl", model);
			}
			map.put(entry.getKey(), options);
		}
		return map;
		*/
			Map<String, String> map = new HashMap<String, String>();

		List<TaskOpinion> list = taskOpinionService.getByActInstId(instanceId);
		for (TaskOpinion option : list) {
			if (StringUtil.isEmpty(option.getFieldName()))
				continue;
			String fieldName = option.getFieldName().toLowerCase();
			generateOpinion(option, fieldName, map);
		}
		return map;
	}
	private void generateOpinion(TaskOpinion opinion, String fieldName, Map<String, String> map) throws IOException, TemplateException {
		String resultOpinion = "";
		if (map.containsKey(fieldName))
			resultOpinion = map.get(fieldName);
		resultOpinion += TaskOpinionService.getOpinion(opinion, true);
		map.put(fieldName, resultOpinion);
	}
	/**
	 * 获取tab的html。
	 * 
	 * @param tabTitle
	 * @param html
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	private String getTabHtml(String tabTitle, String html) throws TemplateException, IOException {
		String[] aryTitle = tabTitle.split(BpmFormDef.PageSplitor);
		String[] aryHtml = html.split(BpmFormDef.PageSplitor);

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < aryTitle.length; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("title", aryTitle[i]);
			map.put("html", aryHtml[i] );
			list.add(map);
		}
		String formPath = BpmFormTemplateService.getFormTemplatePath() + "tab.ftl";
		String tabTemplate = FileUtil.readFile(formPath);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tabList", list);
		String output = freemarkEngine.parseByStringTemplate(map, tabTemplate);
	// 分时把js移到tab外面防止ligerTab二次加载js
		Pattern pattern = Pattern.compile("<script([\\s\\S]*?)script>");
		Matcher matcher = pattern.matcher(output);
		while (matcher.find()) {
			String str = matcher.group();
			output = output.replace(str, "");
			output = str + "\n" + output;
		}
		return output;
	}

	/**
	 * 处理动态表单数据
	 * 
	 * @param bpmFormData
	 * @throws Exception
	 */
	public void handFormData(BpmFormData bpmFormData, String formKey,String jsonData) throws Exception {
		SysBusEvent busEvent = sysBusEventService.getByFormKey(formKey);
		handEvent(busEvent, true, bpmFormData,jsonData);
		dao.handFormData(bpmFormData);
		handEvent(busEvent, false, bpmFormData,jsonData);
	}
		/**
	 * 处理后台脚本。
	 * 
	 * @param busEvent
	 * @param isBefore
	 * @param bpmFormData
	 */
	private void handEvent(SysBusEvent busEvent, boolean isBefore, BpmFormData bpmFormData,String jsonData) {
		if (busEvent == null)
			return;
		String script = "";
		if (isBefore) {
			script = busEvent.getPreScript();
		} else {
			script = busEvent.getAfterScript();
		}
		if (StringUtil.isEmpty(script))
			return;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("data", bpmFormData);
		params.put("jsonData", jsonData);
		groovyScriptEngine.execute(script, params);
	}

	/**
	 * 处理动态表单数据
	 * @param bpmFormData
	 * @param processRun
	 * @throws Exception
	 */
	public void handFormData(BpmFormData bpmFormData,ProcessRun processRun) throws Exception {
		dao.handFormData(bpmFormData, processRun);
	}
	
	/**
	 * 处理动态表单数据
	 * @param bpmFormData
	 * @param processRun
	 * @param nodeId
	 * @throws Exception
	 */
	public void handFormData(BpmFormData bpmFormData,ProcessRun processRun,String nodeId) throws Exception {
		dao.handFormData(bpmFormData, processRun, nodeId);
	}

	/**
	 * 根据主键查询列表数据。
	 * 
	 * @param tableId
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public BpmFormData getByKey(long tableId, String pkValue )throws Exception {
		return dao.getByKey(tableId, pkValue,true);
	}

	
	/**
	 * 
	 * @param dsAlias
	 * @return
	 * @throws Exception 
	 */
	public JdbcDao getNewJdbcDao(String dsAlias) throws Exception{
		JdbcDao jdao=new JdbcDao();
		jdao.setDialect(DialectUtil.getDialectByDataSourceAlias(dsAlias));
		jdao.setJdbcTemplate(new JdbcTemplate(DataSourceUtil.getDataSourceByAlias(dsAlias)));
		return jdao;
	}

	/**
	 * 删除业务数据。
	 * @param id
	 * @param tableName
	 * @throws Exception 
	 */
	public void delById(String pkValue,BpmFormTable bpmFormTable) throws Exception{
	//	String sql="delete from w_" + bpmFormTable.getTableName() + " where id=" + pkValue;
			String sql="delete from " + TableModel.CUSTOMER_TABLE_PREFIX+bpmFormTable.getTableName() + " where id=" + pkValue;
		if(bpmFormTable.isExtTable()){
			sql="delete from " + bpmFormTable.getTableName() + " where " + bpmFormTable.getPkField() + "=" + pkValue;
			this.getNewJdbcDao(bpmFormTable.getDsAlias()).upd(sql);
		}else{
			jdbcDao.upd(sql);
		}
	}
	
	
	

/*
	public boolean isExistsData(String tableName,String pkValue){
		if(tableName.indexOf(TableModel.CUSTOMER_TABLE_PREFIX.toLowerCase())<0){
			tableName=TableModel.CUSTOMER_TABLE_PREFIX+tableName;
		}
		Map<String,Object> data=dao.getByKey(tableName, pkValue);
		if(BeanUtils.isEmpty(data)){
			return false;
		}else{
			return true;
		}
	}
*/
	public boolean isExistsData(String dsName, String tableName, String pkName, String pkValue) throws Exception {

		Map<String, Object> data = dao.getByKey(dsName, tableName, pkName, pkValue);
		if (BeanUtils.isEmpty(data)) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * 删除业务数据。
	 * 
	 * @param id
	 * @param tableName
	 */
	public void delByIdTableName(String id, String tableName) {
		String sql = "delete from " + tableName + " where id=" + id;
		jdbcDao.upd(sql);
	}

	/**
	 * 根据表名和主键是否有数据。
	 * 
	 * @param tableName
	 * @param pk
	 * @return
	 */
	public boolean isHasDataByPk(String tableName, Long pk) {
		return dao.isHasDataByPk(tableName, pk);
	}

	public void delByDsAliasAndTableName(String dsAlias, String tableName,String pk) throws Exception {
//		BpmFormTable bpmFormTable=bpmFormTableService.getByAliasTableName(dsAlias, tableName);
//		JdbcHelper<?, ?> jdbcHelper= ServiceUtil.getJdbcHelper(dsAlias);
//		DbContextHolder.setDataSource(dsAlias);
//		String sql="delete from "+tableName+" where "+bpmFormTable.getPkField()+"="+pk;
//		jdbcHelper.getJdbcTemplate().execute(sql);
//		jdbcTemplate.execute(sql);
		
			BpmFormTable bpmFormTable = bpmFormTableService.getByAliasTableName(dsAlias, tableName);
		String sql = "delete from " + tableName + " where " + bpmFormTable.getPkField() + "=" + pk;
		JdbcTemplateUtil.execute(dsAlias, sql);
	}

	/**
	 * 计算默认值 流水号,脚本计算结果,当前日期，当前人,当前组织
	 * 
	 * @param list
	 *            当前表单字段List
	 * @param data
	 *            表单数据
	 * @return
	 */
	public Map<String, Object> getDataMap(List<BpmFormField> list, BpmFormData data) {
		// 获取流水号,脚本计算结果,当前日期
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SysUser user = ContextUtil.getCurrentUser();
		SysOrg org = ContextUtil.getCurrentOrg();
		Position position = positionDao.getPosByUserId(user.getUserId());
		
		for (BpmFormField field : list) {
			String fieldName = field.getFieldName().toLowerCase();
			String prop = field.getCtlProperty();
			// 值来源为流水号。
			if (field.getValueFrom() == BpmFormField.VALUE_FROM_IDENTITY) {
				String id = identityService.nextId(field.getIdentity());
				resultMap.put(fieldName, id);
			}
			// 值来源为脚本。
			else if (field.getValueFrom() == BpmFormField.VALUE_FROM_SCRIPT_SHOW) {
				Object result = FormUtil.calcuteField(field.getScript(), data.getMainFields(), TableModel.CUSTOMER_COLUMN_PREFIX);
				resultMap.put(fieldName, result);
			}
			// 计算默认日期数据
			else if (field.getControlType() == FieldPool.DATEPICKER || field.getFieldType().equals("date")) {
				// {"format":"yyyy-MM-dd","displayDate":1,"condition":"like"}
				if (StringUtil.isEmpty(prop))
					continue;
				try {
					JSONObject jsonObject = JSONObject.fromObject(prop);
					String format = jsonObject.getString("format");
					String displayDate = jsonObject.getString("displayDate");
					if (displayDate.equals("1")) {
						resultMap.put(fieldName, TimeUtil.getDateString(format));
					}
				} catch (Exception ex) {
				}
			}

			// 用户选择器默认当前用户
			else if (field.getControlType() == FieldPool.SELECTOR_USER_SINGLE) {
				if (StringUtil.isEmpty(prop))
					continue;
				try {
					JSONObject jsonObj = JSONObject.fromObject(prop);
					if (!jsonObj.containsKey("showCurUser"))
						continue;
					String showCurUser = jsonObj.getString("showCurUser");
//					if (!showCurUser.equals("1")) continue;
					
					if(showCurUser.equals("1")){
						if (field.getIsHidden() == 1) {
							resultMap.put(fieldName, user.getUserId());
						} else {
							resultMap.put(fieldName, user.getFullname());
						}
					}else if(showCurUser.equals("2")){
						SysUserOrg userOrg= sysUserOrgService.getPrimaryByUserId(user.getUserId());
						if(userOrg==null){
							continue;
						}
						Long orgId=userOrg.getOrgId();
						List<SysUser> leaders = sysUserDao.getSuperiorByUserId(user.getUserId(), orgId);
						if(BeanUtils.isNotEmpty(leaders)){
							if (field.getIsHidden() == 1) {
								resultMap.put(fieldName, leaders.get(0).getUserId());
							} else {
								resultMap.put(fieldName, leaders.get(0).getFullname());
							}
						}
					}
				} catch (Exception ex) {
					String msg=ExceptionUtil.getExceptionMessage(ex);
					logger.debug(msg);
				}
			}

			// 组织选择器默认当前组织
			else if (field.getControlType() == FieldPool.SELECTOR_ORG_SINGLE) {
				if (StringUtil.isEmpty(prop))
					continue;
				try {
					JSONObject jsonObj = JSONObject.fromObject(prop);
					if (!jsonObj.containsKey("showCurOrg"))
						continue;
					String showCurOrg = jsonObj.getString("showCurOrg");
					if (!showCurOrg.equals("1"))
						continue;
					if (field.getIsHidden() == 1) {
						resultMap.put(fieldName, org.getOrgId());
					} else {
						resultMap.put(fieldName, org.getOrgName());
					}
				} catch (Exception ex) {
				}
			}
			// 组织单选。
			else if (field.getControlType() == FieldPool.SELECTOR_POSITION_SINGLE) {
				if (StringUtil.isEmpty(prop))
					continue;
				try {
					JSONObject jsonObj = JSONObject.fromObject(prop);
					if (!jsonObj.containsKey("showCurPos"))
						continue;
					String showCurPos = jsonObj.getString("showCurPos");
					if (!showCurPos.equals("1"))
						continue;
					if (field.getIsHidden() == 1) {
						resultMap.put(fieldName, position.getPosId());
					} else {
						resultMap.put(fieldName, position.getPosName());
					}
				} catch (Exception ex) {
				}
			}

		}
		return resultMap;
	}

	/**
	 * 获取选项的国际化资源
	 * @return
	 */
	public Map<String,BpmFormField> getOptionI18nMap(Long tableId){
		Map<String,BpmFormField> map = new HashMap<String, BpmFormField>();
		BpmFormTable mainFormTableDef = bpmFormTableDao.getById(tableId);
		
		List<BpmFormField> list = bpmFormFieldDao.getByTableIdContainHidden(tableId);
		
		String prefix = "m_" + mainFormTableDef.getTableName() + "_";
		handlerOptionMap(list, map, prefix);
		List<BpmFormTable> formTableList = bpmFormTableDao.getSubTableByMainTableId(tableId);
		for(BpmFormTable bpmFormTable:formTableList){
			prefix = "s_" + bpmFormTable.getTableName() + "_";
			Long subTableId = bpmFormTable.getTableId();
			List<BpmFormField> subFieldList = bpmFormFieldDao.getByTableIdContainHidden(subTableId);
			handlerOptionMap(subFieldList, map, prefix);
		}
		return map;
	}
	
	/**
	 * 构建选项的国际化资源map
	 * @param list
	 * @param map
	 * @param prefix
	 */
	private void handlerOptionMap(List<BpmFormField> list,Map<String,BpmFormField> map,String prefix){
		for(BpmFormField bpmFormField : list){
			String options = bpmFormField.getOptions();
			if(StringUtil.isNotEmpty(options)){
				String key = prefix + bpmFormField.getFieldName();
				map.put(key, bpmFormField);
			}
		}
	}
	
	
	/**
	 * 虚构子表的字符串权限并变成 JS变量 的字符串
	 * @param bpmFormTable
	 * @param onlyRead
	 */
	public String getSubPermission(BpmFormTable bpmFormTable,boolean onlyRead){
		String html = "";
		//子表字段权限
		List<BpmFormTable> tableList = bpmFormTable.getSubTableList();
		Map<String, List<JSONObject>> subFileJsonMap = new HashMap<String, List<JSONObject>>();
		List<JSONObject> subJsonList = new ArrayList<JSONObject>();
		List<JSONObject> subTableShowList = new ArrayList<JSONObject>();
		for (BpmFormTable table : tableList) {
            //子表事个表的权限
			JSONObject permission = null;
			if(onlyRead){
				permission = bpmFormRightsService.getReadPermissionJson(table.getTableName(),table.getTableDesc(),4);
			}else{
				permission = bpmFormRightsService.getPermissionJson(table.getTableName(),table.getTableDesc(),4);
			}
			permission.put("tableId", table.getTableId());
			permission.put("tableName", table.getTableName());
			permission.put("mainTableId",bpmFormTable.getTableId());
			permission.put("mainTableName",bpmFormTable.getTableName());
			subTableShowList.add(permission);
			
			//每个子表中的每个字段
			List<BpmFormField> subFieldList = table.getFieldList();
			for (BpmFormField field : subFieldList) {
				//子表事个表的权限
				JSONObject subPermission = null;
				if(onlyRead){
					subPermission = bpmFormRightsService.getReadPermissionJson(field.getFieldName(),field.getFieldDesc(),1);
					subPermission.put("power","r");
				}else{
					subPermission = bpmFormRightsService.getPermissionJson(field.getFieldName(),field.getFieldDesc(),1);
					subPermission.put("power","w");
				}	
				
				//增加字段的控件类型
				subPermission.put("controlType", field.getControlType());
				
				subPermission.put("tableId", table.getTableId());
				subPermission.put("tableName", table.getTableName());
				subPermission.put("mainTableId",bpmFormTable.getTableId());
				subPermission.put("mainTableName",bpmFormTable.getTableId());
				subJsonList.add(subPermission);
			}
		}		
		subFileJsonMap.put("subFileJsonList", subJsonList);
		subFileJsonMap.put("subTableShowList", subTableShowList);
		
		String subFilePermissionMap = "{}";
		if(BeanUtils.isNotEmpty(subFileJsonMap)){
			 subFilePermissionMap = JSONObject.fromObject(subFileJsonMap).toString();
		}
		html+="<script type=\"text/javascript\" > var subFilePermissionMap = "+subFilePermissionMap+" </script>";
		return html;
	}

	/**
	 * 查询子表编辑行默认的东西时，要默认出来
	 * @param list
	 * @param map
	 * @param prefix
	 */
	private String changeSubShow(String html){
		SysUser user = ContextUtil.getCurrentUser();
		SysOrg org = ContextUtil.getCurrentOrg();
		Position pos = positionDao.getPosByUserId(user.getUserId());
		
		Document doc= Jsoup.parseBodyFragment(html);
		//查询子表编辑行默认的东西时，要默认出来 
		Elements formtypes= doc.select("[formtype=edit],[formtype=form],[formtype=window]");
		for (Element ft : formtypes){
		//	Elements rows=ft.select("input[name^=s:],textarea[name^=s:],select[name^=s:]");
			Elements rows=ft.select("input[name^=s:]");
			for (Element el : rows){
				String name = el.attr("name");
				String displayDate = el.attr("displayDate");    //默认当前时间
				if("1".equals(displayDate)){
					/*String datefmt = el.attr("datefmt");
					if("".equals(datefmt)){
						datefmt = "yyyy-MM-dd";
					}
					el.attr("value",TimeUtil.getDateString(datefmt));*/
				}else{
					String showCurUser = el.attr("showCurUser");         //默认当前人
					String showCurOrg = el.attr("showCurOrg");           //默认当前部门
					String showCurPos = el.attr("showCurPos");         //默认当前岗位
					
					Elements inputs = ft.select("input[name="+name+"ID]");					
					Element input = null;
					String value = "";
					Long id_value = 0l;
					if(inputs.size()==1){          //有对应的
						input = ft.select("input[name="+name+"ID]").get(0);
					}
					
					if("1".equals(showCurUser)){
						value = user.getFullname();
						id_value = user.getUserId();
					}else if("1".equals(showCurOrg)){
						value = org.getOrgName();
						id_value = org.getOrgId();						
					}else if("1".equals(showCurPos)){
						value = pos.getPosName();
						id_value = pos.getPosId();
					}
					
					if(StringUtil.isNotEmpty(value)){
						el.attr("value",value);
						//处理选择器是获取当前值时，隐藏域没有值的情况
						el.attr("initvalue",id_value.toString());
						if( BeanUtils.isNotEmpty(input) && id_value>0){
							input.attr("value",id_value.toString());
						}
					}
				}
			}
		}
		return doc.body().html();
	}
	
	/**
	 * 处理在线表单数据。
	 * 
	 * @param processRun
	 * @param processCmd
	 * @return
	 * @throws Exception
	 */
	public BpmFormData handlerFormData(ProcessCmd processCmd, ProcessRun processRun, String nodeId) throws Exception {
		String json = processCmd.getFormData();
		
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		
		BpmFormData bpmFormData = null;
		BpmFormTable bpmFormTable=null;
		String businessKey="";
		//判断节点Id是否为空，为空表示开始节点。
		boolean isStartFlow=false;
		if(StringUtil.isEmpty(nodeId)){
			businessKey = processCmd.getBusinessKey();
			isStartFlow=true;
		}else{
			businessKey = processRun.getBusinessKey();
		}
		if(isStartFlow&&ProcessRun.STATUS_FORM.equals(processRun.getStatus())){
			Long formDefId=processRun.getFormDefId();
			BpmFormDef bpmFormDef=bpmFormDefService.getById(formDefId);
			bpmFormTable= bpmFormTableService.getByTableId(bpmFormDef.getTableId(), 1);
		}else{
			bpmFormTable= bpmFormTableService.getByDefId(processRun.getDefId());
		}
		
		
		// 有主键的情况,表示数据已经存在。
		if (StringUtil.isNotEmpty(businessKey)) {
			String pkName=bpmFormTable.isExtTable()?bpmFormTable.getPkField() :TableModel.PK_COLUMN_NAME;
			PkValue pkValue = new PkValue(pkName,businessKey);
			pkValue.setIsAdd(false);
			bpmFormData = FormDataUtil.parseJson(json, pkValue,bpmFormTable);
		
		} else {
			bpmFormData = FormDataUtil.parseJson(json,bpmFormTable);
		}
		
		processCmd.putVariables(bpmFormData.getVariables());
		// 生成的主键
		PkValue pkValue = bpmFormData.getPkValue();
		businessKey = pkValue.getValue().toString();
		
		String pk=processRun.getBusinessKey();
		
		processRun.setBusinessKey(businessKey);
		processCmd.setBusinessKey(businessKey);
	// 保存bpmFormData之前 处理节点意见映射配置
		TaskEntity taskEntity = bpmService.getTask(processCmd.getTaskId());
		handFieldOpinion(taskEntity, bpmFormData, processCmd);
		//启动流程。
		if(isStartFlow){
			// 保存表单数据,存取表单数据
			this.handFormData(bpmFormData,processRun);
		}else{
			this.handFormData(bpmFormData,processRun,nodeId);
			//业务主键为空的情况，设置流程主键。设置流程变量。
			if(StringUtil.isEmpty(pk)){
				runtimeService.setVariable(processRun.getActInstId(), BpmConst.FLOW_BUSINESSKEY, businessKey);
			}
		}
		return bpmFormData;
	}
	private void handFieldOpinion(TaskEntity taskEntity, BpmFormData bpmFormData, ProcessCmd processCmd) {

		// 第一步 获取节点配置
		String parentNodeId = taskEntity.getTaskDefinitionKey();
		String actDefId = taskEntity.getProcessDefinitionId();
		String parentActDefId = (String) taskService.getVariable(taskEntity.getId(), BpmConst.FLOW_PARENT_ACTDEFID);
		BpmNodeSet bpmNodeSet = null;
		if (StringUtil.isEmpty(parentActDefId)) {
			bpmNodeSet = bpmNodeSetService.getByActDefIdNodeId(actDefId, parentNodeId);
		} else {// 获取子流程节点数据
			bpmNodeSet = bpmNodeSetService.getByActDefIdNodeId(actDefId, parentNodeId, parentActDefId);
		
		
		
		}

		// 第二步 获取节点配置中的意见映射字段
		String opinionField = bpmNodeSet.getOpinionField();
		Short opinionHtml = bpmNodeSet.getOpinionHtml();
		processCmd.addTransientVar(BpmConst.OPINION_FIELD, opinionField);//放临时意见变量进
		processCmd.addTransientVar(BpmConst.OPINION_SUPPORTHTML, opinionHtml);
		ProcessRunService.handFieldOpinion(processCmd, bpmFormData);
	}
	
}
