package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.hotent.core.db.entity.SQLClause;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.system.SysQueryField;
import com.hotent.platform.model.system.SysQuerySetting;
import com.hotent.platform.model.util.FieldPool;
import com.hotent.core.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * <pre>
 * 对象功能:sql自定义查询 查询条件字段解析 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:欧晓斌
 * 创建时间:2014年6月4日15:05:28
 * </pre>
 */
@Service
public class SysQueryConditionFieldParseService {
	@Resource
	private SysQueryFieldService sysQueryFieldService; // 字段配置
	@Resource
	private GroovyScriptEngine groovyScriptEngine;

	/**
	 * 是否有查询条件字段
	 * 
	 * @param conditionField
	 * @return
	 */
	public boolean hasConditionField(String conditionField) {
		if (StringUtil.isEmpty(conditionField))
			return false;
		JSONArray jsonAry = JSONArray.fromObject(conditionField);
		return jsonAry.size() > 0 ? true : false;
	}

	/**
	 * 查询条件字段
	 * 
	 * @return
	 */
	public String getDefaultConditionField(Long sqlId) {
		List<SysQueryField> sysQueryFields = sysQueryFieldService
				.getConditionFieldListBySqlId(sqlId);
		if (sysQueryFields == null)
			return null;
		JSONArray jsonArray = new JSONArray();
		for (SysQueryField field : sysQueryFields) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("id", field.getId());
			jsonObject.accumulate("na", field.getName());
			if ("VARCHAR2".equalsIgnoreCase(field.getType())) {
				jsonObject.accumulate("ty", "varchar");
			} else {
				jsonObject.accumulate("ty", field.getType().toLowerCase());// 控件条件跟ty类型有关
			}
			jsonObject.accumulate("op", "1");// 运算符默认为第一个
			jsonObject.accumulate("cm", field.getFieldDesc());
			jsonObject.accumulate("va", "");
			jsonObject.accumulate("vf", "1");
			jsonObject.accumulate("ct", field.getControlType() == null ? 1
					: field.getControlType());// 控件类型默认为单行文本框
			jsonObject.accumulate("qt", "S");// 查询类型，跟控件类型有关的
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	}

	/**
	 * 根据查询条件列表，计算Where SQL 语句
	 * 
	 * @param where
	 * @param tableName
	 * 
	 * @param conditions
	 * @param params
	 * @param bpmFormFieldMap
	 * @param fieldTypeMap
	 * @return
	 */
	public String getQuerySQL(SysQuerySetting sysQuerySetting, String where,
			Map<String, Object> params) {
		StringBuffer sb = new StringBuffer();
		String and = StringUtils.isEmpty(where) ? "" : " AND ";
		List<SQLClause> conditionFields = this.getConditionList(sysQuerySetting
				.getConditionField());
		if (BeanUtils.isEmpty(conditionFields))
			return where;
		for (SQLClause condition : conditionFields) {
			this.getCluaseSQL(sysQuerySetting, condition, params, sb);
		}
		if (sb.length() > 0)
			where += and + "  1=1 " + sb.toString() ;

		return where;
	}

	/**
	 * 获得条件脚本的SQL
	 * 
	 * @param conditionField
	 * @return
	 */
	private List<SQLClause> getConditionList(String conditionField) {
		List<SQLClause> conditionFields = new ArrayList<SQLClause>();
		if (StringUtil.isEmpty(conditionField))
			return conditionFields;
		JSONArray jsonArray = JSONArray.fromObject(conditionField);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			SQLClause field = new SQLClause();
			// field.setJoinType(jsonObject.getString("jt"));
			field.setJoinType("AND");
			field.setName(jsonObject.getString("na"));
			field.setComment(jsonObject.getString("cm"));
			field.setType(jsonObject.getString("ty"));
			field.setValue(jsonObject.get("va"));
			field.setValueFrom(jsonObject.getInt("vf"));
			field.setOperate(jsonObject.getString("op"));
			field.setControlType(jsonObject.getString("ct"));
			field.setQueryType(jsonObject.getString("qt"));
			conditionFields.add(field);
		}
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

	private void getCluaseSQL(SysQuerySetting sysQuerySetting,
			SQLClause condition, Map<String, Object> params, StringBuffer sb) {
		String field = condition.getName();
		String operate = condition.getOperate();
		int valueFrom = condition.getValueFrom();
		String joinType = condition.getJoinType();
		String type = condition.getType();
		joinType = " " + joinType + " ";
		String controlType = condition.getControlType();

		Boolean isSelector = this.isSelector(controlType);
		Map<String, Object> dateMap = getQueryValue(condition, params, field,
				type, operate);

		Object value = this.getQueryValue(condition, params, field, isSelector);
		if (BeanUtils.isEmpty(value) && BeanUtils.isEmpty(dateMap))
			return;
		if(isSelector){
			if (isrMultiSelector(condition.getControlType())) {
				sb.append(joinType + field + " in ( " + this.addColon(value) + " )");
			} else {
				if (operate.equalsIgnoreCase("2")) {// 1","=" "2","!=" "3","like""4","左like" "5","右like"
					sb.append(joinType + field + "!=:" + field);
					params.put(field, String.valueOf(value));
				} else {
					sb.append(joinType + field + "=:" + field);
					params.put(field, String.valueOf(value));
				}
			}
		}else{
			if (type.equals(ColumnModel.COLUMNTYPE_VARCHAR) ) {// 字符串
				value = value.toString();
				if (operate.equalsIgnoreCase("1")) {// 1","=" "2","!=" "3","like""4","左like" "5","右like"
					sb.append(joinType).append(field).append("=").append(":")
							.append(field);
				} else if (operate.equalsIgnoreCase("2")) {
					sb.append(joinType).append(field).append(" != ")
							.append(":").append(field);
				} else if (operate.equalsIgnoreCase("3")) {
					value = "%" + value.toString() + "%";
					sb.append(joinType).append(field).append(" LIKE :")
							.append(field);
				} else if (operate.equalsIgnoreCase("4")) {
					value = "%" + value.toString();
					sb.append(joinType).append(field).append(" LIKE :")
							.append(field);
				} else if (operate.equalsIgnoreCase("5")) {
					value = value.toString() + "%";
					sb.append(joinType).append(field).append(" LIKE :")
							.append(field);
				} else {
					value = "%" + value.toString() + "%";
					sb.append(joinType).append(field).append(" LIKE :")
							.append(field);
				}
				params.put(field, value);
			} else if (type.equals(ColumnModel.COLUMNTYPE_DATE)) {// 日期
				if ("6".equalsIgnoreCase(operate)) {// 日期范围特殊处理
					if (BeanUtils.isNotEmpty(dateMap
							.get(SysQuerySetting.DATE_BEGIN))) {
						String begingField = SysQuerySetting.DATE_BEGIN + field;
						sb.append(joinType + field + ">=:" + begingField + " ");
						params.put(begingField,
								dateMap.get(SysQuerySetting.DATE_BEGIN));
					}
					if (BeanUtils.isNotEmpty(dateMap.get(SysQuerySetting.DATE_END))) {
						String endField = SysQuerySetting.DATE_END + field;
						sb.append(joinType + field + "<:" + endField + " ");
						params.put(endField, dateMap.get(SysQuerySetting.DATE_END));
					}
				} else {
					String op = this.getOperate(operate);
					if (valueFrom == 1) {
						if (params.containsKey(field)) {
							sb.append(joinType + field + op + ":" + field + " ");
						}
					} else {
						sb.append(joinType + field + op + ":" + field + " ");
						params.put(field, value);
					}
				}
			} else {// 否则则是数字
				String op = this.getOperate(operate);
				if (valueFrom == 1) {
					if (params.containsKey(field)) {

						sb.append(joinType + field + op + ":" + field + " ");
					}
				} else {
					sb.append(joinType + field + op + ":" + field + " ");
					params.put(field, value);
				}
			}
		}
	}
	
	/**
	 * 添加上冒号
	 * @param value
	 * @return
	 */
	private String addColon(Object value) {
		StringBuffer sbf = new StringBuffer();
		if(value != null){
			String []StrArr = value.toString().split(",");
			int size = StrArr.length;
			for(int i=0;i < size;i++){
				sbf.append("'").append(StrArr[i]).append("'");
				if(size - i > 1){
					sbf.append(",");
				}
			}
			return sbf.toString();
		}
		return "";
	}
	
	/**
	 * 获得操作类型
	 * 
	 * @param operate
	 * @return
	 */
	private String getOperate(String operate) {
		String op = "=";
		if ("1".equalsIgnoreCase(operate)) {// =
			op = "=";
		} else if ("2".equalsIgnoreCase(operate)) {// >
			op = ">";
		} else if ("3".equalsIgnoreCase(operate)) {// <
			op = "<";
		} else if ("4".equalsIgnoreCase(operate)) {// >=
			op = ">=";
		} else if ("5".equalsIgnoreCase(operate)) {// <=
			op = "<=";
		}
		return op;
	}

	/**
	 * 是否是多选选择器
	 * 
	 * @param controlType
	 * @return
	 */
	private Boolean isrMultiSelector(String controlType) {
		if (BeanUtils.isEmpty(controlType))
			return false;
		Short controlType1 = Short.valueOf(controlType);
		if (controlType1 == FieldPool.SELECTOR_USER_MULTI
				|| controlType1.shortValue() == FieldPool.SELECTOR_ORG_MULTI
				|| controlType1.shortValue() == FieldPool.SELECTOR_POSITION_MULTI
				|| controlType1.shortValue() == FieldPool.SELECTOR_ROLE_MULTI)
			return true;
		return false;
	}

	private Map<String, Object> getQueryValue(SQLClause condition,
			Map<String, Object> params, String field, String type,
			String operate) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type.equals(ColumnModel.COLUMNTYPE_DATE)
				&& "6".equalsIgnoreCase(operate)) {
			String beginKey = SysQuerySetting.DATE_BEGIN + field;
			Object beginVal = null;
			String endKey = SysQuerySetting.DATE_END + field;
			Object endVal = null;
			if (params.containsKey(beginKey))
				beginVal = params.get(beginKey);
			if (params.containsKey(endKey))
				endVal = params.get(endKey);
			if (BeanUtils.isNotEmpty(beginVal) || BeanUtils.isNotEmpty(endVal)) {
				map.put(SysQuerySetting.DATE_BEGIN, beginVal);
				map.put(SysQuerySetting.DATE_END, endVal);
			}
		}
		return map;
	}

	/**
	 * 获得查询的值
	 * 
	 * @param condition
	 * @param params
	 * @param field
	 * @param isSelector
	 * @return
	 */
	private Object getQueryValue(SQLClause condition,
			Map<String, Object> params, String field, Boolean isSelector) {
		int valueFrom = condition.getValueFrom();
		Object value = null;
		switch (valueFrom) {
		case 1:// 输入
				// 是日期类型，又是日期范围
			if (isSelector)
				field = field + TableModel.PK_COLUMN_NAME;
			if (params.containsKey(field)) {
				value = params.get(field);
			}
			break;
		case 2:// 固定值
			value = condition.getValue();
			break;
		case 3:// 脚本
			String script = (String) condition.getValue();
			if (StringUtil.isNotEmpty(script)) {
				value = groovyScriptEngine.executeObject(script, null);
			}
			break;
		case 4:// 自定义变量
				// value =
				// sysTableManage.getParameterMap().get(condition.getValue().toString());
			break;
		}
		return value;
	}

	/**
	 * 是否是选择器
	 * 
	 * @param controlType
	 * @return
	 */
	private Boolean isSelector(String controlType) {
		if (BeanUtils.isEmpty(controlType))
			return false;
		if (controlType.equals(String.valueOf(FieldPool.SELECTOR_USER_SINGLE))
				|| controlType.equals(String
						.valueOf(FieldPool.SELECTOR_USER_MULTI))
				|| controlType.equals(String
						.valueOf(FieldPool.SELECTOR_ORG_SINGLE))
				|| controlType.equals(String
						.valueOf(FieldPool.SELECTOR_ORG_MULTI))
				|| controlType.equals(String
						.valueOf(FieldPool.SELECTOR_POSITION_SINGLE))
				|| controlType.equals(String
						.valueOf(FieldPool.SELECTOR_POSITION_MULTI))
				|| controlType.equals(String
						.valueOf(FieldPool.SELECTOR_ROLE_SINGLE))
				|| controlType.equals(String
						.valueOf(FieldPool.SELECTOR_ROLE_MULTI)))
			return true;
		return false;
	}
}
