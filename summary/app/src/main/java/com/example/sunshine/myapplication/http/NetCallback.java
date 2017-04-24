package com.example.sunshine.myapplication.http;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liyu on 2016/8/24.
 */
public abstract class NetCallback<T extends BaseResponse> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response == null) {
            onFail("服务器访问失败: response is null!");
        } else if (response.raw().code() == 200) {
                onSuccess(response);

        } else {
            onFail("请求失败!错误代码: " + response.raw().code());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFail(t.getMessage());
    }

    public abstract void onSuccess(Response<T> result);

    public abstract void onFail(String message);


}
