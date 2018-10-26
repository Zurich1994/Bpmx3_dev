/**
 * 对象功能:法定假期设置 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 09:25:49
 */
package com.hotent.platform.dao.worktime;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.worktime.Vacation;

@Repository
public class VacationDao extends BaseDao<Vacation>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return Vacation.class;
	}
	
	/**
	 * 根据年月取法定日期
	 * @return
	 */
	public List<Vacation> getByYearMon(Date statTime, Date endTime){
		Map map = new HashMap();
		map.put("statTime", statTime);
		map.put("endTime", endTime);
		String sqlKey="getByYearMon_"+this.getDbType();
		return this.getBySqlKey(sqlKey, map);
	}
	
}