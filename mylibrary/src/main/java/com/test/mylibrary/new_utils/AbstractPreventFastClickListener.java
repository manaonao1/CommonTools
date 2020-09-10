package com.test.mylibrary.new_utils;

import android.view.View;

/**
 * Created on 2018/12/29
 *  1s内只响应1次点击事件的监听器
 * @author zyf
 */
public abstract class AbstractPreventFastClickListener implements View.OnClickListener {

    private long lastClickTime = 0;
    private static final int DELAY = 1000;

    @Override
    public void onClick(View v) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastClickTime > DELAY) {
            onViewClick(v);
            lastClickTime = currentTimeMillis;
        }
    }

    /**
     * 真正的Onclick事件
     * @param view
     */
    public abstract void onViewClick(View view);

}
