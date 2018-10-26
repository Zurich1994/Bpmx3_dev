package com.hotent.core.web.query.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.entity.FieldLogic;
import com.hotent.core.web.query.entity.FieldSort;
import com.hotent.core.web.query.entity.FieldTableUtil;
import com.hotent.core.web.query.scan.SearchCache;
import com.hotent.core.web.query.scan.TableEntity;
import com.hotent.core.web.query.scan.TableField;
import com.hotent.core.web.query.script.IScopeScript;
import com.hotent.core.web.query.script.ISingleScript;
import com.hotent.core.web.query.script.ISqlScript;
import com.hotent.core.web.query.script.impl.ScopeScript;
import com.hotent.core.web.query.script.impl.SingleScriptFactory;
import com.hotent.core.web.query.script.impl.SqlScript;
import com.hotent.platform.model.util.FieldPool;

/**
 * 
 * <pre>
 * 	对象功能:构造查询条件
 * 	开发公司:广州宏天软件有限公司
 * 	开发人员:zxh
 * 	创建时间:2013-10-16 16:38:00
 * </pre>
 * 
 */
public class QueryBuilder {
	/**
	 * 根据主表名、FieldLogic集合和FieldSorts集合。
	 * 
	 * @param mainTableName
	 * @param fieldLogics
	 * @param fieldSorts
	 * @return
	 */
	public static String buildSql(String mainTableName,
			List<FieldLogic> fieldLogics, List<FieldSort> fieldSorts) {
		TableEntity tableEntity = SearchCache.getTableEntityMap().get(
				mainTableName);
		// 获得表列表
		Set<String> tables = QueryUtil.getTables(fieldLogics);
		tables.add(mainTableName);
		// 构造SQL
		StringBuilder sb = new StringBuilder();
		sb.append("select ").append(buildSelectFields(tableEntity, tables)) // 构造查询字段
				.append(" from ").append(buildFromTables(tables)) // 构造查询表
				.append(" where 1=1 ");
		if (BeanUtils.isNotEmpty(fieldLogics)) {
			String whereSql = buildWhereSqls(fieldLogics);
			if (StringUtils.isNotEmpty(whereSql))
				sb.append(" and ").append(whereSql); // 构造查询条件
		}
		sb.append(buildTableRelations(tableEntity, tables)); // 构造表关联查询条件
		if (BeanUtils.isNotEmpty(fieldSorts))
			sb.append(" order by ").append(buildSorts(fieldSorts)); // 构造排序字段
		return sb.toString();
	}

	/**
	 * 根据主表名，拼接查询字段部分
	 * 
	 * @param tableEntity
	 * @param tables
	 * @return
	 */
	public static String buildSelectFields(TableEntity tableEntity,
			Set<String> tables) {

		StringBuilder sb = new StringBuilder(" distinct ");//
		for (TableField tableField : tableEntity.getTableFieldList()) {
			if (tableField.getDataType().equals(FieldPool.DATATYPE_CLOB))// 如果是大文本的不加入
				continue;
			String fieldName = tableField.getName();

			// if(StringUtil.isNotEmpty(tableField.getFtable())){ //其它表的字段
			// tables.add(tableField.getFtable());//如果有其它字段需要加入其它字段的表
			// fieldName =
			// QueryUtil.fixFieldName(fieldName,tableField.getVar(),tableField.getFtable(),true);
			// }else{ //主表的字段
			// }
			fieldName = FieldTableUtil.fixFieldName(fieldName,
					tableField.getVar(), tableEntity.getTableName(), true);
			sb.append(fieldName).append(",");
		}

		String result = sb.toString();
		if (result.endsWith(","))
			result = result.substring(0, result.length() - 1);
		return result;
	}

	/**
	 * 根据表名集合，拼接from后面的表名部分
	 * 
	 * @param tableNames
	 * @return
	 */
	public static String buildFromTables(Set<String> tableNames) {
		StringBuilder sb = new StringBuilder();
		for (Iterator<String> it = tableNames.iterator(); it.hasNext();) {
			String table = it.next();
			String key = table.toLowerCase();
			sb.append(table).append(" ").append(key).append(",");
		}
		String result = sb.toString();
		if (result.endsWith(","))
			result = result.substring(0, result.length() - 1);
		return result;
	}

	/**
	 * 根据FieldLogic集合拼接where后面的查询条件部分
	 * 
	 * @return
	 */
	public static String buildWhereSqls(List<FieldLogic> fieldLogics) {
		StringBuilder sb = new StringBuilder();
		if (fieldLogics.size() == 0)
			return sb.toString();
		FieldLogic firstFieldLogic = fieldLogics.get(0);
		if (firstFieldLogic.isGroup()) {
			String script = buildWhereSqls(firstFieldLogic.getGroupLogics());
			sb.append(script);
		} else {
			sb.append(getSql(firstFieldLogic));
		}
		int len = fieldLogics.size();
		if (len == 1)
			return sb.toString();
		for (int i = 1; i < len; i++) {
			FieldLogic fieldLogic = fieldLogics.get(i);
			if (StringUtils.isNotEmpty(fieldLogic.getFieldRelation()))
				sb.append(" ").append(fieldLogic.getFieldRelation())
						.append(" ");
			if (fieldLogic.isGroup()) {
				String script = buildWhereSqls(fieldLogic.getGroupLogics());
				sb.append(" (").append(script).append(") ");
			} else {
				sb.append(getSql(fieldLogic));
			}
		}
		return sb.toString();
	}

	/**
	 * 根据表的集合拼接多表关联的查询条件的SQL，附加在where的查询条件后面
	 * 
	 * @param tableEntity
	 * @param tables
	 * @return
	 */
	private static String buildTableRelations(TableEntity tableEntity,
			Set<String> tables) {
		// 主键
		String pk = tableEntity.getPk();
		Map<String, TableEntity> subTableMap = TableEntity
				.getSubTableMap(tableEntity);
		StringBuilder sb = new StringBuilder();
		for (String table : tables) {
			TableEntity subTable = subTableMap.get(table);
			String fk = "";
			if (BeanUtils.isNotEmpty(subTable))
				fk = subTable.getRelation();
			if (StringUtils.isEmpty(fk))
				continue;
			sb.append(" ").append(QueryConstants.QUERY_AND).append(" ")
					.append(tableEntity.getTableName()).append(".").append(pk)
					.append(QueryConstants.SQL_RELATION_EQUAL).append(table)
					.append(".").append(fk);
		}
		return sb.toString();
	}

	/**
	 * 根据FieldSorts集合拼接排序部分的SQL
	 * 
	 * @return
	 */
	public static String buildSorts(List<FieldSort> fieldSorts) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < fieldSorts.size() ; i++){
			FieldSort fieldSort = fieldSorts.get(i);
			sb.append(fieldSort.getFixFieldName()).append(" ")
				.append(fieldSort.getOrderBy());
			if(i!=fieldSorts.size()-1)
				sb.append(",");
			
		}
		return sb.toString();
	}

	/**
	 * 根据单个FieldLogic构造SQL片段
	 * 
	 * @param fieldLogic
	 * @return
	 */
	private static String getSql(FieldLogic fieldLogic) {
		String script = "";
		int judgeType = fieldLogic.getJudgeType();
		if (judgeType == QueryConstants.JUDGE_SINGLE) {// 单值
			ISingleScript singleScript = SingleScriptFactory
					.getQueryScript(fieldLogic.getDataType());
			script = singleScript.getSQL(fieldLogic.getJudgeSingle());
		} else if (judgeType == QueryConstants.JUDGE_SCOPE) {// 范围值
			IScopeScript scopeScript = new ScopeScript();
			script = scopeScript.getSQL(fieldLogic.getJudgeScope());
		} else if (judgeType == QueryConstants.JUDGE_SCRIPT) {// 脚本
			ISqlScript sqlScript = new SqlScript();
			script = sqlScript.getSQL(fieldLogic.getJudgeScript());
		}
		if (StringUtils.isEmpty(script))
			return "";
		return "(" + script + ")";
	}
}
