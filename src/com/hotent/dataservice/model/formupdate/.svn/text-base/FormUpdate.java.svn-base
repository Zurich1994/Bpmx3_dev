package com.hotent.dataservice.model.formupdate;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:原子操作-更新 Model对象
 */
public class FormUpdate extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *名称
	 */
	protected String  name;
	/**
	 *别名
	 */
	protected String  alias;
	/**
	 *查询对象名称
	 */
	protected String  objName;
	/**
	 *是否分页
	 */
	protected Long  needpage;
	/**
	 *条件字段
	 */
	protected String  conditionfield;
	/**
	 *更新字段
	 */
	protected String  updatefield;
	/**
	 *数据源名称
	 */
	protected String  dsname;
	/**
	 *数据源别名
	 */
	protected String  dsalias;
	/**
	 *分页条数
	 */
	protected Long  pagesize;
	/**
	 *是否为表
	 */
	protected Long  isTable;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setAlias(String alias) 
	{
		this.alias = alias;
	}
	/**
	 * 返回 别名
	 * @return
	 */
	public String getAlias() 
	{
		return this.alias;
	}
	public void setObjName(String objName) 
	{
		this.objName = objName;
	}
	/**
	 * 返回 查询对象名称
	 * @return
	 */
	public String getObjName() 
	{
		return this.objName;
	}
	public void setNeedpage(Long needpage) 
	{
		this.needpage = needpage;
	}
	/**
	 * 返回 是否分页
	 * @return
	 */
	public Long getNeedpage() 
	{
		return this.needpage;
	}
	public void setConditionfield(String conditionfield) 
	{
		this.conditionfield = conditionfield;
	}
	/**
	 * 返回 条件字段
	 * @return
	 */
	public String getConditionfield() 
	{
		return this.conditionfield;
	}
	public void setUpdatefield(String updatefield) 
	{
		this.updatefield = updatefield;
	}
	/**
	 * 返回 更新字段
	 * @return
	 */
	public String getUpdatefield() 
	{
		return this.updatefield;
	}
	public void setDsname(String dsname) 
	{
		this.dsname = dsname;
	}
	/**
	 * 返回 数据源名称
	 * @return
	 */
	public String getDsname() 
	{
		return this.dsname;
	}
	public void setDsalias(String dsalias) 
	{
		this.dsalias = dsalias;
	}
	/**
	 * 返回 数据源别名
	 * @return
	 */
	public String getDsalias() 
	{
		return this.dsalias;
	}
	public void setPagesize(Long pagesize) 
	{
		this.pagesize = pagesize;
	}
	/**
	 * 返回 分页条数
	 * @return
	 */
	public Long getPagesize() 
	{
		return this.pagesize;
	}
	public void setIsTable(Long isTable) 
	{
		this.isTable = isTable;
	}
	/**
	 * 返回 是否为表
	 * @return
	 */
	public Long getIsTable() 
	{
		return this.isTable;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof FormUpdate)) 
		{
			return false;
		}
		FormUpdate rhs = (FormUpdate) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.alias, rhs.alias)
		.append(this.objName, rhs.objName)
		.append(this.needpage, rhs.needpage)
		.append(this.conditionfield, rhs.conditionfield)
		.append(this.updatefield, rhs.updatefield)
		.append(this.dsname, rhs.dsname)
		.append(this.dsalias, rhs.dsalias)
		.append(this.pagesize, rhs.pagesize)
		.append(this.isTable, rhs.isTable)
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
		.append(this.objName) 
		.append(this.needpage) 
		.append(this.conditionfield) 
		.append(this.updatefield) 
		.append(this.dsname) 
		.append(this.dsalias) 
		.append(this.pagesize) 
		.append(this.isTable) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("name", this.name) 
		.append("alias", this.alias) 
		.append("objName", this.objName) 
		.append("needpage", this.needpage) 
		.append("conditionfield", this.conditionfield) 
		.append("updatefield", this.updatefield) 
		.append("dsname", this.dsname) 
		.append("dsalias", this.dsalias) 
		.append("pagesize", this.pagesize) 
		.append("isTable", this.isTable) 
		.toString();
	}

}
