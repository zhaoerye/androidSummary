package com.example.sunshine.myapplication.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 针对Android 6.0的运行时权限检查工具
 * <p>
 * Created by liyu on 2016/10/28.
 */

public class PermissionUtil {

    public static final int PERMISSIONS_REQUEST_STORAGE = 116;
    public static final int PERMISSIONS_REQUEST_CAMERA = 118;
    public static final int PERMISSIONS_REQUEST_ALL = 666;

    public static boolean permissionGranted(Activity activity, int requestCode, String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        List<String> permissionList = new ArrayList<String>();
        Collections.addAll(permissionList, permissions);
        return permissionGranted(activity, requestCode, permissionList);
    }

    public static boolean permissionGranted(Activity activity, int requestCode, List<String> permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        boolean hasPermission = true;
        int len = permissions.size();
        for (int i = len - 1; i >= 0; i--) {
            String permission = permissions.get(i);
            boolean iHasPermission = ContextCompat.checkSelfPermission(activity, permission)
                    == PackageManager.PERMISSION_GRANTED;
            if (iHasPermission) {
                permissions.remove(i);
            } else {
                hasPermission = iHasPermission;
            }

        }
        if (!hasPermission) {
            ActivityCompat.requestPermissions(activity, permissions.toArray(new String[permissions.size()]),
                    requestCode);
        }
        return hasPermission;
    }

}
