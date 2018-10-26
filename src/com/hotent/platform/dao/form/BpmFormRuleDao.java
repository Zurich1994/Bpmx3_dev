/**
 * 对象功能:表单验证规则 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2012-01-11 10:53:15
 */
package com.hotent.platform.dao.form;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.form.BpmFormRule;

@Repository
public class BpmFormRuleDao extends BaseDao<BpmFormRule>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmFormRule.class;
	}
}