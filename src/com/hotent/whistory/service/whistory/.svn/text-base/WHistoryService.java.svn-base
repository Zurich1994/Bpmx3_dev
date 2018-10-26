package com.hotent.whistory.service.whistory;
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
import com.hotent.whistory.model.whistory.WHistory;
import com.hotent.whistory.dao.whistory.WHistoryDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class WHistoryService extends BaseService<WHistory>
{
	@Resource
	private WHistoryDao dao;
	
	public WHistoryService()
	{
	}
	
	@Override
	protected IEntityDao<WHistory,Long> getEntityDao() 
	{
		return dao;
	}
	
}
