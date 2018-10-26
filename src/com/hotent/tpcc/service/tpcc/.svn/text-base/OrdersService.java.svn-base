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
import com.hotent.tpcc.model.tpcc.Orders;
import com.hotent.tpcc.dao.tpcc.OrdersDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class OrdersService extends BaseService<Orders>
{
	@Resource
	private OrdersDao dao;
	
	public OrdersService()
	{
	}
	
	@Override
	protected IEntityDao<Orders,Long> getEntityDao() 
	{
		return dao;
	}
	
	public Orders makeorders(String D_NEXT_O_ID,String  C_D_ID,String  C_W_ID,String C_ID)
	{
		return dao.makeorders(D_NEXT_O_ID,C_D_ID,C_W_ID,C_ID);
	}
	public Orders searchorder(String D_ID,String  W_ID,String  C_ID)
	{
		return dao.searchorder(D_ID,W_ID,C_ID);
	}
	public Orders cxddmx(String D_ID,String  W_ID,String  O_ID)
	{
		return dao.cxddmx(D_ID,W_ID,O_ID);
	}
	public Orders chaxunmin(String D_ID,String  W_ID)
	{
		return dao.chaxunmin(D_ID,W_ID);
	}

	public Orders chaxunzuijin()
	{
		return dao.chaxunzuijin();
	}

	public Orders cxkhh() {
		// TODO Auto-generated method stub
		return null;
		
	}

}
