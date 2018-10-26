package com.hotent.platform.service.ats;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsHolidayTypeDao;
import com.hotent.platform.model.ats.AtsConstant;
import com.hotent.platform.model.ats.AtsHolidayType;

/**
 *<pre>
 * 对象功能:假期类型 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-16 20:47:17
 *</pre>
 */
@Service
public class AtsHolidayTypeService extends  BaseService<AtsHolidayType>
{
	@Resource
	private AtsHolidayTypeDao dao;
	
	
	
	public AtsHolidayTypeService()
	{
	}
	
	@Override
	protected IEntityDao<AtsHolidayType, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 保存 假期类型 信息
	 * @param atsHolidayType
	 */
	public void save(AtsHolidayType atsHolidayType){
		Long id=atsHolidayType.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atsHolidayType.setId(id);
			atsHolidayType.setIsSys(AtsConstant.NO);
			this.add(atsHolidayType);
		}
		else{
			this.update(atsHolidayType);
		}
	}
	
}
