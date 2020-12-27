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

public class WordListAdapter extends ArrayAdapter<MyWord> {

    private ArrayList<MyWord> items;

    public WordListAdapter(Context context, int textViewResourceId, ArrayList<MyWord> objects) {
        super(context, textViewResourceId, objects);
        this.items = objects;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_myword, null);
        }

        TextView tvName = v.findViewById(R.id.english_word);
        tvName.setText(items.get(position).getEnglishWord());

        TextView textView = v.findViewById(R.id.korean_word);
        textView.setText(items.get(position).getKoreanWord());

        return v;
    }
}
