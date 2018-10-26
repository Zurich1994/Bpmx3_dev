package com.hotent.platform.dao.system;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysCodeTemplate;
/**主表主键：Long
 *<pre>
 * 对象功能:自定义表代码模版 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2012-12-19 15:38:01
 *</pre>
 */
@Repository
public class SysCodeTemplateDao extends BaseDao<SysCodeTemplate>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysCodeTemplate.class;
	}

	public void delByTemplateType(Short templateType) {
		this.delBySqlKey("delByTemplateType", templateType);
	}

	public SysCodeTemplate getByTemplateAlias(String alias) {
		return this.getUnique("getByTemplateAlias", alias);
	}


}