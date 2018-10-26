package com.hotent.platform.model.bus;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
import com.hotent.core.web.query.entity.FieldShow;

/**
 * 对象功能:查询设置 Model对象 开发公司:广州宏天软件有限公司 开发人员:zxh 创建时间:2014-01-20 15:31:03
 */
public class BusQuerySetting extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2815452680584496462L;
	// 主键
	protected Long id;
	// 表名
	protected String tableName;
	// 显示字段
	protected String displayField;
	// 用户ID
	protected Long userId;
	
	
	protected List<FieldShow> fieldShowList;

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

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 返回 表名
	 * 
	 * @return
	 */
	public String getTableName() {
		return this.tableName;
	}

	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}

	/**
	 * 返回 显示字段
	 * 
	 * @return
	 */
	public String getDisplayField() {
		return this.displayField;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 返回 用户ID
	 * 
	 * @return
	 */
	public Long getUserId() {
		return this.userId;
	}
	
	public List<FieldShow> getFieldShowList() {
		return fieldShowList;
	}

	public void setFieldShowList(List<FieldShow> fieldShowList) {
		this.fieldShowList = fieldShowList;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BusQuerySetting)) {
			return false;
		}
		BusQuerySetting rhs = (BusQuerySetting) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.tableName, rhs.tableName)
				.append(this.displayField, rhs.displayField)
				.append(this.userId, rhs.userId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id)
				.append(this.tableName).append(this.displayField)
				.append(this.userId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("tableName", this.tableName)
				.append("displayField", this.displayField)
				.append("userId", this.userId).toString();
	}

}