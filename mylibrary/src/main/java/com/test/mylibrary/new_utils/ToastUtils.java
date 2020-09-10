package com.test.mylibrary.new_utils;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.mylibrary.R;


/**
 * Created by malong on 18/1/8.
 */
public class ToastUtils {
    private static Toast mToast;
    private static Toast mToast2;

    public static void showToast(Activity act, String text) {
        try {
            //LayoutInflater的作用：对于一个没有被载入或者想要动态载入的界面，都需要LayoutInflater.inflate()来载入
            //LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化
            LayoutInflater inflater = act.getLayoutInflater();//调用Activity的getLayoutInflater()
            View view = inflater.inflate(R.layout.toast_style, null); //加載layout下的布局
            TextView title = view.findViewById(R.id.tvTitleToast);
            title.setText(text+""); //toast的标题
            mToast = new Toast(act.getApplicationContext());
            mToast.setGravity(Gravity.CENTER, 0, 0);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
            mToast.setDuration(Toast.LENGTH_SHORT);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
            mToast.setView(view); //添加视图文件
            mToast.show();
        } catch (Exception e) {
            mToast2 = Toast.makeText(act, text, Toast.LENGTH_SHORT);
            mToast2.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
            mToast2.setMargin(0f, 0.5f);
            mToast2.setText(text+"");
            mToast2.setDuration(Toast.LENGTH_SHORT);
            mToast2.show();
        }
    }

}
