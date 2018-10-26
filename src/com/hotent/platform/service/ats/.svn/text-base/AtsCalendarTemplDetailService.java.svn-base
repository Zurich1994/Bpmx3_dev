package com.hotent.platform.service.ats;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsCalendarTemplDetailDao;
import com.hotent.platform.model.ats.AtsCalendarTemplDetail;

/**
 *<pre>
 * 对象功能:日历模版明细 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 15:45:31
 *</pre>
 */
@Service
public class AtsCalendarTemplDetailService extends  BaseService<AtsCalendarTemplDetail>
{
	@Resource
	private AtsCalendarTemplDetailDao dao;
	
	
	
	public AtsCalendarTemplDetailService()
	{
	}
	
	@Override
	protected IEntityDao<AtsCalendarTemplDetail, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 保存 日历模版明细 信息
	 * @param atsCalendarTemplDetail
	 */
	public void save(AtsCalendarTemplDetail atsCalendarTemplDetail){
		Long id=atsCalendarTemplDetail.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atsCalendarTemplDetail.setId(id);
			this.add(atsCalendarTemplDetail);
		}
		else{
			this.update(atsCalendarTemplDetail);
		}
	}
	
}
