package com.example.sunshine.myapplication.http;

import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import com.example.sunshine.myapplication.utils.StringUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liyu on 2016/8/24.
 */
public class RetrofitManager {

    private static RetrofitManager instance;
    private static Retrofit retrofit;
    private static Gson mGson;
    private static String cookie = "";

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.m.mtime.cn/")
                .client(getNewClient())
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static void reset() {
        instance = null;
    }

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                instance = new RetrofitManager();
            }
        }
        return instance;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

    private static OkHttpClient getNewClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new CookieInterceptor())
                .addInterceptor(new RequestInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
    }

    private static class RequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Token", "mdsd-anes-android-gzjz78987")
                    .header("Cookie", cookie)
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        }


    }

    private static class CookieInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (!TextUtils.isEmpty(originalResponse.header("Set-Cookie"))) {
                cookie = StringUtil.subString(originalResponse.header("Set-Cookie"), "", ";");
            }
            return originalResponse;
        }
    }

    public static Gson gson() {
        if (mGson == null) {
            synchronized (RetrofitManager.class) {
                mGson = new GsonBuilder().setLenient().create();
            }
        }
        return mGson;
    }


}
