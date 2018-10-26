package com.hotent.DeviceActionShowPath.service.DeviceActionShowCode;
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
import com.hotent.DeviceActionShowPath.model.DeviceActionShowCode.DeviceActionShow;
import com.hotent.DeviceActionShowPath.dao.DeviceActionShowCode.DeviceActionShowDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DeviceActionShowService extends BaseService<DeviceActionShow>
{
	@Resource
	private DeviceActionShowDao dao;
	
	public DeviceActionShowService()
	{
	}
	
	@Override
	protected IEntityDao<DeviceActionShow,Long> getEntityDao() 
	{
		return dao;
	}
	
}
