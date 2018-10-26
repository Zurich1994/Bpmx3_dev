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
import com.hotent.tpcc.model.tpcc.OrderLine;
import com.hotent.tpcc.dao.tpcc.OrderLineDao;
import com.hotent.tpcc.model.tpcc.Stock;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class OrderLineService extends BaseService<OrderLine>
{
	@Resource
	private OrderLineDao dao;
	
	public OrderLineService()
	{
	}
	
	@Override
	protected IEntityDao<OrderLine,Long> getEntityDao() 
	{
		return dao;
	}
	public OrderLine insertOrderline(OrderLine orderLine)
	{
		return dao.insertOrderline(orderLine);
	} 
}
