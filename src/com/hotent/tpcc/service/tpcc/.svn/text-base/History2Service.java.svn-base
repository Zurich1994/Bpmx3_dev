package com.hotent.tpcc.service.tpcc;
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
import com.hotent.tpcc.model.tpcc.History2;
import com.hotent.tpcc.dao.tpcc.History2Dao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class History2Service extends BaseService<History2>
{
	@Resource
	private History2Dao dao;
	
	public History2Service()
	{
	}
	
	@Override
	protected IEntityDao<History2,Long> getEntityDao() 
	{
		return dao;
	}
	public void addHistory(History2 history)
	{
		dao.addHistory(history);
	}
}
