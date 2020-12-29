package com.memoria.modeldata;

import java.io.Serializable;

public class MyTest implements Serializable {

    private String groupName;
    private int correct;
    private int miss;
    private float percent;
    private String date;    //날짜 YYYY-MM-DD

    public MyTest(){}

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public float getPercent(){return percent;}

    public void setPercent(float percent){this.percent=percent;}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

