package com.hotent.extension.model.bpm;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:BPM_NODE_DATA Model对象
 * 开发公司:哈尔滨工程大学
 * 开发人员:ray
 * 
 */
public class BpmNodeData extends BaseModel
{
	// ID
	protected Long  id;
	// SET_ID
	protected Long  setId;
	// DIALOG_ALIAS
	protected String  dialogAlias;
	// DIALOG_NAME
	protected String  dialogName;
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 ID
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setSetId(Long setId) 
	{
		this.setId = setId;
	}
	/**
	 * 返回 SET_ID
	 * @return
	 */
	public Long getSetId() 
	{
		return this.setId;
	}
	public void setDialogAlias(String dialogAlias) 
	{
		this.dialogAlias = dialogAlias;
	}
	/**
	 * 返回 DIALOG_ALIAS
	 * @return
	 */
	public String getDialogAlias() 
	{
		return this.dialogAlias;
	}
	public void setDialogName(String dialogName) 
	{
		this.dialogName = dialogName;
	}
	/**
	 * 返回 DIALOG_NAME
	 * @return
	 */
	public String getDialogName() 
	{
		return this.dialogName;
	}

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmNodeData)) 
		{
			return false;
		}
		BpmNodeData rhs = (BpmNodeData) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.setId, rhs.setId)
		.append(this.dialogAlias, rhs.dialogAlias)
		.append(this.dialogName, rhs.dialogName)
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
		.append(this.dialogAlias) 
		.append(this.dialogName) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("setId", this.setId) 
		.append("dialogAlias", this.dialogAlias) 
		.append("dialogName", this.dialogName) 
		.toString();
	}
   
  

}