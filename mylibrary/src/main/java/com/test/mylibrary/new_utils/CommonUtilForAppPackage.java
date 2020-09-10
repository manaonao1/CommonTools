package com.test.mylibrary.new_utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class CommonUtilForAppPackage {
    /**
     * 安装更新包权限判断，8.0以后需要权限
     *
     * @param file 更新包文件
     */
    public static void applyInstallCheck(final Activity activity, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (activity.getPackageManager().canRequestPackageInstalls()) {
                installApk(activity, file);
            } else {
                startInstallPermissionSettingActivity(activity);
            }
        } else {
            installApk(activity, file);
        }
    }

    /**
     * 开启设置安装未知来源应用权限界面
     *
     * @param context
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void startInstallPermissionSettingActivity(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        ((Activity) context).startActivityForResult(intent, 0x101);
    }

    /**
     * 安装更新包
     *
     * @param file 更行包文件
     */
    private static void installApk(Activity activity, File file) {
        if (Build.VERSION.SDK_INT >= 24) {
            Uri apkUri = FileProvider.getUriForFile(activity, "download.fileprovider", file);//在AndroidManifest中的android:authorities值
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            activity.startActivity(install);
        } else {
            if (file != null) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                activity.startActivity(intent);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    }

    public static StringBuilder formatPhone(String phone) {
        StringBuilder stringBuilder = new StringBuilder();
        if (TextUtils.isEmpty(phone) || phone.length() < 11) {
            return stringBuilder;
        }
        stringBuilder.append(phone.substring(0, 3));
        stringBuilder.append(" ");
        stringBuilder.append(phone.substring(3, 7));
        stringBuilder.append(" ");
        stringBuilder.append(phone.substring(7));
        return stringBuilder;
    }

    /**
     * 判断是否字符串是否为汉字
     */
    public static boolean isChinese(String str) {
        List<String> strs = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            strs.add(str.substring(i, i + 1));
        }

        String regEx = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(regEx);
        for (int i = 0; i < strs.size(); i++) {
            if (!pat.matcher(strs.get(i)).find()) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断qq客户端是否存在
     */
    public static boolean isQQAppInstalled(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断应用宝是否存在
     */
    public static boolean isQQDownloaderInstall(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.android.qqdownloader")) {
                    return true;
                }
            }
        }
        return false;
    }
}

