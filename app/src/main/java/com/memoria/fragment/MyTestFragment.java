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

public class MyTestFragment extends Fragment {

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
        view = inflater.inflate(R.layout.fragment_test, container, false);

        myWordDBHelper = new MyWordDBHelper(getContext());
        myWords = myWordDBHelper.selectWordGroupList();

        gridView = view.findViewById(R.id.test_grid_list);
        adapter = new GridAdapter(getActivity(), 0, myWords);
        gridView.setAdapter(adapter);

        start=view.findViewById(R.id.btn_test_start);
        comment=view.findViewById(R.id.test_comment);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
            }
        });

        return view;
    }
}
