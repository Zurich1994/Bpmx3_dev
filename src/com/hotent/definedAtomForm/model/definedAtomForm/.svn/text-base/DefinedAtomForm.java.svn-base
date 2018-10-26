package com.hotent.definedAtomForm.model.definedAtomForm;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:defined_atom_form Model对象
 */
public class DefinedAtomForm extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *setId
	 */
	protected Long  setId;
	/**
	 *defId
	 */
	protected Long  defId;
	/**
	 *nodeId
	 */
	protected Long  nodeId;
	/**
	 *formKey
	 */
	protected Long  formKey;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setSetId(Long setId) 
	{
		this.setId = setId;
	}
	/**
	 * 返回 setId
	 * @return
	 */
	public Long getSetId() 
	{
		return this.setId;
	}
	public void setDefId(Long defId) 
	{
		this.defId = defId;
	}
	/**
	 * 返回 defId
	 * @return
	 */
	public Long getDefId() 
	{
		return this.defId;
	}
	public void setNodeId(Long nodeId) 
	{
		this.nodeId = nodeId;
	}
	/**
	 * 返回 nodeId
	 * @return
	 */
	public Long getNodeId() 
	{
		return this.nodeId;
	}
	public void setFormKey(Long formKey) 
	{
		this.formKey = formKey;
	}
	/**
	 * 返回 formKey
	 * @return
	 */
	public Long getFormKey() 
	{
		return this.formKey;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DefinedAtomForm)) 
		{
			return false;
		}
		DefinedAtomForm rhs = (DefinedAtomForm) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.setId, rhs.setId)
		.append(this.defId, rhs.defId)
		.append(this.nodeId, rhs.nodeId)
		.append(this.formKey, rhs.formKey)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.setId) 
		.append(this.defId) 
		.append(this.nodeId) 
		.append(this.formKey) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("setId", this.setId) 
		.append("defId", this.defId) 
		.append("nodeId", this.nodeId) 
		.append("formKey", this.formKey) 
		.toString();
	}

}
