package com.hotent.core.web.query.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.displaytag.util.ParamEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;

import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.mybatis.Dialect;
import com.hotent.core.page.PageBean;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.DialectUtil;
import com.hotent.core.util.JSONUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.query.entity.FieldLogic;
import com.hotent.core.web.query.entity.FieldSort;
import com.hotent.core.web.query.entity.FieldTableUtil;
import com.hotent.core.web.query.entity.Filter;
import com.hotent.core.web.query.entity.JudgeScope;
import com.hotent.core.web.query.entity.JudgeScript;
import com.hotent.core.web.query.entity.JudgeSingle;
import com.hotent.core.web.query.scan.SearchCache;
import com.hotent.core.web.query.scan.TableEntity;
import com.hotent.core.web.query.scan.TableField;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.dao.system.SysUserOrgDao;
import com.hotent.platform.model.bus.BusQueryFilter;
import com.hotent.platform.model.bus.BusQueryRule;
import com.hotent.platform.model.bus.BusQuerySetting;
import com.hotent.platform.model.form.CommonVar;
import com.hotent.platform.model.form.FilterJsonStruct;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SysUserOrg;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.service.bus.BusQueryFilterService;
import com.hotent.platform.service.bus.BusQueryRuleService;
import com.hotent.platform.service.bus.BusQuerySettingService;
import com.hotent.platform.service.system.PositionService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysRoleService;
import com.hotent.platform.service.system.SysUserOrgService;

/**
 * <pre>
 * 对象功能:查询帮助类
 * 	开发公司:广州宏天软件有限公司
 * 	开发人员:zxh
 * 	创建时间:2013-10-16 16:38:00
 * </pre>
 * 
 */
public class QueryUtil {
	@Resource
	private static JdbcTemplate jdbcTemplate;

	public static Logger logger = LoggerFactory.getLogger(QueryUtil.class);

	// private static ThreadLocal<JdbcHelper<?, ?>> jdbcHelperLocal = new ThreadLocal<JdbcHelper<?, ?>>();
	//
	// public static ThreadLocal<JdbcHelper<?, ?>> getJdbcHelperLocal() {
	// return jdbcHelperLocal;
	// }
	//
	// public static void setJdbcHelperLocal(
	// ThreadLocal<JdbcHelper<?, ?>> jdbcHelperLocal) {
	// QueryUtil.jdbcHelperLocal = jdbcHelperLocal;
	// }

	/**
	 * 将符合BPM_DATA_TEMPLATE表的FILTERFIELD字段的字符串内容转成FilterJsonStruct集合 这样是为了重用。
	 * 
	 * @param filterJson
	 * @param filterKey
	 * @return
	 */
	public static List<FilterJsonStruct> buildFilterJsonStructs(JSONObject filterJson) {
		if (JSONUtil.isEmpty(filterJson))
			return new ArrayList<FilterJsonStruct>();
		return buildFilterJsonStructsByCondition(filterJson.get("condition").toString());
	}

	/**
	 * 获得当前使用的过滤器
	 * 
	 * @param filterField
	 * @param filterKey
	 * @return
	 */
	private static JSONObject getFilterJson(String filterField, String filterKey) {
		if (StringUtils.isEmpty(filterField))
			return null;
		JSONArray jsonArray = JSONArray.fromObject(filterField);
		if (JSONUtil.isEmpty(jsonArray))
			return null;
		// 如果没有过滤Key取默认的
		if (StringUtils.isEmpty(filterKey))
			return jsonArray.getJSONObject(0);
		for (Object obj : jsonArray) {
			JSONObject jObj = (JSONObject) obj;
			String key = (String) jObj.get("key");
			if (key.equals(filterKey)) {
				return jObj;
			}
		}
		return null;
	}

	/**
	 * 将将符合BPM_DATA_TEMPLATE表的FILTERFIELD字段的字符串内容的condition部分转成FilterJsonStruct集合 这样是为了重用。
	 * 
	 * @param condition
	 * @return
	 */
	public static List<FilterJsonStruct> buildFilterJsonStructsByCondition(String condition) {
		return com.alibaba.fastjson.JSONArray.parseArray(condition, FilterJsonStruct.class);
	}

	/**
	 * 将FilterJsonStruct的集合转成FieldLogic的集合. <br/>
	 * FieldLogic是高级查询的数据封装。
	 * 
	 * @param filterJsonStructs
	 * @return
	 */
	public static List<FieldLogic> buildFieldLogics(List<FilterJsonStruct> filterJsonStructs) {
		Map<String, Object> params = new HashMap<String, Object>();
		CommonVar.setCommonVar(params, ContextUtil.getCurrentUserId(), ContextUtil.getCurrentOrgId(),ContextUtil.getCurrentCompanyId());
		List<FieldLogic> fieldLogics = new ArrayList<FieldLogic>();
		for (Iterator<FilterJsonStruct> iterator = filterJsonStructs.iterator(); iterator.hasNext();) {
			FilterJsonStruct filterJsonStruct = iterator.next();
			FieldLogic fieldLogic = buildFieldLogic(filterJsonStruct, params);
			if (filterJsonStruct.getBranch()) {// 组合条件
				List<FieldLogic> groupLogics = buildFieldLogics(filterJsonStruct.getSub());
				fieldLogic.setGroupLogics(groupLogics);
			}
			fieldLogics.add(fieldLogic);
		}
		return fieldLogics;
	}

	/**
	 * 将单个filterJsonStruct对象转成FieldLogic
	 * 
	 * @param filterJsonStruct
	 * @param params
	 * @return
	 */
	public static FieldLogic buildFieldLogic(FilterJsonStruct filter, Map<String, Object> params) {
		FieldLogic fieldLogic = new FieldLogic();
		if (filter.getRuleType().intValue() == FilterJsonStruct.RULE_TYPE_SCRIPT) {// 脚本
			fieldLogic.setFieldRelation(QueryConstants.QUERY_AND);
			fieldLogic.setJudgeType(QueryConstants.JUDGE_SCRIPT);
			fieldLogic.setJudgeScript(buildJudgeScript(filter));
		} else {// 其它类型
			fieldLogic.setGroup(filter.getBranch());
			fieldLogic.setFieldName(filter.getFlowvarKey());
			fieldLogic.setDataType(filter.getOptType());
			fieldLogic.setTableName(filter.getTable());
			fieldLogic.setFieldRelation(filter.getCompType());
			if (StringUtils.isEmpty(filter.getJudgeVal2())) {// 单值
				fieldLogic.setJudgeType(QueryConstants.JUDGE_SINGLE);
				fieldLogic.setJudgeSingle(buildJudgeSingle(filter, params));
			} else {// 范围值
				fieldLogic.setJudgeType(QueryConstants.JUDGE_SCOPE);
				fieldLogic.setJudgeScope(buildJudgeScope(filter));
			}
		}
		return fieldLogic;
	}

	/**
	 * 从FieldLogics集合或者其中表名的集合，返回Set对象，所以不会重复。
	 * 
	 * @param fieldLogics
	 * @return
	 */
	public static Set<String> getTables(List<FieldLogic> fieldLogics) {
		Set<String> tableSet = new java.util.HashSet<String>();
		if (fieldLogics == null || fieldLogics.size() == 0)
			return tableSet;
		for (Iterator<FieldLogic> iterator = fieldLogics.iterator(); iterator.hasNext();) {
			FieldLogic fieldLogic = iterator.next();
			if (StringUtils.isNotEmpty(fieldLogic.getTableName()))
				tableSet.add(fieldLogic.getTableName());

			if (fieldLogic.isGroup()) {
				Set<String> groupSet = getTables(fieldLogic.getGroupLogics());
				tableSet.addAll(groupSet);
			}
		}
		return tableSet;
	}

	/**
	 * 将排序部分的sortJson转成FieldSort的集合。
	 * 
	 * @param sortJson
	 * @return
	 */
	public static List<FieldSort> buildFieldSorts(String sortJson) {
		if (StringUtils.isEmpty(sortJson))
			return new ArrayList<FieldSort>();
		List<FieldSort> fieldSorts = com.alibaba.fastjson.JSONArray.parseArray(sortJson, FieldSort.class);
		return fieldSorts;
	}

	/**
	 * 获取判定的值
	 * 
	 * @param judgeVal
	 * @param compType
	 * @param judgeCon
	 *            判定符
	 * @param params
	 * @return
	 */
	private static String getJudeValue(String judgeVal, Integer optType, String judgeCon, Map<String, Object> params) {
		String judgeValue = "";
		Object o = null;
		switch (optType) {
		case 1:// 数字
		case 2:// 字符串
		case 3:// 日期
			judgeValue = judgeVal;
			break;
		case 4:// 字典
			String[] vals = judgeVal.split("&&");
			for (String val : vals) {
				judgeValue += val + ",";
			}
			judgeValue = StringUtils.removeEnd(judgeValue, ",");
			break;
		case 5:// 角色、组织、岗位选择器
			String[] ids = judgeVal.split("&&");
			if (ids.length == 2) {
				judgeValue = ids[0];
			} else {// 特殊类型的
				if ("3".equalsIgnoreCase(judgeCon) || "4".equalsIgnoreCase(judgeCon))
					// TODO 把判定值替换表单变量
					o = params.get(judgeVal);
				if (o != null)
					judgeValue = String.valueOf(o);

			}
			break;
		}
		return judgeValue;
	}

	/**
	 * 根据FilterJsonStruct构造JudgeSingle（范围值）
	 * 
	 * @param filterJsonStruct
	 * @param params
	 * @return
	 */
	private static JudgeSingle buildJudgeSingle(FilterJsonStruct filterJsonStruct, Map<String, Object> params) {
		JudgeSingle judgeSingle = new JudgeSingle();
		judgeSingle.setTableName(filterJsonStruct.getTable());
		judgeSingle.setFieldName(filterJsonStruct.getFlowvarKey());
		judgeSingle.setCompare(filterJsonStruct.getJudgeCon1());
		judgeSingle.setValue(getJudeValue(filterJsonStruct.getJudgeVal1(), filterJsonStruct.getOptType(), filterJsonStruct.getJudgeCon1(), params));
		return judgeSingle;
	}

	/**
	 * 根据FilterJsonStruct构造JudgeScope
	 * 
	 * @param filterJsonStruct
	 * @return
	 */
	private static JudgeScope buildJudgeScope(FilterJsonStruct filterJsonStruct) {
		JudgeScope judgeScope = new JudgeScope();
		judgeScope.setTableName(filterJsonStruct.getTable());
		judgeScope.setFieldName(filterJsonStruct.getFlowvarKey());
		judgeScope.setCompare(filterJsonStruct.getJudgeCon1());
		judgeScope.setValue(filterJsonStruct.getJudgeVal1());
		judgeScope.setCompareEnd(filterJsonStruct.getJudgeCon2());
		judgeScope.setValueEnd(getJudeValue(filterJsonStruct.getJudgeVal2(), filterJsonStruct.getOptType(), filterJsonStruct.getJudgeCon1(), null));
		// 范围值的关系硬编码为与关系。以后可以扩展这里也可以设置
		judgeScope.setRelation(QueryConstants.QUERY_AND);
		judgeScope.setOptType(filterJsonStruct.getOptType());
		return judgeScope;
	}

	/**
	 * 根据FilterJsonStruct构造JudgeScript
	 * 
	 * @param filter
	 * @return
	 */
	private static JudgeScript buildJudgeScript(FilterJsonStruct filterJsonStruct) {
		JudgeScript judgeScript = new JudgeScript();
		judgeScript.setValue(filterJsonStruct.getScript());
		return judgeScript;
	}

	/**
	 * 获取提交的过滤条件
	 * 
	 * @param request
	 * @param tableEntity
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static List<FilterJsonStruct> getPostQueryFilter(HttpServletRequest request, TableEntity tableEntity) {
		// 只对主表的查询,子表也需要吧
		List<TableField> mainTableFieldList = tableEntity.getTableFieldList();
		if (BeanUtils.isEmpty(mainTableFieldList))
			return null;
		String mainTableName = tableEntity.getTableName();
		Map<String, String> tableFieldMap = getTableFieldMap(mainTableFieldList);
		List<FilterJsonStruct> list = new ArrayList<FilterJsonStruct>();
		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String key = params.nextElement().toString();
			String[] values = request.getParameterValues(key);
			if (values.length > 0 && StringUtils.isNotEmpty(values[0])) {
				if (key.startsWith("Q_")) {// 只针对查询条件的
					FilterJsonStruct filterJsonStruct = setFilterJsonStruct(key, values, mainTableName, tableFieldMap);
					if (BeanUtils.isEmpty(filterJsonStruct))
						continue;
					list.add(filterJsonStruct);
				}
			}
		}
		return list;
	}

	private static FilterJsonStruct setFilterJsonStruct(String key, String[] values, String mainTableName, Map<String, String> tableFieldMap) {
		String[] aryParaKey = key.split("_");
		if (aryParaKey.length < 3)
			return null;
		String paraName = key.substring(2, key.lastIndexOf("_"));
		String type = key.substring(key.lastIndexOf("_") + 1);
		String tableField = getTableField(tableFieldMap, paraName, type);
		String relation = getQueryRelation(type);
		if (StringUtils.isEmpty(tableField))
			return null;

		FilterJsonStruct f = new FilterJsonStruct();
		if (values.length == 1) {
			String value = values[0].trim();
			if (StringUtil.isNotEmpty(value)) {
				try {
					if (value.indexOf("_") != -1) {
						value = value.replaceAll("_", "|_");
					}
					f = getFilterJsonStruct(mainTableName, tableField, paraName, relation);
				} catch (Exception e) {
					logger.debug(e.getMessage());
				}
			}
		}
		return f;
	}

	/**
	 * 处理日期字段
	 * 
	 * @param tableFieldMap
	 * @param paraName
	 * @param type
	 * @return
	 */
	private static String getTableField(Map<String, String> tableFieldMap, String paraName, String type) {
		if ("DL".equals(type)) {
			paraName = StringUtils.removeStart(paraName, "begin");
		}
		// date end
		else if ("DG".equals(type)) {
			paraName = StringUtils.removeStart(paraName, "end");
		}
		return tableFieldMap.get(paraName);
	}

	/**
	 * 获取查询的关联关系
	 * 
	 * @param type
	 *            页面传入的类型 S、SL、DL等
	 * @return
	 */
	private static String getQueryRelation(String type) {
		String relation = QueryConstants.SQL_RELATION_EQUAL;
		// 字符串、长整形、整形等 精准匹配
		if ("S".equals(type) || "L".equals(type) || "N".equals(type) || "DB".equals(type) || "BD".equals(type) || "FT".equals(type) || "SN".equals(type)) {
			relation = QueryConstants.SQL_RELATION_EQUAL;
		}
		// 字符串 模糊查询
		else if ("SL".equals(type) || "SLR".equals(type) || "SLL".equals(type) || "SUPL".equals(type) || "SUPLR".equals(type) || "SUPLL".equals(type) || "SLOL".equals(type) || "SLOLR".equals(type) || "SLOLL".equals(type)) {
			relation = QueryConstants.SQL_RELATION_LIKE;
		}
		// date begin
		else if ("DL".equals(type)) {
			relation = QueryConstants.SQL_RELATION_GT_EQUAL;
		}
		// date end
		else if ("DG".equals(type)) {
			relation = QueryConstants.SQL_RELATION_LT_EQUAL;
		}

		return relation;
	}

	/**
	 * 根据传入的类型获得真正值的格式
	 * 
	 * @param mainTableName
	 * @param tableField
	 * @param paraName
	 * @param relation
	 * @param value
	 * @return
	 */
	private static FilterJsonStruct getFilterJsonStruct(String tableName, String fieldName, String paraName, String relation) {
		FilterJsonStruct f = new FilterJsonStruct();
		f.setRuleType(FilterJsonStruct.RULE_TYPE_SCRIPT);
		StringBuffer script = new StringBuffer();
		// 不知道是什么数据类型的，直接拼接SQL
		// 拼接SQL
		script.append(FieldTableUtil.fixFieldName(fieldName, tableName)).append(" ").append(relation).append(" :").append(paraName);

		f.setScript(script.toString());
		return f;
	}

	private static Map<String, String> getTableFieldMap(List<TableField> mainTableFieldList) {
		Map<String, String> map = new HashMap<String, String>();
		for (TableField tableField : mainTableFieldList) {
			map.put(tableField.getVar(), tableField.getName());
		}
		return map;
	}

	/**
	 * 获取页面提交的排序字段
	 * 
	 * @param queryFilter
	 *            查询过滤
	 * @return
	 */
	private static FieldSort getPostFieldSort(QueryFilter queryFilter, String tableName) {
		if (queryFilter == null)
			return null;
		Map<String, Object> filters = queryFilter.getFilters();
		Object orderField = filters.get("orderField");
		if (BeanUtils.isEmpty(orderField))
			return null;
		Object orderSeq = filters.get("orderSeq");
		return new FieldSort(tableName, String.valueOf(orderField), String.valueOf(orderSeq));
	}

	/**
	 * 取得设置的排序
	 * 
	 * @param sortField
	 * @param tableName
	 * @return
	 */
	private static List<FieldSort> buildFieldSort(String sortField, String tableName) {
		if (StringUtils.isEmpty(sortField) || StringUtils.isEmpty(tableName))
			return null;
		List<FieldSort> list = new ArrayList<FieldSort>();
		JSONArray jsonArray = JSONArray.fromObject(sortField);
		if (JSONUtil.isEmpty(jsonArray))
			return null;
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObj = (JSONObject) jsonArray.get(i);
			String name = (String) jsonObj.get("name");
			String sort = (String) jsonObj.get("sort");
			list.add(new FieldSort(tableName, name, sort));
		}
		return list;
	}

	// TODO
	/**
	 * 获得查询脚本SQL
	 * 
	 * @param busQueryRule
	 * @param tableEntity
	 * @param queryFilter
	 * @param params
	 * @param busQueryFilter
	 * @return
	 */
	public static String getQuerySQL(BusQueryRule busQueryRule, TableEntity tableEntity, QueryFilter queryFilter, Map<String, Object> params, BusQueryFilter busQueryFilter) {
		// 获得从页面提交的json串
		List<FilterJsonStruct> postFilterJsonStructs = null;
		FieldSort fieldSort = null;
		HttpServletRequest request = queryFilter.getRequest();
		// 获得过滤的条件
		String filterKey = RequestUtil.getString(request, QueryConstants.FILTER_KEY);
		if (BeanUtils.isNotEmpty(busQueryFilter)) {
			filterKey = busQueryFilter.getFilterKey();
			postFilterJsonStructs = getBusQueryFilter(busQueryFilter, tableEntity);
			fieldSort = getQuerySort(busQueryFilter, tableEntity);
		} else {
			// 通过queryFilter里面的request处理获得
			postFilterJsonStructs = getPostQueryFilter(request, tableEntity);
			// 通过queryFilter里面的request处理获得排序条件
			fieldSort = getPostFieldSort(queryFilter, tableEntity.getTableName());
		}
		JSONObject filterJson = getFilterJson(busQueryRule.getFilterField(), filterKey);
		// 使用SQL
		if (JSONUtil.isNotEmpty(filterJson)) {
			String type = (String) filterJson.get("type");
			if ("2".equals(type)) {
				String condition = (String) filterJson.get("condition");
				Map<String, Object> paramsMap = new HashMap<String, Object>();

				Long userId = ContextUtil.getCurrentUserId();
				Long curOrgId = ContextUtil.getCurrentOrgId();
				CommonVar.setCommonVar(params, userId, curOrgId,ContextUtil.getCurrentCompanyId());
				paramsMap.put("map", params);
				paramsMap.put("sort", getFieldSortMap(fieldSort));
				GroovyScriptEngine groovyScriptEngine = (GroovyScriptEngine) AppUtil.getBean(GroovyScriptEngine.class);
				String querySQL = groovyScriptEngine.executeString(condition, paramsMap);
				logger.info(querySQL);
				return querySQL;
			}
		}

		List<FilterJsonStruct> filterJsonStructs = buildFilterJsonStructs(filterJson);

		// 合并两部分
		if (BeanUtils.isNotEmpty(postFilterJsonStructs))
			filterJsonStructs.addAll(postFilterJsonStructs);

		// =========== 构造查询条件===========
		List<FieldLogic> fieldLogics = buildFieldLogics(filterJsonStructs);

		// =========== 构造排序条件===========
		//
		List<FieldSort> fieldSorts = new ArrayList<FieldSort>();

		if (fieldSort != null)
			fieldSorts.add(fieldSort);
		else
			// 取得设置的排序
			fieldSorts = buildFieldSort(busQueryRule.getSortField(), tableEntity.getTableName());

		// 获得查询SQL
		String querySQL = QueryBuilder.buildSql(tableEntity.getTableName(), fieldLogics, fieldSorts);

		logger.info(querySQL);
		return querySQL;
	}

	/**
	 * 获取过滤器的排序
	 * 
	 * @param fieldSort
	 * @return
	 */
	private static Map<String, Object> getFieldSortMap(FieldSort fieldSort) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (BeanUtils.isEmpty(fieldSort))
			return map;

		map.put("orderField", fieldSort.getFieldName());
		map.put("orderSeq", fieldSort.getOrderBy());
		map.put("table", fieldSort.getTableName());
		return map;
	}

	/**
	 * 获取过滤器的排序
	 * 
	 * @param busQueryFilter
	 * @param tableEntity
	 * @return
	 */
	private static FieldSort getQuerySort(BusQueryFilter busQueryFilter, TableEntity tableEntity) {
		String sortParameter = busQueryFilter.getSortParameter();
		if (StringUtils.isEmpty(sortParameter))
			return null;
		JSONObject json = JSONObject.fromObject(sortParameter);
		if (JSONUtil.isEmpty(json))
			return null;
		Object orderField = json.get("orderField");
		if (BeanUtils.isEmpty(orderField))
			return null;
		Object orderSeq = json.get("orderSeq");
		return new FieldSort(tableEntity.getTableName(), String.valueOf(orderField), String.valueOf(orderSeq));
	}

	/**
	 * 获取过滤器的条件
	 * 
	 * @param busQueryFilter
	 * @param tableEntity
	 * @return
	 */
	private static List<FilterJsonStruct> getBusQueryFilter(BusQueryFilter busQueryFilter, TableEntity tableEntity) {
		String queryParameter = busQueryFilter.getQueryParameter();
		if (StringUtils.isEmpty(queryParameter))
			return null;
		JSONArray jsonArray = JSONArray.fromObject(queryParameter);
		if (JSONUtil.isEmpty(jsonArray))
			return null;

		List<TableField> mainTableFieldList = tableEntity.getTableFieldList();
		if (BeanUtils.isEmpty(mainTableFieldList))
			return null;
		String mainTableName = tableEntity.getTableName();
		Map<String, String> tableFieldMap = getTableFieldMap(mainTableFieldList);

		List<FilterJsonStruct> list = new ArrayList<FilterJsonStruct>();
		for (Object obj : jsonArray) {
			JSONObject jObj = (JSONObject) obj;
			String paraName = (String) jObj.get("paraName");
			String type = (String) jObj.get("type");
			String tableField = getTableField(tableFieldMap, paraName, type);
			String relation = getQueryRelation(type);
			if (StringUtils.isEmpty(tableField))
				return null;
			FilterJsonStruct filterJsonStruct = getFilterJsonStruct(mainTableName, tableField, paraName, relation);

			list.add(filterJsonStruct);
		}
		return list;
	}

	/**
	 * 从配置文件中读取配置属性数据的信息
	 * 
	 * @return
	 * @throws Exception
	 */
	// public static JdbcHelper<?, ?> getConfigJdbcHelper() {
	// try {
	//
	// if (jdbcHelperLocal.get() == null) {
	// String dbType = AppConfigUtil.get("jdbc.dbType");
	// String className = AppConfigUtil.get("jdbc.driverClassName");
	// String url = AppConfigUtil.get("jdbc.url");
	// String user = AppConfigUtil.get("jdbc.username");
	// String pwd = AppConfigUtil.get("jdbc.password");
	// JdbcHelper<?, ?> jdbcHelper = JdbcHelper.getJdbcHelper(user,
	// className, url, user, pwd, dbType);
	// jdbcHelperLocal.set(jdbcHelper);
	// return jdbcHelper;
	// } else
	// return jdbcHelperLocal.get();
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

	// TODO 获取分页List
	/**
	 * 
	 * 获取分页List
	 * 
	 * @param dataRule
	 * @param queryFilter
	 * @return
	 */
	public static List<?> getPageList(BusQueryRule busQueryRule, QueryFilter queryFilter) {
		if (StringUtils.isEmpty(busQueryRule.getFilterKey()) && busQueryRule.getIsFilter().shortValue() == QueryConstants.IS_QUERY_NOT) {
			// 设置回请求对象，避免前台页面报错
			queryFilter.setForWeb();
			return null;
		}

		List<?> list = null;
		HttpServletRequest request = queryFilter.getRequest();
		short isQuery = getIsQuery(request, busQueryRule);
		// 是否初始化查询
		if (isQuery == QueryConstants.IS_QUERY_YES) {
			TableEntity tableEntity = getTableEntity(busQueryRule.getTableName());
			BusQueryFilter busQueryFilter = getBusQueryFilter(busQueryRule.getFilterFlag());
			Map<String, Object> paraMap = getFilters(queryFilter.getFilters(), busQueryFilter);

			// 获取查询SQL
			String querySQL = getQuerySQL(busQueryRule, tableEntity, queryFilter, paraMap, busQueryFilter);
			// JdbcHelper<?, ?> jdbcHelper = getConfigJdbcHelper();

			// 是否分页
			if (busQueryRule.getNeedPage().intValue() == 1) {// 分页
				ParamEncoder paramEncoder = queryFilter.getParamEncoder();
				String tableIdCode = paramEncoder.encodeParameterName("");
				PageBean page = queryFilter.getPageBean();
				int currentPage = page.getCurrentPage();
				int pageSize = page.getPageSize();
				int oldPageSize = RequestUtil.getInt(request, tableIdCode + "oz", -1);
				if (oldPageSize < 0)
					pageSize = (page.getPageSize() != busQueryRule.getPageSize()) ? busQueryRule.getPageSize() : page.getPageSize();
				PageBean pageBean = new PageBean(currentPage, pageSize);
				queryFilter.setPageBean(pageBean);
				// list = jdbcHelper.getPage(querySQL, paraMap, pageBean);
				list = JdbcTemplateUtil.getPage(DataSourceUtil.DEFAULT_DATASOURCE, querySQL, paraMap, pageBean);

			} else {
				list = jdbcTemplate.queryForList(querySQL, paraMap);
			}
		}
		// 设置回请求对象，避免前台页面报错
		queryFilter.setForWeb();
		return list;
	}

	/**
	 * 获取是否查询
	 * 
	 * @param request
	 * @param busQueryRule
	 * @return
	 */
	private static short getIsQuery(HttpServletRequest request, BusQueryRule busQueryRule) {
		if (BeanUtils.isEmpty(busQueryRule))
			return QueryConstants.IS_QUERY_YES;
		Short isQuery = RequestUtil.getShort(request, QueryConstants.IS_QUERY, null);
		if (BeanUtils.isEmpty(isQuery))
			return busQueryRule.getIsQuery();
		return isQuery;
	}

	/**
	 * 获取过滤条件的Map
	 * 
	 * @param filters
	 * @param busQueryFilter
	 * @return
	 */
	private static Map<String, Object> getFilters(Map<String, Object> filters, BusQueryFilter busQueryFilter) {
		if (BeanUtils.isEmpty(busQueryFilter))
			return filters;
		String queryParameter = busQueryFilter.getQueryParameter();
		if (StringUtils.isEmpty(queryParameter))
			return null;
		JSONArray jsonArray = JSONArray.fromObject(queryParameter);
		if (JSONUtil.isEmpty(jsonArray))
			return null;
		filters = new HashMap<String, Object>();
		for (Object obj : jsonArray) {
			JSONObject jObj = (JSONObject) obj;
			String paraName = (String) jObj.get("paraName");
			String value = (String) jObj.get("value");
			filters.put(paraName, value);
		}
		return filters;
	}

	/**
	 * 获取默认排序
	 * 
	 * @param id
	 * @return
	 */
	private static BusQueryFilter getBusQueryFilter(Long id) {
		if (BeanUtils.isNotEmpty(id)) {
			BusQueryFilterService busQueryFilterService = (BusQueryFilterService) AppUtil.getBean(BusQueryFilterService.class);
			return busQueryFilterService.getById(id);
		}
		return null;
	}

	/**
	 * 获得表实体数据
	 * 
	 * @param request
	 * @return
	 */
	public static TableEntity getTableEntity(HttpServletRequest request) {
		// 获得URL
		String uri = request.getRequestURI();
		// 解析出实体参数名
		String var = StringUtils.substring(uri, 0, uri.lastIndexOf("/"));
		var = StringUtils.substring(var, var.lastIndexOf("/") + 1);
		// 获得TableEntity
		return SearchCache.getTableVarMap().get(var);
	}

	/**
	 * 
	 * 先根据displaytagId获得,如果获取不到，通过变量
	 * 
	 * @param request
	 * @return
	 */
	public static TableEntity getTableEntity(String displayTagId, HttpServletRequest request) {
		TableEntity tableEntity = SearchCache.getDisplayTagIdMap().get(displayTagId);
		if (tableEntity != null)
			return tableEntity;
		return getTableEntity(request);
	}

	/**
	 * 获得表实体数据
	 * 
	 * @param request
	 * @return
	 */
	public static TableEntity getTableEntity(String tableName) {
		// 获得TableEntity
		return SearchCache.getTableEntityMap().get(tableName);
	}

	/**
	 * 是否是超级管理员
	 * 
	 * @return
	 */
	public static boolean isSuperAdmin() {
		List<GrantedAuthority> roles = (List<GrantedAuthority>) ContextUtil.getCurrentUser().getAuthorities();
		boolean hasAuth = false;
		if (roles.size() > 0) {
			// 先判断有没有超级管理员角色
			for (GrantedAuthority grantedAuthority : roles) {
				if (grantedAuthority.equals(SystemConst.ROLE_GRANT_SUPER)) { // 超级管理员
					hasAuth = true;
					break;
				}
			}
		}
		if (hasAuth)
			return true;
		return false;
	}

	/**
	 * 获取查询规则
	 * 
	 * 
	 * @param rightMap
	 * @return
	 */
	public static BusQueryRule getBusQueryRule(String displayTagId, HttpServletRequest request) {
		String url = request.getRequestURI();
		TableEntity tableEntity = getTableEntity(displayTagId, request);

		if (BeanUtils.isEmpty(tableEntity))
			return null;
		String tableName = tableEntity.getTableName();

		// 如果是管理员没有限制
		if (SysUser.isSuperAdmin()) {
			BusQueryRule busQueryRule = new BusQueryRule();
			busQueryRule.setUrl(url);
			busQueryRule.setTableName(tableName);
			return busQueryRule;
		}
		// 实例化bean
		BusQueryRuleService busQueryRuleService = (BusQueryRuleService) AppUtil.getBean(BusQueryRuleService.class);

		BusQueryRule busQueryRule = busQueryRuleService.getByTableName(tableName);

		if (BeanUtils.isEmpty(busQueryRule)) {
			busQueryRule = new BusQueryRule();
			busQueryRule.setUrl(url);
			busQueryRule.setTableName(tableName);
			return busQueryRule;
		}

		Long filterFlag = RequestUtil.getLong(request, QueryConstants.FILTER_FLAG, null);
		// 当前用户的权限
		Map<String, Object> rightMap = QueryUtil.getRightMap();
		// 取得有权限的过滤条件，如果有权限的过滤条件为空则返回null
		busQueryRule = getRightFilter(busQueryRule, rightMap);
		// 过滤条件的Key
		String filterKey = QueryUtil.getFilterKey(busQueryRule, request);
		// 字段权限
		Map<String, Boolean> permission = QueryUtil.getPermission(busQueryRule, rightMap);
		// 当前用户的权限
		BusQuerySettingService busQuerySettingService = (BusQuerySettingService) AppUtil.getBean(BusQuerySettingService.class);
		BusQuerySetting busQuerySetting = busQuerySettingService.getByTableNameUserId(tableName, ContextUtil.getCurrentUserId());
		permission = getPermissionSetting(permission, busQuerySetting);
		// 过滤列表
		List<Filter> filterList = QueryUtil.getFilter(busQueryRule, rightMap, filterKey);
		busQueryRule.setFilterKey(filterKey);
		busQueryRule.setFilterList(filterList);
		busQueryRule.setPermission(permission);
		busQueryRule.setUrl(url);
		busQueryRule.setFilterFlag(filterFlag);
		return busQueryRule;
	}

	private static Map<String, Boolean> getPermissionSetting(Map<String, Boolean> permission, BusQuerySetting busQuerySetting) {
		if (BeanUtils.isEmpty(busQuerySetting))
			return permission;
		if (BeanUtils.isEmpty(busQuerySetting.getDisplayField()))
			return permission;

		Map<String, Boolean> right = getDisplayFieldShow(busQuerySetting.getDisplayField());
		return getPermissionSetting(permission, right);
	}

	private static Map<String, Boolean> getPermissionSetting(Map<String, Boolean> permission, Map<String, Boolean> right) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		for (Iterator<Entry<String, Boolean>> it = permission.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Boolean> e = (Map.Entry<String, Boolean>) it.next();
			String key = e.getKey();
			boolean val = e.getValue();
			if (val)
				val = BeanUtils.isEmpty(right.get(key)) ? val : right.get(key);
			map.put(key, val);
		}
		return map;
	}

	private static Map<String, Boolean> getDisplayFieldShow(String displayField) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		if (StringUtil.isEmpty(displayField))
			return null;

		JSONArray jsonAry = JSONArray.fromObject(displayField);

		for (Object obj : jsonAry) {
			JSONObject json = JSONObject.fromObject(obj);
			String name = (String) json.get("name");
			String show = (String) json.get("show");
			map.put(name, "1".equals(show) ? false : true);
		}
		return map;
	}

	/**
	 * 取得有权限的过滤条件，如果有权限的过滤条件为空则返回null
	 * 
	 * @param busQueryRule
	 * @param rightMap
	 * @return
	 */
	private static BusQueryRule getRightFilter(BusQueryRule busQueryRule, Map<String, Object> rightMap) {
		String filterField = busQueryRule.getFilterField();
		JSONArray jsonArray = JSONArray.fromObject(filterField);
		String destFilterField = new JSONArray().toString();
		if (JSONUtil.isEmpty(jsonArray)) {
			busQueryRule.setFilterField(destFilterField);
			return busQueryRule;
		}

		// 有权限过滤条件
		JSONArray jsonAry = new JSONArray();
		for (Object obj : jsonArray) {
			JSONObject jObj = (JSONObject) obj;
			JSONArray rightAry = JSONArray.fromObject(jObj.get("right"));
			Iterator<JSONObject> iterator = rightAry.iterator();
			while(iterator.hasNext()){
				JSONObject permission = iterator.next();
				if(permission.containsKey("type")&&hasRight(permission, rightMap)){
					jsonAry.add(obj);
					break;
				}
					
			}
		}
		if (JSONUtil.isEmpty(jsonAry)) {
			busQueryRule.setFilterField(destFilterField);
			return busQueryRule;
		}

		busQueryRule.setFilterField(jsonAry.toString());
		return busQueryRule;
	}

	/**
	 * 获取权限
	 * 
	 * @param busQueryRule
	 * @param rightMap
	 * @return
	 */
	public static Map<String, Boolean> getPermission(BusQueryRule busQueryRule, Map<String, Object> rightMap) {
		if (BeanUtils.isEmpty(busQueryRule))
			return null;
		return getPermission(0, busQueryRule.getDisplayField(), rightMap);
	}

	private static Map<String, Boolean> getPermission(int type, String displayField, Map<String, Object> rightMap) {
		JSONArray jsonAry = JSONArray.fromObject(displayField);
		return getPermissionMap(type, jsonAry, rightMap);
	}
	
	public static String getRightsName(JSONObject json){
		String var = (String) json.get("variable");
		if(StringUtil.isEmpty(var))
			var = json.getString("name");
		return var;
	}

	/**
	 * 获取权限map
	 * 
	 * @param jsonAry
	 * @param type
	 * @param rightMap
	 * @return
	 */
	public static Map<String, Boolean> getPermissionMap(int type, JSONArray jsonAry, Map<String, Object> rightMap) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		if (JSONUtil.isEmpty(jsonAry))
			return map;
		for (Object obj : jsonAry) {
			JSONObject json = JSONObject.fromObject(obj);
			String var = QueryUtil.getRightsName(json);
			JSONArray rights = (JSONArray) json.get("right");
			Iterator<JSONObject> iterator = rights.iterator();
			boolean hasRight = false;
			while(true){
				JSONObject permission = iterator.next();
				Integer s = (Integer) permission.get("s");
				if (s==null||s.intValue() == type&&permission.containsKey("type")){
					hasRight = hasRight(permission, rightMap);
					if(hasRight){
						map.put(var, hasRight);
						break;
					}
				}
				if(!iterator.hasNext()){
					map.put(var, hasRight);
					break;
				}
			}
		}
		return map;
	}
	

	/**
	 * 判断是否有权限。
	 * 
	 * @param permission
	 * @param rightMap
	 *            权限map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean hasRight(JSONObject permission, Map<String, Object> rightMap) {
		String type = permission.get("type").toString();
		Object script = permission.get("script");
		if ("none".equals(type)) // 无
			return false;

		if ("everyone".equals(type))// 所有人
			return true;

		String idd = permission.get("id").toString();
		String[] userIdd = null;
		String[] curOrgIdd = null;
		if(BeanUtils.isNotEmpty(rightMap.get("userId"))){
			userIdd = rightMap.get("userId").toString().split(",");
		}
		if(BeanUtils.isNotEmpty(rightMap.get("curOrgId"))){
			curOrgIdd = rightMap.get("curOrgId").toString().split(",");
		}
		
		List<SysRole> roles = (List<SysRole>) rightMap.get("roles");
		List<Position> positions = (List<Position>) rightMap.get("positions");
		List<SysOrg> orgs = (List<SysOrg>) rightMap.get("orgs");
		List<SysUserOrg> ownOrgs = (List<SysUserOrg>) rightMap.get("ownOrgs");
		// 指定用户
		if ("user".equals(type)) {
			if(BeanUtils.isNotEmpty(userIdd))
				for(String ui:userIdd){
					return StringUtil.contain(idd, ui);
				}
		}
		// 指定角色
		else if ("role".equals(type)) {
			if(BeanUtils.isNotEmpty(roles))
				for (SysRole role : roles) {
					if (StringUtil.contain(idd, role.getRoleId().toString())) {
						return true;
					}
				}
		}
		// 指定组织
		else if ("org".equals(type)) {
			if(BeanUtils.isNotEmpty(orgs))
				for (SysOrg org : orgs) {
					if (StringUtil.contain(idd, org.getOrgId().toString())) {
						return true;
					}
				}
		}
		// 组织负责人
		else if ("orgMgr".equals(type)) {
			if(BeanUtils.isNotEmpty(ownOrgs))
				for (SysUserOrg sysUserOrg : ownOrgs) {
					if (StringUtil.contain(idd, sysUserOrg.getOrgId().toString())) {
						return true;
					}
				}
		}
		// 岗位
		else if ("pos".equals(type)) {
			if(BeanUtils.isNotEmpty(positions))
				for (Position position : positions) {
					if (StringUtil.contain(idd, position.getPosId().toString())) {
						return true;
					}
				}
		} else if ("script".equals(type)) {
			if (BeanUtils.isEmpty(script))
				return false;
			Map<String, Object> map = new HashMap<String, Object>();
			if(userIdd!=null&&curOrgIdd!=null)
				CommonVar.setCommonVar(map, Long.parseLong(userIdd[0]), Long.parseLong(curOrgIdd[0]),ContextUtil.getCurrentCompanyId()); 
			GroovyScriptEngine groovyScriptEngine = (GroovyScriptEngine) AppUtil.getBean(GroovyScriptEngine.class);
			return groovyScriptEngine.executeBoolean(script.toString(), map);
		}
		return false;
	}

	/**
	 * 获取当前用户的权限Map
	 * 
	 * @return
	 */
	public static Map<String, Object> getRightMap() {
		if (SysUser.isSuperAdmin())// 如果是超级管理员
			return null;

		Long userId = ContextUtil.getCurrentUserId();
		Long curOrgId = ContextUtil.getCurrentOrgId();

		// 实例化bean
		SysRoleService sysRoleService = (SysRoleService) AppUtil.getBean(SysRoleService.class);
		PositionService positionService = (PositionService) AppUtil.getBean(PositionService.class);
		SysOrgService sysOrgService = (SysOrgService) AppUtil.getBean(SysOrgService.class);
		SysUserOrgService sysUserOrgService = AppUtil.getBean(SysUserOrgService.class);

		Map<String, Object> map = new HashMap<String, Object>();
		List<SysRole> roles = sysRoleService.getByUserId(userId);
		List<Position> positions = positionService.getByUserId(userId);
		List<SysOrg> orgs = sysOrgService.getOrgsByUserId(userId);
		// 获取可以管理的组织列表。
		List<SysUserOrg> ownOrgs = sysUserOrgService.getChargeOrgByUserId(userId);

		map.put("userId", userId);
		map.put("curOrgId", curOrgId);
		map.put("roles", roles);
		map.put("positions", positions);
		map.put("orgs", orgs);
		map.put("ownOrgs", ownOrgs);
		return map;
	}

	/**
	 * 获取过滤条件
	 * 
	 * @param busQueryRule
	 * @param rightMap
	 * @param filterKey
	 * @return
	 */
	public static List<Filter> getFilter(BusQueryRule busQueryRule, Map<String, Object> rightMap, String filterKey) {
		if (BeanUtils.isEmpty(busQueryRule) || StringUtils.isEmpty(busQueryRule.getFilterField()))
			return null;
		JSONArray jsonAry = JSONArray.fromObject(busQueryRule.getFilterField());
		if (JSONUtil.isEmpty(jsonAry))
			return null;
		List<Filter> list = new ArrayList<Filter>();
		for (Object obj : jsonAry) {
			JSONObject jObj = (JSONObject) obj;
			String name = (String) jObj.get("name");
			String key = (String) jObj.get("key");
			JSONArray rightAry = JSONArray.fromObject(jObj.get("right"));
			JSONObject permission = (JSONObject) rightAry.getJSONObject(0);
			if (hasRight(permission, rightMap)) {// 是否有权限
				Filter filter = new Filter();
				filter.setName(name);
				filter.setDesc(StringUtil.subString(name, 5, "..."));
				filter.setKey(key);
				list.add(filter);
			}
		}

		return list;
	}

	/**
	 * 获取默认过滤条件的Key
	 * 
	 * @param busQueryRule
	 * @param request
	 * @return
	 */
	public static String getFilterKey(BusQueryRule busQueryRule, HttpServletRequest request) {
		String filterKey = RequestUtil.getString(request, QueryConstants.FILTER_KEY);
		if (StringUtils.isNotEmpty(filterKey) || BeanUtils.isEmpty(busQueryRule))
			return filterKey;
		JSONArray jsonArray = JSONArray.fromObject(busQueryRule.getFilterField());
		if (JSONUtil.isEmpty(jsonArray))
			return filterKey;
		return jsonArray.getJSONObject(0).getString("key");
	}

}
