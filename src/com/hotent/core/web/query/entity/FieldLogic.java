package com.hotent.core.web.query.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 字段映射关系逻辑
 * 
 * @author csx
 */
public class FieldLogic extends FieldTable {
	/**
	 * 是否组合条件
	 */
	private boolean isGroup;
	/**
	 * 组合条件查询集合
	 */
	private List<FieldLogic> groupLogics = new ArrayList<FieldLogic>();
	/**
	 * 字段数据类型
	 */
	private Integer dataType;
	/**
	 * 判断类型：1 单值；2 范围值；3 脚本
	 */
	private int judgeType;
	/**
	 * 单值的数据封装
	 */
	private JudgeSingle judgeSingle;
	/**
	 * 范围值的数据封装
	 */
	private JudgeScope judgeScope;

	/**
	 * 脚本值的数据封装
	 */
	private JudgeScript judgeScript;
	/**
	 * 字段关系，表示该字段和前面其它条件的关系。
	 */
	protected String fieldRelation;

	public FieldLogic() {
	}

	public boolean isGroup() {
		return isGroup;
	}

	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}

	public List<FieldLogic> getGroupLogics() {
		return groupLogics;
	}

	public void setGroupLogics(List<FieldLogic> groupLogics) {
		this.groupLogics = groupLogics;
	}

	public Integer getDataType() {
		return dataType;
	}

	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	public int getJudgeType() {
		return judgeType;
	}

	public void setJudgeType(int judgeType) {
		this.judgeType = judgeType;
	}

	public JudgeSingle getJudgeSingle() {
		return judgeSingle;
	}

	public void setJudgeSingle(JudgeSingle judgeSingle) {
		this.judgeSingle = judgeSingle;
	}

	public JudgeScope getJudgeScope() {
		return judgeScope;
	}

	public void setJudgeScope(JudgeScope judgeScope) {
		this.judgeScope = judgeScope;
	}

	public JudgeScript getJudgeScript() {
		return judgeScript;
	}

	public void setJudgeScript(JudgeScript judgeScript) {
		this.judgeScript = judgeScript;
	}

	public String getFieldRelation() {
		return fieldRelation;
	}

	public void setFieldRelation(String fieldRelation) {
		this.fieldRelation = fieldRelation;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("isGroup", isGroup)
				.append("groupLogics.size", groupLogics.size())
				.append("dataType", dataType).append("judgeType", judgeType)
				.append("fieldRelation", fieldRelation)
				.append("judgeSingle", judgeSingle)
				.append("JudgeScope", judgeScope).toString();
	}

	// public String getSql() {
	// if(whereClauses.size()==0) return "";
	// if(whereClauses.size()==1 && !NOT.equals(fieldRelation)) return
	// whereClauses.get(0).getSql();
	//
	// StringBuffer sqlBuf=new StringBuffer("(");
	// int i=0;
	// if(whereClauses.size()>0 && NOT.equals(fieldRelation)){
	// sqlBuf.append(" NOT (");
	// for(IWhereClause clause:whereClauses){
	// if(i++>0){
	// sqlBuf.append(" ").append(AND).append(" ");
	// }
	// sqlBuf.append(clause.getSql());
	// }
	// sqlBuf.append(")");
	//
	// return sqlBuf.toString();
	// }
	//
	// for(IWhereClause clause:whereClauses){
	// if(i++>0){
	// sqlBuf.append(" ").append(fieldRelation).append(" ");
	// }
	// sqlBuf.append(clause.getSql());
	// }
	// sqlBuf.append(")");
	//
	// return sqlBuf.toString();
	// }

}
