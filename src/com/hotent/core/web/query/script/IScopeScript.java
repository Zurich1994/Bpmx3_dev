package com.hotent.core.web.query.script;

import com.hotent.core.web.query.entity.JudgeScope;

/**
 * 范围值条件脚本接口
 * 
 * @author zxh
 * 
 */
public interface IScopeScript {

	/**
	 * 获取脚本的SQL
	 * @param judgeScope
	 * @return
	 */
	public String getSQL(JudgeScope judgeScope);
}
