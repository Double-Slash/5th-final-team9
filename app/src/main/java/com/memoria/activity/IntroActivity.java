package com.memoria.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.memoria.R;

public class IntroActivity extends AppCompatActivity {

    public SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        prefs = getSharedPreferences("Pref", MODE_PRIVATE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                checkFirstRun();
            }
        },2000);
    }

    public void checkFirstRun() {
        boolean isFirstRun = prefs.getBoolean("isFirstRun", true);
        if (isFirstRun) {
            Intent newIntent = new Intent(IntroActivity.this,PermissionActivity.class);
            startActivity(newIntent);
            prefs.edit().putBoolean("isFirstRun", false).apply();
            finish();
        }else {
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}