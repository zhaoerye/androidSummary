package com.example.sunshine.myapplication.widgets.quicktable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;


import com.example.sunshine.myapplication.utils.SizeUtils;

import java.util.List;

/**
 * Created by liyu on 2016/11/4.
 */

public class QuickTableView extends LinearLayout {

    LayoutParams vLineParams;
    LayoutParams rowParams;
    LayoutParams hLineParams;

    /**
     * 表格线框颜色
     */
    private int borderColor;

    /**
     * 表格线框粗度
     */
    private float borderWidth;

    /**
     * 单元格内容最小上间距
     */
    private float cellMarginTop;

    /**
     * 单元格内容最小下间距
     */
    private float cellMarginBottom;

    public QuickTableView(Context context) {
        this(context, null);
    }

    public QuickTableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
//        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.QuickTableView);
//        borderColor = mTypedArray.getColor(R.styleable.QuickTableView_borderLineColor, Color.BLACK);
//        borderWidth = mTypedArray.getDimension(R.styleable.QuickTableView_borderLineWidth, 2);
//        cellMarginTop = mTypedArray.getDimension(R.styleable.QuickTableView_cellMarginTop, SizeUtils.dp2px(context, 4));
//        cellMarginBottom = mTypedArray.getDimension(R.styleable.QuickTableView_cellMarginBottom, SizeUtils.dp2px(context, 4));
//        mTypedArray.recycle();
        initLayoutParams();
    }

    public void addRows(List<QuickTableRow> rows) {
        boolean hasChild = this.getChildCount() > 0;
        for (int i = 0; i < rows.size(); i++) {
            drawRow(this, rows.get(i), hasChild ? false : i == 0);
        }
    }

    public void addRow(QuickTableRow row) {
        boolean hasChild = this.getChildCount() > 0;
        drawRow(this, row, !hasChild);
    }

    private void initLayoutParams() {
        rowParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        hLineParams = new LayoutParams((int) borderWidth, LayoutParams.MATCH_PARENT);
        vLineParams = new LayoutParams(LayoutParams.MATCH_PARENT, (int) borderWidth);
    }

    private void drawRow(LinearLayout parent, QuickTableRow quickTableRow, boolean isFirstRow) {
        if (isFirstRow && parent instanceof QuickTableView) {
            View vLineTop = new View(getContext());
            vLineTop.setBackgroundColor(borderColor);
            parent.addView(vLineTop, vLineParams);
        }
        LinearLayout row = new LinearLayout(getContext());
        row.setLayoutParams(rowParams);
        row.setOrientation(HORIZONTAL);
        row.removeAllViews();
        List cells = quickTableRow.getCells();
        for (int i = 0; i < cells.size(); i++) {
            LayoutParams cellParams = new LayoutParams(0,
                    LayoutParams.WRAP_CONTENT, quickTableRow.getCellWeights().get(i));
            cellParams.gravity = Gravity.CENTER;
            if (parent instanceof QuickTableView || i != 0) {
                View hLineStart = new View(getContext());
                hLineStart.setBackgroundColor(borderColor);
                row.addView(hLineStart, hLineParams);
            }
            if (cells.get(i) instanceof QuickTableRow) {
                LinearLayout cellRow = new LinearLayout(getContext());
                cellRow.setLayoutParams(rowParams);
                cellRow.setOrientation(HORIZONTAL);
                cellRow.removeAllViews();
                drawRow(cellRow, ((QuickTableRow) cells.get(i)), false);
                cellRow.setLayoutParams(cellParams);
                row.addView(cellRow);
            } else if (cells.get(i) instanceof View) {
                cellParams.setMargins(0, (int) cellMarginTop, 0, (int) cellMarginBottom);
                View v = (View) cells.get(i);
                v.setLayoutParams(cellParams);
                row.addView(v);
            } else if (cells.get(i) instanceof List) {
                LinearLayout cellRow = new LinearLayout(getContext());
                cellRow.setLayoutParams(cellParams);
                cellRow.setOrientation(VERTICAL);
                cellRow.removeAllViews();
                for (int n = 0; n < ((List<QuickTableRow>) (cells.get(i))).size(); n++) {
                    LinearLayout cell = new LinearLayout(getContext());
                    cell.setLayoutParams(rowParams);
                    cell.setOrientation(HORIZONTAL);
                    cell.removeAllViews();
                    drawRow(cell, ((List<QuickTableRow>) (cells.get(i))).get(n), false);
                    cellRow.addView(cell);
                    if (n != ((List<QuickTableRow>) (cells.get(i))).size() - 1) {
                        View vLineBottom = new View(getContext());
                        vLineBottom.setBackgroundColor(borderColor);
                        cellRow.addView(vLineBottom, vLineParams);
                    }
                }
                row.addView(cellRow);
            }
            if (i == cells.size() - 1 && parent instanceof QuickTableView) {
                View hLineEnd = new View(getContext());
                hLineEnd.setBackgroundColor(borderColor);
                row.addView(hLineEnd, hLineParams);
            }
        }
        parent.addView(row);
        View vLineBottom = new View(getContext());
        vLineBottom.setBackgroundColor(borderColor);
        parent.addView(vLineBottom, vLineParams);
    }
}
