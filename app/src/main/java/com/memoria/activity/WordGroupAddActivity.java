package com.memoria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.memoria.R;
import com.memoria.dbhelper.MyWordDBHelper;

public class WordGroupAddActivity extends AppCompatActivity {

    EditText groupAddEdit;
    TextView finishBtn;

    MyWordDBHelper myWordDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_group_add);

        groupAddEdit = findViewById(R.id.group_add_edit);
        myWordDBHelper = new MyWordDBHelper(this);

        finishBtn = findViewById(R.id.finish_btn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupAddName =  groupAddEdit.getText().toString();
                myWordDBHelper.insertWordGroup(groupAddName);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}