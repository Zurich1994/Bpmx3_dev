package com.hotent.platform.model.system;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:流水号生成 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-03 14:40:58
 */
@XmlRootElement(name="identity")
@XmlAccessorType(XmlAccessType.NONE)
public class Identity extends BaseModel
{
	// 主键
	@XmlAttribute
	protected Long id=0L;
	// 名称
	@XmlAttribute
	protected String name;
	// 别名
	@XmlAttribute
	protected String alias;
	// 规则
	@XmlAttribute
	protected String rule;
	// 每天生成
	@XmlAttribute
	protected Short genType=1;
	// 流水号长度
	@XmlAttribute
	protected Integer noLength;
	// 初始值
	@XmlAttribute
	protected Integer initValue;
	// 当前值
	@XmlAttribute
	protected Long curValue=0L;
	
	protected String curDate="";
	
	//步长
	@XmlAttribute
	protected Short step=1;
	
	
	protected Long newCurValue=1L;

	
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() 
	{
		return id;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 名称
	 * @return
	 */
	public String getName() 
	{
		return name;
	}

	public void setAlias(String alias) 
	{
		this.alias = alias;
	}
	/**
	 * 返回 别名
	 * @return
	 */
	public String getAlias() 
	{
		return alias;
	}

	public void setRule(String rule) 
	{
		this.rule = rule;
	}
	/**
	 * 返回 规则
	 * @return
	 */
	public String getRule() 
	{
		return rule;
	}

	public void setGenType(Short genType) 
	{
		this.genType = genType;
	}
	/**
	 * 返回 每天生成
	 * @return
	 */
	public Short getGenType() 
	{
		return genType;
	}

	public void setNoLength(Integer noLength) 
	{
		this.noLength = noLength;
	}
	/**
	 * 返回 流水号长度
	 * @return
	 */
	public Integer getNoLength() 
	{
		return noLength;
	}

	public void setInitValue(Integer initValue) 
	{
		this.initValue = initValue;
	}
	/**
	 * 返回 初始值
	 * @return
	 */
	public Integer getInitValue() 
	{
		return initValue;
	}

	public void setCurValue(Long curValue) 
	{
		this.curValue = curValue;
	}
	/**
	 * 返回 当前值
	 * @return
	 */
	public Long getCurValue() 
	{
		if(curValue==null) return 0L;
		return curValue;
	}
	
	public Short getStep() {
		return step;
	}
	public void setStep(Short step) {
		this.step = step;
	}
	
	public String getCurDate() {
		return curDate;
	}
	public void setCurDate(String curDate) {
		this.curDate = curDate;
	}

   
   	
	public Long getNewCurValue() {
		return newCurValue;
	}
	public void setNewCurValue(Long newCurValue) {
		this.newCurValue = newCurValue;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Identity)) 
		{
			return false;
		}
		Identity rhs = (Identity) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.alias, rhs.alias)
		.append(this.rule, rhs.rule)
		.append(this.genType, rhs.genType)
		.append(this.noLength, rhs.noLength)
		.append(this.initValue, rhs.initValue)
		.append(this.curValue, rhs.curValue)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.name) 
		.append(this.alias) 
		.append(this.rule) 
		.append(this.genType) 
		.append(this.noLength) 
		.append(this.initValue) 
		.append(this.curValue) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("name", this.name) 
		.append("alias", this.alias) 
		.append("rule", this.rule) 
		.append("genType", this.genType) 
		.append("noLength", this.noLength) 
		.append("initValue", this.initValue) 
		.append("curValue", this.curValue) 
		.toString();
	}
   
  

}