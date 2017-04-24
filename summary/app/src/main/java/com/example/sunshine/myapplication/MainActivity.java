package com.example.sunshine.myapplication;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;


import com.example.sunshine.myapplication.http.ApiFactory;
import com.example.sunshine.myapplication.http.BaseResponse;
import com.example.sunshine.myapplication.http.Model.Coming;
import com.example.sunshine.myapplication.http.Model.Movice;
import com.example.sunshine.myapplication.http.Model.Trailers;
import com.example.sunshine.myapplication.http.NetCallback;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ViewListAdapter viewListAdapter;
    private List<Trailers> trailersList=null;
    private AuthInfo mAuthInfo;

    /** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
    private Oauth2AccessToken mAccessToken;

    /** 注意：SsoHandler 仅当 SDK 支持 SSO 时有效 */
    private SsoHandler mSsoHandler;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void initViews() {
        recyclerView= (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        viewListAdapter=new ViewListAdapter(R.layout.item_list,null,MainActivity.this);
        recyclerView.setAdapter(viewListAdapter);

    }

    @Override
    protected void loadData() {
        getPatientList();
    }

    private void getPatientList() {

//ApiFactory.getCommonController().getAllMovices().enqueue(new NetCallback<BaseResponse<Movice>>() {
//    @Override
//    public void onSuccess(Response<BaseResponse<Movice>> result) {
//
//    }
//
//    @Override
//    public void onFail(String message) {
//
//    }
//});


        ApiFactory.getCommonController().GetTraliersList().enqueue(new NetCallback<BaseResponse<List<Trailers>>>() {
            @Override
            public void onSuccess(Response<BaseResponse<List<Trailers>>> result) {
                trailersList=result.body().data;
                viewListAdapter.setNewData(trailersList);
            }
            @Override
            public void onFail(String message) {

            }
        });
    }


//    @Override
//    public void onNetConnected() {
//        super.onNetConnected();
//        getSupportActionBar().setTitle("网络已连接");
//    }
}
