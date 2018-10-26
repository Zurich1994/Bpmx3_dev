/**
 * 对象功能:日历分配 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 09:25:51
 */
package com.hotent.platform.dao.worktime;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.worktime.CalendarAssign;

@Repository
public class CalendarAssignDao extends BaseDao<CalendarAssign>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return CalendarAssign.class;
	}
	
	/**
	 * 根据分配类型和分配ID取得分配对象。
	 * @param assignType 1,用户,2,组织
	 * @param assignId	分配ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public CalendarAssign getByAssignId(int assignType,long assignId){
		
		Map map=new HashMap();
		map.put("assignType", assignType);
		map.put("assignId", assignId);
		CalendarAssign obj=this.getUnique("getByAssignId", map);
		return obj;
	}
	
	/**
	 * 根据日历id删除记录
	 * @param calId
	 */
	public void delByCalId(Long[] calIds){
		for(Long calId:calIds){
			this.getBySqlKey("delByCalId", calId);
		}
	}
	
	/**
	 * 根据用户ID得到唯一条分配信息
	 * @param assignId
	 * @return
	 */
	public CalendarAssign getbyAssignId(Long assignId){
		
		return this.getUnique("getbyAssign", assignId);
	}
	
}