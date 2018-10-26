package com.hotent.monitorQuota.service.monitorQuotaPac;
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
import com.hotent.monitorQuota.model.monitorQuotaPac.MonitorQuota;
import com.hotent.monitorQuota.dao.monitorQuotaPac.MonitorQuotaDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class MonitorQuotaService extends BaseService<MonitorQuota>
{
	@Resource
	private MonitorQuotaDao dao;
	
	public MonitorQuotaService()
	{
	}
	
	@Override
	protected IEntityDao<MonitorQuota,Long> getEntityDao() 
	{
		return dao;
	}
	
	public List<MonitorQuota> getByIds(Long ids[])
	{
		return dao.getByIds(ids);
	}
}
