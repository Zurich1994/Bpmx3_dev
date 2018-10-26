package com.hotent.platform.controller.form;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.activiti.engine.impl.util.json.JSONStringer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.PinyinUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.tableParcel.model.tableParcel.TableParcel;
import com.hotent.tableParcel.service.tableParcel.TableParcelService;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 对象功能:自定义表 控制器类 开发公司:广州宏天软件有限公司 开发人员:xwy 创建时间:2011-11-30 14:29:22
 */
@Controller
@RequestMapping("/platform/form/bpmFormTable/")
@Action(ownermodel=SysAuditModelType.FORM_MANAGEMENT)
public class BpmFormTableFormController extends BaseFormController {
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private TableParcelService tableParcelService;

	/**
	 * 添加自定义表。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("saveTable")
	@Action(description="添加自定义表",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if StringUtil.isNotEmpty(isAdd)>" +
						"<#if isAdd==0>添加" +
						"<#else>更新" +
						"</#if>" +
						"自定义表  :【${SysAuditLinkService.getBpmFormTableLink(Long.valueOf(id))}】" +
					"<#else>" +
						"添加或更新自定义表：【表名:${table.tableName}, 表注释:${table.tableDesc}】失败" +
					"</#if>")
	public void saveTable(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String tableJson=request.getParameter("table");
		String fieldsJson=request.getParameter("fields");
		JSONObject jsonObject=JSONObject.fromObject(tableJson);
		ArrayList<String> list = new ArrayList<String>();
		org.activiti.engine.impl.util.json.JSONArray jsonarr=new org.activiti.engine.impl.util.json.JSONArray(fieldsJson);
		for(int i=0;i<jsonarr.length();i++)
		{
			org.activiti.engine.impl.util.json.JSONObject jso= jsonarr.getJSONObject(i);
			String field=jso.getString("fieldName");
			String comment=jso.getString("fieldDesc");
		list.add("{\"field\":\""+field+"\",\"comment\":\""+comment+"\"}");
        }
		JSONArray jsonarray = JSONArray.fromObject(list); 
		
		
		String tablename=jsonObject.getString("tableName");
		String tabledesc=jsonObject.getString("tableDesc");
		TableParcel tableparcel=new TableParcel();
		
		Long id=UniqueIdUtil.genId();
		
		tableparcel.setId(id);
		tableparcel.setParcel_alias(tabledesc);
		tableparcel.setParcel_name(tablename);
		tableparcel.setTable_name(tablename);
		tableparcel.setParcel_value(jsonarray.toString());
		tableParcelService.add(tableparcel);		
				
	    Long categoryId = RequestUtil.getLong(request, "categoryId", null);		
		int generator = RequestUtil.getInt(request, "generator");
		int isadd= 0;
		try {
			List<BpmFormField> fieldList=getByFormFieldJson(fieldsJson);
			JSONObject tableJsonObj = JSONObject.fromObject(tableJson);
			
			tableJsonObj.remove("createtime");
			tableJsonObj.remove("publishTime");
			if(categoryId != null){
				tableJsonObj.accumulate("categoryId", categoryId);
			}

			BpmFormTable table = (BpmFormTable) JSONObject.toBean(tableJsonObj,BpmFormTable.class);			
			table.setFieldList(fieldList);
			String msg = "";
			
			//系统日志参数
			try {
				SysAuditThreadLocalHolder.putParamerter("table", table);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
			
			if (table.getTableId() == 0) {
				if (bpmFormTableService.isTableNameExisted(table.getTableName())) {
					msg = "表名已存在";
					writeResultMessage(response.getWriter(), msg,ResultMessage.Fail);
					return;
				}

				int rtn = bpmFormTableService.addFormTable(table);
				if (rtn == -1) {
					msg = "字段中存在"+TableModel.CUSTOMER_COLUMN_CURRENTUSERID+"字段 ";
					writeResultMessage(response.getWriter(), msg,ResultMessage.Fail);
					return;
				}
				isadd=1;
				msg = "保存自定义表成功";
			} else {
				boolean isExist = bpmFormTableService.isTableNameExistedForUpd(table.getTableId(), table.getTableName());
				if (isExist) {
					//"输入的表名在系统中已经存在!"
					msg = "表名已存在";
					writeResultMessage(response.getWriter(), msg,ResultMessage.Fail);
					return;
				}

				int rtn = bpmFormTableService.upd(table,generator);
				if (rtn == -1) {
					msg = "字段中存在"+TableModel.CUSTOMER_COLUMN_CURRENTUSERID+"字段 ";
					writeResultMessage(response.getWriter(), msg,ResultMessage.Fail);
					return;
				} else if (rtn == -2) {
					//自定义数据表中已经有数据，字段不能设置为非空，请检查添加的字段!
					msg = "表中已经有数据，字段不能设置为非空，请检查添加的字段！";
					writeResultMessage(response.getWriter(), msg,ResultMessage.Fail);
					return;
				} else if (rtn == 0) {
					msg = "更新自定义表成功";
				}
			}
			// 是否需要为已生成的主表 生成新的子表
			if (generator == 1) {
				bpmFormTableService.generateTable(table.getTableId(),ContextUtil.getCurrentUser().getFullname());
			}
			SysAuditThreadLocalHolder.putParamerter("isAdd", String.valueOf(isadd));
			SysAuditThreadLocalHolder.putParamerter("id", table.getTableId().toString());
			writeResultMessage(response.getWriter(), msg, ResultMessage.Success);
		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, "更新失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	
	/**
	 * 根据字段的JSON返回字段列表。
	 * @param fieldsJson
	 * @return
	 */
	private List<BpmFormField> getByFormFieldJson(String fieldsJson){
		JSONArray aryJson = JSONArray.fromObject(fieldsJson);
		List<BpmFormField> list = new ArrayList<BpmFormField>();
		for(Object obj : aryJson){
			JSONObject fieldJObject = (JSONObject)obj;
			String options = "";
			String ctlProperty="";
			if (fieldJObject.containsKey("options")) {
				options = fieldJObject.getString("options");
				fieldJObject.remove("options");
			}
			
			if (fieldJObject.containsKey("ctlProperty")) {
				ctlProperty = fieldJObject.getString("ctlProperty");
				fieldJObject.remove("ctlProperty");
				
			}
			BpmFormField bpmFormField = (BpmFormField)JSONObject.toBean(fieldJObject,BpmFormField.class);
			bpmFormField.setOptions(options);
			bpmFormField.setCtlProperty(ctlProperty);
			bpmFormField.setFieldName(StringUtil.trim(bpmFormField.getFieldName(), " "));
			
			list.add(bpmFormField);
		}
	
		return list;
	}

	@RequestMapping("saveExtTable")
	public void saveExtTable(HttpServletRequest request,HttpServletResponse response) throws Exception {

		String tableJson = request.getParameter("table");
		String fieldsJson = request.getParameter("fields");

		BpmFormTable table = (BpmFormTable) JSONObject.toBean(JSONObject.fromObject(tableJson), BpmFormTable.class);
		//表明转成小写
		table.setTableName(table.getTableName().toLowerCase());
		List<BpmFormField> list= getByFormFieldJson(fieldsJson);
		table.setFieldList(list);
	
		String msg = "";
		try {
			if (table.getTableId() == 0) {
				String tableName = table.getTableName();
				String dsAlias = table.getDsAlias();
				if (bpmFormTableService.isTableNameExternalExisted(tableName,dsAlias)) {
					//"表名已存在"
					msg = "表名已存在";
					writeResultMessage(response.getWriter(), msg,ResultMessage.Fail);
					return;
				}
				bpmFormTableService.addExt(table);
				msg = "保存外部表成功";
				writeResultMessage(response.getWriter(), msg,ResultMessage.Success);
			}
			else {
				bpmFormTableService.updExtTable(table);
				writeResultMessage(response.getWriter(), msg,ResultMessage.Success);
			}
		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, "保存外部表失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}

	/**
	 * 通过流程定义标题自动生成流程KEY
	 * 
	 * @param request
	 * @return flowkey
	 * @throws Exception
	 */
	@RequestMapping("getFieldKey")
	public void getFieldKey(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String subject = RequestUtil.getString(request, "subject");
		if (StringUtil.isEmpty(subject))
			return;
		String msg = "";
		String pingyin = PinyinUtil.getPinYinHeadCharFilter(subject);
		msg = pingyin;
		writeResultMessage(response.getWriter(), msg, ResultMessage.Success);
	}

	/**
	 * 通过流程定义标题自动生成流程KEY
	 * 
	 * @param request
	 * @return flowkey
	 * @throws Exception
	 */
	@RequestMapping("getTableKey")
	public void getTableKey(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tableName = RequestUtil.getString(request, "subject");
		Long tableId = RequestUtil.getLong(request, "tableId");
		String msg = "";
		String pingyin = PinyinUtil.getPinYinHeadCharFilter(tableName);
		msg = pingyin;
		try {
			if (tableId == 0) {
				if (bpmFormTableService.isTableNameExisted(pingyin)) {
					msg = "表名已存在";
					writeResultMessage(response.getWriter(), msg,
							ResultMessage.Fail);
					return;
				}
			} else {
				boolean isExist = bpmFormTableService.isTableNameExistedForUpd(
						tableId, pingyin);
				if (isExist) {
					msg = "输入的表名在系统中已经存在!";
					writeResultMessage(response.getWriter(), msg,
							ResultMessage.Fail);
					return;
				}
			}
			writeResultMessage(response.getWriter(), msg, ResultMessage.Success);
		} catch (Exception ex) {
			writeResultMessage(response.getWriter(), ex.getMessage(),
					ResultMessage.Fail);
		}
	}
}
