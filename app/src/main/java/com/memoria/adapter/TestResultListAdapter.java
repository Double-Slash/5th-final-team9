package com.memoria.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.memoria.R;
import com.memoria.modeldata.MyWord;

import java.util.ArrayList;

public class TestResultListAdapter extends ArrayAdapter<MyWord> {

    private ArrayList<MyWord> items;
    TextView wordNum;

    public TestResultListAdapter(Context context, int textViewResourceId, ArrayList<MyWord> objects) {
        super(context, textViewResourceId, objects);
        this.items = objects;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item_test_result, null);
        }

        LinearLayout linearLayout = v.findViewById(R.id.item_layout);
        wordNum = v.findViewById(R.id.word_num);
        TextView englishWordText = v.findViewById(R.id.english_word);
        TextView koreanWordText = v.findViewById(R.id.korean_word);
        TextView userWordText = v.findViewById(R.id.user_wrod);

        if (position==0){
            wordNum.setText("번호");
            englishWordText.setText("단어");
            koreanWordText.setText("의미");
            userWordText.setText("답안");

            wordNum.setTextColor(Color.BLACK);
            englishWordText.setTextColor(Color.BLACK);
            koreanWordText.setTextColor(Color.BLACK);
            userWordText.setTextColor(Color.BLACK);

            linearLayout.setBackgroundResource(R.drawable.shape_rectangle_shadow_white_coners_10);
            wordNum.setBackgroundResource(R.drawable.shape_rectangle_shadow_gray_10);
            englishWordText.setBackgroundResource(R.drawable.shape_rectangle_shadow_gray_10);
            koreanWordText.setBackgroundResource(R.drawable.shape_rectangle_shadow_gray_10);
            userWordText.setBackgroundResource(R.drawable.shape_rectangle_shadow_gray_10);
        }else {
            wordNum.setText(position+"");
            linearLayout.setBackgroundResource(R.drawable.shape_rectangle_shadow_white_coners_10);
            if (!(items.get(position).getKoreanWord().equals(items.get(position).getUserTestWord()))) {
                linearLayout.setBackgroundResource(R.drawable.shape_rectangle_shadow_red_coners_10);
                wordNum.setTextColor(Color.RED);
                userWordText.setTextColor(Color.RED);
            } else {
                linearLayout.setBackgroundResource(R.drawable.shape_rectangle_shadow_white_coners_10);
                wordNum.setTextColor(Color.parseColor("#707070"));
                userWordText.setTextColor(Color.parseColor("#707070"));
            }
        }

        englishWordText.setText(items.get(position).getEnglishWord());
        koreanWordText.setText(items.get(position).getKoreanWord());
        userWordText.setText(items.get(position).getUserTestWord());

        return v;
    }

}