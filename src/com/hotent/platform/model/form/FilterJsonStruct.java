package com.hotent.platform.model.form;

import java.io.Serializable;
import java.util.List;

import com.hotent.core.util.StringPool;

public class FilterJsonStruct implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 规则类型-普通=1
	 */
	public final static int RULE_TYPE_NORMAL = 1;
	/**
	 * 规则类型-脚本=2
	 */
	public final static int RULE_TYPE_SCRIPT = 2;
	
	//是否组合规则
	private Boolean branch = false;
	//子规则
	private List<FilterJsonStruct> sub;
	//规则类型 
	//1：普通规则 
	//2：脚本规则
	// 字段 操作符 脚本
	private Integer ruleType;
	//规则字段类型
	/*optType
		1:数值
		2:字符串
		3.日期
		4.数据字典
		5.人员选择器
	*/
	private Integer optType;
	//规则字段
	private String flowvarKey;
	//运算类型 or and
	private String compType = "";
	//判断符1
	/**
	 * 日期数字:
		  1:等于
		  2：不等于
		  3:大于
		  4:>=
		  5:<
		  6:<=
		  7:in type: in (1,2,3)
		  
		字符串，数据字典
		1:等于
		2:不等于
		3:等于(忽略大小写)
		4:like
		5:like左
		6:like 右
		
		人员选择器
			1:包含
			2.不包含
			3.等于变量 
			4.不等于变量
	 * 
	 *
	 */
	private String judgeCon1;
	//判断值1
	private String judgeVal1;
	//判断符2
	private String judgeCon2;
	//判断值2
	private String judgeVal2;
	//规则描述文字
	private String conDesc;
	//表和表关系
	private String tables;
	//脚本
	private String script;
	//来源 1= 表示自定义表，0，表示其他表
	private String source="1";
	
	protected Integer isHidden = 0;
	
	//日期格式化
	private String datefmt=StringPool.DATE_FORMAT_DATE;

	/**
	 * 表名
	 */
	private String table ="";
	/**
	 * 主表
	 */
	private String mainTable="";
	/**
	 * 外键字段
	 */
	private String relation="";
	
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
	public List<FilterJsonStruct> getSub() {
		return sub;
	}
	public void setSub(List<FilterJsonStruct> sub) {
		this.sub = sub;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getDatefmt() {
		return datefmt;
	}
	public void setDatefmt(String datefmt) {
		this.datefmt = datefmt;
	}
		
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	
	public String getMainTable() {
		return mainTable;
	}
	public void setMainTable(String mainTable) {
		this.mainTable = mainTable;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getTables() {
		return tables;
	}
	public void setTables(String tables) {
		this.tables = tables;
	}
	
	public Integer getIsHidden() {
		return isHidden;
	}
	public void setIsHidden(Integer isHidden) {
		this.isHidden = isHidden;
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