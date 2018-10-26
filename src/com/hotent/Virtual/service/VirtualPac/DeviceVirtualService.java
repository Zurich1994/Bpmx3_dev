package com.hotent.Virtual.service.VirtualPac;
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
import com.hotent.Virtual.model.VirtualPac.DeviceVirtual;
import com.hotent.Virtual.dao.VirtualPac.DeviceVirtualDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DeviceVirtualService extends BaseService<DeviceVirtual>
{
	@Resource
	private DeviceVirtualDao dao;
	
	public DeviceVirtualService()
	{
	}
	
	@Override
	protected IEntityDao<DeviceVirtual,Long> getEntityDao() 
	{
		return dao;
	}
	
}
