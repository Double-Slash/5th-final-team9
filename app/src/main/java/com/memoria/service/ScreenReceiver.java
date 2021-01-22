package com.memoria.service;


import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.memoria.activity.LockScreenActivity;

// 핸드폰 화면의 on/off를 인식해줄 리시버
public class ScreenReceiver extends BroadcastReceiver {
    private KeyguardManager km = null;

    private KeyguardManager.KeyguardLock keyLock = null;



    @Override

    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

            if (km == null)

                km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);



            if (keyLock == null)

                keyLock = km.newKeyguardLock(Context.KEYGUARD_SERVICE);



            disableKeyguard();



            Intent i = new Intent(context, LockScreenActivity.class);

            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(i);

        }

    }



    public void reenableKeyguard() {

        keyLock.reenableKeyguard();

    }



    public void disableKeyguard() {

        keyLock.disableKeyguard();

    }


}