package com.example.sunshine.myapplication.utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by liyu on 2016/9/13.
 */
public class JsonUtil {

    public static Map<String, String> jsonToMap(String jsonStr) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            JSONObject json = new JSONObject(jsonStr);
            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = json.getString(key);
                map.put(key, value);
            }
        } catch (Exception e) {
            return null;
        }
        return map;
    }

    public static String mapToJson(Map<String, String> map) {
        Set<String> keys = map.keySet();
        String key = "";
        String value = "";
        StringBuffer jsonBuffer = new StringBuffer();
        jsonBuffer.append("{");
        for (Iterator<String> it = keys.iterator(); it.hasNext(); ) {
            key = (String) it.next();
            value = map.get(key);
            jsonBuffer.append("\"" + key + "\"" + ":" + "\"" + value + "\"");
            if (it.hasNext()) {
                jsonBuffer.append(",");
            }
        }
        jsonBuffer.append("}");
        return jsonBuffer.toString();
    }
}
