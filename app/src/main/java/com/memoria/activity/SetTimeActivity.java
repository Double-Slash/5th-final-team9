package com.memoria.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.memoria.R;

public class SetTimeActivity extends AppCompatActivity {

    String[] items = {"1일", "2일", "3일", "4일", "5일", "6일", "7일", "8일", "9일", "10일",
            "11일", "12일", "13일", "14일", "15일", "16일", "17일", "18일", "19일", "20일",
            "21일", "22일", "23일", "24일", "25일", "26일", "27일", "28일", "29일", "30일"};

    private Spinner spinner;
    private Button spinnerButton;
    private int time;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        spinner= (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        time = position + 1;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        time = 2;
                    }
                }
        );

        spinnerButton = findViewById(R.id.spinnerButton);
        spinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetTimeActivity.this, MainActivity.class);
//               intent.putExtra("time",time);
                Bundle bundle = new Bundle();
                String datestr = Integer.toString(time);
                bundle.putString("time", datestr );
                Fragment fragment = new Fragment();
                Log.d("bundle",datestr);
                fragment.setArguments(bundle);
                startActivity(intent);
           }
        }

        );

    }
}