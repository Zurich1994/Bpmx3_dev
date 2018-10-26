package com.hotent.platform.service.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.hotent.core.page.PageList;
import javax.annotation.Resource;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.excel.Excel;
import com.hotent.core.page.PageBean;
import com.hotent.core.page.PageUtils;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.JSONUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.form.BpmFormTemplateDao;
import com.hotent.platform.dao.system.SysQuerySettingDao;
import com.hotent.platform.model.form.BpmFormTemplate;
import com.hotent.platform.model.form.CommonVar;
import com.hotent.platform.model.system.Dictionary;
import com.hotent.platform.model.system.SysQueryField;
import com.hotent.platform.model.system.SysQuerySetting;
import com.hotent.platform.model.system.SysQuerySql;
import com.hotent.platform.model.util.FieldPool;

import freemarker.template.TemplateException;

/**
 * <pre>
 * 对象功能:SYS_QUERY_SETTING Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Aschs
 * 创建时间:2014-05-21 17:14:02
 * </pre>
 */
@Service
public class SysQuerySettingService extends BaseService<SysQuerySetting> {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private SysQuerySettingDao dao;
	@Resource
	private BpmFormTemplateDao bpmFormTemplateDao; // 模板
	@Resource
	private SysQuerySqlService sysQuerySqlService;
	@Resource
	private SysQueryFieldService sysQueryFieldService; // 字段配置
	@Resource
	private FreemarkEngine freemarkEngine;// 模板引擎
	@Resource
	private GroovyScriptEngine groovyScriptEngine;
	@Resource
	private SysQueryRightParseService sysQueryRightParseService; // 权限
	@Resource
	private SysQueryDisplayFieldParseService sysQueryDisplayFieldParseService; // 显示列字段
	@Resource
	private SysQueryConditionFieldParseService sysQueryConditionFieldParseService; // 查询条件字段
	@Resource
	private SysQuerySortFieldParseService sysQuerySortFieldParseService; // 排序字段
	@Resource
	private SysQueryFilterFieldParseService sysQueryFilterFieldParseService; // 过滤条件
	@Resource
	private SysQueryExportFieldParseService sysQueryExportFieldParseService; // 导出字段
	@Resource
	private SysQueryManageFieldParseService sysQueryManageFieldParseService; // 功能按钮
	
	@Resource
	private DictionaryService dictionaryService;
	
	@Override
	protected IEntityDao<SysQuerySetting, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 获取配置对象，若【显示列字段】、【查询条件字段】、【导出字段】、【功能按钮】为空，需要初始化默认值
	 * 
	 * @param sqlId
	 * @return
	 * @throws Exception
	 */
	public SysQuerySetting getBySqlId(Long sqlId) throws Exception {
		SysQuerySetting sysQuerySetting = dao.getBySqlId(sqlId);
		// 设置默认【是否分页】
		if(sysQuerySetting.getNeedPage() == null){
			sysQuerySetting.setNeedPage(SysQuerySetting.NO_NEED_PAGE);
		}
		// 设置默认 【显示列字段】
		if (!sysQueryDisplayFieldParseService.hasDisplayField(sysQuerySetting.getDisplayField())) {
			sysQuerySetting.setDisplayField(sysQueryDisplayFieldParseService.getDefaultDisplayField(sqlId));
		}
		// 设置默认 【查询条件字段】
		if (!sysQueryConditionFieldParseService.hasConditionField(sysQuerySetting.getConditionField())) {
			sysQuerySetting.setConditionField(sysQueryConditionFieldParseService.getDefaultConditionField(sqlId));
		}
		// 设置默认 【导出字段 】
		if (!sysQueryExportFieldParseService.hasExportField(sysQuerySetting.getExportField())) {
			sysQuerySetting.setExportField(sysQueryExportFieldParseService.getDefaultExportField(sqlId));
		}
		// 设置默认【功能按钮】
		if (!sysQueryManageFieldParseService.hasManageField(sysQuerySetting.getManageField())) {
			sysQuerySetting.setManageField(sysQueryManageFieldParseService.getDefaultManageButton(sqlId));
		}

		return sysQuerySetting;
	}

	/**
	 * 根据json字符串获取SysQuerySetting对象
	 * 
	 * @param json
	 * @return
	 */
	public SysQuerySetting getSysQuerySetting(String json) {
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if (StringUtil.isEmpty(json))
			return null;
		JSONObject obj = JSONObject.fromObject(json);
		SysQuerySetting sysQuerySetting = (SysQuerySetting) JSONObject.toBean(obj, SysQuerySetting.class);
		return sysQuerySetting;
	}

	/**
	 * 保存信息
	 * 
	 * @param bpmDataTemplate
	 * @param flag
	 *            是否是新增
	 * @throws Exception
	 */
	public void save(SysQuerySetting sysQuerySetting, boolean flag, boolean recreateTemplete) throws Exception {
		String templateHtml = this.generateTemplate(sysQuerySetting);
		sysQuerySetting.setTemplateHtml(templateHtml);
		if (flag) {
			sysQuerySetting.setId(UniqueIdUtil.genId());
			this.add(sysQuerySetting);
		} else {
			if (recreateTemplete) {

			}
			this.update(sysQuerySetting);
		}
	}

	/**
	 * 解析生成第一次的模板，第一次编译模板,主要编译内容有：编译【查询字段】、【导出按钮】、【管理按钮】、【显示按钮】、查询字段的格式化信息
	 * 
	 * 
	 * @param bpmDataTemplate
	 * @return
	 * @throws Exception
	 */
	public String generateTemplate(SysQuerySetting sysQuerySetting) throws Exception {
		BpmFormTemplate bpmFormTemplate = bpmFormTemplateDao.getByTemplateAlias(sysQuerySetting.getTemplateAlias());
		// 是否有条件查询
		boolean hasCondition = sysQueryConditionFieldParseService.hasConditionField(sysQuerySetting.getConditionField());
		// 获取字段设置属性
		List<SysQueryField> sysQueryFieldList = sysQueryFieldService.getListBySqlId(sysQuerySetting.getSqlId());
		// 获取权限map
		Map<String, Object> rightMap = sysQueryRightParseService.getRightMap(ContextUtil.getCurrentUserId(), ContextUtil.getCurrentOrgId());
		// 判断当前用户是否有导出按钮
		boolean hasManageButton = sysQueryManageFieldParseService.hasManageButton(sysQuerySetting.getManageField(), rightMap);
		// 获取当前用户有权限的按钮包括导出按钮
		String manageJsonAry = sysQueryManageFieldParseService.getRightManage(sysQuerySetting, rightMap);
		// 格式化
		
		// 第一次解析模板
		Map<String, Object> map = new HashMap<String, Object>();
		if(sysQueryFieldList!=null){
			Map<String, Object> formatDataMap = this.getFormatDataMap(sysQueryFieldList);
			map.put("formatData", formatDataMap);
		}else{
			map.put("formatData", "");
		}
		
		map.put("manageJsonAry", manageJsonAry);
		map.put("hasManageButton", hasManageButton);
		map.put("sysQuerySetting", sysQuerySetting);
		map.put("hasCondition", hasCondition);
		String templateHtml = freemarkEngine.parseByStringTemplate(map, bpmFormTemplate.getHtml());
		if (logger.isDebugEnabled())
			logger.debug("第一次解析html:" + templateHtml);
		return templateHtml;
	}

	/**
	 * 获取格式化的数据
	 * 
	 * @param bpmFormTable
	 * @return
	 */
	private Map<String, Object> getFormatDataMap(List<SysQueryField> sysQueryFieldList) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 字段信息
		for (SysQueryField sysQueryField : sysQueryFieldList) {
			String fieldName = sysQueryField.getName();
			Short controlType = sysQueryField.getControlType() == null ? 0 : sysQueryField.getControlType();
			String fieldType = sysQueryField.getType();

			// 下面那些判断主要是为了字段值不能直接获取到的，例如日期，下拉框之类需要特殊处理的字段，那些输入框直接输入的就不用
			if (SysQueryField.DATATYPE_DATE.equalsIgnoreCase(fieldType)) {
				map.put(fieldName, sysQueryField.getFormat());
			} else if (FieldPool.SELECT_INPUT == controlType || FieldPool.RADIO_INPUT == controlType || FieldPool.CHECKBOX == controlType) {
				String options = sysQueryField.getControlContent();
				if (StringUtil.isNotEmpty(options)) {
					Map<String, String> optionMap = new HashMap<String, String>();
					JSONArray jarray = JSONArray.fromObject(options);
					for (Object obj : jarray) {
						JSONObject json = (JSONObject) obj;
						String key = (String) json.get("key");
						String value = (String) json.get("val");
						optionMap.put(key, value);
					}
					map.put(fieldName, optionMap);
				}
			} else if (controlType.shortValue() == FieldPool.DICTIONARY) {
				// 数据字典情况
				String dicId = sysQueryField.getControlContent();
				List<Dictionary> dictionarieList = dictionaryService.getByParentId(Long.valueOf(dicId));
				Map<String, String> dicMap = new HashMap<String, String>();
				for (Dictionary dictionary : dictionarieList) {
					dicMap.put(dictionary.getItemName(), dictionary.getItemName());
				}
				map.put(fieldName, dicMap);
			}

		}
		return map;
	}

	/**
	 * 预览数据
	 * 
	 * @param id
	 * @param params
	 *            ：表查询的相关信息参数
	 * @param queryParams
	 *            ：数据库查询条件参数
	 * @return
	 * @throws Exception
	 */
	public String getDisplay(String alias, Map<String, Object> params, Map<String, Object> queryParams) throws Exception {
		// 设置常用变量到params中，其实就是把当前登陆用户id和组织id放到params中
		CommonVar.setCommonVar(params, ContextUtil.getCurrentUserId(), ContextUtil.getCurrentOrgId(), ContextUtil.getCurrentCompanyId());
		// 获取权限map
		Map<String, Object> rightMap = sysQueryRightParseService.getRightMap(ContextUtil.getCurrentUserId(), ContextUtil.getCurrentOrgId());
		SysQuerySetting sysQuerySetting = dao.getByAlias(alias);// 获取业务模板数据
		// 检查请求是否是查询请求
		if (!params.containsKey(SysQuerySetting.PARAMS_KEY_ISQUERYDATA)) {
			params.put(SysQuerySetting.PARAMS_KEY_ISQUERYDATA, sysQuerySetting.getIsQuery() == 0);
		}
		String baseURL = (String) params.get("__baseURL");
		// 获取当前用户有权限的过滤字段
		String filterJsonAry = sysQueryFilterFieldParseService.getRightFilterField(sysQuerySetting, rightMap, baseURL);
		sysQuerySetting.setFilterField(filterJsonAry);

		// 过滤条件设定的Key
		String filterKey = sysQueryFilterFieldParseService.getFilterKey(sysQuerySetting, params);
		params.put(SysQuerySetting.PARAMS_KEY_FILTERKEY, filterKey);
		// 构建URL
		String tableIdCode = (String) params.get("__tic");
		if (tableIdCode == null) {
			tableIdCode = "";
			params.put("__tic", "");
		}
		// 排序
		Map<String, String> sortMap = sysQuerySortFieldParseService.getSortMap(params, tableIdCode);
		// 取得当前过滤的条件
		JSONObject filterJson = sysQueryFilterFieldParseService.getFilterFieldJson(sysQuerySetting, rightMap, params);
		// 获取字段设置属性
		List<SysQueryField> sysQueryFieldList = sysQueryFieldService.getListBySqlId(sysQuerySetting.getSqlId());
		// 格式化的数据
		Map<String, Object> formatData = this.getFormatDataMap(sysQueryFieldList);
		boolean isQueryData = (Boolean) params.get(SysQuerySetting.PARAMS_KEY_ISQUERYDATA);
		if (isQueryData) {
			// 取得数据
			sysQuerySetting = this.getData(sysQuerySetting, rightMap, params, sortMap, formatData, filterJson);
		}

		// 需要排除的url
		List<String> excludes = this.getExcludes(params, queryParams);

		String pageURL = this.addParametersToUrl(baseURL, params, excludes);
		String searchFormURL = baseURL;

		String templateHtml = sysQuerySetting.getTemplateHtml();
		// 如果取不到默认模板，就重新解析
		if (StringUtil.isEmpty(templateHtml))
			templateHtml = this.generateTemplate(sysQuerySetting);

		Map<String, Object> map = new HashMap<String, Object>();
		// 获得分页的数据
		String pageHtml = this.getPageHtml(sysQuerySetting, map, tableIdCode, pageURL);

		// 第二次解析模板
		map.clear();
		map.put("sysQuerySetting", sysQuerySetting);
		map.put("pageHtml", pageHtml);
		map.put("pageURL", pageURL);
		map.put("sort", sortMap);
		map.put("sortField", sortMap.get("sortField"));
		map.put("orderSeq", sortMap.get("orderSeq"));
		map.put("tableIdCode", tableIdCode);
		map.put("searchFormURL", searchFormURL);
		// 当前字段的权限
		map.put("permission", sysQueryDisplayFieldParseService.getDisplayFieldPermission(SysQuerySetting.RIGHT_TYPE_SHOW, sysQuerySetting.getDisplayField(), rightMap));
		// 功能按钮的权限
		map.put("filterKey", filterKey);
		map.put("formatData", formatData);
		map.put("param", queryParams);
		map.put("ctx", params.get(SysQuerySetting.PARAMS_KEY_CTX));
		String html = freemarkEngine.parseByStringTemplate(map, templateHtml);
		logger.info(html);
		return html;
	}

	/**
	 * 获取分页的HTML
	 * 
	 * @param bpmDataTemplate
	 * @param map
	 * @param tableIdCode
	 * @param pageURL
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	private String getPageHtml(SysQuerySetting sysQuerySetting, Map<String, Object> map, String tableIdCode, String pageURL) throws IOException, TemplateException {
		String pageHtml = "";
		if (BeanUtils.isEmpty(sysQuerySetting.getList()))
			return pageHtml;
		// 需要分页
		if (sysQuerySetting.getNeedPage() == 1) {
			PageBean pageBean = sysQuerySetting.getPageBean();
			map.put("tableIdCode", tableIdCode);
			map.put("pageBean", pageBean);
			map.put("showExplain", true);
			map.put("showPageSize", true);
			map.put("baseHref", pageURL);
			map.put("pageURL", pageURL);
			pageHtml = freemarkEngine.mergeTemplateIntoString("pageAjax.ftl", map);
		}
		return pageHtml;
	}

	private String addParametersToUrl(String url, Map<String, Object> params, List<String> excludes) {
		StringBuffer sb = new StringBuffer();
		int idx1 = url.indexOf("?");
		if (idx1 > 0) {
			sb.append(url.substring(0, idx1));
		} else {
			sb.append(url);
		}
		sb.append("?");

		Map<String, Object> map = getQueryStringMap(url);
		if (BeanUtils.isNotEmpty(params)) {
			map.putAll(params);
		}
		// 排除
		if (excludes != null) {
			for (String ex : excludes) {
				map.remove(ex);
			}
		}

		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			if (BeanUtils.isEmpty(val))
				continue;
			sb.append(key);
			sb.append("=");
			sb.append(val);
			sb.append("&");
		}
		return sb.substring(0, sb.length() - 1);
	}

	/**
	 * 获得查询的Map
	 * 
	 * @param url
	 * @return
	 */
	private static Map<String, Object> getQueryStringMap(String url) {
		Map<String, Object> map = new HashMap<String, Object>();
		int idx1 = url.indexOf("?");
		if (idx1 > 0) {
			String queryStr = url.substring(idx1 + 1);
			String[] queryNodeAry = queryStr.split("&");
			for (String queryNode : queryNodeAry) {
				String[] strAry = queryNode.split("=");
				if (strAry.length == 1) {
					map.put(strAry[0], null);
				} else {
					map.put(strAry[0].trim(), strAry[1]);
				}
			}
		}
		return map;
	}

	/**
	 * 排除的参数
	 * 
	 * @param params
	 * @param queryParams
	 * @return
	 */
	private List<String> getExcludes(Map<String, Object> params, Map<String, Object> queryParams) {

		List<String> excludes = new ArrayList<String>();
		for (String key : params.keySet()) {
			if (key.endsWith("__ns__")) {
				excludes.add(key);
			}
			if (key.endsWith("__pk__")) {
				excludes.add(key);
			}
		}
		excludes.add("rand");
		excludes.add("__baseURL");
		excludes.add("__tic");
		excludes.add("__displayId");
		// 排除查询的
		for (Entry<String, Object> entry : queryParams.entrySet()) {
			String key = entry.getKey();
			if (!key.startsWith("Q_"))
				continue;
			String[] aryParaKey = key.split("_");
			if (aryParaKey.length < 3)
				continue;
			String paraName = key.substring(2, key.lastIndexOf("_"));
			excludes.add(paraName);
		}

		return excludes;
	}

	/**
	 * 取的数据
	 * 
	 * @param bpmDataTemplate
	 * @param bpmFormTable
	 * @param rightMap
	 *            权限数据
	 * @param params
	 *            页面传过来的参数
	 * @param sortMap
	 *            排序map
	 * @param formatData
	 *            格式化的数据
	 * @param filterJson
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private SysQuerySetting getData(SysQuerySetting sysQuerySetting, Map<String, Object> rightMap, Map<String, Object> params, Map<String, String> sortMap, Map<String, Object> formatData, JSONObject filterJson) throws Exception {
		SysQuerySql sysQuerySql = sysQuerySqlService.getById(sysQuerySetting.getSqlId());
		// 获取jdbc
		// JdbcHelper jdbcHelper = this.getJdbcHelper(sysQuerySql.getDsalias());
		// DbContextHolder.setDataSource(sysQuerySql.getDsalias());

		String tableIdCode = "";
		if (params.get("__tic") != null) {
			tableIdCode = (String) params.get("__tic");
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "";
		// 是否需要分页。
		if (sysQuerySetting.getNeedPage().shortValue() == SysQuerySetting.NEED_PAGE) {
			int currentPage = 1;
			Object pageObj = params.get(tableIdCode + SysQuerySetting.PAGE);
			if (pageObj != null) {
				currentPage = Integer.parseInt(params.get(tableIdCode + SysQuerySetting.PAGE).toString());
			}
			int pageSize = sysQuerySetting.getPageSize();
			Object pageSizeObj = params.get(tableIdCode + SysQuerySetting.PAGESIZE);
			if (pageSizeObj != null) {
				pageSize = Integer.parseInt(params.get(tableIdCode + SysQuerySetting.PAGESIZE).toString());
			}
			// 获取SQL
			sql = this.getSQL(filterJson, sysQuerySetting, sysQuerySql, rightMap, params, sortMap);
			Object oldPageSizeObj = params.get(tableIdCode + "oz");
			int oldPageSize = sysQuerySetting.getPageSize();
			if (oldPageSizeObj != null) {
				oldPageSize = Integer.parseInt(params.get(tableIdCode + "oz").toString());
			}
			if (pageSize != oldPageSize) {
				int first = PageUtils.getFirstNumber(currentPage, oldPageSize);
				currentPage = first / pageSize + 1;
			}
				
			list = JdbcTemplateUtil.getPage(sysQuerySql.getDsalias(), currentPage, pageSize, sql, params);
			sysQuerySetting.setPageBean(((PageList)list).getPageBean());
		} else {
			// 获取数据
			sql = this.getSQL(filterJson, sysQuerySetting, sysQuerySql, rightMap, params, sortMap);
			list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).queryForList(sql, params);
		}
		
		sysQuerySetting.setList(list);
		logger.debug("查询的SQL：" + sql);
		logger.debug("查询的params：" + params.toString());
		return sysQuerySetting;
	}


	/**
	 * TODO 获取拼接的SQL
	 * 
	 * @param filterJson
	 * 
	 * @param sysQuerySetting
	 * @param sysQuerySql
	 * @param rightMap
	 * @param params
	 * @param sortMap
	 * @return
	 */
	private String getSQL(JSONObject filterJson, SysQuerySetting sysQuerySetting, SysQuerySql sysQuerySql, Map<String, Object> rightMap, Map<String, Object> params, Map<String, String> sortMap) {
		if (JSONUtil.isNotEmpty(filterJson)) {
			String type = (String) filterJson.get("type");
			if ("2".equals(type)) {
				String condition = (String) filterJson.get("condition");
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				paramsMap.put("map", params);
				return groovyScriptEngine.executeString(condition, paramsMap);
			}
		}
		// 以下是拼接SQL的
		StringBuffer sql = new StringBuffer("SELECT * FROM ( ").append(sysQuerySql.getSql()).append(" ) T").append(" WHERE 1=1 ");
		// 主从表关联关系
		String whereStr = "";
		// 过滤条件 sql
		whereStr = sysQueryFilterFieldParseService.getFilterSQL(filterJson);
		// 查询条件 sql
		whereStr = sysQueryConditionFieldParseService.getQuerySQL(sysQuerySetting, whereStr, params);
		String orderStr = sysQuerySortFieldParseService.getOrderBySql(sysQuerySetting.getSortField(), sortMap);
		if (StringUtil.isNotEmpty(whereStr)) {
			whereStr = "AND " + whereStr;
		}
		return sql.append(whereStr).append(orderStr).toString();
	}

	/**
	 * 通过数据源别名获取数据源
	 * 
	 * @param bpmFormTable
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	/*private JdbcHelper getJdbcHelper(String dsAlias) throws Exception {
		return ServiceUtil.getJdbcHelper(dsAlias);
	}*/

	/**
	 * 导出execl
	 * 
	 * @param id
	 * @param exportIds
	 * @param exportType
	 * @param filterKey
	 * @return
	 * @throws Exception
	 */
		public HSSFWorkbook export(Long id, int exportType, Map<String, Object> params) throws Exception {
		Long curUserId = ContextUtil.getCurrentUserId();
		Long curOrgId = ContextUtil.getCurrentOrgId();
		CommonVar.setCommonVar(params, curUserId, curOrgId, ContextUtil.getCurrentCompanyId());
		SysQuerySetting sysQuerySetting = dao.getById(id);
		if (BeanUtils.isEmpty(sysQuerySetting))
			return null;
		// 获取权限map
		Map<String, Object> rightMap = sysQueryRightParseService.getRightMap(curUserId, curOrgId);
		// 当前用户有哪些导出字段的权限
		Map<String, Boolean> exportFieldMap = sysQueryExportFieldParseService.getExportFieldPermission(SysQuerySetting.RIGHT_TYPE_EXPORT, sysQuerySetting.getExportField(), rightMap);
		// 显示的字段的名
		Map<String, String> displayFieldName = sysQueryExportFieldParseService.getExportFieldShowName(sysQuerySetting.getExportField(), exportFieldMap);
		// 格式化的数据
		// Map<String, Object> formatData = this.getFormatDataMap(bpmFormTable);
		// 如果有选择数据, 设置为不需要分页,导出所有的
		if (exportType == 0)
			sysQuerySetting.setNeedPage((short) 0);
		// 获取当前用户有权限的过滤字段
		String filterJsonAry = sysQueryFilterFieldParseService.getRightFilterField(sysQuerySetting, rightMap, "/getDisplay.ht");
		sysQuerySetting.setFilterField(filterJsonAry);
		// 取得当前过滤的条件
		JSONObject filterJson = sysQueryFilterFieldParseService.getFilterFieldJson(sysQuerySetting, rightMap, params);

		// 获取所有显示的数据
		sysQuerySetting = this.getData(sysQuerySetting, rightMap, params, null, new HashMap(), filterJson);
		// 获取有权限显示的数据
		List<Map<String, Object>> rightDataList = sysQueryExportFieldParseService.getRightDataList(sysQuerySetting.getList(), exportFieldMap);
		// 创建新的Excel 工作簿
		Excel excel = sysQueryExportFieldParseService.getExcel(rightDataList, displayFieldName);
		return excel.getWorkBook();
	}
	/**
	 * 
	 * 同步setting的json和Field
	 * 
	 * @param sysQuerySetting
	 * @param fields
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	public void syncSettingAndField(SysQuerySetting sysQuerySetting, List<SysQueryField> fields, SysQuerySql sysQuerySql) {
		syncConditionAndField(sysQuerySetting, fields);
		syncDisplayAndField(sysQuerySetting, fields);
		syncManageFieldAndSql(sysQuerySetting, sysQuerySql);
	}
	/**
	 * 同步sysQuerySetting和sql的管理字段
	 * 
	 * @param sysQuerySetting
	 * @param sysQuerySql
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	private void syncManageFieldAndSql(SysQuerySetting sysQuerySetting, SysQuerySql sysQuerySql) {
		if (sysQuerySql == null || sysQuerySetting.getManageField() == null || sysQuerySetting.getManageField().equals("null") || sysQuerySetting.getManageField().equals("")) {
			return;
		}

		JSONArray result = new JSONArray();
		if(sysQuerySql.getUrlParams()==null) return;
		JSONArray jsonArray = JSONArray.fromObject(sysQuerySql.getUrlParams());
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			JSONArray ja = JSONArray.fromObject(sysQuerySetting.getManageField());
			JSONObject exitJO = null;//在setting已有的字段
			for (int j = 0; j < ja.size(); j++) {
				JSONObject jo = ja.getJSONObject(j);
				if (jsonObject.getString("name").equals(jo.getString("name"))) {
					exitJO = jo;
					break;
				}
			}
			if (exitJO != null) {// 复制已有权限
				jsonObject.put("right", exitJO.getString("right"));
			} else {
				jsonObject.put("right", SysQueryRightParseService.getDefaultRight(SysQuerySetting.RIGHT_TYPE_MANAGE));
			}
			result.add(jsonObject);
		}
		sysQuerySetting.setManageField(result.toString());
		update(sysQuerySetting);
	}

	/**
	 * 
	 * 同步setting的displayfield和field
	 * 
	 * @param sysQuerySetting
	 * @param fields
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	private void syncDisplayAndField(SysQuerySetting sysQuerySetting, List<SysQueryField> fields) {
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		// 检查field的isShow和isSearch字段是否与setting的json相符，
		// 主要为了处理json已设置好了，但field的isShow改变了，那么json的对应字段就应该删除或增加上去 by Aschs
		// 格式应该跟bpmdataTemplete的json格式一样
		JSONArray jsonArray = new JSONArray();

		if (sysQuerySetting.getDisplayField() != null && !sysQuerySetting.getDisplayField().equals("null") && !sysQuerySetting.getDisplayField().equals("")) {
			jsonArray = JSONArray.fromObject(sysQuerySetting.getDisplayField());
		}

		boolean change = false;
		List<JSONObject> newJsons = new ArrayList<JSONObject>();

		for (SysQueryField field : fields) {

			boolean exit = false;// json是否存在field字段
			int exitIndex = 0;// 存在的话，下标位置
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				if (jsonObject != null && jsonObject.getLong("id") == field.getId()) {
					exitIndex = i;
					exit = true;
				}
			}

			if (exit) {
				if (field.getIsShow().equals(SysQueryField.IS_NOT_SHOW)) {// json存在，但是isShow已改为0(false)，那么改删除
					change = true;
					jsonArray.remove(exitIndex);
				}
			}
			if (!exit) {
				if (field.getIsShow().equals(SysQueryField.IS_SHOW)) {// json不存在，但是isShow已改成1(true)，那么添加到json
					change = true;
					JSONObject jsonObject = new JSONObject();
					jsonObject.accumulate("id", field.getId());
					jsonObject.accumulate("name", field.getName());
					jsonObject.accumulate("desc", field.getFieldDesc());
					jsonObject.accumulate("right", sysQueryDisplayFieldParseService.getDefaultDisplayFieldRight());
					// jsonArray.add(jsonObject);
					newJsons.add(jsonObject);
				}
			}

		}

		if (change) {
			if (!newJsons.isEmpty()) {
				jsonArray.addAll(newJsons);
			}
			sysQuerySetting.setDisplayField(jsonArray.toString());// 注意，这里是临时隐藏，数据库的json字段还是没改的，当重新选择了isShow还是有对应的权限配置信息
			// System.out.println(jsonArray.toString());
			update(sysQuerySetting);// 更新数据库
		}
	}

	/**
	 * 
	 * 同步setting的condition_field和field字段
	 * 
	 * @param sysQuerySetting
	 * @param fields
	 *            void
	 * @exception
	 * @since 1.0.0
	 */
	private void syncConditionAndField(SysQuerySetting sysQuerySetting, List<SysQueryField> fields) {
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		JSONArray jsonArray = new JSONArray();
		if (sysQuerySetting.getConditionField() != null && !sysQuerySetting.getConditionField().equals("null") && !sysQuerySetting.getConditionField().equals("")) {
			jsonArray = JSONArray.fromObject(sysQuerySetting.getConditionField());
		}

		boolean change = false;
		List<JSONObject> newJsons = new ArrayList<JSONObject>();
		for (SysQueryField field : fields) {
			boolean exit = false;// json是否存在field字段
			int exitIndex = 0;// 存在的话，下标位置
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				try {
					if (jsonObject != null && jsonObject.getLong("id") == field.getId()) {
						exitIndex = i;
						exit = true;
					}
				} catch (Exception e) {
				}
			}

			if (exit) {
				if (field.getIsSearch().equals(SysQueryField.IS_NOT_SEARCH)) {// json存在，但是isShow已改为0(false)，那么改删除
					change = true;
					jsonArray.remove(exitIndex);
				}
			}
			if (!exit) {
				if (field.getIsSearch().equals(SysQueryField.IS_SEARCH)) {// json不存在，但是isShow已改成1(true)，那么添加到json
					change = true;
					JSONObject jsonObject = new JSONObject();
					jsonObject.accumulate("id", field.getId());
					jsonObject.accumulate("na", field.getName());
					if (field.getType().equals("VARCHAR2")) {
						jsonObject.accumulate("ty", "varchar");
					} else {
						jsonObject.accumulate("ty", field.getType().toLowerCase());// 控件条件跟ty类型有关
					}
					jsonObject.accumulate("op", "1");// 运算符默认为第一个
					jsonObject.accumulate("cm", field.getFieldDesc());
					jsonObject.accumulate("va", "");
					jsonObject.accumulate("vf", "1");
					jsonObject.accumulate("ct", "1");// 控件类型默认为单行文本框
					jsonObject.accumulate("qt", "S");// 查询类型，跟控件类型有关的
					// jsonArray.add(jsonObject);
					newJsons.add(jsonObject);
				}
			}
		}
		if (change) {
			if (!newJsons.isEmpty()) {
				jsonArray.addAll(newJsons);
			}
			sysQuerySetting.setConditionField(jsonArray.toString());
			update(sysQuerySetting);
		}
	}
	
	/**
	 * 删除数据
	 * @param lAryId
	 */
	public void delBySqlIds(Long[] lAryId) {
		if(lAryId != null && lAryId.length > 0){
			for(Long sqlId : lAryId){
				dao.delBySqlId(sqlId);
			}
		}
	}

}
