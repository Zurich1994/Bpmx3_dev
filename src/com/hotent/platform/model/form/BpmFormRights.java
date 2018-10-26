package com.hotent.platform.model.form;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
import com.hotent.mobile.model.form.PlatformType;
/**
 * 对象功能:字段权限 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xwy
 * 创建时间:2012-02-14 16:55:15
 */
@XmlRootElement(name = "bpmFormRights")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmFormRights extends BaseModel implements Cloneable
{
	public static final short FieldRights=1;
	
	public static final short TableRights=2;
	
	public static final short OpinionRights=3;
	
	public static final short TableShowRights=4;               //子表是否显示
	
	/** 无*/
	public static String TYPE_NONE = "none";
	/**所有人*/
	public static String TYPE_EVERYONE = "everyone";
	/**用户*/
	public static String TYPE_USER = "user";
	/**角色*/
	public static String TYPE_ROLE = "role";
	/**组织*/
	public static String TYPE_ORG = "org";
	/**组织负责人*/
	public static String TYPE_ORGMGR ="orgMgr";
	/**岗位*/
	public static String TYPE_POS ="pos";
	protected Long formDefId;
	protected PlatformType platformType = PlatformType.PC;
	
	// id
	@XmlAttribute
	protected Long id;
	// 表单定义KEY
	@XmlAttribute
	protected String formKey;
	// 字段名
	@XmlAttribute
	protected String name;
	// 权限
	@XmlAttribute
	protected String permission = "";
	//权限类型(1,字段,2,子表,3,意见)
	@XmlAttribute
	protected short type=1;
	//流程定义ID
	@XmlAttribute
	protected String actDefId="";
	//父流程定义ID（是子流程才有值的）
	@XmlAttribute
	protected String parentActDefId="";
	//流程任务ID
	@XmlAttribute
	protected String nodeId="";
	@XmlAttribute
	protected Integer sn=0;
	//所属平台
	@XmlAttribute
	protected Integer platform =0;
	
	public BpmFormRights()
	{
	}
	
	public BpmFormRights(Long id, String formKey, String name, String permission,short type,PlatformType platformType)
	{
		super();
		this.id = id;
		this.formKey = formKey;
		this.name = name;
		this.permission = permission;
		this.type=type;
		this.platformType=platformType;
	}
	public BpmFormRights(Long id, String formKey, String name, String permission,short type)
	{
		super();
		this.id = id;
		this.formKey = formKey;
		this.name = name;
		this.permission = permission;
		this.type=type;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 id
	 * @return
	 */
	public Long getId() 
	{
		return id;
	}

	public void setFormKey(String formKey) 
	{
		this.formKey = formKey;
	}
	/**
	 * 返回 表单定义ID
	 * @return
	 */
	public String getFormKey() 
	{
		return formKey;
	}

	public void setName(String fieldName) 
	{
		this.name = fieldName;
	}
	/**
	 * 返回 字段名
	 * @return
	 */
	public String getName() 
	{
		return name;
	}

	public void setPermission(String permission) 
	{
		this.permission = permission;
	}
	/**
	 * 返回 权限
	 * @return
	 */
	public String getPermission() 
	{
		return permission;
	}
	
	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	
	public String getActDefId() {
		return actDefId;
	}

	public void setActDefId(String actDefId) {
		this.actDefId = actDefId;
	}
	
	public String getParentActDefId() {
		return parentActDefId;
	}

	public void setParentActDefId(String parentActDefId) {
		this.parentActDefId = parentActDefId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

   
   	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public Integer getPlatform() {
		if(this.getPlatformType()==null) {
			return null;
		}
		return Integer.parseInt(this.getPlatformType().getValue());
	}

	public void setPlatform(String platform) {
		this.setPlatformType(PlatformType.getEnumFromString(platform));
	}
	public PlatformType getPlatformType() {
		return platformType;
	}

	public void setPlatformType(PlatformType platform) {
		this.platformType = platform;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmFormRights)) 
		{
			return false;
		}
		BpmFormRights rhs = (BpmFormRights) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.permission, rhs.permission)
		.isEquals();
	}
	public Long getFormDefId() 
	{
		return formDefId;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public void setFormDefId(Long formDefId) 
	{
		this.formDefId = formDefId;
	}
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.name) 
		.append(this.permission) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("fieldName", this.name) 
		.append("permission", this.permission) 
		.toString();
	}
	
	
	public Object clone()
	{
		BpmFormRights obj=null;
		try{
			obj=(BpmFormRights)super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}
   
  

}