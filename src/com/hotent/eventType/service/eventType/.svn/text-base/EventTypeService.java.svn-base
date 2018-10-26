package com.hotent.eventType.service.eventType;
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
import com.hotent.eventType.model.eventType.EventType;
import com.hotent.eventType.dao.eventType.EventTypeDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class EventTypeService extends BaseService<EventType>
{
	@Resource
	private EventTypeDao dao;
	
	public EventTypeService()
	{
	}
	
	@Override
	protected IEntityDao<EventType,Long> getEntityDao() 
	{
		return dao;
	}
	
}
