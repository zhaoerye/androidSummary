package com.example.sunshine.myapplication.http;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by liyu on 2016/8/24.
 */
public class BaseResponse<T> {

   //@SerializedName("trailers")
    public T data;

    public String message;



}
