package com.example.catsystem.entity;

/**
 * author ： yxm521
 * time    ： 2022/3/19
 * 用户的实体类
 */
public class User {
    //用户编号
    private Integer userId;
    //用户名
    private String name;
    //用户密码
    private String pwd;
    //电话号码
    private String phone;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
