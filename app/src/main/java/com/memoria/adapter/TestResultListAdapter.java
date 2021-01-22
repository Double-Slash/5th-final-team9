package com.memoria.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.memoria.R;
import com.memoria.modeldata.MyTest;

import java.util.ArrayList;

public class TestResultListAdapter extends ArrayAdapter<MyTest> {

    private ArrayList<MyTest> items;

    public TestResultListAdapter(Context context, int textViewResourceId, ArrayList<MyTest> objects) {
        super(context, textViewResourceId, objects);
        this.items = objects;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_test_result, null);
        }

        TextView wordNum = v.findViewById(R.id.word_num);
        TextView EnglishWordText = v.findViewById(R.id.english_word);
        TextView KoreanWordText = v.findViewById(R.id.korean_word);
        TextView userWordText = v.findViewById(R.id.user_wrod);

        wordNum.setText(position);


        return v;
    }

}