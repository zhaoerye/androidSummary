package com.example.sunshine.myapplication.widgets.quicktable;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyu on 2016/11/4.
 */

public class QuickTableRow {

    private List cells;
    private List<Float> cellWeights;

    public QuickTableRow() {
        cells = new ArrayList<>();
        cellWeights = new ArrayList<>();
    }

    public void putCell(@NonNull View view) {
        putCell(view, 1);
    }

    public void putCell(@NonNull QuickTableRow row) {
        putCell(row, 1);
    }

    public void putCell(@NonNull List<QuickTableRow> rows) {
        putCell(rows, 1);
    }

    public void putCell(@NonNull View view, float weight) {
        cells.add(view);
        cellWeights.add(weight);
    }

    public void putCell(@NonNull QuickTableRow row, float weight) {
        cells.add(row);
        cellWeights.add(weight);
    }

    public void putCell(@NonNull List<QuickTableRow> rows, float weight) {
        cells.add(rows);
        cellWeights.add(weight);
    }

    public List getCells() {
        return cells;
    }

    public List<Float> getCellWeights() {
        return cellWeights;
    }

}
