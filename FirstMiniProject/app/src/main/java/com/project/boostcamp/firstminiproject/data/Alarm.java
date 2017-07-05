package com.project.boostcamp.firstminiproject.data;

import android.graphics.Bitmap;

/**
 * Created by Hong Tae Joon on 2017-07-04.
 */

public class Alarm {
    // 누구로부터의 알림
    private String title;
    // 알림 이미지
    private Bitmap thumb;
    // 알림 타입
    private int type;
    public final static int TYPE_LIKE = 0x1000;
    public final static int TYPE_COMMENT = 0x1001;
    // 알림 시간
    private String time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getThumb() {
        return thumb;
    }

    public void setThumb(Bitmap thumb) {
        this.thumb = thumb;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
