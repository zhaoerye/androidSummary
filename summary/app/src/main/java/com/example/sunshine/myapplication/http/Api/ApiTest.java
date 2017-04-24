package com.example.sunshine.myapplication.http.Api;

import com.example.sunshine.myapplication.http.BaseResponse;
import com.example.sunshine.myapplication.http.Model.Coming;
import com.example.sunshine.myapplication.http.Model.Movice;
import com.example.sunshine.myapplication.http.Model.Trailers;
import com.example.sunshine.myapplication.http.Model.User;
import com.example.sunshine.myapplication.http.NetCallback;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by zhaoerye on 2017/3/27.
 */

public interface ApiTest {
    @GET("2016/07/24/mp4/160724055620533327_480.mp4/")
  Call<BaseResponse<String>> getVideos();

    @GET("PageSubArea/TrailerList.api")
    Call<BaseResponse<List<Trailers>>> GetTraliersList();

    @GET("Service/GetAllUsers")
    Call<BaseResponse<List<User>>> getAllUsers();

  @GET("http://api.meituan.com/mmdb/movie/v2/list/rt/order/coming.json?ci=1&limit=12&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=xiaomi&utm_medium=android&utm_term=6.8.0&utm_content=868030022327462&net=255&dModel=MI%205&uuid=0894DE03C76F6045D55977B6D4E32B7F3C6AAB02F9CEA042987B380EC5687C43&lat=40.100673&lng=116.378619&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463704714271&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=1a0b4a9b-44ec-42fc-b110-ead68bcc2824&__skcy=sXcDKbGi20CGXQPPZvhCU3%2FkzdE%3D")
  Call<BaseResponse<Movice>>getAllMovices();


}
