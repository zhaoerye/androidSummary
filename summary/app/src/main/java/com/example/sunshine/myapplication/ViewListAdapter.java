package com.example.sunshine.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sunshine.myapplication.http.Model.Trailers;

import java.util.List;

import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by zhaoerye on 2017/3/27.
 */

public class ViewListAdapter extends BaseQuickAdapter<Trailers, BaseViewHolder> {
    Context mContext;

    public ViewListAdapter(int layoutResId, List<Trailers> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Trailers trailers) {

        baseViewHolder.setText(R.id.tv1, trailers.getVideoTitle());
        Glide.with(mContext).load(trailers.getCoverImg()).thumbnail(0.1f).bitmapTransform(new RoundedCornersTransformation(mContext, 30, 0, RoundedCornersTransformation.CornerType.ALL)).fitCenter()
                .centerCrop().into((ImageView) baseViewHolder.getView(R.id.imageView1));
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LocalVideoActivity.class);
                intent.putExtra(LocalVideoActivity.URL_VIDEO_FLAG,trailers.getUrl());
                mContext.startActivity(intent);


            }
        });
    }
}
