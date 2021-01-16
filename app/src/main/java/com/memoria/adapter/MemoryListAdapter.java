package com.memoria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.memoria.R;
import com.memoria.dbhelper.MyMemoryDBHelper;
import com.memoria.modeldata.MyMemory;

import java.util.ArrayList;

public class MemoryListAdapter extends ArrayAdapter<MyMemory> {

    private ArrayList<MyMemory> items;
    private boolean deleteVisible;

    MyMemoryDBHelper myMemoryDBHelper;


    public MemoryListAdapter(Context context, int textViewResourceId, ArrayList<MyMemory> objects, boolean deleteVisible) {
        super(context, textViewResourceId, objects);
        this.items = objects;
        this.deleteVisible = deleteVisible;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        myMemoryDBHelper = new MyMemoryDBHelper(getContext());

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_mymemory, null);
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
                myMemoryDBHelper.deleteMemory(items.get(position).getEnglishMemory());
                items.remove(position);
                notifyDataSetChanged();
            }
        });

        TextView tvName = v.findViewById(R.id.memory_text);
        tvName.setText(items.get(position).getEnglishMemory());

        return v;
    }
}
