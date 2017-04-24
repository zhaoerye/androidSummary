package com.example.sunshine.myapplication.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sunshine.myapplication.R;
import com.example.sunshine.myapplication.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyu on 2016/9/23.
 */

public class HisUserEditText extends LinearLayout {

    private CharSequence[] mEntries;

    private AlertDialog choiceDialog = null;

    private HisEditText editText;

    private ImageView imgChoice;

    public HisEditText getEditText() {
        return editText;
    }

    public Editable getText() {
        return editText.getText();
    }

    public void setText(CharSequence text) {
        editText.setText(text);
    }

    public HisUserEditText(Context context) {
        this(context, null);
    }

    public HisUserEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public HisUserEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.HisUserEditText);
        String job = mTypedArray.getString(R.styleable.HisUserEditText_job);
        String dept = mTypedArray.getString(R.styleable.HisUserEditText_dept);
        mTypedArray.recycle();
        this.setPadding(0, 0, 0, 0);
        editText = new HisEditText(context, attrs, defStyleAttr);
        // editText.setBackground(null);
        addView(editText, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        imgChoice = new ImageView(context);
        // imgChoice.setImageDrawable(ThemeUtil.setTintDrawable(R.drawable.ic_spinner, context, R.color.dark));
        imgChoice.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog();

            }
        });
        addView(imgChoice, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }
//        HisQueryUtil.getUserNameList(job, dept, new HisQueryUtil.OnResultCallback() {
//            @Override
//            public void onFinish(List<String> users) {
//                setChoiceData(users.toArray(new String[users.size()]));
//                editText.setNewData(users);
//            }
//        });
//    }

    /**
     * 设置数据源
     *
     * @param data
     */
    public void setChoiceData(CharSequence[] data) {
        if (data != null) {
            this.mEntries = data;
        }
    }

    public void setHisText(String text) {
        editText.setHisText(text);
    }

   // public String getHisText() {
   //     return editText.getHisText();
   // }

    /**
     * 弹出选择窗口
     */
    private void showDialog() {
        if (mEntries == null) {
            ToastUtil.showShort("无可选项");
            return;
        }

        if (choiceDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setSingleChoiceItems(mEntries, 0,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            editText.setText(mEntries[which]);
                            editText.setSelection(editText.getText()
                                    .length());
                            dialog.dismiss();
                        }
                    }).setNegativeButton("取消", null);

            choiceDialog = builder.create();
        }
        choiceDialog.show();
    }

    public class HisEditText extends AppCompatAutoCompleteTextView {

        private static final int SAVE_TYPE_NAME = 0;
        private static final int SAVE_TYPE_ID = 1;
        private int saveType = SAVE_TYPE_NAME;
        private ArrayAdapter arrayAdapter;
        private List<String> users;

        public HisEditText(Context context) {
            this(context, null);
        }

        public HisEditText(Context context, AttributeSet attrs) {
            this(context, attrs, android.R.attr.autoCompleteTextViewStyle);
        }

        public HisEditText(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);

            TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.HisUserEditText);
            saveType = mTypedArray.getInt(R.styleable.HisUserEditText_saveType, 0);
            mTypedArray.recycle();
            users = new ArrayList<>();
//            arrayAdapter = new ArrayAdapter(context,
//                    R.layout.spinner_text_black, users);
//            arrayAdapter.setDropDownViewResource(R.layout.spinner_text_black);
            this.setThreshold(1);
            this.setAdapter(arrayAdapter);
        }

        public void setNewData(List<String> list) {
            if (!users.isEmpty()) {
                users.clear();
            }
            users.addAll(list);
            arrayAdapter.notifyDataSetChanged();
        }

        public void setHisText(String text) {
            if (saveType == HisEditText.SAVE_TYPE_NAME) {
                this.setText(text);
            } else {
                // this.setText(HisQueryUtil.getNameById(text));
            }
        }

        public void getHisText() {
            String user_name = this.getText().toString();
            if (saveType == HisEditText.SAVE_TYPE_NAME) {
                // return user_name;
            } else {
                //  return HisQueryUtil.getIDByName(user_name);
            }

        }
    }
}
