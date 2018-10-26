package com.hotent.eventgraphrelation.service.eventgraphrelation;
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
import com.hotent.eventgraphrelation.model.eventgraphrelation.Eventgraphrelation;
import com.hotent.eventgraphrelation.dao.eventgraphrelation.EventgraphrelationDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class EventgraphrelationService extends BaseService<Eventgraphrelation>
{
	@Resource
	private EventgraphrelationDao dao;
	
	public EventgraphrelationService()
	{
	}
	
	@Override
	protected IEntityDao<Eventgraphrelation,Long> getEntityDao() 
	{
		return dao;
	}
	//zl根据事件ID获取事件图关联关系
	public Eventgraphrelation getByEventId(String eventID)
	{
		return dao.getByEventId(eventID);
	}
}
