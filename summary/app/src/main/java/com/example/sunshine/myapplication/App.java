package com.example.sunshine.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;


/**
 * Created by liyu on 2016/9/12.
 */
public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
       // initScale();
        mContext = getApplicationContext();
        if (!BuildConfig.DEBUG) {
          //  AppExceptionHandler.getInstance().setCrashHanler(this);
        }
    }

    public static Context getContext() {
        return mContext;
    }

//    private void initScale() {
//        Configuration config = getResources().getConfiguration();
//        config.fontScale = FontUtil.getFontScale(FontUtil.getCurrentFontScale(this));
//        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
//    }
}
