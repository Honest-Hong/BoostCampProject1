package com.project.boostcamp.publiclibrary.model;

/**
 * Created by Hong Tae Joon on 2017-07-27.
 */

public class Apply {
    public static final int STATE_EDIT = 0x0;
    public static final int STATE_APPLY = 0x1;
    public static final int STATE_FAIL = 0x2;

    private int id;
    private String writerName;
    private String title;
    private int number;
    private long wantedTime;
    private String wantedStyle;
    private String wantedMenu;
    private double wantedLatitude;
    private double wantedLongitude;
    private long writedTime;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getWantedTime() {
        return wantedTime;
    }

    public void setWantedTime(long wantedTime) {
        this.wantedTime = wantedTime;
    }

    public String getWantedStyle() {
        return wantedStyle;
    }

    public void setWantedStyle(String wantedStyle) {
        this.wantedStyle = wantedStyle;
    }

    public String getWantedMenu() {
        return wantedMenu;
    }

    public void setWantedMenu(String wantedMenu) {
        this.wantedMenu = wantedMenu;
    }

    public double getWantedLatitude() {
        return wantedLatitude;
    }

    public void setWantedLatitude(double wantedLatitude) {
        this.wantedLatitude = wantedLatitude;
    }

    public double getWantedLongitude() {
        return wantedLongitude;
    }

    public void setWantedLongitude(double wantedLongitude) {
        this.wantedLongitude = wantedLongitude;
    }

    public long getWritedTime() {
        return writedTime;
    }

    public void setWritedTime(long writedTime) {
        this.writedTime = writedTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
