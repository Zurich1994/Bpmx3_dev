package com.hotent.eventgraphrelation.model.eventgraphrelation;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:事件图关联表 Model对象
 */
public class Eventgraphrelation extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *事件ID
	 */
	protected String  eventID;
	/**
	 *所属图流程定义ID
	 */
	protected String  defID;
	/**
	 *节点ID
	 */
	protected String  nodeID;
	/**
	 *本节点图流程定义ID
	 */
	protected String  defbID;
	/**
	 *发生概率
	 */
	protected String  probability;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setEventID(String eventID) 
	{
		this.eventID = eventID;
	}
	/**
	 * 返回 事件ID
	 * @return
	 */
	public String getEventID() 
	{
		return this.eventID;
	}
	public void setDefID(String defID) 
	{
		this.defID = defID;
	}
	/**
	 * 返回 所属图流程定义ID
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
	public void setDefbID(String defbID) 
	{
		this.defbID = defbID;
	}
	/**
	 * 返回 本节点图流程定义ID
	 * @return
	 */
	public String getDefbID() 
	{
		return this.defbID;
	}
	public void setProbability(String probability) 
	{
		this.probability = probability;
	}
	/**
	 * 返回 发生概率
	 * @return
	 */
	public String getProbability() 
	{
		return this.probability;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Eventgraphrelation)) 
		{
			return false;
		}
		Eventgraphrelation rhs = (Eventgraphrelation) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.eventID, rhs.eventID)
		.append(this.defID, rhs.defID)
		.append(this.nodeID, rhs.nodeID)
		.append(this.defbID, rhs.defbID)
		.append(this.probability, rhs.probability)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.eventID) 
		.append(this.defID) 
		.append(this.nodeID) 
		.append(this.defbID) 
		.append(this.probability) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("eventID", this.eventID) 
		.append("defID", this.defID) 
		.append("nodeID", this.nodeID) 
		.append("defbID", this.defbID) 
		.append("probability", this.probability) 
		.toString();
	}
	

}
