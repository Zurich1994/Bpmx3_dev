package com.hotent.platform.service.ats;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.StringPool;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsShiftTimeDao;
import com.hotent.platform.model.ats.AtsShiftTime;

/**
 *<pre>
 * 对象功能:班次时间设置 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-19 14:05:03
 *</pre>
 */
@Service
public class AtsShiftTimeService extends  BaseService<AtsShiftTime>
{
	@Resource
	private AtsShiftTimeDao dao;
	
	
	
	public AtsShiftTimeService()
	{
	}
	
	@Override
	protected IEntityDao<AtsShiftTime, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 保存 班次时间设置 信息
	 * @param atsShiftTime
	 */
	public void save(AtsShiftTime atsShiftTime){
		Long id=atsShiftTime.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atsShiftTime.setId(id);
			this.add(atsShiftTime);
		}
		else{
			this.update(atsShiftTime);
		}
	}

	public String getShiftTime(Long shiftId) {
		List<AtsShiftTime>  list = dao.getByShiftId(shiftId);
		Date onTime = null;
		Date offTime = null;
		for (AtsShiftTime atsShiftTime : list) {
			Date onTime1 = atsShiftTime.getOnTime();
			if(BeanUtils.isEmpty(onTime)){
				onTime = onTime1;
			}else{
				if(onTime1.compareTo(onTime) < 0)
					onTime = onTime1;
			}
			
			Date offTime1 = atsShiftTime.getOffTime();
			if(BeanUtils.isEmpty(offTime)){
				offTime = offTime1;
			}else{
				if(offTime1.compareTo(offTime) > 0)
					offTime = offTime1;
			}
		}
		return DateFormatUtil.format(onTime, StringPool.DATE_FORMAT_TIME_NOSECOND) +"~"+DateFormatUtil.format(offTime,StringPool.DATE_FORMAT_TIME_NOSECOND);
	}
	
}
