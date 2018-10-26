package com.hotent.platform.service.ats;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsCalendarTemplDao;
import com.hotent.platform.dao.ats.AtsCalendarTemplDetailDao;
import com.hotent.platform.dao.ats.AtsWorkCalendarDao;
import com.hotent.platform.model.ats.AtsCalendarTempl;
import com.hotent.platform.model.ats.AtsCalendarTemplDetail;
import com.hotent.platform.model.ats.AtsConstant;
import com.hotent.platform.model.ats.AtsWorkCalendar;

/**
 * <pre>
 * 对象功能:日历模版 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 15:44:41
 * </pre>
 */
@Service
public class AtsCalendarTemplService extends BaseService<AtsCalendarTempl> {
	@Resource
	private AtsCalendarTemplDao dao;

	@Resource
	private AtsCalendarTemplDetailDao atsCalendarTemplDetailDao;

	@Resource
	private AtsWorkCalendarDao atsWorkCalendarDao;
	
	public AtsCalendarTemplService() {
	}

	@Override
	protected IEntityDao<AtsCalendarTempl, Long> getEntityDao() {
		return dao;
	}
	
	
	/**
	 * 删除数据
	 * @param ids
	 * @return
	 */
	public String delDataByIds(Long[] ids) {
		//判断是否使用
		String msg = "";
		for (Long id : ids) {
			List<AtsWorkCalendar> list = atsWorkCalendarDao.getByCalendarTempl(id);
			if(BeanUtils.isNotEmpty(list)){
				msg +="已经有使用,不允许删除";
				return msg;		
			}
		}
	
		this.delByIds(ids);
		return msg;
	}

	/**
	 * 保存 日历模版 信息
	 * 
	 * @param atsCalendarTempl
	 */
	public void save(AtsCalendarTempl atsCalendarTempl) {
		Long id = atsCalendarTempl.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			atsCalendarTempl.setId(id);
			atsCalendarTempl.setIsSys(AtsConstant.NO);
			this.add(atsCalendarTempl);
		} else {
			this.update(atsCalendarTempl);
			atsCalendarTemplDetailDao.delByCalendarId(id);
		}
		String detailList = atsCalendarTempl.getDetailList();
		JSONArray jary = JSONArray.fromObject(detailList);
		for (Object obj : jary) {
			JSONObject json = (JSONObject) obj;
			String week = (String) json.get("week");
			String dayType = (String) json.get("dayType");
			AtsCalendarTemplDetail ctd = new AtsCalendarTemplDetail();
			ctd.setWeek(Short.parseShort(week));
			ctd.setDayType(Short.parseShort(dayType));
			ctd.setCalendarId(id);
			ctd.setId(UniqueIdUtil.genId());
			atsCalendarTemplDetailDao.add(ctd);
		}

	}

	public String getDetailList(Long id) {
		List<AtsCalendarTemplDetail> list = atsCalendarTemplDetailDao
				.getByCalendarId(id);
		JSONArray jary = new JSONArray();
		for (AtsCalendarTemplDetail ctd : list) {
			JSONObject json = new JSONObject();
			json.accumulate("week", ctd.getWeek().toString());
			json.accumulate("dayType", ctd.getDayType().toString());
			jary.add(json);
		}
		return jary.toString();
	}

}
