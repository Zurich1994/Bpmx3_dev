package com.hotent.platform.model.bpm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.core.model.BaseModel;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 对象功能:流程定义节点用户
 * 对象 开发公司:广州宏天软件有限公司 
 * 开发人员:cjj 创建时间:2011-12-08
 * 09:21:29
 */
@XmlRootElement(name = "bpmNodeUser")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmNodeUser extends BaseModel implements Cloneable {
	/**
	 * 发起人=0
	 */
	public static final short ASSIGN_TYPE_START_USER = 0;
	/**
	 * 用户=1
	 */
	public static final String ASSIGN_TYPE_USER = "users";
	/**
	 * 角色=2
	 */
	public static final String ASSIGN_TYPE_ROLE = "role";
	/**
	 * 组织=3
	 */
	public static final String ASSIGN_TYPE_ORG = "org";
	/**
	 * 组织负责人=4
	 */
	public static final String ASSIGN_TYPE_ORG_CHARGE = "orgCharge";
	/**
	 * 岗位=5
	 */
	public static final String ASSIGN_TYPE_POS = "pos";
	/**
	 * 上下级=6
	 */
	public static final String ASSIGN_TYPE_UP_LOW = "upLow";
	/**
	 * 用户属性=7
	 */
	public static final String ASSIGN_TYPE_USER_ATTR = "userAttr";
	/**
	 * 组织属性=8
	 */
	public static final String ASSIGN_TYPE_ORG_ATTR = "orgAttr";
	/**
	 * 本部门=9 即与发起人相同部门
	 */
	public static final short ASSIGN_TYPE_SAME_DEP = 9;
	/**
	 * 跟某个节相同执行人
	 */
	public static final String ASSIGN_TYPE_SAME_NODE = "sameNode";
	/**
	 * 发起人的直属领导
	 */
	public static final short ASSIGN_TYPE_DIRECT_LED = 11;
	/**
	 * 脚本
	 */
	public static final short ASSIGN_TYPE_SCRIPT = 12;
	/**
	 * 上个任务执行人的直属领导(组织)
	 */
	public static final short ASSIGN_TYPE_PREUSER_ORG_LEADER=13;
	/**
	 * 发起人的领导
	 */
	public static final short ASSIGN_TYPE_STARTUSER_LEADER=14;
	/**
	 * 上个任务执行人的领导
	 */
	public static final short ASSIGN_TYPE_PREVUSER_LEADER=15;
	
	/**
	 * 执行者部门的上级类型部门的负责人
	 */
	public static final short ASSIGN_TYPE_PREVTYPEUSER_LEADER=16;
	

	

	/**
	 * 运算类型为 or
	 */
	public static final short COMP_TYPE_OR = 0;
	/**
	 * 运算类型为 and
	 */
	public static final short COMP_TYPE_AND = 1;
	/**
	 * 运算类型为 exclude
	 */
	public static final short COMP_TYPE_EXCLUDE = 2;
	
	
	/**
	 * 通知任务执行人。
	 */
//	public static final String USER_TYPE_NOTIFY="NOTIFY";
	
	/**
	 * 归档触发的任务节点id
	 */
	public static final String NODE_ID_SYS_PROCESS_END_TASK="SYS_PROCESS_END_TASK";

	// nodeUserId
	@XmlAttribute
	protected Long nodeUserId;
	
	// 指派人员类型(技术类型)
	@XmlAttribute
	protected String assignType="";
	//
	@XmlAttribute
	protected String cmpIds;
	@XmlAttribute
	protected String cmpNames;

	// 运算类型（与、或、非）
	@XmlAttribute
	protected Short compType;
	//
	@XmlAttribute
	protected Integer sn;
	//条件ID
	@XmlAttribute
	protected Long conditionId;
	//是否抽取用户 0:不抽取 1:抽取
	@XmlAttribute
	protected Short extractUser;

	public void setNodeUserId(Long nodeUserId) {
		this.nodeUserId = nodeUserId;
	}
	
	

	/**
	 * 返回 nodeUserId
	 * 
	 * @return
	 */
	public Long getNodeUserId() {
		return nodeUserId;
	}

	

	public void setAssignType(String assignType) {
		this.assignType = assignType;
	}

	/**
	 * 返回 指派人员类型
	 * 
	 * @return
	 */
	public String getAssignType() {
		return assignType;
	}



	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BpmNodeUser)) {
			return false;
		}
		BpmNodeUser rhs = (BpmNodeUser) object;
		return new EqualsBuilder().append(this.nodeUserId, rhs.nodeUserId).
				append(this.cmpIds, rhs.cmpIds)
				.append(this.conditionId, rhs.conditionId).append(this.cmpNames, rhs.cmpNames).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.nodeUserId).append(this.assignType)
				.append(this.cmpIds)	.append(this.conditionId).append(this.cmpNames).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("nodeUserId", this.nodeUserId)
				.append("assignType", this.assignType).append("cmpIds", this.cmpIds).append("cmpNames", this.cmpNames)				
					.append("conditionId",this.conditionId).toString();
	}

	public String getCmpIds() {
		return cmpIds;
	}

	public void setCmpIds(String cmpIds) {
		this.cmpIds = cmpIds;
	}

	public String getCmpNames() {
		return cmpNames;
	}

	public void setCmpNames(String cmpNames) {
		this.cmpNames = cmpNames;
	}

	public Short getCompType() {
		return compType;
	}

	public void setCompType(Short compType) {
		this.compType = compType;
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	
	
	public Long getConditionId() {
		return conditionId;
	}

	public void setConditionId(Long conditionId) {
		this.conditionId = conditionId;
	}
	

	
	

	public Short getExtractUser() {
		return extractUser;
	}

	public void setExtractUser(Short extractUser) {
		this.extractUser = extractUser;
	}

	public Object clone() {
		BpmNodeUser obj = null;
		try {
			obj = (BpmNodeUser) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}

}