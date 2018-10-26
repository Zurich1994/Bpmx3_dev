package com.hotent.platform.dao.form;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.form.FormDefCombinate;
/**
 *<pre>
 * 对象功能:form_def_combinate Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-05-20 09:55:36
 *</pre>
 */
@Repository
public class FormDefCombinateDao extends BaseDao<FormDefCombinate>
{
	@Override
	public Class<?> getEntityClass()
	{
		return FormDefCombinate.class;
	}

	
	
	
	
}