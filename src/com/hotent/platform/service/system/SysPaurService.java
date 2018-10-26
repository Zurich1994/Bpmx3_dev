package com.hotent.platform.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.SysPaur;
import com.hotent.platform.dao.system.SysPaurDao;

/**
 *<pre>
 * 对象功能:SYS_PAUR Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-12-24 14:41:59
 *</pre>
 */
@Service
public class SysPaurService extends BaseService<SysPaur>
{
	@Resource
	private SysPaurDao dao;
	
	public SysPaurService()
	{
	}
	
	@Override
	protected IEntityDao<SysPaur, Long> getEntityDao() 
	{
		return dao;
	}
	/**
	 * 根据用户id和资源别名获取个性资源设置记录
	 * @param userId
	 * @param aliasName
	 * @return
	 */
	public SysPaur getByUserAndAlias(Long userId,String aliasName){	
		if(userId==null || aliasName==null)  return null;
		return dao.getByUserAndAlias(userId, aliasName);
	}
	
	/**
	 * 取当前用户皮肤设置
	 * @return
	 */
	public  String getCurrentUserSkin(Long userId){
		String skinStyle="default";		
		SysPaur skinSysPaur=dao.getByUserAndAlias(userId, "skin");
		if(skinSysPaur==null){
			skinSysPaur=dao.getByUserAndAlias(0L, "skin");//个性设置空的话取系统默认设置
		}
		if(skinSysPaur!=null && skinSysPaur.getPaurvalue()!=null){
			skinStyle=skinSysPaur.getPaurvalue();
		}
		return skinStyle;
	}
}
