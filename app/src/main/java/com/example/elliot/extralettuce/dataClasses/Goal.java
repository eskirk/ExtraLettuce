package com.example.elliot.extralettuce.dataClasses;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by Elliot on 1/16/16.
 */
public class Goal {
    private String goalName;
    private int goalAmount;
    private int days;
    private int currentBal;

    //Goals can be gotten from the server through www.extralettuce.co/account/goals
    public Goal(String goalName, int goalAmount, int days, int currentBal) {
        this.goalName = goalName;
        this.goalAmount = goalAmount;
        this.days = days;
        this.currentBal = currentBal;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public int getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(int goalAmount) {
        this.goalAmount = goalAmount;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getCurrentBal() {
        return currentBal;
    }

    public void setCurrentBal(int currentBal) {
        this.currentBal = currentBal;
    }
}