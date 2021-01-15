package com.memoria.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.memoria.R;
import com.memoria.activity.MainActivity;
import com.memoria.adapter.GridAdapter;
import com.memoria.dbhelper.MyWordDBHelper;
import com.memoria.modeldata.MyWord;

import java.util.ArrayList;

public class MyTestFragment extends Fragment {

    View view;
    Button start;
    MyTestFragment2 fragment2;
    MainActivity activity;
    public static final int REQUEST_CODE = 100;

    private MyWordDBHelper myWordDBHelper;

    ArrayList<MyWord> myWords = new ArrayList<>();
    GridAdapter adapter;
    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test, container, false);

        myWordDBHelper = new MyWordDBHelper(getContext());
        myWords = myWordDBHelper.selectWordGroupList();

        gridView = view.findViewById(R.id.test_grid_list);
        adapter = new GridAdapter(getActivity(), 0, myWords);
        gridView.setAdapter(adapter);

        gridView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        activity=(MainActivity)getActivity();
        fragment2=new MyTestFragment2();

        start=view.findViewById(R.id.btn_test_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(fragment2);
            }
        });

        return view;
    }
}
