package com.example.sunshine.myapplication;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sunshine.myapplication.http.ApiFactory;
import com.example.sunshine.myapplication.http.BaseResponse;
import com.example.sunshine.myapplication.http.Model.Trailers;
import com.example.sunshine.myapplication.http.NetCallback;
import com.example.sunshine.myapplication.utils.KeyboardUtils;
import com.example.sunshine.myapplication.utils.PermissionUtil;
import com.example.sunshine.myapplication.utils.SPUtil;
import com.example.sunshine.myapplication.utils.TimeUtil;
import com.example.sunshine.myapplication.utils.ToastUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private EditText nameInput;
    private EditText passwordInput;
    private AppCompatCheckBox rememberPassword;
    private Button btnLogin;
    private TextView tvSettings;

    private static final String KEY_NAME = "NAME";
    private static final String KEY_PASSWORD = "PASSWORD";
    private static final String KEY_REMEMBER = "REMEMBER";
    private StringBuffer buf;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void initViews() {

        setExitEnable(true);

        nameInput = (EditText) findViewById(R.id.etAccount);
        passwordInput = (EditText) findViewById(R.id.etPassword);
        rememberPassword = (AppCompatCheckBox) findViewById(R.id.rememberPassword);
        btnLogin = (Button) findViewById(R.id.login);
        tvSettings = (TextView) findViewById(R.id.tv_settings);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(LoginActivity.this, SettingsActivity.class));
            }
        });
        boolean isRememberId = (boolean) SPUtil.get(this, KEY_REMEMBER, true);
        rememberPassword.setChecked(isRememberId);
        nameInput.setText((String) SPUtil.get(this, KEY_NAME, ""));
        passwordInput.setText((String) SPUtil.get(this, KEY_PASSWORD, ""));
        passwordInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    KeyboardUtils.hideSoftInput(LoginActivity.this);
                    login();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void loadData() {
        checkNetWork();
        PermissionUtil.permissionGranted(this, PermissionUtil.PERMISSIONS_REQUEST_ALL,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private void login() {
        String name = nameInput.getText().toString();
        String password = passwordInput.getText().toString();
//        if (name.length() == 0) {
//            nameInput.setError("请输入用户名");
//            return;
//        } else if (password.length() == 0) {
//            passwordInput.setError("请输入密码");
//            return;
//        }
//        if (!name.equals("123456")&&!password.equals("456"))
//        {
//            return;
//        }

      String  Str=  TimeUtil.DateToChineseString(new Date(System.currentTimeMillis()));
        ToastUtil.showLong(Str);

        KeyboardUtils.hideSoftInput(this);

        Intent intent=new Intent();
        intent.setClass(LoginActivity.this,MainActivity.class);
        startActivity(intent);


        new  Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL("http://www.baidu.com");
                    try {
                        HttpURLConnection connection= (HttpURLConnection) url.openConnection();

                        InputStream inputStream= connection.getInputStream();
                        InputStreamReader in=new InputStreamReader(inputStream);
                        BufferedReader bufferedReader=new BufferedReader(in);
                        buf = new StringBuffer();
                        String line=null;
                        String resout=null;
                        while ((line=bufferedReader.readLine())!=null)
                        {
                            buf.append(line);
                        }
                        resout= buf.toString();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

//        CommonAction.userLogin(name, password.toUpperCase(), new ActionListener<User>(this, "登录中...") {
//            @Override
//            public void onSuccess(User user) {
//                super.onSuccess(user);
//                savePassword();
//                AppGlobal.currentUser = user;
//                if (AppGlobal.onLine && DataSupport.count(User.class) == 0) {
//                    DataSyncActivity.startAutoSync(LoginActivity.this, true);
//                } else
//                    startActivity(new Intent(LoginActivity.this, SearchActivity.class));
//            }
//
//            @Override
//            public void onFail(String msg) {
//                super.onFail(msg);
//            }
//        });
    }

    private void savePassword() {
        if (rememberPassword.isChecked()) {
            SPUtil.put(this, KEY_NAME, nameInput.getText().toString().trim());
            SPUtil.put(this, KEY_PASSWORD, passwordInput.getText().toString().trim());
        } else {
            SPUtil.remove(this, KEY_PASSWORD);
        }
    }

    private void checkNetWork() {
//        if (TextUtils.isEmpty((String) SPUtil.get(this, SettingsActivity.KEY_IP, ""))) {
//            startActivity(new Intent(this, SettingsActivity.class));
//            return;
//        }
//        CommonAction.GetTraliersList(new ActionListener<Boolean>() {
//            @Override
//            public void onFail(String msg) {
//                super.onFail(msg);
//                AppGlobal.onLine = false;
//                ToastUtil.showShort("网络不可用，进入离线模式！");
//            }
//
//            @Override
//            public void onSuccess(Boolean aBoolean) {
//                super.onSuccess(aBoolean);
//                AppGlobal.onLine = true;
//            }
//        });
    }

}
