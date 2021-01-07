package com.memoria.fragment;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.memoria.R;
import com.memoria.activity.DataBackupActivity;
import com.memoria.activity.DataRestoreActivity;
import com.memoria.activity.MainActivity;
import com.memoria.service.BroadcastD;

import java.util.Calendar;
import java.util.Date;

public class OptionFragment extends Fragment {
    //잠금화면 설정
    private Switch switchOfLock;
    private ImageButton lockButton;
    //푸시알림 설정
    private Switch switchOfPush;
    private ImageButton pushButton;
    //데이터 설정
    private ImageButton backupButton;
    private ImageButton restoreButton;

    View view;

    public OptionFragment() {
        // Required empty public constructor
    }

    public static OptionFragment newInstance() {
        OptionFragment fragment = new OptionFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_option, container, false);

        switchOfPush = view.findViewById(R.id.switchOfPush);
        switchOfPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){ new AlarmHATT(getContext()).setTime(); }
            }
        });

        backupButton = view.findViewById(R.id.backupButton);
        backupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), DataBackupActivity.class);
                startActivity(intent1);
            }
        });

        restoreButton = view.findViewById(R.id.restoreButton);
        restoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getActivity(), DataRestoreActivity.class);
                startActivity(intent2);
            }
        });


        return view;
    }

    private class AlarmHATT {
        private Context context;
        public AlarmHATT(Context context) {
            this.context=context;
        }

        AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), BroadcastD.class);
        PendingIntent sender = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

        public void setTime() {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 20);
            //calendar.set(Calendar.MINUTE, 30);
            //알람 예약
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    1000 * 60 * 60 * 24, sender);
        }
    }

}
