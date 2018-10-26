package com.hotent.platform.controller.form;

import java.io.*;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.Markovchain.service.Markovchain.MarkovchainService;
import com.hotent.Newjsprelation.service.Newjsprelation.NewjsprelationService;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.bpmn20.entity.activiti.Out;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.impl.TableMetaFactory;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.formParcel.model.formParcel.FormParcel;
import com.hotent.formParcel.service.formParcel.FormParcelService;
import com.hotent.mobile.model.form.PlatformType;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.BpmFormTemplate;
import com.hotent.platform.model.form.TeamModel;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.form.BpmDataTemplateService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.form.BpmFormRightsService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.form.BpmFormTemplateService;
import com.hotent.platform.service.form.FormUtil;
import com.hotent.platform.service.form.ParseReult;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.xml.util.MsgUtil;
import com.hotent.tableParcel.model.tableParcel.TableParcel;
import com.hotent.tableParcel.service.tableParcel.TableParcelService;


import freemarker.template.TemplateException;
/**
 * 对象功能:自定义表单 控制器类 开发公司:广州宏天软件有限公司 开发人员:xwy 创建时间:2011-12-22 11:07:56
 */
@Controller
@RequestMapping("/platform/form/bpmFormDef/")
@Action(ownermodel=SysAuditModelType.FORM_MANAGEMENT)
public class BpmFormDefController extends BaseController {

	@Resource
	private BpmFormDefService service;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private BpmFormFieldService bpmFormFieldService;
	@Resource
	private BpmFormTemplateService bpmFormTemplateService;

	@Resource
	private BpmFormRightsService bpmFormRightsService;
	@Resource
	private FreemarkEngine freemarkEngine;
	@Resource
	private BpmService bpmService;
	@Resource
	private BpmFormHandlerService bpmFormHandlerService;
	@Resource
	private GlobalTypeService globalTypeService;
	
	@Resource
	private BpmDataTemplateService bpmDataTemplateService;
	@Resource
	private BpmNodeSetService bpmNodeSetService; 

	@Resource
	private NewjsprelationService newjsprelationService;

	@Resource
	private TableParcelService tableParcelService;
	@Resource
	private FormParcelService formParcelService;



	@RequestMapping("manage")
	@Action(description = "自定义表单管理页面")
	public ModelAndView manage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();
		return mv;
	}
	@RequestMapping("glmanage")
	@Action(description = "自定义表单管理页面")
	public ModelAndView glmanage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();
		return mv;
	}
	/**
	 * 取得自定义表单分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 *            请求页数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看自定义表单分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page)
			throws Exception {
		Long categoryId = RequestUtil.getLong(request, "categoryId");
		String dataTemplate=RequestUtil.getString(request,"dataTemplate");
		QueryFilter filter = new QueryFilter(request, "bpmFormDefItem");
		List<BpmFormDef> list = service.getAll(filter);
		Map<Long, Integer> publishedCounts = new HashMap<Long, Integer>();
		Map<Long, Integer> dataTemplateCounts = new HashMap<Long, Integer>();
		Map<Long, BpmFormDef> defaultVersions = new HashMap<Long, BpmFormDef>();
		for (int i = 0; i < list.size(); i++) {
			BpmFormDef formDef = list.get(i);
			String formKey=formDef.getFormKey();
			Long formId=formDef.getFormDefId();
			
			Integer publishedCount = service.getCountByFormKey(formKey);
			Integer dataFormCount=bpmDataTemplateService.getCountByFormKey(formKey);
		
			
			dataTemplateCounts.put(formId, dataFormCount);
			publishedCounts.put(formId, publishedCount);
			BpmFormDef defaultVersion = service.getDefaultVersionByFormKey(formKey);
			if (defaultVersion != null) {
				defaultVersions.put(formId, defaultVersion);
			}
		}
		if("true".equals(dataTemplate))
		{
			Iterator<BpmFormDef> iterator = list.iterator();
			while(iterator.hasNext()){
				BpmFormDef a = iterator.next();
				if(dataTemplateCounts.get(a.getFormDefId())<=0)
					iterator.remove();   //注意这个地方
			}
		}
		ModelAndView mv = this.getAutoView().addObject("bpmFormDefList", list)
				.addObject("publishedCounts", publishedCounts)
				.addObject("defaultVersions", defaultVersions)
				.addObject("dataTemplateCounts", dataTemplateCounts)
				.addObject("categoryId", categoryId)
				.addObject("dataTemplate", dataTemplate);
		return mv;
	}
	@RequestMapping("gllist")
	@Action(description = "查看自定义表单分页列表")
	public ModelAndView gllist(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page)
			throws Exception {
		Long categoryId = RequestUtil.getLong(request, "categoryId");
		QueryFilter filter = new QueryFilter(request, "bpmFormDefItem");
		List<BpmFormDef> list = service.getAll(filter);
		Map<Long, Integer> publishedCounts = new HashMap<Long, Integer>();
		Map<Long, Integer> dataTemplateCounts = new HashMap<Long, Integer>();
		Map<Long, BpmFormDef> defaultVersions = new HashMap<Long, BpmFormDef>();
		for (int i = 0; i < list.size(); i++) {
			BpmFormDef formDef = list.get(i);
			String formKey=formDef.getFormKey();
			Long formId=formDef.getFormDefId();
			
			Integer publishedCount = service.getCountByFormKey(formKey);
			Integer dataFormCount=bpmDataTemplateService.getCountByFormKey(formKey);
			
			dataTemplateCounts.put(formId, dataFormCount);
			publishedCounts.put(formId, publishedCount);
			BpmFormDef defaultVersion = service.getDefaultVersionByFormKey(formKey);
			if (defaultVersion != null) {
				defaultVersions.put(formId, defaultVersion);
			}
		}

		ModelAndView mv = this.getAutoView().addObject("bpmFormDefList", list)
				.addObject("publishedCounts", publishedCounts)
				.addObject("defaultVersions", defaultVersions)
				.addObject("dataTemplateCounts", dataTemplateCounts)
				.addObject("categoryId", categoryId);
		return mv;
	}

	@RequestMapping("newVersion")
	@Action(description = "新建表单版本",
			detail="新建表单版本【${SysAuditLinkService.getBpmFormDefLink(Long.valueof(formDefId))}】"
	)
	public void newVersion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String preUrl = RequestUtil.getPrePage(request);
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		ResultMessage msg;
		try {
			service.newVersion(formDefId);
			msg = new ResultMessage(ResultMessage.Success, "新建表单版本成功!");
		} catch (Exception ex) {
			msg = new ResultMessage(ResultMessage.Fail, "新建表单版本失败!");
		}
		addMessage(msg, request);
		response.sendRedirect(preUrl);
	}
	
	@ResponseBody
	@RequestMapping("getTableInfo")
	@Action(description = "获取对应自定义表信息")
	public List<BpmFormTable> getTableInfo(HttpServletRequest request,
			HttpServletResponse response){
		Long formDefId=RequestUtil.getLong(request, "formDefId", 0L);
		if(formDefId==0L)return null;
		List<BpmFormTable> bpmFormTableList=new ArrayList<BpmFormTable>();
		try {
			BpmFormDef bpmFormDef=service.getById(formDefId);
			Long tableId=bpmFormDef.getTableId();
			BpmFormTable bpmFormTable=bpmFormTableService.getById(tableId);
			bpmFormTableList.add(bpmFormTable);
			List<BpmFormTable>subTableList=bpmFormTableService.getSubTableByMainTableId(tableId);
			if(BeanUtils.isNotEmpty(subTableList)){
				bpmFormTableList.addAll(subTableList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bpmFormTableList;
		
	}

	/**
	 * 收集信息，为添加表单做准备
	 * 
	 * @param request
	 * @param response
	 * @param page
	 *            请求页数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("gatherInfo")
	@Action(description = "收集信息")
	public ModelAndView gatherInfo(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page)
			throws Exception {
		long categoryId = RequestUtil.getLong(request, "categoryId");
		String subject = RequestUtil.getString(request, "subject");
		String formDesc = RequestUtil.getString(request, "formDesc");
		int designType = RequestUtil.getInt(request, "designType",0);
	
		return this.getAutoView()
				.addObject("categoryId", categoryId)
				.addObject("subject",subject)
				.addObject("formDesc",formDesc)
				.addObject("designType",designType);
	}

	/**
	 * 选择模板
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selectTemplate")
	@Action(description = "选择模板")
	public ModelAndView selectTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String subject = RequestUtil.getString(request, "subject");
		Long categoryId = RequestUtil.getLong(request, "categoryId");
		String formDesc = RequestUtil.getString(request, "formDesc");
		Long tableId = RequestUtil.getLong(request, "tableId");
		int isSimple = RequestUtil.getInt(request, "isSimple", 0);
		String templatesId = RequestUtil.getString(request, "templatesId");
		String formKey = RequestUtil.getString(request, "formKey");

		ModelAndView mv = this.getAutoView();
		BpmFormTable table = bpmFormTableService.getById(tableId);

		if (table.getIsMain() == 1) {
			List<BpmFormTable> subTables = bpmFormTableService
					.getSubTableByMainTableId(tableId);
			List<BpmFormTemplate> mainTableTemplates = bpmFormTemplateService
					.getAllMainTableTemplate();
			List<BpmFormTemplate> subTableTemplates = bpmFormTemplateService
					.getAllSubTableTemplate();
			mv.addObject("mainTable", table).addObject("subTables", subTables)
					.addObject("mainTableTemplates", mainTableTemplates)
					.addObject("subTableTemplates", subTableTemplates);

		} else {
			List<BpmFormTable> subTables = new ArrayList<BpmFormTable>();
			subTables.add(table);
			List<BpmFormTemplate> subTableTemplates = bpmFormTemplateService
					.getAllSubTableTemplate();
			mv.addObject("subTables", subTables).addObject("subTableTemplates",
					subTableTemplates);
		}
		mv.addObject("subject", subject).addObject("categoryId", categoryId)
				.addObject("tableId", tableId).addObject("formDesc", formDesc)
				.addObject("isSimple", isSimple)
				.addObject("formKey", formKey)
				.addObject("templatesId", templatesId);

		return mv;
	}
	/**
	 * 选择数据包样式模板
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selectParcelTemplate")
	@Action(description = "选择数据包样式模板")
	public ModelAndView selectParcelTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mv = this.getAutoView();
		String subject = RequestUtil.getString(request, "subject");
		Long categoryId = RequestUtil.getLong(request, "categoryId");
		String formDesc = RequestUtil.getString(request, "formDesc");
		String tableId = RequestUtil.getString(request, "tableId");
		String formKey = RequestUtil.getString(request, "formKey");
		int isSimple = RequestUtil.getInt(request, "isSimple", 0);
		String templatesId = RequestUtil.getString(request, "templatesId");
		String tableId1[]=new String[tableId.length()/14];
		
		for(int i=0;i<tableId.length()/14;i++){
			tableId1[i]=tableId.substring(i*14,i*14+14);
			Long tableId2=Long.parseLong(tableId1[i]);
			
			TableParcel tableParcel=tableParcelService.getById(tableId2);
			
			

			List<BpmFormTable> subTables = bpmFormTableService
			.getSubTableByMainTableId(tableId2);
			List<BpmFormTemplate> mainTableTemplates = bpmFormTemplateService
			.getAllMainTableTemplate();
	List<BpmFormTemplate> subTableTemplates = bpmFormTemplateService
			.getAllSubTableTemplate();
	mv.addObject("mainTable", tableParcel).addObject("subTables", subTables)
			.addObject("mainTableTemplates", mainTableTemplates)
			.addObject("subTableTemplates", subTableTemplates);
			}
		

		/*TableParcel tableParcel=tableParcelService.getById(tableId);
	
		

		List<BpmFormTable> subTables = bpmFormTableService
		.getSubTableByMainTableId(tableId);*/

			
		mv.addObject("subject", subject).addObject("categoryId", categoryId)
				.addObject("tableId", tableId).addObject("formDesc", formDesc)
				.addObject("isSimple", isSimple)
				.addObject("formKey", formKey)
				.addObject("templatesId", templatesId);

		return mv;
	}
	/**
	 * 加载编辑器设计模式的模板列表
	 */
	@RequestMapping("chooseDesignTemplate")
	@Action(description = "选择编辑器设计模板")
	public ModelAndView chooseDesignTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();
		String subject = RequestUtil.getString(request, "subject");
		String formKey = RequestUtil.getString(request, "formKey");
		Long categoryId = RequestUtil.getLong(request, "categoryId");
		String formDesc = RequestUtil.getString(request, "formDesc");
		int isSimple = RequestUtil.getInt(request, "isSimple", 0);

		String templatePath = FormUtil.getDesignTemplatePath();
		String xml = FileUtil.readFile(templatePath + "designtemps.xml");
		Document document = Dom4jUtil.loadXml(xml);
		Element root = document.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> list = root.elements();
		String reStr = "[";
		for (Element element : list) {
			String alias = element.attributeValue("alias");
			String name = element.attributeValue("name");
			String templateDesc = element.attributeValue("templateDesc");
			if (!reStr.equals("["))
				reStr += ",";
			reStr += "{name:'" + name + "',alias:'" + alias
					+ "',templateDesc:'" + templateDesc + "'}";
		}
		reStr += "]";
		mv.addObject("subject", subject).addObject("categoryId", categoryId)
				.addObject("formDesc", formDesc).addObject("temps", reStr)
				.addObject("isSimple", isSimple)
				.addObject("formKey", formKey);
		return mv;
	}

	/**
	 * 编辑器设计表单
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("designEdit")
	@Action(description = "编辑器设计表单")
	public ModelAndView designEdit(HttpServletRequest request) throws Exception {
		Long formDefId = RequestUtil.getLong(request, "formDefId", 0);
		int isBack = RequestUtil.getInt(request, "isBack", 0);

		boolean isPublish = false;

		ModelAndView mv = getAutoView();
		BpmFormDef bpmFormDef = null;
		if (formDefId == 0) {
			bpmFormDef = new BpmFormDef();
			bpmFormDef
					.setCategoryId(RequestUtil.getLong(request, "categoryId"));
			bpmFormDef.setFormDesc(RequestUtil.getString(request, "formDesc"));
			bpmFormDef.setSubject(RequestUtil.getString(request, "subject"));
			bpmFormDef.setFormKey(RequestUtil.getString(request, "formKey"));
			bpmFormDef.setDesignType(BpmFormDef.DesignType_CustomDesign);
			String tempalias = RequestUtil.getString(request, "tempalias");
			String templatePath = FormUtil.getDesignTemplatePath();
			String reult = FileUtil
					.readFile(templatePath + tempalias + ".html");
			bpmFormDef.setHtml(reult);

			mv.addObject("canEditColumnNameAndType", true);
		} else {
			boolean canEditColumnNameAndType = true;
			bpmFormDef = service.getById(formDefId);
			Long tableId = bpmFormDef.getTableId();

			if (tableId > 0) {
				canEditColumnNameAndType = bpmFormTableService
						.getCanEditColumnNameTypeByTableId(bpmFormDef
								.getTableId());
				BpmFormTable bpmFormTable = bpmFormTableService
						.getById(tableId);
				mv.addObject("bpmFormTable", bpmFormTable);
				isPublish = true;
			}

			mv.addObject("canEditColumnNameAndType", canEditColumnNameAndType);
		}
		// 回退到编辑页面。
		if (isBack > 0) {
			Long categoryId = RequestUtil.getLong(request, "categoryId");
			String subject = RequestUtil.getString(request, "subject");
			String formDesc = RequestUtil.getString(request, "formDesc");
			String formKey =RequestUtil.getString(request, "formKey");
			String title = RequestUtil.getString(request, "tabTitle");
			String html = request.getParameter("html");

			bpmFormDef.setCategoryId(categoryId);
			bpmFormDef.setFormDesc(formDesc);
			bpmFormDef.setSubject(subject);
			bpmFormDef.setFormKey(formKey);
			bpmFormDef.setDesignType(BpmFormDef.DesignType_CustomDesign);
			bpmFormDef.setHtml(html);
			bpmFormDef.setTabTitle(title);
		}
		mv.addObject("bpmFormDef", bpmFormDef);
		mv.addObject("isPublish", isPublish);

		return mv;
	}

	/**
	 * 流程表单授权
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("rightsDialog")
	@Action(description = "流程表单授权")
	public ModelAndView rightsDialog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String actDefId = RequestUtil.getString(request, "actDefId");
		Long defId = RequestUtil.getLong(request, "defId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		String formKey = RequestUtil.getString(request, "formKey");
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		Integer platform = RequestUtil.getInt(request, "platform",0);
		
		boolean isNodeRights = false;

		ModelAndView mv = this.getAutoView();
		// 是否针对流程节点授权。
		if (StringUtil.isNotEmpty(nodeId) ) {
			List<BpmNodeSet> bpmNodeSetList = new ArrayList<BpmNodeSet>();
			if(StringUtil.isEmpty(parentActDefId)){
				bpmNodeSetList = bpmNodeSetService.getByDefId(defId);
			}else{
				bpmNodeSetList = bpmNodeSetService.getByDefId(defId, parentActDefId);
			}
			
			mv.addObject("bpmNodeSetList", bpmNodeSetList);
			mv.addObject("nodeId", nodeId);
			isNodeRights = true;
		}
		mv.addObject("formKey", formKey).
		addObject("actDefId", actDefId).
		addObject("isNodeRights", isNodeRights)
		.addObject("parentActDefId", parentActDefId)
		.addObject("platform",platform);
		return mv;
	}

	/**
	 * 根据表Id和模版Id获取所有的控件定义。
	 * 
	 * @param request
	 * @param response
	 * @param templateId
	 *            表单模板Id
	 * @param tableId
	 *            自定义表Id
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
/*	@ResponseBody
	@RequestMapping("getControls")
	@Action(description = "获取表单控件")
	public Map<String, String> getMacroTemplate(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "templateAlias") Long templateAlias,
			@RequestParam(value = "tableId") Long tableId)
			throws TemplateException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		BpmFormTemplate template = bpmFormTemplateService.getById(templateAlias);
		if (template != null) {
			template = bpmFormTemplateService.getByTemplateAlias(template
					.getMacroTemplateAlias());
			String macro = template.getHtml();
			BpmFormTable table = bpmFormTableService.getById(tableId);
			List<BpmFormField> fields = bpmFormFieldService
					.getByTableId(tableId);
			for (BpmFormField field : fields) {
				String fieldname = field.getFieldName();
				// 字段命名规则
				// 表类型(m:主表，s:子表) +":" + 表名 +“：” + 字段名称
				field.setFieldName((table.getIsMain() == 1 ? "m:" : "s:")
						+ table.getTableName() + ":" + field.getFieldName());
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("field", field);
				map.put(fieldname,
						freemarkEngine.parseByStringTemplate(data, macro
								+ "<@input field=field/>"));
			}
		}
		return map;
	}*/

	/**
	 * 删除自定义表单。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("delByFormKey")
	@Action(description = "删除自定义表单",
			execOrder=ActionExecOrder.BEFORE,
			detail="自定义表单:" +
					"<#list StringUtils.split(formKey,\",\") as item>" +
					"<#assign entity=bpmFormDefService.getById(Long.valueOf(item))/>" +
					"【${entity.subject}】" +
					"<#if bpmFormDefService.getFlowUsed(item)<=0 >删除成功" +
					"<#else>不能被删除" +
					"</#if>"+
					"</#list>" 
	)
	public void delByFormKey(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] aryFormKey = RequestUtil.getStringAryByStr(request, "formKey");
		String type=RequestUtil.getString(request, "type");
		System.out.println("输出:"+type);
		ResultMessage msg = null;
		FormParcel formParcel=new FormParcel();
		List<String> subject=new ArrayList<String>();
		List<String> actDef=new ArrayList<String>();
		boolean error = false ;
		int rtn = 0;
		for(String formKey:aryFormKey){
			rtn = service.getFlowUsed(formKey);
			
			if (rtn > 0) {
				error = true ;
				List list = service.getBpmFormRelatedDef(formKey);
				if(BeanUtils.isEmpty(list) || list.size()!=2) continue ;
				if(BeanUtils.isEmpty(subject)){
					//多个表单被关联，只提示被关联的表单名列表
					actDef = (List<String>) list.get(1);
				}
				subject.add((String)list.get(0));
			} else {
				try {
					if("2".equals(type))
					{
					List<FormParcel> list=formParcelService.getByFormName(formKey);
					System.out.println("LIST:"+list);
					for(int i=0;i<list.size();i++)
					{
						formParcelService.delById(list.get(i).getId());
						
					}
					
					service.delByFormKey(formKey);
					
					}
					else{
						service.delByFormKey(formKey);
					}
					
					
				} catch (Exception e) {
					msg = new ResultMessage(ResultMessage.Fail, "删除表单失败!");
				}
			}
		}
		if (error) {
			if(subject.size()>1){
				//多个表单被关联，只提示被关联的表单名列表
				String result = "" ;
				for(String str:subject){
					result += "<br>"+str;
				}
				msg = new ResultMessage(ResultMessage.Fail, "以下表单已和流程进行关联，不能删除:"+result);
			}else if(subject.size()==1){
				String result = "" ;
				for(int i=0;i<actDef.size();i++){
					result += "<br>"+(i+1)+"、"+actDef.get(i);
				}
				msg = new ResultMessage(ResultMessage.Fail, "表单：\""+subject.get(0)+"\"<br>已和以下流程进行关联，不能删除:"+result);
			}
		} else if(BeanUtils.isEmpty(msg)){
			msg = new ResultMessage(ResultMessage.Success, "删除表单成功!");
		}
		writeResultMessage(response.getWriter(), msg);
	}

	/**
	 * 编辑自定义表单。
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑自定义表单")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		ModelAndView mv = getAutoView();
	
    
       
       
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		String returnUrl = RequestUtil.getPrePage(request);
		System.out.println(returnUrl);
		BpmFormDef bpmFormDef = null;
		if (formDefId != 0) {
			bpmFormDef = service.getById(formDefId);
		} else {
			bpmFormDef = new BpmFormDef();
			bpmFormDef.setTableId(RequestUtil.getLong(request, "tableId"));
			bpmFormDef.setCategoryId(RequestUtil.getLong(request, "categoryId"));
			System.out.println(RequestUtil.getLong(request, "categoryId"));
			String formDesc=RequestUtil.getString(request, "formDesc");
			System.out.println(formDesc);
			bpmFormDef.setFormDesc(RequestUtil.getString(request, "formDesc"));
			bpmFormDef.setSubject(RequestUtil.getString(request, "subject"));
			System.out.println(RequestUtil.getString(request, "subject"));
			bpmFormDef.setFormKey(RequestUtil.getString(request, "formKey"));
			System.out.println(RequestUtil.getString(request, "formKey"));
			Long[] templateTableId = RequestUtil.getLongAryByStr(request,"templateTableId");
          
			String[] templateAlias = RequestUtil.getStringAryByStr(request,"templateAlias");
			String templatesId = getTemplateId(templateTableId, templateAlias);
			
			String reult = this.genTemplate(templateTableId, templateAlias);
			bpmFormDef.setHtml(reult);
			bpmFormDef.setTemplatesId(templatesId);
			}
			
			
			
		

		mv.addObject("bpmFormDef", bpmFormDef)
				.addObject("returnUrl", returnUrl);

		return mv;
	}

	/**
	 * 数据包中编辑自定义表单。
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("edit1")
	@Action(description = "编辑数据包自定义表单")
	public ModelAndView edit1(HttpServletRequest request) throws Exception {
		ModelAndView mv = getAutoView();
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		String returnUrl = RequestUtil.getPrePage(request);
		 String tableid=RequestUtil.getString(request, "tableId");
		 
		 mv.addObject("tableId",tableid);
		 BpmFormDef bpmFormDef = null;
		// request.setAttribute("tableId", tableid);
		
		if (formDefId != 0) {
			bpmFormDef = service.getById(formDefId);
		} else {
			bpmFormDef = new BpmFormDef();
			
			//bpmFormDef.setTableId(RequestUtil.getLong(request, "tableId"));
       
			bpmFormDef.setCategoryId(RequestUtil.getLong(request, "categoryId"));
			bpmFormDef.setFormDesc(RequestUtil.getString(request, "formDesc"));
			bpmFormDef.setSubject(RequestUtil.getString(request, "subject"));
			bpmFormDef.setFormKey(RequestUtil.getString(request, "formKey"));
      //      String tableid=RequestUtil.getString(request, "tableId");
            Long[] templateTableId = new Long[tableid.length()/14];
            for(int i=0;i<tableid.length()/14;i++)
            {
            	templateTableId[i]=Long.parseLong(tableid.substring(i*14, i*14+14));
            	bpmFormDef.setTableId(templateTableId[i]);
            }
			
			
          
			String[] templateAlias = RequestUtil.getStringAryByStr(request,"templateAlias");
			String templatesId = getTemplateId(templateTableId, templateAlias);
			
				String reult = this.genParcelTemplate(templateTableId, templateAlias[0]);
				bpmFormDef.setHtml(reult);
			    bpmFormDef.setTemplatesId(templatesId);
		}
 
		mv.addObject("bpmFormDef", bpmFormDef)
				.addObject("returnUrl", returnUrl);

		return mv;
	}
	/**
	 * 获得模板与表的对应关系
	 * 
	 * @param templateTablesId
	 * @param templatesId
	 * @return
	 */
	private String getTemplateId(Long[] templateTablesId, Long[] templatesId) {
		StringBuffer sb = new StringBuffer();
		if (BeanUtils.isEmpty(templateTablesId)
				|| BeanUtils.isEmpty(templatesId))
			return sb.toString();
		for (int i = 0; i < templateTablesId.length; i++) {
			for (int j = 0; j < templatesId.length; j++) {
				if (i == j) {
					sb.append(templateTablesId[i]).append(",")
							.append(templatesId[j]);
					break;
				}
			}
			sb.append(";");
		}
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * 取得自定义表单明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看自定义表单明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "formDefId");
		long canReturn = RequestUtil.getLong(request, "canReturn",0);
		String preUrl = RequestUtil.getPrePage(request);
		BpmFormDef bpmFormDef = service.getById(id);
		return getAutoView().addObject("bpmFormDef", bpmFormDef).addObject(
				"returnUrl", preUrl).addObject("canReturn",canReturn);
	}

	/**
	 * 取得表fields, 如果是主表，同时取所有子表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getAllFieldsByTableId")
	public String getAllFieldsByTableId(HttpServletRequest request,HttpServletResponse response)throws Exception {
		Long tableId = RequestUtil.getLong(request, "tableId",0);
		
		Long formDefId = RequestUtil.getLong(request, "formDefId",0);
		if(tableId.longValue()==0){
			if(formDefId.longValue()>0){
				BpmFormDef bpmFormDef = service.getById(formDefId);
				tableId = bpmFormDef.getTableId();
			}
		}
		if(tableId.longValue()==0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		BpmFormTable mainTable = bpmFormTableService.getById(tableId);
		//System.out.println("1:"+mainTable.getTableDesc()+"#2:"+mainTable.getTableName());
		List<BpmFormField> mainTableFields = bpmFormFieldService.getByTableId(tableId);
		sb.append("{mainname:\"");
		// 表没有填写描述时，取表名
		sb.append(StringUtil.isEmpty(mainTable.getTableDesc()) ? mainTable.getTableName() : mainTable.getTableDesc());
		sb.append("\",mainid:");
		sb.append(tableId);
		sb.append(",tablename:\"");
		sb.append(mainTable.getTableName());
		sb.append("\",mainfields:");
		JSONArray jArray = (JSONArray) JSONArray.fromObject(mainTableFields);
		String s = jArray.toString();
		sb.append(s);
		sb.append(",subtables:[");

		List<BpmFormTable> subTables = bpmFormTableService.getSubTableByMainTableId(tableId);
		for (int i = 0; i < subTables.size(); i++) {
			BpmFormTable subTable = subTables.get(i);
			Long subTableId = subTable.getTableId();
			List<BpmFormField> subFields = bpmFormFieldService.getByTableId(subTableId);
			if (i > 0)
				sb.append(",");
			sb.append("{name:\"");
			sb.append(StringUtil.isEmpty(subTable.getTableDesc()) ? subTable.getTableName() : subTable.getTableDesc());
			sb.append("\",id:");
			sb.append(subTableId);
			sb.append(",tablename:\"");
			sb.append(subTable.getTableName());
			sb.append("\",subfields:");
			JSONArray subArray = (JSONArray) JSONArray.fromObject(subFields);
			sb.append(subArray.toString());
			sb.append("}");
		}
		sb.append("]}");
		System.out.println("表的："+sb.toString());
		return sb.toString();
	}

	/**
	 *根据表id 获取表的所有数据包
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author wb
	 */
	@ResponseBody
	@RequestMapping("getAllParcelsByTableId")
	public String getAllParcelsByTableId(HttpServletRequest request,HttpServletResponse response)throws Exception {

		Long tableId = RequestUtil.getLong(request, "tableId",0);
		BpmFormTable mainTable = bpmFormTableService.getById(tableId);
		String tablename=mainTable.getTableName();
		List<TableParcel> parcels=tableParcelService.getParcelsbyTableName(tablename);
		
		JSONArray chidrentree=new JSONArray();
		
		for(int i=0;i<parcels.size();i++){
			JSONObject chidren=new JSONObject();
			JSONArray fildarr=new JSONArray();
			
			String parcelname= parcels.get(i).getParcel_name();
			String values=parcels.get(i).getParcel_value();
			JSONArray jvalues=JSONArray.fromObject(values);
		
			//[{"field":"F_actDef_Id","comment":"流程ID"},{"field":"F_activity_id","comment":"活动ID"}]
			
			for(int j=0;j<jvalues.size();j++){
				JSONObject fild=new JSONObject();
				
				JSONObject jobj=(JSONObject) jvalues.get(j);
				String value=jobj.getString("comment")+"("+jobj.getString("field")+")";
		
				fild.accumulate("name", value);
			//	fild.accumulate("name", "wb");
				fildarr.add(fild);
				
			}
			chidren.accumulate("name", parcelname);
			chidren.accumulate("children", fildarr);
			chidrentree.add(chidren);
		}
		
		JSONObject parcel=new JSONObject();
		parcel.accumulate("name", "数据包");
		parcel.accumulate("children", chidrentree);
		parcel.accumulate("open", true);
		
		JSONArray jarray=new JSONArray();	
		jarray.add(parcel);
		
		return jarray.toString();
	}

	

	/**
	 * 取得数据包fields
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getAllFieldsByTableId1")
	public String getAllFieldsByTableId1(HttpServletRequest request,HttpServletResponse response)throws Exception {
		
		String tableId = request.getParameter("tableId");
	
		Long []tableid=new Long[tableId.length()/14];
		StringBuffer sb = new StringBuffer();
		List<BpmFormField> mainTableFields2 = new ArrayList<BpmFormField>();
		for(int z=0;z<tableId.length()/14;z++)
		{
			tableid[z]=Long.parseLong(tableId.substring(z*14, z*14+14));
			TableParcel q=tableParcelService.getById(tableid[z]);
			String tp2=q.getParcel_value();
			String table_name=q.getTable_name();
			Long table_id=bpmFormTableService.getByTableName(table_name).getTableId();
		
			Long formDefId = RequestUtil.getLong(request, "formDefId",0);
			if(table_id.longValue()==0){
				if(formDefId.longValue()>0){
					BpmFormDef bpmFormDef = service.getById(formDefId);
					table_id = bpmFormDef.getTableId();
				}
			}
			if(table_id.longValue()==0){
				return "";
			}										
			List<BpmFormField> mainTableFields = bpmFormFieldService.getByTableId(table_id);
			
			
			ArrayList<String> list = new ArrayList<String>();
			JSONArray jsonArr = JSONArray.fromObject(tp2);
			Iterator<JSONArray> itr = jsonArr.iterator(); 
			while(itr.hasNext()) { 
				String []a=null;
				JSONObject temp = JSONObject.fromObject(itr.next()); 
				list.add("fieldName="+temp.get("field"));		    
			}
			
			for(int j=0;j<mainTableFields.size();j++)
			{
				String[] st=mainTableFields.get(j).toString().split("\\[");
			    String split=st[1].substring(0, st[1].indexOf("]"));
			    for(int k=0;k<list.size();k++)
				{if(list.get(k).equals(split))
				{
					mainTableFields2.add(mainTableFields.get(j));
				}
				}
			}
		}
		
			for(int x=0;x<1;x++)
			{
				TableParcel q=tableParcelService.getById(tableid[x]);
				String tp2=q.getParcel_value();
				String table_name=q.getTable_name();
				Long table_id=bpmFormTableService.getByTableName(table_name).getTableId();
			
				Long formDefId = RequestUtil.getLong(request, "formDefId",0);
				if(table_id.longValue()==0){
					if(formDefId.longValue()>0){
						BpmFormDef bpmFormDef = service.getById(formDefId);
						table_id = bpmFormDef.getTableId();
					}
				}
				if(table_id.longValue()==0){
					return "";
				}
				BpmFormTable mainTable = bpmFormTableService.getById(table_id);
				sb.append("{mainname:\"");
			// 表没有填写描述时，取表名
			sb.append(StringUtil.isEmpty(mainTable.getTableDesc()) ? mainTable.getTableName() : mainTable.getTableDesc());
			sb.append("\",mainid:");
			sb.append(table_id);
			sb.append(",tablename:\"");
			sb.append(mainTable.getTableName());
			sb.append("\",mainfields:");
			JSONArray jArray = (JSONArray) JSONArray.fromObject(mainTableFields2);
			String s = jArray.toString();
			sb.append(s);
			sb.append(",subtables:[");

			List<BpmFormTable> subTables = bpmFormTableService.getSubTableByMainTableId(table_id);
			for (int i = 0; i < subTables.size(); i++) {
				BpmFormTable subTable = subTables.get(i);
				Long subTableId = subTable.getTableId();
				List<BpmFormField> subFields = bpmFormFieldService.getByTableId(subTableId);
				if (i > 0)
					sb.append(",");
				sb.append("{name:\"");
				sb.append(StringUtil.isEmpty(subTable.getTableDesc()) ? subTable.getTableName() : subTable.getTableDesc());
				sb.append("\",id:");
				sb.append(subTableId);
				sb.append(",tablename:\"");
				sb.append(subTable.getTableName());
				sb.append("\",subfields:");
				JSONArray subArray = (JSONArray) JSONArray.fromObject(subFields);
				sb.append(subArray.toString());
				sb.append("}");
			}
			sb.append("]}");
			
		}
		
		System.out.println("数据包的："+sb.toString());
		return sb.toString();
		
	}
	/**
	 * 根据表和表单定义ID获取表单权限信息。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//TODO
	@ResponseBody
	@RequestMapping("getPermissionByTableFormKey")
	public Map<String, List<JSONObject>> getPermissionByTableFormKey(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long tableId = RequestUtil.getLong(request, "tableId");
		String formKey = RequestUtil.getString(request, "formKey");
		Integer platform = RequestUtil.getInt(request, "platform",0);
		PlatformType platformType = PlatformType.getEnumFromString(RequestUtil.getString(request, "platform","PC"));
		Map<String, List<JSONObject>> permission = null;
		if (StringUtil.isNotEmpty(formKey) ) {
			permission = bpmFormRightsService.getPermission(formKey, null, null,null,platform,platformType);
		} else {
			// 还没有生成表单，没有定义表单权限。
			// 则根据表定义获取表单的权限。
			// 这里还缺少意见表单权限。
			permission = bpmFormRightsService.getPermissionByTableId(tableId);
		}
		return permission;
	}
	
	
	/**
	 * 根据表和表单定义ID获取表单权限信息。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//TODO 
	@ResponseBody
	@RequestMapping("getPermissionByActDefId")
	public Map<String, List<JSONObject>> getPermissionByActDefId(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String formKey = RequestUtil.getString(request, "formKey");
		String actDefId = RequestUtil.getString(request, "actDefId");
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		Integer platform = RequestUtil.getInt(request, "platform",0);
		Map<String, List<JSONObject>> permission = null;

		PlatformType platformType = PlatformType.getEnumFromString(RequestUtil.getString(request, "platform","PC"));
		if(StringUtil.isEmpty(parentActDefId)){
			permission = bpmFormRightsService.getPermission(formKey, actDefId, null,null,platform,platformType);
		}else{
			permission = bpmFormRightsService.getPermission(formKey, actDefId, null, parentActDefId,platform,platformType);
		}
		return permission;
	}

	
	/**
	 * 根据流程定义，任务节点，表单定义id获取权限信息。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *
	 */
	@ResponseBody
	@RequestMapping("getPermissionByFormNode")
	public Map<String, List<JSONObject>> getPermissionByFormNode(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String actDefId = RequestUtil.getString(request, "actDefId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		String formKey = RequestUtil.getString(request, "formKey");
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		Integer platform = RequestUtil.getInt(request, "platform",0);
		Map<String, List<JSONObject>> permission = null;
		PlatformType platformType = PlatformType.getEnumFromString(RequestUtil.getString(request, "platform","PC"));
		if(StringUtil.isEmpty(parentActDefId)){
			permission = bpmFormRightsService.getPermission(formKey, actDefId, nodeId,null,platform,platformType);
		}else{
			permission =  bpmFormRightsService.getPermission(formKey, actDefId, nodeId,parentActDefId,platform,platformType);
		}
		return permission;
	}
	//TODO

	/**
	 * 保存表单权限。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("savePermission")
	@Action(description="保存表单权限",
			detail="保存表单【${SysAuditLinkService.getBpmFormDefLink(formKey)}】的表单权限"
	)
	public void savePermission(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		String permission = request.getParameter("permission");
		String actDefId = RequestUtil.getString(request, "actDefId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		String formKey = RequestUtil.getString(request, "formKey");
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		JSONObject jsonObject = JSONObject.fromObject(permission);
		ResultMessage resultMessage = null;
		PlatformType platformType = PlatformType.getEnumFromString(RequestUtil.getString(request, "platform","PC"));
		try {
			if (StringUtil.isNotEmpty(nodeId)) {
				// 设置节点权限。
				bpmFormRightsService.save(actDefId, nodeId, formKey, jsonObject, parentActDefId,platformType);
			}else if(StringUtil.isNotEmpty(actDefId)){
				// 设置流程全局权限。
				bpmFormRightsService.save(actDefId, null, formKey, jsonObject, parentActDefId,platformType);
			}else {
				// 根据表单key保存权限。
				bpmFormRightsService.save(formKey, jsonObject,platformType);
			}

			resultMessage = new ResultMessage(ResultMessage.Success,
					"表单权限保存成功!");
		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,
						"表单权限保存失败:" + str);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
			}
		}
		out.print(resultMessage);
	}

	/**
	 * 根据模板产生html。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("genByTemplate")
	public void genByTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long[] templateTableId = RequestUtil.getLongAryByStr(request,
				"templateTableId");
		String[] templateAlias = RequestUtil.getStringAryByStr(request, "templateAlias");
		PrintWriter out = response.getWriter();
		String html = genTemplate(templateTableId, templateAlias);
		out.println(html);
	}

	/**
	 * 根据模板别名返回 编辑器设计模板
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getHtmlByAlias")
	public void getHtmlByAlias(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tempalias = RequestUtil.getString(request, "tempalias");
		PrintWriter out = response.getWriter();
		String templatePath = FormUtil.getDesignTemplatePath();
		String reult = FileUtil.readFile(templatePath + tempalias + ".html");
		out.println(reult);
	}

	/**
	 * 根据表和指定的html生成表单。
	 * 
	 * @param tableIds
	 * @param tableTemplateIds
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	private String genTemplate(Long[] tableIds, Long[] tableTemplateIds)
			throws TemplateException, IOException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tableIds.length; i++) {
			// 表
			Map<String, Object> fieldsMap = new HashMap<String, Object>();
			BpmFormTable table = bpmFormTableService.getById(tableIds[i]);
			List<BpmFormField> fields = bpmFormFieldService
					.getByTableId(tableIds[i]);
			fieldsMap.put("table", table);
			fieldsMap.put("fields", fields);
			for (BpmFormField field : fields) {
				field.setFieldName((table.getIsMain() == 1 ? "m:" : "s:")
						+ table.getTableName() + ":" + field.getFieldName());
			}
			// 模板
			BpmFormTemplate tableTemplate = bpmFormTemplateService
					.getById(tableTemplateIds[i]);
			BpmFormTemplate macroTemplate = bpmFormTemplateService
					.getByTemplateAlias(tableTemplate.getMacroTemplateAlias());
			String macroHtml = "";
			if (macroTemplate != null) {
				macroHtml = macroTemplate.getHtml();
			}
			String result = freemarkEngine.parseByStringTemplate(fieldsMap,
					macroHtml + tableTemplate.getHtml());
			if (table.getIsMain() == 1) {
				sb.append(result);
			} else {
				sb.append("<div type=\"subtable\" right=\"w\" tableName=\"");
				sb.append(table.getTableName());
				sb.append("\">\n");
				sb.append(result);
				sb.append("</div>\n");
			}
		}
		return sb.toString();
	}

	/**
	 * 发布表单
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("publish")
	@Action(description = "发布表单",detail="发布表单【${SysAuditLinkService.getBpmFormDefLink(Long.valueOf(formDefId))}】")
	public void publish(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		String prevPage = RequestUtil.getPrePage(request);
		ResultMessage resultObj = null;
		try {
			service.publish(formDefId, ContextUtil.getCurrentUser()
					.getFullname());
			resultObj = new ResultMessage(ResultMessage.Success, "发布版本成功!");
		} catch (Exception e) {
			e.printStackTrace();
			resultObj = new ResultMessage(ResultMessage.Fail, e.getCause()
					.toString());
		}
		addMessage(resultObj, request);
		response.sendRedirect(prevPage);
	}

	/**
	 * 查看版本
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("versions")
	@Action(description = "查看版本")
	public ModelAndView versions(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = getAutoView();

		String formKey = request.getParameter("formKey");

		// 版本信息
		List<BpmFormDef> versions = service.getByFormKey(formKey);
		result.addObject("versions", versions).addObject("formName",
				versions.get(0).getSubject());
		return result;
	}

	/**
	 * 设置默认版本
	 * 
	 * @param request
	 * @param response
	 * @param formDefId
	 * @param formDefId
	 * @throws Exception
	 */
	@RequestMapping("setDefaultVersion")
	@Action(description = "设置默认版本" , detail="设置表单【${SysAuditLinkService.getBpmFormDefLink(formDefId)}： ${formDefId}】为默认版本")
	public void setDefaultVersion(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("formDefId") Long formDefId,
			@RequestParam("formKey") String formKey) throws Exception {
		ResultMessage resultObj = new ResultMessage(ResultMessage.Success,
				"设置默认版本成功!");
		String preUrl = RequestUtil.getPrePage(request);
		service.setDefaultVersion(formDefId, formKey);
		addMessage(resultObj, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 
	 * 选择器
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description = "选择器")
	public ModelAndView selector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter queryFilter = new QueryFilter(request, "bpmFormDefItem");
		boolean dataTemplate = RequestUtil.getBoolean(request,"dataTemplate");
		boolean isMutl = RequestUtil.getBoolean(request,"isMutl");
		List<BpmFormDef> list = service.getPublished(queryFilter);
		List<BpmFormDef> removeList = new ArrayList();
		if(dataTemplate){
			for(BpmFormDef bpmform:list){
				int dataFormCount=bpmDataTemplateService.getCountByFormKey(bpmform.getFormKey());
				if(dataFormCount==0){
					removeList.add(bpmform);
				}
			}
			list.removeAll(removeList);
		}
		ModelAndView mv = this.getAutoView().addObject("bpmFormDefList", list).addObject("isMutl",isMutl);
		return mv;
	}

	/**
	 * 根据表单定义id获取是否可以删除。
	 * 
	 * @param formDefId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("getFlowUsed")
	public void getFlowUsed(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String formKey = RequestUtil.getString(request, "formKey");
		int rtn = service.getFlowUsed(formKey);
		out.println(rtn);
	}

	/**
	 * 设计表。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "designTable", method = RequestMethod.POST)
	public ModelAndView designTable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String formKey = request.getParameter("formKey");
		String content = request.getParameter("content");
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		Long categoryId = RequestUtil.getLong(request, "categoryId");

		String subject = RequestUtil.getString(request, "subject");
		String formDesc = RequestUtil.getString(request, "formDesc");
		String tabTitle = RequestUtil.getString(request, "tabTitle");

		ParseReult result = FormUtil.parseHtmlNoTable(content, "", "");

		String tableName = RequestUtil.getString(request, "tableName");
		String tableComment = RequestUtil.getString(request, "tableComment");

		boolean canEditTableName = true;

		if (formDefId > 0) {
			BpmFormDef bpmFormDef = service.getById(formDefId);
			Long tableId = bpmFormDef.getTableId();
			if (tableId > 0) {
				canEditTableName = bpmFormTableService
						.getCanEditColumnNameTypeByTableId(tableId);
			}
		}

		ModelAndView mv = this.getAutoView();
		mv.addObject("result", result).addObject("content", content)
				.addObject("formDefId", formDefId)
				.addObject("subject", subject)
				.addObject("formKey", formKey)
				.addObject("categoryId", categoryId)
				.addObject("formDesc", formDesc)
				.addObject("tabTitle", tabTitle)
				.addObject("tableName", tableName)
				.addObject("tableComment", tableComment)
				.addObject("canEditTableName", canEditTableName);
		return mv;
	}
	/**
	 * 对表单的html进行验证，验证html是否合法。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "validDesign", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> validDesign(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		Long formDefId = RequestUtil.getLong(request, "formDefId");
		String html = request.getParameter("html");
		// html=html.replace("&amp;#39;", "'");
		String tableName = RequestUtil.getString(request, "tableName");
		String tableComment = RequestUtil.getString(request, "tableComment");
		Boolean incHide = RequestUtil.getBoolean(request, "incHide", false);
		// boolean isTableExist=false;
		Long tableId = 0L;
		if (formDefId > 0) {
			BpmFormDef bpmFormDef = service.getById(formDefId);
			tableId = bpmFormDef.getTableId();
		}
		// 输入了主表名。
		// 验证主表名称。
		if (StringUtil.isNotEmpty(tableName)) {
			if (tableId > 0) {
				BpmFormTable bpmFormTable = bpmFormTableService
						.getById(tableId);
				// 输入的表名和原来的表名不一致的情况。
				if (!tableName.equalsIgnoreCase(bpmFormTable.getTableName())) {
					boolean isTableExist = bpmFormTableService
							.isTableNameExisted(tableName);
					if (isTableExist) {
						map.put("valid", false);
						map.put("errorMsg", "表:" + tableName + ",在系统中已经存在!");
						return map;
					}
				}
			} else {
				boolean isTableExist = bpmFormTableService
						.isTableNameExisted(tableName);
				if (isTableExist) {
					map.put("valid", false);
					map.put("errorMsg", "表:" + tableName + ",在系统中已经存在!");
					return map;
				}
			}
		}

		ParseReult result = FormUtil.parseHtmlNoTable(html, tableName,
				tableComment);

		BpmFormTable bpmFormTable = result.getBpmFormTable();
		// 验证子表。
		String strValid = validSubTable(bpmFormTable, tableId);

		if (StringUtil.isNotEmpty(strValid)) {
			map.put("valid", false);
			map.put("errorMsg", strValid);
			return map;
		}
		// 验证表单是否有错。
		boolean rtn = result.hasErrors();
		if (rtn) {
			map.put("valid", false);
			map.put("errorMsg", result.getError());
		} else {
			map.put("valid", true);
			map.put("table", result.getBpmFormTable());
			if (incHide) {// 需要添加隐藏的控件ID
				addHiddenFiled(bpmFormTable);
				List<BpmFormTable> subList = bpmFormTable.getSubTableList();
				for (BpmFormTable sub : subList) {
					addHiddenFiled(sub);
				}
				map.put("table", bpmFormTable);
			}
		}
		return map;

	}

	/**
	 * 根据传入的bpmFormTable，复制并添加控件对应的隐藏ID字段信息到传入的bpmFormTable中
	 * 
	 * @param bpmFormTable
	 */
	private void addHiddenFiled(BpmFormTable bpmFormTable) {
		List<BpmFormField> list = bpmFormTable.getFieldList();
		BpmFormField hiddenField = null;
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			BpmFormField field = list.get(i);
			if (BpmFormTableService.isExecutorSelector(field.getControlType())) {
				hiddenField = (BpmFormField) field.clone();
				hiddenField.setIsHidden(BpmFormField.HIDDEN);
				hiddenField.setFieldName(field.getFieldName()
						+ BpmFormField.FIELD_HIDDEN);
				hiddenField.setFieldDesc(field.getFieldDesc()
						+ BpmFormField.FIELD_HIDDEN);
				bpmFormTable.addField(hiddenField);
			}
		}
	}

	/**
	 * 验证子表系统中是否存在。
	 * 
	 * <pre>
	 * 	对表单的子表循环。
	 * 		1.表单还没有生成子表的情。
	 * 			验证子表表名系统中是否存在。
	 * 		2.已经生成子表。
	 * 			判断当前子表是否在原子表的列表中，如果不存在，则表示该子表为新添加的表，需要验证子表系统中是否已经存在。
	 * 
	 * </pre>
	 * 
	 * @param bpmFormTable
	 * @param tableId
	 * @return
	 */
	private String validSubTable(BpmFormTable bpmFormTable, Long tableId) {

		List<BpmFormTable> subTableList = bpmFormTable.getSubTableList();
		if (BeanUtils.isEmpty(subTableList))
			return "";
		String str = "";
		for (BpmFormTable subTable : subTableList) {
			String tableName = subTable.getTableName().toLowerCase();
			// 还没有生成表的情况。
			if (tableId == 0) {
				boolean isTableExist = bpmFormTableService
						.isTableNameExisted(tableName);
				if (isTableExist) {
					str += "子表:【" + tableName + "】系统中已经存在!\r\n";
				}
			}
			// 表已经生成的情况。
			else {
				BpmFormTable orginTable = bpmFormTableService
						.getTableById(tableId);
				List<BpmFormTable> orginSubTableList = orginTable
						.getSubTableList();
				Map<String, BpmFormTable> mapSubTable = new HashMap<String, BpmFormTable>();
				for (BpmFormTable table : orginSubTableList) {
					mapSubTable.put(table.getTableName().toLowerCase(), table);
				}
				// 原子表中不存在该表，表示该表为新添加的表。
				if (!mapSubTable.containsKey(tableName)) {
					boolean isTableExist = bpmFormTableService
							.isTableNameExisted(tableName);
					if (isTableExist) {
						str += "子表:【" + tableName + "】系统中已经存在!\r\n";
					}
				}
			}
		}
		return str;
	}

	/**
	 * 设计时对表单进行预览。
	 * 
	 * <pre>
	 * 	步骤：
	 * 	1.获取设计的html。
	 *  2.对设计的html解析。
	 *  3.生成相应的freemaker模版。
	 *  4.解析html模版输入实际的html。
	 * </pre>
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("preview")
	@Action(description = "编辑器设计表单预览")
	public ModelAndView preview(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long formDefId = RequestUtil.getLong(request, "formDefId", 0);
		PlatformType platformType = PlatformType.getEnumFromString(RequestUtil
				.getString(request, "platform", "PC"));
		String html = "";
		String name = "";
		String comment = "";
		String title = "";
		// 在列表页面点击预览
		Map<String, Map<String, String>> permission = null;
		if (formDefId > 0) {
			BpmFormDef bpmFormDef = service.getById(formDefId);
			html = bpmFormDef.getHtml();
			name = bpmFormDef.getSubject();
			comment = bpmFormDef.getFormDesc();
			title = bpmFormDef.getTabTitle();
			permission = bpmFormRightsService.getByFormKeyAndUserId(bpmFormDef
					.getFormKey(), ContextUtil.getCurrentUserId(), "", "",
					platformType);

			Long tableId = bpmFormDef.getTableId();
			if (tableId.longValue() > 0) {
				BpmFormTable mainTable = bpmFormTableService.getById(tableId);
				if (mainTable != null) {
					name = mainTable.getTableName();
				}
			}
		}
		// 在编辑页面预览
		else {
			html = request.getParameter("html");
			name = RequestUtil.getString(request, "name");
			title = RequestUtil.getString(request, "title");
			comment = RequestUtil.getString(request, "comment");
		}
		ParseReult result = FormUtil.parseHtmlNoTable(html, name, comment);
		String outHtml = bpmFormHandlerService.obtainHtml(title, result,
				permission, true);
		ModelAndView mv = this.getAutoView();
		mv.addObject("html", outHtml);
		return mv;
	}

	/**
	 * 保存表单。
	 * 
	 * <pre>
	 * 	1.新建表单的情况，不发布。
	 * 		1.添加表单定义。
	 * 		2.添加表定义。
	 * 	2.新建表单发，发布。
	 * 		1.添加表单定义。
	 * 		2.添加表定义。
	 *  3.编辑表单，未发布的情况。
	 *  	1.编辑表单定义。
	 *  	2.删除表定义重新添加。
	 *  4.编辑表单，已经发布。
	 *  	1.编辑表单定义
	 *  	2.是否有多个版本，或者已经有数据。
	 *  		1.是只对表进行编辑。
	 *  		2.否则删除表重建。
	 * 
	 * </pre>
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "saveForm", method = RequestMethod.POST)
	@Action(description = "保存表单", detail = "保存表单【${SysAuditLinkService.getBpmFormDefLink(Long.valueOf(formDefId))}】")
	public void saveForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
PrintWriter out= response.getWriter();
		
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		Long categoryId = RequestUtil.getLong(request, "categoryId");
		String formKey = RequestUtil.getString(request, "formKey");
		String subject = RequestUtil.getString(request, "subject");
		String formDesc = RequestUtil.getString(request, "formDesc");
		String tableName = RequestUtil.getString(request, "tableName");
		String tableComment = RequestUtil.getString(request, "tableComment");
		String title = RequestUtil.getString(request, "tabTitle");
		String html = request.getParameter("html");
		int publish = RequestUtil.getInt(request, "publish", 0);
		String json = request.getParameter("json");

		html = html.replace("？", "");
		html = html.replace("<p>﻿</p>", "");

		if (StringUtil.isNotEmpty(json)) {
			// 根据json更新html
			html = FormUtil.changeHtml(html, json, tableName);
		}
		ParseReult result = FormUtil.parseHtmlNoTable(html, tableName,
				tableComment);

		SysUser sysUser = ContextUtil.getCurrentUser();
		String userName = sysUser.getFullname();
		BpmFormDef bpmFormDef = null;
		
		//判断表单别名是否重复。
		ResultMessage resultMessage= isFormkeyExist(formDefId, formKey);
		if(resultMessage.getResult()==ResultMessage.Fail){
			out.print(resultMessage);
			return;
		}
		
		if (formDefId == 0) {
			bpmFormDef = new BpmFormDef();
			bpmFormDef.setCategoryId(categoryId);
			bpmFormDef.setFormKey(formKey);
			
		} else {
			bpmFormDef = service.getById(formDefId);
		}

		Long tableId = bpmFormDef.getTableId();
		
		String message = null;
		
		
		// 输入了主表名。
		// 验证主表名称。
		boolean isTableExist = isTableExists(tableName,tableId);
		
		/*ResultMessage resultMessage = null;
		String message = null;
		Long tableId = bpmFormDef.getTableId();
		boolean isTableExist = false;
		// 输入了主表名。
		// 验证主表名称。
		if (StringUtil.isNotEmpty(tableName)) {
			if (tableId > 0) {
				BpmFormTable bpmFormTable = bpmFormTableService
						.getById(tableId);
				// 输入的表名和原来的表名不一致的情况。
				if (!tableName.equalsIgnoreCase(bpmFormTable.getTableName())) {
					isTableExist = bpmFormTableService
							.isTableNameExisted(tableName);
				}
			} else {
				isTableExist = bpmFormTableService
						.isTableNameExisted(tableName);
			}
		}*/
		/*/if (isTableExist) {
			resultMessage = new ResultMessage(ResultMessage.Fail, "表【"
					+ tableName + "】在数据库中已存在!");
			response.getWriter().print(resultMessage);
			return;
		}*/
		if (isTableExist) {
			resultMessage = new ResultMessage(ResultMessage.Fail, "表【"+ tableName + "】在数据库中已存在!");
			out.print(resultMessage);
			return;
		}

		bpmFormDef.setSubject(subject);
		bpmFormDef.setFormDesc(formDesc);
		bpmFormDef.setTabTitle(title);
		bpmFormDef.setHtml(html);
		bpmFormDef.setTemplate(result.getTemplate());

		bpmFormDef.setPublishedBy(userName);

		message = (publish == 1) ? "表单发布成功!" : "保存表单成功!";

		resultMessage = new ResultMessage(ResultMessage.Success, message);
		try {
			// 是否发布
			boolean isPublish = (publish == 1);
			// 通过解析后的到的表对象。
			BpmFormTable bpmFormTable = result.getBpmFormTable();
			service.saveForm(bpmFormDef, bpmFormTable, isPublish);
		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail, "保存表单失败:"
						+ str);
			} else {
				message = ExceptionUtil.getExceptionMessage(ex);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
			}
		}
		//response.getWriter().print(resultMessage);
		out.print(resultMessage);
	}
	
	private boolean isTableExists(String tableName,Long tableId){
		boolean isTableExist = false;
		if (StringUtil.isNotEmpty(tableName)) {
			if (tableId > 0) {
				BpmFormTable bpmFormTable = bpmFormTableService.getById(tableId);
				// 输入的表名和原来的表名不一致的情况。
				if (!tableName.equalsIgnoreCase(bpmFormTable.getTableName())) {
					isTableExist = bpmFormTableService.isTableNameExisted(tableName);
				}
			} else {
				isTableExist = bpmFormTableService.isTableNameExisted(tableName);
			}
		}
		return isTableExist;
	}
	
	private ResultMessage isFormkeyExist(Long formDefId,String formKey){
		ResultMessage resultMessage = new ResultMessage(ResultMessage.Success, "");
		if(formDefId==0){
			int rtn=service.getCountByFormKey(formKey);
			if(rtn>0){
				resultMessage = new ResultMessage(ResultMessage.Fail, "指定别名已经存在请重新表单别名!");
			}
		}
		return resultMessage;
	}

	
	
	
	
	/**
	 * 取出json中主表和子表的所有字段添加到JSONArray中。
	 * 
	 * @param json
	 */
	private JSONArray parseJsonToJSONArray(String json) {
		JSONArray allRows = new JSONArray();
		JSONObject jsonobject = JSONObject.fromObject(json);
		JSONArray mainRows = jsonobject.getJSONArray("rows");
		for (int i = 0; i < mainRows.size(); i++) {
			JSONObject object = JSONObject.fromObject(mainRows.get(i)
					.toString());
			allRows.add(object);
		}
		JSONArray subTables = jsonobject.getJSONArray("subTable");
		for (int i = 0; i < subTables.size(); i++) {
			JSONObject subobject = JSONObject.fromObject(subTables.get(i)
					.toString());
			JSONArray subRows = subobject.getJSONArray("rows");
			for (int j = 0; j < subRows.size(); j++) {
				String objs = subRows.get(j).toString();
				JSONObject object = JSONObject.fromObject(objs);
				allRows.add(object);
			}
		}

		return allRows;
	}

	/**
	 * 复制表单（克隆表单）
	 * 
	 * <pre>
	 * 1、获取要复制的表单对象；
	 * 2、重新生成表单ID（formDefId）、表单key（formKey）；
	 * 3、打开复制表单的设置页面，让用户设置重新设置『表单标题』、『表单分类』、『表单描述』；
	 * 4、保存以后，复制的表单和原表单使用同样的表，表可以添加字段和修改字段（修改的字段不能修改字段名和类型）。
	 * </pre>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("copy")
	@Action(description = "复制表单")
	public ModelAndView copy(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long formDefId = RequestUtil.getLong(request, "formDefId", 0);
		BpmFormDef bpmFormDef = null;

		if (formDefId > 0) {
			bpmFormDef = service.getById(formDefId);
		}
		Long cateId = bpmFormDef.getCategoryId();
		if (cateId != null && cateId != 0) {
			GlobalType globalType = globalTypeService.getById(bpmFormDef
					.getCategoryId());
			bpmFormDef.setCategoryName(globalType.getTypeName());
		}
		ModelAndView mv = this.getAutoView();
		mv.addObject("bpmFormDef", bpmFormDef);
		return mv;
	}

	/**
	 * 保存克隆
	 * 
	 * @param request
	 * @param response
	 * @param po
	 * @throws Exception
	 */
	@RequestMapping("saveCopy")
	@Action(description = "保存克隆", detail = "保存表单【${SysAuditLinkService.getBpmFormDefLink(Long.valueOf(formDefId))}】的克隆")
	public void saveCopy(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		long formDefId = RequestUtil.getLong(request, "formDefId");
		String formName = RequestUtil.getString(request, "formName");
		
		String formKey = RequestUtil.getString(request, "formKey");
		
		Long typeId = RequestUtil.getLong(request, "typeId");
		String formDesc = RequestUtil.getString(request, "formDesc");

		BpmFormDef bpmFormDef = service.getById(formDefId);
		//long oldFormKey = bpmFormDef.getFormKey();
		String oldFormKey = bpmFormDef.getFormKey();
		if (bpmFormDef != null) {
			if (!StringUtil.isEmpty(formName)) {
				bpmFormDef.setSubject(formName);
			}
			if (typeId > 0) {
				bpmFormDef.setCategoryId(typeId);
			}
			if (!StringUtil.isEmpty(formDesc)) {
				bpmFormDef.setFormDesc(formDesc);
			}
			long id = UniqueIdUtil.genId();
			bpmFormDef.setFormDefId(id);
			//bpmFormDef.setFormKey(id);
			bpmFormDef.setFormKey(formKey);
			bpmFormDef.setIsPublished((short) 0);
			bpmFormDef.setIsDefault((short) 1);
			bpmFormDef.setVersionNo(1);
			try {
				service.copyForm(bpmFormDef, oldFormKey);
				writeResultMessage(writer, "复制表单成功!", ResultMessage.Success);
			} catch (Exception ex) {
				String str = MessageUtil.getMessage();
				if (StringUtil.isNotEmpty(str)) {
					ResultMessage resultMessage = new ResultMessage(
							ResultMessage.Fail, "复制表单失败:" + str);
					response.getWriter().print(resultMessage);
				} else {
					String message = ExceptionUtil.getExceptionMessage(ex);
					ResultMessage resultMessage = new ResultMessage(
							ResultMessage.Fail, message);
					response.getWriter().print(resultMessage);
				}
			}
		} else {
			writeResultMessage(writer, "未能获取到要复制的表单", ResultMessage.Fail);
			return;
		}
	}

	/**
	 * 导入表单源文件
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("importForm")
	@Action(description = "导入表单源文件")
	public void importForm(MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MultipartFile fileLoad = request.getFile("importInput");
		String str = FileUtil.inputStream2String(fileLoad.getInputStream());
		response.getWriter().print(str);
	}

	/**
	 * 导出表单源文件
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportForm")
	@Action(description = "导出表单")
	public void exportForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String title = RequestUtil.getString(request, "title");
		String html = request.getParameter("html");
		if (StringUtil.isNotEmpty(title)) {
			html += "#content-title#" + title;
		}
		String subject = request.getParameter("subject") + ".html";
		response.setContentType("APPLICATION/OCTET-STREAM");
		String filedisplay = StringUtil.encodingString(subject, "GBK",
				"ISO-8859-1");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ filedisplay);
		response.getWriter().write(html);
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 初始化表单权限设置
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("initRights")
	@Action(description = "初始化表单权限设置", detail = "初始化表单【${SysAuditLinkService.getBpmFormDefLink(formkey)}】权限设置")
	public ResultMessage initRights(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResultMessage resultMessage = null;
		PlatformType platformType = PlatformType.getEnumFromString(RequestUtil
				.getString(request, "platform", "PC"));
		try {
			//Long formKey = RequestUtil.getLong(request, "formKey", 0);
			String formKey = RequestUtil.getString(request, "formKey");
			
			String actDefId = RequestUtil.getString(request, "actDefId");
			String nodeId = RequestUtil.getString(request, "nodeId");
			String parentActDefId = RequestUtil.getString(request,
					"parentActDefId", "");
			if (StringUtil.isEmpty(parentActDefId)) {
				bpmFormRightsService.deleteRight(formKey, actDefId, nodeId,
						platformType);
			} else {
				bpmFormRightsService.deleteRight(formKey, actDefId, nodeId,
						parentActDefId, platformType);
			}
			resultMessage = new ResultMessage(ResultMessage.Success,
					"重置表单权限设置成功!");
		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,
						"重置表单权限设置失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
		return resultMessage;
	}

	/**
	 * 判断是否有子表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("isSubTable")
	public String isSubTable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StringBuffer sb = new StringBuffer();
		
		/*Long formKey = RequestUtil.getLong(request, "formKey", 0);
		BpmFormDef bpmFormDef = service.getById(formKey);*/
		String formKey = RequestUtil.getString(request, "formKey");
		BpmFormDef bpmFormDef = service.getDefaultPublishedByFormKey(formKey);
		
		if (BeanUtils.isNotEmpty(bpmFormDef)
				&& BeanUtils.isNotEmpty(bpmFormDef.getTableId())) {
			List<BpmFormTable> list = bpmFormTableService
					.getSubTableByMainTableId(bpmFormDef.getTableId());
			if (BeanUtils.isNotEmpty(list)) {
				sb.append("{success:true,tableId:").append(
						bpmFormDef.getTableId()).append("}");
			} else {
				sb.append("{success:false,msg:'该表单没有子表,不需要设置子表数据权限！'}");
			}
		} else {
			sb.append("{success:false,msg:'未获得表单信息！'}");
		}

		return sb.toString();
	}

	/**
	 * 流程表单子表授权
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("subRightsDialog")
	@Action(description = "流程表单子表授权")
	public ModelAndView subRightsDialog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String actDefId = RequestUtil.getString(request, "actDefId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		Long formKey = RequestUtil.getLong(request, "formKey");
		Long tableId = RequestUtil.getLong(request, "tableId");
		String parentActDefId = RequestUtil.getString(request,
				"parentActDefId", "");

		ModelAndView mv = this.getAutoView();
		mv.addObject("actDefId", actDefId);
		mv.addObject("nodeId", nodeId);
		mv.addObject("formKey", formKey);
		mv.addObject("tableId", tableId);
		mv.addObject("parentActDefId", parentActDefId);
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
	@Action(description = " 导出xml窗口")
	public ModelAndView export(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String formDefIds = RequestUtil.getString(request, "formDefIds");

		ModelAndView mv = this.getAutoView();
		mv.addObject("formDefIds", formDefIds);
		return mv;
	}

	/**
	 * 导出自定义表单XML
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportXml")
	@Action(description = "导出自定义表单XML", execOrder = ActionExecOrder.AFTER, detail = "导出自定义表单:"
			+ "<#list StringUtils.split(formDefIds,\",\") as item>"
			+ "<#assign entity=bpmFormDefService.getById(Long.valueOf(item))/>"
			+ "【${entity.subject}】" + "</#list>")
	public void exportXml(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long[] formDefIds = RequestUtil.getLongAryByStr(request, "formDefIds");

		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("bpmFormDef", true);
		map
				.put("bpmFormTable", RequestUtil.getBoolean(request,
						"bpmFormTable"));
		map.put("bpmFormDefOther", RequestUtil.getBoolean(request,
				"bpmFormDefOther"));
		map.put("bpmFormRights", RequestUtil.getBoolean(request,
				"bpmFormRights"));
		map.put("bpmTableTemplate", RequestUtil.getBoolean(request,
				"bpmTableTemplate"));

		if (BeanUtils.isEmpty(formDefIds))
			return;
		try {
			String fileName = DateFormatUtil.getNowByString("yyyyMMddHHmmdd");
			if (formDefIds.length == 1) {
				BpmFormDef formDef = service.getById(formDefIds[0]);
				fileName = formDef.getSubject() + "_" + fileName;
			} else
				fileName = "多条表单记录_" + fileName;

			String strXml = service.exportXml(formDefIds, map);
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ StringUtil.encodingString(fileName, "GBK", "ISO-8859-1")
					+ ".xml");
			response.getWriter().write(strXml);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 导入自定义表单的XML。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("importXml")
	@Action(description = "导入自定义表单")
	public void importXml(MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MultipartFile fileLoad = request.getFile("xmlFile");
		ResultMessage message = null;
		try {
			service.importXml(fileLoad.getInputStream());
			message = new ResultMessage(ResultMessage.Success, MsgUtil
					.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			message = new ResultMessage(ResultMessage.Fail,
					"导入出错了，请检查导入格式是否正确或者导入的数据是否有问题！");
		}
		writeResultMessage(response.getWriter(), message);
	}

	@RequestMapping("setCategory")
	@Action(description = "设置分类", detail = "设置表单"
			+ "<#list StringUtils.split(formKeys,\",\") as item>"
			+ " 【${SysAuditLinkService.getBpmFormDefLink(item)}】 "
			+ "</#list>"
			+ "的分类为"
			+ "${SysAuditLinkService.getGlobalTypeLink(Long.valueOf(categoryId))}")
	public void setCategory(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		Long categoryId = RequestUtil.getLong(request, "categoryId", 0);
		String formKeys = RequestUtil.getString(request, "formKeys");
		String[] aryFormKey = formKeys.split(",");

		if (categoryId == 0L) {
			// writer
			writeResultMessage(
					writer,
					new ResultMessage(
							ResultMessage.Fail,
							ContextUtil
									.getMessages("controller.bpmFormDef.setCategory.notCategoryId")));
			return;
		}

		if (StringUtil.isEmpty(formKeys)) {
			writeResultMessage(
					writer,
					new ResultMessage(
							ResultMessage.Fail,
							ContextUtil
									.getMessages("controller.bpmFormDef.setCategory.notSelectForm")));
			return;
		}

		List<String> list = new ArrayList<String>();

		for (String formKey : aryFormKey) {
			list.add(formKey);
		}
		try {
			service.updCategory(categoryId, list);
			writeResultMessage(
					writer,
					new ResultMessage(
							ResultMessage.Success,
							ContextUtil
									.getMessages("controller.bpmFormDef.setCategory.success")));
		} catch (Exception ex) {
			String msg = ExceptionUtil.getExceptionMessage(ex);
			writeResultMessage(writer, new ResultMessage(ResultMessage.Fail,
					msg));
		}

	}

	
	//***********参考表单************
	@RequestMapping("setCategory1")
	@Action(description = "设置分类" , 
			detail="设置表单" +
					"<#list StringUtils.split(formKeys,\",\") as item>" +
					" 【${SysAuditLinkService.getBpmFormDefLink(item)}】 "+
					"</#list>" +
					"的分类为" +
					"${SysAuditLinkService.getGlobalTypeLink(Long.valueOf(categoryId))}")
	public void setCategory1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		Long categoryId = RequestUtil.getLong(request, "categoryId", 0);
		String formKeys = RequestUtil.getString(request, "formKeys");
		String[] aryFormKey = formKeys.split(",");

		if (categoryId == 0L) {
			// writer
			writeResultMessage(writer, new ResultMessage(ResultMessage.Fail,
					ContextUtil.getMessages("controller.bpmFormDef.setCategory.notCategoryId")));
			return;
		}
		
		if (StringUtil.isEmpty(formKeys)) {
			writeResultMessage(writer, new ResultMessage(ResultMessage.Fail,
					  ContextUtil.getMessages("controller.bpmFormDef.setCategory.notSelectForm")));
			return;
		}
		
		//List<Long> list = new ArrayList<Long>();
		List<String> list = new ArrayList<String>();
		
		for (String formKey : aryFormKey) {
			//list.add(Long.parseLong(formKey));
			list.add(formKey);
		}
		try {
			service.updCategory(categoryId, list);
			writeResultMessage(writer, new ResultMessage(ResultMessage.Success,
					  ContextUtil.getMessages("controller.bpmFormDef.setCategory.success")));
		} catch (Exception ex) {
			String msg = ExceptionUtil.getExceptionMessage(ex);
			writeResultMessage(writer, new ResultMessage(ResultMessage.Fail,
					msg));
		}
		
	}

	/**
	 * 获得模板与表的对应关系
	 * 
	 * @param templateTablesId
	 * @param templateAlias
	 * @return
	 */
	private String getTemplateId(Long[] templateTablesId, String[] templateAlias) {
		StringBuffer sb = new StringBuffer();
		if (BeanUtils.isEmpty(templateTablesId)
				|| BeanUtils.isEmpty(templateAlias))
			return sb.toString();
		for (int i = 0; i < templateTablesId.length; i++) {
			for (int j = 0; j < templateAlias.length; j++) {
				if (i == j) {
					sb.append(templateTablesId[i]).append(",").append(
							templateAlias[j]);
					break;
				}
			}
			sb.append(";");
		}
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		
		return sb.toString();
	}

	/**
	 * 根据表和指定的html生成表单。
	 * 
	 * @param tableIds
	 * @param templateAlias
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	private String genTemplate(Long[] tableIds, String[] templateAlias)
			throws TemplateException, IOException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tableIds.length; i++) {
			// 表
			Map<String, Object> fieldsMap = new HashMap<String, Object>();
			BpmFormTable table = bpmFormTableService.getById(tableIds[i]);

			List<BpmFormField> fields = bpmFormFieldService.getByTableId(tableIds[i]);
									
			//删除主键外键字段。
			removeField(table,fields);

			fieldsMap.put("table", table);
			fieldsMap.put("fields", fields);
		
		
			// 设置主表和子表分组
			this.setTeamFields(fieldsMap, table, fields);

			this.setBpmFormFieldName(table, fields);
			// 模板
			BpmFormTemplate tableTemplate = bpmFormTemplateService
					.getByTemplateAlias(templateAlias[i]);
			BpmFormTemplate macroTemplate = bpmFormTemplateService
					.getByTemplateAlias(tableTemplate.getMacroTemplateAlias());
			String macroHtml = "";
			if (macroTemplate != null) {
				macroHtml = macroTemplate.getHtml(); 
			}
			
			String result = freemarkEngine.parseByStringTemplate(fieldsMap,
					macroHtml + tableTemplate.getHtml());
		//System.out.println(macroHtml + tableTemplate.getHtml());
			if (table.getIsMain() == 1) {
				sb.append(result);
			} else {
				sb.append("<div type=\"subtable\" tableName=\"");
				sb.append(table.getTableName());
				sb.append("\">\n");
				sb.append(result);
				sb.append("</div>\n");
			}
		}
		
		return sb.toString();
	}

	
	/**
	 * 根据数据包和指定的html生成表单。
	 * 
	 * @param tableIds
	 * @param templateAlias
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	private String genParcelTemplate(Long[] tableIds, String templateAlias)
			throws TemplateException, IOException {
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tableIds.length; i++) {
			// 表
			Map<String, Object> fieldsMap = new HashMap<String, Object>();
		
			List<TableParcel> tp=tableParcelService.getAll();
			TableParcel q=tableParcelService.getById(tableIds[i]);			
			String tp2=q.getParcel_value();
			String table_name=q.getTable_name();
			
			Long table_id=bpmFormTableService.getByTableName(table_name).getTableId();		
			BpmFormTable table = bpmFormTableService.getById(table_id);
			List<BpmFormField> fields = bpmFormFieldService.getByTableId(table_id);
			List<BpmFormField> fields2 = new ArrayList<BpmFormField>();
			ArrayList<String> list = new ArrayList<String>();
			JSONArray jsonArr = JSONArray.fromObject(tp2);
			Iterator<JSONArray> itr = jsonArr.iterator(); 
			while(itr.hasNext()) { 
				String []a=null;
				JSONObject temp = JSONObject.fromObject(itr.next());			
				list.add("fieldName="+temp.get("field"));										    
			}
			
			for(int j=0;j<fields.size();j++)
			{
				String[] st=fields.get(j).toString().split("\\[");
			    String split=st[1].substring(0, st[1].indexOf("]"));
			  
			    for(int k=0;k<list.size();k++)
				{if(list.get(k).equals(split))
				{
					fields2.add(fields.get(j));
				}}
			}			
			//删除主键外键字段。
			

			fieldsMap.put("table", table);
			fieldsMap.put("fields", fields2);
		
			Iterator it = fieldsMap.entrySet().iterator();
			// 设置主表和子表分组
			this.setTeamFields(fieldsMap, table, fields2);

			this.setBpmFormFieldName(table, fields2);
			// 模板
			BpmFormTemplate tableTemplate = bpmFormTemplateService.getByTemplateAlias(templateAlias);
			BpmFormTemplate macroTemplate = bpmFormTemplateService.getByTemplateAlias(tableTemplate.getMacroTemplateAlias());
			String macroHtml = "";
			if (macroTemplate != null) {
				macroHtml = macroTemplate.getHtml(); 
			}
			
			String result = freemarkEngine.parseByStringTemplate(fieldsMap,
					macroHtml + tableTemplate.getHtml());
	
			if (table.getIsMain() == 1) {
				sb.append(result);
			} else {
				sb.append("<div type=\"subtable\" tableName=\"");
				sb.append(table.getTableName());
				sb.append("\">\n");
				sb.append(result);
				sb.append("</div>\n");
			}
		}
	
		return sb.toString();
	}
	/**
	 * 删除外部表的主键外键字段。
	 * 
	 * @param table
	 * @param fields
	 */
	private void removeField(BpmFormTable table, List<BpmFormField> fields) {
		if (!table.isExtTable())
			return;
		String pk = table.getPkField();
		String fk = table.getRelation();
		for (Iterator<BpmFormField> it = fields.iterator(); it.hasNext();) {
			BpmFormField field = it.next();
			if (field.getFieldName().equalsIgnoreCase(fk)) {
				it.remove();
				continue;
			}
			if (field.getFieldName().equalsIgnoreCase(pk)) {
				it.remove();
			}
		}
	}

	/**
	 * 设置分组字段
	 * 
	 * @param fieldsMap
	 * @param table
	 * @param fields
	 * @param fieldList
	 * @return
	 */
	private List<TeamModel> setTeamFields(Map<String, Object> fieldsMap,
			BpmFormTable table, List<BpmFormField> fields) {
		if (StringUtil.isEmpty(table.getTeam()))
			return null;
		List<TeamModel> list = new ArrayList<TeamModel>();
		JSONObject json = JSONObject.fromObject(table.getTeam());
		fieldsMap.put("isShow", json.get("isShow"));
		fieldsMap.put("showPosition", json.get("showPosition"));

		JSONArray teamJson = JSONArray.fromObject(json.get("team"));
		for (Object obj : teamJson) {
			TeamModel teamModel = new TeamModel();
			JSONObject jsonObj = (JSONObject) obj;
			String teamName = (String) jsonObj.get("teamName");
			teamModel.setTeamName(teamName);
			// 获取字段
			JSONArray jArray = (JSONArray) jsonObj.get("teamField");
			List<BpmFormField> teamFields = new ArrayList<BpmFormField>();
			for (Object object : jArray) {
				JSONObject fieldObj = (JSONObject) object;
				String fieldName = (String) fieldObj.get("fieldName");
				BpmFormField bpmFormField = this
						.getTeamField(fields, fieldName);
				if (BeanUtils.isNotEmpty(bpmFormField)) {
					fields.remove(bpmFormField);
					teamFields.add(bpmFormField);
				}
			}
			this.setBpmFormFieldName(table, teamFields);
			teamModel.setTeamFields(teamFields);
			list.add(teamModel);
		}
		fieldsMap.put("teamFields", list);
		return list;
	}

	/**
	 * 设置字段名字
	 * 
	 * @param table
	 * @param fields
	 */
	private void setBpmFormFieldName(BpmFormTable table,
			List<BpmFormField> fields) {
		for (BpmFormField field : fields) {
			field
					.setFieldName((table.getIsMain().shortValue() == BpmFormTable.IS_MAIN
							.shortValue() ? "m:" : "s:")
							+ table.getTableName() + ":" + field.getFieldName());
		}

	}

	/**
	 * 获取分组字段
	 * 
	 * @param fields
	 * @param fieldName
	 * @return
	 */
	private BpmFormField getTeamField(List<BpmFormField> fields,
			String fieldName) {
		for (BpmFormField bpmFormField : fields) {
			if (bpmFormField.getFieldName().equals(fieldName))
				return bpmFormField;
		}
		return null;
	}

	/**
	 * 根据表Id和模版Id获取所有的控件定义。
	 * 
	 * @param request
	 * @param response
	 * @param templateAlias
	 *            表单模板别名
	 * @param tableId
	 *            自定义表Id
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("getControls")
	@Action(description = "获取表单控件")
	public Map<String, String> getControls(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "templateAlias") String templateAlias,
			@RequestParam(value = "tableId") Long tableId)
			throws TemplateException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		BpmFormTemplate template = bpmFormTemplateService
				.getByTemplateAlias(templateAlias);
		if (template != null) {
			template = bpmFormTemplateService.getByTemplateAlias(template
					.getMacroTemplateAlias());
			String macro = template.getHtml();
			BpmFormTable table = bpmFormTableService.getById(tableId);
			List<BpmFormField> fields = bpmFormFieldService
					.getByTableId(tableId);
			for (BpmFormField field : fields) {
				String fieldname = field.getFieldName();
				// 字段命名规则
				// 表类型(m:主表，s:子表) +":" + 表名 +“：” + 字段名称
				field.setFieldName((table.getIsMain() == 1 ? "m:" : "s:")
						+ table.getTableName() + ":" + field.getFieldName());
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("field", field);
				map.put(fieldname, freemarkEngine.parseByStringTemplate(data,
						macro + "<@input field=field/>"));
			}
		}
		return map;
	}

	/**
	 * OFFICE控件菜单权限设置页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 * @exception
	 * @since 1.0.0
	 */
	@Action(description = "OFFICE控件菜单权限设置页面")
	public ModelAndView selectOfficeMenu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();
		return mv;
	}

	/**
	 * Ajax取回数据库表名
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getTableName")
	public String getTableName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String formKey = RequestUtil.getString(request,"formKey");
		if (formKey != null) {
			String result = "";
			JdbcTemplate jdbcTemplate = (JdbcTemplate) AppUtil
					.getBean("jdbcTemplate");
			result = jdbcTemplate
					.queryForObject(
							"select SUBJECT from bpm_form_def where FORMDEFID =(select FORMDEFID from bpm_form_def where FORMKEY = '"
									+ formKey + "' )", String.class);
			System.out.println("Ajax Return text: " + result);
			return result;
		} else {
			String b = "获取表名失败，请重试！";
			return b;
		}
	}
	/**
	 * 根据表的Id返回表和字段对象，只获取全局表单和主表的字段
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("fieldDialog")
	public ModelAndView fieldDialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String formKey = RequestUtil.getString(request, "formKey");
		BpmFormDef bpmFormDef = service.getDefaultPublishedByFormKey(formKey);
		Long tableId = bpmFormDef.getTableId();
		List<BpmFormField> fieldList = bpmFormFieldService.getByTableIdContainHidden(tableId);
		return getAutoView().addObject("bpmFormFieldList", fieldList);
	}
	
	/**
	 * 根据formKey查询是否存在别名。
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getCountByFormKey")
	@ResponseBody
	public Integer getCountByFormKey(HttpServletRequest request, HttpServletResponse response){
		String formKey=RequestUtil.getString(request, "formKey");
		return service.getCountByFormKey(formKey);
	}
	// 导入功能那晓旭
	@RequestMapping("daoru")
	@Action(description = "导入")
	public ModelAndView upload(
			@RequestParam("formid") String formid,
			@RequestParam(value = "file", required = false) CommonsMultipartFile file,
			HttpServletResponse response, HttpServletRequest request,
			ModelMap model) throws Exception {

		System.out.println("开始");
		System.out.println(formid);
		String path = request.getSession().getServletContext().getRealPath(
				"upload");
		System.out.println("path" + path);
		String fileName = "temp.jsp";

		System.out.println(path);
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("fileUrl", request.getContextPath() + "/upload/"
				+ fileName);
		// 存放临时上传html 文件的路径
		String filetemp = "C:/Tomcat 6.0/webapps/mas/upload/temp.jsp";
		String html = readFile(filetemp);
		// System.out.println(html);
		//long formidl = Long.parseLong(formid); // 把formid转换成Long 型
		long formidl = Long.parseLong("10000058590160");
		readHtml r = new readHtml();
		getName getn = new getName();
		getValue getValue = new getValue();
		// deleteValue delValue=new deleteValue();

		ArrayList<String> al = r.read(html);
		ArrayList<String> names = getn.getNames(al);
		for (int i = 0; i < names.size(); i++) {
			System.out.println("names" + names.get(i));
		}
		ArrayList<String> values = getValue.getValues(al);
		System.out.println(html);
		for (int i = 0; i < values.size(); i++) {
			System.out.println("values" + values.get(i));
			html=html.replace(values.get(i),"");
		}
		
		// delValue.delete(html);

		int beginIx = 0;
		
		// 如果有<input进入
		/*while (html.indexOf("<input", beginIx) != -1) {
			// 找到<input的位置
			beginIx = html.indexOf("<input", beginIx);
			beginIx = html.indexOf("type=\"", beginIx);
			beginIx = html.indexOf("\"", beginIx);
			int endIx2 = html.indexOf("\"", beginIx + 1);
			String temp2 = html.substring(beginIx + 1, endIx2);
			System.out.println("temp2" + temp2);
			//如果tpye的  类型是text则删除 value里值 
			if (temp2.equals("text")) {

				// 如果没有找到value这一项直接在<input位置+1
				if (html.indexOf("value=", beginIx) == -1) {
					beginIx = html.indexOf("<input", beginIx) + 1;

				} else {// // 找到value=的位置找到了将其删除
					if (html.indexOf("value=\"", beginIx) == -1)// 如果有value="
					{
						beginIx = html.indexOf("value=", beginIx);
						int endIx = html.indexOf("}", beginIx);
						String temp = html.substring(beginIx, endIx);

						System.out.println("获取出value值" + temp);
						// 将value值清空重新赋值给html
						html = html.replace(temp, "");
						
						
					} else // 只有value=
					{
						beginIx = html.indexOf("value=", beginIx);

						// 找到value值开始的位置
						beginIx = html.indexOf("\"", beginIx);
						System.out.println("找到value值开始的位置 " + beginIx);
						// 找到value值结束的位置
						int endIx = html.indexOf("\"", beginIx + 1);
						System.out.println("找到value值结束的位置 " + endIx);
						// 获取出value值
						String temp = html.substring(beginIx + 1, endIx);

						System.out.println("获取出value值" + temp);
						System.out.println("beginIx:" + beginIx + "endIx:"
								+ endIx + "temp" + temp);
						// 将value值清空重新赋值给html
						html = html.replace(temp, "");
						
					}

				}
			}

		}
*/
		beginIx = 0;
		// 如果有<%@进入
		while (html.indexOf("<%@", beginIx) != -1) {
			// 找到<input的位置
			beginIx = html.indexOf("<%@", beginIx);
			System.out.println("<%@page" + beginIx);

			int endIx = html.indexOf(">", beginIx + 1);
			System.out.println("找到<%@page值结束的位置 " + endIx);
			// 获取出%@page值
			String temp = html.substring(beginIx, endIx + 1);
			System.out.println("获取出<%@page值" + temp);
			System.out.println("beginIx:" + beginIx + "endIx:" + endIx + "temp"
					+ temp);
			// 将%@page值清空重新赋值给html
			html = html.replace(temp, "");

		}

		System.out.println(html);

		BpmFormDef bpmFormDef = service.getById(formidl);
		bpmFormDef.setHtml(html);
		// 如果这个formDefId没有存在，则直接插入进去 新的数据
		if (newjsprelationService.getFormDefIdByFormDefId(formidl) != null) {

			newjsprelationService.updatenameandvalueByFormDefId(formidl, names,
					values);
		} else// 如果已经存在，则更新nameandvalue
		{
			service.addjspnameandvalue(formidl, names, values);
		}

		ModelAndView mv = new ModelAndView("/platform/form/bpmFormDefEdit.jsp")
				.addObject("bpmFormDef", bpmFormDef);
		return mv;

	}

	// 获取name的值
	public class getName {

		ArrayList<String> getNames(ArrayList<String> a) {
			ArrayList<String> names = new ArrayList<String>();
			String buf = null;
			String result = "";

			for (String s : a) {
				buf = s;
				int beginIx = buf.indexOf("name=\"");
				if (beginIx > 0) {
					buf = buf.substring(beginIx);
					buf = buf.replaceAll("name=\"", "");
					int endIx = buf.indexOf("\"");
					if (endIx > 0) {
						result = buf.substring(0, endIx);

						System.out.println(result);
						names.add(result);
					}

				}
			}
			return names;

		}
	}

	// 获取value的值
	public class getValue {

		ArrayList<String> getValues(ArrayList<String> a) {
			ArrayList<String> values = new ArrayList<String>();
			String buf = null;
			String result = "";

			for (String s : a) {
				buf = s;
				int beginIx = buf.indexOf("name=\"");
				if (beginIx > 0) {
					buf = buf.substring(beginIx);
					buf = buf.replaceAll("name=\"", "");
					int endIx = buf.indexOf("\"");
					if (buf.indexOf("value=", endIx) != -1)// 如果有value
					{
						beginIx = buf.indexOf("value=\"", endIx);
						if (beginIx != -1)// 如果name后面有value=“
						{
							buf = buf.substring(beginIx);
							buf = buf.replaceAll("value=\"", "");
							endIx = buf.indexOf("\"");
							if (endIx > 0) {
								result = buf.substring(0, endIx);
								values.add(result);
								System.out.println(result);
							}
						} else // 只有value=
						{
							beginIx = buf.indexOf("value=", endIx);
							buf = buf.substring(beginIx);
							buf = buf.replaceAll("value=", "");
							endIx = buf.indexOf("}");
							if (endIx > 0) {
								result = buf.substring(0, endIx + 1);
								values.add(result);
								System.out.println(result);
							}

						}

					}

				}
			}
			return values;

		}
	}

	// 将html解析成以行为单位，每一行都是以"<input"开头
	public class readHtml {
		public ArrayList<String> read(String Html) {

			BufferedReader reader = null;
			StringBuffer buff = new StringBuffer();
			try {
				System.out.println("以行为单位读取文件内容，一次读一整行：");
				reader = new BufferedReader(new StringReader(Html));
				String tempString = null;
				int line = 1;

				String rs = null;
				ArrayList<String> inputList = new ArrayList<String>();
				while ((tempString = reader.readLine()) != null) {
					rs = getHref(tempString);

					if (rs != null) {
						inputList.add(rs);
						System.out.println(rs);
					}

					// buff.append(tempString);
					line++;
				}

				return inputList;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
					}
				}
			}
			return null;

		}

		private String getHref(String str) {
			// 将给定的正则表达式编译到模式中
			Pattern pattern = Pattern.compile("<input.*>");
			// 创建匹配给定输入与此模式的匹配器
			Matcher matcher = pattern.matcher(str);
			if (matcher.find())// 尝试查找与该模式匹配的输入序列的下一个子序列
				return matcher.group(0);
			return null;
		}
	}

	// 把文件读成流
	public static void readToBuffer(StringBuffer buffer, String filePath)
			throws IOException {
		InputStream is = new FileInputStream(filePath);
		String line; // 用来保存每行读取的内容
		BufferedReader reader = new BufferedReader(new InputStreamReader((is),
				"utf-8"));
		line = reader.readLine(); // 读取第一行
		while (line != null) { // 如果 line 为空说明读完了
			buffer.append(line); // 将读到的内容添加到 buffer 中
			buffer.append("\n"); // 添加换行符
			line = reader.readLine(); // 读取下一行
		}
		reader.close();
		is.close();
	}

	/**
	 * 读取文本文件内容
	 * 
	 * @param filePath
	 *            文件所在路径
	 * @return 文本内容
	 * @throws IOException
	 *             异常
	 * @author cn.outofmemory
	 * @date 2013-1-7
	 */
	public static String readFile(String filePath) throws IOException {
		StringBuffer sb = new StringBuffer();
		BpmFormDefController.readToBuffer(sb, filePath);
		return sb.toString();
	}
	
	@RequestMapping("gatherInfoys")
	@Action(description = "收集信息")
	public ModelAndView gatherInfoys(HttpServletRequest request,
	HttpServletResponse response,
	@RequestParam(value = "page", defaultValue = "1") int page)
	throws Exception {
		long categoryId = RequestUtil.getLong(request, "categoryId");
		String fDefId = request.getParameter("fDefId");
		Long  formDefId= Long.valueOf(fDefId);
		String subject = RequestUtil.getString(request, "subject");
		String formDesc = RequestUtil.getString(request, "formDesc");
		int designType = RequestUtil.getInt(request, "designType",3);
		return this.getAutoView()
			.addObject("categoryId", categoryId)
			.addObject("formDefId", formDefId)
			.addObject("subject",subject)
			.addObject("formDesc",formDesc)
			.addObject("designType",designType);
	}
	
	//操作图编辑表单
	@RequestMapping("edit2")
	@Action(description = "编辑自定义表单")
	public ModelAndView edit2(HttpServletRequest request) throws Exception {
		ModelAndView mv = getAutoView();
		String fDefId=request.getParameter("formDefId");
		Long formDefId=Long.valueOf(fDefId);
		String returnUrl = RequestUtil.getPrePage(request);
		BpmFormDef bpmFormDef = null;
		bpmFormDef = service.getById(formDefId);
		bpmFormDef.setTableId(RequestUtil.getLong(request, "tableId"));
		Long btableId=bpmFormDef.getTableId();
		bpmFormDef.setCategoryId(RequestUtil.getLong(request, "categoryId"));
		bpmFormDef.setFormDesc(RequestUtil.getString(request, "formDesc"));
		bpmFormDef.setSubject(RequestUtil.getString(request, "subject"));
		bpmFormDef.setFormKey(RequestUtil.getString(request, "formKey"));
		Long[] templateTableId = RequestUtil.getLongAryByStr(request,"templateTableId");
		String[] templateAlias = RequestUtil.getStringAryByStr(request,"templateAlias");
		String templatesId = getTemplateId(templateTableId, templateAlias);
		String reult = this.genTemplate(templateTableId, templateAlias);
		bpmFormDef.setHtml(reult);
		bpmFormDef.setTemplatesId(templatesId);
		mv.addObject("bpmFormDef", bpmFormDef)
		  .addObject("returnUrl", returnUrl);

		return mv;
	}
	
	/**
	/**
	* 操作图选择模板
	* 
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping("selectTemplateczt")
	@Action(description = "选择模板")
	public ModelAndView selectTemplateczt(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();
		Long formDefId = RequestUtil.getLong(request, "formDefId");
		String subject = RequestUtil.getString(request, "subject");
		Long categoryId = RequestUtil.getLong(request, "categoryId");
		String formDesc = RequestUtil.getString(request, "formDesc");
		Long tableId = RequestUtil.getLong(request, "tableId");
		int isSimple = RequestUtil.getInt(request, "isSimple", 0);
		String templatesId = RequestUtil.getString(request, "templatesId");
		String formKey = RequestUtil.getString(request, "formKey");
		BpmFormTable table = bpmFormTableService.getById(tableId);

		if (table.getIsMain() == 1) {
			List<BpmFormTable> subTables = bpmFormTableService
				.getSubTableByMainTableId(tableId);
			List<BpmFormTemplate> mainTableTemplates = bpmFormTemplateService
				.getAllMainTableTemplate();
			List<BpmFormTemplate> subTableTemplates = bpmFormTemplateService
				.getAllSubTableTemplate();
			mv.addObject("mainTable", table).addObject("subTables", subTables)
				.addObject("mainTableTemplates", mainTableTemplates)
				.addObject("subTableTemplates", subTableTemplates);

		} else {
			List<BpmFormTable> subTables = new ArrayList<BpmFormTable>();
			subTables.add(table);
			List<BpmFormTemplate> subTableTemplates = bpmFormTemplateService
				.getAllSubTableTemplate();
			mv.addObject("subTables", subTables).addObject("subTableTemplates",
				subTableTemplates);
		}
		mv.addObject("subject", subject).addObject("categoryId", categoryId)
			.addObject("tableId", tableId).addObject("formDesc", formDesc)
			.addObject("isSimple", isSimple)
			.addObject("formKey", formKey)
			.addObject("formDefId", formDefId)
			.addObject("templatesId", templatesId);

		return mv;
	}
	/**
	 * 操作图代码生成
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getTableInfo1")
	@Action(description = "获取对应自定义表信息")
	public List<BpmFormTable> getTableInfo1(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		//从前端获得操作图的defid
		Long DefId=RequestUtil.getLong(request, "formDefId", 0L);
		Long tableId = service.xString(DefId);

		List<BpmFormTable> bpmFormTableList=new ArrayList<BpmFormTable>();
		
		try {
			BpmFormTable bpmFormTable=bpmFormTableService.getById(tableId);
			bpmFormTableList.add(bpmFormTable);
			List<BpmFormTable>subTableList=bpmFormTableService.getSubTableByMainTableId(tableId);
			if(BeanUtils.isNotEmpty(subTableList)){
				bpmFormTableList.addAll(subTableList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bpmFormTableList;
		
	}

	
}