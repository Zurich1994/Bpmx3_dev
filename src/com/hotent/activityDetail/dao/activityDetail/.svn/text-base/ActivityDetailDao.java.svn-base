
package com.hotent.activityDetail.dao.activityDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.activityDetail.model.activityDetail.ActivityDetail;

@Repository
public class ActivityDetailDao extends BaseDao<ActivityDetail>
{
	@Override
	public Class<?> getEntityClass()
	{
		return ActivityDetail.class;
	}

	public ActivityDetail getByactDefId(String actDef_Id,String activity_id) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("actDef_Id", actDef_Id);
		params.put("activity_id", activity_id);
		List ls = getBySqlKey("getByactDefId", params);
		if(ls.isEmpty())
			return null;
		else
			return (ActivityDetail)ls.get(0);
	}
	public List<ActivityDetail> getBy_ActDefId_ActivityId(String actDef_Id,String activity_id){
		Map  map=new HashMap();
		map.put("actDef_Id", actDef_Id);
		map.put("activity_id", activity_id);		
		List<ActivityDetail> wactList=getBySqlKey("getBy_ActDefId_ActivityId", map);	
		return wactList;
		
	}
	public void updataSysDefNodeName(String actDef_Id,String activity_id,String work_subsys_name,String work_name,String activity_name){
		Map  map=new HashMap();
		map.put("actDef_Id", actDef_Id);
		map.put("activity_id", activity_id);
		map.put("work_subsys_name", work_subsys_name);
		map.put("work_name", work_name);
		map.put("activity_name", activity_name);
		List<ActivityDetail> wactList=getBySqlKey("update1", map);	
	}
}