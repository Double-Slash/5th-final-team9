package com.memoria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.memoria.R;
import com.memoria.dbhelper.MyMemoryDBHelper;
import com.memoria.modeldata.MyMemory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemoryAddActivity extends AppCompatActivity {

    EditText memoryAddEdit;
    TextView finishBtn;

    MyMemoryDBHelper myMemoryDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_add);

        memoryAddEdit = findViewById(R.id.memory_add_edit);
        myMemoryDBHelper = new MyMemoryDBHelper(this);

        finishBtn = findViewById(R.id.finish_btn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //현재날짜
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
                String formatDate = sdfNow.format(date);

                MyMemory myMemory = new MyMemory();
                myMemory.setEnglishMemory(memoryAddEdit.getText().toString());
                myMemory.setDate(formatDate);
                myMemoryDBHelper.insertMemory(myMemory);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}