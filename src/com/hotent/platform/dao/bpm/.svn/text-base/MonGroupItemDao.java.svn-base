package com.hotent.platform.dao.bpm;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.bpm.MonGroupItem;
/**
 *<pre>
 * 对象功能:监控流程项目 Dao类
 * 开发公司:广州宏天软件
 * 开发人员:zyp
 * 创建时间:2013-06-08 11:14:50
 *</pre>
 */
@Repository
public class MonGroupItemDao extends BaseDao<MonGroupItem>
{
	@Override
	public Class<?> getEntityClass()
	{
		return MonGroupItem.class;
	}

	public List<MonGroupItem> getByMainId(Long groupid) {
		return this.getBySqlKey("getMonGroupItemList", groupid);
	}
	
	public void delByMainId(Long groupid) {
		this.delBySqlKey("delByMainId", groupid);
	}
}