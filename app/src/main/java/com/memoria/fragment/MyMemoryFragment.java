package com.memoria.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.memoria.R;

public class MyMemoryFragment extends Fragment {

    View view;

    public MyMemoryFragment() {
        // Required empty public constructor
    }

    public static MyMemoryFragment newInstance() {
        MyMemoryFragment fragment = new MyMemoryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mymemory, container, false);

        return view;
    }

}