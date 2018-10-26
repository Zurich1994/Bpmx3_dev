package com.hotent.tpcc.service.tpcc;
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
import com.hotent.tpcc.model.tpcc.Customer;
import com.hotent.tpcc.dao.tpcc.CustomerDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class CustomerService extends BaseService<Customer>
{
	@Resource
	private CustomerDao dao;
	
	public CustomerService()
	{
	}
	
	@Override
	protected IEntityDao<Customer,Long> getEntityDao() 
	{
		return dao;
	}
	
	public List<Customer> getcustomerMsg(String W_ID,String D_ID,String C_ID)
	{
		return dao.getcustomerMsg(W_ID,D_ID,C_ID);
	}
	public List<Customer> getcustomerMsg2(String W_ID,String D_ID,String C_ID)
	{
		return dao.getcustomerMsg2(W_ID,D_ID,C_ID);
	}
	public int count(String W_ID,String D_ID, String C_ID)
	{
		return dao.count(W_ID,D_ID,C_ID);
	}
	public List<Customer> get(String W_ID,String D_ID, String C_ID)
	{
		return dao.get(W_ID,D_ID,C_ID);
	}
	public List<Customer> getIdbyStoreIdAndAreaIdAndName(Long customStoreNumber,
			Long customAreaNumber, String customName) {
		return dao.getIdbyStoreIdAndAreaIdAndName(customStoreNumber,customAreaNumber,customName);
	}

	public int countByIdStoreIdAndAreaIdAndName(Long customStoreNumber,
			Long customAreaNumber, String customName) {
		return dao.countByIdStoreIdAndAreaIdAndName(customStoreNumber,customAreaNumber,customName);
	}

	public Customer getByStoreIdAndAreaIdAndId(Long customStoreNumber,
			Long customAreaNumber, Long customNumber) {
		return dao.getByStoreIdAndAreaIdAndId(customStoreNumber,customAreaNumber,customNumber);
	}

	public String getDataByStoreIdAndAreaIdAndId(Long customStoreNumber,
			Long customAreaNumber, Long customNumber) {
		return dao.getDataByStoreIdAndAreaIdAndId(customStoreNumber,customAreaNumber,customNumber);
	}

	public void updateBalanceAndDataByStoreIdAndAreaIdAndId(String data,
			Long orderValue, Long customStoreNumber, Long customAreaNumber,
			Long customNumber) {
		dao.updateBalanceAndDataByStoreIdAndAreaIdAndId(data,orderValue,customStoreNumber,customAreaNumber,customNumber);
	}

	public void updateBalanceByStoreIdAndAreaIdAndId(Long orderValue,
			Long customStoreNumber, Long customAreaNumber, Long customNumber) {
		dao.updateBalanceByStoreIdAndAreaIdAndId(orderValue,customStoreNumber,customAreaNumber,customNumber);
	}

	
	

	
}
