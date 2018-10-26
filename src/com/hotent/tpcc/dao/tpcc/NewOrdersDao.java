
package com.hotent.tpcc.dao.tpcc;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.tpcc.model.tpcc.NewOrders;

@Repository
public class NewOrdersDao extends BaseDao<NewOrders>
{
	@Override
	public Class<?> getEntityClass()
	{
		return NewOrders.class;
	}

	public NewOrders makeneworders(String D_NEXT_O_ID,String C_D_ID,String C_W_ID) {
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("D_NEXT_O_ID", D_NEXT_O_ID);
		map.put("C_D_ID", C_D_ID);
		map.put("C_W_ID", C_W_ID);
		String statement = getIbatisMapperNamespace() + "." + "makeneworders";
		return getSqlSessionTemplate().selectOne(statement,map);
	}
}

