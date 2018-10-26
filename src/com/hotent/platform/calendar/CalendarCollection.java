package com.hotent.platform.calendar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.hotent.core.util.BeanUtils;
import com.hotent.platform.calendar.model.CalendarData;

/**
 * 日历集合
 * @author zxh
 *
 */
public class CalendarCollection {
	
	
	private List<ICalendarDatas> calendarDatas = new ArrayList<ICalendarDatas>();

	/**
	 * 获取日历json
	 * 返回格式eg: [ { "id":"10000005210157", // 项id "type":"1", //
	 * 类型，如任务、消息 "startTime":"12/07/2012 00:00:00 AM", // 开始时间
	 * "endTime":"12/08/2012 00:00:00 AM", // 结束时间
	 * "title":"测试流程变量-admin-2012-10-17 11:55:07", // 标题 } ]
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String getCalendarDatasString(Map<String, Object> map)
			throws Exception {
		List<CalendarData> calendarDataList = new ArrayList<CalendarData>();
		for (ICalendarDatas ic : calendarDatas) {
			List<CalendarData> list = ic.getCalendarData(map); 
			if(BeanUtils.isNotEmpty(list))
				calendarDataList.addAll(list);
		}
		JSONArray jsonAry =	JSONArray.fromObject(calendarDataList);
		return jsonAry.toString();
	}


	public List<ICalendarDatas> getCalendarDatas() {
		return calendarDatas;
	}

	public void setCalendarDatas(List<ICalendarDatas> calendarDatas) {
		this.calendarDatas = calendarDatas;
	}



}
