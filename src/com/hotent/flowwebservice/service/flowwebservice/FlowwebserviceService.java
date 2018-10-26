package com.hotent.flowwebservice.service.flowwebservice;
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
import com.hotent.flowwebservice.model.flowwebservice.Flowwebservice;
import com.hotent.flowwebservice.dao.flowwebservice.FlowwebserviceDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class FlowwebserviceService extends BaseService<Flowwebservice>
{
	@Resource
	private FlowwebserviceDao dao;
	
	public FlowwebserviceService()
	{
	}
	
	@Override
	protected IEntityDao<Flowwebservice,Long> getEntityDao() 
	{
		return dao;
	}
	
}
