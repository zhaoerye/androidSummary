package com.example.sunshine.myapplication.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sunshine.myapplication.R;

import com.example.sunshine.myapplication.utils.ToastUtil;



/**
 * 可以选择并可以编辑内容的edittext
 * <p>
 * <br>
 *
 * @author jianyu.l
 * @Modify liyu
 * @since 2014年8月4日
 */
public class ChoiceEditText extends LinearLayout {

    private CharSequence[] mEntries;

    private boolean mMultipleChoice = false;

    private AlertDialog choiceDialog = null;

    private boolean[] selected;

    private EditText editText;

    private ImageView imgChoice;

    public EditText getEditText() {
        return editText;
    }

    public Editable getText() {
        return editText.getText();
    }

    public void setText(CharSequence text) {
        editText.setText(text);
    }

    public ChoiceEditText(Context context) {
        this(context, null);
    }

    public ChoiceEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ChoiceEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray type = context.obtainStyledAttributes(attrs,
                R.styleable.ChoiceEditText);
        editText = new EditText(context, attrs, defStyle);
        setChoiceData(type.getTextArray(R.styleable.ChoiceEditText_entries));
        editText.setEnabled(type.getBoolean(R.styleable.ChoiceEditText_enabledEdit, true));
        this.mMultipleChoice = type.getBoolean(
                R.styleable.ChoiceEditText_multipleChoice, true);
        final String dict = type.getString(R.styleable.ChoiceEditText_dict);
        if (!TextUtils.isEmpty(dict)) {
//            DataSupport.where("name = ?", dict).findFirstAsync(DictItem.class).listen(new FindCallback() {
//                @Override
//                public <T> void onFinish(T t) {
//                    DictItem item = (DictItem) t;
//                    if (item == null) {
//                        return;
//                    }
//                    List<String> list = RetrofitManager.gson().fromJson(item.getValue(),
//                            new TypeToken<List<String>>() {
//                            }.getType());
//
//                    setChoiceData(list.toArray(new String[list.size()]));
//                }
        //    });
        }
        type.recycle();
        this.setPadding(0, 0, 0, 0);

       // editText.setBackground(null);
        addView(editText, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        imgChoice = new ImageView(context);
        //imgChoice.setImageDrawable(ThemeUtil.setTintDrawable(R.drawable.ic_spinner, context, R.color.dark));
        imgChoice.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog();

            }
        });
        //imgChoice.setBackgroundColor(getResources().getColor(R.color.black));
        //考虑到有的时候因为图标太小，点击时有的时候会点击不到
        addView(imgChoice, new LayoutParams(40, LayoutParams.WRAP_CONTENT));
    }


    /**
     * 设置数据源
     *
     * @param data
     */
    public void setChoiceData(CharSequence[] data) {
        if (data != null) {
            this.mEntries = data;
            selected = new boolean[this.mEntries.length];
        }
    }

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

            if (mMultipleChoice) {// 多选
                builder.setMultiChoiceItems(mEntries,
                        new boolean[mEntries.length],
                        new OnMultiChoiceClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                selected[which] = isChecked;
                            }
                        }).setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                String resultStr = "";
                                for (int i = 0; i < selected.length; i++) {
                                    if (selected[i]) {
                                        resultStr += mEntries[i] + ",";
                                    }
                                }
                                if (resultStr.length() > 0) {
                                    resultStr = resultStr.substring(0,
                                            resultStr.length() - 1);
                                }
                                editText.setText(resultStr);
                                editText.setSelection(editText.getText()
                                        .length());

                            }
                        });

            } else {// 单选
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
            }

            choiceDialog = builder.create();
        }

        choiceDialog.show();
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        try {
            super.onRestoreInstanceState(state);
        } catch (Exception e) {
        }
        state = null;
    }

}
