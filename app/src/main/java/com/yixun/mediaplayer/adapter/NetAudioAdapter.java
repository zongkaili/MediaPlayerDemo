package com.yixun.mediaplayer.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yixun.mediaplayer.R;
import com.yixun.mediaplayer.bean.MediaItem;
import com.yixun.mediaplayer.bean.NetAudioBean;
import com.yixun.mediaplayer.utils.Constant;
import com.yixun.mediaplayer.utils.Utils;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by zongkaili on 2017/3/21.
 */

public class NetAudioAdapter extends BaseAdapter {
    private final Context mContext;
    private final ArrayList<MediaItem> datas;
    private final boolean isVideo;//true:视频 false:音频
    private Utils utils;

    public NetAudioAdapter(Context mContext, ArrayList<MediaItem> mediaItems, boolean b) {
        this.mContext = mContext;
        this.datas = mediaItems;
        utils = new Utils();
        this.isVideo = b;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NetAudioAdapter.ViewHolder viewHolder;
        if(convertView==null){
            convertView = View.inflate(mContext, R.layout.item_net_audio,null);
            viewHolder = new NetAudioAdapter.ViewHolder();
            viewHolder.jcvVideoplayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.jcv_videoplayer);
            //设置tag
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (NetAudioAdapter.ViewHolder) convertView.getTag();
        }

        //根据位置得到对应的数据
        MediaItem mediaItem = datas.get(position);

        //设置数据
        viewHolder.setData();

        return convertView;
    }

    class ViewHolder{
        JCVideoPlayerStandard jcvVideoplayer;


        public void setData() {
//            //第一个参数是视频播放地址，第二个参数是显示封面的地址，第三参数是标题
//            boolean setUp = jcvVideoplayer.setUp(Constant.BASE_URL+mediaItem.getVideo().getVideo().get(0), JCVideoPlayer.SCREEN_LAYOUT_LIST,
//                    "");
//
//            //加载图片
//            if (setUp) {
//                //设置默认封面
//                Glide.with(mContext).load(Constant.BASE_URL+mediaItem.getVideo().getThumbnail().get(0)).into(jcvVideoplayer.thumbImageView);
//            }
        }
    }
}
