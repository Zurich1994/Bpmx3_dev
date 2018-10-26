
package com.hotent.tpcc.dao.tpcc;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.tpcc.model.tpcc.Item;
import com.hotent.tpcc.model.tpcc.Stock;

@Repository
public class StockDao extends BaseDao<Stock>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Stock.class;
	}
	public Stock queryStock(String I_ID,String S_W_ID)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("I_ID", I_ID);
		map.put("S_W_ID", S_W_ID);
		String statement = getIbatisMapperNamespace() + "." + "queryStock";
		return getSqlSessionTemplate().selectOne(statement,map);
	}
	
	public Stock updateStock(String I_ID,String S_W_ID,Long S_QUANTITY,Long AMOUNT)
	{
		Long S_QUANTITY2= Long.valueOf(S_QUANTITY-AMOUNT);
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("I_ID", I_ID);
		map.put("S_W_ID", S_W_ID);
		map.put("S_QUANTITY2", S_QUANTITY2);
		String statement = getIbatisMapperNamespace() + "." + "updateStock";
		return getSqlSessionTemplate().selectOne(statement,map);
	}

}
