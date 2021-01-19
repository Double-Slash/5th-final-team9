package com.memoria.modeldata;

import java.io.Serializable;

public class MyTest implements Serializable {

    private int correct;
    private int total;
    private float percent;
    private String status;
    private String date;    //날짜 YYYY-MM-DD

    public MyTest(){}

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

