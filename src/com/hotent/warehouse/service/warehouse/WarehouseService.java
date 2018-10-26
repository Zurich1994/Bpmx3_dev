package com.hotent.warehouse.service.warehouse;
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
import com.hotent.warehouse.model.warehouse.Warehouse;
import com.hotent.warehouse.dao.warehouse.WarehouseDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class WarehouseService extends BaseService<Warehouse>
{
	@Resource
	private WarehouseDao dao;
	
	public WarehouseService()
	{
	}
	
	@Override
	protected IEntityDao<Warehouse,Long> getEntityDao() 
	{
		return dao;
	}

	public void updateWare(Long orderValue, Long stockStoreNumber) {
		// TODO Auto-generated method stub
		dao.updataWare(orderValue,stockStoreNumber);
	}
	
}
