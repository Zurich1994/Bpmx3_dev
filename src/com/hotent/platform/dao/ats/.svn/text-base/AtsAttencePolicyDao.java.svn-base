package com.hotent.platform.dao.ats;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.ats.AtsAttencePolicy;
/**
 *<pre>
 * 对象功能:考勤制度 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 20:54:19
 *</pre>
 */
@Repository
public class AtsAttencePolicyDao extends BaseDao<AtsAttencePolicy>
{
	@Override
	public Class<?> getEntityClass()
	{
		return AtsAttencePolicy.class;
	}

	public AtsAttencePolicy getByDefault() {
		List<AtsAttencePolicy> list =  this.getBySqlKey("getByDefault");
		if(BeanUtils.isNotEmpty(list))
			return list.get(0);
		return null;
	}

	public AtsAttencePolicy getByName(String name) {
		List<AtsAttencePolicy> list =  this.getBySqlKey("getByName",name);
		if(BeanUtils.isNotEmpty(list))
			return list.get(0);
		return null;
	}

}