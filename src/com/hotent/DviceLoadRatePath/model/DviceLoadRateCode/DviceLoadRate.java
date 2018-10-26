package com.hotent.DviceLoadRatePath.model.DviceLoadRateCode;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:设备负载值及利用率表 Model对象
 */
public class DviceLoadRate extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *节点设备id
	 */
	protected Long  node_dev_id;
	/**
	 *时间
	 */
	protected Long  active_start;
	/**
	 *负载类型
	 */
	protected String  load_type;
	/**
	 *负载值
	 */
	protected Long  load_value;
	/**
	 *资源类型
	 */
	protected String  load_info;
	/**
	 *利用率
	 */
	protected Long  load_use_rate;
	/**
	 *a
	 */
	protected String  a;
	/**
	 *b
	 */
	protected String  b;
	/**
	 *c
	 */
	protected String  c;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNode_dev_id(Long node_dev_id) 
	{
		this.node_dev_id = node_dev_id;
	}
	/**
	 * 返回 节点设备id
	 * @return
	 */
	public Long getNode_dev_id() 
	{
		return this.node_dev_id;
	}
	public void setActive_start(Long active_start) 
	{
		this.active_start = active_start;
	}
	/**
	 * 返回 时间
	 * @return
	 */
	public Long getActive_start() 
	{
		return this.active_start;
	}
	public void setLoad_type(String load_type) 
	{
		this.load_type = load_type;
	}
	/**
	 * 返回 负载类型
	 * @return
	 */
	public String getLoad_type() 
	{
		return this.load_type;
	}
	public void setLoad_value(Long load_value) 
	{
		this.load_value = load_value;
	}
	/**
	 * 返回 负载值
	 * @return
	 */
	public Long getLoad_value() 
	{
		return this.load_value;
	}
	public void setLoad_info(String load_info) 
	{
		this.load_info = load_info;
	}
	/**
	 * 返回 资源类型
	 * @return
	 */
	public String getLoad_info() 
	{
		return this.load_info;
	}
	public void setLoad_use_rate(Long load_use_rate) 
	{
		this.load_use_rate = load_use_rate;
	}
	/**
	 * 返回 利用率
	 * @return
	 */
	public Long getLoad_use_rate() 
	{
		return this.load_use_rate;
	}
	public void setA(String a) 
	{
		this.a = a;
	}
	/**
	 * 返回 a
	 * @return
	 */
	public String getA() 
	{
		return this.a;
	}
	public void setB(String b) 
	{
		this.b = b;
	}
	/**
	 * 返回 b
	 * @return
	 */
	public String getB() 
	{
		return this.b;
	}
	public void setC(String c) 
	{
		this.c = c;
	}
	/**
	 * 返回 c
	 * @return
	 */
	public String getC() 
	{
		return this.c;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DviceLoadRate)) 
		{
			return false;
		}
		DviceLoadRate rhs = (DviceLoadRate) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.node_dev_id, rhs.node_dev_id)
		.append(this.active_start, rhs.active_start)
		.append(this.load_type, rhs.load_type)
		.append(this.load_value, rhs.load_value)
		.append(this.load_info, rhs.load_info)
		.append(this.load_use_rate, rhs.load_use_rate)
		.append(this.a, rhs.a)
		.append(this.b, rhs.b)
		.append(this.c, rhs.c)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.node_dev_id) 
		.append(this.active_start) 
		.append(this.load_type) 
		.append(this.load_value) 
		.append(this.load_info) 
		.append(this.load_use_rate) 
		.append(this.a) 
		.append(this.b) 
		.append(this.c) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("node_dev_id", this.node_dev_id) 
		.append("active_start", this.active_start) 
		.append("load_type", this.load_type) 
		.append("load_value", this.load_value) 
		.append("load_info", this.load_info) 
		.append("load_use_rate", this.load_use_rate) 
		.append("a", this.a) 
		.append("b", this.b) 
		.append("c", this.c) 
		.toString();
	}

}
