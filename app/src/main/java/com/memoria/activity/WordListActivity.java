package com.memoria.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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
    String groupName;
    ImageButton addBtn;

    ArrayList<MyWord> myWords;
    WordListAdapter wordListAdapter;
    ListView listView;
    TextView titleText;

    public static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        Intent intent = getIntent();
        groupName = intent.getExtras().getString("groupName");

        myWordDBHelper = new MyWordDBHelper(this);
        myWords = myWordDBHelper.selectWordListByGroup(groupName);

        listView = findViewById(R.id.word_list);
        wordListAdapter = new WordListAdapter(this, 0, myWords);

        listView.setAdapter(wordListAdapter);

        addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WordListActivity.this, WordAddActivity.class);
                intent.putExtra("groupName", groupName);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        titleText = findViewById(R.id.title_text);
        titleText.setText(groupName + "(" + myWords.size() + ")");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                return;
            }
            myWords = myWordDBHelper.selectWordListByGroup(groupName);
            wordListAdapter = new WordListAdapter(this, 0, myWords);
            listView.setAdapter(wordListAdapter);
            titleText.setText(groupName + "(" + myWords.size() + ")");
        }
    }
}