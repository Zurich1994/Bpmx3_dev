package com.hotent.platform.model.bpm;

import java.io.Serializable;
import java.util.List;

public class ConditionJsonStruct implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//是否组合规则
	private Boolean branch = false;
	//子规则
	private List<ConditionJsonStruct> sub;
	//规则类型 1：普通规则 2：脚本规则
	private Integer ruleType;
	//规则字段类型
	private Integer optType;
	//规则字段
	private String flowvarKey;
	//运算类型 or and
	private String compType = "";
	//判断符1
	private String judgeCon1;
	//判断值1
	private String judgeVal1;
	//判断符2
	private String judgeCon2;
	//判断值2
	private String judgeVal2;
	//规则描述文字
	private String conDesc;
	//脚本
	private String script;
	
	
	public Integer getRuleType() {
		return ruleType;
	}
	public void setRuleType(Integer ruleType) {
		this.ruleType = ruleType;
	}
	public Integer getOptType() {
		return optType;
	}
	public void setOptType(Integer optType) {
		this.optType = optType;
	}
	public String getFlowvarKey() {
		return flowvarKey;
	}
	public void setFlowvarKey(String flowvarKey) {
		this.flowvarKey = flowvarKey;
	}
	public String getCompType() {
		return compType;
	}
	public void setCompType(String compType) {
		this.compType = compType;
	}
	public String getJudgeCon1() {
		return judgeCon1;
	}
	public void setJudgeCon1(String judgeCon1) {
		this.judgeCon1 = judgeCon1;
	}
	public String getJudgeVal1() {
		return judgeVal1;
	}
	public void setJudgeVal1(String judgeVal1) {
		this.judgeVal1 = judgeVal1;
	}
	public String getJudgeCon2() {
		return judgeCon2;
	}
	public void setJudgeCon2(String judgeCon2) {
		this.judgeCon2 = judgeCon2;
	}
	public String getJudgeVal2() {
		return judgeVal2;
	}
	public void setJudgeVal2(String judgeVal2) {
		this.judgeVal2 = judgeVal2;
	}
	public String getConDesc() {
		return conDesc;
	}
	public void setConDesc(String conDesc) {
		this.conDesc = conDesc;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	
	public Boolean getBranch() {
		return branch;
	}
	public void setBranch(Boolean branch) {
		this.branch = branch;
	}
	public List<ConditionJsonStruct> getSub() {
		return sub;
	}
	public void setSub(List<ConditionJsonStruct> sub) {
		this.sub = sub;
	}
	
	public String toString() {
		return "ConditionJsonStruct [ruleType=" + ruleType + ", optType="
				+ optType + ", flowvarKey=" + flowvarKey + ", compType="
				+ compType + ", judgeCon1=" + judgeCon1 + ", judgeVal1="
				+ judgeVal1 + ", judgeCon2=" + judgeCon2 + ", judgeVal2="
				+ judgeVal2 + ", conDesc=" + conDesc + ", script=" + script
				+ "]";
	}
}