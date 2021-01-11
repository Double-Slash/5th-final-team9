package com.memoria.activity;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getList();
        // 랜덤으로 섞는다.
        Collections.shuffle(wordList);

        english=findViewById(R.id.test_english);
        answer=findViewById(R.id.test_answer);
        next=findViewById(R.id.test_next);
        for(MyWord element:wordList){
            element.getEnglishWord();
            english.setText((CharSequence) element);
            next.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(answer.getText().toString()==element.getKoreanWord().toString()){

                    }

                }
            });

        }

    }

    public void getList(){
        selectGroup = getIntent().getStringArrayListExtra("SELECTED_GROUP");
        myWordDBHelper = new MyWordDBHelper(this);
        wordList= new ArrayList<MyWord>();
        for(String element:selectGroup){
            myWords = myWordDBHelper.selectWordListByGroup(element);
            wordList.addAll(myWords);
        }
    }
}
