package com.memoria.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.memoria.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<com.memoria.adapter.RecyclerViewAdapter.ViewHolder> {

    private final ArrayList<String> titleList;
    private final ArrayList<String> countList;
    TextView goalTitle;
    TextView goalCount;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goalTitle = itemView.findViewById(R.id.goal_title_text);
            goalCount = itemView.findViewById(R.id.goal_count_text);
        }
    }

    public RecyclerViewAdapter(ArrayList<String> titleList, ArrayList<String> countList) {
        this.titleList = titleList;
        this.countList = countList;
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
        goalTitle.setText(titleList.get(position));
        goalCount.setText(countList.get(position));

    }

    @Override
    public int getItemCount() {
        return titleList.size() ;
    }

}