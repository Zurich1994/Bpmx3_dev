package com.hotent.platform.dao.bpm;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.bpm.BpmCommonWsSet;
/**
 *<pre>
 * 对象功能:通用webservice调用设置 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-10-17 10:09:20
 *</pre>
 */
@Repository
public class BpmCommonWsSetDao extends BaseDao<BpmCommonWsSet>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BpmCommonWsSet.class;
	}
	
	public BpmCommonWsSet getByAlias(String alias){
		List<BpmCommonWsSet> list = this.getBySqlKey("getByAlias",alias);
		if(BeanUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}

}