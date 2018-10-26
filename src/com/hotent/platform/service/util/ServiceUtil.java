package com.hotent.platform.service.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.bpm.setting.ISkipCondition;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.db.JdbcHelper;
import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.model.CurrentUser;
import com.hotent.core.mybatis.Dialect;
import com.hotent.core.mybatis.dialect.DB2Dialect;
import com.hotent.core.mybatis.dialect.DmDialect;
import com.hotent.core.mybatis.dialect.H2Dialect;
import com.hotent.core.mybatis.dialect.MySQLDialect;
import com.hotent.core.mybatis.dialect.OracleDialect;
import com.hotent.core.mybatis.dialect.SQLServer2005Dialect;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.SqlTypeConst;
import com.hotent.core.util.AppConfigUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
//import com.hotent.platform.dao.system.SysDataSourceDao;
import com.hotent.platform.dao.system.SysDataSourceLDao;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysDataSourceL;
import com.hotent.platform.model.system.SysUser;
//import com.hotent.platform.model.system.SysDataSource;
import com.hotent.platform.service.bpm.BpmNodeWebServiceService;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.system.SysPropertyService;
import com.hotent.tableParcel.model.tableParcel.TableParcel;
import com.hotent.tableParcel.service.tableParcel.TableParcelService;

public class ServiceUtil {
	/**
	 * 获取模板路径。
	 * @return
	 */
	@Resource
	private BpmNodeWebServiceService bpmNodeWebServiceService;
	
	public static String getTemplatePath(){
		return FileUtil.getClassesPath() +"template" + File.separator;
	}
	/**
	 * 获取首页模板路径。
	 * @return
	 */
	public static String getIndexTemplatePath(){
		return getTemplatePath() + File.separator+"index"+File.separator;
	}


	
	/**
	 * 获取列表的sql语句
	 * 
	 * @param objectName
	 *            表名或视图名称。
	 * @param displayList
	 *            显示字段列表。
	 * @param conditionList
	 *            条件字段列表。
	 * @param params
	 *            传入的条件列表。
	 * @return
	 */
	public static String getSql(Map<String, Object> map, Map<String, Object> params) {
		String objectName=(String) map.get("objectName");
		List<DialogField> retrunList=(List<DialogField>) map.get("returnList");
		List<DialogField> displayList=(List<DialogField>) map.get("displayList");
		List<DialogField> conditionList=(List<DialogField>) map.get("conditionList");
		List<DialogField> sortList=(List<DialogField>) map.get("sortList");
		String sql = "select ";
		if (BeanUtils.isEmpty(retrunList)) {
			sql = "select a.* from " + objectName + " a";
		} else {
			String returnStr = "";
			for (DialogField dialogField : retrunList) {
				returnStr += "," + dialogField.getFieldName();
			}
			returnStr = returnStr.replaceFirst(",", "");
			sql += returnStr + " from " + objectName;
		}
		String where = getWhere(conditionList, params);

		String orderBy = " order by ";
		if (BeanUtils.isEmpty(displayList)) {
			for(int i=0;i<sortList.size();i++){
				DialogField df = sortList.get(i) ;
				if(i!=0){
					orderBy += ",";
				}
				orderBy += " " + df.getFieldName() + " " + df.getComment();
			}
			if(BeanUtils.isEmpty(sortList)){
				return sql + where;
			}
			return sql + where + orderBy;
		}
		if (params.containsKey("sortField")) {
			orderBy += params.get("sortField");
		} else if(BeanUtils.isEmpty(sortList)){
			orderBy += displayList.get(0).getFieldName();
		}
		//sortField有值或srotList为空
		if(!" order by ".equals(orderBy)){
			if (params.containsKey("orderSeq")) {
				orderBy += " " + params.get("orderSeq");
			} else {
				orderBy += " ASC";
			}
			for(DialogField df:sortList){
				// 添加一个判断  因为sqlserver不允许有重复的order by的字段。否则会报错
				if(!params.get("sortField").equals(df.getFieldName())){
					orderBy += ", " + df.getFieldName() + " " + df.getComment();
				}
			}
		}else{
			//sortField无值以及说sortList不为空
			for(DialogField df:sortList){
				orderBy += df.getFieldName() + " " + df.getComment()+ ",";
			}
		}
		sql = sql + where + orderBy;
		if(sql.trim().endsWith(",")){
			sql=sql.substring(0,sql.length()-1);
		}
		return sql;
	}
	
	/**
	 * 取得流程启动后的查询sql语句（与原来相似）。
	 * author:zonghy
	 */
	public static String getquerySqls(Map<String, Object> map, Map<String, Object> params, ArrayList<Map<String, Object>> inputBindingList,String nodeId) {
		String objectName=(String) map.get("objectName");
		List<DialogField> retrunList=(List<DialogField>) map.get("returnList");
		List<DialogField> displayList=(List<DialogField>) map.get("displayList");
		List<DialogField> conditionList=(List<DialogField>) map.get("conditionList");
		List<DialogField> sortList=(List<DialogField>) map.get("sortList");
		System.out.println("列表数据值"+conditionList);
		String sql = "select ";
		if (BeanUtils.isEmpty(retrunList)) {
			sql = "select a.* from " + objectName + " a";
		} else {
			String returnStr = "";
			for (DialogField dialogField : retrunList) {
				returnStr += "," + dialogField.getFieldName();
			}
			returnStr = returnStr.replaceFirst(",", "");
			sql += returnStr + " from " + objectName;
		}
		System.out.println("map++++++++++++++ :"+map);

		String where = getWheres(conditionList, params, inputBindingList,nodeId);

		String orderBy = " order by ";
		if (BeanUtils.isEmpty(displayList)) {
			for(int i=0;i<sortList.size();i++){
				DialogField df = sortList.get(i) ;
				if(i!=0){
					orderBy += ",";
				}
				
				orderBy += " " + df.getFieldName() + " " + df.getComment();
			}
			if(BeanUtils.isEmpty(sortList)){
				return sql + where;
			}
			return sql + where + orderBy;
		}
		if (params.containsKey("sortField")) {
			orderBy += params.get("sortField");
		} else if(BeanUtils.isEmpty(sortList)){
			if(!(displayList.get(0).getFieldName().equals("")))
				orderBy += displayList.get(0).getFieldName();
		}
		//sortField有值或srotList为空
		if(!" order by ".equals(orderBy)){
			if (params.containsKey("orderSeq")) {
				orderBy += " " + params.get("orderSeq");
			} else {
				orderBy += " ASC";
			}
			for(DialogField df:sortList){
				// 添加一个判断  因为sqlserver不允许有重复的order by的字段。否则会报错
				if(!params.get("sortField").equals(df.getFieldName())){
					orderBy += ", " + df.getFieldName() + " " + df.getComment();
				}
			}
		}else{
			//sortField无值以及说sortList不为空
			for(DialogField df:sortList){
				orderBy += df.getFieldName() + " " + df.getComment()+ ",";
			}
			sql = sql + where;
			return sql;
		}
		sql = sql + where + orderBy;
		if(sql.trim().endsWith(",")){
			sql=sql.substring(0,sql.length()-1);
		}
		return sql;
	}
	
	/**
	 * 取得delete的sql语句。
	 * author:zonghy
	 */
	public static String getdeleteSqls(Map<String, Object> map, Map paramsMap,ArrayList<Map<String, Object>> inputBindingList,String nodeId) 
	{
		String objectName=(String) map.get("objectName");
		List<DialogField> retrunList=(List<DialogField>) map.get("returnList");
		List<DialogField> displayList=(List<DialogField>) map.get("displayList");
		List<DialogField> conditionList=(List<DialogField>) map.get("conditionList");
		List<DialogField> sortList=(List<DialogField>) map.get("sortList");
		String sql = "delete from ";
		sql += objectName;
		String where = getWheres(conditionList, paramsMap, inputBindingList,nodeId);
		sql = sql + where;
		return sql;
	}

	
	
	/**
	 * 取得update的sql语句。
	 * author:zonghy
	 * @param outputBindingList 
	 */
	public static String getupdateSqls(Map<String, Object> map, Map paramsMap,
			ArrayList<Map<String, Object>> inputBindingList, ArrayList<Map<String,Object>> outputBindingList,String nodeId) {
		String objectName = (String) map.get("objectName");
		List<DialogField> returnList=(List<DialogField>) map.get("returnList");
		List<DialogField> displayList=(List<DialogField>) map.get("displayList");
		List<DialogField> conditionList=(List<DialogField>) map.get("conditionList");
		List<DialogField> sortList=(List<DialogField>) map.get("sortList");
		List<DialogField> settingList=(List<DialogField>) map.get("settingList");
		String sql = "update ";
		sql += objectName;
		String set = getSets(settingList,paramsMap,outputBindingList,nodeId);
		String where = getWheres(conditionList, paramsMap, inputBindingList,nodeId);
		sql = sql + set + where;
		return sql;
	}
	
	/**
	 * 取得insert的sql语句。
	 * author:zonghy
	 * @param outputBindingList 
	 */
	public static String getinsertSqls(Map<String, Object> map, Map paramsMap,
			ArrayList<Map<String, Object>> inputBindingList, ArrayList<Map<String,Object>> outputBindingList) {
		String objectName = (String) map.get("objectName");
		List<DialogField> returnList=(List<DialogField>) map.get("returnList");
		List<DialogField> displayList=(List<DialogField>) map.get("displayList");
		List<DialogField> conditionList=(List<DialogField>) map.get("conditionList");
		List<DialogField> sortList=(List<DialogField>) map.get("sortList");
		List<DialogField> settingList=(List<DialogField>) map.get("settingList");
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ");
		sb.append(objectName);
		sb.append(" ( ");
		insert_value1(settingList,paramsMap,outputBindingList,sb);
		sb.append(" ) values ( ");
		insert_value2(settingList,paramsMap,outputBindingList,sb);
		sb.append(" )");
		//String where = getWheres(conditionList, paramsMap, inputBindingList);
		//sb.append(where.toString()) ;
		return sb.toString();
	}
	
	
	
	private static void insert_value2(List<DialogField> settingList,
			Map paramsMap, ArrayList<Map<String, Object>> outputBindingList,
			StringBuffer sb) {
		int size = outputBindingList.size();
		if(size==0)
			return;
		for (int i=0;i<size-1;i++) {
			getStringByDialogField_insert(settingList.get(i),paramsMap, sb, outputBindingList.get(i));
			sb.append(",");
		}
		getStringByDialogField_insert(settingList.get(size-1),paramsMap, sb, outputBindingList.get(size-1));
	}

	private static void insert_value1(List<DialogField> settingList,
			Map paramsMap, ArrayList<Map<String, Object>> outputBindingList,
			StringBuffer sb) {
		int size = outputBindingList.size();
		if(size==0)
			return;
		for(int i=0;i<size-1;i++)
		{
			sb.append(outputBindingList.get(i).get("name"));
			sb.append(",");
		}
		sb.append(outputBindingList.get(size-1).get("name"));
	}

	/**
	 * 取得where 条件。
	 * 
	 * @param conditionMap
	 *            条件map。
	 * @param params
	 *            传入的参数
	 * @return
	 */
	public static String getWhere(List<DialogField> conditionList,
			Map<String, Object> params) {
		StringBuffer sb = new StringBuffer();
		for (DialogField dialogField : conditionList) {
			getStringByDialogField(dialogField, params, sb);
			if(dialogField.getDefaultType()==5){
				//java脚本，只需循环一次便可得到sql语句
				break ;
			}
		}
		if (sb.length() > 0) {
			return " where 1=1 " + sb.toString();
		}
		return "";
		
	}
	
	
	/**
	 * 取得流程启动where条件。
	 * author:zonghy
	 */
	public static String getWheres(List<DialogField> conditionList,Map<String, Object> params,ArrayList<Map<String, Object>> inputBindingList,String nodeId) {
		if (inputBindingList.size() == 0) {
			return " where 1=1 ";
		}

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		int size = inputBindingList.size();
		System.out.println("-----size:"+size);
		for (int i=0;i<size-1;i++) {
			//System.out.println("inputBindingList.get(i):"+inputBindingList.get(i));
			System.out.println("conditionList.get(i)----------:"+conditionList.get(i));
			getStringByDialogField_new(conditionList.get(i), params, sb, inputBindingList.get(i),nodeId);
			if(conditionList.get(i).getDefaultType()==5){
				//java脚本，只需循环一次便可得到sql语句
				break ;
			}
			sb.append(" and ");
		}
		System.out.println("---------------------------");
		System.out.println("params"+params);
		System.out.println("sb"+sb);
		System.out.println("inputBindingList.get(size-1)"+inputBindingList.get(size-1));
		System.out.println("conditionList.get(size-1)"+conditionList.size());
		System.out.println("---------------------------");

		System.out.println("数组:"+conditionList.get(0));
		getStringByDialogField_new(conditionList.get(size-1), params, sb, inputBindingList.get(size-1),nodeId);
		System.out.println("where语句: "+sb.toString());
		return sb.toString();
		
	}
	
	/**
	 * 取得流程启动set条件。
	 * author:zonghy
	 */
	public static String getSets(List<DialogField> conditionList,Map<String, Object> params,ArrayList<Map<String,Object>> outputBindingList,String nodeId) {
		StringBuffer sb = new StringBuffer();
		int size = outputBindingList.size();
		if(size==0)
			return "";
		sb.append(" set ");
		for (int i=0;i<size-1;i++) {
			getStringByDialogField_new(conditionList.get(i),params, sb, outputBindingList.get(i),nodeId);
			sb.append(" , ");
		}
		getStringByDialogField_new(conditionList.get(size-1),params, sb, outputBindingList.get(size-1),nodeId);
		return sb.toString();
	}
	

	/**
	 * 根据参数配置，上下文参数计算SQL语句。
	 * 
	 * @param dialogField
	 * @param params
	 * @param sb
	 */
	private static void getStringByDialogField(DialogField dialogField,
			Map<String, Object> params, StringBuffer sb) {
		String field = dialogField.getFieldName();
		String condition = dialogField.getCondition();
		int conditionType = dialogField.getDefaultType();
		System.out.println("into getStringByDialogField: field:"+field+",condition:"+condition+",conditionType:"+conditionType);
		Object value = null;
		boolean resu =  params.containsKey(field);
		System.out.println(resu+"");
		switch (conditionType) {
		case 1:
			if (params.containsKey(field)) {
				value =  params.get(field).toString();
				System.out.println("value:"+value);
			}
			break;
		case 2:
			value = dialogField.getDefaultValue();
			break;
		case 3:
			String script = dialogField.getDefaultValue();
			if (StringUtil.isNotEmpty(script)) {
				GroovyScriptEngine groovyScriptEngine = (GroovyScriptEngine) AppUtil
						.getBean(GroovyScriptEngine.class);
				value = groovyScriptEngine.executeObject(script, null);
			}			
			break ;
		case 4:
			//向对话框传参数
			if (params.containsKey(field)) {
				value =  params.get(field).toString();
			}
			break ;
		case 5:
			//java脚本
			String java = dialogField.getDefaultValue();
			if (StringUtil.isNotEmpty(java)) {
				GroovyScriptEngine groovyScriptEngine = (GroovyScriptEngine) AppUtil
						.getBean(GroovyScriptEngine.class);
				Map<String,Object> paramsMap = new HashMap<String,Object>() ;
				paramsMap.put("map", params) ;
				value = groovyScriptEngine.executeObject(java, paramsMap);
			}
			break ;

		}
		if (BeanUtils.isEmpty(value)){
			//解决时间类型between条件时查不到数据
			if(!(params.containsKey("start" + field) || params.containsKey("end" + field)))
			return;
		}
		
		
		if (value.toString().indexOf("#,")!=-1) {
			String temp=value.toString().replaceAll("#,",",");
			sb.append(" and " + field + " in ("+ temp+")");
			return ;
		}
		

		if (dialogField.getFieldType().equals(ColumnModel.COLUMNTYPE_VARCHAR)) {
			if (condition.equals("=")) {
				sb.append(" and " + field + "='" + value.toString() + "' ");
			} else if (condition.equals("like")) {
				sb.append(" and " + field + " like '%" + value.toString()
						+ "%' ");
			} else {
				sb.append(" and " + field + " like '" + value.toString()
						+ "%' ");
			}
		} else if (dialogField.getFieldType().equals(
				ColumnModel.COLUMNTYPE_DATE)) {
			if (dialogField.getCondition().equals("=")) {
				sb.append(" and " + field + "=:" + field + " ");
				if (!params.containsKey(field)) {
					params.put(field, value);
				}
			} else if (dialogField.getCondition().equals(">=")) {
				if (conditionType == 1) {
					if (params.containsKey("start" + field)) {
						sb.append(" and " + field + ">=:start" + field + " ");
					}
				} else {
					sb.append(" and " + field + ">=:start" + field + " ");
					params.put("start" + field, value);
				}
			} else if (dialogField.getCondition().equals("<=")) {
				if (conditionType == 1) {
					if (params.containsKey("end" + field)) {
						sb.append(" and " + field + "<=:end" + field + " ");
					}
				} else {
					sb.append(" and " + field + "<=:end" + field + " ");
					params.put("end" + field, value);
				}
			}
			//添加时间类型between条件
			else if (dialogField.getCondition().equals("between") && conditionType == 1) {
				if (params.containsKey("start" + field)) {
					sb.append(" and " + field + ">=:start" + field + " ");
				}
				if (params.containsKey("end" + field)) {
					sb.append(" and " + field + "<=:end" + field + " ");
				}
			}
		} else {
			if (conditionType == 1) {
				if (params.containsKey(field)) {
					sb.append(" and " + field + dialogField.getCondition()
							+ ":" + field + " ");
				}
			}else if (conditionType == 5) {
				if(value.toString().trim().startsWith("and")){
					sb.append(value.toString()) ;
				}else
					sb.append(" and " + value.toString());
			} else  if (conditionType == 2){
				//固定值不为varchar和date类型时
				sb.append(" and " + field + dialogField.getCondition() + value + " ");
			}else{
				sb.append(" and " + field + dialogField.getCondition() + ":"
						+ field + " ");
				params.put(field, value);
			}
		}
	}
	
	
	/**
	 * 根据参数配置计算where-SQL语句。
	 * author:zonghy
	 */
	@SuppressWarnings("unchecked")
	private static void getStringByDialogField_new(DialogField dialogField,
			Map<String, Object> params, StringBuffer sb,Map<String, Object> binding,String nodeId) {
		
		String field = dialogField.getFieldName();
		String condition = dialogField.getCondition();
		String name = (String)binding.get("name");
		String soapType = (String) binding.get("soapType");
		String conditionType_str = (String) binding.get("bindingType");
		String conditionValue = (String) binding.get("bindingVal");
		//数据 包
		List<String> parcellist  = (List<String> )binding.get("parcellist");		
		//去除字符串后边括号和内容
	
		conditionValue = conditionValue.replaceAll("\\(.*\\)","");
		System.out.println("conditionValue :"+conditionValue);
		int conditionType = Integer.valueOf(conditionType_str);
		System.out.println("into getStringByDialogField: field:"+field+",name:"+name+",conditionType:"+conditionType+",conditionValue:"+conditionValue+",soapType:"+soapType);
		Object value = null;
//		boolean resu =  params.containsKey(test);
//		System.out.println(resu+"");
		switch (conditionType) {
		case 1://固定值
			if(soapType.equals("number"))
				sb.append(name+"="+conditionValue);
			else
				sb.append(name+"='"+conditionValue+"'");
			break;
		case 2://流程变量
			if (params.containsKey(conditionValue)) { 
				value =  params.get(conditionValue).toString();
				System.out.println("value :"+value);
				if(soapType.equals("number"))
					sb.append(name+"="+value);
				else
					sb.append(name+"='"+value+"'");
				System.out.println("2:"+sb.toString());
			}else if(params.containsKey(nodeId)){
				//前序引擎数据,此节点为选中的前序节点非当前节点
				value = params.get(nodeId);
				Map<String,String> preMap = (Map<String,String>)value;
				System.out.println("value:"+value+"conditionValue:"+conditionValue);
				String x =preMap.get(conditionValue);
				if(x==null){
					for(String parcel :parcellist){
						sb.append(" "+parcel+"="+preMap.get(parcel)+" ");
						if(parcel.equals(parcellist.get(parcellist.size()-1))){
						   
						}
						else{							
							sb.append("AND");
						}
					}
					
				}else{
				sb.append(name+"="+x);
				}
				//sb.append(name+"="+x);
			}
			break;
		case 3:
			String script = dialogField.getDefaultValue();
			if (StringUtil.isNotEmpty(script)) {
				GroovyScriptEngine groovyScriptEngine = (GroovyScriptEngine) AppUtil
						.getBean(GroovyScriptEngine.class);
				value = groovyScriptEngine.executeObject(script, null);
			}			
			break ;
		case 4:
			//向对话框传参数
			if (params.containsKey(field)) {
				value =  params.get(field).toString();
			}
			break ;
		case 5:
			//java脚本
			String java = dialogField.getDefaultValue();
			if (StringUtil.isNotEmpty(java)) {
				GroovyScriptEngine groovyScriptEngine = (GroovyScriptEngine) AppUtil
						.getBean(GroovyScriptEngine.class);
				Map<String,Object> paramsMap = new HashMap<String,Object>() ;
				paramsMap.put("map", params) ;
				value = groovyScriptEngine.executeObject(java, paramsMap);
			}
			break ;
		}
	}
	
	/**
	 * 根据参数配置计算insert值语句。
	 * author:zonghy
	 */
	private static void getStringByDialogField_insert(DialogField dialogField,
			Map<String, Object> params, StringBuffer sb,Map<String, Object> output_binding) {
		String field = dialogField.getFieldName();
		String condition = dialogField.getCondition();
		String name = (String)output_binding.get("name");
		String soapType = (String) output_binding.get("soapType");
		String conditionType_str = (String) output_binding.get("bindingType");
		String conditionValue = (String) output_binding.get("bindingVal");
		conditionValue = conditionValue.replaceAll("\\(.*\\)","");
		int conditionType = Integer.valueOf(conditionType_str);
		Object value = null;
//		boolean resu =  params.containsKey(test);
//		System.out.println(resu+"");
		switch (conditionType) {
		case 1://固定值
			if(soapType.equals("number"))
				sb.append(conditionValue);
			else
				sb.append("'"+conditionValue+"'");
			break;
		case 2://流程变量
			if (params.containsKey(conditionValue)) {
				value =  params.get(conditionValue).toString();
				if(soapType.equals("number"))
					sb.append(value);
				else
					sb.append("'"+value+"'");
			}
			break;
		case 3:
			String script = dialogField.getDefaultValue();
			if (StringUtil.isNotEmpty(script)) {
				GroovyScriptEngine groovyScriptEngine = (GroovyScriptEngine) AppUtil
						.getBean(GroovyScriptEngine.class);
				value = groovyScriptEngine.executeObject(script, null);
			}			
			break ;
		case 4:
			//向对话框传参数
			if (params.containsKey(field)) {
				value =  params.get(field).toString();
			}
			break ;
		case 5:
			//java脚本
			String java = dialogField.getDefaultValue();
			if (StringUtil.isNotEmpty(java)) {
				GroovyScriptEngine groovyScriptEngine = (GroovyScriptEngine) AppUtil
						.getBean(GroovyScriptEngine.class);
				Map<String,Object> paramsMap = new HashMap<String,Object>() ;
				paramsMap.put("map", params) ;
				value = groovyScriptEngine.executeObject(java, paramsMap);
			}
			break ;
		}
	}
	
	
	/**
	 * 获取方言。
	 * 
	 * @param dbType
	 * @return
	 * @throws Exception
	 */
	private static Dialect getDialect(String dbType) throws Exception {
		Dialect  dialect = new OracleDialect();
		if (dbType.equals(SqlTypeConst.ORACLE)) {
			dialect = new OracleDialect();
		} else if (dbType.equals(SqlTypeConst.SQLSERVER)) {
			dialect = new SQLServer2005Dialect();
		} else if (dbType.equals(SqlTypeConst.DB2)) {
			dialect = new DB2Dialect();
		} else if (dbType.equals(SqlTypeConst.MYSQL)) {
			dialect = new MySQLDialect();
		} else if (dbType.equals(SqlTypeConst.H2)) {
			dialect = new H2Dialect();
		} else if (dbType.equals(SqlTypeConst.DM)) {
			dialect = new DmDialect();
		} else {
			throw new Exception("没有设置合适的数据库类型");
		}
		return dialect;

	}
	
	/**
	 * 去到提示信息页。
	 * @param content
	 * @return
	 */
	public static ModelAndView getTipInfo(String content){
		ModelAndView mv=new ModelAndView("/platform/console/tipInfo.jsp");
		mv.addObject("content", content);
		return mv;
	}
	
	/**
	 * 去到提示信息页。
	 * @param request
	 * @param response
	 * @param content
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void gotoTipInfo(HttpServletRequest request,HttpServletResponse response, String content) throws ServletException, IOException{
		request.setAttribute("content", content);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/platform/console/tipInfo.jsp");
		dispatcher.forward(request, response);
		
	}
	
	/**
	 * 根据字符串获取不重复的ID列表。
	 * @param ids
	 * @return
	 */
	public static List<Long> getListByStr(String ids) {
		String[] aryUid = ids.split("[,]");
		Set<Long> uidSet = new LinkedHashSet<Long>();
		for(String id:aryUid){
			Long orgId=Long.parseLong(id);
			uidSet.add(orgId);
		}
		List<Long> list=new ArrayList<Long>();
		list.addAll(uidSet);
		return list;
	}
	
	/**
	 * 替换模板中标题的标签。
	 * @param template		模板
	 * @param receiver		收件人
	 * @param sender		发件人
	 * @param subject		事项名称
	 * @param cause			原因
	 * @return
	 */
	public static String replaceTitleTag(String template,String receiver,String sender,String subject,String cause){
		if(StringUtil.isEmpty(template))
			return "";
		template = template.replace("${收件人}", receiver)
						   .replace("${发件人}", sender)
						   .replace("${转办人}", sender)
						   .replace("${代理人}", sender);
		if(StringUtil.isEmpty(cause))
			cause = "无";
		template=template.replace("${原因}", cause)
						 .replace("${逾期级别}", cause);

		template=template.replace("${事项名称}", subject);
		//替换特殊字符
		template=template.replaceAll("&nuot;","\n");
		return template;
	}
	
	
	/**
	 * 替换模板中的标签。
	 * @param template		模板
	 * @param receiver		收件人
	 * @param sender		发件人
	 * @param subject		主题
	 * @param url			url。
	 * @param cause			原因
	 * @param isSms			是否手机短信
	 * @return
	 */
	public static String replaceTemplateTag(String template,String receiver,String sender,String subject,String url,String cause,boolean isSms){
		if(StringUtil.isEmpty(template))
			return "";
		template = template.replace("${收件人}", receiver)
						   .replace("${发件人}", sender)
						   .replace("${转办人}", sender)
						   .replace("${代理人}", sender)
						   .replace("${原因}", cause)
						   .replace("${逾期级别}", cause)
						   .replace("${事项意见}", cause);;
		if(isSms || StringUtil.isEmpty(url)){
			template=template.replace("${事项名称}", subject);
		}else{
			template=template.replace("${事项名称}", "<a href='"+ url+"' target='_blank'>" + subject +"</a>");
		}
		//替换特殊字符
		template=template.replaceAll("&nuot;","\n");
		return template;
	}
	
	/**
	 * 获取任务或实例页面处理路径。
	 * @param id
	 * @param isTask
	 * @return
	 */
	public static String getUrl(String id,boolean isTask){
		if(BeanUtils.isEmpty(id)) return "";
		String url= AppConfigUtil.get("serverUrl");
		if(isTask){
			url+="/platform/bpm/task/toStart.ht?taskId=" + id;
		}
		else{
			 url+="/platform/bpm/processRun/info.ht?runId=" + id;     
		}
		return url;
	}
	
	public static String getProcessModuleType(int type){
		String info = "";
		switch (type) {
			case 1:
				info ="待办";
				break;
			case 2:
				info ="驳回";
				break;
			case 3:
				info ="撤销";
				break;
			case 4:
				info ="催办";
				break;
			case 5:
				info ="驳回发起人";
				break;
			case 6:
				info ="沟通反馈";
				break;
			case 7:
				info ="会话通知";
				break;
			case 8:
				info =" 转办";
				break;
			case 9:
				info ="代理";
				break;
			case 10:
				info ="取消转办";
				break;
			case 11:
				info ="取消代理";
				break;
			case 12:
				info ="归档";
				break;
			case 13:
				info ="抄送";
				break;
			case 14:
				info ="终止";
				break;
			case 15:
				info ="转发";
				break;
			case 17:
				info ="消息节点";
				break;
			default:
				break;
		}
		return info;	
	}
	
	/**
	 * 判断账号为空。
	 * @param assignee
	 * @return
	 */
	public static boolean isAssigneeEmpty(String assignee){
		if(StringUtil.isEmpty(assignee) || BpmConst.EMPTY_USER.equals(assignee)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断任务执行人非空。
	 * @param assignee
	 * @return
	 */
	public static boolean isAssigneeNotEmpty(String assignee){
		return !isAssigneeEmpty(assignee);
	}
	
	/**
	 * 取得用户连接。
	 * @param userId
	 * @param fullName
	 * @return
	 */
	public static String getUserLink(String userId,String fullName){
		String url= AppConfigUtil.get("serverUrl");
		url+="/platform/system/sysUser/get.ht?userId=" + userId+"&hasClose=true";
		return "<a href='" +url +"'>"+ fullName +"</a>";
	}
	/**
	 * 获取项目路径，如/bpm3
	 * @author hjx
	 * @version 创建时间：2014-2-8  下午5:59:41
	 * @return
	 */
	public static String getBaseUrl(){
		String baseUrl= ContextLoader.getCurrentWebApplicationContext().getServletContext().getContextPath();
		return baseUrl;
	}
	/**
	 * 取得用户连接。
	 * @param userId
	 * @param fullName
	 * @return
	 */
	public static String getUserLinkOpenWindow(String userId,String fullName){
		//String baseUrl= AppConfigUtil.get("serverUrl");//如果app设置错误会导致url出错
		String baseUrl= getBaseUrl();
		String imgSrc = "<img src='" + baseUrl + "/styles/default/images/bpm/user-16.png'>&nbsp;" ;
		//canReturn=1 用户页面无返回按钮
		String userUrl=baseUrl+"/platform/system/sysUser/get.ht?userId=" + userId+"&canReturn=1&hasClose=true";
		String url=imgSrc+"<a href='" +userUrl +"'target='_blank'>"+ fullName +"</a>&nbsp";
		return url;
	}
	
	/**
	 * 配置文件中获取文件上传路径
	 * 如果为空则采用默认路径/attachFiles/temp
	 * 这个路径返回没有/或\结尾。
	 * 
	 * @author hjx
	 * @version 创建时间：2013-11-4  下午3:46:28
	 * @return
	 */
	public static String getBasePath() {
		//String attachPath=SysPropertyService.getByAlias("file.upload");
		String attachPath=AppConfigUtil.get("file.upload");
		if (StringUtil.isEmpty(attachPath)) {
			attachPath = AppUtil.getRealPath("/attachFiles/temp");
		}
		attachPath=StringUtil.trimSufffix(attachPath, "\\") ;
		attachPath=StringUtil.trimSufffix(attachPath, "/") ;
		
		return attachPath;
	}
	
	
	/**
	 * 配置文件中获取文件存放的类型
	 * @author xcx
	 * @version 创建时间：2013-12-27  下午3:53:20
	 * @return
	 */
	public static String getSaveType() {
		String saveType = SysPropertyService.getByAlias("file.saveType","Folder");
		
		return saveType.trim().toLowerCase();
	}
	
	/**
	 * 获取系统中的消息类型。
	 * @author hjx
	 * @version 创建时间：2013-12-26  上午11:59:19
	 * @return
	 */
	public static Map<String,String> getInfoType(){
		Map<String,String> result=new LinkedHashMap<String, String>();
		@SuppressWarnings("unchecked")
		Map<String,IMessageHandler> map=(Map<String, IMessageHandler>) AppUtil.getBean("handlersMap");
		Set<Map.Entry<String, IMessageHandler>> set = map.entrySet();
        for (Iterator<Map.Entry<String, IMessageHandler>> it = set.iterator(); it.hasNext();) {
            Map.Entry<String, IMessageHandler> entry = (Map.Entry<String, IMessageHandler>) it.next();
            result.put(entry.getKey(), entry.getValue().getTitle());
        }
        return result;
	}
	
	/**
	 * 获取默认勾选的消息类型的key字符串,逗号分割。
	 * @author hjx
	 * @version 创建时间：2013-12-26  上午11:59:19
	 * @return
	 */
	public static String getDefaultSelectInfoType() {
		StringBuffer result = new StringBuffer();
		@SuppressWarnings("unchecked")
		Map<String, IMessageHandler> map = (Map<String, IMessageHandler>) AppUtil.getBean("handlersMap");
		Set<Map.Entry<String, IMessageHandler>> set = map.entrySet();
		for (Iterator<Map.Entry<String, IMessageHandler>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, IMessageHandler> entry = (Map.Entry<String, IMessageHandler>) it.next();
			if (entry.getValue().getIsDefaultChecked() == true) {
				if (result != null && result.length() > 0)
					result.append(",");
				result.append(entry.getKey());
			}
		}
		return result.toString();
	}
	
	/**
	 * 获取跳过条件map集合。
	 * @return
	 */
	public static Map<String,ISkipCondition> getSkipConditionMap(){
		return (Map<String, ISkipCondition>) AppUtil.getBean("skipConditionMap");
	}
	
	
	/**
	 * 根据别名获取JdbcTemplate对象。
	 * @param alias
	 * @return
	 * @throws Exception
	 */
	public static JdbcTemplate getJdbcTemplate(String alias) throws Exception{
		JdbcTemplate jdbcTemplate=null;
		if(StringUtil.isEmpty(alias) || alias.equals(BpmConst.LOCAL_DATASOURCE)){
			jdbcTemplate = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		}else{
			DataSource dataSource = DataSourceUtil.getDataSourceByAlias(alias);
			jdbcTemplate=new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}
	

	
	/**
	 * 获取当前用户对象。
	 * @return
	 */
	public static CurrentUser getCurrentUser(){
		SysUser curUser=ContextUtil.getCurrentUser(); 
		Position pos= ContextUtil.getCurrentPos();
				
		CurrentUser currentUser=new CurrentUser();
		
		currentUser.setUserId(curUser.getUserId());
		currentUser.setAccount(curUser.getAccount());
		currentUser.setName(curUser.getFullname());
		if(pos != null){
			currentUser.setOrgId(pos.getOrgId());
			currentUser.setPosId(pos.getPosId());
		}
		
		return currentUser;
	}
	
	/**
	 * 获取处理器类型。
	 * @return
	 */
	public static Map<String, IMessageHandler> getHandlerMap(){
		return (Map<String, IMessageHandler>) AppUtil.getBean("handlersMap");
	}
	
	/**
	 * 根据数据源获取JdbcHelper。
	 * 
	 * @param dsName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static JdbcHelper getJdbcHelper(String dsName) throws Exception {
		
		String dbType = AppConfigUtil.get("jdbc.dbType");
		DataSource dataSource = DataSourceUtil.getDataSourceByAlias(dsName);

		JdbcHelper jdbcHelper = JdbcHelper.getInstance();
		jdbcHelper.init(dsName, dataSource);
		jdbcHelper.setCurrentDb(dsName);
		
		Dialect dialect = getDialect(dbType);
		jdbcHelper.setDialect(dialect);
		return jdbcHelper;
	}

}
