package com.memoria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.memoria.R;
import com.memoria.adapter.WordListAdapter;
import com.memoria.dbhelper.MyWordDBHelper;
import com.memoria.modeldata.MyWord;

import java.util.ArrayList;

public class WordListActivity extends AppCompatActivity {

    private MyWordDBHelper myWordDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        Intent intent = getIntent();
        String groupName = intent.getExtras().getString("groupName");

        myWordDBHelper = new MyWordDBHelper(this);
        ArrayList<MyWord> myWords = myWordDBHelper.selectWordListByGroup(groupName);

        ListView listView = findViewById(R.id.word_list);
        WordListAdapter wordListAdapter = new WordListAdapter(this, 0, myWords);

        listView.setAdapter(wordListAdapter);

        TextView titleText = findViewById(R.id.title_text);
        titleText.setText(groupName + "(" + myWords.size() + ")");

    }
}