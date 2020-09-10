package com.test.mylibrary.new_utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片下载
 * Created by hory on 17/5/22.
 */

public class DownLoadImageService implements Runnable {
    private String url;
    private Context context;
    private ImageDownLoadCallBack callBack;
    private File currentFile;
    private String fileName;
    public DownLoadImageService(Context context, String url, String fileName, ImageDownLoadCallBack callBack) {
        this.url = url;
        this.callBack = callBack;
        this.context = context;
        this.fileName = fileName;
    }

    public void saveImageToGallery(Context context, Bitmap bmp) {
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();//注意小米手机必须这样获得public绝对路径
        String dirName = "IZK";
        File appDir = new File(file, dirName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        // 首先保存图片
        if (fileName == null) {
            String fileName = System.currentTimeMillis() + ".jpg";
            currentFile = new File(appDir, fileName);
        } else {
            currentFile = new File(appDir, fileName);
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
            }
        }

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(currentFile.getPath()))));
    }

    @Override
    public void run() {

    }

    public interface ImageDownLoadCallBack {
        void onDownLoadSuccess(File file);
        void onDownLoadSuccess(Bitmap bitmap);
        void onDownLoadFailed();
    }
}
