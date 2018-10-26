
package com.hotent.tpcc.dao.tpcc;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.tpcc.model.tpcc.OrderLine;
import com.hotent.tpcc.model.tpcc.Orders;

@Repository
public class OrdersDao extends BaseDao<Orders>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Orders.class;
	}

	public Orders makeorders(String D_NEXT_O_ID,String  C_D_ID,String  C_W_ID,String C_ID)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("D_NEXT_O_ID",D_NEXT_O_ID);
		map.put("C_D_ID",C_D_ID);
		map.put("C_W_ID",C_W_ID);
		map.put("C_ID",C_ID);
		String statement = getIbatisMapperNamespace() + "." + "makeorders";
		return getSqlSessionTemplate().selectOne(statement,map);
	}
	public Orders searchorder(String D_ID,String  W_ID,String  C_ID)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("D_ID",D_ID);
		map.put("W_ID",W_ID);
		map.put("C_ID",C_ID);
		String statement = getIbatisMapperNamespace() + "." + "searchorder";
		Orders orders = getSqlSessionTemplate().selectOne(statement,map);
		if(orders.getO_carrier_id()==null)
			orders.setO_carrier_id(0L);
		return orders;
	}
	public Orders cxddmx(String D_ID,String  W_ID,String  O_ID)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("D_ID",D_ID);
		map.put("W_ID",W_ID);
		map.put("O_ID",O_ID);
		String statement = getIbatisMapperNamespace() + "." + "cxddmx";
		Orders ob=getSqlSessionTemplate().selectOne(statement,map);
		return ob;
	}
	public Orders chaxunmin(String D_ID,String  W_ID)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("D_ID",D_ID);
		map.put("W_ID",W_ID);
		String statement = getIbatisMapperNamespace() + "." + "chaxunmin";	
		return getSqlSessionTemplate().selectOne(statement,map);
	}

	public Orders chaxunzuijin() {
		// TODO Auto-generated method stub
		return null;
	}
}
