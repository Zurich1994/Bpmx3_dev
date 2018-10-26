
package com.hotent.monitorQuota.dao.monitorQuotaPac;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.util.BeanUtils;
import com.hotent.monitorQuota.model.monitorQuotaPac.MonitorQuota;

@Repository
public class MonitorQuotaDao extends BaseDao<MonitorQuota>
{
	@Override
	public Class<?> getEntityClass()
	{
		return MonitorQuota.class;
	}

	public List<MonitorQuota> getByIds(Long ids[])
	{
		if(BeanUtils.isEmpty(ids)) return null;
		List<MonitorQuota> list = new ArrayList<MonitorQuota>();
		for (Long p : ids){
			list.add(getById(p));
		}
		return list;
	}
}
