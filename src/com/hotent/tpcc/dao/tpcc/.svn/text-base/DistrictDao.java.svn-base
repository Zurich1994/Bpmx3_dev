
package com.hotent.tpcc.dao.tpcc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.tpcc.model.tpcc.Customer;
import com.hotent.tpcc.model.tpcc.District;

@Repository
public class DistrictDao extends BaseDao<District>
{
	private static final Object C_ID = null;

	@Override
	public Class<?> getEntityClass()
	{
		return District.class;
	}
	public District orderno(String W_ID,String D_ID)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("W_ID", W_ID);
		map.put("D_ID", D_ID);
		String statement = getIbatisMapperNamespace() + "." + "orderno";
		return getSqlSessionTemplate().selectOne(statement,map);
	}
	public Integer itemno(String W_ID,String D_ID,String D_NEXT_O_ID,String D_LEVEL)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("D_ID", Integer.parseInt(D_ID));
		map.put("W_ID", Integer.parseInt(W_ID));
		map.put("D_NEXT_O_ID", Integer.parseInt(D_NEXT_O_ID));
		map.put("D_NEXT_O_ID1", Integer.parseInt(D_NEXT_O_ID)-20);
		map.put("D_LEVEL",Integer.parseInt(D_LEVEL));
		Object ob = this.getOne("itemno", map);
		return (Integer)ob;
	}

	public List<District> getorderMsg(String W_ID,String D_ID)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("W_ID", W_ID);
		map.put("D_ID", D_ID);
		String statement = getIbatisMapperNamespace() + "." + "getorderMsg";
		return getSqlSessionTemplate().selectList(statement, map);
	}
	public List<District> getorderMsg2(String D_ID,String W_ID)
	{
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("D_ID", D_ID);
		map.put("W_ID", W_ID);
		String statement = getIbatisMapperNamespace() + "." + "getorderMsg2";
		return getSqlSessionTemplate().selectList(statement, map);
	}
	public void updateDis(Long stockStoreNumber, Long stockAreaNumber,
			Long orderValue) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("orderValue", orderValue);
		params.put("stockStoreNumber", stockStoreNumber);
		params.put("stockAreaNumber", stockAreaNumber);

		update("updateDis", params);
	}

	public District getByStoreIdAndAreaId(Long stockAreaNumber,
			Long stockStoreNumber) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("stockAreaNumber", stockAreaNumber);
		params.put("stockStoreNumber", stockStoreNumber);
		return (District)getOne("getByStoreIdAndAreaId",params);
	}
}
