package com.memoria.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.memoria.R;
import com.memoria.adapter.GridAdapter;
import com.memoria.dbhelper.MyWordDBHelper;
import com.memoria.modeldata.MyWord;

import java.util.ArrayList;

public class MyTestFragment2 extends Fragment {

    View view;
    Button start;
    TextView comment;
    public static final int REQUEST_CODE = 100;

    private MyWordDBHelper myWordDBHelper;

    ArrayList<MyWord> myWords = new ArrayList<>();
    GridAdapter adapter;
    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test2, container, false);

        myWordDBHelper = new MyWordDBHelper(getContext());
        myWords = myWordDBHelper.selectWordGroupList();

        gridView = view.findViewById(R.id.test_grid_list2);
        adapter = new GridAdapter(getActivity(), 0, myWords);
        gridView.setAdapter(adapter);

        return view;
    }
}
