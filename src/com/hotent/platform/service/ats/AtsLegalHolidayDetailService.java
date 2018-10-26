package com.hotent.platform.service.ats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.DateUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsLegalHolidayDetailDao;
import com.hotent.platform.model.ats.AtsLegalHolidayDetail;

/**
 * <pre>
 * 对象功能:法定节假日明细 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 15:48:34
 * </pre>
 */
@Service
public class AtsLegalHolidayDetailService extends
		BaseService<AtsLegalHolidayDetail> {
	@Resource
	private AtsLegalHolidayDetailDao dao;

	public AtsLegalHolidayDetailService() {
	}

	@Override
	protected IEntityDao<AtsLegalHolidayDetail, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 保存 法定节假日明细 信息
	 * 
	 * @param atsLegalHolidayDetail
	 */
	public void save(AtsLegalHolidayDetail atsLegalHolidayDetail) {
		Long id = atsLegalHolidayDetail.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			atsLegalHolidayDetail.setId(id);
			this.add(atsLegalHolidayDetail);
		} else {
			this.update(atsLegalHolidayDetail);
		}
	}

	public List<AtsLegalHolidayDetail> getByHolidayId(Long holidayId) {
		return dao.getByHolidayId(holidayId);
	}

	public List<AtsLegalHolidayDetail> getHolidayListByAttencePolicy(
			Long attencePolicy) {
		return dao.getHolidayListByAttencePolicy(attencePolicy);
	}

	/**
	 * 处理节假日
	 * 
	 * @param attencePolicy
	 * @return
	 */
	public Map<String, String> getHolidayMap(Long attencePolicy) {
		List<AtsLegalHolidayDetail> holidayList = dao
				.getHolidayListByAttencePolicy(attencePolicy);

		Map<String, String> holidayMap = new HashMap<String, String>();
		for (AtsLegalHolidayDetail atsLegalHolidayDetail : holidayList) {
			String[] dates = DateUtil.getDaysBetweenDate(
					atsLegalHolidayDetail.getStartTime(),
					atsLegalHolidayDetail.getEndTime());
			for (String d : dates) {
				holidayMap.put(d, atsLegalHolidayDetail.getName());
			}
		}
		return holidayMap;
	}
}
