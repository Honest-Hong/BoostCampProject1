package com.project.boostcamp.thirdminiproject;

import java.io.Serializable;

/**
 * Created by Hong Tae Joon on 2017-07-18.
 */

public class Restraurant implements Serializable{
    private double latitude; // 위도
    private double longitude; // 경도
    private String name; // 이름
    private String tele; // 전화번호
    private String desc; // 설명

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
