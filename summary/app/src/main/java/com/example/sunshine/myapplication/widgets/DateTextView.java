package com.example.sunshine.myapplication.widgets;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;


import com.example.sunshine.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 * 默认显示日期的textview控件，点击弹出日期选择
 * <p>
 * <br>
 *
 * @author jianyu.l
 * @modify liyu
 * @since 2014年8月5日
 */
@SuppressLint("SimpleDateFormat")
public class DateTextView extends AppCompatTextView implements OnClickListener,
        OnFocusChangeListener {

    DatePickerDialog datePickerDialog = null;
    AlertDialog customDatePickerDialog = null;
    TimePickerDialog timePickerDialog = null;
    AlertDialog bothAlertDialog = null;
    /**
     * 显示类型还是日期<br>
     * 默认为0日期1时间2日期和时间
     */
    int timeType = 0;
    /**
     * 设置日期的显示格式
     * 默认为 1 显示格式为  yyyy-MM-dd
     * 如果为0 显示格式为  yyyy年MM月dd日
     */
    int dateFormat = 0;
    /**
     * 设置时间后的回调
     */

    /**
     * 显示风格，仅仅支持日期的选择
     * 0为系统默认风格
     * 1为自定义风格
     */
    int style = 0;

    private OnSetListener onSetListener;

    private Context mContext;

    public OnSetListener getOnSetListener() {
        return onSetListener;
    }

    public void setOnSetListener(OnSetListener onSetListener) {
        this.onSetListener = onSetListener;
    }

    public DateTextView(Context context) {
        this(context, null);
    }

    public DateTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public DateTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        TypedArray a = mContext.obtainStyledAttributes(attrs,
                R.styleable.DateTextView, defStyle, 0);

        this.timeType = a.getInt(R.styleable.DateTextView_timeType, 0);
        boolean now = a.getBoolean(R.styleable.DateTextView_now, true);
        this.dateFormat = a.getInt(R.styleable.DateTextView_dateFormat, 1);
        this.style = a.getInt(R.styleable.DateTextView_style, 0);
        a.recycle();

        setOnClickListener(this);
        setOnFocusChangeListener(this);

        if (now) {
            if (this.timeType == 1)
                setText((new SimpleDateFormat("HH:mm")).format(new Date()));
            else if (this.timeType == 0)
                if (dateFormat == 0) {
                    setText((new SimpleDateFormat("yyyy年MM月dd日"))
                            .format(new Date()));
                } else {
                    setText((new SimpleDateFormat("yyyy-MM-dd"))
                            .format(new Date()));
                }

            else if (dateFormat == 0) {
                setText((new SimpleDateFormat("yyyy年MM月dd日 HH:mm"))
                        .format(new Date()));
            } else {
                setText((new SimpleDateFormat("yyyy-MM-dd  HH:mm"))
                        .format(new Date()));
            }

        }

    }

    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            onClick(v);
        }
    }

    public void onClick(View v) {
        if (this.timeType == 1)
            showTime();
        else if (this.timeType == 0)
            showDate();
        else
            showBoth();

    }

    /**
     * 显示日期
     */
    private void showDate() {
        if (style == 0) {
            if (datePickerDialog == null)
                datePickerDialog = new DatePickerDialog(mContext,
                        new OnDateSetListener() {

                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                setText(year, monthOfYear, dayOfMonth, 0, 0);

                            }
                        }, Calendar.getInstance().get(Calendar.YEAR), Calendar
                        .getInstance().get(Calendar.MONTH), Calendar
                        .getInstance().get(Calendar.DAY_OF_MONTH));

            datePickerDialog.show();
//        } else {
//            if (customDatePickerDialog == null) {
//                customDatePickerDialog = new AlertDialog.Builder(getContext()).create();
//                customDatePickerDialog.show();
//                DPTManager.getInstance().initCalendar(new DateDialogTheme());
//                final cn.aigestudio.datepicker.views.DatePicker picker = new cn.aigestudio.datepicker.views.DatePicker(getContext());
//                picker.setDate(Calendar.getInstance().get(Calendar.YEAR), Calendar
//                        .getInstance().get(Calendar.MONTH) + 1);
//                picker.setMode(DPMode.SINGLE);
//                picker.setHolidayDisplay(false);
//                picker.setOnDatePickedListener(new cn.aigestudio.datepicker.views.DatePicker.OnDatePickedListener() {
//                    @Override
//                    public void onDatePicked(String date) {
//                        picker.setTodayDisplay(false);
//                        customDatePickerDialog.dismiss();
//                        if (dateFormat == 0) {
//                            SimpleDateFormat formatEN = new SimpleDateFormat("yyyy-MM-dd");
//                            SimpleDateFormat formatCN = new SimpleDateFormat("yyyy年MM月dd日");
//                            try {
//                                Date dateStr = formatEN.parse(date);
//                                setText(formatCN.format(dateStr));
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                                setText(date);
//                            }
//                        } else {
//                            setText(date);
//                        }
//                        if (onSetListener != null)
//                            onSetListener.onSet(getText().toString());
//                    }
//                });
//                FontsManager.changeFonts(picker);
//                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                customDatePickerDialog.getWindow().setContentView(picker, params);
//                customDatePickerDialog.getWindow().setGravity(Gravity.CENTER);
//            } else
//                customDatePickerDialog.show();
//        }
        }
    }

    /**
     * 显示时间
     */
    private void showTime() {
        if (timePickerDialog == null)
            timePickerDialog = new TimePickerDialog(mContext,
                    new OnTimeSetListener() {

                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            setText(0, 0, 0, hourOfDay, minute);

                        }
                    }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE), true);

        timePickerDialog.show();
    }

    /**
     * 显示日期和时间
     */
    private void showBoth() {
        if (bothAlertDialog == null) {
            LinearLayout layout = new LinearLayout(mContext);
            layout.setGravity(Gravity.CENTER);
            layout.setOrientation(LinearLayout.VERTICAL);
            final DatePicker datePicker = new DatePicker(mContext);
            final TimePicker timePicker = new TimePicker(mContext);

            datePicker.setCalendarViewShown(false);
            timePicker.setIs24HourView(true);

            layout.addView(datePicker);
            layout.addView(timePicker);

            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {

                        setText(datePicker.getYear(), datePicker.getMonth(),
                                datePicker.getDayOfMonth(),
                                timePicker.getCurrentHour(),
                                timePicker.getCurrentMinute());
                    }
                }
            };

            bothAlertDialog = new AlertDialog.Builder(mContext).create();
            bothAlertDialog.setView(layout);
            bothAlertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "设定",
                    onClickListener);
            bothAlertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                    onClickListener);
        }

        bothAlertDialog.show();
    }

    /**
     * 设置当前的显示内容
     *
     * @param year
     * @param month
     * @param dayOfMonth
     * @param hour
     * @param minute
     */
    private void setText(int year, int month, int dayOfMonth, int hour,
                         int minute) {
        switch (timeType) {
            case 0:
                if (dateFormat == 0) {
                    setText(String.format("%04d年%02d月%02d 日", year, month + 1,
                            dayOfMonth));
                } else {
                    setText(String.format("%04d-%02d-%02d", year, month + 1,
                            dayOfMonth));
                }

                break;

            case 1:
                setText(String.format("%02d:%02d", hour, minute));
                break;
            case 2:
                if (dateFormat == 0) {
                    setText(String.format("%04d年%02d月%02d 日%02d:%02d", year, month + 1,
                            dayOfMonth, hour, minute));
                } else {
                    setText(String.format("%04d-%02d-%02d %02d:%02d", year, month + 1,
                            dayOfMonth, hour, minute));
                }


                setText(String.format("%04d年%02d月%02d 日%02d:%02d", year, month + 1,
                        dayOfMonth, hour, minute));
                break;
        }

        Calendar.getInstance().set(year, month, dayOfMonth, hour, minute);

        if (onSetListener != null)
            onSetListener.onSet(getText().toString());
    }

    public interface OnSetListener {
        void onSet(String text);
    }

    private int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

//    private class DateDialogTheme extends DPTheme {
//
//        /**
//         * 月视图背景色
//         * <p>
//         * Color of MonthView's background
//         *
//         * @return 16进制颜色值 hex color
//         */
//        @Override
//        public int colorBG() {
//            return 0xFFFFFFFF;
//        }
//
//        /**
//         * 选中背景圆颜色
//         * <p>
//         * Color of MonthView's selected circle
//         *
//         * @return 16进制颜色值 hex color
//         */
//        @Override
//        public int colorBGCircle() {
//            return adjustAlpha(getContext().getResources().getColor(ThemeUtil.getCurrentColorPrimary(getContext())), 0.4f);
//        }
//
//        /**
//         * 标题栏背景色
//         * <p>
//         * Color of TitleBar's background
//         *
//         * @return 16进制颜色值 hex color
//         */
//        @Override
//        public int colorTitleBG() {
//            return getContext().getResources().getColor(ThemeUtil.getCurrentColorPrimary(getContext()));
//        }
//
//        /**
//         * 标题栏文本颜色
//         * <p>
//         * Color of TitleBar text
//         *
//         * @return 16进制颜色值 hex color
//         */
//        @Override
//        public int colorTitle() {
//            return 0xEEFFFFFF;
//        }
//
//        /**
//         * 今天的背景色
//         * <p>
//         * Color of Today's background
//         *
//         * @return 16进制颜色值 hex color
//         */
//        @Override
//        public int colorToday() {
//            return adjustAlpha(getContext().getResources().getColor(ThemeUtil.getCurrentColorPrimary(getContext())), 0.4f);
//        }
//
//        /**
//         * 公历文本颜色
//         * <p>
//         * Color of Gregorian text
//         *
//         * @return 16进制颜色值 hex color
//         */
//        @Override
//        public int colorG() {
//            return 0xEE333333;
//        }
//
//        /**
//         * 节日文本颜色
//         * <p>
//         * Color of Festival text
//         *
//         * @return 16进制颜色值 hex color
//         */
//        @Override
//        public int colorF() {
//            return getContext().getResources().getColor(ThemeUtil.getCurrentColorPrimary(getContext()));
//        }
//
//        /**
//         * 周末文本颜色
//         * <p>
//         * Color of Weekend text
//         *
//         * @return 16进制颜色值 hex color
//         */
//        @Override
//        public int colorWeekend() {
//            return getContext().getResources().getColor(ThemeUtil.getCurrentColorPrimary(getContext()));
//        }
//
//        /**
//         * 假期文本颜色
//         * <p>
//         * Color of Holiday text
//         *
//         * @return 16进制颜色值 hex color
//         */
//        @Override
//        public int colorHoliday() {
//            return 0x80FED6D6;
//        }
//
//        /**
//         * 农历文本颜色
//         * <p>
//         * Color of Lunar text
//         *
//         * @return 16进制颜色值 hex color
//         */
//        public int colorL() {
//            return 0xEE888888;
//        }
//
//        /**
//         * 补休日期背景颜色
//         * <p>
//         * Color of Deferred background
//         *
//         * @return 16进制颜色值 hex color
//         */
//        public int colorDeferred() {
//            return 0x50B48172;
//        }
//    }

}
