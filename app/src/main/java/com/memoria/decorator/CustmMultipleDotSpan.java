package com.memoria.decorator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

import static com.prolificinteractive.materialcalendarview.spans.DotSpan.DEFAULT_RADIUS;

public class CustmMultipleDotSpan implements LineBackgroundSpan {


    private final float radius;
    private int[] color = new int[4];


    public CustmMultipleDotSpan() {
        this.radius = DEFAULT_RADIUS;
        this.color[0] = 0;
    }


    public CustmMultipleDotSpan(int color) {
        this.radius = DEFAULT_RADIUS;
        this.color[0] = color;
    }


    public CustmMultipleDotSpan(float radius) {
        this.radius = radius;
        this.color[0] = 0;
    }


    public CustmMultipleDotSpan(float radius, int[] color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void drawBackground(
            Canvas canvas, Paint paint,
            int left, int right, int top, int baseline, int bottom,
            CharSequence charSequence,
            int start, int end, int lineNum
    ) {
        if( color==null){
            paint.setColor(Color.GRAY);
            return;
        }

        int total = Math.min(color.length, 5);
        int leftMost = (total - 1) * - 8 ;

        for (int i = 0; i < total; i++) {
            int oldColor = paint.getColor();
            if (color[i] != 0) {
                paint.setColor(color[i]);
            }
            canvas.drawCircle((left + right) / 2 - leftMost, bottom + radius, radius, paint);
            paint.setColor(oldColor);
            leftMost = leftMost + 14;
            //leftMost는 Dot간의 거리
        }
    }
}