
package com.hotent.banking.dao.banking;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.banking.model.banking.Account1;

@Repository
public class Account1Dao extends BaseDao<Account1>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Account1.class;
	}
	
	public List<Account1> getUSERID(String USERID){
		return getBySqlKey("getUSERID", USERID);
	}

}
