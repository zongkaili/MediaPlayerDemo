package com.yixun.mediaplayer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yixun.tablayout.CommonTabLayout;
import com.yixun.tablayout.listener.CustomTabEntity;
import com.yixun.tablayout.listener.OnTabSelectListener;
import com.yixun.mediaplayer.adapter.HomePagerAdapter;
import com.yixun.mediaplayer.entity.TabEntity;
import com.yixun.mediaplayer.fragment.LocalVideoFragment;
import com.yixun.mediaplayer.fragment.NetVideoFragment;
import com.yixun.mediaplayer.fragment.SimpleCardFragment;
import com.yixun.mediaplayer.utils.ViewFindUtils;
import com.yixun.videoplayer.YXVideoPlayer;

import java.util.ArrayList;

/**
 * Created by zongkaili on 2017/3/21.
 */
public class HomeActicity extends AppCompatActivity {
    private View mDecorView;
    private ViewPager mViewPager;
    private CommonTabLayout mTabLayout;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int[] mIconUnselectIds;
    private int[] mIconSelectIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initData();
        initFragment();
        initView();
        bindWidge();
    }

    private void initData(){
        mTitles = new String[]{"首页", "消息", "联系人", "更多"};
        mIconUnselectIds = new int[]{R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
                    R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
        mIconSelectIds = new int[]{R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
                R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    }

    private void initView() {
        mDecorView = getWindow().getDecorView();
        mViewPager = ViewFindUtils.find(mDecorView, R.id.home_vp);
        mViewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
        mTabLayout = ViewFindUtils.find(mDecorView, R.id.home_tablayout);
    }

    private void initFragment() {
        for (int i = 0; i < mTitles.length; i++) {
            if (i == 0)
                mFragments.add(new LocalVideoFragment());//本地视频
            else if (i == 1)
                mFragments.add(new NetVideoFragment());//网络视频
            else
                mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + mTitles[i]));
        }
    }

    private void bindWidge(){
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onBackPressed() {
        if (YXVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        YXVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
