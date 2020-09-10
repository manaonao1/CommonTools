package com.test.mylibrary.new_utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by Malong
 * on 2019/9/27.
 */
public class CallPhoneUtils {

    public static void call(Context context, String phone) {
        if (!TextUtils.isEmpty(phone)) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "电话号码错误,请查证后再拨,谢谢", Toast.LENGTH_SHORT).show();
        }
    }
}
