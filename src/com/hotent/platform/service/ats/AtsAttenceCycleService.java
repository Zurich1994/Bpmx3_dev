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
import com.hotent.platform.dao.ats.AtsAttenceCycleDao;
import com.hotent.platform.dao.ats.AtsAttenceCycleDetailDao;
import com.hotent.platform.model.ats.AtsAttenceCycle;
import com.hotent.platform.model.ats.AtsAttenceCycleDetail;

/**
 * <pre>
 * 对象功能:考勤周期 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 22:03:30
 * </pre>
 */
@Service
public class AtsAttenceCycleService extends BaseService<AtsAttenceCycle> {
	@Resource
	private AtsAttenceCycleDao dao;
	@Resource
	private AtsAttenceCycleDetailDao atsAttenceCycleDetailDao;

	public AtsAttenceCycleService() {
	}

	@Override
	protected IEntityDao<AtsAttenceCycle, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 保存 考勤周期 信息
	 * 
	 * @param atsAttenceCycle
	 * @throws Exception
	 */
	public void save(AtsAttenceCycle atsAttenceCycle) throws Exception {
		Long id = atsAttenceCycle.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			atsAttenceCycle.setId(id);
			this.add(atsAttenceCycle);
		} else {
			this.update(atsAttenceCycle);
			atsAttenceCycleDetailDao.delByCycleId(id);
		}

		String detailList = atsAttenceCycle.getDetailList();
		JSONArray jary = JSONArray.fromObject(detailList);
		for (Object obj : jary) {
			JSONObject json = (JSONObject) obj;
			String name = (String) json.get("name");
			String startTime = (String) json.get("startTime");
			String endTime = (String) json.get("endTime");
			AtsAttenceCycleDetail acd = new AtsAttenceCycleDetail();
			acd.setName(name);
			acd.setStartTime(DateFormatUtil.parseDate(startTime));
			acd.setEndTime(DateFormatUtil.parseDate(endTime));
			acd.setCycleId(id);
			acd.setId(UniqueIdUtil.genId());
			atsAttenceCycleDetailDao.add(acd);
		}
	}

	public String getDetailList(Long id) {
		List<AtsAttenceCycleDetail> list = atsAttenceCycleDetailDao
				.getByCycleId(id);
		JSONArray jary = new JSONArray();
		for (AtsAttenceCycleDetail acd : list) {
			JSONObject json = new JSONObject();
			json.accumulate("name", acd.getName().toString());
			json.accumulate("startTime",
					DateFormatUtil.formatDate(acd.getStartTime()));
			json.accumulate("endTime",
					DateFormatUtil.formatDate(acd.getEndTime()));
			jary.add(json);
		}
		return jary.toString();
	}

}
