package com.hotent.platform.model.system;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.hotent.core.model.BaseModel;

/**
 * 对象功能:子系统管理 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-11-21 12:22:06
 */
public class SubSystem extends BaseModel{
	//当前系统
	public static String CURRENT_SYSTEM="CURRENT_SYSTEM";
	//默认图标
	public static String DEFAULT_LOG="/styles/default/images/resicon/home.png";
	
	public static short isLocal_Y=1;
	public static short isLocal_N=0;
	
	// 主键
	private long systemId=0L;
	// 系统名称
	private String sysName;
	// 别名(系统中唯一)
	private String alias;
	// 系统的图标
	private String logo;
	// 系统首页地址
	private String defaultUrl;
	// 备注
	private String memo;
	// 创建时间
	private Date createtime;
	// 创建人
	private String creator;
	// 允许删除
	private Short allowDel=0;
	// 选择组织架构
	private Short needOrg;
	// 是否激活
	private Short isActive=1;
	//父系统，用于生成树
	private Long parentId=0l;
	//是否本地
	private Short isLocal=1;
	//
	private String homePage;
	//系统类别
	private Short systemType=1;
	//联系方式
	private String phoneNumber;
	//平台介绍
	private String platIntroduce;
	//联系人
	private String contacts;
	

	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	public Short getIsLocal() {
		return isLocal;
	}
	public void setIsLocal(Short isLocal) {
		this.isLocal = isLocal;
	}
	
	public Short getSystemType() {   //系统类别   (业务类和系统类)
		return systemType;
	}
	public void setSystemType(Short systemType) {
		this.systemType = systemType;
	}
	
	public String getPhoneNumber() {   //logo中平台联系方式
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPlatIntroduce() {   //logo中平台介绍
		return platIntroduce;
	}
	public void setPlatIntroduce(String platIntroduce) {
		this.platIntroduce = platIntroduce;
	}
	public String getContacts() {   //联系人
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	/**
	 * 角色列表
	 */
	List<SysRole> roleList;

	
	public void setSystemId(long systemId) {
		this.systemId = systemId;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public long getSystemId() {
		return systemId;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	/**
	 * 返回 系统名称
	 * @return
	 */
	public String getSysName() {
		return sysName;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * 返回 别名(系统中唯一)
	 * @return
	 */
	public String getAlias() {
		return alias;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * 返回 系统的图标
	 * @return
	 */
	public String getLogo() {
		if(logo!=null){
			return logo;
		}else{
			return DEFAULT_LOG;
		}
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}
	/**
	 * 返回 系统首页地址
	 * @return
	 */
	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getMemo() {
		return memo;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 返回 创建时间
	 * @return
	 */
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * 返回 创建人
	 * @return
	 */
	public String getCreator() {
		return creator;
	}

	public void setAllowDel(Short allowDel) {
		this.allowDel = allowDel;
	}
	/**
	 * 返回 允许删除
	 * @return
	 */
	public Short getAllowDel() {
		return allowDel;
	}

	public void setNeedOrg(Short needOrg) {
		this.needOrg = needOrg;
	}
	/**
	 * 返回 选择组织架构
	 * @return
	 */
	public Short getNeedOrg() {
		return needOrg;
	}

	public void setIsActive(Short isActive) {
		this.isActive = isActive;
	}
	/**
	 * 返回 是否激活
	 * @return
	 */
	public Short getIsActive() {
		return isActive;
	}	
	
	public List<SysRole> getRoleList() {
		return roleList;
	}
	
	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}
	
	public Long getParentId()
	{
		return parentId;
	}
	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}
	
	 public int hashCode() {
		 HashCodeBuilder has=new HashCodeBuilder();
		 has.append(this.systemId);
		 return has.toHashCode();
	 }
	 
	public boolean equals(Object object) 
	{
		if (!(object instanceof SubSystem)) 
		{
			return false;
		}
		SubSystem rhs = (SubSystem) object;
		return new EqualsBuilder()
		.append(this.systemId, rhs.systemId)
		.isEquals();
	}
	
	
	
}