package com.memoria.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.memoria.R;
import com.memoria.activity.intro.GoalSettingActivity;
import com.memoria.adapter.RecyclerViewAdapter;
import com.memoria.dbhelper.GoalDBHelper;
import com.memoria.dbhelper.MyMemoryDBHelper;
import com.memoria.dbhelper.MyTestDBHelper;
import com.memoria.dbhelper.MyWordDBHelper;
import com.memoria.decorator.EventDecorator;
import com.memoria.modeldata.Goal;
import com.memoria.modeldata.MyTest;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import static android.content.Context.MODE_PRIVATE;

public class CalendarFragment extends Fragment {

    View view;
    Dialog guideDilaog; // 커스텀 다이얼로그

    public SharedPreferences prefs;

    ImageButton goalModifyBtn;

    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    MaterialCalendarView materialCalendarView;

    MyWordDBHelper wordDBHelper;
    MyMemoryDBHelper memoryDBHelper;
    GoalDBHelper goalDBHelper;
    MyTestDBHelper testDBHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_calendar, container, false);

        goalModifyBtn = view.findViewById(R.id.goal_modify_btn);
        goalModifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GoalSettingActivity.class);
                startActivity(intent);
            }
        });

        Context context = getContext();
        wordDBHelper = new MyWordDBHelper(context);
        memoryDBHelper = new MyMemoryDBHelper(context);
        goalDBHelper = new GoalDBHelper(context);
        testDBHelper = new MyTestDBHelper(context);

        //달성률 업데이트
        Goal goal = new Goal();
        goal.setAchieveWord(wordDBHelper.selectWordCountByDate(getNowDate()));
        goal.setAchieveMemory(memoryDBHelper.selectMemoryCountByDate(getNowDate()));
        goal.setAchieveTest(testDBHelper.selectUnLockMaxPercent());
        goal.setAchieveQuiz(testDBHelper.selectLockMaxPercent());
        goalDBHelper.updateAchieve(goal);

        recyclerView = view.findViewById(R.id.todaygoad_list) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)) ;
        recyclerViewSetting(goal);
        goalDotDecoration();

        //캘린더뷰
        materialCalendarView = view.findViewById(R.id.calendarView);
        materialCalendarViewSetting();

        goalDBHelper.close();
        wordDBHelper.close();
        memoryDBHelper.close();
        testDBHelper.close();

        //캘린더 처음 올 경우만 가이드 등장
        prefs = getActivity().getSharedPreferences("Pref", MODE_PRIVATE);
        boolean isFirstRun = prefs.getBoolean("isFirstCalendarRun", true);
        if(isFirstRun){
            prefs.edit().putBoolean("isFirstCalendarRun", false).apply();
            showDialog();
        }
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] dateFormat;
        String goalCheck;
        int[] colorData = {Color.parseColor("#E97F7F"),
                            Color.parseColor("#E0AAFF"),
                            Color.parseColor("#7FE7FF"),
                            Color.parseColor("#81FB85")};

        List<Integer> colorList = new ArrayList<>();
        int[] colors;

        ApiSimulator(String[] dateFormat, String goalCheck) {
            this.dateFormat = dateFormat;
            this.goalCheck = goalCheck;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월이라 -1 해야함. 년,일은 그대로*/
            //string 문자열인 dateFormat 을 받아와서 -를 기준으로 int형 변환

            for (String s : dateFormat) {
                String[] time = s.split("-");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int day = Integer.parseInt(time[2]);
                calendar.set(year, month - 1, day);
                CalendarDay calendarDay = CalendarDay.from(calendar);
                dates.add(calendarDay);
            }

            //뒤로 추가되면 Dot컬러가 반대순서가 되어버림. 추가할때마다 0번 인덱스에 추가.
            for( int i = 0 ; i <4 ; i++) if (goalCheck.charAt(i) == '1') colorList.add(0,colorData[i]);
            colors = colorList.stream().mapToInt(Integer::intValue).toArray();

            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);
            materialCalendarView.addDecorators(new EventDecorator(colors, calendarDays, getActivity()));
        }
    }
    public String getNowDate(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
        return sdfNow.format(date);
    }

    public void recyclerViewSetting(Goal goal){
        ArrayList<String> title = new ArrayList<>(Arrays.asList("My Word", "My Memory", "Test", "Quiz"));
        ArrayList<String> count = new ArrayList<>(Arrays.asList(
                goal.getAchieveWord()+"개",
                goal.getAchieveMemory()+"개",
                goal.getAchieveTest()+"%",
                goal.getAchieveQuiz()+"%"));

        adapter = new RecyclerViewAdapter(title, count) ;
        recyclerView.setAdapter(adapter);
    }

    // dialog01 커스텀
    public void showDialog(){

        // *findViewById()를 쓸 때 앞에 반드시 다이얼로그 이름
        guideDilaog = new Dialog(getContext(),android.R.style.Theme_Translucent_NoTitleBar_Fullscreen); //화면 꽉차게
        guideDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        guideDilaog.setContentView(R.layout.dialog_calendar_guide);
        guideDilaog.show();

        ImageButton xBtn = guideDilaog.findViewById(R.id.x_btn);
        xBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guideDilaog.dismiss();
            }
        });

    }


    //캘린더 연결
    public void materialCalendarViewSetting(){
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
        materialCalendarView.setSelectionColor(getResources().getColor(R.color.gray));
        materialCalendarView.setHeaderTextAppearance(R.style.calendar_titlebar);
        materialCalendarView.setWeekDayLabels(new CharSequence[]{"S","M","T","W","T","F","S"});
        materialCalendarView.state().edit().isCacheCalendarPositionEnabled(true);

        //일요일이면 빨간색으로
        materialCalendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                // check if weekday is sunday
                return day.getCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
            }
            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new ForegroundColorSpan(Color.RED));
            }
        });
        materialCalendarView.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                return day.getMonth()+1+"월";
            }
        });
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();
                String shot_Day = Year + "-" + (Month<10 ? "0"+Month : Month)+ "-" + (Day<10 ? "0"+Day : Day);

                //목표량, 달성량 가져오기
                goalDBHelper= new GoalDBHelper(getContext());
                Goal goalDay = goalDBHelper.selectGoalAchieveByDate(shot_Day);
                goalDBHelper.close();
                recyclerViewSetting(goalDay);
            }
        });
    }

    public void goalDotDecoration(){
        ArrayList<Goal> goalArrayList = goalDBHelper.selectAllData();
        HashMap<String, ArrayList<String>> hashMapDotDateList = new HashMap<>(goalArrayList.size());

        //Dot 찍을때 달성 체크를 0과 1로 구분. 1이면 달성
        for(Goal g : goalArrayList) {
            String s = "";
            s = (g.getGoalWord() <= g.getAchieveWord()) ? s + "1" : s + "0";
            s = (g.getGoalMemory() <= g.getAchieveMemory()) ? s + "1" : s + "0";
            s = (g.getGoalTest() <= g.getAchieveTest()) ? s + "1" : s + "0";
            s = (g.getGoalQuiz() <= g.getAchieveQuiz()) ? s + "1" : s + "0";

            ArrayList<String> labelDates;
            if (!hashMapDotDateList.containsKey(s)){
                labelDates = new ArrayList<>();
                labelDates.add(g.getDate());
            } else {
                labelDates = hashMapDotDateList.get(s);
                labelDates.add(g.getDate());
            }
            hashMapDotDateList.put(s,labelDates);
        }

        for (Map.Entry<String, ArrayList<String>> entry : hashMapDotDateList.entrySet()) {
            String[] res = entry.getValue().toArray(new String[entry.getValue().size()]);
            new ApiSimulator(res, entry.getKey()).executeOnExecutor(Executors.newSingleThreadExecutor());
        }
    }

}