package com.hotent.platform.dao.ats;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.ats.AtsAttenceCycle;
/**
 *<pre>
 * 对象功能:考勤周期 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 22:03:30
 *</pre>
 */
@Repository
public class AtsAttenceCycleDao extends BaseDao<AtsAttenceCycle>
{
	@Override
	public Class<?> getEntityClass()
	{
		return AtsAttenceCycle.class;
	}

	
	
	
	
}