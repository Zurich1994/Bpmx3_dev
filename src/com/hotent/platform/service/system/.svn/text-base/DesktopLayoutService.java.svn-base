package com.hotent.platform.service.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.system.DesktopLayoutDao;
import com.hotent.platform.model.system.DesktopLayout;

/**
 * 对象功能:桌面布局 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-03-20 16:39:01
 */
@Service
public class DesktopLayoutService extends BaseService<DesktopLayout>
{
	@Resource
	private DesktopLayoutDao dao;
	
	public DesktopLayoutService()
	{
	}
	
	@Override
	protected IEntityDao<DesktopLayout, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	
	/**
	 * 得到默认布局
	 * @param isDefault
	 * @return
	 */
	public DesktopLayout getDefaultLayout() {
		return dao.getDefaultLayout();
	}
	
	
	

	/**
	 * 设置默认桌面布局
	 * @param outMailUserSeting
	 * @throws Exception
	 */
	public void setDefault(Long layoutId)throws Exception{
		dao.updNotDefault();
		dao.updateDefault(layoutId);
	}
	
	public DesktopLayout getLayoutByUserId(Long userId){
		return dao.getLayoutByUserId(userId);
	}
	
}
