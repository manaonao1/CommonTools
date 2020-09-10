package com.test.mylibrary.new_utils;

import android.os.Build;
import android.text.TextUtils;

import java.util.UUID;

/**
 * Created on 2020/8/24
 *
 * @author zyf
 */
public class DeviceUtils {
    private static final String DEVICE_ID_KEY = "device_id_sp_key";

    /**
     * 获得设备硬件uuid
     * 使用硬件信息，计算出一个随机数
     *
     * @return 设备硬件uuid
     */
    public static String getDeviceUUID() {
        try {
            String deviceId = SPUtil.get(DEVICE_ID_KEY, "").toString();
            if (!TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }
            String dev = "3883756" +
                    Build.BOARD.length() % 10 +

                    Build.BRAND.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.HARDWARE.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.SERIAL.length() % 10;
            deviceId = new UUID(dev.hashCode(), Build.SERIAL.hashCode()).toString();
            SPUtil.put(DEVICE_ID_KEY, deviceId);
            return deviceId;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
