package com.example.sunshine.myapplication.widgets.quicktable;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sunshine.myapplication.widgets.CheckBoxList;


/**
 * Created by liyu on 2016/11/4.
 */

public class QuickTableCell {

    public static TextView makeTextCell(Context context, String text) {
        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.BLACK);
        tv.setText(text);
        return tv;
    }

    public static EditText makeEditCell(Context context, String tag) {
        EditText et = new EditText(context);
        et.setBackground(null);
        if (!TextUtils.isEmpty(tag))
            et.setTag(tag);
        et.setGravity(Gravity.CENTER);
        et.setSingleLine(true);
        return et;
    }

    public static CheckBoxList makeCheckBoxListCell(Context context, String tag, String items, String values, boolean isMulti) {
        CheckBoxList checkBoxList = new CheckBoxList(context);
        if (!TextUtils.isEmpty(tag)) {
            checkBoxList.setTag(tag);
        }
        checkBoxList.setMultiple(isMulti);
        checkBoxList.setItems(items);
        checkBoxList.setValues(values);
        return checkBoxList;
    }

    public static CheckBoxList makeCheckBoxListCell(Context context, String tag, String items, String values) {
        return makeCheckBoxListCell(context, tag, items, values, false);
    }

    public static CheckBoxList makeCheckBoxListCell(Context context, String tag, String items) {
        return makeCheckBoxListCell(context, tag, items, null, false);
    }
}
