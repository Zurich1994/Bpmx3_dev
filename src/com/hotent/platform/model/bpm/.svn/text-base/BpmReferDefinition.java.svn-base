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
 * 对象功能:流程定义引用 Model对象
 * 开发公司: 广州宏天软件 
 * 开发人员:tgl 
 * 创建时间:2011-11-30 15:41:24
 */
@XmlRootElement(name="bpmReferDefinition")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmReferDefinition extends BaseModel implements Cloneable{
	
	/**
	 * @Field: serialVersionUID:
	 */
	private static final long serialVersionUID = -6303198451148356035L;
	
	//表名称
	public final static String TABLE_NAME="tt_bpm_refer_definition";
	
	
	@XmlAttribute
	protected Long id;
	// 流程定义ID
	@XmlAttribute
	protected Long defId;
	// 引用流程定义Key
	@XmlAttribute
	protected String referDefKey;
	//流程定义名称
	@XmlAttribute
	protected String subject; 
	// 流程状态
	@XmlAttribute
	protected String state;
	// 备注
	@XmlAttribute
	protected String remark;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setDefId(Long defId)
	{
		this.defId = defId;
	}

	/**
	 * 返回 流程定义ID
	 * 
	 * @return
	 */
	public Long getDefId()
	{
		return defId;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object)
	{
		if (!(object instanceof BpmReferDefinition))
		{
			return false;
		}
		BpmReferDefinition rhs = (BpmReferDefinition) object;
		return new EqualsBuilder()
				.append(this.defId, rhs.defId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.defId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return new ToStringBuilder(this)
				.append("defId", this.defId)
				.append("createtime", this.createtime)
				.append("state", this.state)
				.append("createBy", this.createBy)
				.append("updateBy", this.updateBy)
				.append("updatetime", this.updatetime)
				.append("referDefKey", this.referDefKey)
				.toString();
	}
	public Object clone(){
		BpmReferDefinition obj=null;
		try{
			obj=(BpmReferDefinition)super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public String getReferDefKey() {
		return referDefKey;
	}

	public void setReferDefKey(String referDefKey) {
		this.referDefKey = referDefKey;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}	
}