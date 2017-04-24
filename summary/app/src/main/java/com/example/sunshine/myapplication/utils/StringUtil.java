package com.example.sunshine.myapplication.utils;

/**
 * Created by liyu on 2016/8/31.
 */
public class StringUtil {

    public static String subString(String src, String fromString,
                                   String toString) {
        int fromPos = 0;
        if (fromString != null && fromString.length() > 0) {
            fromPos = src.indexOf(fromString);
            fromPos += fromString.length();
        }
        int toPos = src.indexOf(toString, fromPos);
        return src.substring(fromPos, toPos);
    }
}
