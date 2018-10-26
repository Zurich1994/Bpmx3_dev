package com.hotent.monitorDevice.service.monitorDevice;
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
import com.hotent.monitorDevice.model.monitorDevice.MonitorDevice;
import com.hotent.monitorDevice.dao.monitorDevice.MonitorDeviceDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class MonitorDeviceService extends BaseService<MonitorDevice>
{
	@Resource
	private MonitorDeviceDao dao;
	
	public MonitorDeviceService()
	{
	}
	
	@Override
	protected IEntityDao<MonitorDevice,Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 根据主键Id获取IP列表
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getIPById(Long id)
	{
		return dao.getIPById(id);
	}

	public List<String> getQuotaIDSById(Long id) {
		
		
		return dao.getQuotaIDSById(id);
	}
	
	public String getOIDById(Long id) {
		
		return dao.getOIDById(id);
	}
}
