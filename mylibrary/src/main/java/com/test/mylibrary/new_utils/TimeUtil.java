package com.test.mylibrary.new_utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hory on 17/4/5.
 */
public class TimeUtil {

    /**
     * 获取指定日期是星期几
     * 参数为null时表示获取当前日期是星期几
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String week;
        if (date != null) {
            String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
            Calendar calendar = Calendar.getInstance();
            if (date != null) {
                calendar.setTime(date);
            }
            int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0) {
                w = 0;
            }
            week = weekOfDays[w];
        } else {
            week = "";
        }
        return week;
    }

    /**
     * 把时间戳转为月.日（02.19）
     *
     * @param string
     * @return
     */
    public static String stampToDate(String string) {
        String res;
        if (string != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.dd");
            long time = new Long(string);
            time = time * 1000;
            Date date = new Date(time);
            res = simpleDateFormat.format(date);
        } else {
            res = "";
        }
        return res;
    }

    /**
     * 把时间戳转为时:分（19:30）
     *
     * @param string
     * @return
     */
    public static String stampToTime(String string) {
        String res;
        if (string != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            long time = new Long(string);
            time = time * 1000;
            Date date = new Date(time);
            res = simpleDateFormat.format(date);
        } else {
            res = "";
        }
        return res;
    }

    /**
     * 获取日期
     *
     * @param string
     * @return
     */
    public static Date getDate(String string) {
        Date date = null;
        if (string != null) {
            long time = new Long(string);
            time = time * 1000;
            date = new Date(time);
        } else {
            date = new Date();
        }
        return date;
    }

    /**
     * 获取月日及时分
     *  秒转换
     * @param string
     * @return
     */
    public static String getStrTime(String string) {
        String re_StrTime = null;
        if (string != null) {
            //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日  HH:mm");
            // 例如：cc_time=1291778220
            long lcc_time = Long.valueOf(string);
            re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        } else {
            re_StrTime = "";
        }
        return re_StrTime;
    }

    /**
     * 获取月日及时分
     *  毫秒转换
     * @param string
     * @return
     */
    public static String getStrTime2(String string) {
        String re_StrTime = null;
        if (string != null) {
            //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH:mm");
            // 例如：cc_time=1291778220
            long lcc_time = Long.valueOf(string);
            re_StrTime = sdf.format(new Date(lcc_time));
        } else {
            re_StrTime = "";
        }
        return re_StrTime;
    }

    /**
     * 获取年月日及时分
     *
     * @param string
     * @return
     */
    public static String getStrYearTime(String string) {
        try {
            String re_StrTime = null;
            if (string != null) {
                //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
                // 例如：cc_time=1291778220
                long lcc_time = Long.valueOf(string);
                re_StrTime = sdf.format(new Date(lcc_time * 1000L));
            } else {
                re_StrTime = "";
            }
            return re_StrTime;
        } catch (Exception e){
            return "";
        }
    }

    /**
     * 获取年月日及时分
     *
     * @param string
     * @return
     */
    public static String getStrYearPointTime(String string) {
        String re_StrTime = null;
        if (string != null) {
            //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  HH:mm");
            // 例如：cc_time=1291778220
            long lcc_time = Long.valueOf(string);
            re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        } else {
            re_StrTime = "";
        }
        return re_StrTime;
    }

    /**
     * 获取年月日及时分
     *
     * @param string
     * @return
     */
    public static String getStrYearTimeOnce(String string) {
        String re_StrTime = null;
        if (string != null) {
            //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
            // 例如：cc_time=1291778220
            long lcc_time = Long.valueOf(string);
            re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        } else {
            re_StrTime = "";
        }
        return re_StrTime;
    }

    /**
     * 获取月日
     *
     * @param string
     * @return
     */
    public static String getStrMonthAndDay(String string) {
        String re_StrTime = null;
        if (string != null) {
            //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
            // 例如：cc_time=1291778220
            long lcc_time = Long.valueOf(string);
            re_StrTime = sdf.format(new Date(lcc_time));
        } else {
            re_StrTime = "";
        }
        return re_StrTime;
    }

    /**
     * 获取月日
     *
     * @param time 时间戳
     * @return 格式化后的月日
     */
    public static String getMonthAndDay(long time) {
        String re_StrTime;
        //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日", Locale.CHINA);
        // 例如：cc_time=1291778220
        re_StrTime = sdf.format(new Date(time));

        return re_StrTime;
    }

    /**
     * 获取年月日
     *
     * @param string
     * @return
     */
    public static String getStrYearTimes(String string) {
        String re_StrTime = null;
        if (string != null) {
            //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            // 例如：cc_time=1291778220
            long lcc_time = Long.valueOf(string);
            re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        } else {
            re_StrTime = "";
        }

        return re_StrTime;
    }

    /**
     * 获取年月日
     *
     * @param string
     * @return
     */
    public static String getStrYearTimesPoint(String string) {
        String re_StrTime = null;
        if (string != null) {
            //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
            // 例如：cc_time=1291778220
            long lcc_time = Long.valueOf(string);
            re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        } else {
            re_StrTime = "";
        }
        return re_StrTime;
    }

    /**
     * 获取年月日  13位时间戳
     *
     * @param string
     * @return
     */
    public static String getStrYearTimesPoint2(String string) {
        String re_StrTime = null;
        if (string != null) {
            //同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
            // 例如：cc_time=1291778220
            long lcc_time = Long.valueOf(string);
            re_StrTime = sdf.format(new Date(lcc_time));
        } else {
            re_StrTime = "";
        }
        return re_StrTime;
    }

    /**
     * 获取月日时分
     *
     * @param string
     * @return
     */
    public static String getStrMonthDayTimesPoint(String string) {
        String re_StrTime = null;
        if (string != null) {
            //同理也可以转为其它样式的时间格式.例如："MM/dd HH:mm"
            SimpleDateFormat sdf = new SimpleDateFormat("MM.dd  HH:mm");
            // 例如：cc_time=1291778220
            long lcc_time = Long.valueOf(string);
            re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        } else {
            re_StrTime = "";
        }
        return re_StrTime;
    }

    /**
     * 毫秒时间戳转换时间
     *
     * @param pattern
     * @param dateTime
     * @return
     */
    public static String getFormatedDateTime(String pattern, long dateTime) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date(dateTime + 0));
    }

    public final static long ONE_SECOND_IN_MILLS = 1000;
    public final static long ONE_MINUTE_IN_MILLS = ONE_SECOND_IN_MILLS * 60; //一分钟
    public final static long ONE_HOUR_IN_MILLS = ONE_MINUTE_IN_MILLS * 60;//一小时
    public final static long ONE_DAY_IN_MILLS = ONE_HOUR_IN_MILLS * 24;//一天
    public final static long TEN_MINUTE_IN_MILLS = ONE_MINUTE_IN_MILLS * 10; //10分钟
    public final static long FIFTY_MINUTE_IN_MILLS = ONE_MINUTE_IN_MILLS * 50; //50分钟
    public final static long EXIT_WARNING_INTERVAL_IN_MILLS = ONE_SECOND_IN_MILLS * 2; //退出警告间隔时间
    /**
     * 格式化天内时间差
     *
     * @param timeIntervalInMills 以毫秒为单位的时间差
     * @return
     */
    public static String formatTimeIntervalOfDay(long timeIntervalInMills) {
        long hour = timeIntervalInMills / ONE_HOUR_IN_MILLS;
        long minute = (timeIntervalInMills - hour * ONE_HOUR_IN_MILLS) / ONE_MINUTE_IN_MILLS;
        if (hour != 0 && minute != 0) {
            return String.format("%d小时%d分钟", hour, minute);
        } else if (hour != 0) {
            return String.format("%d小时", hour);
        } else if (minute != 0) {
            return String.format("%d分钟", minute);
        } else if (timeIntervalInMills >= 0) {
            return "不到1分钟";
        } else {
            return "-1分钟";
        }
    }

    /**
     * 格式化小时内时间差
     *
     * @param timeIntervalInMills
     * @return
     */
    public static String formatTimeIntervalOfHour(long timeIntervalInMills) {
        long minute = timeIntervalInMills / ONE_MINUTE_IN_MILLS;
        long second = (timeIntervalInMills - minute * ONE_MINUTE_IN_MILLS) / ONE_SECOND_IN_MILLS;
        if (minute != 0 && second != 0) {
            return String.format("%d分%d秒", minute, second);
        } else if (minute != 0) {
            return String.format("%d分", minute);
        } else if (second != 0) {
            return String.format("%d秒", second);
        } else if (timeIntervalInMills >= 0) {
            return "不到1秒";
        } else {
            return "-1秒";
        }
    }

}
