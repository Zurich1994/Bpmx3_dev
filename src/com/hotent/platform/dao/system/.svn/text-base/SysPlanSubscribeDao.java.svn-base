package com.hotent.platform.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysPlanSubscribe;
/**
 *<pre>
 * 对象功能:日程订阅表 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-12-19 11:26:03
 *</pre>
 */
@Repository
public class SysPlanSubscribeDao extends BaseDao<SysPlanSubscribe>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysPlanSubscribe.class;
	}

	
	public List<SysPlanSubscribe> getByPlanId(Long planId){		
		return this.getBySqlKey("getByPlanId", planId);
	}
	
	public void delByPlanId(Long planId){		
		this.delBySqlKey("delByPlanId", planId);
	}
}