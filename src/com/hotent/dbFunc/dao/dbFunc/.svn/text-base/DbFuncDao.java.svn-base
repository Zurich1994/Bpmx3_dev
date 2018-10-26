
package com.hotent.dbFunc.dao.dbFunc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.dbFunc.model.dbFunc.DbFunc;

@Repository
public class DbFuncDao extends BaseDao<DbFunc>
{
	@Override
	public Class<?> getEntityClass()
	{
		return DbFunc.class;
	}

	public List<DbFunc> getByTableName(String table_name) {
		// TODO Auto-generated method stub
		return getBySqlKey("getByTableName", table_name);
	}
	
	public  DbFunc getByTableNameandFuncName(String table_name, String func_name) 
	{
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();	
		params.put("table_name", table_name);
		params.put("func_name", func_name);
		return getUnique("getByTableNameandFuncName", params);
	}	
	
	public String getResults(String namespace, String method) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("namespace", namespace);
		params.put("method", method);
		return (String)getOne("getResult", params);
	}

	public String getCondition(String namespace, String method) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("namespace", namespace);
		params.put("method", method);
		return (String)getOne("getCondition", params);
	}

	public String getSort(String namespace, String method) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("namespace", namespace);
		params.put("method", method);
		return (String)getOne("getSort", params);
	}

	public String getSetting(String namespace, String method) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("namespace", namespace);
		params.put("method", method);
		return (String)getOne("getSetting", params);
	}

	public String getDisplay(String namespace, String method) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("namespace", namespace);
		params.put("method", method);
		return (String)getOne("getDisplay", params);
	}
	
	
	public  String getType(String namespace, String method) 
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("namespace", namespace);
		params.put("method", method);
		return (String)getOne("getType", params);
	}	
}
