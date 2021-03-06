package com.memoria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.memoria.R;
import com.memoria.adapter.TestResultListAdapter;
import com.memoria.modeldata.MyWord;

import java.util.ArrayList;

public class TestResultDetailsActivity extends AppCompatActivity {

    TestResultListAdapter wordListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result_details);

        Intent intent = getIntent();
        ArrayList<MyWord> wordArrayList = (ArrayList<MyWord>) intent.getSerializableExtra("wordTestResult");
        MyWord myWord = new MyWord();
        myWord.setKoreanWord("의미");
        myWord.setEnglishWord("단어");
        myWord.setUserTestWord("답안");
        wordArrayList.add(0, myWord); //0인덱스는 보기 넣을거라 그냥 빈 오브젝트넣음

        ListView listView = findViewById(R.id.word_list);

        wordListAdapter = new TestResultListAdapter(this, 0, wordArrayList);

        listView.setAdapter(wordListAdapter);

        Button button = findViewById(R.id.backMainBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}