package com.hotent.banking.service.banking;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;

import com.hotent.core.service.GenericService;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.banking.model.banking.Account;
import com.hotent.banking.dao.banking.AccountDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class AccountService extends BaseService<Account>
{
	@Resource
	private AccountDao dao;
	
	public AccountService()
	{
	}
	
	@Override
	protected IEntityDao<Account,Long> getEntityDao() 
	{
		return dao;
	}
	
	public void transfer1(String nowaccount,int amount) {
		// TODO Auto-generated method stub
		dao.transfer1(nowaccount, amount);
		
	}

	public void transfer2(String toaccount,int amount) {
		
		// TODO Auto-generated method stub
		dao.transfer2(toaccount, amount);
	}

	public void update2(double balance,double accountno) {
		// TODO Auto-generated method stub
		dao.update2(balance,accountno);
	}

	public Account getByAccountno(double nowaccount) {
		// TODO Auto-generated method stub
		return dao.getByAccountno(nowaccount);
	}

	
}