package com.example.sunshine.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;


/**
 * Created by liyu on 2016/9/12.
 */
public class SearchBarDialog extends AlertDialog {


    protected SearchBarDialog(@NonNull Context context) {
        super(context);
    }

    protected SearchBarDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected SearchBarDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }



}
