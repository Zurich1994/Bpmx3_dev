package com.hotent.monitorRecords.service.monitorRecordsPac;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;

import com.hotent.core.service.GenericService;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.monitorRecords.model.monitorRecordsPac.MonitorRecords;
import com.hotent.monitorRecords.dao.monitorRecordsPac.MonitorRecordsDao;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;


@Service
public class MonitorRecordsService extends BaseService<MonitorRecords>
{
	@Resource
	private MonitorRecordsDao dao;
	
	public MonitorRecordsService()
	{
	}
	
	@Override
	protected IEntityDao<MonitorRecords,Long> getEntityDao() 
	{
		return dao;
	}
	
	public List<MonitorRecords> getBydeviceidAndquotaid(String deviceId, String quotaId, String startTime, String curTime,QueryFilter queryFilter) {
		return dao.getBydeviceidAndquotaid(deviceId, quotaId, startTime, curTime,queryFilter);
	}
	public List<MonitorRecords> getBydeviceidAndquotaid(String deviceId, String quotaId) {
		return dao.getBydeviceidAndquotaid(deviceId, quotaId);
	}
}
