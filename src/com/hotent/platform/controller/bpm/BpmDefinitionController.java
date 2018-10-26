package com.hotent.platform.controller.bpm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.util.json.JSONArray;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Encoder;

import com.hotent.BpmFormBang.model.bpmFormBang.BpmFormBang;
import com.hotent.BpmFormBang.service.bpmFormBang.BpmFormBangService;
import com.hotent.CodeTemplate.service.CodeTemplate.CodeTemplateService;
import com.hotent.DeviceActionShowPath.service.DeviceActionShowCode.DeviceActionShowService;
import com.hotent.DevicerelationshipCode.service.DevicerelationshipCodePath.DevicerelationshipService;
import com.hotent.DviceLoadRatePath.service.DviceLoadRateCode.DviceLoadRateService;
import com.hotent.LineActionShowPath.service.LineActionShowCode.LineActionShowService;
import com.hotent.LineLoadRatePath.service.LineLoadRateCode.LineLoadRateService;
import com.hotent.Markovchain.model.Markovchain.Markovchain;
import com.hotent.Markovchain.service.Markovchain.MarkovchainService;
import com.hotent.Subsystemdef.model.Subsystemdef.Subsystemdef;
import com.hotent.Subsystemdef.service.Subsystemdef.SubsystemdefService;
import com.hotent.SysDefNodeErgodic.service.SysDefNodeErgodic.SysdefnodeergodicService;
import com.hotent.activityDetail.model.activityDetail.ActivityDetail;
import com.hotent.activityDetail.service.activityDetail.ActivityDetailService;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.bpm.graph.ShapeMeta;
import com.hotent.core.bpm.graph.activiti.ProcessDiagramGenerator;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.setting.ISkipCondition;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ClassLoadUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.TimeUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.ZipUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.dataservice.service.formupdate.FormUpdateService;
import com.hotent.dbFunc.model.dbFunc.DbFunc;
import com.hotent.dbFunc.service.dbFunc.DbFuncService;
import com.hotent.deviceNodeSet.service.deviceNodeSet.DeviceNodeSetService;
import com.hotent.eventgraphrelation.model.eventgraphrelation.Eventgraphrelation;
import com.hotent.eventgraphrelation.service.eventgraphrelation.EventgraphrelationService;
import com.hotent.excelk.model.excelk.Excelk;
import com.hotent.excelk.service.excelk.ExcelkService;
import com.hotent.formQueryDefinition.service.com.FqrelationService;
import com.hotent.netWorkMap.model.netWorkMap.Bpmnetworkmap;
import com.hotent.netWorkMap.service.netWorkMap.BpmnetworkmapService;
import com.hotent.platform.model.bpm.AuthorizeRight;
import com.hotent.platform.model.bpm.BpmDefVar;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmFormResult;
import com.hotent.platform.model.bpm.BpmNode;
import com.hotent.platform.model.bpm.BpmNodeButton;
import com.hotent.platform.model.bpm.BpmNodeRule;
import com.hotent.platform.model.bpm.BpmNodeScript;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.bpm.BpmReferDefinition;
import com.hotent.platform.model.bpm.BpmUserCondition;
import com.hotent.platform.model.bpm.NodeUserMap;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskReminder;
import com.hotent.platform.model.bpm.BpmDefAuthorizeType.BPMDEFAUTHORIZE_RIGHT_TYPE;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.model.system.Demension;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysCodeTemplate;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.model.system.SysOrgType;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.TransactionInfo;
import com.hotent.platform.model.system.WDefInformation;
import com.hotent.platform.model.system.WOperateInfo;
import com.hotent.platform.service.bpm.BpmDefAuthorizeService;
import com.hotent.platform.service.bpm.BpmDefVarService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmFormQueryService;
import com.hotent.platform.service.bpm.BpmFormRunService;
import com.hotent.platform.service.bpm.BpmNodeButtonService;
import com.hotent.platform.service.bpm.BpmNodeRuleService;
import com.hotent.platform.service.bpm.BpmNodeScriptService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmNodeUserCalculationSelector;
import com.hotent.platform.service.bpm.BpmNodeUserService;
import com.hotent.platform.service.bpm.BpmReferDefinitionService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.BpmUserConditionService;
import com.hotent.platform.service.bpm.IBpmNodeUserCalculation;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.TaskReminderService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.form.FormUtil;
import com.hotent.platform.service.form.IParseHandler;
import com.hotent.platform.service.form.ParseReult;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.system.CodeUtil;
import com.hotent.platform.service.system.DemensionService;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.service.system.ShareService;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.platform.service.system.SysCodeTemplateService;
import com.hotent.platform.service.system.SysFileService;
import com.hotent.platform.service.system.SysOrgTypeService;
import com.hotent.platform.service.util.ServiceUtil;
import com.hotent.platform.xml.util.FileXmlUtil;
import com.hotent.platform.xml.util.MsgUtil;

/**
 * <pre>
 * 对象功能:流程定义 控制器类 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:csx 
 * 创建时间:2011-11-21 22:50:46
 * </pre>
 */
@Controller
@RequestMapping("/platform/bpm/bpmDefinition/")
@Action(ownermodel = SysAuditModelType.PROCESS_MANAGEMENT)
public class BpmDefinitionController extends BaseController {
	@Resource
	private BpmFormHandlerService bpmFormHandlerService;
	@Resource
	private FreemarkEngine freemarkEngine;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private DbFuncService dbFuncService;
	@Resource
	private SysCodeTemplateService sysCodeTemplateService;
	@Resource
	private BpmService bpmService;
	@Resource
	private Map<String,IParseHandler> parseHandlerMap;
	@Resource
	private BpmFormBangService bpmformbangservice;

	@Resource
	private BpmDefinitionService bpmDefinitionService;
	private BpmDefinition bpmdefinitionsher = null;
	private BpmDefinition bpmdefinitionnew = null;
	@Resource
	private EventgraphrelationService eventgraphrelationService;
	// 史欣慧
	@Resource
	private DeviceActionShowService deviceActionShowService;

	// 王百合
	@Resource
	private SubsystemdefService subsystemdefService;

	@Resource
	private LineActionShowService lineActionShowService;

	@Resource
	private DviceLoadRateService dviceLoadRateService;

	@Resource
	private LineLoadRateService lineLoadRateService;

	// whp
	@Resource
	private BpmnetworkmapService bpmnetworkmapService;
	// zl
	@Resource
	private DeviceNodeSetService deviceNodeSetService;

	@Resource
	private ProcessRunService processRunService;

	@Resource
	private GlobalTypeService globalTypeService;

	@Resource
	private BpmNodeUserService bpmNodeUserService;

	@Resource
	private BpmNodeSetService bpmNodeSetService;

	@Resource
	private DevicerelationshipService devicerelationshipService;

	@Resource
	private BpmFormRunService bpmFormRunService;

	@Resource
	private BpmNodeRuleService bpmNodeRuleService;
	@Resource
	private BpmNodeScriptService bpmNodeScriptService;
	@Resource
	private BpmNodeButtonService bpmNodeButtonService;
	@Resource
	private TaskReminderService taskReminderService;
	@Resource
	private ShareService shareService;
	@Resource
	private DemensionService demensionService;
	@Resource
	private SysOrgTypeService sysOrgTypeService;
	@Resource
	private BpmUserConditionService bpmUserConditionService;
	@Resource
	private BpmFormFieldService bpmFormFieldService;
	@Resource
	private BpmNodeUserCalculationSelector bpmNodeUserCalculationSelector;

	@Resource
	private SysFileService sysFileService;

	@Resource
	private BpmDefVarService bpmDefVarService;
	@SuppressWarnings("rawtypes")
	@Resource
	private Map handlersMap;
	@Resource
	private SubSystemService subSystemService;

	// 于达
	@Resource
	private FqrelationService fqRelationService;
	@Resource
	private BpmFormQueryService bpmFormQueryService;
	@Resource
	private FormUpdateService formUpdateService;

	@Resource
	private BpmDefAuthorizeService bpmDefAuthorizeService;
	/**
	 * @author tgl 添加流程引用接口
	 */
	@Resource
	private BpmReferDefinitionService bpmReferDefinitionService;

	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private ActivityDetailService activityDetailService;
	//那晓旭
	@Resource
	private MarkovchainService markovchainService;
	private String Markovchain=null;
	
	@Resource
	private CodeTemplateService codeTemplateService;
	@Resource
	private SysdefnodeergodicService sysdefnodeergodicService;
	@Resource
	private ExcelkService excelkService;
	/**
	 * 返回流程设计生成的BPMNxml
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("bpmnXml")
	public ModelAndView bpmnXml(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "defId");
		BpmDefinition po = bpmDefinitionService.getById(id);
		if (po.getActDeployId() != null) {
			String bpmnXml = bpmService.getDefXmlByDeployId(po.getActDeployId()
					.toString());

			if (bpmnXml
					.startsWith("<?xml version=\"1.0\" encoding=\"utf-8\"?>")) {
				bpmnXml = bpmnXml.replace(
						"<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
			}
			if (bpmnXml.startsWith("<?xml version=\"1.0\" encoding=\"GBK\"?>")) {
				bpmnXml = bpmnXml.replace(
						"<?xml version=\"1.0\" encoding=\"GBK\"?>", "");
			}
			bpmnXml = bpmnXml.trim();

			request.setAttribute("bpmnXml", bpmnXml);
		}
		return getAutoView();
	}
	/**
	 * 接收参数
	 * 
	 */
	@RequestMapping("genOPeCha")
	public void genOpeCha(HttpServletRequest request,HttpServletResponse response)throws Exception{
		long id=RequestUtil.getLong(request, "defId");
	}
	
	private String getFormHtml(BpmFormDef bpmFormDef, BpmFormTable table, boolean isEdit) throws Exception {
		String html = bpmFormDef.getHtml();
		if (BpmFormDef.DesignType_CustomDesign == bpmFormDef.getDesignType()) {
			ParseReult result = FormUtil.parseHtmlNoTable(html, table.getTableName(), table.getTableDesc());
			html = bpmFormHandlerService.obtainHtml(bpmFormDef.getTabTitle(), result, null, false);
		}
		String template = CodeUtil.getFreeMarkerTemplate(html, table, isEdit);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isEdit", isEdit);
		map.put("table", table);
		String output = freemarkEngine.parseByStringTemplate(map, template);
		return output;

	}	
	/**
	 * 下载文件的方法
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @throws Exception
	 * 王焕然
	 */
	@RequestMapping("downLoadZip")
	public void downLoadZip(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String basePathZip = BASE_PATH + File.separator + "codegen.zip";
			File file = new File(basePathZip);
			InputStream in = new FileInputStream(file);
			String fileName = "codegen.zip";
			response.setContentType("application/x-download");
			if (System.getProperty("file.encoding").equals("GBK")) {
				response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes(), "ISO-8859-1") + "\"");
			} else {
				response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "utf-8") + "\"");
			}
			ServletOutputStream out = response.getOutputStream();
			IOUtils.copy(in, out);
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Fail, "文件不存在，请重新再生成一次!:" + e.getMessage()));
		}
	}
	/**
	 * 根据前端所配置 获得表信息列表
	 * 
	 * @param request
	 * @return
	 */
	private List<BpmFormTable> getTableModels(HttpServletRequest request) {
		List<BpmFormTable> list = new ArrayList<BpmFormTable>();
		String[] tableIds = request.getParameterValues("tableId");
		String[] classNames = request.getParameterValues("className");//类名
		String[] classVars = request.getParameterValues("classVar");//变量名
		String[] packageNames = request.getParameterValues("packageName");//包名
		String system = request.getParameter("system");//系统
		List<BpmFormTable> subtables = new ArrayList<BpmFormTable>();
		for (int i = 0; i < tableIds.length; i++) {
			Long tableId = Long.parseLong(tableIds[i]);//获取表ID
			System.out.println("tables"+tableIds[i]);
			Map<String, String> vars = new HashMap<String, String>();//定义MAP类型变量存放4个参数
			vars.put("class", classNames[i]);
			System.out.println("class"+classNames[i]);
			vars.put("classVar", classVars[i]);
			vars.put("package", packageNames[i]);
			System.out.println("package"+packageNames[i]);
			vars.put("system", system);
			BpmFormTable bpmFormTable = bpmFormTableService.getById(tableId);//根据主键Id获取表对象
			String tableName =bpmFormTable.getTableName();
			//System.out.println("在数据库中表名为："+bpmFormTable.getFactTableName());
			//System.out.println("表名为："+bpmFormTable.getTableName());
			//System.out.println("是否外部数据源   ："+bpmFormTable.getIsExternal());
			bpmFormTable.setVariable(vars);//设置Variable变量 
			List<BpmFormField> fieldList = bpmFormFieldService.getByTableIdContainHidden(tableId);//BpmFormField表示bpm_form_field Model对象
			List<BpmFormField> fields = new ArrayList<BpmFormField>();
			//找表中对应的方法
			List<DbFunc> dbFunc1  =dbFuncService.getByTableName(tableName);//DbFunc集合
			List<DbFunc> dbFunc2=new ArrayList<DbFunc>();//模板方法集合
			List<DialogField> condition=new ArrayList<DialogField>();
			List<Map> mapList=new ArrayList<Map>();
			String fieldName="";
			String fieldType="";
			String comment="";
			for(DbFunc db:dbFunc1)
			{  
				Map<String,String> map=new HashMap<String, String>(); 
				String Func_name=db.getFunc_name();
				Long Func_type= db.getFunc_type();
				String  parameterType=db.getParameterType();
				if(Func_name.equals("add")||Func_name.equals("delById")||Func_name.equals("getAll")||Func_name.equals("update")||Func_name.equals("getById"))
				{
					dbFunc2.add(db);
				}
				else     //其余方法为自定义的方法
				{
					condition =db.getConditionList();
					for(DialogField diag:condition){
						fieldName=diag.getFieldName();
						fieldType=diag.getFieldType();
						comment=diag.getComment();
						map.put("fieldName",fieldName);
						map.put("fieldType",fieldType);
						map.put("comment",comment);
						//System.out.println("fieldName为："+fieldName);
						//System.out.println("fieldType为："+fieldType);
						//System.out.println("comment为："+comment);
					}
					map.put("db_Func_Name",Func_name);
					map.put("db_Func_Type",Func_type.toString());
					map.put("db_Func_ParameterType",parameterType);
					//System.out.println("db_Func_Name为："+Func_name);
					//System.out.println("db_Func_Type为："+Func_type.toString());
					//System.out.println("db_Func_ParameterType为："+parameterType);
					mapList.add(map);
				}
			}
			bpmFormTable.setMapList(mapList); 
			// 字段值来源为脚本计算时，脚本去掉换行处理
			for (BpmFormField field : fieldList) {
				if (bpmFormTable.getIsExternal() == 1) {//外部表变小写     是否外部表 0，本地表 1，外地表
					field.setFieldName(field.getFieldName().toLowerCase());
				}
				//下面开始处理单选，复选，下拉框的值
				String[] formDefIds = RequestUtil.getString(request, "formDefIds").split(",");
				try {
					for (String formDefId : formDefIds) {
						BpmFormDef bpmFormDef = bpmFormDefService.getById(Long.parseLong(formDefId));
						String options = CodeUtil.getDialogTags(getFormHtml(bpmFormDef, bpmFormTable, true), field);
						if (StringUtil.isNotEmpty(options)) {  //判断字符串非空
							field.setOptions(options);//设置下拉框
						}
					}
				} catch (Exception e) {
				}
				fields.add(field);
			}
			if (bpmFormTable.getIsExternal() == 1) {
				bpmFormTable.setPkField(bpmFormTable.getPkField().toLowerCase());//设置主键字段
				if (bpmFormTable.getIsMain() != 1) {//返回 是否主表 1-是 0-否
					bpmFormTable.setRelation(bpmFormTable.getRelation().toLowerCase());//设置外键字段
				}
			}
			bpmFormTable.setFieldList(fields);//设置字段信息
			if (bpmFormTable.getIsMain() != 1) {
				subtables.add(bpmFormTable);
			}
			list.add(bpmFormTable);
		}
		for (BpmFormTable subtable : subtables) {
			for (BpmFormTable table : list) {// 返回所属主表，该字段仅针对从表
				if ((table.getIsMain() == 1) && (table.getTableId().equals(subtable.getMainTableId()))) {
					table.getSubTableList().add(subtable);//子表列表
				}
			}
		}
		return list;
	}
	private List<String> genCode(String basePath, String system, List<BpmFormTable> tables, String[] templateIds, int override, String flowKey, BpmFormDef bpmFormDef) throws Exception {
		List<String> fileList = new ArrayList<String>();
		for (int i = 0; i < templateIds.length; i++) {
			Long templateId = Long.parseLong(templateIds[i]);
			SysCodeTemplate template = sysCodeTemplateService.getById(templateId);
			for (BpmFormTable table : tables) {
				Map<String, String> variables = table.getVariable();
				variables.put("system", system);
				String fileName = template.getFileName();
				String fileDir = template.getFileDir();//生成文件的所在目录    文件路径
				String path = basePath + File.separator + template.getFileDir();//路径
				
				int isSub = template.getIsSub();//是否子表需要生成的模版文件
				if (table.getIsMain() != 1) {
					if (isSub == 0) {
						continue;
					}
				}
				
				Map<String, Object> model = new HashMap<String, Object>();
				
			
				IParseHandler parseHandler = parseHandlerMap.get(template.getTemplateAlias());
				
				if(parseHandler!=null){//找到解释器就说明有html要解释
					String html = bpmFormDef.getHtml();
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("table", table);
					param.put("bpmFormDef",bpmFormDef);
					String str = parseHandler.parseHtml(html, param);
					model.put("html", str);
				}
				
				//其他参数
				model.put("table", table);
				model.put("system", system);
				if (StringUtil.isNotEmpty(flowKey)) {
					model.put("flowKey", flowKey);
				}
				String templateStr = template.getHtml();//返回 模版HTML
				String templatePath=SysCodeTemplateService.getRelateTemplatePath(template.getTemplateAlias());//根据路径获取模板
				String html = freemarkEngine.mergeTemplateIntoString(templatePath , model);//把指定的模板生成对应的字符串。
				String fileStr = path + File.separator + fileName;
				String filePath = StringUtil.replaceVariable(fileStr, variables);//根据变量替换模板。 
				addFile(filePath, html, override);
				String relativePath = StringUtil.replaceVariable(fileDir + File.separator + fileName, variables);
				fileList.add(relativePath);
			}
		}
		return fileList;
	}
	
	/**
	 * 生成jsp
	 * 王焕然
	 */
	@RequestMapping("genJsp")
	public void genJsp(HttpServletRequest request,HttpServletResponse response)throws Exception{
		// 文件相关参数

		String[] templateIds = request.getParameterValues("templateId");
		String flowKey = RequestUtil.getString(request, "defKey");
		String[] formDefIds = RequestUtil.getString(request, "formDefIds").split(",");
		// 自定义表相关
		String basePath = BASE_PATH + File.separator + "codegen";
		String basePathZip = BASE_PATH + File.separator + "codegen.zip";
		String system = RequestUtil.getString(request, "system");
		List<BpmFormTable> list = getTableModels(request);// 在获取过程中还解释
		try {
			for (String formDefId : formDefIds) {
				BpmFormDef bpmFormDef = bpmFormDefService.getById(Long.parseLong(formDefId));
				List<BpmFormTable> tables = new ArrayList<BpmFormTable>();
				Long tableId = bpmFormDef.getTableId();
				for (BpmFormTable model : list) {
					if (model.getTableId().equals(tableId) || model.getMainTableId().equals(tableId)) {
						tables.add(model);
					}
				}
				// 生成文件
				genCode(basePath, system, tables, templateIds, 1, flowKey, bpmFormDef);
			}
			// 先删除掉已经压缩的文件
			FileUtil.deleteFile(basePathZip);
			// 压缩后删除其他被压缩文件
			ZipUtil.zip(basePath, true);

			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Success, "自定义表生成代码成功"));
		} catch (Exception e) {
			e.printStackTrace();
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Fail, "自定义表生成代码失败:" + e.getMessage()));
		}
			
	}
	/**
	 * 返回流程设计的xml
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("designXml")
	public ModelAndView designXml(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "defId");
		BpmDefinition po = bpmDefinitionService.getById(id);
		String defXml = po.getDefXml();
		if (defXml.trim().startsWith(
				"<?xml version=\"1.0\" encoding=\"utf-8\"?>")) {
			defXml = defXml.replace(
					"<?xml version=\"1.0\" encoding=\"utf-8\"?>", "");
		}
		request.setAttribute("designXml", defXml);
		return getAutoView();
	}

	/**
	 * 取得流程定义分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked", "null" })
	@RequestMapping("list")
	@Action(description = "查看流程定义分页列表,含按分类查询所有流程")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "bpmDefinitionItem");
		Long typeId = RequestUtil.getLong(request, "typeId", 0);
		String typeName=null;
		typeName=RequestUtil.getString(request,"typeName");
		String documentGeneration=RequestUtil.getString(request,"documentGeneration");
		// System.out.println(typeId);
		// if (typeId != 0 && (typeId + "").length() > 1) {
		if (typeId > 1) 
		{
			GlobalType globalType = globalTypeService.getById(typeId);
			if (globalType != null) 
			{
				filter.getFilters().put("nodePath",
				globalType.getNodePath() + "%");
			}
		}
		// 增加流程分管授权查询判断
		Long userId = ContextUtil.getCurrentUserId();
		String isNeedRight = "";
		Map<String, AuthorizeRight> authorizeRightMap = null;
		if (!SysUser.isSuperAdmin()) {
			// if(!checkSuperAdmin()){
			isNeedRight = "yes";
			// 获得流程分管授权与用户相关的信息
			Map<String, Object> actRightMap = bpmDefAuthorizeService
					.getActRightByUserMap(userId,
							BPMDEFAUTHORIZE_RIGHT_TYPE.MANAGEMENT, true, true);
			// 获得流程分管授权与用户相关的信息集合的流程KEY
			String actRights = (String) actRightMap.get("authorizeIds");
			filter.addFilter("actRights", actRights);
			// 获得流程分管授权与用户相关的信息集合的流程权限内容
			authorizeRightMap = (Map<String, AuthorizeRight>) actRightMap
					.get("authorizeRightMap");
		}
		filter.addFilter("isNeedRight", isNeedRight);

		// 查询流程列表
		List<BpmDefinition> list = bpmDefinitionService.getAll(filter);

		// 把前面获得的流程分管授权的权限内容设置到流程管理列表
		if (authorizeRightMap != null) {
			for (BpmDefinition bpmDefinition : list) {
				bpmDefinition.setAuthorizeRight(authorizeRightMap
						.get(bpmDefinition.getDefKey()));
			}
		} else {
			// 如果需要所有权限的就直接虚拟一个有处理权限的对象
			AuthorizeRight authorizeRight = new AuthorizeRight();
			authorizeRight.setRightByAuthorizeType("Y",
					BPMDEFAUTHORIZE_RIGHT_TYPE.MANAGEMENT);
			for (BpmDefinition bpmDefinition : list) {
				bpmDefinition.setAuthorizeRight(authorizeRight);
			}
		}
		// 魏嫚
		for (BpmDefinition bpm : list) {
			Long typeid = bpm.getTypeId();
			if (typeid == null || typeid == 3L || typeid == 4L) {
				typeid = 2L;
			}
			GlobalType gt = globalTypeService.getById(typeid);
			int depth = gt.getDepth();
			while (depth != 1) {
				typeid = gt.getParentId();
				gt = globalTypeService.getById(typeid);
				depth = gt.getDepth();
			}
			bpm.setTypeId(typeid);
		}
		// 魏嫚 end
		
		ModelAndView mv = getAutoView().addObject("bpmDefinitionList", list)
										.addObject("typeName", typeName)
										.addObject("defIdForReturn", typeId)
										.addObject("documentGeneration", documentGeneration);
		return mv;
	}


	/**
	 * 取得B表流程定义分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("list_b")
	@Action(description = "查看流程定义分页列表,含按分类查询所有流程")
	public ModelAndView listB(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "bpmnetworkmapItem");
		Long typeId = RequestUtil.getLong(request, "typeId", 0);
		if (typeId != 0 && (typeId + "").length() > 1) {
			GlobalType globalType = globalTypeService.getById(typeId);
			if (globalType != null) {
				filter.getFilters().put("nodePath",
						globalType.getNodePath() + "%");
			}
		}

		// 增加流程分管授权查询判断
		Long userId = ContextUtil.getCurrentUserId();
		String isNeedRight = "";
		Map<String, AuthorizeRight> authorizeRightMap = null;
		// if(!checkSuperAdmin()){
		if (!SysUser.isSuperAdmin()) {
			isNeedRight = "yes";
			// 获得流程分管授权与用户相关的信息
			Map<String, Object> actRightMap = bpmDefAuthorizeService
					.getActRightByUserMap(userId,
							BPMDEFAUTHORIZE_RIGHT_TYPE.MANAGEMENT, true, true);
			// 获得流程分管授权与用户相关的信息集合的流程KEY
			String actRights = (String) actRightMap.get("authorizeIds");
			actRights = "";

			// yq modify
			List<Bpmnetworkmap> list_all = bpmnetworkmapService.getAll();
			for (int i = 0; i < list_all.size(); i++) {
				String key = list_all.get(i).getDefKey();
				actRights += "'" + key + "'";
				if (i != list_all.size() - 1)
					actRights += ",";
			}
			System.out.println("B表：" + actRights);

			// end
			filter.addFilter("actRights", actRights);
			// 获得流程分管授权与用户相关的信息集合的流程权限内容
			authorizeRightMap = (Map<String, AuthorizeRight>) actRightMap
					.get("authorizeRightMap");
		}
		filter.addFilter("isNeedRight", isNeedRight);

		// 查询流程列表
		List<Bpmnetworkmap> list = bpmnetworkmapService.getAll(filter);

		// 把前面获得的流程分管授权的权限内容设置到流程管理列表
		if (authorizeRightMap != null) {
			for (Bpmnetworkmap bpmDefinition : list) {
				bpmDefinition.setAuthorizeRight(authorizeRightMap
						.get(bpmDefinition.getDefKey()));
			}
		} else {
			// 如果需要所有权限的就直接虚拟一个有处理权限的对象
			AuthorizeRight authorizeRight = new AuthorizeRight();
			authorizeRight.setRightByAuthorizeType("Y",
					BPMDEFAUTHORIZE_RIGHT_TYPE.MANAGEMENT);
			for (Bpmnetworkmap bpmDefinition : list) {
				bpmDefinition.setAuthorizeRight(authorizeRight);
			}
		}

		/*
		 * AuthorizeRight authorizeRight = new AuthorizeRight();
		 * authorizeRight.setRightByAuthorizeType("Y",
		 * BPMDEFAUTHORIZE_RIGHT_TYPE.MANAGEMENT); for (Bpmnetworkmap
		 * bpmDefinition : list){
		 * bpmDefinition.setAuthorizeRight(authorizeRight);}
		 */

		ModelAndView mv = getAutoView().addObject("bpmnetworkmapList", list);
		return mv;
	}

	/*
	 * 根据defId查流程
	 */
	@RequestMapping("list_defid")
	public ModelAndView list_defid(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "bpmDefinitionItem");
		Long defId = RequestUtil.getLong(request, "defId", 0);
		System.out.println(defId);
		if (defId != 0 && (defId + "").length() > 1) {
			GlobalType globalType = globalTypeService.getById(defId);
			if (globalType != null) {
				filter.getFilters().put("nodePath",
						globalType.getNodePath() + "%");
			}
		}

		// 增加流程分管授权查询判断
		Long userId = ContextUtil.getCurrentUserId();
		String isNeedRight = "";
		Map<String, AuthorizeRight> authorizeRightMap = null;
		// if(!checkSuperAdmin()){
		if (!SysUser.isSuperAdmin()) {
			isNeedRight = "yes";
			// 获得流程分管授权与用户相关的信息
			Map<String, Object> actRightMap = bpmDefAuthorizeService
					.getActRightByUserMap(userId,
							BPMDEFAUTHORIZE_RIGHT_TYPE.MANAGEMENT, true, true);
			// 获得流程分管授权与用户相关的信息集合的流程KEY
			String actRights = (String) actRightMap.get("authorizeIds");
			filter.addFilter("actRights", actRights);
			// 获得流程分管授权与用户相关的信息集合的流程权限内容
			authorizeRightMap = (Map<String, AuthorizeRight>) actRightMap
					.get("authorizeRightMap");
		}
		filter.addFilter("isNeedRight", isNeedRight);

		// 查询流程列表
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);

		if (bpmDefinition != null) {
			// 把前面获得的流程分管授权的权限内容设置到流程管理列表
			if (authorizeRightMap != null) {
				bpmDefinition.setAuthorizeRight(authorizeRightMap
						.get(bpmDefinition.getDefKey()));

			} else {
				// 如果需要所有权限的就直接虚拟一个有处理权限的对象
				AuthorizeRight authorizeRight = new AuthorizeRight();
				authorizeRight.setRightByAuthorizeType("Y",
						BPMDEFAUTHORIZE_RIGHT_TYPE.MANAGEMENT);
				bpmDefinition.setAuthorizeRight(authorizeRight);

			}
		}
		ModelAndView mv = getAutoView().addObject("bpmDefinitionList",
				bpmDefinition);

		return mv;
	}

	/**
	 * 检查是否是超级管理员
	 * 
	 * @return boolean
	 * @exception
	 * @since 1.0.0
	 */
	// private boolean checkSuperAdmin(){
	// Collection<GrantedAuthority> auths= (Collection<GrantedAuthority>)
	// ContextUtil.getCurrentUser().getAuthorities();
	// 是否是超级管理员
	// if(auths!=null&&auths.size()>0&&auths.contains(SystemConst.ROLE_GRANT_SUPER)){
	// return true;
	// }
	// return false;
	// }

	/**
	 * 对读页面设置
	 * 
	 * @author 史欣慧
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexDefWritePage")
	@Action(description = "对读页面原子操作设置", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefWritePage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flex9将结果返回给flex");
		// 获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		String nodeId = RequestUtil.getString(request, "bpmDefinition.defId2");
		Long defId = Long.valueOf(RequestUtil.getString(request,
				"bpmDefinition.defId"));
		String nodeLabel = RequestUtil.getString(request,
				"bpmDefinition.nodeLabel");

		System.out.println("ip=" + ip);
		// 跳转地址
		// String newurl="http://" + ip +
		// ":8080/mas/platform/form/bpmFormDef/dialog.ht";
		String newurl = "http://" + ip
				+ ":8080/mas/PageLoadPath/PageloadCode/pageload/edit.ht?defId="
				+ defId + "&nodeId=" + nodeId;

		PrintWriter out = response.getWriter();
		out.print(newurl);
		System.out.println(newurl);
		return null;
	}

	/**
	 * flex动态展现
	 * 
	 * @author 史欣慧
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexDynamicShow")
	@Action(description = "flex动态展现", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDynamicShow(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flex13将结果返回给flex");

		Long defId = RequestUtil.getLong(request, "defId");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) AppUtil
				.getBean("jdbcTemplate");
		List<Map<String, Object>> node_dev_id = jdbcTemplate
				.queryForList("SELECT ID AS Node_ID,F_nodeid AS Node_IP,F_deviceTable AS Node_Table FROM w_device_node_set WHERE F_actdefid = (select ACTDEFID from bpmnetworkmap where DEFID = '"
						+ defId + "')");

		List<String> node_devId_set = new ArrayList<String>();
		List<String> load_value = new ArrayList<String>();
		List<String> node_color = new ArrayList<String>();
		List<String> node_flash = new ArrayList<String>();

		List<String> dev_set = new ArrayList<String>(); // 设备ID，用于判断两设备之间是否存在关联
		// 遍历图中所有node，并取出对应的设备ID
		for (int i = 0; i < node_dev_id.size(); i++) {
			HashMap<String, Object> test = (HashMap<String, Object>) (node_dev_id
					.get(i));
			String pat = "\\{Node_ID=(.+), Node_IP=(.+), Node_Table=(.+)\\}";
			Pattern pattern = Pattern.compile(pat);
			Matcher matcher = pattern.matcher(test.toString());
			matcher.find();
			String node_id = matcher.group(1);
			String node_ip = matcher.group(2);
			String node_table = matcher.group(3);

			HashMap<String, Object> result = (HashMap<String, Object>) (jdbcTemplate
					.queryForList("SELECT F_load_value AS Load_Value,F_node_color AS Node_Color, F_node_flash AS Node_Flash FROM w_device_action_show,w_dvice_load_rate WHERE w_device_action_show.F_node_dev_id = w_dvice_load_rate.F_node_dev_id AND w_dvice_load_rate.F_node_dev_id = '"
							+ node_id + "'")).get(0);
			pat = "\\{Load_Value=(.+), Node_Color=(.+), Node_Flash=(.+)\\}";
			pattern = Pattern.compile(pat);
			matcher = pattern.matcher(result.toString());
			matcher.find();
			node_devId_set.add(node_ip);
			load_value.add(matcher.group(1));
			node_color.add(matcher.group(2));
			node_flash.add(matcher.group(3));

			/*
			 * StringBuilder select_clause = new StringBuilder(" SELECT ");
			 * StringBuilder from_clause = new StringBuilder(" FROM ");
			 * StringBuilder where_clause = new StringBuilder(" WHERE 1=1 ");
			 * if(node_table.equals("w_kvm")){
			 * select_clause.append(" kvm.F_kvm_ID AS node_devId ");
			 * from_clause.append(" w_kvm AS kvm ");
			 * where_clause.append(" AND F_manage_IP = '"+node_ip+"'"); }else
			 * if(node_table.equals("w_device_virtual")){
			 * select_clause.append(" virtual.F_virtual_ID AS node_devId ");
			 * from_clause.append(" w_device_virtual AS virtual ");
			 * where_clause.append(" AND F_ip = '"+node_ip+"'"); }else
			 * if(node_table.equals("w_device_router")){
			 * select_clause.append(" router.F_dev_ID AS node_devId ");
			 * from_clause.append(" w_device_router AS router ");
			 * where_clause.append(" AND F_manage_IP = '"+node_ip+"'"); }else
			 * if(node_table.equals("w_firewall")){
			 * select_clause.append(" firewall.F_fireWall_ID AS node_devId ");
			 * from_clause.append(" w_firewall AS firewall ");
			 * where_clause.append(" AND F_manage_IP = '"+node_ip+"'"); }else
			 * if(node_table.equals("w_device_server")){
			 * select_clause.append(" server.F_server_ID AS node_devId ");
			 * from_clause.append(" w_device_server AS server ");
			 * where_clause.append(" AND F_manage_IP = '"+node_ip+"'"); }else
			 * if(node_table.equals("w_device_switch")){
			 * select_clause.append(" switch.F_switch_ID AS node_devId ");
			 * from_clause.append(" w_device_switch AS switch ");
			 * where_clause.append(" AND F_manage_IP = '"+node_ip+"'"); }
			 * 
			 * String dev_id =
			 * (jdbcTemplate.queryForList(select_clause.toString(
			 * )+from_clause.toString
			 * ()+where_clause.toString())).get(0).toString();
			 * dev_set.add(dev_id.substring(dev_id.indexOf("=")));
			 */
		}
		// 处理线信息
		List<Map<String, Object>> line_set = jdbcTemplate
				.queryForList("SELECT ID AS Line_ID,F_dev_ID AS Node_ID FROM w_devicerelationship WHERE F_actdefid = (select ACTDEFID from bpmnetworkmap where DEFID = '"
						+ defId + "')");
		List<String> line_id = new ArrayList<String>();
		List<String> line_color = new ArrayList<String>();
		List<String> line_info = new ArrayList<String>();
		List<String> line_use = new ArrayList<String>();
		List<String> node_set = new ArrayList<String>();
		for (int i = 0; i < line_set.size(); i++) {
			HashMap<String, Object> test = (HashMap<String, Object>) (line_set
					.get(i));
			String pat = "\\{Line_ID=(.+), Node_ID=(.+)\\}";
			Pattern pattern = Pattern.compile(pat);
			Matcher matcher = pattern.matcher(test.toString());
			matcher.find();
			String id = matcher.group(1);
			String node_id = matcher.group(2);
			line_id.add(id);
			node_set.add(node_id);

			String result = jdbcTemplate
					.queryForList(
							"SELECT F_line_color AS Line_Color, F_line_info AS Line_Info, F_load_use_rate AS Line_Use FROM w_line_action_show AS action, w_line_load_rate AS rate WHERE action.F_line_id = rate.F_line_id AND rate.F_line_id = '"
									+ id + "'").toString();
			pat = "Line_Color=(.+), Line_Info=(.+), Line_Use=(.+)";
			pattern = Pattern.compile(pat);
			result = result.replace("[{", "").replace("}]", "");
			matcher = pattern.matcher(result);
			matcher.find();
			line_color.add(matcher.group(1));
			line_info.add(matcher.group(2));
			line_use.add(matcher.group(3));
		}

		StringBuffer msg = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><flowdata>");
		for (int k = 0; k < node_devId_set.size() - 1; k++) {
			msg.append("<node id=\"" + node_devId_set.get(k)
					+ "\" yellowcolor=\"" + node_color.get(k)
					+ "\" animation=\"" + node_flash.get(k) + "\" load=\""
					+ load_value.get(k) + "\" line_id=\"" + line_id.get(k)
					+ "\" type=\"" + line_color.get(k) + "\" message=\""
					+ line_info.get(k) + "\" line_rate=\"" + line_use.get(k)
					+ "\" />");
			// msg.append("<node_devId>" + node_devId_set.get(k) +
			// "</node_devId>");
			// msg.append("<node_color>" + node_color.get(k) + "</node_color>");
			// msg.append("<node_flash>" + node_flash.get(k) + "</node_flash>");
			// msg.append("<load_value>" + load_value.get(k) + "</load_value>");
			// msg.append("</node>");
		}
		msg.append("<node id=\""
				+ node_devId_set.get(node_devId_set.size() - 1)
				+ "\" yellowcolor=\""
				+ node_color.get(node_devId_set.size() - 1) + "\" animation=\""
				+ node_flash.get(node_devId_set.size() - 1) + "\" load=\""
				+ load_value.get(node_devId_set.size() - 1) + "\" line_id=\""
				+ "null" + "\" type=\"" + "null" + "\" message=\"" + "null"
				+ "\" line_rate=\"" + "null" + "\" />");
		msg.append("</flowdata>");
		System.out.println(msg);
		/*
		 * StringBuffer msg = new StringBuffer(
		 * "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Result>");
		 * msg.append("<node_devId>" + node_dev_id + "</node_devId>");
		 * msg.append("<line_color>" + line_color + "</line_color>");
		 * msg.append("<line_info>" + line_info + "</line_info>");
		 * msg.append("<node_color>" + node_color + "</node_color>");
		 * msg.append("<node_flash>" + node_flash + "</node_flash>");
		 * msg.append("<load_value>" + load_value + "</load_value>");
		 * System.out.println("ip="+ip);
		 */

		PrintWriter out = response.getWriter();
		out.print(msg.toString());

		return null;
	}

	/**
	 * flex设备利用率图表显示
	 * 曲线图 
	 * @author 史欣慧
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexPictureShow")
	@Action(description = "flex设备利用率图表显示", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexPictureShow(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("flex14将结果返回给flex");
		String nodeId = RequestUtil.getString(request, "bpmDefinition.defId2");
		String nodeLabel = RequestUtil.getString(request, "bpmDefinition.nodeLabel");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@label"+nodeId);
		Long defId = Long.valueOf(RequestUtil.getString(request,
				"bpmDefinition.defId"));
		JdbcTemplate jdbcTemplate = (JdbcTemplate) AppUtil
				.getBean("jdbcTemplate");
		List<Map<String, Object>> result = jdbcTemplate
				.queryForList("SELECT F_active_start AS Start_Time, F_load_value AS Load_Value " +
						"FROM w_dvice_load_rate " +
						"WHERE F_node_dev_id = (SELECT ID " +
												"FROM w_device_node_set " +
												"WHERE F_actdefid = (SELECT ACTDEFID " +
																		"FROM bpmnetworkmap " +
																		"WHERE DEFID = '"+ defId + "'" +
																	") " +
												"AND F_nodeid = '" + nodeId + "'" +
												")");

		List<String> time_set = new ArrayList<String>();
		List<String> load_set = new ArrayList<String>();

		for (int i = 0; i < result.size(); i++) {
			HashMap<String, Object> test = (HashMap<String, Object>) (result
					.get(i));
			String pat = "\\{Start_Time=(.+), Load_Value=(.+)\\}";
			Pattern pattern = Pattern.compile(pat);
			Matcher matcher = pattern.matcher(test.toString());
			matcher.find();
			time_set.add(matcher.group(1));
			load_set.add(matcher.group(2));
		}
		StringBuffer msg = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><data>");
		for (int k = 0; k < time_set.size(); k++) {
			msg.append("<deal time=\" " + time_set.get(k) + "\" number1=\""
					+ load_set.get(k) + "\" />");
		}
		msg.append("</data>");
		System.out.println(msg.toString());
		PrintWriter out = response.getWriter();
		StringBuffer xml1 = new StringBuffer("<?xml version='1.0' encoding='UTF-8'?><data>" +
				"<node date='08:30'  serverTask1='0'  SwitchTask1='2'  routerTask1='15' kvmTask1='0'  firewallTask1='3' firewallTask2='2'/> " +
				"<node date='09:00'  serverTask1='5'  SwitchTask1='12' routerTask1='19' kvmTask1='5'  firewallTask1='7' firewallTask2='7'/> " +
				"<node date='09:30'  serverTask1='10' SwitchTask1='22' routerTask1='20'  kvmTask1='8'  firewallTask1='11' firewallTask2='10'/> " +
				"<node date='10:00'  serverTask1='15' SwitchTask1='15' routerTask1='24' kvmTask1='9'  firewallTask1='15' firewallTask2='13'/> " +
				"<node date='10:30'  serverTask1='20' SwitchTask1='17' routerTask1='26' kvmTask1='12' firewallTask1='19' firewallTask2='16'/> " +
				"<node date='11:00'  serverTask1='25' SwitchTask1='18' routerTask1='28' kvmTask1='17' firewallTask1='22' firewallTask2='21'/> " +
				"<node date='11:30'  serverTask1='30' SwitchTask1='25' routerTask1='33' kvmTask1='20' firewallTask1='26' firewallTask2='26'/> " +
				"<node date='12:00'  serverTask1='35'  SwitchTask1='30' routerTask1='37' kvmTask1='30' firewallTask1='28' firewallTask2='33'/> " +
				"<node date='12:30'  serverTask1='45' SwitchTask1='46' routerTask1='42' kvmTask1='48' firewallTask1='45' firewallTask2='44'/>" +
				"<node date='13:00'  serverTask1='40' SwitchTask1='38' routerTask1='28' kvmTask1='30' firewallTask1='40' firewallTask2='38'/> " +
				"<node date='13:30'  serverTask1='38' SwitchTask1='29' routerTask1='17' kvmTask1='29' firewallTask1='35' firewallTask2='35'/>" +
				"<node date='14:00'  serverTask1='37'  SwitchTask1='25' routerTask1='15'  kvmTask1='26' firewallTask1='30' firewallTask2='32'/> " +
		        "<node date='14:30'  serverTask1='33' SwitchTask1='15' routerTask1='14' kvmTask1='24' firewallTask1='28' firewallTask2='28'/> " +
				"<node date='15:00'  serverTask1='30' SwitchTask1='14'  routerTask1='12' kvmTask1='22' firewallTask1='25' firewallTask2='25'/> " +
				"<node date='15:30'  serverTask1='28' SwitchTask1='13' routerTask1='11' kvmTask1='19' firewallTask1='20' firewallTask2='21'/> " +
				"<node date='16:00'  serverTask1='26' SwitchTask1='11' routerTask1='9' kvmTask1='15' firewallTask1='15' firewallTask2='17'/> " +
				"<node date='16:30'  serverTask1='20' SwitchTask1='8' routerTask1='6' kvmTask1='13' firewallTask1='13' firewallTask2='13'/> " +
	        	"<node date='17:00'  serverTask1='10'  SwitchTask1='5' routerTask1='4' kvmTask1='5' firewallTask1='10' firewallTask2='9'/> " +
				"<node date='17:30'  serverTask1='5' SwitchTask1='2'  routerTask1='0' kvmTask1='4' firewallTask1='6' firewallTask2='4'/>");
		xml1.append("</data>");
		
		out.print(xml1);
		return null;
	}

	/**
	 * 查找我的流程分类列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping("myList")
	@Action(description = "查看我的流程定义分页列表")
	public ModelAndView myList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "bpmDefinitionItem");
		Long typeId = RequestUtil.getLong(request, "typeId", 0);
		// filter.addFilter("status", BpmDefinition.STATUS_ENABLED);
		// filter.addFilter("disableStatus", BpmDefinition.DISABLEStATUS_EN);

		// 增加流程分管授权的启动权限分配查询判断
		Long userId = ContextUtil.getCurrentUserId();
		String isNeedRight = "";
		Map<String, AuthorizeRight> authorizeRightMap = null;
		if (!SysUser.isSuperAdmin()) {
			// if(!checkSuperAdmin()){
			isNeedRight = "yes";
			// 获得流程分管授权与用户相关的信息
			Map<String, Object> actRightMap = bpmDefAuthorizeService
					.getActRightByUserMap(userId,
							BPMDEFAUTHORIZE_RIGHT_TYPE.START, false, false);
			// 获得流程分管授权与用户相关的信息集合的流程KEY
			String actRights = (String) actRightMap.get("authorizeIds");
			filter.addFilter("actRights", actRights);
		}
		filter.addFilter("isNeedRight", isNeedRight);

		List<BpmDefinition> list = bpmDefinitionService.getMyDefList(filter,
				typeId);

		/*
		 * // 旧时按授权表（BPM_DEF_RIGHTS）查询流程启动权限 List<BpmDefinition> list =
		 * bpmDefinitionService.getList(filter, typeId);
		 */

		ModelAndView mv = getAutoView().addObject("bpmDefinitionList", list);
		return mv;
	}

	/**
	 * 查看某一流程的所有历史版本
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("versions")
	@Action(description = "查看某一流程的所有历史版本")
	public ModelAndView versions(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		List<BpmDefinition> list = bpmDefinitionService
				.getAllHistoryVersions(defId);
		ModelAndView mv = getAutoView().addObject("bpmDefinitionList", list)
				.addObject("bpmDefinition", bpmDefinition);

		return mv;
	}

	/**
	 * 删除流程定义
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除流程定义", execOrder = ActionExecOrder.BEFORE, detail = "删除流程<#list StringUtils.split(defId,\",\") as item>"
			+ "【${SysAuditLinkService.getBpmDefinitionLink(Long.valueOf(item))}】"
			+ "</#list>的流程定义")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResultMessage resultMessage = null;
		String preUrl = RequestUtil.getPrePage(request);
		// 是否删除
		String isOnlyVersion = request.getParameter("isOnlyVersion");
		boolean onlyVersion = "true".equals(isOnlyVersion) ? onlyVersion = true
				: false;
		try {
			String lAryId = RequestUtil.getString(request, "defId");
			if (StringUtil.isEmpty(lAryId)) {
				resultMessage = new ResultMessage(ResultMessage.Success,
						"没有传入流程定义ID!");
			} else {
				String[] aryDefId = lAryId.split(",");
				for (String defId : aryDefId) {
					Long lDefId = Long.parseLong(defId);
					// 级联删除流程定义
					bpmDefinitionService.delDefbyDeployId(lDefId, onlyVersion);
				}
				resultMessage = new ResultMessage(ResultMessage.Success,
						"删除流程定义成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage(ResultMessage.Fail, "删除流程定义失败:"
					+ e.getMessage());
		}
		addMessage(resultMessage, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 删除流程定义B表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del_b")
	@Action(description = "删除流程定义", execOrder = ActionExecOrder.BEFORE, detail = "删除流程<#list StringUtils.split(defId,\",\") as item>"
			+ "【${SysAuditLinkService.getBpmDefinitionLink(Long.valueOf(item))}】"
			+ "</#list>的流程定义")
	public void del_b(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResultMessage resultMessage = null;
		String preUrl = RequestUtil.getPrePage(request);
		// 是否删除
		String isOnlyVersion = request.getParameter("isOnlyVersion");
		boolean onlyVersion = "true".equals(isOnlyVersion) ? onlyVersion = true
				: false;
		try {
			String lAryId = RequestUtil.getString(request, "defId");
			if (StringUtil.isEmpty(lAryId)) {
				resultMessage = new ResultMessage(ResultMessage.Success,
						"没有传入流程定义ID!");
			} else {
				String[] aryDefId = lAryId.split(",");
				for (String defId : aryDefId) {
					Long lDefId = Long.parseLong(defId);
					// 级联删除流程定义
					bpmnetworkmapService.delDefbyDeployId(lDefId, onlyVersion);
				}
				resultMessage = new ResultMessage(ResultMessage.Success,
						"删除流程定义成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage(ResultMessage.Fail, "删除流程定义失败:"
					+ e.getMessage());
		}
		addMessage(resultMessage, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 取得流程定义明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看流程定义明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "defId");
		String defKey = RequestUtil.getString(request, "defKey");
		BpmDefinition po = null;
		if (StringUtil.isNotEmpty(defKey)) {
			po = bpmDefinitionService.getMainByDefKey(defKey);
		} else {
			po = bpmDefinitionService.getById(id);
		}
		ModelAndView modelAndView = getAutoView();
		if (po.getTypeId() != null) {
			GlobalType globalType = globalTypeService.getById(po.getTypeId());
			modelAndView.addObject("globalType", globalType);
		}
		if (po.getActDeployId() != null) {
			String defXml = bpmService.getDefXmlByDeployId(po.getActDeployId()
					.toString());
			modelAndView.addObject("defXml", defXml);
		}

		return modelAndView.addObject("bpmDefinition", po);

	}

	/**
	 * 取得流程定义明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	@Action(description = "查看流程定义明细")
	public ModelAndView detail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "defId");
		BpmDefinition po = bpmDefinitionService.getById(id);
		ModelAndView modelAndView = getAutoView();
		// 保存查看明细之前的流程定义ID，用于返回
		long defIdForReturn = RequestUtil.getLong(request, "defIdForReturn", 0);
		if (defIdForReturn != 0) {
			modelAndView.addObject("defIdForReturn", defIdForReturn);
		}
		if (po.getTypeId() != null) {
			GlobalType globalType = globalTypeService.getById(po.getTypeId());
			modelAndView.addObject("globalType", globalType);
		}

		return modelAndView.addObject("bpmDefinition", po);
	}

	/**
	 * 流程节点设置
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("nodeSet")
	@Action(description = "流程节点设置")
	public ModelAndView nodeSet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long defId = RequestUtil.getLong(request, "defId");
		String parentActDefId = RequestUtil.getString(request,
				"parentActDefId", "");
		BpmDefinition po = bpmDefinitionService.getById(defId);
		ModelAndView modelAndView = getAutoView();
		if (po.getActDeployId() != null) {
			String defXml = bpmService.getDefXmlByDeployId(po.getActDeployId()
					.toString());
			modelAndView.addObject("defXml", defXml);
			ShapeMeta shapeMeta = BpmUtil.transGraph(defXml);
			modelAndView.addObject("shapeMeta", shapeMeta);
		}
		List<FlowNode> flowNodeList = NodeCache.getFirstNode(po.getActDefId());
		return modelAndView.addObject("bpmDefinition", po).addObject(
				"parentActDefId", parentActDefId).addObject("flowNodeList",
				flowNodeList);
	}
	@RequestMapping("htmlNodeSet")
	@Action(description = "流程节点设置")
	public ModelAndView htmlNodeSet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long defId = RequestUtil.getLong(request, "defId");
		String parentActDefId = RequestUtil.getString(request,
				"parentActDefId", "");
		BpmDefinition po = bpmDefinitionService.getById(defId);
		ModelAndView modelAndView = getAutoView();
		
		if (po.getActDeployId() != null) {
			String defXml = bpmService.getDefXmlByDeployId(po.getActDeployId()
					.toString());
			
			String jsonStr = "";
			
			modelAndView.addObject("json", jsonStr);
		}
		List<FlowNode> flowNodeList = NodeCache.getFirstNode(po.getActDefId());
		modelAndView.addObject("bpmDefinition", po).addObject(
				"parentActDefId", parentActDefId).addObject("flowNodeList",
				flowNodeList);
		return modelAndView;
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("userSet")
	@Action(description = "人员设置")
	public ModelAndView userSet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		String parentActDefId = RequestUtil.getString(request,
				"parentActDefId", "");
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		List<BpmNodeSet> setList = null;
		if (StringUtil.isEmpty(parentActDefId)) {
			setList = bpmNodeSetService.getByDefId(defId);
		} else {
			setList = bpmNodeSetService.getByDefId(defId, parentActDefId);
		}
		List<NodeUserMap> nodeUserMapList = new ArrayList<NodeUserMap>();
		ModelAndView modelView = getAutoView();
		// 单节点人员设置
		if (StringUtil.isNotEmpty(nodeId)) {
			BpmNodeSet nodeSet = null;
			if (StringUtil.isEmpty(parentActDefId)) {
				nodeSet = bpmNodeSetService.getByDefIdNodeId(defId, nodeId);
			} else {
				nodeSet = bpmNodeSetService.getByDefIdNodeId(defId, nodeId,
						parentActDefId);
			}
			List<BpmUserCondition> bpmUserConditionList = bpmUserConditionService
					.getBySetId(nodeSet.getSetId());
			NodeUserMap nodeUserMap = new NodeUserMap();
			nodeUserMap.setSetId(nodeSet.getSetId());
			nodeUserMap.setNodeId(nodeSet.getNodeId());
			nodeUserMap.setNodeName(nodeSet.getNodeName());
			nodeUserMap.setBpmUserConditionList(bpmUserConditionList);
			nodeUserMapList.add(nodeUserMap);
			modelView.addObject("nodeId", nodeId);
		}
		// 整个流程的人员设置
		else {
			// 获取每个节点的人员设置
			for (BpmNodeSet nodeSet : setList) {
				List<BpmUserCondition> bpmUserConditionList = bpmUserConditionService
						.getBySetId(nodeSet.getSetId());
				NodeUserMap nodeUserMap = new NodeUserMap();
				nodeUserMap.setSetId(nodeSet.getSetId());
				nodeUserMap.setNodeId(nodeSet.getNodeId());
				nodeUserMap.setNodeName(nodeSet.getNodeName());
				nodeUserMap.setBpmUserConditionList(bpmUserConditionList);
				nodeUserMapList.add(nodeUserMap);
			}
		}
		modelView.addObject("nodeSetList", setList);
		modelView.addObject("bpmDefinition", bpmDefinition);
		modelView.addObject("nodeUserMapList", nodeUserMapList);
		modelView.addObject("defId", defId);
		modelView.addObject("parentActDefId", parentActDefId);
		return modelView;
	}

	/**
	 * 跳转规则设置
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ruleSet")
	@Action(description = "跳转规则设置")
	public ModelAndView ruleSet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		// String nodeId=RequestUtil.getString(request, "nodeId");
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		List<BpmNodeSet> nodeSets = bpmNodeSetService.getByDefId(defId);
		Map<Long, List<BpmNodeRule>> nodeSetNodeRulesMap = getNodeRuleByNodeSet(nodeSets);
		ModelAndView modelView = getAutoView();
		for (BpmNodeSet nodeSet : nodeSets) {
			if (nodeSet.getNodeId() == null) {
				nodeSets.remove(nodeSet);
				break;
			}

		}
		modelView.addObject("bpmDefinition", bpmDefinition);
		modelView.addObject("nodeSets", nodeSets);
		modelView.addObject("nodeSetNodeRulesMap", nodeSetNodeRulesMap);
		modelView.addObject("defId", defId);
		return modelView;
	}

	/**
	 * 根据节点设置，取得对应的节点规则
	 * 
	 * @param nodeSets
	 * @return
	 */
	private Map<Long, List<BpmNodeRule>> getNodeRuleByNodeSet(
			List<BpmNodeSet> nodeSets) {
		Map<Long, List<BpmNodeRule>> nodeSetRuleMap = new HashMap<Long, List<BpmNodeRule>>();
		for (BpmNodeSet nodeSet : nodeSets) {
			List<BpmNodeRule> nodeRules = bpmNodeRuleService.getByDefIdNodeId(
					nodeSet.getActDefId(), nodeSet.getNodeId());
			nodeSetRuleMap.put(nodeSet.getSetId(), nodeRules);
		}
		return nodeSetRuleMap;
	}

	/**
	 * 绑定按钮与图的在线设置流程
	 * 
	 * @author zl
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// 设计
	@RequestMapping("designsher")
	@Action(description = "流程在线设计")
	public ModelAndView designsher(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 此处用于检测授权限制
		ClassLoadUtil.transform("", "", "");
		long defId = RequestUtil.getLong(request, "defId");
		SysAuditThreadLocalHolder.putParamerter("isExist", defId);
		if (defId > 0) {
			BpmDefinition po = bpmDefinitionService.getById(defId);
			request.setAttribute("proDefinition", po);
			request.setAttribute("subject", po.getSubject());
		} else {
			request.setAttribute("subject", "未命名");
		}
		Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
		// xml流程分类
		String sb = globalTypeService.getXmlByCatkey(GlobalType.CAT_FLOW);
		request.setAttribute("xmlRecord", sb);
		request.setAttribute("uId", uId); // 当前用户id
		request.setAttribute("defId", defId); // defId流程定义ids
		String[] x = sb.split("<folder id='10000006460068' label='事务图'>");
		System.out.println(x[0]);
		String sb1 = x[0] + "</folder>";
		System.out.println("sb1=" + sb1);
		String sb2 = "<folder id='0' label='全部'><folder id='10000006460068' label='事务图'>"
				+ x[1];
		System.out.println("sb2=" + sb2);
		System.out.println(x[1]);
		String a = request.getParameter("flagflex");
		if (a == null) {

			a = "2";// 流程图

			BpmDefinition po = bpmDefinitionService.getById(defId);
			String name = po.getTypeId().toString();
			System.out.println("name:=" + name);
			String t = "10000006460068";
			if (name.equals(t)) {

				a = "3";// 事务图

			}
		}
		if (a.equals("2")) {
			request.setAttribute("xmlRecord", sb1);
			System.out.println("2!!!!");
		} else {
			request.setAttribute("xmlRecord", sb2);
			System.out.println("3!!!!");
		}
		request.setAttribute("flagflex", a);
		return getAutoView();
	}

	/**
	 * flex，保存发布流程信息。
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping("flexDefSavesher")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefSavesher(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 新建流程定义时，若仅是保存流程定义时，则不发布流程。但下次更新时，若点发布新版时，则需要发布流程定义。
		// 新建流程定义时，若点了发布新版，则发布流程定义,下次再更新定义时，也是直接修改流程的定义。也可以重新发布新版本的流程
		// 是否发布新流程定义
		Boolean isDeploy = Boolean.parseBoolean(request.getParameter("deploy"));
		String actFlowDefXml = "";
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		ResultMessage resultMessage = null;
		BpmFormBang bpmformbang = null;
		List<BpmFormBang> bpmformbanglist = null;
		List<BpmFormBang> bpmformbanglist1 = null;
		boolean flag = true;
		String btnname = RequestUtil.getString(request, "btnname");

		try {
			// 获取flex中提交过来的数据构建流程定义对象。
			BpmDefinition bpmDefinition = getFromRequest(request);
			BpmDefinition bpmdefinition;
			SysAuditThreadLocalHolder.putParamerter("defKey", bpmDefinition
					.getDefKey());
			actFlowDefXml = BpmUtil.transform(bpmDefinition.getDefKey(),
					bpmDefinition.getSubject(), bpmDefinition.getDefXml());
			bpmDefinitionService.saveOrUpdate(bpmDefinition, isDeploy,
					actFlowDefXml);
			response.getWriter().print("success");
			bpmdefinitionsher = bpmDefinition;
			bpmdefinitionnew = bpmDefinition;
		} catch (Exception ex) {
			ex.printStackTrace();
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,
						"流程定义保存或发布失败:\r\n" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ex.getMessage();
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
		return null;
	}

	// ys
	/**
	 * 流程通过flex 在线设置流程
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("design")
	@Action(description = "流程在线设计")
	public ModelAndView design(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 此处用于检测授权限制
		ClassLoadUtil.transform("", "", "");
		long defId = 0L;
		defId = RequestUtil.getLong(request, "defId");
		String defKey = RequestUtil.getString(request, "defKey");
		String eventId = RequestUtil.getString(request, "buttonId");
		String typeName=RequestUtil.getString(request, "typeName");
		String markid=RequestUtil.getString(request, "markid");
		request.setAttribute("markid", markid);
		
		String checklv="";
		if(typeName.equals("flowChart1"))
		{
			typeName="flowChart";
			checklv="true";
		}
		else
			if(typeName.equals("operationChart1"))
			{
				typeName="operationChart";
				checklv="true";
			}else
			if(typeName.equals("transactionChart1"))
			{
				typeName="transactionChart";
				checklv="true";
			}
		if (defId == 0L && defKey != "" && defKey != null) {
			List<BpmDefinition> bo = bpmDefinitionService.getByDefKey(defKey);
			if (bo.size() > 0) {
				defId = bo.get(0).getDefId();
			}
		}
		// String typeId = RequestUtil.getString(request, "typeId");
		SysAuditThreadLocalHolder.putParamerter("isExist", defId);
		if (defId > 0) {
			BpmDefinition po = bpmDefinitionService.getById(defId);
			// long name=po.getTypeId();
			// System.out.println("name:="+name);
			request.setAttribute("proDefinition", po);
			request.setAttribute("subject", po.getSubject());
		} else if("lineChart".equals(typeName)){
			request.setAttribute("subject","");
		} else {
			request.setAttribute("subject", "未命名");
		}
		Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
		// xml流程分类
		//mz
		String sb=""; 
		request.setAttribute("uId", uId); // 当前用户id
		request.setAttribute("defId", defId); // defId流程定义id	
		if(typeName.equals("operationChart")){
			sb="operationChart";
		}else if(typeName.equals("transactionChart")){
			sb="transactionChart";
		}else if(typeName.equals("flowChart")){
			sb="flowChart";
		}else if(typeName.equals("lineChart")){				
			sb="lineChart";
		}
		String b= globalTypeService.getXmlByCatkey_f(GlobalType.CAT_FLOW,sb);// 流程图和事务图分类树
		request.setAttribute("xmlRecord",b);
		String designChart=request.getParameter("designChart");
		request.setAttribute("designChart", designChart);
		request.setAttribute("typeName", typeName);
		request.setAttribute("checklv", checklv);
		return getAutoView();

	}

	// ys end

	/**
	 * 王焕然的中转站
	 */
	@RequestMapping("sherlock")
	@Action(description = "查看流程定义分页列表,含按分类查询所有流程")
	public void sherlock(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String eventId = RequestUtil.getString(request, "buttonId");
		String defbId = RequestUtil.getString(request, "defbId");
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		Long btnId = RequestUtil.getLong(request, "btnId");
		Long defId = RequestUtil.getLong(request, "defId");
		Long xdefId = RequestUtil.getLong(request, "xdefId");
		Long defIdsher = RequestUtil.getLong(request, "defIdsher");
		String gai = RequestUtil.getString(request, "gai");
		Long bangId = RequestUtil.getLong(request, "bangId");
		String buttonname = RequestUtil.getString(request, "buttonname");
		BpmFormBang bpmformbang = new BpmFormBang();
		BpmFormBang bpmformbang1 = new BpmFormBang();
		BpmFormBang bpmformbang2 = new BpmFormBang();
		BpmDefinition bpmdefinition = null;
		RequestDispatcher rd;
		List<BpmFormBang> bpmformbanglist = null;
		List<BpmFormBang> bpmformbanglist1 = null;
		Eventgraphrelation eventgraphrelation = eventgraphrelationService
				.getByEventId(eventId);
		System.out.println("gai:" + gai);
		System.out.println("btnId:" + btnId);
		System.out.println("bangId:" + bangId);
		System.out.println("defId:" + defId);
		if (btnId > 0) {
			bpmformbang2 = bpmformbangservice.getById(btnId);
			bpmformbang2.setBtn_probability(gai);
			bpmformbangservice.update(bpmformbang2);
		}
		if (eventgraphrelation != null && defId > 0) {
			eventgraphrelation.setDefbID(Long.toString(defId));
			eventgraphrelationService.update(eventgraphrelation);
		}
		if (formDefId > 0) {
			if (bangId > 0) {
				bpmformbang = bpmformbangservice.getById(bangId);
				if (bpmformbang != null) {
					if (defId > 0)
						bpmformbang.setDefbId(Long.toString(defId));
					if (gai != null && gai != "")
						bpmformbang.setBtn_probability(gai);
					bpmformbang.setFormDefId(formDefId.toString());
					bpmformbangservice.update(bpmformbang);

				} else {
					bpmformbang1.setId(bangId);
					if (defId > 0)
						bpmformbang1.setDefbId(Long.toString(defId));
					bpmformbang1.setFormDefId(formDefId.toString());
					if (gai != null && gai != "")
						bpmformbang1.setBtn_probability(gai);
					bpmformbangservice.add(bpmformbang1);
				}
			}

		}
	}

	/**
	 * 王焕然的自定义按钮中转站
	 */
	@RequestMapping("sherlockb")
	@Action(description = "查看流程定义分页列表,含按分类查询所有流程")
	public void sherlockb(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String btnname = RequestUtil.getString(request, "btnname");
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		Long defId = RequestUtil.getLong(request, "defId");

		BpmFormBang bpmformbang = new BpmFormBang();
		BpmDefinition bpmdefinition = null;
		List<BpmFormBang> bpmformbanglist = null;
		if (btnname != null && btnname != "") {
			bpmformbanglist = bpmformbangservice.getByFormDefId(Long
					.toString(formDefId));
			if (bpmformbanglist.size() != 0) {
				if (defId > 0) {
					bpmformbanglist.get(0).setDefbId(Long.toString(defId));
				}
				bpmformbanglist.get(0).setBtn_name(btnname);
				bpmformbangservice.update(bpmformbanglist.get(0));
			} else {
				if (defId > 0) {
					bpmformbang.setDefbId(Long.toString(defId));
				}
				bpmformbang.setId(UniqueIdUtil.genId());
				bpmformbang.setFormDefId(formDefId.toString());
				bpmformbang.setBtn_name(btnname);
				bpmformbangservice.add(bpmformbang);
			}
		}
	}
	@RequestMapping("gettransaction")
	@Action(description = "绑定事件图 王焕然")
	public ModelAndView gettransaction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		ModelAndView mv = this.getAutoView();
		String formsubject = RequestUtil.getString(request, "formsubject");
		BpmDefinition bpmdefinition = null;
		//bpmdefinitionnew = null;
		if(bpmdefinition!=null){
			mv.addObject("defId", bpmdefinition.getDefId().toString());
		}
		mv.addObject("formDefId", formDefId).addObject("formsubject",formsubject);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("getvarlist")
	@Action(description = "获取流程变量 王焕然")
	public String getvarlist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		JSONObject jo = new JSONObject();
		List<BpmDefVar> defvar = new ArrayList<BpmDefVar>();
		defvar = bpmDefVarService.getVarsByFlowDefId(defId);
		JSONArray ja = new JSONArray();
	    for(int i = 0;i<defvar.size();i++){
	    	jo.put("varId",defvar.get(i).getVarId());
	    	jo.put("varname",defvar.get(i).getVarName());
	    	jo.put("vartype",defvar.get(i).getVarType());
	    	ja.put(jo);
	    }
		return ja.toString();
	}
	@ResponseBody
	@RequestMapping("getvarlist2")
	@Action(description = "获取流程变量 王焕然")
	public String getvarlist2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = Long.valueOf(RequestUtil.getString(request, "defId"));
		JSONObject jo = new JSONObject();
		List<BpmDefVar> defvar = new ArrayList<BpmDefVar>();
		defvar = bpmDefVarService.getVarsByFlowDefId(defId);
		JSONArray ja = new JSONArray();
	    for(int i = 0;i<defvar.size();i++){
	    	jo.put("varId",defvar.get(i).getVarId());
	    	jo.put("varname",defvar.get(i).getVarName());
	    	jo.put("vartype",defvar.get(i).getVarType());
	    	ja.put(jo);
	    }
		return ja.toString();
	}
	
	@ResponseBody
	@RequestMapping("getvarliststr")
	@Action(description = "获取流程变量 王焕然")
	public String getvarliststr(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String defId = RequestUtil.getString(request, "defId");
		System.out.println("finnnnnnnn+"+defId);
		JSONObject jo = new JSONObject();
		List<BpmDefVar> defvar = new ArrayList<BpmDefVar>();
		defvar = bpmDefVarService.getVarsByFlowDefId(Long.parseLong(defId));
		JSONArray ja = new JSONArray();
	    for(int i = 0;i<defvar.size();i++){
	    	jo.put("varId",defvar.get(i).getVarId());
	    	jo.put("varname",defvar.get(i).getVarName());
	    	jo.put("vartype",defvar.get(i).getVarType());
	    	ja.put(jo);
	    }
	    System.out.println(ja.toString());
		return ja.toString();
	}
	
	
	@ResponseBody
	@RequestMapping("eventclose")
	@Action(description = "清空全局变量 王焕然")
	public Boolean eventclose(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		bpmdefinitionnew = null;
		return true;
	}
	@ResponseBody
	@RequestMapping("eventrefresh")
	@Action(description = "绑定事件图 王焕然")
	public List<String> eventrefresh(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<String> result = new ArrayList<String>();
		for(int i = 0;i<3;i++){
			result.add("");
		}
		BpmDefinition bpmdefinition = null;
		bpmdefinition = bpmdefinitionnew;
		if(bpmdefinition!=null){
			result.set(0, bpmdefinition.getDefId().toString());
			result.set(1,bpmdefinition.getSubject());
			result.set(2,bpmdefinition.getDefKey());
			return result;
		}
		return result;
	}
	
	@RequestMapping("sherlockbutton")
	@Action(description = "查看流程定义分页列表,含按分类查询所有流程")
	public ModelAndView sherlockbutton(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mv = this.getAutoView();
		BpmFormBang bpmformbang = new BpmFormBang();
		List<BpmFormBang> bpmformbanglist;
		BpmDefinition bpmdefinition;
		boolean flag = true;
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		String btnname = RequestUtil.getString(request, "buttonname");
		if (bpmdefinitionsher != null) {
			System.out.println("bpmdefinitionsher:"
					+ bpmdefinitionsher.getDefKey());
			mv.addObject("defKeysher", bpmdefinitionsher.getDefKey());
			mv.addObject("defsubjectsher", bpmdefinitionsher.getSubject());
		}

		mv.addObject("bpmdefinitionsher", bpmdefinitionsher);
		/*
		 * if(formDefId>0&&btnname!=null&&btnname!=""){ bpmformbanglist =
		 * bpmformbangservice.getByFormDefId(Long.toString(formDefId));
		 * 
		 * if(bpmformbanglist.size()>0){ for(int
		 * i=0;i<bpmformbanglist.size();i++){
		 * if(btnname.equals(bpmformbanglist.get(i).getBtn_name())){
		 * if(bpmformbanglist
		 * .get(i).getDefbId()!=null&&bpmformbanglist.get(i).getDefbId()!=""){
		 * bpmdefinition =
		 * bpmDefinitionService.getById((Long.parseLong(bpmformbanglist
		 * .get(i).getDefbId())));
		 * mv.addObject("gai",bpmformbanglist.get(i).getBtn_probability());
		 * mv.addObject("bpmdefinition",bpmdefinition); }else{
		 * mv.addObject("gai",bpmformbanglist.get(i).getBtn_probability()); }
		 * flag = false; break; } } if(flag==true){
		 * bpmformbanglist.get(0).setBtn_name(btnname);
		 * bpmformbangservice.update(bpmformbanglist.get(0));
		 * mv.addObject("gai",bpmformbanglist.get(0).getBtn_probability()); }
		 * }else{ bpmformbang.setId(UniqueIdUtil.genId());
		 * bpmformbang.setFormDefId(formDefId.toString());
		 * bpmformbang.setBtn_name(btnname);
		 * bpmformbangservice.add(bpmformbang); } }
		 * mv.addObject("btnname",btnname) .addObject("formDefId",formDefId);
		 */
		return mv;
	}

	@RequestMapping("disbang")
	@Action(description = "查看流程定义分页列表,含按分类查询所有流程")
	public void disbang(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BpmFormBang bpmformbang = new BpmFormBang();
		List<BpmFormBang> bpmformbanglist = null;
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		String btnname = RequestUtil.getString(request, "btnname");
		Long bangId = RequestUtil.getLong(request, "bangId");
		String eventId = RequestUtil.getString(request, "buttonId");

		if (formDefId > 0) {
			bpmformbanglist = bpmformbangservice.getByFormDefId(formDefId
					.toString());
			for (int i = 0; i < bpmformbanglist.size(); i++) {
				if (bpmformbanglist.get(i).getBtn_name().equals(btnname)) {
					bpmformbanglist.get(i).setBtn_name(null);
					bpmformbanglist.get(i).setDefbId(null);
					bpmformbangservice.update(bpmformbanglist.get(i));
				}
			}
		}

		if (bangId > 0) {
			bpmformbang = bpmformbangservice.getById(bangId);
			bpmformbang.setDefbId(null);
			bpmformbang.setBtn_name(null);
			bpmformbangservice.update(bpmformbang);
		}
		if (eventId != null && eventId != "") {
			Eventgraphrelation eventgraphrelation = eventgraphrelationService
					.getByEventId(eventId);
			eventgraphrelation.setDefbID(null);
			eventgraphrelationService.update(eventgraphrelation);
		}

	}

	@RequestMapping("sherlockform")
	@Action(description = "查看流程定义分页列表,含按分类查询所有流程")
	public ModelAndView sherlockform(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		String formsubject = RequestUtil.getString(request, "formsubject");
		List<BpmFormBang> bpmformbanglist;
		Long bangId = 0L;
		BpmFormBang bpmformbang = new BpmFormBang();
		BpmDefinition bpmdefinition = new BpmDefinition();
		ModelAndView mv = this.getAutoView();

		bpmformbanglist = bpmformbangservice.getByFormDefId(Long
				.toString(formDefId));

		if (bpmformbanglist.size() != 0) {
			if ((bpmformbanglist.get(0).getDefbId()) != null
					&& (bpmformbanglist.get(0).getDefbId()) != ""
					&& (bpmformbanglist.get(0).getDefbId()) != "0") {
				bpmdefinition = bpmDefinitionService.getById(Long
						.parseLong((bpmformbanglist.get(0).getDefbId())));
				if (bpmdefinition == null) {
					mv.addObject("probability", bpmformbanglist.get(0)
							.getBtn_probability());
					mv.addObject("bangId", bpmformbanglist.get(0).getId());
				} else {
					mv.addObject("probability", bpmformbanglist.get(0)
							.getBtn_probability());
					mv.addObject("bpmdefinition", bpmdefinition).addObject(
							"bangId", bpmformbanglist.get(0).getId());
				}
			} else {
				mv.addObject("probability", bpmformbanglist.get(0)
						.getBtn_probability());
				mv.addObject("bangId", bpmformbanglist.get(0).getId());
			}
		} else {
			bangId = UniqueIdUtil.genId();
			mv.addObject("bangId", bangId);
		}
		mv.addObject("formDefId", formDefId).addObject("formsubject",
				formsubject);
		return mv;
	}

	/**
	 * 绑定按钮与图的在线设置流程
	 * 
	 * @author zl
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// 设计
	@RequestMapping("designBtn")
	@Action(description = "流程在线设计")
	public ModelAndView designBtn(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 此处用于检测授权限制
		ClassLoadUtil.transform("", "", "");
		long defId = RequestUtil.getLong(request, "defId");
		Long sdefId = RequestUtil.getLong(request, "sdefId");
		long formDefId = RequestUtil.getLong(request, "formDefId");
		String btnname = RequestUtil.getString(request, "btnname");
		String eventId = RequestUtil.getString(request, "buttonId");

		List<BpmFormBang> bpmformbanglist = null;
		SysAuditThreadLocalHolder.putParamerter("isExist", defId);
		Eventgraphrelation eventgraphrelation = null;
		BpmFormBang bpmformbang = null;
		BpmFormBang bpmformbang1 = null;
		List<BpmFormBang> bpmformbanglist1 = null;
		if (eventId != null && eventId != "") {
			eventgraphrelation = eventgraphrelationService
					.getByEventId(eventId);
		}
		if (eventgraphrelation != null) {

			/*
			 * eventgraphrelation.setDefbID(Long.toString(sdefId));
			 * eventgraphrelationService.update(eventgraphrelation);
			 */
			request.setAttribute("eventId", eventId);
		}
		if (btnname != null && btnname != "") {
			bpmformbanglist1 = bpmformbangservice.getByBtnName(btnname);
			if (bpmformbanglist1.size() == 0) {

				bpmformbang1 = new BpmFormBang();
				bpmformbang1.setId(UniqueIdUtil.genId());
				bpmformbang1.setBtn_name(btnname);
				bpmformbangservice.add(bpmformbang1);
				request.setAttribute("subject", "未命名");

				Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
				// xml流程分类
				String sb = globalTypeService
						.getXmlByCatkey(GlobalType.CAT_FLOW);
				request.setAttribute("xmlRecord", sb);
				request.setAttribute("uId", uId); // 当前用户id
				request.setAttribute("defId", defId); // defId流程定义id
				request.setAttribute("formDefId", formDefId); // formDefId流程定义id
				request.setAttribute("btnname", btnname);
				return getAutoView();
			} else {
				if (bpmformbanglist1.get(0).getDefbId() != null) {

					BpmDefinition po = bpmDefinitionService.getById(Long
							.parseLong(bpmformbanglist1.get(0).getDefbId()));
					request.setAttribute("proDefinition", po);
					request.setAttribute("subject", po.getSubject());// 流程标题

					Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
					// xml流程分类
					String sb = globalTypeService
							.getXmlByCatkey(GlobalType.CAT_FLOW);
					request.setAttribute("xmlRecord", sb);
					request.setAttribute("uId", uId); // 当前用户id
					request.setAttribute("defId", bpmformbanglist1.get(0)
							.getDefbId()); // defId流程定义id
					request.setAttribute("formDefId", formDefId); // formDefId流程定义id
					request.setAttribute("btnname", btnname);
					return getAutoView();
				} else {
					request.setAttribute("subject", "未命名");

					Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
					// xml流程分类
					String sb = globalTypeService
							.getXmlByCatkey(GlobalType.CAT_FLOW);
					request.setAttribute("xmlRecord", sb);
					request.setAttribute("uId", uId); // 当前用户id
					request.setAttribute("defId", defId); // defId流程定义id
					request.setAttribute("formDefId", formDefId); // formDefId流程定义id
					request.setAttribute("btnname", btnname);
					return getAutoView();
				}
			}
		} else if (formDefId > 0) {
			bpmformbanglist = bpmformbangservice.getByFormDefId(Long
					.toString(formDefId));

			if (bpmformbanglist.size() == 0) {
				bpmformbang = new BpmFormBang();
				bpmformbang.setId(UniqueIdUtil.genId());
				bpmformbang.setFormDefId(Long.toString(formDefId));
				bpmformbangservice.add(bpmformbang);
				request.setAttribute("subject", "未命名");

				Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
				// xml流程分类
				String sb = globalTypeService
						.getXmlByCatkey(GlobalType.CAT_FLOW);
				request.setAttribute("xmlRecord", sb);
				request.setAttribute("uId", uId); // 当前用户id
				request.setAttribute("defId", defId); // defId流程定义id
				request.setAttribute("formDefId", formDefId); // formDefId流程定义id
				request.setAttribute("btnname", btnname);
				return getAutoView();
			} else {

				if (bpmformbanglist.get(0).getDefbId() != null) {
					BpmDefinition po = bpmDefinitionService.getById(Long
							.parseLong(bpmformbanglist.get(0).getDefbId()));
					request.setAttribute("proDefinition", po);
					request.setAttribute("subject", po.getSubject());// 流程标题

					Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
					// xml流程分类
					String sb = globalTypeService
							.getXmlByCatkey(GlobalType.CAT_FLOW);
					request.setAttribute("xmlRecord", sb);
					request.setAttribute("uId", uId); // 当前用户id
					request.setAttribute("defId", bpmformbanglist.get(0)
							.getDefbId()); // defId流程定义id
					request.setAttribute("formDefId", formDefId); // formDefId流程定义id
					request.setAttribute("btnname", btnname);
					return getAutoView();
				} else {
					request.setAttribute("subject", "未命名");
					Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
					// xml流程分类
					String sb = globalTypeService
							.getXmlByCatkey(GlobalType.CAT_FLOW);
					request.setAttribute("xmlRecord", sb);
					request.setAttribute("uId", uId); // 当前用户id
					request.setAttribute("defId", defId); // defId流程定义id
					request.setAttribute("formDefId", formDefId); // formDefId流程定义id
					// request.setAttribute("btnname", btnname);
					return getAutoView();
				}
			}
		} else {

			if (defId > 0 && formDefId <= 0) {

				BpmDefinition po = bpmDefinitionService.getById(defId);
				request.setAttribute("proDefinition", po);
				request.setAttribute("subject", po.getSubject());// 流程标题
			} else {
				request.setAttribute("subject", "未命名");
			}
			Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
			// xml流程分类
			String sb = globalTypeService.getXmlByCatkey(GlobalType.CAT_FLOW);
			request.setAttribute("xmlRecord", sb);
			request.setAttribute("uId", uId); // 当前用户id
			request.setAttribute("defId", defId); // defId流程定义id
			request.setAttribute("formDefId", formDefId); // formDefId流程定义id
			request.setAttribute("btnname", btnname);
			String[] x = sb.split("<folder id='10000006460068' label='事务图'>");
			System.out.println(x[0]);
			String sb1 = x[0] + "</folder>";
			System.out.println("sb1=" + sb1);
			String sb2 = "<folder id='0' label='全部'><folder id='10000006460068' label='事务图'>"
					+ x[1];
			System.out.println("sb2=" + sb2);
			System.out.println(x[1]);
			String a = request.getParameter("flagflex");
			if (a == null) {

				a = "2";// 流程图

				BpmDefinition po = bpmDefinitionService.getById(defId);
				String name = po.getTypeId().toString();
				System.out.println("name:=" + name);
				String t = "10000006460068";
				if (name.equals(t)) {

					a = "3";// 事务图

				}
			}
			if (a.equals("2")) {
				request.setAttribute("xmlRecord", sb1);
				System.out.println("2!!!!");
			} else {
				request.setAttribute("xmlRecord", sb2);
				System.out.println("3!!!!");
			}
			request.setAttribute("flagflex", a);
			return getAutoView();
		}
	}

	/**
	 * 绑定按钮与图的在线设置流程
	 * 
	 * @author zl
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	// 设计
	@RequestMapping("designBtnx")
	@Action(description = "流程在线设计")
	public ModelAndView designBtnx(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 此处用于检测授权限制
		ClassLoadUtil.transform("", "", "");
		long defId = RequestUtil.getLong(request, "defId");
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		String btnname = RequestUtil.getString(request, "btnname");
		Long bangId = RequestUtil.getLong(request, "bangId");

		BpmFormBang bpmformbang = null;
		List<BpmFormBang> bpmformbanglist = null;
		// String typeId = RequestUtil.getString(request, "typeId");
		SysAuditThreadLocalHolder.putParamerter("isExist", defId);
		if (defId > 0) {
			BpmDefinition po = bpmDefinitionService.getById(defId);
			request.setAttribute("proDefinition", po);
			request.setAttribute("subject", po.getSubject());
		} else {
			request.setAttribute("subject", "未命名");
		}
		Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
		// xml流程分类
		String sb = globalTypeService.getXmlByCatkey(GlobalType.CAT_FLOW);
		request.setAttribute("xmlRecord", sb);
		request.setAttribute("uId", uId); // 当前用户id
		request.setAttribute("defId", defId); // defId流程定义id
		request.setAttribute("btnname", btnname);
		request.setAttribute("formDefId", formDefId);
		request.setAttribute("bangId", bangId);
		String[] x = sb.split("<folder id='10000006460068' label='事务图'>");
		System.out.println(x[0]);
		String sb1 = x[0] + "</folder>";
		System.out.println("sb1=" + sb1);
		String sb2 = "<folder id='0' label='全部'><folder id='10000006460068' label='事务图'>"
				+ x[1];
		System.out.println("sb2=" + sb2);
		System.out.println(x[1]);
		String a = request.getParameter("flagflex");
		if (a == null) {

			a = "2";// 流程图

			BpmDefinition po = bpmDefinitionService.getById(defId);
			String name = po.getTypeId().toString();
			System.out.println("name:=" + name);
			String t = "10000006460068";
			if (name.equals(t)) {

				a = "3";// 事务图

			}
		}
		if (a.equals("2")) {
			request.setAttribute("xmlRecord", sb1);
			System.out.println("2!!!!");
		} else {
			request.setAttribute("xmlRecord", sb2);
			System.out.println("3!!!!");
		}
		request.setAttribute("flagflex", a);
		return getAutoView();
	}

	/**
	 * 流程通过flex 查看拓扑图
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("design1")
	@Action(description = "流程在线设计")
	public ModelAndView design1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("design1");
		// 此处用于检测授权限制
		ClassLoadUtil.transform("", "", "");
		long defId = RequestUtil.getLong(request, "defId");
		// String typeId = RequestUtil.getString(request, "typeId");
		SysAuditThreadLocalHolder.putParamerter("isExist", defId);
		if (defId > 0) {
			Bpmnetworkmap po = bpmnetworkmapService.getById(defId);
			request.setAttribute("proDefinition", po);
			request.setAttribute("subject", po.getSubject());
		} else {
			request.setAttribute("subject", "未命名");
		}
		Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
		// xml流程分类
		String sb = globalTypeService.getXmlByCatkey(GlobalType.CAT_NETWORKMAP);
		request.setAttribute("xmlRecord", sb);
		request.setAttribute("uId", uId); // 当前用户id
		request.setAttribute("defId", defId); // defId流程定义id
		String a = "1";
		request.setAttribute("flagflex", a);
		return getAutoView();
	}

	/**
	 * 流程通过flex 绑定信息拓扑图
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("design2")
	@Action(description = "流程在线设计")
	public ModelAndView design2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("design2");
		// 此处用于检测授权限制
		ClassLoadUtil.transform("", "", "");
		long defId = RequestUtil.getLong(request, "defId");
		// String typeId = RequestUtil.getString(request, "typeId");
		SysAuditThreadLocalHolder.putParamerter("isExist", defId);
		if (defId > 0) {
			Bpmnetworkmap po = bpmnetworkmapService.getById(defId);
			request.setAttribute("proDefinition", po);
			request.setAttribute("subject", po.getSubject());
		} else {
			request.setAttribute("subject", "未命名");
		}
		Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
		// xml流程分类
		String sb = globalTypeService.getXmlByCatkey(GlobalType.CAT_NETWORKMAP);
		request.setAttribute("xmlRecord", sb);
		request.setAttribute("uId", uId); // 当前用户id
		request.setAttribute("defId", defId); // defId流程定义id
		String a = "1";
		request.setAttribute("flagflex", a);
		return getAutoView();
	}

	/**
	 * 流程通过flex 展现拓扑图
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("design3")
	@Action(description = "流程在线设计")
	public ModelAndView design3(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("design3");
		// 此处用于检测授权限制
		ClassLoadUtil.transform("", "", "");
		long defId = RequestUtil.getLong(request, "defId");
		// String typeId = RequestUtil.getString(request, "typeId");
		SysAuditThreadLocalHolder.putParamerter("isExist", defId);
		if (defId > 0) {
			Bpmnetworkmap po = bpmnetworkmapService.getById(defId);
			request.setAttribute("proDefinition", po);
			request.setAttribute("subject", po.getSubject());
		} else {
			request.setAttribute("subject", "未命名");
		}
		Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
		// xml流程分类
		String sb = globalTypeService.getXmlByCatkey(GlobalType.CAT_NETWORKMAP);
		request.setAttribute("xmlRecord", sb);
		request.setAttribute("uId", uId); // 当前用户id
		request.setAttribute("defId", defId); // defId流程定义id
		String a = "1";
		request.setAttribute("flagflex", a);
		return getAutoView();
	}

	/*
	 * 
	 * B表在线设计
	 */
	@RequestMapping("design_b")
	@Action(description = "流程在线设计")
	public ModelAndView design_b(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("design_b");
		// 此处用于检测授权限制
		ClassLoadUtil.transform("", "", "");
		long defId = RequestUtil.getLong(request, "defId");
		// String typeId = RequestUtil.getString(request, "typeId");
		SysAuditThreadLocalHolder.putParamerter("isExist", defId);
		if (defId > 0) {
			// yq
			Bpmnetworkmap po = bpmnetworkmapService.getById(defId);
			request.setAttribute("proDefinition", po);
			request.setAttribute("subject", po.getSubject());
		} else {
			request.setAttribute("subject", "未命名");
		}
		Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
		// xml流程分类
		String sb = globalTypeService.getXmlByCatkey(GlobalType.CAT_NETWORKMAP);
		request.setAttribute("xmlRecord", sb);
		request.setAttribute("uId", uId); // 当前用户id
		request.setAttribute("defId", defId); // defId流程定义id
		String a = "1";
		request.setAttribute("flagflex", a);
		return getAutoView();
	}

	// end

	/**
	 * flex，保存发布流程信息。
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping("flexDefSave")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public String flexDefSave(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 新建流程定义时，若仅是保存流程定义时，则不发布流程。但下次更新时，若点发布新版时，则需要发布流程定义。
		// 新建流程定义时，若点了发布新版，则发布流程定义,下次再更新定义时，也是直接修改流程的定义。也可以重新发布新版本的流程
		// 是否发布新流程定义
		Boolean isDeploy = Boolean.parseBoolean(request.getParameter("deploy"));
		String actFlowDefXml = "";
		String defIdstr="";
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		String bangId = RequestUtil.getString(request, "bangId");
		ResultMessage resultMessage = null;
		BpmFormBang bpmformbang = null;
		BpmFormBang bpmformbang1 = null;
		List<BpmFormBang> bpmformbanglist1 = null;
		boolean flag = true;
		String btnname = RequestUtil.getString(request, "btnname");

		try {
			// 获取flex中提交过来的数据构建流程定义对象。
			BpmDefinition bpmDefinition = getFromRequest(request);
			BpmDefinition bpmdefinition;
			SysAuditThreadLocalHolder.putParamerter("defKey", bpmDefinition
					.getDefKey());
			actFlowDefXml = BpmUtil.transform(bpmDefinition.getDefKey(),
					bpmDefinition.getSubject(), bpmDefinition.getDefXml());
			bpmDefinitionService.saveOrUpdate(bpmDefinition, isDeploy,
					actFlowDefXml);
			bpmdefinitionnew = bpmDefinition;
			response.getWriter().print("success");
			System.out.println("saveByNode:bangId:" + bangId);
			if (bangId != "" && bangId != null) {
				if (Long.parseLong(bangId) > 0) {
					bpmformbang1 = bpmformbangservice.getById(Long
							.parseLong(bangId));

					if (bpmformbang1 != null) {
						System.out.println("wokao!:"
								+ bpmDefinition.getDefId().toString());
						bpmformbang1.setDefbId(bpmDefinition.getDefId()
								.toString());
						bpmformbangservice.update(bpmformbang1);
					} else {
						bpmformbang = new BpmFormBang();
						bpmformbang.setId(UniqueIdUtil.genId());
						bpmformbang.setDefbId((bpmDefinition.getDefId())
								.toString());
						bpmformbangservice.add(bpmformbang);
					}
				}
			}
			if (formDefId > 0 && btnname != null && btnname != "") {
				bpmformbanglist1 = bpmformbangservice.getByFormDefId(Long
						.toString(formDefId));

				if (bpmformbanglist1.size() > 0) {
					for (int i = 0; i < bpmformbanglist1.size(); i++) {

						if (btnname.equals(bpmformbanglist1.get(i)
								.getBtn_name())) {
							bpmformbanglist1.get(i).setDefbId(
									bpmDefinition.getDefId().toString());
							bpmformbangservice.update(bpmformbanglist1.get(i));
							flag = false;
							break;
						}
					}
					if (flag == true) {
						System.out.println("咋还没有呢");
					}
				} else {
					System.out.println("咋还没有呢!!!");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,
						"流程定义保存或发布失败:\r\n" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ex.getMessage();
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
		return null;
	}

	/**
	 * 
	 * 查看拓扑图节点详细信息
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexDefSave23")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefSave23(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flexDefSave23将结果返回给flex");
		// 获取节点id
		String nodeId = RequestUtil.getString(request, "bpmDefinition.defId2");
		System.out.println("@@@nodeId=" + nodeId);
		// 获取defId
		Long defId = Long.valueOf(RequestUtil.getString(request,
				"bpmDefinition.defId"));
		System.out.println("@@@defId=" + defId);
		Bpmnetworkmap po = bpmnetworkmapService.getById(defId);
		String actDefId = po.getActDefId();
		System.out.println("actDefId=" + actDefId);
		// 获取节点类型nodeLabel
		String nodeLabel = RequestUtil.getString(request,
				"bpmDefinition.nodeLabel");
		System.out.println("@@@nodeLabel=" + nodeLabel);
		// 获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		System.out.println("ip=" + ip);
		// 跳转地址
		String url = "";
		if (nodeLabel.equals("netRouter")) {
			url = "/deviceRouter/deviceRouter/deviceRouter/smailedit.ht?actDefId=";
			// titl = "路由器";
		} else if (nodeLabel.equals("Switch")) {
			url = "/switchSet/switchSet/deviceSwitch/smailedit.ht?actDefId=";
			// titl = "交换机";
		} else if (nodeLabel.equals("Server")) {
			url = "/serviceSet/serviceSet/deviceServer/smailedit.ht?actDefId=";
			// titl = "服务器";
		} else if (nodeLabel.equals("Firewall")) {
			url = "/firewallSet/firewallSet/firewall/smailedit.ht?actDefId=";
			// titl = "防火墙";
		} else if (nodeLabel.equals("KVM")) {
			url = "/kvmSet/kvmSet/kvm/smailedit.ht?actDefId=";
			// titl = "KVM";
		}

		// Long formid = serviceN.getFormID(nodeLabel,"编辑","0");
		// String pk="10000019100017";
		// String newurl="http://" + ip +
		// ":8080/mas"+url+actDefId+"&nodeId="+nodeId+"&selectIsDisabled=disabled";
		// String newurl="http://" + ip +
		// ":8080/mas/platform/form/bpmDataTemplate/edit2Data.ht?__formId__="+formid+"&__pk__="+pk;
		String newurl = "http://"
				+ ip
				+ ":8080/mas/platform/form/bpmDataTemplate/edit2Data.ht?nodeLabel="
				+ nodeLabel + "&actDefId=" + actDefId + "&nodeId=" + nodeId;
		// ActivityInformWindow1({__formId__:"10000020400112",__pk__:"10000020410112"});
		request.setAttribute("selectIsDisabled", true);
		PrintWriter out = response.getWriter();
		out.print(newurl);
		System.out.println(newurl);
		return null;
	}

	/**
	 * flex，保存发布流程信息,并保存节点信息。
	 * 
	 * @author zl
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping("flexDefSaveNode")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefSaveNode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 新建流程定义时，若仅是保存流程定义时，则不发布流程。但下次更新时，若点发布新版时，则需要发布流程定义。
		// 新建流程定义时，若点了发布新版，则发布流程定义,下次再更新定义时，也是直接修改流程的定义。也可以重新发布新版本的流程
		// 是否发布新流程定义
		Boolean isDeploy = Boolean.parseBoolean(request.getParameter("deploy"));
		String actFlowDefXml = "";
		HttpServletRequest requestx;
		BpmFormBang bpmformbang = null;
		List<BpmFormBang> bpmformbanglist = null;
		BpmFormBang bpmformbang1 = null;
		List<BpmFormBang> bpmformbanglist1 = null;
		ResultMessage resultMessage = null;
		long formDefId = RequestUtil.getLong(request, "formDefId");
		String btnname = RequestUtil.getString(request, "btnname");
		String eventId = RequestUtil.getString(request, "eventId");
		// 记录流程定义ID
		String defId = null;
		try {
			// 获取flex中提交过来的数据构建流程定义对象。
			BpmDefinition bpmDefinition = getFromRequest(request);
			SysAuditThreadLocalHolder.putParamerter("defKey", bpmDefinition
					.getDefKey());
			actFlowDefXml = BpmUtil.transform(bpmDefinition.getDefKey(),
					bpmDefinition.getSubject(), bpmDefinition.getDefXml());
			bpmDefinitionService.saveOrUpdate(bpmDefinition, isDeploy,
					actFlowDefXml);
			Eventgraphrelation eventgraphrelation = eventgraphrelationService
					.getByEventId(eventId);
			if (eventgraphrelation != null) {
				eventgraphrelation.setDefbID(bpmDefinition.getDefId()
						.toString());
				eventgraphrelationService.update(eventgraphrelation);

				// requestx.setSession.setAttribute("bpmDefinitionx",bpmDefinition);
			}
			if (btnname != null && btnname != "") {
				bpmformbanglist1 = bpmformbangservice.getByBtnName(btnname);
				if (bpmformbanglist1.size() == 0) {
					bpmformbang1 = new BpmFormBang();
					bpmformbang1.setId(UniqueIdUtil.genId());
					bpmformbang1.setDefbId(bpmDefinition.getDefId().toString());
					bpmformbang1.setBtn_name(btnname);
					bpmformbangservice.add(bpmformbang1);
				} else {
					bpmformbanglist1.get(0).setDefbId(
							bpmDefinition.getDefId().toString());
					bpmformbangservice.update(bpmformbanglist1.get(0));
				}
			}
			if (formDefId > 0) {
				bpmformbanglist = bpmformbangservice.getByFormDefId(Long
						.toString(formDefId));
				if (bpmformbanglist.size() != 0) {
					bpmformbanglist.get(0).setDefbId(
							bpmDefinition.getDefId().toString());
					bpmformbangservice.update(bpmformbanglist.get(0));
				} else {
					bpmformbang = new BpmFormBang();
					bpmformbang.setDefbId(bpmDefinition.getDefId().toString());
					bpmformbang.setFormDefId(Long.toString(formDefId));
					bpmformbangservice.add(bpmformbanglist.get(0));
				}
			}
			response.getWriter().print("success");

			defId = bpmDefinition.getDefId().toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,
						"流程定义保存或发布失败:\r\n" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ex.getMessage();
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
		// zl保存流程定义ID到文件
		if (null != defId) {
			String actdeffilePath = FormUtil.getDesignButtonPath();
			File file = new File(actdeffilePath + "actdef.conf");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(defId);
			bw.close();
		}
		return null;
	}

	/*
	 * 
	 * B表 flex 保存发布流程信息。
	 * 
	 * @param request
	 * 
	 * @param response
	 * 
	 * @return ModelAndView
	 * 
	 * @throws Exception
	 */
	@RequestMapping("flexDefSave_b")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefSave_b(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flexDefSave_b");
		// 新建流程定义时，若仅是保存流程定义时，则不发布流程。但下次更新时，若点发布新版时，则需要发布流程定义。
		// 新建流程定义时，若点了发布新版，则发布流程定义,下次再更新定义时，也是直接修改流程的定义。也可以重新发布新版本的流程
		// 是否发布新流程定义
		Boolean isDeploy = Boolean.parseBoolean(request.getParameter("deploy"));
		String actFlowDefXml = "";
		ResultMessage resultMessage = null;
		String btnname = RequestUtil.getString(request, "btnname");
		// BpmFormBang bpmformbang = null;
		// List<BpmFormBang> bpmformbanglist = null;
		try {
			// 获取flex中提交过来的数据构建流程定义对象。
			Bpmnetworkmap bpmDefinition = getFromRequest_b(request);
			// zl获取旧的流程定义id
			String oldactdefid = bpmDefinition.getActDefId();
			SysAuditThreadLocalHolder.putParamerter("defKey", bpmDefinition
					.getDefKey());
			actFlowDefXml = BpmUtil.transform(bpmDefinition.getDefKey(),
					bpmDefinition.getSubject(), bpmDefinition.getDefXml());
			String newactdefid = bpmnetworkmapService.saveOrUpdate(
					bpmDefinition, isDeploy, actFlowDefXml);
			// System.out.println("输出啊！！！！！！！！！！！！"+btnname);
			// zl更新节点设置表和设备线关联表
			if (newactdefid != oldactdefid) {
				deviceNodeSetService.updateActdefid(oldactdefid, newactdefid);
				devicerelationshipService.updateActdefid(oldactdefid,
						newactdefid);
			}

			response.getWriter().print("success");
		} catch (Exception ex) {
			ex.printStackTrace();
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,
						"流程定义保存或发布失败:\r\n" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ex.getMessage();
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
		return null;
	}

	/*
	 * 根据从flex提交的数据构建流程定义对象。B表
	 * 
	 * @param request
	 * 
	 * @return
	 */

	private Bpmnetworkmap getFromRequest_b(HttpServletRequest request)
			throws Exception {
		Long proTypeId = RequestUtil.getLong(request, "bpmDefinition.typeId"); // 流程分类
		String subject = RequestUtil
				.getString(request, "bpmDefinition.subject"); // 流程标题
		String defKey = RequestUtil.getString(request, "bpmDefinition.defKey"); // 流程key
		String descp = RequestUtil.getString(request, "bpmDefinition.descp"); // description
		String defXml = RequestUtil.getString(request, "bpmDefinition.defXml"); // defXml
		defXml = defXml.replace("''", "'");
		// 处理泳道池的泳道Lane 的ID重复的问题
		defXml = this.dealPool(defXml);

		String reason = RequestUtil.getString(request, "bpmDefinition.reason");// reason
		Long defId = RequestUtil.getLong(request, "bpmDefinition.defId");

		Bpmnetworkmap bpmnetworkmap = null;
		if (defId != null && defId > 0) {
			bpmnetworkmap = bpmnetworkmapService.getById(defId);
		} else if (StringUtil.isNotEmpty(defKey)) {
			if (bpmDefinitionService.isDefKeyExists(defKey)) {
				throw new Exception("流程key已存在");
			}
		}
		if (bpmnetworkmap == null) {
			bpmnetworkmap = new Bpmnetworkmap();
			if (StringUtils.isNotEmpty(defKey)) {
				bpmnetworkmap.setDefKey(defKey);
				// 流程定义规则。
				bpmnetworkmap.setTaskNameRule(Bpmnetworkmap.DefaultSubjectRule);
			}
		}
		// 设置属性值
		if (proTypeId != null && proTypeId > 0) {
			bpmnetworkmap.setTypeId(proTypeId);
		}
		if (StringUtils.isNotEmpty(subject)) {
			bpmnetworkmap.setSubject(subject);
		}
		if (StringUtils.isNotEmpty(reason)) {
			bpmnetworkmap.setReason(reason);
		}
		if (StringUtils.isNotEmpty(descp)) {
			bpmnetworkmap.setDescp(descp);
		} else {
			bpmnetworkmap.setDescp(subject);
		}
		if (StringUtils.isNotEmpty(defXml)) {
			bpmnetworkmap
					.setDefXml("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
							+ defXml);
		}

		return bpmnetworkmap;
	}

	/**
	 * 查询功能
	 * 
	 * @author 王钊
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flex11")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flex11(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flex11将结果返回给flex");
		// 获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		System.out.println("ip=" + ip);
		// 跳转地址
		// String newurl="http://" + ip +
		// ":8080/mas/devicein/deviceinpack/deviceInfomation/smailedit.ht?actDefId="+actDeId+"&nodeId="+nodeId;

		PrintWriter out = response.getWriter();

		// 获取节点id
		String nodeId = RequestUtil.getString(request, "bpmDefinition.defId2");

		String nodeLabel = RequestUtil.getSecureString(request,
				"bpmDefinition.nodeLabel");
		System.out.println("@@@nodeId=" + nodeId);
		System.out.println("@@@nodeLabel=" + nodeLabel);

		// 获取defId
		Long defId = Long.valueOf(RequestUtil.getString(request,
				"bpmDefinition.defId"));
		System.out.println("@@@defId=" + defId);

		// 获取actdefId
		BpmDefinition pos = bpmDefinitionService.getById(defId);
		String actDefId = pos.getActDefId();
		System.out.println("actDeId=" + actDefId);

		if (nodeLabel.equals("查询数据")) {
			String newurl1 = "http://"
					+ ip
					+ ":8080/mas/platform/bpm/bpmNodeWebService/editwb.ht?actDefId="
					+ actDefId + "&nodeId=" + nodeId + "&defId=" + defId
					+ "&operate=" + "select";

			out.print(newurl1);
		}
		if (nodeLabel.equals("增加数据")) {
			String newurl2 = "http://"
					+ ip
					+ ":8080/mas/platform/bpm/bpmNodeWebService/editwb.ht?actDefId="
					+ actDefId + "&nodeId=" + nodeId + "&defId=" + defId
					+ "&operate=" + "add";
			out.print(newurl2);
		}
		if (nodeLabel.equals("修改数据")) {
			String newurl3 = "http://"
					+ ip
					+ ":8080/mas/platform/bpm/bpmNodeWebService/editwb.ht?actDefId="
					+ actDefId + "&nodeId=" + nodeId + "&defId=" + defId
					+ "&operate=" + "update";
			out.print(newurl3);
		}
		if (nodeLabel.equals("删除数据")) {
			String newurl4 = "http://"
					+ ip
					+ ":8080/mas/platform/bpm/bpmNodeWebService/editwb.ht?actDefId="
					+ actDefId + "&nodeId=" + nodeId + "&defId=" + defId
					+ "&operate=" + "delete";
			out.print(newurl4);
		}
		if (nodeLabel.equals("读页面")) {
			String newurl5 = "http://"
					+ ip
					+ ":8080/mas/platform/bpm/bpmNodeWebService/formwb.ht?defId="
					+ defId + "&actDefId=" + actDefId + "&nodeId=" + nodeId
					+ "&type=" + "input";
			out.print(newurl5);
		}
		if (nodeLabel.equals("写页面")) {
			String newurl6 = "http://"
					+ ip
					+ ":8080/mas/platform/bpm/bpmNodeWebService/formwb.ht?defId="
					+ defId + "&actDefId=" + actDefId + "&nodeId=" + nodeId
					+ "&type=" + "output";
			out.print(newurl6);
		}
		if (nodeLabel.equals("页面跳转")) {
			String newurl7 = "http://"
					+ ip
					+ ":8080/mas/redirection/redirection/redirection/edit.ht?defId="
					+ defId + "&actDefId=" + actDefId + "&nodeId=" + nodeId
					+ "&type=" + "output";
			out.print(newurl7);
		}

		return null;

				/*System.out.println("flex11将结果返回给flex");
				//获得本机IP
				InetAddress addr = InetAddress.getLocalHost();
				String ip=addr.getHostAddress().toString();
				System.out.println("ip="+ip);
				//跳转地址
				//String newurl="http://" + ip + ":8080/mas/devicein/deviceinpack/deviceInfomation/smailedit.ht?actDefId="+actDeId+"&nodeId="+nodeId;
		
				PrintWriter out = response.getWriter();
		
				//获取节点id
				String nodeId=RequestUtil.getString(request, "bpmDefinition.defId2");
				
				String nodeLabel = RequestUtil.getSecureString(request, "bpmDefinition.nodeLabel");
				System.out.println("@@@nodeId="+nodeId);
				System.out.println("@@@nodeLabel="+nodeLabel);
		
				//获取defId
				Long defId=Long.valueOf(RequestUtil.getString(request, "bpmDefinition.defId"));
				System.out.println("@@@defId="+defId);
		
				//获取actdefId
				BpmDefinition pos = bpmDefinitionService.getById(defId);
				String actDefId=pos.getActDefId();
		        System.out.println("actDeId="+actDefId);
				
		       
				if(nodeLabel.indexOf("查询数据")!= -1)
				{
					String newurl1="http://" + ip + ":8080/mas/platform/bpm/bpmNodeWebService/editwb.ht?actDefId="+actDefId+"&nodeId="+nodeId+"&defId="+defId+"&operate="+"select";
					
					out.print(newurl1);
				}
				if(nodeLabel.indexOf("增加数据")!= -1)
				{
					String newurl2="http://" + ip + ":8080/mas/platform/bpm/bpmNodeWebService/editwb.ht?actDefId="+actDefId+"&nodeId="+nodeId+"&defId="+defId+"&operate="+"add";
					out.print(newurl2);
				}
				if(nodeLabel.indexOf("修改数据")!= -1)
				{
					String newurl3="http://" + ip + ":8080/mas/platform/bpm/bpmNodeWebService/editwb.ht?actDefId="+actDefId+"&nodeId="+nodeId+"&defId="+defId+"&operate="+"update";
					out.print(newurl3);
				}
				if(nodeLabel.indexOf("删除数据 ")!= -1)
				{
					String newurl4="http://" + ip + ":8080/mas/platform/bpm/bpmNodeWebService/editwb.ht?actDefId="+actDefId+"&nodeId="+nodeId+"&defId="+defId+"&operate="+"delete";
					out.print(newurl4);
				}
				if(nodeLabel.indexOf("读页面")!= -1)
				{
					String newurl5="http://" + ip + ":8080/mas/platform/bpm/bpmNodeWebService/formwb.ht?defId="+defId+"&actDefId="+actDefId+"&nodeId="+nodeId+"&type="+"input";
					out.print(newurl5);
				}
				if(nodeLabel.indexOf("写页面")!= -1)
				{
					String newurl6="http://" + ip + ":8080/mas/platform/bpm/bpmNodeWebService/formwb.ht?defId="+defId+"&actDefId="+actDefId+"&nodeId="+nodeId+"&type="+"output";
					out.print(newurl6);
				}
				if(nodeLabel.indexOf("页面跳转")!= -1)
				{
					String newurl7="http://" + ip + ":8080/mas/redirection/redirection/redirection/edit.ht?defId="+defId+"&actDefId="+actDefId+"&nodeId="+nodeId+"&type="+"output";
					out.print(newurl7);
				}
				
	
		
		return null;	
>>>>>>> .r1173*/
	}
	//zdn
	@RequestMapping("flex101")
	@Action(description = "流程在线设计，保存，发布操作",
			detail="设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】," +
	               "<#if Boolean.parseBoolean(deploy)>" +
					  "<#if 0==bpmDefinition.getStatus()>"+
				          "版本号为【${bpmDefinition.versionNo}】" +
					  "<#else> 版本号为【${bpmDefinition.versionNo+1}】" +
				      "</#if>"+
	               "</#if>"
	)
	
	//zdn  点击变量管理按钮  链接页面 跳转地址
	public ModelAndView flex101(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("list将结果返回给flex");
		//获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip=addr.getHostAddress().toString();
		System.out.println("ip="+ip);
		//跳转地址
		//String newurl="http://" + ip + ":8080/mas/devicein/deviceinpack/deviceInfomation/smailedit.ht?actDefId="+actDeId+"&nodeId="+nodeId;

		PrintWriter out = response.getWriter();

		//获取节点id
		String nodeId=RequestUtil.getString(request, "bpmDefinition.defId2");
		
		String nodeLabel = RequestUtil.getSecureString(request, "bpmDefinition.nodeLabel");
		System.out.println("@@@nodeId="+nodeId);
		System.out.println("@@@nodeLabel="+nodeLabel);

		//获取defId
		Long defId=Long.valueOf(RequestUtil.getString(request, "bpmDefinition.defId"));
		System.out.println("@@@defId="+defId);

		//获取actdefId
		BpmDefinition pos = bpmDefinitionService.getById(defId);
		String actDefId=pos.getActDefId();
        System.out.println("actDeId="+actDefId);

		String newurl="http://" + ip + ":8080/mas/platform/bpm/bpmDefVar/list.ht?defId="+defId+"&actDefId="+actDefId+"&nodeId="+nodeId+"&type=output";
		out.print(newurl);

        
        return null;
        
        
	}
	/**
	 * 自定义查询功能
	 * 
	 * @author 于达
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping("flex11")
	 * 
	 * @Action(description = "流程在线设计，保存，发布操作",
	 * detail="设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
	 * + "<#if Boolean.parseBoolean(deploy)>" +
	 * "<#if 0==bpmDefinition.getStatus()>"+ "版本号为【${bpmDefinition.versionNo}】"
	 * + "<#else> 版本号为【${bpmDefinition.versionNo+1}】" + "</#if>"+ "</#if>" )
	 * public ModelAndView flex11(HttpServletRequest request,
	 * HttpServletResponse response) throws Exception {
	 * System.out.println("flex11将结果返回给flex"); //获得本机IP InetAddress addr =
	 * InetAddress.getLocalHost(); String ip=addr.getHostAddress().toString();
	 * System.out.println("ip="+ip); //跳转地址 //String newurl="http://" + ip +
	 * ":8080/mas/devicein/deviceinpack/deviceInfomation/smailedit.ht?actDefId="
	 * +actDeId+"&nodeId="+nodeId;
	 * 
	 * PrintWriter out = response.getWriter();
	 * 
	 * //获取节点id String nodeId=RequestUtil.getString(request,
	 * "bpmDefinition.defId2"); System.out.println("@@@nodeId="+nodeId);
	 * 
	 * //获取defId Long defId=Long.valueOf(RequestUtil.getString(request,
	 * "bpmDefinition.defId")); System.out.println("@@@defId="+defId);
	 * 
	 * 
	 * 
	 * List<Fqrelation> fqrelations = fqRelationService.getQueryMsg(nodeId,
	 * String.valueOf(defId)); System.out.println(fqrelations.size()); String
	 * selectStr= "datainquire"; if(nodeId.indexOf(selectStr)!= -1){
	 * if(fqrelations.size() == 0){ System.out.println("对象为null"); String
	 * newurl="http://" + ip +
	 * ":8080/mas/platform/bpm/bpmFormQuery/edit.ht?defId="
	 * +defId+"&nodeId="+nodeId+"&operate="+"select"; out.print(newurl); }else{
	 * System.out.println("对象不为null"); String fqID =
	 * fqrelations.get(0).getFqID(); System.out.println(fqID); String
	 * newurl="http://" + ip +
	 * ":8080/mas/platform/bpm/bpmFormQuery/edit.ht?id="+
	 * fqID+"&defId="+defId+"&nodeId="+nodeId+"&operate="+"select";
	 * out.print(newurl); } }else if(nodeId.indexOf("datachange")!= -1){
	 * if(fqrelations.size() == 0){ System.out.println("对象为null"); String
	 * newurl="http://" + ip +
	 * ":8080/mas/dataservice/formupdate/formUpdate/edit.ht?defId="
	 * +defId+"&nodeId="+nodeId+"&operate="+"update"; out.print(newurl); }else{
	 * System.out.println("对象不为null"); String fqID =
	 * fqrelations.get(0).getFqID(); System.out.println(fqID); String
	 * newurl="http://" + ip +
	 * ":8080/mas/dataservice/formupdate/formUpdate/edit.ht?id="
	 * +fqID+"&defId="+defId+"&nodeId="+nodeId+"&operate="+"update";
	 * out.print(newurl); }
	 * 
	 * 
	 * }
	 * 
	 * return null; }
	 */
	/**
	 * @author 于达
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping("amountOccurrence") public ModelAndView
	 * amountOccurrence(HttpServletRequest request, HttpServletResponse
	 * response) throws Exception{ System.out.println("发生量计算"); //获得本机IP
	 * InetAddress addr = InetAddress.getLocalHost(); String
	 * ip=addr.getHostAddress().toString(); System.out.println("ip="+ip);
	 * 
	 * PrintWriter out = response.getWriter();
	 * 
	 * Long defId=Long.valueOf(RequestUtil.getString(request,
	 * "bpmDefinition.defId")); System.out.println("@@@defId="+defId);
	 * 
	 * String newurl="http://" + ip +
	 * ":8080/mas/BusinessCollectCot/lc/businessCollectCot/edit.ht?processId="
	 * +defId; out.print(newurl); return null;
	 * 
	 * }
	 */
	/**
	 * Web service编辑功能
	 * 
	 * @author 王钊
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flex111")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flex111(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flex111将结果返回给flex");
		// 获得本机IP
		PrintWriter out = response.getWriter();
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		System.out.println("ip=" + ip);
		// 获取节点id
		String nodeId = RequestUtil.getString(request, "bpmDefinition.defId2");
		System.out.println("@@@nodeId=" + nodeId);

		// 获取defId
		Long defId = Long.valueOf(RequestUtil.getString(request,
				"bpmDefinition.defId"));
		System.out.println("@@@defId=" + defId);

		// 获取actdefId
		BpmDefinition po = bpmDefinitionService.getById(defId);
		String actDefId = po.getActDefId();
		System.out.println("actDeId=" + actDefId);

		// 跳转地址
		String newurl = "http://" + ip
				+ ":8080/mas/platform/bpm/bpmNodeWebService/edit.ht?actDefId="
				+ actDefId + "&nodeId=" + nodeId + "&defId=" + defId;
		out.print(newurl);

		/*
		 * List<Fqrelation> fqrelations = fqRelationService.getQueryMsg(nodeId,
		 * String.valueOf(defId)); System.out.println(fqrelations.size());
		 * String selectStr= "datainquire"; if(nodeId.indexOf(selectStr)!= -1){
		 * if(fqrelations.size() == 0){ System.out.println("对象为null"); String
		 * newurl="http://" + ip +
		 * ":8080/mas/platform/bpmCommonWsSet/bpmCommonWsSet/edit.ht?defId="
		 * +defId+"&nodeId="+nodeId+"&operate="+"select"; out.print(newurl);
		 * }else{ System.out.println("对象不为null"); String fqID =
		 * fqrelations.get(0).getFqID(); System.out.println(fqID); String
		 * newurl="http://" + ip +
		 * ":8080/mas/platform/bpm/bpmFormQuery/edit.ht?id="
		 * +fqID+"&operate="+"select"; out.print(newurl); } }else
		 * if(nodeId.indexOf("datachange")!= -1){ if(fqrelations.size() == 0){
		 * System.out.println("对象为null"); String newurl="http://" + ip +
		 * ":8080/mas/dataservice/formupdate/formUpdate/edit.ht?defId="
		 * +defId+"&nodeId="+nodeId+"&operate="+"update"; out.print(newurl);
		 * }else{ System.out.println("对象不为null"); String fqID =
		 * fqrelations.get(0).getFqID(); System.out.println(fqID); String
		 * newurl="http://" + ip +
		 * ":8080/mas/dataservice/formupdate/formUpdate/edit.ht?id="
		 * +fqID+"&defId="+defId+"&nodeId="+nodeId+"&operate="+"update";
		 * out.print(newurl); }
		 * 
		 * 
		 * }
		 */
		return null;
	}

	/**
	 * 拓扑图中网络模板
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexDefSave15")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefSave15(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flexDefSave15将结果返回给flex");
		String defkey = RequestUtil.getString(request, "bpmDefinition.defkey");
		System.out.println("defkey=" + defkey);
		JdbcTemplate template = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql = "SELECT bpmnetworkmap.DEFID " + "FROM bpmnetworkmap "
				+ "WHERE bpmnetworkmap.DEFKEY=" + "'" + defkey + "'"
				+ " AND bpmnetworkmap.VERSIONNO="
				+ "(SELECT MAX(bpmnetworkmap.VERSIONNO) "
				+ "FROM bpmnetworkmap WHERE bpmnetworkmap.DEFKEY=" + "'"
				+ defkey + "'" + ")";
		List<Map<String, Object>> list = template.queryForList(sql);
		String def_Id = list.get(0).get("DEFID").toString();
		System.out.println("%%%%def_Id=" + def_Id);
		Long defId = Long.valueOf(def_Id);
		Bpmnetworkmap bpmDefinition = null;
		if (defId > 0) {
			bpmDefinition = bpmnetworkmapService.getById(defId);
		} else {
			bpmDefinition = new Bpmnetworkmap();
			Long proTypeId = RequestUtil.getLong(request, "defId");
			if (proTypeId > 0) {
				bpmDefinition.setTypeId(new Long(proTypeId));
			}
		}
		StringBuffer msg = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><Result>");
		msg.append("<defId>" + bpmDefinition.getDefId() + "</defId>");
		msg.append("<defXml>" + bpmDefinition.getDefXml() + "</defXml>");
		if (bpmDefinition.getTypeId() != null) {
			GlobalType proType = globalTypeService.getById(bpmDefinition
					.getTypeId());
			msg.append("<typeName>" + proType.getTypeName() + "</typeName>");
			msg.append("<typeId>" + proType.getTypeId() + "</typeId>");
		}
		msg.append("<subject>" + bpmDefinition.getSubject() + "</subject>");
		msg.append("<defKey>" + bpmDefinition.getDefKey() + "</defKey>");
		msg.append("<descp>" + bpmDefinition.getDescp() + "</descp>");
		msg.append("<versionNo>" + bpmDefinition.getVersionNo()
				+ "</versionNo>");
		msg.append("</Result>");
		String temp = request.getParameter("temp");
		PrintWriter out = response.getWriter();
		out.print(msg.toString());
		System.out.println(msg.toString());
		return null;
	}

	/**
	 * 流程图中中网络模板
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexDef15")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDef15(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flexDef15将结果返回给flex");
		String defkey = RequestUtil.getString(request, "bpmDefinition.defkey");
		System.out.println("defkey=" + defkey);
		JdbcTemplate template = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql = "SELECT bpm_definition.DEFID " + "FROM bpm_definition "
				+ "WHERE bpm_definition.DEFKEY=" + "'" + defkey + "'"
				+ " AND bpm_definition.VERSIONNO="
				+ "(SELECT MAX(bpm_definition.VERSIONNO) "
				+ "FROM bpm_definition WHERE bpm_definition.DEFKEY=" + "'"
				+ defkey + "'" + ")";
		List<Map<String, Object>> list = template.queryForList(sql);
		String def_Id = list.get(0).get("DEFID").toString();
		System.out.println("%%%%def_Id=" + def_Id);
		Long defId = Long.valueOf(def_Id);
		BpmDefinition bpmDefinition = null;
		if (defId > 0) {
			bpmDefinition = bpmDefinitionService.getById(defId);
		} else {
			bpmDefinition = new BpmDefinition();
			Long proTypeId = RequestUtil.getLong(request, "defId");
			if (proTypeId > 0) {
				bpmDefinition.setTypeId(new Long(proTypeId));
			}
		}
		StringBuffer msg = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><Result>");
		msg.append("<defId>" + bpmDefinition.getDefId() + "</defId>");
		msg.append("<defXml>" + bpmDefinition.getDefXml() + "</defXml>");
		if (bpmDefinition.getTypeId() != null) {
			GlobalType proType = globalTypeService.getById(bpmDefinition
					.getTypeId());
			msg.append("<typeName>" + proType.getTypeName() + "</typeName>");
			msg.append("<typeId>" + proType.getTypeId() + "</typeId>");
		}
		msg.append("<subject>" + bpmDefinition.getSubject() + "</subject>");
		msg.append("<defKey>" + bpmDefinition.getDefKey() + "</defKey>");
		msg.append("<descp>" + bpmDefinition.getDescp() + "</descp>");
		msg.append("<versionNo>" + bpmDefinition.getVersionNo()
				+ "</versionNo>");
		msg.append("</Result>");

		String temp = request.getParameter("temp");
		System.out.println("temp=" + temp);
		PrintWriter out = response.getWriter();
		out.println(msg.toString());
		System.out.println(msg.toString());
		return null;
	}

	/**
	 * 查看拓扑图节点基本信息
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexDefSave1")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefSave1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flexDefSave1将结果返回给flex");
		// 获取节点id
		String nodeId = RequestUtil.getString(request, "bpmDefinition.defId2");
		System.out.println("@@@nodeId=" + nodeId);
		// 获取defId
		Long defId = Long.valueOf(RequestUtil.getString(request,
				"bpmDefinition.defId"));
		System.out.println("@@@defId=" + defId);
		Bpmnetworkmap po = bpmnetworkmapService.getById(defId);
		String actDeId = po.getActDefId();
		System.out.println("actDeId=" + actDeId);
		// 获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		System.out.println("ip=" + ip);
		// 跳转地址
		String newurl = "http://"
				+ ip
				+ ":8080/mas/devicein/deviceinpack/deviceInfomation/smailedit.ht?actDefId="
				+ actDeId + "&nodeId=" + nodeId;
		PrintWriter out = response.getWriter();
		out.print(newurl);
		System.out.println(newurl);
		return null;
	}

	/**
	 * 查看拓扑图节点详细信息
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexDefSave2")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefSave2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flexDefSave2将结果返回给flex");
		// 获取节点id
		String nodeId = RequestUtil.getString(request, "bpmDefinition.defId2");
		System.out.println("@@@nodeId=" + nodeId);
		// 获取defId
		Long defId = Long.valueOf(RequestUtil.getString(request,
				"bpmDefinition.defId"));
		System.out.println("@@@defId=" + defId);
		Bpmnetworkmap po = bpmnetworkmapService.getById(defId);
		String actDefId = po.getActDefId();
		System.out.println("actDefId=" + actDefId);
		// 获取节点类型nodeLabel
		String nodeLabel = RequestUtil.getString(request,
				"bpmDefinition.nodeLabel");
		System.out.println("@@@nodeLabel=" + nodeLabel);
		// 获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		System.out.println("ip=" + ip);
		// 跳转地址
		String url = "";
		if (nodeLabel.equals("netRouter")) {
			url = "/deviceRouter/deviceRouter/deviceRouter/smailedit.ht?actDefId=";
			// titl = "路由器";
		} else if (nodeLabel.equals("Switch")) {
			url = "/switchSet/switchSet/deviceSwitch/smailedit.ht?actDefId=";
			// titl = "交换机";
		} else if (nodeLabel.equals("Server")) {
			url = "/serviceSet/serviceSet/deviceServer/smailedit.ht?actDefId=";
			// titl = "服务器";
		} else if (nodeLabel.equals("Firewall")) {
			url = "/firewallSet/firewallSet/firewall/smailedit.ht?actDefId=";
			// titl = "防火墙";
		} else if (nodeLabel.equals("KVM")) {
			url = "/kvmSet/kvmSet/kvm/smailedit.ht?actDefId=";
			// titl = "KVM";
		}
		String newurl = "http://" + ip + ":8080/mas" + url + actDefId
				+ "&nodeId=" + nodeId + "&selectIsDisabled=disabled";
		request.setAttribute("selectIsDisabled", true);
		PrintWriter out = response.getWriter();
		out.print(newurl);
		System.out.println(newurl);
		return null;
	}

	/**
	 * 查看“查看”下的拓扑图外部子图
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexDefSave01")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefSave01(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flexDefSave01将结果返回给flex");
		// 获取子图key
		String defsubkey = RequestUtil.getString(request,
				"bpmDefinition.defsubkey");
		System.out.println("@@@defsubkey=" + defsubkey);
		// 获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		System.out.println("ip=" + ip);
		// 跳转地址
		JdbcTemplate template = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql = "SELECT bpmnetworkmap.DEFID " + "FROM bpmnetworkmap "
				+ "WHERE bpmnetworkmap.DEFKEY=" + "'" + defsubkey + "'"
				+ " AND bpmnetworkmap.VERSIONNO="
				+ "(SELECT MAX(bpmnetworkmap.VERSIONNO) "
				+ "FROM bpmnetworkmap WHERE bpmnetworkmap.DEFKEY=" + "'"
				+ defsubkey + "'" + ")";
		List<Map<String, Object>> list = template.queryForList(sql);
		String subkey = list.get(0).get("DEFID").toString();
		System.out.println("%%%%subkey=" + subkey);
		String newurl = "http://" + ip
				+ ":8080/mas/platform/bpm/bpmDefinition/design1.ht?defId="
				+ subkey;
		PrintWriter out = response.getWriter();
		out.print(newurl);
		System.out.println(newurl);
		return null;
	}

	/**
	 * 查看“设计”下的拓扑图外部子图
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexDefSave00")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefSave00(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flexDefSave00将结果返回给flex");
		// 获取子图key
		String defsubkey = RequestUtil.getString(request,
				"bpmDefinition.defsubkey");
		System.out.println("@@@defsubkey=" + defsubkey);
		// 获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		System.out.println("ip=" + ip);
		// 跳转地址
		JdbcTemplate template = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql = "SELECT bpmnetworkmap.DEFID " + "FROM bpmnetworkmap "
				+ "WHERE bpmnetworkmap.DEFKEY=" + "'" + defsubkey + "'"
				+ " AND bpmnetworkmap.VERSIONNO="
				+ "(SELECT MAX(bpmnetworkmap.VERSIONNO) "
				+ "FROM bpmnetworkmap WHERE bpmnetworkmap.DEFKEY=" + "'"
				+ defsubkey + "'" + ")";
		List<Map<String, Object>> list = template.queryForList(sql);
		String subkey = list.get(0).get("DEFID").toString();
		System.out.println("%%%%subkey=" + subkey);
		String newurl = "http://" + ip
				+ ":8080/mas/platform/bpm/bpmDefinition/design_b.ht?defId="
				+ subkey;
		PrintWriter out = response.getWriter();
		out.print(newurl);
		System.out.println(newurl);
		return null;
	}

	/**
	 * 查看“绑定信息”下的拓扑图外部子图
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexDefSave02")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefSave02(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flexDefSave02将结果返回给flex");
		// 获取子图key
		String defsubkey = RequestUtil.getString(request,
				"bpmDefinition.defsubkey");
		System.out.println("@@@defsubkey=" + defsubkey);
		// 获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		System.out.println("ip=" + ip);
		// 跳转地址
		JdbcTemplate template = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql = "SELECT bpmnetworkmap.DEFID " + "FROM bpmnetworkmap "
				+ "WHERE bpmnetworkmap.DEFKEY=" + "'" + defsubkey + "'"
				+ " AND bpmnetworkmap.VERSIONNO="
				+ "(SELECT MAX(bpmnetworkmap.VERSIONNO) "
				+ "FROM bpmnetworkmap WHERE bpmnetworkmap.DEFKEY=" + "'"
				+ defsubkey + "'" + ")";
		List<Map<String, Object>> list = template.queryForList(sql);
		String subkey = list.get(0).get("DEFID").toString();
		System.out.println("%%%%subkey=" + subkey);
		String newurl = "http://" + ip
				+ ":8080/mas/platform/bpm/bpmDefinition/design2.ht?defId="
				+ subkey;
		PrintWriter out = response.getWriter();
		out.print(newurl);
		System.out.println(newurl);
		return null;
	}

	/**
	 * 对拓扑图节点进行业务IP的设置
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexDefSave3")
	@Action(description = "对拓扑图节点进行业务IP的设置", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefSave3(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flexDefSave3将结果返回给flex");
		// 获取节点id
		String nodeId = RequestUtil.getString(request, "bpmDefinition.defId2");
		System.out.println("@@@nodeId=" + nodeId);
		// 获取defId
		Long defId = Long.valueOf(RequestUtil.getString(request,
				"bpmDefinition.defId"));
		System.out.println("@@@defId=" + defId);
		Bpmnetworkmap po = bpmnetworkmapService.getById(defId);
		String actDefId = po.getActDefId();
		System.out.println("actDefId=" + actDefId);
		// 获取节点类型nodeLabel
		String nodeLabel = RequestUtil.getString(request,
				"bpmDefinition.nodeLabel");
		System.out.println("@@@nodeLabel=" + nodeLabel);
		// 获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		System.out.println("ip=" + ip);
		// 跳转地址
		String url = "";
		if (nodeLabel.equals("netRouter")) {
			url = "/deviceRouter/deviceRouter/deviceRouter/smailedit.ht?actDefId=";
			// titl = "路由器";
		} else if (nodeLabel.equals("Switch")) {
			url = "/switchSet/switchSet/deviceSwitch/smailedit.ht?actDefId=";
			// titl = "交换机";
		} else if (nodeLabel.equals("Server")) {
			url = "/serviceSet/serviceSet/deviceServer/smailedit.ht?actDefId=";
			// titl = "服务器";
		} else if (nodeLabel.equals("Firewall")) {
			url = "/firewallSet/firewallSet/firewall/smailedit.ht?actDefId=";
			// titl = "防火墙";
		} else if (nodeLabel.equals("KVM")) {
			url = "/kvmSet/kvmSet/kvm/smailedit.ht?actDefId=";
			// titl = "KVM";
		}
		String newurl = "http://" + ip + ":8080/mas" + url + actDefId
				+ "&nodeId=" + nodeId;
		// request.setAttribute("selectIsDisabled","false");
		PrintWriter out = response.getWriter();
		out.print(newurl);
		System.out.println(newurl);
		return null;
	}

	/**
	 * 检验节点是否绑定设备信息
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexDefSave4")
	@Action(description = "检验节点是否绑定设备信息", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefSave4(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flexDefSave4将结果返回给flex");
		// 获取defId
		Long defId = Long.valueOf(RequestUtil.getString(request,
				"bpmDefinition.defId"));
		System.out.println("@@@defId=" + defId);
		// 查询ActDefId
		Bpmnetworkmap po = bpmnetworkmapService.getById(defId);
		String actDefId = po.getActDefId();
		System.out.println("actDefId=" + actDefId);
		// 查询图中全部节点nodeid
		JdbcTemplate template = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql1 = "SELECT nodeid from bpm_node_set WHERE DEFID =" + defId;
		List<Map<String, Object>> list1 = template.queryForList(sql1);
		// 查询图中已绑定设备信息的节点nodeid
		String sql2 = "SELECT F_nodeid FROM w_device_node_set WHERE F_actdefid ="
				+ "'" + actDefId + "'";
		List<Map<String, Object>> list2 = template.queryForList(sql2);
		// 检验哪些节点未绑定设备信息
		StringBuffer msg = new StringBuffer("<flowdata>");
		for (int i = 0; i < list1.size(); i++) {
			String k = "F";
			String nodeid = list1.get(i).get("NODEID").toString();
			for (int j = 0; j < list2.size(); j++) {
				String F_nodeid = list2.get(j).get("F_NODEID").toString();
				if (nodeid.equals(F_nodeid)) {
					k = "T";
				}
			}
			if (k == "F") {
				// 将该未绑定信息的节点nodeid放入xml报文中
				msg.append("<node id=\"" + nodeid + "\"/>");
			}
		}
		msg.append("</flowdata>");
		String undo = null;
		PrintWriter out = response.getWriter();
		out.print(msg);
		System.out.println(msg);
		return null;
	}

	/**
	 * 设置拓扑图线信息
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexDefSave5")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefSave5(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flexDefSave5将结果返回给flex");
		// 获取defId
		Long defId = Long.valueOf(RequestUtil.getString(request,
				"bpmDefinition.defId"));
		System.out.println("@@@defId=" + defId);
		Bpmnetworkmap po = bpmnetworkmapService.getById(defId);
		String actDefId = po.getActDefId();
		System.out.println("actDefId=" + actDefId);
		// 获取线的id
		String sequenceFlowId = RequestUtil.getString(request,
				"bpmDefinition.sequenceFlowId");
		System.out.println("@@@sequenceFlowId=" + sequenceFlowId);
		// 获取开始节点id
		String sequenceFlowStartNodeId = RequestUtil.getString(request,
				"bpmDefinition.sequenceFlowStartNodeId");
		System.out.println("@@@StartNodeId=" + sequenceFlowStartNodeId);
		// 获取结束节点id
		String sequenceFlowEndNodeId = RequestUtil.getString(request,
				"bpmDefinition.sequenceFlowEndNodeId");
		System.out.println("@@@EndNodeId=" + sequenceFlowEndNodeId);
		// 获取开始节点类型nodeLabel
		String startNodeLabel = RequestUtil.getString(request,
				"bpmDefinition.startNodeLabel");
		System.out.println("@@@startNodeLabel=" + startNodeLabel);
		// 获取结束节点类型nodeLabel
		String endNodeLabel = RequestUtil.getString(request,
				"bpmDefinition.endNodeLabel");
		System.out.println("@@@endNodeLabel=" + endNodeLabel);
		// 获取开始节点的设备编号
		JdbcTemplate template = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String start_sql = "select F_businessIP from w_device_node_set where F_actDefId='"
				+ actDefId + "' AND F_nodeId='" + sequenceFlowStartNodeId + "'";
		List<Map<String, Object>> start_list = template.queryForList(start_sql);
		// 判断该开始节点是否已绑定业务IP
		String start_dev_ID = "";
		if (start_list.size() != 0) {
			String start_businessIP = start_list.get(0).get("F_businessIP")
					.toString();
			System.out.println("@@@start_businessIP=" + start_businessIP);
			if (startNodeLabel.equals("netRouter")) {
				String start_sql1 = "select F_dev_ID from w_device_router where F_manage_IP='"
						+ start_businessIP + "'";
				List<Map<String, Object>> start_list1 = template
						.queryForList(start_sql1);
				start_dev_ID = start_list1.get(0).get("F_dev_ID").toString();
				System.out.println("@@@start_dev_ID=" + start_dev_ID);
				// titl = "路由器";
			} else if (startNodeLabel.equals("Switch")) {
				String start_sql2 = "select F_switch_ID from w_device_switch where F_manage_IP='"
						+ start_businessIP + "'";
				List<Map<String, Object>> start_list2 = template
						.queryForList(start_sql2);
				start_dev_ID = start_list2.get(0).get("F_switch_ID").toString();
				System.out.println("@@@start_dev_ID=" + start_dev_ID);
				// titl = "交换机";
			} else if (startNodeLabel.equals("Server")) {
				String start_sql3 = "select F_server_ID from w_device_server where F_manage_IP='"
						+ start_businessIP + "'";
				List<Map<String, Object>> start_list3 = template
						.queryForList(start_sql3);
				start_dev_ID = start_list3.get(0).get("F_server_ID").toString();
				System.out.println("@@@start_dev_ID=" + start_dev_ID);
				// titl = "服务器";
			} else if (startNodeLabel.equals("Firewall")) {
				String start_sql4 = "select F_fireWall_ID from w_fireWall where F_manage_IP='"
						+ start_businessIP + "'";
				List<Map<String, Object>> start_list4 = template
						.queryForList(start_sql4);
				start_dev_ID = start_list4.get(0).get("F_fireWall_ID")
						.toString();
				System.out.println("@@@start_dev_ID=" + start_dev_ID);
				// titl = "防火墙";
			} else if (startNodeLabel.equals("KVM")) {
				String start_sql5 = "select F_kvm_ID from w_kvm where F_manage_IP='"
						+ start_businessIP + "'";
				List<Map<String, Object>> start_list5 = template
						.queryForList(start_sql5);
				start_dev_ID = start_list5.get(0).get("F_kvm_ID").toString();
				System.out.println("@@@start_dev_ID=" + start_dev_ID);
				// titl = "KVM";
			}
		}
		// 获取结束节点的设备编号
		String end_sql = "select F_businessIP from w_device_node_set where F_actDefId='"
				+ actDefId + "' AND F_nodeId='" + sequenceFlowEndNodeId + "'";
		List<Map<String, Object>> end_list = template.queryForList(end_sql);
		// 判断该节点是否已绑定业务IP
		String end_dev_ID = "";
		if (end_list.size() != 0) {
			String end_businessIP = end_list.get(0).get("F_businessIP")
					.toString();
			System.out.println("@@@end_businessIP=" + end_businessIP);
			if (endNodeLabel.equals("netRouter")) {
				String end_sql1 = "select F_dev_ID from w_device_router where F_manage_IP='"
						+ end_businessIP + "'";
				List<Map<String, Object>> end_list1 = template
						.queryForList(end_sql1);
				end_dev_ID = end_list1.get(0).get("F_dev_ID").toString();
				System.out.println("@@@end_dev_ID=" + end_dev_ID);
				// titl = "路由器";
			} else if (endNodeLabel.equals("Switch")) {
				String end_sql2 = "select F_switch_ID from w_device_switch where F_manage_IP='"
						+ end_businessIP + "'";
				List<Map<String, Object>> end_list2 = template
						.queryForList(end_sql2);
				end_dev_ID = end_list2.get(0).get("F_switch_ID").toString();
				System.out.println("@@@end_dev_ID=" + end_dev_ID);
				// titl = "交换机";
			} else if (endNodeLabel.equals("Server")) {
				String end_sql3 = "select F_server_ID from w_device_server where F_manage_IP='"
						+ end_businessIP + "'";
				List<Map<String, Object>> end_list3 = template
						.queryForList(end_sql3);
				end_dev_ID = end_list3.get(0).get("F_server_ID").toString();
				System.out.println("@@@end_dev_ID=" + end_dev_ID);
				// titl = "服务器";
			} else if (endNodeLabel.equals("Firewall")) {
				String end_sql4 = "select F_fireWall_ID from w_fireWall where F_manage_IP='"
						+ end_businessIP + "'";
				List<Map<String, Object>> end_list4 = template
						.queryForList(end_sql4);
				end_dev_ID = end_list4.get(0).get("F_fireWall_ID").toString();
				System.out.println("@@@end_dev_ID=" + end_dev_ID);
				// titl = "防火墙";
			} else if (endNodeLabel.equals("KVM")) {
				String end_sql5 = "select F_kvm_ID from w_kvm where F_manage_IP='"
						+ end_businessIP + "'";
				List<Map<String, Object>> end_list5 = template
						.queryForList(end_sql5);
				end_dev_ID = end_list5.get(0).get("F_kvm_ID").toString();
				System.out.println("@@@end_dev_ID=" + end_dev_ID);
				// titl = "KVM";
			}
		}
		// 获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		System.out.println("ip=" + ip);
		// 跳转地址
		String url = "/DevicerelationshipCode/DevicerelationshipCodePath/devicerelationship/smailedit.ht?F_dev_ID="
				+ start_dev_ID
				+ "&F_opp_ID="
				+ end_dev_ID
				+ "&actDefId="
				+ actDefId;
		String newurl = "http://" + ip + ":8080/mas" + url;
		PrintWriter out = response.getWriter();
		out.print(newurl);
		System.out.println(newurl);
		return null;
	}

	/**
	 * 查看置拓扑图线信息
	 * 
	 * @author 王乙闲
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexDefSave6")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexDefSave6(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flexDefSave6将结果返回给flex");
		// 获取defId
		Long defId = Long.valueOf(RequestUtil.getString(request,
				"bpmDefinition.defId"));
		System.out.println("@@@defId=" + defId);
		Bpmnetworkmap po = bpmnetworkmapService.getById(defId);
		String actDefId = po.getActDefId();
		System.out.println("actDefId=" + actDefId);
		// 获取线的id
		String sequenceFlowId = RequestUtil.getString(request,
				"bpmDefinition.sequenceFlowId");
		System.out.println("@@@sequenceFlowId=" + sequenceFlowId);
		// 获取开始节点id
		String sequenceFlowStartNodeId = RequestUtil.getString(request,
				"bpmDefinition.sequenceFlowStartNodeId");
		System.out.println("@@@StartNodeId=" + sequenceFlowStartNodeId);
		// 获取结束节点id
		String sequenceFlowEndNodeId = RequestUtil.getString(request,
				"bpmDefinition.sequenceFlowEndNodeId");
		System.out.println("@@@EndNodeId=" + sequenceFlowEndNodeId);
		// 获取开始节点类型nodeLabel
		String startNodeLabel = RequestUtil.getString(request,
				"bpmDefinition.startNodeLabel");
		System.out.println("@@@startNodeLabel=" + startNodeLabel);
		// 获取结束节点类型nodeLabel
		String endNodeLabel = RequestUtil.getString(request,
				"bpmDefinition.endNodeLabel");
		System.out.println("@@@endNodeLabel=" + endNodeLabel);
		// 获取开始节点的设备编号
		JdbcTemplate template = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String start_sql = "select F_businessIP from w_device_node_set where F_actDefId='"
				+ actDefId + "' AND F_nodeId='" + sequenceFlowStartNodeId + "'";
		List<Map<String, Object>> start_list = template.queryForList(start_sql);
		// 判断该开始节点是否已绑定业务IP
		String start_dev_ID = "";
		if (start_list.size() != 0) {
			String start_businessIP = start_list.get(0).get("F_businessIP")
					.toString();
			System.out.println("@@@start_businessIP=" + start_businessIP);
			if (startNodeLabel.equals("netRouter")) {
				String start_sql1 = "select F_dev_ID from w_device_router where F_manage_IP='"
						+ start_businessIP + "'";
				List<Map<String, Object>> start_list1 = template
						.queryForList(start_sql1);
				start_dev_ID = start_list1.get(0).get("F_dev_ID").toString();
				System.out.println("@@@start_dev_ID=" + start_dev_ID);
				// titl = "路由器";
			} else if (startNodeLabel.equals("Switch")) {
				String start_sql2 = "select F_switch_ID from w_device_switch where F_manage_IP='"
						+ start_businessIP + "'";
				List<Map<String, Object>> start_list2 = template
						.queryForList(start_sql2);
				start_dev_ID = start_list2.get(0).get("F_switch_ID").toString();
				System.out.println("@@@start_dev_ID=" + start_dev_ID);
				// titl = "交换机";
			} else if (startNodeLabel.equals("Server")) {
				String start_sql3 = "select F_server_ID from w_device_server where F_manage_IP='"
						+ start_businessIP + "'";
				List<Map<String, Object>> start_list3 = template
						.queryForList(start_sql3);
				start_dev_ID = start_list3.get(0).get("F_server_ID").toString();
				System.out.println("@@@start_dev_ID=" + start_dev_ID);
				// titl = "服务器";
			} else if (startNodeLabel.equals("Firewall")) {
				String start_sql4 = "select F_fireWall_ID from w_fireWall where F_manage_IP='"
						+ start_businessIP + "'";
				List<Map<String, Object>> start_list4 = template
						.queryForList(start_sql4);
				start_dev_ID = start_list4.get(0).get("F_fireWall_ID")
						.toString();
				System.out.println("@@@start_dev_ID=" + start_dev_ID);
				// titl = "防火墙";
			} else if (startNodeLabel.equals("KVM")) {
				String start_sql5 = "select F_kvm_ID from w_kvm where F_manage_IP='"
						+ start_businessIP + "'";
				List<Map<String, Object>> start_list5 = template
						.queryForList(start_sql5);
				start_dev_ID = start_list5.get(0).get("F_kvm_ID").toString();
				System.out.println("@@@start_dev_ID=" + start_dev_ID);
				// titl = "KVM";
			}
		}
		// 获取结束节点的设备编号
		String end_sql = "select F_businessIP from w_device_node_set where F_actDefId='"
				+ actDefId + "' AND F_nodeId='" + sequenceFlowEndNodeId + "'";
		List<Map<String, Object>> end_list = template.queryForList(end_sql);
		// 判断该节点是否已绑定业务IP
		String end_dev_ID = "";
		if (end_list.size() != 0) {
			String end_businessIP = end_list.get(0).get("F_businessIP")
					.toString();
			System.out.println("@@@end_businessIP=" + end_businessIP);
			if (endNodeLabel.equals("netRouter")) {
				String end_sql1 = "select F_dev_ID from w_device_router where F_manage_IP='"
						+ end_businessIP + "'";
				List<Map<String, Object>> end_list1 = template
						.queryForList(end_sql1);
				end_dev_ID = end_list1.get(0).get("F_dev_ID").toString();
				System.out.println("@@@end_dev_ID=" + end_dev_ID);
				// titl = "路由器";
			} else if (endNodeLabel.equals("Switch")) {
				String end_sql2 = "select F_switch_ID from w_device_switch where F_manage_IP='"
						+ end_businessIP + "'";
				List<Map<String, Object>> end_list2 = template
						.queryForList(end_sql2);
				end_dev_ID = end_list2.get(0).get("F_switch_ID").toString();
				System.out.println("@@@end_dev_ID=" + end_dev_ID);
				// titl = "交换机";
			} else if (endNodeLabel.equals("Server")) {
				String end_sql3 = "select F_server_ID from w_device_server where F_manage_IP='"
						+ end_businessIP + "'";
				List<Map<String, Object>> end_list3 = template
						.queryForList(end_sql3);
				end_dev_ID = end_list3.get(0).get("F_server_ID").toString();
				System.out.println("@@@end_dev_ID=" + end_dev_ID);
				// titl = "服务器";
			} else if (endNodeLabel.equals("Firewall")) {
				String end_sql4 = "select F_fireWall_ID from w_fireWall where F_manage_IP='"
						+ end_businessIP + "'";
				List<Map<String, Object>> end_list4 = template
						.queryForList(end_sql4);
				end_dev_ID = end_list4.get(0).get("F_fireWall_ID").toString();
				System.out.println("@@@end_dev_ID=" + end_dev_ID);
				// titl = "防火墙";
			} else if (endNodeLabel.equals("KVM")) {
				String end_sql5 = "select F_kvm_ID from w_kvm where F_manage_IP='"
						+ end_businessIP + "'";
				List<Map<String, Object>> end_list5 = template
						.queryForList(end_sql5);
				end_dev_ID = end_list5.get(0).get("F_kvm_ID").toString();
				System.out.println("@@@end_dev_ID=" + end_dev_ID);
				// titl = "KVM";
			}
		}
		// 获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		System.out.println("ip=" + ip);
		// 跳转地址
		String url = "/DevicerelationshipCode/DevicerelationshipCodePath/devicerelationship/listnew.ht?F_dev_ID="
				+ start_dev_ID
				+ "&F_opp_ID="
				+ end_dev_ID
				+ "&actDefId="
				+ actDefId;
		String newurl = "http://" + ip + ":8080/mas" + url;
		PrintWriter out = response.getWriter();
		out.print(newurl);
		System.out.println(newurl);
		return null;
	}

	/**
	 * 根据从flex提交的数据构建流程定义对象。
	 * 
	 * @param request
	 * @return
	 */
	private BpmDefinition getFromRequest(HttpServletRequest request)
			throws Exception {
		Long proTypeId = RequestUtil.getLong(request, "bpmDefinition.typeId"); // 流程分类
		String subject = RequestUtil
				.getString(request, "bpmDefinition.subject"); // 流程标题
		String defKey = RequestUtil.getString(request, "bpmDefinition.defKey"); // 流程key
		String descp = RequestUtil.getString(request, "bpmDefinition.descp"); // description
		String defXml = RequestUtil.getString(request, "bpmDefinition.defXml"); // defXml
		defXml = defXml.replace("''", "'");
		// 处理泳道池的泳道Lane 的ID重复的问题
		defXml = this.dealPool(defXml);

		String reason = RequestUtil.getString(request, "bpmDefinition.reason");// reason
		Long defId = RequestUtil.getLong(request, "bpmDefinition.defId");

		BpmDefinition bpmDefinition = null;
		if (defId != null && defId > 0) {
			bpmDefinition = bpmDefinitionService.getById(defId);
		} else if (StringUtil.isNotEmpty(defKey)) {
			if (bpmDefinitionService.isDefKeyExists(defKey)) {
				throw new Exception("流程key已存在");
			}
		}
		if (bpmDefinition == null) {
			bpmDefinition = new BpmDefinition();
			if (StringUtils.isNotEmpty(defKey)) {
				bpmDefinition.setDefKey(defKey);
				// 流程定义规则。
				bpmDefinition.setTaskNameRule(BpmDefinition.DefaultSubjectRule);
			}
		}
		// 设置属性值
		if (proTypeId != null && proTypeId > 0) {
			bpmDefinition.setTypeId(proTypeId);
		}
		if (StringUtils.isNotEmpty(subject)) {
			bpmDefinition.setSubject(subject);
		}
		if (StringUtils.isNotEmpty(reason)) {
			bpmDefinition.setReason(reason);
		}
		if (StringUtils.isNotEmpty(descp)) {
			bpmDefinition.setDescp(descp);
		} else {
			bpmDefinition.setDescp(subject);
		}
		if (StringUtils.isNotEmpty(defXml)) {
			bpmDefinition
					.setDefXml("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
							+ defXml);
		}

		return bpmDefinition;
	}

	/**
	 * 处理泳道池的泳道Lane 的ID重复的问题
	 * 
	 * @param defXml
	 */
	private String dealPool(String defXml) {
		// 控制ID不重复
		int v = 1;
		int h = 1;
		// 处理垂直泳道池
		Pattern vRegex = Pattern.compile("<bg:VLane\\s*(id=\"\\w+\")\\s*");
		Matcher vRegexMatcher = vRegex.matcher(defXml);
		while (vRegexMatcher.find()) {
			String vLane = "id=\"vLane" + v + "\"";
			defXml = defXml.replaceFirst(vRegexMatcher.group(1), vLane);
			v++;
		}
		// 处理水平泳道池
		Pattern hRegex = Pattern.compile("<bg:HLane\\s*(id=\"\\w+\")\\s*");
		Matcher hRegexMatcher = hRegex.matcher(defXml);
		while (hRegexMatcher.find()) {
			String hLane = "id=\"hLane" + h + "\"";
			defXml = defXml.replaceFirst(hRegexMatcher.group(1), hLane);
			h++;
		}
		return defXml;
	}

	/**
	 * 
	 * 
	 * flex，根据defId获取，流程对应的详细信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexGet")
	@Action(description = "流程在线设计，查看详细信息")
	public ModelAndView flexGet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long defId = RequestUtil.getLong(request, "defId");
		SysAuditThreadLocalHolder.putParamerter("isExist", defId);
		BpmDefinition bpmDefinition = null;
		if (defId > 0) {
			bpmDefinition = bpmDefinitionService.getById(defId);
		} else {
			bpmDefinition = new BpmDefinition();
			Long proTypeId = RequestUtil.getLong(request, "defId");
			if (proTypeId > 0) {
				bpmDefinition.setTypeId(new Long(proTypeId));
			}
		}
		StringBuffer msg = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><Result>");
		msg.append("<defId>" + bpmDefinition.getDefId() + "</defId>");
		msg.append("<defXml>" + bpmDefinition.getDefXml() + "</defXml>");
		if (bpmDefinition.getTypeId() != null) {
			GlobalType proType = globalTypeService.getById(bpmDefinition
					.getTypeId());
			msg.append("<typeName>" + proType.getTypeName() + "</typeName>");
			msg.append("<typeId>" + proType.getTypeId() + "</typeId>");
		}
		msg.append("<subject>" + bpmDefinition.getSubject() + "</subject>");
		msg.append("<defKey>" + bpmDefinition.getDefKey() + "</defKey>");
		msg.append("<descp>" + bpmDefinition.getDescp() + "</descp>");
		msg.append("<versionNo>" + bpmDefinition.getVersionNo()
				+ "</versionNo>");
		msg.append("</Result>");

		String temp = request.getParameter("temp");
		System.out.println("temp=" + temp);
		PrintWriter out = response.getWriter();
		out.println(msg.toString());
		// System.out.println("报文！！！！："+msg.toString());
		return null;
	}

	/**
	 * 
	 * 
	 * flex，根据defId获取，B表流程对应的详细信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexGet_b")
	@Action(description = "流程在线设计，查看详细信息")
	public ModelAndView flexGet_b(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long defId = RequestUtil.getLong(request, "defId");
		SysAuditThreadLocalHolder.putParamerter("isExist", defId);
		Bpmnetworkmap bpmDefinition = null;
		if (defId > 0) {
			bpmDefinition = bpmnetworkmapService.getById(defId);
		} else {
			bpmDefinition = new Bpmnetworkmap();
			Long proTypeId = RequestUtil.getLong(request, "defId");
			if (proTypeId > 0) {
				bpmDefinition.setTypeId(new Long(proTypeId));
			}
		}
		StringBuffer msg = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><Result>");
		msg.append("<defId>" + bpmDefinition.getDefId() + "</defId>");
		msg.append("<defXml>" + bpmDefinition.getDefXml() + "</defXml>");
		if (bpmDefinition.getTypeId() != null) {
			GlobalType proType = globalTypeService.getById(bpmDefinition
					.getTypeId());
			msg.append("<typeName>" + proType.getTypeName() + "</typeName>");
			msg.append("<typeId>" + proType.getTypeId() + "</typeId>");
		}
		msg.append("<subject>" + bpmDefinition.getSubject() + "</subject>");
		msg.append("<defKey>" + bpmDefinition.getDefKey() + "</defKey>");
		msg.append("<descp>" + bpmDefinition.getDescp() + "</descp>");
		msg.append("<versionNo>" + bpmDefinition.getVersionNo()
				+ "</versionNo>");
		msg.append("</Result>");

		String temp = request.getParameter("temp");
		System.out.println("temp=" + temp);
		PrintWriter out = response.getWriter();
		out.print(msg.toString() + temp);
		System.out.println("@@@" + msg.toString());
		return null;
	}

	@RequestMapping("saveUser")
	@Action(description = "保存流程的节点人员设置", detail = "修改流程定义【${SysAuditLinkService.getBpmDefinitionLink(Long.valueOf(defId))}】的节点人员设置")
	public void saveUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// String nodeTag = RequestUtil.getString(request, "nodeTag");
		Long conditionId = RequestUtil.getLong(request, "conditionId");
		String[] assignTypes = request.getParameterValues("assignType");

		String[] cmpIdss = request.getParameterValues("cmpIds");
		String[] cmpNamess = request.getParameterValues("cmpNames");
		String[] nodeUserIds = request.getParameterValues("nodeUserId");
		String[] compTypes = request.getParameterValues("compType");

		String resultMsg = "";
		try {
			if (assignTypes != null && assignTypes.length > 0) {
				for (int i = 0; i < assignTypes.length; i++) {
					BpmNodeUser bnUser = null;
					if (StringUtils.isNotEmpty(nodeUserIds[i])) {
						long nodeUserId = new Long(nodeUserIds[i]);

						bnUser = bpmNodeUserService.getById(nodeUserId);
						bnUser.setCmpIds(cmpIdss[i]);
						bnUser.setCmpNames(cmpNamess[i]);
						bnUser.setCompType(new Short(compTypes[i]));
						bnUser.setSn(i);
						bnUser.setConditionId(conditionId);
						bpmNodeUserService.update(bnUser);
					} else {
						long nodeUserId = UniqueIdUtil.genId();

						bnUser = new BpmNodeUser();
						bnUser.setCmpIds(cmpIdss[i]);
						bnUser.setCmpNames(cmpNamess[i]);

						bnUser.setAssignType(assignTypes[i]);
						bnUser.setCompType(new Short(compTypes[i]));

						bnUser.setNodeUserId(nodeUserId);
						bnUser.setSn(i);
						bnUser.setConditionId(conditionId);
						bpmNodeUserService.add(bnUser);
					}
				}
			}
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), resultMsg + ","
					+ e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 删除流程节点的人员设置
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delBpmNodeUser")
	@ResponseBody
	@Action(description = "删除流程节点的人员设置", execOrder = ActionExecOrder.BEFORE, detail = "<#assign nodeuser=bpmNodeUserService.getById(Long.valueOf(nodeUserId))/>"
			+ "<#assign usercond=bpmUserConditionService.getById(nodeuser.conditionId)/>"
			+ "<#assign bpmdef=bpmDefinitionService.getByActDefId(usercond.actdefid)/>"
			+ "删除流程定义【${bpmdef.subject}】的节点【${SysAuditLinkService.getNodeName(usercond.actdefid,usercond.nodeid)}】的人员设置")
	public String delBpmNodeUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long nodeUserId = RequestUtil.getLong(request, "nodeUserId");
		bpmNodeUserService.delById(nodeUserId);
		return "{success:true}";
	}

	/**
	 * 取得流程实例扩展分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("instance")
	// @Action(description="查看流程实例扩展分页列表")
	public ModelAndView instance(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();

		QueryFilter filter = new QueryFilter(request, "processRunItem");
		// 过滤掉草稿实例
		filter.addFilter("exceptStatus", 4);
		filter.addFilter("exceptDefStatus", 3);
		List<ProcessRun> list = processRunService.getAll(filter);
		Long defId = RequestUtil.getLong(request, "defId");
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);

		mv.addObject("bpmDefinition", bpmDefinition);
		mv.addObject("processRunList", list);
		return mv;
	}

	@RequestMapping("deploy")
	@Action(description = "发布流程", detail = "发布流程定义【${SysAuditLinkService.getBpmDefinitionLink(Long.valueOf(defId))}】")
	public void deploy(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		Long defId = RequestUtil.getLong(request, "defId");
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);

		String defXml = bpmDefinition.getDefXml();
		String actDefXml = BpmUtil.transform(bpmDefinition.getDefKey(),
				bpmDefinition.getSubject(), defXml);
		bpmDefinitionService.deploy(bpmDefinition, actDefXml);
		ResultMessage message = new ResultMessage(ResultMessage.Success,
				"发布流程成功!");
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 设置流程分支条件
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("setCondition")
	public ModelAndView setCondition(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String deployId = RequestUtil.getString(request, "deployId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		long defId = RequestUtil.getLong(request, "defId");

		ModelAndView mv = null;
		String vers = request.getHeader("USER-AGENT");
		if (vers.indexOf("MSIE 6") != -1) {
			mv = new ModelAndView(
					"/platform/bpm/bpmDefinitionSetCondition_ie6.jsp");
		} else {
			mv = getAutoView();
		}

		ProcessDefinitionEntity proDefEntity = bpmService
				.getProcessDefinitionByDeployId(deployId);
		// 找到当前设置的节点
		ActivityImpl curActivity = proDefEntity.findActivity(nodeId);

		String curNodeType = (String) curActivity.getProperty("type");
		Boolean ifInclusiveGateway = curNodeType.equals("inclusiveGateway");
		// 如果是条件同步节点，则获取之前是否已经设置了选择执行路径
		if (ifInclusiveGateway) {
			String curNodeName = (String) curActivity.getId();
			BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
			Object val = bpmDefinition.getCanChoicePathNodeMap().get(
					curNodeName);
			if (val != null) {
				mv.addObject("canChoicePathGateway", curNodeName).addObject(
						"selectCanChoicePathNodeId", val);
			}
		}
		// 找到该节点的入口节点及出口节点
		List<BpmNode> incomeNodes = new ArrayList<BpmNode>();
		List<BpmNode> outcomeNodes = new ArrayList<BpmNode>();
		// 取得当前节点的上一节点列表
		List<PvmTransition> inTrans = curActivity.getIncomingTransitions();
		for (PvmTransition tran : inTrans) {
			ActivityImpl act = (ActivityImpl) tran.getSource();
			String id = act.getId();
			String nodeName = (String) act.getProperty("name");
			String nodeType = (String) act.getProperty("type");
			Boolean isMultiple = act.getProperty("multiInstance") != null ? true
					: false;
			incomeNodes.add(new BpmNode(id, nodeName, nodeType, isMultiple));
		}

		String xml = bpmService.getDefXmlByDeployId(deployId);
		Map<String, String> conditionMap = BpmUtil.getDecisionConditions(xml,
				nodeId);

		// 取得当前节点下一节点列表
		List<PvmTransition> outTrans = curActivity.getOutgoingTransitions();
		for (PvmTransition tran : outTrans) {
			ActivityImpl act = (ActivityImpl) tran.getDestination();
			String id = act.getId();
			String nodeName = (String) act.getProperty("name");
			String nodeType = (String) act.getProperty("type");
			Boolean isMultiple = act.getProperty("multiInstance") != null ? true
					: false;
			BpmNode bpmNode = new BpmNode(id, nodeName, nodeType, isMultiple);
			String condition = conditionMap.get(id);
			if (condition != null) {
				bpmNode.setCondition(condition);
			}
			outcomeNodes.add(bpmNode);
		}

		mv.addObject("defId", defId).addObject("ifInclusiveGateway",
				ifInclusiveGateway).addObject("nodeId", nodeId).addObject(
				"deployId", deployId).addObject("incomeNodes", incomeNodes)
				.addObject("outcomeNodes", outcomeNodes);
		return mv;
	}
    /**
     * zdn设置分支条件跳转大页面
     * */
	@RequestMapping("setCondition1")
	public ModelAndView setCondition1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String deployId = RequestUtil.getString(request, "deployId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		long defId = RequestUtil.getLong(request, "defId");

		ModelAndView mv = null;
		String vers = request.getHeader("USER-AGENT");
		if (vers.indexOf("MSIE 6") != -1) {
			mv = new ModelAndView(
					"/platform/bpm/bpmDefinitionSetCondition_ie6.jsp");
		} else {
			mv = getAutoView();
		}

		ProcessDefinitionEntity proDefEntity = bpmService
				.getProcessDefinitionByDeployId(deployId);
		// 找到当前设置的节点
		ActivityImpl curActivity = proDefEntity.findActivity(nodeId);

		String curNodeType = (String) curActivity.getProperty("type");
		Boolean ifInclusiveGateway = curNodeType.equals("inclusiveGateway");
		// 如果是条件同步节点，则获取之前是否已经设置了选择执行路径
		if (ifInclusiveGateway) {
			String curNodeName = (String) curActivity.getId();
			BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
			Object val = bpmDefinition.getCanChoicePathNodeMap().get(
					curNodeName);
			if (val != null) {
				mv.addObject("canChoicePathGateway", curNodeName).addObject(
						"selectCanChoicePathNodeId", val);
			}
		}
		// 找到该节点的入口节点及出口节点
		List<BpmNode> incomeNodes = new ArrayList<BpmNode>();
		List<BpmNode> outcomeNodes = new ArrayList<BpmNode>();
		// 取得当前节点的上一节点列表
		List<PvmTransition> inTrans = curActivity.getIncomingTransitions();
		for (PvmTransition tran : inTrans) {
			ActivityImpl act = (ActivityImpl) tran.getSource();
			String id = act.getId();
			String nodeName = (String) act.getProperty("name");
			String nodeType = (String) act.getProperty("type");
			Boolean isMultiple = act.getProperty("multiInstance") != null ? true
					: false;
			incomeNodes.add(new BpmNode(id, nodeName, nodeType, isMultiple));
		}

		String xml = bpmService.getDefXmlByDeployId(deployId);
		Map<String, String> conditionMap = BpmUtil.getDecisionConditions(xml,
				nodeId);

		// 取得当前节点下一节点列表
		List<PvmTransition> outTrans = curActivity.getOutgoingTransitions();
		for (PvmTransition tran : outTrans) {
			ActivityImpl act = (ActivityImpl) tran.getDestination();
			String id = act.getId();
			String nodeName = (String) act.getProperty("name");
			String nodeType = (String) act.getProperty("type");
			Boolean isMultiple = act.getProperty("multiInstance") != null ? true
					: false;
			BpmNode bpmNode = new BpmNode(id, nodeName, nodeType, isMultiple);
			String condition = conditionMap.get(id);
			if (condition != null) {
				bpmNode.setCondition(condition);
			}
			outcomeNodes.add(bpmNode);
		}

		mv.addObject("defId", defId).addObject("ifInclusiveGateway",
				ifInclusiveGateway).addObject("nodeId", nodeId).addObject(
				"deployId", deployId).addObject("incomeNodes", incomeNodes)
				.addObject("outcomeNodes", outcomeNodes);
		return mv;
	}
	
	/**
	 * 保存条件xml,更新流程定义文件和流程设计文件。
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("saveCondition")
	@Action(description = "保存流程条件设置", detail = "设置流程定义【${SysAuditLinkService.getBpmDefinitionLink(Long.valueOf(defId))}】节点【${SysAuditLinkService.getNodeName(Long.valueOf(defId),nodeId)}】的条件设置")
	public void saveCondition(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		try {
			long defId = RequestUtil.getLong(request, "defId");
			String nodeId = request.getParameter("nodeId");
			String tasks = request.getParameter("tasks");
			String conditions = request.getParameter("conditions");
			String[] aryTasks = tasks.split("#split#");
			String canChoicePathNodeId = RequestUtil.getString(request,
					"canChoicePathNodeId");
			conditions = " " + conditions + " ";
			String[] aryCondition = conditions.split("#split#");
			Map<String, String> map = new HashMap<String, String>();

			for (int i = 0; i < aryTasks.length; i++) {
				String condition = aryCondition[i].trim();
				map.put(aryTasks[i], condition);
			}
			bpmService.saveCondition(defId, nodeId, map, canChoicePathNodeId);

			writeResultMessage(writer, "保存流程条件成功", ResultMessage.Success);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error:" + e.getMessage());
			writeResultMessage(writer, "出错:" + e.getMessage(),
					ResultMessage.Fail);
		}
	}

	/**
	 * 显示流程定义中的每个节点锚点信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flowImg")
	public ModelAndView flowImg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String actDefId = request.getParameter("actDefId");

		String defXml = bpmService.getDefXmlByProcessDefinitionId(actDefId);
		ShapeMeta shapeMeta = BpmUtil.transGraph(defXml);

		ModelAndView modelAndView = getAutoView();

		modelAndView.addObject("shapeMeta", shapeMeta);
		modelAndView.addObject("actDefId", actDefId);

		return modelAndView;
	}

	/**
	 * 显示流程定义中的每个节点人员的情况
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("nodeUser")
	public ModelAndView nodeUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String processDefinitionId = request
				.getParameter("processDefinitionId");
		String nodeId = request.getParameter("nodeId");
		Map<String, Object> vars = new HashMap<String, Object>();
		String curUserId = ContextUtil.getCurrentUserId().toString();
		List<TaskExecutor> list = bpmNodeUserService.getExeUserIds(
				processDefinitionId, null, nodeId, curUserId, curUserId, vars);
		ModelAndView modelAndView = getAutoView();
		modelAndView.addObject("candidateUsers", list);
		return modelAndView;
	}

	/**
	 * 设置流程标题规则页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("otherParam")
	public ModelAndView otherParam(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long defId = RequestUtil.getLong(request, "defId");
		String parentActDefId = RequestUtil.getString(request,
				"parentActDefId", "");
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		Map<String, ISkipCondition> skipMap = ServiceUtil.getSkipConditionMap();
		boolean isStartMultipleNode = NodeCache
				.isMultipleFirstNode(bpmDefinition.getActDefId());
		// List<BpmNodeSet> list = bpmNodeSetService.getByDefId(defId);
		SysFile sysFile = null;
		if (bpmDefinition.getAttachment() != null
				&& bpmDefinition.getAttachment() > 0L) {
			sysFile = sysFileService.getById(bpmDefinition.getAttachment());
		}
		List<BpmReferDefinition> referList = bpmReferDefinitionService
				.getByDefId(defId);

		Map<String, IMessageHandler> handlersMap = ServiceUtil.getHandlerMap();

		ModelAndView modelAndView = getAutoView();
		return modelAndView.addObject("bpmDefinition", bpmDefinition)
				.addObject("ccMessageType", bpmDefinition.getCcMessageType())
				.addObject("defId", bpmDefinition.getDefId())
				// .addObject("nodeSetList", list)
				.addObject("referList", referList).addObject(
						"isStartMultipleNode", isStartMultipleNode).addObject(
						"sysFile", sysFile).addObject("handlersMap",
						handlersMap)
				.addObject("parentActDefId", parentActDefId).addObject(
						"skipMap", skipMap);
	}

	/**
	 * 设置流程标题规则
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("saveParam")
	@Action(description = "保存流程参数", detail = "设置流程定义【${SysAuditLinkService.getBpmDefinitionLink(Long.valueOf(defId))}】的其他参数设置")
	public void saveParam(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		BpmDefinition bpmDefinition = new BpmDefinition();
		// 流程ID
		long defId = RequestUtil.getLong(request, "defId");
		long formDefId = RequestUtil.getLong(request, "formDefId");
		System.out.println("第二出输出" + formDefId);
		bpmDefinition.setDefId(defId);

		// bpmformbang.setDefbId(Long.toString(defId));
		// 表单变量
		String taskNameRule = RequestUtil.getString(request, "taskNameRule");
		bpmDefinition.setTaskNameRule(taskNameRule);
		// 跳过第一个任务
		short toFirstNode = (short) RequestUtil.getInt(request, "toFirstNode");
		bpmDefinition.setToFirstNode(toFirstNode);
		// 流程启动选择执行人
		short showFirstAssignee = (short) RequestUtil.getInt(request,
				"showFirstAssignee", 0);
		bpmDefinition.setShowFirstAssignee(showFirstAssignee);
		// 表单明细Url
		String formDetailUrl = RequestUtil.getString(request, "formDetailUrl",
				"");
		bpmDefinition.setFormDetailUrl(formDetailUrl);
		// 提交是否需要确认
		int submitConfirm = RequestUtil.getInt(request, "submitConfirm", 0);
		bpmDefinition.setSubmitConfirm(submitConfirm);
		// 是否允许转办
		int allowDivert = RequestUtil.getInt(request, "allowDivert", 0);
		bpmDefinition.setAllowDivert(allowDivert);
		// 是否允许我的办结转发
		int allowFinishedDivert = RequestUtil.getInt(request,
				"allowFinishedDivert", 0);
		bpmDefinition.setAllowFinishedDivert(allowFinishedDivert);
		// 归档时发送消息给发起人
		String informStart = RequestUtil.getString(request, "informStart");
		bpmDefinition.setInformStart(informStart);
		// 通知类型
		String informType = RequestUtil.getString(request, "informType");
		bpmDefinition.setInformType(informType);
		// 是否允许办结抄送
		Integer allowFinishedCc = RequestUtil
				.getInt(request, "allowFinishedCc");
		bpmDefinition.setAllowFinishedCc(allowFinishedCc);
		// 流程实例归档后是否允许打印表单
		short isPrintForm = (short) RequestUtil.getInt(request, "isPrintForm",
				0);
		bpmDefinition.setIsPrintForm(isPrintForm);
		// 流程帮助 的附件
		Long attachment = RequestUtil.getLong(request, "attachment");
		bpmDefinition.setAttachment(attachment);
		// 流程状态
		short status = (short) RequestUtil.getInt(request, "status", 1);
		bpmDefinition.setStatus(status);
		// 相邻任务节点人员相同是自动跳过
		short sameExecutorJump = (short) RequestUtil.getInt(request,
				"sameExecutorJump", 0);
		bpmDefinition.setSameExecutorJump(sameExecutorJump);

		// 允许API调用
		short isUseOutForm = (short) RequestUtil.getInt(request,
				"isUseOutForm", 0);
		bpmDefinition.setIsUseOutForm(isUseOutForm);
		// 流程参考
		Integer allowRefer = RequestUtil.getInt(request, "allowRefer");
		bpmDefinition.setAllowRefer(allowRefer);
		// 参考条数
		Integer instanceAmount = RequestUtil.getInt(request, "instanceAmount");
		bpmDefinition.setInstanceAmount(instanceAmount);
		// 直接启动流程。
		Integer directstart = RequestUtil.getInt(request, "directstart", 0);
		bpmDefinition.setDirectstart(directstart);
		// 抄送消息类型
		String ccMessageType = RequestUtil.getString(request, "ccMessageType");
		bpmDefinition.setCcMessageType(ccMessageType);

		// 测试标签
		String testStatusTag = RequestUtil.getString(request, "testStatusTag");
		bpmDefinition.setTestStatusTag(testStatusTag);

		// 是否手机审批
		Integer allowMobile = RequestUtil.getInt(request, "allowMobile");
		bpmDefinition.setAllowMobile(allowMobile);

		String skipSetting = RequestUtil.getString(request, "skipSetting");
		bpmDefinition.setSkipSetting(skipSetting);

		int count = bpmDefinitionService.saveParam(bpmDefinition);
		if (count > 0) {
			ResultMessage message = new ResultMessage(ResultMessage.Success,
					"设置参数成功");
			out.print(message.toString());
		} else {
			ResultMessage message = new ResultMessage(ResultMessage.Fail,
					"参数设置失败");
			out.print(message.toString());
		}
	}

	@RequestMapping("saveParam_b")
	@Action(description = "拓扑图保存流程参数", detail = "设置流程定义【${SysAuditLinkService.getBpmDefinitionLink(Long.valueOf(defId))}】的其他参数设置")
	public void saveParam_b(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		Bpmnetworkmap bpmnetworkmap = new Bpmnetworkmap();
		// 流程ID
		long defId = RequestUtil.getLong(request, "defId");
		long formDefId = RequestUtil.getLong(request, "formDefId");
		bpmnetworkmap.setDefId(defId);
		System.out.println("第三处输出" + formDefId);
		// 表单变量
		String taskNameRule = RequestUtil.getString(request, "taskNameRule");
		bpmnetworkmap.setTaskNameRule(taskNameRule);
		// 跳过第一个任务
		short toFirstNode = (short) RequestUtil.getInt(request, "toFirstNode");
		bpmnetworkmap.setToFirstNode(toFirstNode);
		// 流程启动选择执行人
		short showFirstAssignee = (short) RequestUtil.getInt(request,
				"showFirstAssignee", 0);
		bpmnetworkmap.setShowFirstAssignee(showFirstAssignee);
		// 表单明细Url
		String formDetailUrl = RequestUtil.getString(request, "formDetailUrl",
				"");
		bpmnetworkmap.setFormDetailUrl(formDetailUrl);
		// 提交是否需要确认
		int submitConfirm = RequestUtil.getInt(request, "submitConfirm", 0);
		bpmnetworkmap.setSubmitConfirm(submitConfirm);
		// 是否允许转办
		int allowDivert = RequestUtil.getInt(request, "allowDivert", 0);
		bpmnetworkmap.setAllowDivert(allowDivert);
		// 是否允许我的办结转发
		int allowFinishedDivert = RequestUtil.getInt(request,
				"allowFinishedDivert", 0);
		bpmnetworkmap.setAllowFinishedDivert(allowFinishedDivert);
		// 归档时发送消息给发起人
		String informStart = RequestUtil.getString(request, "informStart");
		bpmnetworkmap.setInformStart(informStart);
		// 通知类型
		String informType = RequestUtil.getString(request, "informType");
		bpmnetworkmap.setInformType(informType);
		// 是否允许办结抄送
		Integer allowFinishedCc = RequestUtil
				.getInt(request, "allowFinishedCc");
		bpmnetworkmap.setAllowFinishedCc(allowFinishedCc);
		// 流程实例归档后是否允许打印表单
		short isPrintForm = (short) RequestUtil.getInt(request, "isPrintForm",
				0);
		bpmnetworkmap.setIsPrintForm(isPrintForm);
		// 流程帮助 的附件
		Long attachment = RequestUtil.getLong(request, "attachment");
		bpmnetworkmap.setAttachment(attachment);
		// 流程状态
		short status = (short) RequestUtil.getInt(request, "status", 1);
		bpmnetworkmap.setStatus(status);
		// 相邻任务节点人员相同是自动跳过
		short sameExecutorJump = (short) RequestUtil.getInt(request,
				"sameExecutorJump", 0);
		bpmnetworkmap.setSameExecutorJump(sameExecutorJump);

		// 允许API调用
		short isUseOutForm = (short) RequestUtil.getInt(request,
				"isUseOutForm", 0);
		bpmnetworkmap.setIsUseOutForm(isUseOutForm);
		// 流程参考
		Integer allowRefer = RequestUtil.getInt(request, "allowRefer");
		bpmnetworkmap.setAllowRefer(allowRefer);
		// 参考条数
		Integer instanceAmount = RequestUtil.getInt(request, "instanceAmount");
		bpmnetworkmap.setInstanceAmount(instanceAmount);
		// 直接启动流程。
		Integer directstart = RequestUtil.getInt(request, "directstart", 0);
		bpmnetworkmap.setDirectstart(directstart);
		// 抄送消息类型
		String ccMessageType = RequestUtil.getString(request, "ccMessageType");
		bpmnetworkmap.setCcMessageType(ccMessageType);

		// 测试标签
		String testStatusTag = RequestUtil.getString(request, "testStatusTag");
		bpmnetworkmap.setTestStatusTag(testStatusTag);

		// 是否手机审批
		Integer allowMobile = RequestUtil.getInt(request, "allowMobile");
		bpmnetworkmap.setAllowMobile(allowMobile);

		int count = bpmnetworkmapService.saveParam(bpmnetworkmap);
		if (count > 0) {
			ResultMessage message = new ResultMessage(ResultMessage.Success,
					"设置参数成功");
			out.print(message.toString());
		} else {
			ResultMessage message = new ResultMessage(ResultMessage.Fail,
					"参数设置失败");
			out.print(message.toString());
		}
	}

	@RequestMapping("dialog")
	public ModelAndView dialog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = getAutoView();
		int showAll = RequestUtil.getInt(request, "showAll", 0);
		int validStatus = RequestUtil.getInt(request, "validStatus", 0);
		boolean isSingle = RequestUtil.getBoolean(request, "isSingle", false);
		int sign = RequestUtil.getInt(request, "sign", 0);
		System.out.println("这个应该输出才对" + sign);

		mv.addObject("showAll", showAll).addObject("validStatus", validStatus)
				.addObject("isSingle", isSingle).addObject("sign", sign);

		return mv;
	}
	@RequestMapping("eventdialog")
	public ModelAndView eventdialog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = getAutoView();
		int showAll = RequestUtil.getInt(request, "showAll", 0);
		int validStatus = RequestUtil.getInt(request, "validStatus", 0);
		boolean isSingle = RequestUtil.getBoolean(request, "isSingle", false);
		int sign = RequestUtil.getInt(request, "sign", 0);
		System.out.println("这个应该输出才对a:" + sign);

		mv.addObject("showAll", showAll).addObject("validStatus", validStatus)
				.addObject("isSingle", isSingle).addObject("sign", sign);
		return mv;
	}
	/**
	 * 用于对话框选择流程。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	public ModelAndView selector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "bpmDefinitionItem");
		Long typeId = RequestUtil.getLong(request, "typeId", 0);
		// 是否显示全部流程
		int showAll = RequestUtil.getInt(request, "showAll", 0);
		boolean isSingle = RequestUtil.getBoolean(request, "isSingle");
		List<BpmDefinition> list = null;
		// 显示所有的流程，或者不忽略管理员的情况
		if (showAll == 1) {
			if (typeId != 0) {
				GlobalType globalType = globalTypeService.getById(typeId);
				if (BeanUtils.isNotEmpty(globalType))
					filter.getFilters().put("nodePath",
							globalType.getNodePath() + "%");
			}
			list = bpmDefinitionService.getAll(filter);
		} else {
			// 增加流程分管授权的启动权限分配查询判断
			Long userId = ContextUtil.getCurrentUserId();
			String isNeedRight = "";
			@SuppressWarnings("unused")
			Map<String, AuthorizeRight> authorizeRightMap = null;
			if (userId > 0) {
				isNeedRight = "yes";
				// 获得流程分管授权与用户相关的信息
				Map<String, Object> actRightMap = bpmDefAuthorizeService
						.getActRightByUserMap(userId,
								BPMDEFAUTHORIZE_RIGHT_TYPE.START, false, false);
				// 获得流程分管授权与用户相关的信息集合的流程KEY
				String actRights = (String) actRightMap.get("authorizeIds");
				filter.addFilter("actRights", actRights);
			}
			filter.addFilter("isNeedRight", isNeedRight);

			list = bpmDefinitionService.getMyDefList(filter, typeId);

			// list = bpmDefinitionService.getList(filter, typeId);
		}
		ModelAndView mv = getAutoView().addObject("bpmDefinitionList", list)
				.addObject("isSingle", isSingle);
		return mv;
	}
	/**
	 * 用于对话框选择流程。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("eventselector")
	public ModelAndView eventselector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "bpmDefinitionItem");
		Long typeId = RequestUtil.getLong(request, "typeId", 0);
		// 是否显示全部流程
		int showAll = RequestUtil.getInt(request, "showAll", 0);
		boolean isSingle = RequestUtil.getBoolean(request, "isSingle");
		List<BpmDefinition> list = null;
		List<BpmDefinition> eventlist = new ArrayList<BpmDefinition>();
		// 显示所有的流程，或者不忽略管理员的情况
		if (showAll == 1) {
			if (typeId != 0) {
				GlobalType globalType = globalTypeService.getById(typeId);
				if (BeanUtils.isNotEmpty(globalType))
					filter.getFilters().put("nodePath",
							globalType.getNodePath() + "%");
			}
			list = bpmDefinitionService.getAll(filter);
			for(int i = 0;i<list.size();i++){
				if((list.get(i).getTypeId().toString().equals("10000065960139"))){
					eventlist.add(list.get(i));
				}
			}			
		} else {
			// 增加流程分管授权的启动权限分配查询判断
			Long userId = ContextUtil.getCurrentUserId();
			String isNeedRight = "";
			@SuppressWarnings("unused")
			Map<String, AuthorizeRight> authorizeRightMap = null;
			if (userId > 0) {
				isNeedRight = "yes";
				// 获得流程分管授权与用户相关的信息
				Map<String, Object> actRightMap = bpmDefAuthorizeService
						.getActRightByUserMap(userId,
								BPMDEFAUTHORIZE_RIGHT_TYPE.START, false, false);
				// 获得流程分管授权与用户相关的信息集合的流程KEY
				String actRights = (String) actRightMap.get("authorizeIds");
				filter.addFilter("actRights", actRights);
			}
			filter.addFilter("isNeedRight", isNeedRight);

			list = bpmDefinitionService.getMyDefList(filter, typeId);

			// list = bpmDefinitionService.getList(filter, typeId);
		}
		ModelAndView mv = getAutoView().addObject("bpmDefinitionList", eventlist)
				.addObject("isSingle", isSingle);
		return mv;
	}
	/**
	 * 导出xml窗口
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("export")
	@Action(description = "导出xml窗口")
	public ModelAndView export(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String bpmDefIds = RequestUtil.getString(request, "bpmDefIds");
		ModelAndView mv = this.getAutoView();
		mv.addObject("bpmDefIds", bpmDefIds);
		return mv;
	}

	/**
	 * 导出流程定义信息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportXml")
	@Action(description = "导出流程定义信息")
	public void exportXml(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long[] bpmDefIds = RequestUtil.getLongAryByStr(request, "bpmDefIds");
		if (BeanUtils.isEmpty(bpmDefIds))
			return;
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("bpmDefinition", true);
		map.put("bpmDefinitionHistory", RequestUtil.getBoolean(request,
				"bpmDefinitionHistory"));
		map.put("bpmNodeSet", true);
		map.put("bpmUserCondition", RequestUtil.getBoolean(request,
				"bpmUserCondition"));
		map.put("bpmNodeUser", RequestUtil.getBoolean(request, "bpmNodeUser"));
		map.put("bpmNodeUserUplow", RequestUtil.getBoolean(request,
				"bpmNodeUserUplow"));
		map
				.put("bpmDefRights", RequestUtil.getBoolean(request,
						"bpmDefRights"));
		map.put("taskApprovalItems", RequestUtil.getBoolean(request,
				"taskApprovalItems"));
		map.put("bpmNodeRule", RequestUtil.getBoolean(request, "bpmNodeRule"));
		map.put("bpmNodeScript", RequestUtil.getBoolean(request,
				"bpmNodeScript"));
		map.put("bpmNodeButton", RequestUtil.getBoolean(request,
				"bpmNodeButton"));
		map.put("bpmDefVar", RequestUtil.getBoolean(request, "bpmDefVar"));
		map.put("bpmNodeMessage", RequestUtil.getBoolean(request,
				"bpmNodeMessage"));
		map.put("bpmNodeSign", RequestUtil.getBoolean(request, "bpmNodeSign"));
		map
				.put("taskReminder", RequestUtil.getBoolean(request,
						"taskReminder"));
		map.put("bpmGangedSet", true);
		map.put("bpmReferDefinition", true);
		map.put("subBpmDefinition", RequestUtil.getBoolean(request,
				"subBpmDefinition"));
		map.put("bpmFormDef", RequestUtil.getBoolean(request, "bpmFormDef"));
		map.put("bpmFormTable", RequestUtil.getBoolean(request, "bpmFormDef"));
		try {
			exportZip(bpmDefIds, map, response, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出压缩文件
	 * 
	 * @param bpmDefIds
	 * @param map
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	private void exportZip(Long[] bpmDefIds, Map<String, Boolean> map,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		String nowDate = DateFormatUtil.getNowByString("yyyyMMddHHmmss");
		String fileName = "bpmDefinition_" + nowDate;
		// String name = nowDate;
		// if(bpmDefIds.length==1){
		// BpmDefinition bpmDefinition = dao.getById(bpmDefIds[0]);
		// name = bpmDefinition.getSubject()+"_"+nowDate;
		// }
		// else name="多条流程定义记录_"+nowDate;
		String zipFileName = fileName + ".zip";

		String realFilePath = StringUtil.trimSufffix(ServiceUtil.getBasePath()
				.toString(), File.separator)
				+ File.separator
				+ "attachFiles"
				+ File.separator
				+ "tempZip"
				+ File.separator;

		String filePath = realFilePath + fileName;
		String zipFilePath = realFilePath + File.separator + zipFileName;

		// 导出xml
		String strXml = bpmDefinitionService
				.exportXml(bpmDefIds, map, filePath);

		// 写XML
		FileXmlUtil.writeXmlFile(fileName + ".flow.xml", strXml, filePath);
		// logger.info("写XML：" + fileName + ".flow.xml");
		// 打包
		ZipUtil.zip(filePath, true);
		// logger.info("打包：" + zipFileName);
		// 导出
		FileUtil.downLoadFile(request, response, zipFilePath, zipFileName);
		// logger.info("导出：" + zipFileName);
		// 删除导出的文件
		FileUtil.deleteFile(zipFilePath);
		// logger.info("删除导出的文件：" + zipFileName);
	}

	/**
	 * 导入流程定义信息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("importXml")
	@Action(description = "导入流程定义信息")
	public void importXml(MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MultipartFile fileLoad = request.getFile("xmlFile");
		ResultMessage message = null;
		try {
			bpmDefinitionService.importZip(fileLoad);
			message = new ResultMessage(ResultMessage.Success, MsgUtil
					.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			MsgUtil.clean();
			FileUtil.deleteFile(MsgUtil.getFilePath());
			MsgUtil.cleanFilePath();
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				message = new ResultMessage(ResultMessage.Fail, "导入失败:" + str);
			} else {
				String msg = ExceptionUtil.getExceptionMessage(e);
				message = new ResultMessage(ResultMessage.Fail, msg);
			}

		}
		writeResultMessage(response.getWriter(), message);
	}

	/**
	 * 编辑节点设置为分发或汇总
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editForkJoin")
	public ModelAndView editForkJoin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String actDefId = request.getParameter("actDefId");
		String nodeId = request.getParameter("nodeId");

		BpmNodeSet bpmNodeSet = bpmNodeSetService.getByMyActDefIdNodeId(
				actDefId, nodeId);

		ModelAndView view = getAutoView();

		if (bpmNodeSet != null) {
			view.addObject("bpmNodeSet", bpmNodeSet);
		}

		Map<String, String> nodeMap = bpmService.getExecuteNodesMap(actDefId,
				false);
		nodeMap.remove(nodeId);

		view.addObject("actDefId", actDefId);
		view.addObject("nodeId", nodeId);
		view.addObject("nodeMap", nodeMap);

		return view;
	}

	/**
	 * 保存节点分发或汇总的设置
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveForkJoin")
	@Action(description = "保存节点分发或汇总的设置", detail = "设置流程定义【${SysAuditLinkService.getBpmDefinitionLink(actDefId)}】节点"
			+ "【${SysAuditLinkService.getNodeName(actDefId,nodeId)}】为"
			+ "<#if '0'==nodeType>【普通任务】<#else>【分发任务】,汇总任务名称为【${joinTaskName}】</#if>")
	public void saveForkJoin(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String actDefId = request.getParameter("actDefId");
		String nodeId = request.getParameter("nodeId");
		String nodeType = request.getParameter("nodeType");
		String joinTaskKey = request.getParameter("joinTaskKey");
		String joinTaskName = request.getParameter("joinTaskName");

		BpmNodeSet bpmNodeSet = bpmNodeSetService.getByMyActDefIdNodeId(
				actDefId, nodeId);
		BpmNodeSet joinKeyNodeSet = bpmNodeSetService.getByActDefIdJoinTaskKey(
				actDefId, joinTaskKey);

		// 查看该汇总是否被其他节点设置了为汇总节点
		if (joinKeyNodeSet != null) {
			if (bpmNodeSet == null
					|| (bpmNodeSet != null && !bpmNodeSet.getSetId().equals(
							joinKeyNodeSet.getSetId()))) {
				writeResultMessage(response.getWriter(), "该汇总节点["
						+ joinTaskName + "]已经由 ["
						+ joinKeyNodeSet.getNodeName() + "]节点设置了！",
						ResultMessage.Fail);
				return;
			}
		}
		if (bpmNodeSet != null) {
			bpmNodeSet.setNodeType(new Short(nodeType));
			bpmNodeSet.setJoinTaskKey(joinTaskKey);
			bpmNodeSet.setJoinTaskName(joinTaskName);
			bpmNodeSetService.update(bpmNodeSet);
			writeResultMessage(response.getWriter(), "成功设置分发或汇总",
					ResultMessage.Success);
		}
	}

	/**
	 * 设置流程任务节点是否支持手机查看页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("mobileSet")
	public ModelAndView mobileSet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String actDefId = request.getParameter("actDefId");
		String nodeId = request.getParameter("nodeId");
		BpmNodeSet bpmNodeSet = bpmNodeSetService.getByActDefIdNodeId(actDefId,
				nodeId);
		return getAutoView().addObject("bpmNodeSet", bpmNodeSet);
	}

	/**
	 * 清除流程数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("cleanData")
	@Action(description = "清除测试状态的流程数据", detail = "清除流程定义【${SysAuditLinkService.getBpmDefinitionLink(Long.parseLong(defId))}】"
			+ "测试状态下启动流程产生的流程数据")
	public void cleanData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		try {
			bpmDefinitionService.cleanData(defId);
			writeResultMessage(response.getWriter(), new ResultMessage(
					ResultMessage.Success, "清除数据成功"));
		} catch (Exception e) {
			e.printStackTrace();
			writeResultMessage(response.getWriter(), new ResultMessage(
					ResultMessage.Fail, "清除数据失败"));
		}

	}

	/**
	 * 设置任务节点通知方法页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("informType")
	public ModelAndView informType(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String actDefId = request.getParameter("actDefId");
		String nodeId = request.getParameter("nodeId");
		String parentActDefId = RequestUtil.getString(request,
				"parentActDefId", "");
		BpmNodeSet bpmNodeSet = null;
		if (StringUtil.isEmpty(parentActDefId)) {
			bpmNodeSet = bpmNodeSetService.getBpmNodeSetByActDefIdNodeId(
					actDefId, nodeId);
		} else {
			bpmNodeSet = bpmNodeSetService.getBpmNodeSetByActDefIdNodeId(
					actDefId, nodeId, parentActDefId);
		}

		Map handlersMap = ServiceUtil.getHandlerMap();

		return getAutoView().addObject("bpmNodeSet", bpmNodeSet).addObject(
				"handlersMap", handlersMap).addObject("parentActDefId",
				parentActDefId);
	}

	/**
	 * 保存节点任务的通知方式设置
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("saveInformType")
	@Action(description = "设置节点任务的通知方式", detail = "设置流程【${SysAuditLinkService.getBpmDefinitionLink(actDefId)}】的节点"
			+ "【${SysAuditLinkService.getNodeName(actDefId,nodeId)}】的任务通知方式"
			+ "<#if '1'==isSendMail>"
			+ "【邮件方式】"
			+ "</#if>"
			+ "<#if '1'==isSendMobile>" + "【短信方式】" + "</#if>"

	)
	public void saveInformType(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String actDefId = request.getParameter("actDefId");
		String nodeId = request.getParameter("nodeId");
		String informType = RequestUtil.getString(request, "informTypes");
		String parentActDefId = RequestUtil.getString(request,
				"parentActDefId", "");
		BpmNodeSet bpmNodeSet = null;
		if (StringUtil.isEmpty(parentActDefId)) {
			bpmNodeSet = bpmNodeSetService.getBpmNodeSetByActDefIdNodeId(
					actDefId, nodeId);
		} else {
			bpmNodeSet = bpmNodeSetService.getBpmNodeSetByActDefIdNodeId(
					actDefId, nodeId, parentActDefId);
		}
		bpmNodeSet.setInformType(informType);
		try {
			bpmNodeSetService.update(bpmNodeSet);
			writeResultMessage(response.getWriter(), new ResultMessage(
					ResultMessage.Success, "设置成功"));
		} catch (Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(
						ResultMessage.Fail, "设置失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(
						ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}

	/**
	 * 设置流程任务节点是否支持手机
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("setMobile")
	@Action(description = "设置流程任务节点是否支持手机", detail = "设置流程【${SysAuditLinkService.getBpmDefinitionLink(actDefId)}】的任务节点"
			+ "【${SysAuditLinkService.getNodeName(actDefId,nodeId)}】"
			+ "<#if '1'==isAllowMobile>能<#else>不</#if>支持手机访问")
	public void setMobile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String actDefId = request.getParameter("actDefId");
		String nodeId = request.getParameter("nodeId");
		Short isAllowMobile = RequestUtil.getShort(request, "isAllowMobile");
		BpmNodeSet bpmNodeSet = bpmNodeSetService.getByActDefIdNodeId(actDefId,
				nodeId);
		bpmNodeSet.setIsAllowMobile(isAllowMobile);
		try {
			bpmNodeSetService.update(bpmNodeSet);
			writeResultMessage(response.getWriter(), new ResultMessage(
					ResultMessage.Success, "设置成功"));
		} catch (Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(
						ResultMessage.Fail, "设置失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(
						ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}

	@RequestMapping("taskNodes")
	public ModelAndView taskNodes(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String actDefId = RequestUtil.getString(request, "actDefId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		Map<String, String> taskDefNodeMap = bpmService.getTaskNodes(actDefId,
				nodeId);
		ModelAndView view = getAutoView();
		view.addObject("taskNodeMap", taskDefNodeMap);
		return view;
	}

	/**
	 * 选择节点
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selectNodes")
	public ModelAndView selectNodes(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String defKey = RequestUtil.getString(request, "defKey");
		BpmDefinition bpmDefinition = bpmDefinitionService
				.getMainByDefKey(defKey);
		Map<String, String> taskNodeMap = new HashMap<String, String>();
		if (BeanUtils.isNotEmpty(bpmDefinition))
			taskNodeMap = bpmService.getExecuteNodesMap(bpmDefinition
					.getActDefId(), false);

		return getAutoView().addObject("taskNodeMap", taskNodeMap);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("showScript")
	public ModelAndView showScript(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String defId = RequestUtil.getString(request, "defId");
		String parentActDefId = RequestUtil.getString(request,
				"parentActDefId", "");
		ModelAndView view = getAutoView();
		view.addObject("defId", defId);
		view.addObject("parentActDefId", parentActDefId);
		return view;
	}

	/**
	 * 根据流程定义id获取流程定义 并判断是否有开始节点表单的配置。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getCanDirectStart")
	@ResponseBody
	public boolean getCanDirectStart(HttpServletRequest request)
			throws Exception {
		// System.out.println("getCanDirectStart");
		Long defId = RequestUtil.getLong(request, "defId");
		boolean rtn = bpmFormRunService.getCanDirectStart(defId);
		// System.out.println("rtn="+rtn);
		return rtn;
	}

	/**
	 * 通过流程定义标题自动生成流程KEY
	 * 
	 * @param request
	 * @return flowkey
	 * @throws Exception
	 */
	@RequestMapping("getFlowKey")
	@Action(description = "流程在线设计，通过标题获取defkey")
	public ModelAndView getFlowKey(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String subject = RequestUtil.getString(request, "subject");
		if (StringUtil.isEmpty(subject))
			return null;
		String pingyin = shareService.getPingyin(subject);
		String key = pingyin;
		int count = 0;
		// BpmDefinition bpmDefinition;
		do {
			key = pingyin + (count == 0 ? "" : count);
			count++;
		} while (bpmDefinitionService.isActDefKeyExists(key));
		PrintWriter out = response.getWriter();
		out.println(key);
		return null;
	}

	@RequestMapping("getFlowListByTypeId")
	@Action(description = "流程在线设计，获取分类的所有流程")
	public ModelAndView getFlowListByTypeId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = RequestUtil.getString(request, "typeId");
		String word = RequestUtil.getString(request, "word");
		List<BpmDefinition> list;
		if (StringUtil.isEmpty(word)) {
			list = bpmDefinitionService.getPublishedByTypeId(id);
		} else {
			list = bpmDefinitionService.getAllPublished("%" + word + "%");
		}
		StringBuffer msg = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><Result>");
		for (BpmDefinition bpmDefinition : list) {
			msg.append("<item name=\"" + bpmDefinition.getSubject()
					+ "\" key=\"" + bpmDefinition.getDefKey() + "\" type=\""
					+ bpmDefinition.getTypeId() + "\"></item>");
		}
		msg.append("</Result>");
		PrintWriter out = response.getWriter();
		out.println(msg.toString());
		return null;
	}

	// yq modify
	@RequestMapping("getFlowListByTypeId_b")
	@Action(description = "流程在线设计，获取分类的所有流程")
	public ModelAndView getFlowListByTypeId_b(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = RequestUtil.getString(request, "typeId");
		String word = RequestUtil.getString(request, "word");
		List<Bpmnetworkmap> list;
		if (StringUtil.isEmpty(word)) {
			list = bpmnetworkmapService.getPublishedByTypeId(id);
		} else {
			list = bpmnetworkmapService.getAllPublished("%" + word + "%");
		}
		StringBuffer msg = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><Result>");
		for (Bpmnetworkmap bpmDefinition : list) {
			msg.append("<item name=\"" + bpmDefinition.getSubject()
					+ "\" key=\"" + bpmDefinition.getDefKey() + "\" type=\""
					+ bpmDefinition.getTypeId() + "\"></item>");
		}
		msg.append("</Result>");
		PrintWriter out = response.getWriter();
		out.println(msg.toString());
		return null;
	}

	// end

	/**
	 * 外部子过程的流程示意图
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("subFlowImage")
	public ModelAndView subFlowImage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String subDefKey = null;
		BpmDefinition subBpmDefinition = null;
		String subDefXml = null;
		Long defId = RequestUtil.getLong(request, "defId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		String defXml = null;
		if (bpmDefinition.getActDeployId() != null) {
			defXml = bpmService.getDefXmlByDeployId(bpmDefinition
					.getActDeployId().toString());
		} else {
			defXml = BpmUtil.transform(bpmDefinition.getDefKey(), bpmDefinition
					.getSubject(), bpmDefinition.getDefXml());
		}
		defXml = defXml.replace(
				"xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"", "");
		Document doc = Dom4jUtil.loadXml(defXml);
		Element root = doc.getRootElement();

		List<Node> callActivityList = root
				.selectNodes("./process//callActivity");
		for (Node node : callActivityList) {
			Element element = (Element) node;
			String id = element.attributeValue("id");
			if (id.equals(nodeId)) {
				subDefKey = element.attributeValue("calledElement");
				break;
			}
		}
		subBpmDefinition = bpmDefinitionService
				.getMainDefByActDefKey(subDefKey);

		if (subBpmDefinition.getActDeployId() != null) {
			subDefXml = bpmService.getDefXmlByDeployId(subBpmDefinition
					.getActDeployId().toString());
		} else {
			subDefXml = BpmUtil
					.transform(subBpmDefinition.getDefKey(), subBpmDefinition
							.getSubject(), subBpmDefinition.getDefXml());
		}
		ShapeMeta shapeMeta = BpmUtil.transGraph(subDefXml);
		ModelAndView mv = getAutoView();
		mv.addObject("bpmDefinition", subBpmDefinition);
		mv.addObject("defXml", subDefXml);
		mv.addObject("shapeMeta", shapeMeta);
		return mv;
	}

	/**
	 * 外部子过程的流程明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("subFlowDetail")
	public void subFlowDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String nodeId = RequestUtil.getString(request, "nodeId");
		String parentActDefId = RequestUtil.getString(request, "actDefId");

		FlowNode flowNode = NodeCache
				.getNodeByActNodeId(parentActDefId, nodeId);
		String subDefKey = flowNode.getAttribute("subFlowKey");

		if (StringUtil.isEmpty(subDefKey)) {
			throw new Exception("传入节点非子流程节点,请检查输入");
		}

		BpmDefinition subBpmDefinition = bpmDefinitionService
				.getMainDefByActDefKey(subDefKey);

		response.sendRedirect(request.getContextPath()
				+ "/platform/bpm/bpmDefinition/detail.ht?defId="
				+ subBpmDefinition.getDefId() + "&parentActDefId="
				+ parentActDefId);
	}

	/**
	 * 外部子过程的流程示意图
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("typeSetDialog")
	public ModelAndView typeSetDialog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Demension> demensionList = demensionService.getAll();
		List<SysOrgType> sysOrgTypelist = sysOrgTypeService.getAll();
		ModelAndView mv = getAutoView().addObject("demensionList",
				demensionList).addObject("sysOrgTypelist", sysOrgTypelist);
		return mv;
	}

	@RequestMapping("conditionEdit")
	public ModelAndView conditionEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		Long conditionId = RequestUtil.getLong(request, "conditionId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		// 默认为流程人员，可以的值是抄送人员，消息节点人员。
		Integer conditionType = RequestUtil.getInt(request, "conditionType", 0);
		String parentActDefId = RequestUtil.getString(request,
				"parentActDefId", "");

		// 获取表单结果。
		BpmFormResult bpmFormResult = null;
		if (StringUtil.isEmpty(parentActDefId)) {
			bpmFormResult = bpmDefinitionService.getBpmFormResult(defId);
		} else {
			bpmFormResult = bpmDefinitionService.getBpmFormResult(defId,
					parentActDefId);
		}

		if (bpmFormResult.getResult() == 1) {
			return ServiceUtil.getTipInfo("系统表单对应多个主表，请先修改流程定义表单配置!");
		}

		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		ModelAndView modelView = getAutoView();
		String actDefId = bpmDefinition.getActDefId();

		// 获取节点名称
		String nodeName = "";
		boolean isMultiInstance = false;
		// 获取节点设置名称是否会签节点。
		if (StringUtil.isNotEmpty(nodeId)) {
			FlowNode flowNode = NodeCache.getNodeByActNodeId(actDefId, nodeId);
			if (flowNode != null) {
				nodeName = flowNode.getNodeName();
				if ("userTask" == flowNode.getNodeType()) {
					isMultiInstance = flowNode.getIsMultiInstance();
				}
			}
		}

		List<BpmNodeUser> userList = new ArrayList<BpmNodeUser>();
		// conditionId不为空说明是修改表单规则
		if (conditionId != null && conditionId > 0) {
			BpmUserCondition bpmUserCondition = bpmUserConditionService
					.getById(conditionId);
			if (bpmUserCondition != null) {
				String formIdentity = bpmUserCondition.getFormIdentity();
				if (bpmFormResult.getResult() == 0) {
					String name = bpmFormResult.getTableName();
					// 表名不相等，则说明 之前设置的规则不适合现在的表
					if (!name.equals(formIdentity)) {
						bpmUserCondition.setCondition("");
					}
				}
			}
			modelView.addObject("bpmUserCondition", bpmUserCondition);
			userList = bpmNodeUserService.getByConditionId(conditionId);
		} else {

			BpmUserCondition bpmUserCondition = new BpmUserCondition();
			Long currentSn = TimeUtil.getCurrentTimeMillis();
			bpmUserCondition.setSn(currentSn);
			bpmUserCondition.setFormIdentity(bpmFormResult.getTableName());
			bpmUserCondition.setConditionType(conditionType);
			modelView.addObject("bpmUserCondition", bpmUserCondition);
		}
		// 添加节点名称
		modelView.addObject("nodeName", nodeName);
		// 添加用户选项。
		modelView.addObject("userList", userList);
		// 是否多实例
		modelView.addObject("isMultiInstance", isMultiInstance);
		// 获取流程变量
		List<BpmFormField> flowVars = null;
		if (StringUtil.isEmpty(parentActDefId)) {
			flowVars = bpmFormFieldService.getFlowVarByFlowDefId(defId, true);
		} else {
			flowVars = bpmFormFieldService.getFlowVarByFlowDefId(defId,
					parentActDefId, true);
		}
		List<BpmDefVar> bpmdefVars = bpmDefVarService.getVarsByFlowDefId(defId);
		// 获取人员类型。
		Map<String, IBpmNodeUserCalculation> assignUserTypes = bpmNodeUserCalculationSelector
				.getBpmNodeUserCalculation();

		modelView.addObject("flowVars", flowVars).addObject("defVars",
				bpmdefVars).addObject("bpmDefinition", bpmDefinition)
				.addObject("defId", defId).addObject("nodeId", nodeId)
				.addObject("assignUserTypes", assignUserTypes).addObject(
						"userList", userList).addObject("parentActDefId",
						parentActDefId);

		return modelView;
	}

	/**
	 * 节点概要
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("nodeSummary")
	@Action(description = "节点概要")
	public ModelAndView nodeSummary(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		String parentActDefId = RequestUtil.getString(request,
				"parentActDefId", "");
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		List<BpmNodeSet> nodeSetList = null;
		BpmNodeSet globalNodeSet = null;
		if (StringUtil.isEmpty(parentActDefId)) {
			nodeSetList = bpmNodeSetService.getByDefId(defId);
			globalNodeSet = bpmNodeSetService.getBySetType(defId,
					BpmNodeSet.SetType_GloabalForm);
		} else {
			nodeSetList = bpmNodeSetService.getByDefId(defId, parentActDefId);
			globalNodeSet = bpmNodeSetService.getBySetType(defId,
					BpmNodeSet.SetType_GloabalForm, parentActDefId);
		}
		for (BpmNodeSet nodeSet : nodeSetList) {
			if (nodeSet.getNodeId() == null) {
				nodeSetList.remove(nodeSet);
				break;
			}
		}

		String actDefId = bpmDefinition.getActDefId();
		FlowNode startFlowNode = NodeCache.getStartNode(actDefId);
		List<FlowNode> endFlowNodeList = NodeCache.getEndNode(actDefId);

		Map<String, Boolean> startScriptMap = new HashMap<String, Boolean>();
		Map<String, Boolean> endScriptMap = new HashMap<String, Boolean>();
		Map<Long, Boolean> preScriptMap = new HashMap<Long, Boolean>();
		Map<Long, Boolean> afterScriptMap = new HashMap<Long, Boolean>();

		Map<Long, Boolean> assignScriptMap = new HashMap<Long, Boolean>();
		Map<Long, Boolean> nodeRulesMap = new HashMap<Long, Boolean>();

		Map<Long, Boolean> bpmFormMap = new HashMap<Long, Boolean>();
		Map<String, Boolean> buttonMap = new HashMap<String, Boolean>();
		Map<Long, Boolean> nodeButtonMap = new HashMap<Long, Boolean>();
		Map<Long, Boolean> taskReminderMap = new HashMap<Long, Boolean>();
		Map<Long, Boolean> mobileSetMap = new HashMap<Long, Boolean>();
		Map<Long, Boolean> nodeUserMap = new HashMap<Long, Boolean>();
		Map<String, Boolean> formMap = new HashMap<String, Boolean>();
		Map<Long, Boolean> taskApprovalItemsMap = new HashMap<Long, Boolean>();
		Map<String, Boolean> globalApprovalMap = new HashMap<String, Boolean>();
		// 用户设置
		this.getNodeUserMap(nodeSetList, actDefId, nodeUserMap, parentActDefId);

		// 流程事件(脚本)
		this.getNodeScriptMap(nodeSetList, actDefId, startScriptMap,
				endScriptMap, preScriptMap, afterScriptMap, assignScriptMap);
		// 流程节点规则
		this.getNodeRulesMap(nodeSetList, actDefId, nodeRulesMap);

		// 操作按钮
		this.getNodeButtonMap(nodeSetList, defId, buttonMap, nodeButtonMap);

		// 催办信息
		this.getTaskReminderMap(nodeSetList, actDefId, taskReminderMap);
		// 手机，表单
		this.getNodeSetMap(nodeSetList, bpmFormMap, mobileSetMap);

		// 全局
		if (checkForm(globalNodeSet))
			formMap.put("global", true);
		return this.getAutoView().addObject("bpmDefinition", bpmDefinition)
				.addObject("deployId", bpmDefinition.getActDeployId())
				.addObject("defId", defId).addObject("actDefId", actDefId)
				.addObject("nodeSetList", nodeSetList).addObject(
						"startScriptMap", startScriptMap).addObject(
						"endScriptMap", endScriptMap).addObject("preScriptMap",
						preScriptMap).addObject("afterScriptMap",
						afterScriptMap).addObject("assignScriptMap",
						assignScriptMap)
				.addObject("nodeRulesMap", nodeRulesMap).addObject(
						"nodeUserMap", nodeUserMap).addObject("bpmFormMap",
						bpmFormMap).addObject("formMap", formMap).addObject(
						"buttonMap", buttonMap).addObject("nodeButtonMap",
						nodeButtonMap).addObject("taskReminderMap",
						taskReminderMap).addObject("taskApprovalItemsMap",
						taskApprovalItemsMap).addObject("globalApprovalMap",
						globalApprovalMap).addObject("mobileSetMap",
						mobileSetMap).addObject("startFlowNode", startFlowNode)
				.addObject("endFlowNodeList", endFlowNodeList).addObject(
						"parentActDefId", parentActDefId);

	}

	/**
	 * 用户设置
	 * 
	 * @param nodeSetList
	 * @param actDefId
	 * @param nodeUserMap
	 */
	private void getNodeUserMap(List<BpmNodeSet> nodeSetList, String actDefId,
			Map<Long, Boolean> nodeUserMap, String parentActDefId) {
		List<BpmUserCondition> bpmUserConditionList = null;
		if (StringUtil.isEmpty(parentActDefId)) {
			bpmUserConditionList = bpmUserConditionService
					.getByActDefId(actDefId);
		} else {
			bpmUserConditionList = bpmUserConditionService
					.getByActDefIdWithParent(actDefId, parentActDefId);
		}
		for (BpmNodeSet nodeSet : nodeSetList) {

			for (BpmUserCondition bpmUserCondition : bpmUserConditionList) {
				if (nodeSet.getNodeId().equals(bpmUserCondition.getNodeid()))
					nodeUserMap.put(nodeSet.getSetId(), true);
			}

		}
	}

	private void getNodeSetMap(List<BpmNodeSet> nodeSetList,
			Map<Long, Boolean> bpmFormMap, Map<Long, Boolean> mobileSetMap) {
		for (BpmNodeSet nodeSet : nodeSetList) {
			if (nodeSet.getIsAllowMobile().shortValue() == 1)
				mobileSetMap.put(nodeSet.getSetId(), true);
			if (checkForm(nodeSet))
				bpmFormMap.put(nodeSet.getSetId(), true);
		}
	}

	/**
	 * 判断是否设置表单
	 * 
	 * @param nodeSet
	 * @return
	 */
	private Boolean checkForm(BpmNodeSet bpmNodeSet) {
		if (BeanUtils.isEmpty(bpmNodeSet))
			return false;
		if (bpmNodeSet.getFormType().shortValue() == BpmNodeSet.FORM_TYPE_ONLINE
				.shortValue()) {
			String formKey = bpmNodeSet.getFormKey();
			// if (bpmNodeSet.getFormKey().longValue() > 0L)
			if (StringUtil.isNotEmpty(formKey)) {
				return true;
			}

		} else if (bpmNodeSet.getFormType().shortValue() == BpmNodeSet.FORM_TYPE_URL
				.shortValue()) {
			if (StringUtil.isNotEmpty(bpmNodeSet.getFormUrl()))
				return true;
		}
		return false;
	}

	/**
	 * 流程事件(脚本)
	 * 
	 * @param nodeSetList
	 * @param actDefId
	 * @param startScriptMap
	 * @param endScriptMap
	 * @param preScriptMap
	 * @param afterScriptMap
	 * @param assignScript
	 * @return
	 */
	private void getNodeScriptMap(List<BpmNodeSet> nodeSetList,
			String actDefId, Map<String, Boolean> startScriptMap,
			Map<String, Boolean> endScriptMap, Map<Long, Boolean> preScriptMap,
			Map<Long, Boolean> afterScriptMap,
			Map<Long, Boolean> assignScriptMap) {
		// 这个逻辑有问题，暂时这样处理，前置脚本和开始节点脚本为同一种类型，后置脚本跟结束节点脚本为同一种类型。
		List<BpmNodeScript> bpmNodeScriptList = bpmNodeScriptService
				.getByNodeScriptId(null, actDefId);
		// 处理开始和结束
		for (BpmNodeScript bpmNodeScript : bpmNodeScriptList) {
			if (StringUtil.isNotEmpty(bpmNodeScript.getNodeId())) {
				if (bpmNodeScript.getScriptType().intValue() == BpmNodeScript.SCRIPT_TYPE_1
						.intValue())
					startScriptMap.put(bpmNodeScript.getNodeId(), true);
				else if (bpmNodeScript.getScriptType().intValue() == BpmNodeScript.SCRIPT_TYPE_2
						.intValue())
					endScriptMap.put(bpmNodeScript.getNodeId(), true);
			}
		}
		// 处理前置、后置，分配
		for (BpmNodeSet nodeSet : nodeSetList) {
			for (BpmNodeScript bpmNodeScript : bpmNodeScriptList) {
				if (StringUtil.isNotEmpty(bpmNodeScript.getNodeId())
						&& nodeSet.getNodeId()
								.equals(bpmNodeScript.getNodeId())) {
					if (bpmNodeScript.getScriptType().intValue() == BpmNodeScript.SCRIPT_TYPE_1
							.intValue())
						preScriptMap.put(nodeSet.getSetId(), true);

					else if (bpmNodeScript.getScriptType().intValue() == BpmNodeScript.SCRIPT_TYPE_2
							.intValue())
						afterScriptMap.put(nodeSet.getSetId(), true);

					else if (bpmNodeScript.getScriptType().intValue() == BpmNodeScript.SCRIPT_TYPE_4
							.intValue())
						assignScriptMap.put(nodeSet.getSetId(), true);
				}

			}
		}
	}

	/**
	 * 节点规则
	 * 
	 * @param nodeSetList
	 * @param actDefId
	 * @param nodeRulesMap
	 * @return
	 */
	private void getNodeRulesMap(List<BpmNodeSet> nodeSetList, String actDefId,
			Map<Long, Boolean> nodeRulesMap) {
		List<BpmNodeRule> nodeRuleList = bpmNodeRuleService
				.getByActDefId(actDefId);
		for (BpmNodeSet nodeSet : nodeSetList) {
			for (BpmNodeRule bpmNodeRule : nodeRuleList) {
				if (nodeSet.getNodeId().equals(bpmNodeRule.getNodeId()))
					nodeRulesMap.put(nodeSet.getSetId(), true);
			}
		}
	}

	/**
	 * 操作按钮
	 * 
	 * @param nodeSetList
	 * @param defId
	 * @param buttonMap
	 * @param nodeButtonMap
	 */
	private void getNodeButtonMap(List<BpmNodeSet> nodeSetList, Long defId,
			Map<String, Boolean> buttonMap, Map<Long, Boolean> nodeButtonMap) {
		List<BpmNodeButton> nodeButtonList = bpmNodeButtonService
				.getByDefId(defId);
		for (BpmNodeButton bpmNodeButton : nodeButtonList) {
			buttonMap.put(bpmNodeButton.getNodeid(), true);
		}

		for (BpmNodeSet nodeSet : nodeSetList) {
			for (BpmNodeButton bpmNodeButton : nodeButtonList) {
				if (nodeSet.getNodeId().equals(bpmNodeButton.getNodeid()))
					nodeButtonMap.put(nodeSet.getSetId(), true);
			}
		}
	}

	private void getTaskReminderMap(List<BpmNodeSet> nodeSetList,
			String actDefId, Map<Long, Boolean> taskReminderMap) {
		List<TaskReminder> taskReminderList = taskReminderService
				.getByActDefId(actDefId);
		for (BpmNodeSet nodeSet : nodeSetList) {
			for (TaskReminder taskReminder : taskReminderList) {
				if (nodeSet.getNodeId().equals(taskReminder.getNodeId()))
					taskReminderMap.put(nodeSet.getSetId(), true);
			}
		}
	}

	/**
	 * 更新流程定义启用状态 0启用，1禁止
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("updateDisableStatus")
	@Action(description = "更新流程定义启用状态", detail = "设置流程定义【${SysAuditLinkService.getBpmDefinitionLink(Long.valueOf(defId))}】为"
			+ "<#if '1'==disableStatus>启用<#else>禁止</#if>状态")
	public void updateDisableStatus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		Short disableStatus = RequestUtil.getShort(request, "disableStatus");
		// 从启用改成禁止，或从禁止改成启用
		disableStatus = (disableStatus == BpmDefinition.STATUS_DISABLED ? BpmDefinition.STATUS_ENABLED
				: BpmDefinition.STATUS_DISABLED);
		bpmDefinitionService.updateDisableStatus(defId, disableStatus);
		response.sendRedirect(RequestUtil.getPrePage(request));
	}

	/**
	 * 检查流程版本
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("checkVersion")
	@Action(description = "检查流程版本")
	public String checkVersion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		JSONObject jsonObject = new JSONObject();
		try {
			if (BpmDefinition.STATUS_INST_DISABLED.equals(bpmDefinition
					.getStatus())) {
				jsonObject.accumulate("success", false).accumulate("msg",
						"流程定义已经禁用实例！");
				return jsonObject.toString();
			}
			jsonObject.accumulate("success", true).accumulate("isMain",
					bpmDefinition.getIsMain()).accumulate("msg", "检查版本成功!");
		} catch (Exception e) {
			jsonObject.accumulate("success", false).accumulate("msg",
					"检查版本错误!" + e.getMessage());
		}
		return jsonObject.toString();

	}

	@RequestMapping("copyUserList")
	@Action(description = "流程抄送人员设置列表")
	public ModelAndView copyUserList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		String parentActDefId = RequestUtil.getString(request,
				"parentActDefId", "");
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		String actDefId = bpmDefinition.getActDefId();
		List<BpmUserCondition> copyUserList = null;
		if (StringUtil.isEmpty(parentActDefId)) {
			copyUserList = bpmUserConditionService.getCcByActDefId(actDefId);
		} else {
			copyUserList = bpmUserConditionService.getCcByActDefId(actDefId,
					parentActDefId);
		}

		ModelAndView mv = getAutoView();
		mv.addObject("copyUserList", copyUserList).addObject("bpmDefinition",
				bpmDefinition).addObject("parentActDefId", parentActDefId);
		return mv;
	}

	/**
	 * 取得流程定义分页列表
	 * 
	 * @author tgl
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("defReferSelector")
	@Action(description = "选择流程定义分页列表,含按分类查询所有流程")
	public ModelAndView defReferSelector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		QueryFilter filter = new QueryFilter(request, "bpmDefinitionItem");
		filter.addFilter("validStatus", "1");

		Long typeId = RequestUtil.getLong(request, "typeId", 0);
		if (typeId != 0 && (typeId + "").length() > 1) {
			GlobalType globalType = globalTypeService.getById(typeId);
			if (globalType != null) {
				filter.addFilter("typeId", typeId);
				filter.addFilter("nodePath", globalType.getNodePath() + "%");
			}
		}

		List<BpmDefinition> list = bpmDefinitionService.getAll(filter);

		Long defId = RequestUtil.getLong(request, "defId");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("defId", defId);
		modelMap.put("bpmDefinitionList", list);

		ModelAndView mv = getAutoView().addAllObjects(modelMap).addObject(
				"defId", defId);
		return mv;
	}

	/**
	 * @author tgl 保存流程定义引用
	 */
	@RequestMapping("saveReferDef")
	@ResponseBody
	@Action(description = "保存流程定义引用", detail = "流程定义【${SysAuditLinkService.getBpmDefinitionLink(Long.valueOf(defId))}】添加流程引用"
			+ "<#list StringUtils.split(refers,\",\") as item>"
			+ "【${SysAuditLinkService.getBpmDefinitionLink(Long.valueOf(item))}】 "
			+ "</#list>")
	public Map<Long, String> saveReferDef(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long defId = RequestUtil.getLong(request, "defId");
		String[] refers = RequestUtil.getStringAryByStr(request, "refers");
		List<BpmReferDefinition> list = bpmReferDefinitionService
				.getByDefId(defId);
		List<String> referList = new ArrayList<String>();
		int i = 0;
		for (i = 0; i < refers.length; i++) {
			referList.add(refers[i]);
		}
		for (BpmReferDefinition refer : list) {
			for (i = 0; i < referList.size(); i++) {
				if (refer.getReferDefKey().equals(referList.get(i))) {
					referList.remove(i);
				}
			}
		}
		for (String r : referList) {
			if (StringUtils.isNotBlank(r)) {
				BpmReferDefinition ref = new BpmReferDefinition();
				ref.setId(UniqueIdUtil.genId());
				ref.setDefId(defId);
				ref.setReferDefKey(r);
				ref.setState("1");
				ref.setRemark("流程定义引用");
				bpmReferDefinitionService.saveReferDef(ref);
			}
		}

		Map<Long, String> map = new HashMap<Long, String>();
		List<BpmReferDefinition> bpmReferList = bpmReferDefinitionService
				.getByDefId(defId);
		for (BpmReferDefinition bpmReferDefinition : bpmReferList) {
			map
					.put(bpmReferDefinition.getId(), bpmReferDefinition
							.getSubject());
		}
		return map;
	}

	@RequestMapping("setCategory")
	@Action(description = "设置分类", detail = "设置表单"
			+ "<#list StringUtils.split(defKeys,\",\") as item>"
			+ " 【${SysAuditLinkService.getBpmDefinitionLinkByKey(item)}】 "
			+ "</#list>" + "的分类为"
			+ "${SysAuditLinkService.getGlobalTypeLink(Long.valueOf(typeId))}")
	public void setCategory(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		Long typeId = RequestUtil.getLong(request, "typeId", 0);
		String defKeys = RequestUtil.getString(request, "defKeys");
		String[] aryDefKey = defKeys.split(",");

		if (typeId == 0L) {
			// writer
			writeResultMessage(
					writer,
					new ResultMessage(
							ResultMessage.Fail,
							ContextUtil
									.getMessages("controller.bpmFormDef.setCategory.notCategoryId")));
			return;
		}

		if (StringUtil.isEmpty(defKeys)) {
			writeResultMessage(
					writer,
					new ResultMessage(
							ResultMessage.Fail,
							ContextUtil
									.getMessages("controller.bpmFormDef.setCategory.notSelectForm")));
			return;
		}

		List<String> list = new ArrayList<String>();

		for (String defKey : aryDefKey) {
			list.add(defKey);
		}
		try {
			bpmDefinitionService.updCategory(typeId, list);
			writeResultMessage(
					writer,
					new ResultMessage(
							ResultMessage.Success,
							ContextUtil
									.getMessages("controller.bpmDefinition.setCategory.success")));
		} catch (Exception ex) {
			String msg = ExceptionUtil.getExceptionMessage(ex);
			writeResultMessage(writer, new ResultMessage(ResultMessage.Fail,
					msg));
		}

	}

	/**
	 * @author 王百合 设置子系统分类
	 */
	@RequestMapping("setCategory1")
	@Action(description = "设置子系统", detail = "设置表单"
			+ "<#list StringUtils.split(defKeys,\",\") as item>"
			+ " 【${SysAuditLinkService.getBpmDefinitionLinkByKey(item)}】 "
			+ "</#list>" + "的分类为"
			+ "${SysAuditLinkService.getGlobalTypeLink(Long.valueOf(typeId))}")
	public void setCategory1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		Long typeId = RequestUtil.getLong(request, "typeId", 0);
		String defKeys = RequestUtil.getString(request, "defKeys");
		String[] aryDefKey = defKeys.split(",");

		Subsystemdef subsystemdef = new Subsystemdef();
		subsystemdef = null;

		for (int i = 0; i < aryDefKey.length; i++) {
			List<Subsystemdef> subsystemdeflist = subsystemdefService
					.getByDefKey1(aryDefKey[i]);

			if (subsystemdeflist.size() == 0) {
				subsystemdef = new Subsystemdef();
				subsystemdef.setId(UniqueIdUtil.genId());
				subsystemdef.setSys_id(typeId);
				subsystemdef.setSys_defkey(aryDefKey[i]);
				subsystemdefService.add(subsystemdef);
			} else {
				subsystemdeflist.get(0).setSys_id(typeId);
				subsystemdefService.update(subsystemdeflist.get(0));
			}

		}

	}

	/**
	 * @author tgl 删除流程定义引用
	 */
	@RequestMapping("delReferDef")
	@Action(description = "删除流程定义引用", execOrder = ActionExecOrder.BEFORE, detail = "<#assign entity=bpmReferDefinitionService.getById(Long.valueOf(refId))/>"
			+ "流程定义【${SysAuditLinkService.getBpmDefinitionLink(entity.defId)}】"
			+ "<#assign entity1=bpmDefinitionService.getById(entity.referDefId)/>"
			+ "删除流程引用【${entity1.subject}】")
	public void delReferDef(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "refId");
		bpmReferDefinitionService.getById(id);
		try {
			bpmReferDefinitionService.delById(id);
			writeResultMessage(response.getWriter(), "删除成功",
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), "删除失败",
					ResultMessage.Success);
		}
	}

	/**
	 * @author tgl 根据表单key查找绑定的流程
	 */
	@RequestMapping("getBpmDefinitionByFormKey")
	@Action(description = "根据表单key查找绑定的流程", detail = "根据表单key查找绑定的流程")
	@ResponseBody
	public List<BpmDefinition> getBpmDefinitionByFormKey(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String formKey = RequestUtil.getString(request, "formKey");
		List<BpmDefinition> list = bpmDefinitionService
				.getBpmDefinitionByFormKey(formKey);
		// List<BpmDefinition> list0 = bpmDefinitionService.
		// Long formKey = RequestUtil.getLong(request, "formKey");
		// List<BpmDefinition> list =
		// bpmDefinitionService.getBpmDefinitionByFormKey(formKey);
		return list;
	}

	/**
	 * flex 跳转节点
	 * 
	 * @author lyc
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexRedirect")
	@Action(description = "对跳转原子操作设置", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexRedirect(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("跳转");
		// 获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		String nodeId = RequestUtil.getString(request, "bpmDefinition.defId2");
		Long defId = Long.valueOf(RequestUtil.getString(request,
				"bpmDefinition.defId"));

		System.out.println("ip=" + ip);
		// 跳转地址
		// String newurl="http://" + ip +
		// ":8080/mas/platform/form/bpmFormDef/dialog.ht";
		String newurl = "http://"
				+ ip
				+ ":8080/mas/redirection/redirection/redirection/edit.ht?defId="
				+ defId + "&nodeId=" + nodeId;

		PrintWriter out = response.getWriter();
		out.print(newurl);
		System.out.println(newurl);
		return null;
	}

	/**
	 * 业务部署
	 * 
	 * @author 宋霄宇
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flexbs")
	@Action(description = "业务部署", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "<#if Boolean.parseBoolean(deploy)>"
			+ "<#if 0==bpmDefinition.getStatus()>"
			+ "版本号为【${bpmDefinition.versionNo}】"
			+ "<#else> 版本号为【${bpmDefinition.versionNo+1}】"
			+ "</#if>"
			+ "</#if>")
	public ModelAndView flexbs(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("flexbs将结果返回给flex");
		// 获得本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		System.out.println("ip=" + ip);
		// 跳转地址
		// String newurl="http://" + ip +
		// ":8080/mas/devicein/deviceinpack/deviceInfomation/smailedit.ht?actDefId="+actDeId+"&nodeId="+nodeId;

		PrintWriter out = response.getWriter();

		// 获取节点id
		String nodeId = RequestUtil.getString(request, "bpmDefinition.defId2");
		System.out.println("@@@nodeId=" + nodeId);

		// 获取defId
		Long defId = Long.valueOf(RequestUtil.getString(request,
				"bpmDefinition.defId"));
		System.out.println("@@@defId=" + defId);

		String selectStr = "server";
		if (nodeId.indexOf(selectStr) != -1) {
			String newurl = "http://"
					+ ip
					+ ":8080/mas/Ywbsbd/Ywbsbd/ywbsbd/list.ht?defId="
					+ defId +"&nodeId="+nodeId+"&operate=" + "select";
			out.print(newurl);
		}

		return null;
	}

	private static final String BASE_PATH = FileUtil.getRootPath()
			+ File.separator + "codegen"; // 生成代码临时存放位置

	/*
	 * private String filePath; // 文件路径 private String fileName; // 文件名称 private
	 * String fileOnlyName; // 文件唯一名
	 * 
	 * @RequestMapping("exportchart")
	 * 
	 * @Action(description = "导出一图四表") public void
	 * exportchart(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception { String[] bpmDefIds = RequestUtil.getString(request,
	 * "bpmDefIds").split(",");
	 * 
	 * try { for(int i=0;i<bpmDefIds.length;i++){ cword( bpmDefIds[i]); } String
	 * fileName = "bpmDefinition"; String realFilePath ="c:\\"; String
	 * zipFileName = fileName + ".zip"; String zipFilePath = realFilePath +
	 * File.separator + zipFileName; String filePath = realFilePath + fileName;
	 * //打包 ZipUtil.zip(filePath, true); System.out.println("打包路径"+filePath); //
	 * 导出 FileUtil.downLoadFile(request, response, zipFilePath, zipFileName); //
	 * logger.info("导出：" + zipFileName); // 删除导出的文件
	 * FileUtil.deleteFile(zipFilePath);
	 * 
	 * } catch (Exception e) { }
	 * 
	 * 
	 * } public void cword(String DefId) throws IOException, TemplateException{
	 * 
	 * // List<Map<String,Object>> listKey = new
	 * ArrayList<Map<String,Object>>(); // for(int i = 0;i<10;i++){ //
	 * Map<String,Object> map = new HashMap<String, Object>(); //
	 * map.put("name",bpmdefinition.getDefId()); // map.put("age", "sss"); //
	 * //map.put("fss", "ssssss"); // listKey.add(map); // } //
	 * dataMap.put("listKey", listKey); // List<BpmDefinition> blist = new
	 * ArrayList<BpmDefinition>(); BpmDefinition bpmdefinition =
	 * bpmDefinitionService.getById(Long.parseLong(DefId));
	 * bpmdefinition.getSubject(); Map<String, Object> model = new
	 * HashMap<String, Object>(); model.put("bpmdefinition", bpmdefinition);
	 * String templatePath =
	 * "/bpmx3_dev/src/com/hotent/platform/controller/document/test4.ftl";
	 * String html = freemarkEngine.mergeTemplateIntoString(templatePath ,
	 * model); bpmdefinition.getDefId(); Map<String, Object> dataMap = new
	 * HashMap<String, Object>(); dataMap.put("age", "asd");
	 * dataMap.put("bpmdefinition.DefId", bpmdefinition.getDefId());
	 * dataMap.put("gender", "asd"); dataMap.put("phone", "asd");
	 * 
	 * 
	 * / 文件名称，唯一字符串 Random r = new Random(); StringBuffer sb = new
	 * StringBuffer(); sb.append("_"); sb.append(r.nextInt(100)); fileOnlyName =
	 * sb + "dddd.doc";
	 * 
	 * // 文件名称 String fileName = "bpmDefinition"; String realFilePath = "c:\\";
	 * String filePath = realFilePath + fileName; System.out.println("路径：" +
	 * filePath); // 生成word //String html =
	 * freemarkEngine.mergeTemplateIntoString(templatePath , model);
	 * WordUtil.createWord(dataMap, "test4.ftl", filePath, fileOnlyName);
	 * 
	 * }
	 */

	/**
	 * 导出一图四表
	 * 
	 * @author an
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportchart")
	@Action(description = "导出一图四表")
	public void codegenZip(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 文件相关参数
		String templateIds = "10000042660000";
		// 相关的路径
		String basePath = BASE_PATH + File.separator + "codegen";
		String basePathZip = BASE_PATH + File.separator + "codegen.zip";

		try {

			String[] bpmDefIds = RequestUtil.getString(request, "bpmDefIds")
					.split(",");
			for (String bpmDefId : bpmDefIds) {
				// 得到bpmdefinition对象
				BpmDefinition bpmdefinition = bpmDefinitionService.getById(Long
						.parseLong(bpmDefId));

				// 找到寻找图片的id
				String processInstanceId = bpmdefinition.getActDefId();
				ProcessDefinitionEntity entity = bpmService
						.getProcessDefinitionByDefId(processInstanceId);
				bpmdefinition.setImage(cimage(entity.getDeploymentId()));
				//模板固定的id
				String h = "10000042690006";
				bpmdefinition.setImagea(cimage(h));

				// 生成word方法
				genCode1(basePath, templateIds, 1, bpmdefinition);
			}

			FileUtil.deleteFile(basePathZip);
			ZipUtil.zip(basePath, true);
			String zipFileName = "codegen.zip";
			FileUtil.downLoadFile(request, response, basePathZip, zipFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 生成Word文档方法
	private List<String> genCode1(String basePath, String templateIds,
			int override, BpmDefinition bpmdefinition) throws Exception {
		List<String> fileList = new ArrayList<String>();
		Long templateId = Long.parseLong(templateIds);
		SysCodeTemplate template = sysCodeTemplateService.getById(templateId);
		// 文档命名
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		sb.append(dateFormat.format(new Date()));
		sb.append("_");
		sb.append(r.nextInt(100));

		String fileName = sb + ".doc";
		String fileDir = "src";
		String path = basePath + File.separator + fileDir;
		//定义集合
		Map<String, String> variables = new HashMap<String, String>();
		Map<String, Object> model = new HashMap<String, Object>();
		// 三层循环找到表的字段

		List<BpmNodeSet> nodesets = bpmNodeSetService.getByDefId(bpmdefinition
				.getDefId());
		 
	
		//节点集合
		List<BpmNodeSet> nodeList = new ArrayList<BpmNodeSet>();
		List<BpmFormDef> tableList = new ArrayList<BpmFormDef>();
		for (BpmNodeSet node : nodesets) {
			String actDefId = node.getActDefId();
			String nodeId = node.getNodeId();
			List<BpmFormDef> bpmformdefs = bpmFormDefService.getByFormKey(node
					.getFormKey());
			//表单集合 
			for (BpmFormDef bpmformdef : bpmformdefs) {

				List<BpmFormField> bpmformfields = bpmFormFieldService
						.getByTableId(bpmformdef.getTableId());
				
				List<BpmFormField> fieldList = new ArrayList<BpmFormField>();
				tableList.add(bpmformdef);
				//表集合
				for (BpmFormField bpmformfield : bpmformfields) {
					fieldList.add(bpmformfield);

				}
				bpmdefinition.setFieldList(fieldList);
			}
			bpmdefinition.setTableList(tableList);
			nodeList.add(node);
			//获得数据包部分结尾所需要的属性
			//数据库数据不全所以暂时写死
			//ActivityDetail activitydetail = activityDetailService.getByactDefId(actDefId, nodeId);
			ActivityDetail activitydetail = activityDetailService.getByactDefId("bxftj:1:10000049930057", "UserTask1");
		    activitydetail.setOp_comp1(String.valueOf(activitydetail.getOp_comp()));			
			model.put("a", activitydetail);
		}
		bpmdefinition.setNodeList(nodeList);
		// List<String> list = bpmdefinition.getMaplist();

		// 得到subsystemdef对象
		List<Subsystemdef> subsystemdeflist = subsystemdefService
				.getByDefKey1(bpmdefinition.getDefKey());
		Subsystemdef subsystemdef = subsystemdeflist.get(0);
		// 得到subsystem对象
		Long ids = subsystemdef.getSys_id();
		//进行判断如果没找到对应的对象,就指定唯一一个对象
		if (ids == null) {
			String x = "1";
			SubSystem subsystem = subSystemService.getById(Long.parseLong(x));
			model.put("s", subsystem);
		} else {
			SubSystem subsystem = subSystemService.getById(ids);
			model.put("s", subsystem);
		}

		// 像model中加入对象,在模板中就可以调用对象的字段
		model.put("b", bpmdefinition);
		//模板路径,写死的代码
		String templatePath = SysCodeTemplateService
				.getRelateTemplatePath(template.getTemplateAlias());
		String html = freemarkEngine.mergeTemplateIntoString(templatePath,
				model);
		String fileStr = path + File.separator + fileName;
		String filePath = StringUtil.replaceVariable(fileStr, variables);
		addFile(filePath, html, override);
		String relativePath = StringUtil.replaceVariable(fileDir
				+ File.separator + fileName, variables);
		fileList.add(relativePath);
		return fileList;
	}
	/**
	 * @param deployId
	 * @return
	 * @throws IOException
	 */
	private String cimage(String deployId) throws IOException {

		// 生成图片
		InputStream is = null;
		// 根据流程deployId产生图片
		String bpmnXml = bpmService.getDefXmlByDeployId(deployId);
		is = ProcessDiagramGenerator.generatePngDiagram(bpmnXml);
		//将获得的图片流转换成对应的Word文档是别的64位base码
		byte[] data = null;
		try {
			data = new byte[is.available()];
			is.read(data);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	private void addFile(String filePath, String html, int override) {
		File newFile = new File(filePath);
		if (newFile.exists()) {
			if (override == 1) {
				FileUtil.deleteFile(filePath);
				FileUtil.writeFile(filePath, html);
			}
		} else {
			FileUtil.writeFile(filePath, html);
		}
	}

	@RequestMapping("tongji")
	@Action(description = "查看流程定义明细")
	public ModelAndView Tongji(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//System.out.println("进入def目的");	
	
			Long[] defId =RequestUtil.getLongAryByStr(request, "id") ;//获得jsp中打对钩的子系统id		
			System.out.println("defId="+defId[0]);
			// 增加流程分管授权查询判断
				Long userId = ContextUtil.getCurrentUserId();
				//String isNeedRight = "";
				Map<String, AuthorizeRight> authorizeRightMap = null;
				if (!SysUser.isSuperAdmin()) {
					// if(!checkSuperAdmin()){
					//isNeedRight = "yes";
					// 获得流程分管授权与用户相关的信息
					Map<String, Object> actRightMap = bpmDefAuthorizeService
							.getActRightByUserMap(userId,
									BPMDEFAUTHORIZE_RIGHT_TYPE.MANAGEMENT, true, true);
					// 获得流程分管授权与用户相关的信息集合的流程KEY
					String actRights = (String) actRightMap.get("authorizeIds");
					//filter.addFilter("actRights", actRights);
					// 获得流程分管授权与用户相关的信息集合的流程权限内容
					authorizeRightMap = (Map<String, AuthorizeRight>) actRightMap
							.get("authorizeRightMap");
				}
				//filter.addFilter("isNeedRight", isNeedRight);
			if(defId[0].toString().equals("10001"))
			{System.out.println("進入if語句");
				JdbcTemplate jdbcTemplate = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
				List<Map<String, Object>> f_List=jdbcTemplate.queryForList
				(
						"SELECT defid "+
				"FROM bpm_definition "+
				"where defid in "+
				"( "+
						"select max(DEFID) "+
						"from "+
						"( "+
									"select DEFID,DEFKEY "+
									"from bpm_definition "+
									"where defkey not in "+
									"("+
												"select F_sys_defkey "+
												"from w_subsystemdef " +									
									")"+
						")as a GROUP BY a.DEFKEY"+
				")and typeId=\""+2+"\""
				);	
				Long[] defids=new Long[f_List.size()];
				for(int i=0;i<f_List.size();i++)
				{
					defids[i]=Long.valueOf(f_List.get(i).get("defid").toString());
					System.out.println(i+"流程id為："+defids[i]);
				}
				

				// 查询流程列表
				List<BpmDefinition> list=new ArrayList<BpmDefinition>();
				for(int i=0;i<defids.length;i++)
					if(defids[i].SIZE==0)continue;
					else
					{	List<BpmDefinition> a1=bpmDefinitionService.getByDefId(defids[i]);
						if(a1.size()==0)continue;
						BpmDefinition a2=a1.get(a1.size()-1);
						GlobalType a3=globalTypeService.getById(a2.getTypeId());
						if(a3==null);
						else
						{
						String typeName=a3.getTypeName();
						a2.setTypeName(typeName);
						}
						list.add(a2);
					}

				// 把前面获得的流程分管授权的权限内容设置到流程管理列表
				if (authorizeRightMap != null) {
					for (BpmDefinition bpmDefinition : list) {
						bpmDefinition.setAuthorizeRight(authorizeRightMap
								.get(bpmDefinition.getDefKey()));
					}
				} else {
					// 如果需要所有权限的就直接虚拟一个有处理权限的对象
					AuthorizeRight authorizeRight = new AuthorizeRight();
					authorizeRight.setRightByAuthorizeType("Y",
							BPMDEFAUTHORIZE_RIGHT_TYPE.MANAGEMENT);
					for (BpmDefinition bpmDefinition : list) {
						bpmDefinition.setAuthorizeRight(authorizeRight);
					}
				}
				ModelAndView mv = this.getAutoView().addObject("bpmDefinitionList", list);//.//addObject("typeName", "flowChart");
				return mv;	
			}			
			else{
				System.out.println("進入else語句");
	
				List<BpmDefinition> list=new ArrayList<BpmDefinition>();
				for(int i=0;i<defId.length;i++)
					if(defId[i].SIZE==0)continue;
					else
					{	List<BpmDefinition> a1=bpmDefinitionService.getByDefId(defId[i]);
						if(a1.size()==0)continue;
						BpmDefinition a2=a1.get(a1.size()-1);
						GlobalType a3=globalTypeService.getById(a2.getTypeId());
						if(a3==null);
						else
						{
						String typeName=a3.getTypeName();
						a2.setTypeName(typeName);
						}
						list.add(a2);
					}
	
				// 把前面获得的流程分管授权的权限内容设置到流程管理列表
				if (authorizeRightMap != null) {
					for (BpmDefinition bpmDefinition : list) {
						bpmDefinition.setAuthorizeRight(authorizeRightMap
								.get(bpmDefinition.getDefKey()));
					}
				} else {
					// 如果需要所有权限的就直接虚拟一个有处理权限的对象
					AuthorizeRight authorizeRight = new AuthorizeRight();
					authorizeRight.setRightByAuthorizeType("Y",
							BPMDEFAUTHORIZE_RIGHT_TYPE.MANAGEMENT);
					for (BpmDefinition bpmDefinition : list) {
						bpmDefinition.setAuthorizeRight(authorizeRight);
					}
				}
				ModelAndView mv = this.getAutoView().addObject("bpmDefinitionList", list);//.//addObject("typeName", "flowChart");
				return mv;		
				//sys_information=subSystemService.get_jiben_tongji(sys_information);	//基本信息统计
				//sys_information=subSystemService.get_jisuan_tongji(sys_information);	//运算基本信息统计
			}
	}

			@RequestMapping("flexOperateDefSave")
			@Action(description = "流程在线设计，保存，发布操作",
					detail="设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】," +
			               "<#if Boolean.parseBoolean(deploy)>" +
							  "<#if 0==bpmDefinition.getStatus()>"+
						          "版本号为【${bpmDefinition.versionNo}】" +
							  "<#else> 版本号为【${bpmDefinition.versionNo+1}】" +
						      "</#if>"+
			               "</#if>"
			)
			public void flexOperateDefSave(HttpServletRequest request,HttpServletResponse response) throws Exception {
				// 新建流程定义时，若仅是保存流程定义时，则不发布流程。但下次更新时，若点发布新版时，则需要发布流程定义。
				// 新建流程定义时，若点了发布新版，则发布流程定义,下次再更新定义时，也是直接修改流程的定义。也可以重新发布新版本的流程
				// 是否发布新流程定义
				System.out.println("开始生成操作图");
				String defId =request.getParameter("defId");
				String flag =request.getParameter("flag");
				String templateName ="cz_model";
				Boolean isDeploy = Boolean.parseBoolean(request.getParameter("deploy"));
				System.out.println("--isDeploy--:"+isDeploy);
				String actFlowDefXml = null;
				ResultMessage resultMessage = null;
				String preUrl = RequestUtil.getPrePage(request);
				int f=0;
				int flags=1;
				System.out.println("defId++:"+defId);
			    BpmDefinition bpmDefinition =bpmDefinitionService.getDefinitionById(defId);//通过流程Id得到bpmDefinition对象
				String defXml=bpmDefinition.getDefXml();
				System.out.println("defXml:++"+defXml);
				f=bpmDefinitionService.bpmDefinitionCreater(bpmDefinition,templateName,flags,isDeploy);
			    if(f==1)
		    			resultMessage = new ResultMessage(ResultMessage.Success,"生成操作图成功");
		    	else
		    		    resultMessage = new ResultMessage(ResultMessage.Fail,"节点已经生成过操作图不能再次生成!" );
		    	addMessage(resultMessage, request);
		    	response.sendRedirect(preUrl);
			    }
			@RequestMapping("transactionDefSave")
			@Action(description = "流程在线设计，保存，发布操作",
					detail="设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】," +
			               "<#if Boolean.parseBoolean(deploy)>" +
							  "<#if 0==bpmDefinition.getStatus()>"+
						          "版本号为【${bpmDefinition.versionNo}】" +
							  "<#else> 版本号为【${bpmDefinition.versionNo+1}】" +
						      "</#if>"+
			               "</#if>"
			)
			public void transactionDefSave(HttpServletRequest request,HttpServletResponse response) throws Exception {
				// 新建流程定义时，若仅是保存流程定义时，则不发布流程。但下次更新时，若点发布新版时，则需要发布流程定义。
				// 新建流程定义时，若点了发布新版，则发布流程定义,下次再更新定义时，也是直接修改流程的定义。也可以重新发布新版本的流程
				// 是否发布新流程定义
				System.out.println("开始生成事务图");
				String defId =request.getParameter("defId");
				String flag =request.getParameter("flag");
				String templateName ="sw_model";
				Boolean isDeploy = Boolean.parseBoolean(request.getParameter("deploy"));
				System.out.println("--isDeploy--:"+isDeploy);
				String actFlowDefXml = null;
				ResultMessage resultMessage = null;     
				String preUrl = RequestUtil.getPrePage(request);
				int f=0;
				int flags=0;
			    BpmDefinition bpmDefinition =bpmDefinitionService.getDefinitionById(defId);//通过流程Id得到bpmDefinition对象
				String defXml=bpmDefinition.getDefXml();
				f=bpmDefinitionService.bpmDefinitionCreater(bpmDefinition,templateName,flags,isDeploy);
			    if(f==1)
		    			resultMessage = new ResultMessage(ResultMessage.Success,"生成事务图成功");
		    	else
		    		    resultMessage = new ResultMessage(ResultMessage.Fail,"节点已经生成过事务图不能再次生成!" );
		    	addMessage(resultMessage, request);
		    	response.sendRedirect(preUrl);
		    }
	
	//naxiaoxu  马尔科夫链预览
	@RequestMapping("flex110")
	@Action(description = "马尔科夫链预览",detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
		+ "</#if>")
	public ModelAndView flex110(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/xml");
		//获取markid
		Long markid=RequestUtil.getLong(request, "markid");
		System.out.println("新markid"+markid);
		PrintWriter out = response.getWriter();
		try{
			Markovchain markvochain= markovchainService.getById(markid);
			String sd=markvochain.getMarkovchainXML();
			sd=sd.replaceAll("\"", "\'");
			out.print(sd);
		}
		catch(Exception e){
			out.print(e);
		}
        return null;        
        
	}
	/**
	 * 设置马尔科夫链xml路径
	 * 
	 * @author 那晓旭
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("flex112")
	@Action(description = "流程在线设计，保存，发布操作", detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
			+ "</#if>")
	public void  flex112(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Markovchain=RequestUtil.getString(request, "bpmDefinition.Markovchain");
		String defId=RequestUtil.getString(request, "defId");
		Long markid=RequestUtil.getLong(request, "markid");
		if(markid!=0)
		{
			Markovchain markovchain=markovchainService.getById(markid);
			markovchain.setMarkovchainXML(Markovchain);
			markovchainService.update(markovchain);
		}
		
	}
	//ajax局部刷新xml文本输入框
	@RequestMapping("updateMessageStateUnread")
    public void updateMessageState(HttpServletResponse response) throws Exception{
       
	   PrintWriter out = response.getWriter();
       out.print(Markovchain);
       out.flush();
       out.close();
    }
	
	
			/**mz
			流程图：flowChart
			操作图：operationChart
			事务图：transactionChart
			*/
			@RequestMapping("flex102")
			@Action(description = "用户任务和外部子图获取想要的分类的流程")
			public ModelAndView flex102(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
				String nodelabel=RequestUtil.getString(request, "bpmDefinition.NodeLabel");
				String dataFlagFlex=RequestUtil.getString(request, "bpmDefinition.dataFlagFlex");
				String Label=RequestUtil.getString(request, "bpmDefinition.Label");
				String typeNameresult="";	
				System.out.println("jjj"+nodelabel);
				System.out.println("jjjghhhh"+dataFlagFlex);
				if (dataFlagFlex.equals("operationChart")){
					if(nodelabel.equals("用户任务")){
						typeNameresult="";
					}
					else if(nodelabel.equals("外部子图")){
						typeNameresult="transactionChart";
					}
					else if(nodelabel.equals("事务图")){
						typeNameresult="transactionChart";	
					}
				}else if(dataFlagFlex.equals("transactionChart")){
					if(nodelabel.equals("用户任务")){
						typeNameresult="";
					}
					else if(nodelabel.equals("外部子图")){
						typeNameresult="transactionChart";
					}
					else if(nodelabel.equals("子事务图")){
						typeNameresult="transactionChart";
					}
				}else if(dataFlagFlex.equals("flowChart")){
					if(nodelabel.equals("用户任务")){
						typeNameresult="operationChart";
					}
					else if(nodelabel.equals("外部子图")){
						typeNameresult="flowChart";
					}
					
				}
				String sb=globalTypeService.getXmlByCatkey_f(GlobalType.CAT_FLOW,typeNameresult);
				PrintWriter out = response.getWriter();
				out.println(sb);
				return null;
				}
			//zdn 查看操作图
				@RequestMapping("flex103")
				@Action(description = "流程在线设计，保存，发布操作",
						detail="设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】," +
				               "<#if Boolean.parseBoolean(deploy)>" +
								  "<#if 0==bpmDefinition.getStatus()>"+
							          "版本号为【${bpmDefinition.versionNo}】" +
								  "<#else> 版本号为【${bpmDefinition.versionNo+1}】" +
							      "</#if>"+
				               "</#if>"
				)
				public ModelAndView flex103(HttpServletRequest request,
						HttpServletResponse response) throws Exception {
					System.out.println("操作图将结果返回给flex");
					//获得本机IP
					InetAddress addr = InetAddress.getLocalHost();
					String ip=addr.getHostAddress().toString();
					System.out.println("ip="+ip);
					//跳转地址
					//String newurl="http://" + ip + ":8080/mas/devicein/deviceinpack/deviceInfomation/smailedit.ht?actDefId="+actDeId+"&nodeId="+nodeId;
					PrintWriter out = response.getWriter();
					//获取defkey
					String defKey=RequestUtil.getString(request, "bpmDefinition.defKey");
					String typeName=RequestUtil.getString(request, "bpmDefinition.proTypeName");
					System.out.println("@@@defKey="+defKey);
					System.out.println("@@@typeName="+typeName);
					BpmDefinition pos1 = bpmDefinitionService.getMainByDefKey(defKey);
					//System.out.println("defKey22222222="+pos1);
					//System.out.println("defKey="+defKey);
					Long defId=pos1.getDefId();	
			        System.out.println("actDeId="+defId);
					String newurl103="http://" + ip + ":8080/mas/platform/bpm/bpmDefinition/design.ht?defId="+defId+"&typeName=operationChart&status=4";
					out.print(newurl103);
			        return null;   
				}
		//查看页面
		@RequestMapping("flex104")
		@Action(description = "流程在线设计，保存，发布操作",
				detail="设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】," +
		               "<#if Boolean.parseBoolean(deploy)>" +
						  "<#if 0==bpmDefinition.getStatus()>"+
					          "版本号为【${bpmDefinition.versionNo}】" +
						  "<#else> 版本号为【${bpmDefinition.versionNo+1}】" +
					      "</#if>"+
		               "</#if>"
		)
		public ModelAndView flex104(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			System.out.println("页面将结果返回给flex");
			//获得本机IP
			InetAddress addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString();
			System.out.println("ip="+ip);
			//跳转地址
			//String newurl="http://" + ip + ":8080/mas/devicein/deviceinpack/deviceInfomation/smailedit.ht?actDefId="+actDeId+"&nodeId="+nodeId;

			PrintWriter out = response.getWriter();
			//获取节点id
	        String nodeId=RequestUtil.getString(request, "bpmDefinition.defId2");
			
			//String nodeLabel = RequestUtil.getSecureString(request, "bpmDefinition.nodeLabel");
			System.out.println("@@@nodeId="+nodeId);
			//System.out.println("@@@nodeLabel="+nodeLabel);
			//获取defId
			Long defId=Long.valueOf(RequestUtil.getString(request, "bpmDefinition.defId"));
			System.out.println("@@@defId="+defId);

			//获取actdefId
			BpmDefinition pos = bpmDefinitionService.getById(defId);
			String actDefId=pos.getActDefId();
	        System.out.println("actDeId="+actDefId);
			String newurl104="http://" + ip + ":8080/mas/platform/bpm/bpmNodeSet/listform.ht?defId="+defId+"&actDefId="+actDefId+"&nodeId="+nodeId+"&type=output";
			out.print(newurl104);  
	        return null;      
		}
		//查看事务图
		@RequestMapping("flex105")
		@Action(description = "流程在线设计，保存，发布操作",
				detail="设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】," +
		               "<#if Boolean.parseBoolean(deploy)>" +
						  "<#if 0==bpmDefinition.getStatus()>"+
					          "版本号为【${bpmDefinition.versionNo}】" +
						  "<#else> 版本号为【${bpmDefinition.versionNo+1}】" +
					      "</#if>"+
		               "</#if>"
		)
		public ModelAndView flex105(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			System.out.println("事务图将结果返回给flex");
			//获得本机IP
			InetAddress addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString();
			System.out.println("ip="+ip);
			PrintWriter out = response.getWriter();
			//获取defkey
			String defKey=RequestUtil.getString(request, "bpmDefinition.defKey");
			String typeName=RequestUtil.getString(request, "bpmDefinition.proTypeName");
			System.out.println("@@@defKey!!!!!!!!!!!!!!="+typeName);
			System.out.println("@@@defKey="+defKey);
			BpmDefinition pos1 = bpmDefinitionService.getMainByDefKey(defKey);
			System.out.println("defKey="+defKey);
			Long defId=pos1.getDefId();	
	        System.out.println("actDeId="+defId);
			String newurl105="http://" + ip + ":8080/mas/platform/bpm/bpmDefinition/design.ht?defId="+defId + "&typeName=transactionChart&status=4";
			out.print(newurl105); 
	        return null;   
		}
		//查看子图
		@RequestMapping("flex106")
		@Action(description = "流程在线设计，保存，发布操作",
				detail="设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】," +
		               "<#if Boolean.parseBoolean(deploy)>" +
						  "<#if 0==bpmDefinition.getStatus()>"+
					          "版本号为【${bpmDefinition.versionNo}】" +
						  "<#else> 版本号为【${bpmDefinition.versionNo+1}】" +
					      "</#if>"+
		               "</#if>"
		)
		public ModelAndView flex106(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			System.out.println("子图将结果返回给flex");
			//获得本机IP
			InetAddress addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString();
			System.out.println("ip="+ip);
			PrintWriter out = response.getWriter();
			//获取defkey
			String defKey=RequestUtil.getString(request, "bpmDefinition.defKey");
			String typeName=RequestUtil.getString(request, "bpmDefinition.proTypeName");
			System.out.println("@@@defKey!!!!!!!!!!!!!!="+typeName);
			System.out.println("@@@defKey="+defKey);
			BpmDefinition pos1 = bpmDefinitionService.getMainByDefKey(defKey);
			System.out.println("defKey="+defKey);
			Long defId=pos1.getDefId();	
	        System.out.println("actDeId="+defId);
			String newurl106="http://" + ip + ":8080/mas/platform/bpm/bpmDefinition/design.ht?defId="+defId + "&typeName=flowChart&status=4";
			out.print(newurl106); 
	        return null;   
		}
		//设置脚本
		
		@RequestMapping("flex107")
		@Action(description = "流程在线设计，保存，发布操作",
				detail="设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】," +
		               "<#if Boolean.parseBoolean(deploy)>" +
						  "<#if 0==bpmDefinition.getStatus()>"+
					          "版本号为【${bpmDefinition.versionNo}】" +
						  "<#else> 版本号为【${bpmDefinition.versionNo+1}】" +
					      "</#if>"+
		               "</#if>"
		)
		public ModelAndView flex107(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			System.out.println("脚本将结果返回给flex");
			//获得本机IP
			InetAddress addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString();
			System.out.println("ip="+ip);
			PrintWriter out = response.getWriter();
			String nodeId=RequestUtil.getString(request, "bpmDefinition.defId2");
			//String nodeLabel = RequestUtil.getSecureString(request, "bpmDefinition.nodeLabel");
			System.out.println("@@@nodeId="+nodeId);
			//System.out.println("@@@nodeLabel="+nodeLabel);
			//获取defId
			Long defId=Long.valueOf(RequestUtil.getString(request, "bpmDefinition.defId"));
			System.out.println("defId:"+defId);
			
			BpmDefinition pos = bpmDefinitionService.getById(defId);
			String actDefId=pos.getActDefId();
			System.out.println("ActDEFID:"+actDefId);

			String newurl107="http://" + ip + ":8080/mas/platform/bpm/bpmNodeScript/edit1.ht?type=script&actDefId="+actDefId+"&nodeId="+nodeId+"&defId="+defId;
			out.print(newurl107); 
	        return null;   
		}
		//设置分支条件
		@RequestMapping("flex108")
		@Action(description = "流程在线设计，保存，发布操作",
				detail="设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】," +
		               "<#if Boolean.parseBoolean(deploy)>" +
						  "<#if 0==bpmDefinition.getStatus()>"+
					          "版本号为【${bpmDefinition.versionNo}】" +
						  "<#else> 版本号为【${bpmDefinition.versionNo+1}】" +
					      "</#if>"+
		               "</#if>"
		)
		public ModelAndView flex108(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			System.out.println("分支条件将结果返回给flex");
			//获得本机IP
			InetAddress addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString();
			System.out.println("ip="+ip);
			PrintWriter out = response.getWriter();
			String nodeId=RequestUtil.getString(request, "bpmDefinition.defId2");
			System.out.println("@@@nodeId="+nodeId);
			Long defId=Long.valueOf(RequestUtil.getString(request, "bpmDefinition.defId"));
			System.out.println("defId:"+defId);
			//Long actdeployId=Long.valueOf(RequestUtil.getLong(request, "bpmDefinition.deployId"));			
			//System.out.println("SSSSSSSSSSSS="+actdeployId);
			List<BpmDefinition> bpmList= bpmDefinitionService.getByDefId(defId);
	        Long actdeployId=bpmList.get(0).getActDeployId();
	        System.out.println("SSSSSSSSSSSS="+actdeployId);
			//System.out.println("ActDEFID:"+actDefId);
			String newurl108="http://" + ip + ":8080/mas/platform/bpm/bpmDefinition/setCondition1.ht?deployId="+actdeployId+"&defId="+defId+"&nodeId="+nodeId+"&parentActDefId=";
			out.print(newurl108); 
	        return null;   
		}
		//消息参数
		@RequestMapping("flex109")
		@Action(description = "流程在线设计，保存，发布操作",
				detail="设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】," +
		               "<#if Boolean.parseBoolean(deploy)>" +
						  "<#if 0==bpmDefinition.getStatus()>"+
					          "版本号为【${bpmDefinition.versionNo}】" +
						  "<#else> 版本号为【${bpmDefinition.versionNo+1}】" +
					      "</#if>"+
		               "</#if>"
		)
		public ModelAndView flex109(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			System.out.println("分支条件将结果返回给flex");
			//获得本机IP
			InetAddress addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString();
			System.out.println("ip="+ip);
			PrintWriter out = response.getWriter();
			String nodeId=RequestUtil.getString(request, "bpmDefinition.defId2");
			System.out.println("@@@nodeId="+nodeId);
			Long defId=Long.valueOf(RequestUtil.getString(request, "bpmDefinition.defId"));
			System.out.println("defId:"+defId);
			BpmDefinition pos = bpmDefinitionService.getById(defId);
			String actDefId=pos.getActDefId();
	        System.out.println("actDeId="+actDefId);
			String newurl109="http://" + ip + ":8080/mas/platform/bpm/bpmNodeMessage/edit1.ht?actDefId="+actDefId+"&nodeId="+nodeId;
			out.print(newurl109); 
	        return null;   
		}
			/**
			 *袁松
			 * 操作图建表单
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			@RequestMapping("createFormSave")
			@Action(description = "添加或更新自定义表单",execOrder=ActionExecOrder.AFTER,
					detail="<#if isSuccess>" +
							"<#if isAdd>添加" +
							"<#else>更新" +
							"</#if>自定义表单：" +
							"【${SysAuditLinkService.getBpmFormDefLink(defId)}】成功" +
							"<#else>" +
							"添加或更新自定义表单失败</#if>")
			public void createFormSave(HttpServletRequest request, HttpServletResponse response) throws Exception{
				// 表定义
				String defId=request.getParameter("defId");
				BpmDefinition templateBpmDefinition = bpmDefinitionService.getDefinitionById(defId);
				String subject=templateBpmDefinition.getSubject();//表单版本
			    String defXml = templateBpmDefinition.getDefXml();//得到defXml
				String nodename="Task";
				List<String> nodeList=bpmDefinitionService.findNodeName(defXml,nodename);
				String preUrl = RequestUtil.getPrePage(request);
				System.out.println("nodeList:"+nodeList.size());
				
				
				
				String templateNamebd ="bd_model";
				Long defIdbd = codeTemplateService.getByBm(templateNamebd);//通过（写死的）表单模板别名得到模板id
				System.out.println("defIdbd:"+defIdbd);
				BpmFormDef bpmFormDef=bpmFormDefService.getById(defIdbd);
			    
				if(nodeList.size()!=0){
					for(int i=0;i<nodeList.size();i++)
					{
						String subject1=subject+"_"+i;
						bpmFormDef.setSubject(subject1);
				//表单添加
				//boolean isAdd=bpmFormDef.getFormDefId() == 0;
			
						boolean isSuccess=true;
				//boolean isadd=false;
				//long defId=0;
						try{
							bpmFormDefService.addFormDirect(bpmFormDef);
						}
						catch (Exception e) {//异常
							e.printStackTrace();//输出跟踪信息
							String str = MessageUtil.getMessage();//添加消息、、返回流程信息
							if (StringUtil.isNotEmpty(str)) {//判断字符串空
								ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"保存自定义表单数据失败:" + str);
								response.getWriter().print(resultMessage);
							} else {
									String message = ExceptionUtil.getExceptionMessage(e);
									ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
									response.getWriter().print(resultMessage);//响应
							}
							isSuccess=false;
						}
			
			
					SysAuditThreadLocalHolder.putParamerter("isSuccess", isSuccess);
					SysAuditThreadLocalHolder.putParamerter("defId", String.valueOf(defId));
				
					
					}
					
					ResultMessage resultMessage =null;
					resultMessage = new ResultMessage(ResultMessage.Success,"生成表单成功");
					addMessage(resultMessage, request);
					response.sendRedirect(preUrl);
				}
				else 
				{
					ResultMessage resultMessage =null;
					resultMessage = new ResultMessage(ResultMessage.Fail,"生成表单失败");
					addMessage(resultMessage, request);
					response.sendRedirect(preUrl);
				}
				}


			/**
			 * @param defid
			 * @return
			 * @throws 流程图生成操作图或者操作图
			 *             生成事务图
			 */
			@RequestMapping("mass_produce")        //批量生成 开始 ****
			public void mass_produce(HttpServletRequest request,HttpServletResponse response) throws Exception
			{
				System.out.println("进入tingsubsys统计按钮");		
				
				try{
					Long[] ids =RequestUtil.getLongAryByStr(request, "defId") ;//获得jsp中打对钩的子系统id,并传给ids
					String typeName=RequestUtil.getString(request,"typeName");
					Long[] status=RequestUtil.getLongAryByStr(request,"status");
					System.out.println("subsystem获得id【】:");
					System.out.println("typeName="+typeName);
					System.out.println("ids="+ids);
					System.out.println("status="+status);
					Boolean isDeploy = Boolean.parseBoolean(request.getParameter("deploy"));		
					System.out.println("开始生成图");			
					System.out.println("--isDeploy--:"+isDeploy);
					String actFlowDefXml = null;
					ResultMessage resultMessage = null;
					String preUrl = RequestUtil.getPrePage(request);
					System.out.println("进入service函数");
					String a=bpmDefinitionService.xhmethod(isDeploy,ids,typeName,status);
					resultMessage = new ResultMessage(ResultMessage.Success,a);
					
					addMessage(resultMessage, request);
					response.sendRedirect(preUrl);
				}catch (Exception e) {
				System.out.println("统计按钮失败");		
				}	
				System.out.println("完成统计按钮");
				  System.out.println("jinru ");
			}// 批量生成mass_produce方法结束		
			/**
			 * @param defid
			 * @return
			 * @throws 返回设备情况
			 */
			@RequestMapping("equipmentCondition")        //返回设备情况
			public void equipmentCondition(HttpServletRequest request,HttpServletResponse response) throws Exception
			{
				String stepvalue = RequestUtil.getString(request, "bpmDefinition.stepValue");
				String  timenow = RequestUtil.getString(request, "bpmDefinition.timeNow");
				 PrintWriter out = response.getWriter();
				 String xml="<flowdata>" +
				 		"<node id='serverTask1' lable='填写任务书'  startTime='15:51:44' durTime='1分钟' status='闪烁' rate='60%'/>" +
				 		"</flowdata>";
//			       out.print(xml);
				 out.print("asdas");
				 System.out.println("当前时间：："+timenow);
				 System.out.println("步长值：："+stepvalue);
			       out.close();
			}
			@RequestMapping("flex113")//流程校验
			public ModelAndView flex113(HttpServletRequest request,HttpServletResponse response) throws Exception 
			{
				long defId = 0L;
				defId = RequestUtil.getLong(request, "defId");
				List<BpmDefinition> bpmDefinitionList=bpmDefinitionService.getByDefId(defId);
				String typename=RequestUtil.getString(request, "bpmDefinition.dataFlagFlex");
				System.out.println("@@@@@@@@@@@QWEQWEWQE"+typename+"+++++++++");
				String str="";
				if(typename.equals("flowChart")){  //流程图
					WDefInformation def_info=new WDefInformation(defId);
					def_info.bpmdef=bpmDefinitionList.get(0);
					def_info=sysdefnodeergodicService.defGetlianjie(def_info,"");
					str=sysdefnodeergodicService.getNotSetNodeXmlByDef(def_info);
						
				
				}else if(typename.equals("operationChart")){  //操作图
					WOperateInfo operInfo=new WOperateInfo(bpmDefinitionList.get(0));
					operInfo= sysdefnodeergodicService.setOpeMarkovchainId(operInfo);//给操作图对象下的马尔科夫链进行赋值				
					operInfo = sysdefnodeergodicService.statisticsOperate(operInfo);
					
					str=sysdefnodeergodicService.getNotSetNodeXmlByOper(operInfo);
					
				}else if(typename.equals("transactionChart")){  //事务图
					
					TransactionInfo transInfo=new TransactionInfo();
					BpmDefinition bpmTransactionInfo = bpmDefinitionList.get(0);//得到事物对象
					transInfo.bpmtransaction = bpmTransactionInfo;//赋值到操作图节点类下
					
					transInfo = sysdefnodeergodicService.statisticsTransaction(transInfo);
					
					str=sysdefnodeergodicService.getNotSetNodeXmlBytrans(transInfo);
					
				}
				System.out.println("str==============================================="+str);
				PrintWriter out = response.getWriter();
				out.println(str);
				return null;
			}
			//获取曲线图数据
			@RequestMapping("flex114") 
			@Action(description = "asdf")
			public ModelAndView flex114(HttpServletRequest request,HttpServletResponse response) throws Exception {
				//动态获取数据
				Long []defId=excelkService.sort();
				int n=2;
				Long processId=0l;
				    StringBuffer xml1 = new StringBuffer("<?xml version='1.0' encoding='UTF-8'?><data>" );					
				    	List<Excelk> pictureDataList = excelkService.getPictureData(defId[0]);
				    	for(Excelk a:pictureDataList)
				    	{
				    		xml1.append("<node date='");
				    		xml1.append(a.getTime());
				    		xml1.append("' ");
				    		List<BpmDefinition> bpm=bpmDefinitionService.getByDefId(defId[0]);
				    		xml1.append(bpm.get(0).getSubject());
					    	xml1.append("='");
					    	xml1.append(a.getCount());
					    	xml1.append("' ");
				    		for(int i=1;i<n;i++)
				    		{
				    			List<BpmDefinition> bpm1=bpmDefinitionService.getByDefId(defId[i]);
				    			xml1.append(bpm1.get(0).getSubject());
						    	xml1.append("='");
						    	List<Excelk> b=excelkService.getTimeByIdAndTime(a.getTime(),defId[i]);
						    	xml1.append(b.get(0).getCount());
						    	xml1.append("' ");
				    		}
				    		xml1.append("/>");
				    	}	    	
				 xml1.append("</data>");
				 System.out.println(xml1);
				PrintWriter out = response.getWriter();
				out.print(xml1);
			    return null;   
			}
			//获取发生量曲线的名称
			@RequestMapping("flex115")
			@Action(description = "asdf")
			public ModelAndView flex115(HttpServletRequest request,HttpServletResponse response) throws Exception {
				Long []defId=excelkService.sort();
				int n=2;
				//String xml="\"";
				String xml = "";
				for(int j=0;j<n;j++)
				{	
					List<BpmDefinition> a=bpmDefinitionService.getByDefId(defId[j]);
					xml+=a.get(0).getSubject();
					if(j!=n-1)xml+=",";
				}
				//xml+="\"";
				 System.out.println(xml);
				PrintWriter out = response.getWriter();
				out.print(xml);
			    return null;   
			}
			//任务消息设置
			@RequestMapping("flex116")
			@Action(description = "流程在线设计，保存，发布操作",
					detail="设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】," +
			               "<#if Boolean.parseBoolean(deploy)>" +
							  "<#if 0==bpmDefinition.getStatus()>"+
						          "版本号为【${bpmDefinition.versionNo}】" +
							  "<#else> 版本号为【${bpmDefinition.versionNo+1}】" +
						      "</#if>"+
			               "</#if>"
			)
			public ModelAndView flex116(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
				System.out.println("分支条件将结果返回给flex");
				//获得本机IP
				InetAddress addr = InetAddress.getLocalHost();
				String ip=addr.getHostAddress().toString();
				System.out.println("ip="+ip);
				PrintWriter out = response.getWriter();
				String nodeId=RequestUtil.getString(request, "bpmDefinition.defId2");
				System.out.println("@@@nodeId="+nodeId);
				Long defId=Long.valueOf(RequestUtil.getString(request, "bpmDefinition.defId"));
				System.out.println("defId:"+defId);
				BpmDefinition pos = bpmDefinitionService.getById(defId);
				String actDefId=pos.getActDefId();
		        System.out.println("actDeId="+actDefId);
				String newurl116="http://" + ip + ":8080/mas/activityDetail/activityDetail/activityDetail/activityInform.ht?actDefId="+actDefId+"&nodeId="+nodeId;
				out.print(newurl116); 
		        return null;   
			}
			
			/**
			 *显示及交互建模信息
			 * 
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			@RequestMapping("showmyjson")
			@Action(description = "查看流程定义明细")
			public ModelAndView showmyjson(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
				String jsonstr = RequestUtil.getString(request, "jsonstr");
				int num = RequestUtil.getInt(request, "num");
				ModelAndView modelAndView = getAutoView();
				modelAndView.addObject("jsonstr", jsonstr);
				return modelAndView;
			}
			
			
			/**
			 * 获得事件表
			 * 
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			@RequestMapping("watchAllevent")
			@Action(description = "查看流程定义明细")
			public ModelAndView watchAllevent(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
				String myjsonlist = RequestUtil.getString(request, "myjsonlist");
				int num = RequestUtil.getInt(request, "num");
				ModelAndView modelAndView = getAutoView();
				modelAndView.addObject("myjsonlist", myjsonlist);
				modelAndView.addObject("num", num);
				return modelAndView;
			}
			
			/**
			 * 主从参数绑定
			 * 
			 * @author 安宁
			 * @param request
			 * @param response
			 * @return
			 * @throws Exception
			 */
			@RequestMapping("flexan")
			
			public ModelAndView flexan(HttpServletRequest request,
					HttpServletResponse response) throws Exception {
				System.out.println("flex11将结果返回给flex");
				// 获得本机IP
				InetAddress addr = InetAddress.getLocalHost();
				String ip = addr.getHostAddress().toString();
				System.out.println("ip=" + ip);
			
				//PrintWriter out = response.getWriter();

				// 获取节点id
				String nodeId = RequestUtil.getString(request, "nodeId");
				System.out.println("@@@nodeId=" + nodeId);

				// 获取defId
				Long defId = Long.valueOf(RequestUtil.getString(request,
						"defId"));
				System.out.println("@@@defId=" + defId);

				// 获取actdefId
				//BpmDefinition pos = bpmDefinitionService.getById(defId);
				String actDefId = RequestUtil.getString(request, "actDefId");
				System.out.println("actDeId=" + actDefId);
				  /*
					String newurl6 = "http://"
							+ ip
							+ ":8080/mas/platform/bpm/bpmNodeWebService/editwb.ht?defId="
							+ defId + "&actDefId=" + actDefId + "&nodeId=" + nodeId
							+ "&type=" + "output";
					//out.print(newurl6);
					 **/
					 
					ModelAndView mv = new ModelAndView();
					mv.setViewName("/platform/bpm/bpmNodeWebServiceForman.jsp");
					mv.addObject("nodeId", nodeId);
					mv.addObject("defId",defId);
					mv.addObject("actDefId",actDefId);
				

				return mv;
			}
			
}
