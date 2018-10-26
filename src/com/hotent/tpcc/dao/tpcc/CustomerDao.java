
package com.hotent.tpcc.dao.tpcc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.tpcc.model.tpcc.Customer;

@Repository
public class CustomerDao extends BaseDao<Customer>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Customer.class;
	}

	public List<Customer> getcustomerMsg(String W_ID,String D_ID,String C_ID)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("W_ID", W_ID);
		map.put("D_ID", D_ID);
		map.put("C_ID", C_ID);
		String statement = getIbatisMapperNamespace() + "." + "getcustomerMsg";
		return getSqlSessionTemplate().selectList(statement,map);
	}
	public List<Customer> getcustomerMsg2(String W_ID,String D_ID,String C_ID)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("W_ID", W_ID);
		map.put("D_ID", D_ID);
		map.put("C_ID", C_ID);
		String statement = getIbatisMapperNamespace() + "." + "getcustomerMsg2";
		return getSqlSessionTemplate().selectList(statement,map);
	}
	public int count(String W_ID,String D_ID, String C_ID)
	{
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("W_ID", W_ID);
		map.put("D_ID", D_ID);
		map.put("C_ID", C_ID);

		return (Integer)getOne("count", map);
	}
	public List<Customer> get(String W_ID, String D_ID, String C_ID) 
	{
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("W_ID", W_ID);
		map.put("D_ID", D_ID);
		map.put("C_ID", C_ID);
		String statement = getIbatisMapperNamespace() + "." + "get";
		return getSqlSessionTemplate().selectList(statement,map);
	}

	
	public List<Customer> getIdbyStoreIdAndAreaIdAndName(
			Long customStoreNumber, Long customAreaNumber, String customName) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("customStoreNumber", customStoreNumber);
		params.put("customAreaNumber", customAreaNumber);
		params.put("customName", customName);

		return getBySqlKey("getIdbyStoreIdAndAreaIdAndName", params);
	}

	public int countByIdStoreIdAndAreaIdAndName(Long customStoreNumber,
			Long customAreaNumber, String customName) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("customStoreNumber", customStoreNumber);
		params.put("customAreaNumber", customAreaNumber);
		params.put("customName", customName);

		return (Integer)getOne("countByIdStoreIdAndAreaIdAndName", params);
	}

	public Customer getByStoreIdAndAreaIdAndId(Long customStoreNumber,
			Long customAreaNumber, Long customNumber) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("customStoreNumber", customStoreNumber);
		params.put("customAreaNumber", customAreaNumber);
		params.put("customNumber", customNumber);
		return getUnique("getByStoreIdAndAreaIdAndId", params);
	}

	public String getDataByStoreIdAndAreaIdAndId(Long customStoreNumber,
			Long customAreaNumber, Long customNumber) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("customStoreNumber", customStoreNumber);
		params.put("customAreaNumber", customAreaNumber);
		params.put("customNumber", customNumber);

		return (String)getOne("getDataByStoreIdAndAreaIdAndId", params);
	}

	public void updateBalanceAndDataByStoreIdAndAreaIdAndId(String data,
			Long orderValue, Long customStoreNumber, Long customAreaNumber,
			Long customNumber) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("customStoreNumber", customStoreNumber);
		params.put("customAreaNumber", customAreaNumber);
		params.put("customNumber", customNumber);
		params.put("orderValue", orderValue);
		params.put("data", data);

		update("updateBalanceAndDataByStoreIdAndAreaIdAndId", params);
	}

	public void updateBalanceByStoreIdAndAreaIdAndId(Long orderValue,
			Long customStoreNumber, Long customAreaNumber, Long customNumber) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("customStoreNumber", customStoreNumber);
		params.put("customAreaNumber", customAreaNumber);
		params.put("customNumber", customNumber);
		params.put("orderValue", orderValue);

		update("updateBalanceByStoreIdAndAreaIdAndId", params);
	}

}
