package com.hotent.Number_Rule.service.Number_RulePac;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;

import com.hotent.core.service.GenericService;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.Number_Rule.model.Number_RulePac.DeviceNumberRule;
import com.hotent.Number_Rule.dao.Number_RulePac.DeviceNumberRuleDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DeviceNumberRuleService extends BaseService<DeviceNumberRule>
{
	@Resource
	private DeviceNumberRuleDao dao;
	
	public DeviceNumberRuleService()
	{
	}
	
	@Override
	protected IEntityDao<DeviceNumberRule,Long> getEntityDao() 
	{
		return dao;
	}
	
}
