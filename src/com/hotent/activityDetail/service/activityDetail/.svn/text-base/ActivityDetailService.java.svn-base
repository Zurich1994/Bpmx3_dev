package com.hotent.activityDetail.service.activityDetail;
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
import com.hotent.activityDetail.model.activityDetail.ActivityDetail;
import com.hotent.activityDetail.dao.activityDetail.ActivityDetailDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class ActivityDetailService extends BaseService<ActivityDetail>
{
	@Resource
	private ActivityDetailDao dao;
	
	public ActivityDetailService()
	{
	}
	
	@Override
	protected IEntityDao<ActivityDetail,Long> getEntityDao() 
	{
		return dao;
	}

	public ActivityDetail getByactDefId(String actDefId,String nodeId) {
		return dao.getByactDefId(actDefId,nodeId);
	}

	public boolean panding(String actDefId, String activityId) {
		// TODO Auto-generated method stub
		dao.getBy_ActDefId_ActivityId(actDefId,activityId);
		if(dao.getBy_ActDefId_ActivityId(actDefId,activityId).size()==0)
			return true;
		else return false;
	}
	
}
