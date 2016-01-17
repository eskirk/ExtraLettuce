package com.example.elliot.extralettuce.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.elliot.extralettuce.dataClasses.Goal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 1/16/2016.
 */
public class GoalCardAdapter extends RecyclerView.Adapter<GoalCardAdapter.ViewHolder> {
    private List<Goal> goalList;

    public GoalCardAdapter(List<Goal> goals) {
        goalList = goals;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return goalList != null ? goalList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void addGoal(Goal goal) {
        goalList.add(goal);
        sortList();
        notifyDataSetChanged();
    }

    public void removeGoal(int pos) {
        goalList.remove(pos);
        notifyDataSetChanged();
    }

    public void setGoalList(List<Goal> goalList) {
        this.goalList = goalList;
    }

    private void sortList() {

    }

}
