package com.memoria.modeldata;

public class GoalAchieve {

    private String goalTitle;
    private String goalCount;

    public GoalAchieve(){}

    public GoalAchieve(String goalTitle, String goalCount) {
        this.goalTitle = goalTitle;
        this.goalCount = goalCount;
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    public void setGoalTitle(String goalTitle) {
        this.goalTitle = goalTitle;
    }

    public String getGoalCount() {
        return goalCount;
    }

    public void setGoalCount(String goalCount) {
        this.goalCount = goalCount;
    }
}
