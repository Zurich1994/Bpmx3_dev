package com.hotent.platform.controller.form;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.table.BaseTableMeta;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.IDbView;
import com.hotent.core.table.ITableOperator;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.impl.TableMetaFactory;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.dbFunc.model.dbFunc.DbFunc;
import com.hotent.dbFunc.service.dbFunc.DbFuncService;
import com.hotent.dbResult.model.dbResult.DbResult;
import com.hotent.dbResult.service.dbResult.DbResultService;
import com.hotent.platform.model.bpm.BpmSubtableRights;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormRule;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.BpmFormTableIndex;
import com.hotent.platform.model.system.Identity;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysDataSourceL;
import com.hotent.platform.model.util.FieldPool;
import com.hotent.platform.service.bpm.BpmSubtableRightsService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.form.BpmFormRuleService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.system.IdentityService;
import com.hotent.platform.service.system.SysDataSourceLService;
import com.hotent.platform.xml.util.MsgUtil;
import com.hotent.tableParcel.model.tableParcel.TableParcel;
import com.hotent.tableParcel.service.tableParcel.TableParcelService;

/**
 * 对象功能:自定义表 控制器类 开发公司:广州宏天软件有限公司 开发人员:xwy 创建时间:2011-11-30 14:29:22
 */
@Controller
@RequestMapping("/platform/form/bpmFormTable/")
@Action(ownermodel = SysAuditModelType.FORM_MANAGEMENT)
public class BpmFormTableController extends BaseController {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private BpmFormTableService service;

	@Resource
	private BpmSubtableRightsService bpmSubtableRightsService;

	@Resource
	private BpmFormFieldService bpmFormFieldService;

	@Resource
	private SysDataSourceLService sysDataSourceLService;

	@Resource
	private BpmFormRuleService bpmFormRuleService;

	@Resource
	private IdentityService identityService;

	@Resource
	private FreemarkEngine freemarkEngine;

	@Resource
	private Properties configproperties;

	@Resource
	private BpmFormDefService bpmFormDefService;

	@Resource
	private ITableOperator tableOperator;
	
	@Resource
	private TableParcelService tableParcelService;
	
	@Resource
	private BpmFormTableService bpmFormTableService;
	
	@Resource
	private DbFuncService dbFuncService;
	
	@Resource
	private DbResultService dbResultService;
	

	/**
	 * 导入外部表。
	 * 
	 * @param request
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("defExtTable{viewName}")
	public ModelAndView defExtTable(HttpServletRequest request,
	 @PathVariable String viewName) throws Exception {
		ModelAndView mv = this.getAutoView();
		// 选择表
		if (viewName.equals("1")) {
//		     List<SysDataSource> dsList = sysDataSourceService.getAll();
//			 mv.addObject("dsList", dsList);
			List<SysDataSourceL> sysDataSourceLs = AppUtil.getBean(SysDataSourceLService.class).getAll();
			mv.addObject("dsList", sysDataSourceLs);
		} else if (viewName.equals("2")) {

			Long tableId = RequestUtil.getLong(request, "tableId");
			String dataSource = RequestUtil.getString(request, "dataSource");
			String tableName = RequestUtil.getString(request, "tableName");

			boolean hasForm = false;

			if (tableId > 0) {
				BpmFormTable bpmFormTable = service.getById(tableId);
				// 获取业务表单的主键
				if (bpmFormTable != null) {
					mv.addObject("bpmFormTable", bpmFormTable);
				}
				dataSource = bpmFormTable.getDsAlias();
				tableName = bpmFormTable.getTableName();

				Long tmpTableId = tableId;

				// 如果是子表的情况，先取得主表的ID,根据主表id判断是否可以编辑。
				if (bpmFormTable.getIsMain() == 0) {
					tmpTableId = bpmFormTable.getMainTableId();
				}
				if (tmpTableId != null && tmpTableId > 0)
					hasForm = bpmFormDefService.isTableHasFormDef(tmpTableId);
			}
			List<BpmFormTable> mainTableList = service.getAllUnpublishedMainTable();
			mv.addObject("canEditTbColName", false)
			        .addObject("tableId", tableId)
			        .addObject("hasForm", hasForm)
		  	        .addObject("mainTableList", mainTableList)
			        .addObject("dataSource", dataSource)
		 	        .addObject("tableName", tableName);
		}
		return mv;
	}

	/**
	 * 编辑自定义表。
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("edit")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		ModelAndView mv = this.getAutoView();
		Long tableId = RequestUtil.getLong(request, "tableId");

		boolean canEditTbColName = true;
		// 无表单定义可以做任何的修改。
		if (tableId > 0) {
			//
			Long tmpTableId = tableId;

			BpmFormTable bpmFormTable = service.getById(tmpTableId);
			// 如果是子表的情况，先取得主表的ID,根据主表id判断是否可以编辑。
			if (bpmFormTable.getIsMain().shortValue() == BpmFormTable.NOT_MAIN.shortValue()) {
				tmpTableId = bpmFormTable.getMainTableId();
			}
			if (tmpTableId != null && tmpTableId > 0)
				canEditTbColName = !bpmFormDefService.isTableHasFormDef(tmpTableId);

		}
		List<BpmFormTable> mainTableList = service.getAllUnpublishedMainTable();
		mv.addObject("canEditTbColName", canEditTbColName)
				.addObject("tableId", tableId)
				.addObject("mainTableList", mainTableList);
		return mv;
	}

	/**
	 * 编辑数据包。
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("editwb")
	public ModelAndView editwb(HttpServletRequest request) throws Exception {
		ModelAndView mv = this.getAutoView();
		Long tableId = RequestUtil.getLong(request, "tableId");
		Long parcelId=RequestUtil.getLong(request, "id", 0);
		
		if(parcelId>0){
			TableParcel tableParcel=tableParcelService.getById(parcelId);
			mv.addObject("parcel", tableParcel);
		}
		
		System.out.println("id"+parcelId);
		boolean canEditTbColName = true;
		// 无表单定义可以做任何的修改。
		if (tableId > 0) {
			//
			Long tmpTableId = tableId;

			BpmFormTable bpmFormTable = service.getById(tmpTableId);
			// 如果是子表的情况，先取得主表的ID,根据主表id判断是否可以编辑。
			if (bpmFormTable.getIsMain().shortValue() == BpmFormTable.NOT_MAIN.shortValue()) {
				tmpTableId = bpmFormTable.getMainTableId();
			}
			if (tmpTableId != null && tmpTableId > 0)
				canEditTbColName = !bpmFormDefService.isTableHasFormDef(tmpTableId);
			
		}
		List<BpmFormTable> mainTableList = service.getAllUnpublishedMainTable();
		
		BpmFormTable bpmFormTable = service.getById(tableId);
		ArrayList<BpmFormField> fiedls=(ArrayList<BpmFormField>) bpmFormTable.getFieldList();
		mv.addObject("canEditTbColName", canEditTbColName)
				.addObject("tableId", tableId)
				.addObject("mainTableList", mainTableList)
				.addObject("bpmFormTable",bpmFormTable)
				.addObject("bpmFormfiedls",fiedls);
		
		return mv;
	}
	/**
	 * 数据包列表。
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("parcellist")
	public ModelAndView parcellist(HttpServletRequest request) throws Exception {
		
		Long tableId = RequestUtil.getLong(request, "tableId");
		
		BpmFormTable bpmFormTable = service.getById(tableId);
		String tablename=bpmFormTable.getTableName();
		List<TableParcel> list=tableParcelService.getParcelsbyTableName(tablename);
		
		ModelAndView mv = this.getAutoView();
		mv.addObject("tableParcelList", list)
		  .addObject("tableId"+tableId);
		return mv;
	}
	@RequestMapping("delparcel")
	@Action(description="删除table_parcel")
	public void delparcel(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			tableParcelService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除table_parcel成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	/**
	 * 设置列
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("setting")
	public ModelAndView setting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "id");
		System.out.println("tableid:"+id);
		
		ModelAndView mv=getAutoView();
		BpmFormTable bpmFormTable = service.getById(id);
		System.out.println("table"+bpmFormTable.toString());
		
		ArrayList<BpmFormField> fields=(ArrayList<BpmFormField>) bpmFormFieldService.getByTableId(id);
		for(BpmFormField field:fields){
			System.out.println("field"+field.toString());
		}
		mv.addObject("bpmFormFields",fields);
		
		return mv;
	}

	/**
	 * 将隐藏字段的脚本放到对应字段的scriptID属性中。
	 * 
	 * @param list
	 */
	private void converFieldScript(List<BpmFormField> list) {
		Map<String, BpmFormField> fieldMap = new HashMap<String, BpmFormField>();
		for (BpmFormField field : list) {
			fieldMap.put(field.getFieldName(), field);
		}

		for (Iterator<BpmFormField> it = list.iterator(); it.hasNext();) {
			BpmFormField field = it.next();
			Short controleType = field.getControlType();
			// 是人员选择器
			boolean isSelector = service.isExecutorSelector(controleType);
			short valueFrom = field.getValueFrom().shortValue();
			// 选择器 脚本 隐藏字段。
			if (isSelector && field.getIsHidden().shortValue() == BpmFormField.HIDDEN && field.getIsDeleted().shortValue() == BpmFormField.IS_DELETE_N) {
				if (valueFrom == BpmFormField.VALUE_FROM_SCRIPT_SHOW || valueFrom == BpmFormField.VALUE_FROM_SCRIPT_HIDDEN) {
					String script = field.getScript();
					String fieldId = field.getFieldName();
					fieldId = StringUtil.trimSufffix(fieldId, BpmFormField.FIELD_HIDDEN);

					BpmFormField bpmFormField = fieldMap.get(fieldId);
					bpmFormField.setScriptID(script);
				}
				// 删除隐藏字段。
				it.remove();
			}
		}
	}

	/**
	 * 根据表的Id返回表和字段对象。
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getByTableId")
	public Map<String, Object> getByTableId(HttpServletRequest request) {
		Long tableId = RequestUtil.getLong(request, "tableId");
		int mainTableIsPublished = RequestUtil.getInt(request, "mainTableIsPublished");
		Map<String, Object> map = new HashMap<String, Object>();
		BpmFormTable bpmFormTable = service.getById(tableId);

		// 子表展示
		if (BeanUtils.isNotEmpty(bpmFormTable) && bpmFormTable.getIsMain().shortValue() == 0) {
			Long mainTableId = bpmFormTable.getMainTableId();
			if (mainTableId > 0) {
				BpmFormTable table = service.getById(mainTableId);
				// 为已发布的主表 添加一个子表时
				if (mainTableIsPublished == 1) {
					bpmFormTable.setMainTableDesc(table.getTableName());
				}
				else{
					bpmFormTable.setMainTableDesc(StringUtils.isEmpty(table.getTableDesc())?table.getTableName():table.getTableDesc());
				}
				bpmFormTable.setMainTableName(table.getTableName());
			}

		}
		List<BpmFormField> fieldList = bpmFormFieldService.getByTableIdContainHidden(tableId);
		map.put("table", bpmFormTable);

		converFieldScript(fieldList);
		map.put("fieldList", fieldList);

		return map;
	}

	/**
	 * 表选择对话框。
	 * 
	 * <pre>
	 *  只选择自定义表。
	 * </pre>
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dialog")
	public ModelAndView dialog(HttpServletRequest request) throws Exception {
		ModelAndView mv = this.getAutoView();

		QueryFilter queryFilter = new QueryFilter(request, "bpmFormTableItem");
		// 只查询自定义表。
		// queryFilter.addFilter("genByForm", 0);
		List<BpmFormTable> bpmFormTableList = service.getAllMainTable(queryFilter);
		
		mv.addObject("bpmFormTableList", bpmFormTableList);
		return mv;
	}


	/**
	 * 表选择对话框。
	 * 
	 * <pre>
	 *  只选择数据包表。
	 * </pre>
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dialog1")
	public ModelAndView dialog1(HttpServletRequest request) throws Exception {
		
		// 只查询自定义表。
		// queryFilter.addFilter("genByForm", 0);
		List<TableParcel> list=tableParcelService.getAll(new QueryFilter(request,"bpmFormTableItem"));
		ModelAndView mv=this.getAutoView().addObject("tableParcelList",list);
		
		return mv;
	
	}
	/**
	 * 编辑列对话框。
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("columnDialog")
	public ModelAndView columnDialog(HttpServletRequest request) 
			throws Exception {
		ModelAndView mv = this.getAutoView();
		int isAdd = RequestUtil.getInt(request, "isAdd", 0);
		int isMain = RequestUtil.getInt(request, "isMain", 0);
		// 验证规则
		List<BpmFormRule> validRuleList = bpmFormRuleService.getAll();
		// 获取流水号
		// List<Identity> identityList = identityService.getAll();
		String vers = request.getHeader("USER-AGENT");
		if (vers.indexOf("MSIE 6") != -1) {
			mv = new ModelAndView("/platform/form/bpmFormTableColumnDialog.jsp");
		}
		mv.addObject("validRuleList", validRuleList);
		// mv.addObject("identityList", identityList);
		mv.addObject("isAdd", isAdd);
		mv.addObject("isMain", isMain);
		return mv;

	}

	/**
	 * 编辑索引。
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("editIndex")
	public ModelAndView editIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = this.getAutoView();
		Long tableId = RequestUtil.getLong(request, "tableId");
		String tableName = RequestUtil.getString(request, "tableName");
		List<BpmFormTableIndex> tableIndexes = tableOperator
				.getIndexesByTable(TableModel.CUSTOMER_TABLE_PREFIX + tableName);
		for (BpmFormTableIndex index : tableIndexes) {
			index.setIndexTable(index.getIndexTable().toUpperCase()
					.substring(TableModel.CUSTOMER_TABLE_PREFIX.length()));
			if (!index.isPkIndex()) {
				index.setIndexName(index.getIndexName().toUpperCase()
						.substring(TableModel.CUSTOMER_INDEX_PREFIX.length()));
			}
			List<String> fields = new ArrayList<String>();
			for (String field : index.getIndexFields()) {
				if (field.toUpperCase().startsWith(
						TableModel.CUSTOMER_COLUMN_PREFIX)) {
					fields.add(field.toUpperCase().substring(
							TableModel.CUSTOMER_COLUMN_PREFIX.length()));
				} else {
					fields.add(field);
				}
			}
			index.setIndexFields(fields);
		}
		BpmFormTable table = service.getById(tableId);
		mv.addObject("table", table).addObject("tableId", tableId)
				.addObject("tableIndexes", tableIndexes);
		return mv;
	}

	/**
	 * 编辑索引对话框。
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("indexDialog")
	public ModelAndView indexDialog(HttpServletRequest request)
			throws Exception {
		int isAdd = RequestUtil.getInt(request, "isAdd", 0);
		Long tableId = RequestUtil.getLong(request, "tableId");
		String tableName = RequestUtil.getString(request, "tableName");
		String indexName = RequestUtil.getString(request, "indexName");
		boolean pkIndex = RequestUtil.getString(request, "pkIndex")
				.equalsIgnoreCase("true") ? true : false;

		BpmFormTableIndex index;
		if (isAdd == 0) {
			String preTableName = TableModel.CUSTOMER_TABLE_PREFIX + tableName;
			String preIndexName = indexName;
			if (!pkIndex) {
				preIndexName = TableModel.CUSTOMER_INDEX_PREFIX + indexName;
			}
			index = tableOperator.getIndex(preTableName, preIndexName);
			index.setIndexTable(index.getIndexTable().toUpperCase()
					.replaceFirst(TableModel.CUSTOMER_TABLE_PREFIX, ""));
			index.setIndexName(index.getIndexName().toUpperCase()
					.replaceFirst(TableModel.CUSTOMER_INDEX_PREFIX, ""));
			List<String> fields = new ArrayList<String>();
			for (String field : index.getIndexFields()) {
				fields.add(field.toUpperCase().replaceFirst(
						TableModel.CUSTOMER_COLUMN_PREFIX, ""));
			}
			index.setIndexFields(fields);
		} else {
			index = new BpmFormTableIndex();
			index.setIndexTable(tableName);
			index.setIndexFields(new ArrayList<String>());
		}
		List<BpmFormField> tableFileds = bpmFormFieldService
				.getByTableId(tableId);

		String selectedFields = JSONArray.fromObject(index.getIndexFields())
				.toString();

		ModelAndView mv = this.getAutoView();
		mv.addObject("dbType", getDbType());
		mv.addObject("index", index);
		mv.addObject("tableName", index.getIndexTable());
		mv.addObject("tableFileds", tableFileds);
		mv.addObject("selectedFields", selectedFields);
		mv.addObject("isAdd", isAdd);
		return mv;
	}

	/**
	 * 保存/新建索引
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("saveIndex")
	@Action(description="保存/新建索引", 
			detail = "表 【${tableName}】 " +
					"<#if '1'=isAdd>添加索引【${indexName}(${StringUtils.join(indexFields, \",\")})】" +
					"<#else>修改索引【${oldIndexName}】为【${indexName}(${StringUtils.join(indexFields, \",\")})】" +
					"</#if>"
	)
	public void saveIndex(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int isAdd = RequestUtil.getInt(request, "isAdd", 0);
		// Long tableId = RequestUtil.getLong(request, "tableId");
		String tableName = RequestUtil.getString(request, "tableName");
		String indexType = RequestUtil.getString(request, "indexType");
		String indexName = RequestUtil.getString(request, "indexName");
		String oldIndexName = RequestUtil.getString(request, "oldIndexName");
		String[] indexFieldsAry = request.getParameterValues("indexFields");

		for (int i = 0; i < indexFieldsAry.length; i++) {
			indexFieldsAry[i] = TableModel.CUSTOMER_COLUMN_PREFIX
					+ indexFieldsAry[i];
		}
		List<String> indexFields = Arrays.asList(indexFieldsAry);

		ResultMessage resultMessage;
		BpmFormTableIndex index = new BpmFormTableIndex();
		index.setIndexName(indexName);
		index.setIndexType(indexType);
		index.setIndexFields(indexFields);
		index.setIndexTable(tableName);
		index.setIndexTable(TableModel.CUSTOMER_TABLE_PREFIX
				+ index.getIndexTable());
		index.setIndexName(TableModel.CUSTOMER_INDEX_PREFIX
				+ index.getIndexName());
		try {
			if (isAdd == 1) {
				tableOperator.createIndex(index);
				resultMessage = new ResultMessage(ResultMessage.Success,
						"添加索引成功！");
			} else {

				tableOperator.dropIndex(index.getIndexTable(),
						TableModel.CUSTOMER_INDEX_PREFIX + oldIndexName);
				tableOperator.createIndex(index);
				resultMessage = new ResultMessage(ResultMessage.Success,
						"修改索引成功！");
			}

		} catch (Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail, "修改索引失败:"
						+ str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
		writeResultMessage(response.getWriter(), resultMessage);
	}

	@RequestMapping("delIndex")
	@Action(description="删除自定义表索引",
			detail="删除自定义表 【${tableName}】 索引【 ${indexName}】"
	)
	public void delIndex(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String tableName = RequestUtil.getString(request, "tableName");
		String indexName = RequestUtil.getString(request, "indexName");

		ResultMessage resultMessage;
		String preUrl = RequestUtil.getPrePage(request);
		try {
			tableName = TableModel.CUSTOMER_TABLE_PREFIX + tableName;
			indexName = TableModel.CUSTOMER_INDEX_PREFIX + indexName;
			tableOperator.dropIndex(tableName, indexName);
			resultMessage = new ResultMessage(ResultMessage.Success, "删除表索引成功!");
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = new ResultMessage(ResultMessage.Fail, "删除表索引失败!");
		}
		addMessage(resultMessage, request);
		response.sendRedirect(preUrl);
	}

	private String getDbType() {

		return tableOperator.getDbType();
	}

	/**
	 * 选择视图
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selectView")
	public ModelAndView selectView(HttpServletRequest request) throws Exception {
		ModelAndView mv = this.getAutoView();
		List<SysDataSourceL> dsList = sysDataSourceLService.getAll();
		mv.addObject("dsList", dsList);
		return mv;
	}

	/**
	 * 根据视图名称显示视图字段。
	 * 
	 * @param dsName
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getModelByView")
	public ModelAndView getModelByView(HttpServletRequest request)
			throws Exception {
		String ds = RequestUtil.getString(request, "dataSource");
		String viewName = RequestUtil.getString(request, "selList");

		IDbView idbView = TableMetaFactory.getDbView(ds);
		TableModel tableModel = idbView.getModelByViewName(viewName);

		ModelAndView mv = getAutoView();
		mv.addObject("table", tableModel);

		return mv;
	}

	/**
	 * 编辑视图
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editView")
	public ModelAndView editView(HttpServletRequest request) throws Exception {
		String viewName = RequestUtil.getString(request, "viewName");
		String viewComment = RequestUtil.getString(request, "viewComment");
		String pkName = RequestUtil.getString(request, "pkName");
		String[] aryCol = request.getParameterValues("column");
		String[] aryDbType = request.getParameterValues("dbtype");
		String[] aryComment = request.getParameterValues("comment");
		TableModel tableModel = new TableModel();
		tableModel.setName(viewName);
		tableModel.setComment(viewComment);

		for (int i = 0; i < aryCol.length; i++) {
			ColumnModel colModel = new ColumnModel();
			colModel.setName(aryCol[i]);
			colModel.setColumnType(aryDbType[i]);
			colModel.setComment(aryComment[i]);
			tableModel.addColumnModel(colModel);
		}
		Map map = new HashMap();
		map.put("table", tableModel);
		map.put("pkName", pkName);
		String html = freemarkEngine.mergeTemplateIntoString("view.ftl", map);

		ModelAndView mv = getAutoView();
		mv.addObject("html", html);
		mv.addObject("viewName", viewName);
		return mv;

	}

	/**
	 * 保存视图。
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("saveView")
	@Action(description="保存视图",
			detail="保存自定义表视图"
	)
	public void saveView(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String viewpath = configproperties.getProperty("viewpath");
		String viewCharset = configproperties.getProperty("viewCharset");
		String viewName = RequestUtil.getString(request, "viewName");

		String content = request.getParameter("txtViewHtml");
		if (!viewpath.endsWith(File.separator))
			viewpath += File.separator;
		viewpath = viewpath + viewName + ".aspx";

		Pattern regex = Pattern.compile(
				"<div\\s*style=\"display:none\"\\s*>(.*?)</div>",
				Pattern.DOTALL | Pattern.CASE_INSENSITIVE
						| Pattern.UNICODE_CASE);
		Matcher regexMatcher = regex.matcher(content);
		while (regexMatcher.find()) {
			String tag = regexMatcher.group(0);
			String innerContent = regexMatcher.group(1);

			content = content.replace(tag, innerContent);
		}
		content = content.replace("&lt;", "<");
		content = content.replace("&gt;", ">");

		FileUtil.writeFile(viewpath, content, viewCharset);
		PrintWriter writer = response.getWriter();
		ResultMessage resultMessage = new ResultMessage(ResultMessage.Success,
				"保存视图信息成功!");
		writeResultMessage(writer, resultMessage);
	}

	/**
	 * 根据数据源和表的名称获取外部表数据。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getTableModel")
	public Map<String, Object> getTableModel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String dataSource = RequestUtil.getString(request, "dataSource");
		String tableName = RequestUtil.getString(request, "tableName");
		BaseTableMeta meta = TableMetaFactory.getMetaData(dataSource);
		TableModel tableModel = meta.getTableByName(tableName);

		BpmFormTable table = new BpmFormTable();
		table.setTableName(tableName);
		table.setTableDesc(tableModel.getComment());
		// 取得字段列表
		List<BpmFormField> fieldList = convertFieldList(tableModel);

		DbContextHolder.clearDataSource();
		DbContextHolder.setDefaultDataSource();// 换回本地数据源
		List<Identity> identityList = identityService.getAll();// 这个语句要调用本地的数据源

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("identityList", identityList);
		map.put("table", table);
		map.put("fieldList", fieldList);
		map.put("pkList", tableModel.getPrimayKey());
		return map;
	}

	/**
	 * 将表的列表转换为FieldLst。
	 * 
	 * @param tableModel
	 * @return
	 */
	private List<BpmFormField> convertFieldList(TableModel tableModel) {
		List<BpmFormField> fieldList = new ArrayList<BpmFormField>();
		List<ColumnModel> colList = tableModel.getColumnList();
		for (ColumnModel model : colList) {
			// if(model.getIsPk()) continue;
			BpmFormField field = new BpmFormField();
			// field.setIsPk(0);

			field.setFieldName(model.getName());
			field.setFieldDesc(model.getComment());
			field.setCharLen(model.getCharLen());
			field.setIntLen(model.getIntLen());
			field.setDecimalLen(model.getDecimalLen());
			field.setFieldType(model.getColumnType());
			short isRequired = (short) (model.getIsNull() ? 0 : 1);
			field.setIsRequired(isRequired);

			setControlType(field);
			fieldList.add(field);

		}
		return fieldList;
	}

	/**
	 * 设置字段的默认控件类型。
	 * 
	 * @param field
	 */
	private void setControlType(BpmFormField field) {
		String fieldType = field.getFieldType();
		if (StringUtil.isEmpty(fieldType)) {
			field.setFieldType(ColumnModel.COLUMNTYPE_VARCHAR);
			fieldType=ColumnModel.COLUMNTYPE_VARCHAR;
		}
		if (fieldType.equalsIgnoreCase(ColumnModel.COLUMNTYPE_VARCHAR) || fieldType.equalsIgnoreCase(ColumnModel.COLUMNTYPE_NUMBER)) {
			field.setControlType(FieldPool.TEXT_INPUT);
		}
		else if(fieldType.equalsIgnoreCase(ColumnModel.COLUMNTYPE_DATE)){
			field.setControlType(FieldPool.DATEPICKER);
		}
		else if(fieldType.equalsIgnoreCase(ColumnModel.COLUMNTYPE_CLOB)){
			field.setControlType(FieldPool.TEXTAREA);
		}

	}

	/**
	 * 根据表名获取数据库列表。
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("getTableList")
	@ResponseBody
	public Map getTableList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map resultMap = new HashMap();
		try {
			String ds = RequestUtil.getString(request, "ds");
			String table = RequestUtil.getString(request, "table");
			BaseTableMeta meta = TableMetaFactory.getMetaData(ds);
			Map<String, String> map = meta.getTablesByName(table);
			resultMap.put("success", "true");
			resultMap.put("tables", map);
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMap.put("success", "false");
		}
		return resultMap;
	}

	@RequestMapping("getTableById")
	@ResponseBody
	public Map<String, Object> getTableById(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("tableId") Long tableId)
			throws Exception {
		Map<String, Object> fields = new HashMap<String, Object>();
		Boolean incHide = RequestUtil.getBoolean(request, "incHide", false);
		int need = 0;
		if (incHide) {
			need = 1;
		}
		BpmFormTable mainTable = service.getByTableId(tableId, need); // need 0为正常字段 1为正常字段加上隐藏字段 2 为正常字段加上逻辑删除字段
		fields.put("table", mainTable);
		return fields;
	}

	/**
	 * 取得视图列表。
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getViewList")
	@ResponseBody
	public Map getViewList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map resultMap = new HashMap();
		try {
			String ds = RequestUtil.getString(request, "ds");
			String table = RequestUtil.getString(request, "table");
			IDbView idbView = TableMetaFactory.getDbView(ds);
			List list = idbView.getViews(table);
			resultMap.put("success", "true");
			resultMap.put("views", list);
		} catch (Exception ex) {
			resultMap.put("success", "false");
		}
		return resultMap;
	}

	// /**
	// * 设置表关系
	// *
	// * @param request
	// * @param response
	// * @throws Exception
	// */
	// @RequestMapping("setRelation")
	// public ModelAndView setRelation(HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// ModelAndView mv = this.getAutoView();
	// long tableId = RequestUtil.getLong(request, "tableId");
	//
	// String dsName = RequestUtil.getString(request, "dsName");
	// /**
	// * 获取字段
	// */
	// List<BpmFormField> fieldList = bpmFormFieldService
	// .getByTableId(tableId);
	// /**
	// * 获取可以关联的表
	// */
	// List<BpmFormTable> tables = service.getByDsSubTable(dsName);
	//
	// BpmFormTable bpmFormTable = service.getById(tableId);
	//
	// String tableName = bpmFormTable.getTableName();
	//
	// tables.remove(tableName);
	// String pkField = bpmFormTable.getPkField();
	// if (StringUtil.isNotEmpty(pkField))
	// pkField = "";
	// mv.addObject("pkField", bpmFormTable.getPkField());
	// mv.addObject("tables", tables);
	// mv.addObject("fieldList", fieldList);
	//
	// mv.addObject("tableName", tableName);
	// mv.addObject("dataSource", dsName);
	// mv.addObject("relation", bpmFormTable.getTableRelation());
	//
	// return mv;
	// }

	// /**
	// * 保存表之间的关系
	// *
	// * @param request
	// * @param response
	// * @throws Exception
	// */
	// @RequestMapping("saveRelation")
	// public void saveRelation(HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	// PrintWriter writer = response.getWriter();
	// try {
	// String relation = request.getParameter("relation");
	// String tablename = RequestUtil.getString(request, "tablename");
	// String dataSource = RequestUtil.getString(request, "dataSource");
	//
	// service.saveRelation(dataSource, tablename, relation);
	//
	// this.writeResultMessage(writer, "设置成功", ResultMessage.Success);
	// } catch (Exception e) {
	// e.printStackTrace();
	// String str = MessageUtil.getMessage();
	// if (StringUtil.isNotEmpty(str)) {
	// ResultMessage resultMessage = new ResultMessage(
	// ResultMessage.Fail, "设置失败:" + str);
	// response.getWriter().print(resultMessage);
	// } else {
	// String message = ExceptionUtil.getExceptionMessage(e);
	// ResultMessage resultMessage = new ResultMessage(
	// ResultMessage.Fail, message);
	// response.getWriter().print(resultMessage);
	// }
	// }
	// }

	/**
	 * 判断表名是否存在。
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("isTableNameExternalExisted")
	public void isTableNameExternalExisted(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		String tablename = RequestUtil.getString(request, "tablename");
		String dataSource = RequestUtil.getString(request, "dataSource");
		boolean rtn = service.isTableNameExternalExisted(tablename, dataSource);
		writer.print(rtn);
	}

	/**
	 * 是否有主表
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("isMain")
	public Map<String, Object> isMain(HttpServletRequest request) {
		Long tableId = RequestUtil.getLong(request, "tableId", 0);
		Map<String, Object> map = new HashMap<String, Object>();
		if (tableId != 0) {
			List<BpmFormTable> list = service.getSubTableByMainTableId(tableId);
			map.put("isMain", list.size() > 0 ? true : false);
		} else {
			map.put("isMain", false);
		}
		return map;
	}
	
	@RequestMapping("manage")
	@Action(description = "自定义表管理页面")
	public ModelAndView manage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();

		return mv;
	}

	/**
	 * 取得自定义表分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看自定义表分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "page", defaultValue = "1") int page)
			throws Exception {
		List<BpmFormTable> list = service.getAll(new QueryFilter(request,
				"bpmFormTableItem"));
		ModelAndView mv = this.getAutoView()
				.addObject("bpmFormTableList", list);

		return mv;
	}
	
	@RequestMapping("setCategory")
	public void setCategory(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		Long categoryId = RequestUtil.getLong(request, "categoryId", 0);
		String tableIds = RequestUtil.getString(request, "tableIds");
		String[] aryTableId = tableIds.split(",");
		try {
			service.updCategory(categoryId, aryTableId);
			writeResultMessage(writer, new ResultMessage(ResultMessage.Success,
					  ContextUtil.getMessages("controller.bpmFormDef.setCategory.success")));
		} catch (Exception ex) {
			String msg = ExceptionUtil.getExceptionMessage(ex);
			writeResultMessage(writer, new ResultMessage(ResultMessage.Fail,
					msg));
		}
		
	}

	/**
	 * 编辑外部表。
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editExt")
	@Action(description = "编辑外部表")
	public ModelAndView editExt(HttpServletRequest request) throws Exception {
		Long tableId = RequestUtil.getLong(request, "tableId");
		String returnUrl = RequestUtil.getPrePage(request);
		return getAutoView().addObject("tableId", tableId).addObject(
				"returnUrl", returnUrl);
	}

	/**
	 * 删除外部表定义。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("delTableById")
	@Action(description = "删除外部表定义表",
			execOrder=ActionExecOrder.BEFORE,
			detail="<#assign entity=bpmFormTableService.getById(Long.valueOf(tableId))/>" +
					"删除外部表定义表: ${entity.tableDesc}【${entity.tableName}】" 
	)
	public void delTableById(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResultMessage resultMessage;
		String preUrl = RequestUtil.getPrePage(request);
		try {
			Long tableId = RequestUtil.getLong(request, "tableId");
			service.delExtTableById(tableId);
			resultMessage = new ResultMessage(ResultMessage.Success, "删除表定义成功!");
		} catch (Exception ex) {
			resultMessage = new ResultMessage(ResultMessage.Fail, "删除表定义失败!");
		}
		addMessage(resultMessage, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 取得自定义表明细
	 * 
	 * @param request
	 * @param response
	 * @param tableId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看自定义表明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long tableId = RequestUtil.getLong(request, "tableId");
		Long canClose = RequestUtil.getLong(request, "canClose");
		ModelAndView mv = getAutoView();
		BpmFormTable table = service.getById(tableId);
		List<BpmFormField> fields = bpmFormFieldService.getByTableId(tableId);
		mv.addObject("table", table).addObject("fields", fields);
		String mainTable = ContextUtil.getMessages("controller.bpmFormTable.unallocated");
		if (table.getIsMain() == 1) {
			List<BpmFormTable> subList = service.getSubTableByMainTableId(tableId);
			mv.addObject("subList", subList);
		} else {
			Long mainTableId = table.getMainTableId();
			if (mainTableId > 0) {
				BpmFormTable tb = service.getById(mainTableId);
				if (tb.getIsMain() == 1)
					mainTable = tb.getTableName();
				mv.addObject("mainTable", mainTable);
			}
		}
		return mv.addObject("canClose", canClose).addObject("canReturn", 0);
	}

	/**
	 * 取得自定义表明细
	 * 
	 * @param request
	 * @param response
	 * @param tableId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getOfLink")
	@Action(description = "查看自定义表明细")
	public ModelAndView getOfLink(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long tableId = RequestUtil.getLong(request, "tableId");
		Long canReturn = RequestUtil.getLong(request, "canReturn", 1);
		ModelAndView mv = new ModelAndView("/platform/form/bpmFormTableGet.jsp");
		BpmFormTable table = service.getById(tableId);
		if (table != null) {
			List<BpmFormField> fields = bpmFormFieldService.getByTableId(tableId);
			mv.addObject("table", table).addObject("fields", fields);
			String mainTable = ContextUtil.getMessages("controller.bpmFormTable.unallocated");
			if (table.getIsMain() == 1) {
				List<BpmFormTable> subList = service.getSubTableByMainTableId(tableId);
				mv.addObject("subList", subList);
			} else {
				Long mainTableId = table.getMainTableId();
				if (mainTableId > 0) {
					BpmFormTable tb = service.getById(mainTableId);
					if (tb.getIsMain() == 1)
						mainTable = tb.getTableName();
					mv.addObject("mainTable", mainTable);
				}
			}
		}
		return mv.addObject("canReturn", canReturn);
	}

	/**
	 * 生成表。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("generateTable")
	@Action(description = "生成表",
			detail="生成表【${SysAuditLinkService.getBpmFormTableLink(Long.valueOf(tableId))}】"
	)
	public void publish(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResultMessage resultMessage = null;
		PrintWriter out = response.getWriter();
		Long tableId = RequestUtil.getLong(request, "tableId");
		BpmFormTable table = service.getByTableId(tableId,1);
		try {
			service.generateTable(tableId, ContextUtil.getCurrentUser()
					.getFullname());
			
			resultMessage = new ResultMessage(ResultMessage.Success, null);
		} catch (Exception e) {
			e.printStackTrace();
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,
						"生成自定义表失败:" + str);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
			}
		}
		out.print(resultMessage);
		CreateFunc(table);
	}
	
	
	//生成对应的方法
	private void CreateFunc(BpmFormTable table1){
		//++++++++++++++++++++++++++++++++++生成方法对应的sql语句++++++++++++++++++++++++++++++++++------------start
	
		
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
	    		ss = ss + "{\"field\":"+ "\""+s.getFieldName()+"\""+ ",\"comment\":"+"\""+s.getFieldDesc()+"\""+"}"+",";
	    	}
	    	else  
	    		ss = ss + "{\"field\":"+ "\""+s.getFieldName()+"\""+ ",\"comment\":"+"\""+s.getFieldDesc()+"\""+"}"+",";
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
		//[{"field":"ID","comment":"主键","condition":"=","dbType":"","defaultType":"1","defaultValue":"","paraCt":"1"}]
		dbFunc.setConditionField("[{\"field\":"+ "\""+"ID"+"\""+ ",\"comment\":"+"\""+"ASC"+"\""+",\"condition\":"+"\""+"="+"\""+",\"dbType\":"+"\""+""+"\""+",\"defaultType\":"+"\""+"1"+"\""+",\"defaultValue\":"+"\""+""+"\""+",\"paraCt\":"+"\""+"1"+"\""+"}"+"]");
		dbFunc.setSortField("[]");
		dbFunc.setSettingField("[]");
		dbFunc.setDisplayField(ss);
		
		dbFunc.setDsName("LOCAL");
		dbFunc.setDs_Alias("LOCAL");
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
//		dbFunc1.setConditionField(mohu);
		dbFunc1.setSortField("[{\"field\":"+ "\""+"ID"+"\""+ ",\"comment\":"+"\""+"ASC"+"\""+"}"+"]");
		dbFunc1.setSettingField("[]");
		dbFunc1.setDisplayField(ss);
		dbFunc1.setConditionField("[]");
		
		dbFunc1.setDsName("LOCAL");
		dbFunc1.setDs_Alias("LOCAL");
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
		dbFunc2.setConditionField("[{\"field\":"+ "\""+"ID"+"\""+ ",\"comment\":"+"\""+"ASC"+"\""+",\"condition\":"+"\""+"="+"\""+",\"dbType\":"+"\""+""+"\""+",\"defaultType\":"+"\""+"1"+"\""+",\"defaultValue\":"+"\""+""+"\""+",\"paraCt\":"+"\""+"1"+"\""+"}"+"]");
		dbFunc2.setSortField("[]");
		dbFunc2.setSettingField("[]");
		dbFunc2.setDisplayField(ss);
		dbFunc2.setDsName("LOCAL");
		dbFunc2.setDs_Alias("LOCAL");
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
		dbFunc3.setDsName("LOCAL");
		dbFunc3.setDs_Alias("LOCAL");
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
		dbFunc4.setConditionField("[{\"field\":"+ "\""+"ID"+"\""+ ",\"comment\":"+"\""+"ASC"+"\""+",\"condition\":"+"\""+"="+"\""+",\"dbType\":"+"\""+""+"\""+",\"defaultType\":"+"\""+"1"+"\""+",\"defaultValue\":"+"\""+""+"\""+",\"paraCt\":"+"\""+"1"+"\""+"}"+"]");
		dbFunc4.setSortField("[]");
		dbFunc4.setSettingField(ss);
		dbFunc4.setDisplayField(ss);
		
		dbFunc4.setDsName("LOCAL");
		dbFunc4.setDs_Alias("LOCAL");
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
		
		/*DbResult dbResult  =  new DbResult();
		dbResult.setId(id);
		dbResult.setTable_name(table1.getTableName());
		dbResult.setClass_name("columns");
		dbResult.setPro_col("[{"+"\"id\""+":"+"\"ID\""+","+ss1+"}]");
		dbResultService.add(dbResult);
		System.out.println("插入columns成功");*/
		
		
		//++++++++++++++++++++++++++++++++++生成方法对应的sql语句++++++++++++++++++++++++++++++++++------------end
		
	}

	/**
	 * 查看子表
	 * 
	 * @param request
	 * @param response
	 * @param tableId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("subTable")
	@Action(description = "查看子表")
	public ModelAndView subTable(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("tableId") Long tableId)
			throws Exception {

		BpmFormTable table = service.getById(tableId);
		List<BpmFormTable> subTables = service
				.getSubTableByMainTableId(tableId);

		return getAutoView().addObject("table", table).addObject("subTables",
				subTables);
	}

	/**
	 * 关联子表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("linkSubtable")
	public void linkSubtable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultMessage resultMessage = null;
		PrintWriter out = response.getWriter();
		try {
			Long mainTableId = RequestUtil.getLong(request, "mainTableId");
			Long subTableId = RequestUtil.getLong(request, "subTableId");
			String fkField = request.getParameter("fkField");
			service.linkSubtable(mainTableId, subTableId, fkField);
			resultMessage = new ResultMessage(ResultMessage.Success, "关联表成功!");
		} catch (Exception e) {
			e.printStackTrace();
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail, "关联表失败:" + str);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
			}
		}
		out.print(resultMessage);
	}

	/**
	 * 移除子表关系
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("unlinkSubTable")
	@Action(description = "移除子表关系")
	public void unlinkSubTable(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("subTableId") Long subTableId) throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		service.unlinkSubTable(subTableId);
		response.sendRedirect(preUrl);
	}

	@ResponseBody
	@RequestMapping("getExtTableByTableId")
	public Map<String, Object> getExtTableByTableId(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("tableId") Long tableId)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		BpmFormTable table = service.getById(tableId);
		// 取得字段列表
		List<BpmFormField> fieldList = bpmFormFieldService
				.getAllByTableId(tableId);

		List<Identity> identityList = identityService.getAll();
		// 验证规则
		List<BpmFormRule> validRuleList = bpmFormRuleService.getAll();
		// 通过子表获得可关联的主表
		map.put("table", table);
		map.put("fieldList", fieldList);

		map.put("identityList", identityList);

		map.put("validRuleList", validRuleList);

		return map;
	}

	/**
	 * 获得所有未分配的子表
	 * 
	 * @param request
	 * @param response
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getAllUnassignedSubTable")
	public List<BpmFormTable> getAllUnassignedSubTable(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam("tableName") String tableName) throws Exception {
		List<BpmFormTable> allUnassignedSubTable = service
				.getAllUnassignedSubTable();
		return allUnassignedSubTable;
	}

	/**
	 * 删除自定义表。 如果表已经定义表单，那么表不可以删除。
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("delByTableId")
	@Action(description="删除自定义表",
			execOrder=ActionExecOrder.BEFORE,
			detail="<#assign entity=bpmFormTableService.getById(Long.valueOf(tableId))/>" +
					"自定义表: ${entity.tableDesc}【${entity.tableName}】," +
					"<#if !isTableHasFormDef>" +
						"已删除" +
				   "<#else>不能删除</#if>"
	)
	public void delByTableId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Long[] aryTableId = RequestUtil.getLongAryByStr(request, "tableId");
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
	    TableParcel tableparcel=new TableParcel();
		boolean rtn = false;
		for (Long tableId : aryTableId) {
			System.out.println("TABLEID:"+tableId);
			BpmFormTable table = service.getById(tableId);
			Long fatherTableId = 0L;
			if(BeanUtils.isNotEmpty(table)){
				fatherTableId = table.getMainTableId();
			}
			// 删除的时候 判断是否是子表 ，并且判断子表的父表是否绑定表单
			if (BeanUtils.isNotEmpty(fatherTableId) && fatherTableId != 0) {
				rtn = bpmFormDefService.isTableHasFormDef(fatherTableId);
				if (!rtn) {
					// 表未定义表单，可删除
				
					String tablename=service.getById(tableId).getTableName();
					System.out.println("tablename:"+tablename);
					service.delByTableId(tableId);
					List<TableParcel> list=tableParcelService.getParcelsbyTableName(tablename);
					if(list!=null)
					{for(int i=0;i<list.size();i++)
					{
						tableParcelService.delById(list.get(i).getId());
					}
					}
					
					
				} else if (BeanUtils.isEmpty(message)) {
					message = new ResultMessage(ResultMessage.Fail, "该子表的父表已定义表单,不能删除!");
				}
			} else {
				rtn = bpmFormDefService.isTableHasFormDef(tableId);
				SysAuditThreadLocalHolder.putParamerter("isTableHasFormDef", rtn);
				if (!rtn) {
					// 表未定义表单，可删除
					
                    String tablename=service.getById(tableId).getTableName();
                	service.delByTableId(tableId);
					List<TableParcel> list=tableParcelService.getParcelsbyTableName(tablename);
					if(list!=null)
					{	for(int i=0;i<list.size();i++)
					{
						tableParcelService.delById(list.get(i).getId());
					}
					}
				} else if (BeanUtils.isEmpty(message)) {
					message = new ResultMessage(ResultMessage.Fail, "该表已定义表单不能删除!");
				}
			}
		}
		if (BeanUtils.isEmpty(message)) {
			message = new ResultMessage(ResultMessage.Success, "表定义已成功删除!");
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 删除扩展表。
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("delExtTableById")
	@Action(description="删除扩展表",
			execOrder=ActionExecOrder.BEFORE,
			detail="<#assign entity=bpmFormTableService.getById(Long.valueOf(tableId))/>" +
					"扩展表: ${entity.tableDesc}【${entity.tableName}】," +
					"<#if isTableHasFormDef>" +
						"已删除" +
				   "<#else>不能删除</#if>"
	)
	public void delExtTableById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Long tableId = RequestUtil.getLong(request, "tableId");
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		boolean rtn = bpmFormDefService.isTableHasFormDef(tableId);
		SysAuditThreadLocalHolder.putParamerter("isTableHasFormDef", rtn);
		if (rtn) {
			message = new ResultMessage(ResultMessage.Fail, "该表已定义表单不能删除!");
			addMessage(message, request);
		} else {
			service.delExtTableById(tableId);
			message = new ResultMessage(ResultMessage.Success, "表定义已成功删除!");
			addMessage(message, request);
		}
		response.sendRedirect(preUrl);
	}

	/**
	 * 分组设置
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("team")
	@Action(description = "分组设置")
	public ModelAndView team(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long tableId = RequestUtil.getLong(request, "tableId");
		BpmFormTable bpmFormTable = service.getById(tableId);

		return this.getAutoView().addObject("bpmFormTable", bpmFormTable);

	}

	/**
	 * 分组设置保存
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("teamSave")
	@Action(description = "分组设置保存",
			detail ="保存自定义表【${SysAuditLinkService.getBpmFormTableLink(Long.valueOf(tableId))}】分组" 
	)
	public String teamSave(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String json = RequestUtil.getString(request, "json");
		Long tableId = RequestUtil.getLong(request, "tableId");

		BpmFormTable bpmFormTable = service.getById(tableId);
		JSONObject jsonObject = new JSONObject();
		try {
			bpmFormTable.setTeam(json);
			service.update(bpmFormTable);
			jsonObject.accumulate("success", true).accumulate("msg", ContextUtil.getMessages("保存成功!"));
		} catch (Exception e) {
			jsonObject.accumulate("success", false).accumulate("msg", ContextUtil.getMessages("保存失败!") + ":" + e.getMessage());
		}
		return jsonObject.toString();
	}

	/**
	 * 取得表fields, 如果是主表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getFieldsByTableId")
	public String getFieldsByTableId(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("tableId") Long tableId)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		List<BpmFormField> bpmFormFieldList = bpmFormFieldService
				.getByTableId(tableId);
		sb.append("{fields:");
		JSONArray jArray = (JSONArray) JSONArray.fromObject(bpmFormFieldList);
		sb.append(jArray.toString()).append("}");

		return sb.toString();
	}

	/**
	 * 复制表。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("copyTable")
	@Action(description = "复制自定义表")
	public 	ModelAndView  copyTable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long tableId = RequestUtil.getLong(request, "tableId");
		BpmFormTable bpmFormTable = service.getByTableId(tableId, 0);
		Boolean isSubTable = false;
		if (BeanUtils.isNotEmpty(bpmFormTable) && BeanUtils.isNotEmpty(bpmFormTable.getSubTableList()))
			isSubTable = true;
		return this.getAutoView().addObject("bpmFormTable", bpmFormTable).addObject("isSubTable", isSubTable);
	}

	/**
	 * 复制表。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("saveCopy")
	@Action(description = "保存复制自定义表",
			detail="<#list jsonArray as item>" +
						"<#if item_index==0>" +
							"复制自定义表【${SysAuditLinkService.getBpmFormTableLink(Long.valueOf(item.tableId))}】," +
							"复制的自定义表名为【${item.tableName}】,表注释为【${item.tableDesc}】   " +
						"<#else>" +
							"复制子表【${SysAuditLinkService.getBpmFormTableLink(Long.valueOf(item.tableId))}】," +
							"复制的子表名【${item.tableName}】,子表注释【${item.tableDesc}】         " +
						"</#if>" +
					"</#list>"
	)
	public 	void  saveCopy(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String json = RequestUtil.getString(request, "json");
		if(StringUtil.isEmpty(json)) return;
		PrintWriter writer = response.getWriter();		
		try {
			Boolean flag = false;
			JSONArray jsonArray = JSONArray.fromObject(json);
			SysAuditThreadLocalHolder.putParamerter("jsonArray", jsonArray);
			String[] nameArray = new String[jsonArray.size()];
			for (int i = 0; i < jsonArray.size(); i++) {
				Object object = jsonArray.get(i);
				JSONObject jsonObject = JSONObject.fromObject(object);
				String tableName = (String) jsonObject.get("tableName");
				if (i > 0) {
					Boolean b = ArrayUtils.contains(nameArray, tableName);
					if (b)
						flag = true;
				}
				nameArray[i] = tableName;
			}
			if (!flag) {
				service.saveCopy(json);
				writeResultMessage(writer, ContextUtil.getMessages("保存成功!"), ResultMessage.Success);
			} else {
				writeResultMessage(writer, ContextUtil.getMessages("保存失败"), ResultMessage.Fail);
			}

		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(
						ResultMessage.Fail, ContextUtil.getMessages("controller.bpmFormTable.saveCopy.fail")+":" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				ResultMessage resultMessage = new ResultMessage(
						ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}

	/**
	 * 分配主表。 取得未分配的主表。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("assignMainTable")
	public ModelAndView assignMainTable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long subTableId = RequestUtil.getLong(request, "subTableId");

		BpmFormTable bpmFormTable = service.getById(subTableId);

		String dataSource = bpmFormTable.getDsAlias();
		String tableName = bpmFormTable.getTableName();

		BaseTableMeta meta = TableMetaFactory.getMetaData(dataSource);
		TableModel tableModel = meta.getTableByName(tableName);
		// 取得字段列表
		List<BpmFormField> fieldList = convertFieldList(tableModel);

		List<BpmFormTable> mainTableList = service.getMainTableSubTableId(subTableId);// service.getAssignableMainTable();
		ModelAndView mv = getAutoView();
		mv.addObject("mainTableList", mainTableList).addObject("subTableId", subTableId).addObject("fieldList", fieldList);
		return mv;

	}

	/**
	 * 导出选择导出xml
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("export")
	@Action(description = "导出选择导出xml")
	public ModelAndView export(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableIds = RequestUtil.getString(request, "tableIds");

		ModelAndView mv = this.getAutoView();
		mv.addObject("tableIds", tableIds);
		return mv;
	}

	/**
	 * 导出表定义xml。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportXml")
	@Action(description = "导出自定义表",
			detail="导出自定义表:" +
					"<#list StringUtils.split(tableIds,\",\") as item>" +
					"<#assign entity=bpmFormTableService.getById(Long.valueOf(item))/>" +
					"【${entity.tableDesc}(${entity.tableName})】" +
					"</#list>")
	public void exportXml(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long[] tableIds = RequestUtil.getLongAryByStr(request, "tableIds");
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("bpmFormTable", true);
		map.put("bpmFormField", true);
		map.put("subTable", RequestUtil.getBoolean(request, "subTable"));
		map.put("identity", RequestUtil.getBoolean(request, "identity"));

		if (BeanUtils.isEmpty(tableIds))
			return;
		try {
			String strXml = service.exportXml(tableIds, map);
			String fileName = DateFormatUtil.getNowByString("yyyyMMddHHmmdd");
			if (tableIds.length == 1) {
				BpmFormTable formtable = service.getById(tableIds[0]);
				fileName = formtable.getTableDesc() + "_" + fileName;
			} else
				fileName = "多条表记录_" + fileName;
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
	 * 导入表的XML。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("importXml")
	@Action(description = "导入自定义表")
	public void importXml(MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MultipartFile fileLoad = request.getFile("xmlFile");
		ResultMessage message = null;
		try {
			service.importXml(fileLoad.getInputStream());
			message = new ResultMessage(ResultMessage.Success,
					MsgUtil.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			message = new ResultMessage(ResultMessage.Fail, "导入出错了，请检查导入格式是否正确或者导入的数据是否有问题！");
		}
		writeResultMessage(response.getWriter(), message);
	}

	/**
	 * 获取流程变量树
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getSubTree")
	@ResponseBody
	public String getSubTree(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ctx=request.getContextPath();
		Long tableId = RequestUtil.getLong(request, "tableId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		String actDefId = RequestUtil.getString(request, "actDefId");
		String parentActDefId = RequestUtil.getString(request, "parentActDefId", "");
		StringBuffer sb = new StringBuffer("[");

		if (tableId.longValue() == 0L) {
			sb.append("]");
			return sb.toString();
		}
		List<BpmFormTable> list = service.getSubTableByMainTableId(tableId);
		
		sb.append("{id:").append("-1")
		.append(",isTable:").append("1").append("")
		.append(",name:\"").append("中间业务表").append("\"")
		.append(",icon:\"").append("").append("\"")
		.append(",showName: \"").append("中间业务表")
		.append("\",children:[" );
		
		sb.append("{id:").append("\"b.BUS_FLOW_RUNID\"")
				.append(",isTable:").append("0").append("")
				.append(",fieldType:\"").append("number").append("\"")
				.append(",name:\"").append("b.BUS_FLOW_RUNID").append("\"")
				.append(",icon:\"").append("").append("\"")
				.append(",showName: \"").append("流程运行ID")
				.append("\"},");
		
		sb.append("{id:").append("\"b.BUS_CREATOR_ID\"")
		.append(",isTable:").append("0").append("")
		.append(",fieldType:\"").append("number").append("\"")
		.append(",name:\"").append("b.BUS_CREATOR_ID").append("\"")
		.append(",icon:\"").append("").append("\"")
		.append(",showName: \"").append("创建人ID")
		.append("\"},");
		
		sb.append("{id:").append("\"b.BUS_ORG_ID\"")
		.append(",isTable:").append("0").append("")
		.append(",fieldType:\"").append("number").append("\"")
		.append(",name:\"").append("b.BUS_ORG_ID").append("\"")
		.append(",icon:\"").append("").append("\"")
		.append(",showName: \"").append("组织ID")
		.append("\"}");
		
		
		sb.append("]},");

		for (BpmFormTable bpmFormTable : list) {
			List<BpmFormField> bpmFormFieldList = bpmFormFieldService.getByTableId(bpmFormTable.getTableId());

			String sign = "<img src='" + ctx + "/styles/tree/no.png'>";
			BpmSubtableRights bpmSubtableRights = null;
			if (StringUtil.isEmpty(parentActDefId)) {
				bpmSubtableRights = bpmSubtableRightsService.getByDefIdAndNodeId(actDefId, nodeId, bpmFormTable.getTableId());
			} else {
				bpmSubtableRights = bpmSubtableRightsService.getByDefIdAndNodeId(actDefId, nodeId, bpmFormTable.getTableId(), parentActDefId);
			}

			if (bpmSubtableRights != null) {
				sign = "<img src='" + ctx + "/styles/tree/yes.png'>";
			}
			
			sb.append("{id:").append(bpmFormTable.getTableId())
					.append(",isTable:").append("1").append("")
					.append(",name:\"").append(bpmFormTable.getTableName()).append("\"")
					.append(",icon:\"").append("").append("\"")
					.append(",img:\"").append(sign).append("\"")
					.append(",showName: \"").append(bpmFormTable.getTableName())
					.append("(" + bpmFormTable.getTableDesc() + ")").append(sign)
					.append("\",children:[");
			
			
			
			for (BpmFormField bpmFormField : bpmFormFieldList) {
				sb.append("{id:").append(bpmFormField.getFieldId())
				.append(",isTable:").append("0").append("")
				.append(",fieldType:\"").append(bpmFormField.getFieldType()).append("\"")
				.append(",name:\"a.f_").append(bpmFormField.getFieldName()).append("\"")
				.append(",icon:\"").append("").append("\"")
				.append(",showName: \"").append(bpmFormField.getFieldName())
				.append("(" + bpmFormField.getFieldDesc() + ")")
				.append("\"},");
			}

			if (!list.isEmpty()) {
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append("]},");

		}
		if (!list.isEmpty()) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * 比较两个列表是否相等。在比较两个列表的元素时，比较的方式为(o==null ? e==null : o.equals(e)).
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	private boolean isListEqual(List list1, List list2) {
		if (list1.size() != list2.size()) {
			return false;
		}
		if (list1.containsAll(list2)) {
			return true;
		}
		return false;
	}

	/**
	 * 重置自定义表。删除已生成的自定义物理表、删除标记为删除的字段。<br/>
	 * 1、已经绑定表单的自定义表不能重置
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("reset")
	@Action(description="重置自定义表",
			detail=	"重置自定义表【SysAuditLinkService.getBpmFormTableLink(Long.valueOf(tableId))}】"
	)
	public void reset(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long tableId = RequestUtil.getLong(request, "tableId");
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		boolean rtn = bpmFormDefService.isTableHasFormDef(tableId);
		if (rtn) {
			message = new ResultMessage(ResultMessage.Fail, "已绑定表单，不能重置！");
		} else {
			service.reset(tableId);
			message = new ResultMessage(ResultMessage.Success, "重置成功");
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 创建视图页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("createView")
	public ModelAndView createView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysDataSourceL> sysDataSourceLs = AppUtil.getBean(SysDataSourceLService.class).getAll();
		return getAutoView().addObject("dsList", sysDataSourceLs);
	}

	/**
	 * 创建视图
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("create")
	public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String dsalias = RequestUtil.getString(request, "dsalias");
		String sql = RequestUtil.getString(request, "sqlView", false);
		String viewName = RequestUtil.getString(request, "viewName");
		ResultMessage message = null;
		try {
			IDbView dbView = TableMetaFactory.getDbView(dsalias);
			dbView.createOrRep(viewName, sql);
			message = new ResultMessage(ResultMessage.Success, "创建视图成功");
		} catch (Exception e) {
			message = new ResultMessage(ResultMessage.Fail, "创建视图失败：" + e.getMessage());
		}
		writeResultMessage(response.getWriter(), message);
	}

	/**
	 * 验证创建视图查询语句 是否正确
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("validSql")
	@ResponseBody
	public boolean validSql(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sql = RequestUtil.getString(request, "sql", false);
		String dsalias = RequestUtil.getString(request, "dsalias");
		try {
			// JdbcHelper<Map<String, Object>, ?> jdbcHelper= ServiceUtil.getJdbcHelper(dsalias);
			// jdbcHelper.queryForList(sql, new HashMap());
			DbContextHolder.setDataSource(dsalias);
			JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).queryForList(sql, new HashMap());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 根据表ID和字段名称取得对应字段的数据（没有隐藏字段）。
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getFieldByTidFnaNh")
	public void getFieldByTidFnaNh(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		try {
			String fieldName = RequestUtil.getString(request, "fieldName", "");
			long tableId = RequestUtil.getLong(request, "tableId", 0);
			String subTableName = RequestUtil.getString(request, "subTableName", "");
			BpmFormField field = null;
			if (StringUtil.isNotEmpty(fieldName) && tableId > 0) {
				field = bpmFormFieldService.getFieldByTidFnaNh(tableId, fieldName, subTableName);
				if (BeanUtils.isNotEmpty(field)) {
					JSONObject obj = new JSONObject().fromObject(field);
					writer.print(obj.toString().replaceAll(":null", ":\"\""));
				} else {
					writer.print("");
				}
			} else {
				writer.print("");
			}

		} catch (Exception e) {
			logger.warn(e.getMessage());
			writer.print("");
		} finally {
			writer.close();
		}
	}

	@ResponseBody
	@RequestMapping("syncToExtTable")
	public Map<String, Object> syncToExtTable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] fieldNames =RequestUtil.getStringAryByStr(request, "fieldNames");
		String dataSource = RequestUtil.getString(request, "dataSource");
		String tableName = RequestUtil.getString(request, "tableName");
		BaseTableMeta meta = TableMetaFactory.getMetaData(dataSource);
		TableModel tableModel = meta.getTableByName(tableName);

		// 取得外部表的字段列表
		List<BpmFormField> extTableFieldList = convertFieldList(tableModel);

		Map<String, Object> map = new HashMap<String, Object>();
		// 将原先的所有字段删除掉，直接使用数据库同步出来的字段
		map.put("addFields", extTableFieldList);
		map.put("removeFields", Arrays.asList(fieldNames));
		return map;
	}

	/**
	 * 获取表的所有字段信息
	 * 
	 * @param request
	 * @param response
	 * @param tableId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getAllFieldsByTableId")
	public String getAllFieldsByTableId(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("tableId") Long tableId)
			throws Exception {
		BpmFormTable bpmFormTable = service.getTableById(tableId);

		JSONObject json = JSONObject.fromObject(bpmFormTable);
		return json.toString();
	}
	
	@ResponseBody
	@RequestMapping("getJsonByTableId")
	public String getJsonByTableId(HttpServletRequest request){
		long tableId= RequestUtil.getLong(request, "tableId", 0);
		BpmFormTable bpmFormTable=service.getTableById(tableId, 1);
		JSONObject jsonTable=getByTable(bpmFormTable);
		
		JSONArray children= jsonTable.getJSONArray("children");
		
		List<BpmFormTable> formTables= bpmFormTable.getSubTableList();
		if(BeanUtils.isNotEmpty(formTables)){
			for(BpmFormTable table:formTables){
				JSONObject subTable= getByTable(table);
				children.add(subTable);
			}
		}
		return jsonTable.toString();
	}
	
	/**
	 * 根据表获取JSONObject 对象。
	 * @param bpmFormTable
	 * @return
	 */
	private JSONObject getByTable(BpmFormTable bpmFormTable){
		int isMain=bpmFormTable.getIsMain().intValue();
		JSONObject jsonTable=getJsonObj(bpmFormTable.getTableName(),bpmFormTable.getTableName(),bpmFormTable.getTableDesc(),1,isMain,"",bpmFormTable.isExtTable());
		JSONArray aryField=new JSONArray();
		
		List<BpmFormField> mainList= bpmFormTable.getFieldList();
		for(BpmFormField field:mainList){
			JSONObject json=getJsonObj(bpmFormTable.getTableName(),field.getFieldName(),field.getFieldDesc(),0,isMain,field.getFieldType(),bpmFormTable.isExtTable());
			aryField.add(json);
		}
		jsonTable.accumulate("children", aryField);
		
		return jsonTable;
	}
	
	private JSONObject getJsonObj(String tableName, String name,String comment,int isTable,int isMain,String dataType,boolean isExternal){
		JSONObject json=new JSONObject();
		json.accumulate("name", name)
		.accumulate("comment", comment)
		.accumulate("tableName", tableName)
		.accumulate("isTable", isTable)
		.accumulate("isMain", isMain)
		.accumulate("isExternal", isExternal?1:0)
		.accumulate("dataType", dataType);
		return json;
	}
}
