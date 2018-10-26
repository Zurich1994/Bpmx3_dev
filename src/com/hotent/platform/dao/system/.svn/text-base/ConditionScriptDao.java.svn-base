package com.hotent.platform.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.ConditionScript;
/**
 *<pre>
 * 对象功能:系统条件脚本 Dao类
 * 开发公司:hotent
 * 开发人员:heyifan
 * 创建时间:2013-04-05 11:34:56
 *</pre>
 */
@Repository
public class ConditionScriptDao extends BaseDao<ConditionScript>
{
	@Override
	public Class<?> getEntityClass()
	{
		return ConditionScript.class;
	}

	public List<ConditionScript> getConditionScript(){
		return this.getBySqlKey("getConditionScript");
	}
}