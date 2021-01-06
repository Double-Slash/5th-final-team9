package com.memoria.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.memoria.R;
import com.memoria.adapter.RecyclerViewAdapter;
import com.memoria.decorator.EventDecorator;
import com.memoria.decorator.OneDayDecorator;
import com.memoria.modeldata.GoalAchieve;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

public class CalendarFragment extends Fragment {

    View view;

    ArrayList<GoalAchieve> goalAchieves;

    MaterialCalendarView materialCalendarView;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_calendar, container, false);


        //리사이클러뷰 샘플
        goalAchieves = new ArrayList<>();
        goalAchieves.add(new GoalAchieve("My Word", "20개"));
        goalAchieves.add(new GoalAchieve("My Memory", "2개"));
        goalAchieves.add(new GoalAchieve("Test", "100%"));
        goalAchieves.add(new GoalAchieve("Quiz", "50%"));

        RecyclerView recyclerView = view.findViewById(R.id.todaygoad_list) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)) ;
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(goalAchieves) ;
        recyclerView.setAdapter(adapter);


        //캘릭더뷰

        materialCalendarView = view.findViewById(R.id.calendarView);
//        materialCalendarView.addDecorator();

        String[] result = {"2020,12,18","2020,12,19","2020,12,28","2020,12,30"};
        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());



        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                Log.i("Year test", Year + "");
                Log.i("Month test", Month + "");
                Log.i("Day test", Day + "");

                String shot_Day = Year + "," + Month + "," + Day;

                Log.i("shot_Day test", shot_Day + "");
                materialCalendarView.clearSelection();

                Toast.makeText(getContext(), shot_Day , Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    @SuppressLint("StaticFieldLeak")
    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator(String[] Time_Result) {
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();
            calendar.add(Calendar.MONTH, -2);
            for (int i = 0; i < 30; i++) {
                CalendarDay day = CalendarDay.from(calendar);
                dates.add(day);
                calendar.add(Calendar.DATE, 5);
            }

            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(Calendar.MONTH, -4);
            for (int i = 0; i < 30; i++) {
                CalendarDay day = CalendarDay.from(calendar2);
                dates.add(day);
                calendar2.add(Calendar.DATE, 3);
            }

//            /*특정날짜 달력에 점표시해주는곳*/
//            /*월은 0이 1월 년,일은 그대로*/
//            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
//            for (int i = 0; i < Time_Result.length; i++) {
//                CalendarDay day = CalendarDay.from(calendar);
//                String[] time = Time_Result[i].split(",");
//                int year = Integer.parseInt(time[0]);
//                int month = Integer.parseInt(time[1]);
//                int dayy = Integer.parseInt(time[2]);
//
//                dates.add(day);
//                calendar.set(year, month - 1, dayy);
//                calendar.add(Calendar.MONTH, -2);
//                ArrayList<CalendarDay> dates = new ArrayList<>();
//                for (int i = 0; i < 30; i++) {
//                    CalendarDay day = CalendarDay.from(calendar);
//                    dates.add(day);
//                    calendar.add(Calendar.DATE, 5);
//                }
//            }
            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);
            materialCalendarView.addDecorators(new EventDecorator(Color.GREEN, calendarDays, getActivity()),
                    new EventDecorator(Color.rgb(233,127,127), calendarDays, getActivity()));
        }

    }
}