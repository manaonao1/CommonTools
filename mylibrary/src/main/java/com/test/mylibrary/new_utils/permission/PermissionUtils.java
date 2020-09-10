package com.test.mylibrary.new_utils.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 权限动态请求工具类
 *
 * @author Wang
 * Created on 2019-12-05 14:06
 */
public class PermissionUtils {
    private static final int PERMISSION_REQUEST_CODE = 1001;
    private static PermissionCallBack mActivityCallBack;

    /**
     * Activity单个权限申请
     *
     * @param activity           需要申请权限的Activity
     * @param perm               需要申请的权限
     * @param permissionCallBack 请求结果回调，可以为null
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void activityRequestPermission(Activity activity, String perm, PermissionCallBack permissionCallBack) {
        mActivityCallBack = permissionCallBack;

        String[] reqPerm = new String[]{perm};
        if (ContextCompat.checkSelfPermission(activity, perm) != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(reqPerm, PERMISSION_REQUEST_CODE);
        } else {
            List<String> grantedPerms = new ArrayList<>();
            grantedPerms.add(perm);
            permissionCallBack.granted(grantedPerms);
        }
    }

    private static List<String> mActivityGrantedPerms = null;

    /**
     * Activity多个权限申请
     *
     * @param activity           需要申请权限的Activity
     * @param perms              需要申请的权限
     * @param permissionCallBack 请求结果回调，可以为null
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void activityRequestPermission(Activity activity, String[] perms, PermissionCallBack permissionCallBack) {
        mActivityCallBack = permissionCallBack;

        List<String> needReqPerms = new ArrayList<>();
        for (int i = 0; i < perms.length; i++) {
            if (ContextCompat.checkSelfPermission(activity, perms[i]) != PackageManager.PERMISSION_GRANTED) {
                needReqPerms.add(perms[i]);
            } else {
                if (mActivityGrantedPerms == null) {
                    mActivityGrantedPerms = new ArrayList<>();
                }
                mActivityGrantedPerms.add(perms[i]);
            }
        }

        if (needReqPerms.size() > 0) {
            String[] reqPerms = new String[needReqPerms.size()];
            activity.requestPermissions(needReqPerms.toArray(reqPerms), PERMISSION_REQUEST_CODE);
        } else {
            if (mActivityCallBack != null) {
                mActivityCallBack.granted(mActivityGrantedPerms);
            }
        }

    }

    /**
     * 权限请求结果，在Activity基类的onRequestPermissionsResult中使用
     *
     * @param requestCode  请求Code
     * @param permissions  请求的权限
     * @param grantResults 权限请求结果
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void onActivityRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mActivityCallBack != null) {
            if (mActivityGrantedPerms == null) {
                mActivityGrantedPerms = new ArrayList<>();
            }
            List<String> deniedPerms = new ArrayList<>();
            if (requestCode == PERMISSION_REQUEST_CODE) {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        mActivityGrantedPerms.add(permissions[i]);
                    } else {
                        deniedPerms.add(permissions[i]);
                    }
                }
            }

            mActivityCallBack.granted(mActivityGrantedPerms);
            mActivityCallBack.denied(deniedPerms);
        }
    }

    private static PermissionCallBack mFragmentCallBack;

    /**
     * Fragment单个权限申请
     *
     * @param fragment           需要申请权限的Fragment
     * @param perm               需要申请的权限
     * @param permissionCallBack 请求结果回调，可以为null
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void fragmentRequestPermission(Fragment fragment, String perm, PermissionCallBack permissionCallBack) {
        mFragmentCallBack = permissionCallBack;

        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(fragment.getContext()), perm) != PackageManager.PERMISSION_GRANTED) {
            String[] reqPerm = new String[]{perm};
            fragment.requestPermissions(reqPerm, PERMISSION_REQUEST_CODE);
        } else {
            List<String> grantedPerms = new ArrayList<>();
            grantedPerms.add(perm);
            permissionCallBack.granted(grantedPerms);
        }

    }

    private static List<String> mFragmentGrantedPerms;

    /**
     * Fragment多个权限申请
     *
     * @param fragment           需要申请权限的Fragment
     * @param perms              需要申请的权限
     * @param permissionCallBack 请求结果回调，可以为null
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void fragmentRequestPermission(Fragment fragment, String[] perms, PermissionCallBack permissionCallBack) {
        mFragmentCallBack = permissionCallBack;

        List<String> needReqPerms = new ArrayList<>();
        for (int i = 0; i < perms.length; i++) {
            if (ContextCompat.checkSelfPermission(Objects.requireNonNull(fragment.getContext()), perms[i]) != PackageManager.PERMISSION_GRANTED) {
                needReqPerms.add(perms[i]);
            } else {
                if (mFragmentGrantedPerms == null) {
                    mFragmentGrantedPerms = new ArrayList<>();
                }
                mFragmentGrantedPerms.add(perms[i]);
            }
        }

        if (needReqPerms.size() > 0) {
            String[] reqPerms = new String[needReqPerms.size()];
            fragment.requestPermissions(needReqPerms.toArray(reqPerms), PERMISSION_REQUEST_CODE);
        } else {
            if (mFragmentCallBack != null) {
                mFragmentCallBack.granted(mFragmentGrantedPerms);
            }
        }
    }

    /**
     * 权限请求结果，在Fragment基类的onRequestPermissionsResult中使用
     *
     * @param requestCode  请求Code
     * @param permissions  请求的权限
     * @param grantResults 权限请求结果
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void onFragmentRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mFragmentCallBack != null) {
            if (mFragmentGrantedPerms == null) {
                mFragmentGrantedPerms = new ArrayList<>();
            }
            List<String> deniedPerms = new ArrayList<>();
            if (requestCode == PERMISSION_REQUEST_CODE) {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        mFragmentGrantedPerms.add(permissions[i]);
                    } else {
                        deniedPerms.add(permissions[i]);
                    }
                }
            }

            mFragmentCallBack.granted(mFragmentGrantedPerms);
            mFragmentCallBack.denied(deniedPerms);
        }
    }
}
