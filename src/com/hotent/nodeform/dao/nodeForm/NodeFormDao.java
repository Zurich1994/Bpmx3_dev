
package com.hotent.nodeform.dao.nodeForm;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.formQueryDefinition.model.com.Fqrelation;
import com.hotent.nodeform.model.nodeForm.NodeForm;

@Repository
public class NodeFormDao extends BaseDao<NodeForm>
{
	@Override
	public Class<?> getEntityClass()
	{
		return NodeForm.class;
	}

	public Long getFormID(String string, String string2,
			String string3) {
		
		HashMap<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("condition1", string);
		queryMap.put("condition2", string2);
		queryMap.put("condition3", string3);
		System.out.println("queryMap.size:"+queryMap.size());
		//String getStatement= getIbatisMapperNamespace()+"."+"getQueryMsg";
		//System.out.println(getStatement);
		System.out.println(string);
		System.out.println(string2);
		System.out.println(string3);
		//List<Fqrelation> fqrelations = getSqlSessionTemplate().selectList(getStatement, queryMap);
		Long formID = (Long)getOne("getFormID", queryMap);
		return formID;
		
		
	}
	
	public Long getTableID(Long formID){
		Long tableID = (Long)getOne("getTableID", formID);
		return tableID;
	}
	
	public String getTableName(Long tableID){
		String tableName = (String)getOne("getTableName", tableID);
		return tableName;
	}

}
