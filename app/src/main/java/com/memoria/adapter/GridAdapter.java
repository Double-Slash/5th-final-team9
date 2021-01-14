package com.memoria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.memoria.R;
import com.memoria.dbhelper.MyWordDBHelper;
import com.memoria.modeldata.MyWord;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends ArrayAdapter<MyWord> {

    private ArrayList<MyWord> items;
    private boolean deleteVisible;
    public List<Integer> selectedPositions;

    MyWordDBHelper myWordDBHelper;

    public GridAdapter(Context context, int textViewResourceId, ArrayList<MyWord> objects, boolean deleteVisible) {
        super(context, textViewResourceId, objects);
        this.items = objects;
        this.deleteVisible = deleteVisible;
        selectedPositions = new ArrayList<>();

    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        myWordDBHelper = new MyWordDBHelper(getContext());

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.grid_item_myword, null);
        }

        if (deleteVisible){
            ImageButton deleteXBtn = v.findViewById(R.id.delete_x_btn);
            deleteXBtn.setVisibility(View.VISIBLE);
        }
        ImageButton deleteXBtn = v.findViewById(R.id.delete_x_btn);

        deleteXBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), position + " ", Toast.LENGTH_SHORT).show();
                myWordDBHelper.deleteGroub(items.get(position).getGroupName());
                items.remove(position);
                notifyDataSetChanged();
            }
        });


        TextView textView = v.findViewById(R.id.group_name);
        textView.setText(items.get(position).getGroupName());

        return v;
    }
}