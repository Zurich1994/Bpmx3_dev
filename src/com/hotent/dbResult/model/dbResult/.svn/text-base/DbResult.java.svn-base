package com.hotent.dbResult.model.dbResult;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:数据库返回结果集 Model对象
 */
public class DbResult extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *表名
	 */
	protected String  table_name;
	/**
	 *类名
	 */
	protected String  class_name;
	/**
	 *属性名_列名
	 */
	protected String  pro_col;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTable_name(String table_name) 
	{
		this.table_name = table_name;
	}
	/**
	 * 返回 表名
	 * @return
	 */
	public String getTable_name() 
	{
		return this.table_name;
	}
	public void setClass_name(String class_name) 
	{
		this.class_name = class_name;
	}
	/**
	 * 返回 类名
	 * @return
	 */
	public String getClass_name() 
	{
		return this.class_name;
	}
	public void setPro_col(String pro_col) 
	{
		this.pro_col = pro_col;
	}
	/**
	 * 返回 属性名_列名
	 * @return
	 */
	public String getPro_col() 
	{
		return this.pro_col;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DbResult)) 
		{
			return false;
		}
		DbResult rhs = (DbResult) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.table_name, rhs.table_name)
		.append(this.class_name, rhs.class_name)
		.append(this.pro_col, rhs.pro_col)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.table_name) 
		.append(this.class_name) 
		.append(this.pro_col) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("table_name", this.table_name) 
		.append("class_name", this.class_name) 
		.append("pro_col", this.pro_col) 
		.toString();
	}

}
