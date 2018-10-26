package com.hotent.platform.service.ats;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsLegalHolidayDao;
import com.hotent.platform.dao.ats.AtsLegalHolidayDetailDao;
import com.hotent.platform.model.ats.AtsLegalHoliday;
import com.hotent.platform.model.ats.AtsLegalHolidayDetail;

/**
 *<pre>
 * 对象功能:法定节假日 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 15:48:22
 *</pre>
 */
@Service
public class AtsLegalHolidayService extends  BaseService<AtsLegalHoliday>
{
	@Resource
	private AtsLegalHolidayDao dao;
	@Resource
	private AtsLegalHolidayDetailDao atsLegalHolidayDetailDao;
	
	
	public AtsLegalHolidayService()
	{
	}
	
	@Override
	protected IEntityDao<AtsLegalHoliday, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 保存 法定节假日 信息
	 * @param atsLegalHoliday
	 * @throws Exception 
	 */
	public void save(AtsLegalHoliday atsLegalHoliday) throws Exception{
		Long id=atsLegalHoliday.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atsLegalHoliday.setId(id);
			this.add(atsLegalHoliday);
		}
		else{
			this.update(atsLegalHoliday);
			atsLegalHolidayDetailDao.delByHolidayId(id);
		}
		String detailList = atsLegalHoliday.getDetailList();
		JSONArray jary = JSONArray.fromObject(detailList);
		for (Object obj : jary) {
			JSONObject json = (JSONObject) obj;
			String name = (String) json.get("name");
			String startTime = (String) json.get("startTime");
			String endTime = (String) json.get("endTime");
			AtsLegalHolidayDetail lhd = new AtsLegalHolidayDetail();
			lhd.setName(name);
			lhd.setStartTime(DateFormatUtil.parseDate(startTime));
			lhd.setEndTime(DateFormatUtil.parseDate(endTime));
			lhd.setHolidayId(id);
			lhd.setId(UniqueIdUtil.genId());
			atsLegalHolidayDetailDao.add(lhd);
		}
	}

	public String getDetailList(Long id) {
		List<AtsLegalHolidayDetail> list = atsLegalHolidayDetailDao
				.getByHolidayId(id);
		JSONArray jary = new JSONArray();
		for (AtsLegalHolidayDetail lhd : list) {
			JSONObject json = new JSONObject();
			json.accumulate("name", lhd.getName().toString());
			json.accumulate("startTime",DateFormatUtil.formatDate(lhd.getStartTime()));
			json.accumulate("endTime",DateFormatUtil.formatDate(lhd.getEndTime()));
			jary.add(json);
		}
		return jary.toString();
	}
	
}
