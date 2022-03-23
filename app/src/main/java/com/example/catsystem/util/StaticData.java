package com.example.catsystem.util;

import com.example.catsystem.entity.Question;
import com.google.gson.Gson;

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
    //Gson
    private static Gson gson;
    /**
     * 后端接口使用的url
     */
    private static String baseUrl = "http://192.168.43.167:8080/CATSystem";
    private static String register = "/user/register";
    private static String login = "/user/login";

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

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getRegister() {
        return register;
    }

    public static Gson getGson() {
        if(gson == null){
            gson = new Gson();
        }
        return gson;
    }

    public static String getLogin() {
        return login;
    }
}
