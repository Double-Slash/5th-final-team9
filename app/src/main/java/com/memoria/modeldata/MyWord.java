package com.memoria.modeldata;

import java.io.Serializable;

public class MyWord implements Serializable {

    private String groupName;
    private String englishWord;
    private String koreanWord;
    private String date;    //날짜 YYYY-MM-DD

    private String userTestWord;

    public MyWord(){}

    public MyWord(String groupName, String englishWord, String koreanWord, String date) {
        this.groupName = groupName;
        this.englishWord = englishWord;
        this.koreanWord = koreanWord;
        this.date = date;
    }

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

    public String getUserTestWord() {
        return userTestWord;
    }

    public void setUserTestWord(String userTestWord) {
        this.userTestWord = userTestWord;
    }
}
