package com.example.sunshine.myapplication;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sunshine.myapplication.receiver.INetWorkReceiver;
import com.example.sunshine.myapplication.receiver.NetworkReceiver;
import com.example.sunshine.myapplication.utils.ActivityUtil;
import com.example.sunshine.myapplication.utils.ToastUtil;


public abstract class BaseActivity extends AppCompatActivity implements INetWorkReceiver {

    // 双击退出的间隔时间，默认两秒
    private static final long DOUBLE_INTERVAL_TIME = 2000;

    /**
     * 第一点击返回按钮时间
     */
    private long firstBackPressedTime = 0;

    protected abstract
    @LayoutRes
    int getLayoutId();

    protected abstract
    @MenuRes
    int getMenuId();

    protected abstract void initViews();

    protected abstract void loadData();

    NetworkReceiver networkReceiver;
    protected boolean isExitEnable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ActivityUtil.getInstance().addActivity(this);
        initToolBar();
        initViews();

        loadData();

    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected void setDisplayHomeAsUpEnabled(boolean enable) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
    }


    /**
     * 是否可双击退出程序
     *
     * @return 默认false
     */
    protected void setExitEnable(boolean enable) {
        isExitEnable = enable;
    }

    @Override
    public void onBackPressed() {
        if (isExitEnable) {
            if (System.currentTimeMillis() - firstBackPressedTime < DOUBLE_INTERVAL_TIME) {
                ActivityUtil.getInstance().appExit();
            } else {
                ToastUtil.showShort("再按一次退出应用");
                firstBackPressedTime = System.currentTimeMillis();
            }
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuId() != 0) {
            getMenuInflater().inflate(getMenuId(), menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (networkReceiver == null)
            networkReceiver = new NetworkReceiver(this);
        networkReceiver.registerReceiver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (networkReceiver != null)
            networkReceiver.unRegisterReceiver();
    }

    @Override
    public void onNetConnected() {

    }

    @Override
    public void onNetDisConnected() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtil.getInstance().removeActivity(this);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //这里对于运行时权限的授权或者阻止暂不做处理
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
