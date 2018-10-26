package com.hotent.platform.dao.ats;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.ats.AtsHolidayPolicy;
/**
 *<pre>
 * 对象功能:假期制度 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-19 17:42:20
 *</pre>
 */
@Repository
public class AtsHolidayPolicyDao extends BaseDao<AtsHolidayPolicy>
{
	@Override
	public Class<?> getEntityClass()
	{
		return AtsHolidayPolicy.class;
	}

	public AtsHolidayPolicy getByDefault() {
		List<AtsHolidayPolicy> list =  this.getBySqlKey("getByDefault");
		if(BeanUtils.isNotEmpty(list))
			return list.get(0);
		return null;
	}

	public AtsHolidayPolicy getByName(String name) {
		List<AtsHolidayPolicy> list =  this.getBySqlKey("getByName",name);
		if(BeanUtils.isNotEmpty(list))
			return list.get(0);
		return null;
	}

	
	
	
	
}