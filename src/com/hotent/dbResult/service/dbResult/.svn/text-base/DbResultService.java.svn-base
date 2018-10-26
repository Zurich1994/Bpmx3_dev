package com.hotent.dbResult.service.dbResult;
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
import com.hotent.dbResult.model.dbResult.DbResult;
import com.hotent.dbResult.dao.dbResult.DbResultDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DbResultService extends BaseService<DbResult>
{
	@Resource
	private DbResultDao dao;
	
	public DbResultService()
	{
	}
	
	@Override
	protected IEntityDao<DbResult,Long> getEntityDao() 
	{
		return dao;
	}
	
}
