package com.memoria.fragment;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
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
import com.memoria.activity.SetTimeActivity;
import com.memoria.service.BroadcastD;
import com.memoria.service.ScreenService;

import java.text.SimpleDateFormat;
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

    //mylockfragment
    MyLockTestFragment fragment2;


    static private int setM;
    static private int setH;

    View view;
    int time = 1;

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

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            time = Integer.parseInt(bundle.getString("send"));
            Log.d("time", Integer.toString(time));
        }

        // 잠금화면에 띄울 단어 설정과 스위치 부분 제가 임시로 할게요...

        switchOfLock = (Switch)view.findViewById(R.id.switchOfLock);
        switchOfLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Intent intent = new Intent(getActivity(), ScreenService.class);
                    getActivity().startService(intent);
                }
                else{
                    Intent intent = new Intent(getActivity(), ScreenService.class);
                    getActivity().stopService(intent);
                }
            }
        });
        fragment2=new MyLockTestFragment();

        lockButton=view.findViewById(R.id.lockButton);
        lockButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fragment2);

            }
        });

        //SharedPreferences sharedPreferences = getShared

        pushButton = view.findViewById(R.id.pushButton);
        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SetTimeActivity.class);
                startActivity(intent);
            }
        });

        switchOfPush = view.findViewById(R.id.switchOfPush);
        switchOfPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    long now = System.currentTimeMillis();
//                    Date mDate = new Date(now);
//                    SimpleDateFormat simpleH = new SimpleDateFormat("hh");
//                    SimpleDateFormat simpleM = new SimpleDateFormat("mm");
//                    String getH = simpleH.format(mDate);
//                    String getM = simpleM.format(mDate);
//
//                    if (Integer.toString(setH) == getH && Integer.toString(setM) == getM) {
                        new AlarmHATT(getContext()).setTime(time);
//                    }
                }
                else { new AlarmHATT(getContext()).cancelTime();}
            }
        });

        backupButton = view.findViewById(R.id.backupButton);
        backupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DataBackupActivity.class);
                startActivity(intent);
            }
        });

        restoreButton = view.findViewById(R.id.restoreButton);
        restoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DataRestoreActivity.class);
                startActivity(intent);
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

        private void setTime(int time) {
            Calendar calendar = Calendar.getInstance();
            //calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 14);
            calendar.set(Calendar.MINUTE, 00);
//            setH = calendar.get(Calendar.HOUR_OF_DAY);
//            setM = calendar.get(Calendar.MINUTE);

            long interval = 1000 * 60 * 60 * 24;
            long aTime = System.currentTimeMillis();
            long bTime = calendar.getTimeInMillis();
            if(aTime > bTime){
                bTime += interval;
            }

            //알람 예약
            am.setRepeating(AlarmManager.RTC_WAKEUP, bTime,
                    interval * time, sender);
        }
        private void cancelTime(){
            if (am!= null) {
                am.cancel(sender);
            }
        }
    }

}
