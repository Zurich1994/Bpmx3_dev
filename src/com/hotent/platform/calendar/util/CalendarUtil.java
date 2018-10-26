package com.hotent.platform.calendar.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.DateUtil;
import com.hotent.platform.calendar.model.CalendarData;

/**
 * 日历帮助类
 * 
 * @author zxh
 * 
 */
public class CalendarUtil {

	/**
	 * 
	 * 设置日历的数据
	 * 
	 * @param calendarData
	 * @param mode
	 * @param nowDate
	 * @param startDate
	 * @param endDate
	 *            void
	 * @since 1.0.0
	 */
	public static void setCalendarData(CalendarData calendarData, String mode,
			Date nowDate, Date startDate, Date endDate) {
		Date startTime = (startDate == null ? nowDate : startDate);
		Date endTime = (endDate == null && "month".equals(mode) ? startTime
				: endDate);

		String sTime = DateUtil.formatEnDate(startTime);
		String eTime = endTime == null ? "" : DateUtil.formatEnDate(endTime);

		String eTime0 = "";
		if ("month".equals(mode)
				&& sTime.substring(0, 10).equals(eTime.substring(0, 10))) {
			String[] dateArr = sTime.substring(0, 10).split("/");
			eTime0 = DateUtil.addOneDay(dateArr[2] + "-" + dateArr[0] + "-"
					+ dateArr[1])
					+ " 00:00:00 AM";
		}

		if (!"month".equals(mode)) {
			String[] dateArr = sTime.substring(0, 10).split("/");
			eTime0 = DateUtil.addOneHour(dateArr[2] + "-" + dateArr[0] + "-"
					+ dateArr[1] + sTime.substring(10, sTime.length()));
		}

		if ("month".equals(mode))
			calendarData.setStartTime(sTime.substring(0, 10) + " 00:00:00 AM");
		else
			calendarData.setStartTime(sTime);

		if (!eTime0.equals(""))
			calendarData.setEndTime(eTime0);
		else
			calendarData.setEndTime(eTime);
	}

	public static Map<String, Object> getCalendarMap(Map<String, Object> map) {
		String mode = (String) map.get("mode");
		String sDate = (String) map.get("startDate");
		String eDate = (String) map.get("endDate");

		Date startDate = null;
		Date endDate = null;

		if ("month".equals(mode)) {
			try {
				Date reqDate = DateUtils.parseDate(sDate,
						new String[] { "MM/dd/yyyy" });
				Calendar cal = Calendar.getInstance();
				cal.setTime(reqDate);
				startDate = DateUtil.setStartDay(cal).getTime();
				reqDate = DateUtils.parseDate(eDate,
						new String[] { "MM/dd/yyyy" });
				cal.setTime(reqDate);
				endDate = DateUtil.setEndDay(cal).getTime();

			} catch (Exception ex) {
				// logger.error(ex.getMessage());
			}

		} else if ("day".equals(mode)) {
			try {
				Date reqDay = DateUtils.parseDate(sDate,
						new String[] { "MM/dd/yyyy" });

				Calendar cal = Calendar.getInstance();
				cal.setTime(reqDay);

				// 开始日期为本月1号 00时00分00秒
				startDate = DateUtil.setStartDay(cal).getTime();

				cal.add(Calendar.MONTH, 1);
				cal.add(Calendar.DAY_OF_MONTH, -1);

				// 结束日期为本月最后一天的23时59分59秒
				endDate = DateUtil.setEndDay(cal).getTime();

			} catch (Exception ex) {
				// logger.error(ex.getMessage());
			}

		} else if ("week".equals(mode)) {

			try {
				Date reqStartWeek = DateUtils.parseDate(sDate,
						new String[] { "MM/dd/yyyy" });
				Date reqEndWeek = DateUtils.parseDate(eDate,
						new String[] { "MM/dd/yyyy" });
				Calendar cal = Calendar.getInstance();

				cal.setTime(reqStartWeek);

				startDate = DateUtil.setStartDay(cal).getTime();
				cal.setTime(reqEndWeek);

				endDate = DateUtil.setEndDay(cal).getTime();

			} catch (Exception ex) {
				// logger.error(ex.getMessage());
			}

		} else if ("workweek".equals(mode)) {
			try {
				Date reqStartWeek = DateUtils.parseDate(sDate,
						new String[] { "MM/dd/yyyy" });
				Date reqEndWeek = DateUtils.parseDate(eDate,
						new String[] { "MM/dd/yyyy" });
				Calendar cal = Calendar.getInstance();

				cal.setTime(reqStartWeek);

				startDate = DateUtil.setStartDay(cal).getTime();
				cal.setTime(reqEndWeek);

				endDate = DateUtil.setEndDay(cal).getTime();

			} catch (Exception ex) {
				// logger.error(ex.getMessage());
			}
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", ContextUtil.getCurrentUserId());
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		return params;
	}

}
