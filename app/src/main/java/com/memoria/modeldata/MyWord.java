package com.memoria.modeldata;

import java.io.Serializable;

public class MyWord implements Serializable {

    private String groupName;
    private String englishWord;
    private String koreanWord;
    private String date;    //날짜 YYYY-MM-DD
    private int correct;

    public MyWord(){}

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getKoreanWord() {
        return koreanWord;
    }

    public void setKoreanWord(String koreanWord) {
        this.koreanWord = koreanWord;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCorrect(){ return correct;}

    public void setCorrect(int correct){ this.correct= correct;}


}
