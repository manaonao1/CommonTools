package com.test.mylibrary.new_utils;

/**
 * Created by Malong
 * on 2019/9/18.
 */
public class CountdownTimeUtils {

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    private String left;
    private String timeStamp;
    private String right;

    public CountdownTimeUtils(String left, String timeStamp,String right) {
        this.left = left;
        this.timeStamp = timeStamp;
        this.right = right;
    }

    /**
     * 时间戳转为时间
     */
    public static String dealTimeCalculate(long time) {
        long hour = time / 3600;
        long minute = (time - hour * 3600) / 60;
        long seconds = time - hour * 3600 - minute * 60;
        String hourString = hour < 10 ? "0" + hour : "" + hour;
        String minuteString = minute < 10 ? "0" + minute : "" + minute;
        String secondsString = seconds < 10 ? "0" + seconds : "" + seconds;
        return hourString + ":" + minuteString + ":" + secondsString;
    }

}
