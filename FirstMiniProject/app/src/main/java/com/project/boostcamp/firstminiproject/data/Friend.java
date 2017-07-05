package com.project.boostcamp.firstminiproject.data;

/**
 * Created by Hong Tae Joon on 2017-07-04.
 */

public class Friend {
    // 친구의 이름
    private String name;
    // 함께 아는 친구의 수
    private int same;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSame() {
        return same;
    }

    public void setSame(int same) {
        this.same = same;
    }
}
