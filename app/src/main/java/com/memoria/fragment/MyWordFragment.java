package com.memoria.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.memoria.R;

public class MyWordFragment extends Fragment {

    View view;

    public MyWordFragment() {
        // Required empty public constructor
    }

    public static MyWordFragment newInstance() {
        MyWordFragment fragment = new MyWordFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_myword, container, false);




        return view;
    }

}
