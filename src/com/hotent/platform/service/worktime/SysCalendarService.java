package com.hotent.platform.service.worktime;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.worktime.CalendarSettingDao;
import com.hotent.platform.dao.worktime.SysCalendarDao;
import com.hotent.platform.model.worktime.CalendarSetting;
import com.hotent.platform.model.worktime.SysCalendar;

/**
 * 对象功能:系统日历 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 14:57:32
 */
@Service
public class SysCalendarService extends BaseService<SysCalendar>
{
	@Resource
	private SysCalendarDao dao;
	
	@Resource
	private CalendarSettingDao calendarSettingDao;
	
	public SysCalendarService()
	{
	}
	
	@Override
	protected IEntityDao<SysCalendar, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	
	/**
	 * 取得默认的日历。
	 * @return
	 */
	public SysCalendar getDefaultCalendar(){
		return dao.getDefaultCalendar();
	}
	
	/**
	 * 设置默认日历
	 * @param id
	 */
	public void setDefaultCal(Long id){
		//设置非默认
		dao.setNotDefaultCal();
		SysCalendar syscal = getById(id);
		syscal.setIsDefault(new Short("1"));
		update(syscal);
	}
	

	/**
	 * 保存日历设置情况。
	 * @param json 格式
	 *  var data={
	 *				id:calId,
	 *				name:name,
	 *				memo:memo,
	 *				year:year,
	 *				month:month,
	 *				days:[{day:day,type:type,worktimeid:worktimeid}]
	 *		};
	 * @throws Exception
	 */
	public void saveCalendar(String json) throws Exception{
		JSONObject jsonObject=JSONObject.fromObject(json);
		Long id=jsonObject.getLong("id");
		String name=jsonObject.getString("name");
		String memo=jsonObject.getString("memo");
		short year=(short)jsonObject.getInt("year");
		short month=(short)jsonObject.getInt("month");
		JSONArray aryDay=jsonObject.getJSONArray("days");
		if(id==0){
			id=UniqueIdUtil.genId();
			SysCalendar defaultCal=dao.getDefaultCalendar();
			SysCalendar sysCalendar=new SysCalendar();
			sysCalendar.setId(id);
			sysCalendar.setName(name);
			sysCalendar.setMemo(memo);
			if(defaultCal==null){
				sysCalendar.setIsDefault((short)1);
			}
			else{
				sysCalendar.setIsDefault((short)0);
			}
			add(sysCalendar);
		}
		else{
			SysCalendar sysCalendar=dao.getById(id);
			sysCalendar.setName(name);
			sysCalendar.setMemo(memo);
			dao.update(sysCalendar);
			calendarSettingDao.delByCalidYearMon(id, year, month);
		}
		addCalendarSetting(id,year,month,aryDay);
		
	}
	
	private void addCalendarSetting(Long calendarId,short year,short month,JSONArray aryDay) throws Exception{
		for(Object obj:aryDay){
			JSONObject jsonObj=(JSONObject)obj;
			//day:day,type:type
			short day=(short)jsonObj.getInt("day");
			short type=(short)jsonObj.getInt("type");
			Long worktimeid=0L;
			if(type==1){
				worktimeid=jsonObj.getLong("worktimeid");
			}
			CalendarSetting setting=new CalendarSetting();
			setting.setId(UniqueIdUtil.genId());
			setting.setCalendarId(calendarId);
			setting.setYears(year);
			setting.setMonths(month);
			setting.setDays(day);
			setting.setType(type);
			setting.setWorkTimeId(worktimeid);
			calendarSettingDao.add(setting);
		}
		
		
	}
	
}
