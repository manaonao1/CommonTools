package com.test.mylibrary.new_utils;

import android.content.Context;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;


/**
 * Created by zoulu on 16/3/29.
 * 生成cookie来访问web页
 */
public class MakeCookie {

    public static void synCookies(Context context, String url) {
        try {
            CookieSyncManager.createInstance(context);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            //cookieManager.removeAllCookie();//移除
            //cookieManager.setCookie(url, "path=" + "/; ");
            CookieSyncManager.getInstance().sync();
        } catch (Exception e) {
            Log.i("MakeCookie", e.toString());
        }
    }

    public static void clearAllCookies(){
        CookieManager.getInstance().removeAllCookie();
    }

    /**
     * 上传图片通知HTML
     * @param context
     * @param url
     * @param answerPics
     */
    public static void synCookies(Context context, String url,String answerPics ) {
        try {
            CookieSyncManager.createInstance(context);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeAllCookie();//移除
            cookieManager.setCookie(url, "path=" + "/; ");
            cookieManager.setCookie(url, "token=" + SPUtil.get(context, "token", ""));
            cookieManager.setCookie(url,"answerUrls=" + answerPics);
            CookieSyncManager.getInstance().sync();
        } catch (Exception e) {
            Log.i("MakeCookie", e.toString());
        }
    }
}
