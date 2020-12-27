package com.memoria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.memoria.R;
import com.memoria.modeldata.MyMemory;

import java.util.ArrayList;

public class MemoryListAdapter extends ArrayAdapter<MyMemory> {

    private ArrayList<MyMemory> items;

    public MemoryListAdapter(Context context, int textViewResourceId, ArrayList<MyMemory> objects) {
        super(context, textViewResourceId, objects);
        this.items = objects;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_mymemory, null);
        }

        TextView tvName = v.findViewById(R.id.memory_text);
        tvName.setText(items.get(position).getEnglishMemory());

        return v;
    }
}
