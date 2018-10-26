package com.hotent.platform.service.ats;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsShiftTypeDao;
import com.hotent.platform.model.ats.AtsConstant;
import com.hotent.platform.model.ats.AtsShiftType;

/**
 *<pre>
 * 对象功能:班次类型 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-16 21:44:00
 *</pre>
 */
@Service
public class AtsShiftTypeService extends  BaseService<AtsShiftType>
{
	@Resource
	private AtsShiftTypeDao dao;
	
	
	
	public AtsShiftTypeService()
	{
	}
	
	@Override
	protected IEntityDao<AtsShiftType, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 保存 班次类型 信息
	 * @param atsShiftType
	 */
	public void save(AtsShiftType atsShiftType){
		Long id=atsShiftType.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atsShiftType.setId(id);
			atsShiftType.setIsSys(AtsConstant.NO);
			this.add(atsShiftType);
		}
		else{
			this.update(atsShiftType);
		}
	}

	public List<AtsShiftType> getListByStatus(Short status) {
		return dao.getListByStatus(status);
	}
	
}
