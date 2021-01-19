package com.memoria.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.memoria.R;
import com.memoria.dbhelper.MyWordDBHelper;
import com.memoria.modeldata.MyWord;


import java.util.ArrayList;
import java.util.Collections;

public class TestActivity extends AppCompatActivity {

    ArrayList<String> selectGroup;
    private MyWordDBHelper myWordDBHelper;
    ArrayList<MyWord> myWords;
    ArrayList<MyWord> wordList;
    TextView english;
    EditText answer;
    Button next;
    MyWord currentWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        selectGroup = getIntent().getStringArrayListExtra("SELECTED_GROUP");
        myWordDBHelper = new MyWordDBHelper(this);
        wordList = new ArrayList<MyWord>();
        for (String element : selectGroup) {
            myWords = myWordDBHelper.selectWordListByGroup(element);
            wordList.addAll(myWords);
        }
        // 랜덤으로 섞는다.
        Collections.shuffle(wordList);
        english = findViewById(R.id.test_english);
        next = findViewById(R.id.test_next);
        currentWord = wordList.get(0);
        english.setText(currentWord.getEnglishWord());
        answer = findViewById(R.id.test_answer);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer.getText().toString() == currentWord.getKoreanWord()) {
                    if (wordList.size() == 1) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        wordList.remove(0);
                        currentWord = wordList.get(0);
                        english.setText(currentWord.getEnglishWord());
                        answer.getText().clear();
                        if(wordList.size()==1){
                            next.setBackgroundColor(Color.parseColor("#7AEAC3"));
                            next.setText("DONE");
                        }
                    }
                } else {
                    if (wordList.size() == 1) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        currentWord = wordList.get(1);
                        wordList.remove(0);
                        english.setText(currentWord.getEnglishWord());
                        answer.getText().clear();
                        if(wordList.size()==1){
                            next.setBackgroundColor(Color.parseColor("#7AEAC3"));
                            next.setText("DONE");
                        }
                    }
                }
            }

        });
    }
}
