package com.project.boostcamp.thirdminiproject;

import java.io.Serializable;

/**
 * Created by Hong Tae Joon on 2017-07-18.
 */

public class Restraurant implements Serializable{
    private String name; // 이름
    private String address; // 주소
    private String tele; // 전화번호
    private String desc; // 설명

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
