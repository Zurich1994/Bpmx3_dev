
package com.hotent.HistoryData.dao.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.formQueryDefinition.model.com.Fqrelation;
import com.hotent.HistoryData.model.lc.HistoryData;

@Repository
public class HistoryDataDao extends BaseDao<HistoryData>
{
	@Override
	public Class<?> getEntityClass()
	{
		return HistoryData.class;
	}
	
	public List<HistoryData> getProcessId(String timeType){
		
		List<HistoryData> list = getBySqlKey("getProcessId",timeType);
		System.out.println(list.size());
		return list;
	}
	
	public List<HistoryData> getPictureData(String processId,String timeType){
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("processId", processId);
		map.put("timeType", timeType);
		List<HistoryData> list = getBySqlKey("getPictureData", map);
		
		System.out.println(list.size());
		return list;
	}
	
	public List<HistoryData> getOccurence(String processId,String occurenceTime){
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("processId", processId);
		map.put("occurenceTime", occurenceTime);
		List<HistoryData> list = getBySqlKey("getOccurence", map);
		System.out.println(list.size());
		return list;
	}

}
