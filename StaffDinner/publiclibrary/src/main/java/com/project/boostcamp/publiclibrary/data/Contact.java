package com.project.boostcamp.publiclibrary.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hong Tae Joon on 2017-07-25.
 */

public class Contact implements Parcelable{
    private int _id;
    private String applierName;
    private int applyNumber;
    private String applyDate;
    private String applierPhone;
    private double applyLat;
    private double applyLng;
    private String estimaterName;
    private String estimaterPhone;
    private String estimateDate;
    private String estimaterMessage;
    private double estimateLat;
    private double estimateLng;
    private String contactDate;

    public Contact() {
    }

    protected Contact(Parcel in) {
        _id = in.readInt();
        applierName = in.readString();
        applyNumber = in.readInt();
        applyDate = in.readString();
        applierPhone = in.readString();
        applyLat = in.readDouble();
        applyLng = in.readDouble();
        estimaterName = in.readString();
        estimaterPhone = in.readString();
        estimateDate = in.readString();
        estimaterMessage = in.readString();
        estimateLat = in.readDouble();
        estimateLng = in.readDouble();
        contactDate = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(_id);
        parcel.writeString(applierName);
        parcel.writeInt(applyNumber);
        parcel.writeString(applyDate);
        parcel.writeString(applierPhone);
        parcel.writeDouble(applyLat);
        parcel.writeDouble(applyLng);
        parcel.writeString(estimaterName);
        parcel.writeString(estimaterPhone);
        parcel.writeString(estimateDate);
        parcel.writeString(estimaterMessage);
        parcel.writeDouble(estimateLat);
        parcel.writeDouble(estimateLng);
        parcel.writeString(contactDate);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getApplierName() {
        return applierName;
    }

    public void setApplierName(String applierName) {
        this.applierName = applierName;
    }

    public int getApplyNumber() {
        return applyNumber;
    }

    public void setApplyNumber(int applyNumber) {
        this.applyNumber = applyNumber;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplierPhone() {
        return applierPhone;
    }

    public void setApplierPhone(String applierPhone) {
        this.applierPhone = applierPhone;
    }

    public double getApplyLat() {
        return applyLat;
    }

    public void setApplyLat(double applyLat) {
        this.applyLat = applyLat;
    }

    public double getApplyLng() {
        return applyLng;
    }

    public void setApplyLng(double applyLng) {
        this.applyLng = applyLng;
    }

    public String getEstimaterName() {
        return estimaterName;
    }

    public void setEstimaterName(String estimaterName) {
        this.estimaterName = estimaterName;
    }

    public String getEstimaterPhone() {
        return estimaterPhone;
    }

    public void setEstimaterPhone(String estimaterPhone) {
        this.estimaterPhone = estimaterPhone;
    }

    public String getEstimateDate() {
        return estimateDate;
    }

    public void setEstimateDate(String estimateDate) {
        this.estimateDate = estimateDate;
    }

    public String getEstimaterMessage() {
        return estimaterMessage;
    }

    public void setEstimaterMessage(String estimaterMessage) {
        this.estimaterMessage = estimaterMessage;
    }

    public double getEstimateLat() {
        return estimateLat;
    }

    public void setEstimateLat(double estimateLat) {
        this.estimateLat = estimateLat;
    }

    public double getEstimateLng() {
        return estimateLng;
    }

    public void setEstimateLng(double estimateLng) {
        this.estimateLng = estimateLng;
    }

    public String getContactDate() {
        return contactDate;
    }

    public void setContactDate(String contactDate) {
        this.contactDate = contactDate;
    }
}
