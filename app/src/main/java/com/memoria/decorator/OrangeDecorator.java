//package com.memoria.decorator;
//
//import android.app.Activity;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.drawable.Drawable;
//
//import com.memoria.R;
//import com.prolificinteractive.materialcalendarview.CalendarDay;
//import com.prolificinteractive.materialcalendarview.DayViewDecorator;
//import com.prolificinteractive.materialcalendarview.DayViewFacade;
//import com.prolificinteractive.materialcalendarview.spans.DotSpan;
//
//import java.util.Collection;
//import java.util.HashSet;
//
//public class OrangeDecorator implements DayViewDecorator {
//
//
//
//    private final Drawable drawable;
//    private int color;
//    private HashSet<CalendarDay> dates;
//
//    public OrangeDecorator(int color, Collection<CalendarDay> dates, Activity context) {
//        drawable = context.getResources().getDrawable(R.drawable.more);
//        this.color = color;
//        this.dates = new HashSet<>(dates);
//    }
//
//    @Override
//    public boolean shouldDecorate(CalendarDay day) {
//        return false;
//    }
//
//    @Override
//    public void decorate(DayViewFacade view) {
//        view.addSpan(new OrangeDecorator(6,new CustomSpan(Color.GREEN, 1)));
//    }
//
//    private static class CustomSpan extends DotSpan {
//        private int color;
//        private int xOffset;
//        private float radius = 3;
//
//        CustomSpan(int color, int xOffset){
//            this.color = color;
//            this.xOffset = xOffset;
//        }
//
//        @Override
//        public void drawBackground(Canvas canvas, Paint paint, int left, int right, int top, int baseline,
//                                   int bottom, CharSequence text, int start, int end, int lnum) {
//
//            int oldColor = paint.getColor();
//            if (color != 0) {
//                paint.setColor(color);
//            }
//
//            canvas.drawCircle((left + right) / 2 - 20, bottom + radius, radius, paint);
//            paint.setColor(oldColor);
//
//        }
//    }
//}
