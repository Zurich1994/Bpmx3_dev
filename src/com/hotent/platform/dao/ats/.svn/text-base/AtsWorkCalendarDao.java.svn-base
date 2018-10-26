package com.hotent.platform.dao.ats;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.ats.AtsWorkCalendar;

/**
 * <pre>
 * 对象功能:工作日历 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 15:46:19
 * </pre>
 */
@Repository
public class AtsWorkCalendarDao extends BaseDao<AtsWorkCalendar> {
	@Override
	public Class<?> getEntityClass() {
		return AtsWorkCalendar.class;
	}

	public List<AtsWorkCalendar> getByCalendarTempl(Long calendarTempl) {
		return this.getBySqlKey("getByCalendarTempl", calendarTempl);
	}
}