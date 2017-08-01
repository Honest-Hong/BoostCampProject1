package com.project.boostcamp.publiclibrary.data;

/**
 * Created by Hong Tae Joon on 2017-08-01.
 */

public class Client {
    private String access;
    private String id;
    private String name;
    private String phone;
    private String token;
    private int type;

    public String getId() {
        return id;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "access:" + access + "\n" +
                "id:" + id + "\n" +
                "name:" + name + "\n" +
                "phone:" + phone + "\n" +
                "token:" + token + "\n" +
                "type:" + type;
    }
}
