package com.test.mylibrary.new_utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by hory on 17/10/12.
 * 获取屏幕参数
 */

public class ScreenUtil {
    /**
     * 获取屏幕的高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }
}
