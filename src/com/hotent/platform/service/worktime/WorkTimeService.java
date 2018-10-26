package com.hotent.platform.service.worktime;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.worktime.WorkTimeDao;
import com.hotent.platform.model.worktime.WorkTime;

/**
 * 对象功能:班次时间 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2012-02-22 16:58:15
 */
@Service
public class WorkTimeService extends BaseService<WorkTime>
{
	@Resource
	private WorkTimeDao dao;
	
	public WorkTimeService()
	{
	}
	
	@Override
	protected IEntityDao<WorkTime, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 根据settingId取worktime
	 * @param settingId
	 * @return
	 */
	public List<WorkTime> getBySettingId(Long settingId){
		return dao.getBySettingId(String.valueOf(settingId));
	}
	
	public void workTimeAdd(Long settingId, String[] startTime, String[] endTime, String[] memo){
		
		if(startTime!=null && endTime!=null){
			
			dao.delBySqlKey("delBySettingId", settingId);
			
			for(int idx=0;idx<startTime.length;idx++){
				WorkTime worktime = new WorkTime();
				try {
					worktime.setId(UniqueIdUtil.genId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				worktime.setSettingId(settingId);
				worktime.setStartTime(startTime[idx]);
				worktime.setEndTime(endTime[idx]);
				worktime.setMemo(memo[idx]);
				dao.add(worktime);
			}
		}
	}
}
