package com.hotent.platform.service.calendar.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hotent.platform.calendar.ICalendarDatas;
import com.hotent.platform.calendar.model.CalendarData;
import com.hotent.platform.calendar.util.CalendarUtil;
import com.hotent.platform.dao.calendar.PersonalCalendarDao;
import com.hotent.platform.model.calendar.PersonalCalendar;

/**
 * 个人日历
 * @author zxh
 *
 */
public class PersonalCalendarData  implements ICalendarDatas {
	@Resource
	private PersonalCalendarDao personalCalendarDao;
	public List<CalendarData> getCalendarData(Map<String, Object> map)
			throws Exception {
		List<PersonalCalendar> list =  personalCalendarDao.getPersonalCalendar(map);
		String mode = (String)map.get("mode");
		Date nowDate =  new Date();
		List<CalendarData> calendarDataList = new ArrayList<CalendarData>();
		for (PersonalCalendar personalCalendar : list) {		
			CalendarData calendarData = new CalendarData();
			calendarData.setId(personalCalendar.getId());

			CalendarUtil.setCalendarData(calendarData, mode,nowDate,personalCalendar.getStartTime(),personalCalendar.getEndTime() );
			
			calendarData.setDescription(personalCalendar.getDescription());
			calendarData.setTitle(personalCalendar.getTitle());
			calendarData.setType(CalendarData.TYPE_MY_CALENDARS);
			calendarData.setStatus(CalendarData.COLOR_MY_CALENDARS);		
			calendarDataList.add(calendarData);
		}
		return calendarDataList;
	}

}
