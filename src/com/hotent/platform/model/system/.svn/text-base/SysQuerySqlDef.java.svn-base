package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.alibaba.fastjson.annotation.JSONField;
import com.hotent.core.model.BaseModel;


/**
 * 对象功能:自定义SQL定义 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2015-06-14 10:54:16
 */
public class SysQuerySqlDef extends BaseModel{
	// 主键
	protected Long id;
	// 名称
	protected String name;
	// sql语句
//	@JSONField(serialize=false)
	protected String sql;
	// 数据源名称
	protected String dsname;
	// 按钮定义
	protected String buttonDef;
	// 别名
	protected String alias;
	// 分类
	protected String categoryid;
	//字段
	protected List<SysQueryMetaField> metaFields=new ArrayList<SysQueryMetaField>();

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
	 * 返回 名称
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	public void setSql(String sql){
		this.sql = sql;
	}
	/**
	 * 返回 sql语句
	 * @return
	 */
	public String getSql() {
		return this.sql;
	}
	public void setDsname(String dsname){
		this.dsname = dsname;
	}
	/**
	 * 返回 数据源名称
	 * @return
	 */
	public String getDsname() {
		return this.dsname;
	}
	public void setButtonDef(String buttonDef){
		this.buttonDef = buttonDef;
	}
	/**
	 * 返回 按钮定义
	 * @return
	 */
	public String getButtonDef() {
		return this.buttonDef;
	}
	public void setAlias(String alias){
		this.alias = alias;
	}
	/**
	 * 返回 别名
	 * @return
	 */
	public String getAlias() {
		return this.alias;
	}
	public void setCategoryid(String categoryid){
		this.categoryid = categoryid;
	}
	/**
	 * 返回 分类
	 * @return
	 */
	public String getCategoryid() {
		return this.categoryid;
	}
	

   	public List<SysQueryMetaField> getMetaFields() {
		return metaFields;
	}
	public void setMetaFields(List<SysQueryMetaField> metaFields) {
		this.metaFields = metaFields;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysQuerySqlDef)) 
		{
			return false;
		}
		SysQuerySqlDef rhs = (SysQuerySqlDef) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.sql, rhs.sql)
		.append(this.dsname, rhs.dsname)
		.append(this.buttonDef, rhs.buttonDef)
		.append(this.alias, rhs.alias)
		.append(this.categoryid, rhs.categoryid)
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
		.append(this.sql) 
		.append(this.dsname) 
		.append(this.buttonDef) 
		.append(this.alias) 
		.append(this.categoryid) 
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
		.append("sql", this.sql) 
		.append("dsname", this.dsname) 
		.append("buttonDef", this.buttonDef) 
		.append("alias", this.alias) 
		.append("categoryid", this.categoryid) 
		.toString();
	}
   
  

}