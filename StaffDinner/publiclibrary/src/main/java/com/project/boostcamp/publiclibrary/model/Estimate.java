package com.project.boostcamp.publiclibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class Estimate implements Parcelable{
    private String restName;
    private String restImgUrl;
    private String restStyle;
    private double restLat;
    private double restLng;
    private String restMenu;
    private int restMenuCost;
    private String sendDate;
    private String message;

    public Estimate() {
    }

    protected Estimate(Parcel in) {
        restName = in.readString();
        restImgUrl = in.readString();
        restStyle = in.readString();
        restLat = in.readDouble();
        restLng = in.readDouble();
        restMenu = in.readString();
        restMenuCost = in.readInt();
        sendDate = in.readString();
        message = in.readString();
    }

    public static final Creator<Estimate> CREATOR = new Creator<Estimate>() {
        @Override
        public Estimate createFromParcel(Parcel in) {
            return new Estimate(in);
        }

        @Override
        public Estimate[] newArray(int size) {
            return new Estimate[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(restName);
        parcel.writeString(restImgUrl);
        parcel.writeString(restStyle);
        parcel.writeDouble(restLat);
        parcel.writeDouble(restLng);
        parcel.writeString(restMenu);
        parcel.writeInt(restMenuCost);
        parcel.writeString(sendDate);
        parcel.writeString(message);
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestImgUrl() {
        return restImgUrl;
    }

    public void setRestImgUrl(String restImgUrl) {
        this.restImgUrl = restImgUrl;
    }

    public String getRestStyle() {
        return restStyle;
    }

    public void setRestStyle(String restStyle) {
        this.restStyle = restStyle;
    }

    public double getRestLat() {
        return restLat;
    }

    public void setRestLat(double restLat) {
        this.restLat = restLat;
    }

    public double getRestLng() {
        return restLng;
    }

    public void setRestLng(double restLng) {
        this.restLng = restLng;
    }

    public String getRestMenu() {
        return restMenu;
    }

    public void setRestMenu(String restMenu) {
        this.restMenu = restMenu;
    }

    public int getRestMenuCost() {
        return restMenuCost;
    }

    public void setRestMenuCost(int restMenuCost) {
        this.restMenuCost = restMenuCost;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
