package com.example.sunshine.myapplication.widgets.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.example.sunshine.myapplication.R;
import com.example.sunshine.myapplication.utils.SizeUtils;

/**
 * Created by liyu on 2016/8/23.
 */
public class IndicatorItem extends View implements View.OnClickListener {

    private Paint paint;

    private float roundWidth;

    private float innerCircle;

    private boolean isChecked;

    private int textColor;

    private float textSize;

    private String text;

    private int color;

    private OnCheckedChangeListener mChildOnCheckedChangeListener;


    public IndicatorItem(Context context) {
        this(context, null);
    }

    public IndicatorItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.IndicatorItem);
       // color = mTypedArray.getColor(R.styleable.IndicatorItem_indicatorColor, context.getResources().getColor(ThemeUtil.getCurrentColorPrimary(context)));
        textColor = mTypedArray.getColor(R.styleable.IndicatorItem_indicatorTextColor, Color.BLACK);
        roundWidth = mTypedArray.getDimension(R.styleable.IndicatorItem_indicatorRingWidth, SizeUtils.dp2px(context, 3));
        textSize = mTypedArray.getDimension(R.styleable.IndicatorItem_indicatorTextSize, SizeUtils.sp2px(context, 14));
        text = mTypedArray.getString(R.styleable.IndicatorItem_indicatorText);
        if (TextUtils.isEmpty(text)) {
            text = "2012-06-30";
        }
        isChecked = mTypedArray.getBoolean(R.styleable.IndicatorItem_indicatorChecked, false);
        innerCircle = mTypedArray.getDimension(R.styleable.IndicatorItem_indicatorInnerCircle, SizeUtils.dp2px(context, 4));
        mTypedArray.recycle();

        this.setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        int center = getWidth() / 2;
        int height = getHeight();
        int padding = SizeUtils.dp2px(this.getContext(), 4);

        if (isChecked) {
            //绘制内圆
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(center, height / 3, innerCircle, this.paint);
        }

        //绘制外圆
        paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(roundWidth);
        canvas.drawCircle(center, height / 3, innerCircle + padding, this.paint);

        this.paint.setStrokeWidth(roundWidth);
        canvas.drawLine(0, height / 3, center - innerCircle - padding, height / 3, paint);

        canvas.drawLine(center + innerCircle + padding, height / 3, getWidth(), height / 3, paint);

        paint.setStrokeWidth(0);
        paint.setColor(textColor);
       // paint.setTypeface(FontsManager.defaultTypeface);
        if (isChecked) {
            paint.setTextSize(textSize + 5);
        } else {
            paint.setTextSize(textSize);
        }
        float textWidth = paint.measureText(text);
        canvas.drawText(text, center - textWidth / 2, height / 3 + innerCircle + SizeUtils.dp2px(this.getContext(), 32), paint);

        super.onDraw(canvas);
    }

    public void setOnCheckedChangeWidgetListener(OnCheckedChangeListener mChildOnCheckedChangeListener) {
        this.mChildOnCheckedChangeListener = mChildOnCheckedChangeListener;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        if (isChecked == checked)
            return;
        this.isChecked = checked;
        if (mChildOnCheckedChangeListener != null)
            mChildOnCheckedChangeListener.onCheckedChanged(this, isChecked);
        postInvalidate();
    }

    public void setText(String text) {
        this.text = text;
        postInvalidate();
    }

    public String getText() {
        return this.text;
    }

    @Override
    public void onClick(View v) {
        setChecked(true);
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(View v, boolean isChecked);
    }

}
