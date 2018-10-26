package com.hotent.platform.service.ats;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsBaseItemDao;
import com.hotent.platform.model.ats.AtsBaseItem;

/**
 *<pre>
 * 对象功能:基础数据 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-16 18:08:43
 *</pre>
 */
@Service
public class AtsBaseItemService extends  BaseService<AtsBaseItem>
{
	@Resource
	private AtsBaseItemDao dao;
	
	
	
	public AtsBaseItemService()
	{
	}
	
	@Override
	protected IEntityDao<AtsBaseItem, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 保存 基础数据 信息
	 * @param atsBaseItem
	 */
	public void save(AtsBaseItem atsBaseItem){
		Long id=atsBaseItem.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atsBaseItem.setId(id);
			this.add(atsBaseItem);
		}
		else{
			this.update(atsBaseItem);
		}
	}
	
}
