
package com.hotent.banking.dao.banking;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.banking.model.banking.Account;

@Repository
public class AccountDao extends BaseDao<Account>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Account.class;
	}
	public void transfer1(String nowaccount,int amount )
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		System.out.println("执行到dao层：");
		map.put("nowaccount", nowaccount);
		map.put("amount", amount);
		update("transfer1", map);
	
	}
	public void transfer2(String toaccount,int amount )
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("toaccount", toaccount);
		map.put("amount", amount);
		update("transfer2", map);
		
		
	}
	public void update2(double balance,double accountno) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("balance", balance);
		map.put("accountno", accountno);
		System.out.println("dao:"+ balance);
		update("update2", map);
	}
	public Account getByAccountno(double nowaccount) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("nowblance", nowaccount);
		return getUnique("getByAccountno", nowaccount);
	}

}
