package com.hotent.core.web.query.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.util.QueryConstants;

/**
 * 查询条件的范围值（两个值）封装。
 * 
 * @author win
 * 
 */
public class JudgeScope extends JudgeSingle {
	/**
	 * 结束值的比较符
	 */
	private String compareEnd;
	/**
	 * 结束值
	 */
	private String valueEnd;
	/**
	 * 结束值和开始值的关系
	 */
	private String relation;
	
	/**
	 * 操作类型
	 */
	private Integer optType;

	public JudgeSingle getBeginJudge() {
		JudgeSingle judge = new JudgeSingle();
		judge.setFieldName(fieldName);
		judge.setCompare(compare);
		judge.setValue(value);
		return judge;
	}

	public JudgeSingle getEndJudge() {
		JudgeSingle judge = new JudgeSingle();
		judge.setFieldName(fieldName);
		judge.setCompare(compareEnd);
		judge.setValue(valueEnd);
		return judge;
	}

	public void setCompareEnd(String compareEnd) {
		this.compareEnd = compareEnd;
	}

	public void setValueEnd(String valueEnd) {
		this.valueEnd = valueEnd;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getRelation() {
		if(BeanUtils.isEmpty(relation))
			// 目前默认是与关系
			return QueryConstants.QUERY_AND;	
		 return relation;
	}

	


	public Integer getOptType() {
		return optType;
	}

	public void setOptType(Integer optType) {
		this.optType = optType;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fieldName", fieldName)
				.append("compare", compare).append("value", value)
				.append("compareEnd", compareEnd).append("valueEnd", valueEnd)
				.append("relation", relation).toString();
	}
}
