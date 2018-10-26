package com.hotent.platform.model.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
import com.hotent.core.page.PageBean;
/**
 * 对象功能:业务数据模板 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-09-05 14:14:50
 */
@XmlRootElement(name = "bpmDataTemplate")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmDataTemplate extends BaseModel
{
	/**权限类型（显示）=0*/
	public static final  int 	RIGHT_TYPE_SHOW= 0;
	/**权限类型（打印）=1*/
	public static final  int 	RIGHT_TYPE_PRINT= 1;
	/**权限类型（导出）=2*/
	public static final  int 	RIGHT_TYPE_EXPORT= 2;
	/**权限类型（过滤条件）=3*/
	public static final  int 	RIGHT_TYPE_FILTER= 3;
	/**权限类型（管理）=4*/
	public static final  int 	RIGHT_TYPE_MANAGE =4;
	
	/** 参数标识(过滤条件KEY)*/
	public static final  String PARAMS_KEY_FILTERKEY=  "__filterKey__";
	/** 参数标识(流程定义ID)*/
	public static final  String PARAMS_KEY_DEFID=  "__defId__";
	
	public static final  String PARAMS_KEY_ALIAS=  "alias";
	
	/** 参数标识(当前路径)*/
	public static final  String PARAMS_KEY_CTX=  "__ctx";
	public static final  String PARAMS_KEY_ISQUERYDATA=  "__isQueryData";
	
	/** SQL 条件 and */
	public static final  String CONDITION_AND=  " AND ";
	
	/**SQL 条件 or */
	public static final  String CONDITION_OR=  " OR ";
	
	/** 数据来源于自定义表=1*/
	public static final  String SOURCE_CUSTOM_TABLE ="1";
	/** 数据来源于其它表=2 */
	public static final  String SOURCE_OTHER_TABLE ="2";
	
	/** 新增*/
	public static final  String MANAGE_TYPE_ADD="add";
	/** 编辑 */
	public static final  String MANAGE_TYPE_EDIT="edit";
	/** 删除*/
	public static final  String MANAGE_TYPE_DEL="del";
	/** 明细 */
	public static final  String MANAGE_TYPE_DETAIL="detail";
	/** 导入 */
	public static final  String MANAGE_TYPE_IMPORT="import";
	/** 导出 */
	public static final  String MANAGE_TYPE_EXPORT="export";
	/** 打印 */
	public static final  String MANAGE_TYPE_PRINT="print";
	/** 启动流程*/
	public static final  String MANAGE_TYPE_START="start";
	/** 重启流程*/
	public static final  String MANAGE_TYPE_RESTART="reStart";

	/**
	 * 业务数据表
	 */
	public static final  String BUS_TABLE = "BPM_BUS_LINK";
	public static final  String BUS_TABLE_PK= "BUS_PK";
	public static final  String BUS_TABLE_PK_STR= "BUS_PKSTR";
	
	/**
	 * 日期开始结束标记
	 */
	public static final  String  DATE_BEGIN ="begin";
	public static final  String  DATE_END ="end";
	
	
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
	public static final String SORTFIELD="s";
	/**
	 * 排序方向常量
	 */
	public static final String ORDERSEQ="o";
	
	/**
	 * 显示样式 列表
	 */
	public static final  int 	STYLE_LIST	= 0;
	/**
	 * 显示样式 树型
	 */
	public static final  int 	STYLE_TREE	= 1;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	@XmlAttribute
	protected Long  id;
	// 自定义表ID
	@XmlAttribute
	protected Long  tableId;
	// 自定义表单key
	@XmlAttribute
	protected String  formKey="";
	// 名称
	@XmlAttribute
	protected String  name;
	// 别名
	@XmlAttribute
	protected String  alias;
	// 样式：0-列表，1-树形
	@XmlAttribute
	protected Short  style=0;
	// 是否需要分页：0-不分页，1-分页
	@XmlAttribute
	protected Short  needPage=1;
	// 分页大小
	@XmlAttribute
	protected Integer  pageSize=20;
	@XmlAttribute
	protected Long  categoryid;
	// 数据模板别名
	@XmlAttribute
	protected String  templateAlias;
	// 数据模板代码
	@XmlAttribute
	protected String  templateHtml;
	// 显示字段
	@XmlAttribute
	protected String  displayField;
	// 条件字段
	@XmlAttribute
	protected String  conditionField;
	// 排序字段
	@XmlAttribute
	protected String  sortField;
	// 管理字段，按钮
	@XmlAttribute
	protected String  manageField;
	// 过滤条件
	@XmlAttribute
	protected String  filterField;
	//导出字段
	@XmlAttribute
	protected String  exportField;
	//打印字段
	@XmlAttribute
	protected String  printField;
	
	// 变量字段
	@XmlAttribute
	protected String  varField;
	//过滤条件
	@XmlAttribute
	protected Short  filterType=0;
	
	//流程定义Id
	@XmlAttribute
	protected Long  defId;
	@XmlAttribute
	protected Long  categoryId;
	@XmlAttribute
	protected String  tablename;
	//是否查询
	@XmlAttribute
	protected Short  isQuery=1;
	//是否过滤
	@XmlAttribute
	protected Short  isFilter=1;
	
	/**
	 * 数据来源（ 1来自自定义表）
	 */
	@XmlAttribute
	protected String source=SOURCE_CUSTOM_TABLE;
	
	//以下非数据库字段
	// 分页数据
	//private List<HashMap<String, Object>>list1=new ArrayList<HashMap<String, Object>>();
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	// 分页bean。
	private PageBean pageBean;
	//流程定义标题
	private String subject;
	
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
   	public Long getTableId() {
		return tableId;
	}
	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}
   	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getFormKey() {
		return formKey;
	}
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
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
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getTemplateAlias() {
		return templateAlias;
	}
	public void setTemplateAlias(String templateAlias) {
		this.templateAlias = templateAlias;
	}
	public String getTemplateHtml() {
		return templateHtml;
	}
	public void setTemplateHtml(String templateHtml) {
		this.templateHtml = templateHtml;
	}
	public String getDisplayField() {
		return displayField;
	}
	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}
	public String getConditionField() {
		return conditionField;
	}
	public void setConditionField(String conditionField) {
		this.conditionField = conditionField;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getManageField() {
		return manageField;
	}
	public void setManageField(String manageField) {
		this.manageField = manageField;
	}
	public String getFilterField() {
		return filterField;
	}
	public void setFilterField(String filterField) {
		this.filterField = filterField;
	}
	public String getVarField() {
		return varField;
	}
	public void setVarField(String varField) {
		this.varField = varField;
	}
	public String getFormkey() {
		return formKey;
	}
	public void setFormkey(String formkey) {
		this.formKey = formkey;
	}
	public Short getFilterType() {
		return filterType;
	}
	public void setFilterType(Short filterType) {
		this.filterType = filterType;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public Long getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	public PageBean getPageBean() {
		if(pageBean == null)
			pageBean = new PageBean(1, pageSize);
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	public Long getDefId() {
		return defId;
	}
	public void setDefId(Long defId) {
		this.defId = defId;
	}
	public Short getIsQuery() {
		return isQuery;
	}
	public void setIsQuery(Short isQuery) {
		this.isQuery = isQuery;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public Short getIsFilter() {
		return isFilter;
	}
	public void setIsFilter(Short isFilter) {
		this.isFilter = isFilter;
	}
	public String getExportField() {
		return exportField;
	}
	public void setExportField(String exportField) {
		this.exportField = exportField;
	}
	public String getPrintField() {
		return printField;
	}
	public void setPrintField(String printField) {
		this.printField = printField;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmDataTemplate)){
			return false;
		}
		BpmDataTemplate rhs = (BpmDataTemplate) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.tableId, rhs.tableId)
		.append(this.formKey, rhs.formKey)
		.append(this.name, rhs.name)
//		.append(this.alias, rhs.alias)
		.append(this.style, rhs.style)
		.append(this.needPage, rhs.needPage)
		.append(this.pageSize, rhs.pageSize)
		.append(this.templateAlias, rhs.templateAlias)
		.append(this.templateHtml, rhs.templateHtml)
		.append(this.displayField, rhs.displayField)
		.append(this.conditionField, rhs.conditionField)
		.append(this.sortField, rhs.sortField)
		.append(this.manageField, rhs.manageField)
		.append(this.filterField, rhs.filterField)
		.append(this.filterType, rhs.filterType)
		.append(this.varField, rhs.varField)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.tableId) 
		.append(this.formKey) 
		.append(this.name) 
//		.append(this.alias) 
		.append(this.style) 
		.append(this.needPage) 
		.append(this.pageSize) 
		.append(this.templateAlias) 
		.append(this.templateHtml) 
		.append(this.displayField) 
		.append(this.conditionField) 
		.append(this.sortField) 
		.append(this.manageField) 
		.append(this.filterField) 
		.append(this.filterType)
		.append(this.varField) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("tableId", this.tableId) 
		.append("formKey", this.formKey) 
		.append("name", this.name) 
//		.append("alias", this.alias) 
		.append("style", this.style) 
		.append("needPage", this.needPage) 
		.append("pageSize", this.pageSize) 
		.append("templateId", this.templateAlias) 
		.append("templateHtml", this.templateHtml) 
		.append("displayField", this.displayField) 
		.append("conditionField", this.conditionField) 
		.append("sortField", this.sortField) 
		.append("manageField", this.manageField) 
		.append("filterField", this.filterField) 
		.append("filterType",this.filterType)
		.append("varField", this.varField) 
		.toString();
	}
   
  

}