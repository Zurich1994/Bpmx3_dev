package com.hotent.platform.dao.system;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.system.SysLogSwitch;
/**
 *<pre>
 * 对象功能:日志开关 Dao类
 * 开发公司:广州宏天
 * 开发人员:Raise
 * 创建时间:2013-06-24 11:12:26
 *</pre>
 */
@Repository
public class SysLogSwitchDao extends BaseDao<SysLogSwitch>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysLogSwitch.class;
	}

	public SysLogSwitch getByModel(String model) {
		List<SysLogSwitch> switchs = this.getBySqlKey("getByModel", model);
		if(BeanUtils.isNotEmpty(switchs)){
			return switchs.get(0);
		}
		return null;
	}

}