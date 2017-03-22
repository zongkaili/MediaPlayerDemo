package com.yixun.mediaplayer.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.yixun.mediaplayer.R;
import com.yixun.mediaplayer.SystemVideoPlayerActivity;
import com.yixun.mediaplayer.adapter.LocalVideoAdapter;
import com.yixun.mediaplayer.bean.MediaItem;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by zongkaili on 2017/3/21.
 */
public class LocalVideoFragment extends BaseFragment {
    private static final String TAG = LocalVideoFragment.class.getSimpleName();
    private ListView listview;
    private TextView tv_no_media;
    private LocalVideoAdapter adapter;
    private ArrayList<MediaItem> mediaItems;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mediaItems != null && mediaItems.size() >0) {
                tv_no_media.setVisibility(View.GONE);
                adapter = new LocalVideoAdapter(mContext,mediaItems, true);
                listview.setAdapter(adapter);
            } else {
                tv_no_media.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    public View initView() {
        Log.e(TAG, "本地视频ui初始化了。。");
        View view = View.inflate(mContext, R.layout.fragment_local_video, null);
        listview = (ListView) view.findViewById(R.id.listview);
        tv_no_media = (TextView) view.findViewById(R.id.tv_no_media);

        //设置item的监听
        listview.setOnItemClickListener(new MyOnItemClickListener());
        return view;
    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MediaItem mediaItem = mediaItems.get(position);

            //系统播放器videoView播放界面
//            Intent intent = new Intent(mContext,SystemVideoPlayerActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("videolist",mediaItems);
//            intent.putExtras(bundle);
//            intent.putExtra("position",position);
//            startActivity(intent);

            //播放网络视频
//            JCVideoPlayerStandard.startFullscreen(mContext, JCVideoPlayerStandard.class, "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", "嫂子辛苦了");
            //播放本地视频
            JCVideoPlayerStandard.startFullscreen(mContext, JCVideoPlayerStandard.class,
                    mediaItem.getData(), mediaItem.getName());

        }
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "本地视频数据初始化了。。");
        //在子线程中加载视频
        getDataFromLocal();
    }

    /**
     * 子线程中得到视频
     */
    private void getDataFromLocal() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                //初始化集合
                mediaItems = new ArrayList<MediaItem>();
                ContentResolver resolver = mContext.getContentResolver();
                //sdcard 的视频路径
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objs = {
                        MediaStore.Video.Media.DISPLAY_NAME,//在sdcard显示的视频名称
                        MediaStore.Video.Media.DURATION,//视频的时长,毫秒
                        MediaStore.Video.Media.SIZE,//文件大小-byte
                        MediaStore.Video.Media.DATA,//在sdcard的路径-播放地址
                        MediaStore.Video.Media.ARTIST//艺术家
                };
                Cursor cusor = resolver.query(uri, objs, null, null, null);
                if (cusor != null) {

                    while (cusor.moveToNext()) {

                        MediaItem mediaItem = new MediaItem();

                        //添加到集合中
                        mediaItems.add(mediaItem);//可以

                        String name = cusor.getString(0);
                        mediaItem.setName(name);
                        long duration = cusor.getLong(1);
                        mediaItem.setDuration(duration);
                        long size = cusor.getLong(2);
                        mediaItem.setSize(size);
                        String data = cusor.getString(3);//播放地址
                        mediaItem.setData(data);
                        String artist = cusor.getString(4);//艺术家
                        mediaItem.setArtist(artist);

                    }

                    cusor.close();
                }

                //发消息-切换到主线程
                handler.sendEmptyMessage(2);


            }
        }.start();

    }


    @Override
    public void onRefrshData() {
        super.onRefrshData();
//        Log.e("TAG","onHiddenChanged。。"+this.toString());
    }


}
