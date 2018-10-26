package com.hotent.platform.service.ats;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.StringPool;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsShiftInfoDao;
import com.hotent.platform.dao.ats.AtsShiftTimeDao;
import com.hotent.platform.model.ats.AtsCardRule;
import com.hotent.platform.model.ats.AtsHolidayPolicy;
import com.hotent.platform.model.ats.AtsShiftInfo;
import com.hotent.platform.model.ats.AtsShiftTime;

/**
 * <pre>
 * 对象功能:班次设置 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-18 17:21:46
 * </pre>
 */
@Service
public class AtsShiftInfoService extends BaseService<AtsShiftInfo> {
	@Resource
	private AtsShiftInfoDao dao;
	@Resource
	private AtsShiftTimeDao atsShiftTimeDao;

	@Resource
	private AtsCardRuleService atsCardRuleService;

	public AtsShiftInfoService() {
	}

	@Override
	protected IEntityDao<AtsShiftInfo, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 保存 班次设置 信息
	 * 
	 * @param atsShiftInfo
	 * @throws Exception
	 */
	public void save(AtsShiftInfo atsShiftInfo) throws Exception {
		Long id = atsShiftInfo.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			atsShiftInfo.setId(id);
			this.add(atsShiftInfo);
		} else {
			this.update(atsShiftInfo);
			atsShiftTimeDao.delByShiftId(id);
		}

		String detailList = atsShiftInfo.getDetailList();
		if (BeanUtils.isEmpty(detailList))
			return;
		JSONArray jary = JSONArray.fromObject(detailList);
		for (Object obj : jary) {
			JSONObject json = (JSONObject) obj;
			String segment = (String) json.get("segment");
			String attendanceType = (String) json.get("attendanceType");
			String onTime = (String) json.get("onTime");
			String onPunchCard = (String) json.get("onPunchCard");
			String onFloatAdjust = (String) json.get("onFloatAdjust");
			String segmentRest = (String) json.get("segmentRest");
			String offTime = (String) json.get("offTime");
			String offPunchCard = (String) json.get("offPunchCard");
			String offFloatAdjust = (String) json.get("offFloatAdjust");

			AtsShiftTime ast = new AtsShiftTime();
			ast.setSegment(Short.valueOf(segment));
			ast.setAttendanceType(Short.valueOf(attendanceType));
			ast.setOnTime(DateFormatUtil.parse(onTime,
					StringPool.DATE_FORMAT_TIME_NOSECOND));
			ast.setOnPunchCard(Short.valueOf(onPunchCard));
			ast.setOnFloatAdjust(StringUtils.isEmpty(onFloatAdjust) ? 0
					: Double.valueOf(onFloatAdjust));
			ast.setSegmentRest(StringUtils.isEmpty(segmentRest) ? 0 : Double
					.valueOf(segmentRest));
			ast.setOffTime(DateFormatUtil.parse(offTime,
					StringPool.DATE_FORMAT_TIME_NOSECOND));
			ast.setOffPunchCard(Short.valueOf(offPunchCard));
			ast.setOffFloatAdjust(StringUtils.isEmpty(offFloatAdjust) ? 0
					: Double.valueOf(offFloatAdjust));

			ast.setShiftId(id);
			ast.setId(UniqueIdUtil.genId());
			atsShiftTimeDao.add(ast);
		}
	}

	public String getDetailList(Long id) {
		List<AtsShiftTime> list = atsShiftTimeDao.getByShiftId(id);
		JSONArray jary = new JSONArray();
		for (AtsShiftTime ast : list) {
			JSONObject json = new JSONObject();
			json.accumulate("segment", String.valueOf(ast.getSegment()));
			json.accumulate("attendanceType",
					String.valueOf(ast.getAttendanceType()));
			json.accumulate("onTime",
					DateFormatUtil.format(ast.getOnTime(), "HH:mm"));
			json.accumulate("onPunchCard", String.valueOf(ast.getOnPunchCard()));
			json.accumulate("onFloatAdjust",
					String.valueOf(ast.getOnFloatAdjust()));
			json.accumulate("segmentRest", String.valueOf(ast.getSegmentRest()));
			json.accumulate("offTime",
					DateFormatUtil.format(ast.getOffTime(), "HH:mm"));
			json.accumulate("offPunchCard",
					String.valueOf(ast.getOffPunchCard()));
			json.accumulate("offFloatAdjust",
					String.valueOf(ast.getOffFloatAdjust()));
			jary.add(json);
		}
		return jary.toString();
	}

	public AtsShiftInfo getShiftInfoById(Long id) {
		AtsShiftInfo atsShiftInfo = this.getById(id);
		List<AtsShiftTime> shiftTimeList = atsShiftTimeDao.getByShiftId(id);
		if (BeanUtils.isNotEmpty(atsShiftInfo)) {
			AtsCardRule atsCardRule = atsCardRuleService.getById(atsShiftInfo
					.getCardRule());
			atsShiftInfo.setAtsCardRule(atsCardRule);
			atsShiftInfo.setShiftTimeList(shiftTimeList);
		}
		return atsShiftInfo;

	}

	public AtsShiftInfo getByShiftName(String shiftName) {
		return dao.getByShiftName(shiftName);
	}

	public AtsShiftInfo getByDefault() {
		return dao.getByDefault();
	}

}
