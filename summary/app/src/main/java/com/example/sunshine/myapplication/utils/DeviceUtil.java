package com.example.sunshine.myapplication.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.text.TextUtils;


import com.example.sunshine.myapplication.App;

import java.io.File;

/**
 * 设备的相关信息获取工具类
 *
 * @author Jianyu.L (<a href="mailto:lijianyu2012@gmail.com">lijianyu2012@gmail.com</a>)
 * @since 2015-05-07 15:24
 */
public class DeviceUtil {

    /**
     * 获取缓存的对应目录，默认sdcard路劲，没有则本地
     *
     * @param filePath
     * @return
     */
    public static String getCacheDir(String filePath) {
        String dir;
        if (isSdCardEnable()) {
            dir = App.getContext().getExternalCacheDir().getAbsolutePath();
        } else {
            dir = App.getContext().getCacheDir().getAbsolutePath();
        }

        if (TextUtils.isEmpty(filePath))
            return dir;
        else {
            if (filePath.startsWith(File.separator)) {
                dir += filePath;
            } else
                dir += File.separator + filePath;


            FileUtil.makeDirs(dir);

            return dir;
        }
    }


    /**
     * 获取文件的对应目录，默认sdcard路劲，没有则本地
     *
     * @param filePath
     * @return
     */
    public static String getFileDir(String filePath) {
        String dir;
        if (isSdCardEnable()) {
            dir = App.getContext().getExternalFilesDir("").getAbsolutePath();
        } else {
            dir = App.getContext().getFilesDir().getAbsolutePath();
        }

        if (TextUtils.isEmpty(filePath))
            return dir;
        else {
            if (filePath.startsWith(File.separator)) {
                dir += filePath;
            } else
                dir += File.separator + filePath;


            FileUtil.makeDirs(dir);

            return dir;
        }
    }

    /**
     * 是否存在sdcard
     *
     * @return boolean
     */
    public static boolean isSdCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())
                || !Environment.isExternalStorageRemovable();
    }


    /**
     * 获取当前应用的版本号
     *
     * @return
     */
    public static int getAppVersion() {
        try {
            PackageInfo info = App.getContext().getPackageManager().getPackageInfo(
                    App.getContext().getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 判断当前应用是否处于debug状态
     *
     * @return
     */
    public static boolean isApkDebugable() {
        try {
            ApplicationInfo info = App.getContext().getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * 获取当前Wifi的MAC地址
     *
     * @param context
     * @return
     */
    public static String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }
}
