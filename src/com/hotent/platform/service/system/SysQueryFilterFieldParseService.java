package com.hotent.platform.service.system;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.JSONUtil;
import com.hotent.core.util.StringPool;
import com.hotent.platform.model.system.SysQuerySetting;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.FilterJsonStruct;
import com.hotent.core.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

/**
 * <pre>
 * 对象功能:sql自定义查询 过滤条件解析 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:欧晓斌
 * 创建时间:2014年6月4日15:05:28
 * </pre>
 */
@Service
public class SysQueryFilterFieldParseService {

	@Resource
	private SysQueryRightParseService sysQueryRightParseService;

	/**
	 * 获取有权限的过滤字段
	 * 
	 * @param sysQuerySetting
	 * @param rightMap
	 * @param baseURL
	 * @return
	 */
	public String getRightFilterField(SysQuerySetting sysQuerySetting,
			Map<String, Object> rightMap, String baseURL) {
		String filterField = sysQuerySetting.getFilterField();// 获取过滤条件
		JSONArray jsonArray = JSONArray.fromObject(filterField);
		String destFilterField = new JSONArray().toString();
		if (JSONUtil.isEmpty(jsonArray)) {
			return destFilterField;
		}
		// 有权限过滤条件
		JSONArray jsonAry = new JSONArray();
		for (Object obj : jsonArray) {
			JSONObject jObj = (JSONObject) obj;
			JSONArray rightAry = JSONArray.fromObject(jObj.get("right"));
			JSONObject permission = (JSONObject) rightAry.getJSONObject(0);
			if (sysQueryRightParseService.hasRight(permission, rightMap))
				jsonAry.add(obj);
		}
		if (JSONUtil.isEmpty(jsonAry)) {
			return destFilterField;
		}
		JSONArray destJsonAry = new JSONArray();
		baseURL = baseURL.replace("/getDisplay.ht", "/preview.ht");
		String url = baseURL + "?__displayId__=" + sysQuerySetting.getId();
		for (Object obj : jsonAry) {
			JSONObject json = (JSONObject) obj;
			String name = json.getString("name");
			String key = json.getString("key");
			json.accumulate("desc", StringUtil.subString(name, 5, "..."));// 展示字段
			json.accumulate("url", url + "&"
					+ SysQuerySetting.PARAMS_KEY_FILTERKEY + "=" + key);
			destJsonAry.add(json);
		}
		return destJsonAry.toString();
	}

	/**
	 * 过滤条件KEY
	 * 
	 * @param sysQuerySetting
	 * @param params
	 * @return
	 */
	public String getFilterKey(SysQuerySetting sysQuerySetting,
			Map<String, Object> params) {
		Object key = params.get(SysQuerySetting.PARAMS_KEY_FILTERKEY);
		if (BeanUtils.isNotEmpty(key))
			return (String) key;
		String filterField = sysQuerySetting.getFilterField();
		if (StringUtil.isEmpty(filterField))
			return "";
		JSONArray jsonAry = JSONArray.fromObject(filterField);
		if (JSONUtils.isNull(jsonAry) || jsonAry.size() == 0)
			return "";
		JSONObject jsonObj = jsonAry.getJSONObject(0);
		return jsonObj.getString("key");
	}

	/**
	 * 获取过滤字段json
	 * 
	 * @param sysQuerySetting
	 * @param rightMap
	 * @param params
	 * @return
	 */
	public JSONObject getFilterFieldJson(SysQuerySetting sysQuerySetting,
			Map<String, Object> rightMap, Map<String, Object> params) {
		JSONObject filterJson = this.getFilterFieldJson(
				sysQuerySetting.getFilterField(), rightMap, params);
		if (JSONUtil.isEmpty(filterJson)) {
			JSONArray jsonAry = new JSONArray();
			jsonAry.add(this.getDefaultFilterJson());
			sysQuerySetting.setFilterField(jsonAry.toString());
		}
		return filterJson;
	}

	/**
	 * 默认的条件
	 * 
	 * @return
	 */
	private JSONObject getDefaultFilterJson() {
		return JSONObject
				.fromObject("{\"name\":\"默认条件 \",\"key\":\"Default\",\"type\":\"1\",\"condition\":\"[]\",\"right\":[{\"s\":3,\"type\":\"everyone\",\"id\":\"\",\"name\":\"\",\"script\":\"\"}]}");
	}

	/**
	 * 获取过滤字段的JSON 如果没有值取默认
	 * 
	 * @param filterField
	 * @param isFilter
	 * @param rightMap
	 * @param params
	 * @return
	 */
	private JSONObject getFilterFieldJson(String filterField,
			Map<String, Object> rightMap, Map<String, Object> params) {
		JSONObject filterJson = this.getFilterJson(filterField, rightMap,
				params);
		return filterJson;
	}
	
	/**
	 * 取出满足过滤条件的JSON对象
	 * 
	 * @param filterField
	 * @param rightMap
	 *            权限
	 * @param params
	 *            参数
	 * @return
	 */
	private JSONObject getFilterJson(String filterField,
			Map<String, Object> rightMap, Map<String, Object> params) {
		JSONObject jsonObj = null;
		JSONArray jsonAry = JSONArray.fromObject(filterField);
		if (JSONUtil.isEmpty(jsonAry))
			return jsonObj;
		// 取得满足key的条件
		String filterKey = (String) params
				.get(SysQuerySetting.PARAMS_KEY_FILTERKEY);
		if (StringUtil.isEmpty(filterKey)) {
			jsonObj = jsonAry.getJSONObject(0);
		} else {
			for (Object obj : jsonAry) {
				JSONObject jObj = (JSONObject) obj;
				String key = (String) jObj.get("key");
				if (key.equals(filterKey)) {
					jsonObj = jObj;// 取出满足的Key
					break;
				}
			}
		}
		return jsonObj;
	}
	
	/**
	 * 获取过滤的SQL
	 * 
	 * @param jsonObj
	 * 
	 * @param bpmDataTemplate
	 * @param tableMap
	 *            表对应的表Map<表，真实表名>
	 * @param rightMap
	 * @param params
	 * @param relationMap
	 * @return
	 */
	public String getFilterSQL(JSONObject filterJson) {
		if (JSONUtil.isEmpty(filterJson))
			return "";
		//
		List<Map<String, Object>> operatorList = new ArrayList<Map<String, Object>>();
		// 转换成SQL
		List<FilterJsonStruct> filters = com.alibaba.fastjson.JSONArray
				.parseArray(filterJson.get("condition").toString(),
						FilterJsonStruct.class);
		this.getFilterResult(filters, operatorList);

		return this.executeOperator(operatorList);
	}

	/**
	 * 过滤所有的条件
	 * 
	 * @param filters
	 * @param tableMap
	 * @param relationMap
	 * @param operatorList
	 */
	private void getFilterResult(List<FilterJsonStruct> filters,
			List<Map<String, Object>> operatorList) {
		for (FilterJsonStruct filter : filters) {
			// 组合条件
			if (filter.getBranch()) {
				List<Map<String, Object>> branchResultList = new ArrayList<Map<String, Object>>();
				this.getFilterResult(filter.getSub(), branchResultList);
				String branchResult = this.executeOperator(branchResultList);
				Map<String, Object> resultMap = this.getResultMap(
						filter.getCompType(), branchResult);
				operatorList.add(resultMap);
			} else {
				if (filter.getRuleType().intValue() == 2) {// 脚本直接返回结果
					String script = filter.getScript();
					if (StringUtil.isNotEmpty(script)) {
						Map<String, Object> resultMap = this.getResultMap(
								filter.getCompType(), script);
						operatorList.add(resultMap);
					}
				} else {
					this.getNormalFilterResult(filter, operatorList);
				}
			}
		}
	}

	/**
	 * 获取普通条件的结果
	 * 
	 * @param filter
	 * @param tableMap
	 * @param relationMap
	 * @param operatorList
	 */
	private void getNormalFilterResult(FilterJsonStruct filter,
			List<Map<String, Object>> operatorList) {
		String flowvarKey = filter.getFlowvarKey();
		// String table = filter.getTable();
		// String source = filter.getSource();

		// 获取表的数据
		// this.setTableMap(filter, tableMap, relationMap);

		String script = "";
		switch (filter.getOptType()) {
		case 1:// 数字
		case 2:// 字符串
				// 条件一
			if (StringUtils.isNotEmpty(filter.getJudgeVal1())) {
				script = this.getCompareScript(filter.getJudgeCon1(),
						flowvarKey, filter.getJudgeVal1(), filter.getOptType(),
						filter.getIsHidden());
			}
			// 条件二
			if (StringUtils.isNotEmpty(filter.getJudgeVal2())) {
				String moreScript = getCompareScript(filter.getJudgeCon2(),
						flowvarKey, filter.getJudgeVal2(), filter.getOptType(),
						filter.getIsHidden());
				if (StringUtils.isNotEmpty(script))
					script = script + SysQuerySetting.CONDITION_AND;
				script = script + moreScript;
			}
			break;
		case 3:// 日期
				// 条件一
			if (StringUtils.isNotEmpty(filter.getJudgeVal1())) {
				String val1 = this.sqlToDate(filter.getJudgeVal1(),
						filter.getDatefmt());
				script = this.getCompareScript(filter.getJudgeCon1(),
						flowvarKey, val1, filter.getOptType(),
						filter.getIsHidden());
			}
			// 条件二
			if (StringUtils.isNotEmpty(filter.getJudgeVal2())) {
				String val2 = this.sqlToDate(filter.getJudgeVal2(),
						filter.getDatefmt());
				String moreScript = getCompareScript(filter.getJudgeCon2(),
						flowvarKey, val2, filter.getOptType(),
						filter.getIsHidden());
				if (StringUtils.isNotEmpty(script))
					script = script + SysQuerySetting.CONDITION_AND;
				script = script + moreScript;
			}
			break;
		case 4:// 字典
			String[] vals = filter.getJudgeVal1().split("&&");
			for (String val : vals) {
				if (StringUtils.isNotEmpty(script))
					script += SysQuerySetting.CONDITION_AND;
				script += getCompareScript(filter.getJudgeCon1(), flowvarKey,
						val, filter.getOptType(), filter.getIsHidden());
			}
			break;
		case 5:// 角色、组织、岗位选择器
			String judgeCon = filter.getJudgeCon1();
			String[] ids = filter.getJudgeVal1().split("&&");
			if (ids.length == 2) {
				script = getCompareScript(judgeCon, filter.getFlowvarKey(),
						ids[0], filter.getOptType(), filter.getIsHidden());
			} else {// 特殊类型的
				if ("3".equalsIgnoreCase(judgeCon)
						|| "4".equalsIgnoreCase(judgeCon))
					script = getCompareScript(judgeCon, filter.getFlowvarKey(),
							filter.getJudgeVal1(), filter.getOptType(),
							filter.getIsHidden());

			}
			break;
		}

		if (StringUtil.isEmpty(script))
			return;
		// 执行结果记录到operatorList中
		Map<String, Object> resultMap = this.getResultMap(filter.getCompType(),
				script);
		operatorList.add(resultMap);
	}

	/**
	 * 格式日期
	 * 
	 * @param val
	 * @param datefmt
	 * @return
	 */
	private String sqlToDate(String val, String datefmt) {
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
	 * 
	 * @param judgeCon
	 *            判断条件
	 * @param flowvarKey
	 *            字段
	 * @param judgeVal
	 *            字段的值
	 * @param type
	 *            类型
	 * @param table
	 *            表名
	 * @param source
	 *            数据来源
	 * @param isHidden
	 * @return
	 */
	private String getCompareScript(String judgeCon, String fieldName,
			String judgeVal, int type, int isHidden) {
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
				sb.append(fieldName).append("=").append("'").append(judgeVal)
						.append("'");
			} else if ("2".equals(judgeCon)) {
				sb.append(fieldName).append("!=").append("'").append(judgeVal)
						.append("'");
			} else if ("3".equals(judgeCon)) {
				sb.append("UPPER(").append(fieldName).append(")=")
						.append(" UPPER('").append(judgeVal).append("')");
			} else if ("4".equals(judgeCon)) {
				sb.append(fieldName).append(" LIKE").append(" '%")
						.append(judgeVal).append("%'");
			} else if ("5".equals(judgeCon)) {
				sb.append(fieldName).append(" LIKE").append(" '")
						.append(judgeVal).append("%'");
			} else if ("6".equals(judgeCon)) {
				sb.append(fieldName).append(" LIKE").append(" '%")
						.append(judgeVal).append("'");
			}
			break;
		case 5:// 人员选择器
			if (isHidden == BpmFormField.NO_HIDDEN)
				fieldName = fieldName + TableModel.PK_COLUMN_NAME;

			if ("1".equals(judgeCon)) {
				sb.append(fieldName).append(" in (").append("")
						.append(judgeVal).append(")");
			} else if ("2".equals(judgeCon)) {
				sb.append(fieldName).append(" not in (").append("")
						.append(judgeVal).append(")");
			} else if ("3".equals(judgeCon)) {
				sb.append(fieldName).append(" = :").append("").append(judgeVal)
						.append("");
			} else if ("4".equals(judgeCon)) {
				sb.append(fieldName).append(" != :").append("")
						.append(judgeVal).append("");
			}

			break;
		default:
			break;
		}
		return sb.toString();
	}

	private Map<String, Object> getResultMap(String operator, String result) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("operator", operator);
		resultMap.put("result", result);
		return resultMap;
	}

	/**
	 * 获取SQL运算结果
	 * 
	 * @param operatorList
	 * @return
	 */
	private String executeOperator(List<Map<String, Object>> operatorList) {
		if (operatorList.size() == 0)
			return "";
		String returnVal = (String) operatorList.get(0).get("result");
		if (operatorList.size() == 1)
			return returnVal;
		int size = operatorList.size();
		for (int k = 1; k < size; k++) {
			Map<String, Object> resultMap = operatorList.get(k);
			String operator = resultMap.get("operator").toString();
			if ("or".equals(operator)) { // 或运算
				returnVal = "(" + returnVal + ") OR ("
						+ resultMap.get("result") + ")";
			} else if ("and".equals(operator)) { // 与运算
				returnVal = "(" + returnVal + ") AND ("
						+ resultMap.get("result") + ")";
			}
		}
		if (StringUtils.isNotEmpty(returnVal))
			returnVal = "(" + returnVal + ")";
		return returnVal;
	}



}
