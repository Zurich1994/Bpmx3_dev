package com.hotent.platform.dao.mobile;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.mobile.MobileUserInfo;
/**
 *<pre>
 * 对象功能:MOBILE_USER_INFO Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-01-06 11:55:09
 *</pre>
 */
@Repository
public class MobileUserInfoDao extends BaseDao<MobileUserInfo>
{
	@Override
	public Class<?> getEntityClass()
	{
		return MobileUserInfo.class;
	}

}