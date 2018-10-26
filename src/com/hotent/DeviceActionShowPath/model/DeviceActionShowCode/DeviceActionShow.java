package com.hotent.DeviceActionShowPath.model.DeviceActionShowCode;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:设备动作展现表 Model对象
 */
public class DeviceActionShow extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *节点设备id
	 */
	protected String  node_dev_id;
	/**
	 *发起时间
	 */
	protected String  start_time;
	/**
	 *动作名称
	 */
	protected String  actionname;
	/**
	 *触发动作间隔时间
	 */
	protected String  actiontime;
	/**
	 *动作循环次数
	 */
	protected String  actioncount;
	/**
	 *负载类型
	 */
	protected String  lode_type;
	/**
	 *利用率值
	 */
	protected Long  load_use_rate;
	/**
	 *设备类型
	 */
	protected String  dev_type_code;
	/**
	 *重复次数
	 */
	protected String  sameaction_sign;
	/**
	 *重复次数1
	 */
	protected String  sameaction_sign1;
	/**
	 *节点颜色
	 */
	protected String  node_color;
	/**
	 *节点动画
	 */
	protected String  node_flash;
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
	/**
	 *d
	 */
	protected String  d;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNode_dev_id(String node_dev_id) 
	{
		this.node_dev_id = node_dev_id;
	}
	/**
	 * 返回 节点设备id
	 * @return
	 */
	public String getNode_dev_id() 
	{
		return this.node_dev_id;
	}
	public void setStart_time(String start_time) 
	{
		this.start_time = start_time;
	}
	/**
	 * 返回 发起时间
	 * @return
	 */
	public String getStart_time() 
	{
		return this.start_time;
	}
	public void setActionname(String actionname) 
	{
		this.actionname = actionname;
	}
	/**
	 * 返回 动作名称
	 * @return
	 */
	public String getActionname() 
	{
		return this.actionname;
	}
	public void setActiontime(String actiontime) 
	{
		this.actiontime = actiontime;
	}
	/**
	 * 返回 触发动作间隔时间
	 * @return
	 */
	public String getActiontime() 
	{
		return this.actiontime;
	}
	public void setActioncount(String actioncount) 
	{
		this.actioncount = actioncount;
	}
	/**
	 * 返回 动作循环次数
	 * @return
	 */
	public String getActioncount() 
	{
		return this.actioncount;
	}
	public void setLode_type(String lode_type) 
	{
		this.lode_type = lode_type;
	}
	/**
	 * 返回 负载类型
	 * @return
	 */
	public String getLode_type() 
	{
		return this.lode_type;
	}
	public void setLoad_use_rate(Long load_use_rate) 
	{
		this.load_use_rate = load_use_rate;
	}
	/**
	 * 返回 利用率值
	 * @return
	 */
	public Long getLoad_use_rate() 
	{
		return this.load_use_rate;
	}
	public void setDev_type_code(String dev_type_code) 
	{
		this.dev_type_code = dev_type_code;
	}
	/**
	 * 返回 设备类型
	 * @return
	 */
	public String getDev_type_code() 
	{
		return this.dev_type_code;
	}
	public void setSameaction_sign(String sameaction_sign) 
	{
		this.sameaction_sign = sameaction_sign;
	}
	/**
	 * 返回 重复次数
	 * @return
	 */
	public String getSameaction_sign() 
	{
		return this.sameaction_sign;
	}
	public void setSameaction_sign1(String sameaction_sign1) 
	{
		this.sameaction_sign1 = sameaction_sign1;
	}
	/**
	 * 返回 重复次数1
	 * @return
	 */
	public String getSameaction_sign1() 
	{
		return this.sameaction_sign1;
	}
	public void setNode_color(String node_color) 
	{
		this.node_color = node_color;
	}
	/**
	 * 返回 节点颜色
	 * @return
	 */
	public String getNode_color() 
	{
		return this.node_color;
	}
	public void setNode_flash(String node_flash) 
	{
		this.node_flash = node_flash;
	}
	/**
	 * 返回 节点动画
	 * @return
	 */
	public String getNode_flash() 
	{
		return this.node_flash;
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
	public void setD(String d) 
	{
		this.d = d;
	}
	/**
	 * 返回 d
	 * @return
	 */
	public String getD() 
	{
		return this.d;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof DeviceActionShow)) 
		{
			return false;
		}
		DeviceActionShow rhs = (DeviceActionShow) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.node_dev_id, rhs.node_dev_id)
		.append(this.start_time, rhs.start_time)
		.append(this.actionname, rhs.actionname)
		.append(this.actiontime, rhs.actiontime)
		.append(this.actioncount, rhs.actioncount)
		.append(this.lode_type, rhs.lode_type)
		.append(this.load_use_rate, rhs.load_use_rate)
		.append(this.dev_type_code, rhs.dev_type_code)
		.append(this.sameaction_sign, rhs.sameaction_sign)
		.append(this.sameaction_sign1, rhs.sameaction_sign1)
		.append(this.node_color, rhs.node_color)
		.append(this.node_flash, rhs.node_flash)
		.append(this.a, rhs.a)
		.append(this.b, rhs.b)
		.append(this.c, rhs.c)
		.append(this.d, rhs.d)
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
		.append(this.start_time) 
		.append(this.actionname) 
		.append(this.actiontime) 
		.append(this.actioncount) 
		.append(this.lode_type) 
		.append(this.load_use_rate) 
		.append(this.dev_type_code) 
		.append(this.sameaction_sign) 
		.append(this.sameaction_sign1) 
		.append(this.node_color) 
		.append(this.node_flash) 
		.append(this.a) 
		.append(this.b) 
		.append(this.c) 
		.append(this.d) 
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
		.append("start_time", this.start_time) 
		.append("actionname", this.actionname) 
		.append("actiontime", this.actiontime) 
		.append("actioncount", this.actioncount) 
		.append("lode_type", this.lode_type) 
		.append("load_use_rate", this.load_use_rate) 
		.append("dev_type_code", this.dev_type_code) 
		.append("sameaction_sign", this.sameaction_sign) 
		.append("sameaction_sign1", this.sameaction_sign1) 
		.append("node_color", this.node_color) 
		.append("node_flash", this.node_flash) 
		.append("a", this.a) 
		.append("b", this.b) 
		.append("c", this.c) 
		.append("d", this.d) 
		.toString();
	}

}
