
package com.hotent.warehouse.dao.warehouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.warehouse.model.warehouse.Warehouse;

@Repository
public class WarehouseDao extends BaseDao<Warehouse>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Warehouse.class;
	}

	public void updataWare(Long orderValue, Long stockStoreNumber) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("orderValue", orderValue);
		params.put("stockStoreNumber", stockStoreNumber);

		update("updataWare", params);
	}

}
