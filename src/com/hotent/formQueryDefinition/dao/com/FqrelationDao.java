
package com.hotent.formQueryDefinition.dao.com;



import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.formQueryDefinition.model.com.Fqrelation;

@Repository
@SuppressWarnings("unchecked")
public class FqrelationDao extends BaseDao<Fqrelation>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Fqrelation.class;
	}
	
	public List<Fqrelation> getQueryMsg(String nodeId,String defId)
	{
		HashMap<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("nodeID", nodeId);
		queryMap.put("defID", defId);
		System.out.println("queryMap.size:"+queryMap.size());
		//String getStatement= getIbatisMapperNamespace()+"."+"getQueryMsg";
		//System.out.println(getStatement);
		System.out.println(nodeId);
		System.out.println(defId);
		//List<Fqrelation> fqrelations = getSqlSessionTemplate().selectList(getStatement, queryMap);
		List<Fqrelation> list = getBySqlKey("getQueryMsg", queryMap);
		System.out.println(list.size());
		return list;
	}
    public void delByfqId(Long fqId){
    	getUnique("delByfqId", fqId);
    }
}
