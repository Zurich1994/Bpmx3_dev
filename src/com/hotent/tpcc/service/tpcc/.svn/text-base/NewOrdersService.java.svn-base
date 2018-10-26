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
import com.hotent.tpcc.model.tpcc.NewOrders;
import com.hotent.tpcc.dao.tpcc.NewOrdersDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class NewOrdersService extends BaseService<NewOrders>
{
	@Resource
	private NewOrdersDao dao;
	
	public NewOrdersService()
	{
	}
	
	@Override
	protected IEntityDao<NewOrders,Long> getEntityDao() 
	{
		return dao;
	}
	
	public NewOrders makeneworders(String D_NEXT_O_ID,String C_D_ID,String C_W_ID)
	{
		return dao.makeneworders(D_NEXT_O_ID,C_D_ID,C_W_ID);
		
	};
	
}
