package com.memoria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.memoria.R;

public class DataBackupResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_backup_result);

        Button backMainBtn = findViewById(R.id.backMainBtn);
        backMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataBackupResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}