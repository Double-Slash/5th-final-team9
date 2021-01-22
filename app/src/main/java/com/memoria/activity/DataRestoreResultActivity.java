package com.memoria.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.memoria.R;

import java.util.ArrayList;
import java.util.Arrays;

public class DataRestoreResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_restore_result);

        Button backMainBtn = findViewById(R.id.startButton);
        backMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataRestoreResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        PieChart pieChart = findViewById(R.id.piechart);
//        ArrayList NoOfEmp = new ArrayList();
//
//        NoOfEmp.add(new Entry(60, 0));
//        NoOfEmp.add(new Entry(40, 1));
////        NoOfEmp.add(new Entry(1133f, 2));
////        NoOfEmp.add(new Entry(1240f, 3));
////        NoOfEmp.add(new Entry(1369f, 4));
////        NoOfEmp.add(new Entry(1487f, 5));
////        NoOfEmp.add(new Entry(1501f, 6));
////        NoOfEmp.add(new Entry(1645f, 7));
////        NoOfEmp.add(new Entry(1578f, 8));
////        NoOfEmp.add(new Entry(1695f, 9));
//
//
//        PieDataSet dataSet = new PieDataSet(NoOfEmp, "score");
//        ArrayList year = new ArrayList();
//        year.add("2008");
//        year.add("2009");
////        year.add("2010");
////        year.add("2011");
////        year.add("2012");
////        year.add("2013");
////        year.add("2014");
////        year.add("2015");
////        year.add("2016");
////        year.add("2017");
//
//        int[] colorData = {Color.parseColor("#7AEAC3"),
//                Color.parseColor("#FFFFFF"),};
//
//        Integer[] colorDataInteger = {Color.parseColor("#7AEAC3"),
//                Color.parseColor("#FFFFFF"),};
//
//        PieData data = new PieData(year, dataSet);
//        pieChart.setData(data);
//        dataSet.setColors(colorData);
//        dataSet.setValueTextColors(Arrays.asList(colorDataInteger));
////        dataSet.setColors(Collections.singletonList(getResources().getColor(R.color.main_mint)));
//        pieChart.animateXY(2000, 2000);
//        pieChart.setCenterText(" ");
//        pieChart.setCenterTextSize(Float.MIN_VALUE);
//        pieChart.setDrawCenterText(false);
//        pieChart.setDrawSliceText(false);
//        pieChart.setDescriptionColor(Color.parseColor("#FFFFFF"));
//        pieChart.setDescriptionTextSize(Float.MIN_VALUE);
//        pieChart.setCenterTextRadiusPercent(Float.MIN_VALUE);
////        pieChart.set

    }

}