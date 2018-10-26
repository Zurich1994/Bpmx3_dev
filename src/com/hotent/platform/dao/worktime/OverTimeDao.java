/**
 * 对象功能:加班情况 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 09:25:51
 */
package com.hotent.platform.dao.worktime;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.worktime.OverTime;

@Repository
public class OverTimeDao extends BaseDao<OverTime>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return OverTime.class;
	}

	/**
	 * 根据用户Id，工作类型(1加班,2请假)，
	 * 开始时间，结束时间
	 * 获取用户在某一段时间内的加班/请假管理列表
	 * @param userId
	 * @param workType
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<OverTime> getListByUserId(long userId,int type,Date startTime,Date endTime) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("workType", type);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		return this.getBySqlKey("getListByUserId", params);
	}

	public List<OverTime> getListByStart(Date startTime, long userId, int type) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("startTime", startTime);
		params.put("userId", userId);
		params.put("workType", type);
		
		return this.getBySqlKey("getListByStart", params);
	}
}