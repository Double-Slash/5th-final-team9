package com.memoria.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.memoria.R;
import com.memoria.activity.MainActivity;

public class BroadcastD extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default");
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setSmallIcon(R.drawable.logo)
                .setContentTitle("메모리아 캐치프레이즈")
                .setContentText("이제 공부할 시간입니다!")
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent).setAutoCancel(true);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(new NotificationChannel("default","푸시알림", NotificationManager.IMPORTANCE_DEFAULT));
        manager.notify(1, builder.build());
    }
}
