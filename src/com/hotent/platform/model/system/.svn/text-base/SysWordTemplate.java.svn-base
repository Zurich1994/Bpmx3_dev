package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:word套打模板 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:guojh
 * 创建时间:2015-03-23 10:48:07
 */
public class SysWordTemplate extends BaseModel{
	// 主键
	protected Long  id;
	// 报表名称
	protected String  name;
	// 报表别名
	protected String  alias;
	// word模板附件ID
	protected Long  fileId;
	// 类型	protected String  type = FORM_TYPE;
	// 表ID
	protected Long  tableId;
	// 表名
	protected String tableName;
	// 数据源别名
	protected String  dsAlias;
	// Sql语句
	protected String  sql;
	
	/**
	 * 自定义表单类型
	 */
	public final static String FORM_TYPE = "0";
	/**
	 * SQL类型
	 */
	public final static String SQL_TYPE = "1";
	
	/**
	 * 主键占位符
	 */
	public final static String PK_KEY_ = "__PK__";

	public void setId(Long id){
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 返回 报表名称
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	public void setAlias(String alias){
		this.alias = alias;
	}
	/**
	 * 返回 报表别名
	 * @return
	 */
	public String getAlias() {
		return this.alias;
	}
	public void setFileId(Long fileId){
		this.fileId = fileId;
	}
	/**
	 * 返回 模板
	 * @return
	 */
	public Long getFileId() {
		return this.fileId;
	}
	public void setType(String type){
		this.type = type;
	}
	/**
	 * 返回 类型
	 * @return
	 */
	public String getType() {
		return this.type;
	}
	public void setTableId(Long tableId){
		this.tableId = tableId;
	}
	/**
	 * 返回 表ID
	 * @return
	 */
	public Long getTableId() {
		return this.tableId;
	}
	public void setDsAlias(String dsAlias){
		this.dsAlias = dsAlias;
	}
	/**
	 * 返回 数据源别名
	 * @return
	 */
	public String getDsAlias() {
		return this.dsAlias;
	}
	public void setSql(String sql){
		this.sql = sql;
	}
	/**
	 * 返回 Sql语句
	 * @return
	 */
	public String getSql() {
		return this.sql;
	}
	

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysWordTemplate)) 
		{
			return false;
		}
		SysWordTemplate rhs = (SysWordTemplate) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.alias, rhs.alias)
		.append(this.fileId, rhs.fileId)
		.append(this.createtime, rhs.createtime)
		.append(this.type, rhs.type)
		.append(this.tableId, rhs.tableId)
		.append(this.dsAlias, rhs.dsAlias)
		.append(this.sql, rhs.sql)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.name) 
		.append(this.alias) 
		.append(this.fileId) 
		.append(this.createtime) 
		.append(this.type) 
		.append(this.tableId) 
		.append(this.dsAlias) 
		.append(this.sql) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("name", this.name) 
		.append("alias", this.alias) 
		.append("fileId", this.fileId) 
		.append("createtime", this.createtime) 
		.append("type", this.type) 
		.append("tableId", this.tableId) 
		.append("dsAlias", this.dsAlias) 
		.append("sql", this.sql) 
		.toString();
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}