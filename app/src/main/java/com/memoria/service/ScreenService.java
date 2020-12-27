package com.memoria.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class ScreenService extends Service {

    private ScreenReceiver mReceiver = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    @Override
    public void onCreate() {
        super.onCreate();

        mReceiver = new ScreenReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);  //addAction으로 2개 이상의 신호를 받을 수 있어
        registerReceiver(mReceiver,filter);         //브로드캐스트리시버를 등록
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {  //서비스를 강제 종료했을때, 서비스를 어떤 방법으로 다시 시작시킬지 결정.
        super.onStartCommand(intent, flags, startId);

        if(intent != null){
            if(intent.getAction()==null){
                if(mReceiver==null){
                    mReceiver = new ScreenReceiver();
                    IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
                    filter.addAction(Intent.ACTION_SCREEN_ON);
                    registerReceiver(mReceiver,filter);
                }
            }
        }
        return START_REDELIVER_INTENT;      //이후 서비스 재생성 가능, 강제로 종료되기 전에 전달된 마지막 Intent를 다시 전달해주는 기능 포함.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mReceiver != null){
            unregisterReceiver(mReceiver);      //리스버 등록 해제
        }
    }
}