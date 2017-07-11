package com.project.boostcamp.secondminiproject;

import android.graphics.Bitmap;

/**
 * Created by Hong Tae Joon on 2017-07-11.
 * 맛집 데이터
 */

public class Shop {
    private String name; // 맛집 이름
    private String text; // 맛집 설명
    private int image; // 맛집 이미지
    private boolean checked; // 체크 유무
    private int distance; // 거리
    private int like; // 좋아요
    private long time; // 시간

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
