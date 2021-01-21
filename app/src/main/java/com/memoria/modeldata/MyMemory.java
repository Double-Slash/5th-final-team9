package com.memoria.modeldata;

import java.io.Serializable;

public class MyMemory implements Serializable {

    private String englishMemory;
    private String date;    //날짜 YYYY-MM-DD

    public MyMemory(){}

    public MyMemory(String englishMemory, String date) {
        this.englishMemory = englishMemory;
        this.date = date;
    }

    public String getEnglishMemory() {
        return englishMemory;
    }

    public void setEnglishMemory(String englishMemory) {
        this.englishMemory = englishMemory;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
