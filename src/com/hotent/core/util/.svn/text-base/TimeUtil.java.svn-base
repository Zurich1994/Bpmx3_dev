package com.hotent.core.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间操作类
 * 
 * @version
 */
public class TimeUtil {
	public final static int SECOND = 0;
	public final static int MINUTE = 1;
	public final static int HOUR = 2;
	public final static int DAY = 3;
	public final static int MONTH = 4;

	/**
	 * 将字符串转固定格式的日期类型
	 * 
	 * @param value
	 * @param format
	 * @return
	 */
	public static Date convertString(String value, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (value == null)
			return null;
		try {
			return sdf.parse(value);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将字符串转日期类型，格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param value
	 * @return
	 */
	public static Date convertString(String value) {
		return convertString(value, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 将字符串转日期类型，格式yyyy-MM-dd
	 * @param value
	 * @return
	 */
	public static Date convertDateString(String value) {
		return convertString(value, "yyyy-MM-dd");
	}

	/**
	 * 得到当前时间，例如"2002-11-06 17:08:59"
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		return formatter.format(date);
	}

	/**
	 * 根据长整型毫秒数返回时间形式:如:2007-01-23 13:45:21
	 * 
	 * @param millseconds
	 * @return
	 */
	public static String getDateTimeString(long millseconds) {

		return getDate(millseconds, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 取得yyyyMMdd格式的时间
	 * 
	 * @param time
	 * @return
	 */
	public static String getDayDate(long time) {
		return getDate(time, "yyyyMMdd");
	}

	public static String getDate(long time, String format) {
		java.text.SimpleDateFormat formater = new SimpleDateFormat(format);
		return formater.format(new Date(time));
	}

	/**
	 * 取得时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateTimeString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 取得时间
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateTimeString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 比较两个时间大小 结束时间是否大于开始时间
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean isTimeLarge(String startTime, String endTime) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			ParsePosition pos1 = new ParsePosition(0);
			Date dt1 = formatter.parse(startTime, pos);
			Date dt2 = formatter.parse(endTime, pos1);
			long lDiff = dt2.getTime() - dt1.getTime();
			return lDiff > 0;
		} catch (Exception e) {

			return false;
		}
	}

	public static long getTime(Date startTime, Date endTime) {
		return endTime.getTime() - startTime.getTime();
	}

	/**
	 * 比较两个时间取得两个时间差
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getTimeDiff(String startTime, String endTime) {
		try {
			String tmp = "";
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			ParsePosition pos1 = new ParsePosition(0);
			Date dt1 = formatter.parse(startTime, pos);
			Date dt2 = formatter.parse(endTime, pos1);
			long lDiff = dt2.getTime() - dt1.getTime();
			int days = (int) (lDiff / (1000 * 60 * 60 * 24));
			if (days > 0) {
				lDiff = lDiff - days * 1000 * 60 * 60 * 24;
				tmp += days + "天";
			}
			int hours = (int) (lDiff / (1000 * 60 * 60));
			if (hours > 0)
				lDiff = lDiff - hours * 1000 * 60 * 60;
			tmp += hours + "小时";
			int minute = (int) (lDiff / (1000 * 60));
			tmp += minute + "分钟";
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
	}

	/**
	 * 根据长整形的毫秒数返回字符串类型的时间段
	 * 
	 * @param millseconds
	 * @return
	 */
	public static String getTime(Long millseconds) {
		String time = "";
		if (millseconds == null) {
			return "";
		}
		int days = (int) (long) millseconds / 1000 / 60 / 60 / 24;
		if (days > 0) {
			time += days + "天";
		}
		long hourMillseconds = millseconds - days * 1000 * 60 * 60 * 24;
		int hours = (int) hourMillseconds / 1000 / 60 / 60;
		if (hours > 0) {
			time += hours + "小时";
		}
		long minuteMillseconds = millseconds - days * 1000 * 60 * 60 * 24
				- hours * 1000 * 60 * 60;
		int minutes = (int) minuteMillseconds / 1000 / 60;
		if (minutes > 0) {
			time += minutes + "分钟";
		}
		return time;
	}

	/**
	 * 取得日期
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		if (date != null) {
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		}
		return "";
	}

	public static String getCurrentDate(String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		java.util.Date date = new java.util.Date();
		return formatter.format(date);
	}

	/**
	 * 得到当前日期，例如"2002-11-06"
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		return formatter.format(date);
	}

	/**
	 * 根据长整型毫秒数返回日期形式:如:2007-01-23
	 * 
	 * @param millseconds
	 * @return
	 */
	public static String getDateString(long millseconds) {
		return new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(
				millseconds));
	}

	/**
	 * 根据指定格式取得日期字符格式。
	 * 
	 * @param formater
	 * @return
	 */
	public static String getDateString(String formater) {
		return new SimpleDateFormat(formater).format(new java.util.Date());
	}

	/**
	 * 取得当前日期的毫秒数
	 * 
	 * @return
	 */
	public static long getMillsByToday() {
		String str = getDateString("yyyy-MM-dd");
		str = String.valueOf(getMillsByDateString(str));
		return Long.parseLong((str.substring(0, str.length() - 3) + "000"));
	}

	/**
	 * 取得多少天后的数据。
	 * 
	 * @param days
	 * @return
	 */
	public static long getNextDays(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		String str = String.valueOf(cal.getTimeInMillis());
		return Long.parseLong((str.substring(0, str.length() - 3) + "000"));
	}

	/**
	 * 取得下一天。
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getNextDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/**
	 * 根据给出的日期字符串返回该日期的长整型毫秒数(字符串前后空格不计) 如果strDate的格式不正确将返回0
	 * 
	 * @param strDate
	 *            形如:2007-01-23的日期字符串
	 * @return
	 */
	public static long getMillsByDateString(String strDate) {
		Calendar cal = Calendar.getInstance();
		if (strDate != null && strDate.trim().length() > 9) {
			strDate = strDate.trim();
			try {
				int year = Integer.parseInt(strDate.substring(0, 4));
				int month = Integer.parseInt(strDate.substring(5, 7)) - 1;
				int date = Integer.parseInt(strDate.substring(8, 10));
				cal.set(year, month, date, 0, 0, 0);
				String str = String.valueOf(cal.getTimeInMillis());
				return Long
						.parseLong((str.substring(0, str.length() - 3) + "000"));

			} catch (Exception e) {

			}
		}

		return 0;
	}

	/**
	 * 根据给出的时间字符串返回该时间的长整型毫秒数(字符串前后空格不计) 如果strDateTime的格式不正确将返回0
	 * 
	 * @param strDateTime
	 *            形如:2007-01-23 13:45:21的时间字符串
	 * @return
	 */
	public static long getMillsByDateTimeString(String strDateTime) {
		Calendar cal = Calendar.getInstance();
		if (strDateTime != null && strDateTime.trim().length() > 18) {
			strDateTime = strDateTime.trim();
			try {
				int year = Integer.parseInt(strDateTime.substring(0, 4));
				int month = Integer.parseInt(strDateTime.substring(5, 7)) - 1;
				int date = Integer.parseInt(strDateTime.substring(8, 10));
				int hour = Integer.parseInt(strDateTime.substring(11, 13));
				int minute = Integer.parseInt(strDateTime.substring(14, 16));
				int second = Integer.parseInt(strDateTime.substring(17, 19));
				cal.set(year, month, date, hour, minute, second);
				return cal.getTimeInMillis();
			} catch (Exception e) {

			}
		}

		return 0;
	}

	/**
	 * 根据长整型毫秒数返回时间形式:
	 * 
	 * @param millseconds
	 * @return
	 */
	public static String getFormatString(long millseconds, String format) {
		if (format == null || format.trim().length() == 0) {
			format = "yyyy-MM-dd";
		}
		format = format.trim();
		return new SimpleDateFormat(format).format(new java.util.Date(
				millseconds));
	}

	/**
	 * 得到当前的毫秒时间
	 * 
	 * @return
	 */
	public static long getCurrentTimeMillis() {
		Calendar c = Calendar.getInstance();
		return c.getTimeInMillis();
	}

	/**
	 * 根据millsecond时间取得 如 yyyy-MM-dd HH:mm:ss 的时间,如果时分秒都为0就返回 yyyy-MM-dd
	 * 
	 * @param mills
	 * @return
	 * @throws java.lang.Exception
	 */
	public static String getTimeByMills(long mills) throws Exception {
		try {
			if (mills == 0)
				return "-";
			Date date = null;
			Calendar ca = Calendar.getInstance();
			ca.setTimeInMillis(mills);
			date = ca.getTime();
			SimpleDateFormat myformat;

			if (ca.get(Calendar.HOUR_OF_DAY) == 0 && ca.get(Calendar.MINUTE) == 0
					&& ca.get(Calendar.SECOND) == 0) {
				myformat = new SimpleDateFormat("yyyy-MM-dd");
			} else {
				myformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
			return myformat.format(date);
		} catch (Exception e) {
			return "-";
		}
	}

	/**
	 * 将长整型的日期转换成为yyyy-MM-dd格式的日期。
	 * 
	 * @param mills
	 * @return
	 * @throws Exception
	 */
	public static String formatDate(long mills) throws Exception {
		try {
			if (mills == 0)
				return "-";
			Date date = null;
			Calendar ca = Calendar.getInstance();
			ca.setTimeInMillis(mills);
			date = ca.getTime();
			SimpleDateFormat myformat;

			myformat = new SimpleDateFormat("yyyy-MM-dd");

			return myformat.format(date);
		} catch (Exception e) {
			return "-";
		}
	}

	/**
	 * 将长整型的日期转换成为yyyy-MM-dd HH:mm:ss格式的日期。
	 * 
	 * @param mills
	 * @return
	 * @throws Exception
	 */
	public static String formatTime(long mills) throws Exception {
		try {
			if (mills == 0)
				return "-";
			Date date = null;
			Calendar ca = Calendar.getInstance();
			ca.setTimeInMillis(mills);
			date = ca.getTime();
			SimpleDateFormat myformat;

			myformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			return myformat.format(date);
		} catch (Exception e) {
			return "-";
		}
	}

	/**
	 * 根据时间格式如 yyyy-MM-dd HH:mm:ss 的时间取得 UTC 时间
	 * 
	 * @param 格式如
	 *            的字符串yyyy-MM-dd HH:mm:ss 或者 yyyy-mm-dd
	 * @return long Utc Millisecond 时间
	 */
	public static long getMillsByTime(String strTime) throws Exception {
		try {
			int year, month, day, hour, minute, second;
			if (strTime.length() != 19 && strTime.length() != 10) {
				throw new Exception("the time string is wrong.");
			}

			year = Integer.parseInt(strTime.substring(0, 4));
			month = Integer.parseInt(strTime.substring(5, 7)) - 1;
			day = Integer.parseInt(strTime.substring(8, 10));

			if (year < 1000 || year > 3000) {
				throw new Exception("the year is wrong.");
			}

			if (month < 0 || month > 12) {
				throw new Exception("the month is wrong.");
			}

			if (day < 1 || day > 31) {
				throw new Exception("the day is wrong");
			}

			Calendar ca = Calendar.getInstance();
			if (strTime.length() == 19) {
				hour = Integer.parseInt(strTime.substring(11, 13));
				minute = Integer.parseInt(strTime.substring(14, 16));
				second = Integer.parseInt(strTime.substring(17, 19));

				if (hour < 0 || hour > 24) {
					throw new Exception("the hour is wrong.");
				}

				if (minute < 0 || minute > 60) {
					throw new Exception("the minute is wrong.");
				}

				if (second < 0 || second > 60) {
					throw new Exception("the second is wrong.");
				}

				ca.set(year, month, day, hour, minute, second);
			} else if (strTime.length() == 10) {
				ca.set(year, month, day, 0, 0, 0);
			}
			return ca.getTimeInMillis();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 根据时间单位，时间间隔，当前时间计算出指定时间加上时间间隔后的时间
	 * 
	 * @param TimeUnit
	 *            请使用TimeUtil的静态常量
	 * @param interval
	 *            可为负值
	 * @param timeMill
	 * @return
	 */
	public static long getNextTime(int timeUnit, int interval, long timeMill) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(timeMill);
		switch (timeUnit) {
		case TimeUtil.SECOND:
			ca.add(Calendar.SECOND, interval);
			break;
		case TimeUtil.MINUTE:
			ca.add(Calendar.MINUTE, interval);
			break;
		case TimeUtil.HOUR:
			ca.add(Calendar.HOUR, interval);
			break;
		case TimeUtil.DAY:
			ca.add(Calendar.DATE, interval);
			break;
		case TimeUtil.MONTH:
			ca.add(Calendar.MONTH, interval);
			break;
		default:
			return 0;
		}
		return ca.getTimeInMillis();
	}

	/**
	 * 根据时间字符串返回date类型
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateTimeByTimeString(String timeString) {
		Date date = new Date();
		try {
			date = DateFormatUtil.parse(timeString, "yy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 根据日期字符串返回date类型。
	 * 
	 * @param timeString
	 * @return
	 */
	public static Date getDateByDateString(String timeString) {
		Date date = new Date();
		try {
			date = DateFormatUtil.parse(timeString, "yy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 取得日期。
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date getDate(int year, int month, int date) {
		return getDate(year, month, date, 0, 0, 0);
	}
	
	/**
	 * 取得日期。
	 * @param year
	 * @param month
	 * @param date
	 * @param hourOfDay
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date getDate(int year, int month, int date, int hourOfDay,
			int minute,int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, date, hourOfDay, minute, second);
		return cal.getTime();
	}

	/**
	 * 取得结束时间和开始时间的秒数。
	 * 
	 * @param endTime
	 *            结束时间
	 * @param startTime
	 *            开始时间
	 * @return
	 */
	public static int getSecondDiff(Date endTime, Date startTime) {
		long start = startTime.getTime();
		long end = endTime.getTime();
		return (int) ((end - start) / 1000);
	}

	/**
	 * 取得一个月的天数
	 * 
	 * @param year
	 *            实际年份
	 * @param mon
	 *            实际月份 1到12月
	 * @return
	 */
	public static int getDaysOfMonth(int year, int mon) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, mon - 1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);

	}

	/**
	 * 取得某月第一天为星期几。<br>
	 * 星期天为1。 星期六为7。
	 * 
	 * @param year
	 * @param mon
	 * @return
	 */
	public static int getWeekDayOfMonth(int year, int mon) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, mon - 1, 1);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取持续时间
	 * 
	 * @param time
	 * @return
	 */
	public static String getDurationTime(Date time) {
		if (BeanUtils.isEmpty(time))
			return "";
		Long millseconds = getTime(time, new Date());
		return getTime(millseconds);
	}

}