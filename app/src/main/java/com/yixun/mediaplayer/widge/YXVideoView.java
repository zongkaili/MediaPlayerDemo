package com.yixun.mediaplayer.widge;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by zongkaili on 2017/3/21.
 */
public class YXVideoView extends android.widget.VideoView {
    public YXVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }


    /**
     * 根据传入视频的大小，设置视频的画面大小
     *
     * @param screenWidth
     * @param screeHeight
     */
    public void setViewSize(int screenWidth, int screeHeight) {
        //视频画面的宽和高
        ViewGroup.LayoutParams l = getLayoutParams();
        l.width = screenWidth;
        l.height = screeHeight;
        setLayoutParams(l);

    }
}
