package com.hotent.platform.service.ats;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsHolidayPolicyDao;
import com.hotent.platform.dao.ats.AtsHolidayPolicyDetailDao;
import com.hotent.platform.model.ats.AtsHolidayPolicy;
import com.hotent.platform.model.ats.AtsHolidayPolicyDetail;

/**
 *<pre>
 * 对象功能:假期制度 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-19 17:42:20
 *</pre>
 */
@Service
public class AtsHolidayPolicyService extends  BaseService<AtsHolidayPolicy>
{
	@Resource
	private AtsHolidayPolicyDao dao;
	
	@Resource
	private AtsHolidayPolicyDetailDao atsHolidayPolicyDetailDao;
	
	
	
	public AtsHolidayPolicyService()
	{
	}
	
	@Override
	protected IEntityDao<AtsHolidayPolicy, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 保存 假期制度 信息
	 * @param atsHolidayPolicy
	 */
	public void save(AtsHolidayPolicy atsHolidayPolicy){
		Long id=atsHolidayPolicy.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atsHolidayPolicy.setId(id);
			this.add(atsHolidayPolicy);
		}
		else{
			this.update(atsHolidayPolicy);
			atsHolidayPolicyDetailDao.delByHolidayId(id);
		}
		List<AtsHolidayPolicyDetail> atsHolidayPolicyDetailList = atsHolidayPolicy.getAtsHolidayPolicyDetailList();
		if(atsHolidayPolicyDetailList!=null)
			for(AtsHolidayPolicyDetail atsHolidayPolicyDetail : atsHolidayPolicyDetailList){
				if(BeanUtils.isEmpty(atsHolidayPolicyDetail.getHolidayType()))
					continue;
				atsHolidayPolicyDetail.setHolidayId(id);
				atsHolidayPolicyDetail.setId(UniqueIdUtil.genId());
				atsHolidayPolicyDetailDao.add(atsHolidayPolicyDetail);
			}
	}
	@Override
	public AtsHolidayPolicy getById(Long id ){
		AtsHolidayPolicy atsHolidayPolicy = dao.getById(id);
		if(atsHolidayPolicy!=null){
			List<AtsHolidayPolicyDetail> list = atsHolidayPolicyDetailDao.getByHolidayId(id);
			if(list!=null){
				atsHolidayPolicy.setAtsHolidayPolicyDetailList(list);
			}
		}
		return atsHolidayPolicy;
	}

	public AtsHolidayPolicy getByDefault() {
		return dao.getByDefault();
	}

	public AtsHolidayPolicy getByName(String name) {
		return dao.getByName(name);
	}
}
