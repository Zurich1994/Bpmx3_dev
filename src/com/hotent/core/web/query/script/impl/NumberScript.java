package com.hotent.core.web.query.script.impl;

import com.hotent.core.web.query.entity.JudgeSingle;
import com.hotent.core.web.query.script.ISingleScript;

/**
 * 数值类型的脚本
 * 
 * @author zxh
 * 
 */
public class NumberScript implements ISingleScript {

	public String getSQL(JudgeSingle judgeSingle) {
		StringBuilder sb = new StringBuilder();
		if ("1".equals(judgeSingle.getCompare())) {
			sb.append(judgeSingle.getFixFieldName()).append("=")
					.append(judgeSingle.getValue());
		} else if ("2".equals(judgeSingle.getCompare())) {
			sb.append(judgeSingle.getFixFieldName()).append("!=")
					.append(judgeSingle.getValue());
		} else if ("3".equals(judgeSingle.getCompare())) {
			sb.append(judgeSingle.getFixFieldName()).append(">")
					.append(judgeSingle.getValue());
		} else if ("4".equals(judgeSingle.getCompare())) {
			sb.append(judgeSingle.getFixFieldName()).append(">=")
					.append(judgeSingle.getValue());
		} else if ("5".equals(judgeSingle.getCompare())) {
			sb.append(judgeSingle.getFixFieldName()).append("<")
					.append(judgeSingle.getValue());
		} else if ("6".equals(judgeSingle.getCompare())) {
			sb.append(judgeSingle.getFixFieldName()).append("<=")
					.append(judgeSingle.getValue());
		}
		return sb.toString();
	}

}
