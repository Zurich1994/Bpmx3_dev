
package com.hotent.banking.dao.banking;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.banking.model.banking.add_payee;

@Repository
public class add_payeeDao extends BaseDao<add_payee>
{
	@Override
	public Class<?> getEntityClass()
	{
		return add_payee.class;
	}
    public add_payee getByUSERID(String USERID){
    	HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("USERID", USERID);
		return getUnique("getByUSERID", map);
    }
}
