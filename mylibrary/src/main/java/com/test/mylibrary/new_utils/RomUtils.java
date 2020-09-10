package com.test.mylibrary.new_utils;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * 判断手机系统工具类
 */
public class RomUtils {
    public static final int MIUI = 1;
    public static final int FLYME = 2;
    public static final int ANDROID_NATIVE = 3;
    public static final int NA = 4;
    public static final int OPPO = 5;
    public static final int VIVO = 6;

    private static final String STR_OPPO = "OPPO";
    private static final String STR_VIVO = "VIVO";

    public static int getLightStatusBarAvailableRomType() {
        //开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错
        if (isMiUIV7()) {
            return ANDROID_NATIVE;
        }

        if (isMiUIV6()) {
            return MIUI;
        }

        if (isFlyme()) {
            return FLYME;
        }

        if (isAndroidM()) {
            return ANDROID_NATIVE;
        }

        if (Build.MANUFACTURER.equalsIgnoreCase(STR_OPPO)) {
            return OPPO;
        }

        if (Build.MANUFACTURER.equalsIgnoreCase(STR_VIVO)) {
            return VIVO;
        }

        return NA;
    }

    //Flyme V4的displayId格式为 [Flyme OS 4.x.x.xA]
    //Flyme V5的displayId格式为 [Flyme 5.x.x.x beta]

    /**
     * 判断是否是魅族系统
     * @return true：魅族系统 false：非魅族系统
     */
    private static boolean isFlyme() {
        String displayId = Build.DISPLAY;
        if (!TextUtils.isEmpty(displayId) && displayId.contains("Flyme")) {
            String[] displayIdArray = displayId.split(" ");
            for (String temp : displayIdArray) {
                //版本号4以上，形如4.x.
                if (temp.matches("^[4-9]\\.(\\d+\\.)+\\S*")) {
                    return true;
                }
            }
        }
        return false;
    }

    //Android Api 23以上
    private static boolean isAndroidM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";

    /**
     * 判断是否是小米系统，并且版本低于7.7.13
     * @return true:是小米系统，且版本低于7.7.13
     */
    private static boolean isMiUIV6() {
        try {
            final Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            String uiCode = properties.getProperty(KEY_MIUI_VERSION_CODE, null);
            if (uiCode != null) {
                int code = Integer.parseInt(uiCode);
                return code >= 4;
            } else {
                return false;
            }

        } catch (final Exception e) {
            return false;
        }

    }

    /**
     * 判断是否是小米系统，并且版本高于等于7.7.13
     * @return true:是小米系统，且版本高于等于7.7.13
     */
    private static boolean isMiUIV7() {
        try {
            final Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            String uiCode = properties.getProperty(KEY_MIUI_VERSION_CODE, null);
            if (uiCode != null) {
                int code = Integer.parseInt(uiCode);
                return code >= 5;
            } else {
                return false;
            }

        } catch (final Exception e) {
            return false;
        }

    }

}
