package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;

/**
 * 对象功能:SYS_QUERY_SQL Model对象 开发公司:广州宏天软件有限公司 开发人员:Aschs 创建时间:2014-05-22 12:42:13
 */
public class SysQuerySql extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 不是自定义参数
	 */
	public static final short IS_NOT_CUSTOM_PARAM = 0;
	/**
	 * 是自定义参数
	 */
	public static final short IS_CUSTOM_PARAM = 1;
	
	
	/**
	 * id
	 */
	protected Long id;
	/**
	 * sql语句
	 */
	protected String sql;
	/**
	 * 名称
	 */
	protected String name;
	/**
	 * 数据源
	 */
	protected String dsalias;
	/**
	 * 分类ID
	 */
	private Long categoryId;
	
	/**
	 * 分类名 
	 */
	private String categoryName;
	
	/**
	 * 自定义url参数，数据json结构为： [{"name":"view","desc":"查看","url_path":
	 * "/platform/form/bpmDataTemplate/view.ht",
	 * "url_params":[{"key":"test1","value"
	 * ,"test1"},{"key":"test2","value":"teset2"}]}]
	 */
	protected String urlParams;

	/**
	 * get() and set()
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDsalias() {
		return dsalias;
	}

	public void setDsalias(String dsalias) {
		this.dsalias = dsalias;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUrlParams() {
		return urlParams;
	}

	public void setUrlParams(String urlParams) {
		this.urlParams = urlParams;
	}

}