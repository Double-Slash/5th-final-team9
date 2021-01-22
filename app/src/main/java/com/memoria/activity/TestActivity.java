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
import com.memoria.dbhelper.MyTestDBHelper;
import com.memoria.dbhelper.MyWordDBHelper;
import com.memoria.modeldata.MyTest;
import com.memoria.modeldata.MyWord;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class TestActivity extends AppCompatActivity {

    ArrayList<String> selectGroup;
    private MyWordDBHelper myWordDBHelper;
    private MyTestDBHelper myTestDBHelper;
    ArrayList<MyWord> myWords;
    ArrayList<MyWord> wordList;
    ArrayList<MyWord> wordList1;

    TextView english;
    EditText answer;
    TextView title;
    Button next;
    MyWord currentWord;
    int current=1;
    int currentCorrect=0;
    String Question=null;
    String Answer=null;
    String GroupName =null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        selectGroup = getIntent().getStringArrayListExtra("SELECTED_GROUP");
        myWordDBHelper = new MyWordDBHelper(this);
        wordList = new ArrayList<MyWord>();

        myTestDBHelper = new MyTestDBHelper(this);

        for (String element : selectGroup) {
            myWords = myWordDBHelper.selectWordListByGroup(element);
            GroupName=GroupName+element+",";
            wordList.addAll(myWords);
        }
        GroupName = GroupName.substring(4, GroupName.length()-1);
        MyTest mytest = new MyTest();
        mytest.setGroup(GroupName);
        mytest.setTotal(wordList.size());
        mytest.setStatus("unlock");
        //title
        title=findViewById(R.id.title_text);
        title.setText("Test(" + current + "/"+mytest.getTotal()+ ")");
        // 랜덤으로 섞는다.
        Collections.shuffle(wordList);
        english = findViewById(R.id.test_english);
        next = findViewById(R.id.test_next);
        currentWord = wordList.get(0);
        english.setText(currentWord.getEnglishWord());
        answer = findViewById(R.id.test_answer);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = sdfNow.format(date);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Answer=answer.getText().toString().replaceAll(" ", "");
                Question=currentWord.getKoreanWord().replaceAll(" ", "");
                if(Answer.equals(Question)){
                    title.setText("Test(" + current + "/"+mytest.getTotal()+ ")");
                    currentCorrect+=1;
                    if (wordList.size() == 1) {
                        mytest.setCorrect(currentCorrect);
                        mytest.setPercent(currentCorrect*100/mytest.getTotal());
                        mytest.setDate(formatDate);
                        myTestDBHelper.insertScore(mytest);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        current+=1;
                        title.setText("Test(" + current + "/"+mytest.getTotal()+ ")");
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
                        mytest.setCorrect(currentCorrect);
                        mytest.setPercent(currentCorrect*100/mytest.getTotal());
                        mytest.setDate(formatDate);
                        myTestDBHelper.insertScore(mytest);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        current+=1;
                        title.setText("Test(" + current + "/"+mytest.getTotal()+ ")");
                        wordList.remove(0);
                        currentWord = wordList.get(0);
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
