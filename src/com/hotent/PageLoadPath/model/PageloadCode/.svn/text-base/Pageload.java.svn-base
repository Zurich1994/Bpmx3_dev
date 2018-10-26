package com.hotent.PageLoadPath.model.PageloadCode;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:页面加载 Model对象
 */
public class Pageload extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *表单名称
	 */
	protected String  formname;
	/**
	 *流程ID
	 */
	protected String  defID;
	/**
	 *节点ID
	 */
	protected String  nodeID;
	/**
	 *页面元素
	 */
	protected String  element;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setFormname(String formname) 
	{
		this.formname = formname;
	}
	/**
	 * 返回 表单名称
	 * @return
	 */
	public String getFormname() 
	{
		return this.formname;
	}
	public void setDefID(String defID) 
	{
		this.defID = defID;
	}
	/**
	 * 返回 流程ID
	 * @return
	 */
	public String getDefID() 
	{
		return this.defID;
	}
	public void setNodeID(String nodeID) 
	{
		this.nodeID = nodeID;
	}
	/**
	 * 返回 节点ID
	 * @return
	 */
	public String getNodeID() 
	{
		return this.nodeID;
	}
	public void setElement(String element) 
	{
		this.element = element;
	}
	/**
	 * 返回 页面元素
	 * @return
	 */
	public String getElement() 
	{
		return this.element;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Pageload)) 
		{
			return false;
		}
		Pageload rhs = (Pageload) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.formname, rhs.formname)
		.append(this.defID, rhs.defID)
		.append(this.nodeID, rhs.nodeID)
		.append(this.element, rhs.element)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.formname) 
		.append(this.defID) 
		.append(this.nodeID) 
		.append(this.element) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("formname", this.formname) 
		.append("defID", this.defID) 
		.append("nodeID", this.nodeID) 
		.append("element", this.element) 
		.toString();
	}

}
