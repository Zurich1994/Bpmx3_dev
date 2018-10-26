package com.hotent.core.web.query.script.impl;

import com.hotent.core.web.query.entity.JudgeSingle;
import com.hotent.core.web.query.script.ISingleScript;

/**
 * 
 * String 类型的实现类
 * 
 * @author win
 * @Date
 * 
 */
public class StringScript implements ISingleScript {
	public String getSQL(JudgeSingle judgeSingle) {
		StringBuilder sb = new StringBuilder();
		if ("1".equals(judgeSingle.getCompare())) {//等于
			sb.append(judgeSingle.getFixFieldName()).append("=").append("'")
					.append(judgeSingle.getValue()).append("'");
		} else if ("2".equals(judgeSingle.getCompare())) {//不等于
			sb.append(judgeSingle.getFixFieldName()).append("!=").append("'")
					.append(judgeSingle.getValue()).append("'");
		} else if ("3".equals(judgeSingle.getCompare())) {//等于忽略大小写
			sb.append("UPPER(").append(judgeSingle.getFixFieldName()).append(") =")
					.append(" UPPER('").append(judgeSingle.getValue())
					.append("')");
		} else if ("4".equals(judgeSingle.getCompare())) {
			sb.append(judgeSingle.getFixFieldName()).append(" LIKE ").append("'%")
					.append(judgeSingle.getValue()).append("%'");
		} else if ("5".equals(judgeSingle.getCompare())) {
			sb.append(judgeSingle.getFixFieldName()).append(" LIKE ").append("'")
					.append(judgeSingle.getValue()).append("%'");
		} else if ("6".equals(judgeSingle.getCompare())) {
			sb.append(judgeSingle.getFixFieldName()).append(" LIKE ").append("'%")
					.append(judgeSingle.getValue()).append("'");
		}
		return sb.toString();
	}
}
