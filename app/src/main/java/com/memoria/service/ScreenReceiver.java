package com.memoria.service;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.memoria.activity.LockScreenActivity;

// 핸드폰 화면의 on/off를 인식해줄 리시버
public class ScreenReceiver extends BroadcastReceiver {

    long first = 0;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){    //화면이 켜졌다는 인텐트를 받으면
            first = System.currentTimeMillis();       //켜졌을때의 시간 계산
        }

        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){              //화면이 꺼졌다는 인텐트를 받으면
            Intent i = new Intent(context, LockScreenActivity.class);         //LockScreenActivity를 띄움
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            long second = System.currentTimeMillis();       //꺼졌을때의 시간 계산

            i.putExtra("SECOND", second);       //screen-On 시간과 screen-OFF 시간을 넘겨줘서 LockScreenActivity에서 계산
            i.putExtra("FIRST", first);

            context.startActivity(i);
        }
    }
}