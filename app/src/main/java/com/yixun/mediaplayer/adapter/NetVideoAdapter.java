package com.yixun.mediaplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yixun.mediaplayer.R;
import com.yixun.mediaplayer.utils.VideoConstant;
import com.yixun.videoplayer.YXVideoPlayer;
import com.yixun.videoplayer.YXVideoPlayerStandard;

import java.util.Map;

/**
 * Created by zongkaili on 2017/3/21.
 */

public class NetVideoAdapter extends RecyclerView.Adapter<NetVideoAdapter.MyViewHolder> {
    private Context context;
    public static final String TAG = NetVideoAdapter.class.getSimpleName();
    private Map<String , String[]> dataMap;

    public NetVideoAdapter(Context context , Map<String , String[]> dataMap) {
        this.context = context;
        this.dataMap = dataMap;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_net_audio, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder [" + holder.jcVideoPlayer.hashCode() + "] position=" + position);

        holder.jcVideoPlayer.setUp(
                dataMap.get(VideoConstant.urls_key)[position], YXVideoPlayer.SCREEN_LAYOUT_LIST,
                dataMap.get(VideoConstant.titles_key)[position]);
        Glide.with(holder.jcVideoPlayer.getContext())
                .load(dataMap.get(VideoConstant.thumbs_key)[position])
                .into(holder.jcVideoPlayer.thumbImageView);
    }

    @Override
    public int getItemCount() {
        return dataMap.get(VideoConstant.urls_key).length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        YXVideoPlayerStandard jcVideoPlayer;

        public MyViewHolder(View itemView) {
            super(itemView);
            jcVideoPlayer = (YXVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);
        }
    }
}
