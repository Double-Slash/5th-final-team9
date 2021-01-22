package com.memoria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.memoria.R;

public class DataRestoreResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_restore_result);

        Button backMainBtn = findViewById(R.id.startButton);
        backMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataRestoreResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}