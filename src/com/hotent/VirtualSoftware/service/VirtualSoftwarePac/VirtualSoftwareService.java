package com.hotent.VirtualSoftware.service.VirtualSoftwarePac;
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
import com.hotent.VirtualSoftware.model.VirtualSoftwarePac.VirtualSoftware;
import com.hotent.VirtualSoftware.dao.VirtualSoftwarePac.VirtualSoftwareDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class VirtualSoftwareService extends BaseService<VirtualSoftware>
{
	@Resource
	private VirtualSoftwareDao dao;
	
	public VirtualSoftwareService()
	{
	}
	
	@Override
	protected IEntityDao<VirtualSoftware,Long> getEntityDao() 
	{
		return dao;
	}
	
}