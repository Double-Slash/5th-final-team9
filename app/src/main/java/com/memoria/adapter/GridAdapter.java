package com.memoria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.memoria.R;
import com.memoria.modeldata.MyWord;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends ArrayAdapter<MyWord> {

    private ArrayList<MyWord> items;
    public List<Integer> selectedPositions;

    public GridAdapter(Context context, int textViewResourceId, ArrayList<MyWord> objects) {
        super(context, textViewResourceId, objects);
        this.items = objects;
        selectedPositions = new ArrayList<>();

    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.grid_item_myword, null);
        }

        TextView textView = v.findViewById(R.id.group_name);
        textView.setText(items.get(position).getGroupName());

        return v;
    }
}