
package com.hotent.e_business.dao.e_business;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.e_business.model.e_business.UserInfo;

@Repository
public class UserInfoDao extends BaseDao<UserInfo>
{
	@Override
	public Class<?> getEntityClass()
	{
		return UserInfo.class;
	}
	public UserInfo match(String EMAIL,String PASSWORD)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("EMAIL",EMAIL);
		map.put("PASSWORD",PASSWORD);
		//map.put("O_ID",O_ID);
		String statement = getIbatisMapperNamespace() + "." + "getByEMAIL";
		UserInfo ob=getSqlSessionTemplate().selectOne(statement,map);
		return ob;
	}
}
