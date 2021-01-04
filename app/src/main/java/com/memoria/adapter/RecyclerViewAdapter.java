package com.memoria.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.memoria.R;
import com.memoria.modeldata.GoalAchieve;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<com.memoria.adapter.RecyclerViewAdapter.ViewHolder> {

    private ArrayList<GoalAchieve> items;
    TextView goalTitle;
    TextView goalCount;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goalTitle = itemView.findViewById(R.id.goal_title_text);
            goalCount = itemView.findViewById(R.id.goal_count_text);
        }
    }

    public RecyclerViewAdapter(ArrayList<GoalAchieve> list) {
        items = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item_goal, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        goalTitle.setText(items.get(position).getGoalTitle());
        goalCount.setText(items.get(position).getGoalCount());

    }

    @Override
    public int getItemCount() {
        return items.size() ;
    }

}