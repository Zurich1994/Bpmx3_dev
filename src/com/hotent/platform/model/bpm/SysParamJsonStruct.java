package com.hotent.platform.model.bpm;

import java.io.Serializable;
import java.util.List;

public class SysParamJsonStruct implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//是否组合规则
	private Boolean branch = false;
	//子规则
	private List<SysParamJsonStruct> sub;
	//规则类型 3：用户属性 2：组织属性
	private String ruleType;
	//规则字段类型
	private String dataType;
	//规则字段
	private String paramKey;
	//运算类型 or and
	private String compType = "";
	//判断符
	private String paramCondition;
	//判断值
	private String paramValue;
	//规则描述文字
	private String conDesc;
	//表达式
	private String expression;
	
	
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getCompType() {
		return compType;
	}
	public void setCompType(String compType) {
		this.compType = compType;
	}
	public String getConDesc() {
		return conDesc;
	}
	public void setConDesc(String conDesc) {
		this.conDesc = conDesc;
	}
	public Boolean getBranch() {
		return branch;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getParamKey() {
		return paramKey;
	}
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	public String getParamCondition() {
		return paramCondition;
	}
	public void setParamCondition(String paramCondition) {
		this.paramCondition = paramCondition;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public void setBranch(Boolean branch) {
		this.branch = branch;
	}
	public List<SysParamJsonStruct> getSub() {
		return sub;
	}
	public void setSub(List<SysParamJsonStruct> sub) {
		this.sub = sub;
	}
	
	public String toString() {
		return "ConditionJsonStruct [ruleType=" + ruleType + ", dataType="
				+ dataType + ", paramKey=" + paramKey + ", compType="
				+ compType + ", paramCondition=" + paramCondition + ", paramValue="
				+ paramValue + ", conDesc=" + conDesc + ", expression=" + expression
				+ "]";
	}
}