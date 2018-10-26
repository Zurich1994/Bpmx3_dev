package com.hotent.ServerSoftware.service.ServerSoftwarePac;
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
import com.hotent.ServerSoftware.model.ServerSoftwarePac.ServerSoftware;
import com.hotent.ServerSoftware.dao.ServerSoftwarePac.ServerSoftwareDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class ServerSoftwareService extends BaseService<ServerSoftware>
{
	@Resource
	private ServerSoftwareDao dao;
	
	public ServerSoftwareService()
	{
	}
	
	@Override
	protected IEntityDao<ServerSoftware,Long> getEntityDao() 
	{
		return dao;
	}
	
}
