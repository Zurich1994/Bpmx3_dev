/**
 * 对象功能:日历设置 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 14:57:32
 */
package com.hotent.platform.dao.worktime;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.TimeUtil;
import com.hotent.platform.model.worktime.CalendarSetting;

@Repository
public class CalendarSettingDao extends BaseDao<CalendarSetting>
{
	@SuppressWarnings("rawtypes")
	@Override                                          
	public Class getEntityClass()
	{
		return CalendarSetting.class;
	}
	
	/**
	 * 根据日历ID取得日历列表。
	 * @param calendarId
	 * @return
	 */
	public List<CalendarSetting> getByCalendarId(long calendarId,Date startTime){
		String start=TimeUtil.getDateString(startTime);
		Map map=new HashMap();
		map.put("startTime", start);
		map.put("calendarId", calendarId);
		List<CalendarSetting> list=this.getBySqlKey("getByCalendarId", map);
		return list;
	}
	
	/**
	 * 根据日历和开始结束时间取得时间段。
	 * @param calendarId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<CalendarSetting> getSegmentByCalId(Long calendarId,String startDate ,String endDate){
		String curDate=TimeUtil.getCurrentDate();
		Map map=new HashMap();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("calendarId", calendarId);
		List<CalendarSetting> list=this.getBySqlKey("getSegmentByCalId", map);
		return list;
	}
	
	/**
	 * 根据开始时间，结束时间得到日历
	 * @param startDate
	 * @param endDate
	 * @return
	 */
//	public List<CalendarSetting> getCalendarByStartAndEnd(Date startDate,Date endDate){
//		
//	}
	
	
	/**
	 * 根据日历id，year，month 得到日历
	 * @param id
	 * @param year
	 * @param month
	 * @return
	 */
	public List<CalendarSetting> getCalByIdYearMon(Long id, int year, int month){
		Map map=new HashMap();
		map.put("id", id);
		map.put("year", year);
		map.put("month", month);
		List<CalendarSetting> list=this.getBySqlKey("getCalByIdYearMon", map);
		return list;
	}
	
	/**
	 * 根据 日历id，year，month 删除日历
	 * @param calid
	 * @param year
	 * @param month
	 */
	public void delByCalidYearMon(Long calid, short year, short month){
		Map map=new HashMap();
		map.put("id", calid);
		map.put("year", year);
		map.put("month", month);
		this.delBySqlKey("delByCalidYearMon", map);
	}
	
	/**
	 * 根据日历id删除记录
	 * @param calId
	 */
	public void delByCalId(Long[] calIds){
		for(Long calId:calIds){
			this.delBySqlKey("delByCalId", calId);
		}
	}
	
}