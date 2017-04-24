package com.example.sunshine.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sunshine.myapplication.utils.KeyboardUtils;
import com.example.sunshine.myapplication.utils.PermissionUtil;
import com.example.sunshine.myapplication.utils.SPUtil;

public class AutoActivity extends Activity {

    private EditText nameInput;
    private EditText passwordInput;
    private AppCompatCheckBox rememberPassword;
    private Button btnLogin;
    private TextView tvSettings;

    private static final String KEY_NAME = "NAME";
    private static final String KEY_PASSWORD = "PASSWORD";
    private static final String KEY_REMEMBER = "REMEMBER";



}
