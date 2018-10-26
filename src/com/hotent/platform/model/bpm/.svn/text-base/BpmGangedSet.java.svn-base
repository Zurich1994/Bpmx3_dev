package com.hotent.platform.model.bpm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * <pre>
 * 对象功能:联动设置表 Model对象 
 * 开发公司:广州宏天软件有限公司
 * 开发人员:heyifan
 * 创建时间:2012-12-28 16:50:37
 * </pre>
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "bpmGangedSet")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmGangedSet extends BaseModel {
	/**
	 * 开始节点ID
	 */
	public static final String START_NODEID = "1";
	public static final String ALL_NODEID = "2";

	// 主键
	@XmlAttribute
	protected Long id;
	// 流程定义ID
	@XmlAttribute
	protected Long defid;
	// 流程节点ID
	@XmlAttribute
	protected String nodeid;
	// 流程节点名称
	@XmlAttribute
	protected String nodename;
	// 选择框字段设置
	@XmlAttribute
	protected String choisefield;
	// 变更字段设置
	@XmlAttribute
	protected String changefield;

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 主键
	 * 
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	public void setDefid(Long defid) {
		this.defid = defid;
	}

	/**
	 * 返回 流程定义ID
	 * 
	 * @return
	 */
	public Long getDefid() {
		return this.defid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	/**
	 * 返回 流程节点ID
	 * 
	 * @return
	 */
	public String getNodeid() {
		return this.nodeid;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	/**
	 * 返回 流程节点名称
	 * 
	 * @return
	 */
	public String getNodename() {
		return this.nodename;
	}

	public void setChoisefield(String choisefield) {
		this.choisefield = choisefield;
	}

	/**
	 * 返回 选择框字段设置
	 * 
	 * @return
	 */
	public String getChoisefield() {
		return this.choisefield;
	}

	public void setChangefield(String changefield) {
		this.changefield = changefield;
	}

	/**
	 * 返回 变更字段设置
	 * 
	 * @return
	 */
	public String getChangefield() {
		return this.changefield;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BpmGangedSet)) {
			return false;
		}
		BpmGangedSet rhs = (BpmGangedSet) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.defid, rhs.defid).append(this.nodeid, rhs.nodeid)
				.append(this.nodename, rhs.nodename)
				.append(this.choisefield, rhs.choisefield)
				.append(this.changefield, rhs.changefield).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id)
				.append(this.defid).append(this.nodeid).append(this.nodename)
				.append(this.choisefield).append(this.changefield).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("defid", this.defid).append("nodeid", this.nodeid)
				.append("nodename", this.nodename)
				.append("choisefield", this.choisefield)
				.append("changefield", this.changefield).toString();
	}

}