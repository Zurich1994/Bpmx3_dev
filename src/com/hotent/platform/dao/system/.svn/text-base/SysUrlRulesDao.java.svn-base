package com.hotent.platform.dao.system;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysUrlRules;
/**
 *<pre>
 * 对象功能:URL地址拦截脚本管理 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:wdr
 * 创建时间:2014-03-27 16:32:01
 *</pre>
 */
@Repository
public class SysUrlRulesDao extends BaseDao<SysUrlRules>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysUrlRules.class;
	}

	public List<SysUrlRules> getByUrlPer(Long id) {
		return this.getBySqlKey("getSysUrlRulesList", id);
	}

	public void delByUrlPerId(long id) {
		this.delBySqlKey("delByUrlPerId", id);
	}

}