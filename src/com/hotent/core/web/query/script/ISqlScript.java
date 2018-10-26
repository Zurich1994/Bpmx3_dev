package com.hotent.core.web.query.script;

import com.hotent.core.web.query.entity.JudgeScript;

/**
 * SQL条件脚本接口
 * 
 * @author zxh
 * 
 */
public interface ISqlScript {

	/**
	 * 获取脚本的SQL
	 * 
	 * @param judgeSingle
	 * @return
	 */
	public String getSQL(JudgeScript judgeScript);
}
