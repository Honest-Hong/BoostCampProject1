package com.project.boostcamp.thirdminiproject;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Hong Tae Joon on 2017-07-18.
 */

public class Restraurant implements Parcelable{
    private String name; // 이름
    private String address; // 주소
    private String tele; // 전화번호
    private String desc; // 설명

    public Restraurant() {
    }

    protected Restraurant(Parcel in) {
        name = in.readString();
        address = in.readString();
        tele = in.readString();
        desc = in.readString();
    }

    public static final Creator<Restraurant> CREATOR = new Creator<Restraurant>() {
        @Override
        public Restraurant createFromParcel(Parcel in) {
            return new Restraurant(in);
        }

        @Override
        public Restraurant[] newArray(int size) {
            return new Restraurant[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(tele);
        parcel.writeString(desc);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
