package com.hotent.platform.service.system;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.model.system.ResourcesUrl;
import com.hotent.platform.dao.system.ResourcesUrlDao;

/**
 * 对象功能:资源URL Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-05 17:00:58
 */
@Service
public class ResourcesUrlService extends BaseService<ResourcesUrl>
{
	@Resource
	private ResourcesUrlDao resourcesUrlDao;
	
	public ResourcesUrlService()
	{
	}
	
	@Override
	protected IEntityDao<ResourcesUrl, Long> getEntityDao() 
	{
		return resourcesUrlDao;
	}
	
	public List<ResourcesUrl> getByResId(long resId){
		
		return resourcesUrlDao.getByResId(resId);
	}
	
	public void update(long resId,List<ResourcesUrl> resourcesUrlList){
		resourcesUrlDao.delByResId(resId);
		if(resourcesUrlList!=null&&resourcesUrlList.size()>0){
			for(ResourcesUrl url:resourcesUrlList){
				add(url);
			}
		}
	}
	
	
	
}
