package com.yixun.mediaplayer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.yixun.mediaplayer.fragment.BaseFragment;
import com.yixun.mediaplayer.fragment.LocalVideoFragment;
import com.yixun.mediaplayer.fragment.NetAudioFragment;

import java.util.ArrayList;

/**
 * Created by zongkaili on 2017/3/21.
 */
public class MainActivity extends AppCompatActivity {
    private RadioGroup mRgMain;
    private ArrayList<BaseFragment> mFragments;
    private int mPagePos;
    private Fragment mTempFragment;//缓存的fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRgMain = (RadioGroup) findViewById(R.id.rg_main);

        initFragment();

        initListenter();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        mFragments.add(new LocalVideoFragment());//本地视频
        mFragments.add(new NetAudioFragment());//本地音乐
    }

    private void initListenter() {
        mRgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_local_video:
                        mPagePos = 0;
                        break;
                    case R.id.rb_local_audio:
                        mPagePos = 1;
                        break;
                }

                //Fragment-当前的Fragment
                Fragment currentFragment = mFragments.get(mPagePos);
                switchFragment(currentFragment);
            }
        });

        //默认选中本地视频
        mRgMain.check(R.id.rb_local_video);
    }

    private void switchFragment(Fragment currentFragment) {
        if (mTempFragment != currentFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (currentFragment != null) {
                if (!currentFragment.isAdded()) {
                    if (mTempFragment != null) {
                        ft.hide(mTempFragment);
                    }
                    ft.add(R.id.fl_mainc_content, currentFragment);

                } else {
                    if (mTempFragment != null) {
                        ft.hide(mTempFragment);
                    }
                    ft.show(currentFragment);
                }
                ft.commit();
            }
            mTempFragment = currentFragment;
        }
    }

}
