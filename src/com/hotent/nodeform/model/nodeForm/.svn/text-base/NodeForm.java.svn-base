package com.hotent.nodeform.model.nodeForm;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:节点表单对应表 Model对象
 */
public class NodeForm extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *查询条件1
	 */
	protected String  condition1;
	/**
	 *查询条件2
	 */
	protected String  condition2;
	/**
	 *查询条件3
	 */
	protected String  condition3;
	/**
	 *表单id
	 */
	protected Long  formID;
	/**
	 *表单名称
	 */
	protected String  formName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setCondition1(String condition1) 
	{
		this.condition1 = condition1;
	}
	/**
	 * 返回 查询条件1
	 * @return
	 */
	public String getCondition1() 
	{
		return this.condition1;
	}
	public void setCondition2(String condition2) 
	{
		this.condition2 = condition2;
	}
	/**
	 * 返回 查询条件2
	 * @return
	 */
	public String getCondition2() 
	{
		return this.condition2;
	}
	public void setCondition3(String condition3) 
	{
		this.condition3 = condition3;
	}
	/**
	 * 返回 查询条件3
	 * @return
	 */
	public String getCondition3() 
	{
		return this.condition3;
	}
	public void setFormID(Long formID) 
	{
		this.formID = formID;
	}
	/**
	 * 返回 表单id
	 * @return
	 */
	public Long getFormID() 
	{
		return this.formID;
	}
	public void setFormName(String formName) 
	{
		this.formName = formName;
	}
	/**
	 * 返回 表单名称
	 * @return
	 */
	public String getFormName() 
	{
		return this.formName;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof NodeForm)) 
		{
			return false;
		}
		NodeForm rhs = (NodeForm) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.condition1, rhs.condition1)
		.append(this.condition2, rhs.condition2)
		.append(this.condition3, rhs.condition3)
		.append(this.formID, rhs.formID)
		.append(this.formName, rhs.formName)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.condition1) 
		.append(this.condition2) 
		.append(this.condition3) 
		.append(this.formID) 
		.append(this.formName) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("condition1", this.condition1) 
		.append("condition2", this.condition2) 
		.append("condition3", this.condition3) 
		.append("formID", this.formID) 
		.append("formName", this.formName) 
		.toString();
	}

}
