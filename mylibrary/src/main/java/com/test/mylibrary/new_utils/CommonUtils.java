package com.test.mylibrary.new_utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;



public final class CommonUtils {

    public final static long ONE_SECOND_IN_MILLS = 1000;
    public final static long ONE_MINUTE_IN_MILLS = ONE_SECOND_IN_MILLS * 60; //一分钟
    public final static long ONE_HOUR_IN_MILLS = ONE_MINUTE_IN_MILLS * 60;//一小时
    public final static long ONE_DAY_IN_MILLS = ONE_HOUR_IN_MILLS * 24;//一天
    public final static long TEN_MINUTE_IN_MILLS = ONE_MINUTE_IN_MILLS * 10; //10分钟
    public final static long FIFTY_MINUTE_IN_MILLS = ONE_MINUTE_IN_MILLS * 50; //50分钟
    public final static long EXIT_WARNING_INTERVAL_IN_MILLS = ONE_SECOND_IN_MILLS * 2; //退出警告间隔时间
    public final static String GENDER_FEMALE = "female";//女性
    public final static String GENDER_MALE = "male"; //男性
    public final static String UNLIMITED_STRING = "不限";
    public final static int SUCCESS = 1; //网络请求成功标志
    public final static int PICWIDTH = 720;
    public final static int PICHEIGHT = 1280;
    public final static int PICQUALITY = 75;


    public static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * 获取AndroidManifest里的meta-data值
     */
    public static String getMetaData(Context context, String key) {
        String channel = "";
        try {
            channel = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channel;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            result = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void deleteFile(String str) {
        try {
            //删除压缩后的图片
            File oldFile = new File(str);
            boolean flag = oldFile.delete();
            if (flag) {
                Log.e("=======", "delete success: ");
            } else {
                Log.e("=======", "delete fail: ");
            }
        } catch (Exception e) {
        }
    }

    public enum NetState {
        enable("有网络"),
        Disable("无网络"),
        Wifi("WIFI"),
        Mobile("移动网络");
        private String remark;

        public String getRemark() {
            return remark;
        }

        NetState(String remark) {
            this.remark = remark;
        }
    }


    /**
     * 判断是否为连击
     *
     * @return boolean
     */
    private static long lastClickTime = 0;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * @param strs
     * @param i
     * @param num
     */
    private static void compare(String[] strs, int i, int j, int num) {
        //判断2个字符串谁的长度最小，则以当前长度作为num+1的最大标准
        if (strs[i].length() >= strs[j].length()) {
            if (num + 1 <= strs[j].length()) {
                if (strs[j].charAt(num) < strs[i].charAt(num)) {
                    String temp = strs[i];
                    strs[i] = strs[j];
                    strs[j] = temp;
                    //若相等，则判断第二个
                } else if (strs[j].charAt(num) == strs[i].charAt(num)) {
                    num++;
                    compare(strs, i, j, num);
                }
            }
        } else {
            if (num + 1 <= strs[i].length()) {
                if (strs[j].charAt(num) < strs[i].charAt(num)) {
                    String temp = strs[i];
                    strs[i] = strs[j];
                    strs[j] = temp;
                    //若相等，则判断第二个
                } else if (strs[j].charAt(num) == strs[i].charAt(num)) {
                    num++;
                    compare(strs, i, j, num);
                }
            } else {
                //表示当前字符串内容都一致，strs[j]的长度大。 则放前面。
                String temp = strs[i];
                strs[i] = strs[j];
                strs[j] = temp;
            }
        }
    }

    public Bitmap compress(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 4096) {// 判断如果图片大于4M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// 这里压缩80%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;

        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 1920f;// 这里设置高度为800f
        float ww = 1080f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;// 降低图片从ARGB888到RGB565
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return bitmap;
    }

    /**
     * 对List对象按照某个成员变量进行排序
     *
     * @param list      List对象
     * @param sortField 排序的属性名称
     * @param sortMode  排序方式：ASC 升序，DESC降序 任选其一
     */
    public static <T> void sortList(List<T> list, final String sortField, final String sortMode) {
        if (list == null || list.size() < 2) {
            return;
        }
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                try {
                    Class clazz = o1.getClass();
                    Field field = clazz.getDeclaredField(sortField); //获取成员变量
                    field.setAccessible(true); //设置成可访问状态
                    String typeName = field.getType().getName().toLowerCase(); //转换成小写

                    Object v1 = field.get(o1); //获取field的值
                    Object v2 = field.get(o2); //获取field的值

                    boolean ASC_order = (sortMode == null || "ASC".equalsIgnoreCase(sortMode));

                    //判断字段数据类型，并比较大小
                    if (typeName.endsWith("string")) {
                        String value1 = v1.toString();
                        String value2 = v2.toString();
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("short")) {
                        Short value1 = Short.parseShort(v1.toString());
                        Short value2 = Short.parseShort(v2.toString());
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("byte")) {
                        Byte value1 = Byte.parseByte(v1.toString());
                        Byte value2 = Byte.parseByte(v2.toString());
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("char")) {
                        Integer value1 = (int) (v1.toString().charAt(0));
                        Integer value2 = (int) (v2.toString().charAt(0));
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("int") || typeName.endsWith("integer")) {
                        Integer value1 = Integer.parseInt(v1.toString());
                        Integer value2 = Integer.parseInt(v2.toString());
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("long")) {
                        Long value1 = Long.parseLong(v1.toString());
                        Long value2 = Long.parseLong(v2.toString());
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("float")) {
                        Float value1 = Float.parseFloat(v1.toString());
                        Float value2 = Float.parseFloat(v2.toString());
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("double")) {
                        Double value1 = Double.parseDouble(v1.toString());
                        Double value2 = Double.parseDouble(v2.toString());
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("boolean")) {
                        Boolean value1 = Boolean.parseBoolean(v1.toString());
                        Boolean value2 = Boolean.parseBoolean(v2.toString());
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("date")) {
                        Date value1 = (Date) (v1);
                        Date value2 = (Date) (v2);
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else {
                        //调用对象的compareTo()方法比较大小
                        Method method = field.getType().getDeclaredMethod("compareTo", field.getType());
                        method.setAccessible(true); //设置可访问权限
                        int result = (Integer) method.invoke(v1, new Object[]{v2});
                        return ASC_order ? result : result * (-1);
                    }
                } catch (Exception e) {
                    String err = e.getLocalizedMessage();
                    System.out.println(err);
                    e.printStackTrace();
                }

                return 0; //未知类型，无法比较大小
            }
        });
    }

    /**
     * double类型如果小数点后为零显示整数否则保留 返回String
     */
    public static String doubleTrans(double num) {
        if (Math.round(num) - num == 0) {
            return String.valueOf((long) num);
        }
        return String.valueOf(num);
    }

}
