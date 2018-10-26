
package com.hotent.RegularOccurrence.dao.lc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.RegularOccurrence.model.lc.Regularoccurrence;

@Repository
public class RegularoccurrenceDao extends BaseDao<Regularoccurrence>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Regularoccurrence.class;
	}
	
	public List<Regularoccurrence> getRegular(String processId,String timeType){
		Map<String,Object> paramMap = new HashMap<String,Object>(); 
		paramMap.put("processId", processId);
		paramMap.put("timeType", timeType);
		List<Regularoccurrence> regularList = getBySqlKey("getRegular",paramMap);
		return regularList;
	}
	
	public List<Regularoccurrence> getProcessId(String timeType){
	
		List<Regularoccurrence> regularList = getBySqlKey("getProcessId",timeType);
		return regularList;
	}

}
