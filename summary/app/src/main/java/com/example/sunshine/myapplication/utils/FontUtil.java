package com.example.sunshine.myapplication.utils;

import android.content.Context;
import android.graphics.Typeface;

import com.example.sunshine.myapplication.App;


/**
 * Created by liyu on 2016/9/28.
 */

public class FontUtil {

    public enum Font {
        Default,
        Kaiti,
        Yingbi,
        Shaonv
    }

    public enum Scale {
        VerySmall,
        Small,
        Normal,
        Big,
        VeryBig
    }

    public static Typeface getTypeface(Font font) {
        switch (font) {
            case Default:
                return Typeface.DEFAULT;
            case Kaiti:
                return Typeface.createFromAsset(App.getContext().getAssets(), "fonts/kaiti.ttf");
            case Yingbi:
                return Typeface.createFromAsset(App.getContext().getAssets(), "fonts/yingbi.ttf");
            case Shaonv:
                return Typeface.createFromAsset(App.getContext().getAssets(), "fonts/shaonv.ttf");
            default:
                return Typeface.DEFAULT;

        }
    }

    public static float getFontScale(Scale scale) {
        switch (scale) {
            case VerySmall:
                return 0.5f;
            case Small:
                return 0.8f;
            case Normal:
                return 1.0f;
            case Big:
                return 1.2f;
            case VeryBig:
                return 1.5f;
            default:
                return 1.0f;
        }
    }

    public static Font getCurrentFont(Context context) {
        return Font.valueOf((String) SPUtil.get(context, "app_font", Font.Default.name()));
    }

    public static void setCurrentFont(Context context, Font currentFont) {
        SPUtil.put(context, "app_font", currentFont.name());
    }

    public static Scale getCurrentFontScale(Context context) {
        return Scale.valueOf((String) SPUtil.get(context, "app_font_scale", Scale.Normal.name()));
    }

    public static void setCurrentFontScale(Context context, Scale scale) {
        SPUtil.put(context, "app_font_scale", scale.name());
    }
}
