
package com.hotent.Number_Rule.dao.Number_RulePac;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.Number_Rule.model.Number_RulePac.DeviceNumberRule;

@Repository
public class DeviceNumberRuleDao extends BaseDao<DeviceNumberRule>
{
	@Override
	public Class<?> getEntityClass()
	{
		return DeviceNumberRule.class;
	}

}
