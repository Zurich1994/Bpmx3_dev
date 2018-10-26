package com.hotent.platform.dao.system;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.AuthRole;
/**
 *<pre>
 * 对象功能:SYS_AUTH_ROLE Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:miao
 * 创建时间:2014-08-08 10:16:21
 *</pre>
 */
@Repository
public class AuthRoleDao extends BaseDao<AuthRole>
{
	@Override
	public Class<?> getEntityClass()
	{
		return AuthRole.class;
	}
	
	public void delByAuthId(Long authId) {
		this.delBySqlKey("delByAuthId", authId);
	}

}