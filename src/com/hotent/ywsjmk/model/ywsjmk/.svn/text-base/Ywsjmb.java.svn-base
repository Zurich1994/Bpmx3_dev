package com.hotent.ywsjmk.model.ywsjmk;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:业务数据模板 Model对象
 */
public class Ywsjmb extends BaseModel
{
	//主键
	protected Long id;
	protected String name;
	
	protected Long tableid;
	/**
	 *表单标题
	 */
	protected String  subject;
	/**
	 *表单别名
	 */
	protected String  formkey;
	/**
	 *模板类型
	 */
	protected String  templatealias;
	
	/**
	 *对应表
	 */
	protected String  tablename;
	protected String  typename;
	/**
	 *表单分类
	 */
	protected String  categoryid;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	
	
	
	public Long getTableid() {
		return tableid;
	}
	public void setTableid(Long tableid) {
		this.tableid = tableid;
	}
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}
	/**
	 * 返回 表单标题
	 * @return
	 */
	public String getSubject() 
	{
		return this.subject;
	}
	public void setFormkey(String formkey) 
	{
		this.formkey = formkey;
	}
	
	/**
	 * 返回 模板类型
	 * @return
	 */
	public String getTemplatealias() {
		return this.templatealias;
	}
	public void setTemplatealias(String templatealias) {
		this.templatealias = templatealias;
	}
	/**
	 * 返回 表单别名
	 * @return
	 */
	public String getFormkey() 
	{
		return this.formkey;
	}
	public void setTablename(String tablename) 
	{
		this.tablename = tablename;
	}
	/**
	 * 返回 对应表
	 * @return
	 */
	public String getTablename() 
	{
		return this.tablename;
	}
	public void setCategoryid(String categoryid) 
	{
		this.categoryid = categoryid;
	}
	/**
	 * 返回 表单分类
	 * @return
	 */
	public String getCategoryid() 
	{
		return this.categoryid;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Ywsjmb)) 
		{
			return false;
		}
		Ywsjmb rhs = (Ywsjmb) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.tableid, rhs.tableid)
		.append(this.subject, rhs.subject)
		.append(this.formkey, rhs.formkey)
		.append(this.tablename, rhs.tablename)
		.append(this.categoryid, rhs.categoryid)
		.append(this.typename,rhs.typename)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.tableid)
		.append(this.subject) 
		.append(this.formkey) 
		.append(this.tablename) 
		.append(this.categoryid) 
		.append(this.typename)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("tableid",this.tableid)
		.append("subject", this.subject) 
		.append("formkey", this.formkey) 
		.append("tablename", this.tablename) 
		.append("typename",this.typename)
		.append("categoryid", this.categoryid) 
		.toString();
	}

}