package com.hotent.platform.service.ats;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.DateUtil;
import com.hotent.core.util.StringPool;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.ats.AtsAttenceCalculateDao;
import com.hotent.platform.model.ats.AtsAttenceCalculate;
import com.hotent.platform.model.ats.AtsAttencePolicy;
import com.hotent.platform.model.ats.AtsAttendanceFile;
import com.hotent.platform.model.ats.AtsCardRule;
import com.hotent.platform.model.ats.AtsConstant;
import com.hotent.platform.model.ats.AtsScheduleShift;
import com.hotent.platform.model.ats.AtsShiftInfo;
import com.hotent.platform.model.ats.AtsShiftTime;

/**
 * <pre>
 * 对象功能:考勤计算 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-31 13:51:08
 * </pre>
 */
@Service
public class AtsAttenceCalculateService extends
		BaseService<AtsAttenceCalculate> {
	@Resource
	private AtsAttenceCalculateDao dao;
	@Resource
	private AtsAttencePolicyService atsAttencePolicyService;
	@Resource
	private AtsAttendanceFileService atsAttendanceFileService;
	@Resource
	private AtsScheduleShiftService atsScheduleShiftService;
	@Resource
	private AtsCardRecordService atsCardRecordService;
	@Resource
	private AtsCardRuleService atsCardRuleService;

	public AtsAttenceCalculateService() {
	}

	@Override
	protected IEntityDao<AtsAttenceCalculate, Long> getEntityDao() {
		return dao;
	}

	public List<AtsAttenceCalculate> getByFileIdAttenceTime(Long fileId,
			Date beginattenceTime, Date endattenceTime) {
		return dao.getByFileIdAttenceTime(fileId, beginattenceTime,
				endattenceTime);
	}

	public AtsAttenceCalculate getByFileIdAttenceTime(Long fileId,
			Date attenceTime) {
		return dao.getByFileIdAttenceTime(fileId, attenceTime);
	}

	/**
	 * 保存 考勤计算 信息
	 * 
	 * @param atsAttenceCalculate
	 */
	public void save(AtsAttenceCalculate atsAttenceCalculate) {
		Long fileId = atsAttenceCalculate.getFileId();
		Date attenceTime = atsAttenceCalculate.getAttenceTime();
		AtsAttenceCalculate calculate = dao.getByFileIdAttenceTime(fileId,
				attenceTime);
		if (BeanUtils.isEmpty(calculate)) {
			atsAttenceCalculate.setId(UniqueIdUtil.genId());
			this.add(atsAttenceCalculate);
		} else {
			atsAttenceCalculate.setId(calculate.getId());
			this.update(atsAttenceCalculate);
		}
	}
	private void delCalculate(AtsAttenceCalculate calculate) {
		Long fileId = calculate.getFileId();
		Date attenceTime = calculate.getAttenceTime();
		AtsAttenceCalculate cal = dao.getByFileIdAttenceTime(fileId,
				attenceTime);
		if (BeanUtils.isNotEmpty(cal)) {
			dao.delById(cal.getId());
		}
	}

	public void calculate(Date startTime, Date endTime, Long attendPolicyId,
			Long[] fileIds) {
		AtsAttencePolicy atsAttencePolicy = atsAttencePolicyService
				.getById(attendPolicyId);
		int betweenDays = DateUtil.daysBetween(startTime, endTime);
		for (Long fileId : fileIds) {
			AtsAttendanceFile atsAttendanceFile = atsAttendanceFileService
					.getById(fileId);
			this.calculate(startTime, endTime, betweenDays, atsAttencePolicy,
					atsAttendanceFile);
		}

	}

	/**
	 * 计算所有
	 * 
	 * @param startTime
	 * @param endTime
	 * @param attendPolicyId
	 */
	public void allCalculate(Date startTime, Date endTime, Long attendPolicyId) {
		// 把考勤档案的所有人查出来 TODO 这个数据要好久
		AtsAttencePolicy atsAttencePolicy = atsAttencePolicyService
				.getById(attendPolicyId);
		List<AtsAttendanceFile> list = atsAttendanceFileService
				.getByAttendPolicy(attendPolicyId);
		int betweenDays = DateUtil.daysBetween(startTime, endTime);
		// 取得这个时间段的排班
		for (AtsAttendanceFile atsAttendanceFile : list) {
			this.calculate(startTime, endTime, betweenDays, atsAttencePolicy,
					atsAttendanceFile);
		}
	}

	/**
	 * 考勤计算
	 * 
	 * @param startTime
	 * @param endTime
	 * @param betweenDays
	 * @param atsAttencePolicy
	 * @param atsAttendanceFile
	 */
	public void calculate(Date startTime, Date endTime, int betweenDays,
			AtsAttencePolicy atsAttencePolicy,
			AtsAttendanceFile atsAttendanceFile) {
		Long fileId = atsAttendanceFile.getId();
		Map<String, AtsScheduleShift> scheduleShiftMap = atsScheduleShiftService
				.getByFileIdStartEndTimeMap(fileId, startTime, endTime);
		Map<String, Set<Date>> cardRecordMap = atsCardRecordService
				.getByCardNumberMap(atsAttendanceFile.getCardNumber(),
						startTime, endTime);

		for (int i = 0; i <= betweenDays; i++) {
			AtsAttenceCalculate calculate = new AtsAttenceCalculate();
			Date attenceTime = DateUtil.addDay(startTime, i);
			calculate.setFileId(fileId);
			calculate.setAttenceTime(attenceTime);
			String dateStr = DateFormatUtil.formatDate(attenceTime);
			// 排班
			AtsScheduleShift atsScheduleShift = scheduleShiftMap.get(dateStr);
			if (BeanUtils.isEmpty(atsScheduleShift)) {
				// calculate.setIsScheduleShift(AtsConstant.NO);
				// calculate.setAbsentTime(atsAttencePolicy.getDaysHour());
				// this.save(calculate);
			
				this.delCalculate(calculate);
				continue;
			}
			// 时间类型
			Short dateType = atsScheduleShift.getDateType();
			calculate.setIsScheduleShift(AtsConstant.YES);
			calculate.setDateType(dateType);
			if (dateType.shortValue() == AtsConstant.DATE_TYPE_DAYOFF
					|| dateType.shortValue() == AtsConstant.DATE_TYPE_HOLIDAY) {
				// 这个类型不计算迟到早退等情况，可以计算加班
				if (dateType.shortValue() == AtsConstant.DATE_TYPE_HOLIDAY)
					calculate.setHolidayName(atsScheduleShift.getTitle());
				calculate.setShouldAttenceHours(0d);
				this.save(calculate);
				continue;
			}
			// 排班设置
			AtsShiftInfo shiftInfo = atsScheduleShift.getShiftInfo();
			List<AtsShiftTime> shiftTimeList = shiftInfo.getShiftTimeList();
			String shiftTime = getShiftTime(shiftTimeList);
			// 排班时间
			calculate.setShiftTime(shiftTime);
			// 排班时间
			Map<Short, AtsShiftTime> shiftTimeMap = getShiftTimeMap(shiftTimeList);
			// 取卡规则
			AtsCardRule atsCardRule = shiftInfo.getAtsCardRule();
			// 段次
			Short segmentNum = atsCardRule.getSegmentNum();
			// 打卡记录
			Set<Date> cardRecordSet = cardRecordMap.get(dateStr);
			double shouldAttenceHours = atsAttencePolicy.getDaysHour();
			if (BeanUtils.isEmpty(cardRecordSet)) {// 没有打卡记录计旷工 //如果有请假另算
				calculate.setShouldAttenceHours(shouldAttenceHours);
				calculate.setIsCardRecord(AtsConstant.NO);
				calculate.setAbsentNumber((double) segmentNum);
				calculate.setAbsentTime(atsAttencePolicy.getDaysHour());
				this.save(calculate);
				continue;
			} else {
				calculate.setIsCardRecord(AtsConstant.YES);
				String s = "";
				for (Date date : cardRecordSet) {
					s += DateFormatUtil.format(date,
							StringPool.DATE_FORMAT_TIME) + "|";
				}
				calculate.setCardRecord(s);
			}

			// 一段处理
			if (segmentNum == AtsConstant.SEGMENT_1) {
				handleSegment(calculate, atsAttencePolicy, shiftTimeMap,
						atsCardRule, cardRecordSet, segmentNum);
			} else if (segmentNum == AtsConstant.SEGMENT_2) {
				handleSegment2(calculate, atsAttencePolicy, shiftTimeMap,
						atsCardRule, cardRecordSet);
			} else if (segmentNum == AtsConstant.SEGMENT_3) {
				handleSegment3(calculate, atsAttencePolicy, shiftTimeMap,
						atsCardRule, cardRecordSet);
			}

			double actualAttenceHours = shouldAttenceHours; // 减去旷工，迟到，早退的；
			if (BeanUtils.isNotEmpty(calculate.getAbsentTime()))
				actualAttenceHours = actualAttenceHours
						- calculate.getAbsentTime();
			if (BeanUtils.isNotEmpty(calculate.getLateTime()))
				actualAttenceHours = actualAttenceHours
						- (calculate.getLateTime() / 60);// 减分钟
			if (BeanUtils.isNotEmpty(calculate.getLeaveTime()))
				actualAttenceHours = actualAttenceHours
						- (calculate.getLeaveTime() / 60);// 减分钟
			calculate.setShouldAttenceHours(shouldAttenceHours);
			calculate.setActualAttenceHours(actualAttenceHours);// 实际
			this.save(calculate);

		}
	}



	/**
	 * 上班第一次取卡 有效卡
	 * 
	 * @param cardRecordSet
	 * @param atsCardRule
	 * @param onTime
	 */
	private Date onFirTakeCard(Set<Date> cardRecordSet,
			AtsCardRule atsCardRule, Date onTime) {
		return handrTakeCard(cardRecordSet, onTime,
				atsCardRule.getSegBefFirStartNum(),
				atsCardRule.getSegBefFirEndNum(),
				atsCardRule.getSegBefFirTakeCardType());
	}

	/**
	 * 下班第一次取卡 有效卡
	 * 
	 * @param cardRecordSet
	 * @param atsCardRule
	 * @param onTime
	 */
	private Date offFirTakeCard(Set<Date> cardRecordSet,
			AtsCardRule atsCardRule, Date offTime) {
		return handrTakeCard(cardRecordSet, offTime,
				atsCardRule.getSegAftFirStartNum(),
				atsCardRule.getSegAftFirEndNum(),
				atsCardRule.getSegAftFirTakeCardType());
	}

	/**
	 * 取卡处理
	 * 
	 * @param cardRecordSet
	 * @param time
	 * @param startNum
	 * @param endNum
	 * @param cardType
	 * @return
	 */
	private Date handrTakeCard(Set<Date> cardRecordSet, Date time,
			Double startNum, Double endNum, Short cardType) {
		Date date = null;
		if (BeanUtils.isEmpty(cardRecordSet))
			return date;
		Date adjustStartTime = DateUtil.addMinute(
				DateUtil.addHour(time, -startNum), -1);
		Date adjustEndTime = DateUtil.addMinute(DateUtil.addHour(time, endNum),
				1);
		List<Date> list = new ArrayList<Date>();
		for (Date cardTime : cardRecordSet) {
			if (DateUtil.isBetween(adjustStartTime, adjustEndTime, cardTime))// 取出有效卡
				list.add(cardTime);
		}

		if (BeanUtils.isEmpty(list))
			return date;

		if (cardType == AtsConstant.CARD_FIRST)// 取最早卡
			date = list.get(0);
		else
			date = list.get(list.size() - 1);// 取最晚卡
		return date;
	}

	/**
	 * 处理3段打卡
	 * 
	 * @param calculate
	 * @param atsAttencePolicy
	 * @param shiftTimeMap
	 * @param atsCardRule
	 * @param cardRecordSet
	 */
	private void handleSegment3(AtsAttenceCalculate calculate,
			AtsAttencePolicy atsAttencePolicy,
			Map<Short, AtsShiftTime> shiftTimeMap, AtsCardRule atsCardRule,
			Set<Date> cardRecordSet) {
		handleSegment(calculate, atsAttencePolicy, shiftTimeMap, atsCardRule,
				cardRecordSet, AtsConstant.SEGMENT_1);
		handleSegment(calculate, atsAttencePolicy, shiftTimeMap, atsCardRule,
				cardRecordSet, AtsConstant.SEGMENT_2);
		handleSegment(calculate, atsAttencePolicy, shiftTimeMap, atsCardRule,
				cardRecordSet, AtsConstant.SEGMENT_3);
	}

	/**
	 * 处理2段打卡
	 * 
	 * @param calculate
	 * @param atsAttencePolicy
	 * @param shiftTimeMap
	 * @param atsCardRule
	 * @param cardRecordSet
	 */
	private void handleSegment2(AtsAttenceCalculate calculate,
			AtsAttencePolicy atsAttencePolicy,
			Map<Short, AtsShiftTime> shiftTimeMap, AtsCardRule atsCardRule,
			Set<Date> cardRecordSet) {
		handleSegment(calculate, atsAttencePolicy, shiftTimeMap, atsCardRule,
				cardRecordSet, AtsConstant.SEGMENT_1);

		handleSegment(calculate, atsAttencePolicy, shiftTimeMap, atsCardRule,
				cardRecordSet, AtsConstant.SEGMENT_2);

		// 段内处理
		// handleSegmentInside()

	}

	/**
	 * 一段式处理
	 * 
	 * @param calculate
	 * @param atsAttencePolicy
	 * @param shiftTimeMap
	 * @param startNum
	 * @param endNum
	 * @param cardRecordSet
	 * @return
	 */
	private void handleSegment(AtsAttenceCalculate calculate,
			AtsAttencePolicy atsAttencePolicy,
			Map<Short, AtsShiftTime> shiftTimeMap, AtsCardRule atsCardRule,
			Set<Date> cardRecordSet, short segment) {

		AtsShiftTime atsShiftTime = shiftTimeMap.get(segment);

		// 如果该浮动调整值不为0，且大于迟到、早退允许值，那么该点的迟到、早退允许值以浮动调整值为准；否则仍然以班次中设置的迟到、早退允许值为准；
		// 上班提前几个小时
		Double startNum = atsCardRule.getStartNum();
		// 下班延后几个小时
		Double endNum = atsCardRule.getEndNum();
		// 上班时间
		Date onTime = DateUtil.getTime(calculate.getAttenceTime(),
				atsShiftTime.getOnTime());
		// 下班时间
		Date offTime = DateUtil.getTime(calculate.getAttenceTime(),
				atsShiftTime.getOffTime());

		// 上班提前开始算打卡有效时间
		Date onAdjustStartTime = DateUtil.addMinute(
				DateUtil.addHour(onTime, -startNum), -1);

		// 到上班允许迟到时间
		Date onAdjustEndTime = DateUtil.addMinute(
				onTime,
				getFloatAdjust(atsAttencePolicy.getLateAllow(),
						atsShiftTime.getOnFloatAdjust()) + 1);
		// 旷工
		Date absentTime = DateUtil.addMinute(
				onTime,
				getFloatAdjust(atsAttencePolicy.getAbsentAllow(),
						atsShiftTime.getOnFloatAdjust()) + 1);

		// 早退开始时间
		Date leaveTime = DateUtil.addMinute(offTime,
				-atsAttencePolicy.getLeaveStart() - 1);
		// 早退
		Date offAdjustStartTime = DateUtil.addMinute(
				offTime,
				-getFloatAdjust(atsAttencePolicy.getLeaveAllow(),
						atsShiftTime.getOffFloatAdjust()) - 1);

		// 下班延迟多久算打卡有效时间
		Date offAdjustEndTime = DateUtil.addMinute(
				DateUtil.addHour(offTime, endNum), 1);

		Date onFirDate = null;
		Short onTimeType = AtsConstant.TIME_TYPE_ON;
		//
		if (atsShiftTime.getOnPunchCard() == AtsConstant.YES) {
			// 上班第一次取卡 取出上班的有效卡
			onFirDate = this.onFirTakeCard(cardRecordSet, atsCardRule, onTime);
			onTimeType = this.judgeOnTimeType(onFirDate, onAdjustStartTime,
					onAdjustEndTime, absentTime);
		}
		Date offFirDate = null;
		Short offTimeType = AtsConstant.TIME_TYPE_OFF;
		if (atsShiftTime.getOffPunchCard() == AtsConstant.YES) {
			offFirDate = this.offFirTakeCard(cardRecordSet, atsCardRule,
					offTime);
			offTimeType = this.judgeOffTimeType(offFirDate, offAdjustStartTime,
					offAdjustEndTime, leaveTime);
		}

		// 如果都正常上班，计正常上班
		if (onTimeType == AtsConstant.TIME_TYPE_ON
				&& offTimeType == AtsConstant.TIME_TYPE_OFF) {
			return;
		}
		double segmentRest = atsShiftTime.getSegmentRest();
		// 上、下班都旷工，计一次旷工
		if (onTimeType == AtsConstant.TIME_TYPE_ABSENT
				&& offTimeType == AtsConstant.TIME_TYPE_ABSENT) {
			this.setAbsentCalculate(calculate, segment, onTime, offTime,
					onFirDate, offFirDate, segmentRest);
			return;
		}

		// 上班正常 下班旷工计旷工
		if (onTimeType == AtsConstant.TIME_TYPE_ON
				&& offTimeType == AtsConstant.TIME_TYPE_ABSENT) {
			this.setAbsentCalculate(calculate, segment, onTime, offTime,
					onFirDate, offFirDate, segmentRest);
		}
		// 下班正常 上班旷工计旷工
		else if (onTimeType == AtsConstant.TIME_TYPE_ABSENT
				&& offTimeType == AtsConstant.TIME_TYPE_OFF) {
			this.setAbsentCalculate(calculate, segment, onTime, offTime,
					onFirDate, offFirDate, segmentRest);
		}

		// 上班迟到 计迟到
		if (onTimeType == AtsConstant.TIME_TYPE_LATE) {
			this.setLateCalculate(calculate, segment, onTime, onFirDate);
		}
		// 下班早退计 早退
		if (offTimeType == AtsConstant.TIME_TYPE_LEAVE) {// 早退
			this.setLeaveCalculate(calculate, segment, offTime, offFirDate);
		}

		// 是否计算出来,没有计算这进行第二次取卡

	}

	// 如果该浮动调整值不为0，且大于迟到、早退允许值，那么该点的迟到、早退允许值以浮动调整值为准；否则仍然以班次中设置的迟到、早退允许值为准；
	private Integer getFloatAdjust(Integer num, Double floatAdjust) {
		if (BeanUtils.isEmpty(floatAdjust))
			return num;
		if (floatAdjust > 0 && floatAdjust > num)
			return floatAdjust.intValue();
		return num;
	}

	/**
	 * 设置早退的时间计算
	 * 
	 * @param calculate
	 * @param segment
	 * @param offTime
	 * @param offFirDate
	 */
	private void setLeaveCalculate(AtsAttenceCalculate calculate,
			short segment, Date offTime, Date offFirDate) {
		double leaveNumber = 1d;
		double leaveTime1 = DateUtil.betweenMinute(offFirDate, offTime);
		JSONObject json = new JSONObject();
		json.accumulate("segment", segment)
				.accumulate(
						"date",
						DateFormatUtil.format(offFirDate,
								StringPool.DATE_FORMAT_TIME_NOSECOND))
				.accumulate("time", leaveTime1);
		JSONArray jary = new JSONArray();
		if (segment != AtsConstant.SEGMENT_1) {
			if (BeanUtils.isNotEmpty(calculate.getLeaveNumber()))
				leaveNumber += calculate.getLeaveNumber();
			if (BeanUtils.isNotEmpty(calculate.getLeaveTime()))
				leaveTime1 += calculate.getLeaveTime();
			if (BeanUtils.isNotEmpty(calculate.getLeaveRecord())) {// 取出之前的记录
				JSONArray ary = JSONArray
						.fromObject(calculate.getLeaveRecord());
				for (Object o : ary) {
					jary.add(o);
				}
			}
		}

		jary.add(json);
		calculate.setLeaveNumber(leaveNumber);
		calculate.setLeaveTime(leaveTime1);
		calculate.setLeaveRecord(jary.toString());

	}

	/**
	 * 设置迟到的时间计算
	 * 
	 * @param calculate
	 * @param segment
	 * @param onTime
	 * @param onFirDate
	 */
	private void setLateCalculate(AtsAttenceCalculate calculate, short segment,
			Date onTime, Date onFirDate) {
		double lateNumber = 1d;
		double lateTime = DateUtil.betweenMinute(onTime, onFirDate);
		JSONObject json = new JSONObject();
		json.accumulate("segment", segment)
				.accumulate(
						"date",
						DateFormatUtil.format(onFirDate,
								StringPool.DATE_FORMAT_TIME_NOSECOND))
				.accumulate("time", lateTime);
		JSONArray jary = new JSONArray();
		if (segment != AtsConstant.SEGMENT_1) {
			if (BeanUtils.isNotEmpty(calculate.getLateNumber()))
				lateNumber += calculate.getLateNumber();
			if (BeanUtils.isNotEmpty(calculate.getLateTime()))
				lateTime += calculate.getLateTime();
			if (BeanUtils.isNotEmpty(calculate.getLateRecord())) {// 取出之前的记录
				JSONArray ary = JSONArray.fromObject(calculate.getLateRecord());
				for (Object o : ary) {
					jary.add(o);
				}
			}
		}
		jary.add(json);

		calculate.setLateNumber(lateNumber);
		calculate.setLateTime(lateTime);
		calculate.setLateRecord(jary.toString());
	}

	/**
	 * 设置旷工计算
	 * 
	 * @param calculate
	 * @param segment
	 * @param onTime
	 * @param offTime
	 * @param onFirDate
	 * @param offFirDate
	 * @param double1
	 */
	private void setAbsentCalculate(AtsAttenceCalculate calculate,
			short segment, Date onTime, Date offTime, Date onFirDate,
			Date offFirDate, Double segmentRest) {
		double absentNumber = 1d;
		double absentTime1 = DateUtil.betweenHour(onTime, offTime, segmentRest);
		JSONObject json = new JSONObject();
		if (BeanUtils.isNotEmpty(onFirDate) || BeanUtils.isNotEmpty(offFirDate)) {
			String onCardTime = "";
			String offCardTime = "";
			if (BeanUtils.isNotEmpty(onFirDate))
				onCardTime = DateFormatUtil.format(onFirDate,
						StringPool.DATE_FORMAT_TIME_NOSECOND);
			if (BeanUtils.isNotEmpty(offFirDate))
				offCardTime = DateFormatUtil.format(offFirDate,
						StringPool.DATE_FORMAT_TIME_NOSECOND);
			json.accumulate("segment", segment)
					.accumulate("onCardTime", onCardTime)
					.accumulate("offCardTime", offCardTime)
					.accumulate("time", absentTime1);
		}
		JSONArray jary = new JSONArray();
		if (segment != AtsConstant.SEGMENT_1) {
			if (BeanUtils.isNotEmpty(calculate.getAbsentNumber()))
				absentNumber += calculate.getAbsentNumber();
			if (BeanUtils.isNotEmpty(calculate.getAbsentTime()))
				absentTime1 += calculate.getAbsentTime();
			if (BeanUtils.isNotEmpty(calculate.getAbsentRecord())) {// 取出之前的记录
				JSONArray ary = JSONArray.fromObject(calculate
						.getAbsentRecord());
				for (Object o : ary) {
					jary.add(o);
				}
			}
		}
		jary.add(json);
		calculate.setAbsentNumber(absentNumber);
		calculate.setAbsentTime(absentTime1);
		calculate.setAbsentRecord(jary.toString());
	}

	/**
	 * 判断下班时间
	 * 
	 * @param onCardTime
	 * @param onAdjustStartTime
	 * @param onAdjustEndTime
	 * @param absentTime
	 * @return
	 */
	private Short judgeOnTimeType(Date onCardTime, Date onAdjustStartTime,
			Date onAdjustEndTime, Date absentTime) {
		if (BeanUtils.isEmpty(onCardTime))
			return AtsConstant.TIME_TYPE_ABSENT;
		if (DateUtil.isBetween(onAdjustStartTime, onAdjustEndTime, onCardTime)) {// 计算上班开始时间--上班结束时间这段计为上班有效时间
			return AtsConstant.TIME_TYPE_ON;
		} else if (DateUtil.isBetween(onAdjustEndTime, absentTime, onCardTime)) {// 计算上班结束时间--旷工开始时间这段计为迟到
			return AtsConstant.TIME_TYPE_LATE;
		}
		return AtsConstant.TIME_TYPE_ABSENT;
	}

	private Short judgeOffTimeType(Date onCardTime, Date offAdjustStartTime,
			Date offAdjustEndTime, Date leaveTime) {
		if (BeanUtils.isEmpty(onCardTime))
			return AtsConstant.TIME_TYPE_ABSENT;
		if (DateUtil
				.isBetween(offAdjustStartTime, offAdjustEndTime, onCardTime)) {// 计算早退开始时间
																				// ---下班有效时间；计为下班有效时间
			return AtsConstant.TIME_TYPE_OFF;
		} else if (DateUtil
				.isBetween(leaveTime, offAdjustStartTime, onCardTime)) {// 计算旷工开始时间---早退开始时间；这段计为早退
			return AtsConstant.TIME_TYPE_LEAVE;
		}
		return AtsConstant.TIME_TYPE_ABSENT;
	}

	private String getShiftTime(List<AtsShiftTime> shiftTimeList) {
		JSONArray jary = new JSONArray();
		try {
			for (AtsShiftTime atsShiftTime : shiftTimeList) {
				JSONObject json = new JSONObject();
				Date onTime1 = atsShiftTime.getOnTime();
				Date offTime1 = atsShiftTime.getOffTime();
				String onTime = DateFormatUtil.format(onTime1,
						StringPool.DATE_FORMAT_TIME_NOSECOND);
				String offTime = DateFormatUtil.format(offTime1,
						StringPool.DATE_FORMAT_TIME_NOSECOND);
				json.accumulate("segment", atsShiftTime.getSegment())
						.accumulate("onTime", onTime)
						.accumulate("offTime", offTime);
				jary.add(json);

			}
		} catch (Exception e) {
		}
		return jary.toString();
	}

	private Map<Short, AtsShiftTime> getShiftTimeMap(
			List<AtsShiftTime> shiftTimeList) {
		Map<Short, AtsShiftTime> map = new HashMap<Short, AtsShiftTime>();
		for (AtsShiftTime atsShiftTime : shiftTimeList) {
			map.put(atsShiftTime.getSegment(), atsShiftTime);
		}
		return map;
	}

	public List<AtsAttenceCalculate> getList(QueryFilter filter) {
		return dao.getList(filter);
	}

}
