package com.hotent.platform.service.worktime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.TimeUtil;
import com.hotent.platform.model.worktime.CalendarSetting;
import com.hotent.platform.model.worktime.WorkTime;
import com.hotent.platform.dao.worktime.CalendarSettingDao;;

/**
 * 对象功能:日历设置 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 14:57:32
 */
@Service
public class CalendarSettingService extends BaseService<CalendarSetting>
{
	@Resource
	private CalendarSettingDao dao;
	
	public CalendarSettingService()
	{
	}
	
	@Override
	protected IEntityDao<CalendarSetting, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 根据日历查询时间。
	 * 将时间进行分段。
	 * 开始时间1 结束时间1
	 * 开始时间2 结束时间2
	 * @param calendarId
	 * @return
	 */
	public List<WorkTime> getByCalendarId(long calendarId,Date startTime){
		List<WorkTime>  rtnList=new ArrayList<WorkTime>();
		List<WorkTime>  tmpList=new ArrayList<WorkTime>();
		List<CalendarSetting> list=dao.getByCalendarId(calendarId,startTime);
		for(CalendarSetting calendarSetting:list){
			String calDay=calendarSetting.getCalDay();
			List<WorkTime> workTimeList=calendarSetting.getWorkTimeList();
			for(WorkTime workTime:workTimeList){
				workTime.setCalDay(calDay);
				tmpList.add((WorkTime)workTime.clone());
			}
		}
		int len=tmpList.size();
		for(int i=0;i<len;i++){
			WorkTime curTime=tmpList.get(i);
			if(i<len-1){
				int j=i+1;
				WorkTime nextTime=tmpList.get(j);
				if(curTime.getEndDateTime().compareTo(nextTime.getStartDateTime())>0){
					curTime.setEndDateTime(nextTime.getEndDateTime());
					rtnList.add(curTime);
					i++;
				}
				else{
					rtnList.add(curTime);
				}
			}
			else{
				rtnList.add(curTime);
			}
		}
		return rtnList;
	}
	
	
	
	
	/**
	 * 获取工作时间。
	 * 以日历ID，开始时间和结束时间为备件获取所有符合备件的工作时间的列表。
	 * @param calendarId 日历ID
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 工作时间的列表。
	 */
	public List<WorkTime> getByCalendarId(long calendarId,Date startTime,Date endTime){
		List<WorkTime>  rtnList=new ArrayList<WorkTime>();
		List<WorkTime>  tmpList=new ArrayList<WorkTime>();
//		List<CalendarSetting> list1=dao.getByCalendarId(calendarId,startTime);
		String startDate = TimeUtil.getDateString(startTime);
		String endDate = TimeUtil.getDateString(endTime);
		List<CalendarSetting> list = dao.getSegmentByCalId(calendarId, startDate, endDate);
		for(CalendarSetting calendarSetting:list){
			String calDay=calendarSetting.getCalDay();
			List<WorkTime> workTimeList=calendarSetting.getWorkTimeList();
			for(WorkTime workTime:workTimeList){
				workTime.setCalDay(calDay);
				tmpList.add((WorkTime)workTime.clone());
			}
		}
		int len=tmpList.size();
		for(int i=0;i<len;i++){
			WorkTime curTime=tmpList.get(i);
			if(i<len-1){
				int j=i+1;
				WorkTime nextTime=tmpList.get(j);
				if(curTime.getEndDateTime().compareTo(nextTime.getStartDateTime())>0){
					curTime.setEndDateTime(nextTime.getEndDateTime());
					rtnList.add(curTime);
					i++;
				}
				else{
					rtnList.add(curTime);
				}
			}
			else{
				rtnList.add(curTime);
			}
		}
		return rtnList;
	}
	
	/**
	 * 根据日历id，year，month 得到日历
	 * @param id
	 * @param year
	 * @param month
	 * @return
	 */
	public List<CalendarSetting> getCalByIdYearMon(Long id, int year, int month){
		return dao.getCalByIdYearMon(id, year, month);
	}
	
	/**
	 * 根据 日历id，year，month 删除日历
	 * @param calid
	 * @param year
	 * @param month
	 */
	public void delByCalidYearMon(Long calid, Short year, Short month){
		dao.delByCalidYearMon(calid, year, month);
	}
	
	/**
	 * 根据日历id删除记录
	 * @param calId
	 */
	public void delByCalId(Long[] calIds){
		dao.delByCalId(calIds);
	}
	
}
