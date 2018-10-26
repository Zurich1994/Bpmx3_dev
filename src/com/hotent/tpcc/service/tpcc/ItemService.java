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
import com.hotent.tpcc.model.tpcc.Item;
import com.hotent.tpcc.dao.tpcc.ItemDao;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.tpcc.model.tpcc.Customer;


@Service
public class ItemService extends BaseService<Item>
{
	@Resource
	private ItemDao dao;
	
	public ItemService()
	{
	}
	
	@Override
	protected IEntityDao<Item,Long> getEntityDao() 
	{
		return dao;
	}
		
	public Item queryItem(String I_ID)
	{
		return dao.queryItem(I_ID);
	}
	
	
}
