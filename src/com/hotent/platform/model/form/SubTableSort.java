package com.hotent.platform.model.form;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:bpm_subtable_sort Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2015-03-12 13:56:01
 */
public class SubTableSort extends BaseModel{
	// 主键
	protected Long  id;
	// 流程定义key
	protected String  actdefkey;
	// 子表名称
	protected String  tablename;
	// 字段名，json数据
	protected String  fieldsort;

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
	public void setActdefkey(String actdefkey){
		this.actdefkey = actdefkey;
	}
	/**
	 * 返回 流程定义key
	 * @return
	 */
	public String getActdefkey() {
		return this.actdefkey;
	}
	public void setTablename(String tablename){
		this.tablename = tablename;
	}
	/**
	 * 返回 子表名称
	 * @return
	 */
	public String getTablename() {
		return this.tablename;
	}
	public void setFieldsort(String fieldsort){
		this.fieldsort = fieldsort;
	}
	/**
	 * 返回 字段名，json数据
	 * @return
	 */
	public String getFieldsort() {
		return this.fieldsort;
	}
	

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SubTableSort)) 
		{
			return false;
		}
		SubTableSort rhs = (SubTableSort) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.actdefkey, rhs.actdefkey)
		.append(this.tablename, rhs.tablename)
		.append(this.fieldsort, rhs.fieldsort)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.actdefkey) 
		.append(this.tablename) 
		.append(this.fieldsort) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("actdefkey", this.actdefkey) 
		.append("tablename", this.tablename) 
		.append("fieldsort", this.fieldsort) 
		.toString();
	}
   
  

}