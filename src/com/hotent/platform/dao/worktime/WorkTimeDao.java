/**
 * 对象功能:班次时间 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2012-02-22 16:58:15
 */
package com.hotent.platform.dao.worktime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.worktime.WorkTime;

@Repository
public class WorkTimeDao extends BaseDao<WorkTime>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return WorkTime.class;
	}
	
	/**
	 * 根据settingId取worktime
	 * @param settingId
	 * @return
	 */
	public List<WorkTime> getBySettingId(String settingId){
		Map<String, String> p = new HashMap<String, String>();
		p.put("settingId", settingId);
		return this.getSqlSessionTemplate().selectList(
				this.getIbatisMapperNamespace() + ".getBySettingId", p);
	}
}