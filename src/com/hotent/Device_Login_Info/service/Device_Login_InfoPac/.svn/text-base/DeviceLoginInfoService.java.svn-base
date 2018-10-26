package com.hotent.Device_Login_Info.service.Device_Login_InfoPac;
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
import com.hotent.Device_Login_Info.model.Device_Login_InfoPac.DeviceLoginInfo;
import com.hotent.Device_Login_Info.dao.Device_Login_InfoPac.DeviceLoginInfoDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DeviceLoginInfoService extends BaseService<DeviceLoginInfo>
{
	@Resource
	private DeviceLoginInfoDao dao;
	
	public DeviceLoginInfoService()
	{
	}
	
	@Override
	protected IEntityDao<DeviceLoginInfo,Long> getEntityDao() 
	{
		return dao;
	}
	
}
