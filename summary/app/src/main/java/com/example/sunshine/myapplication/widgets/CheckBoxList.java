package com.example.sunshine.myapplication.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.example.sunshine.myapplication.R;


public class CheckBoxList extends AutoWrapLayout {

    private Context context;
    @SuppressWarnings("unused")
    private final static String TAG = "AutoWrapLayout";

    private String[] items;
    private String[] values;
    private AppCompatCheckBox[] checkBoxs;
    private String splitChar = ",";
    private boolean multiple = false;
    private boolean isValueMapping = false; //是否值映射

    private CheckBoxStatusListener statusListener;

    public CheckBoxList(Context context) {
        super(context);
        this.context = context;
    }

    public CheckBoxList(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        TypedArray type = context.obtainStyledAttributes(attrs,
                R.styleable.CheckBoxList);

        multiple = type.getBoolean(R.styleable.CheckBoxList_multiple, false);

        String items = type.getString(R.styleable.CheckBoxList_items);
        if (items != null && !items.isEmpty()) {
            setItems(items.split(","));
        }

        String values = type.getString(R.styleable.CheckBoxList_values);
        if (values != null && !values.isEmpty()) {
            isValueMapping = true;
            this.values = values.split(",");
        }

        type.recycle();

    }

    public String getText() {
        String val = "";
        for (int i = 0; i < checkBoxs.length; i++) {
            if (checkBoxs[i].isChecked()) {
                if (val.equals("")) {
                    if (isValueMapping)
                        val = values[i];
                    else
                        val = items[i];

                } else {
                    if (isValueMapping)
                        val += splitChar + values[i];
                    else
                        val += splitChar + items[i];

                }
            }
        }
        return val;
    }

    public void setText(String text) {
        String[] stringList = text.split(",");

        if (checkBoxs != null && checkBoxs.length > 0) {
            for (int i = 0; i < stringList.length; i++) {
                if (isValueMapping) {
                    for (int j = 0; j < values.length; j++) {
                        if (values[j].equals(stringList[i])) {
                            checkBoxs[j].setChecked(true);
                        }
                    }
                } else {
                    for (int j = 0; j < items.length; j++) {
                        if (items[j].equals(stringList[i])) {
                            checkBoxs[j].setChecked(true);
                        }
                    }
                }
            }
        }
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String items) {
        if (!TextUtils.isEmpty(items))
            setItems(items.split(","));
    }

    public void setItems(String[] items) {
        if (items != null) {
            removeAllViews();
            this.items = items;
            checkBoxs = new AppCompatCheckBox[items.length];
            for (int i = 0; i < items.length; i++) {
                AppCompatCheckBox checkbox = new AppCompatCheckBox(this.context);
                checkbox.setText(items[i]);
                if (!multiple) {
                    checkbox.setOnCheckedChangeListener(singalListener);
                }
                checkBoxs[i] = checkbox;
                addView(checkbox);
            }
        }
    }

    public void setValues(String values) {
        if (!TextUtils.isEmpty(values)) {
            isValueMapping = true;
            this.values = values.split(",");
        }

    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    /*
     * 单选监控
     * */
    private OnCheckedChangeListener singalListener = new OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                if (checkBoxs != null) {
                    for (int i = 0; i < checkBoxs.length; i++) {
                        if (checkBoxs[i].isChecked() && !buttonView.getText().toString().equals(items[i])) {
                            checkBoxs[i].setChecked(false);
                        }
                    }
                }
                if (statusListener != null) {
                    statusListener.onChecked(CheckBoxList.this, isChecked);
                }
            }
        }
    };

    public void setValueMapping(boolean mapping) {
        this.isValueMapping = mapping;
    }

    public void setStatusListener(CheckBoxStatusListener listener) {
        this.statusListener = listener;
    }

    public interface CheckBoxStatusListener {
        void onChecked(CheckBoxList which, boolean isChecked);
    }

    public void clear() {
        for (CheckBox box : checkBoxs) {
            box.setChecked(false);
        }
    }

}
