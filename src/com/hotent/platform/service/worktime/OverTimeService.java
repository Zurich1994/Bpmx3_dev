package com.hotent.platform.service.worktime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.worktime.OverTimeDao;
import com.hotent.platform.model.worktime.OverTime;

/**
 * 对象功能:加班情况 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 09:25:52
 */
@Service
public class OverTimeService extends BaseService<OverTime>
{
	@Resource
	private OverTimeDao dao;
	
	public OverTimeService()
	{
	}
	
	@Override
	protected IEntityDao<OverTime, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 *  取得工作日类型
	 * @return
	 */
	public List getWorkType()
	{
		List typelist = new ArrayList();
		Map map = new HashMap();
		map.put("typeId", "1");
		map.put("typeName", "加班");
		typelist.add(map);
		map = new HashMap();
		map.put("typeId", "2");
		map.put("typeName", "请假");
		typelist.add(map);
		return typelist;
	}
	
	/**
	 * 取得一段时间内的加班/请假情况列表
	 * @param userId 用户ID
	 * @param type   工作变更类型 1，加班 2，请假
	 * @param startTime  任务开始时间
	 * @param endTime    结束时间
	 * @return
	 */
	public List<OverTime> getListByUserId(long userId,int type,Date startTime,Date endTime){
		return dao.getListByUserId(userId, type, startTime, endTime);
	}
	
	
	/**
	 * 取得一开始时间（有关的\之后）用户的加班/请假情况列表
	 * @param startTime  开始时间
	 * @param userId  用户ID
	 * @param type	工作变更类型 1，加班 2，请假
	 * @return
	 */
	public List<OverTime> getListByStart(Date startTime, long userId, int type) {
		return dao.getListByStart(startTime,userId,type);
	}
	
}
