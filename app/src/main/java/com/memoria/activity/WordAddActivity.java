package com.memoria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.memoria.R;
import com.memoria.dbhelper.MyWordDBHelper;
import com.memoria.modeldata.MyWord;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WordAddActivity extends AppCompatActivity {

    EditText englishWordAddEdit;
    EditText koreanWordAddEdit;
    TextView finishBtn;

    MyWordDBHelper myWordDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_add);

        myWordDBHelper = new MyWordDBHelper(this);

        Intent intent = getIntent();
        String groupName = intent.getExtras().getString("groupName");

        englishWordAddEdit = findViewById(R.id.english_word_add_edit);
        koreanWordAddEdit = findViewById(R.id.korean_word_add_edit);

        finishBtn = findViewById(R.id.finish_btn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String englishWordAdd =  englishWordAddEdit.getText().toString();
                String koreanWordAdd =  koreanWordAddEdit.getText().toString();

                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
                String formatDate = sdfNow.format(date);

                MyWord myWord = new MyWord();
                myWord.setGroupName(groupName);
                myWord.setEnglishWord(englishWordAdd);
                myWord.setKoreanWord(koreanWordAdd);
                myWord.setDate(formatDate);
                myWordDBHelper.insertWord(myWord);

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}