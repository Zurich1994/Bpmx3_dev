package com.hotent.tableParcel.model.tableParcel;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import com.hotent.core.table.ColumnModel;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:table_parcel Model对象
 */
public class TableParcel extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *表名
	 */
	protected String  table_name;
	/**
	 *数据包名
	 */
	protected String  parcel_name;
	/**
	 *数据包注释
	 */
	protected String  parcel_alias;
	/**
	 *数据包值
	 */
	protected String  parcel_value;
	
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
	public void setParcel_name(String parcel_name) 
	{
		this.parcel_name = parcel_name;
	}
	/**
	 * 返回 数据包名
	 * @return
	 */
	public String getParcel_name() 
	{
		return this.parcel_name;
	}
	public void setParcel_alias(String parcel_alias) 
	{
		this.parcel_alias = parcel_alias;
	}
	/**
	 * 返回 数据包注释
	 * @return
	 */
	public String getParcel_alias() 
	{
		return this.parcel_alias;
	}
	public void setParcel_value(String parcel_value) 
	{
		this.parcel_value = parcel_value;
	}
	/**
	 * 返回 数据包值
	 * @return
	 */
	public String getParcel_value() 
	{
		return this.parcel_value;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof TableParcel)) 
		{
			return false;
		}
		TableParcel rhs = (TableParcel) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.table_name, rhs.table_name)
		.append(this.parcel_name, rhs.parcel_name)
		.append(this.parcel_alias, rhs.parcel_alias)
		.append(this.parcel_value, rhs.parcel_value)
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
		.append(this.parcel_name) 
		.append(this.parcel_alias) 
		.append(this.parcel_value) 
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
		.append("parcel_name", this.parcel_name) 
		.append("parcel_alias", this.parcel_alias) 
		.append("parcel_value", this.parcel_value) 
		.toString();
	}
	private List<TableParcel> tableparcel=new ArrayList<TableParcel>();
	public void addParcelModel(TableParcel tableparcel){
		this.tableparcel.add(tableparcel);
	}
	public List<TableParcel> getParcelList() {
		return tableparcel;
	}
	public void setParcelList(List<TableParcel> tableparcel) {
		this.tableparcel=tableparcel;
	}
}
