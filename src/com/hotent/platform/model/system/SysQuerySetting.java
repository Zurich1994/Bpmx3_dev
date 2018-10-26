package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hotent.core.model.BaseModel;
import com.hotent.core.page.PageBean;


/**
 * 对象功能:SYS_QUERY_SETTING Model对象 开发公司:广州宏天软件有限公司 开发人员:Aschs 创建时间:2014-05-22
 * 12:43:13
 */
public class SysQuerySetting extends BaseModel {

	private static final long serialVersionUID = 1L;
	/** 权限类型（显示）=0 */
	public static final int RIGHT_TYPE_SHOW = 0;
	/** 权限类型（打印）=1 */
	public static final int RIGHT_TYPE_PRINT = 1;
	/** 权限类型（导出）=2 */
	public static final int RIGHT_TYPE_EXPORT = 2;
	/** 权限类型（过滤条件）=3 */
	public static final int RIGHT_TYPE_FILTER = 3;
	/** 权限类型（管理）=4 */
	public static final int RIGHT_TYPE_MANAGE = 4;

	/** 参数标识(过滤条件KEY) */
	public static final String PARAMS_KEY_FILTERKEY = "__filterKey__";
	/** 参数标识(当前路径) */
	public static final String PARAMS_KEY_CTX = "__ctx";
	public static final String PARAMS_KEY_ISQUERYDATA = "__isQueryData";

	/**
	 * 导出按钮表示
	 */
	public static final String EXPORT_BUTTON = "exportButton";
	
	/** SQL 条件 and */
	public static final String CONDITION_AND = " AND ";

	/** SQL 条件 or */
	public static final String CONDITION_OR = " OR ";

	/**
	 * 日期开始结束标记
	 */
	public static final String DATE_BEGIN = "begin";
	public static final String DATE_END = "end";

	/**
	 * 分页常量
	 */
	public static final String PAGE = "p";
	/**
	 * 分页大小常量。
	 */
	public static final String PAGESIZE = "z";
	/**
	 * 排序常量
	 */
	public static final String SORTFIELD = "s";
	/**
	 * 排序方向常量
	 */
	public static final String ORDERSEQ = "o";

	/**
	 * 显示样式 列表
	 */
	public static final int STYLE_LIST = 0;
	/**
	 * 显示样式 树型
	 */
	public static final int STYLE_TREE = 1;
	
	public static final Short NO_NEED_PAGE = 0;	
	public static final Short NEED_PAGE = 1;	
	

	// ID
	protected Long id;
	// sqlID
	protected Long sqlId;
	// 名称
	protected String name;
	// 别名
	protected String alias;
	// 显示类型，0表示列表，1表示树形，默认为0
	protected Short style;
	// 需要分页
	protected Short needPage;
	// 分页大小
	protected Short pageSize;
	// 是否默认查询
	protected Short isQuery;
	// 模板别名
	protected String templateAlias;
	// 模板html，第一次解析的
	protected String templateHtml;
	/*
	 * 显示字段(json)
	 * [{"name":"sfhk","desc":"是否汇款","type":"varchar","style":"yyyy-MM-dd","right":[{"s":0,"type":"none","id":"","name":"","script":""}]}]
	 */
	protected String displayField;
	// 过滤字段(json)
	protected String filterField;
	// 查询字段(json)
	protected String conditionField;
	/*
	 * 排序字段(json)
	 * [{"name":"dddh","desc":"订单单号","sort":"DESC"}]
	 */
	protected String sortField;
	// 导出字段(json)
	protected String exportField;
	// 管理字段(json)
	protected String manageField;

	
	//以下非数据库字段
	// 分页数据
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	// 分页bean。
	private PageBean pageBean;
	
	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 ID
	 * 
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	public void setSqlId(Long sqlId) {
		this.sqlId = sqlId;
	}

	/**
	 * 返回 SQL_ID
	 * 
	 * @return
	 */
	public Long getSqlId() {
		return this.sqlId;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 NAME
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * 返回 ALIAS
	 * 
	 * @return
	 */
	public String getAlias() {
		return this.alias;
	}

	public void setTemplateAlias(String templateAlias) {
		this.templateAlias = templateAlias;
	}

	/**
	 * 返回 TEMPLATE_ALIAS
	 * 
	 * @return
	 */
	public String getTemplateAlias() {
		return this.templateAlias;
	}

	public void setTemplateHtml(String templateHtml) {
		this.templateHtml = templateHtml;
	}

	/**
	 * 返回 TEMPLATE_HTML
	 * 
	 * @return
	 */
	public String getTemplateHtml() {
		return this.templateHtml;
	}

	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}

	/**
	 * 返回 DISPLAY_FIELD
	 * 
	 * @return
	 */
	public String getDisplayField() {
		return this.displayField;
	}

	public void setFilterField(String filterField) {
		this.filterField = filterField;
	}

	/**
	 * 返回 FILTER_FIELD
	 * 
	 * @return
	 */
	public String getFilterField() {
		return this.filterField;
	}

	public void setConditionField(String conditionField) {
		this.conditionField = conditionField;
	}

	/**
	 * 返回 CONDITION_FIELD
	 * 
	 * @return
	 */
	public String getConditionField() {
		return this.conditionField;
	}

	public Short getStyle() {
		return style;
	}

	public void setStyle(Short style) {
		this.style = style;
	}

	public Short getNeedPage() {
		return needPage;
	}

	public void setNeedPage(Short needPage) {
		this.needPage = needPage;
	}

	public Short getPageSize() {
		return pageSize;
	}

	public void setPageSize(Short pageSize) {
		this.pageSize = pageSize;
	}

	public Short getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(Short isQuery) {
		this.isQuery = isQuery;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getExportField() {
		return exportField;
	}

	public void setExportField(String exportField) {
		this.exportField = exportField;
	}

	public String getManageField() {
		return manageField;
	}

	public void setManageField(String manageField) {
		this.manageField = manageField;
	}
	


}