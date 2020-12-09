package com.memoria;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LockScreenActivity extends AppCompatActivity {

    private Window window;
    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED); //안드로이드 기본 잠금화면 보다 위에 이 activity를 띄워라
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);


        long first = getIntent().getLongExtra("FIRST", 0);
        long second = getIntent().getLongExtra("SECOND", 0);

        long timeUsed = (second - first)/1000/60;           //지난번 핸드폰 사용시간 계산

        textView = (TextView)findViewById(R.id.time);
        textView.setText("핸드폰 사용 시간(분): "+timeUsed);


    }

    @Override public void onBackPressed() {
        //뒤로가기 막기
        //super.onBackPressed();
    }

    //메뉴키 막는 방법

    protected void onPause() {
        super.onPause();
        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);
        Toast.makeText(this, "메뉴키 사용불가.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onUserLeaveHint() {

        Toast.makeText(this, "홈 버튼", Toast.LENGTH_LONG).show();
//        finish();

        Intent i = new Intent(this, LockScreenActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

        super.onUserLeaveHint();
    }

}