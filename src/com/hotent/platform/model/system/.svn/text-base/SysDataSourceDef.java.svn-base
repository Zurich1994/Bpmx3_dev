package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 对象功能:SYS_DATA_SOURCE_DEF Model对象 开发公司:广州宏天软件有限公司 开发人员:Aschs 创建时间:2014-08-20 11:10:06
 */
public class SysDataSourceDef extends BaseModel {
	// 主键
	protected Long id;
	// 名称
	protected String name;
	// 类路径
	protected String classPath;
	// 设置字段JSON
	protected String settingJson;
	// 初始化方法，可为空
	protected String initMethod;
	// 是否是系统内部，是的话生成了的数据源在服务器开启时就自动加载到服务器
	protected Boolean isSystem;
	// 在关闭数据源还需要调用的方法，可为空
	protected String closeMethod;
	protected Long runId = 0L;
	
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

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 名称
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	/**
	 * 返回 类路径
	 * 
	 * @return
	 */
	public String getClassPath() {
		return this.classPath;
	}

	public void setSettingJson(String settingJson) {
		this.settingJson = settingJson;
	}

	/**
	 * 返回 设置字段JSON
	 * 
	 * @return
	 */
	public String getSettingJson() {
		return this.settingJson;
	}

	public void setInitMethod(String initMethod) {
		this.initMethod = initMethod;
	}

	/**
	 * 返回 初始化方法，可为空
	 * 
	 * @return
	 */
	public String getInitMethod() {
		return this.initMethod;
	}

	/**
	 * isSystem
	 * @return  the isSystem
	 * @since   1.0.0
	 */
	
	public Boolean getIsSystem() {
		return isSystem;
	}

	/**
	 * @param isSystem the isSystem to set
	 */
	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	public void setCloseMethod(String closeMethod) {
		this.closeMethod = closeMethod;
	}

	/**
	 * 返回 在关闭数据源还需要调用的方法，可为空
	 * 
	 * @return
	 */
	public String getCloseMethod() {
		return this.closeMethod;
	}

	/**
	 * runId
	 * @return  the runId
	 * @since   1.0.0
	 */
	
	public Long getRunId() {
		return runId;
	}

	/**
	 * @param runId the runId to set
	 */
	public void setRunId(Long runId) {
		this.runId = runId;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof SysDataSourceDef)) {
			return false;
		}
		SysDataSourceDef rhs = (SysDataSourceDef) object;
		return new EqualsBuilder().append(this.id, rhs.id).append(this.name, rhs.name).append(this.classPath, rhs.classPath).append(this.settingJson, rhs.settingJson).append(this.initMethod, rhs.initMethod).append(this.isSystem, rhs.isSystem).append(this.closeMethod, rhs.closeMethod).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id).append(this.name).append(this.classPath).append(this.settingJson).append(this.initMethod).append(this.isSystem).append(this.closeMethod).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("name", this.name).append("classPath", this.classPath).append("settingJson", this.settingJson).append("initMethod", this.initMethod).append("isSystem", this.isSystem).append("closeMethod", this.closeMethod).toString();
	}

}