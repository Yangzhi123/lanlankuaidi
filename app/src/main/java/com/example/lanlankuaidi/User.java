package com.example.lanlankuaidi;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private Integer age;
    private Integer gender;
    private Integer score;
    public Integer getScore() {
        return score;
    }

    public User setScore(Integer score) {
        this.score = score;
        return this;
    }
    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public User setGender(Integer gender) {
        this.gender = gender;
        return this;
    }
}
