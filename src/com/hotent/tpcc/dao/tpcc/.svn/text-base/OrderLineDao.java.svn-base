
package com.hotent.tpcc.dao.tpcc;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.tpcc.model.tpcc.OrderLine;
import com.hotent.tpcc.model.tpcc.Stock;

@Repository
public class OrderLineDao extends BaseDao<OrderLine>
{
	@Override
	public Class<?> getEntityClass()
	{
		return OrderLine.class;
	}
	
	public OrderLine insertOrderline(OrderLine orderLine)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("OL_O_ID", orderLine.getOl_o_id());
		map.put("OL_D_ID", orderLine.getOl_d_id());
		map.put("OL_W_ID", orderLine.getOl_w_id());
		map.put("OL_NUMBER", orderLine.getOl_number());
		map.put("OL_I_ID", orderLine.getOl_i_id());
		map.put("OL_SUPPLY_W_ID", orderLine.getOl_supply_w_id());
		map.put("OL_QUANTITY", orderLine.getOl_quantity());
		map.put("OL_AMOUNT", orderLine.getOl_amount());
		map.put("OL_DIST_INFO", orderLine.getOl_dist_info());
		String statement = getIbatisMapperNamespace() + "." + "insertOrderline";
		return getSqlSessionTemplate().selectOne(statement,map);
	}
	
}
