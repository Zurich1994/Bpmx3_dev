/**
 * 描述：TODO
 * 包名：com.hotent.platform.model.form.sqldatatmp
 * 文件名：BpmSqlDataTemplate.java
 * 作者：User-mailto:liyj@jee-soft.cn
 * 日期2014-5-20-上午9:21:15
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.platform.model.form.sqldatatmp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;

import com.hotent.core.page.PageBean;

/**
 * <pre>
 * 描述：TODO
 * 构建组：bpm_3.3
 * 作者：lyj
 * 邮箱:liyj@jee-soft.cn
 * 日期:2014-5-20-上午9:21:15
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class BpmSqlDataTemplate {
	// 主键
	protected Long id;
	// 名称
	protected String name;
	// 别名
	protected String alias;
	//最重要的sql语句，数据是根据这个生成的
	protected String sql;
	// 样式：0-列表，1-树形
	protected Short style = 0;
	// 是否需要分页：0-不分页，1-分页
	protected Short needPage = 1;
	// 分页大小
	protected Integer pageSize = 20;
	// 数据模板别名
	protected String templateAlias;
	// 数据模板代码
	protected String templateHtml;
	// 是否启用过滤条件
	protected Short filterType = 0;
	// 过滤条件
	protected String filterField;
	//字段信息
	protected List<BpmSqlDataTemplateField> fields;
	/**
	 * 数据来源（ 1来自自定义表）
	 */
	// 以下非数据库字段
	// 分页数据
	// private List<HashMap<String, Object>>list1=new ArrayList<HashMap<String, Object>>();
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	// 分页bean。
	private PageBean pageBean;
	// 流程定义标题
	private String subject;
	/**
	 * id
	 * @return  the id
	 * @since   1.0.0
	 */
	
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * name
	 * @return  the name
	 * @since   1.0.0
	 */
	
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * alias
	 * @return  the alias
	 * @since   1.0.0
	 */
	
	public String getAlias() {
		return alias;
	}
	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	/**
	 * sql
	 * @return  the sql
	 * @since   1.0.0
	 */
	
	public String getSql() {
		return sql;
	}
	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}
	/**
	 * style
	 * @return  the style
	 * @since   1.0.0
	 */
	
	public Short getStyle() {
		return style;
	}
	/**
	 * @param style the style to set
	 */
	public void setStyle(Short style) {
		this.style = style;
	}
	/**
	 * needPage
	 * @return  the needPage
	 * @since   1.0.0
	 */
	
	public Short getNeedPage() {
		return needPage;
	}
	/**
	 * @param needPage the needPage to set
	 */
	public void setNeedPage(Short needPage) {
		this.needPage = needPage;
	}
	/**
	 * pageSize
	 * @return  the pageSize
	 * @since   1.0.0
	 */
	
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * templateAlias
	 * @return  the templateAlias
	 * @since   1.0.0
	 */
	
	public String getTemplateAlias() {
		return templateAlias;
	}
	/**
	 * @param templateAlias the templateAlias to set
	 */
	public void setTemplateAlias(String templateAlias) {
		this.templateAlias = templateAlias;
	}
	/**
	 * templateHtml
	 * @return  the templateHtml
	 * @since   1.0.0
	 */
	
	public String getTemplateHtml() {
		return templateHtml;
	}
	/**
	 * @param templateHtml the templateHtml to set
	 */
	public void setTemplateHtml(String templateHtml) {
		this.templateHtml = templateHtml;
	}
	/**
	 * filterType
	 * @return  the filterType
	 * @since   1.0.0
	 */
	
	public Short getFilterType() {
		return filterType;
	}
	/**
	 * @param filterType the filterType to set
	 */
	public void setFilterType(Short filterType) {
		this.filterType = filterType;
	}
	/**
	 * filterField
	 * @return  the filterField
	 * @since   1.0.0
	 */
	
	public String getFilterField() {
		return filterField;
	}
	/**
	 * @param filterField the filterField to set
	 */
	public void setFilterField(String filterField) {
		this.filterField = filterField;
	}
	/**
	 * fields
	 * @return  the fields
	 * @since   1.0.0
	 */
	
	public List<BpmSqlDataTemplateField> getFields() {
		return fields;
	}
	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<BpmSqlDataTemplateField> fields) {
		this.fields = fields;
	}
	/**
	 * list
	 * @return  the list
	 * @since   1.0.0
	 */
	
	public List<Map<String, Object>> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	/**
	 * pageBean
	 * @return  the pageBean
	 * @since   1.0.0
	 */
	
	public PageBean getPageBean() {
		return pageBean;
	}
	/**
	 * @param pageBean the pageBean to set
	 */
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	/**
	 * subject
	 * @return  the subject
	 * @since   1.0.0
	 */
	
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
	
	

}
