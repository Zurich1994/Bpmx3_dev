package com.hotent.platform.model.bus;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:查询过滤 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-12-17 11:34:40
 */
public class BusQueryFilter extends BaseModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  static final short SHARE =1;
	public  static final short NO_SHARE =0;
	// 主键
	protected Long  id;
	// 规则ID
	protected Long  ruleId;
	// 表名
	protected String  tableName;
	// 过滤名称
	protected String  filterName;
	// 过滤描述
	protected String  filterDesc;
	// 过滤条件Key
	protected String  filterKey;
	// 查询参数
	protected String  queryParameter;
	// 排序参数
	protected String  sortParameter;
	// 是否共享
	protected Short  isShare=0;
	// 创建时间
	protected java.util.Date  createtime;
	// 创建人ID
	protected Long  createBy;
	
	
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
	public void setRuleId(Long ruleId) 
	{
		this.ruleId = ruleId;
	}
	/**
	 * 返回 规则ID
	 * @return
	 */
	public Long getRuleId() 
	{
		return this.ruleId;
	}
	public void setTableName(String tableName) 
	{
		this.tableName = tableName;
	}
	/**
	 * 返回 表名
	 * @return
	 */
	public String getTableName() 
	{
		return this.tableName;
	}
	public void setFilterName(String filterName) 
	{
		this.filterName = filterName;
	}
	/**
	 * 返回 过滤名称
	 * @return
	 */
	public String getFilterName() 
	{
		return this.filterName;
	}
	public void setFilterDesc(String filterDesc) 
	{
		this.filterDesc = filterDesc;
	}
	/**
	 * 返回 过滤描述
	 * @return
	 */
	public String getFilterDesc() 
	{
		return this.filterDesc;
	}
	public void setFilterKey(String filterKey) 
	{
		this.filterKey = filterKey;
	}
	/**
	 * 返回 过滤条件Key
	 * @return
	 */
	public String getFilterKey() 
	{
		return this.filterKey;
	}
	public void setQueryParameter(String queryParameter) 
	{
		this.queryParameter = queryParameter;
	}
	/**
	 * 返回 查询参数
	 * @return
	 */
	public String getQueryParameter() 
	{
		return this.queryParameter;
	}
	public void setSortParameter(String sortParameter) 
	{
		this.sortParameter = sortParameter;
	}
	/**
	 * 返回 排序参数
	 * @return
	 */
	public String getSortParameter() 
	{
		return this.sortParameter;
	}
	public void setIsShare(Short isShare) 
	{
		this.isShare = isShare;
	}
	/**
	 * 返回 是否共享
	 * @return
	 */
	public Short getIsShare() 
	{
		return this.isShare;
	}
	public void setCreatetime(java.util.Date createtime) 
	{
		this.createtime = createtime;
	}
	
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreatetime() 
	{
		return this.createtime;
	}
	public void setCreateBy(Long createBy) 
	{
		this.createBy = createBy;
	}
	/**
	 * 返回 创建人ID
	 * @return
	 */
	public Long getCreateBy() 
	{
		return this.createBy;
	}

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BusQueryFilter)) 
		{
			return false;
		}
		BusQueryFilter rhs = (BusQueryFilter) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.ruleId, rhs.ruleId)
		.append(this.tableName, rhs.tableName)
		.append(this.filterName, rhs.filterName)
		.append(this.filterDesc, rhs.filterDesc)
		.append(this.filterKey, rhs.filterKey)
		.append(this.queryParameter, rhs.queryParameter)
		.append(this.sortParameter, rhs.sortParameter)
		.append(this.isShare, rhs.isShare)
		.append(this.createtime, rhs.createtime)
		.append(this.createBy, rhs.createBy)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.ruleId) 
		.append(this.tableName) 
		.append(this.filterName) 
		.append(this.filterDesc) 
		.append(this.filterKey) 
		.append(this.queryParameter) 
		.append(this.sortParameter) 
		.append(this.isShare) 
		.append(this.createtime) 
		.append(this.createBy) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("ruleId", this.ruleId) 
		.append("tableName", this.tableName) 
		.append("filterName", this.filterName) 
		.append("filterDesc", this.filterDesc) 
		.append("filterKey", this.filterKey) 
		.append("queryParameter", this.queryParameter) 
		.append("sortParameter", this.sortParameter) 
		.append("isShare", this.isShare) 
		.append("createtime", this.createtime) 
		.append("createBy", this.createBy) 
		.toString();
	}
   
  

}