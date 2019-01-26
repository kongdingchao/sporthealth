package com.sporthealth.entity;

import com.sporthealth.enums.SexEnum;

import java.util.Date;

public class UserInfo {
    private long userId;
    private String name;
    private SexEnum sex;
    private int age;
    private Date regTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", sex=" + sex.getValue() +
                ", age=" + age +
                ", regTime=" + regTime +
                '}';
    }
}
