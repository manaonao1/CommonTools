package com.test.mylibrary;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MlToastUtils2 {

    public static void show(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public static void goSDKIntent(Context context){
        Intent intent = new Intent(context,MyActivity.class);
        context.startActivity(intent);
    }

}
