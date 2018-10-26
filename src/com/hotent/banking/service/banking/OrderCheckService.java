package com.hotent.banking.service.banking;
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
import com.hotent.banking.model.banking.OrderCheck;
import com.hotent.banking.dao.banking.OrderCheckDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class OrderCheckService extends BaseService<OrderCheck>
{
	@Resource
	private OrderCheckDao dao;
	
	public OrderCheckService()
	{
	}
	
	@Override
	protected IEntityDao<OrderCheck,Long> getEntityDao() 
	{
		return dao;
	}
	
}
