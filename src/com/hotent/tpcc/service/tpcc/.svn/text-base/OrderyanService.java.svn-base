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
import com.hotent.tpcc.model.tpcc.Orderyan;
import com.hotent.tpcc.dao.tpcc.OrderyanDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class OrderyanService extends BaseService<Orderyan>
{
	@Resource
	private OrderyanDao dao;
	
	public OrderyanService()
	{
	}
	
	@Override
	protected IEntityDao<Orderyan,Long> getEntityDao() 
	{
		return dao;
	}
	
}
