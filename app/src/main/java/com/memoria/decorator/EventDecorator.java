package com.memoria.decorator;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.memoria.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.ArrayList;
import java.util.Collection;

public class EventDecorator implements DayViewDecorator {

    private int[] colors;
    private ArrayList<CalendarDay> dates;
    private Drawable drawable;
    Context context;

    public EventDecorator(int[] colors, Collection<CalendarDay> dates, Context context) {
        drawable = context.getResources().getDrawable(R.drawable.calendar_selector_color);
        this.colors = colors;
        this.dates = new ArrayList<>(dates);
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
//        view.setBackgroundDrawable(drawable);
        view.setSelectionDrawable(drawable);
        view.addSpan(new CustmMultipleDotSpan(5, colors)); // 날자밑에 점

    }
}