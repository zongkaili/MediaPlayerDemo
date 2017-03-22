package com.yixun.mediaplayer.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yixun.mediaplayer.R;
import com.yixun.mediaplayer.adapter.NetVideoAdapter;
import com.yixun.mediaplayer.utils.VideoConstant;
import com.yixun.videoplayer.YXVideoPlayer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zongkaili on 2017/3/21.
 */
public class NetVideoFragment extends BaseFragment {
    private static final String TAG = LocalVideoFragment.class.getSimpleName();
    private RecyclerView mRvList;
    private NetVideoAdapter mVideoListAdapter;
    private TextView mTvNomedia;
    private Map<String , String[]> dataMap;

    @Override
    public View initView() {
        Log.e(TAG, "本地视频ui初始化了。。");
        View view = View.inflate(mContext, R.layout.fragment_net_video, null);
        mRvList = (RecyclerView) view.findViewById(R.id.recyclerview);
        mTvNomedia = (TextView) view.findViewById(R.id.tv_no_media);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "本地视频数据初始化了。。");

        setData();

        handleData();
    }

    private void setData() {
        dataMap = new HashMap<>();
        dataMap.put(VideoConstant.urls_key , VideoConstant.videoUrls[0]);
        dataMap.put(VideoConstant.titles_key , VideoConstant.videoTitles[0]);
        dataMap.put(VideoConstant.thumbs_key , VideoConstant.videoThumbs[0]);
    }

    private void handleData() {
        if (dataMap != null && dataMap.get(VideoConstant.urls_key).length >0) {
            mTvNomedia.setVisibility(View.GONE);
            mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));

            mVideoListAdapter = new NetVideoAdapter(getActivity() , dataMap);
            mRvList.setAdapter(mVideoListAdapter);
        } else {
            mTvNomedia.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefrshData() {
        super.onRefrshData();
//        Log.e("TAG","onHiddenChanged。。"+this.toString());
    }

    @Override
    public void onPause() {
        super.onPause();
        YXVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            YXVideoPlayer.releaseAllVideos();
        }
    }
}
