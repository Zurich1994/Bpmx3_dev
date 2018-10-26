package com.hotent.platform.controller.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.lang.String;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.Markovchain.service.Markovchain.MarkovchainService;
import com.hotent.SysDefNodeErgodic.service.SysDefNodeErgodic.SysdefnodeergodicService;
import com.hotent.core.annotion.Action;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.ZipUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.dbFunc.model.dbFunc.DbFunc;
import com.hotent.dbFunc.service.dbFunc.DbFuncService;
import com.hotent.dbResult.model.dbResult.DbResult;
import com.hotent.dbResult.service.dbResult.DbResultService;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.model.system.SysCodeTemplate;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.form.FormUtil;
import com.hotent.platform.service.form.IParseHandler;
import com.hotent.platform.service.form.ParseReult;
import com.hotent.platform.service.system.CodeUtil;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.platform.service.system.SysCodeTemplateService;

/**
 * 对象功能:基于自定义表的代码生成器 控制器类 开发公司:广州宏天软件有限公司 开发人员:zyp 创建时间:2012-12-19 15:38:01
 */
@Controller
@RequestMapping("/platform/system/sysCodeoperate/")
public class SysCodeoperateController extends BaseController {
	@Resource
	private BpmFormDefService bpmFormDefService;
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
	private DbFuncService dbFuncService;

	@Resource
	private SubSystemService subSystemService;
	@Resource
	private DbResultService dbResultService;
	@Resource
	private Map<String, IParseHandler> parseHandlerMap;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	private static final String BASE_PATH = FileUtil.getRootPath()
			+ File.separator + "codeoperate"; // 生成代码临时存放位置

	@Resource
	private MarkovchainService markovchainService;
	@Resource
	private SysdefnodeergodicService sysdefnodeergodicService;
	

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
	public List<BpmDefinition> getTableData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String typeId = RequestUtil.getString(request, "typeId");
		String subject = RequestUtil.getString(request, "subject");
		List<BpmDefinition> bpmDefinitionList = bpmDefinitionService
				.getBySubjectAndTypeId(subject, typeId);     //**得到subject 和typeId 

		for (BpmDefinition bdf : bpmDefinitionList) {
			bdf.setHtml("");
			bdf.setTemplate("");
		}
		return bpmDefinitionList;
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
	public ModelAndView genDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<SysCodeTemplate> templateList = sysCodeTemplateService.getAll();
		return getAutoView().addObject("templateList", templateList).addObject(
				"flowName", RequestUtil.getString(request, "flowName", ""))
				.addObject("defKey",
						RequestUtil.getString(request, "defKey", ""))
				.addObject("baseDir",
						RequestUtil.getString(request, "baseDir", ""))
				.addObject("system",
						RequestUtil.getString(request, "system", ""));
	}

	private List<String> genCode(String basePath, String system,List<BpmDefinition> opers,
			List<BpmFormTable> tables, String[] templateIds, int override,
			String flowKey, BpmFormDef bpmFormDef) throws Exception {
		List<String> fileList = new ArrayList<String>();
		Long[] templateIdss = new Long[6];
		templateIdss[0] = 10000041250030L;
		templateIdss[1] = 10000041250031L;
		templateIdss[2] = 10000041250032L;
		templateIdss[3] = 10000041250033L;
		templateIdss[4] = 10000063800000L;
		templateIdss[5] = 10000063820000L;

		for (int i = 0; i < templateIdss.length; i++) { 
			SysCodeTemplate template = sysCodeTemplateService         //**通过模板ID得到模板
					.getById(templateIdss[i]);
			
			//操作图代码生成
			for (BpmDefinition oper :opers){                  
				
			for (BpmFormTable table : tables) {
				
				
				Map<String, String> variables = table.getVariable();

				String fileName = template.getFileName();
				String fileDir = template.getFileDir();
				String path = basePath + File.separator + template.getFileDir();
				System.out.println("741258  path        " + path);
				variables.put("system", system);
				int isSub = template.getIsSub();
				if (table.getIsMain() != 1) {
					if (isSub == 0) {
						continue;
					}
				}

				Map<String, Object> model = new HashMap<String, Object>();

				IParseHandler parseHandler = parseHandlerMap.get(template
						.getTemplateAlias());

				if (parseHandler != null) {// 找到解释器就说明有html要解释
					String html = bpmFormDef.getHtml();
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("table", table);
					param.put("bpmFormDef", bpmFormDef);
					String str = parseHandler.parseHtml(html, param);
					model.put("html", str);
				}

				// 其他参数
				model.put("oper", oper);
				model.put("table", table);
				model.put("system", system);
				if (StringUtil.isNotEmpty(flowKey)) {
					model.put("flowKey", flowKey);
				}
				String templateStr = template.getHtml();
				String templatePath = SysCodeTemplateService
						.getRelateTemplatePath(template.getTemplateAlias());
				String html = freemarkEngine.mergeTemplateIntoString(
						templatePath, model);
				String fileStr = path + File.separator + fileName;
				System.out.println("741258  fileStr " + fileStr);
				String filePath = StringUtil
						.replaceVariable(fileStr, variables);
				System.out.println("741258  filePath " + filePath);
				addFile(filePath, html, override);
				String relativePath = StringUtil.replaceVariable(fileDir
						+ File.separator + fileName, variables);
				fileList.add(relativePath);
			}
			}
		}
		return fileList;
	}

	private String getFormHtml(BpmFormDef bpmFormDef, BpmFormTable table,
			boolean isEdit) throws Exception {
		String html = bpmFormDef.getHtml();
		if (BpmFormDef.DesignType_CustomDesign == bpmFormDef.getDesignType()) {
			ParseReult result = FormUtil.parseHtmlNoTable(html, table
					.getTableName(), table.getTableDesc());
			html = bpmFormHandlerService.obtainHtml(bpmFormDef.getTabTitle(),
					result, null, false);
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
	 * 由于原生成代码方法codegen()无论是否勾选打包文件，都会直接在平台中生成代码。因此极有可能带来平台文件管理的絮乱问题，
	 * 严重时还可能带来了平台的安全问题， 为了减小这种人为的疏忽错误，决定修改为：在生成代码时，先将所生成的代码打包成一个zip文件，
	 * 保持在webapp/codegen文件夹中，再提供用户下载。只能保留一个压缩文件。
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @author ouxb@jee-soft.cn
	 * @throws Exception
	 */

	@RequestMapping("codeoperateZip")
	public void codeoperateZip(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String basePath = "BASE_PATH" + File.separator + "codegen";
		String basePathZip = "BASE_PATH" + File.separator + "codegen.zip";
		String flowKey = RequestUtil.getString(request, "defKey");                          //**把defkey定义成flowkey
		String[] formDefIds = RequestUtil.getString(request, "bpmDefIds")   
				.split(",");                                                    //**从前端得到bpmdefids定义成formdefids
		String[] templateIds = new String[6];
		templateIds[0] = "10000041250030";
		templateIds[1] = "10000041250031";
		templateIds[2] = "10000041250032";
		templateIds[3] = "10000041250033";
		templateIds[4] = "10000063800000";
		templateIds[5] = "10000041250037";
		List<BpmFormTable> list = viewCodegen(request);// 在获取过程中还解释 . 操作图逻辑解析   **获得了表的操作属性方法类型
														// 将操作图进行全面拆分解析获得数据
		//操作图节点集合
		List<BpmDefinition> list1 = new ArrayList<BpmDefinition>();
		//事物图节点集合
		List<BpmDefinition> list2 = new ArrayList<BpmDefinition>();
		int num = 0;
		try {
			for (String formDefId : formDefIds) {
				  BpmDefinition bpmDefinition = bpmDefinitionService.getDefinitionById(formDefId);
				  //操作图list
				  List<Map> maplist2 = new ArrayList<Map>();            
					
				   //根据操作图确定方法的个数
				    List<Map<String, String>> funList = bpmFormDefService.sAndUser(Long.parseLong(formDefId)); //**全部事物与用户对存入list
					Map<String, String> map1 = 	geName(funList);            //**把取出来的的事物与用户对组成一个方法的名字
					Iterator<Map.Entry<String, String>> entries = map1.entrySet().iterator();  //**wtf？  **迭代处理了map1中的方法名字
					while(entries.hasNext()){
						Map<String, String> opers = new HashMap<String, String>(); //**存放得到的方法 名字
						 Map.Entry<String, String> entry = entries.next();  
						  
						    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
						    //方法名
						    opers.put("funcName", entry.getValue());           //**存放得到的方法 ** 方法名字是自己命名的
						    //根据事物图名获得事物图对象
						    List<BpmDefinition> bpmDefinitions = bpmDefinitionService.getAllPublished(entry.getKey()); //**entry.getkey是subject
						    BpmDefinition Definition = bpmDefinitions.get(bpmDefinitions.size()-1);
						    //解析事物图 判断其原子操作方法
						    List<String> nodelist = markovchainService.findBpmNode(Definition.getDefId());    //**得到有顺序的事务图节点
						    //事物图List
						    List<Map> maplist3 = new ArrayList<Map>();
						    //事物图节点集合
						    for(String nodeid :nodelist){
						    	Map<String, String> business = new HashMap<String, String>();    //**存放节点对应的方法的集合
						    	//事物图节点集合
						    					    	
						    	String subString = subSystemService.findScriptNodeDefKey11(Definition.getActDefId(),nodeid);//**得到节点对应的增删改查的方法
						    	System.out.println("subString:+++++++++++++++++="+subString);
						    	business.put("bnode", subString);   //**把得到的方法集放入business map
//						    	if("ADD".equals(subString)||"DEL".equals(subString)||"UPD".equals(subString)||"QUI".equals(subString)){
//						    		opers.put("funcType", subString);
//						    		System.out.println("加入curd");
//						    	}else {
//						    		//System.out.println("ssssssssssss:"+opers.get("funcType"));
//						    		if(opers.get("funcType")==null){
//						    			opers.put("funcType", subString);
//						    			System.out.println("加入rw");
//						    		}
//						    	}
						    	
						    	maplist3.add(business);       //**存放得到的方法的集
						    	System.out.println("maplist3:+"+maplist3);
						    }
						    maplist2.add(opers);         //**存放的 方法的名字
						    
						    bpmDefinition.setMapList3(maplist3);
						    //list1.add(bpmDefinition);
						    list2.add(bpmDefinition);         //**存放的 装有方法集的bpmdefinition对象
					}
					//bpmDefinition.setOpermap(opers);
					bpmDefinition.setMapList2(maplist2);
					System.out.println("循环:"+bpmDefinition.getMapList2()+"ssss"+bpmDefinition.getMapList3());
					list1.add(bpmDefinition);        //**存放了  装有方法名字集的 bpmdefinition对象

				Map<Long, String> tableId = bpmFormDefService.xdString(Long
						.parseLong(formDefId));// 操作图代码生成业务逻辑处理 将操作图转换为表i
				List<BpmFormDef> bpmFormDefs = null;
				for (Long tableid : tableId.keySet()) {// 遍历tableId得到单个表id
					bpmFormDefs = bpmFormDefService.gettableId(tableid);// 通过tableid获取对象
					num++;
					System.out.println("bpmFormDefs  :  " + bpmFormDefs.size());
					for (int i = 0; i < bpmFormDefs.size(); i++) {
						BpmFormDef bpmFormDef = bpmFormDefs.get(i);
						List<BpmFormTable> tables = new ArrayList<BpmFormTable>();
						for (BpmFormTable model : list) {
							if (model.getTableId().equals(tableid)
									|| model.getMainTableId().equals(tableid)) {
								tables.add(model);
							}
							
						}
						long formidd = Long.parseLong(formDefId);
						List<String> ddName = bpmFormDefService
								.xddString(formidd);
						for (String system : ddName) {
							genCode(basePath, system, list1,tables, templateIds, 1,
									flowKey, bpmFormDef);
							
						}
					}
				}
			}

			FileUtil.deleteFile(basePathZip);
			ZipUtil.zip(basePath, true);
			String zipFileName = "codegen.zip";
			FileUtil.downLoadFile(request, response, basePathZip, zipFileName);

		} catch (Exception e) {
			e.printStackTrace();
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
	public void downLoadZip(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("进来了么");
		try {
			String basePathZip = BASE_PATH + File.separator + "codeoperate.zip";
			File file = new File(basePathZip);
			InputStream in = new FileInputStream(file);
			String fileName = "codeoperate.zip";
			response.setContentType("application/x-download"); // 使客户端区分不同种类的数据
			if (System.getProperty("file.encoding").equals("GBK")) { // GBK
																		// 汉字扩展规范转码的东西
																		// 文件的保存编码
				response.setHeader("Content-Disposition",
						"attachment;filename=\""
								+ new String(fileName.getBytes(), "ISO-8859-1")
								+ "\"");
			} else {
				response.setHeader("Content-Disposition",
						"attachment;filename=\""
								+ URLEncoder.encode(fileName, "utf-8") + "\"");
			}
			ServletOutputStream out = response.getOutputStream(); 
			IOUtils.copy(in, out);

			if (in != null)
				in.close();
			if (out != null)
				out.close();
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), new ResultMessage(
					ResultMessage.Fail, "文件不存在，请重新再生成一次!:" + e.getMessage()));
		}
	}

	/**
	 * 操作图逻辑解析 将操作图进行全面拆分解析获得数据
	 * 
	 */
	public List<BpmFormTable> viewCodegen(HttpServletRequest request)
			throws Exception {
		List<BpmFormTable> list = new ArrayList<BpmFormTable>();

		String system = request.getParameter("system");// 系统
		String[] formDefIds = RequestUtil.getString(request, "bpmDefIds")
				.split(",");

		for (String formDefId : formDefIds) {
		 
			
			// 得到表单id
			long formidd = Long.parseLong(formDefId);                
			Map<Long, String> tableId = bpmFormDefService.xdString(Long
					.parseLong(formDefId));// 操作图代码生成业务逻辑处理 将操作图转换为表id
			// 通过tableid 找到formdefid
			for (Long tableid : tableId.keySet()) {// 遍历tableId得到单个表id
				List<String> ddName = bpmFormDefService.xddString(formidd);  //**得到事务图节点defkey作为的ddName

				List<BpmFormTable> subtables = new ArrayList<BpmFormTable>();
				for (String dName : ddName) {
					Map<String, String> vars = new HashMap<String, String>();// 定义MAP类型变量存放4个参数
					//**对应之前表生成代码的时候类名报名都是自己填的现在把defkey作为类名包名
					vars.put("class", dName); 
					vars.put("classVar", dName);
					vars.put("package", dName);
					System.out.println("package" + dName);
					vars.put("system", system);         //**what？why？
					BpmFormTable bpmFormTable = bpmFormTableService
							.getById(tableid);// 根据主键Id获取表对象 **tableid就是单个表id
					String tableName = bpmFormTable.getTableName();     //**表名
					System.out.println("表名为：" + bpmFormTable.getTableName());
					bpmFormTable.setVariable(vars);// 设置Variable变量  **vars中放的就是名字 加入到表对象
					List<BpmFormField> fieldList = bpmFormFieldService
							.getByTableIdContainHidden(tableid);// BpmFormField表示bpm_form_field
																// Model对象
					List<BpmFormField> fields = new ArrayList<BpmFormField>();
					// 找到原子操作的具体方法

					// 找表中对应的方法
					List<DbFunc> dbFunc1 = dbFuncService
							.getByTableName(tableName);// DbFunc集合    
					List<DbFunc> dbFunc2 = new ArrayList<DbFunc>();// 模板方法集合            **存放的是方法
					List<DialogField> condition = new ArrayList<DialogField>();
					List<Map> mapList = new ArrayList<Map>();            //**存放表的属性操作方法类型
					String fieldName = "";
					String fieldType = "";
					String comment = "";
				

					for (DbFunc db : dbFunc1) {
						Map<String, String> map = new HashMap<String, String>();
						String Func_name = db.getFunc_name();         //**得到方法名称
						Long Func_type = db.getFunc_type();           //**得到方法类型
						String parameterType = db.getParameterType();  //**得到传参类型
						if (Func_name.equals("add")
								|| Func_name.equals("delById")
								|| Func_name.equals("getAll")
								|| Func_name.equals("update")
								|| Func_name.equals("getById")) {
							dbFunc2.add(db);                            //**如果是一个增删改查 里的一个方法就放在dbfunc2中
						} else // 其余方法为自定义的方法
						{
							condition = db.getConditionList();              //**得到条件
							for (DialogField diag : condition) {
								fieldName = diag.getFieldName();
								fieldType = diag.getFieldType();
								comment = diag.getComment();
								map.put("fieldName", fieldName);// 表的属性
								map.put("fieldType", fieldType);
								map.put("comment", comment);
							}
							map.put("db_Func_Name", Func_name);// 操作
							map.put("db_Func_Type", Func_type.toString());
							map.put("db_Func_ParameterType", parameterType);
							mapList.add(map);              //**把表的属性和操作 和方法类型放进一个List
						}
					}
					bpmFormTable.setMapList(mapList);      //**把获得存放表属性操作方法的List 放入 bpmfortable表对象
					// 字段值来源为脚本计算时，脚本去掉换行处理
					for (BpmFormField field : fieldList) {
						if (bpmFormTable.getIsExternal() == 1) {// 外部表变小写 是否外部表
																// 0，本地表 1，外地表
							field.setFieldName(field.getFieldName()
									.toLowerCase());
						}
						// 下面开始处理单选，复选，下拉框的值
						String[] formDefIds1 = RequestUtil.getString(request,
								"formDefIds").split(",");
						try {

							for (String formDefId1 : formDefIds1) {
								BpmFormDef bpmFormDef = bpmFormDefService
										.getById(Long.parseLong(formDefId1));
								String options = CodeUtil.getDialogTags(
										getFormHtml(bpmFormDef, bpmFormTable,
												true), field);
								if (StringUtil.isNotEmpty(options)) { // 判断字符串非空
									field.setOptions(options);// 设置下拉框
								}
							}
						} catch (Exception e) {
						}
						fields.add(field);
					}
					if (bpmFormTable.getIsExternal() == 1) {
						bpmFormTable.setPkField(bpmFormTable.getPkField()
								.toLowerCase());// 设置主键字段
						if (bpmFormTable.getIsMain() != 1) {// 返回 是否主表 1-是 0-否
							bpmFormTable.setRelation(bpmFormTable.getRelation()
									.toLowerCase());// 设置外键字段
						}
					}
					bpmFormTable.setFieldList(fields);// 设置字段信息
					if (bpmFormTable.getIsMain() != 1) {
						subtables.add(bpmFormTable);
					}
					list.add(bpmFormTable);// 将从表放入list

					for (BpmFormTable subtable : subtables) {
						for (BpmFormTable table : list) {// 返回所属主表，该字段仅针对从表
							if ((table.getIsMain() == 1)
									&& (table.getTableId().equals(subtable
											.getMainTableId()))) {
								table.getSubTableList().add(subtable);// 子表列表
							}
						}
					}

				}
			}
		}
		return list;
	}

	/**
	 * 生成方法名函数
	 * 
	 * @param formDefId
	 * @return
	 */
	public Map<String, String> geName(List<Map<String,String>> LMap){
		Map<String,String> customs=new HashMap<String, String>();
		for(Map<String, String> denode :LMap){
               for(Map.Entry<String, String> entry:denode.entrySet())
               {
            	   String deqss = entry.getKey();    //得到TaskScript 
            	   String dehss = entry.getValue();   //得到后面key  
            	   String custom = deqss+dehss; //自己组合出的名字 custom= tascrip加上事务图名字
            	   customs.put(dehss, custom); 
            	   }
               //System.out.println("456987 "+customs);
			}
		return customs;
	}

}
