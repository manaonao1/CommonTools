package com.test.mylibrary.new_utils;

import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * 防止多次快速点击的Util
 */
public class FastClickCheckUtil {

    public static Map<Integer, Long> viewIdMap = new HashMap<>();
    private static final int MIN_DELAY_TIME = 1000;  // 两次点击间隔不能少于1000ms

    public static boolean isFastClick(View view) {
        //上次被点击的时间
        Long lastClickTime = viewIdMap.get(view.getId());
        if (lastClickTime == null || lastClickTime == 0) {
            viewIdMap.put(view.getId(), System.currentTimeMillis());
            return false;
        }
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            viewIdMap.put(view.getId(), currentClickTime);
            return false;
        }
        return true;
    }
}
