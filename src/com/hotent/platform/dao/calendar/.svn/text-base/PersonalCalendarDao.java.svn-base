package com.hotent.platform.dao.calendar;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.calendar.util.CalendarUtil;
import com.hotent.platform.model.calendar.PersonalCalendar;
/**
 *<pre>
 * 对象功能:个人日历 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2014-03-12 14:50:14
 *</pre>
 */
@Repository
public class PersonalCalendarDao extends BaseDao<PersonalCalendar>
{
	@Override
	public Class<?> getEntityClass()
	{
		return PersonalCalendar.class;
	}

	public List<PersonalCalendar> getPersonalCalendar(Map<String, Object> params) {
		return this.getBySqlKey("getPersonalCalendar", CalendarUtil.getCalendarMap(params));
	}

}