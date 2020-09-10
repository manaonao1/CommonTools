package com.test.mylibrary.new_utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by Malong
 * on 19/3/25.
 * 限制按钮连击，导致请求或者跳转出现多页面
 */
public class ClickRepeatUtils {

    //判断是否为连击
    private static long lastClickTime = 0;

    public static boolean clickRepeat() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean checkPermission(Context context, String permission) {
        int i = ContextCompat.checkSelfPermission(context, permission);
        return i == PackageManager.PERMISSION_GRANTED;
    }

}
