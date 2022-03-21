package com.example.catsystem.util;

import com.example.catsystem.entity.Question;

import java.util.List;

/**
 * author ： yxm521
 * time    ： 2022/3/19
 * 用作数据流转的工具类
 */
public class StaticData {
    //底部导航栏的位置,默认为0
    private static int bottomPosition = 0;
    //抽取的测试题目
    private static List<Question> questions;
    //做正确的个数

    public static int getBottomPosition() {
        return bottomPosition;
    }

    public static void setBottomPosition(int bottomPosition) {
        StaticData.bottomPosition = bottomPosition;
    }

    public static List<Question> getQuestions() {
        return questions;
    }

    public static void setQuestions(List<Question> questions) {
        StaticData.questions = questions;
    }

    public static void updateQuestion(int position,String answer){
        questions.get(position).setUserAnswer(answer);
    }
}
