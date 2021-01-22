package com.memoria.modeldata;

import java.io.Serializable;

public class MyTest implements Serializable {

    private int correct;
    private int total;
    private float percent;
    private String status;
    private String groups;
    private String date;    //날짜 YYYY-MM-DD

    public MyTest(){}

    public MyTest(int correct, int total, float percent, String groups, String status, String date) {
        this.correct = correct;
        this.total = total;
        this.percent = percent;
        this.status = status;
        this.date = date;
        this.groups = groups;
    }

    public String getStatus(){ return status;}

    public void setStatus(String status){ this.status=status;}

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total =total;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public float getPercent(){return percent;}

    public void setPercent(float percent){this.percent=percent;}

    public String getGroup() {return groups;}

    public void setGroup(String groups){this.groups=groups;}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

