package com.memoria.modeldata;

// 목표 설정 및 달성량
public class Goal {

    private int goalWord;
    private int goalMemory;
    private int goalTest;
    private int goalQuiz;

    private int achieveWord;
    private int achieveMemory;
    private int achieveTest;
    private int achieveQuiz;
    private String date;

    public Goal(){}

    public Goal(int goalWord, int goalMemory, int goalTest, int goalQuiz, int achieveWord, int achieveMemory, int achieveTest, int achieveQuiz, String date) {
        this.goalWord = goalWord;
        this.goalMemory = goalMemory;
        this.goalTest = goalTest;
        this.goalQuiz = goalQuiz;
        this.achieveWord = achieveWord;
        this.achieveMemory = achieveMemory;
        this.achieveTest = achieveTest;
        this.achieveQuiz = achieveQuiz;
        this.date = date;
    }

    public int getGoalWord() {
        return goalWord;
    }

    public void setGoalWord(int goalWord) {
        this.goalWord = goalWord;
    }

    public int getGoalMemory() {
        return goalMemory;
    }

    public void setGoalMemory(int goalMemory) {
        this.goalMemory = goalMemory;
    }

    public int getGoalTest() {
        return goalTest;
    }

    public void setGoalTest(int goalTest) {
        this.goalTest = goalTest;
    }

    public int getGoalQuiz() {
        return goalQuiz;
    }

    public void setGoalQuiz(int goalQuiz) {
        this.goalQuiz = goalQuiz;
    }

    public int getAchieveWord() {
        return achieveWord;
    }

    public void setAchieveWord(int achieveWord) {
        this.achieveWord = achieveWord;
    }

    public int getAchieveMemory() {
        return achieveMemory;
    }

    public void setAchieveMemory(int achieveMemory) {
        this.achieveMemory = achieveMemory;
    }

    public int getAchieveTest() {
        return achieveTest;
    }

    public void setAchieveTest(int achieveTest) {
        this.achieveTest = achieveTest;
    }

    public int getAchieveQuiz() {
        return achieveQuiz;
    }

    public void setAchieveQuiz(int achieveQuiz) {
        this.achieveQuiz = achieveQuiz;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
