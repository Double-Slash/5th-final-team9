package com.memoria.activity.intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.memoria.R;
import com.memoria.activity.MainActivity;
import com.memoria.dbhelper.GoalDBHelper;

public class IntroActivity extends AppCompatActivity {

    public SharedPreferences prefs;

    GoalDBHelper goalDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        prefs = getSharedPreferences("Pref", MODE_PRIVATE);
        goalDBHelper = new GoalDBHelper(this);
        //나중에 지우기
//        goalDBHelper.insertDefault();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                checkFirstRun();
            }
        },2000);
    }

    public void checkFirstRun() {
        boolean isFirstRun = prefs.getBoolean("isFirstRun", false);
        if (!isFirstRun) {
            goalDBHelper.insertDefault();
            Intent newIntent = new Intent(IntroActivity.this, PermissionActivity.class);
            startActivity(newIntent);
            prefs.edit().putBoolean("isFirstRun", false).apply();
            finish();
        }else {
            if(  !goalDBHelper.selectExistTodayData()) goalDBHelper.insertFollowYesterday();
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}