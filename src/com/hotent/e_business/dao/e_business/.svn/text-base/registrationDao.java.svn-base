
package com.hotent.e_business.dao.e_business;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.e_business.model.e_business.UserInfo;
import com.hotent.e_business.model.e_business.registration;

@Repository
public class registrationDao extends BaseDao<registration>
{
	@Override
	public Class<?> getEntityClass()
	{
		return registration.class;
	}
	public registration save(String EMAIL)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("EMAIL",EMAIL);
		
		//map.put("O_ID",O_ID);
		String statement = getIbatisMapperNamespace() + "." + "getByEMAIL";
		registration ob=getSqlSessionTemplate().selectOne(statement,map);
		return ob;
	}
}

