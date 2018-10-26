
package com.hotent.tpcc.dao.tpcc;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.tpcc.model.tpcc.Customer;
import com.hotent.tpcc.model.tpcc.Item;

@Repository
public class ItemDao extends BaseDao<Item>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Item.class;
	}
	
	public Item queryItem(String I_ID)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("I_ID", I_ID);
		String statement = getIbatisMapperNamespace() + "." + "queryItem";
		return getSqlSessionTemplate().selectOne(statement,map);
	}

}
