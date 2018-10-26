package com.hotent.formQueryDefinition.model.com;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:节点查询关联表 Model对象
 */
public class Fqrelation extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *自定义查询ID
	 */
	protected String  fqID;
	/**
	 *流程ID
	 */
	protected String  defID;
	/**
	 *节点ID
	 */
	protected String  nodeID;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setFqID(String fqID) 
	{
		this.fqID = fqID;
	}
	/**
	 * 返回 自定义查询ID
	 * @return
	 */
	public String getFqID() 
	{
		return this.fqID;
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
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Fqrelation)) 
		{
			return false;
		}
		Fqrelation rhs = (Fqrelation) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.fqID, rhs.fqID)
		.append(this.defID, rhs.defID)
		.append(this.nodeID, rhs.nodeID)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.fqID) 
		.append(this.defID) 
		.append(this.nodeID) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("fqID", this.fqID) 
		.append("defID", this.defID) 
		.append("nodeID", this.nodeID) 
		.toString();
	}

}
