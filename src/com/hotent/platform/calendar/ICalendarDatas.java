package com.hotent.platform.calendar;

import java.util.List;
import java.util.Map;

import com.hotent.platform.calendar.model.CalendarData;

/**
 * 日历数据接口
 * <pre> 
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2014-3-20-下午3:30:40
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface ICalendarDatas {

	/**
	 * 获取数据
	 * @param map 传入的Map
	 * @return
	 * @throws Exception
	 */
	public List<CalendarData> getCalendarData(Map<String,Object> map)  throws Exception ;
}
