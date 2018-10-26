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
import com.hotent.tpcc.model.tpcc.Stock;
import com.hotent.tpcc.dao.tpcc.StockDao;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.tpcc.model.tpcc.Item;


@Service
public class StockService extends BaseService<Stock>
{
	@Resource
	private StockDao dao;
	
	public StockService()
	{
	}
	
	@Override
	protected IEntityDao<Stock,Long> getEntityDao() 
	{
		return dao;
	}
	
	public Stock queryStock(String I_ID,String S_W_ID)
	{
		return dao.queryStock(I_ID,S_W_ID);
	}
	public Stock updateStock(String I_ID,String S_W_ID,Long S_QUANTITY,Long AMOUNT)
	{
		return dao.updateStock(I_ID,S_W_ID,S_QUANTITY,AMOUNT);
	}
	
	
}
