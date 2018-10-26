
package com.hotent.e_business.dao.e_business;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.e_business.model.e_business.OrderShipping;

@Repository
public class OrderShippingDao extends BaseDao<OrderShipping>
{
	@Override
	public Class<?> getEntityClass()
	{
		return OrderShipping.class;
	}
	public List<OrderShipping> getByEMAIL(String EMAIL){
		return  getBySqlKey("getByEMAIL", EMAIL);
	}

}
