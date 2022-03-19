package com.example.catsystem.util;

/**
 * author ： yxm521
 * time    ： 2022/3/19
 * 用作数据流转的工具类
 */
public class StaticData {
    //底部导航栏的位置,默认为0
    private static int bottomPosition = 0;

    public static int getBottomPosition() {
        return bottomPosition;
    }

    public static void setBottomPosition(int bottomPosition) {
        StaticData.bottomPosition = bottomPosition;
    }



}
