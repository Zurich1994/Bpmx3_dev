/**
 * 对象功能:桌面布局 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-03-20 16:39:01
 */
package com.hotent.platform.dao.system;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.DesktopLayout;

@Repository
public class DesktopLayoutDao extends BaseDao<DesktopLayout>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return DesktopLayout.class;
	}
	
	public DesktopLayout getDefaultLayout() {
		return this.getUnique("getDefaultLayout", null);
	}
	
	
	public int updateDefault(long id) {
		return this.update("updateDefault", id);
	}
	
	public int updNotDefault() {
		return this.update("updNotDefault",null);
	}

	public long getDefaultId() {
		return (Long) this.getOne("getDefaultId",null);
	}
	
	public String getNameById(long layoutId) {
		return (String)this.getOne("getNameById", layoutId);
	}
	
	public DesktopLayout getLayoutByUserId(Long userId){		
		return this.getUnique("getLayoutByUserId", userId);
	}
}