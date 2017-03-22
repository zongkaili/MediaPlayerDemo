package com.yixun.videoplayer;

/**
 * Put JCVideoPlayer into layout
 * From a JCVideoPlayer to another JCVideoPlayer
 * Created by Nathen on 16/7/26.
 */
public class YXVideoPlayerManager {

    public static YXVideoPlayer FIRST_FLOOR_JCVD;
    public static YXVideoPlayer SECOND_FLOOR_JCVD;

    public static void setFirstFloor(YXVideoPlayer jcVideoPlayer) {
        FIRST_FLOOR_JCVD = jcVideoPlayer;
    }

    public static void setSecondFloor(YXVideoPlayer jcVideoPlayer) {
        SECOND_FLOOR_JCVD = jcVideoPlayer;
    }

    public static YXVideoPlayer getFirstFloor() {
        return FIRST_FLOOR_JCVD;
    }

    public static YXVideoPlayer getSecondFloor() {
        return SECOND_FLOOR_JCVD;
    }

    public static YXVideoPlayer getCurrentJcvd() {
        if (getSecondFloor() != null) {
            return getSecondFloor();
        }
        return getFirstFloor();
    }

    public static void completeAll() {
        if (SECOND_FLOOR_JCVD != null) {
            SECOND_FLOOR_JCVD.onCompletion();
            SECOND_FLOOR_JCVD = null;
        }
        if (FIRST_FLOOR_JCVD != null) {
            FIRST_FLOOR_JCVD.onCompletion();
            FIRST_FLOOR_JCVD = null;
        }
    }
}
