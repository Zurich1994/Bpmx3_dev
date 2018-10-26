
package com.hotent.monitorRecords.dao.monitorRecordsPac;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.monitorRecords.model.monitorRecordsPac.MonitorRecords;

@Repository
public class MonitorRecordsDao extends BaseDao<MonitorRecords>
{
	@Override
	public Class<?> getEntityClass()
	{
		return MonitorRecords.class;
	}

	public List<MonitorRecords> getBydeviceidAndquotaid(String deviceId, String quotaId,String startTime, String curTime,QueryFilter queryFilter) {
		//String statementName = getIbatisMapperNamespace() + ".getBydeviceidAndquotaid";
		Map map = new HashMap();
		map.put("deviceId", deviceId);
		map.put("quotaId", quotaId);
		map.put("startTime", startTime);
		map.put("curTime", curTime);
		queryFilter.getFilters().putAll(map);
		List<MonitorRecords> list = getBySqlKey("getBydeviceidAndquotaid", queryFilter);
		return list;
	}
	public List<MonitorRecords> getBydeviceidAndquotaid(String deviceId, String quotaId) {
		//String statementName = getIbatisMapperNamespace() + ".getBydeviceidAndquotaid";
		Map map = new HashMap();
		map.put("deviceId", deviceId);
		map.put("quotaId", quotaId);
		List<MonitorRecords> list = getBySqlKey("getBydeviceidAndquotaid2",map);
		return list;
	}
}
