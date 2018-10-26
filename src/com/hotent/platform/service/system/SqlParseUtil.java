package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hotent.core.db.entity.SQLClause;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.SqlTypeConst;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ArrayUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.JSONUtil;
import com.hotent.core.util.StringPool;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.TimeUtil;
import com.hotent.platform.model.form.FilterJsonStruct;
import com.hotent.platform.model.system.SysQuerySetting;
import com.hotent.platform.model.system.SysQueryView;

public class SqlParseUtil {
	
	/**
	 * 获取排序的SQL语句。
	 * @param sortField
	 * @param orderSeq
	 * @return
	 */
	public static String getSortSql(String sortField,String orderSeq){
		sortField=sortField.trim();
		if(StringUtil.isEmpty(sortField)) return "";
		//category asc, amount desc, category
		sortField=sortField.replaceAll("\\s+", " ");
		String[] arySort=sortField.split(",");
		if(arySort.length==1){
			return " order by " + sortField +" " + orderSeq;
		}
		
		
		String curField=arySort[arySort.length-1].trim();
		String rtn="";
		boolean flag=false;
		for(int i=0;i<arySort.length-1;i++){
			String order=arySort[i].trim();
			if(order.startsWith(curField)){
				rtn+="," +curField + " " + orderSeq;
				flag=true;
			}
			else{
				rtn+="," +order;
			}
			
		}
		if(!flag){
			rtn+="," +curField +" " +orderSeq;
		}
		rtn=" order by "+ StringUtil.trim(rtn.trim(), ",");
		
		return rtn;
	}
	
	public static void main(String[] args) {
		String str="category   asc,     amount desc, name".toLowerCase();
		String rtn=getSortSql(str,"asc");
		
		System.out.println(rtn);
	}
	
	/**
	 * 获取查询参数。
	 * @param queryView
	 * @param isForm
	 * @return
	 */
	public static List<SQLClause> getQueryParameters(SysQueryView queryView,boolean isForm){
		List<SQLClause> list=new ArrayList<SQLClause>();
		List<SQLClause> conditionFields = getConditionList(queryView.getConditions());
		for(SQLClause clause:conditionFields){
			int valFrom=isForm?SQLClause.VALUEFROM_FORM:SQLClause.VALUEFROM_INPUT;
			if(clause.getValueFrom()==valFrom){
				conditionFields.add(clause);
			}
		}
		return list;
		
	}

	
	/**
	 * 根据视图配置的过滤器和数据库类型返回SQL语句。
	 * @param filterJson
	 * @param dbType
	 * @return
	 */
	public static String getFilterSQL(String filterJson,String dbType) {
		if (JSONUtil.isEmpty(filterJson))
			return "";
		//
		List<Map<String, Object>> operatorList = new ArrayList<Map<String, Object>>();
		// 转换成SQL
		List<FilterJsonStruct> filters = com.alibaba.fastjson.JSONArray.parseArray(filterJson,FilterJsonStruct.class);
		
		getFilterResult(filters, operatorList,dbType);

		return executeOperator(operatorList);
	}
	
	/**
	 * 过滤所有的条件
	 * 
	 * @param filters
	 * @param tableMap
	 * @param relationMap
	 * @param operatorList
	 */
	private static void getFilterResult(List<FilterJsonStruct> filters,
			List<Map<String, Object>> operatorList,String dbType) {
		for (FilterJsonStruct filter : filters) {
			// 组合条件
			if (filter.getBranch()) {
				List<Map<String, Object>> branchResultList = new ArrayList<Map<String, Object>>();
				getFilterResult(filter.getSub(), branchResultList,dbType);
				String branchResult = executeOperator(branchResultList);
				Map<String, Object> resultMap = getResultMap(filter.getCompType(), branchResult);
				operatorList.add(resultMap);
			} 
			//普通条件
			else {
				getNormalFilterResult(filter, operatorList,dbType);
			}
		}
	}
	
	
	/**
	 * 获取SQL运算结果
	 * 
	 * @param operatorList
	 * @return
	 */
	private static String executeOperator(List<Map<String, Object>> operatorList) {
		if (operatorList.size() == 0) return "";
		String returnVal = (String) operatorList.get(0).get("result");
		if (operatorList.size() == 1) return returnVal;
		int size = operatorList.size();
		for (int k = 1; k < size; k++) {
			Map<String, Object> resultMap = operatorList.get(k);
			String operator = resultMap.get("operator").toString();
			if ("or".equals(operator)) { // 或运算
				returnVal = "(" + returnVal + ") OR ("+ resultMap.get("result") + ")";
			} 
			else if ("and".equals(operator)) { // 与运算
				returnVal = "(" + returnVal + ") AND ("+ resultMap.get("result") + ")";
			}
		}
		if (StringUtils.isNotEmpty(returnVal)) returnVal = "(" + returnVal + ")";
		return returnVal;
	}
	
	/**
	 * 
	 * @param filter
	 * @param isJudgeVal1
	 * @return
	 */
	private static String getJudgeVal(FilterJsonStruct filter,boolean isJudgeVal1){
		String val=isJudgeVal1?filter.getJudgeVal1():filter.getJudgeVal2();
		int ruleType=filter.getRuleType();
		if(ruleType==FilterJsonStruct.RULE_TYPE_NORMAL) return val;
		GroovyScriptEngine groovyScriptEngine=AppUtil.getBean(GroovyScriptEngine.class);
		//脚本计算。
		Object object=groovyScriptEngine.executeString(val, null);
		return object.toString();
	}
	
	
	/**
	 * 获取普通条件的结果
	 * 
	 * @param filter
	 * @param tableMap
	 * @param relationMap
	 * @param operatorList
	 */
	private static void getNormalFilterResult(FilterJsonStruct filter,
			List<Map<String, Object>> operatorList,String dbType) {
		String flowvarKey = filter.getFlowvarKey();
		String judgeVal1=getJudgeVal(filter,true);
		String judgeVal2=getJudgeVal(filter,false);
		
		int optType=filter.getOptType();
		
		String script = "";
		switch (optType) {
			// 数字
			case 1:
				// 条件一
				if (StringUtils.isNotEmpty(judgeVal1)) {
					script = getCompareScript(filter.getJudgeCon1(),flowvarKey, judgeVal1, filter.getOptType());
				}
				// 条件二
				if (StringUtils.isNotEmpty(judgeVal2)) {
					String moreScript = getCompareScript(filter.getJudgeCon2(),flowvarKey, judgeVal2, filter.getOptType());
					if (StringUtils.isNotEmpty(script))
						script = script + SysQuerySetting.CONDITION_AND;
					script = script + moreScript;
				}
				break;
			// 字符串
			case 2:
				// 条件一
				if (StringUtils.isNotEmpty(judgeVal1)) {
					script = getCompareScript(filter.getJudgeCon1(),flowvarKey, judgeVal1, filter.getOptType());
				}
				break;
			// 日期
			case 3:
				// 条件一
				if (StringUtils.isNotEmpty(judgeVal1)) {
					String val1 = sqlToDate(judgeVal1,filter.getDatefmt(),dbType);
					script = getCompareScript(filter.getJudgeCon1(),flowvarKey, val1, filter.getOptType());
				}
				// 条件二
				if (StringUtils.isNotEmpty(judgeVal2)) {
					String val2 = sqlToDate(judgeVal2,
							filter.getDatefmt(),dbType);
					String moreScript = getCompareScript(filter.getJudgeCon2(),flowvarKey, val2, filter.getOptType());
					if (StringUtils.isNotEmpty(script))
						script = script + SysQuerySetting.CONDITION_AND;
					script = script + moreScript;
				}
				break;
			// 字典
			case 4:
				String[] vals = judgeVal1.split("&&");
				for (String val : vals) {
					if (StringUtils.isNotEmpty(script)){
						script += SysQuerySetting.CONDITION_AND;
					}
					script += getCompareScript(filter.getJudgeCon1(), flowvarKey,val, filter.getOptType());
				}
				break;
			// 角色、组织、岗位选择器
			case 5:
				String judgeCon = filter.getJudgeCon1();
				String[] ids = judgeVal1.split("&&");
				if (ids.length == 2) {
					script = getCompareScript(judgeCon, filter.getFlowvarKey(),ids[0], filter.getOptType());
				} else {// 特殊类型的
					if ("3".equalsIgnoreCase(judgeCon) || "4".equalsIgnoreCase(judgeCon)){
						script = getCompareScript(judgeCon, filter.getFlowvarKey(),judgeVal1, filter.getOptType());
					}
				}
				break;
		}

		if (StringUtil.isEmpty(script)) return;
		// 执行结果记录到operatorList中
		Map<String, Object> resultMap = getResultMap(filter.getCompType(),script);
		operatorList.add(resultMap);
	}
	
	/**
	 * 格式日期
	 * 
	 * @param val
	 * @param datefmt
	 * @return
	 */
	private static String sqlToDate(String val, String datefmt,String dbType) {
		StringBuffer sb = new StringBuffer();
		// TODO 需要修复，如果数据库不是ORACL的有问题
		sb.append("TO_DATE('")
				.append(val)
				.append("','")
				.append(StringUtils.isEmpty(datefmt) ? StringPool.DATE_FORMAT_DATE
						: datefmt).append("')");
		return sb.toString();
	}
	
	
	/**
	 * 获取根据条件组合的脚本
	 * @param judgeCon
	 * @param fieldName
	 * @param judgeVal
	 * @param type
	 * @return
	 */
	private static String getCompareScript(String judgeCon, String fieldName,
			String judgeVal, int type) {
		StringBuffer sb = new StringBuffer();
		switch (type) {
		case 1:// 数值
		case 3:// 日期
			if ("1".equals(judgeCon)) {
				sb.append(fieldName).append("=").append(judgeVal);
			} else if ("2".equals(judgeCon)) {
				sb.append(fieldName).append("!=").append(judgeVal);
			} else if ("3".equals(judgeCon)) {
				sb.append(fieldName).append(">").append(judgeVal);
			} else if ("4".equals(judgeCon)) {
				sb.append(fieldName).append(">=").append(judgeVal);
			} else if ("5".equals(judgeCon)) {
				sb.append(fieldName).append("<").append(judgeVal);
			} else if ("6".equals(judgeCon)) {
				sb.append(fieldName).append("<=").append(judgeVal);
			}
			break;
		case 2:// 字符串
		case 4:// 字典
			if ("1".equals(judgeCon)) {
				sb.append(fieldName).append("=").append("'").append(judgeVal).append("'");
			} else if ("2".equals(judgeCon)) {
				sb.append(fieldName).append("!=").append("'").append(judgeVal).append("'");
			} else if ("3".equals(judgeCon)) {
				sb.append("UPPER(").append(fieldName).append(")=").append(" UPPER('").append(judgeVal).append("')");
			} else if ("4".equals(judgeCon)) {
				sb.append(fieldName).append(" LIKE").append(" '%").append(judgeVal).append("%'");
			} else if ("5".equals(judgeCon)) {
				sb.append(fieldName).append(" LIKE").append(" '").append(judgeVal).append("%'");
			} else if ("6".equals(judgeCon)) {
				sb.append(fieldName).append(" LIKE").append(" '%").append(judgeVal).append("'");
			}
			break;
		case 5:// 人员选择器
			if ("1".equals(judgeCon)) {
				sb.append(fieldName).append(" in (").append("").append(judgeVal).append(")");
			} else if ("2".equals(judgeCon)) {
				sb.append(fieldName).append(" not in (").append("").append(judgeVal).append(")");
			} else if ("3".equals(judgeCon)) {
				sb.append(fieldName).append(" = :").append("").append(judgeVal).append("");
			} else if ("4".equals(judgeCon)) {
				sb.append(fieldName).append(" != :").append("").append(judgeVal).append("");
			}
			break;
		}
		return sb.toString();
	}
	
	private static Map<String, Object> getResultMap(String operator, String result) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("operator", operator);
		resultMap.put("result", result);
		return resultMap;
	}
	
	/**
	 * 根据查询条件列表，计算Where SQL 语句
	 * @param sysQueryView
	 * @param where
	 * @param params
	 * @return
	 */
	public static String getQuerySQL(SysQueryView sysQueryView, boolean isWhereEmpty,
			Map<String, Object> params,String dbType) {
		StringBuffer sb = new StringBuffer();
		
		List<SQLClause> conditionFields = getConditionList(sysQueryView.getConditions());
		if (BeanUtils.isEmpty(conditionFields))	return "";
		for (SQLClause condition : conditionFields) {
			String field=condition.getName();
			//上下文参数不包含设定条件的参数则忽略。
			if(!params.containsKey(field) && !params.containsKey (SysQueryView.FIELD_BEGIN + field )
					&& !params.containsKey (SysQueryView.FIELD_END + field)){
				continue;
			}
			getCluaseSQL(sysQueryView, condition, params, sb,dbType);
		}
		String and =isWhereEmpty ? "" : " AND ";
		String rtn="";
		if (sb.length() > 0)
			rtn= and  + sb.toString() ;
		return rtn;
	}
	
	/**
	 * 获得条件脚本的SQL
	 * 	{
	 *	name:字段名称
	 *	type:数据类型
	 *	operate:操作符
	 *	comment:"备注",
	 *	valueFrom:"输入，动态传入",
	 *	controlType:"",
	 *	dateFormat:""
	 *  }
	 * @param conditionField
	 * @return
	 */
	private static List<SQLClause> getConditionList(String conditionField) {
		List<SQLClause> conditionFields = new ArrayList<SQLClause>();
		if (StringUtil.isEmpty(conditionField)) return conditionFields;
		conditionFields = com.alibaba.fastjson.JSONArray.parseArray(conditionField,SQLClause.class);
		return conditionFields;
	}
	
	
	/**
	 * 计算出WHERE SQL
	 * 
	 * @param bpmDataTemplate
	 * @param tableName
	 * @param condition
	 * @param params
	 * @param bpmFormFieldMap
	 * @param fieldTypeMap
	 * @param sb
	 */
	private static void getCluaseSQL(SysQueryView queryView,
			SQLClause condition, Map<String, Object> params, StringBuffer sb,String dbType) {
	
		String joinType = condition.getJoinType();
		String type = condition.getType();
		joinType = " " + joinType + " ";
		condition.setJoinType(joinType);
		
		String field=condition.getName();
		
		
		
		//varchar ，text
		if(ColumnModel.COLUMNTYPE_VARCHAR.equals(type) || ColumnModel.COLUMNTYPE_TEXT.equals(type)){
			handStringCondition(condition, params, sb,dbType);
		}
		//日期
		else if(ColumnModel.COLUMNTYPE_DATE .equals(type)){
			handDateCondition(condition, params, sb);
		}
		//数字的情况。
		else if(ColumnModel.COLUMNTYPE_NUMBER.equals(type)){
			handNumberCondition(condition, params, sb);
		}

	
	}
	
	
	/**
	 * 数字处理。
	 * 1:等于
	 * 2：不等于
	 * 3:大于
	 * 4:>=
	 * 5:<
	 * 6:<=
	 * 7:in type: in (1,2,3)
	 * 8.between
	 * 9:not in 
	 * @param condition
	 * @param params
	 * @param sb
	 * @param dbType
	 */
	private static void handNumberCondition(SQLClause condition,Map<String, Object> params,StringBuffer sb){
		String joinType= condition.getJoinType();
		String field=condition.getName();
		int operate=Integer.parseInt(condition.getOperate());
		
		String val=(String)params.get(field);
		float value=Float.parseFloat(val);
		switch (operate) {
			//等于
			case 1:
				sb.append(joinType).append(field).append("=:").append(field);
				params.put(field, value);
				break;
			//不等于
			case 2:
				sb.append(joinType).append(field).append("!=:").append(field);
				params.put(field, value);
				break;
			//大于
			case 3:
				sb.append(joinType).append(field).append(">:").append(field);
				params.put(field, value);
				break;
			//大于等于
			case 4:
				sb.append(joinType).append(field).append(">=:").append(field);
				params.put(field, value);
				break;
			//小于
			case 5:
				sb.append(joinType).append(field).append("<:").append(field);
				params.put(field, value);
				break;
			//小于等于
			case 6:
				sb.append(joinType).append(field).append("<=:").append(field);
				params.put(field, value);
				break;
			//in 查询
			case 7:
				sb.append(joinType).append(field).append(" in (").append(val).append(")");
				break;
			// between
			case 8:
				String beginField=SysQueryView.FIELD_BEGIN +field;
				String endField=SysQueryView.FIELD_END +field;
				String beginVal=(String) params.get(beginField);
				String endVal=(String) params.get(endField);
				if(StringUtil.isNotEmpty(beginVal)){
					sb.append(joinType).append(field).append(">=:").append(beginField);
					params.put(beginField,Float.parseFloat(beginVal));
				}
				if(StringUtil.isNotEmpty(endVal)){
					sb.append(joinType).append(field).append("<=:").append(endField);
					params.put(endField, Float.parseFloat(beginVal));
				}
				break;
			//不包含
			case 9:
				sb.append(joinType).append(field).append(" not in (").append(val).append(")");
	
		}
	}
	
	/**
	 * 处理数据比较。
	 * 1:等于
	 *	  2：不等于
	 *	  3:大于
	 *	  4:>=
	 *	  5:<
	 *	  6:<=
	 *	  7:between and
	 *		日期格式为：2015-06-14
	 * @param condition
	 * @param params
	 * @param sb
	 * @param dbType
	 */
	private static void handDateCondition(SQLClause condition,Map<String, Object> params,StringBuffer sb){
		String joinType= condition.getJoinType();
		String field=condition.getName();
		int operate=Integer.parseInt(condition.getOperate());
		
		String val=(String)params.get(field);
		Date date=null;
		if(StringUtil.isNotEmpty(val)){
			 date=TimeUtil.convertDateString(val);
		}
		switch (operate) {
			//等于
			case 1:
				sb.append(joinType).append(field).append("=:").append(field);
				params.put(field, date);
				break;
			case 2:
				sb.append(joinType).append(field).append("!=:").append(field);
				params.put(field, date);
				break;
			case 3:
				sb.append(joinType).append(field).append(">:").append(field);
				params.put(field, date);
				break;
			case 4:
				sb.append(joinType).append(field).append(">=:").append(field);
				params.put(field, date);
				break;
			case 5:
				sb.append(joinType).append(field).append("<:").append(field);
				params.put(field, date);
				break;
			case 6:
				sb.append(joinType).append(field).append("<=:").append(field);
				params.put(field, date);
				break;
			//between
			case 7:
				String beginField=SysQueryView.FIELD_BEGIN +field;
				String endField=SysQueryView.FIELD_END +field;
				String beginVal=(String) params.get(beginField);
				String endVal=(String) params.get(endField);
				if(StringUtil.isNotEmpty(beginVal)){
					sb.append(joinType).append(field).append(">=:").append(beginField);
					params.put(beginField, TimeUtil.convertDateString(beginVal));
				}
				if(StringUtil.isNotEmpty(endVal)){
					sb.append(joinType).append(field).append("<=:").append(endField);
					params.put(endField, TimeUtil.convertDateString(endVal));
				}
				break;
			
		}
	}
	
	/**
	 * 1:等于
	 *	2:不等于
	 *	3:等于(忽略大小写)
	 *	4:like
	 *	5:like左
	 *	6:like 右
	 *	7:in
	 * @param condition
	 * @param params
	 * @param sb
	 */
	private static void handStringCondition(SQLClause condition, Map<String, Object> params,StringBuffer sb,String dbType){
		String joinType= condition.getJoinType();
		String field=condition.getName();
		int operate=Integer.parseInt(condition.getOperate());
		
		String val="";
		switch (operate) {
			//等于
			case 1:
				sb.append(joinType).append(field).append("=").append(":").append(field);
				break;
			//不等于
			case 2:
				sb.append(joinType).append(field).append("!=").append(":").append(field);
				break;
			case 3:
				if(SqlTypeConst.ORACLE.equals(dbType)){
					//转成大写
					val=params.get(field).toString().toUpperCase();
					params.put(field, val);
					sb.append(joinType).append("Upper(" + field +")").append("=").append(":").append(field);
				}
				else{
					sb.append(joinType).append(field).append("=").append(":").append(field);
				}
				break;
			case 4:
				val="%" + params.get(field).toString() +"%";
				params.put(field, val);
				sb.append(joinType).append(field).append(" like ").append(":").append(field);
				break;
			case 5:
				val=params.get(field).toString() +"%";
				params.put(field, val);
				sb.append(joinType).append(field).append(" like ").append(":").append(field);
				break;
			case 6:
				val="%" +params.get(field).toString() ;
				params.put(field, val);
				sb.append(joinType).append(field).append(" like ").append(":").append(field);
				break;
			case 7:
				val=params.get(field).toString();
				val=ArrayUtil.addQuote(val);
				sb.append(joinType).append(field).append(" in ("+val+")");
				break;
		}
	}
	
	
	

}
