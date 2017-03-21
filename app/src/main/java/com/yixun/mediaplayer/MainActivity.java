package com.yixun.mediaplayer;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yixun.mediaplayer.fragment.BaseFragment;
import com.yixun.mediaplayer.fragment.LocalVideoFragment;

import java.util.ArrayList;

/**
 * Created by zongkaili on 2017/3/21.
 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new LocalVideoFragment());//本地视频
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fl_mainc_content, fragments.get(0));
        fragmentTransaction.commit();
    }

}
