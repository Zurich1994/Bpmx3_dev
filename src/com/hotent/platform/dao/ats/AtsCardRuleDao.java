package com.hotent.platform.dao.ats;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.ats.AtsCardRule;
/**
 *<pre>
 * 对象功能:取卡规则 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-18 16:16:16
 *</pre>
 */
@Repository
public class AtsCardRuleDao extends BaseDao<AtsCardRule>
{
	@Override
	public Class<?> getEntityClass()
	{
		return AtsCardRule.class;
	}

	
	
	
	
}