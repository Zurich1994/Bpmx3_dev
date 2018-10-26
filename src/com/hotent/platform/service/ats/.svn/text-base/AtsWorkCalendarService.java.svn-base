package com.hotent.platform.service.ats;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsWorkCalendarDao;
import com.hotent.platform.model.ats.AtsWorkCalendar;

/**
 *<pre>
 * 对象功能:工作日历 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 15:46:19
 *</pre>
 */
@Service
public class AtsWorkCalendarService extends  BaseService<AtsWorkCalendar>
{
	@Resource
	private AtsWorkCalendarDao dao;
	
	
	
	public AtsWorkCalendarService()
	{
	}
	
	@Override
	protected IEntityDao<AtsWorkCalendar, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 保存 工作日历 信息
	 * @param atsWorkCalendar
	 */
	public void save(AtsWorkCalendar atsWorkCalendar){
		Long id=atsWorkCalendar.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atsWorkCalendar.setId(id);
			this.add(atsWorkCalendar);
		}
		else{
			this.update(atsWorkCalendar);
		}
	}
	
}
