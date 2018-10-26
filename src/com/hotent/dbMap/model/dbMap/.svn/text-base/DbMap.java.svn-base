package com.hotent.dbMap.model.dbMap;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:数据库中map.xml信息表 Model对象
 */
public class DbMap extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *map.xml文件名
	 */
	protected String  map_name;
	/**
	 *绑定表名
	 */
	protected String  table_name;
	/**
	 *xml内容
	 */
	protected String  xml_text;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setMap_name(String map_name) 
	{
		this.map_name = map_name;
	}
	/**
	 * 返回 map.xml文件名
	 * @return
	 */
	public String getMap_name() 
	{
		return this.map_name;
	}
	public void setTable_name(String table_name) 
	{
		this.table_name = table_name;
	}
	/**
	 * 返回 绑定表名
	 * @return
	 */
	public String getTable_name() 
	{
		return this.table_name;
	}
	public void setXml_text(String xml_text) 
	{
		this.xml_text = xml_text;
	}
	/**
	 * 返回 xml内容
	 * @return
	 */
	public String getXml_text() 
	{
		return this.xml_text;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DbMap)) 
		{
			return false;
		}
		DbMap rhs = (DbMap) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.map_name, rhs.map_name)
		.append(this.table_name, rhs.table_name)
		.append(this.xml_text, rhs.xml_text)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.map_name) 
		.append(this.table_name) 
		.append(this.xml_text) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("map_name", this.map_name) 
		.append("table_name", this.table_name) 
		.append("xml_text", this.xml_text) 
		.toString();
	}

}
