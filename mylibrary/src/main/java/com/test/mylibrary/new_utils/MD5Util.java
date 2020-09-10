package com.test.mylibrary.new_utils;

/**
 * Created by zoulu on 16/5/19.
 * md5工具类
 */

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

//    private static String tag = "Md5Util";

    public static final String KEY = ";zk;";

    public static String encode(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuffer buf = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
            // if (type) {
            // return buf.toString(); // 32
            // } else {
            // return buf.toString().substring(8, 24);// 16
            // }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * studentid md5
     */
    public static String studenIdToMD5(String stu_id){
        return encode(stu_id + KEY);
    }

    /**
     * MD5加密
     * @param string
     * @param secret
     * @return
     */
    public static String stringToMD5(String string, String secret){
        return encode(string + secret);
    }

    public static String md5LowerCase(String string) {
        if(TextUtils.isEmpty(string)){
            return "";
        }
        try {
            MessageDigest digester = MessageDigest.getInstance(ALGORITHM);
            byte[] buffer = string.getBytes(DEFAULT_CHARSET);
            digester.update(buffer);
            buffer = digester.digest();
            string = toLowerCaseHex(buffer);

        } catch (Exception e) {
        }
        return string;
    }

    public static String md5(String string) {
        try {
            MessageDigest digester = MessageDigest.getInstance(ALGORITHM);
            byte[] buffer = string.getBytes(DEFAULT_CHARSET);
            digester.update(buffer);

            buffer = digester.digest();
            string = toHex(buffer);
        } catch  (Exception e) {

        }
        return string;
    }

    public static String md5(byte[] buff) {
        try {
            MessageDigest digester = MessageDigest.getInstance(ALGORITHM);
            digester.update(buff);
            byte[] buffer = digester.digest();
            return toHex(buffer);
        } catch (NoSuchAlgorithmException e) {
        }
        return "";
    }

    public static String md5(File file) {
        InputStream fis = null;
        byte[] buffer = new byte[1024];
        int numRead = 0;
        MessageDigest md5;

        try {
            fis = new FileInputStream(file);
            md5 = MessageDigest.getInstance(ALGORITHM);
            while ((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }

            return toHex(md5.digest());
        } catch (Exception e) {
            Log.i("MD5Util", e.toString()+"");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    Log.i("MD5Util", e.toString()+"");
                }
            }
        }

        return null;
    }

    private static String toHex(byte[] b) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            int v = b[i];
            builder.append(HEX[(0xF0 & v) >> 4]);
            builder.append(HEX[0x0F & v]);
        }
        return builder.toString();
    }

    private static String toLowerCaseHex(byte[] b) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            int v = b[i];
            builder.append(HEX_LOWER_CASE[(0xF0 & v) >> 4]);
            builder.append(HEX_LOWER_CASE[0x0F & v]);
        }
        return builder.toString();
    }

    private static final char[] HEX = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };
    private static final char[] HEX_LOWER_CASE = {
            '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };
    public static final String ALGORITHM = "MD5";
    public static final String DEFAULT_CHARSET = "UTF-8";



    public static String getDeviceId(Context context){

        TelephonyManager telephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

        return telephonyMgr.getDeviceId();
    }

    public static String getSerialNumber(Context context){

        TelephonyManager telephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

        return telephonyMgr.getSimSerialNumber();
    }


    public static String getAndroidId(Context context){

        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}

