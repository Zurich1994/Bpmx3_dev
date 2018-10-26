package com.hotent.platform.model.bus;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
import com.hotent.core.web.query.entity.Filter;

/**
 * <pre>
 * 对象功能:高级查询规则 Model对象
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:zxh 
 * 创建时间:2013-12-09 14:17:41
 * </pre>
 */
public class BusQueryRule extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	protected Long id;
	// 表名
	protected String tableName;
	// 是否需要分页：0-不分页，1-分页
	protected Short needPage = 1;
	// 分页大小
	protected Integer pageSize = 20;
	// 初始是否进行查询：0-是，1-否
	protected Short isQuery = 0;
	// 没有过滤条件是否默认条件
	protected Short isFilter = 0;
	// 显示字段
	protected String displayField;
	// 过滤器字段
	protected String filterField;
	// 排序字段
	protected String sortField;
	// 导出字段
	protected String exportField;
	// 打印字段
	protected String printField;
	// 创建时间
	protected java.util.Date createtime;
	// 创建人ID
	protected Long createBy;
	// 更新时间
	protected java.util.Date updatetime;
	// 更新人ID
	protected Long updateBy;

	// =====非数据库
	// 过滤Key
	protected String filterKey;
	// 过滤列表
	protected List<Filter> filterList;
	// 权限列表
	protected Map<String, Boolean> permission;
	// url地址
	protected String url;

	protected Long filterFlag;

	public String getFilterKey() {
		return filterKey;
	}

	public void setFilterKey(String filterKey) {
		this.filterKey = filterKey;
	}

	public List<Filter> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<Filter> filterList) {
		this.filterList = filterList;
	}

	public Map<String, Boolean> getPermission() {
		return permission;
	}

	public void setPermission(Map<String, Boolean> permission) {
		this.permission = permission;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getFilterFlag() {
		return filterFlag;
	}

	public void setFilterFlag(Long filterFlag) {
		this.filterFlag = filterFlag;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 主键
	 * 
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 返回 表名
	 * 
	 * @return
	 */
	public String getTableName() {
		return this.tableName;
	}

	/**
	 * 是否需要分页：0-不分页，1-分页
	 * 
	 * @return
	 */
	public Short getNeedPage() {
		return needPage;
	}

	public void setNeedPage(Short needPage) {
		this.needPage = needPage;
	}

	/**
	 * 分页大小
	 * 
	 * @return
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 初始是否查询
	 * 
	 * @return
	 */
	public Short getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(Short isQuery) {
		this.isQuery = isQuery;
	}

	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}

	/**
	 * 返回 显示字段
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
	 * 返回 过滤器字段
	 * 
	 * @return
	 */
	public String getFilterField() {
		return this.filterField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	/**
	 * 返回 排序字段
	 * 
	 * @return
	 */
	public String getSortField() {
		return this.sortField;
	}

	public void setExportField(String exportField) {
		this.exportField = exportField;
	}

	/**
	 * 返回 导出字段
	 * 
	 * @return
	 */
	public String getExportField() {
		return this.exportField;
	}

	public void setPrintField(String printField) {
		this.printField = printField;
	}

	/**
	 * 返回 打印字段
	 * 
	 * @return
	 */
	public String getPrintField() {
		return this.printField;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * 返回 创建时间
	 * 
	 * @return
	 */
	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	/**
	 * 返回 创建人ID
	 * 
	 * @return
	 */
	public Long getCreateBy() {
		return this.createBy;
	}

	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * 返回 更新时间
	 * 
	 * @return
	 */
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 返回 更新人ID
	 * 
	 * @return
	 */
	public Long getUpdateBy() {
		return this.updateBy;
	}

	public Short getIsFilter() {
		return isFilter;
	}

	public void setIsFilter(Short isFilter) {
		this.isFilter = isFilter;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BusQueryRule)) {
			return false;
		}
		BusQueryRule rhs = (BusQueryRule) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.tableName, rhs.tableName)
				.append(this.needPage, rhs.needPage)
				.append(this.pageSize, rhs.pageSize)
				.append(this.isQuery, rhs.isQuery)
				.append(this.isFilter, rhs.isFilter)
				.append(this.displayField, rhs.displayField)
				.append(this.filterField, rhs.filterField)
				.append(this.sortField, rhs.sortField)
				.append(this.exportField, rhs.exportField)
				.append(this.printField, rhs.printField)
				.append(this.createtime, rhs.createtime)
				.append(this.createBy, rhs.createBy)
				.append(this.updatetime, rhs.updatetime)
				.append(this.updateBy, rhs.updateBy).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id)
				.append(this.tableName).append(this.needPage)
				.append(this.pageSize).append(this.isQuery)
				.append(this.isFilter).append(this.displayField)
				.append(this.filterField).append(this.sortField)
				.append(this.exportField).append(this.printField)
				.append(this.createtime).append(this.createBy)
				.append(this.updatetime).append(this.updateBy).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("tableName", this.tableName)
				.append("needPage", this.needPage)
				.append("pageSize", this.pageSize)
				.append("isQuery", this.isQuery)
				.append("isFilter", this.isFilter)
				.append("displayField", this.displayField)
				.append("filterField", this.filterField)
				.append("sortField", this.sortField)
				.append("exportField", this.exportField)
				.append("printField", this.printField)
				.append("createtime", this.createtime)
				.append("createBy", this.createBy)
				.append("updatetime", this.updatetime)
				.append("updateBy", this.updateBy).toString();
	}

}