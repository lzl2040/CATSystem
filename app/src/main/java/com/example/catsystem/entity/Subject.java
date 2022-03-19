package com.example.catsystem.entity;

/**
 * author ： yxm521
 * time    ： 2022/3/19
 * 选择科目的时候需要用到的实体
 */
public class Subject {
    //科目名字
    private String name;
    //科目图片
    private int subject_img;
    //背景颜色
    private int back_color;

    public Subject(String name, int subject_img) {
        this.name = name;
        this.subject_img = subject_img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubject_img() {
        return subject_img;
    }

    public void setSubject_img(int subject_img) {
        this.subject_img = subject_img;
    }

    public int getBack_color() {
        return back_color;
    }

    public void setBack_color(int back_color) {
        this.back_color = back_color;
    }
}
