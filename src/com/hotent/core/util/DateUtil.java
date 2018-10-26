package com.hotent.core.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {

	private static final Log logger = LogFactory.getLog(DateUtil.class);

	/**
	 * 设置当前时间为当天的最初时间（即00时00分00秒）
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar setStartDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal;
	}

	public static Calendar setEndDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal;
	}

	/**
	 * 把源日历的年月日设置到目标日历对象中
	 * 
	 * @param destCal
	 * @param sourceCal
	 */
	public static void copyYearMonthDay(Calendar destCal, Calendar sourceCal) {
		destCal.set(Calendar.YEAR, sourceCal.get(Calendar.YEAR));
		destCal.set(Calendar.MONTH, sourceCal.get(Calendar.MONTH));
		destCal.set(Calendar.DAY_OF_MONTH, sourceCal.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 格式化日期为
	 * 
	 * @return
	 */
	public static String formatEnDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

		return sdf.format(date).replaceAll("上午", "AM").replaceAll("下午", "PM");
	}

	public static Date parseDate(String dateString) {
		Date date = null;
		try {
			date = DateUtils.parseDate(dateString, new String[] {
					StringPool.DATE_FORMAT_DATETIME,
					StringPool.DATE_FORMAT_DATE,
					StringPool.DATE_FORMAT_DATETIME_NOSECOND,
					StringPool.DATE_FORMAT_TIME,
					StringPool.DATE_FORMAT_TIME_NOSECOND,
					StringPool.DATE_FORMAT_TIMESTAMP });
		} catch (Exception ex) {
			logger.error("Pase the Date(" + dateString + ") occur errors:"
					+ ex.getMessage());
		}
		return date;
	}

	/**
	 * 日期加N天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DAY_OF_MONTH, day);
		return ca.getTime();
	}

	/**
	 * 日期加N天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addOneDay(Date date) {
		return addDay(date, 1);
	}

	/**
	 * 日期加一天
	 * 
	 * @param date
	 * @return
	 */
	public static String addOneDay(String date) {
		DateFormat format = new SimpleDateFormat(StringPool.DATE_FORMAT_DATE);
		Calendar calendar = Calendar.getInstance();
		try {
			Date dd = format.parse(date);
			calendar.setTime(dd);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String tmpDate = format.format(calendar.getTime());
		return tmpDate.substring(5, 7) + "/" + tmpDate.substring(8, 10) + "/"
				+ tmpDate.substring(0, 4);
	}

	/**
	 * 加一小时
	 * 
	 * @param date
	 * @return
	 */
	public static String addOneHour(String date) {

		String amPm = date.substring(20, 22);

		DateFormat format = new SimpleDateFormat(
				StringPool.DATE_FORMAT_DATETIME);
		Calendar calendar = Calendar.getInstance();

		int hour = Integer.parseInt(date.substring(11, 13));
		try {
			if (amPm.equals("PM")) {
				hour += 12;
			}
			date = date.substring(0, 11) + (hour >= 10 ? hour : "0" + hour)
					+ date.substring(13, 19);
			Date dd = format.parse(date);
			calendar.setTime(dd);
			calendar.add(Calendar.HOUR_OF_DAY, 1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String tmpDate = format.format(calendar.getTime());

		hour = Integer.parseInt(tmpDate.substring(11, 13));
		amPm = hour >= 12 && hour != 0 ? "PM" : "AM";
		if (amPm.equals("PM")) {
			hour -= 12;
		}
		tmpDate = tmpDate.substring(5, 7) + "/" + tmpDate.substring(8, 10)
				+ "/" + tmpDate.substring(0, 4) + " "
				+ (hour >= 10 ? hour : "0" + hour)
				+ tmpDate.substring(13, tmpDate.length()) + " " + amPm;

		return tmpDate;
	}

	/**
	 * 标准时间格式转为时间字符格式
	 * 
	 * @param timeStr
	 *            eg:Mon Feb 06 00:00:00 CST 2012
	 * @return
	 */
	public static String timeStrToDateStr(String timeStr) {

		String dateStr = timeStr.substring(24, 28) + "-";

		String mon = timeStr.substring(4, 7);
		if (mon.equals("Jan")) {
			dateStr += "01";
		} else if (mon.equals("Feb")) {
			dateStr += "02";
		} else if (mon.equals("Mar")) {
			dateStr += "03";
		} else if (mon.equals("Apr")) {
			dateStr += "04";
		} else if (mon.equals("May")) {
			dateStr += "05";
		} else if (mon.equals("Jun")) {
			dateStr += "06";
		} else if (mon.equals("Jul")) {
			dateStr += "07";
		} else if (mon.equals("Aug")) {
			dateStr += "08";
		} else if (mon.equals("Sep")) {
			dateStr += "09";
		} else if (mon.equals("Oct")) {
			dateStr += "10";
		} else if (mon.equals("Nov")) {
			dateStr += "11";
		} else if (mon.equals("Dec")) {
			dateStr += "12";
		}

		dateStr += "-" + timeStr.substring(8, 10);

		return dateStr;
	}

	/**
	 * 根据日期得到星期多余天数
	 * 
	 * @param sDate
	 * @return
	 */
	public static int getExtraDayOfWeek(String sDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					StringPool.DATE_FORMAT_DATE);
			Date date = format.parse(sDate);
			String weekday = date.toString().substring(0, 3);
			if (weekday.equals("Mon")) {
				return 1;
			} else if (weekday.equals("Tue")) {
				return 2;
			} else if (weekday.equals("Wed")) {
				return 3;
			} else if (weekday.equals("Thu")) {
				return 4;
			} else if (weekday.equals("Fri")) {
				return 5;
			} else if (weekday.equals("Sat")) {
				return 6;
			} else {
				return 0;
			}

		} catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * 根据日期得到星期多余天数
	 * 
	 * @param sDate
	 * @return
	 */
	public static String getDateWeekDay(String sDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					StringPool.DATE_FORMAT_DATE);
			Date date = format.parse(sDate);
			String weekday = date.toString().substring(0, 3);
			// format.format(date)+" "+
			return weekday;

		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 取得上下五年
	 * 
	 * @param cal
	 * @return
	 */
	public static List<String> getUpDownFiveYear(Calendar cal) {
		List<String> yearlist = new ArrayList<String>();

		int curyear = cal.get(Calendar.YEAR);
		yearlist.add(String.valueOf(curyear - 2));
		yearlist.add(String.valueOf(curyear - 1));
		yearlist.add(String.valueOf(curyear));
		yearlist.add(String.valueOf(curyear + 1));
		yearlist.add(String.valueOf(curyear + 2));

		return yearlist;
	}

	/**
	 * 取得12个月
	 * 
	 * @param cal
	 * @return
	 */
	public static List<String> getTwelveMonth() {
		List<String> monthlist = new ArrayList<String>();

		for (int idx = 1; idx <= 12; idx++) {
			monthlist.add(String.valueOf(idx));
		}

		return monthlist;
	}

	/**
	 * 得到两日期间所有日期
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public static String[] getDaysBetweenDate(Date startTime, Date endTime) {
		String[] dateArr = null;
		try {
			Integer day = daysBetween(startTime, endTime);

			dateArr = new String[day + 1];
			for (int i = 0; i < dateArr.length; i++) {
				if (i == 0) {
					dateArr[i] = DateFormatUtil.formatDate(startTime);
				} else {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(startTime);
					calendar.add(Calendar.DAY_OF_MONTH, i);
					dateArr[i] = DateFormatUtil.formatDate(calendar.getTime());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateArr;

	}

	/**
	 * 得到两日期间所有日期
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public static String[] getDaysBetweenDate(String startTime, String endTime) {

		String[] dateArr = null;
		try {

			String stime = timeStrToDateStr(startTime);
			String etime = timeStrToDateStr(endTime);

			// 日期相减算出秒的算法
			Date date1 = new SimpleDateFormat(StringPool.DATE_FORMAT_DATE)
					.parse(stime);
			Date date2 = new SimpleDateFormat(StringPool.DATE_FORMAT_DATE)
					.parse(etime);

			long day = (date1.getTime() - date2.getTime())
					/ (24 * 60 * 60 * 1000) > 0 ? (date1.getTime() - date2
					.getTime()) / (24 * 60 * 60 * 1000)
					: (date2.getTime() - date1.getTime())
							/ (24 * 60 * 60 * 1000);

			dateArr = new String[Integer.valueOf(String.valueOf(day + 1))];
			for (int idx = 0; idx < dateArr.length; idx++) {
				if (idx == 0) {
					dateArr[idx] = stime;
				} else {
					stime = addOneDay(stime);
					stime = stime.substring(6, 10) + "-"
							+ stime.substring(0, 2) + "-"
							+ stime.substring(3, 5);
					dateArr[idx] = stime;
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateArr;
	}

	/**
	 * 获取给定时间所在月的第一天的日期
	 * 
	 * @param calendar
	 * @return 这个月的第一天的日期
	 */
	public static Date getMonthStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 得到当天是这月的第几天
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		// 减去dayOfMonth,得到第一天的日期，因为Calendar用０代表每月的第一天，所以要减一
		calendar.add(Calendar.DAY_OF_MONTH, -(dayOfMonth - 1));
		Date firstDateOfMonth = calendar.getTime();
		return firstDateOfMonth;
	}

	/**
	 * 获取给定时间所在月的最后一天的日期
	 * 
	 * @param calendar
	 * @return 这个月的最后一天的日期
	 */
	public static Date getMonthEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 得到当天是这月的第几天
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		// calendar.getActualMaximum(Calendar.DAY_OF_MONTH)得到这个月有几天
		calendar.add(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - dayOfMonth);
		Date lastDateOfMonth = calendar.getTime();
		return lastDateOfMonth;
	}

	/**
	 * 获取给定时间所在月的第一天的日期的开始时间
	 * 
	 * @param calendar
	 * @return 这个月的第一天的日期的开始时间
	 */
	public static Date getMonthStartDateTime(Date date) {
		Date firstDateOfMonth = getMonthStartDate(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDateOfMonth);
		calendar = setStartDay(calendar);
		return calendar.getTime();
	}

	/**
	 * 获取给定时间所在月的最后一天的日期结束时间
	 * 
	 * @param calendar
	 * @return 这个月的最后一天的日期的结束时间
	 */
	public static Date getMonthEndDateTime(Date date) {
		Date endDateOfMonth = getMonthEndDate(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDateOfMonth);
		calendar = setEndDay(calendar);
		return calendar.getTime();
	}

	/**
	 * 获取给定时间所在周的第一天的（星期一）
	 * 
	 * @param calendar
	 * @return 这周的第一天的日期（星期一）
	 */
	public static Date getWeekStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		return calendar.getTime();
	}

	/**
	 * 获取给定时间所在周的最后一天的日期（星期日）
	 * 
	 * @param calendar
	 * @return 这个周的最后一天的日期（星期日）
	 */
	public static Date getWeekEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		return calendar.getTime();
	}

	/**
	 * 获取给定时间所在周的第一天的日期的开始时间（星期一）
	 * 
	 * @param calendar
	 * @return 这个周的第一天的日期的开始时间（星期一）
	 */
	public static Date getWeekStartDateTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		calendar = setStartDay(calendar);
		return calendar.getTime();
	}

	/**
	 * 获取给定时间所在周的最后一天的日期结束时间（星期日）
	 * 
	 * @param calendar
	 * @return 这个周的最后一天的日期的结束时间（星期日）
	 */
	public static Date getWeekEndDateTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		calendar = setEndDay(calendar);
		return calendar.getTime();
	}

	/**
	 * 返回星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 获取两个时间的相差的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return 获取两个时间的相差的天数
	 */
	public static int daysBetween(Date startDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long startTime = cal.getTimeInMillis();
		cal.setTime(endDate);
		long endTime = cal.getTimeInMillis();
		long between_days = (endTime - startTime) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 根据时间单位，时间间隔，当前时间计算出指定时间加上时间间隔后的时间
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date getNextTime(Date date, int field, int amount) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(field, amount);
		return ca.getTime();
	}

	/**
	 * 指定的时间加或减少几个小时
	 * 
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date date, int hour) {
		return getNextTime(date, Calendar.HOUR_OF_DAY, hour);
	}

	/**
	 * 指定的时间加或减少几个小时
	 * 
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date date, double time) {
		int hour = (int) time;
		double minute1 = (time - hour) * 60;
		int minute = (int) minute1;
		int second = (int) ((minute1 - minute) * 60);
		Date dateHour = getNextTime(date, Calendar.HOUR_OF_DAY, hour);
		Date dateMinute = getNextTime(dateHour, Calendar.MINUTE, minute);
		return getNextTime(dateMinute, Calendar.SECOND, second);
	}

	/**
	 * 指定的时间加或减少几个分钟
	 * 
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addMinute(Date date, int minute) {
		return getNextTime(date, Calendar.MINUTE, minute);
	}

	public static boolean isBetween(Date startTime, Date endTime, Date date) {
		if (date.after(startTime) && date.before(endTime))
			return true;
		return false;
	}

	public static double betweenMinute(Date startTime, Date endTime) {
		Long seconds = (endTime.getTime() - startTime.getTime()) / 1000;
		double s = ((double) seconds % 60 / 60);
		DecimalFormat df = new DecimalFormat("0.00");
		double sec = Double.parseDouble(df.format(s));
		Long minute = seconds / 60;
		return minute + sec;
	}

	public static double betweenHour(Date startTime, Date endTime) {
		Long minutes = (endTime.getTime() - startTime.getTime()) / 1000 / 60;
		double m = ((double) minutes % 60 / 60);
		DecimalFormat df = new DecimalFormat("0.00");
		double min = Double.parseDouble(df.format(m));

		Long hour = minutes / 60;
		return hour + min;
	}

	public static double betweenHour(Date startTime, Date endTime,
			Double restMinutes) {
		Long minutes = (endTime.getTime() - startTime.getTime()) / 1000 / 60;
		Long ms = (long) (minutes - restMinutes);

		double m = ((double) ms % 60 / 60);
		DecimalFormat df = new DecimalFormat("0.00");
		double min = Double.parseDouble(df.format(m));

		Long hour = ms / 60;
		return hour + min;
	}

	public static Date getTime(Date date, Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar ca = Calendar.getInstance();
		ca.setTime(time);
		ca.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DAY_OF_MONTH));
		return ca.getTime();

	}

}
