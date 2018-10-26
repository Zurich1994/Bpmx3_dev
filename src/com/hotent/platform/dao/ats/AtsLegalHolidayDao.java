package com.hotent.platform.dao.ats;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.ats.AtsLegalHoliday;
/**
 *<pre>
 * 对象功能:法定节假日 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 15:48:22
 *</pre>
 */
@Repository
public class AtsLegalHolidayDao extends BaseDao<AtsLegalHoliday>
{
	@Override
	public Class<?> getEntityClass()
	{
		return AtsLegalHoliday.class;
	}

	
	
	
	
}