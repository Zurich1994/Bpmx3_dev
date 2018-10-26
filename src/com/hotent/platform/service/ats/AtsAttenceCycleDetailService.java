package com.hotent.platform.service.ats;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsAttenceCycleDetailDao;
import com.hotent.platform.model.ats.AtsAttenceCycleDetail;

/**
 *<pre>
 * 对象功能:考勤周期明细 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 15:47:49
 *</pre>
 */
@Service
public class AtsAttenceCycleDetailService extends  BaseService<AtsAttenceCycleDetail>
{
	@Resource
	private AtsAttenceCycleDetailDao dao;
	
	
	
	public AtsAttenceCycleDetailService()
	{
	}
	
	@Override
	protected IEntityDao<AtsAttenceCycleDetail, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 保存 考勤周期明细 信息
	 * @param atsAttenceCycleDetail
	 */
	public void save(AtsAttenceCycleDetail atsAttenceCycleDetail){
		Long id=atsAttenceCycleDetail.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atsAttenceCycleDetail.setId(id);
			this.add(atsAttenceCycleDetail);
		}
		else{
			this.update(atsAttenceCycleDetail);
		}
	}

	public List<AtsAttenceCycleDetail> getByCycleId(Long cycleId) {
		return dao.getByCycleId(cycleId);
	}
	
	public List<AtsAttenceCycleDetail> getByCycleId(Long cycleId,boolean isCal) {
		
		List<AtsAttenceCycleDetail> list =  dao.getByCycleId(cycleId);
		if(!isCal)
			return list;
		for (AtsAttenceCycleDetail acd : list) {
			acd.setMemo(acd.getName()+"(" +DateFormatUtil.formatDate(acd.getStartTime())+"--" +DateFormatUtil.formatDate(acd.getEndTime())+")");
		}
		return list;
	}
	
}
