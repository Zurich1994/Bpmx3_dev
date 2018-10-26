package com.hotent.TbaActivityInfoPath.model.TbaActivityInfoCode;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:活动操作信息表 Model对象
 */
public class TbaActivityInfo extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *项目ID
	 */
	protected String  item_id;
	/**
	 *作业类别
	 */
	protected String  work_type;
	/**
	 *作业项
	 */
	protected String  work_item;
	/**
	 *作业号
	 */
	protected String  work_id;
	/**
	 *作业名称
	 */
	protected String  flow_name;
	/**
	 *活动ID
	 */
	protected String  activity_id;
	/**
	 *活动名称
	 */
	protected String  activity_name;
	/**
	 *活动形态
	 */
	protected String  server_shape;
	/**
	 *活动方式
	 */
	protected String  server_way;
	/**
	 *活动类型
	 */
	protected String  server_class;
	/**
	 *活动来源
	 */
	protected String  server_source;
	/**
	 *活动性质
	 */
	protected String  server_nature;
	/**
	 *信息形态
	 */
	protected String  info_shape;
	/**
	 *信息标准化
	 */
	protected String  info_stand;
	/**
	 *信息类型
	 */
	protected String  info_type;
	/**
	 *活动类别
	 */
	protected String  server_type;
	/**
	 *活动成熟度
	 */
	protected Long  op_comp;
	/**
	 *作业名
	 */
	protected String  work_name;
	/**
	 *信息量
	 */
	protected Long  op_info_sum;
	/**
	 *A
	 */
	protected String  A;
	/**
	 *B
	 */
	protected String  B;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setItem_id(String item_id) 
	{
		this.item_id = item_id;
	}
	/**
	 * 返回 项目ID
	 * @return
	 */
	public String getItem_id() 
	{
		return this.item_id;
	}
	public void setWork_type(String work_type) 
	{
		this.work_type = work_type;
	}
	/**
	 * 返回 作业类别
	 * @return
	 */
	public String getWork_type() 
	{
		return this.work_type;
	}
	public void setWork_item(String work_item) 
	{
		this.work_item = work_item;
	}
	/**
	 * 返回 作业项
	 * @return
	 */
	public String getWork_item() 
	{
		return this.work_item;
	}
	public void setWork_id(String work_id) 
	{
		this.work_id = work_id;
	}
	/**
	 * 返回 作业号
	 * @return
	 */
	public String getWork_id() 
	{
		return this.work_id;
	}
	public void setFlow_name(String flow_name) 
	{
		this.flow_name = flow_name;
	}
	/**
	 * 返回 作业名称
	 * @return
	 */
	public String getFlow_name() 
	{
		return this.flow_name;
	}
	public void setActivity_id(String activity_id) 
	{
		this.activity_id = activity_id;
	}
	/**
	 * 返回 活动ID
	 * @return
	 */
	public String getActivity_id() 
	{
		return this.activity_id;
	}
	public void setActivity_name(String activity_name) 
	{
		this.activity_name = activity_name;
	}
	/**
	 * 返回 活动名称
	 * @return
	 */
	public String getActivity_name() 
	{
		return this.activity_name;
	}
	public void setServer_shape(String server_shape) 
	{
		this.server_shape = server_shape;
	}
	/**
	 * 返回 活动形态
	 * @return
	 */
	public String getServer_shape() 
	{
		return this.server_shape;
	}
	public void setServer_way(String server_way) 
	{
		this.server_way = server_way;
	}
	/**
	 * 返回 活动方式
	 * @return
	 */
	public String getServer_way() 
	{
		return this.server_way;
	}
	public void setServer_class(String server_class) 
	{
		this.server_class = server_class;
	}
	/**
	 * 返回 活动类型
	 * @return
	 */
	public String getServer_class() 
	{
		return this.server_class;
	}
	public void setServer_source(String server_source) 
	{
		this.server_source = server_source;
	}
	/**
	 * 返回 活动来源
	 * @return
	 */
	public String getServer_source() 
	{
		return this.server_source;
	}
	public void setServer_nature(String server_nature) 
	{
		this.server_nature = server_nature;
	}
	/**
	 * 返回 活动性质
	 * @return
	 */
	public String getServer_nature() 
	{
		return this.server_nature;
	}
	public void setInfo_shape(String info_shape) 
	{
		this.info_shape = info_shape;
	}
	/**
	 * 返回 信息形态
	 * @return
	 */
	public String getInfo_shape() 
	{
		return this.info_shape;
	}
	public void setInfo_stand(String info_stand) 
	{
		this.info_stand = info_stand;
	}
	/**
	 * 返回 信息标准化
	 * @return
	 */
	public String getInfo_stand() 
	{
		return this.info_stand;
	}
	public void setInfo_type(String info_type) 
	{
		this.info_type = info_type;
	}
	/**
	 * 返回 信息类型
	 * @return
	 */
	public String getInfo_type() 
	{
		return this.info_type;
	}
	public void setServer_type(String server_type) 
	{
		this.server_type = server_type;
	}
	/**
	 * 返回 活动类别
	 * @return
	 */
	public String getServer_type() 
	{
		return this.server_type;
	}
	public void setOp_comp(Long op_comp) 
	{
		this.op_comp = op_comp;
	}
	/**
	 * 返回 活动成熟度
	 * @return
	 */
	public Long getOp_comp() 
	{
		return this.op_comp;
	}
	public void setWork_name(String work_name) 
	{
		this.work_name = work_name;
	}
	/**
	 * 返回 作业名
	 * @return
	 */
	public String getWork_name() 
	{
		return this.work_name;
	}
	public void setOp_info_sum(Long op_info_sum) 
	{
		this.op_info_sum = op_info_sum;
	}
	/**
	 * 返回 信息量
	 * @return
	 */
	public Long getOp_info_sum() 
	{
		return this.op_info_sum;
	}
	public void setA(String A) 
	{
		this.A = A;
	}
	/**
	 * 返回 A
	 * @return
	 */
	public String getA() 
	{
		return this.A;
	}
	public void setB(String B) 
	{
		this.B = B;
	}
	/**
	 * 返回 B
	 * @return
	 */
	public String getB() 
	{
		return this.B;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof TbaActivityInfo)) 
		{
			return false;
		}
		TbaActivityInfo rhs = (TbaActivityInfo) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.item_id, rhs.item_id)
		.append(this.work_type, rhs.work_type)
		.append(this.work_item, rhs.work_item)
		.append(this.work_id, rhs.work_id)
		.append(this.flow_name, rhs.flow_name)
		.append(this.activity_id, rhs.activity_id)
		.append(this.activity_name, rhs.activity_name)
		.append(this.server_shape, rhs.server_shape)
		.append(this.server_way, rhs.server_way)
		.append(this.server_class, rhs.server_class)
		.append(this.server_source, rhs.server_source)
		.append(this.server_nature, rhs.server_nature)
		.append(this.info_shape, rhs.info_shape)
		.append(this.info_stand, rhs.info_stand)
		.append(this.info_type, rhs.info_type)
		.append(this.server_type, rhs.server_type)
		.append(this.op_comp, rhs.op_comp)
		.append(this.work_name, rhs.work_name)
		.append(this.op_info_sum, rhs.op_info_sum)
		.append(this.A, rhs.A)
		.append(this.B, rhs.B)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.item_id) 
		.append(this.work_type) 
		.append(this.work_item) 
		.append(this.work_id) 
		.append(this.flow_name) 
		.append(this.activity_id) 
		.append(this.activity_name) 
		.append(this.server_shape) 
		.append(this.server_way) 
		.append(this.server_class) 
		.append(this.server_source) 
		.append(this.server_nature) 
		.append(this.info_shape) 
		.append(this.info_stand) 
		.append(this.info_type) 
		.append(this.server_type) 
		.append(this.op_comp) 
		.append(this.work_name) 
		.append(this.op_info_sum) 
		.append(this.A) 
		.append(this.B) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("item_id", this.item_id) 
		.append("work_type", this.work_type) 
		.append("work_item", this.work_item) 
		.append("work_id", this.work_id) 
		.append("flow_name", this.flow_name) 
		.append("activity_id", this.activity_id) 
		.append("activity_name", this.activity_name) 
		.append("server_shape", this.server_shape) 
		.append("server_way", this.server_way) 
		.append("server_class", this.server_class) 
		.append("server_source", this.server_source) 
		.append("server_nature", this.server_nature) 
		.append("info_shape", this.info_shape) 
		.append("info_stand", this.info_stand) 
		.append("info_type", this.info_type) 
		.append("server_type", this.server_type) 
		.append("op_comp", this.op_comp) 
		.append("work_name", this.work_name) 
		.append("op_info_sum", this.op_info_sum) 
		.append("A", this.A) 
		.append("B", this.B) 
		.toString();
	}

}
