package com.frame.util.date;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * <p>[时间工具类]<p>
 * @author yushp
 *
 */
public class DateFormat {

	/**
	 * 
	 * <p>[将指定的时间转换成 指定的格式]<p>
	 * @author yushp
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String getStringCurrentTime(Date date, String formatStr) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * 
	 * <p>[获取现在时间]<p>
	 * @author yushp
	 * @return
	 */
	public static String getStringCurrentDate() {
		return getStringCurrentTime(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * <p>[获取现在时间]<p>
	 * @author yushp
	 * @return
	 */
	public static String getStringCurrentDateShort() {
		return getStringCurrentTime(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 
	 * <p>[获取时间 小时:分;秒 HH:mm:ss]<p>
	 * @author yushp
	 * @return
	 */
	public static String getStringCurrentTime() {
		return getStringCurrentTime(new Date(), "HH:mm:ss");
	}

	/**
	 * 
	 * <p>[将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss]<p>
	 * @author yushp
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date strtodate = null;
		try {
			strtodate = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return strtodate;
	}
	
	/**
	 * 
	 * <p>[将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss]<p>
	 * @author yushp
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 
	 * <p>[得到现在时间]<p>
	 * @author yushp
	 * @return
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}
	
}
