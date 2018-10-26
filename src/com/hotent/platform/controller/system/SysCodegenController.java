package com.hotent.platform.controller.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.ZipUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.dbFunc.model.dbFunc.DbFunc;
import com.hotent.dbFunc.service.dbFunc.DbFuncService;
import com.hotent.dbResult.model.dbResult.DbResult;
import com.hotent.dbResult.service.dbResult.DbResultService;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.model.system.SysCodeTemplate;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.form.FormUtil;
import com.hotent.platform.service.form.IParseHandler;
import com.hotent.platform.service.form.ParseReult;
import com.hotent.platform.service.system.CodeUtil;
import com.hotent.platform.service.system.SysCodeTemplateService;

/**
 * 对象功能:基于自定义表的代码生成器 控制器类 开发公司:广州宏天软件有限公司 开发人员:zyp 创建时间:2012-12-19 15:38:01
 */
@Controller
@RequestMapping("/platform/system/sysCodegen/")
public class SysCodegenController extends BaseController {
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private DbFuncService dbFuncService;
	@Resource
	private SysCodeTemplateService sysCodeTemplateService;
	@Resource
	private BpmFormFieldService bpmFormFieldService;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private BpmFormHandlerService bpmFormHandlerService;
	@Resource
	private FreemarkEngine freemarkEngine;
	
	

	@Resource
	private DbResultService dbResultService;
	@Resource
	private Map<String,IParseHandler> parseHandlerMap;
	
	private static final String BASE_PATH = FileUtil.getRootPath() + File.separator + "codegen"; // 生成代码临时存放位置
	
	
	/**
	 * 获取所有自定义表数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getTableData")
	public List<BpmFormDef> getTableData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String subject = RequestUtil.getString(request, "subject", "");
		List<BpmFormDef> bpmFormDefList = bpmFormDefService.getAllPublished(subject);
		for(BpmFormDef bdf:bpmFormDefList){
			bdf.setHtml("");
			bdf.setTemplate("");
		}
		return bpmFormDefList;
	}

	/**
	 * 代码模板文件列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	public ModelAndView genDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysCodeTemplate> templateList = sysCodeTemplateService.getAll();
		return getAutoView().addObject("templateList", templateList).addObject("flowName", RequestUtil.getString(request, "flowName", "")).addObject("defKey", RequestUtil.getString(request, "defKey", "")).addObject("baseDir", RequestUtil.getString(request, "baseDir", "")).addObject("system", RequestUtil.getString(request, "system", ""));
	}

	/**
	 * 生成代码
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("codegen")
	public void codegen(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 文件相关参数
		String[] templateIds = request.getParameterValues("templateId");//模板Id
		int override = RequestUtil.getInt(request, "override");//扩展HttpServletRequest的功能，所有请求参数获取都通过该类方法来获取。覆盖重写
		String flowKey = RequestUtil.getString(request, "defKey");
		int isZip = RequestUtil.getInt(request, "isZip");
		String folderPath = RequestUtil.getString(request, "folderPath");//文件夹路径
		String[] formDefIds = RequestUtil.getString(request, "formDefIds").split(",");
		// 自定义表相关
		String basePath = RequestUtil.getString(request, "baseDir");//路径
		String system = RequestUtil.getString(request, "system");
		List<String> codeFiles = new ArrayList<String>();//String 类型的列表
		List<BpmFormTable> list = getTableModels(request);//BpmFormTable 自定义表 Model对象  类型的列表  根据前端所配置 获得表信息列表
		try {
			for (String formDefId : formDefIds) {
				BpmFormDef bpmFormDef = bpmFormDefService.getById(Long.parseLong(formDefId));//根据主键表单ID获取对象
				List<BpmFormTable> tables = new ArrayList<BpmFormTable>();//BpmFormTable 类型的列表
				Long tableId = bpmFormDef.getTableId();//返回表单对应的表的TABLEID
				for (BpmFormTable model : list) {
					if (model.getTableId().equals(tableId) || model.getMainTableId().equals(tableId)) {
						tables.add(model);             //model.getMainTableId()是返回 所属主表，该字段仅针对从表
					}
				}
				List<String> fileList = genCode(basePath, system, tables, templateIds, override, flowKey, bpmFormDef);
				codeFiles.addAll(fileList);
			}
			// 压缩生成文件到本地
			if (isZip == 1) {
				String toDir = folderPath + File.separator + "codegen";
				for (String filePath : codeFiles) {
					FileUtil.createFolderFile(toDir + File.separator + filePath);
					FileUtil.copyFile(basePath + File.separator + filePath, toDir + File.separator + filePath);
				}
				ZipUtil.zip(toDir, true);
			}
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Success, "自定义表生成代码成功"));
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Fail, "自定义表生成代码失败:" + e.getMessage()));
		}
	}

//	private List<String> genCode_old(String basePath, String system, List<BpmFormTable> tables, String[] templateIds, int override, String flowKey, BpmFormDef bpmFormDef) throws Exception {
//		List<String> fileList = new ArrayList<String>();
//		for (int i = 0; i < templateIds.length; i++) {
//			Long templateId = Long.parseLong(templateIds[i]);
//			SysCodeTemplate template = sysCodeTemplateService.getById(templateId);
//			for (BpmFormTable table : tables) {
//				
//				
//				Map<String, String> variables = table.getVariable();
//				variables.put("system", system);
//				String fileName = template.getFileName();
//				String fileDir = template.getFileDir();
//				String path = basePath + File.separator + template.getFileDir();
//				
//				int isSub = template.getIsSub();
//				if (table.getIsMain() != 1) {
//					if (isSub == 0) {
//						continue;
//					}
//				}
//				
//				Map<String, Object> model = new HashMap<String, Object>();
//				if (template.getFormEdit() == 1) {
//					// 先解释表单的html
//					String str = getFormHtml(bpmFormDef, table, true);
//					model.put("html", str);
//				}
//				if (template.getFormDetail() == 1) {
//					String str = getFormHtml(bpmFormDef, table, false);
//					model.put("html", str);
//				}
//				
//				//其他参数
//				model.put("table", table);
//				model.put("system", system);
//				if (StringUtil.isNotEmpty(flowKey)) {
//					model.put("flowKey", flowKey);
//				}
//				
//				String templateStr = template.getHtml();
//				FreemarkEngine freemarkEngine = new FreemarkEngine();
//				String html = freemarkEngine.parseByStringTemplate(model, templateStr);
//				String fileStr = path + File.separator + fileName;
//				String filePath = StringUtil.replaceVariable(fileStr, variables);
//				addFile(filePath, html, override);
//				String relativePath = StringUtil.replaceVariable(fileDir + File.separator + fileName, variables);
//				fileList.add(relativePath);
//			}
//		}
//		return fileList;
//	}
	
	//TODO！！！整理一下垃圾代码
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
				//++++++++++++++++++++++++++++++++++生成方法对应的sql语句++++++++++++++++++++++++++++++++++------------start
				/*if(i==0)
				{
				BpmFormTable table1 = tables.get(0);
				
		//	    System.out.println("-----------------"+model.getClass());
			    System.out.println("class:::::"+table1.getVariable().getClass());
			    System.out.println("!!!!!!!!!!!!!!"+table1.getTableName());
			    System.out.println("::::::::::::::::::::"+table1.getFieldList());
			    
			    long a=1 ,b=2,c=3,d=4;
				String ss = "[";
				String ss1 = "";
			    Long id=table1.getTableId();
			    System.out.println("::::::::::::::::::::::::::");
			    String primkey = "主键";

			    for(int ii=0;ii<table1.getFieldList().size();ii++)
			    {
			    	BpmFormField s =table1.getFieldList().get(ii);
			    	
			    	if(ii==table1.getFieldList().size()-1)
			    		
			    	{			    		
			    		ss = ss + "{\"field\":"+ "\""+s.getFieldName()+"\""+ ",\"comment\":"+"\""+s.getFieldDesc()+"\""+"}\"";
			    	}
			    	else  
			    		ss = ss + "{\"field\":"+ "\""+s.getFieldName()+"\""+ ",\"comment\":"+"\""+s.getFieldDesc()+"\""+"}\"";
			    	//[{"field":"ID","comment":""}]
			    
			    }
			    ss = ss.substring(0,
						ss.length() - 1);
			         ss = ss+ "]";
			    System.out.println(ss);
			    System.out.println("::::::::::::::::::::::::::");
	//插入getbyID		    
			    
				DbFunc dbFunc = new DbFunc();				
				dbFunc.setId(id+1);
				dbFunc.setTable_name(table1.getTableName());
				dbFunc.setFunc_type(d);
				dbFunc.setFunc_name("getById");
				dbFunc.setFunc_alias(null);
				dbFunc.setRemarks(null);
				dbFunc.setParameterType("java.lang.Long");				
	 			dbFunc.setResultType(table1.getTableName());  
	 			
				dbFunc.setResultField(ss);    			
				dbFunc.setConditionField("[{\"field\":"+ "\""+"ID"+"\""+ ",\"comment\":"+"\""+primkey+"\""+"}\""+"]\"");
				dbFunc.setSortField("[]");
				dbFunc.setSettingField("[]");
				dbFunc.setDisplayField(ss);
				
				dbFunc.setDsName(table1.getDsName());
				dbFunc.setDs_Alias(table1.getDsAlias());
				dbFunc.setIsTable(a);				
				dbFunc.setIs_Using(null);
				dbFunc.setObjname(table1.getFactTableName());
				dbFuncService.add(dbFunc);
				System.out.println("插入getbyid成功");
	//插入getAll
				DbFunc dbFunc1 = new DbFunc();
				dbFunc1.setId(id);
				dbFunc1.setTable_name(table1.getTableName());
				dbFunc1.setFunc_type(d);
				dbFunc1.setFunc_name("getAll");
				dbFunc1.setFunc_alias(null);
				dbFunc1.setRemarks(null);
				dbFunc1.setParameterType(null);	 
	 			dbFunc1.setResultType(table1.getTableName());    			
				dbFunc1.setResultField(ss); 
			
				
				String mohu1="F_TABLE_NAME  LIKE" + dbFunc1.getTable_name(); 
				String mohu2=" AND F_FUNC_TYPE  =" + dbFunc1.getFunc_type() ;
				String mohu3=" AND F_FUNC_NAME  LIKE " + dbFunc1.getFunc_name(); 
				String mohu4=" AND F_FUNC_ALIAS LIKE " + dbFunc1.getFunc_alias();
				String mohu5=" AND F_REMARKS  ="  + dbFunc1.getRemarks();
				String mohu6=" AND F_PARAMETERTYPE  LIKE "+ dbFunc1.getParameterType();
	    		String mohu7= " AND F_RESULTTYPE  LIKE " + dbFunc1.getResultType();
				String mohu8="  AND F_RESULTFIELD  LIKE "+ dbFunc1.getResultField();
				String mohu9="  AND F_CONDITIONFIELD  LIKE "+dbFunc1.getConditionField();
				String mohu10="  AND F_SORTFIELD  LIKE " +dbFunc1.getSortField();
				String mohu11="  AND F_SETTINGFIELD  LIKE "+dbFunc1.getSettingField();
				String mohu12="  AND F_DISPLAYFIELD  LIKE " + dbFunc1.getDisplayField();
				String mohu13="  AND F_DSNAME  LIKE " + dbFunc1.getDsName();
				String mohu14="  AND F_DS_ALIAS  LIKE "+ dbFunc1.getDs_Alias();
				String mohu15="  AND F_ISTABLE  ="+ dbFunc1.getIsTable();
				String mohu16="  AND F_IS_USING  =" + dbFunc1.getIs_Using();    			
				String mohu=mohu1+mohu2+mohu3+mohu4+mohu5+mohu6+mohu7+mohu8+mohu9+mohu10+mohu11+mohu12+mohu13+mohu14+mohu15+mohu16;   			
//				dbFunc1.setConditionField(mohu);
				dbFunc1.setSortField("[{\"field\":"+ "\""+"ID"+"\""+ ",\"comment\":"+"\""+primkey+"\""+"}\""+"]\"");
				dbFunc1.setSettingField("[]");
				dbFunc1.setDisplayField(ss);
				dbFunc1.setConditionField("[]");
				
				dbFunc1.setDsName(table1.getDsName());
				dbFunc1.setDs_Alias(table1.getDsAlias());
				dbFunc1.setIsTable(a);				
				dbFunc1.setIs_Using(null);
				dbFunc1.setObjname(table1.getFactTableName());
				dbFuncService.add(dbFunc1);
				System.out.println("插入getall成功");
				
	//插入delById
				DbFunc dbFunc2 = new DbFunc();
				dbFunc2.setId(id+2);
				dbFunc2.setTable_name(table1.getTableName());
				dbFunc2.setFunc_type(b);
				dbFunc2.setFunc_name("delById");
				dbFunc2.setFunc_alias(null);
				dbFunc2.setRemarks(null);
				dbFunc2.setParameterType("java.lang.Long");				
	 			dbFunc2.setResultType(null);   
	 			
				dbFunc2.setResultField("[]");    			
				dbFunc2.setConditionField("[{\"field\":"+ "\""+"ID"+"\""+ ",\"comment\":"+"\""+primkey+"\""+"}\""+"]\"");
				dbFunc2.setSortField("[]");
				dbFunc2.setSettingField("[]");
				dbFunc2.setDisplayField(ss);
				dbFunc2.setDsName(table1.getDsName());
				dbFunc2.setDs_Alias(table1.getDsAlias());
				dbFunc2.setIsTable(a);
				dbFunc2.setIs_Using(null);
				dbFunc2.setObjname(table1.getFactTableName());
				dbFuncService.add(dbFunc2);
				System.out.println("插入delbyid成功");
				
	//插入add				
				DbFunc dbFunc3 = new DbFunc();		    
				dbFunc3.setId(id+3);
				dbFunc3.setTable_name(table1.getTableName());
				dbFunc3.setFunc_type(a);
				dbFunc3.setFunc_name("add");
				dbFunc3.setFunc_alias(null);
				dbFunc3.setRemarks(null);
				dbFunc3.setParameterType(table1.getTableName());
	 			dbFunc3.setResultType("[]");    			
				dbFunc3.setResultField("[]");    			
				dbFunc3.setConditionField("[]");
				dbFunc3.setSortField("[]");
				dbFunc3.setSettingField(ss);
				dbFunc3.setDisplayField(ss);
				dbFunc3.setDsName(table1.getDsName());
				dbFunc3.setDs_Alias(table1.getDsAlias());
				dbFunc3.setIsTable(a);
				dbFunc3.setIs_Using(null);
				dbFunc3.setObjname(table1.getFactTableName());
				dbFuncService.add(dbFunc3);
				System.out.println("插入add成功");
	//update
				DbFunc dbFunc4 = new DbFunc();		    
				dbFunc4.setId(id+4);
				dbFunc4.setTable_name(table1.getTableName());
				dbFunc4.setFunc_type(c);
				dbFunc4.setFunc_name("update");
				dbFunc4.setFunc_alias(null);
				dbFunc4.setRemarks(null);
				dbFunc4.setParameterType(table1.getTableName());
	 			dbFunc4.setResultType(null);  
	 			
				dbFunc4.setResultField("[]");    			
				dbFunc4.setConditionField("[{\"field\":"+ "\""+"ID"+"\""+ ",\"comment\":"+"\""+primkey+"\""+"}\""+"]\"");
				dbFunc4.setSortField("[]");
				dbFunc4.setSettingField(ss);
				dbFunc4.setDisplayField(ss);
				
				dbFunc4.setDsName(table1.getDsName());
				dbFunc4.setDs_Alias(table1.getDsAlias());
				dbFunc4.setIsTable(a);
				dbFunc4.setIs_Using(null);
				dbFunc4.setObjname(table1.getFactTableName());
				dbFuncService.add(dbFunc4);
				System.out.println("插入update成功");
				

				
	//插入dbresult  DbFunc				
				DbResult dbResult1 = new DbResult();
				dbResult1.setId(id+1);
				dbResult1.setTable_name(table1.getTableName());
				dbResult1.setClass_name(table1.getFactTableName());
				
				
				
			    for(int ii=0;ii<table1.getFieldList().size();ii++)
			    {
			    	BpmFormField s =table1.getFieldList().get(ii);
			    	
			    	if(ii==table1.getFieldList().size()-1)
			    		
			    	{			    		
			    		ss1 = ss1 + "\""+s.getFieldName()+"\""+":"+"\""+"F_"+s.getFieldName().toUpperCase()+"\"";
			    	}
			    	else  
			    		ss1 = ss1 + "\""+s.getFieldName()+"\""+":"+"\""+"F_"+s.getFieldName().toUpperCase()+"\"" +","  ; 
			    	
	 			    }			
			    dbResult1.setPro_col("[{"+"\"id\""+":"+"\"ID\""+","+ss1+"}]");
			    
			    dbResultService.add(dbResult1);
			    System.out.println("插入dbfunc成功");
	//插入dbresult  columns
				
				DbResult dbResult  =  new DbResult();
				dbResult.setId(id);
				dbResult.setTable_name(table1.getTableName());
				dbResult.setClass_name("columns");
				dbResult.setPro_col("[{"+"\"id\""+":"+"\"ID\""+","+ss1+"}]");
				dbResultService.add(dbResult);
				System.out.println("插入columns成功");
				
				}*/
				//++++++++++++++++++++++++++++++++++生成方法对应的sql语句++++++++++++++++++++++++++++++++++------------end
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

	/**
	 * 生成代码zip
	 * <p>
	 * 由于原生成代码方法codegen()无论是否勾选打包文件，都会直接在平台中生成代码。因此极有可能带来平台文件管理的絮乱问题， 严重时还可能带来了平台的安全问题， 为了减小这种人为的疏忽错误，决定修改为：在生成代码时，先将所生成的代码打包成一个zip文件， 保持在webapp/codegen文件夹中，再提供用户下载。只能保留一个压缩文件。
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @author ouxb@jee-soft.cn
	 * @throws Exception
	 */
	@RequestMapping("codegenZip")
	public void codegenZip(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	 * 下载文件的方法
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @throws Exception
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

}
