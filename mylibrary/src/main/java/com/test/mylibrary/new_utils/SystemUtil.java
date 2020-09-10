package com.test.mylibrary.new_utils;

import android.content.Context;
import android.util.Log;


import java.util.Locale;

/**
 * Created by hory on 17/5/19.
 */
public class SystemUtil {
    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return  语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return  手机IMEI
     */
    public static String getIMEI(Context ctx) {
        String imei = (String) SPUtil.get(ctx,"imei_code","");
        Log.e("SystemUtil","imei =="+imei);
        return imei;
    }

    /**
     * 获取手机宽高比
     * @param context
     * @return 宽高比字符串
     */
    public static String getWH(Context context){
        return String.valueOf(ScreenUtil.getScreenWidth(context) / ScreenUtil.getScreenHeight(context));
    }


}
