
package com.hotent.redirection.dao.redirection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.activityDetail.model.activityDetail.ActivityDetail;
import com.hotent.core.db.BaseDao;
import com.hotent.redirection.model.redirection.Redirection;

@Repository
public class RedirectionDao extends BaseDao<Redirection>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Redirection.class;
	}
	public Redirection search(Object redirection){
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("redirection", redirection);
		String statement = getIbatisMapperNamespace() + "." + "search";
		return getSqlSessionTemplate().selectOne(statement, map);
		
		
		
	}
	
	public void updateredirectionurl(Object redirection){
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("redirection", redirection);
		String statement = getIbatisMapperNamespace() + "." + "updateredirectionurl";
		getSqlSessionTemplate().update(statement);
		
	}
	public void delByIds(String defid,String nodeid){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("defid", defid);
		map.put("nodeid", nodeid);
		getUnique("delByIds", map);
    }
	public Redirection getByactDefId(String defID, String nodeID) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("defID", defID);
		params.put("nodeID", nodeID);
		List ls = getBySqlKey("getByactDefId", params);
		if(ls.isEmpty())
			return null;
		else
			return (Redirection)ls.get(0);
	}
	public int getByTwo(String defid, String nodeid) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("defID", defid);
		params.put("nodeID", nodeid);
		String result = (String) getOne("getByTwo", params);
		if(result == null)
		{
			return -1;
		}
		System.out.println("DaoResult:"+result);
		return Integer.parseInt(result);
	}

}
