package com.test.mylibrary;

import android.content.Context;

public class MLSDKInitializer {

    static Context appCntext;
    public static void init(Context context){
        if (null != context){
            appCntext = context;
        }
    }

}
