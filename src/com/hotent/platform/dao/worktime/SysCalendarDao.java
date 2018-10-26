/**
 * 对象功能:系统日历 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 14:57:32
 */
package com.hotent.platform.dao.worktime;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.worktime.SysCalendar;

@Repository
public class SysCalendarDao extends BaseDao<SysCalendar>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return SysCalendar.class;
	}
	
	/**
	 * 取得默认的日历。
	 * @return
	 */
	public SysCalendar getDefaultCalendar(){
		return this.getUnique("getDefaultCalendar", null);
	}
	
	/**
	 * 设置默认日历
	 * @param id
	 */
	public void setNotDefaultCal(){
		this.update("setNotDefaultCal", null);
	}
}