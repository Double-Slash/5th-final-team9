package com.memoria.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.memoria.R;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetTimeActivity extends AppCompatActivity {

    String[] items = {"1일", "2일", "3일", "4일", "5일", "6일", "일주일", "한달"};

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