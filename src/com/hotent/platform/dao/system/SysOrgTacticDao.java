package com.hotent.platform.dao.system;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysOrgTactic;
/**
 *<pre>
 * 对象功能:组织策略 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-31 13:43:14
 *</pre>
 */
@SuppressWarnings("unchecked")
@Repository
public class SysOrgTacticDao extends BaseDao<SysOrgTactic>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysOrgTactic.class;
	}

	
	
	
	
}