package cn.ourpass.zxmvc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期帮助类
 * @author xiaojianyu
 *
 */
public class DateUtils {
	
    /**yyyy-MM-dd**/
    private static final String YYYYMMDD="yyyy-MM-dd";
    /**yyyy-MM-dd HH:mm:ss**/
    private static final String YYYYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";
    /**yyyy-MM-dd HH:mm**/
    private static final String YYYYMMDDHHMM="yyyy-MM-dd HH:mm";
    
    /**
     * 判断两个日期是否是同一天
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean compareSameDate(Date d1,Date d2){ 
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD); 
        String s1 = sdf.format(d1); 
        String s2 = sdf.format(d2); 
        return s1.equals(s2)?true:false; 
    }  
    
	/**
	 * 判断两个日期是否相同
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Boolean compareDate(Date d1,Date d2){ 
	    SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS); 
	    String s1 = sdf.format(d1); 
	    String s2 = sdf.format(d2); 
	    if(s1.equals(s2))return true; 
	    else return false; 
	}  
	/**
	 * 获取该日期的一天最后时间
	 * @param endday
	 * @return
	 * @throws Exception
	 */
	public static Date getDateEndDay(String endday)throws Exception{
		SimpleDateFormat formatdate = new SimpleDateFormat(YYYYMMDDHHMMSS);
		endday = endday + " 23:59:59";
		return formatdate.parse(endday);
	}	
	
	/**
	 * 获取该日期按样式
	 * @param endday yyyy-MM-dd HH:mm
	 * @return
	 * @throws Exception
	 */
	public static Date getDateByTimeType(String endday)throws Exception{
		SimpleDateFormat formatdate = new SimpleDateFormat(YYYYMMDDHHMM);
		return formatdate.parse(endday);
	}	
	/**
	 * 字符串拼接初始化时间
	 * @param year 年月日 2014-01-18
	 * @param mp  上午或者下午
	 * @param hours 小时
	 * @param minute 分钟
	 * @return
	 * @throws Exception
	 */
	public static Date initDate(String year,String mp,String hours,String minute) throws Exception{
		SimpleDateFormat formatdate = new SimpleDateFormat(YYYYMMDDHHMM);
		int hours_ = Integer.valueOf(hours);
		if(mp.equals("下午")){
			hours_ = hours_ + 12;
		}
		return formatdate.parse(year+" "+hours_+":"+minute);
	}
	//获取小时 12小时制
	public static String getDateHour(Date date) throws Exception{
		SimpleDateFormat formatdate = new SimpleDateFormat("HH");	
		Integer hh=Integer.valueOf(formatdate.format(date));
		if(hh>12){
			hh=hh-12;
		}
		return String.valueOf(hh);
	}
	//获取分钟
	public static String getDateMinute(Date date) throws Exception{
		SimpleDateFormat formatdate = new SimpleDateFormat("mm");	
		return formatdate.format(date);
	}
	public static Calendar getMonthFirst(Date date){
		  Calendar cal=Calendar.getInstance();//获取当前日期
		  cal.setTime(date);
	      cal.add(Calendar.MONTH, 0);
	      cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
	      return cal;
	}
	
	public static String initDate(Date date,short time){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date_ = new Date(date.getTime()+time*60*1000);
		return sf.format(date_);
	}
	
	public static Calendar getMonthLast(Date date){
		  Calendar cal=Calendar.getInstance();//获取当前日期
		  cal.setTime(date);
	      cal.add(Calendar.MONTH, +1);
	      cal.set(Calendar.DAY_OF_MONTH,1);
	      cal.add(Calendar.DAY_OF_MONTH,-1);
	      return cal;
	}
	
	public static Calendar setDate(Date date,int n){
		  Calendar cal=Calendar.getInstance();//获取当前日期
		  cal.setTime(date);
		  if(n/cal.getActualMaximum(Calendar.DAY_OF_MONTH)>0){
			  cal.add(Calendar.MONTH, +n/cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			  cal.add(Calendar.DAY_OF_MONTH, +n%cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		  }else{
			  cal.add(Calendar.DAY_OF_MONTH, +n); 
		  }
	      return cal;
	}
	
	public static int days(Date date,int n){
		  Calendar cal=Calendar.getInstance();//获取当前日期
		  cal.setTime(getMonthLast(date).getTime());
		  int m = cal.get(Calendar.DAY_OF_WEEK)-1;
		  if(m<n){
			  m=m+7-n;
		  }else{
			  m=m-n;
		  }
	      return m;
	}
	
	public static Calendar days_(Date date,int n){
		  Calendar cal=Calendar.getInstance();//获取当前日期
		  cal.setTime(date);
		  cal.add(Calendar.MONTH, +n);
	      return cal;
	}
	
	
	public static short stringToShort(int s){
		short sh = Integer.valueOf(s).shortValue();
		return sh;
	}
	
	public static int checkEventDate(Date start,Date end){
		int flag = 2;
		if(null!=start && null!=end && start.before(end)){
			Calendar cal_start = Calendar.getInstance();
			cal_start.setTime(start);
			Calendar cal_end = Calendar.getInstance();
			cal_end.setTime(end);
			if(cal_start.get(Calendar.YEAR)==cal_end.get(Calendar.YEAR)){
				if(cal_start.get(Calendar.MONTH)==cal_end.get(Calendar.MONTH)){
					if(cal_start.get(Calendar.DAY_OF_MONTH) == cal_end.get(Calendar.DAY_OF_MONTH)){
						flag = 0;
					}else{
						flag = 1;
					}					
				}else{
					flag = 2;
				}
			}else if(cal_start.get(Calendar.YEAR)<cal_end.get(Calendar.YEAR)){
				flag = 3;
			}
		}
		return flag;
	}
	
	public static void main(String []args){
		Date date = new Date();
		System.out.println(changeWeek(date,0,0));
	}
	/**
	 * 当前日期+4位随机数
	 * 
	 * @return String
	 */
	public static String getTimeRan() {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 4; i++) {
			result += random.nextInt(10);
		}
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
		return dateFormat.format(date) + result;
	}

	/**
	 * 获取年 yyyy
	 * @return
	 */
	public static String getYearTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		return dateFormat.format(date);
	}
	
	/**
	 * 获取月 MM
	 * @return
	 */
	public static String getMonthTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		return dateFormat.format(date);
	}
	
	/**
	 * 获取文件名
	 * @param c_path
	 * @return
	 */
	public static String getFileTimeName() {
		SimpleDateFormat shijian = new SimpleDateFormat("yyyyMMDDhhmmssSSS");
		String filename = shijian.format(new Date());
		return filename;
	}
	/**
	 * 获取日 dd
	 * @return
	 */
	public static String getDayTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		return dateFormat.format(date);
	}
	
	/*
	 * 将传进来的时间  截取为 dd格式
	 */
	public static String getDay(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		return dateFormat.format(date);
	}
	
	/**
	 * 将传进来的时间  截取为  年月  YYYY-MM格式
	 */
	public static String getYearAndMonth(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		return dateFormat.format(date);
	}
	
	/**
     * 将传进来的时间  截取为   yyyy年MM月dd日 EEE HH:mm
     */
    public static String getAllDateFormat(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 EEEE HH:mm");
        return dateFormat.format(date);
    }
    
	
	/**
	 * 根据选择的时间 组合时间 发布活动时组合报名开始时间 结束时间
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static Date setTimesByTime(Date datetimes, int days, String ampm,
			int hours, int min) throws ParseException {
		Date date = new Date();
		SimpleDateFormat formatdate = new SimpleDateFormat(YYYYMMDDHHMM);
		long oneday = 3600 * 24 * 1000;
		long all_time = 0;
		all_time += oneday*days;
		if ("下午".equals(ampm)) {
			all_time += (hours + 12)*3600*1000+min*1000*60;
		} else {
			all_time += hours*3600*1000+min*1000*60;
		}
		date.setTime(datetimes.getTime() - all_time);
		return formatdate.parse(formatdate.format(date));
	}

	/**
	 * 根据时间向前位移，返回前几天几小时几分的时间
	 * 
	 * @return String
	 * @throws ParseException
	 */
	public static Date shiftDate(Date date, int days, int hours, int minutes)
			throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
		cal.add(Calendar.HOUR, -hours);
		cal.add(Calendar.MINUTE, -minutes);
		return cal.getTime();
	}

	public static Date getBeginDateOfToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, calendar.getActualMaximum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar
				.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar
				.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.get(Calendar.DAY_OF_MONTH) - 1);
		return calendar.getTime();
	}

	public static Date getBeginDateOfYesterDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, calendar.getActualMaximum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar
				.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar
				.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.get(Calendar.DAY_OF_MONTH) - 2);
		return calendar.getTime();
	}

	public static Date getBeginDateOfThisWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.AM_PM, Calendar.AM);
		calendar.set(Calendar.HOUR, calendar.getActualMinimum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar
				.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar
				.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.DAY_OF_WEEK, calendar
				.getActualMinimum(Calendar.DAY_OF_WEEK));
		return calendar.getTime();
	}

	public static Date getBeginDateOfLastWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, calendar.getActualMaximum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar
				.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar
				.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 7);
		calendar.set(Calendar.DAY_OF_WEEK, calendar
				.getActualMinimum(Calendar.DAY_OF_WEEK));
		return calendar.getTime();
	}

	public static Date getBeginDateOfThreeDaysBefore() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, calendar.getActualMaximum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar
				.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar
				.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.get(Calendar.DAY_OF_MONTH) - 3);
		return calendar.getTime();
	}

	public static Date getMonthBeginDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, calendar.getActualMaximum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar
				.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar
				.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH) - 1);
		return calendar.getTime();
	}

	public static Date getBeforBeginOneDay(Date beginDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	public static Date getAfterBeginOneDay(Date beginDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	public static Date getThreeMonthBeginDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, calendar.getActualMaximum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar
				.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar
				.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH) - 3);
		return calendar.getTime();
	}

	public static Date getLastMonthBeginDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, calendar.getActualMaximum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar
				.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar
				.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH) - 2);
		return calendar.getTime();
	}

	public static Date getYearBeginDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, calendar.getActualMaximum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar
				.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar
				.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.DAY_OF_YEAR, calendar
				.getActualMinimum(Calendar.DAY_OF_YEAR) - 1);
		return calendar.getTime();
	}

	public static final Date getMinWeekDay(int i) {
		return processMinTime(getWeekDay(i));
	}

	public static final Date getMaxWeekDay(int i) {
		return processMaxTime(getWeekDay(i));
	}

	private static final Calendar getWeekDay(int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, calendar
				.getActualMinimum(Calendar.DAY_OF_WEEK));
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				+ i);
		return calendar;
	}

	public static final Date getMinMonthDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		return processMinTime(calendar);
	}

	public static final Date getMaxMonthDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		return processMaxTime(calendar);
	}

	private static final Date processMaxTime(Calendar calendar) {
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.HOUR, calendar.getActualMaximum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar
				.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar
				.getActualMaximum(Calendar.SECOND));
		return calendar.getTime();
	}

	private static final Date processMinTime(Calendar calendar) {
		calendar.set(Calendar.AM_PM, Calendar.AM);
		calendar.set(Calendar.HOUR, calendar.getActualMinimum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar
				.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar
				.getActualMinimum(Calendar.SECOND));
		return calendar.getTime();
	}

	/**
	 * 得到本年的第一天
	 * 
	 * @return
	 */
	public static Date getYearFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar
				.getActualMinimum(Calendar.MONTH), calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH), 0, 0, 0);
		return calendar.getTime();
	}

	/**
	 * 得到本年的最后一天
	 * 
	 * @return
	 */
	public static String getYearLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar
				.getActualMaximum(Calendar.MONTH), calendar
				.getActualMaximum(Calendar.DATE), 0, 0, 0);
		calendar.set(calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return formatMe(calendar.getTime());
	}

	/**
	 * 得到本月的第一天
	 * 
	 * @return
	 */
	public static Date getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH), 0, 0, 0);
		return calendar.getTime();
	}

	/**
	 * 得到本月的最后一天
	 * 
	 * @return
	 */
	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
		// calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND)-1);
		return formatMe(calendar.getTime());
	}

	/**
	 * 得到本周的第一天
	 * 
	 * @return
	 */
	public static String getWeekFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH), 0, 0, 0);
		return formatMe(calendar.getTime());
	}

	/**
	 * 得到本周的最后一天
	 * 
	 * @return
	 */
	public static String getWeekLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.getActualMaximum(Calendar.DAY_OF_WEEK) + 1, 0, 0, 0);
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) - 1);
		return formatMe(calendar.getTime());
	}

	public static String formatMe(Date d1) {
		SimpleDateFormat f = new SimpleDateFormat(YYYYMMDD);
		String s = f.format(d1);
		return s;
	}

	/**
	 * 日期格式化 MM-dd 周五 HH:mm
	 * @param date
	 * @return
	 * @author xiaojianyu
	 */
	public static String formatDate(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sf_ = new SimpleDateFormat("MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week_day = c.get(Calendar.DAY_OF_WEEK);
        String week = "";
        switch(week_day){
            case 1:week="周日";break;
            case 2:week="周一";break;
            case 3:week="周二";break;
            case 4:week="周三";break;
            case 5:week="周四";break;
            case 6:week="周五";break;
            case 7:week="周六";break;
            default:break;
        }
        return sf_.format(date)+" "+week+" "+sf.format(date);
    }
	
	/**
     * 日期格式化 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     * @author xiaojianyu
     */
    public static String formatDateYMDHMS(Date date) {
        SimpleDateFormat f = new SimpleDateFormat(YYYYMMDDHHMMSS);
        String s = f.format(date);
        return s;
    }
    
	public static Date formatYmd(String d1) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat(YYYYMMDD);
		return f.parse(d1);
	}
	
	public static List<String> getDays(String from, String to)
			throws ParseException {
		SimpleDateFormat formater = new SimpleDateFormat(YYYYMMDD);
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		start.setTime(formater.parse(from));
		end.setTime(formater.parse(to));

		List<String> list = new ArrayList<String>();
		SimpleDateFormat outformat = new SimpleDateFormat("yyyy年MM月dd日");
		while (start.compareTo(end) <= 0) {
			String day1 = outformat.format(start.getTime()); 
			// start.get(Calendar.YEAR)+"年"+(start.get(Calendar.MONTH)+1)+"月"+start.get(Calendar.DATE)+"日";
			list.add(day1);
			start.add(Calendar.DATE, 1);
		}
		return list;
	}

	// 下边这些方法是为了获取周范围
	public static Date getWeekBegin() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		Date mm = nDaysAgo(cal.get(Calendar.DAY_OF_WEEK) - 2, date);
		return getDayBegin(mm);
	}

	public static Date getWeekEnd() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		Date mm = nDaysAfter(8 - cal.get(Calendar.DAY_OF_WEEK), date);
		return getDayEnd(mm);

	}

	public static Date getDayBegin(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal
				.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		return cal.getTime();
	}

	public static Date nDaysAfter(int n, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + n);
		return cal.getTime();
	}
	
	public static Date nDaysAgo(Integer n, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - n);
		return cal.getTime();
	}

	public static Date nHoursAgo(Integer n, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - n);
		return cal.getTime();
	}
	
	public static Date nMinutesAgo(Integer n, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) - n);
		return cal.getTime();
	}
	
	public static Date nMinutesAfter(int n, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + n);
		return cal.getTime();
	}
	
	public static Date getDayEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal
				.get(Calendar.DAY_OF_MONTH) + 1, 0, 0, 0);
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) - 1);

		return cal.getTime();
	}

	/**
	 * 取得当前日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}
	
	public static String changeWeek(Date date,int language,int type) {
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sf_ = new SimpleDateFormat("yyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week_day = c.get(Calendar.DAY_OF_WEEK);
		String week = "";
		if(language == 0){
			switch(week_day){
				case 1:week="周日";break;
				case 2:week="周一";break;
				case 3:week="周二";break;
				case 4:week="周三";break;
				case 5:week="周四";break;
				case 6:week="周五";break;
				case 7:week="周六";break;
				default:break;
			}
		}else{
			switch(week_day){
				case 1:week="Sun";break;
				case 2:week="周一";break;
				case 3:week="周二";break;
				case 4:week="周三";break;
				case 5:week="周四";break;
				case 6:week="周五";break;
				case 7:week="周六";break;
				default:break;
			}
		}
		
		if(type == 0){
			return week+" "+sf.format(date);
		}else{
			return sf_.format(date)+" "+week+" "+sf.format(date);
		}
		
	}

	public static String changeDate(Date date,int language,int type) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		String month_ = "";
		String day_ = "";
		if(month<10){
			month_ = "0"+month;
		}else{
			month_ = month+"";
		}
		if(day<10){
			day_ = "0"+day;
		}else{
			day_ = day+"";
		}
		String dateVar = "";
		if(language == 0){
			if(type == 0){
				dateVar = day_+"日";
			}
			if(type == 1){
				dateVar = month_+"月"+day_+"日";
			}
			if(type == 2){
				dateVar = year+"年"+month_+"月"+day_+"日";
			}			
		}else{
			dateVar = year+"Y"+month+"M"+day+"D";
		}		
		return dateVar;
	}
	
	/**
	 * 取得当前日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param str1
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param str2
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return String 返回值为：xx天xx小时xx分xx秒
	 */
	public static long[] getDistanceTime(Date one, Date two) {
		long time1 = one.getTime();
		long time2 = two.getTime();
		long diff = time2 - time1;

		long day = diff / (24 * 60 * 60 * 1000);
		long hour = (diff / (60 * 60 * 1000) - day * 24);
		long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long[] times = { day, hour, min, sec };
		return times;
	}

	public static long getDistanceMinTime(Date one, Date two) {
		long time1 = one.getTime();
		long time2 = two.getTime();
		long diff = time2 - time1;
		long min = diff / (60 * 1000);
		return min;
	}
	
	/**
	 * 返回时间的星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String week = sdf.format(date);
		week = week.replace("Mon", "星期一");
		week = week.replace("Tue", "星期二");
		week = week.replace("Wed", "星期三");
		week = week.replace("Thu", "星期四");
		week = week.replace("Fri", "星期五");
		week = week.replace("Sat", "星期六");
		week = week.replace("Sun", "星期日");
		return week;
	}

	/**
	 * 返回时间的英文星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeek_EN(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String week = sdf.format(date);
		week = week.replace("星期一", "Mon");
		week = week.replace("星期二", "Tue");
		week = week.replace("星期三", "Wed");
		week = week.replace("星期四", "Thu");
		week = week.replace("星期五", "Fri");
		week = week.replace("星期六", "Sat");
		week = week.replace("星期日", "Sun");
		return week;
	}

	/**
	 * 返回时间的上午下午
	 * 
	 * @param date
	 * @return
	 */
	public static String getChangeM(Date date) {
		//2014-09-24 00:00:00  2014-09-24 12:00:00
		SimpleDateFormat sdf = new SimpleDateFormat("a");
		String week = sdf.format(date);
		SimpleDateFormat twelveCheck = new SimpleDateFormat("H");
		String hour = twelveCheck.format(date);
		if(StringUtils.isNotBlank(hour)) {
			if("12".equals(hour)) {
				week = "Am";
			} else if("00".equals(hour) || "0".equals(hour)) {
				week = "Pm";
			}
		}
		week = week.replace("Am", "上午");
		week = week.replace("Pm", "下午");
		week = week.replace("AM", "上午");
		week = week.replace("PM", "下午");
		return week;
	}

	/**
	 * 返回时间的英文上午下午
	 * 
	 * @param date
	 * @return
	 */
	public static String getChangeM_EN(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("a");
		String week = sdf.format(date);
		week = week.replace("上午", "Am");
		week = week.replace("下午", "Pm");
		return week;
	}
	/**
	 * 天数加n
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayOfMonth(Date date, int day) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getTime().getDate() + day);
		return c.getTime();
	}

	/**
	 * 时间为time
	 * 
	 * @param date
	 * @return
	 */
	public static Date getAddTime(Date date, int num) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, c.getTime().getHours() + num);
		return c.getTime();
	}

	/**
	 * 时间为time
	 * 
	 * @param date
	 * @return
	 */
	public static Date getTime(Date date, int time) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, time);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取某个月份的最后一天日期
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public static int getMonthLastDay(int year,int month) throws ParseException{
		Calendar date = Calendar.getInstance();
		if(month==1){
			month=12;
		}
		date.set(year,month-1,1);
		return date.getActualMaximum(Calendar.DAY_OF_MONTH);	
	}
	
	/**
	 * 返回周几，1代表星期日，2代表星期一，依次类推
	 * @param date
	 * @return
	 */
	public static int getWeekDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return(cal.get(Calendar.DAY_OF_WEEK));
	}
	
	/**
	 * 开始时间和结束时间组合成字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getStartEndToString(Date sdate, Date edate,boolean isshow_start_time,boolean isshow_end_time,int language) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy年MM月dd日");
		if(language != 0){
			dateFormat =  new SimpleDateFormat(YYYYMMDD);
		}
		SimpleDateFormat monFormat = new SimpleDateFormat("MM月dd日");
		if(language != 0){
			monFormat =  new SimpleDateFormat("MM-dd");
		}
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd日");
		if(language != 0){
			dayFormat =  new SimpleDateFormat("dd");
		}
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
		SimpleDateFormat minFormat = new SimpleDateFormat("mm");
		String date = "";
		int sdate_hour = Integer.valueOf(hourFormat.format(sdate));
		if(sdate_hour>12){
			sdate_hour = sdate_hour-12;
		}
		int edate_hour = Integer.valueOf(hourFormat.format(edate));;
		if(edate_hour>12){
			edate_hour = edate_hour-12;
		}
		String sdatehour = sdate_hour+":"+minFormat.format(sdate);
		String edatehour = edate_hour+":"+minFormat.format(edate);
		if(isshow_start_time && isshow_end_time){
			date = dateFormat.format(sdate);
			if(language == 0){
				date += " "+getWeek(sdate)+" "+getChangeM(sdate)+" "+ sdatehour+" 到 ";
			}else{
				date += " "+getWeek_EN(sdate)+" "+getChangeM_EN(sdate)+" "+ sdatehour+" to ";
			}
			if (sdate.getYear() == edate.getYear()) {
				if (sdate.getMonth() == edate.getMonth()) {
					if (sdate.getDay() == edate.getDay()) {
						if(language == 0){
							date += getChangeM(edate)+" "+ edatehour;
						}else{
							date += getChangeM_EN(edate)+" "+ edatehour;
						}						
					}else{						
						if(language == 0){
							date += dayFormat.format(edate)+" "+getWeek(edate)+" "+getChangeM(edate)+" "+ edatehour;
						}else{
							date += dayFormat.format(edate)+" "+getWeek_EN(edate)+" "+getChangeM_EN(edate)+" "+ edatehour;
						}
					}
				}else{
					if(language == 0){
						date += monFormat.format(edate)+" "+getWeek(edate)+" "+getChangeM(edate)+" "+ edatehour;
					}else{
						date += monFormat.format(edate)+" "+getWeek_EN(edate)+" "+getChangeM_EN(edate)+" "+ edatehour;
					}					
				}
			}else{
				if(language == 0){
					date += dateFormat.format(edate)+" "+getWeek(edate)+" "+getChangeM(edate)+" "+ edatehour;
				}else{
					date += dateFormat.format(edate)+" "+getWeek_EN(edate)+" "+getChangeM_EN(edate)+" "+ edatehour;
				}				
			}
		}else if(isshow_start_time && !isshow_end_time){
			if(language == 0){
				date = "开始时间："+dateFormat.format(sdate) + " "+getWeek(sdate)+" "+getChangeM(sdate)+" "+ sdatehour;
			}else{
				date ="Start："+dateFormat.format(sdate) + " "+getWeek_EN(sdate)+" "+getChangeM_EN(sdate)+" "+ sdatehour;
			}
		}else if(!isshow_start_time && isshow_end_time){
			if(language == 0){
				date = "截止时间:"+dateFormat.format(edate)+" "+getWeek(edate)+" "+getChangeM(edate)+" "+ edatehour;
			}else{
				date = "End:"+dateFormat.format(edate)+" "+getWeek_EN(edate)+" "+getChangeM_EN(edate)+" "+ edatehour;
			}
		}
		return date;
	}
	
	/**
	 * 计算一个月前
	 * @param now
	 * @return
	 */
	public static Calendar getLastMonth(Date now){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(GregorianCalendar.MONTH, -1);
		return calendar;
	}
	
	/**
	 * 计算二个月前
	 * @param now
	 * @return
	 */
	public static Calendar getTwoMonth(Date now){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(GregorianCalendar.MONTH, -2);
		return calendar;
	}
	
	/**
	 * 计算三个月前
	 * @param now
	 * @return
	 */
	public static Calendar getThreeMonth(Date now){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(GregorianCalendar.MONTH, -3);
		return calendar;
	}
	
	/**
	 * 计算四个月前
	 * @param now
	 * @return
	 */
	public static Calendar getFourMonth(Date now){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(GregorianCalendar.MONTH, -4);
		return calendar;
	}
	
	/**
	 * 计算五个月前
	 * @param now
	 * @return
	 */
	public static Calendar getFiveMonth(Date now){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(GregorianCalendar.MONTH, -5);
		return calendar;
	}
	
	/**
	 * 计算六个月前
	 * @param now
	 * @return
	 */
	public static Calendar getSixMonth(Date now){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(GregorianCalendar.MONTH, -6);
		return calendar;
	}
	
	/**
	 * 计算七个月前
	 * @param now
	 * @return
	 */
	public static Calendar getSevenMonth(Date now){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(GregorianCalendar.MONTH, -7);
		return calendar;
	}
	
	/**
	 * 计算八个月前
	 * @param now
	 * @return
	 */
	public static Calendar getEightMonth(Date now){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(GregorianCalendar.MONTH, -8);
		return calendar;
	}
	
	/**
	 * 计算九个月前
	 * @param now
	 * @return
	 */
	public static Calendar getNineMonth(Date now){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(GregorianCalendar.MONTH, -9);
		return calendar;
	}
	
	/**
	 * 计算十个月前
	 * @param now
	 * @return
	 */
	public static Calendar getTenMonth(Date now){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(GregorianCalendar.MONTH, -10);
		return calendar;
	}
	
	/**
	 * 计算十一个月前
	 * @param now
	 * @return
	 */
	public static Calendar getElevenMonth(Date now){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(GregorianCalendar.MONTH, -11);
		return calendar;
	}
	
	/**
	 * 计算十二个月前
	 * @param now
	 * @return
	 */
	public static Calendar getTwelMonth(Date now){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(GregorianCalendar.MONTH, -12);
		return calendar;
	}
	
	/**
	 * 计算一个月后
	 * @param now
	 * @return
	 */
	public static Date getOneMonthAfter(Date now){
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(GregorianCalendar.MONTH, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取当前格式化的时间
	 * @return
	 */
	public static String getCurrentTimeFormat() {
		return new SimpleDateFormat(YYYYMMDDHHMMSS).format(new Date());
	}
	
	/**
	 * 获取当前时间的20091225091010格式
	 * @return
	 */
	public static String getCurrentTimeNumFormat() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
}
