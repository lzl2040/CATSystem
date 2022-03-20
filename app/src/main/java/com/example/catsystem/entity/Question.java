package com.example.catsystem.entity;

/**
 * author ： yxm521
 * time    ： 2022/3/19
 * 问题的实体类
 */
public class Question {
    //题目ID
    private Integer itemId;
    //问题内容
    private String content;
    //问题答案
    private String answer;
    //该题目对应的做题者的能力值
    private Double value;
    //选项A内容
    private String choiceA;
    //选项B内容
    private String choiceB;
    //选项C内容
    private String choiceC;
    //选项D内容
    private String choiceD;
    //题目解析
    private String explanation;
    //用户的选项,默认为NO
    private String userAnswer = "NO";

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
