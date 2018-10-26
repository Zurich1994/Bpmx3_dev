package com.hotent.dbMap.service.dbMap;
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
import com.hotent.dbMap.model.dbMap.DbMap;
import com.hotent.dbMap.dao.dbMap.DbMapDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DbMapService extends BaseService<DbMap>
{
	@Resource
	private DbMapDao dao;
	
	public DbMapService()
	{
	}
	
	@Override
	protected IEntityDao<DbMap,Long> getEntityDao() 
	{
		return dao;
	}
	
}
