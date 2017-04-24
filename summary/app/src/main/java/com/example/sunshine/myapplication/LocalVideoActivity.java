package com.example.sunshine.myapplication;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.sunshine.myapplication.http.ApiFactory;
import com.example.sunshine.myapplication.http.BaseResponse;
import com.example.sunshine.myapplication.http.Model.Trailers;
import com.example.sunshine.myapplication.http.NetCallback;

import java.util.List;

import retrofit2.Response;

public class LocalVideoActivity extends BaseActivity {
    VideoView videoView;
   static String URL_VIDEO_FLAG="URL_VIDEO_FLAG";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected void initViews() {
        videoView = (VideoView)this.findViewById(R.id.videoView );

        //设置视频控制器
        videoView.setMediaController(new MediaController(this));

        //播放完成回调
        videoView.setOnCompletionListener( new MyPlayerOnCompletionListener());

        //设置视频路径
        videoView.setVideoURI(Uri.parse(getIntent().getStringExtra(URL_VIDEO_FLAG)));

        //开始播放视频
        videoView.start();
    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText( LocalVideoActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void loadData() {

    }

//    private void getPatientList() {
//        ApiFactory.getCommonController().GetTraliersList().enqueue(new NetCallback<BaseResponse<List<Trailers>>>() {
//            @Override
//            public void onSuccess(Response<BaseResponse<List<Trailers>>> result) {
//                trailersList=result.body().data;
//                viewListAdapter.setNewData(trailersList);
//            }
//            @Override
//            public void onFail(String message) {
//
//            }
//        });
//    }

//    @Override
//    public void onNetConnected() {
//        super.onNetConnected();
//        getSupportActionBar().setTitle("网络已连接");
//    }
}
