package com.project.boostcamp.serverexample;

/**
 * Created by Hong Tae Joon on 2017-07-20.
 */

public class User {
    private String name;
    private int age;
    private String gen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    @Override
    public String toString() {
        return String.format("{name:%s, age:%d, gen:%s}", name, age, gen);
    }
}
